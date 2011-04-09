package com.google.code.p.keytooliui.ktl.swing.button;

/*

    known subclasses:
    . BESSign24

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

public abstract class BESSignAbs extends BEnabledState
{    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strToolTipText = "Sign (not JAR) file with private key";
    
    // ------------------
    // STATIC INITIALIZER

    /*static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".BESSignAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESSignAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strToolTipText = rbeResources.getString("toolTipText");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strWhere = null;
    }*/
    
    // ---------
    // PROTECTED
        
    protected BESSignAbs(java.awt.event.ActionListener alr, String strImage)
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage), alr);
        
        String strMethod = "BESSignAbs(alr, strImage)";
        
        if (BESSignAbs._s_strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil BESSignAbs._s_strToolTipText");
            
        setToolTipText(BESSignAbs._s_strToolTipText);
        
    }
    
}
