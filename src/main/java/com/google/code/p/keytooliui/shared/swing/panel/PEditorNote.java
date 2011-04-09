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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;


import com.google.code.p.keytooliui.shared.lang.*;

import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;

import javax.swing.*;

import java.awt.*;

public final class PEditorNote extends PEditorAbstract implements
    MEditEditorTextListener
{    
    // ------
    // PUBLIC
    
    public boolean doFileOpen(java.io.ObjectInputStream ois, int intPageId)
        throws Exception
    {
        String f_strMethod = "doFileOpen(ois, intPageId)";
        
        if (! ((SPEditorStyled)super._nsl_).doFileOpen(ois))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._ntl_.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._nbr_.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (super._bar_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil super._bar_");
            return false;
        }
        
        if (! ((PBarContainerPagerAbstract)super._bar_).setPageId(intPageId))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! ((PBarContainerPagerAbstract)super._bar_).setVisiblePager(true))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
    
        setEnabled(true);
        return true;
    }
    
    public boolean doFileNew(int intPageId)
        throws Exception
    {
        String f_strMethod = "doFileNew(intPageId)";
        
        if (! super._nsl_.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._ntl_.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._nbr_.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (super._bar_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil super._bar_");
            return false;
        }
        
        if (! ((PBarContainerPagerAbstract)super._bar_).setPageId(intPageId))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! ((PBarContainerPagerAbstract)super._bar_).setVisiblePager(true))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        setEnabled(true);
        return true;
    }

    public boolean writeTo(java.io.ObjectOutputStream oos)
        throws Exception
    {
        return ((SPEditorStyled)super._nsl_).writeTo(oos);
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
	    String f_strMethod = "insertTime(evtMEditEditorText)";
	    
	    String strTime = MySystem.s_getDateCurrent();
	    boolean blnOk = false;
	    
	    try
	    {
	        blnOk = ((SPEditorStyled)super._nsl_).insertContent(strTime);
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutExit(this, f_strMethod, "exception caught"); 
	    }
	    
	    if (! blnOk)
	    {
	        MySystem.s_printOutExit(this, f_strMethod, "failed");
	    }
	}
    
    
    public PEditorNote(
        PEditorAbstractListener pndListenerParent,
        Component cmpFrameOwner)
    {
        super(pndListenerParent, cmpFrameOwner);
    
        String f_strMethod = "PEditorNote(pndListenerParent, cmpFrameOwner)";
         
	    _createListeners();
	    
	    // ----
	    super._nsl_ = new SPEditorStyled();
	    
	    if (! super._nsl_.init())
        {
            MySystem.s_printOutExit(this, f_strMethod, "failed");
        }
	    
	    // --
	    
	    java.util.Hashtable<Object, Action> htbActions = ((SPEditorStyled)super._nsl_).getAllActions();
	    
	    if (htbActions == null)
	    {
	        MySystem.s_printOutExit(this, f_strMethod, "nil htbActions"); 
	    }
	    
	    
	    
	    AbstractAction aanUndo = ((SPEditorStyled)super._nsl_).getAbstractActionUndo();
	    
	    if (aanUndo == null)
	    {
	        MySystem.s_printOutExit(this, f_strMethod, "nil aanUndo");
	    }
	    
	    
	    
	    AbstractAction aanRedo = ((SPEditorStyled)super._nsl_).getAbstractActionRedo();
	    
	    if (aanRedo == null)
	    {
	        MySystem.s_printOutExit(this, f_strMethod, "nil aanRedo");
	    }
	    
        // ----
        
        super._nbr_ = new MBEditorNote(
            cmpFrameOwner, 
            (java.awt.event.ActionListener) this,
            super._nfeListenerThis_, this._netListenerThis, htbActions, aanUndo, aanRedo);
        
        
        super._ntl_ = new TBEditorNote(
            (java.awt.event.ActionListener) this, 
            super._ntlListenerThis_, 
            htbActions, 
            aanUndo, 
            aanRedo);
        
        super._bar_ = new PBarContainerPagerNote();
    }
    
    
    
    public void destroy()
    {
        super._destroy_();
        _destroyListeners();
    }
    
    public static void main(String[] args)
    {
        final String f_strWhere = "main(strsArg)";
        
        try
        {
            JFrame frame = new JFrame();
	        frame.getContentPane().setLayout(new BorderLayout());
	
	        
	        PEditorAbstract ntp = new PEditorNote(null, frame);
	        
	        if (! ntp.init())
	        {
	            MySystem.s_printOutExit(f_strWhere, "failed"); 
	        }
	        
	        frame.getContentPane().add("Center", ntp);
	        
	        com.google.code.p.keytooliui.shared.awt.event.WindowCloser acr = new com.google.code.p.keytooliui.shared.awt.event.WindowCloser();
	        
	        frame.addWindowListener(acr);
	        Toolkit.getDefaultToolkit().beep();
	        frame.pack();
	        
	        int intPreferredW = frame.getPreferredSize().width;
	        int intW = 400;
	        
	        if (intPreferredW < intW)
	            intW = intPreferredW;
	        
	        frame.setSize(intW, 400);
            frame.setVisible(true);
        }
        
        catch (Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "exception caught");
        }
    }
    
    // -------
    // PRIVATE

    // ---------
    // listeners

    private MEditEditorTextListener _netListenerThis = null;
   
    private void _createListeners()
    {
        addMEditEditorTextListener(this);
    }
    
    private void _destroyListeners()
    {
        removeMEditEditorTextListener(this);
    }
}