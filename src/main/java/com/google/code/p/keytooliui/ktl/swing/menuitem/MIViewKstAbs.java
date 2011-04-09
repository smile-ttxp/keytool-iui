/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */


package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
 known subclasses:
 . MIViewKstJks
 . MIViewKstJceks

 **/

import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIAbstract;

public abstract class MIViewKstAbs extends MIAbstract
{
    // --------------
    // PRIVATE STATIC

    private static String _s_strText = null;

    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =

            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".MIViewKstAbs" // class name
            ;


        String strWhere = "com.google.code.p.keytooliui.ktl.swing.menuitem.MIViewKstAbs";

        try
        {
            ResourceBundle rbeResources = ResourceBundle.getBundle(strBundleFileShort, Locale.getDefault());

            MIViewKstAbs._s_strText = rbeResources.getString("text");
        }
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }

    // ------
    // PUBLIC

    public boolean init()
    {
        String strMethod = "init()";

        if (!super.init())
        {
            return false;
        }

        javax.swing.ImageIcon iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(
                com.google.code.p.keytooliui.ktl.swing.button.BESView16.f_s_strImage);

        if (iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil iin");
            return false;
        }

        setIcon(iin);

        // --
        return true;
    }

    // ---------
    // PROTECTED

    protected MIViewKstAbs(String strTypeKst, ActionListener actListenerParent)
    {
        super(strTypeKst + " " + MIViewKstAbs._s_strText, actListenerParent
        );
    }
}
