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

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.text.*;


public final class METextFontStyleEditorText extends MAbstract 
{
    /** #############
        PUBLIC STATIC
        #############
    **/
    
    public static final boolean F_S_BLN_BOLD = false;
    public static final boolean F_S_BLN_ITALIC = false;
    public static final boolean F_S_BLN_UNDERLINE = false;
    
    /** ##############
        PRIVATE STATIC
        ##############
    **/
    
    private static java.util.ResourceBundle _s_rbeResources;
    
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".METextFontStyleEditorText" // class name
        ;
    
    
    private static final String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.METextFontStyleEditorText";
        
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
    
    /** ######
        PUBLIC
        ######
    **/
    
    public boolean doFileNew()
        throws Exception
    {
        
        this._mimBold.setEnabled(true);
        this._mimItalic.setEnabled(true);
        this._mimUnderline.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        
        this._mimBold.setEnabled(true);
        this._mimItalic.setEnabled(true);
        this._mimUnderline.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    public METextFontStyleEditorText()
    {
        _createChildren();
        _createListeners();
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
        
        _addChildren();
        _disableChildrenAtInit();
        return true;
    }
    
    public void destroy()
    {

    }
    
    /** #######
        PRIVATE
        #######
    **/
    
    // --------
    // children
    private JMenuItem _mimBold = null;
    private JMenuItem _mimItalic = null;
    private JMenuItem _mimUnderline = null;
    
    
    // ---------
    // listeners
    
    private void _addChildren()
    {
        add(this._mimBold);
        add(this._mimItalic);
        add(this._mimUnderline);
    }
    
    private void _disableChildrenAtInit()
    {
        this._mimBold.setEnabled(false);
        this._mimItalic.setEnabled(false);
        this._mimUnderline.setEnabled(false);
    }
    
    
    private void _createChildren()
    {
        this._mimBold = new JMenuItem();
        this._mimItalic = new JMenuItem();
        this._mimUnderline = new JMenuItem();  
    }
    
    private void _createListeners()
    {
        Action act = null;
        
        act = new StyledEditorKit.BoldAction();
        this._mimBold.addActionListener(act);
        
        act = new StyledEditorKit.ItalicAction();
        this._mimItalic.addActionListener(act);
        
        act = new StyledEditorKit.UnderlineAction();
        this._mimUnderline.addActionListener(act);
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
	        
	        
	        strValue = _s_rbeResources.getString("text_bold");
	        this._mimBold.setText(strValue);
	        
	        strValue = _s_rbeResources.getString("text_italic");
	        this._mimItalic.setText(strValue);
	        
	        strValue = _s_rbeResources.getString("text_underline");
	        this._mimUnderline.setText(strValue);
	       
	        
	        // MNEMONICS
	        

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
    
    private boolean _loadResourceImage()
    {
        String f_strMethod = "_loadResourceImage()";
        
        ImageIcon iin = null;
        String strName = null;
        
        // ---------
        // bold
        
        strName = "bold16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimBold.setIcon(iin);
	    
	    // ---------
        // italic
        
        strName = "italic16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimItalic.setIcon(iin);
	    
	    // ---------
        // bold
        
        strName = "underline16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimUnderline.setIcon(iin);
	    
	    // ending
	    return true;
    }
}