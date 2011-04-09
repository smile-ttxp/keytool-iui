package com.google.code.p.keytooliui.ktl.swing.button;

/*


    known subclasses:
    . BESFolderOpen16

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

abstract public class BESFolderOpenAbs extends BEnabledState
{    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strToolTipText = "Expand all from highlighted folder"; //null;
    
    // ------------------
    // STATIC INITIALIZER

    /*static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".BESFolderOpenAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESFolderOpenAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BESFolderOpenAbs._s_strToolTipText = rbeResources.getString("toolTipText");
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
        
    protected BESFolderOpenAbs(
        java.awt.event.ActionListener alr, 
        String strImage,
        int intDim)
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage), alr);
        
        String strMethod = "BESFolderOpenAbs(alr, strImage)";
        
        if (BESFolderOpenAbs._s_strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil BESFolderOpenAbs._s_strToolTipText");
            
        setToolTipText(BESFolderOpenAbs._s_strToolTipText);
        
        intDim += 8;
        java.awt.Dimension dim = new java.awt.Dimension(intDim, intDim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
    }
    
}
