package com.google.code.p.keytooliui.ktl.swing.button;

/*

 JOOST requirements: view XML file in directory to be jarred
 *
    known subclasses:
    . BESMore16

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

abstract public class BESMoreAbs extends BEnabledState
{    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strToolTipText = "View XML description"; //null;
    
    // ------------------
    // STATIC INITIALIZER

    /*static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".BESMoreAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESMoreAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BESMoreAbs._s_strToolTipText = rbeResources.getString("toolTipText");
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
        
    protected BESMoreAbs(
        java.awt.event.ActionListener alr, 
        String strImage,
        int intDim)
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage), alr);
        
        String strMethod = "BESMoreAbs(alr, strImage)";
        
        if (BESMoreAbs._s_strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil BESMoreAbs._s_strToolTipText");
            
        setToolTipText(BESMoreAbs._s_strToolTipText);
        
        intDim += 8;
        java.awt.Dimension dim = new java.awt.Dimension(intDim, intDim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
    }
    
}
