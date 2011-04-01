/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**    
    known subclasses:
    . PSelBtnTfdStrXlsCountryCode
    . PSelBtnTfdStrXlsCbxGender
 *
 *"Cbx": ComboBox
**/


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComboBox;

import com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract;

abstract public class PSelBtnTfdStrXlsCbxAbs extends PSelBtnTfdStrXlsAbs implements FocusListener
{
 
    // ---------------
    // abstract public
    
    abstract public void focusGained(FocusEvent evtFocus);
    
    public void actionPerformed(ActionEvent evtAction) { super.actionPerformed(evtAction); }
    
    // ------------------
    // abstract protected
    
    abstract protected boolean _setValueDefault_();
    
    // ------
    // PUBLIC
    
    public boolean init()
    {        
        
        if (! super.init())
            return false;
        
        _addItem();

        // ending
        return true;
    }
    
    


    public void focusLost(FocusEvent evtFocus) {}
    
    public void destroy()
    {
        if (this._cmbArrayValue_ != null)
        {
            this._cmbArrayValue_.removeActionListener(this);
            this._cmbArrayValue_.removeFocusListener(this);
            this._cmbArrayValue_ = null;
        }
    }
    

   
    
    // ---------
    // PROTECTED
    
    protected JComboBox _cmbArrayValue_ = null;
    
    protected PSelBtnTfdStrXlsCbxAbs(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        String strLabel,
        TFAbstract tfd,
        Object objDocPropValue,
        boolean blnFieldRequired,
        String[] strsSComboBox
        )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            strLabel,
            tfd,
            objDocPropValue,
            blnFieldRequired
            );
        
        this._cmbArrayValue_ = new JComboBox(strsSComboBox);
        
        _setValueDefault_();
        this._cmbArrayValue_.addActionListener(this); // used in subclasses
        this._cmbArrayValue_.addFocusListener(this);
    }
    
    // -------
    // private
    
    private void _addItem()
    {
	super._pnl_.add(this._cmbArrayValue_);	
    }
}
