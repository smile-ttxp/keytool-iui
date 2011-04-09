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
import javax.swing.text.*;

import java.awt.event.*;
import java.util.*;

import java.beans.*;

public final class MEditEditorText extends MAbstract implements
    ActionListener,
    MEditEditorTextListener,
    PropertyChangeListener
{
    
    /** ##############
        PRIVATE STATIC
        ##############
    **/
    
    private static java.util.ResourceBundle _s_rbeResources;
    
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".MEditEditorText" // class name
        ;
    
    
    private static final String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MEditEditorText";
        
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
        
        this._mimCut.setEnabled(true);
        this._mimCopy.setEnabled(true);
        this._mimPaste.setEnabled(true);
        this._mimSelectAll.setEnabled(true);
        this._mimInsertTime.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        
        this._mimCut.setEnabled(true);
        this._mimCopy.setEnabled(true);
        this._mimPaste.setEnabled(true);
        this._mimSelectAll.setEnabled(true);
        this._mimInsertTime.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    
    
    /*
        This code was done in a hurry!!!!!!!!!!!!!
        Need to be completely rewritten
    */
    public void propertyChange(PropertyChangeEvent evtPropertyChange)
    {
        String f_strMethod = "propertyChange(evtPropertyChange)";
        
        JMenuItem mim = null;
        
        String strClass = evtPropertyChange.getSource().getClass().toString();
        boolean blnIsUndo = false;
        
        if (strClass.endsWith("UndoAction"))
        {
            mim = this._mimUndo;
            blnIsUndo = true;
        }
        
        else if (strClass.endsWith("RedoAction"))
        {
            mim = this._mimRedo;
        }
        
        else
        {
            MySystem.s_printOutExit(this, f_strMethod, strClass + ", unexpected class");
            
        }
        
        
        // got respective button
        String strPropertyName = evtPropertyChange.getPropertyName();
        
        if (strPropertyName.equalsIgnoreCase("enabled"))
        {
            Boolean bol = (Boolean) evtPropertyChange.getNewValue();
            mim.setEnabled(bol.booleanValue());
        }
        
        
        
        else if (strPropertyName.equalsIgnoreCase("Name"))
        {
            String strText = null;
            
            if (blnIsUndo)
            {
                strText = _strPrefixUndo;
            }
            
            else
            {
                strText = _strPrefixRedo;
            }
            
            String strNewValue = (String) evtPropertyChange.getNewValue();
            
            if (strNewValue.endsWith("addition"))
            {
                strText += " " + _strSuffixAddition;
            }
            
            else if (strNewValue.endsWith("deletion"))
            {
                strText += " " + _strSuffixDeletion;
            }
            
            else if (strNewValue.endsWith("style change"))
            {
                strText += " " + _strSuffixStyleChange;
            }
            
            mim.setText(strText);
        }
        
        else
        {
            MySystem.s_printOutExit(this, f_strMethod, "uncaught property name");
            
        }    
    }
    
    
    
    public synchronized void addMEditEditorTextListener(MEditEditorTextListener netListener)
	{
	    if (this._netListenerThis == null)
            this._netListenerThis = MEditEditorTextEventMulticaster.add(this._netListenerThis, netListener);
	}
  
	public synchronized void removeMEditEditorTextListener(MEditEditorTextListener netListener)
	{
	    if (this._netListenerThis != null)
            this._netListenerThis = MEditEditorTextEventMulticaster.remove(this._netListenerThis, netListener);
	}
	
	public void insertTime(MEditEditorTextEvent evtMEditEditorText)
	{		    
	    if (this._netListenerParent != null)
            this._netListenerParent.insertTime(evtMEditEditorText);
	    
	}
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String f_strMethod = "actionPerformed(evtAction)";
        
        if (! (evtAction.getSource() instanceof JMenuItem))
        {
            MySystem.s_printOutExit(this, f_strMethod, "wrong source");
            
        }
        
        JMenuItem mim = (JMenuItem) evtAction.getSource();
        
        if (mim == this._mimInsertTime)
        {
            
            
            MEditEditorTextEvent evtThis = new MEditEditorTextEvent(this, MEditEditorTextEvent.MEDITEDITORTEXTEVENT_INSERTTIME);
            
            if (this._netListenerThis != null)
                this._netListenerThis.insertTime(evtThis);
            
            return;
        }
        
         MySystem.s_printOutExit(this, f_strMethod, "unexpected menuItem");
         
    }
    
    
    public MEditEditorText(MEditEditorTextListener netListenerParent, Hashtable actions, AbstractAction aanUndo, AbstractAction aanRedo)
    {
        String f_strMethod = "MEditEditorText(...)";
        
        this._netListenerParent = netListenerParent;
        _createChildren();
        
        if (! _createListeners(actions, aanUndo, aanRedo))
        {
            MySystem.s_printOutExit(this, f_strMethod, "failed");
            
        }
       
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
        _destroyListeners();
    }
    
    /** #######
        PRIVATE
        #######
    **/
    
     // undo/redo
    private String _strPrefixUndo = null;
	private String _strPrefixRedo = null;
	private String _strSuffixAddition = null;
	private String _strSuffixDeletion = null;
	private String _strSuffixStyleChange = null;
    
    // --------
    // children
    private JMenuItem _mimCut = null;
    private JMenuItem _mimCopy = null;
    private JMenuItem _mimPaste = null;
    
    private JMenuItem _mimSelectAll = null;
    private JMenuItem _mimInsertTime = null;
    private JMenuItem _mimDelete = null;
    
    
    private JMenuItem _mimUndo = null;
    private JMenuItem _mimRedo = null;
    
    // ---------
    // listeners
    private MEditEditorTextListener _netListenerThis = null;
    private MEditEditorTextListener _netListenerParent = null;
    
    
    private void _addChildren()
    {
    
        add(this._mimCut);
        add(this._mimCopy); 
        add(this._mimPaste);
        
        addSeparator();
        add(this._mimSelectAll);
        add(this._mimInsertTime);
        // TEMPO add(_mimDelete);
         
        addSeparator();
        add(this._mimUndo);
        add(this._mimRedo);
    }
    
    
    private Action _getActionByName(Hashtable actions, String name)
    {
        String f_strMethod = "_getActionByName(actions, name)";
        
        if (actions==null || name==null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil arg");
            return null;
        }
        
        
        return (Action)(actions.get(name));
    }


    
    
    private void _createChildren()
    {
        this._mimCut = new JMenuItem();
        this._mimCopy = new JMenuItem();
        this._mimPaste = new JMenuItem();
        
        this._mimSelectAll = new JMenuItem();
        this._mimInsertTime = new JMenuItem();
        this._mimDelete = new JMenuItem();
        
        this._mimUndo = new JMenuItem();
        this._mimRedo = new JMenuItem();
        
    }
    
    private boolean _createListeners(Hashtable actions, AbstractAction aanUndo, AbstractAction aanRedo)
    {
        String f_strMethod = "_createListeners(actions, aanUndo, aanRedo)";
        
        if (actions==null || aanUndo==null || aanRedo==null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil arg");
            return false;
        }
        
        addMEditEditorTextListener(this);
        
        Action act = null;
        
        act = _getActionByName(actions, DefaultEditorKit.cutAction);
        
        if (act == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil act");
            return false;
        }
        
        this._mimCut.addActionListener(act);
        
        act = _getActionByName(actions, DefaultEditorKit.copyAction);
        
        if (act == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil act");
            return false;
        }
        
        this._mimCopy.addActionListener(act);
        
        act = _getActionByName(actions, DefaultEditorKit.pasteAction);
        
        if (act == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil act");
            return false;
        }
        
        this._mimPaste.addActionListener(act);
        
        
        // ------
        
        act = _getActionByName(actions, "select-all");
        
        if (act == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil act");
            return false;
        }
        
        this._mimSelectAll.addActionListener(act);
        
        
        //act = _getActionByName(actions, "insert-content");
        this._mimInsertTime.addActionListener(this);
        
        // ----------
        // ----
        
        act = (Action) aanUndo;
        this._mimUndo.addActionListener(act);
        act.addPropertyChangeListener(this);
        
        
        act = (Action) aanRedo;
        this._mimRedo.addActionListener(act);
        act.addPropertyChangeListener(this);
        
        
        // ----
        return true;
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
	        
	        strValue = _s_rbeResources.getString("text_cut");
	        this._mimCut.setText(strValue);
	        strValue = _s_rbeResources.getString("text_copy");
	        this._mimCopy.setText(strValue);
	        strValue = _s_rbeResources.getString("text_paste");
	        this._mimPaste.setText(strValue);
	        
	        strValue = _s_rbeResources.getString("text_selectAll");
	        this._mimSelectAll.setText(strValue);
	        strValue = _s_rbeResources.getString("text_insertTime");
	        this._mimInsertTime.setText(strValue);
	        strValue = _s_rbeResources.getString("text_delete");
	        this._mimDelete.setText(strValue);
	        
	        
	        

	        
	        // ---------
	        // undo/redo
	        strValue = _s_rbeResources.getString("text_undoPrefix");
	        this._mimUndo.setText(strValue);
	        _strPrefixUndo = strValue;
	        strValue = _s_rbeResources.getString("text_redoPrefix");
	        this._mimRedo.setText(strValue);
	        _strPrefixRedo = strValue;
	        
	        strValue = _s_rbeResources.getString("text_undoRedoAdditionSuffix");
	        _strSuffixAddition = strValue;
	        
	        strValue = _s_rbeResources.getString("text_undoRedoDeletionSuffix");
	        _strSuffixDeletion = strValue;
	        
	        strValue = _s_rbeResources.getString("text_undoRedoStyleChangeSuffix");
	        _strSuffixStyleChange = strValue;
	     
	        
	        // ----------
	        // ----------
	        
	        
	        // MNEMONICS
	        
	        char chrValue;
	        
	        strValue = _s_rbeResources.getString("mnemo_this");
	        chrValue = strValue.trim().charAt(0);
	        setMnemonic(chrValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excMissingResource caught");
	        return false;
	    }
	    
	    catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excIndexOutOfBounds caught");
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
        
        // ----------
        // new window
        
        strName = "cut16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimCut.setIcon(iin);
	    
	    // ---------
        // open Edit
        
        strName = "copy16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimCopy.setIcon(iin);
	    
	    // ----------
        // close Edit
        
        strName = "paste16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimPaste.setIcon(iin);
	    
	    // ----------
	    // select all
	    // VOID: no image
	     
	    // ----------
        // delete
        
        strName = "delete16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimDelete.setIcon(iin);
        
	    
	    // ----------
        // print page
        
        strName = "undo16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimUndo.setIcon(iin);
	    
	    // ----
        // redo
        
        strName = "redo16.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._mimRedo.setIcon(iin);
	    
	    // ending
	    return true;
    }
    
    private void _destroyListeners()
    {
        removeMEditEditorTextListener(this);
        
        if (this._mimInsertTime != null)
        {
            this._mimInsertTime.removeActionListener(this);
            this._mimInsertTime = null;
        }
    }
    
    private void _disableChildrenAtInit()
    {
        this._mimCut.setEnabled(false);
        this._mimCopy.setEnabled(false);
        this._mimPaste.setEnabled(false);
        this._mimSelectAll.setEnabled(false);
        this._mimInsertTime.setEnabled(false);
        this._mimDelete.setEnabled(false);
        this._mimUndo.setEnabled(false);
        this._mimRedo.setEnabled(false);
    }
}