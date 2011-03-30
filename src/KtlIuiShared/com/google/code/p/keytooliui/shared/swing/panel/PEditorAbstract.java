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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PEditorNote
    . PEditorDefaultSys
    . PEditorDefaultJar
**/

import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

abstract public class PEditorAbstract extends JPanel implements
    MFileAllEditorListener,
    TBEditorAbstractListener,
    PEditorAbstractListener,
    ActionListener
{    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void destroy();
    
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESExit24)
        {
            PEditorAbstractEvent evtThis = new PEditorAbstractEvent(this, PEditorAbstractEvent.PEDITORABSTRACTEVENT_EXIT);
	    
	        if (this._pndListenerThis == null)
	            MySystem.s_printOutExit(this, strMethod, "nil this._pndListenerThis");
	    
	        this._pndListenerThis.exit(evtThis);
            return;
        }
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.menuitem.MIFileExit)
        {
            PEditorAbstractEvent evtThis = new PEditorAbstractEvent(this, PEditorAbstractEvent.PEDITORABSTRACTEVENT_EXIT);
	    
	        if (this._pndListenerThis == null)
	            MySystem.s_printOutExit(this, strMethod, "nil this._pndListenerThis");
	    
	        this._pndListenerThis.exit(evtThis);
            return;
        }
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESPrint24)
        {
            if (! _print())
	            MySystem.s_printOutExit(this, strMethod, "failed");  
            
            return;
        }
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.menuitem.MIPrint)
        {
            if (! _print())
	            MySystem.s_printOutExit(this, strMethod, "failed");  
            
            return;
        }
           
        MySystem.s_printOutExit(this, strMethod, "uncaught source");
    }
    
    public synchronized void addPEditorAbstractListener(PEditorAbstractListener pnd)
	{
	    if (this._pndListenerThis == null)
            this._pndListenerThis = PEditorAbstractEventMulticaster.add(this._pndListenerThis, pnd);
	}
  
	public synchronized void removePEditorAbstractListener(PEditorAbstractListener pnd)
	{
	    if (this._pndListenerThis != null)
            this._pndListenerThis = PEditorAbstractEventMulticaster.remove(this._pndListenerThis, pnd);
	}
	
	public void fileRecord(PEditorAbstractEvent evtPEditorAbstract)
    {
        if (this._pndListenerParent != null)
             this._pndListenerParent.fileRecord(evtPEditorAbstract);
    }
    
    public void fileDelete(PEditorAbstractEvent evtPEditorAbstract)
    {
        if (this._pndListenerParent != null)
             this._pndListenerParent.fileDelete(evtPEditorAbstract);
    }
    
    public void exit(PEditorAbstractEvent evtPEditorAbstract)
    {
        String strMethod = "exit(evtPEditorAbstract)";
        
        if (this._pndListenerParent == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil this._pndListenerParent");
            
        }
        
        this._pndListenerParent.exit(evtPEditorAbstract);
    }
    
    // ----

    public synchronized void addTBEditorAbstractListener(TBEditorAbstractListener ntlListener)
	{
	    if (this._ntlListenerThis_ == null)
            this._ntlListenerThis_ = TBEditorAbstractEventMulticaster.add(this._ntlListenerThis_, ntlListener);
	}
  
	public synchronized void removeTBEditorAbstractListener(TBEditorAbstractListener ntlListener)
	{
	    if (this._ntlListenerThis_ != null)
            this._ntlListenerThis_ = TBEditorAbstractEventMulticaster.remove(this._ntlListenerThis_, ntlListener);
	}

	public void fileRecord(TBEditorAbstractEvent evtTBEditorAbstract)
	{	
	    String strMethod = "fileRecord(evtTBEditorAbstract)";
	    
	    if (! _fileRecord())
	    {
	        MySystem.s_printOutExit(this, strMethod, "failed");  
	    }
	}
	
	public void fileDelete(TBEditorAbstractEvent evtTBEditorAbstract)
	{	
	    String strMethod = "fileDelete(evtTBEditorAbstract)";
	    
	    if (! _fileDelete())
	    {
	        MySystem.s_printOutExit(this, strMethod, "failed");
	        
	    }
	}
	
	

    // ----
    
    public synchronized void addMFileAllEditorListener(MFileAllEditorListener nfeListener)
	{
	    if (this._nfeListenerThis_ == null)
            this._nfeListenerThis_ = MFileAllEditorEventMulticaster.add(this._nfeListenerThis_, nfeListener);
	}
  
	public synchronized void removeMFileAllEditorListener(MFileAllEditorListener nfeListener)
	{
	    if (this._nfeListenerThis_ != null)
            this._nfeListenerThis_ = MFileAllEditorEventMulticaster.remove(this._nfeListenerThis_, nfeListener);
	}
	
	public void fileRecord(MFileAllEditorEvent evtMFileAllEditor)
	{	
	    String strMethod = "fileRecord(evtMFileAllEditor)";
	    
	    if (! _fileRecord())
	    {
	        MySystem.s_printOutExit(this, strMethod, "failed");    
	    }
	}
	
	public void fileDelete(MFileAllEditorEvent evtMFileAllEditor)
	{	
	    String strMethod = "fileDelete(evtMFileAllEditor)";
	    
	    if (! _fileDelete())
	    {
	        MySystem.s_printOutExit(this, strMethod, "failed");
	    }
	}
    
    // ----
 
    public boolean init()
    {
        String strMethod = "init()";
        
        // --
        
        if (this._nsl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._nsl_");
            return false;
        }
        
        // memo: _nsl_.init() already done in subclasses, creators
        
        // --
        
        if (this._nbr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._nbr_");
            return false;
        }
        
        if (! this._nbr_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._ntl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._ntl_");
            return false;
        }
        
        if (! this._ntl_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        if (this._bar_  == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._bar_");
            return false;
        }
        
        if (! this._bar_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        
        setBorder(BorderFactory.createEtchedBorder());
	    setLayout(new BorderLayout());
        
        JPanel pnlContents = new JPanel();
	    pnlContents.setLayout(new BorderLayout());
	    
	    pnlContents.add("North", this._ntl_);
	    pnlContents.add("Center", this._nsl_);
	    pnlContents.add("South", this._bar_);
	    
	    
	    
	    add("North", this._nbr_);
	    add("Center", pnlContents);
        
        // --
        setPreferredSize(new Dimension(500, 400));
        
        // --
        
        setEnabled(false);
        
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected MFileAllEditorListener _nfeListenerThis_ = null;
    protected TBEditorAbstractListener _ntlListenerThis_ = null;
    
    protected PEditorAbstract(PEditorAbstractListener pndListenerParent, Component cmpFrameOwner, String strApplicationTitle)
    {
        super(true); // double buffering
        this._pndListenerParent = pndListenerParent;

	    _createListeners();
    }
    
    
    
    protected void _destroy_()
    {
        _destroyListeners();
        
        if (this._nbr_ != null)
        {
            this._nbr_.destroy();
            this._nbr_ = null;
        }
        
        if (this._ntl_ != null)
        {
            this._ntl_.destroy();
            this._ntl_ = null;
        }
        
        
        if (this._nsl_ != null)
        {
            this._nsl_.destroy();
            this._nsl_ = null;
        }
        
        if (this._bar_ != null)
        {
            this._bar_.destroy();
            this._bar_ = null;
        }
    }
    
    
     // --------
    // children
    protected MBEditorAbstract _nbr_ = null;
    protected TBEditorAbstract _ntl_ = null;
    protected SPEditorAbstract _nsl_ = null;
    protected PBarContainerAbstract _bar_ = null;
    
    // -------
    // PRIVATE
    
   
    
    // ---------
    // listeners
    private PEditorAbstractListener _pndListenerParent = null;
    private PEditorAbstractListener _pndListenerThis = null;
    
   
    private void _createListeners()
    {
        addPEditorAbstractListener(this);
        addMFileAllEditorListener(this);
        addTBEditorAbstractListener(this);
    }
    
    private void _destroyListeners()
    {
        removePEditorAbstractListener(this);
        removeMFileAllEditorListener(this);
        removeTBEditorAbstractListener(this);
    }
    
    private boolean _fileDelete()
    {   
        String strMethod = "_fileDelete()";
        PEditorAbstractEvent evtThis = new PEditorAbstractEvent(this, PEditorAbstractEvent.PEDITORABSTRACTEVENT_FILEDELETE);
	    
	    if (this._pndListenerThis == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil this._pndListenerThis");
	        return false;
	    }
	    
	    this._pndListenerThis.fileDelete(evtThis);
	    return true;
    }
    
    private boolean _fileRecord()
    {
        String strMethod = "_fileRecord()";
        PEditorAbstractEvent evtThis = new PEditorAbstractEvent(this, PEditorAbstractEvent.PEDITORABSTRACTEVENT_FILERECORD);
	    
	    if (this._pndListenerThis == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil this._pndListenerThis");
	        return false;
	    }
	    
	    this._pndListenerThis.fileRecord(evtThis);
	    return true;
    }
    
    private boolean _print()
    {
        String strMethod = "_print()";
        
        if (this._nsl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._nsl_");
            return false;
        }
        
        return this._nsl_.printContent();
    }
}