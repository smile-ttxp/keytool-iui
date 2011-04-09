package com.google.code.p.keytooliui.ktl.io;


import com.google.code.p.keytooliui.shared.io.*;


import java.io.*;

public class S_FileChooserUI extends S_FileChooserAbs
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.";
    
    
    // -------------
    // PUBLIC STATIC
    
    public static File s_getSaveFile(
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String[] strsExtension,
        String strDescription, // relative to the location of the application
        String strDirNameDefault // eg: mysignedfiles
        )
    {
        String strMethod = _f_s_strClass + "s_getSaveFile(..., strsExtension, ...)";
        
        File fle = S_FileChooserAbs.s_getSaveFile(
            cmpFrameParent,
            strApproveButtonText,
            strsExtension,
            strDescription/*, // relative to the location of the application
            strDirNameDefault // eg: myprojects, myexports, mydeploys
            */
        );
        
        if (fle == null)
            return null;
        
        if (! _s_isAllowed(cmpFrameParent, fle))
            return null;
        
        return fle;    
    }
    
    public static File s_getOpenFile(
        java.awt.Component cmpFrameParent,
        String strApproveButtonText,
        String[] strsExtension, // eg: { "jks", "ks" }
        String strDescription, // eg: keystores
        String strDirNameDefault // eg: mysignedfiles, UNUSED RIGHT NOW
        )
    {
        File fle = S_FileChooserAbs.s_getOpenFile(
            cmpFrameParent,
            strApproveButtonText,
            strsExtension,
            strDescription);
        
        if (fle == null)
            return null;
            
        if (! _s_isAllowed(cmpFrameParent, fle))
            return null;
    
        // ending
        return fle;    
    }
    
    
    // ---------
    // PRIVATE STATIC
    
    private static boolean _s_isAllowed(
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
            
            OPAbstract.s_showDialogWarning(cmpFrameParent, strBody);
            return false;
        }
        **/
        
        return true;
    }
    
}