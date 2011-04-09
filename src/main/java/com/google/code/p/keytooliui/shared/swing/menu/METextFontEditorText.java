/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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



import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.util.*;


public final class METextFontEditorText extends MAbstract
{
    
    /** ##############
        PRIVATE STATIC
        ##############
    **/
    
    private static java.util.ResourceBundle _s_rbeResources;
    
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".METextFontEditorText" // class name
        ;
    
    
    private static final String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.METextFontEditorText";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    /** ###############
        ABSTRACT PUBLIC
        ###############
    **/
    
    /** ######
        PUBLIC
        ######
    **/
    
   public boolean doFileNew()
        throws Exception
    {
        String f_strMethod = "doFileNew()";
        
        if (! this._fam.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! this._sty.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        if (! this._siz.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        return true;
    }
 
    public boolean doFileOpen()
        throws Exception
    {
        String f_strMethod = "doFileOpen()";
        
        if (! this._fam.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! this._sty.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        if (! this._siz.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    
    public METextFontEditorText(Hashtable actions)
    {
        _createChildren(actions);
    }
 
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (! _loadResourceBundle())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! _loadResourceImage())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        
        // --
        
        if (this._fam == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._fam");
            return false;
        }
        
        if (! this._fam.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._sty == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._sty");
            return false;
        }
        
        if (! this._sty.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._siz == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._siz");
            return false;
        }
        
        if (! this._siz.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        add(this._fam);
        add(this._sty);
        add(this._siz);
    
        // ending
        return true;
    }
    
    public void destroy()
    {
        _destroyChildren();
       
    }
    
    /** #######
        PRIVATE
        #######
    **/
    
    // --------
    // children
    private METextFontFamilyEditorText _fam = null;
    private METextFontStyleEditorText _sty = null;
    private METextFontSizeEditorText _siz = null;
    
    
    
    // ---------
    // listeners

    
    
 
    
    
    private void _createChildren(Hashtable actions)
    {
        this._fam = new METextFontFamilyEditorText();
        this._sty = new METextFontStyleEditorText();
        this._siz = new METextFontSizeEditorText(actions);
    }
    
    
    
    
    private boolean _loadResourceBundle()
    {
        String f_strMethod = "_loadResourceBundle()";
        
         /* MEMO: trim() not necessary
        */
        
        try
        {   
            String strValue = null;
            
            // TEXTS
	        strValue = _s_rbeResources.getString("text_this");
	        setText(strValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excMissingResource caught");
	        return false;
	    }
	    
        // ending
        return true;
    }
    
    
    private void _destroyChildren()
    {
         if (this._fam != null)
         {
            this._fam.destroy();
            this._fam = null;
         }
         
         if (this._sty != null)
         {
            this._sty.destroy();
            this._sty = null;
         }
         
         if (this._siz != null)
         {
            this._siz.destroy();
            this._siz = null;
         }
    }
    
    
    private boolean _loadResourceImage()
    {
        String f_strMethod = "_loadResourceImage()";
        
        ImageIcon iin = null;
        String strName = null;
        
        
        
        // ----
        // this
        
        strName = "font16.gif"; // tempo
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this.setIcon(iin);
	    
	    // ending
	    return true;
	}
}