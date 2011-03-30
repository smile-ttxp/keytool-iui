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

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

final public class MIFileOpen extends MIAbstract 
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MIFileOpen" // class name
            ;
    
        
        
        String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.MIFileOpen";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strText = rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strBundleFileShort = null;
        rbeResources = null;
    }
    
    // ------
    // PUBLIC
    
    public MIFileOpen(ActionListener actListenerParent)
    {
        super(_s_strText);
        this._actListenerParent = actListenerParent;  
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
        
        if (this._actListenerParent != null)
            addActionListener(this._actListenerParent);
        
        if (! _loadResourceImage())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._actListenerParent != null)
        {
            removeActionListener(this._actListenerParent);
            this._actListenerParent = null;
        }
    }
    
    // PRIVATE
    
    private ActionListener _actListenerParent = null;
    
    private boolean _loadResourceImage()
    {
        String f_strMethod = "_loadResourceImage()";
        
        ImageIcon iin = null;
        
        
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("open16.gif");
         
        if (iin == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil iin");
            return false;
        }
         
         
        this.setIcon(iin);
        
        // ending
        return true;
    }
}