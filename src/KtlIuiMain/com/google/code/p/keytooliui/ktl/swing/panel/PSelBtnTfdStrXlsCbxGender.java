/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    . 1 comboBox
    
    textfield is not editable

     1-letter gender selection
**/

import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

final public class PSelBtnTfdStrXlsCbxGender extends PSelBtnTfdStrXlsCbxAbs 
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    
    final static private int _f_s_intWidthComboBox = 100;
    
    final static private String _f_s_strListMale = "M";
    
    final static private String[] _f_s_strsList = 
    {
        "M",
        "F"
    };
    
    // ------
    // PUBLIC
    
    protected boolean _setValueDefault_()
    {
        String strMethod = "_setValueDefault_()";   
        
        
        try
        {
            super._cmbArrayValue_.setSelectedItem(PSelBtnTfdStrXlsCbxGender._f_s_strsList[0]);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            return false;
        }
        
        return true;
    }
    
    public void focusGained(FocusEvent evtFocus) 
    {
        String strMethod = "focusGained(evtFocus)";
        
        if (! (evtFocus.getSource() instanceof JComboBox))
            return;
        

        JComboBox cbx = (JComboBox) evtFocus.getSource();

        // ----
        Object obj = cbx.getSelectedItem();
        
        if (obj == null)
            return;
        
        
        if (! (obj instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (obj instanceof String)");

        String str = (String) obj;

        try
        {
            str = str.substring(0, 1);
        }

        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excIndexOutOfBounds caught");
        }

        if (! this._validateText_(str))
            MySystem.s_printOutExit(this, strMethod, "failed");
        
    }


    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";

        if (evtAction.getSource() instanceof JComboBox)
        {
            JComboBox cmb = (JComboBox) evtAction.getSource();
            
            if (super._cmbArrayValue_!=null && super._cmbArrayValue_==cmb)
            {
                Object obj = cmb.getSelectedItem();
                
                if (obj != null)
                {
                    if (! (obj instanceof String))
                        MySystem.s_printOutExit(this, strMethod, "! (obj instanceof String)");
                        
                    String str = (String) obj;
                    
                    try
                    {
                        str = str.substring(0, 1);
                    }
                    
                    catch(IndexOutOfBoundsException excIndexOutOfBounds)
                    {
                        excIndexOutOfBounds.printStackTrace();
                        MySystem.s_printOutExit(this, strMethod, "excIndexOutOfBounds caught");
                    }
                    
                    if (! this._validateText_(str))
                        MySystem.s_printOutExit(this, strMethod, "failed");
                }
                
                // ending
                return;
            }
        }
        
        // redirect to superclasses
        super.actionPerformed(evtAction);
    }
    
    public PSelBtnTfdStrXlsCbxGender(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        String strLabel,
        Object objDocPropValue,
         boolean blnFieldRequired
            )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            strLabel, 
            new TF2x20SelString(),
            objDocPropValue,
            blnFieldRequired,
            PSelBtnTfdStrXlsCbxGender._f_s_strsList,
            _f_s_intWidthComboBox
            );

    }
    
   
    
    // ---------
    // PROTECTED
    
    /**
        also called by superclass
    **/
    protected boolean _validateText_(String str)
    {
        String strMethod = "_validateText_(str)";
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strText");
            return false;
        }
        
        
        if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedCbxGender(str))
        {
             MySystem.s_printOutWarning(this, strMethod, "failed");
             
            String strBody = "Gender type not allowed:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += str;
            strBody += "\"";
                    
            strBody += "\n\n";
            strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRuleCbxGender();
                    
            OPAbstract.s_showDialogWarning(super._frmParent_, super._strTitleAppli_, strBody);
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
    
}
