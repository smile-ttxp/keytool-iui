/*
 * $ProjectName$
 * $ProjectRevision$
 * -----------------------------------------------------------
 * $Id: OggParser.java,v 1.1 2003/03/03 22:06:12 jarnbjo Exp $
 * -----------------------------------------------------------
 *
 * $Author: jarnbjo $
 *
 * Description:
 *
 * Copyright 2002-2003 Tor-Einar Jarnbjo
 * -----------------------------------------------------------
 *
 * Change History
 * -----------------------------------------------------------
 * $Log: OggParser.java,v $
 * Revision 1.1  2003/03/03 22:06:12  jarnbjo
 * no message
 *
 */
 
package de.jarnbjo.jmf;

import java.io.*;
import java.util.*;

import javax.media.*;
import javax.media.format.*;
import javax.media.protocol.*;

import javax.swing.*;

import de.jarnbjo.ogg.*;
import de.jarnbjo.vorbis.*;


public class OggParser implements Demultiplexer {

   private static final String DEMULTIPLEXER_NAME = "Ogg demultiplexer";

   private final ContentDescriptor[] supportedContentTypes = new ContentDescriptor[] {
      new ContentDescriptor(ContentDescriptor.mimeTypeToPackageName("application/ogg")),
      new ContentDescriptor(ContentDescriptor.mimeTypeToPackageName("application/x-ogg"))
   };

   private Track[] tracks;

   private PullDataSource source;
   private PullSourceStream stream;
   private PhysicalOggStream oggStream;

   public OggParser() {
   }

   public Time getDuration() {
      if(tracks==null) {
         return Time.TIME_UNKNOWN;
      }
      long max=0;
      for(int i=0; i<tracks.length; i++) {
         if(tracks[i].getDuration().getNanoseconds()>max) {
            max=tracks[i].getDuration().getNanoseconds();
         }
      }
      return new Time(max);//Time.TIME_UNKNOWN;
   }

   public ContentDescriptor[] getSupportedInputContentDescriptors() {
      return supportedContentTypes;
   }

   public Track[] getTracks() throws BadHeaderException, IOException 
   {
      if(tracks==null) 
      {
         try 
         {
            Collection coll=oggStream.getLogicalStreams();
            //tracks=new Track[1];
            //tracks[0]=OggTrack.createInstance((LogicalOggStream)coll.iterator().next());
            tracks=new Track[coll.size()];
            int i=0;
            
            for(Iterator iter=coll.iterator(); iter.hasNext(); i++) 
            {
               LogicalOggStream los=(LogicalOggStream)iter.next();
               //System.out.println("type: "+los.getFormat());
               tracks[i]=OggTrack.createInstance(los);
            }
         }
         catch(OggFormatException e) {
            throw new BadHeaderException(e.getMessage());
         }
         catch(VorbisFormatException e) {
            throw new BadHeaderException(e.getMessage());
         }
      }
      return tracks;
   }

   public boolean isPositionable() {
      return true;
   }

   public boolean isRandomAccess() {
      return true;
   }

   public Time getMediaTime() {
      /** @todo implement */
      return Time.TIME_UNKNOWN;
   }

   public Time setPosition(Time time, int rounding) {

      try {
         if(tracks[0] instanceof VorbisTrack) {
            long sampleRate=((VorbisTrack)tracks[0]).getSampleRate();
            oggStream.setTime(time.getNanoseconds()*sampleRate/1000000000L);
         }
         else if(tracks[0] instanceof FlacTrack) {
            long sampleRate=((FlacTrack)tracks[0]).getSampleRate();
            oggStream.setTime(time.getNanoseconds()*sampleRate/1000000000L);
         }
      }
      catch(IOException e) {
         e.printStackTrace();
      }

      /** @todo implement */
      return Time.TIME_UNKNOWN;
   }

   public void start() throws IOException {
      if(source!=null) {
         source.start();
      }
   }

   public void stop()  {
      if(source!=null) {
         try {
            source.stop();
         }
         catch(IOException e) {
            // ignore
         }
      }
   }

   public void open() {
      // nothing to be done
   }

   public void close() {
      if(source!=null) {
         try {
            source.stop();
            source.disconnect();
         }
         catch(IOException e) {
            // ignore
         }
         source=null;
      }
   }

   public void reset() {
      setPosition(new Time(0), 0);
   }

   public String getName() {
      return DEMULTIPLEXER_NAME;
   }

   public void setSource(DataSource source) throws IOException, IncompatibleSourceException {

      try {
         if(!(source instanceof PullDataSource)) {
            /** @todo better message */
            throw new IncompatibleSourceException("DataSource not supported: " + source);
         }

         this.source=(PullDataSource)source;

         if(this.source.getStreams()==null || this.source.getStreams().length==0) {
            throw new IOException("DataSource has no streams.");
         }

         if(this.source.getStreams().length>1) {
            throw new IOException("This demultiplexer only supports datasources with one stream.");
         }

         stream=this.source.getStreams()[0];
         oggStream=new OggJmfStream(stream);

         if(!(stream instanceof Seekable)) {
            /** @todo better message */
            throw new IncompatibleSourceException("Stream is not seekable.");
         }
      }
      catch(IncompatibleSourceException e) {
         e.printStackTrace();
         throw e;
      }
      catch(IOException e) {
         e.printStackTrace();
         throw e;
      }
      catch(RuntimeException e) {
         e.printStackTrace();
         throw e;
      }
   }

   public Object getControl(String controlType) {
      return null;
   }

   public Object[] getControls() {
      return new Object[0];
   }

}