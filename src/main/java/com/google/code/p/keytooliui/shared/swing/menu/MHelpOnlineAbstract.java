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
 
 
 package com.google.code.p.keytooliui.shared.swing.menu;

/**
    
    known subclasses:
    . rcr: MHelpOnlineReader
    . shared_gen: MHelpOnlineBuilder
    . xls: MHelpOnlineUIKtl
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;



abstract public class MHelpOnlineAbstract extends MAbstract 
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strText = null;
    
    static private java.util.ResourceBundle _s_rbeResources;
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".MHelpOnlineAbstract" // class name
        ;
    
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MHelpOnlineAbstract";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strText = _s_rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    
    // ------
    // PUBLIC
    
    public boolean init()
    { 
        String strMethod = "init()";
        
        // init children
        
        if (this._hoaHome == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hoaHome");
            return false;
        }
        
        if (! this._hoaHome.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._hoaSupport == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hoaSupport");
            return false;
        }
        
        if (! this._hoaSupport.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // add children
        
        add(this._hoaHome);
        add(this._hoaSupport);
        
        return true; 
    }
    
    public void destroy()
    {
        if (this._hoaHome != null)
        {
            this._hoaHome.destroy();
            this._hoaHome = null;
        }
        
        if (this._hoaSupport != null)
        {
            this._hoaSupport.destroy();
            this._hoaSupport = null;
        }
    }
    
    
    // ---------
    // PROTECTED
    
    protected MHelpOnlineAbstract(java.awt.Component cmpFrameOwner)
    {
        setText(_s_strText);
        
        javax.swing.ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("webcomponent16.gif");
        setIcon(iin);
        
        this._hoaHome = new MIHelpOnlineHome(cmpFrameOwner);
        this._hoaSupport = new MIHelpOnlineSupport(cmpFrameOwner);
    } 
    
    // -------
    // PRIVATE
    
    private MIHelpOnlineAbstract _hoaHome = null;
    private MIHelpOnlineAbstract _hoaSupport = null;
}