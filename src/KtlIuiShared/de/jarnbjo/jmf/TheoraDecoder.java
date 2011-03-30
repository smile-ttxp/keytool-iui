/*
 * $ProjectName$
 * $ProjectRevision$
 * -----------------------------------------------------------
 * $Id: TheoraDecoder.java,v 1.1 2003/03/03 22:06:12 jarnbjo Exp $
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
 * $Log: TheoraDecoder.java,v $
 * Revision 1.1  2003/03/03 22:06:12  jarnbjo
 * no message
 *
 */
 
package de.jarnbjo.jmf;

import java.io.IOException;
import java.awt.Dimension;
import javax.media.*;
import javax.media.format.*;

import de.jarnbjo.theora.*;


public class TheoraDecoder implements Codec {

   private static final String CODEC_NAME = "Theora decoder";

   private static final Format[] supportedInputFormats=new Format[] {
      new VideoFormat("THEORA")
   };

   private static final Format[] supportedOutputFormats=new Format[] {
      new RGBFormat(null, -1, VideoFormat.intArray, -1.0f, 32, 0xff0000, 0xff00, 0xff)
      //new YUVFormat(null, -1, VideoFormat.byteArray, -1.0f, YUVFormat.YUV_422, -1, -1, -1, -1, -1)
   };

   private TheoraStream theoraStream=new TheoraStream();
   private Format currentFormat;

   public TheoraDecoder() {
   }

   public Format[] getSupportedInputFormats() {
      return supportedInputFormats;
   }

   public Format[] getSupportedOutputFormats(Format input) {
      System.out.println("input: "+input);
      if(input==null) {
         return supportedOutputFormats;
      }
      else {
         VideoFormat vf=(VideoFormat)input;
         Format[] res=new Format[1];
         res[0]=new RGBFormat(vf.getSize(), vf.getMaxDataLength(), Format.intArray, vf.getFrameRate(), 32, 0xff0000, 0xff00, 0xff);
         return res;
      }
   }

   boolean first=true;
   long sequence=0;

   public int process(Buffer in, Buffer out) {

      if(1==1) return PlugIn.OUTPUT_BUFFER_NOT_FILLED;

      int[] data=(int[])out.getData();
      if(data==null || data.length<352*208) {
         data=new int[352*208*3];
         java.util.Arrays.fill(data, 4711);
         out.setData(data);
      }


      //for(int i=0; i<data.length; i++) {
      //   data[i]=(byte)(Math.random()*256.0);
      //}

      /*
      currentFormat=new RGBFormat(
         new Dimension(352, 208),
         Format.NOT_SPECIFIED,
         //data.length,
         Format.byteArray,
         24.0f, 24, 1, 2, 3);
      */

      //out.setData(data);
      //out.setFormat(currentFormat);
      out.setSequenceNumber(sequence);
      //out.setDuration(1000000000L/24L);
      long time=1000000000L*sequence/24L;
      System.out.println("v: "+time);
      out.setTimeStamp(time);
      out.setFlags(Buffer.FLAG_NO_DROP);
      sequence++;
      out.setOffset(0);
      out.setLength(data.length);
      //out.setFormat(currentFormat);

      if(1==1) return PlugIn.BUFFER_PROCESSED_OK;

      try {
         //OggPage op=(OggPage)in.getData();

         if(first) {
            first=false;
            byte[] res=theoraStream.decodePacket((byte[])in.getData());
            if(res==null) {
               return PlugIn.OUTPUT_BUFFER_NOT_FILLED;
            }
         }
         Header header=theoraStream.getHeader();
         byte[] res=new byte[119808];
         currentFormat=new YUVFormat(
            new Dimension(header.getWidth(), header.getHeight()),
            res.length,
            YUVFormat.byteArray,
            (float)theoraStream.getHeader().getFrameRate(),
            YUVFormat.YUV_420,
            theoraStream.getPbi().getYStride(),
            theoraStream.getPbi().getUvStride(),
            theoraStream.getPbi().getYOffset(),
            header.getHeight()*theoraStream.getPbi().getYStride(),
            header.getHeight()*theoraStream.getPbi().getYStride()*5/4);
         //   theoraStream.getPbi().getUOffset(),
         //   theoraStream.getPbi().getVOffset());

         System.out.println("--frame info--");
         System.out.println("length: "+res.length);
         System.out.println(theoraStream.getPbi().getYStride());
         System.out.println(theoraStream.getPbi().getUvStride());
         System.out.println(theoraStream.getPbi().getYOffset());
         System.out.println(theoraStream.getPbi().getUOffset());
         System.out.println(theoraStream.getPbi().getVOffset());
         System.out.println("--frame info--");

         for(int i=0; i<res.length; i++) {
            res[i]=(byte)(i);
         }

         out.setData(res);
         out.setOffset(0);
         out.setLength(res.length);
         out.setFormat(currentFormat);
         //out.setSequenceNumber(op.getPageSequenceNumber());
         //out.setFlags(Buffer.FLAG_KEY_FRAME);
         return PlugIn.BUFFER_PROCESSED_OK;
      }
      catch(TheoraFormatException e) {
         e.printStackTrace();
         return PlugIn.BUFFER_PROCESSED_FAILED;
      }
      catch(IOException e) {
         e.printStackTrace();
         return PlugIn.BUFFER_PROCESSED_FAILED;
      }
   }

   public Format setInputFormat(Format format) {
      return format;
   }

   public Format setOutputFormat(Format format) {
      return format;
   }

   public void open() {
   }

   public void close() {
   }

   public void reset() {
   }

   public String getName() {
      return CODEC_NAME;
   }

   public Object getControl(String controlType) {
      return null;
   }

   public Object[] getControls() {
      return null;
   }
}