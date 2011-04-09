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

/*
 *  @author bantchao
 */

package com.google.code.p.keytooliui.ktl.swing.button;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BEnabledState;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpOnlineAbstract;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;



abstract public class BESHelpOnlineAbs extends BEnabledState
{
    // --------------
    // PRIVATE STATIC

    //private static String _STR_TOOL_TIP_TEXT = null;

    // ------------------
    // STATIC INITIALIZER

    /*static
    {

        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".BESViewAbs" // class name
            ;



        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESViewAbs";

        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                java.util.Locale.getDefault());

            BESHelpOnlineAbs._STR_TOOL_TIP_TEXT = rbeResources.getString("toolTipText");
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

    protected BESHelpOnlineAbs(
        final java.awt.Component f_cmpFrameOwner,
     
        String strImage,
        String strToolTipText,
        int intDim,
        final String f_strUrl
         )
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage));

        setToolTipText(strToolTipText);

        intDim += 8;
        java.awt.Dimension dim = new java.awt.Dimension(intDim, intDim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);


        this.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                String strMethod = "MIHelpOnlineAbstract().addActionListener()... actionPerfomed(evtAction)";


                try
                {
                    URI uri = new URI(f_strUrl);
                    java.awt.Desktop.getDesktop().browse(uri);
                }

                catch (Exception exc)
                {
                    exc.printStackTrace();
                    MySystem.s_printOutError(strMethod, "got exception, exc.get<message()=" + exc.getMessage());
                    String strBody = MIHelpOnlineAbstract.STR_DLG_ERROR_BODY
                        + "\n"
                        + f_strUrl;

                    com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(
                            f_cmpFrameOwner, strBody);
                }

            }
        });
    }

}