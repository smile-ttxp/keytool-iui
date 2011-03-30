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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;


import com.google.code.p.keytooliui.shared.util.eventlistener.*;

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.text.*;


import java.awt.event.*;
import java.util.*;
import java.beans.*;

final public class TBEditorNote extends TBEditorAbstract implements
    PropertyChangeListener
{    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".TBEditorNote" // class name
        ;
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";
    
    // --------------
    // STATIC PRIVATE
    
    static private java.util.ResourceBundle _s_rbeResources;
    
    // ------------------
    // STATIC INITIALIZER    
    

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.toolbar.TBEditorNote";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, _f_s_strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    // ------
    // PUBLIC
    
    public boolean doFileOpen()
        throws Exception
    {
        String f_strMethod = "doFileOpen()";
    
        if (! super._doFileOpen_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        this._btnLeft.setEnabled(true);
        this._btnRight.setEnabled(true);
        this._btnCenter.setEnabled(true);
       
        // -----
        this._btnCut.setEnabled(true);
        this._btnCopy.setEnabled(true);
        this._btnPaste.setEnabled(true);
        this._btnBold.setEnabled(true);
        this._btnItalic.setEnabled(true);
        this._btnUnderline.setEnabled(true);
    
        return true;
    }
    
    public boolean doFileNew()
        throws Exception
    {
        String f_strMethod = "doFileNew()";
        
        if (! super._doFileNew_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        this._btnLeft.setEnabled(true);
        this._btnRight.setEnabled(true);
        this._btnCenter.setEnabled(true);
       
        // -----
        this._btnCut.setEnabled(true);
        this._btnCopy.setEnabled(true);
        this._btnPaste.setEnabled(true);
        this._btnBold.setEnabled(true);
        this._btnItalic.setEnabled(true);
        this._btnUnderline.setEnabled(true);
    
        return true;
    }
    
    
    /*
        This code was done in a hurry!!!!!!!!!!!!!
        Need to be completely rewritten
    */
    public void propertyChange(PropertyChangeEvent evtPropertyChange)
    {
        String f_strMethod = "propertyChange(evtPropertyChange)";
        
        BEnabledState btn = null;
        
        String strClass = evtPropertyChange.getSource().getClass().toString();
        boolean blnIsUndo = false;
        
        if (strClass.endsWith("UndoAction"))
        {
            btn = this._btnUndo;
            blnIsUndo = true;
        }
        
        else if (strClass.endsWith("RedoAction"))
        {
            btn = this._btnRedo;
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
            btn.setEnabled(bol.booleanValue());
        }

        else if (strPropertyName.equalsIgnoreCase("Name"))
        {            
            String strTip = null;
            
            if (blnIsUndo)
            {
                strTip = _strPrefixUndo;
            }
            
            else
            {
                strTip = _strPrefixRedo;
            }
            
            String strNewValue = (String) evtPropertyChange.getNewValue();
            
            if (strNewValue.endsWith("addition"))
            {
                strTip += " " + _strSuffixAddition;
            }
            
            else if (strNewValue.endsWith("deletion"))
            {
                strTip += " " + _strSuffixDeletion;
            }
            
            else if (strNewValue.endsWith("style change"))
            {
                strTip += " " + _strSuffixStyleChange;
            }
            
            btn.setToolTipText(strTip);
        }
        
        else
        {
            MySystem.s_printOutExit(this, f_strMethod, "uncaught property name");
            
        }
    }
    
    
  
    
    public TBEditorNote(
        ActionListener alrParent,
        TBEditorAbstractListener ntlListenerParent, 
        java.util.Hashtable actions, 
        AbstractAction aanUndo, 
        AbstractAction aanRedo)
    {
        super(alrParent, ntlListenerParent);
                
        _createChildren();
        _createListeners(actions, aanUndo, aanRedo);
    }
 
    public boolean init()
    {   
        String f_strMethod = "init()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
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
        
        if (! _addChildren())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
    
        
        _disableChildrenAtInit();
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        super._destroy_();
        _destroyListeners();
    }
    
    // -------
    // PRIVATE
    
    // undo/redo
    private String _strPrefixUndo = null;
	private String _strPrefixRedo = null;
	private String _strSuffixAddition = null;
	private String _strSuffixDeletion = null;
	private String _strSuffixStyleChange = null;
	        
	// ---------
    // listeners
	 
    //private java.util.Vector _vecButton = null; // used for rollover
    
    // children

    private BEnabledState _btnCut = null;
    private BEnabledState _btnCopy = null;
    private BEnabledState _btnPaste = null;
    
    private BEnabledState _btnUndo = null;
    private BEnabledState _btnRedo = null;
    
    private BEnabledState _btnBold = null;
    private BEnabledState _btnItalic = null;
    private BEnabledState _btnUnderline = null;
    
    private BEnabledState _btnLeft = null;
    private BEnabledState _btnCenter = null;
    private BEnabledState _btnRight = null;
    
    private void _createChildren()
    {
        this._btnCut = new BEnabledState();
        this._btnCopy = new BEnabledState();
        this._btnPaste = new BEnabledState();
        
        this._btnUndo = new BEnabledState();
        this._btnRedo = new BEnabledState();
        
        this._btnBold = new BEnabledState();
        this._btnItalic = new BEnabledState();
        this._btnUnderline = new BEnabledState();
        
        this._btnLeft = new BEnabledState();
        this._btnCenter = new BEnabledState();
        this._btnRight = new BEnabledState();
     
        // TODO: left/center/right
        
        // ----
        
        
        /**
        this._vecButton.addElement(this._btnCut);
        this._vecButton.addElement(this._btnCopy);
        this._vecButton.addElement(this._btnPaste);
        this._vecButton.addElement(this._btnBold);
        this._vecButton.addElement(this._btnItalic);
        this._vecButton.addElement(this._btnUnderline);
        
        this._vecButton.addElement(this._btnLeft);
        this._vecButton.addElement(this._btnCenter);
        this._vecButton.addElement(this._btnRight);
        
        this._vecButton.addElement(this._btnUndo);
        this._vecButton.addElement(this._btnRedo);
        **/
        
        // ----
        
        //_setBorderPaintedButton(false);
    }
    
    private void _createListeners(java.util.Hashtable actions, AbstractAction aanUndo, AbstractAction aanRedo)
    {
        /**for (int i=0; i<this._vecButton.size(); i++)
        {
            BEnabledState mbn = (BEnabledState) this._vecButton.elementAt(i);
            mbn.addMouseListener(this);
        }**/
        
        Action act = null;
   
        act = _getActionByName(actions, DefaultEditorKit.cutAction);
        this._btnCut.addActionListener(act);
        
        act = _getActionByName(actions, DefaultEditorKit.copyAction);
        this._btnCopy.addActionListener(act);
        
        act = _getActionByName(actions, DefaultEditorKit.pasteAction);
        this._btnPaste.addActionListener(act);
                
        // ----
        
        act = (Action) aanUndo;
        this._btnUndo.addActionListener(act);
        act.addPropertyChangeListener(this);
        
        act = (Action) aanRedo;
        this._btnRedo.addActionListener(act);
        act.addPropertyChangeListener(this);
        
        act = new StyledEditorKit.BoldAction();
        this._btnBold.addActionListener(act);
        
        act = new StyledEditorKit.ItalicAction();
        this._btnItalic.addActionListener(act);
        
        act = new StyledEditorKit.UnderlineAction();
        this._btnUnderline.addActionListener(act);
        
        act = _getActionByName(actions, "left-justify");
        this._btnLeft.addActionListener(act);
        
        act = _getActionByName(actions, "center-justify");
        this._btnCenter.addActionListener(act);
        
        act = _getActionByName(actions, "right-justify");
        this._btnRight.addActionListener(act);
    }
    
    private void _destroyListeners()
    {        
        /**if (this._vecButton != null)
        {
            for (int i=0; i<this._vecButton.size(); i++)
            {
                BEnabledState mbn = (BEnabledState) this._vecButton.elementAt(i);
                
                if (mbn != null)
                {
                    mbn.removeMouseListener(this);
                }           
            }
        } **/
    }
    
    private boolean _loadResourceImage()
    {
        String f_strMethod = "_loadResourceImage()";
        
        ImageIcon iin = null;
        String strName = null;
        
        
	    
	    
	    // ----------
        // cut
        
        strName = "cut24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnCut.setIcon(iin);
	    
	    // ----------
        // copy
        
        strName = "copy24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnCopy.setIcon(iin);
	    
	    // ----------
        // paste
        
        strName = "paste24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnPaste.setIcon(iin);
	    
	    // ----------
        // undo
        
        strName = "undo24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnUndo.setIcon(iin);
	    
	    // ----------
        // redo
        
        strName = "redo24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnRedo.setIcon(iin);
        
        
        // ----------
        // bold
        
        strName = "bold24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnBold.setIcon(iin);
    
        // ----------
        // italic
        
        strName = "italic24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnItalic.setIcon(iin);
	    
	    
	    // ----------
        // 
        
        strName = "underline24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnUnderline.setIcon(iin);
	    
	    
	    // ----------
        // 
        
        strName = "alignleft24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnLeft.setIcon(iin);
	    
	     // ----------
        // 
        
        strName = "aligncenter24.gif"; // tobe changed: sun's stuff
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnCenter.setIcon(iin);
	    
	     // ----------
        // 
        
        strName = "alignright24.gif"; // tobe changed: sun's stuff
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnRight.setIcon(iin);
	    
	    
	    
	    
	    // ending
	    return true;
    }
    
    private boolean _addChildren()
    {   
        String f_strMethod = "_addChildren()";
        
        if (! _insert_(this._btnUndo))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! _insert_(this._btnRedo))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! _insert_(new JToolBar.Separator(TBAbs._f_s_dimSeparator10_)))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        
        if (! _insert_(this._btnLeft))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
         if (! _insert_(this._btnCenter))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
         if (! _insert_(this._btnRight))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        
        if (! _insert_(new JToolBar.Separator(TBAbs._f_s_dimSeparator10_)))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
    
    
        // ending
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
            
	        
	        strValue = _s_rbeResources.getString("tip_cut");
	        this._btnCut.setToolTipText(strValue);
	        strValue = _s_rbeResources.getString("tip_copy");
	        this._btnCopy.setToolTipText(strValue);
	        strValue = _s_rbeResources.getString("tip_paste");
	        this._btnPaste.setToolTipText(strValue);
	        
	        // ---------
	        // undo/redo
	        strValue = _s_rbeResources.getString("tip_undoPrefix");
	        this._btnUndo.setToolTipText(strValue);
	        _strPrefixUndo = strValue;
	        strValue = _s_rbeResources.getString("tip_redoPrefix");
	        this._btnRedo.setToolTipText(strValue);
	        _strPrefixRedo = strValue;
	        
	        strValue = _s_rbeResources.getString("tip_undoRedoAdditionSuffix");
	        _strSuffixAddition = strValue;
	        
	        strValue = _s_rbeResources.getString("tip_undoRedoDeletionSuffix");
	        _strSuffixDeletion = strValue;
	        
	        strValue = _s_rbeResources.getString("tip_undoRedoStyleChangeSuffix");
	        _strSuffixStyleChange = strValue;
	     
	        
	        // ----------
	        // ----------
	        
	        
	        strValue = _s_rbeResources.getString("tip_bold");
	        this._btnBold.setToolTipText(strValue);
	        strValue = _s_rbeResources.getString("tip_italic");
	        this._btnItalic.setToolTipText(strValue);
	        strValue = _s_rbeResources.getString("tip_underline");
	        this._btnUnderline.setToolTipText(strValue);
	        
	        strValue = _s_rbeResources.getString("tip_left");
	        this._btnLeft.setToolTipText(strValue);
	        strValue = _s_rbeResources.getString("tip_center");
	        this._btnCenter.setToolTipText(strValue);
	        strValue = _s_rbeResources.getString("tip_right");
	        this._btnRight.setToolTipText(strValue);
	        
	        strValue = _s_rbeResources.getString("tip_exitSuffix");
	        super._besExit_.setToolTipText(super._besExit_.getToolTipText() + " " + strValue);
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
    
    
    /**private void _setBorderPaintedButton(boolean bln)
    {
        for (int i=0; i<this._vecButton.size(); i++)
        {
            BEnabledState mbn = (BEnabledState) this._vecButton.elementAt(i);
            mbn.setBorderPainted(bln);
        }
    }**/
    
    private Action _getActionByName(Hashtable actions, String name)
    {
        return (Action)(actions.get(name));
    }
    
    private void _disableChildrenAtInit()
    {
        this._btnCut.setEnabled(false);
        this._btnCopy.setEnabled(false);
        this._btnPaste.setEnabled(false);
        this._btnBold.setEnabled(false);
        this._btnItalic.setEnabled(false);
        this._btnUnderline.setEnabled(false);
        
        this._btnLeft.setEnabled(false);
        this._btnCenter.setEnabled(false);
        this._btnRight.setEnabled(false);
        
        this._btnUndo.setEnabled(false);
        this._btnRedo.setEnabled(false);
        
        /**for (int i=0; i<this._vecButton.size(); i++)
        {
            BEnabledState mbn = (BEnabledState) this._vecButton.elementAt(i);
            mbn.setEnabled(false);
        }**/
    }
}