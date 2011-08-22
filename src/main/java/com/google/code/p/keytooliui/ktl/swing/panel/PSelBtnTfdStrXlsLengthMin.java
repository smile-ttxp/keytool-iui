/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable

    ... used to select a string
    eg: keypass, storepass, alias, sigfile
**/

import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.text.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;

import java.awt.*;

public final class PSelBtnTfdStrXlsLengthMin extends PSelBtnTfdStrXlsAbs
{
    // ------
    // PUBLIC
    
    public PSelBtnTfdStrXlsLengthMin(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 

        String strLabel,
        Object objDocPropValue,
        int intNbCharMin,
        boolean blnFieldRequired
        )
    {
        super(
            docListenerParent,
            frmParent, 
       
            strLabel,
            new Tfd10x30SelString(),
            objDocPropValue, 
            blnFieldRequired
            );
                
        this._intNbCharMin = intNbCharMin;  
    }
    
    // ---------
    // PROTECTED
    
    /**
        called by superclass
        
        no nil value allowed
        no space allowed
        fixed min charNb: this._intNbCharMin
    **/
    protected boolean _validateText_(String str)
    {
        String strMethod = "_validateText_(str)";
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strText");
            return false;
        }
        
        if (str.indexOf(' ') != -1)
        {
            MySystem.s_printOutWarning(this, strMethod, "str.indexOf(' ') != -1");
                    
            String strBody = "No spaces allowed in this string value.";
            OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
            return true;
        }
                
                
        if (str.length() < this._intNbCharMin)
        {
            MySystem.s_printOutWarning(this, strMethod, "str.length() < this._intNbCharMin");
                    
            String strBody = "String value requires at least ";
            strBody += Integer.toString(this._intNbCharMin);
            strBody += " chars.";
                    
            OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
            return true;
        }

        super._tfdCurSelection_.setText(str);
        super._setSelectedValue_(true);
        super._btnClearSelection_.setEnabled(true);
        
        // ending
        return true;
    }
    
    
    // -------
    // PRIVATE
    
    private int _intNbCharMin = 0; // ???????
}