/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
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
import java.util.*;
import java.util.zip.*;
import java.util.jar.*;


/**
    map and extract resources included either in a zip or jar.
**/

public final class FileJar
    implements Serializable
{
    // --------------------------
    // PUBLIC STATIC FINAL STRING

    public static final String f_s_strPathRelShortDocRcrScript = "script"; // script.txt
    public static final String f_s_strPathRelShortDocJhrHelpset = "helpset"; // helpset.hs

    public static final String f_s_strFileSeparatorMain = "!/";
    public static final String f_s_strFileSeparatorSub = "/";

    // ---------------------------
    // PRIVATE STATIC FINAL STRING

    private static final String  _f_s_strClass = "com.google.code.p.keytooliui.shared.io.FileJar.";


    // ------
    // STATIC

    /**
        extracting jarred file as "byte[]"
        then writing to temporary file, that should be deleted at session's exit
        finally returns (temporary file's) absolute path.

        eg windows, strUrlSource: "jar:file:D:/foo.jar!/subdir/myfile.[file_extension]
        ==> returns: file:D:/...../temp/[temp_short_name].[file_extension]

        IMPORTANT: requires a file name with an extension
    **/
    public static java.net.URL s_getUrlCloneTemp(String strUrlSource)
    {
        String strMethod = _f_s_strClass + "s_getUrlCloneTemp(strUrlSource)";

        if (strUrlSource == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        // ----

        byte[] byts = FileJar.s_getFileResource(strUrlSource);

        if (byts == null)
        {
            MySystem.s_printOutError(strMethod, "nil byts, strUrlSource=" + strUrlSource);
            return null;
        }

        return s_getUrlCloneTemp(byts, strUrlSource);
    }

    /**
        extracting jarred file as "byte[]"
        then writing to temporary file, that should be deleted at session's exit
        finally returns (temporary file's) absolute path.

        eg windows, strUrlSource:
         . "jar:file:D:/foo.jar!/subdir/myfile.[file_extension]"
         . "subdir/myfile.[file_extension]"

        ==> returns: file:D:/...../temp/[temp_short_name].[file_extension]

        IMPORTANT: requires a file name with an extension
    **/
    public static java.net.URL s_getUrlCloneTemp(byte[] bytsSource, String strFileSource)
    {
        String strMethod = _f_s_strClass + "s_getUrlCloneTemp(bytsSource, strFileSource)";
        
        MySystem.s_printOutTrace(strMethod, "strFileSource=" + strFileSource);

        if (bytsSource==null || strFileSource==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        String strExtension =
            com.google.code.p.keytooliui.shared.lang.string.S_StringShared.s_getExtension(strFileSource);

        if (strExtension == null)
        {
            MySystem.s_printOutError(strMethod, "nil strExtension, strFileSource=" + strFileSource);
            return null;
        }

        strExtension = strExtension.toLowerCase();

        // -------------------------
        // creates a temp file
        String strSuffix = "." + strExtension;

        java.io.File fle = S_FileSys.s_getFileTempFromBytes(bytsSource, strSuffix);

        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil fle");
            return null;
        }

        String strPathJava = com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.s_systemPathToJavaPath(fle.getAbsolutePath());

        fle = null;

        if (strPathJava == null)
        {
            MySystem.s_printOutError(strMethod, "nil strPathJava");
            return null;
        }


        java.net.URL urlClone = null;
        String strUrlClone = "file:" + strPathJava;

        try
	    {
	        urlClone = new java.net.URL(strUrlClone);
	    }

	    catch(java.net.MalformedURLException excMalformedURL)
	    {
	        excMalformedURL.printStackTrace();
	        MySystem.s_printOutError(strMethod, "excMalformedURL caught, strUrlClone=" + strUrlClone);
	        return null;
	    }

	    return urlClone;
    }


    public static boolean s_printContent(String strPathAbsoluteJar)
    {
        String strMethod = _f_s_strClass + "s_printContent(strPathAbsoluteJar)";

        if (strPathAbsoluteJar == null)
        {
            MySystem.s_printOutError(strMethod, "nil strPathAbsoluteJar");
            return false;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.printContent())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        fjr.destroy();
        fjr = null;

        // ending
        return true;
    }


    /**
        adding resource file, NOT directory

        used by as an example "importFromFileSystem"
        strParentPathRelative should always start with "public/"
    **/
    public static boolean s_addResource(
        String strPathAbsoluteJar, String strPathRelativeParent, String strFileName, byte[] byts)
    {
        String strMethod = _f_s_strClass + "s_addResource(strPathAbsoluteJar, strPathRelativeParent, strFileName, byts)";

        if (strPathAbsoluteJar == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.isDirectory(strPathRelativeParent))
        {
            MySystem.s_printOutError(strMethod, "! isDirectory(strPathRelativeParent), strPathRelativeParent=" + strPathRelativeParent);
            return false;
        }

        if (! fjr.addResource(strPathRelativeParent + strFileName, byts))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.write())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        fjr.destroy();
        fjr = null;
        System.gc();

        // ending
        return true;
    }


    /**
        moving file, NOT directory
    **/
    public static boolean s_moveFile(
        String strPathAbsoluteJar, String strParentPathRelativeFrom, String strParentPathRelativeTo, String strFileName)
    {
        String strMethod = _f_s_strClass + "s_moveFile(strPathAbsoluteJar, strParentPathRelativeFrom, strParentPathRelativeTo, strFileName)";

        if (strPathAbsoluteJar == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.moveFile(strParentPathRelativeFrom, strParentPathRelativeTo, strFileName))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        fjr.destroy();
        fjr = null;
        return true;
    }


    /**
        renaming file, NOT directory
    **/
    public static boolean s_renameFile(
        String strPathAbsoluteJar, 
        String strParentPathRelative, 
        String strFileNameCur, 
        String strFileNameNew)
    {
        String strMethod = _f_s_strClass + "s_renameFile(strPathAbsoluteJar, strParentPathRelative, strFileNameCur, strFileNameNew)";

        if (strPathAbsoluteJar == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.renameFile(strParentPathRelative, strFileNameCur, strFileNameNew))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        fjr.destroy();
        fjr = null;
        return true;
    }

    /**
        deleting empty directory, NOT file
    **/
    public static boolean s_deleteDirEmpty(String strPathAbsoluteJar, String strPathRelative)
    {
        String strMethod = _f_s_strClass + "s_deleteDirEmpty(strPathAbsoluteJar, strPathRelative)";

        if (strPathAbsoluteJar==null || strPathRelative==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.deleteDirEmpty(strPathRelative))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        fjr.destroy();
        fjr = null;
        return true;
    }

    /**
        deleting file, NOT directory
    **/
    public static boolean s_deleteFile(String strPathAbsoluteJar, String strPathRelative)
    {
        String strMethod = _f_s_strClass + "s_deleteFile(strPathAbsoluteJar, strPathRelative)";

        if (strPathAbsoluteJar==null || strPathRelative==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.deleteFile(strPathRelative))
        {
            MySystem.s_printOutError(strMethod, "failed, strPathRelative=" + strPathRelative);
            return false;
        }

        fjr.destroy();
        fjr = null;
        return true;
    }

    public static boolean s_makeDirectory(String strPathAbsoluteJar, String strPathRelative)
    {
        String strMethod = _f_s_strClass + "s_makeDirectory(strPathAbsoluteJar, strPathRelative)";

        if (strPathAbsoluteJar==null || strPathRelative==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (! fjr.makeDirectory(strPathRelative))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        fjr.destroy();
        fjr = null;
        return true;
    }

    /**
        if any error, exiting
    **/
    public static boolean s_contains(String strPathAbsoluteJar, String strPathRelative)
    {
        String strMethod = _f_s_strClass + "s_contains(strPathAbsoluteJar, strPathRelative)";

        if (strPathAbsoluteJar==null || strPathRelative==null)
        {
            MySystem.s_printOutExit(strMethod, "nil arg");
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutExit(strMethod, "failed");
        }

        boolean bln = fjr.containsKey(strPathRelative);

        fjr.destroy();
        fjr = null;
        return bln;
    }

    /**
        if any error, return nil

        eg, Windows OS, strUrlResource: "jar:file:D:/foo.jar!/subdir/file"
    **/
    public static byte[] s_getFileResource(String strUrlResource)
    {
        String strMethod = _f_s_strClass + "s_getFileResource(strUrlResource)";


        if (strUrlResource == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        String strUrlBeg = "jar:file:";

        if (! strUrlResource.toLowerCase().startsWith(strUrlBeg))
        {
            MySystem.s_printOutError(strMethod, "! strUrlResource.toLowerCase().startsWith(strUrlBeg), strUrlResource=" + strUrlResource + ", strUrlBeg=" + strUrlBeg);
            return null;
        }

        String strIndex = f_s_strFileSeparatorMain;

        int intIndex = 0;

        try
        {
            intIndex = strUrlResource.toLowerCase().lastIndexOf(strIndex);
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught, strUrlResource=" + strUrlResource);
            return null;
        }

        if (intIndex == -1)
        {
            MySystem.s_printOutError(strMethod, "intIndex == -1, strUrlResource=" + strUrlResource);
            return null;
        }

        String strPathRelative = null;

        try
        {
            strPathRelative = strUrlResource.substring(intIndex + f_s_strFileSeparatorMain.length());
        }

        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIndexOutOfBounds caught, strUrlResource=" + strUrlResource);
            return null;
        }

        String strPathAbsoluteJar = null;

        try
        {
            strPathAbsoluteJar = strUrlResource.substring(strUrlBeg.length(), intIndex);
        }

        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIndexOutOfBounds caught, strUrlResource=" + strUrlResource);
            return null;
        }

        return FileJar.s_getFileResource(strPathAbsoluteJar, strPathRelative);
    }

    /**
        if any error, return nil
    **/
    public static byte[] s_getFileResource(String strPathAbsoluteJar, String strPathRelative)
    {
        String strMethod = _f_s_strClass + "s_getFileResource(strPathAbsoluteJar, strPathRelative)";

        if (strPathAbsoluteJar==null || strPathRelative==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }

        byte[] byts = fjr.getResource(strPathRelative);

        fjr.destroy();
        fjr = null;

        if (byts == null)
        {
            MySystem.s_printOutError(strMethod, "nil byts, strPathRelative=" + strPathRelative);
            return null;
        }

        return byts;
    }

    /**
        if any error, exiting
    **/
    public static long s_getFileLength(String strPathAbsoluteJar, String strPathRelative)
    {
        String strMethod = _f_s_strClass + "s_getFileLength(strPathAbsoluteJar, strPathRelative)";

        if (strPathAbsoluteJar==null || strPathRelative==null)
        {
            MySystem.s_printOutExit(strMethod, "nil arg");

        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutExit(strMethod, "failed");

        }

        long lng = fjr.getResourceSize(strPathRelative);
        fjr.destroy();
        fjr = null;

        return lng;
    }
    
    /*
        returning all files (NOT DIR) suffixed "strNameSuffix"
        if any error, returning nil,
        else if no matching entry found, returning new String[0]
    */
    public static String[] s_getStrsPathRelFileFileSuffixed(
        String strPathAbsJar,
        String strNameSuffix
        )
    {
        String strMethod = _f_s_strClass + "s_getStrsPathRelFileFileSuffixed(strPathAbsJar, strNameSuffix)";
        
        if (strPathAbsJar==null || strNameSuffix==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }
        
        if (strNameSuffix.length() < 2) // ".[x]" ==> length = 2
        {
            MySystem.s_printOutError(strMethod, "strNameSuffix.length < 2");
            return null;
        }
        
        
        FileJar fjr = new FileJar(strPathAbsJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed, strPathAbsJar=" + strPathAbsJar);
            return null;
        }
        
        // --
        
        Enumeration enuKey = fjr.getKeys();

        if (enuKey == null) // empty
            return new String[0];

        Vector<String> vec = new Vector<String>();

        while (enuKey.hasMoreElements())
		{
	        String strKey = (String) enuKey.nextElement();

	        if (strKey == null)
	        {
	            MySystem.s_printOutError(strMethod, "nil strKey");
	            return null;
	        }

            if (! strKey.toLowerCase().endsWith(strNameSuffix))
                continue;
                
	        vec.addElement(strKey);
	    } // end of "while"
       

        

        String[] strs = new String[vec.size()];

        for (int i=0; i<vec.size(); i++)
        {
            strs[i] = (String) vec.elementAt(i);
        }
        
        
        vec = null;
        // --
        fjr.destroy();
        fjr = null;
        
        // ending
        return strs;
    }

    /**
        returns list of DIRECTORY children
        if any error, return nil
    **/
    public static String[] s_getStrsPathRelativeFileDir(String strPathAbsoluteJar)
    {
        String strMethod = _f_s_strClass + "s_getStrsPathRelativeFileDir(strPathAbsoluteJar)";

        if (strPathAbsoluteJar == null)
        {
            MySystem.s_printOutError(strMethod, "nil strPathAbsoluteJar");
            return null;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }



        String[] strs = fjr.getStrsPathRelativeDir();

        fjr.destroy();
        fjr = null;

        if (strs == null)
        {
            MySystem.s_printOutError(strMethod, "nil strs");
            return null;
        }

        // -- sorting
        strs = com.google.code.p.keytooliui.shared.util.sort.StrSortVector.s_sort(strs);

        if (strs == null)
        {
            MySystem.s_printOutError(strMethod, "nil strs");
		    return null;
        }

        // ending
        return strs;
    }

    /**
        returns list of (FILE) children
        if any error, return nil

        MEMO: root folder: ""
    **/
    public static String[] s_getStrsNameFileFile(String strPathAbsoluteJar, String strPathRelativeParent)
    {
        String strMethod = _f_s_strClass + "s_getStrsNameFileFile(strPathAbsoluteJar, strPathRelativeParent)";

        if (strPathAbsoluteJar==null || strPathRelativeParent==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }

        String[] strs = fjr.getStrsNameFileFile(strPathRelativeParent);

        fjr.destroy();
        fjr = null;

        if (strs == null)
        {
            MySystem.s_printOutError(strMethod, "nil strs");
            return null;
        }

        // -- sorting
        strs = com.google.code.p.keytooliui.shared.util.sort.StrSortVector.s_sort(strs);

        if (strs == null)
        {
            MySystem.s_printOutError(strMethod, "nil strs");
		    return null;
        }

        // ending
        return strs;
    }

    /**
        returns list of children
        if any error, return nil

        MEMO: root folder: ""
    **/
    public static String[] s_getStrsNameFileAll(String strPathAbsoluteJar, String strPathRelativeParent)
    {
        String strMethod = _f_s_strClass + "s_getStrsNameFileAll(strPathAbsoluteJar, strPathRelativeParent)";

        // --

        String[] strsFile = s_getStrsNameFileFile(strPathAbsoluteJar, strPathRelativeParent);

        if (strsFile == null)
        {
            MySystem.s_printOutError(strMethod, "nil strsFile");
            return null;
        }

        // --

        String[] strsDir = s_getStrsNameFileDir(strPathAbsoluteJar, strPathRelativeParent);

        if (strsDir == null)
        {
            MySystem.s_printOutError(strMethod, "nil strsDir");
            return null;
        }

        String[] strsAll = new String[strsFile.length + strsDir.length];

        if (strsAll.length == 0)
            return new String[0];

        for (int i=0; i<strsFile.length; i++)
            strsAll[i] = strsFile[i];

        for (int i=0; i<strsDir.length; i++)
            strsAll[i+strsFile.length] = strsDir[i];

        // -- sorting
        strsAll = com.google.code.p.keytooliui.shared.util.sort.StrSortVector.s_sort(strsAll);

        if (strsAll == null)
        {
            MySystem.s_printOutError(strMethod, "nil strsAll");
		    return null;
        }

        // ending
        return strsAll;
    }

    /**
        returns list of children
        if any error, return nil

        MEMO: root folder: ""
    **/
    public static String[] s_getStrsNameFileDir(String strPathAbsoluteJar, String strPathRelativeParent)
    {
        String strMethod = _f_s_strClass + "s_getStrsNameFileDir(strPathAbsoluteJar, strPathRelativeParent)";

        if (strPathAbsoluteJar==null || strPathRelativeParent==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }

        String[] strs = fjr.getStrsNameFileDir(strPathRelativeParent);

        fjr.destroy();
        fjr = null;

        if (strs == null)
        {
            MySystem.s_printOutError(strMethod, "nil strs");
            return null;
        }

        // -- sorting
        strs = com.google.code.p.keytooliui.shared.util.sort.StrSortVector.s_sort(strs);

        if (strs == null)
        {
            MySystem.s_printOutError(strMethod, "nil strs");
		    return null;
        }

        // ending
        return strs;
    }

    /**
        returns list of children
        if any error, return nil
    **/

    public static String[] s_getStrsNameFileFile(String strPathAbsoluteJar, String strPathRelativeParent, String[] strsExtension)
    {
        String strMethod = _f_s_strClass + "s_getStrsNameFileFile(strPathAbsoluteJar, strPathRelativeParent, strsExtension)";

        if (strPathAbsoluteJar==null || strPathRelativeParent==null || strsExtension==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        FileJar fjr = new FileJar(strPathAbsoluteJar);

        if (! fjr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }

        String[] strsFileFile = fjr.getStrsNameFileFile(strPathRelativeParent);

        fjr.destroy();
        fjr = null;

        if (strsFileFile == null)
        {
            MySystem.s_printOutError(strMethod, "nil strsFileFile");
            return null;
        }

        if (strsFileFile.length == 0)
            return strsFileFile;

        // --
        Vector<String> vecFiltered = new Vector<String>();

        com.google.code.p.keytooliui.shared.lang.string.StringFilter sfr =
            new com.google.code.p.keytooliui.shared.lang.string.StringFilter(strsExtension);

        if (! sfr.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }

        for (int i=0; i<strsFileFile.length; i++)
        {
            if (sfr.accept(strsFileFile[i]))
            {
                vecFiltered.addElement(strsFileFile[i]);
            }
        }

        String[] strsFiltered = new String[vecFiltered.size()];

        for (int i=0; i<vecFiltered.size(); i++)
            strsFiltered[i] = (String) vecFiltered.elementAt(i);

        // -- sorting
        strsFiltered = com.google.code.p.keytooliui.shared.util.sort.StrSortVector.s_sort(strsFiltered);

        if (strsFiltered == null)
        {
            MySystem.s_printOutError(strMethod, "nil strsFiltered");
		    return null;
        }

        // ending
        return strsFiltered;
    }


    // -------------
    // PUBLIC ACCESS

    // !!!!!!!!!! never called !!!!!!!!!
    public String getPathAbsoluteOrigin() { return this._strPathAbsoluteOrigin; }

    /**
        if any error, return nil
    **/
    public String[] getStrsNameFileFile(String strPathRelativeParent)
    {
        String strMethod = "getStrsNameFileFile(strPathRelativeParent)";

        if (strPathRelativeParent==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }

        if (! isDirectory(strPathRelativeParent))
        {
            MySystem.s_printOutError(strMethod, "! isDirectory(strPathRelativeParent), strPathRelativeParent=" + strPathRelativeParent);
            return null;
        }

        Enumeration enuKey = this.getKeys();

        if (enuKey == null) // empty
            return new String[0];

        Vector<String> vec = new Vector<String>();

        if (strPathRelativeParent.length() == 0) // root
        {
            while (enuKey.hasMoreElements())
		    {
	            String strKey = (String) enuKey.nextElement();

	            if (strKey == null)
	            {
	                MySystem.s_printOutError(this, strMethod, "nil strKey");
	                return null;
	            }


	            if (strKey.indexOf(f_s_strFileSeparatorSub) != -1) // subLevel (not mainLevel)
	                continue;

	            if (strKey.endsWith(f_s_strFileSeparatorSub)) // directory
	                continue;

	            vec.addElement(strKey);


	        } // end of "while"
        }

        else
        {
            //

            int intIndexBeginChild = strPathRelativeParent.length();
            //intIndexBeginChild ++;

            while (enuKey.hasMoreElements())
		    {
	            String strKey = (String) enuKey.nextElement();

	            if (strKey == null)
	            {
	                MySystem.s_printOutError(this, strMethod, "nil strKey");
	                return null;
	            }

	            if (strKey.compareTo(strPathRelativeParent) == 0) // ==> same dir
	            {
	                continue;
	            }


	            if (strKey.endsWith(f_s_strFileSeparatorSub)) // directory
	                continue;

	            if (! strKey.startsWith(strPathRelativeParent))
	                continue;

	            String strChildCandidate = strKey.substring(intIndexBeginChild);

	            if (strChildCandidate.indexOf(f_s_strFileSeparatorSub) != -1) // grandchild (mainLevel)
	                continue;

	            vec.addElement(strChildCandidate);


	        } // end of "while"
        }

        String[] strs = new String[vec.size()];

        for (int i=0; i<vec.size(); i++)
        {
            strs[i] = (String) vec.elementAt(i);
        }


        // ending
        return strs;
    }

    /**
        if any error, return nil

        EG, strPathRelativeParent:
        . "" ==> main dir (root)
        . "public/"
        . "protected/"
        . "protected/images/"
    **/
    public String[] getStrsNameFileDir(String strPathRelativeParent)
    {
        String strMethod = "getStrsNameFileDir(strPathRelativeParent)";

        if (strPathRelativeParent==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return null;
        }

        Enumeration enuKey = this.getKeys();

        if (enuKey == null) // empty
            return new String[0];

        Vector<String> vec = new Vector<String>();

        if (strPathRelativeParent.length() == 0) // root
        {
            while (enuKey.hasMoreElements())
		    {
	            String strKey = (String) enuKey.nextElement();

	            if (strKey == null)
	            {
	                MySystem.s_printOutError(this, strMethod, "nil strKey");
	                return null;
	            }

	            if (! strKey.endsWith(f_s_strFileSeparatorSub)) // not directory
	                continue;


	            if (strKey.indexOf(f_s_strFileSeparatorSub) != strKey.lastIndexOf(f_s_strFileSeparatorSub)) // subLevel (not mainLevel)
	                continue;



	            vec.addElement(strKey);


	        } // end of "while"
        }

        else
        {
            //

            int intIndexBeginChild = strPathRelativeParent.length();
            //intIndexBeginChild ++;

            while (enuKey.hasMoreElements())
		    {
	            String strKey = (String) enuKey.nextElement();

	            if (strKey == null)
	            {
	                MySystem.s_printOutError(this, strMethod, "nil strKey");
	                return null;
	            }

	            if (strKey.compareTo(strPathRelativeParent) == 0) // ==> same dir
	            {
	                continue;
	            }

	            if (! strKey.endsWith(f_s_strFileSeparatorSub)) // not directory
	                continue;

	            if (! strKey.startsWith(strPathRelativeParent))
	                continue;

	            String strChildCandidate = strKey.substring(intIndexBeginChild);

	            if (strChildCandidate.indexOf(f_s_strFileSeparatorSub) != strChildCandidate.lastIndexOf(f_s_strFileSeparatorSub)) // subchild (sub-subLevel)
	                continue;

	            vec.addElement(strChildCandidate);


	        } // end of "while"
        }

        String[] strs = new String[vec.size()];

        for (int i=0; i<vec.size(); i++)
        {
            strs[i] = (String) vec.elementAt(i);
        }


        // ending
        return strs;
    }

    /**
        if any error, exiting


        Q: what about main dir: ""
    **/
    public boolean isDirectory(String strPathRelative)
    {
        String strMethod = "isDirectory(strPathRelative)";

        if (strPathRelative == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strPathRelative");
        }

        if (strPathRelative.length() == 0) // root
            return true;

        if (! strPathRelative.endsWith(f_s_strFileSeparatorSub)) // all subdir should end with f_s_strFileSeparatorSub
            return false;


        Enumeration enuKey = this.getKeys();

        if (enuKey == null) // empty
            return false;


        while (enuKey.hasMoreElements())
		{
	        String strKey = (String) enuKey.nextElement();

	        if (strKey == null)
	        {
	            MySystem.s_printOutExit(this, strMethod, "nil strKey");

	        }

	        if (strKey.compareTo(strPathRelative) == 0)
	            return true;

	    }

        // ending
        return false;

    }

    /**
        if any error, return nil
        ?? what about mainDir: ""
    **/
    public String[] getStrsPathRelativeDir()
    {
        String strMethod = "getStrsPathRelativeDir()";

        Enumeration enuKey = this.getKeys();

        if (enuKey == null) // empty
            return new String[0];

        Vector<String> vec = new Vector<String>();

        while (enuKey.hasMoreElements())
		{
	        String strKey = (String) enuKey.nextElement();

	        if (strKey == null)
	        {
	            MySystem.s_printOutError(this, strMethod, "nil strKey");
	            return null;
	        }


	        if (! strKey.endsWith(f_s_strFileSeparatorSub)) // directory
	            continue;

            vec.addElement(strKey);
        }

        String[] strs = new String[vec.size()];

        for (int i=0; i<vec.size(); i++)
        {
            strs[i] = (String) vec.elementAt(i);
        }

        // ???? sorting ??????

        // ending
        return strs;

        /**
        if (this._vecStrPathRelativeDir == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil getStrsPathRelativeDir");
            return null;
        }

        if (this._vecStrPathRelativeDir.size() == 0)
            return new String[0];


        // --
        String[] strs = new String[this._vecStrPathRelativeDir.size()];

        for (int i=0; i<this._vecStrPathRelativeDir.size(); i++)
        {
            strs[i] = (String) this._vecStrPathRelativeDir.elementAt(i);
        }

        // --
        // -- sorting
        strs = com.google.code.p.keytooliui.shared.util.sort.StrSortVector.s_sort(strs);

        if (strs == null)
        {
            MySystem.s_printOutError(strMethod, "nil strs");
		    return null;
        }

        // ending
        return strs;
        **/
    }

    // if any error, exits
    // may return nil ==> empty
    public Enumeration getKeys()
    {
        String strMethod = "getKeys()";

        if (this._hstJarContents == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil this._hstJarContents");
        }

        return this._hstJarContents.keys();
    }


    // ------
    // PUBLIC


    /**
        moving file, NOT directory

        memo: strParentPathRelative[From-To] start with "public/"
    **/

    public boolean moveFile(String strParentPathRelativeFrom, String strParentPathRelativeTo, String strFileName)
    {
        String strMethod = "renameFile(strParentPathRelativeFrom, strParentPathRelativeTo, strFileName)";

        if (strParentPathRelativeFrom==null || strParentPathRelativeTo==null || strFileName==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }

        if (! strParentPathRelativeFrom.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "! strParentPathRelativeFrom.endsWith(\"/\"), strParentPathRelativeFrom=" + strParentPathRelativeFrom);
            return false;
        }

        if (! strParentPathRelativeTo.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "! strParentPathRelativeTo.endsWith(\"/\"), strParentPathRelativeTo=" + strParentPathRelativeTo);
            return false;
        }

        if (strFileName.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "strFileName.endsWith(\"/\"), strFileName=" + strFileName);
            return false;
        }

        // ----



        String strPathRelativeTo = strParentPathRelativeTo + strFileName;

        if (this.containsKey(strPathRelativeTo))
        {
            MySystem.s_printOutError(this, strMethod, "this.containsKey(strPathRelativeTo), strPathRelativeTo=" + strPathRelativeTo);
            return false;
        }

        String strPathRelativeFrom = strParentPathRelativeFrom + strFileName;

        byte[] byts = this.getResource(strPathRelativeFrom);

        if (byts == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil byts");
            return false;
        }

        if (! removeResource(strPathRelativeFrom))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (! this.addResource(strPathRelativeTo, byts))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }


        if (! write())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        

        // ending
        return true;
    }

    /**
        renaming file, NOT directory

        memo: strParentPathRelative starts with "public/"
    **/

    public boolean renameFile(String strParentPathRelative, String strFileNameCur, String strFileNameNew)
    {
        String strMethod = "renameFile(strParentPathRelative, strFileNameCur, strFileNameNew)";

        if (strParentPathRelative==null || strFileNameCur==null || strFileNameNew==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }

        if (! strParentPathRelative.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "! strParentPathRelative.endsWith(\"/\"), strParentPathRelative=" + strParentPathRelative);
            return false;
        }

        if (strFileNameCur.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "strFileNameCur.endsWith(\"/\"), strFileNameCur=" + strFileNameCur);
            return false;
        }

        if (strFileNameNew.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "strFileNameNew.endsWith(\"/\"), strFileNameNew=" + strFileNameNew);
            return false;
        }

        // ----



        String strPathRelativeNew = strParentPathRelative + strFileNameNew;

        if (this.containsKey(strPathRelativeNew))
        {
            MySystem.s_printOutError(this, strMethod, "this.containsKey(strPathRelativeNew), strPathRelativeNew=" + strPathRelativeNew);
            return false;
        }

        String strPathRelativeCur = strParentPathRelative + strFileNameCur;

        byte[] byts = this.getResource(strPathRelativeCur);

        if (byts == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil byts");
            return false;
        }

        if (! removeResource(strPathRelativeCur))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (! this.addResource(strPathRelativeNew, byts))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }


        if (! write())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // ending
        return true;
    }

    public boolean deleteDirEmpty(String strPathRelative)
    {
        String strMethod = "deleteDirEmpty(strPathRelative)";

        if (strPathRelative == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathRelative");
            return false;
        }

        if (! strPathRelative.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "! strPathRelative.endsWith(\"/\"), strPathRelative=" + strPathRelative);
            return false;
        }

        if (! this.isDirectory(strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "! isDirectory(strPathRelative), strPathRelative=" + strPathRelative);
            return false;
        }

        if (! this._isDirectoryEmpty(strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "! _isDirectoryEmpty(strPathRelative), strPathRelative=" + strPathRelative);
            return false;
        }

        if (! removeResource(strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (! write())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // ending
        return true;
    }

    /**
        deleting file, NOT directory
        then write

        memo: strParentPathRelative starts with "public/"
    **/
    public boolean deleteFile(String strPathRelative)
    {
        String strMethod = "deleteFile(strPathRelative)";

        if (strPathRelative == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathRelative");
            return false;
        }

        if (strPathRelative.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "strPathRelative.endsWith(\"/\"), strPathRelative=" + strPathRelative);
            return false;
        }

        if (! removeResource(strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }


        if (! write())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // ending
        return true;
    }

    /**
        make directory, then write
    **/
    public boolean makeDirectory(String strPathRelative)
    {
        String strMethod = "makeDirectory(strPathRelative)";

        if (! this.addResourceDir(strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        if (! write())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // ending
        return true;
    }

    /**
        if any error, exits

        memo: key should be pathRelative of file (not directory)
    **/
    public boolean containsKey(String strKey)
    {
        String strMethod = "containsKey(strKey)";

        if (strKey == null)
            MySystem.s_printOutExit(this, strMethod, "nil strKey");

        if (this._hstJarContents == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._hstJarContents");

        Enumeration enuKey = this._hstJarContents.keys();

		if (enuKey == null)
		{
		    return false;
		}

		while (enuKey.hasMoreElements())
		{
	        String strPathRelative =  (String) enuKey.nextElement();

	        if (strPathRelative.compareTo(strKey) == 0)
	            return true;
	    }

		// ending
		return false;
    }

    public void destroy()
    {
        if (this._hstJarContents != null)
        {
            this._hstJarContents.clear();
            this._hstJarContents = null;
        }
        
        this._strPathAbsoluteOrigin = null;
    }

    public boolean printContent()
    {
        String strMethod = "printContent()";

        if (this._hstJarContents == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hstJarContents");
            return false;
        }

        if (this._strPathAbsoluteOrigin == null)
            System.out.println(">> CREATED ON THE FLY");
        else
            System.out.println(">> CREATED FROM: " + this._strPathAbsoluteOrigin);

        Enumeration enuKey = this._hstJarContents.keys();

		System.out.println(">> BEGIN KEYS ...");
		if (enuKey == null)
		{
		    System.out.println(">> ... NO KEYS");
		}

		else
		{
		    int intId = 1;

	        while (enuKey.hasMoreElements())
		    {
	            String strPathRelative = (String) enuKey.nextElement();
		        System.out.println(">> " + intId + ": " + strPathRelative);
		        intId ++;
		    }

		    System.out.println(">> ... DONE KEYS");
		}

	    // ending
	    return true;
	}




    /**
    * creates a FileJar. It extracts all resources from a Jar
    * into an internal hashtable, keyed by resource names.
    * @param jarFileName a jar or zip file
    */
    public FileJar(String strPathAbsoluteOrigin)
    {
        this._strPathAbsoluteOrigin = strPathAbsoluteOrigin;
        this._hstJarContents = new Hashtable<String, byte[]>();
        //this._hstJarContents = new Hashtable();
    }

    public boolean write()
    {
        String strMethod = "write()";

        if (this._strPathAbsoluteOrigin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strPathAbsoluteOrigin");
            return false;
        }

        // 0) delete file

        File fleOld = new File(this._strPathAbsoluteOrigin);

        if (fleOld.exists())
        {
            if (fleOld.isDirectory())
            {
                MySystem.s_printOutError(this, strMethod, "fleOld.isDirectory()");
                return false;
            }

            if (! fleOld.canWrite())
            {
                MySystem.s_printOutError(this, strMethod, "! fleOld.canWrite(), this._strPathAbsoluteOrigin=" + this._strPathAbsoluteOrigin);
                return false;
            }


            try
            {
                if (! fleOld.delete())
                {
                    MySystem.s_printOutError(this, strMethod, "! fleOld.delete(), this._strPathAbsoluteOrigin=" + this._strPathAbsoluteOrigin);
                    return false;
                }
            }

            catch (SecurityException excSecurity)
            {
                excSecurity.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excSecurity caught");
                return false;
            }
        }

        // 1) create jarOutputStream

        JarOutputStream jos = com.google.code.p.keytooliui.shared.util.jar.S_JarOutputStream.s_create(this._strPathAbsoluteOrigin);

        if (jos == null)
        {
			MySystem.s_printOutError(this, strMethod, "failed");
			return false;
		}
		

        // ---- iterate
        Enumeration enuKey = this.getKeys();

        if (enuKey == null) // empty
        {
            MySystem.s_printOutError(this, strMethod, "nil enuKey");
            return false;
        }

        while (enuKey.hasMoreElements())
		{
	        String strKey =  (String) enuKey.nextElement();

	        if (strKey == null)
	        {
	            MySystem.s_printOutError(this, strMethod, "nil strKey");
	            return false;
	        }

	        byte[] byts = this.getResource(strKey);

	        if (byts == null)
	        {
	            MySystem.s_printOutError(this, strMethod, "nil byts");
	            return false;
	        }

	        // ----

	        JarEntry jey = new JarEntry(strKey);
	        
	        if (! com.google.code.p.keytooliui.shared.util.jar.S_JarOutputStream.s_writeEntry(jos, jey, byts))
	        {
	            MySystem.s_printOutError(this, strMethod, "failed");
	            return false;
	        }

	    } // end of "while"

		// 3) save jarOutputStream

        if (! com.google.code.p.keytooliui.shared.util.jar.S_JarOutputStream.s_close(jos))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
        }
        
        jos = null;
        System.gc();

        // ending
        return true;
    }


    public FileJar()
    {
        this((String) null);
    }

    public boolean removeResource(String strKeyPathRelative)
    {
        String strMethod = "removeResource(strKeyPathRelative)";

        if (strKeyPathRelative == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strKeyPathRelative");
            return false;
        }

        if (this._hstJarContents == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hstJarContents");
            return false;
        }

        if (this._hstJarContents.remove(strKeyPathRelative) == null)
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // ending
        return true;
    }

    public boolean addResourceDir(String strPathRelative)
    {
        String strMethod = "addResourceDir(strPathRelative)";

        if (strPathRelative == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathRelative");
            return false;
        }

        if (strPathRelative.length() < 2)
        {
            MySystem.s_printOutError(this, strMethod, "wrong value, strPathRelative.length()=" + strPathRelative.length());
            return false;
        }

        if (! strPathRelative.endsWith(f_s_strFileSeparatorSub))
        {
            MySystem.s_printOutError(this, strMethod, "! strPathRelative.endsWith(\"/\"), strPathRelative=" + strPathRelative);
            return false;
        }

        if (this.containsKey(strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "this.containsKey(strPathRelative), strPathRelative=" + strPathRelative);
            return false;
        }

        this._hstJarContents.put(strPathRelative, new byte[0]);

        // ending
        return true;
    }
    
    
    /**
        filled while using FileJar() constructor
        or by as an example "importFromFileSystem"
    **/
    public boolean addResource(String strPathRelative, byte[] byts)
    {
        String strMethod = "addResource(strPathRelative, byts)";

        if (strPathRelative==null || byts==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }

        if (this.containsKey(strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "this.containsKey(strPathRelative)");
            return false;
        }

        if (this._hstJarContents == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hstJarContents");
            return false;
        }

        this._hstJarContents.put(strPathRelative, byts);

        // ending
        return true;
    }

    /**
    * initializes internal hash tables with Jar file resources.
    */

    public boolean init()
    {
        String strMethod = "init()";

        if (this._hstJarContents == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hstJarContents");
            return false;
        }

        if (this._strPathAbsoluteOrigin == null) // not loaded from file
        {
            MySystem.s_printOutTrace(this, strMethod, "nil this._strPathAbsoluteOrigin, returning true");
            return true;
        }
        // ------------------------

        Hashtable<String, Long> hstSizes = _getHashtableSizes();

        if (hstSizes == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil hstSizes, this._strPathAbsoluteOrigin=" + this._strPathAbsoluteOrigin);
            return false;
        }

        if (! _loadHashtableBytes(hstSizes))
        {
            MySystem.s_printOutError(this, strMethod, "failed, this._strPathAbsoluteOrigin=" + this._strPathAbsoluteOrigin);
            return false;
        }

        // ending
        return true;
    }

    /**
     * Extracts a jar resource as a blob.
     * @param name a resource name.
    */
    public byte[] getResource(String strPathRelative)
    {
        String strMethod = "getResource(strPathRelative)";

        if (this._hstJarContents == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hstJarContents");
            return null;
        }

        return (byte[]) this._hstJarContents.get(strPathRelative);
    }

    public long getResourceSize(String strPathRelative)
    {
        String strMethod = "getResourceSize(strPathRelative)";

        if (strPathRelative == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strPathRelative");

        }

        byte[] byts = getResource(strPathRelative);

        if (byts == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil byts");

        }

        return (long) byts.length;
    }

    // -------
    // PRIVATE

    private Hashtable<String, byte[]> _hstJarContents = null;

    // a jar file
    private String _strPathAbsoluteOrigin = null;

    private Hashtable<String, Long> _getHashtableSizes()
    {
        String strMethod = "_getHashtableSizes()";

        Hashtable<String, Long> hstSizes = new Hashtable<String, Long>();
        JarFile jfe = null;

        if (this._strPathAbsoluteOrigin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strPathAbsoluteOrigin");
            return null;
        }

        File fle = new File(this._strPathAbsoluteOrigin);

        // --

        try
        {
            jfe = new JarFile(fle, false, JarFile.OPEN_READ);
        }

        catch(FileNotFoundException excFileNotFound)
        {
            excFileNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excFileNotFound caught");
            return null;
        }

        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return null;
        }

        catch(IllegalArgumentException excIllegalArgument)
        {
            excIllegalArgument.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIllegalArgument caught");
            return null;
        }

        // ----------------
        int intEntryNb = 0;

        try
        {
            intEntryNb = jfe.size();
        }

        catch(IllegalStateException excIllegalState)
        {
            excIllegalState.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIllegalState caught");
            return null;
        }

        if (intEntryNb < 1)
        {
            MySystem.s_printOutTrace(this, strMethod, "intEntryNb < 1");
            return hstSizes;
        }

        try
        {
            Enumeration enu = jfe.entries();

            if (enu == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil enu");
                return null;
            }

            while (enu.hasMoreElements())
            {
                /** BUG:
                    Exception occurred during event dispatching:
                    java.lang.InternalError: jzentry == 0
                    at java.util.zip.ZipFile$2.nextElement(ZipFile.java:297)
                **/

                Object objCur = enu.nextElement();

                //if (! (objCur instanceof ZipEntry))
                if (! (objCur instanceof JarEntry))
                {
                    MySystem.s_printOutError(this, strMethod, "! (objCur instanceof ZipEntry)");
                    return null;
                }

                //ZipEntry zey = (ZipEntry) objCur;
                JarEntry zey = (JarEntry) objCur;

                String strNameCur = zey.getName();

                if (strNameCur == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil strNameCur");
                    return null;
                }

                hstSizes.put(strNameCur, new Long(zey.getSize()));
            }

        }

        catch(IllegalStateException excIllegalState)
        {
            excIllegalState.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIllegalState caught");
            return null;
        }

        /*
           added 17-1-1, coz bug in linux, corrupted jar file (was ok under WinNT)
        */
	    catch(java.lang.InternalError errInternal)
	    {
                errInternal.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "errInternal caught");
                return null;
	    }

        /*catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            return null;
        }*/



        // ------

        try
        {
            jfe.close();
            jfe = null;
        }

        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return null;
        }

        // ending
        return hstSizes;
    }

    /**
      plus fill vectorDirectory
    **/
    private boolean _loadHashtableBytes(Hashtable hstSizes)
    {
        String strMethod = "_loadHashtableBytes(hstSizes)";


        if (hstSizes == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil hstSizes");
            return false;
        }

        FileInputStream fis = null;


        try
        {
            fis = new FileInputStream(this._strPathAbsoluteOrigin);
        }

        catch(FileNotFoundException excFileNotFound)
        {
            excFileNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excFileNotFound caught");
            return false;
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        ZipInputStream zis = new ZipInputStream(bis);

        ZipEntry zeyCur = null;

        try
        {
            while ((zeyCur = zis.getNextEntry()) != null)
            {
                int intSize = (int) zeyCur.getSize();
                // -1 means unknown intSize.

                if (intSize == -1)
                {
                    long lngSize = ((Long) hstSizes.get(zeyCur.getName())).longValue();
                    intSize = (int) lngSize;
                }

                byte[] byts = new byte[(int) intSize];
                int intRb = 0;
                int intChunk = 0;

                while (((int) intSize - intRb) > 0)
                {
                    intChunk = zis.read(byts, intRb, (int) intSize - intRb);

                    if (intChunk == -1)
                    {
                        break;
                    }

                    intRb += intChunk;
                }

                // add to internal resource hashtable
                this._hstJarContents.put(zeyCur.getName(), byts);
            } // end of "while"

            zis.close();
            zis = null;
        }

        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return false;
        }

        // ending
        return true;
    }

    /**
        if any error, exiting
        Q: what about main dir: ""
    **/
    private boolean _isDirectoryEmpty(String strPathRelative)
    {
        String strMethod = "_isDirectoryEmpty(strPathRelative)";

        if (strPathRelative == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strPathRelative");

        }

        Enumeration enuKey = this.getKeys();

        if (enuKey == null) // empty
            return false;

        if (strPathRelative.length() == 0) // root
        {
            MySystem.s_printOutExit(this, strMethod, "NOT DONE YET: strPathRelative.length() == 0, strPathRelative=" + strPathRelative + ", forcing an exiting");

        }

        // !!!!!!!!!!!!!!!! SUBDIR ONLY

        if (! strPathRelative.endsWith(f_s_strFileSeparatorSub)) // all subdir should end with f_s_strFileSeparatorSub
        {
            MySystem.s_printOutExit(this, strMethod, "! strPathRelative.endsWith(\"/\"), strPathRelative=" + strPathRelative + ", forcing an exiting");

        }

        if (! this.containsKey(strPathRelative))
        {
            MySystem.s_printOutExit(this, strMethod, "! strPathRelative.containsKey(strPathRelative), strPathRelative=" + strPathRelative + ", forcing an exiting");

        }



        while (enuKey.hasMoreElements())
		{
	        String strKey = (String) enuKey.nextElement();

	        if (strKey == null)
	        {
	            MySystem.s_printOutExit(this, strMethod, "nil strKey");

	        }

	        if (strKey.compareTo(strPathRelative) == 0)
	            continue;

	        if (strKey.startsWith(strPathRelative))
	            return false;

	    }

        // ending
        return true;

    }

}	// End of FileJar class.
