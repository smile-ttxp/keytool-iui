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
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable

    ... used to select a string
    eg: keypass, storepass, alias


    known subclasses:
    . dpl: PSelBtnStrJnlpPrefix
    . dpl: PSelBtnStrUrl
    . shared: PSelBtnTfdStrWAbs
    . xls: PSelBtnStrXlsAbs
  
**/



import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

abstract public class PSelBtnTfdStrAbs extends PSelBtnTfdAbs
{
    // ------------------
    // ABSTRACT PROTECTED
    
    abstract protected boolean _validateText_(String str);
 
	// ------
	// PUBLIC

	// used by eg: default values
	public boolean forceValidateText(String str)
	{
		String strMethod = "forceValidateText(str)";

		if (! _validateText_(str))
		{
			MySystem.s_printOutError(this, strMethod, "failed");
			return false;
		}

		// force disabling "clearText" button
		super._btnClearSelection_.setEnabled(false);

		return true;
	}

    // ---------
    // PROTECTED
    
    protected PSelBtnTfdStrAbs(Frame frmParent, String strLabel, TFAbstract tfa, Object objDocPropValue, boolean blnFieldRequired)
    {
        super(frmParent, strLabel, blnFieldRequired);
        
        super._btnSelect_ = new BESString16(this);
        super._btnSelect_.setToolTipText("click to enter string value");
        super._tfdCurSelection_ = tfa;
        
        String strMethod = "PSelBtnTfdStrAbs(...)";
        
        if (objDocPropValue == null)
            MySystem.s_printOutExit(this, strMethod, "nil objDocPropValue");
        
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, objDocPropValue);
    }
    
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";
        
        String strDialogTitle = System.getProperty("_appli.title") + " - enter text";
        
        DSelectString ssgDialog = new DSelectString(
            super._frmParent_,
            strDialogTitle);
            
        if (! ssgDialog.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
            
        ssgDialog.setVisible(true);

        String str = ssgDialog.getValidatedText();
        
        if (str != null)
        {
            str = str.trim();
            
            if (str.length() > 0)
            {
                if (! _validateText_(str))
                    MySystem.s_printOutExit(this, strMethod, "failed");
            }
        }
        
        ssgDialog.destroy();
        ssgDialog = null;
    }
    
}