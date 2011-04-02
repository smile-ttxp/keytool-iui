/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.menu;

/**
    known subclasses:
    . MHelpOfflineGenDoc ==> XLBuilder
    
    contains:
    
    . extended class JMenuItem: Standard Features
    . extended class JMenuItem: Help OnItem
    . extended class JMenuItem: Getting Started
**/

import com.google.code.p.keytooliui.shared.swing.menuitem.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


public class MHelpOffline extends MAbstract
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
            com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
            ".MHelpOffline" // class name
            ;

        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MHelpOffline";
        
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
    
    public JMenuItem getItemHelpOnItem() { return this._mihTrack; }
    
     // menubar's menuItem'
    public void setEnabledHelpSourceAndTrack(javax.help.HelpBroker hbrHelpStandard)  
    {
       if (this._mihSource != null)
       {
           this._mihSource.setEnabledHelp(hbrHelpStandard);
           setEnabled(this._mihSource.isEnabled());
       }
       
       if (this._mihTrack != null)
           this._mihTrack.setEnabledHelp(hbrHelpStandard);
    } 
    
    public boolean init()
    { 
        String strMethod = "init()";
        
        if (_s_strText == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil _s_strText");
            return false;
        }
        
        setText(_s_strText);
        
        /*if (! _loadResourceIcon())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/
        
        if (this._mihSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mihSource");
            return false;
        }
        
        if (! this._mihSource.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._mihTrack != null)
        {
            if (! this._mihTrack.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        if (this._mihStarted != null)
        {   
            if (! this._mihStarted.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        
        // --
        
        add(this._mihSource);
        
        if (this._mihTrack != null)
            add(this._mihTrack);
        
        if (this._mihStarted != null)
            add(this._mihStarted);
        
        return true; 
    }
    
    public void destroy() 
    {
        if (this._mihSource != null)
        {
            this._mihSource.destroy();
            this._mihSource = null;
        }
        
        if (this._mihTrack != null)
        {
            this._mihTrack.destroy();
            this._mihTrack = null;
        }
        
        if (this._mihStarted != null)
        {
            this._mihStarted.destroy();
            this._mihStarted = null;
        }
    }
    
    public MHelpOffline(String strTextSource,
        boolean blnDoHelpOnItem)
    {
        this(blnDoHelpOnItem);
        
        if (strTextSource != null)
            this._mihSource.setText(strTextSource);
    }
    
    // ??? protected or private instead ???
    public MHelpOffline(boolean blnDoHelpOnItem)
    {
        super();
        
        this._mihSource = new MIHelpJHSourceStandard();
        setEnabled(this._mihSource.isEnabled());
        
        if (blnDoHelpOnItem)
            this._mihTrack = new MIHelpJHTrack();
    }

    
    // -------
    // PRIVATE
    
    private MIHelpJHAbstract _mihSource = null;
    private MIHelpJHAbstract _mihTrack = null;
    private MIHelpJHSourceAbstract _mihStarted = null;
   
}