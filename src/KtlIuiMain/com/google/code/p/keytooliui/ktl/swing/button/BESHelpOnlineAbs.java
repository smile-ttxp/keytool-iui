/*
 *  Copyright (C) 2011 geoForge Project
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.google.code.p.keytooliui.ktl.swing.button;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BEnabledState;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpOnlineAbstract;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 *
 * @author bantchao
 *
 * email: bantchao_AT_gmail.com
 * ... please remove "_AT_" from the above string to get the right email address
 *
 */

abstract public class BESHelpOnlineAbs extends BEnabledState
{
    // --------------
    // STATIC PRIVATE

    //static private String _STR_TOOL_TIP_TEXT = null;

    // ------------------
    // STATIC INITIALIZER

    /*static
    {

        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
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
        final String f_strTitleApplication,
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
                            f_cmpFrameOwner, f_strTitleApplication, strBody);
                }

            }
        });
    }

}