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
    . shared_gen: MActionAllMainGenAbs
    
    MEMO: may or may not contain menuctionProjectActive
**/


import com.google.code.p.keytooliui.shared.lang.*;


abstract public class MActionAllMainAbs extends MAbstract
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strTextThis;
    static private char _s_chrMnemoThis;
    
    // ------------------
    // STATIC INITIALIZER
    

    static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MActionAllMainAbs" // class name
        ;
        
        String strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MActionAllMainAbs";
        
        try
        {
            java.util.ResourceBundle _s_rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTextThis = _s_rbeResources.getString("textThis");
            
            String strMnemoThis = _s_rbeResources.getString("mnemoThis");
            _s_chrMnemoThis = strMnemoThis.trim().charAt(0);
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutExit(strWhere, "excIndexOutOfBounds caught");
	    }
    }
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._mep_ != null)
        {
            if (! this._mep_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            add(this._mep_);
        }
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._mep_ != null)
        {
            this._mep_.destroy();
            this._mep_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected MProjCurAbs _mep_ = null;
   
    
    protected MActionAllMainAbs()
    {
        super();
        
        setText(_s_strTextThis);
        setMnemonic(_s_chrMnemoThis);
    }
}