package com.google.code.p.keytooliui.ktl.io;


import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;

import java.io.*;

public class S_FileChooserUI extends S_FileChooserAbs
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.";
    
    
    // -------------
    // STATIC PUBLIC
    
    static public File s_getSaveFile(
        String strTitleAppli,
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String[] strsExtension,
        String strDescription, // relative to the location of the application
        String strDirNameDefault // eg: mysignedfiles
        )
    {
        String strMethod = _f_s_strClass + "s_getSaveFile(..., strsExtension, ...)";
        
        File fle = S_FileChooserAbs.s_getSaveFile(
            strTitleAppli,
            cmpFrameParent,
            strApproveButtonText,
            strsExtension,
            strDescription/*, // relative to the location of the application
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strApplicationDir, // eg: rcr, xlb, tpb, kst
            strDirNameDefault // eg: myprojects, myexports, mydeploys
            */
        );
        
        if (fle == null)
            return null;
        
        if (! _s_isAllowed(strTitleAppli, cmpFrameParent, fle))
            return null;
        
        return fle;    
    }
    
    static public File s_getOpenFile(
        String strTitleAppli,
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String[] strsExtension, // eg: { "jks", "ks" }
        String strDescription, // eg: keystores
        String strDirNameDefault // eg: mysignedfiles, UNUSED RIGHT NOW
        )
    {
        File fle = S_FileChooserAbs.s_getOpenFile(
            strTitleAppli,
            cmpFrameParent,
            strApproveButtonText,
            strsExtension,
            strDescription);
        
        if (fle == null)
            return null;
            
        if (! _s_isAllowed(strTitleAppli, cmpFrameParent, fle))
            return null;
    
        // ending
        return fle;    
    }
    
    
    /**static public File s_getOpenFile(
        String strTitleAppli,
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String strExtension, // eg: jks
        String strDescription, // eg: keystores
        String strDirNameDefault // eg: mysignedfiles
        )
    {
        String strMethod = _f_s_strClass + "s_getOpenFile(...)";        
        
        File fle = S_FileChooserAbs._s_getOpenFile_(
            strTitleAppli,
            cmpFrameParent,
            strApproveButtonText,
            strExtension,
            strDescription//, 
            //com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strApplicationDir, // eg: rcr, xlb, tpb, kst
            //strDirNameDefault // eg: myprojects, myexports, mydeploys
        );
        
        if (fle == null)
            return null;
        
        if (! _s_isAllowed(strTitleAppli, cmpFrameParent, fle))
            return null;
    
        // ending
        return fle;
    }**/
    
    
    
    /**
        memo:
        . strApplicationDirName: kst
    
    **/
    /**static public File s_getSaveFile(
        String strTitleAppli,
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String strExtension,
        String strDescription, // relative to the location of the application
        String strDirNameDefault // eg: mysignedfiles
        )
    {
        String strMethod = _f_s_strClass + "s_getSaveFile(...)";
        
        File fle = S_FileChooserAbs._s_getSaveFile_(
            strTitleAppli,
            cmpFrameParent,
            strApproveButtonText,
            strExtension,
            strDescription//, // relative to the location of the application
            //com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strApplicationDir, // eg: rcr, xlb, tpb, kst
            //strDirNameDefault // eg: myprojects, myexports, mydeploys
        );
        
        if (fle == null)
            return null;
        
        if (! _s_isAllowed(strTitleAppli, cmpFrameParent, fle))
            return null;
    
        // ending
        return fle;
    }**/
    
    
    // ---------
    // STATIC PRIVATE
    
    static private boolean _s_isAllowed(
        String strTitleAppli,
        java.awt.Component cmpFrameParent, 
        File fle)
    {
        // This was the code used for filenames used in DOS or SHELL commands!
        
        
        /**String strMethod = _f_s_strClass + "_s_isAllowed(...)";
        
        
        // check for valid name: limitations in filenames using DOS!
        //
        String strFileName = fle.getName();
        
        if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedFileName(strFileName))
        {
            MySystem.s_printOutWarning(strMethod, "failed");
             
            String strBody = "File name not supported:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += strFileName;
            strBody += "\"";
            
            strBody += "\n\n";        
            strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRuleFileName();
            
            OPAbstract.s_showDialogWarning(cmpFrameParent, strTitleAppli, strBody);
            return false;
        }
        **/
        
        return true;
    }
    
}