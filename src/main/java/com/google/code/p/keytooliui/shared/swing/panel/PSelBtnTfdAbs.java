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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 label
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable, is of class TFAbstract

    ... used to select [fileSys-String-Integer]
    eg: keypass, storepass, alias
    
    known subclasses:
    
    . shared: PSelBtnTfdStrAbs
    . shared: PSelBtnTfdFileDirAbs
    . dpl: PSelBtnTfdFileIcnAbs
    . dpl: PSelBtnFileDocRcrAbs
    . xls: PSelBtnFileJarAbs
    . xls: PSelBtnFileSecAbs (eg: KeyStore, CSR, certificate)
    . xls: PSelBtnRngInt (memo: subclassed)
**/



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public abstract class PSelBtnTfdAbs extends PSelBtnAbs 
{
    
    // ------
    // PUBLIC
    
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        super.actionPerformed(evtAction);
        
        if (! (evtAction.getSource() instanceof BEnabledState))
            MySystem.s_printOutExit(this, strMethod, "! (evtAction.getSource() instanceof BEnabledState)");
            
        BEnabledState btnSource = (BEnabledState) evtAction.getSource();
        
        if (btnSource instanceof BESClearEntry16)
        {
            this._tfdCurSelection_.setText("");
            return;
        }
        
        /* declared in superclass, defined in subclasses, called right there
         */
        _showDialog_();
    }
    
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        if (this._tfdCurSelection_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._tfdCurSelection_");
            return false;
        }
        
        _addItem();

        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    // destroyed in subclasses
    protected JTextField _tfdCurSelection_ = null;
    
    protected PSelBtnTfdAbs(Frame frmParent, String strLabel, boolean blnFieldRequired)
    {
        super(frmParent, strLabel, blnFieldRequired);
    }
    
    // -------
    // PRIVATE
    
    private void _addItem()
    {
        // ------
        // adding

        super._pnl_.add(this._tfdCurSelection_);
    }

    public JTextField getTextField() { return this._tfdCurSelection_; }
}