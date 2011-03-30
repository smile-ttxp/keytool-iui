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
 
 
package com.google.code.p.keytooliui.shared.swing.tabbedpane;

/**
    known subclasses:
    . TPMainGenAbs
    . TPMainUIAbs
    . TPMainDin
    
    contains a welcome (panel) tab
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

abstract public class TPMainAbs extends JTabbedPane 
{
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strTextWelcome = null;
    static private String _s_strTipWelcome = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.tabbedpane.TPMainAbs";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".TPMainAbs" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTextWelcome = rbeResources.getString("textWelcome");
            _s_strTipWelcome = rbeResources.getString("tipWelcome");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
    } 
    
    
    
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._pnlWelcome_ != null)
        {
            if (! this._pnlWelcome_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        if (! _addTabs())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        if (this._pnlWelcome_ != null)
        {
            if (getTabCount() > 0)
                setSelectedIndex(0);
        }
        
        
        if (this._chgListenerParent == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._chgListenerParent");
            return false;
        }
        
        addChangeListener(this._chgListenerParent);
        return true;
    }
    
    public void destroy()
    {
        if (this._pnlWelcome_ != null)
        {
            this._pnlWelcome_.destroy();
            this._pnlWelcome_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    // ----
    // children    
    protected PTabAbstract _pnlWelcome_ = null;
    
    
    protected TPMainAbs(
        String strHelpID, 
        javax.swing.event.ChangeListener chgListenerParent)
    {
        super();
        
        this._chgListenerParent = chgListenerParent;
        
        if (strHelpID != null)
            com.google.code.p.keytooliui.shared.help.MyCSH.setHelpIDString(this, strHelpID);
    }
   
    // -------
    // PRIVATE
    
    
    private javax.swing.event.ChangeListener _chgListenerParent = null;
    
    private boolean _addTabs()
    {
        String strMethod = "_addTabs()";
        
        ImageIcon iin;
       
        // --
        
        if (this._pnlWelcome_ != null)
        {
            iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("home16.gif");
                
            if (iin == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil iin");
                return false;
            }
                
            addTab(_s_strTextWelcome, iin, this._pnlWelcome_, _s_strTipWelcome);
	    }
	    // --
	    
	    return true;
    }
    
}