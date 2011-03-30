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
 
 
package com.google.code.p.keytooliui.shared.swing.menu;

/**
    known subclasses:
    . MPrefAllMainRdr
    . MPrefAllMainGenDoc
    . MPrefAllMainGenTpl
    . MPrefAllMainUIKtl
    
    contains:
    . 1 menu: application preferences
    
    maiy contain:
    . 1 menu: active project preferences
**/

import com.google.code.p.keytooliui.shared.lang.*;


abstract public class MPrefAllMainAbstract extends MAbstract 
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strText = null;
    static private char _s_chrThisMnemo;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MPrefAllMainAbstract" // class name
            ;
    
        
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.MPrefAllMainAbstract";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strText = rbeResources.getString("text");
            String strThisMnemo = rbeResources.getString("mnemo");
            _s_chrThisMnemo = strThisMnemo.trim().charAt(0);
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
        
        catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutExit(f_strWhere, "excIndexOutOfBounds caught");
	    }
    }
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._pamPrefAppli_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pamPrefAppli_");
            return false;
        }
        
        if (! this._pamPrefAppli_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._mep_ != null)
        {
            if (! this._mep_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        // --
        add(this._pamPrefAppli_);
        
        if (this._mep_ != null)
            add(this._mep_);   
        
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._pamPrefAppli_ != null)
        {
            this._pamPrefAppli_.destroy();
            this._pamPrefAppli_ = null;
        }
        
        if (this._mep_ != null)
        {
            this._mep_.destroy();
            this._mep_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected MPrefAppliMainAbstract _pamPrefAppli_ = null;
    protected MProjCurAbs _mep_ = null;
    
    protected MPrefAllMainAbstract()
    {
        super();        
        setText(_s_strText);
        setMnemonic(_s_chrThisMnemo);
    }
}