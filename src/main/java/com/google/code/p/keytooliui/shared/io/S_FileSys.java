/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 
package com.google.code.p.keytooliui.shared.io;


import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;
import java.net.*;

public class S_FileSys
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.S_FileSys";
    private static final String _f_s_strDirTmp = "temp";
    
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".S_FileSys" // class name
        ;
        
    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strDialogFileProtectedTitle = null;
    private static String _s_strDialogFileProtectedBodyR = null;
    private static String _s_strDialogFileProtectedBodyW = null;
    

    // ------------------
    // STATIC INITIALIZER

    static
    {
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
            
            _s_strDialogFileProtectedTitle = rbeResources.getString("dialogFileProtectedTitle");
            _s_strDialogFileProtectedBodyR = rbeResources.getString("dialogFileProtectedBodyR");
            _s_strDialogFileProtectedBodyW = rbeResources.getString("dialogFileProtectedBodyW");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(_f_s_strClass, "excMissingResource caught");
        }
    } 
    
    // -------------
    // PUBLIC STATIC
    
    // should be renamed: stg like s_getPathAbsParentAppliCache
    public static File s_getPathAbsParentAppli(boolean blnReadOnlyAllowed)
    {
        String strMethod = _f_s_strClass + "." + "s_getPathAbsParentAppli(blnReadOnlyAllowed)";
        
        if (_s_fleParentAppli != null)
            return _s_fleParentAppli;
        
        // beg april 13, 2007
        // trick
        
        //if (com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws())
        if (true)
        {
            String str = /*System.getProperty("deployment.user.cachedir");
            
            if (str == null)
                str =*/ System.getProperty("user.home");
            
            if (str == null)
            {
                MySystem.s_printOutWarning(strMethod, "nil str, for property user.home");
                str = System.getProperty("user.dir");
                 
                if (str == null)
                {
                     MySystem.s_printOutError(strMethod, "nil str, for property user.dir");
                     return null;
                }
            }
            
           
            
            File fle = new File(str);
            
            if (! fle.exists())
            {
                MySystem.s_printOutError(strMethod, "! fle.exists(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
                return null;
            }
            
            File fleNew = new File(fle, System.getProperty("_appli.cache"));
            
            if (! fleNew.exists())
            {
                try
                {
                    if (! fleNew.mkdir())
                    {
                        // !!!!!!!!!!!!!!! blnReadOnlyAllowed !!!!!!!!!!!!!!! done in a hurry !!!!!!!!
                        MySystem.s_printOutError(strMethod, "failed to create, fleNew.getAbsolutePath()=" + fleNew.getAbsolutePath());
                        return null;
                    }
                }
                
                catch(Exception exc)
                {
                    exc.printStackTrace();
                    MySystem.s_printOutError(strMethod, "exc caught, fleNew.getAbsolutePath()=" + fleNew.getAbsolutePath());
                    return null;
                }
            }
            
            S_FileSys._s_fleParentAppli = fleNew;
            return fleNew;
        }
        
        // end april 13, 2007

        URL urlResource = null;
        
        try
        {
            //urlResource = this.getClass().getResource("S_FileSys.class");
            S_FileSys sfi = new S_FileSys();
            urlResource = sfi.getClass().getResource("S_FileSys.class");
            sfi = null; // dec 30, 05
        
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        if (urlResource == null)
        {
            MySystem.s_printOutError(strMethod, "nil urlResource");
            return null;
        }

        String strProtocol = urlResource.getProtocol();
        
        if (strProtocol.equalsIgnoreCase("file"))
        {
            // do job
            File fleFile = _s_getPathAbsParentAppliFromUrlFile(urlResource, blnReadOnlyAllowed);
            
            if (fleFile == null)
            {
                MySystem.s_printOutError(strMethod, "failed");
                return null;
            }
            
            _s_fleParentAppli = fleFile;
            
            // return value
            return fleFile;
        }
        
        if (strProtocol.equalsIgnoreCase(S_FileExtension.f_s_strJARDocument))
        {
             // do job
            File fleJar = _s_getPathAbsParentAppliFromUrlJar(urlResource, blnReadOnlyAllowed);
            
            if (fleJar == null)
            {
                MySystem.s_printOutError(strMethod, "failed");
                return null;
            }
            
            _s_fleParentAppli = fleJar;
            
            // return value
            return fleJar;
        }
        
        // unsupported protocol
        MySystem.s_printOutError(strMethod, "unsupported protocol: " + strProtocol);        
        return null;
    }
    
    
   
    
    // -------------
    // PUBLIC STATIC
    /**
        if any error, exiting
    **/
    public static boolean s_tryRename(String strParentPathAbsolute, String strNameFrom, String strNameTo)
    {
        final String f_strWhere = _f_s_strClass + "." + "s_tryRename(strParentPathAbsolute, strNameFrom, strNameTo)";
        
        if (strParentPathAbsolute==null || strNameFrom==null || strNameTo==null)
        {
            MySystem.s_printOutExit(f_strWhere, "nil arg");
        }
        
        File fleParent = new File(strParentPathAbsolute);
        
        if (! fleParent.exists())
        {
            MySystem.s_printOutExit(f_strWhere, "! fleParent.exists");
        }
        
        if (! fleParent.isDirectory())
        {
            MySystem.s_printOutExit(f_strWhere, "! fleParent.isDirectory");
        }
        
        if (! fleParent.canWrite())
        {
            MySystem.s_printOutExit(f_strWhere, "! fleParent.canWrite");
        }
        
        File fleFrom = new File(fleParent, strNameFrom);
        
        if (! fleFrom.exists())
        {
            MySystem.s_printOutExit(f_strWhere, "! fleFrom.exists(), fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath());
        }
        
        if (fleFrom.isDirectory())
        {
            MySystem.s_printOutExit(f_strWhere, "fleFrom.isDirectory(), fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath());
        }
        
        if (! fleFrom.canRead())
        {
            MySystem.s_printOutExit(f_strWhere, "! fleFrom.canRead(), fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath());
        }
        
        if (! fleFrom.canWrite())
        {
            MySystem.s_printOutExit(f_strWhere, "! fleFrom.canWrite(), fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath());
        }
        
        File fleTo = new File(fleParent, strNameTo);
        
        if (fleTo.exists())
        {
            MySystem.s_printOutExit(f_strWhere, "fleTo.exists(), fleTo.getAbsolutePath()=" + fleTo.getAbsolutePath());
        }
        
      
        try
        {
            if (! fleFrom.renameTo(fleTo))
            {
                MySystem.s_printOutTrace(f_strWhere, "failed to rename, fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath() + ", fleTo.getAbsolutePath()=" + fleTo.getAbsolutePath());
                return false;
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "exc caught");
        }
        
        // ending
        return true;
    }
    
    public static boolean s_rename(String strParentPathAbsolute, String strNameFrom, String strNameTo)
    {
        final String f_strWhere = _f_s_strClass + "." + "s_rename(strParentPathAbsolute, strNameFrom, strNameTo)";
        
        if (strParentPathAbsolute==null || strNameFrom==null || strNameTo==null)
        {
            MySystem.s_printOutError(f_strWhere, "nil arg");
            return false;
        }
        
        File fleParent = new File(strParentPathAbsolute);
        
        if (! fleParent.exists())
        {
            MySystem.s_printOutError(f_strWhere, "! fleParent.exists");
            return false;
        }
        
        if (! fleParent.isDirectory())
        {
            MySystem.s_printOutError(f_strWhere, "! fleParent.isDirectory");
            return false;
        }
        
        if (! fleParent.canWrite())
        {
            MySystem.s_printOutError(f_strWhere, "! fleParent.canWrite");
            return false;
        }
        
        File fleFrom = new File(fleParent, strNameFrom);
        
        if (! fleFrom.exists())
        {
            MySystem.s_printOutError(f_strWhere, "! fleFrom.exists(), fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath());
            return false;
        }
        
        if (fleFrom.isDirectory())
        {
            MySystem.s_printOutError(f_strWhere, "fleFrom.isDirectory()");
            return false;
        }
        
        if (! fleFrom.canRead())
        {
            MySystem.s_printOutError(f_strWhere, "! fleFrom.canRead(), fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath());
            return false;
        }
        
        if (! fleFrom.canWrite())
        {
            MySystem.s_printOutError(f_strWhere, "! fleFrom.canWrite(), fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath());
            return false;
        }
        
        File fleTo = new File(fleParent, strNameTo);
        
        if (fleTo.exists())
        {
            MySystem.s_printOutError(f_strWhere, "fleTo.exists(), fleTo.getAbsolutePath()=" + fleTo.getAbsolutePath());
            return false;
        }
        
      
        try
        {
            if (! fleFrom.renameTo(fleTo))
            {
                MySystem.s_printOutError(f_strWhere, "failed to rename, fleFrom.getAbsolutePath()=" + fleFrom.getAbsolutePath() + ", fleTo.getAbsolutePath()=" + fleTo.getAbsolutePath());
                return false;
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(f_strWhere, "exc caught");
            return false;
        }
        
        // ending
        return true;
    }
    
    /**
        creating a temporary file from buffer "byte[]"
        finally returns (temporary file's) absolute path.
    **/
    public static File s_getFileTempFromBytes(byte[] bytsBuffer, String strFileSuffix)
    {
        String strMethod = _f_s_strClass + "." + "s_getFileTempFromBytes(bytsBuffer, strFileSuffix)";
        
        if (bytsBuffer==null || strFileSuffix==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }
        
        // -------------------------
        // creates a temp file
        
        final String f_strPrefix = "fby";
          
        File fle = S_FileSys.s_createTemp(f_strPrefix, strFileSuffix);

        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil fle");
            return null;
        }
        
        // --
        
        try
        {
            FileOutputStream fos = new FileOutputStream(fle);
           
            fos.write(bytsBuffer);
            fos.flush();
            fos.close();
            fos = null;
            //fos.flush();
        }
        
        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            return null;
        }
        
        return fle;
    }
    
    
    
    /**
        if any error, returns nil
        
        create a temp file, deleted on exit
    **/
    public static File s_createTemp(String strPrefix, String strSuffix)
    {
        String strMethod = _f_s_strClass + "." + "s_createTemp(strPrefix, strSuffix)";
        
        if (strPrefix==null || strSuffix==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }
        
        // ---------------
        // get or create fleParent: [appli.home]/tmp
        File fleDirParentAppli = S_FileSys.s_getPathAbsParentAppli(false);
        
        if (fleDirParentAppli == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleDirParentAppli");
            return null;
        }
        
        File fleDirTmp = new File(fleDirParentAppli, _f_s_strDirTmp);
        
        if (fleDirTmp.exists())
        {
            if (! fleDirTmp.isDirectory())
            {
                MySystem.s_printOutError(strMethod, "! fleDirTmp.isDirectory():" + fleDirTmp.getAbsolutePath());
                return null;
            }
        }
        
        else
        {
            fleDirTmp = s_mkdir(fleDirParentAppli, _f_s_strDirTmp);
            
            if (fleDirTmp == null)
            {
                MySystem.s_printOutError(strMethod, "failed");
                return null;
            }
        }
        
        
        // ---------------
        
        File fle = null;
        
        try
        {
            /* TEMPO
            
            cf Java1.3 API, recommended short prefix
            at max 3 chars!!!!!!!!
            
            
            fle = File.createTempFile(strPrefix, strSuffix, fleDirTmp);
            
            
            seems not to delete on exit if prefix is too big!
            
            */
            fle = File.createTempFile(strPrefix, strSuffix, fleDirTmp);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        
        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil fle");
            return null;
        }
           
        try
        {
            fle.deleteOnExit();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        // MORE: http://developer.java.sun.com/developer/bugParade/bugs/4171239.html
        MySystem.s_printOutFlagDev(strMethod, "EYES WIDE OPEN: fle.deleteOnExit(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
		
		return fle;
    }
    
    /**
        deleteFile, meaning file is NOT a directory!
    **/
    
    public static boolean s_deleteFile(String strFileAbsolutePath)
    {
        final String f_strWhere = _f_s_strClass + "." + "s_deleteFile(strFileAbsolutePath)";
        
        if (strFileAbsolutePath == null)
        {
            MySystem.s_printOutError(f_strWhere, "nil strFileAbsolutePath");
            return false;
        }
        
        File fle = new File(strFileAbsolutePath);
            
        if (fle == null)
        {
            MySystem.s_printOutError(f_strWhere, "nil fle, strFileAbsolutePath=" + strFileAbsolutePath);
            return false;      
        }
        
        if (! fle.exists())
        {
            MySystem.s_printOutError(f_strWhere, "nil fle, " + fle.getAbsolutePath());
            return false;  
        }
        
        if (! fle.canWrite())
        {
            MySystem.s_printOutError(f_strWhere, "cannot write fle, " + fle.getAbsolutePath());
            return false;  
        }
        
        if (fle.isDirectory())
        {
            MySystem.s_printOutError(f_strWhere, "fle is a directory, " + fle.getAbsolutePath());
            return false;  
        }
        
        if (! fle.delete())
        {
            MySystem.s_printOutError(f_strWhere, "cannot delete fle, " + fle.getAbsolutePath());
            return false;  
        }
        
        return true;
    }
    
    public static File s_mkdir(File fleParent, String strName)
    {
        String strWhere = _f_s_strClass + "." + "s_mkdir(fleParent, strName)";
        
        if (fleParent == null)
        {
            MySystem.s_printOutError(strWhere, "nil fleParent");
            return null;
        }
        
        if (strName == null)
        {
            MySystem.s_printOutError(strWhere, "nil strName");
            return null;
        }
        
        String strAbsolutePath = fleParent.getAbsolutePath();
        strAbsolutePath += File.separator;
        strAbsolutePath += strName;
        
        return s_mkdir(strAbsolutePath);
    }
    
    public static File s_mkdir(String strAbsolutePath)
    {
        String strWhere = _f_s_strClass + "." + "s_mkdir(strAbsolutePath)";
        
        if (strAbsolutePath == null)
        {
            MySystem.s_printOutError(strWhere, "nil strAbsolutePath");
            return null;
        }
        
        File fleDirectory = new File(strAbsolutePath);
        
        if(fleDirectory.exists())
		{
		    MySystem.s_printOutError(strWhere, "already existing: " + strAbsolutePath);
            return null;
		}
		
		boolean blnOk = false;
        
        try
        {
            blnOk = fleDirectory.mkdir();
        }
        
        catch(java.lang.SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            MySystem.s_printOutError(strWhere, "excSecurity caught");
            return null;
        }
        
        if (! blnOk)
        {
            MySystem.s_printOutError(strWhere, "failed to make dir: " + fleDirectory.getAbsolutePath());
            return null;
        }
        
        return fleDirectory;
    }
    
    // -------- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!     

    // --------------
    // PRIVATE STATIC
    
    private static File _s_fleParentAppli = null; // !!!
    
    private static File _s_getPathAbsParentAppliFromUrlFile(URL urlResource, boolean blnReadOnlyAllowed)
	{
	    String strMethod = _f_s_strClass + "." + "_s_getPathAbsParentAppliFromUrlFile(urlResource, blnReadOnlyAllowed)";
	    
	    // eg: com.google.code.p.keytooliui.shared.io.S_FileSys
	    String strClassName = _f_s_strClass;
	    
	    
        /* begin modif june 17, 2003
             Coz bug since JVM from 1.3 to 1.4:
             In 1.4, for a local URL, spaces show up as %20 
        */
        
        //String strClassResource = urlResource.getFile().toString();
        String strClassResource = com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getFileToString(urlResource);
        // end modif june 17, 2003
        
        
        int intClassLength = strClassName.length();
        
        intClassLength += 7; // meaning: "/" + className + ".class" ==> "/" + ".class" length = 7
        
        int intLength = strClassResource.length();
        int intToSubstract = intLength - intClassLength;
        
        String strFolderAbsolute = null;
        
        try
        { 
            strFolderAbsolute = strClassResource.substring(0, intToSubstract);
        }
        
        catch (IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIndexOutOfBounds caught");
            return null;
        }
        
        // ----
        URL urlFolderAbsolute = null;
        final String f_strProtocol = "file";
        final String f_strHost = "";
        
        
        try
        {
            urlFolderAbsolute = new URL(f_strProtocol, f_strHost, strFolderAbsolute);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        
        
        /* begin modif june 17, 2003
             Coz bug since JVM from 1.3 to 1.4:
             In 1.4, for a local URL, spaces show up as %20 
        */
        
        //File fleFolderAbsolute = new File(urlFolderAbsolute.getFile().toString());
        File fleFolderAbsolute = new File(
            com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getFileToString(urlFolderAbsolute)
            );
        // end modif june 17, 2003
		
		if (blnReadOnlyAllowed)
		{
		    if (! _s_canReadDir(fleFolderAbsolute))
	        {
	            MySystem.s_printOutError(strMethod, "failed, fleFolderAbsolute.toString()=" + fleFolderAbsolute.toString()); 
	            return null;
	        }        
		    
		    return fleFolderAbsolute;
		}
		
		// should be writeable
		
		if (! _s_canWriteDir(fleFolderAbsolute))
	    {
	        MySystem.s_printOutError(strMethod, "failed, fleFolderAbsolute.toString()=" + fleFolderAbsolute.toString()); 
	        return null;
	    }
	   
	    return fleFolderAbsolute;
	}
    
    private static File _s_getPathAbsParentAppliFromUrlJar(URL urlResource, boolean blnReadOnlyAllowed)
    {
        String strMethod = _f_s_strClass + "." + "_s_getPathAbsParentAppliFromUrlJar(urlResource, blnReadOnlyAllowed)";
	
        
        MySystem.s_printOutTrace(strMethod, "urlResource=" + urlResource);
        
        
        // excerpt from 
        //URL urlDummy = S_FileSys.class.getClassLoader().getResource("com.google.code.p.keytooliui/shared/io/S_FileSys.class");
	
        //System.out.println("urlDummy=" + urlDummy);
        
        
        
        String str = urlResource.toString();
        
        if (str.startsWith("jar:")) str = str.substring(4);
        if (str.startsWith("file:///")) str = str.substring(7);
        else if (str.startsWith("file:/")) str = str.substring(5);
        else if (str.startsWith("file:")) str = str.substring(5);
        
        int end = str.indexOf("!/");
        
        if (end > 0)
            str = str.substring(0, end);
        
        URL url = null;
        
        try
        {
           url = new URL("file://" + str);
           URI uri = new URI(url.toString());
           
           if (uri.getAuthority() != null)
               str = uri.getAuthority() + uri.getPath();
           else
               str = uri.getPath();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        // str should be the path to foo.jar in the cache
        
        MySystem.s_printOutTrace(strMethod, "path to jar in cache, str=" + str);
        
        File fleTargetJar = new File(str);
        
        if (! fleTargetJar.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleTargetJar.exists(), fleTargetJar.getAbsolutePath()=" + fleTargetJar.getAbsolutePath());
            return null;
        }
        
        File fleDirCache = fleTargetJar.getParentFile();
        
        
        
        if (blnReadOnlyAllowed)
        {
            if (! _s_canReadDir(fleDirCache))
            {
                MySystem.s_printOutError(strMethod, "failed, fleDirCache.getAbsolutePath()=" + fleDirCache.getAbsolutePath()); 
                return null;
            }

            return fleDirCache;
        }

        if (! _s_canWriteDir(fleDirCache))
        {
            MySystem.s_printOutError(strMethod, "failed, fleDirCache.getAbsolutePath()=" + fleDirCache.getAbsolutePath()); 
            return null;
        }


        return fleDirCache;
        
        
        /**
        String strClassName = _f_s_strClass;

        // EG: file:/D:/prod/.../foo1.jar!/com/.../RCReader.class
 
 
        // begin modif june 17, 2003
          //   Coz bug since JVM from 1.3 to 1.4:
          //   In 1.4, for a local URL, spaces show up as %20 
        
       
        System.out.println("urlResource.toString()=" + urlResource.toString());
            
        //String strClassResource = urlResource.getFile().toString();
        String strClassResource = com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getFileToString(urlResource);
        // end modif june 17, 2003
 
       System.out.println("strClassResource=" + strClassResource);
       

        int intClassLength = strClassName.length();
        
        intClassLength += 7; // meaning: "/" + className + ".class" ==> "/" + ".class" length = 7
        
        intClassLength += 1; // the "!" appended to the jar file
        
        int intLength = strClassResource.length();
        int intToSubstract = intLength - intClassLength;
        
        int intStart = "file:".length();
        
        String strJarAbsolute = null;
        
        try
        { 
            strJarAbsolute = strClassResource.substring(intStart, intToSubstract);
        }
        
        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIndexOutOfBounds caught");
            return null;
        }
        
        
        // eg: strJarAbsolute = "/D:/prod/.../foo1.jar"
        System.out.println("strJarAbsolute=" + strJarAbsolute);
        
        // ----
        URL urlJar = null;
        final String f_strProtocol = S_FileExtension.f_s_strJARDocument.toLowerCase();
        final String f_strHost = "";
        
        try
        {
            urlJar = new URL(f_strProtocol, f_strHost, strJarAbsolute);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
        
        if (urlJar == null)
        {
            MySystem.s_printOutError(strMethod, "nil urlJar");
            return null;
        } 
        
        // begin modif june 17, 2003
          //   Coz bug since JVM from 1.3 to 1.4:
          //   In 1.4, for a local URL, spaces show up as %20 
        
        
        //File fleJarAbsolute = new File(urlJar.getFile().toString());
        File fleJarAbsolute = new File(
            com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getFileToString(urlJar)
            );
        // end modif june 17, 2003
        
        
		    
	if(! fleJarAbsolute.exists())
	{
            MySystem.s_printOutError(strMethod, "fleJarAbsolute does not exist, fleJarAbsolute.getAbsolutePath()=" + fleJarAbsolute.getAbsolutePath()); 
	    return null;
	} 
	    
	    File fleParent = fleJarAbsolute.getParentFile();
	    
	    if (blnReadOnlyAllowed)
	    {
	        if (! _s_canReadDir(fleParent))
	        {
	            MySystem.s_printOutError(strMethod, "failed, fleJarAbsolute.toString()=" + fleJarAbsolute.toString()); 
	            return null;
	        }
	        
	        return fleParent;
	    }
	    
	    if (! _s_canWriteDir(fleParent))
	    {
	        MySystem.s_printOutError(strMethod, "failed, fleJarAbsolute.toString()=" + fleJarAbsolute.toString()); 
	        return null;
	    }
	    
	   
	    return fleParent;
            **/
	}
	
	
	/**
	    this file should be the dir that contains the application
	    check wether this dir is write-protected
	    
	    if anything wrong, displays a warning dialog window
	**/
	private static boolean _s_canWriteDir(File fle)
	{
	    String strMethod = _f_s_strClass + "." + "_s_canWriteDir(fle)";
	    
	    if (fle == null)
	    {
			MySystem.s_printOutError(strMethod, "nil fle"); 
	        return false;
	    } 
	    
	    if (! fle.exists())
	    {
	        MySystem.s_printOutError(strMethod, "! fle.exists():" + fle.toString()); 
	        return false;
	    }
	    
	    if (! fle.isDirectory())
	    {
	        MySystem.s_printOutError(strMethod, "fle: not a directory file:" + fle.toString()); 
	        return false;
	    }
	    
	    if (! fle.canRead())
	    {
	        MySystem.s_printOutWarning(strMethod, "fle: cannot read file:" + fle.toString()); 
	        
	        
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(null, _s_strDialogFileProtectedBodyR + "\n" + fle.toString());
	        
	        
	        return false;
	    }
	    
	    // august 28, 2001: added "try-catch" block in order to handle exception under JavaWebStart-application_calling_rcreader
	    try
	    {
	        if (! fle.canWrite())
	        {
	            MySystem.s_printOutWarning(strMethod, "fle: cannot write file:" + fle.toString()); 
    	        
	       
                com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(null, _s_strDialogFileProtectedBodyW + "\n" + fle.toString());

	            return false;
	        }
	    }
	    
	    catch(java.security.AccessControlException excAccessControl)
	    {
	        excAccessControl.printStackTrace();
	        MySystem.s_printOutWarning(strMethod, "excAccessControl caught");
	       
	        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(null, _s_strDialogFileProtectedBodyW + "\n" + fle.toString());
	        return false;
	    }
	    
	    
	    // ending
	    return true;
	}
	
	/**
	    this file should be the dir that contains the application
	    check wether this dir is read-protected
	    
	    if anything wrong, displays a warning dialog window
	**/
	private static boolean _s_canReadDir(File fle)
	{
	    String strMethod = _f_s_strClass + "." + "_s_canReadDir(fle)";
	    
	    if (fle == null)
	    {
			MySystem.s_printOutError(strMethod, "nil fle"); 
	        return false;
	    } 
	    
	    if (! fle.exists())
	    {
	        MySystem.s_printOutError(strMethod, "! fle.exists():" + fle.toString()); 
	        return false;
	    }
	    
	    if (! fle.isDirectory())
	    {
	        MySystem.s_printOutError(strMethod, "fle: not a directory file:" + fle.toString()); 
	        return false;
	    }
	    
	    // august 28, 2001: added "try-catch" block in order to handle exception under JavaWebStart-application_calling_rcreader
	    try
	    {
	        if (! fle.canRead())
	        {
	            MySystem.s_printOutWarning(strMethod, "fle: cannot read file:" + fle.toString()); 
    	        
	     
                com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(null, _s_strDialogFileProtectedBodyR + "\n" + fle.toString());
    	        
	            return false;
	        }
	    }
	    
	    catch(java.security.AccessControlException excAccessControl)
	    {
	        excAccessControl.printStackTrace();
	        MySystem.s_printOutWarning(strMethod, "excAccessControl caught");
	        
	        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(null, _s_strDialogFileProtectedBodyW + "\n" + fle.toString());
	        return false;
	    }
	    
	    
	    // ending
	    return true;
	}
	
	
	// ------
	// PUBLIC
	
	// -----
    // TRICK
    
    public S_FileSys() {}

}
