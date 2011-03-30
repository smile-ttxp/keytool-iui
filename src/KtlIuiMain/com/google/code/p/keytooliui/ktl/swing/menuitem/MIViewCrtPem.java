package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import java.awt.event.*;

final public class MIViewCrtPem extends MIAbstract
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
            ".MIViewCrtPem" // class name
            ;

        String strWhere = "com.google.code.p.keytooliui.ktl.swing.menuitem.MIViewCrtPem";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            MIViewCrtPem.STR_TEXT = rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }
    
    // ------
    // PUBLIC
    
    public MIViewCrtPem(ActionListener actListenerParent)
    {
        super(
            MIViewCrtPem.STR_TEXT,
            actListenerParent
            );
    }
}