package com.google.code.p.keytooliui.shared.swing.button;


import com.google.code.p.keytooliui.shared.lang.*;

abstract public class BESClearEntryAbs extends BEnabledState
{    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strToolTipText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".BESClearEntryAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.shared.swing.button.BESClearEntryAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BESClearEntryAbs._s_strToolTipText = rbeResources.getString("toolTipText");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strWhere = null;
    }
    
    // ------
    // protected
        
    protected BESClearEntryAbs(
        java.awt.event.ActionListener alr,
        javax.swing.ImageIcon iin,
        int intDim)
    {
        super(iin, alr);
                
        
        if (BESClearEntryAbs._s_strToolTipText != null)   
            setToolTipText(BESClearEntryAbs._s_strToolTipText);
            
        setEnabled(false);
        
        intDim += 8;
        java.awt.Dimension dim = new java.awt.Dimension(intDim, intDim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
    }
}