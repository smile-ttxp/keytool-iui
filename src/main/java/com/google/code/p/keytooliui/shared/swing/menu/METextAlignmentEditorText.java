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


import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.util.*;

public class METextAlignmentEditorText extends MAbstract
{     
    /** ##############
        STATIC PRIVATE
        ##############
    **/
    
    final private static int _S_INT_LEFT = 1;
    final private static int _S_INT_CENTER = 2;
    final private static int _S_INT_RIGHT = 3;
    final private static int _S_INT_DEFAULT = _S_INT_LEFT;
    
    static private java.util.ResourceBundle _s_rbeResources;
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".METextAlignmentEditorText" // class name
        ;
    
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.METextAlignmentEditorText";
        
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
        
        this._mimLeft.setEnabled(true);
        this._mimCenter.setEnabled(true);
        this._mimRight.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        //String f_strMethod = "doFileNew()";
        
        this._mimLeft.setEnabled(true);
        this._mimCenter.setEnabled(true);
        this._mimRight.setEnabled(true);
        
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
        //getAccessibleContext().setAccessibleDescription("Notepad Text Alignment options: select one of several different text alignments for the notepad");  
 
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
    
    public METextAlignmentEditorText(Hashtable actions)
    {
        super();

        _createChildren();
        _createListeners(actions);
        
        
    }
        
    // -------
    // PRIVATE   
     
    private JMenuItem _mimLeft;
    private JMenuItem _mimCenter;
    private JMenuItem _mimRight;
    
    
    
    
        
	
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
	        strValue = _s_rbeResources.getString("text_left");
	        this._mimLeft.setText(strValue);
	        strValue = _s_rbeResources.getString("text_center");
	        this._mimCenter.setText(strValue);
	        strValue = _s_rbeResources.getString("text_right");
	        this._mimRight.setText(strValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, "excMissingResource caught," + _f_s_strBundleFileLong);
	        return false;
	    }
	    
	    return true;
    }
    
    private void _createListeners(Hashtable actions)
    {
        Action act = null;
        
        act = _getActionByName(actions, "left-justify");
        this._mimLeft.addActionListener(act);
        
        act = _getActionByName(actions, "center-justify");
        this._mimCenter.addActionListener(act);
        
        act = _getActionByName(actions, "right-justify");
        this._mimRight.addActionListener(act);
    }
    
    private Action _getActionByName(Hashtable actions, String name)
    {
        return (Action)(actions.get(name));
    }
    
    private void _createChildren()
    {
        this._mimLeft = new JMenuItem();
        this._mimCenter = new JMenuItem();
        this._mimRight = new JMenuItem();
    }
    
    private void _addChildren()
    {
        add(this._mimLeft);
        add(this._mimCenter);
        add(this._mimRight);
    }
    
    private void _disableChildrenAtInit()
    {
        this._mimLeft.setEnabled(false);
        this._mimCenter.setEnabled(false);
        this._mimRight.setEnabled(false);
    }
}

