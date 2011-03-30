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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**
known subclasses:
    
    . shared: PSelCmbStrAbs (array of String)
    . shared: PSelCmbItgAbs (array of Integer)
    
    
    a panel that displays, from left to right:
    
    . 1 label
    . 1 combobox
    
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.*;

abstract public class PSelCmbAbs extends PSelAbs
{
    // ------
    // PUBLIC   
    
    public void destroy()
    {
        super.destroy();
        this._cmbArrayValue_ = null;
    }
    
    public Object getSelectedItemCmb()
    {
        if (this._cmbArrayValue_ == null)
            return null;
            
        return this._cmbArrayValue_.getSelectedItem();
    }
    
    public boolean init()
    {        
        if (! super.init())
            return false;
        
	    _addItem();
	    _setSizeComboBox();

        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
   
    
    // array of [String-Integer)
    protected JComboBox _cmbArrayValue_ = null;
    
    protected PSelCmbAbs(
        String strLabel,
        Object[] objsValue,
        int intSelectedId,
        int intWidthComboBox
        )
    {
        super(
            strLabel, 
            true // blnFieldRequired ==> coz always one value assigned in combobox
            ); 
            
        this._cmbArrayValue_ = new JComboBox(objsValue);
        this._intWidthComboBox = intWidthComboBox;
        
        // ----
        String strMethod = "PSelCmbAbs(...)";
        
        try
        {
            this._cmbArrayValue_.setSelectedItem(objsValue[intSelectedId]);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
        
        if (objsValue.length < 2)
            this._cmbArrayValue_.setEnabled(false);
            
        getLabel().setIcon(super._iinReqFieldOk_);
    }
    
    // -------
    // PRIVATE
    
    private int _intWidthComboBox = -1;
    
    private void _addItem()
    {
        // --
        // should add space : withButtonIcon16 + sep + widthButtonIcon16 + sep

        Dimension dimWidthIcon = new Dimension(16+8,16+8);
        
        super._pnl_.add(Box.createRigidArea(dimWidthIcon));
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
        super._pnl_.add(Box.createRigidArea(dimWidthIcon));
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
        // --
        
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	    super._pnl_.add(this._cmbArrayValue_);	
    }
    
    private void _setSizeComboBox()
    {       
        Dimension dim = new Dimension(this._intWidthComboBox, getLabel().getPreferredSize().height*2);
	    this._cmbArrayValue_.setMaximumSize(dim);
	    this._cmbArrayValue_.setMinimumSize(dim);
	    this._cmbArrayValue_.setPreferredSize(dim);
    }
}