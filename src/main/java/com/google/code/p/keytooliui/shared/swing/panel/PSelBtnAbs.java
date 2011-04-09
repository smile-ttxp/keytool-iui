/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
    
    textfield is not editable

    ... used to select [fileSys-String-Integer]
    eg: keypass, storepass, alias
    
    known subclasses:
    

    . rcr: PSelBtnTfdAbs       ==> one textField of class TFAbstract
    . xls: PSelBtnPasswdXlsAbs ==> one textField of class JPasswordField 
**/


import com.google.code.p.keytooliui.shared.swing.button.*;

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;
import java.awt.event.*;

public abstract class PSelBtnAbs extends PSelAbs implements ActionListener
{    
    // ------------------
    // ABSTRACT PROTECTED
    
    
    protected abstract void _showDialog_();
    
    // ------
    // PUBLIC
    
    public void setSelectedValue(boolean bln)
    {
        this._setSelectedValue_(bln);
    }
    
    public void setFieldRequired(boolean bln) 
    {
        super.setFieldRequired(bln);
        
        this._blnFieldRequired = bln; 
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (! (evtAction.getSource() instanceof BEnabledState))
            MySystem.s_printOutExit(this, strMethod, "! (evtAction.getSource() instanceof BEnabledState)");
            
        BEnabledState btnSource = (BEnabledState) evtAction.getSource();
        
        if (btnSource instanceof BESClearEntry16)
        {
            this._btnClearSelection_.setEnabled(false);
            this._setSelectedValue_(false);
            return;
        }
    }
    
    public boolean setEnabledSelect(boolean bln)
    {
        String strMethod = "setEnabledSelect(bln)";
        
         if (this._btnSelect_ == null)
         {
            MySystem.s_printOutError(this, strMethod, "nil this._btnSelect_");
            return false;
         }
         
         this._btnSelect_.setEnabled(bln);
         return true;
    }
    
    /****/
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnSelect_ != null)
        {
            this._btnSelect_.destroy();
            this._btnSelect_ = null;
        }
        
        if (this._btnClearSelection_ != null)
        {
            this._btnClearSelection_.destroy();
            this._btnClearSelection_ = null;
        }
            
        this._frmParent_ = null;
  
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        if (this._btnSelect_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnSelect_");
            return false;
        }

	_addItem();

        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected BEnabledState _btnSelect_ = null;
    
    
    protected BESClearEntry16 _btnClearSelection_ = null;
    protected Frame _frmParent_ = null;

    
    protected void _setSelectedValue_(boolean bln)
    {
        if (! this._blnFieldRequired)
            return;
            
        if (bln)
            getLabel().setIcon(super._iinReqFieldOk_);
        else
            getLabel().setIcon(super._iinReqFieldWrong_);
    }
    
    protected PSelBtnAbs(
        Frame frmParent, 

        String strLabel,
        boolean blnFieldRequired
        )
    {
        super(strLabel, blnFieldRequired);
        
        this._frmParent_ = frmParent;
      
        this._blnFieldRequired = blnFieldRequired;
       
        // --
        
        this._btnClearSelection_ = new BESClearEntry16(this);  
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnFieldRequired = false;
    
    private void _addItem()
    {
	    // ------
	    // adding

	    //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	    super._pnl_.add(this._btnSelect_);
	    //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	    super._pnl_.add(this._btnClearSelection_);
    }
}