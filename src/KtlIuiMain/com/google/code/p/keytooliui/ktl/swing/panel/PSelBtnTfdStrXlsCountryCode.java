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

import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

final public class PSelBtnTfdStrXlsCountryCode extends PSelBtnTfdStrXlsCbxAbs 
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    
    final static private int _f_s_intWidthComboBox = 180;
    
    final static private String _f_s_strCountryUs = "US (United States)";
    
    final static private String[] _f_s_strsCountry = 
    {
        // A
        "AF (Afghanistan)",
        "AL (Albania)",
        "DZ (Algeria)",
        "AS (American Samoa)",
        "AD (Andorra)",
        "AO (Angola)",
        "AI (Anguilla)",
        "AQ (Antartica)",
        "AG (Antigua & Barbuda)",
        "AR (Argentina)",
        "AM (Armenia)",
        "AW (Aruba)",
        "AU (Australia)",
        "AT (Austria)",
        "AZ (Azerbaijan)",
        // B
        "BS (Bahamas)",
        "BH (Bahrain)",
        "BD (Bangladesh)",
        "BB (Barbados)",
        "BY (Belarus)",
        "BE (Belgium)",
        "BZ (Belize)",
        "BJ (Benin)",
        "BM (Bermuda)",
        "BT (Bhutan)",
        "BO (Bolivia)",
        "BA (Bosnia & Herzegovina)",
        "BW (Bostwana)",
        "BV (Bouvet Island)",
        "BR (Brazil)",
        "IO (British Indian Ocean Territory)",
        "BN (Brunei Darussalam)",
        "BG (Bulgaria)",
        "BF (Burkina Faso)",
        "BI (Burundi)",
        // C
        "KH (Cambodia)",
        "CM (Cameroon)",
        "CA (Canada)",
        "CV (Cape Verde)",
        "KY (Cayman Islands)",
        "CF (Central African Republic)",
        "TD (Chad)",
        "CL (Chile)",
        "CN (China)",
        "CK (Christmas Island)",
        "CC (Cocos Islands)",
        "CO (Colombia)",
        "KM (Comoros)",
        "CG (Congo)",
        "CD (Congo, The Democratic Repubic Of The)",
        "CK (Cook Islands)",
        "CR (Costa Rica)",
        "CI (Cote d'Ivoire)",
        "HR (Croatia)",
        "CU (Cuba)",
        "CY (Cyprus)",
        "CZ (Czech Republic)",
        // D
        "DK (Denmark)",
        "DJ (Djibouti",
        "DM (Dominica)",
        "DO (Dominican Republic)",
        // E
        "EC (Ecuador)",
        "EG (Egypt)",
        "SV (El Salvador)",
        "GQ (Equatorial Guinea)",
        "ER (Eritrea)",
        "EE (Estonia)",
        "ET (Ethiopia)",
        // F
        "FK (Falkland Islands-Malvina)",
        "FO (Faroe Islands)",
        "FJ (Fiji)",
        "FI (Finland)",
        "FR (France)",
        "GF (French Guiana)",
        "PF (French Polynesia)",
        "TF (French Southern Territories)",
        // G
        "GA (Gabon)",
        "GM (Gambia)",
        "GE (Georgia)",
        "DE (Germany)",
        "GH (Ghana)",
        "GI (Gibraltar)",
        "GR (Greece)",
        "GL (Greenland)",
        "GD (Grenada)",
        "GP (Guadeloupe)",
        "GU (Guam)",
        "GT (Guatemala)",
        "GN (Guinea)",
        "GW (Guinea-Bisseau)",
        "GY (Guyana)",
        // H
        "HT (Haiti)",
        "HM (Heard Island & McDonalds Islands)",
        "VA (Holy See-Vatican City State)",
        "HN (Honduras)",
        "HK (Hong Kong)",
        "HU (Hungary)",
        // I
        "IS (Iceland)",
        "IN (India)",
        "ID (Indonesia)",
        "IR (Iran, Islamic Republic Of)",
        "IQ (Iraq)",
        "IE (Ireland)",
        "IL (Israel)",
        "IT (Italy)",
        // J
        "JM (Jamaica)",
        "JP (Japan)",
        "JO (Jordan)",
        // K
        "KZ (Kazakhstan)",
        "KE (Kenia)",
        "KI (Kiribati)",
        "KP (Korea, Democratic People's Republic Of)",
        "KR (Korea, Republic Of)",
        "KW (Kuwait)",
        "KG (Kyrgyzstan)",
        // L
        "LA (Lao People's Democratic Republic)",
        "LV (Latvia)",
        "LB (Lebanon)",
        "LS (Lesotho)",
        "LR (Liberia)",
        "LY (Lybian Arab Jamahiriya)",
        "LI (Liechtenstein)",
        "LT (Lithuana)",
        "LU (Luxembourg)",
        // M
        "MO (Macao)",
        "MK (Macedonia, The Former Yugoslav Republic Of)",
        "MG (Madagascar)",
        "MW (Malawi)",
        "MY (Malaysia)",
        "MV (Maldives)",
        "ML (Mali)",
        "MT (Malta)",
        "MH (Marshall Islands)",
        "MQ (Martinique)",
        "MR (Mauritania)",
        "MU (Mauritius)",
        "YT (Mayotte)",
        "MX (Mexico)",
        "FM (Micronesia, Federated States Of)",
        "MD (Moldova, Republic Of)",
        "MC (Monaco)",
        "MN (Mongolia)",
        "MS (Montserrat)",
        "MA (Morocco)",
        "MZ (Mozambique)",
        "MM (Myanmar)",
        // N
        "NA (Namibia)",
        "NR (Nauru)",
        "NP (Nepal)",
        "NL (Netherlands)",
        "AN (Netherlands Antilles)",
        "NC (New Caledonia)",
        "NZ (New Zealand)",
        "NI (Nicaragua)",
        "NE (Niger)",
        "NG (Nigeria)",
        "NU (Niue)",
        "NF (Norfolk Island)",
        "MP (Northern Mariana Islands)",
        "NO (Norway)",
        // O
        "OM (Oman)",
        // P
        "PK (Pakistan)",
        "PW (Palau)",
        "PS (Palestinian Territory, Occupied)",
        "PA (Panama)",
        "PG (Papua New Guinea)",
        "PY (Paraguay)",
        "PE (Peru)",
        "PH (Philippines)",
        "PN (Pitcairn)",
        "PL (Poland)",
        "PT (Portugal)",
        "PR (Puerto Rico)",
        // Q
        "QA (Qatar)",
        // R
        "RE (Reunion)",
        "RO (Romania)",
        "RU (Russian Federation)",
        "RW (Rwanda)",
        // S
        "SH (Saint Helena)",
        "KN (Saint Kitts & Nevis)",
        "LC (Saint Lucia)",
        "PM (Saint Pierre & Miquelon)",
        "VC (Saint Vincent & The Grenadines)",
        "WS (Samoa)",
        "SM (San Marino)",
        "ST (Sao Tome & Principe)",
        "SA (Saudi Arabia)",
        "SN (Senegal)",
        "SC (Seychelles)",
        "SL (Sierra Leone)",
        "SG (Singapore)",
        "SK (Slovakia)",
        "SI (Slovenia)",
        "SB (Solomon Islands)",
        "SO (Somalia)",
        "ZA (South Africa)",
        "GS (South Georgia & The South Sandwich Islands)",
        "ES (Spain)",
        "LK (Sri Lanka)",
        "SD (Sudan)",
        "SR (Suriname)",
        "SJ (Svalbard & Jan Mayen)",
        "SZ (Swaziland)",
        "SE (Sweden)",
        "CH (Switzerland)",
        "SY (Syrian Arab Republic)",
        // T
        "TW (Taiwan, Province Of China)",
        "TJ (Tajikistan)",
        "TZ (Tanzania, United Republic Of)",
        "TH (Thailand)",
        "TL (Timor-Leste)",
        "TG (Togo)",
        "TK (Tokelau)",
        "TO (Tonga)",
        "TT (Trinidad & Tobago)",
        "TN (Tunisia)",
        "TR (Turkey)",
        "TM (Turkmenistan)",
        "TC (Turks & Caicos Islands)",
        "TV (Tuvalu)",
        // U
        "UG (Uganda)",
        "UA (Ukraine)",
        "AE (United Arab Emirates)",
        "GB (United Kingdom)",
        "US (United States)",
        "UM (United States Minor Outlying Islands)",
        "UY (Uruguay)",
        "UZ (Uzbekistan)",
        // V
        "VU (Vanuatu)",
        "VE (Venezuela)",
        "VN (Viet Nam)",
        "VG (Virgin Islands, British)",
        // W
        "WF (Wallis & Futuna)",
        "EH (Western Sahara)",
        // X
        // Y
        "YE (Yemen)",
        "YU (Yugoslavia)",
        // Z
        "ZM (Zambia)",
        "ZW (Zimbabwe)"
    };
    
    // ------
    // PUBLIC
    
    protected boolean _setValueDefault_()
    {
        String strMethod = "_setValueDefault_()";        
        String str = System.getProperty("user.country");

        if (str!=null && str.length()>2)    // fixing up bug Sun-Solaris 64 bits, march 26, 2007    
        {
            for (int i=0; i<PSelBtnTfdStrXlsCountryCode._f_s_strsCountry.length; i++)
            {
                if (PSelBtnTfdStrXlsCountryCode._f_s_strsCountry[i].toLowerCase().startsWith(str.toLowerCase()))
                {
                    try
                    {
                        super._cmbArrayValue_.setSelectedItem(PSelBtnTfdStrXlsCountryCode._f_s_strsCountry[i]);
                    }

                    catch(Exception exc)
                    {
                        exc.printStackTrace();
                        MySystem.s_printOutError(this, strMethod, "exc caught");
                        return false;
                    }   

                    // returning
                    return true;
                }
            }
        }
        
        
        // ----
        // if str not pointing to array of strings, then assign "US" as default value       
        
        
        try
        {
            super._cmbArrayValue_.setSelectedItem(PSelBtnTfdStrXlsCountryCode._f_s_strCountryUs);
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
            str = str.substring(0, 2);
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
                        str = str.substring(0, 2);
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
    
    public PSelBtnTfdStrXlsCountryCode(
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
            PSelBtnTfdStrXlsCountryCode._f_s_strsCountry,
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
        
        
        if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedCountryCode(str))
        {
             MySystem.s_printOutWarning(this, strMethod, "failed");
             
            String strBody = "Country code value not allowed:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += str;
            strBody += "\"";
                    
            strBody += "\n\n";
            strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRuleCountryCode();
                    
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