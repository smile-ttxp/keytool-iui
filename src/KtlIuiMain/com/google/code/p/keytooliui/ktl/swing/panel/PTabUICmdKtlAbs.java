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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    "Ktl" means Keytool


    known subclasses:
    . PTabUICmdKtlKstSave
    . PTabUICmdKtlKstOpenAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
//import com.google.code.p.keytooliui.shared.swing.layout.*;


import javax.swing.*;


import java.awt.*;

abstract public class PTabUICmdKtlAbs extends PTabUICmdAbs 
{
    // ------
    // PUBLIC
    
    
    public void destroy()
    {
        super.destroy();
        
        
        if (this._pnlSelectPasswdKst_ != null)
        {
            this._pnlSelectPasswdKst_.destroy();
            this._pnlSelectPasswdKst_ = null;
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        if (this._pnlSelectPasswdKst_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectPasswdKst_");
            return false;
        }
        
        if (! this._pnlSelectPasswdKst_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ---
        return true;
    }
    
    // ---------
    // PROTECTED   
    
    protected PSelBtnTfdPasswdXlsKstAny _pnlSelectPasswdKst_ = null;
    protected String _strPasswdKst_ = null;
    
    protected GridBagConstraints _fillInPanelKst_(JPanel pnl)
    {   
        // ----
        
        pnl.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        pnl.add(super._pnlSelectFileKst_, gbc);
        
        gbc.gridy ++;
        pnl.add(this._pnlSelectPasswdKst_, gbc);
        
        
        return gbc;
    }
    
    protected PTabUICmdKtlAbs(
        String strTextLabelPasswd,
        String strHelpID,
        Frame frmOwner,
        String strTitleAppli,
        int intModePassword,
        String strLabelBorderPanelIn, // nil value allowed
        String strLabelBorderPanelOut // nil value allowed
        )
    {
        super(strHelpID, frmOwner, strTitleAppli, strLabelBorderPanelIn, strLabelBorderPanelOut);
        
        this._pnlSelectPasswdKst_ = new PSelBtnTfdPasswdXlsKstAny(
            strTextLabelPasswd,
            (javax.swing.event.DocumentListener) this,
            frmOwner, strTitleAppli, intModePassword);
    }
    
    
    protected PTabUICmdKtlAbs(
        String strHelpID,
        Frame frmOwner,
        String strTitleAppli,
        int intModePassword,
        String strLabelBorderPanelIn, // nil value allowed
        String strLabelBorderPanelOut // nil value allowed
        )
    {
        super(strHelpID, frmOwner, strTitleAppli, strLabelBorderPanelIn, strLabelBorderPanelOut);
        
        this._pnlSelectPasswdKst_ = new PSelBtnTfdPasswdXlsKstAny(
            (javax.swing.event.DocumentListener) this,
            frmOwner, strTitleAppli, intModePassword);
    }
}