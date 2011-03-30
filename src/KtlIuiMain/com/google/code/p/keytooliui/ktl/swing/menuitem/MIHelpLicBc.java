/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import java.awt.event.*;
import java.awt.*;

final public class MIHelpLicBc extends MIAbstract implements
    ActionListener
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
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
            this._strTitleAppli + " - " + MIHelpLicBc._s_strText,
            org.bouncycastle.LICENSE.licenseText
            );
                    
        if (! vsg.init())
            MySystem.s_printOutExit(strMethod, "failed");
                
        vsg.setVisible(true);
    }
    
    public MIHelpLicBc(Component cmpFrameOwner, String strTitleAppli)
    {
        super(
            MIHelpLicBc._s_strText + " " + "..."
            );
        
        this._cmpFrameOwner = cmpFrameOwner;
        this._strTitleAppli = strTitleAppli;
        
        addActionListener(this);
    }
    
    // -------
    // PRIVATE
    
    private Component _cmpFrameOwner = null;
    private String _strTitleAppli = null;
}