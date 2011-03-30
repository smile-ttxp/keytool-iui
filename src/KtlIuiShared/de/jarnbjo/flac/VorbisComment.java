package de.jarnbjo.flac;

import java.io.IOException;
import java.util.*;
import de.jarnbjo.util.io.*;

public class VorbisComment extends MetadataBlock {

   public static final String TITLE = "TITLE";
   public static final String ARTIST = "ARTIST";
   public static final String ALBUM = "ALBUM";
   public static final String TRACKNUMBER = "TRACKNUMBER";
   public static final String VERSION = "VERSION";
   public static final String PERFORMER = "PERFORMER";
   public static final String COPYRIGHT = "COPYRIGHT";
   public static final String LICENSE = "LICENSE";
   public static final String ORGANIZATION = "ORGANIZATION";
   public static final String DESCRIPTION = "DESCRIPTION";
   public static final String GENRE = "GENRE";
   public static final String DATE = "DATE";
   public static final String LOCATION = "LOCATION";
   public static final String CONTACT = "CONTACT";
   public static final String ISRC = "ISRC";

   private String vendor;
   private HashMap<String, ArrayList<String>> _hmpComments = new HashMap<String, ArrayList<String>>();

   public VorbisComment(BitInputStream source) throws FlacFormatException, IOException {

      int length=source.getInt(24);

      source.setEndian(BitInputStream.LITTLE_ENDIAN);

      vendor=getString(source);
      System.out.println("VENDOR = "+vendor);

      int ucLength=source.getInt(32);

      for(int i=0; i<ucLength; i++) {
         String comment=getString(source);
         int ix=comment.indexOf('=');
         String strKey = comment.substring(0, ix);
         String strValue = comment.substring(ix+1);
         //this._hmpComments.put(strKey, strValue);
         addComment(strKey, strValue);
      }
   }

   private void addComment(String strKey, String strValue) {

      System.out.println(strKey + " = "+strValue);

      ArrayList<String> altStringComment = this._hmpComments.get(strKey);
      
      if(altStringComment == null) 
      {
         altStringComment = new ArrayList<String>();
         this._hmpComments.put(strKey, altStringComment);
      }
      
      
      altStringComment.add(strValue);
   }

   public String getVendor() {
      return vendor;
   }

   public String getComment(String strKey) {
       
       
      ArrayList<String> altStrComment = this._hmpComments.get(strKey);
      
      return altStrComment == null?(String)null:(String) altStrComment.get(0);
   }

   public String[] getComments(String strKey) {
      ArrayList<String> altStringComment = (ArrayList<String>) this._hmpComments.get(strKey);
      return altStringComment==null?new String[0]:(String[])altStringComment.toArray(new String[altStringComment.size()]);
   }

   public String getTitle() {
      return getComment(TITLE);
   }

   public String[] getTitles() {
      return getComments(TITLE);
   }

   public String getVersion() {
      return getComment(VERSION);
   }

   public String[] getVersions() {
      return getComments(VERSION);
   }

   public String getAlbum() {
      return getComment(ALBUM);
   }

   public String[] getAlbums() {
      return getComments(ALBUM);
   }

   public String getTrackNumber() {
      return getComment(TRACKNUMBER);
   }

   public String[] getTrackNumbers() {
      return getComments(TRACKNUMBER);
   }

   public String getArtist() {
      return getComment(ARTIST);
   }

   public String[] getArtists() {
      return getComments(ARTIST);
   }

   public String getPerformer() {
      return getComment(PERFORMER);
   }

   public String[] getPerformers() {
      return getComments(PERFORMER);
   }

   public String getCopyright() {
      return getComment(COPYRIGHT);
   }

   public String[] getCopyrights() {
      return getComments(COPYRIGHT);
   }

   public String getLicense() {
      return getComment(LICENSE);
   }

   public String[] getLicenses() {
      return getComments(LICENSE);
   }

   public String getOrganization() {
      return getComment(ORGANIZATION);
   }

   public String[] getOrganizations() {
      return getComments(ORGANIZATION);
   }

   public String getDescription() {
      return getComment(DESCRIPTION);
   }

   public String[] getDescriptions() {
      return getComments(DESCRIPTION);
   }

   public String getGenre() {
      return getComment(GENRE);
   }

   public String[] getGenres() {
      return getComments(GENRE);
   }

   public String getDate() {
      return getComment(DATE);
   }

   public String[] getDates() {
      return getComments(DATE);
   }

   public String getLocation() {
      return getComment(LOCATION);
   }

   public String[] getLocations() {
      return getComments(LOCATION);
   }

   public String getContact() {
      return getComment(CONTACT);
   }

   public String[] getContacts() {
      return getComments(CONTACT);
   }

   public String getIsrc() {
      return getComment(ISRC);
   }

   public String[] getIsrcs() {
      return getComments(ISRC);
   }


   private String getString(BitInputStream source) throws IOException {

      int length=source.getInt(32);

      System.out.println("length: "+length);

      byte[] strArray=new byte[length];

      for(int i=0; i<length; i++) {
         strArray[i]=(byte)source.getInt(8);
      }

      return new String(strArray, "UTF-8");
   }

}