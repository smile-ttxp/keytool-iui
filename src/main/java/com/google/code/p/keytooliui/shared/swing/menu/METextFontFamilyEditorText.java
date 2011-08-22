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

public final class METextFontFamilyEditorText extends MAbstract
{ 
    
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _F_S_STR_DIALOG = new String("Dialog");
    private static final String _F_S_STR_DIALOGINPUT = new String("DialogInput");
    private static final String _F_S_STR_MONOSPACED = new String("Monospaced");
    private static final String _F_S_STR_SERIF = new String("Serif");
    private static final String _F_S_STR_SANSSERIF = new String("SansSerif"); 
    
    // ---------------------------
    // PUBLIC STATIC FINAL STRING
    
     public static final String F_S_STR_DEFAULT = _F_S_STR_DIALOG;
    
    /** ################
        PROTECTED STATIC
        ################
    **/
    
   
   
    
    private static java.util.ResourceBundle _s_rbeResources;
    
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".METextFontFamilyEditorText" // class name
        ;
    
    
    private static final String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.METextFontFamilyEditorText";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + "excMissingResource caught");
        }
    }
    
    /** ######
        PUBLIC
        ######
    **/
    
    public boolean doFileNew()
        throws Exception
    {
        //String f_strMethod = "doFileNew()";
        
        this._mimDialog.setEnabled(true);
        this._mimDialogInput.setEnabled(true);
        this._mimMonospaced.setEnabled(true);
        this._mimSerif.setEnabled(true);
        this._mimSansSerif.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
     
    
     public boolean doFileOpen()
        throws Exception
    {
        //String f_strMethod = "doFileOpen()";
        
        this._mimDialog.setEnabled(true);
        this._mimDialogInput.setEnabled(true);
        this._mimMonospaced.setEnabled(true);
        this._mimSerif.setEnabled(true);
        this._mimSansSerif.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    
    
    // LIFO
    public void destroy()
    {
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
        // 0) context
        //getAccessibleContext().setAccessibleDescription("TextEditor Text Font Family options: select one of several different font families for the notepad");
       
        // --
         if (! _loadResourceBundle())
         {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
         }
      
        _addChildren();
        
        
        _disableChildrenAtInit();
    
        // ending
        return true;
    }
        
    
    public METextFontFamilyEditorText()
    {
        _createChildren();
        _createListeners();
    }
    
    // -------
    // PRIVATE
    
    private JMenuItem _mimDialog;
    private JMenuItem _mimDialogInput;
    private JMenuItem _mimMonospaced;
    private JMenuItem _mimSerif;
    private JMenuItem _mimSansSerif;
	
	private boolean _loadResourceBundle()
	{
        final String strMethod = "_loadResourceBundle()";
        
         /* MEMO: trim() not necessary
        */
        try
        {   
            String strValue = null;
            
            // TEXTS
	        strValue = _s_rbeResources.getString("text_this");
	        setText(strValue);
	        strValue = _s_rbeResources.getString("text_dialog");
	        this._mimDialog.setText(strValue);
	        strValue = _s_rbeResources.getString("text_dialogInput");
	        this._mimDialogInput.setText(strValue);
	        strValue = _s_rbeResources.getString("text_monospaced");
	        this._mimMonospaced.setText(strValue);
	        strValue = _s_rbeResources.getString("text_serif");
	        this._mimSerif.setText(strValue);
	        strValue = _s_rbeResources.getString("text_sansSerif");
	        this._mimSansSerif.setText(strValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "excMissingResource caught," + _f_s_strBundleFileLong);
	        return false;
	    }
	  
	    // ending
	    return true;
    }
    
    private void _disableChildrenAtInit()
    {
        // 4) add buttons
        this._mimDialog.setEnabled(false);
        this._mimDialogInput.setEnabled(false);
        this._mimMonospaced.setEnabled(false);
        this._mimSerif.setEnabled(false);
        this._mimSansSerif.setEnabled(false);
    }
    
    private void _addChildren()
    {
        add(this._mimDialog);
        add(this._mimDialogInput);
        add(this._mimMonospaced);
        add(this._mimSerif);
        add(this._mimSansSerif);
    }
    
    private void _createChildren()
    {
        this._mimDialog = new JMenuItem();
        this._mimDialogInput = new JMenuItem();
        this._mimMonospaced = new JMenuItem();
        this._mimSerif = new JMenuItem();
        this._mimSansSerif = new JMenuItem();   
    }
    
    private void _createListeners()
    {
        Action act = null;
        
        act = new StyledEditorKit.FontFamilyAction(_F_S_STR_DIALOG, _F_S_STR_DIALOG);
        this._mimDialog.addActionListener(act);
        
        act = new StyledEditorKit.FontFamilyAction(_F_S_STR_DIALOG, _F_S_STR_DIALOGINPUT);
        this._mimDialogInput.addActionListener(act);
        
        act = new StyledEditorKit.FontFamilyAction(_F_S_STR_DIALOG, _F_S_STR_MONOSPACED);
        this._mimMonospaced.addActionListener(act);
        
        act = new StyledEditorKit.FontFamilyAction(_F_S_STR_DIALOG, _F_S_STR_SERIF);
        this._mimSerif.addActionListener(act);
        
        act = new StyledEditorKit.FontFamilyAction(_F_S_STR_DIALOG, _F_S_STR_SANSSERIF);
        this._mimSansSerif.addActionListener(act);
    }
}

