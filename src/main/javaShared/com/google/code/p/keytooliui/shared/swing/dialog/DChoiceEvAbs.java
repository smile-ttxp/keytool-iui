/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

/*

    known subclasses 
    . DChoiceEvWarning
    . DChoiceEvQuestion

/** 

no effectes if clicking on the "close" at top-right of the dialog

*/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*; //Property change stuff

abstract public class DChoiceEvAbs extends DEscapeAbstract implements
    PropertyChangeListener
{     
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void propertyChange(PropertyChangeEvent evtPropertyChange);
    
    // ----------------
    // PUBLIC ACCESSORS
    
    public int getValue() { return this._intValue_; }
    
    // ------
    // PUBLIC
    
    // overwriting superclass's method
    public void windowClosing(WindowEvent evt)
    {
        Toolkit.getDefaultToolkit().beep();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._opn_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._opn_");
            return false;
        }
        
        this._opn_.addPropertyChangeListener(this);
        setContentPane(this._opn_);
        
        
        
        if (! this._opn_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected int _intValue_ = -1; // !!!!!!!!!!!!!!!!!!!!!
    protected OPAbstract _opn_ = null;   
    
    protected void _cancel_()
    {
        if (this._opn_ != null)
        {
            this._opn_.removePropertyChangeListener(this);
            this._opn_.destroy();
            this._opn_ = null;
        }
        
        super._cancel_();
    }
    
    protected DChoiceEvAbs(
        String strTitleSuffix
        )
    {
        super((Frame) null, true);
        
        setTitle(System.getProperty("_.appli.title") + " - " + strTitleSuffix);
        
        
        
    }
    
    // -------
    // PRIVATE


     
}