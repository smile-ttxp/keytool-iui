/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
import com.google.code.p.keytooliui.shared.io.filenamefilter.*;

import java.io.*;

public class S_FileFilterSys
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String  _f_s_strClass = "com.google.code.p.keytooliui.shared.io.S_FileFilterSys.";
    
    // -------------
    // PUBLIC STATIC
    
    public static String[] s_list(String strPathAbsFolderParent)
    {
        String strWhere = _f_s_strClass + "s_list(strPathAbsFolderParent)";
        
        File fle = new File(strPathAbsFolderParent);
        
        if (! fle.exists())
        {
            MySystem.s_printOutError(strWhere, "! fle.exists(), strPathAbsFolderParent=" + strPathAbsFolderParent);
		    return null;
        }
        
        if (! fle.isDirectory())
        {
            MySystem.s_printOutError(strWhere, "! fle.isDirectory(), strPathAbsFolderParent=" + strPathAbsFolderParent);
		    return null;
        }
        
        String[] strsList = null;
        
        try
        {
            strsList = fle.list();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strWhere, "exc caught, strPathAbsFolderParent=" + strPathAbsFolderParent);
		    return null;
        }
        
        if (strsList == null)
        {
            MySystem.s_printOutError(strWhere, "nil strsList, strPathAbsFolderParent=" + strPathAbsFolderParent);
		    return null;
        }
        
        return strsList;
    }
    
    public static File[] s_getChildrenDirectoryOnly(String strPathAbsFolderParent)
    {
        String strWhere = _f_s_strClass + "s_getChildrenDirectoryOnly(strPathAbsFolderParent)";
        
        File fleFolderParent = new File(strPathAbsFolderParent);
        
        if (fleFolderParent == null)
        {
            MySystem.s_printOutError(strWhere, "nil fleFolderParent, strPathAbsFolderParent=" + strPathAbsFolderParent);
		    return null;
        }
		    
		if(! fleFolderParent.exists())
		{
		    MySystem.s_printOutError(strWhere, "fleFolderParent !exists(), fleFolderParent.getAbsolutePath()=" + fleFolderParent.getAbsolutePath());
		    return null;
		}
		
		if(! fleFolderParent.isDirectory())
		{
		    MySystem.s_printOutError(strWhere, "fleFolderParent !isDirectory(), fleFolderParent.getAbsolutePath()=" + fleFolderParent.getAbsolutePath());
		    return null;
		}
		
		if(! fleFolderParent.canRead())
		{
		    MySystem.s_printOutError(strWhere, "fleFolderParent !canRead(), fleFolderParent.getAbsolutePath()=" + fleFolderParent.getAbsolutePath());
		    return null;
		}
		
		File[] flesChildAll = s_getChildrenDirectoryAndFile(fleFolderParent);
        
        if (flesChildAll == null)
        {
            MySystem.s_printOutError(strWhere, "nil flesChildAll");
		    return null;
        }
        
        java.util.Vector<File> vecChildFiltered = new java.util.Vector<File>();
        
        for (int i=0; i<flesChildAll.length; i++)
        {
            if (! flesChildAll[i].isDirectory())
                continue;
            // dojob
            vecChildFiltered.addElement(flesChildAll[i]);
        }
        
        File[] flesDirOnly = new File[vecChildFiltered.size()];
        
        for (int i=0; i<vecChildFiltered.size(); i++)
        {
            File fleCur = (File) vecChildFiltered.elementAt(i);
            flesDirOnly[i] = fleCur;
        }
        
        return flesDirOnly;
    }
          
    
    /**
        if returning nil, error
    **/
    public static File[] s_getChildrenDirectoryAndFile(File fleFolderParent, String[] strsFileExtension)
    {
        String strWhere = _f_s_strClass + "s_getChildrenDirectoryAndFile(fleFolderParent, strsFileExtension)";
        
        File[] flesChildAll = s_getChildrenDirectoryAndFile(fleFolderParent);
        
        if (flesChildAll == null)
        {
            MySystem.s_printOutError(strWhere, "nil flesChildAll");
		    return null;
        }
        
        java.util.Vector<File> vecChildFiltered = new java.util.Vector<File>();
        
        if (strsFileExtension == null)
        {
            MySystem.s_printOutError(strWhere, "nil strsFileExtension");
		    return null;
        }
        
        FFExtended ffe = new FFExtended(strsFileExtension);
        
        ffe.init();
        
        for (int i=0; i<flesChildAll.length; i++)
        {
            if (flesChildAll[i].isDirectory())
            {
                // dojob
                vecChildFiltered.addElement(flesChildAll[i]);
                
                continue;
            }
            // not directory
            
            if (! ffe.accept(flesChildAll[i], flesChildAll[i].getName()))
                continue;
                
            vecChildFiltered.addElement(flesChildAll[i]);
        }
        
        
        File[] flesChildFiltered = new File[vecChildFiltered.size()];
        
        for (int i=0; i<vecChildFiltered.size(); i++)
            flesChildFiltered[i] = (File) vecChildFiltered.elementAt(i);
        
        
        // ending
        return flesChildFiltered;
    }
    
    public static File[] s_getChildrenDirectoryAndFile(String strFolderParent)
    {
        String strWhere = _f_s_strClass + "s_getChildrenDirectoryAndFile(strFolderParent)";
        
        if (strFolderParent == null)
        {
            MySystem.s_printOutError(strWhere, "nil strFolderParent");
		    return null;
        }
        
        return s_getChildrenDirectoryAndFile(new File(strFolderParent));
    }
    
    /**
        if returning nil, error
    **/
    public static File[] s_getChildrenDirectoryAndFile(File fleFolderParent)
    {
        String strWhere = _f_s_strClass + "s_getChildrenDirectoryAndFile(fleFolderParent)";
        
        // ----
        // check & get folderParent
        
        
        if (fleFolderParent == null)
        {
            MySystem.s_printOutError(strWhere, "nil fleFolderParent");
		    return null;
        }
		    
		if (! fleFolderParent.exists())
		{
		    MySystem.s_printOutError(strWhere, "fleFolderParent !exists:" + fleFolderParent.getAbsolutePath());
		    return null;
		}
		
		boolean blnOk = false;
		
		try
		{
		    blnOk = fleFolderParent.isDirectory();
		}
		
		catch(java.lang.SecurityException excSecurity)
		{
		    excSecurity.printStackTrace();
		    MySystem.s_printOutError(strWhere, "excSecurity caught, fleFolderParent.getAbsolutePath()=" + fleFolderParent.getAbsolutePath());
		    return null;
		}
		
		if (! blnOk)
		{
		    MySystem.s_printOutError(strWhere, "not a directory: " + fleFolderParent.getAbsolutePath());
		    return null;
		}
		
		 // ----
        // get children list
		
		File[] flesSub = null;
		
		try
		{
		    flesSub = fleFolderParent.listFiles();
		}
		
		catch(java.lang.SecurityException excSecurity)
		{
		    excSecurity.printStackTrace();
		    MySystem.s_printOutError(strWhere, "excSecurity caught");
		    return null;
		}
		
		if (flesSub == null)
		{
		    MySystem.s_printOutError(strWhere, "nil flesSub");
		    return null;
		}
		
		 // -- sorting
        flesSub = com.google.code.p.keytooliui.shared.util.sort.FleSortVector.s_sort(flesSub);
        
        if (flesSub == null)
        {
            MySystem.s_printOutError(strWhere, "nil flesSub");
		    return null;
        }
        
        return flesSub;
    }
    
    
    /**
        IMPORTANT same code as shared_gen.io.S_FileFilterJarProj
        should be rearranged!!!!!!!!!
    **/
    
    // returns all children files (which have the extension in the strsFileExtension list)
    // included in folderParent
    // BUT no directories!
    public static File[] s_getChildrenFileOnly(String strFolderParent, String[] strsFileExtensionLeaf)
    {
        String strWhere = _f_s_strClass + "s_getChildrenFileOnly(strFolderParent, strsFileExtensionLeaf)";
        
        File[] flesSub = _s_getChildrenDirectoryAndFile(strFolderParent);
        
        if (flesSub == null)
        {
            MySystem.s_printOutExit(strWhere, "nil flesSub");
		     
        }
        
        if (flesSub.length < 1)
            return flesSub;
            
        
        File[] flesFileOnly = _s_getFileOnly(flesSub);
        
        if (flesFileOnly == null)
        {
            MySystem.s_printOutExit(strWhere, "nil flesFileOnly");
		     
        }
        
        File[] flesFileOnlyFiltered = _s_getFileOnlyFiltered(flesFileOnly, strsFileExtensionLeaf);
        
        if (flesFileOnlyFiltered == null)
        {
            MySystem.s_printOutExit(strWhere, "nil flesFileOnlyFiltered");
		     
        }
        
        // -- sorting
        flesFileOnlyFiltered = com.google.code.p.keytooliui.shared.util.sort.FleSortVector.s_sort(flesFileOnlyFiltered);
        
        if (flesFileOnlyFiltered == null)
        {
            MySystem.s_printOutExit(strWhere, "nil flesFileOnlyFiltered");
		     
        }
        
        // ending
        return flesFileOnlyFiltered;
    }
    
    // --------------
    // PRIVATE STATIC
    
     /**
        if returning nil, error
    **/
    private static File[] _s_getChildrenDirectoryAndFile(String strFolderParent)
    {
        String strWhere = _f_s_strClass + "_s_getChildrenDirectoryAndFile(strFolderParent)";
        
        // ----
        // check & get folderParent
        
        File fleFolderParent = new File(strFolderParent);
        
        if (fleFolderParent == null)
        {
            MySystem.s_printOutError(strWhere, "nil fleFolderParent:" + strFolderParent);
		    return null;
        }
		    
		if(! fleFolderParent.exists())
		{
		    MySystem.s_printOutError(strWhere, "fleFolderParent !exists:" + strFolderParent);
		    return null;
		}
		
		boolean blnOk = false;
		
		try
		{
		    blnOk = fleFolderParent.isDirectory();
		}
		
		catch(java.lang.SecurityException excSecurity)
		{
		    excSecurity.printStackTrace();
		    MySystem.s_printOutError(strWhere, "excSecurity caught");
		    return null;
		}
		
		if (! blnOk)
		{
		    MySystem.s_printOutError(strWhere, "not a directory: " + fleFolderParent.getAbsolutePath());
		    return null;
		}
		
		 // ----
        // get children list
		
		File[] flesSub = null;
		
		try
		{
		    flesSub = fleFolderParent.listFiles();
		}
		
		catch(java.lang.SecurityException excSecurity)
		{
		    excSecurity.printStackTrace();
		    MySystem.s_printOutError(strWhere, "excSecurity caught");
		    return null;
		}
        
        return flesSub;
    }
    
    // removing all "directory" files
    private static File[] _s_getFileOnly(File[] flesAll)
    {
        String strWhere = _f_s_strClass + "_s_getFileOnly(flesAll)";
        
        java.util.Set<File> setFileOnly = java.util.Collections.synchronizedSet(new java.util.HashSet<File>());
        
        for (int i=0; i<flesAll.length; i++)
		{
		    boolean blnIsDirectory = false;
		    
		    try
		    {
		        blnIsDirectory = flesAll[i].isDirectory();
		    }
		    
		    catch(java.lang.SecurityException excSecurity)
		    {
		        excSecurity.printStackTrace();
		        MySystem.s_printOutExit(strWhere, "excSecurity caught");
		           
		    }
		    
		    if (blnIsDirectory)
		        continue;

		    setFileOnly.add(flesAll[i]);   
		}
		
		File[] flesFileOnly = new File[setFileOnly.size()];
		
		java.util.Iterator itr = setFileOnly.iterator();
        int intId = 0;
        
        while (itr.hasNext())
        {
            File fleCurr = (File) itr.next();
            flesFileOnly[intId] = fleCurr;
            intId++;
		}
		
		return flesFileOnly;
    }
    
     // assuming there are just files (not folders), creating a new file list from the extension list
    private static File[] _s_getFileOnlyFiltered(File[] flesFileOnly, String[] strsFileExtensionLeaf)
    {
        String strWhere = _f_s_strClass + "_s_getFileOnlyFiltered(flesFileOnly, strsFileExtensionLeaf)";
        
        java.util.Set<File> setFileOnlyFiltered = java.util.Collections.synchronizedSet(new java.util.HashSet<File>());
        FFExtended mff = new FFExtended(strsFileExtensionLeaf);
        mff.init();
        
        for (int i=0; i<flesFileOnly.length; i++)
		{
		    if (mff.accept(flesFileOnly[i], flesFileOnly[i].getName()))
		        setFileOnlyFiltered.add(flesFileOnly[i]);   
		}

        // ---
        // ending
        
        File[] flesFileOnlyFiltered = new File[setFileOnlyFiltered.size()];
		
		java.util.Iterator itr = setFileOnlyFiltered.iterator();
        int intId = 0;
        
        while (itr.hasNext())
        {
            File fleCurr = (File) itr.next();
            flesFileOnlyFiltered[intId] = fleCurr;
            intId++;
		}
		
		return flesFileOnlyFiltered;
    }
}