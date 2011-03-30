package com.google.code.p.keytooliui.ktl.swing.button;

/*

    known subclasses:
    . BESView16
    . BESView24

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

abstract public class BESViewAbs extends BEnabledState
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
            ".BESViewAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESViewAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BESViewAbs._s_strToolTipText = rbeResources.getString("toolTipText");
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
        
    protected BESViewAbs(
        java.awt.event.ActionListener alr, 
        String strImage,
        int intDim)
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage), alr);
        
        String strMethod = "BESViewAbs(alr, strImage)";
        
        if (BESViewAbs._s_strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil BESViewAbs._s_strToolTipText");
            
        setToolTipText(BESViewAbs._s_strToolTipText);
        
        intDim += 8;
        java.awt.Dimension dim = new java.awt.Dimension(intDim, intDim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
    }
    
}