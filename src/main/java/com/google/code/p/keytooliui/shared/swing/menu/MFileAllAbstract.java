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
    . shared: MFileAllEditor
    . shared: MFileAllViewerFileText
    . rcr: MFileAllReader
    . shared_gen: MFileAllGenAbstrac
    . xls: MFileAllUI


    contains a menuItemExit
    
    IMPORTANT:
    . create and init menuItemExit
    . not adding menuItemExit (done in subclasses)
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import java.awt.event.*;

public abstract class MFileAllAbstract extends MAbstract
{    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strThisText = null;
    private static char _s_chrThisMnemo;
    
    // -----------------
    // STATIC INTIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MFileAllAbstract";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MFileAllAbstract" // class name
        ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strThisText = rbeResources.getString("thisText");
            
            String strThisMnemo = rbeResources.getString("thisMnemo");
            _s_chrThisMnemo = strThisMnemo.trim().charAt(0);
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutExit(strWhere, strBundleFileLong + ", excIndexOutOfBounds caught");
	    }
        
        strBundleFileLong = null;
        strBundleFileShort = null;
        strWhere = null;
    }
    
    // ---------------
    // ABSTRACT PUBLIC
    
    public abstract void updateTreeUI();
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._fet_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._fet_");
            return false;
        }
        
        if (! this._fet_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._fet_ != null)
        {
            this._fet_.destroy();
            this._fet_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected MIFileExit _fet_ = null;
    
    protected MFileAllAbstract(ActionListener actListenerParent)
    {
        setText(_s_strThisText);
        setMnemonic(_s_chrThisMnemo);
        this._fet_ = new MIFileExit(actListenerParent);
    }
 
}