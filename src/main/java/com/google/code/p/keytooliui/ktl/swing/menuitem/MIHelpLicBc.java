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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.dialog.DViewString;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIAbstract;

public final class MIHelpLicBc extends MIAbstract implements
        ActionListener
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
            ".MIHelpLicBc" // class name
            ;


        String strWhere = "com.google.code.p.keytooliui.ktl.swing.menuitem.MIHelpLicBc";

        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                    java.util.Locale.getDefault());

            MIHelpLicBc._s_strText = rbeResources.getString("text");
        }

        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }

    // ------
    // PUBLIC

    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";

        DViewString vsg = new DViewString(
            this._cmpFrameOwner,
            org.bouncycastle.LICENSE.licenseText
            );
                    
        if (! vsg.init())
        {
            MySystem.s_printOutExit(strMethod, "failed");
        }

        vsg.setVisible(true);
    }

    public MIHelpLicBc(Component cmpFrameOwner)
    {
        super(
                MIHelpLicBc._s_strText + " " + "..."
        );

        this._cmpFrameOwner = cmpFrameOwner;


        addActionListener(this);
    }

    // -------
    // PRIVATE

    private Component _cmpFrameOwner = null;
  
}