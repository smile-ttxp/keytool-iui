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
    Note: JH means JavaHelp

**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

final public class MIHelpJHTrack extends MIHelpJHAbstract
{    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MIHelpJHTrack" // class name
            ;

        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpJHTrack";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strText = rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
    }
    
    // ------
    // PUBLIC 
    
    // standard help
    public void setEnabledHelp(javax.help.HelpBroker hbr)  
    {
       if (hbr == null)
           return;
       
       ActionListener alr = new com.google.code.p.keytooliui.shared.help.MyCSH.MyDisplayHelpAfterTracking(hbr);
       
       if (alr != null)
       {
           this.addActionListener(alr);
           setEnabled(true);
       }
    }  
           
    
    public MIHelpJHTrack()
    {
        super(_s_strText);
        setEnabled(false);
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
        
        if (! _loadResourceIcon())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    // -------
    // PRIVATE
    
    private boolean _loadResourceIcon()
    {
        String strMethod = "_loadResourceIcon()";

        // adding icons
        ImageIcon iin = null;
        
        
        // -- this
        
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("contextualhelp16.gif");
         
        if (iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil iin");
            return false;
        }
 
         
        this.setIcon(iin); 
         
         // ending
        return true;
    }
}