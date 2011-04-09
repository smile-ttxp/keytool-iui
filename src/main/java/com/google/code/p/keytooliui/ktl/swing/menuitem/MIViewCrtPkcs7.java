package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIAbstract;

public final class MIViewCrtPkcs7 extends MIAbstract
{
    // --------------
    // PRIVATE STATIC

    public static String STR_TEXT = null;

    // ------------------
    // STATIC INITIALIZER

    static

    {    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".MIViewCrtPkcs7" // class name
            ;


        String strWhere = "com.google.code.p.keytooliui.ktl.swing.menuitem.MIViewCrtPkcs7";

        try
        {
            ResourceBundle rbeResources = ResourceBundle.getBundle(strBundleFileShort, Locale.getDefault());

            MIViewCrtPkcs7.STR_TEXT = rbeResources.getString("text");
        }
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }

    // ------
    // PUBLIC

    public MIViewCrtPkcs7(ActionListener actListenerParent)
    {
        super(MIViewCrtPkcs7.STR_TEXT, actListenerParent);
    }
}
