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


/**
    known subclasses:
    . S_FileChooserReaderDoc
    . S_FileChooserGenAbs
    . S_FileChooserUI
**/

import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFileChooser;

import com.google.code.p.keytooliui.shared.AppAbs;
import com.google.code.p.keytooliui.shared.AppMainAbs;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.lang.system.S_SystemShared;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;

abstract public class S_FileChooserAbs
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.S_FileChooserAbs.";
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strErrorFileNotFound = null;
    static private String _s_strErrorWrongExtensionPrefix = null;
    static private String _s_strErrorWrongExtensionSuffix = null;
    
    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".S_FileChooserAbs" // class name
            ;    
    
        String strMethod = "com.google.code.p.keytooliui.shared.io.S_FileChooserAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strErrorFileNotFound = rbeResources.getString("errorFileNotFound"); 
            _s_strErrorWrongExtensionPrefix = rbeResources.getString("errorWrongExtensionPrefix"); 
            _s_strErrorWrongExtensionSuffix = rbeResources.getString("errorWrongExtensionSuffix"); 
            
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strMethod, "excMissingResource caught"); 
        }
    } 
    
    // static private
    
    static private String _s_getBodyDlgErrorExtension(String[] strsExt, String strExtSource,
        String strNameFile)
    {
        String strMethod = S_FileChooserAbs._f_s_strClass + "_s_getBodyDlgErrorExtension(...)";
        
        if (strsExt == null)
            MySystem.s_printOutExit(strMethod, "nil strsExt");
        
        String str = com.google.code.p.keytooliui.shared.lang.string.S_StringShared.s_get(strsExt);
            
        if (str == null)
            MySystem.s_printOutExit(strMethod, "nil str");
            
        String strBody = "Wrong file extension name: " + 
            strExtSource + 
            "\n... should be: " + 
            str;
                
        strBody += "\n\n";
        strBody += "If this is the short name, please enter full name, eg:";
        strBody += "\n" + strNameFile + "." + strsExt[0];
        
        return strBody;
    }
    
    // -------------
    // STATIC PUBLIC
    
    
    /**
        creating a new empty directory, returning the respective "File"
    **/
    static public File s_getNewDirectoryEmpty(
        //String strPathAbsHomeAppli,
        String strDirAppli, // eg: xlb, xls
        String strDirNameDefault, // eg: mydeploys

        String strTitleSuffix,
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String strApproveButtonTip
        )
    {
        return S_FileChooserAbs._s_getNewDirectoryEmpty_(
            //strPathAbsHomeAppli,
            strDirAppli,
            strDirNameDefault, // eg: mydeploys
            
      
            strTitleSuffix,
            cmpFrameParent,
            strApproveButtonText,
            strApproveButtonTip
            );
        
    }
    
    // ----
    
    /**
        if no file extension specified by user & short file name already exists
            ==> not allow to overwrite
        else
            ==> ask for overwrite
    **/
    static public File s_getSaveFile(
 
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String[] strsExtension,
        String strDescription
        )
    {
        String strMethod = _f_s_strClass + "s_getSaveFile(...)";
               
        // --
        
        MySystem.s_printOutTrace(strMethod, "...");
        
		FileFilterExtended ffe = new FileFilterExtended(strsExtension, strDescription);	
	
		JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile_(
		    strApproveButtonText, ffe);
		
        if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
		    MySystem.s_printOutTrace(strMethod, "no nil intReturnValue: " + intReturnValue);
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
	    
	    String strFileExtensionCur = ffe.getExtension(fleSelected);
        
        if (strFileExtensionCur == null)
        {
            File fleSave = null;
            
            for (int i=0; i<strsExtension.length; i++)
            {
                String strAbsolutePath = fleSelected.getAbsolutePath() + "." + strsExtension[i];
                fleSave = new File(strAbsolutePath);
                
                if (fleSave.exists())
                {
                    MySystem.s_printOutTrace(strMethod, "fleSave.exists(), strAbsolutePath=" + strAbsolutePath);
                    
                    OPAbstract.s_showDialogWarning(
                        cmpFrameParent, "File (with same short name) already exists:\n " + strAbsolutePath);
                    
                    return null;
                }
            }
            
            if (fleSave == null)
            {
                MySystem.s_printOutExit(strMethod, "nil fleSave");
            }
            
            
            // WARNING: will return a file with the last extension in the array!
            S_FileChooserAbs._s_fleParentDirLast = fleSave.getParentFile();
            return fleSave;
        }
       
        // there is an extension 
        
        boolean blnGotIt = false;
        
        for (int i=0; i<strsExtension.length; i++)
        {
            if (strFileExtensionCur.equalsIgnoreCase(strsExtension[i]))
            {
                blnGotIt = true;
                break;
            }
        }
        
            
        if (! blnGotIt)
        {                        
            String strBody = S_FileChooserAbs._s_getBodyDlgErrorExtension(strsExtension, strFileExtensionCur,
                fleSelected.getName());
       
            OPAbstract.s_showDialogError(cmpFrameParent,  strBody);
            return null;
        }
        
        File fleSave = new File(fleSelected.getAbsolutePath());
           
        if (fleSave.exists())
        {
           
            MySystem.s_printOutTrace(strMethod, 
                "fleSave.exists(), fleSave.getAbsolutePath()=" + fleSave.getAbsolutePath());
            
            String strBody = "File already exists:";
            
            strBody += "\n";
            strBody += "  ";
            strBody += fleSave.getAbsolutePath();
            strBody += "\n\n";
            strBody += "Overwrite this file?"; 
            
            if (! OPAbstract.s_showWarningConfirmDialog(cmpFrameParent, strBody))
            {
                MySystem.s_printOutTrace(strMethod, "cancelled by user");
                return null;
            }
        
        
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleSave.getParentFile();
        return fleSave;   
    }
    
    static public File s_getOpenFile(

        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String[] strsExtension,
        String strDescription)
    {
        String strMethod = _f_s_strClass + "s_getOpenFile(...)";
        
        // --
        
        FileFilterExtended ffe = new FileFilterExtended(strsExtension, strDescription);
		
	JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile_(
            strApproveButtonText, ffe);
		    
	if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
	    
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
	    
        if (! fleSelected.exists())
        {
            Toolkit.getDefaultToolkit().beep();
            String strBody = _s_strErrorFileNotFound + fleSelected.getAbsolutePath();
            OPAbstract.s_showDialogError(cmpFrameParent, strBody);
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleSelected.getParentFile();
        return fleSelected;   
    }
    
    // ----
    
    // in a hurry, should be refactored with s_getOpenFile
    
    static public File s_getOpenDir(

        java.awt.Component cmpFrameParent,
        String strApproveButtonText
            )
    {
        String strMethod = _f_s_strClass + "s_getOpenDir(...)";
		
	JFileChooser jfc = S_FileChooserAbs._s_getFileChooser_("strTitleSuffix");
		    
	if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
        
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
        {
            return null;
        }
		
	File fleSelected = jfc.getSelectedFile();
			    
	if(fleSelected == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleSelected");
            return null;
        }
        
        File fleOpen = new File(fleSelected.getAbsolutePath());
           
        if (! fleOpen.exists())
        {
            Toolkit.getDefaultToolkit().beep();
            String strBody = _s_strErrorFileNotFound + fleSelected.getAbsolutePath();
            OPAbstract.s_showDialogError(cmpFrameParent, strBody);
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleOpen;   
    }
    
    // ----
    
    static public File s_getOpenFile(

        java.awt.Component cmpFrameParent,
        String strApproveButtonText
            )
    {
        String strMethod = _f_s_strClass + "s_getOpenFile(...)";
		
	JFileChooser jfc = S_FileChooserAbs._s_getFileChooser_("strTitleSuffix");
		    
	if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
	    
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
        {
            return null;
        }
		
	File fleSelected = jfc.getSelectedFile();
			    
	if(fleSelected == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleSelected");
            return null;
        }
        
        File fleOpen = new File(fleSelected.getAbsolutePath());
           
        if (! fleOpen.exists())
        {
            Toolkit.getDefaultToolkit().beep();
            String strBody = _s_strErrorFileNotFound + fleSelected.getAbsolutePath();
            OPAbstract.s_showDialogError(cmpFrameParent, strBody);
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleOpen;   
    }
    
    // ----
    
    static public File s_getSaveFile(
  
        java.awt.Component cmpFrameParent,
        String strApproveButtonText
            )
    {
        String strMethod = _f_s_strClass + "s_getSaveFile(...)";
		
	JFileChooser jfc = S_FileChooserAbs._s_getFileChooser_("strTitleSuffix");
		    
	if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
	    
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
        {
            return null;
        }
		
	File fleSelected = jfc.getSelectedFile();
			    
	if(fleSelected == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleSelected");
            return null;
        }
        
        File fleOpen = new File(fleSelected.getAbsolutePath());
           
        if (fleOpen.exists())
        {
            Toolkit.getDefaultToolkit().beep();
            String strBody = "File already exists!\n  " + fleSelected.getAbsolutePath();
            OPAbstract.s_showDialogError(cmpFrameParent, strBody);
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleOpen;   
    }
    
    static public File s_getOpenFile(

        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String strExtension,
        String strDescription)
    {
        String strMethod = _f_s_strClass + "s_getOpenFile(...)";
        
        // --
        
        FileFilterExtended ffe = new FileFilterExtended(
            new String[] { strExtension }, 
            strDescription
            );
       
		JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile(
		    strApproveButtonText, ffe); 
		    
		if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");   
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
	    
	    String strFileExtensionCur = ffe.getExtension(fleSelected);
        
        if (strFileExtensionCur == null)
        {
            String strAbsolutePath = fleSelected.getAbsolutePath() + "." + strExtension;
            File fleOpen = new File(strAbsolutePath);
           
            if (! fleOpen.exists())
            {
                Toolkit.getDefaultToolkit().beep();
                String strBody = _s_strErrorFileNotFound + strAbsolutePath;
                OPAbstract.s_showDialogError(cmpFrameParent, strBody);
                return null;
            }
            
            
            S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
            return fleOpen;
        }
       
        // there is an extension     
            
        if (! strFileExtensionCur.equalsIgnoreCase(strExtension))        
        { 
            Toolkit.getDefaultToolkit().beep();
            
            String strBody = _s_strErrorWrongExtensionPrefix;
            strBody += " " + strFileExtensionCur;
            strBody += "\n" + _s_strErrorWrongExtensionSuffix;
            strBody += " " + strExtension;
            
            OPAbstract.s_showDialogError(cmpFrameParent,strBody);
            return null;
        }
        
        File fleOpen = new File(fleSelected.getAbsolutePath());
           
        if (! fleOpen.exists())
        {
            Toolkit.getDefaultToolkit().beep();
            String strBody = _s_strErrorFileNotFound + fleSelected.getAbsolutePath();
            OPAbstract.s_showDialogError(cmpFrameParent,strBody);
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleOpen;   
    }
    
    
    /**
        should return [appli.home]/lib
        
        IMPORTANT: take care about JWS
    **/
    static public File s_getOpenParentFileRegTarget()
    {
        String strMethod = _f_s_strClass + "s_getOpenParentFileRegTarget(...)";
        
        File fleFolderParentAppli = null;
	    
	    if (com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws())
	    {
	        fleFolderParentAppli = S_FileSys.s_getPathAbsParentAppli(false); 
	        
	        if (fleFolderParentAppli == null)
            {
                MySystem.s_printOutError(strMethod, "nil fleFolderParentAppli");
                return null;
            }
	    }
	    
	    else
	    {
	        String strPathAbsAppli = System.getProperty("user.dir");
	        fleFolderParentAppli = new File(strPathAbsAppli);
	    }
	    
	    if (! fleFolderParentAppli.exists())
		{
		    MySystem.s_printOutError(strMethod, "! fleFolderParentAppli.exists(), fleFolderParentAppli.getAbsolutePath()=" +
		        fleFolderParentAppli.getAbsolutePath());
		        
            return null;
		}
		
		// ---
				
        String strNameFolderLib = null;
        
        if (AppAbs.s_isDeployedWithJws())
            strNameFolderLib = S_SystemShared.f_s_strPrefixFolderJWS + AppMainAbs.f_s_strNameDirLibraries;
        else
            strNameFolderLib = AppMainAbs.f_s_strNameDirLibraries;
        
        // --
        
        File fleDirLib = new File(fleFolderParentAppli, strNameFolderLib);
        
        // --
        
        if (! fleDirLib.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleDirLib.exists(), fleDirLib.getAbsolutePath()=" +
                fleDirLib.getAbsolutePath());
                    
            return null;
        }
            
        if (! fleDirLib.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "! fleDirLib.isDirectory(), fleDirLib.getAbsolutePath()=" +
                fleDirLib.getAbsolutePath());
                    
            return null;
        }
        
        // ending
        // NOT  S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleDirLib;
    }
    
    
    /**
        registration file, eg: _sreg_xls11.jar
        
        MEMO: no need for JWS handling
    
        using "user.home" as directory to open first
    **/
    
    /** TEMPO IN COMMENTS oct 25, 2003
    static public File s_getOpenFileRegSource(
        String strTitleAppli
        )
    {
        String strMethod = _f_s_strClass + "s_getOpenFileRegSource(...)";
        
        String strApproveButtonText = "Open";
        String strExtension = S_FileExtension.f_s_strJARDocument;
        String strDescription = "JAR files";
        
        // --
        
        FileFilterExtended ffe = new FileFilterExtended(
            new String[] { strExtension }, 
            strDescription
            );
        
		
		JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile(
		    strTitleAppli, strApproveButtonText, ffe);
		    
	    if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
        
	    
	    File fleFolderParentAppli = null;
	    
	    
	    String strPathAbsAppli = System.getProperty("user.home");
	    fleFolderParentAppli = new File(strPathAbsAppli);
	   
	    
	    if (fleFolderParentAppli.exists())
		{
			jfc.setCurrentDirectory(fleFolderParentAppli);
	    }
	    
        
        int intReturnValue = jfc.showDialog((java.awt.Component) null, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if (intReturnValue != 0)
		{
		    //MySystem.s_printOutTrace(strMethod, "intReturnValue != 0");
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if (fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
	    
	    String strFileExtensionCur = ffe.getExtension(fleSelected);
        
        if (strFileExtensionCur == null)
        {
            String strAbsolutePath = fleSelected.getAbsolutePath() + "." + strExtension;
            File fleOpen = new File(strAbsolutePath);
           
            if (! fleOpen.exists())
            {
                Toolkit.getDefaultToolkit().beep();	
                String strBody = _s_strErrorFileNotFound + strAbsolutePath;
                OPAbstract.s_showDialogError((java.awt.Component) null, strTitleAppli, strBody);
                return null;
            }
            
            S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
            return fleOpen;
        }
       
        // there is an extension     
            
        if (! strFileExtensionCur.equalsIgnoreCase(strExtension))        
        { 
            Toolkit.getDefaultToolkit().beep();	
            
            String strBody = _s_strErrorWrongExtensionPrefix;
            strBody += " " + strFileExtensionCur;
            strBody += "\n" + _s_strErrorWrongExtensionSuffix;
            strBody += " " + strExtension;
            
            OPAbstract.s_showDialogError((java.awt.Component) null, strTitleAppli, strBody);
            return null;
        }
        
        File fleOpen = new File(fleSelected.getAbsolutePath());
           
        if (! fleOpen.exists())
        {
            Toolkit.getDefaultToolkit().beep();	
            String strBody = _s_strErrorFileNotFound + fleSelected.getAbsolutePath();
            OPAbstract.s_showDialogError((java.awt.Component) null, strTitleAppli, strBody);
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleOpen;   
    }**/
    
    
    // ----------------
    // STATIC PROTECTED
    
     static protected File _s_getFileOnlyDir_(
        java.awt.Component cmpFrameParent,

        String strTitleSuffix,
        //String strPathAbsHomeAppli,
        String strNameDirSub, // nil value allowed
        String strNameDirSubSub, // nil value allowed
        String strApproveButtonText
    )
    {
        String strMethod = _f_s_strClass + "_s_getFileOnlyDir_(...)";
        
        JFileChooser jfc = S_FileChooserAbs._s_getFileChooser_(strTitleSuffix);
        
        if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
        
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        // --
        
        if (! com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws())
        {
            if (strNameDirSub!=null && strNameDirSubSub!=null)
            {
                File fleRelativeFolder = S_FileChooserAbs._s_getFileDirDocProjectNoJws_(
                    //strPathAbsHomeAppli,
                    strNameDirSub,
                    strNameDirSubSub);
		    
		        if(fleRelativeFolder.exists())
			        jfc.setCurrentDirectory(fleRelativeFolder);
			}
	    }
        
        // --
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
		    //MySystem.s_printOutTrace(strMethod, "no nil intReturnValue: " + intReturnValue);
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
        
        File fleOpen = new File(fleSelected.getAbsolutePath());
           
        if (! fleOpen.exists())
        {
            OPAbstract.s_showDialogError(cmpFrameParent,"file not found: " + fleSelected.getAbsolutePath());
            return null;
        }
        
        if (! fleOpen.isDirectory())
        {
            OPAbstract.s_showDialogError(cmpFrameParent, "not a directory: " + fleSelected.getAbsolutePath());
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleOpen;
 
    }
    
    static protected JFileChooser _s_getFileChooserOnlyFile_(
 
        String strTitleSuffix,
        FileFilterExtended ffe
    )
    {
        String strMethod = _f_s_strClass + "_s_getFileChooserOnlyFile_(strTitleSuffix, ffe)";
        
        JFileChooser jfc = S_FileChooserAbs._s_getFileChooser_(strTitleSuffix);
        
        if (jfc == null)
        {
            MySystem.s_printOutError(strMethod, "nil jfc");
            return null;
        }
        
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (ffe != null)
            jfc.setFileFilter(ffe);
        
 
        return jfc;
    }
    
    
    static protected JFileChooser _s_getFileChooser_(

        String strTitleSuffix
        )
    {
        String strMethod = _f_s_strClass + "_s_getFileChooser_(strTitleSuffix)";
        
        JFileChooser jfc = new JFileChooser();
        
        
        jfc.setDialogTitle(System.getProperty("_appli.title") + " - " + strTitleSuffix);
        jfc.setDialogType(JFileChooser.CUSTOM_DIALOG);
        
        FVExtended mfv = new FVExtended();
		
        if (! mfv.init())
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }

        jfc.setFileView(mfv);

        if (S_FileChooserAbs._s_fleParentDirLast != null)
        {                
            jfc.setCurrentDirectory(S_FileChooserAbs._s_fleParentDirLast);
            return jfc;
        }
        
        
        // ending
        return jfc;
    }
    
    
    
    /**
        if no file extension specified by user & short file name already exists
            ==> not allow to overwrite
        else
            ==> ask for overwrite
    **/
    static protected File _s_getSaveFile_(

        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String[] strsExtension,
        String strDescription//, // relative to the location of the application
        //String strDirMain, // eg: rcr, xlb, tpb, xls
        //String strDirSub // eg: myprojects, myexports, mydeploys
        )
    {
        String strMethod = _f_s_strClass + "_s_getSaveFile_(...)";
               
        // --
        
		FileFilterExtended ffe = new FileFilterExtended(strsExtension, strDescription);	
	
		JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile(
		    strApproveButtonText, ffe);
		
        if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
		    MySystem.s_printOutTrace(strMethod, "no nil intReturnValue: " + intReturnValue);
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
	    
	    String strFileExtensionCur = ffe.getExtension(fleSelected);
        
        if (strFileExtensionCur == null)
        {
            File fleSave = null;
            
            for (int i=0; i<strsExtension.length; i++)
            {
                String strAbsolutePath = fleSelected.getAbsolutePath() + "." + strsExtension[i];
                fleSave = new File(strAbsolutePath);
                
                if (fleSave.exists())
                {
                    MySystem.s_printOutTrace(strMethod, "fleSave.exists(), strAbsolutePath=" + strAbsolutePath);
                    
                    OPAbstract.s_showDialogWarning(
                        cmpFrameParent, "File (with same short name) already exists:\n " + strAbsolutePath);
                    
                    return null;
                }
            }
            
            if (fleSave == null)
            {
                MySystem.s_printOutExit(strMethod, "nil fleSave");
            }
            
            
            // WARNING: will return a file with the last extension in the array!
            S_FileChooserAbs._s_fleParentDirLast = fleSave.getParentFile();
            return fleSave;
        }
       
        // there is an extension 
        
        boolean blnGotIt = false;
        
        for (int i=0; i<strsExtension.length; i++)
        {
            if (strFileExtensionCur.equalsIgnoreCase(strsExtension[i]))
            {
                blnGotIt = true;
                break;
            }
        }
            
        if (! blnGotIt)
        { 
            String strBody = S_FileChooserAbs._s_getBodyDlgErrorExtension(strsExtension, strFileExtensionCur,
                fleSelected.getName());
                     
            OPAbstract.s_showDialogError(cmpFrameParent, strBody);
            return null;
        }
        
        File fleSave = new File(fleSelected.getAbsolutePath());
           
        if (fleSave.exists())
        {
            //OPAbstract.s_showDialogError(cmpFrameParent, "File already exists:\n " + fleSelected.getAbsolutePath());
            //return null;
        
            MySystem.s_printOutTrace(strMethod, 
                "fleSave.exists(), fleSave.getAbsolutePath()=" + fleSave.getAbsolutePath());
            
            String strBody = "File already exists:";
            
            strBody += "\n";
            strBody += "  ";
            strBody += fleSave.getAbsolutePath();
            strBody += "\n\n";
            strBody += "Overwrite this file?"; 
            
            if (! OPAbstract.s_showWarningConfirmDialog(cmpFrameParent,  strBody))
            {
                MySystem.s_printOutTrace(strMethod, "cancelled by user");
                return null;
            }
        
        
        }
       
        S_FileChooserAbs._s_fleParentDirLast = fleSave.getParentFile();
        return fleSave;   
    }
    
    static protected File _s_getSaveFile_(

        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String strExtension,
        String strDescription//, // relative to the location of the application
        //String strDirMain, // eg: rcr, xlb, tpb, xls
        //String strDirSub // eg: myprojects, myexports, mydeploys
        )
    {
        String strMethod = _f_s_strClass + "_s_getSaveFile_(...)";
               
        // --
        
	    FileFilterExtended ffe = new FileFilterExtended(
	        new String[] { strExtension }, 
	        strDescription
	        );
	    
		JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile(
		    strApproveButtonText, ffe);
		    
		if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");

        // --
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
		    //MySystem.s_printOutTrace(strMethod, "no nil intReturnValue: " + intReturnValue);
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
	    
	    String strFileExtensionCur = ffe.getExtension(fleSelected);
        
        if (strFileExtensionCur == null)
        {
            String strAbsolutePath = fleSelected.getAbsolutePath() + "." + strExtension;
            File fleSave = new File(strAbsolutePath);
           
            if (fleSave.exists())
            {
                MySystem.s_printOutTrace(strMethod, "fleSave.exists(), strAbsolutePath=" + strAbsolutePath);
                    
                OPAbstract.s_showDialogWarning(
                    cmpFrameParent, "File (with same short name) already exists:\n " + strAbsolutePath);
                    
                return null;
            }
           
            S_FileChooserAbs._s_fleParentDirLast = fleSave.getParentFile();
            return fleSave;
        }
       
        // there is an extension     
            
        if (! strFileExtensionCur.equalsIgnoreCase(strExtension))
        { 
            
                
            OPAbstract.s_showDialogError(cmpFrameParent,  "wrong file extension name: " + strFileExtensionCur + "\n... should be: " + strExtension);
            return null;
        }
        
        File fleSave = new File(fleSelected.getAbsolutePath());
           
        if (fleSave.exists())
        {
            
            MySystem.s_printOutTrace(strMethod, 
                "fleSave.exists(), fleSave.getAbsolutePath()=" + fleSave.getAbsolutePath());
            
            String strBody = "File already exists:";
            
            strBody += "\n";
            strBody += "  ";
            strBody += fleSave.getAbsolutePath();
            strBody += "\n\n";
            strBody += "Overwrite this file?"; 
            
            if (! OPAbstract.s_showWarningConfirmDialog(cmpFrameParent, strBody))
            {
                MySystem.s_printOutTrace(strMethod, "cancelled by user");
                return null;
            } 
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleSave.getParentFile();
        return fleSave;   
    }
    
    
    /**
        september 30, 2003:
        changing, if appli launched by JWS, 
        then just opening up "user.home" directory
        ==> don't show JWS cache folder
    **/
    static protected File _s_getOpenFile_(

        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String strExtension,
        String strDescription//,
        //String strDirMain, // eg: rcr, xlb, tpb, xls
        //String strDirSub // eg: myprojects, myexports, mydeploys
        )
    {
        String strMethod = _f_s_strClass + "_s_getOpenFile_(...)";
        
        // --
        FileFilterExtended ffe = new FileFilterExtended(
            new String[] { strExtension }, 
            strDescription
            );
		
		JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile(
		    strApproveButtonText, ffe);
		    
		if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");  
        
        // --
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
	       
        
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        MySystem.s_printOutError(strMethod, "nil fleSelected");
			return null;
	    }
	    
	    String strFileExtensionCur = ffe.getExtension(fleSelected);
        
        if (strFileExtensionCur == null)
        {
            String strAbsolutePath = fleSelected.getAbsolutePath() + "." + strExtension;
            File fleOpen = new File(strAbsolutePath);
           
            if (! fleOpen.exists())
            {
                Toolkit.getDefaultToolkit().beep();
                String strBody = _s_strErrorFileNotFound + strAbsolutePath;
                OPAbstract.s_showDialogError(cmpFrameParent, strBody);
                return null;
            }
            
            S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
            return fleOpen;
        }
       
        // there is an extension     
            
        if (! strFileExtensionCur.equalsIgnoreCase(strExtension))        
        { 
            Toolkit.getDefaultToolkit().beep();
            
            String strBody = _s_strErrorWrongExtensionPrefix;
            strBody += " " + strFileExtensionCur;
            strBody += "\n" + _s_strErrorWrongExtensionSuffix;
            strBody += " " + strExtension;
            
            
            OPAbstract.s_showDialogError(cmpFrameParent, strBody);
            return null;
        }
        
        File fleOpen = new File(fleSelected.getAbsolutePath());
           
        if (! fleOpen.exists())
        {
            Toolkit.getDefaultToolkit().beep();
            String strBody = _s_strErrorFileNotFound + fleSelected.getAbsolutePath();
            OPAbstract.s_showDialogError(cmpFrameParent, strBody);
            return null;
        }
        
        S_FileChooserAbs._s_fleParentDirLast = fleOpen.getParentFile();
        return fleOpen;   
    }
    
    /**
        eg:
        linux, unix: (shared_gen) myprojects: [appli.home]/doc/hj[x]/myprojects
        
        . strDirSub: myprojects
        . strPathAbsHomeAppli: [appli.home]
        . strDirMain: hj[x] where "[x]" is either "r", "w" or "t"
        
        should not be called under JWS
    **/
    
    static protected File _s_getFileDirDocProjectNoJws_(
        //String strPathAbsHomeAppli, 
        String strDirMain, 
        String strDirSub // nil value NOT allowed
        )
    {
        String strMethod = _f_s_strClass + "_s_getFileDirDocProjectNoJws_(strDirMain, strDirSub)";
        
        if (com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws())
            MySystem.s_printOutExit(strMethod, "CODING ERROR: com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws()");  
        
        String strPathAbsHomeAppli = System.getProperty("user.dir");
        
        if (strPathAbsHomeAppli == null)
        {
            MySystem.s_printOutError(strMethod, "nil strPathAbsHomeAppli");
            return null;
        }
        
        if (strDirMain == null)
        {
            MySystem.s_printOutError(strMethod, "nil strDirMain");
            return null;
        }
        
        if (strDirSub == null)
        {
            MySystem.s_printOutError(strMethod, "nil strDirSub");
            return null;
        }
        
        
        File fleDoc = new File(strPathAbsHomeAppli + File.separator + com.google.code.p.keytooliui.shared.io.FileLocation.f_strDocumentation);
         
        if (fleDoc == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleDoc");
            return null;
        }
        
        if (! fleDoc.exists())
        {
            try
            {
                boolean blnOk = fleDoc.mkdir();
                
                if (! blnOk)
                {
                    MySystem.s_printOutError(strMethod, "failed to make dir: " + fleDoc.getAbsolutePath());
                    return null;
                }
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(strMethod, "exc caught");
                return null;
            }
        }
        
        else if (! fleDoc.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "not a dir: " + fleDoc.getAbsolutePath());
            return null;
        }
        
        // ---
        
        File fleDocHjX = new File(fleDoc, strDirMain);
   
        
        if (fleDocHjX == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleDocHjX");
            return null;
        }
        
        if (! fleDocHjX.exists())
        {
            try
            {
                boolean blnOk = fleDocHjX.mkdir();
                
                if (! blnOk)
                {
                    MySystem.s_printOutError(strMethod, "failed to make dir: " + fleDocHjX.getAbsolutePath());
                    return null;
                }
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(strMethod, "exc caught");
                return null;
            }
        }
        
        else if (! fleDocHjX.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "not a dir: " + fleDocHjX.getAbsolutePath());
            return null;
        }
        
        // -- final
        
        File fleDocHjXDir = new File(fleDocHjX, strDirSub);
        
        if (fleDocHjXDir == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleDocHjXDir");
            return null;
        }
        
        if (! fleDocHjXDir.exists())
        {
            try
            {
                boolean blnOk = fleDocHjXDir.mkdir();
                
                if (! blnOk)
                {
                    MySystem.s_printOutError(strMethod, "failed to make dir: " + fleDocHjXDir.getAbsolutePath());
                    return null;
                }
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(strMethod, "exc caught");
                return null;
            }
        }
        
        else if (! fleDocHjXDir.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "not a dir: " + fleDocHjXDir.getAbsolutePath());
            return null;
        }
        
        // ----
       
        return fleDocHjXDir;
        
    }
    
    
    // --
    
    /**
        creating a new empty directory, returning the respective "File"
    
        eg, args:
        
            . strDirMain:
              . rcr
              . xlb
              . tpb
              . xls
            
            . strDirSub:
              . rcr: language-dependent
              . xlb, tpb: myexports
    **/
    
    static protected File _s_getNewDirectoryEmpty_(
        //String strPathAbsHomeAppli,
        
        String strNameDirSub, // nil value allowed
        String strNameDirSubSub,  // nil value alloed

        String strTitleSuffix,
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String strApproveButtonTip)
    {
        String strMethod = _f_s_strClass + "_s_getNewDirectoryEmpty_(...)";
        
        /*
            oct 25, 2003: changes
            if WebStart, set current directory to ?user.home?
        */
	
		JFileChooser jfc = S_FileChooserAbs._s_getFileChooser_(strTitleSuffix);
        
        if (jfc == null)
            MySystem.s_printOutExit(strMethod, "nil jfc");
        
        jfc.setApproveButtonToolTipText(strApproveButtonTip);
        // --
        
        if (! com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws())
        {
            if (strNameDirSub!=null && strNameDirSubSub!=null)
            {
                
	            File fleRelativeFolder = S_FileChooserAbs._s_getFileDirDocProjectNoJws_(
	                //strPathAbsHomeAppli, 
	                strNameDirSub,
	                strNameDirSubSub
	            );
        	    
	            if (fleRelativeFolder == null)
	                MySystem.s_printOutExit(strMethod, "nil fleRelativeFolder");
        		    
		        if(fleRelativeFolder.exists())
			        jfc.setCurrentDirectory(fleRelativeFolder);
			}
	    }
        
        int intReturnValue = jfc.showDialog(cmpFrameParent, strApproveButtonText);
        
        // ----
        // either "cancel" or "windowClosing"
        if(intReturnValue != 0)
		{
		    //MySystem.s_printOutTrace(strMethod, "no nil intReturnValue: " + intReturnValue);
            return null;
		}
		
		File fleSelected = jfc.getSelectedFile();
			    
	    if(fleSelected == null)
		{
	        //MySystem.s_printOutTrace(strMethod, "nil fleSelected");
			return null;
	    }
	    
        
        File fleSave = new File(fleSelected.getAbsolutePath());
           
        if (fleSave.exists())
        {
            OPAbstract.s_showDialogError(
            cmpFrameParent, "File (or directory) already exists:\n " + fleSelected.getAbsolutePath());
            return null;
        }
            
        return fleSave; 
    }
    
    // --------------
    // STATIC PRIVATE
    
    static private File _s_fleParentDirLast = null;
    
    
   
    
    static private JFileChooser _s_getFileChooserOnlyFile(

        String strTitleSuffix,
        FileFilterExtended ffe
    )
    {
        String strMethod = _f_s_strClass + "_s_getFileChooserOnlyFile(strTitleSuffix, ffe)";
        
        JFileChooser jfc = S_FileChooserAbs._s_getFileChooserOnlyFile_(strTitleSuffix, ffe);
        
        if (jfc == null)
        {
            MySystem.s_printOutError(strMethod, "nil jfc");
            return null;
        }
        
        
        
        // --
        
        if (com.google.code.p.keytooliui.shared.AppAbs.s_isDeployedWithJws())
	    {            
            String strPathAbsUserHome = System.getProperty("user.home");
            File flePathAbs2Open = new File(strPathAbsUserHome);
            
            // !!!
            if(flePathAbs2Open.exists())
			    jfc.setCurrentDirectory(flePathAbs2Open);
	    }
	    
	    else
	    {
	        String strPathAbsUserDir = System.getProperty("user.dir");
	        File flePathAbsUserDir = new File(strPathAbsUserDir);
	        
	        if (flePathAbsUserDir.exists())
			    jfc.setCurrentDirectory(flePathAbsUserDir);
	    }
        
        // --
 
        return jfc;
    }
    
    
}