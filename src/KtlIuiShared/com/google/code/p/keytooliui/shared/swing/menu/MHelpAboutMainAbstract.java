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
 
 
 package com.google.code.p.keytooliui.shared.swing.menu;

/**
    
    
    known subclasses:
    . MHelpAboutMainReader
    . MHelpAboutMainGenDoc
    . MHelpAboutMainGenTpl
    . MHelpAboutMainUIAbs
    
    contains 2 menuItems:
    . aboutProject (optional)
    . aboutAppli (mandatory)
    
    created in subclasses
    
    inited, added, and destroyed in this class
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;


abstract public class MHelpAboutMainAbstract extends MAbstract
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
            ".MHelpAboutMainAbstract" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MHelpAboutMainAbstract";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            MHelpAboutMainAbstract._s_strText = rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        rbeResources = null;
        strBundleFileShort = null;
        strWhere = null;
    }
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._miaAboutAppli_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._miaAboutAppli_");
            return false;
        }
            
        if (! this._miaAboutAppli_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
        add(this._miaAboutAppli_);
        
        if (this._blnAddAboutProj)
        {
            if (this._miaAboutProj_ == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil this._miaAboutProj_");
                return false;
            }
            
            if (! this._miaAboutProj_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            add(this._miaAboutProj_);
        }
        
        
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._miaAboutProj_ != null)
        {
            this._miaAboutProj_.destroy();
            this._miaAboutProj_ = null;
        }
        
        if (this._miaAboutAppli_ != null)
        {
            this._miaAboutAppli_.destroy();
            this._miaAboutAppli_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected MIAbstract _miaAboutProj_ = null;
    protected MIAbstract _miaAboutAppli_ = null;
    
    protected MHelpAboutMainAbstract(boolean blnAddAboutProj)
    {
        super();
        this._blnAddAboutProj = blnAddAboutProj;
        setText(MHelpAboutMainAbstract._s_strText);
        
        javax.swing.ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("about16.gif");
        
        if (iin != null)
            setIcon(iin);
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnAddAboutProj = false;
    
}