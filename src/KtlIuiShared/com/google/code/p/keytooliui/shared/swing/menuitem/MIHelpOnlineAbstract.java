/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
 
package com.google.code.p.keytooliui.shared.swing.menuitem;

/**
    known subclasses:
    . MIHelpOnlineHome     ==> redirecting to default browser: http://code.google.com/p/keytool-iui//index.html
    . MIHelpOnlineSupport  ==> redirecting to default browser: http://code.google.com/p/keytool-iui//support.html
    . MIHelpOnlineBugs     ==> redirecting to default browser: http://code.google.com/p/keytool-iui//bugs.html
    . MIHelpOnlineDemos    ==> redirecting to default browser: http://code.google.com/p/keytool-iui//preview.html
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.event.*;

abstract public class MIHelpOnlineAbstract extends MIAbstract 
{       
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strDlgErrorBody = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MIHelpOnlineAbstract" // class name
            ;

        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpOnlineAbstract";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strDlgErrorBody = rbeResources.getString("dlgErrorBody");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
    }

    // ---------
    // PROTECTED
    
    protected MIHelpOnlineAbstract(
        final java.awt.Component f_cmpFrameOwner, 
        final String f_strTitleApplication,
        String strText, final String f_strUrl)
    {
        super(strText);
        
        this.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                String strMethod = "MIHelpOnlineAbstract().addActionListener()... actionPerfomed(evtAction)";
                              
                if (! com.google.code.p.keytooliui.shared.io.S_ToBrowserDefault.s_displayURL(f_cmpFrameOwner, f_strTitleApplication, f_strUrl))
                {
                    String strBody = _s_strDlgErrorBody
                        + "\n" 
                        + f_strUrl;
                
                    com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(f_cmpFrameOwner, f_strTitleApplication, strBody);
                }
                
            }
        });
        
    }
}