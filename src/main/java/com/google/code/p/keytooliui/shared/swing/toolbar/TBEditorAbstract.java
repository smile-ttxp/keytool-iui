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
 
 
 package com.google.code.p.keytooliui.shared.swing.toolbar;

/**
    known subclasses:
    . TBEditorNote
    . TBEditorDefault
**/


import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;


abstract public class TBEditorAbstract extends TBAbs implements
    ActionListener,
    TBEditorAbstractListener
{        
    // --------------------
    // PRIVATE STATIC FINAL
        
    private static final String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".TBEditorAbstract" // class name
        ;
    
    private static final String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";
    
    // --------------
    // PRIVATE STATIC
        
    private static java.util.ResourceBundle _s_rbeResources;
    
    // ------------------
    // STATIC INITIALIZER
    

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.toolbar.TBEditorAbstract";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + ", excMissingResource caught");
        }
    }
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    abstract public void destroy();
    abstract public boolean doFileNew() throws Exception;
    abstract public boolean doFileOpen() throws Exception;
    
    // ------
    // PUBLIC
    
    
    
    public synchronized void addTBEditorAbstractListener(TBEditorAbstractListener ntl)
	{
	    if (this._ntlListenerThis == null)
            this._ntlListenerThis = TBEditorAbstractEventMulticaster.add(this._ntlListenerThis, ntl);
	}
  
	public synchronized void removeTBEditorAbstractListener(TBEditorAbstractListener ntl)
	{
	    if (this._ntlListenerThis != null)
            this._ntlListenerThis = TBEditorAbstractEventMulticaster.remove(this._ntlListenerThis, ntl);
	}
    
    public void fileRecord(TBEditorAbstractEvent ntlEvent)
    {
        if (this._ntlListenerParent != null)
            this._ntlListenerParent.fileRecord(ntlEvent);
    }
    
    public void fileDelete(TBEditorAbstractEvent ntlEvent)
    {
        if (this._ntlListenerParent != null)
            this._ntlListenerParent.fileDelete(ntlEvent);
    }
    
    
    
    // ----
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String f_strMethod = "actionPerformed(evtAction)";
        
        if (! (evtAction.getSource() instanceof BEnabledState))
        {
            MySystem.s_printOutExit(this, f_strMethod, "wrong source");
            
        }
        
        BEnabledState mbn = (BEnabledState) evtAction.getSource();
        
        if (mbn == this._btnRecord)
        {
            TBEditorAbstractEvent evtThis = new TBEditorAbstractEvent(this, TBEditorAbstractEvent.TBEDITORABSTRACTEVENT_FILERECORD);
            
            if (this._ntlListenerThis != null)
                this._ntlListenerThis.fileRecord(evtThis);
            
            return;
        }
        
        if (mbn == this._btnDelete)
        {
            TBEditorAbstractEvent evtThis = new TBEditorAbstractEvent(this, TBEditorAbstractEvent.TBEDITORABSTRACTEVENT_FILEDELETE);
            
            if (this._ntlListenerThis != null)
                this._ntlListenerThis.fileDelete(evtThis);
            
            return;
        }
        
       
        
        MySystem.s_printOutExit(this, f_strMethod, "unexpected button source");
        
    }
    
    
    // ---------
    // PROTECTED
    
    protected BESExit24 _besExit_ = null;
    
    protected boolean _insert_(Component com)
    {
        String f_strMethod = "_insert_(com)";
        
        if (com == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil com");
            return false;
        }
        
        if (add(com, this._intInsertPos) == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        this._intInsertPos ++;
        
        // ending
        return true;
    }
    
    protected boolean _doFileOpen_()
        throws Exception
    {
    
        this._btnRecord.setEnabled(true);
        this._btnDelete.setEnabled(true);      
        
        this._btnPrint.setEnabled(true);
        this._besExit_.setEnabled(true);
 
        setEnabled(true);
        return true;
    }
    
    protected boolean _doFileNew_()
        throws Exception
    {
        this._btnRecord.setEnabled(true);
        this._btnDelete.setEnabled(false);
        
        this._btnPrint.setEnabled(true);
        this._besExit_.setEnabled(true);
        
        setEnabled(true);
        return true;
    }
    
    protected TBEditorAbstract(ActionListener alrParent, TBEditorAbstractListener ntlListenerParent)
    {
        super(SwingConstants.HORIZONTAL, false);
        
        this._ntlListenerParent = ntlListenerParent;
        
        _createChildren(alrParent);
        _createListeners();
    }
 
    protected boolean _init_()
    {   
        String f_strMethod = "_init_()";
 
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
        
        setEnabled(false);
        
        // ending
        return true;
    }
    
    protected void _destroy_()
    {
        _destroyListeners();
        
        if (this._btnRecord != null)
        {
            this._btnRecord.destroy();
            this._btnRecord = null;
        }
        
        if (this._btnDelete != null)
        {
            this._btnDelete.destroy();
            this._btnDelete = null;
        }
        
        if (this._btnPrint != null)
        {
            this._btnPrint.destroy();
            this._btnPrint = null;
        }
        
        if (this._besExit_ != null)
        {
            this._besExit_.destroy();
            this._besExit_ = null;
        }
        
    }
    
    // -------
    // PRIVATE
	
	// ---------
    // listeners
    private TBEditorAbstractListener _ntlListenerThis = null;
    private TBEditorAbstractListener _ntlListenerParent = null;
	        
	// ---------
    
    private int _intInsertPos = 0;
    
    // children
    
    
    private BEnabledState _btnRecord = null;
    private BEnabledState _btnDelete = null;
    private BEnabledState _btnPrint = null;
    
 
    private void _createChildren(ActionListener alrParent)
    {
        this._btnRecord = new BEnabledState(this);
        this._btnDelete = new BEnabledState(this);
                
        this._btnPrint = new BESPrint24(alrParent); 
        this._besExit_ = new BESExit24(alrParent); 
    }
    
    private void _createListeners()
    {
        addTBEditorAbstractListener(this);
    }
    
    private void _destroyListeners()
    {
        removeTBEditorAbstractListener(this);
    }
    
    private boolean _loadResourceImage()
    {
        String f_strMethod = "_loadResourceImage()";
        
        ImageIcon iin = null;
        String strName = null;
        
        // ----------
        // record
        
        strName = "save24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnRecord.setIcon(iin);
	    
	    // ----------
        // delete
        
        strName = "delete24.gif";
        iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
	    
	    if (iin == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil iin");
	        return false;
	    }
	    
	    this._btnDelete.setIcon(iin);
	    
	    
	    // ending
	    return true;
    }
    
    
    
    
    private void _addChildren()
    {   
        addSeparator(TBAbs._f_s_dimSeparator10_); this._intInsertPos ++;
        
        add(this._btnRecord); this._intInsertPos ++;
        add(this._btnDelete); this._intInsertPos ++;
        addSeparator(TBAbs._f_s_dimSeparator10_); this._intInsertPos ++;
        
        
        add(this._btnPrint);
        addSeparator(TBAbs._f_s_dimSeparator10_);
        add(Box.createHorizontalGlue());
        add(this._besExit_);
        addSeparator(TBAbs._f_s_dimSeparator10_);
        
    }
    
    private boolean _loadResourceBundle()
    {
        String f_strMethod = "_loadResourceBundle()";
        
         /* MEMO: trim() not needed
        */
        
        try
        {   
            String strValue = null;
            
            // TEXTS
            strValue = _s_rbeResources.getString("tip_record");
	        this._btnRecord.setToolTipText(strValue);
	        strValue = _s_rbeResources.getString("tip_delete");
	        this._btnDelete.setToolTipText(strValue);
	       
	        strValue = _s_rbeResources.getString("tip_print");
	        this._btnPrint.setToolTipText(strValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excMissingResource");
	        return false;
	    }
	    
	    // ending
	    return true;
    }
    
    private void _disableChildrenAtInit()
    {
        this._btnRecord.setEnabled(false);
        this._btnDelete.setEnabled(false); 
        this._btnPrint.setEnabled(false);
        // in comments: oct 19, 2001
        // this._besExit_.setEnabled(false);
        
    }
}