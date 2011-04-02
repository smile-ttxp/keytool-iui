/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.button;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

final public class BESHelpJHTrack extends BESHelpJHAbstract
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strImage = "contextualhelp24.gif";
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strToolTipText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".BESHelpJHTrack" // class name
            ;
    
        
        
        String f_strWhere = "com.google.code.p.keytooliui.shared.swing.button.BESHelpJHTrack";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strToolTipText = rbeResources.getString("toolTipText");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
        
        rbeResources = null;
        f_strBundleFileShort = null;
        f_strWhere = null;
    }
    
    // ------
    // PUBLIC
    
    public void setEnabledHelp(javax.help.HelpBroker hbr)
    {
        if (hbr == null)
            return;
        
        ActionListener alr = new com.google.code.p.keytooliui.shared.help.MyCSH.MyDisplayHelpAfterTracking(hbr);
        
        if (alr == null)
            return;
        
        addActionListener(alr);
        setEnabled(true);
    }
        
    public BESHelpJHTrack(/*javax.help.HelpBroker hbr*/)
    {
        super(
            _s_strToolTipText, 
            _f_s_strImage
            );
        
         setEnabled(false);
    }
}