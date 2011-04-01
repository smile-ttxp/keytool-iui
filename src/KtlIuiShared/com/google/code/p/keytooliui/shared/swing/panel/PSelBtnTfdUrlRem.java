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
    
    textfield is not editable, width = 30

    ... used to select a string
    eg: keypass, storepass, alias
**/


import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

final public class PSelBtnTfdUrlRem extends PSelBtnTfdStrWAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intNbCharMin = 9;
    
    // ------
    // PUBLIC
    
    public PSelBtnTfdUrlRem(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
    
        String strLabel,
        Object objDocPropValue,
        boolean blnFieldRequired
        )
    {
        super(
            frmParent, 
        
            strLabel, 
            new TF30x20SelUrlRem(docListenerParent), 
            objDocPropValue,
            blnFieldRequired
            );
    }
    
    // ---------
    // PROTECTED
    
    /**
        called by superclass,
        overwrite superclass's method
    **/
    protected boolean _validateText_(String str)
    {
        String strMethod = "_validateText_(str)";
        
        String strBodyWrongBeg = 
            "Wrong remote URL value!" +
            "\n" +
            "\n"
            ;
            
        String strBodyWrongEnd =
            "\n\n" +
            "EG, correct URL:" +
            "\n" +
            "  " +
            "\"http://www.foo.com\""
            ;
        
        
        
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strText");
            return false;
        }
        
        str = str.trim(); // not really needed!, done in calling methods
        
        if ((str.indexOf(' ') != -1))
        {
            MySystem.s_printOutWarning(this, strMethod, "str.indexOf(' ') != -1");
            
            String strWhat = "No spaces allowed.";
            _showDialogWarning(strWhat, str);
                    
            return true;
        }
                
                
        if (str.length() < _f_s_intNbCharMin)
        {
            MySystem.s_printOutWarning(this, strMethod, "str.length() < _f_s_intNbCharMin");
            
            String strWhat = "Value requires at least ";
            strWhat += Integer.toString(_f_s_intNbCharMin);
            strWhat += " chars.";
            
            _showDialogWarning(strWhat, str);
                    
            return true;
        }
        
        if (! str.toLowerCase().startsWith("http"))
        {
            MySystem.s_printOutWarning(this, strMethod, "! str.toLowerCase().startsWith(\"http\")");
                    
            String strWhat = "Value should start with \"http\"";
            _showDialogWarning(strWhat, str);
            
            return true;
        }
        
        if (str.indexOf('.') == -1)
        {
            MySystem.s_printOutWarning(this, strMethod, "str.indexOf('.') == -1");
            String strWhat = "Value does not contain any \".\" char.";
            _showDialogWarning(strWhat, str);
            
            return true;
        }
        
        if (str.indexOf("://") == -1)
        {
            MySystem.s_printOutWarning(this, strMethod, "str.indexOf(\"://\") == -1");
            
            String strWhat = "Value does not contain \"://\".";
            _showDialogWarning(strWhat, str);
            
            return true;
        }

        // ending
        return super._validateText_(str);
    }
    
    // -------
    // PRIVATE
    
    private void _showDialogWarning(String strWhat, String strValue)
    {
        String strBodyWrongBeg = 
            "Wrong remote URL value!" +
            "\n" +
            "\n"
            ;
            
        String strBodyWrongEnd =
            "\n\n" +
            "EG, correct URL:" +
            "\n" +
            "  " +
            "\"http://www.foo.com\""
            ;
            
        String strBodyWrongMid =
            "\n\n" +
            "Value entered:" +
            "\n" +
            "  " +
            "\"" +
            strValue +
            "\""
            ;
            
        String strBody = new String();
        strBody += strBodyWrongBeg;
        strBody += strWhat;
        strBody += strBodyWrongMid;
        strBody += strBodyWrongEnd;
        
        OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
    }
}