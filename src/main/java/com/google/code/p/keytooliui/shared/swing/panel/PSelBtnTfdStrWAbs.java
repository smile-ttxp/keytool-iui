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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PSelBtnTfdStrW10
    . PSelBtnTfdStrW20
    . PSelBtnTfdStrW30



    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable, width = 10

    ... used to select a string
    eg: keypass, storepass, alias
**/


import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

abstract public class PSelBtnTfdStrWAbs extends PSelBtnTfdStrAbs
{
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // added sept 6, 2002
        super.setEnabledSelect(true);
        
        // ending
        return true;
    }
    
    
    
    // ---------
    // PROTECTED
    
    protected PSelBtnTfdStrWAbs(Frame frmParent, String strLabel, TFAbstract tfa, Object objDocPropValue, boolean blnFieldRequired)
    {
        super(frmParent, strLabel, tfa, objDocPropValue, blnFieldRequired);
    }
    
    /**
        called by superclass
    **/
    protected boolean _validateText_(String str)
    {
        String strMethod = "_validateText_(str)";
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strText");
            return false;
        }
        
        super._tfdCurSelection_.setText(str);
        super._setSelectedValue_(true);
        super._btnClearSelection_.setEnabled(true);
        
        // ending
        return true;
    }
}