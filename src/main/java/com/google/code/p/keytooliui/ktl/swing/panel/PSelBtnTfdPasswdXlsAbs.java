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

    . PSelBtnTfdPasswdXlsKstAbs (KeyStore of type JKS or PKCS12)
    . PSelBtnTfdPasswdXlsKpr (KeyPair)


    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 passwordfieldSelection
    
    passwordfield is not editable

     
**/

import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;

import javax.swing.*;

import java.awt.*;

public abstract class PSelBtnTfdPasswdXlsAbs extends PSelBtnTfdAbs
{
    // --------------------
    // PRIVATE STATIC FINAL    
    
    private static final String _f_s_strLabelSuffix = "password:";

    // ------
    // public
    
    public void destroy()
    {
        super.destroy();
        
        if (super._tfdCurSelection_ != null)
        {
            ((PFAbs)super._tfdCurSelection_).destroy();
            super._tfdCurSelection_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";
        DPasswordAbs dlg = null;
        
        int intMode = ((BESPasswordAbs) super._btnSelect_).getMode();
        
        if (intMode == BESPasswordAbs.f_s_intModeOpen)
            dlg = new DPasswordOpen(super._frmParent_, 
                    false // blnNoPasswdAllowed
                    );
            
        else if (intMode == BESPasswordAbs.f_s_intModeSave)
            dlg = new DPasswordConfirmSave(super._frmParent_);
            
        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught password mode, intMode=" + intMode);
        }
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
            
        dlg.setVisible(true);
        
        char[] chrsPassword = dlg.getPassword();
        
        if (chrsPassword != null)
        {
            if (chrsPassword.length > 0)
            {
                if (! _validate(chrsPassword, intMode))
                    MySystem.s_printOutExit(this, strMethod, "failed");
            }
        }
        
        dlg.destroy();
        dlg = null;
    }
    
    protected PSelBtnTfdPasswdXlsAbs(
        String strLabel,
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 

        Object objDocPropValue,
        boolean blnFieldRequired,
        int intModePassword
        )
    {
        super(
            frmParent, 
      
            strLabel,
            blnFieldRequired
            );
        
        super._btnSelect_ = new BESPassword16(
            (java.awt.event.ActionListener) this,
            intModePassword
            );
            
        super._tfdCurSelection_ = new PF10x20(docListenerParent);
        
        String strMethod = "PSelBtnTfdPasswdXlsAbs(...)";
        
        if (objDocPropValue == null)
            MySystem.s_printOutExit(this, strMethod, "nil objDocPropValue");
        
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, objDocPropValue);
        
    }
    
    protected PSelBtnTfdPasswdXlsAbs(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
       
        Object objDocPropValue,
        String strLabelPrefix,
        boolean blnFieldRequired,
        int intModePassword
        )
    {
        this(
            strLabelPrefix + " " + PSelBtnTfdPasswdXlsAbs._f_s_strLabelSuffix,
            docListenerParent,
            frmParent, 

            objDocPropValue,
            blnFieldRequired,
            intModePassword
            );
    }
    
    // -------
    // PRIVATE
    
    private boolean _validate(char[] chrsPassword, int intMode)
    {
        String strMethod = "_validate(chrsPassword)";
        
        if (chrsPassword == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil chrsPassword");
            return false;
        }
        
        String str = new String(chrsPassword);
             
        if (intMode == BESPasswordAbs.f_s_intModeSave)
        {
            if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedPassword(str))
            {
                MySystem.s_printOutWarning(this, strMethod, "failed");

                String strBody = "Password value not allowed:";
                strBody += "\n";
                strBody += "  ";
                strBody += "\"";
                strBody += str;
                strBody += "\"";

                strBody += "\n\n";
                strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRulePassword();

                OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
                return true;
            }
        }
        

        super._tfdCurSelection_.setText(str);
        super._setSelectedValue_(true);
        super._btnClearSelection_.setEnabled(true);
        
        // ending
        return true;
    }
}