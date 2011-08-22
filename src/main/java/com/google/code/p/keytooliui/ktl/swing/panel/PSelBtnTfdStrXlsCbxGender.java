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
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    . 1 comboBox
    
    textfield is not editable

     1-letter gender selection
**/

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import com.google.code.p.keytooliui.shared.swing.textfield.Tfd3x30SelString;

public final class PSelBtnTfdStrXlsCbxGender extends PSelBtnTfdStrXlsCbxAbs 
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    
    private static final String _f_s_strListMale = "M";
    
    private static final String[] _f_s_strsList = 
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
 
        String strLabel,
        Object objDocPropValue,
         boolean blnFieldRequired
            )
    {
        super(
            docListenerParent,
            frmParent, 
      
            strLabel, 
            new Tfd3x30SelString(),
            objDocPropValue,
            blnFieldRequired,
            PSelBtnTfdStrXlsCbxGender._f_s_strsList
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
    
}
