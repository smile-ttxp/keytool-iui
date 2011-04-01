package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIAbstract;

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
            ResourceBundle rbeResources = ResourceBundle.getBundle(strBundleFileShort, Locale.getDefault());

            MIViewCrtPem.STR_TEXT = rbeResources.getString("text");
        }
        catch (MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }

    // ------
    // PUBLIC

    public MIViewCrtPem(ActionListener actListenerParent)
    {
        super(MIViewCrtPem.STR_TEXT, actListenerParent);
    }
}
