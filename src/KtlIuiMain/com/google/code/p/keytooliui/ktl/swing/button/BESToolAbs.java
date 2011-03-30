package com.google.code.p.keytooliui.ktl.swing.button;

/*

    known subclasses:
    . BESTool16
    . BESTool24

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

abstract public class BESToolAbs extends BEnabledState
{    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strToolTipText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".BESToolAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESToolAbs";
        
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
    }
    
    // ---------
    // PROTECTED
        
    protected BESToolAbs(java.awt.event.ActionListener alr, String strImage)
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage), alr);
        
        String strMethod = "BESToolAbs(alr, strImage)";
        
        if (_s_strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil _s_strToolTipText");
            
        setToolTipText(_s_strToolTipText);
        
    }
    
}