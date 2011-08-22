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
 
 
package com.google.code.p.keytooliui.shared.swing.menu;

/**
    known subclasses:
    . MToolAllMainRdr
    . MToolAllMainGenAbs

    
    maiy contain:
    . 2 menu: 
        . application tool
        . active project tool
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

public abstract class MToolAllMainAbstract extends MAbstract 
{
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strText = null;
    private static char _s_chrThisMnemo;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MToolAllMainAbstract" // class name
            ;
    
        
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.MToolAllMainAbstract";
        
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
        
        if (this._pam_ != null)
        {
            if (! this._pam_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
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
        if (this._pam_ != null)
            add(this._pam_);   
        
        if (this._mep_ != null)
            add(this._mep_);   
        
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._pam_ != null)
        {
            this._pam_.destroy();
            this._pam_ = null;
        }
        
        if (this._mep_ != null)
        {
            this._mep_.destroy();
            this._mep_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected MToolAppliMainAbs _pam_ = null;
    protected MProjCurAbs _mep_ = null;
    
    
    /**
        if at least one child is enabled, setEnabled(true)
        else setEnabbled(false);
        
        MEMO: for now, children may just be JMenu
    **/
    protected void _updateSetEnabledThis_()
    {
        String strMethod = "_updateSetEnabledThis_()";
        
        Component[] cmps = getMenuComponents();
        
        if (cmps == null)
        {
            if (isEnabled())
                setEnabled(false);
        
            return;
        }
        
        // ----
        boolean blnGotChildEnabled = false;
        
        for (int i=0; i<cmps.length; i++)
        {
            if (cmps[i].isEnabled())
            {
                blnGotChildEnabled = true;
                break;
            }
        }
        
        // ----
        
        if (blnGotChildEnabled)
        {
            if (! isEnabled())
                setEnabled(true);
        }
        
        else
        {
            if (isEnabled())
                setEnabled(false);
        }
    }
    
    protected MToolAllMainAbstract()
    {
        setText(_s_strText);
        setMnemonic(_s_chrThisMnemo);
    }
}