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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    known subclasses:
    . DEditorJarProjText
    . DEditorSysText
    . DEditorNote
**/


import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;


import java.awt.*;

abstract public class DEditorAbstract extends DEscapeAbstract implements
    PEditorAbstractListener,
    DEditorAbstractListener
{
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".DEditorAbstract" // class name
        ;
    
    
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strConfirmDeleteAndCloseTitle = null;
    static private String _s_strConfirmDeleteAndCloseBody = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DEditorAbstract";
        
        String strBundleFileLong = _f_s_strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(
                _f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        _s_strConfirmDeleteAndCloseTitle = rbeResources.getString("confirmDeleteAndCloseTitle");
	        _s_strConfirmDeleteAndCloseBody = rbeResources.getString("confirmDeleteAndCloseBody");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, strBundleFileLong + " not found, excMissingResource caught");
        }
    }

    
    // ------
    // PUBLIC
    
    public synchronized void addDEditorAbstractListener(DEditorAbstractListener dapListener)
	{
	    if (this._dapListenerThis == null)
            this._dapListenerThis = DEditorAbstractEventMulticaster.add(this._dapListenerThis, dapListener);
	}
  
	public synchronized void removeDEditorAbstractListener(DEditorAbstractListener dapListener)
	{
	    if (this._dapListenerThis != null)
            this._dapListenerThis = DEditorAbstractEventMulticaster.remove(this._dapListenerThis, dapListener);
	}
	
	public void fileRecord(DEditorAbstractEvent evtDEditorAbstract)
	{	
	    super._cancel_();
	
	    if (this._dapListenerParent != null)
	        this._dapListenerParent.fileRecord(evtDEditorAbstract);
	}
	
	public void fileDelete(DEditorAbstractEvent evtDEditorAbstract)
	{
	    com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
		
		if (! OPAbstract.s_showConfirmDialog(this._cmpFrameOwner, this._strTitleApplication + " - " + _s_strConfirmDeleteAndCloseTitle, _s_strConfirmDeleteAndCloseBody))
		{
		    super._cancel_();
		    return;
		}
	    
	    // ----
	    
	    super._cancel_();
	    
	    if (this._dapListenerParent != null)
	        this._dapListenerParent.fileDelete(evtDEditorAbstract);
	}
	
	// ----
    
    public void fileRecord(PEditorAbstractEvent evtPEditorAbstract)
    {
        DEditorAbstractEvent evtThis = new DEditorAbstractEvent(this, DEditorAbstractEvent.DEDITORABSTRACTEVENT_FILERECORD);
        
        if (this._dapListenerThis != null)
            this._dapListenerThis.fileRecord(evtThis);
    }
    
    public void fileDelete(PEditorAbstractEvent evtPEditorAbstract)
    {  
        DEditorAbstractEvent evtThis = new DEditorAbstractEvent(this, DEditorAbstractEvent.DEDITORABSTRACTEVENT_FILEDELETE);
        
        if (this._dapListenerThis != null)
            this._dapListenerThis.fileDelete(evtThis); 
    }

    
    public synchronized void addPEditorAbstractListener(PEditorAbstractListener pnd)
	{
	    if (this._pndListenerThis_ == null)
            this._pndListenerThis_ = PEditorAbstractEventMulticaster.add(this._pndListenerThis_, pnd);
	}
  
	public synchronized void removePEditorAbstractListener(PEditorAbstractListener pnd)
	{
	    if (this._pndListenerThis_ != null)
            this._pndListenerThis_ = PEditorAbstractEventMulticaster.remove(this._pndListenerThis_, pnd);
	}
    
    public void exit(PEditorAbstractEvent evtPEditorAbstract)
    {
        super._cancel_();
    }
    
    // ----
    
    public boolean init()
    {
        String strMethod = "init()";

        if (this._pnd_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnd_");
            return false;
        }
        
        if (! this._pnd_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        this._pnd_.setPreferredSize(new Dimension(500, 400));
        
        _addChildren();
    
        // ending
        return true;
    }
    
    public void destroy()
    {        
        _destroyListeners();
    
        if (this._pnd_ != null)
        {
            this._pnd_.destroy();
            this._pnd_ = null;
        }
        
        super.destroy();
    }
    
    
    // ---------
    // PROTECTED
    
    // listeners
    protected PEditorAbstractListener _pndListenerThis_ = null;
    
    // --------
    // children
    protected PEditorAbstract _pnd_ = null;
    
    

    
    protected DEditorAbstract(
        DEditorAbstractListener dapListenerParent,
        Component cmpFrameOwner,
        String strTitleApplication,
        String strTitleSuffix)
    {
        super((Frame) cmpFrameOwner, true);
        
        this._dapListenerParent = dapListenerParent;
        this._cmpFrameOwner = cmpFrameOwner;
        this._strTitleApplication = strTitleApplication;

        setTitle(this._strTitleApplication + " - " + strTitleSuffix);
        
        _createListeners();
    }

    // -------
    // PRIVATE
    
    
    // listeners
    private DEditorAbstractListener _dapListenerThis = null;
    private DEditorAbstractListener _dapListenerParent = null;
    
    private String _strTitleApplication = null;
    private Component _cmpFrameOwner = null;
    
    private boolean _addChildren()
    {
        String strMethod = "_addChildren()";
          // ----
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        
        if (this._pnd_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnd_");
            return false;
        }
        
        cntContentPane.add(this._pnd_, BorderLayout.CENTER);
    
        // ending
        return true;
    }
    
    private void _createListeners()
    {
        addPEditorAbstractListener(this);
        addDEditorAbstractListener(this);
    }
    
    private void _destroyListeners()
    {
        removePEditorAbstractListener(this);
        removeDEditorAbstractListener(this);
    }
}