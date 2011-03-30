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
 
 
 package com.google.code.p.keytooliui.shared.swing.textpane;


import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

final public class TPEditorStyled extends TPEditorAbstract 
{    
    // ----------------
    // PUBLIC ACCESSORS
    
    public Hashtable<Object, Action> getAllActions() { return this._hstActions; }
    public AbstractAction getAbstractActionUndo() { return (AbstractAction) this._uan_; }
    public AbstractAction getAbstractActionRedo() { return (AbstractAction) this._ran_; }
    
    
    // ------
    // PUBLIC
    
    public boolean writeTo(java.io.ObjectOutputStream oos)
        throws Exception
    {
        String f_strMethod = "writeTo(oos)";
        DefaultStyledDocument dsd = (DefaultStyledDocument) getDocument();
        
        if (dsd == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil dsd");
            return false;
        }
        
        oos.writeObject(dsd);    
        //MySystem.s_printOutTrace(this, f_strMethod, "oos.writeObject(DefaultStyledDocument)");
        return true;
    }
    
    public boolean doFileOpen(java.io.ObjectInputStream ois)
        throws Exception
    {
        String f_strMethod = "doFileOpen(ois)";
        
        this._uan_.setEnabled(false);
        this._ran_.setEnabled(false);
        
        // initilaizing undoManager
        this._umr_ = new UndoManager();
        
        // ----
        
         // 1) destroying sure?????
        
        DefaultStyledDocument dsdCurrent = (DefaultStyledDocument) getDocument();
        
        if (dsdCurrent != null)
        {
            dsdCurrent.removeDocumentListener(_mdlListener);
            dsdCurrent.removeUndoableEditListener(_mueListener);
            dsdCurrent = null;
        }
        // ----
        
		DefaultStyledDocument dsd = (DefaultStyledDocument) ois.readObject();
		//MySystem.s_printOutTrace(this, f_strMethod, "(DefaultStyledDocument) ois.readObject()");
		//setDocument(dsd);
		
		_setDefaultFont();
		dsd.addDocumentListener(_mdlListener);
	    dsd.addUndoableEditListener(_mueListener);
	    
	    setStyledDocument(dsd); 
        setEnabled(true);
        
        return true;
    }

    /**
        todo: clean-up undo & redo
    **/
    
    public boolean doFileNew()
        throws Exception
    {
        String f_strMethod = "doFileNew()";
        
        //_blnRecorded = false;
        
        // ------------
        // 0) disabling
        
        this._uan_.setEnabled(false);
        this._ran_.setEnabled(false);
        
        // initilaizing undoManager
        this._umr_ = new UndoManager();
        
        // 1) destroying
        
        DefaultStyledDocument dsd = (DefaultStyledDocument) getDocument();
        
        if (dsd == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil dsd");
            return false;
        }
        
        dsd.removeDocumentListener(_mdlListener);
        dsd.removeUndoableEditListener(_mueListener);
        
        // 2) creating
        _setDefaultFont();
            
        StyleContext sct = new StyleContext();
	    dsd = new DefaultStyledDocument(sct);
	    
	    
	    dsd.addDocumentListener(_mdlListener);
	    dsd.addUndoableEditListener(_mueListener);
	    
        setStyledDocument(dsd); 
        setEnabled(true);
        
        return true;
    }
    
    public boolean insertContent(String strContent)
    {
        replaceSelection(strContent);
        return true;
    }

    public TPEditorStyled()
    {
        super();
        
        this._hstActions = new Hashtable<Object, Action>();
        
        _setDefaultFont();
        _createChildren();
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
       
        if (! super._init_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! _createActionTable())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        
        //setEnabled(false);  
    
        return true;
    }
    
    public void destroy()
    {
    }
    
    // ---------
    // PROTECTED
    
    //undo helpers
    protected UndoAction _uan_;
    protected RedoAction _ran_;
    protected UndoManager _umr_ = null;
    
    //This one listens for edits that can be undone.
    protected class MyUndoableEditListener implements UndoableEditListener
    {
        public void undoableEditHappened(UndoableEditEvent e)
        {
            //Remember the edit and update the menus
            _umr_.addEdit(e.getEdit());
            _uan_.update();
            _ran_.update();
        }
    }
    
