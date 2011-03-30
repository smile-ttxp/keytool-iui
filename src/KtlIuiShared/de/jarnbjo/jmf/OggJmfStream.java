/*
 * $ProjectName$
 * $ProjectRevision$
 * -----------------------------------------------------------
 * $Id: OggJmfStream.java,v 1.2 2003/03/31 00:23:18 jarnbjo Exp $
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
 * $Log: OggJmfStream.java,v $
 * Revision 1.2  2003/03/31 00:23:18  jarnbjo
 * no message
 *
 * Revision 1.1  2003/03/03 22:06:12  jarnbjo
 * no message
 *
 */

package de.jarnbjo.jmf;

import java.io.*;
import java.util.*;


import javax.media.protocol.PullSourceStream;
import javax.media.protocol.Seekable;

import de.jarnbjo.ogg.*;

/**
 * Implementation of the <code>PhysicalOggStream</code> interface for accessing
 * JMF (Java Media Framework) media streams.
 */

public class OggJmfStream implements PhysicalOggStream {

   private boolean closed=false;
   private PullSourceStream source;
   private long[] pageOffsets;
   private long numberOfSamples=-1;

   private HashMap<Integer, LogicalOggStreamImpl> _hmpLogicalStreams=new HashMap<Integer, LogicalOggStreamImpl>();

   /**
    * Creates access to the specified <code>PullSourceStream</code> through the
    * <code>PhysicalOggStream</code> interface.
    * The specified source must implement the <code>Seekable</code> interface.
    *
    * @param source the media stream to read from
    *
    * @throws OggFormatException if the stream format is incorrect
    * @throws IOException if some other IO error occurs when reading the stream
    */

   public OggJmfStream(PullSourceStream source) throws OggFormatException, FileNotFoundException, IOException {
      this.source=source;

      if(!(source instanceof Seekable)) {
         throw new OggFormatException("The source stream must be seekable.");
      }

      ArrayList<Long> altPO = new ArrayList<Long>();
      int pageNumber=0;
      
      try 
      {
         while(true) 
         {
            long startPos=((Seekable)source).tell();
            altPO.add(new Long(((Seekable)source).tell()));

            // skip data if pageNumber>0
            OggPage op = getNextPage(true);
            ((Seekable)source).seek(((Seekable)source).tell()+op.getTotalLength());

            LogicalOggStreamImpl los=(LogicalOggStreamImpl)getLogicalStream(op.getStreamSerialNumber());
            if(los==null) {
               los=new LogicalOggStreamImpl(this, op.getStreamSerialNumber());
               this._hmpLogicalStreams.put(new Integer(op.getStreamSerialNumber()), los);
               ((Seekable)source).seek(startPos);
               op=getNextPage();
               los.checkFormat(op);
            }

            /*
            if(pageNumber==0) {
               los.checkFormat(op);
            }
            */

            los.addPageNumberMapping(pageNumber);
            los.addGranulePosition(op.getAbsoluteGranulePosition());

            pageNumber++;
         }
      }
      catch(EndOfOggStreamException e) {
         // ok
      }
      catch(IOException e) {
         throw e;
      }
      ((Seekable)source).seek(0L);
      pageOffsets=new long[altPO.size()];
      int i=0;
      Iterator iter=altPO.iterator();
      while(iter.hasNext()) {
         pageOffsets[i++]=((Long)iter.next()).longValue();
      }
   }

   public Collection getLogicalStreams() {
      return this._hmpLogicalStreams.values();
   }

   public boolean isOpen() {
      return !closed;
   }

   public void close() throws IOException {
      closed=true;
      //source.
   }

   private OggPage getNextPage() throws EndOfOggStreamException, IOException, OggFormatException  {
      return getNextPage(false);
   }

   private OggPage getNextPage(boolean skipData) throws EndOfOggStreamException, IOException, OggFormatException  {
      return OggJmfPage.create(source, skipData);
   }

   public OggPage getOggPage(int index) throws IOException {
      ((Seekable)source).seek(pageOffsets[index]);
      return OggJmfPage.create(source);
   }

   public LogicalOggStream getLogicalStream(int serialNumber) {
      return (LogicalOggStream)this._hmpLogicalStreams.get(new Integer(serialNumber));
   }

   public void setTime(long granulePosition) throws IOException {
      for(Iterator iter=this._hmpLogicalStreams.values().iterator(); iter.hasNext(); ) {
         LogicalOggStream los=(LogicalOggStream)iter.next();
         los.setTime(granulePosition);
      }
   }

   public boolean isSeekable() {
      return true;
   }
}