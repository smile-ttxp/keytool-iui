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

     2-letter countryCode selection
**/

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.event.DocumentListener;

import com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import com.google.code.p.keytooliui.shared.swing.textfield.Tfd3x30SelString;

public final class PSelBtnTfdStrXlsCountryCode extends PSelBtnTfdStrXlsCbxAbs 
{
    private static String defaultCountry;

    private static final String[] countries; 

    static {
        String[] isoCountries = Locale.getISOCountries();
        
        Locale locale = Locale.getDefault();
        
        countries = new String[isoCountries.length];
        for (int i = 0; i < isoCountries.length; i++) {
            String country = isoCountries[i];
            countries[i] = country + " (" + new Locale(locale.getLanguage(), country).getDisplayCountry() + ")";
            
            if (country.equals(locale.getCountry())) {
                defaultCountry = countries[i];
            }
        }
    }

    protected boolean _setValueDefault_()
    {
        _cmbArrayValue_.setSelectedItem(defaultCountry);
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
            str = str.substring(0, 2);
        }

        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excIndexOutOfBounds caught");
        }

        if (! _validateText_(str))
            MySystem.s_printOutExit(this, strMethod, "failed");
        
    }


    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";

        if (evtAction.getSource() instanceof JComboBox)
        {
            JComboBox cmb = (JComboBox) evtAction.getSource();
            
            if (_cmbArrayValue_!=null && _cmbArrayValue_==cmb)
            {
                Object obj = cmb.getSelectedItem();
                
                if (obj != null)
                {
                    if (! (obj instanceof String))
                        MySystem.s_printOutExit(this, strMethod, "! (obj instanceof String)");
                        
                    String str = (String) obj;
                    
                    try
                    {
                        str = str.substring(0, 2);
                    }
                    
                    catch(IndexOutOfBoundsException excIndexOutOfBounds)
                    {
                        excIndexOutOfBounds.printStackTrace();
                        MySystem.s_printOutExit(this, strMethod, "excIndexOutOfBounds caught");
                    }
                    
                    if (! _validateText_(str))
                        MySystem.s_printOutExit(this, strMethod, "failed");
                }
                
                // ending
                return;
            }
        }
        
        // redirect to superclasses
        super.actionPerformed(evtAction);
    }
    
    public PSelBtnTfdStrXlsCountryCode(DocumentListener docListenerParent, Frame frmParent, String strLabel, Object objDocPropValue, boolean blnFieldRequired)
    {
        super(docListenerParent, frmParent, strLabel, new Tfd3x30SelString(), objDocPropValue, blnFieldRequired, countries);
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
        
        
        if (!StringFilterUI.s_isAllowedCountryCode(str))
        {
             MySystem.s_printOutWarning(this, strMethod, "failed");
             
            String strBody = "Country code value not allowed:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += str;
            strBody += "\"";
                    
            strBody += "\n\n";
            strBody += StringFilterUI.s_getRuleCountryCode();
                    
            OPAbstract.s_showDialogWarning(_frmParent_, strBody);
            return true;
        }
        
        super._tfdCurSelection_.setText(str);
        super._setSelectedValue_(true);
        super._btnClearSelection_.setEnabled(true);
        
        return true;
    }
}