    //And this one listens for any changes to the document.
    protected class MyDocumentListener implements DocumentListener
    {
        public void insertUpdate(DocumentEvent evtDocument)
        {
            
            update(evtDocument);
        }
        
        public void removeUpdate(DocumentEvent evtDocument)
        {
            update(evtDocument);
        }
        
        public void changedUpdate(DocumentEvent evtDocument)
        {
        }
        
        private void update(DocumentEvent evtDocument)
        {
        }
    }  
    
    // -------
    // PRIVATE
    
    
    private MyDocumentListener _mdlListener = null;
    private MyUndoableEditListener _mueListener = null;
	    
    private Hashtable<Object, Action> _hstActions = null;
    
    private void _setDefaultFont()
    {
        int intFontStyle = Font.PLAIN;
        
        if (METextFontStyleEditorText.F_S_BLN_BOLD && METextFontStyleEditorText.F_S_BLN_ITALIC)
        {
            intFontStyle = Font.BOLD|Font.ITALIC;
        }
        
        else  if (METextFontStyleEditorText.F_S_BLN_BOLD)
        {
            intFontStyle = Font.BOLD;
        }
        
        else  if (METextFontStyleEditorText.F_S_BLN_ITALIC)
        {
            intFontStyle = Font.ITALIC;
        }
        
        
        Font fnt = new Font(METextFontFamilyEditorText.F_S_STR_DEFAULT, intFontStyle, METextFontSizeEditorText.F_S_INT_DEFAULT);
        setFont(fnt);
    }
    
    //The following two methods allow us to find an
    //action provided by the editor kit by its name.
    private boolean _createActionTable()
    {
        String f_strMethod = "_createActionTable()";
        
        if (this._hstActions == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._hstActions");
            return false;
        }
        
        Action[] actionsArray = getActions();
        
        for (int i = 0; i < actionsArray.length; i++)
        {
            Action a = actionsArray[i];
            this._hstActions.put(a.getValue(Action.NAME), a);
        }
        
        // ending
        return true;
    }
    
    
    private void _createChildren()
    {
        this._umr_ = new UndoManager();
        this._uan_ = new UndoAction();
        this._ran_ = new RedoAction();
	    this._mdlListener = new MyDocumentListener();
	    this._mueListener = new MyUndoableEditListener();
	    
	    DefaultStyledDocument dsd = new DefaultStyledDocument(new StyleContext());
	    
	    dsd.addDocumentListener(this._mdlListener);
	    dsd.addUndoableEditListener(this._mueListener);
	    
        setStyledDocument(dsd); 
    }
    
    
    
    // -------
    // CLASSES
    
    class UndoAction extends AbstractAction
    {
        public UndoAction()
        {
            super("Undo");
            setEnabled(false);
        }
          
        public void actionPerformed(ActionEvent evtAction)
        {
            String f_strMethod = "actionPerformed(evtAction)";
            
            try
            {
                _umr_.undo();
            }
            
            catch (CannotUndoException excCannotUndo)
            {
                excCannotUndo.printStackTrace();
                MySystem.s_printOutExit(this, f_strMethod, "excCannotUndo caught");
                
            }
            
            update();
            _ran_.update();
        }
          
        protected void update()
        {
            if (_umr_.canUndo())
            {
                setEnabled(true);
                putValue(Action.NAME, _umr_.getUndoPresentationName());
            }
            
            else
            {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        } 
        
        protected void _destroy_() {}
        
    }    

    class RedoAction extends AbstractAction
    {
        public RedoAction()
        {
            super("Redo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent evtAction)
        {
            String f_strMethod = "actionPerformed(evtAction)";
            
            try
            {
                _umr_.redo();
            }
            
            catch (CannotRedoException excCannotRedo)
            {
                excCannotRedo.printStackTrace();
                MySystem.s_printOutExit(this, f_strMethod, "excCannotRedo caught");
                
            }
            
            update();
            _uan_.update();
        }

        protected void update()
        {
            if (_umr_.canRedo())
            {
                setEnabled(true);
                putValue(Action.NAME, _umr_.getRedoPresentationName());
            }
            
            else
            {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
        
        protected void _destroy_() {}
    }
    
    // --------------
    // END-OF-CLASSES
   
}