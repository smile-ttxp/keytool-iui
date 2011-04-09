package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    CP ==> content pane

    contains:
    . on top:    toolBar
    . centered:  tabbedPane
    . on bottom: status bar (pending)
**/

import com.google.code.p.keytooliui.ktl.swing.splitpane.SPUIMainKtl;
import com.google.code.p.keytooliui.ktl.swing.toolbar.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import java.awt.*;

public abstract class PCPMainUIAbs extends PCPMainAbs
{   
    // ------
    // PUBLIC
    
    public void setContextualHelpID()
    {
        if (this._spe_ != null)
            this._spe_.setContextualHelpID();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // -------------
        // init children
        
        
        
        if (this._spe_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spe_");
            return false;
        }
        
        if (! this._spe_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        /*if (this._pnlStatusBar == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlStatusBar");
            return false;
        }
        
        if (! this._pnlStatusBar.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/
        
        
        
        // ------------
        // add children
        add(super._tbrToolBar_, java.awt.BorderLayout.NORTH);
        
        
        javax.swing.JPanel pnl = new javax.swing.JPanel();
	    pnl.setLayout(new BorderLayout());
	    pnl.add(this._spe_, BorderLayout.CENTER);
            
            
        //pnl.add(this._pnlStatusBar, BorderLayout.SOUTH);
	    
	    add(pnl, BorderLayout.CENTER);
        
        
        // ------
        // ending
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._spe_ != null)
        {
            this._spe_.destroy();
            this._spe_ = null;
        }
        
        /*if (this._pnlStatusBar != null)
        {
            this._pnlStatusBar.destroy();
            this._pnlStatusBar = null;
        }*/
    }
    
    
    // ---------
    // PROTECTED
    
    protected SPUIMainKtl _spe_ = null;
    
    // SPUIMainKtl
    
    protected PCPMainUIAbs()
    {
        // create children
        //this._pnlStatusBar = new PBarContainerSec(alrParentAppli);
    }
    
    
    // -------
    // PRIVATE
    
    
    //private PBarContainerSec _pnlStatusBar = null;
}