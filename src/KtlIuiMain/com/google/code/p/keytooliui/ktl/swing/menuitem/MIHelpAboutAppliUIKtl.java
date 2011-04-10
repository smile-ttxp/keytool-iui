package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.Component;
import javax.swing.ImageIcon;

import com.google.code.p.keytooliui.ktl.swing.panel.PTabHelpAppliAdvancedUI;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpAboutAppliAbstract;

final public class MIHelpAboutAppliUIKtl extends MIHelpAboutAppliAbstract
{
    final static public String f_s_strDlgBodyVersion = "2.5";


    static public String STR_DLG_BODY_SUFFIX = null;


    final static private String _F_STR_NAME_ICON = "aboutappli16.gif";

    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.menuitem.MIHelpAboutAppliUIKtl";

        try
        {
            String strBundleFileShort =
                com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
                ".MIHelpAboutAppliUIKtl" // class name
                ;
            
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            MIHelpAboutAppliUIKtl.STR_DLG_BODY_SUFFIX = rbeResources.getString("dlgBodySuffix");
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
  
        String strLic)

    {
        super(

            cmpFrameOwner, 
 
            
            MIHelpAboutAppliUIKtl.f_s_strDlgBodyVersion,
            
            // ---
            /*
                modif june 26, 2003
                from v1.1 to v1.2: disabling all
            */
            "", // "Product ID: none", // TEMPO
            //strLic,
            
            // end modif
            
            MIHelpAboutAppliUIKtl.STR_DLG_BODY_SUFFIX,
            new PTabHelpAppliAdvancedUI()
            );
    }

    @Override
    public boolean init()
    {
        String strMethod = "init()";

        if (!super.init())
        {
            return false;
        }

        ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
                MIHelpAboutAppliUIKtl._F_STR_NAME_ICON
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