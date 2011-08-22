/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
 
package com.google.code.p.keytooliui.shared.swing.menuitem;

/*
 * @author bantchao
 */


/**
    known subclasses:
    . MIHelpOnlineHome     ==> redirecting to default browser: http://code.google.com/p/keytool-iui//index.html
    . MIHelpOnlineSupport  ==> redirecting to default browser: http://code.google.com/p/keytool-iui//support.html
    . MIHelpOnlineBugs     ==> redirecting to default browser: http://code.google.com/p/keytool-iui//bugs.html
    . MIHelpOnlineDemos    ==> redirecting to default browser: http://code.google.com/p/keytool-iui//preview.html
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.event.*;
import java.net.URI;

public abstract class MIHelpOnlineAbstract extends MIAbstract 
{       
    // --------------
    // PRIVATE STATIC
    
    public static String STR_DLG_ERROR_BODY = null;
    
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
                
            STR_DLG_ERROR_BODY = rbeResources.getString("dlgErrorBody");
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
   
        String strText,
        final String f_strUrl)
    {
        super(strText);
        
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
                    String strBody = STR_DLG_ERROR_BODY
                        + "\n"
                        + f_strUrl;

                    com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(
                            f_cmpFrameOwner, strBody);
                }
                
            }
        });
        
    }
}