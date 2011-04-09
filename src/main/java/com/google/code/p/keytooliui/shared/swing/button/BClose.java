package com.google.code.p.keytooliui.shared.swing.button;


import com.google.code.p.keytooliui.shared.lang.*;

//import javax.swing.*;

public final class BClose extends BAbs
{    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strText = null;
    private static String _s_strTip = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.button.BClose";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".BClose" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BClose._s_strText = rbeResources.getString("text");
            BClose._s_strTip = rbeResources.getString("tip");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
        
        strWhere = null;
        strBundleFileShort = null;
        strBundleFileLong = null;
    }
    
    // ------
    // PUBLIC
    
     public BClose(java.awt.event.ActionListener alrParent)
    {
        super(BClose._s_strText, BClose._s_strTip, alrParent);
    }
    
    public BClose()
    {
        super(BClose._s_strText, BClose._s_strTip);
    }
}