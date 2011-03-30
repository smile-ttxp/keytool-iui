package com.google.code.p.keytooliui.shared.swing.dialog;

import com.google.code.p.keytooliui.shared.lang.*;

final public class DWClipFileNotFound extends DWClipAbstract
{    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strWhy = null; // "illegal argument.";
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final  String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DWClipFileNotFound" // class name
            ;
        
        final String f_strBundleFileLong = f_strBundleFileShort + ".properties";   
        final String f_strClass = "com.google.code.p.keytooliui.shared.swing.dialog.DWClipFileNotFound.";
    
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
	        _s_strWhy = rbeResources.getString("why");     
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strClass, f_strBundleFileLong + ", excMissingResource caught");
        }
    }
    
    
    
    // ------
    // PUBLIC
    
    
    
    
    public DWClipFileNotFound(
        java.awt.Frame frm,
        String strTitleApplication,
        String strPathRelativeClip)
    {
        super(frm, strTitleApplication, strPathRelativeClip, _s_strWhy);
    }
    
    // -------
    // PRIVATE
}