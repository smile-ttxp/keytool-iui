package com.google.code.p.keytooliui.ktl.swing.button;

/*

    known subclasses:
    . BESRenameAlias24

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

abstract public class BESRenameAliasAbs extends BEnabledState
{    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strToolTipText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".BESRenameAliasAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESRenameAliasAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BESRenameAliasAbs._s_strToolTipText = rbeResources.getString("toolTipText");
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
        
    protected BESRenameAliasAbs(
        java.awt.event.ActionListener alr, 
        String strImage,
        int intDim)
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage), alr);
        
        String strMethod = "BESRenameAliasAbs(alr, strImage)";
        
        
        if (BESRenameAliasAbs._s_strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil BESRenameAliasAbs._s_strToolTipText");
            
        setToolTipText(BESRenameAliasAbs._s_strToolTipText);
        
        intDim += 8;
        java.awt.Dimension dim = new java.awt.Dimension(intDim, intDim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
    }
    
}