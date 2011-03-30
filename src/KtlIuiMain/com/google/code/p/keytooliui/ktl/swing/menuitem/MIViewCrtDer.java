package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import java.awt.event.*;

final public class MIViewCrtDer extends MIAbstract
{
    // --------------
    // STATIC PRIVATE
    
    static public String STR_TEXT = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".MIViewCrtDer" // class name
            ;

        String strWhere = "com.google.code.p.keytooliui.ktl.swing.menuitem.MIViewCrtDer";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            MIViewCrtDer.STR_TEXT = rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }
    
    // ------
    // PUBLIC
    
    public MIViewCrtDer(ActionListener actListenerParent)
    {
        super(
            MIViewCrtDer.STR_TEXT,
            actListenerParent
            );
    }
}