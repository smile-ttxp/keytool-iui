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
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.*;

import java.awt.event.*;

final public class MFileAllEditor extends MFileAllAbstract implements
    ActionListener,
    MFileAllEditorListener
{   
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strTextFileRecord = null;
    static private String _s_strTextFileDelete = null;
    static private String _s_strTextExitSuffix = null;
    
    // ------------------
    // STATIC INITIALIZER
   
    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MFileAllEditor";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MFileAllEditor" // class name
            ;
            
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle _s_rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTextFileRecord = _s_rbeResources.getString("textFileRecord");
            _s_strTextFileDelete = _s_rbeResources.getString("textFileDelete");
            _s_strTextExitSuffix = _s_rbeResources.getString("textExitSuffix");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, strBundleFileLong + " not found, excMissingResource caught");
        }
        
        strWhere = null;
        strBundleFileShort = null;
        strBundleFileLong = null;
    }
    
    // ------
    // PUBLIC
    
    // empty
    public void updateTreeUI() {}
    
    public boolean doFileNew()
        throws Exception
    {        
        this._mimFileRecord.setEnabled(true);
        this._mimFileDelete.setEnabled(false);
        this._mimPrint.setEnabled(true);
        super._fet_.setEnabled(true); // ??
        
        setEnabled(true);
        
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        this._mimFileRecord.setEnabled(true);
        this._mimFileDelete.setEnabled(true);
        this._mimPrint.setEnabled(true);
        super._fet_.setEnabled(true); // ??
        
        setEnabled(true);
        
        return true;
    }
    
   
    public synchronized void addMFileAllEditorListener(MFileAllEditorListener nfe)
	{
	    if (this._nfeListenerThis == null)
            this._nfeListenerThis = MFileAllEditorEventMulticaster.add(this._nfeListenerThis, nfe);
	}
  
	public synchronized void removeMFileAllEditorListener(MFileAllEditorListener nfe)
	{
	    if (this._nfeListenerThis != null)
            this._nfeListenerThis = MFileAllEditorEventMulticaster.remove(this._nfeListenerThis, nfe);
	}
	
    public void fileRecord(MFileAllEditorEvent nfeEvent)
    {
        if (this._nfeListenerParent != null)
            this._nfeListenerParent.fileRecord(nfeEvent);
    }
    
    public void fileDelete(MFileAllEditorEvent nfeEvent)
    {
        if (this._nfeListenerParent != null)
            this._nfeListenerParent.fileDelete(nfeEvent);
    }
    
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String f_strMethod = "actionPerformed(evtAction)";
	      
	    if (! (evtAction.getSource() instanceof JMenuItem)) // bug !!!!!!!
	    {
	        MySystem.s_printOutExit(this, f_strMethod, "wrong instance");
	        
	    }
			            
        JMenuItem mimSource = (JMenuItem)(evtAction.getSource());
        
        if (mimSource == this._mimFileRecord)
		{			            
	        MFileAllEditorEvent nfeEvent = new MFileAllEditorEvent(
			    this, MFileAllEditorEvent.MFILEALLEDITOREVENT_FILERECORD);
		    
		    if (this._nfeListenerThis != null)
		        this._nfeListenerThis.fileRecord(nfeEvent);
			
			return;
        }
        
        if (mimSource == this._mimFileDelete)
		{			            
	        MFileAllEditorEvent nfeEvent = new MFileAllEditorEvent(
			    this, MFileAllEditorEvent.MFILEALLEDITOREVENT_FILEDELETE);
		    
		    if (this._nfeListenerThis != null)
		        this._nfeListenerThis.fileDelete(nfeEvent);
			
			return;
        }
        
        
        MySystem.s_printOutExit(this, f_strMethod, "unknown source");
        
    }
    
    public MFileAllEditor(
        ActionListener actListenerParent,
        MFileAllEditorListener nfeListenerParent)
    {
        super(actListenerParent);
        this._nfeListenerParent = nfeListenerParent;
        
        _createChildren(actListenerParent);
        _createListeners();
    }
 
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (this._mimPrint == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._mimPrint");
            return false;
        }
        
        if (! this._mimPrint.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        

	super._fet_.setText(super._fet_.getText() + " " + _s_strTextExitSuffix);
        
        
        _addChildren();
        _disableChildrenAtInit();
    
        // ending
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        _destroyListeners();
        
         if (this._mimFileRecord != null)
        {
            this._mimFileRecord.destroy();
            this._mimFileRecord = null;
        }
        
        if (this._mimFileDelete != null)
        {
            this._mimFileDelete.destroy();
            this._mimFileDelete = null;
        }
        
        if (this._mimPrint != null)
        {
            this._mimPrint.destroy();
            this._mimPrint = null;
        }
    }
    
    // -------
    // PRIVATE
    
    
    // --------
    // children
    private MIAbstract _mimFileRecord = null;
    private MIAbstract _mimFileDelete = null;
    private MIAbstract _mimPrint = null;
    
    // ---------
    // listeners
    private MFileAllEditorListener _nfeListenerThis = null;
    private MFileAllEditorListener _nfeListenerParent = null;
    
    private void _addChildren()
    {
        add(this._mimFileRecord);
        add(this._mimFileDelete);
        addSeparator();
        add(this._mimPrint);
        addSeparator();
        add(super._fet_);
    }

    private void _createChildren(ActionListener alr)
    {
        this._mimFileRecord = new MIDefault(_s_strTextFileRecord, com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("save16.gif"), alr);
        this._mimFileDelete = new MIDefault(_s_strTextFileDelete, com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("delete16.gif"), alr);
        this._mimPrint = new MIPrint(alr);
    }
    
    private void _createListeners()
    {
        addMFileAllEditorListener(this);
   
    }
    
   
    
    private void _destroyListeners()
    {
        removeMFileAllEditorListener(this); 
    }   
    
    private void _disableChildrenAtInit() 
    {
        this._mimFileRecord.setEnabled(false);
        this._mimFileDelete.setEnabled(false);
        this._mimPrint.setEnabled(false);
        // modif: in comments, oct 20, 2001
        //super._fet_.setEnabled(false);
    }
}