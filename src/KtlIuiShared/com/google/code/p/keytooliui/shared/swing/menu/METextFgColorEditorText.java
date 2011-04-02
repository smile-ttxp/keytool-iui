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

import com.google.code.p.keytooliui.shared.swing.icon.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;


final public class METextFgColorEditorText extends MAbstract 
{     
    /** ##############
        STATIC PRIVATE
        ##############
    **/
    
    final private static Color _S_COL_YELLOW = new Color(204, 204, 0);
    final private static Color _S_COL_BLUE = new Color(0, 0, 204);
    final private static Color _S_COL_RED = new Color(204, 0, 0);
    final private static Color _S_COL_GREEN = new Color(0, 204, 0);
    final private static Color _S_COL_BLACK = java.awt.Color.black; // new Color(204, 204, 204);
    final private static Color _S_COL_DEFAULT = _S_COL_BLACK;
    
    static private java.util.ResourceBundle _s_rbeResources;
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
        ".METextFgColorEditorText" // class name
        ;
    
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.METextFgColorEditorText";
        
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
        //String f_strMethod = "doFileNew()";
        
        this._mimYellow.setEnabled(true);
        this._mimGreen.setEnabled(true);
        this._mimBlue.setEnabled(true);
        this._mimRed.setEnabled(true);
        this._mimBlack.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
     public boolean doFileOpen()
        throws Exception
    {
        //String f_strMethod = "doFileNew()";
        
        this._mimYellow.setEnabled(true);
        this._mimGreen.setEnabled(true);
        this._mimBlue.setEnabled(true);
        this._mimRed.setEnabled(true);
        this._mimBlack.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    
    // LIFO
    public void destroy()
    {
    }
    
  
    
    public boolean init()
    {
        String f_strMethod = "init()" ;
        // 0) context
        //getAccessibleContext().setAccessibleDescription("Notepad Text Fg Color options: select one of several different colors for the application");

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
        
    public METextFgColorEditorText()
    {
        super();
        
        _createChildren();
        _createListeners();
    }
     
    // -------
    // PRIVATE
     
    private JMenuItem _mimYellow;
    private JMenuItem _mimGreen;
    private JMenuItem _mimBlue;
    private JMenuItem _mimRed;
    private JMenuItem _mimBlack;
    
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
	        strValue = _s_rbeResources.getString("text_yellow");
	        this._mimYellow.setText(strValue);
	        strValue = _s_rbeResources.getString("text_green");
	        this._mimGreen.setText(strValue);
	        strValue = _s_rbeResources.getString("text_blue");
	        this._mimBlue.setText(strValue);
	        strValue = _s_rbeResources.getString("text_red");
	        this._mimRed.setText(strValue);
	        strValue = _s_rbeResources.getString("text_black");
	        this._mimBlack.setText(strValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, "excMissingResource caught," + _f_s_strBundleFileLong);
	        return false;
	    }
	    
	    return true;
    }
    
    private void _addChildren()
    {
        add(this._mimYellow);
        add(this._mimGreen);
        add(this._mimBlue);
        add(this._mimRed);
        add(this._mimBlack);
    }
    
    private void _disableChildrenAtInit()
    {
        this._mimYellow.setEnabled(false);
        this._mimGreen.setEnabled(false);
        this._mimBlue.setEnabled(false);
        this._mimRed.setEnabled(false);
        this._mimBlack.setEnabled(false);
    }
    
    private void _createListeners()
    {
        Action act = null;
        
        act = new StyledEditorKit.ForegroundAction("set-foreground-yellow", _S_COL_YELLOW);
        this._mimYellow.addActionListener(act);
        
        act = new StyledEditorKit.ForegroundAction("set-foreground-green", _S_COL_GREEN);
        this._mimGreen.addActionListener(act);
        
        act = new StyledEditorKit.ForegroundAction("set-foreground-blue", _S_COL_BLUE);
        this._mimBlue.addActionListener(act);
        
        act = new StyledEditorKit.ForegroundAction("set-foreground-red", _S_COL_RED);
        this._mimRed.addActionListener(act);
        
        act = new StyledEditorKit.ForegroundAction("set-foreground-black", _S_COL_BLACK);
        this._mimBlack.addActionListener(act);
    }
    
    private void _createChildren()
    {
        this._mimYellow = new JMenuItem();
        this._mimGreen = new JMenuItem();
        this._mimBlue = new JMenuItem();
        this._mimRed = new JMenuItem();
        this._mimBlack = new JMenuItem();
        
        // colored icons
        this._mimYellow.setIcon(new IColoredSquare12(_S_COL_YELLOW));
        this._mimGreen.setIcon(new IColoredSquare12(_S_COL_GREEN));
        this._mimBlue.setIcon(new IColoredSquare12(_S_COL_BLUE));
        this._mimRed.setIcon(new IColoredSquare12(_S_COL_RED));
        this._mimBlack.setIcon(new IColoredSquare12(_S_COL_BLACK));
    }
    
    private boolean _loadResourceImage()
    {
        String f_strMethod = "_loadResourceImage()";
        
        ImageIcon iin = null;
        String strName = null;
        
        // ----
        // this
        
        strName = "color16.gif"; // tempo
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

