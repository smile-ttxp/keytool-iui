package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.*;

import java.awt.*;

final public class MIHelpAboutAppliUIKtl extends MIHelpAboutAppliAbstract
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDlgBodyVersion = "2.4.2";
    
    // -------------
    // STATIC PUBLIC
    
    static public String s_strDlgBodySuffix = null;
    
    
    // ------------------
    // STATIC INITIALIZER

    static
    {    
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.menuitem.MIHelpAboutAppliUIKtl";
        
        try
        {
            String strBundleFileShort =
                com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
                ".MIHelpAboutAppliUIKtl" // class name
                ;
            
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            MIHelpAboutAppliUIKtl.s_strDlgBodySuffix = rbeResources.getString("dlgBodySuffix");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
            
        }
    }
    
    // ------
    // PUBLIC
    
    public MIHelpAboutAppliUIKtl(
        Component cmpFrameOwner,
        String strTitleApplication,
        String strLic)
    {
        super(
            cmpFrameOwner, 
            strTitleApplication, 
            
            MIHelpAboutAppliUIKtl.f_s_strDlgBodyVersion,
            
            // ---
            /*
                modif june 26, 2003
                from v1.1 to v1.2: disabling all
            */
            "", // "Product ID: none", // TEMPO
            //strLic,
            
            // end modif
            
            MIHelpAboutAppliUIKtl.s_strDlgBodySuffix,
            new PTabHelpAppliAdvancedUI()
            );
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
            //com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strAppliUIKtl16
            "aboutappli16.gif"
                );
        
        if (iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil iin");
            return false;
        }
        
        setIcon(iin);
        
        // ending
        return true;
    }
}