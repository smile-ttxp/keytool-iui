package com.google.code.p.keytooliui.shared.swing.button;

/*

    known subclasses:
    . BESPrint16
    . BESPrint24

*/


import com.google.code.p.keytooliui.shared.lang.*;

public abstract class BESPrintAbstract extends BEnabledState
{    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strToolTipText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".BESPrintAbstract" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.shared.swing.button.BESPrintAbstract";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BESPrintAbstract._s_strToolTipText = rbeResources.getString("toolTipText");
            
            if (BESPrintAbstract._s_strToolTipText == null)
                MySystem.s_printOutExit(strWhere, "nil BESPrintAbstract._s_strToolTipText");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strWhere = null;
    }
    
    // ---------
    // PROTECTED
        
    protected BESPrintAbstract(java.awt.event.ActionListener alr, String strImage)
    {
        super(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strImage), alr);
        
        setToolTipText(BESPrintAbstract._s_strToolTipText);
    }
    
    // -------
    // PRIVATE
   
}