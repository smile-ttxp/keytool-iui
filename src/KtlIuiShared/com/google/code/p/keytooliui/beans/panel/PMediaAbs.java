/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

package com.google.code.p.keytooliui.beans.panel;

/**
    a panel that displays JMF-based medias
    
    "P" for "Panel"
    "Media" for JMF-based audios & videos
    "Abs" for "Abstract class"


    Known subclasses:
    . PMediaUrlAbs
    . PMediaRtpAbs
**/




import com.google.code.p.keytooliui.beans.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;

import javax.swing.*;

import java.io.*; // bean
import java.awt.*;
import java.awt.event.*;

abstract public class PMediaAbs extends JPanel implements 
    WindowListener,
    Serializable,
    ActionListener,
    java.beans.PropertyChangeListener
{
    // ----------------------
    // FINAL STATIC PROTECTED
    
    final static protected int _f_s_intHeightMinToolbar_ = 32;
    
    // --------------------
    // FINAL STATIC PRIVATE
	    
	final static private String _f_s_strClass = "com.google.code.p.keytooliui.beans.panel.PMediaAbs.";
	    
	// --------------
    // STATIC PRIVATE
    
    // should be called once got valid ancestor from property listener
    static private JFrame _s_getFrameOwner(Component cmp)
    { 
        String strMethod = _f_s_strClass + "_s_getFrameOwner(cmp)";
        
        while (cmp != null)
	    {
	        if (cmp instanceof JFrame) 
	            return (JFrame) cmp;
        	
	        cmp = cmp.getParent();
	    }
	            
	    MySystem.s_printOutError(strMethod, "failed to get JFrame");
        return null;
    }
    
    
    // ---------------
    // ABSTRACT PUBLIC
    
    
    // ------------------
    // ABSTRACT PROTECTED
    
    abstract protected boolean _createChildren_();  
    abstract protected void _showDialogWarningFailed_();
    
    // ------
    // PUBLIC
    
    
    //should be called by applet ancestor, if any
    public boolean createChildren()
    {
        String strMethod = "createChildren()";
        
        if (this._cmpFrameOwner_ == null)
            this._cmpFrameOwner_ = _s_getFrameOwner(this);
        
        if (this._cmpFrameOwner_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpFrameOwner_");
            return false;
        }
        
        ((JFrame) this._cmpFrameOwner_).addWindowListener(this);
        
        if (! _createChildren_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    // ---
    
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    
    public void windowClosing(WindowEvent e)
    {
        _destroyChildren();
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESPageAbout16)
        {            
            if (this._pnlPage_ != null)
            {
                this._pnlPage_.showAbout();
            }   
            
            // ending
            return;
        }
        
        // MEMO: do not allow exit in bean ==> should display an error dialog instead!
        MySystem.s_printOutError(this, strMethod, 
            "instance of evtAction.getSource() not caught, evtAction.getSource().getClass().toString()=" + 
            evtAction.getSource().getClass().toString());
    }
    
    public void propertyChange(java.beans.PropertyChangeEvent evtPropertyChange)
    {
        final String strMethod = "propertyChange(evtPropertyChange)";
        
        MySystem.s_printOutTrace(this, strMethod, 
            "\n . " + "evtPropertyChange.getPropertyName()=" + evtPropertyChange.getPropertyName() +
            "\n . " + "evtPropertyChange.toString()=" + evtPropertyChange.toString()
            );
        
        if (evtPropertyChange.getPropertyName().toLowerCase().compareTo("ancestor") == 0)
        {
            
            Object objAncestorOld = evtPropertyChange.getOldValue(); 
            Object objAncestorNew = evtPropertyChange.getNewValue(); 
            
            if (objAncestorOld==null && objAncestorNew!=null)
            {
                // should be: if inside JApplet (JApplet as any ancestor)
                if (objAncestorNew instanceof JPanel)
                {
                    MySystem.s_printOutTrace(this, strMethod, "objAncestorNew instanceof JPanel, returning");
                    return;
                }
                
                
                MySystem.s_printOutTrace(this, strMethod, "objAncestorNew.getClass().toString()=" + objAncestorNew.getClass().toString());
                
                MySystem.s_printOutFlagDev(this, strMethod, "changes, sept 24, 03");
                
                // -- BEG NEW
                
                /**javax.swing.SwingUtilities.invokeLater(new Runnable()
	            {
	                public void run()
	                {
	                    if (! _createChildren())
                        {
                            MySystem.s_printOutError(this, strMethod, "failed, TODO: show error dialog");
                            return;
                        }
	                }
	            });**/ 
                
                // -- END NEW
                
                // -- BEG OLD
                
                if (! _createChildren())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    _showDialogWarningFailed_();
                    return;
                }
                
                // -- END OLD
                
                   

                return;
            }
            
            if (objAncestorNew==null && objAncestorOld!=null)
            {
                _destroyChildren();
                return;
            }
        }
    }
    
    
    
    // ---------
    // PROTECTED
    
    protected boolean _blnSetSelectedReadyStart_ = true;
    
    protected PPageMediaAbs _pnlPage_ = null;
    protected TBPageSecMediaAbs _tbrToolbar_ = null;
    
    protected Component _cmpFrameOwner_ = null;
    protected String _strTitleAppli_ = null;
    
    protected boolean _initChildren_()
    {
        String strMethod = "_initChildren_()";
        
        
        boolean blnGotChild = false;
        
        if (this._tbrToolbar_ != null)
        {
            blnGotChild = true;
            
            if (! this._tbrToolbar_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            add(this._tbrToolbar_, java.awt.BorderLayout.NORTH);
            this._tbrToolbar_.addNotify();
        }
        
        if (this._pnlPage_ != null)
        {
            blnGotChild = true;
        
            if (! this._pnlPage_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            add(this._pnlPage_, java.awt.BorderLayout.CENTER);
            this._pnlPage_.addNotify();
        }
        
        
        
        if (blnGotChild)
        {
            validate();
        }
        
        return true;
    }
    
    protected PMediaAbs()
    {
        super();
        
        com.google.code.p.keytooliui.javax.media.MyManager.s_loadLibrary();
        
        // should be used if and only if bean not inide an applet
        addPropertyChangeListener(this);
        
        this._strTitleAppli_ = null;
        setDoubleBuffered(true);
        setLayout(new java.awt.BorderLayout());
    }
    
    // -------
    // PRIVATE
    
    /**
        get  params
        create children
        init children
        add children
    **/
    private boolean _createChildren()
    {
        String strMethod = "_createChildren()";
        
        if (this._cmpFrameOwner_ == null)
            this._cmpFrameOwner_ = _s_getFrameOwner(this);
        
        if (this._cmpFrameOwner_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpFrameOwner_");
            return false;
        }
        
        ((JFrame) this._cmpFrameOwner_).addWindowListener(this);
        
        
        if (! _createChildren_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    /**
        remove children
        destroy children
    **/
    private void _destroyChildren()
    {
        String strMethod = "_destroyChildren()";
        
        MySystem.s_printOutTrace(this, strMethod, "1");

        if (this._cmpFrameOwner_ != null)
        {
            ((JFrame)this._cmpFrameOwner_).removeWindowListener(this);   
            this._cmpFrameOwner_ = null;
        }
        
        //MySystem.s_printOutTrace(this, strMethod, "2");
        
        removeAll();
        
        //MySystem.s_printOutTrace(this, strMethod, "3");
        
        if (this._tbrToolbar_ != null)
        {
            this._tbrToolbar_.destroy();
            this._tbrToolbar_ = null;
        }
        
        //MySystem.s_printOutTrace(this, strMethod, "4");
        
        if (this._pnlPage_ != null)
        {
            MySystem.s_printOutTrace(this, strMethod, "this._pnlPage_ != null");
            this._pnlPage_.destroy();
            this._pnlPage_ = null;
        }
        
        else
        {
            //MySystem.s_printOutTrace(this, strMethod, "this._pnlPage_ == null");
        }
        
        //MySystem.s_printOutTrace(this, strMethod, "5");
    }
}

