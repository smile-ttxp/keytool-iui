package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    MEMO: usage:
    
    * keytool
        -genkey
        ...
        ...
        ...
        ...
        -dname "cn=[Testing Object Signing Certificate], o=[John Johnson], ou=[My Products], c=[EN-FR-DE-AU-RU]"

        ---------------------
        cn = certificate name
        o = organization
        ou = organizational unit
        c = country (first two letters)
        
        memo: in a batch:
        CN: first & last name
        OU: organizational unit
        O:  name of your organization
        L: name of your city or locality
        ST: name of your state or province
        C: what is the two-letter country code for this unit



    known subclasses:
    . PTabUICmdKtlKstOpenCrKprDsa
    . PTabUICmdKtlKstOpenCrKprRsa
    
**/

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.panel.PSelAbs;
import com.google.code.p.keytooliui.shared.swing.panel.PSelBtnTfdStrAbs;
import com.google.code.p.keytooliui.shared.swing.panel.PSelBtnTfdStrW10;
import com.google.code.p.keytooliui.shared.swing.panel.PSelBtnTfdStrW20;
import com.google.code.p.keytooliui.shared.swing.panel.PSelBtnTfdStrW30;
import net.miginfocom.swing.MigLayout;

abstract public class PTabUICmdKtlKstOpenCrKprAbs extends PTabUICmdKtlKstOpenAbs 
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    // properties 
    private static final String _f_s_strDocPropValCN = "cn";
    private static final String _f_s_strDocPropValOU = "ou";
    private static final String _f_s_strDocPropValO = "o";
    private static final String _f_s_strDocPropValL = "l";
    private static final String _f_s_strDocPropValST = "st";
    private static final String _f_s_strDocPropValEMAIL = "email";
    private static final String _f_s_strDocPropValC = "c";
    
    private static final String _f_s_strDocPropValT = "t";
    private static final String _f_s_strDocPropValSN = "sn";
    private static final String _f_s_strDocPropValSTREET = "street";
    private static final String _f_s_strDocPropValBUSINESS_CATEGORY = "business_category";
    private static final String _f_s_strDocPropValPOSTAL_CODE = "postal_code";
    private static final String _f_s_strDocPropValDN_QUALIFIER = "dn_qualifier";
    private static final String _f_s_strDocPropValPSEUDONYM = "pseudonym";
    private static final String _f_s_strDocPropValDATE_OF_BIRTH = "date_of_birth";
    private static final String _f_s_strDocPropValPLACE_OF_BIRTH = "place_of_birth";
    private static final String _f_s_strDocPropValGENDER = "gender";
    private static final String _f_s_strDocPropValCOUNTRY_OF_CITIZENSHIP = "country_of_citizenship";
    private static final String _f_s_strDocPropValCOUNTRY_OF_RESIDENCE = "country_of_residence";
    private static final String _f_s_strDocPropValNAME_AT_BIRTH = "name_at_birth";
    private static final String _f_s_strDocPropValPOSTAL_ADDRESS = "postal_address";
    
    
    private static final String _f_s_strDocPropValSURNAME = "surname";
    private static final String _f_s_strDocPropValGIVENNAME = "givenname";
    private static final String _f_s_strDocPropValINITIALS = "initials";
    private static final String _f_s_strDocPropValGENERATION = "generation";
    private static final String _f_s_strDocPropValUNIQUE_IDENTIFIER = "unique_identifier";
    
    private static final boolean _f_s_blnReqFieldCN = true;
    private static final boolean _f_s_blnReqFieldOU = false;
    private static final boolean _f_s_blnReqFieldO = false;
    private static final boolean _f_s_blnReqFieldL = false;
    private static final boolean _f_s_blnReqFieldST = false;
    private static final boolean _f_s_blnReqFieldC = true; // MEMO: required for SLL cert reply from Geotrust!
    private static final boolean _f_s_blnReqFieldEMAIL = false;
    
    private static final boolean _f_s_blnReqFieldT = false;
    private static final boolean _f_s_blnReqFieldSN = false;
    private static final boolean _f_s_blnReqFieldSTREET = false;
    private static final boolean _f_s_blnReqFieldBUSINESS_CATEGORY = false;
    private static final boolean _f_s_blnReqFieldPOSTAL_CODE = false;
    private static final boolean _f_s_blnReqFieldDN_QUALIFIER = false;
    private static final boolean _f_s_blnReqFieldPSEUDONYM = false;
    private static final boolean _f_s_blnReqFieldDATE_OF_BIRTH = false;
    private static final boolean _f_s_blnReqFieldPLACE_OF_BIRTH = false;
    private static final boolean _f_s_blnReqFieldGENDER = false;
    private static final boolean _f_s_blnReqFieldCOUNTRY_OF_CITIZENSHIP = false;
    private static final boolean _f_s_blnReqFieldCOUNTRY_OF_RESIDENCE = false;
    private static final boolean _f_s_blnReqFieldNAME_AT_BIRTH = false;
    private static final boolean _f_s_blnReqFieldPOSTAL_ADDRESS = false;
    
    private static final boolean _f_s_blnReqFieldSURNAME = false;
    private static final boolean _f_s_blnReqFieldGIVENNAME = false;
    private static final boolean _f_s_blnReqFieldINITIALS = false;
    private static final boolean _f_s_blnReqFieldGENERATION = false;
    private static final boolean _f_s_blnReqFieldUNIQUE_IDENTIFIER = false;
    
    // ------
    // PUBLIC
    
        
    public void insertUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "insertUpdate(evtDocument)";
        
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        
        // ----
        
        int intLength = doc.getLength();
        
        if (intLength == 0)
            MySystem.s_printOutExit(this, strMethod, "intLength == 0");
            
        String strText = null;
        
        try
        {
            strText = doc.getText(0, intLength);
        }
            
        catch(BadLocationException excBadLocation)
        {
            excBadLocation.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excBadLocation caught");
        }
        
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = strText;
            _updateActionButtonDataChanged_(true);
            return;
        }
        
        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = strText;
           // _updateActionButtonDataChanged_(true);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdRngIntValidityKpr.f_s_strDocPropVal) == 0)
        {
            try
            {
                this._intValidityKpr_ = Integer.parseInt(strText);
            }
                
            catch(NumberFormatException excNumberFormat)
            {
                excNumberFormat.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excNumberFormat caught");
            }
                
            _updateActionButtonDataChanged_(true);
            return;
        }
        
        // input right
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCN) == 0)
        {
            this._strCN_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCN)
                _updateActionButtonDataChanged_(true);
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValOU) == 0)
        {
            this._strOU_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldOU)
                _updateActionButtonDataChanged_(true);
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValO) == 0)
        {
            this._strO_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldO)
                _updateActionButtonDataChanged_(true);
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValL) == 0)
        {
            this._strL_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldL)
                _updateActionButtonDataChanged_(true);
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValST) == 0)
        {
            this._strST_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldST)
                _updateActionButtonDataChanged_(true);
            
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValEMAIL) == 0)
        {
            this._strEMAIL_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldEMAIL)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValC) == 0)
        {
            this._strC_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldC)
                _updateActionButtonDataChanged_(true);
            
            return;
        }
        
        // ----
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValT) == 0)
        {
            this._strCrtX500DNM_T_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldT)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSN) == 0)
        {
            this._strCrtX500DNM_SN_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSN)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSTREET) == 0)
        {
            this._strCrtX500DNM_STREET_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSTREET)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValBUSINESS_CATEGORY) == 0)
        {
            this._strCrtX500DNM_BUSINESS_CATEGORY_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldBUSINESS_CATEGORY)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPOSTAL_CODE) == 0)
        {
            this._strCrtX500DNM_POSTAL_CODE_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_CODE)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValDN_QUALIFIER) == 0)
        {
            this._strCrtX500DNM_DN_QUALIFIER_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDN_QUALIFIER)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPSEUDONYM) == 0)
        {
            this._strCrtX500DNM_PSEUDONYM_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPSEUDONYM)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValDATE_OF_BIRTH) == 0)
        {
            this._strCrtX500DNM_DATE_OF_BIRTH_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDATE_OF_BIRTH)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPLACE_OF_BIRTH) == 0)
        {
            this._strCrtX500DNM_PLACE_OF_BIRTH_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPLACE_OF_BIRTH)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGENDER) == 0)
        {
            this._strCrtX500DNM_GENDER_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENDER)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCOUNTRY_OF_CITIZENSHIP) == 0)
        {
            this._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_CITIZENSHIP)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCOUNTRY_OF_RESIDENCE) == 0)
        {
            this._strCrtX500DNM_COUNTRY_OF_RESIDENCE_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_RESIDENCE)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValNAME_AT_BIRTH) == 0)
        {
            this._strCrtX500DNM_NAME_AT_BIRTH_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldNAME_AT_BIRTH)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPOSTAL_ADDRESS) == 0)
        {
            this._strCrtX500DNM_POSTAL_ADDRESS_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_ADDRESS)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        // ----
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSURNAME) == 0)
        {
            this._strCrtX520N_SURNAME_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSURNAME)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGIVENNAME) == 0)
        {
            this._strCrtX520N_GIVENNAME_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGIVENNAME)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValINITIALS) == 0)
        {
            this._strCrtX520N_INITIALS_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldINITIALS)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGENERATION) == 0)
        {
            this._strCrtX520N_GENERATION_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENERATION)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValUNIQUE_IDENTIFIER) == 0)
        {
            this._strCrtX520N_UNIQUE_IDENTIFIER_ = strText;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldUNIQUE_IDENTIFIER)
                _updateActionButtonDataChanged_(true); // memo: field not required
                
            return;
        }
        
        // ----
        
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
    public void removeUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "removeUpdate(evtDocument)";
  
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        
        // --------
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            _updateActionButtonDataChanged_(false);
            return;
        }
        
        // input left
        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = null;
            //_updateActionButtonDataChanged_(false);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdRngIntValidityKpr.f_s_strDocPropVal) == 0)
        {
            this._intValidityKpr_ = 0;
            _updateActionButtonDataChanged_(false);
            return;
        }
        
        // input right
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCN) == 0)
        {
            this._strCN_ = null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCN)
                _updateActionButtonDataChanged_(false);
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValOU) == 0)
        {
            this._strOU_ = null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldOU)
                _updateActionButtonDataChanged_(false);
            
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValO) == 0)
        {
            this._strO_ = null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldO)
                _updateActionButtonDataChanged_(false);
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValL) == 0)
        {
            this._strL_ = null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldL)
                _updateActionButtonDataChanged_(false);
            
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValST) == 0)
        {
            this._strST_ = null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldST)
                _updateActionButtonDataChanged_(false);
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValEMAIL) == 0)
        {
            this._strEMAIL_ = null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldEMAIL)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValC) == 0)
        {
            this._strC_ = null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldC)
                _updateActionButtonDataChanged_(false);
            
            return;
        }
        
        // ----
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValT) == 0)
        {
            this._strCrtX500DNM_T_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldT)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSN) == 0)
        {
            this._strCrtX500DNM_SN_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSN)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSTREET) == 0)
        {
            this._strCrtX500DNM_STREET_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSTREET)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValBUSINESS_CATEGORY) == 0)
        {
            this._strCrtX500DNM_BUSINESS_CATEGORY_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldBUSINESS_CATEGORY)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPOSTAL_CODE) == 0)
        {
            this._strCrtX500DNM_POSTAL_CODE_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_CODE)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValDN_QUALIFIER) == 0)
        {
            this._strCrtX500DNM_DN_QUALIFIER_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDN_QUALIFIER)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPSEUDONYM) == 0)
        {
            this._strCrtX500DNM_PSEUDONYM_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPSEUDONYM)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValDATE_OF_BIRTH) == 0)
        {
            this._strCrtX500DNM_DATE_OF_BIRTH_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDATE_OF_BIRTH)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPLACE_OF_BIRTH) == 0)
        {
            this._strCrtX500DNM_PLACE_OF_BIRTH_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPLACE_OF_BIRTH)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGENDER) == 0)
        {
            this._strCrtX500DNM_GENDER_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENDER)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCOUNTRY_OF_CITIZENSHIP) == 0)
        {
            this._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_CITIZENSHIP)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCOUNTRY_OF_RESIDENCE) == 0)
        {
            this._strCrtX500DNM_COUNTRY_OF_RESIDENCE_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_RESIDENCE)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValNAME_AT_BIRTH) == 0)
        {
            this._strCrtX500DNM_NAME_AT_BIRTH_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldNAME_AT_BIRTH)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPOSTAL_ADDRESS) == 0)
        {
            this._strCrtX500DNM_POSTAL_ADDRESS_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_ADDRESS)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        // ----
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSURNAME) == 0)
        {
            this._strCrtX520N_SURNAME_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSURNAME)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGIVENNAME) == 0)
        {
            this._strCrtX520N_GIVENNAME_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGIVENNAME)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValINITIALS) == 0)
        {
            this._strCrtX520N_INITIALS_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldINITIALS)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGENERATION) == 0)
        {
            this._strCrtX520N_GENERATION_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENERATION)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        if (strPropVal.compareTo(PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValUNIQUE_IDENTIFIER) == 0)
        {
            this._strCrtX520N_UNIQUE_IDENTIFIER_= null;
            
            if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldUNIQUE_IDENTIFIER)
                _updateActionButtonDataChanged_(false); // memo: field not required
            
            return;
        }
        
        // ----
        
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._pnlSelectSizeKeypair_ != null)
        {
            this._pnlSelectSizeKeypair_.destroy();
            this._pnlSelectSizeKeypair_ = null;
        }
        
        if (this._pnlSelectSigAlgoCert_ != null)
        {
            this._pnlSelectSigAlgoCert_.destroy();
            this._pnlSelectSigAlgoCert_ = null;
        }
        
        /*if (this._pnlSelectVersionCert_ != null)
        {
            this._pnlSelectVersionCert_.destroy();
            this._pnlSelectVersionCert_ = null;
        }*/
        
        if (this._pnlSelectValidityKpr != null)
        {
            this._pnlSelectValidityKpr.destroy();
            this._pnlSelectValidityKpr = null;
        }
        
        // ----
        
        if (this._pnlSelectCN != null)
        {
            this._pnlSelectCN.destroy();
            this._pnlSelectCN = null;
        }
        
        if (this._pnlSelectOU != null)
        {
            this._pnlSelectOU.destroy();
            this._pnlSelectOU = null;
        }
        
        if (this._pnlSelectO != null)
        {
            this._pnlSelectO.destroy();
            this._pnlSelectO = null;
        }
        
        if (this._pnlSelectL != null)
        {
            this._pnlSelectL.destroy();
            this._pnlSelectL = null;
        }
        
        if (this._pnlSelectST != null)
        {
            this._pnlSelectST.destroy();
            this._pnlSelectST = null;
        }
        
        if (this._pnlSelectE != null)
        {
            this._pnlSelectE.destroy();
            this._pnlSelectE = null;
        }
        
        if (this._pnlSelectC != null)
        {
            this._pnlSelectC.destroy();
            this._pnlSelectC = null;
        }
        
        // ----
        
        if (this._pnlSelectT != null)
        {
            this._pnlSelectT.destroy();
            this._pnlSelectT = null;
        }
        
        if (this._pnlSelectSN != null)
        {
            this._pnlSelectSN.destroy();
            this._pnlSelectSN = null;
        }
        
        if (this._pnlSelectSTREET != null)
        {
            this._pnlSelectSTREET.destroy();
            this._pnlSelectSTREET = null;
        }
        
        if (this._pnlSelectBUSINESS_CATEGORY != null)
        {
            this._pnlSelectBUSINESS_CATEGORY.destroy();
            this._pnlSelectBUSINESS_CATEGORY = null;
        }
        
        if (this._pnlSelectPOSTAL_CODE != null)
        {
            this._pnlSelectPOSTAL_CODE.destroy();
            this._pnlSelectPOSTAL_CODE = null;
        }
        
        if (this._pnlSelectDN_QUALIFIER != null)
        {
            this._pnlSelectDN_QUALIFIER.destroy();
            this._pnlSelectDN_QUALIFIER = null;
        }
        
        if (this._pnlSelectPSEUDONYM != null)
        {
            this._pnlSelectPSEUDONYM.destroy();
            this._pnlSelectPSEUDONYM = null;
        }
        
        if (this._pnlSelectDATE_OF_BIRTH != null)
        {
            this._pnlSelectDATE_OF_BIRTH.destroy();
            this._pnlSelectDATE_OF_BIRTH = null;
        }
        
        if (this._pnlSelectPLACE_OF_BIRTH != null)
        {
            this._pnlSelectPLACE_OF_BIRTH.destroy();
            this._pnlSelectPLACE_OF_BIRTH = null;
        }
        
        if (this._pnlSelectGENDER != null)
        {
            this._pnlSelectGENDER.destroy();
            this._pnlSelectGENDER = null;
        }
        
        if (this._pnlSelectCOUNTRY_OF_CITIZENSHIP != null)
        {
            this._pnlSelectCOUNTRY_OF_CITIZENSHIP.destroy();
            this._pnlSelectCOUNTRY_OF_CITIZENSHIP = null;
        }
        
        if (this._pnlSelectCOUNTRY_OF_RESIDENCE != null)
        {
            this._pnlSelectCOUNTRY_OF_RESIDENCE.destroy();
            this._pnlSelectCOUNTRY_OF_RESIDENCE = null;
        }
        
        if (this._pnlSelectNAME_AT_BIRTH != null)
        {
            this._pnlSelectNAME_AT_BIRTH.destroy();
            this._pnlSelectNAME_AT_BIRTH = null;
        }
        
        if (this._pnlSelectPOSTAL_ADDRESS != null)
        {
            this._pnlSelectPOSTAL_ADDRESS.destroy();
            this._pnlSelectPOSTAL_ADDRESS = null;
        }
        
        // ----
        
        if (this._pnlSelectSURNAME != null)
        {
            this._pnlSelectSURNAME.destroy();
            this._pnlSelectSURNAME = null;
        }
        
        if (this._pnlSelectGIVENNAME != null)
        {
            this._pnlSelectGIVENNAME.destroy();
            this._pnlSelectGIVENNAME = null;
        }
        
        if (this._pnlSelectINITIALS != null)
        {
            this._pnlSelectINITIALS.destroy();
            this._pnlSelectINITIALS = null;
        }
        
        if (this._pnlSelectGENERATION != null)
        {
            this._pnlSelectGENERATION.destroy();
            this._pnlSelectGENERATION = null;
        }
        
        if (this._pnlSelectUNIQUE_IDENTIFIER != null)
        {
            this._pnlSelectUNIQUE_IDENTIFIER.destroy();
            this._pnlSelectUNIQUE_IDENTIFIER = null;
        }
        
        // ----
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectSigAlgoCert_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectSigAlgoCert_");
            return false;
        }
        
        if (! this._pnlSelectSigAlgoCert_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        /*if (this._pnlSelectVersionCert_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectVersionCert_");
            return false;
        }
        
        if (! this._pnlSelectVersionCert_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/

        if (this._pnlSelectSizeKeypair_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectSizeKeypair_");
            return false;
        }
        
        if (! this._pnlSelectSizeKeypair_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
 
        if (this._pnlSelectValidityKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectValidityKpr");
            return false;
        }
        
        if (! this._pnlSelectValidityKpr.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        // ----
        
        if (this._pnlSelectCN == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectCN");
            return false;
        }
        
        if (! this._pnlSelectCN.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
         if (this._pnlSelectOU == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectOU");
            return false;
        }
        
        if (! this._pnlSelectOU.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
         if (this._pnlSelectO == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectO");
            return false;
        }
        
        if (! this._pnlSelectO.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
         if (this._pnlSelectL == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectL");
            return false;
        }
        
        if (! this._pnlSelectL.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._pnlSelectST == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectST");
            return false;
        }
        
        if (! this._pnlSelectST.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectE == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectE");
            return false;
        }
        
        if (! this._pnlSelectE.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._pnlSelectC == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectC");
            return false;
        }
        
        if (! this._pnlSelectC.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        
        if (this._pnlSelectT == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectT");
            return false;
        }
        
        if (! this._pnlSelectT.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectSN == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectSN");
            return false;
        }
        
        if (! this._pnlSelectSN.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectSTREET == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectSTREET");
            return false;
        }
        
        if (! this._pnlSelectSTREET.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectBUSINESS_CATEGORY == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectBUSINESS_CATEGORY");
            return false;
        }
        
        if (! this._pnlSelectBUSINESS_CATEGORY.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectPOSTAL_CODE == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectPOSTAL_CODE");
            return false;
        }
        
        if (! this._pnlSelectPOSTAL_CODE.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectDN_QUALIFIER == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectDN_QUALIFIER");
            return false;
        }
        
        if (! this._pnlSelectDN_QUALIFIER.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectPSEUDONYM == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectPSEUDONYM");
            return false;
        }
        
        if (! this._pnlSelectPSEUDONYM.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectDATE_OF_BIRTH == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectDATE_OF_BIRTH");
            return false;
        }
        
        if (! this._pnlSelectDATE_OF_BIRTH.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectPLACE_OF_BIRTH == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectPLACE_OF_BIRTH");
            return false;
        }
        
        if (! this._pnlSelectPLACE_OF_BIRTH.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectGENDER == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectGENDER");
            return false;
        }
        
        if (! this._pnlSelectGENDER.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectCOUNTRY_OF_CITIZENSHIP == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectCOUNTRY_OF_CITIZENSHIP");
            return false;
        }
        
        if (! this._pnlSelectCOUNTRY_OF_CITIZENSHIP.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectCOUNTRY_OF_RESIDENCE == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectCOUNTRY_OF_RESIDENCE");
            return false;
        }
        
        if (! this._pnlSelectCOUNTRY_OF_RESIDENCE.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectNAME_AT_BIRTH == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectNAME_AT_BIRTH");
            return false;
        }
        
        if (! this._pnlSelectNAME_AT_BIRTH.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectPOSTAL_ADDRESS == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectPOSTAL_ADDRESS");
            return false;
        }
        
        if (! this._pnlSelectPOSTAL_ADDRESS.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        
        if (this._pnlSelectSURNAME == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectSURNAME");
            return false;
        }
        
        if (! this._pnlSelectSURNAME.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectGIVENNAME == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectGIVENNAME");
            return false;
        }
        
        if (! this._pnlSelectGIVENNAME.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectINITIALS == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectINITIALS");
            return false;
        }
        
        if (! this._pnlSelectINITIALS.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectGENERATION == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectGENERATION");
            return false;
        }
        
        if (! this._pnlSelectGENERATION.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectUNIQUE_IDENTIFIER == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectUNIQUE_IDENTIFIER");
            return false;
        }
        
        if (! this._pnlSelectUNIQUE_IDENTIFIER.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        
        // --
        // alignment

        if (! _alignLabelsPanelContents())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ----
        // memo: right after adding DocListener
        this._pnlSelectValidityKpr.setValueDefault();
        
        //((PSelBtnTfdStrXlsCountryCode) this._pnlSelectC).setValueDefault();
        //((PSelBtnTfdStrXlsCountryCode) this._pnlSelectCOUNTRY_OF_CITIZENSHIP).setValueDefault();
        //((PSelBtnTfdStrXlsCountryCode) this._pnlSelectCOUNTRY_OF_RESIDENCE).setValueDefault();
         
        
        // ----
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected JPanel _pnlOutputAll_ = null;
    protected GridBagConstraints _gbcOutputAll_ = null;
    //protected PSelAbs _pnlSelectVersionCert_ = null; // created at this level
    protected PSelAbs _pnlSelectSizeKeypair_ = null;
    protected PSelAbs _pnlSelectSigAlgoCert_ = null;
    
    protected int _intValidityKpr_ = 0;  
  
    protected String _strCN_ = null;
    protected String _strOU_ = null;
    protected String _strO_ = null;
    protected String _strL_ = null;
    protected String _strST_ = null;
    protected String _strEMAIL_ = null;
    protected String _strC_ = null;
    
    protected String _strCrtX500DNM_T_ = null; // "DN" for "Distinguished Name", "M" for "More""
    protected String _strCrtX500DNM_SN_ = null;
    protected String _strCrtX500DNM_STREET_ = null;
    protected String _strCrtX500DNM_BUSINESS_CATEGORY_ = null;
    protected String _strCrtX500DNM_POSTAL_CODE_ = null;
    protected String _strCrtX500DNM_DN_QUALIFIER_ = null;
    protected String _strCrtX500DNM_PSEUDONYM_ = null;
    protected String _strCrtX500DNM_DATE_OF_BIRTH_ = null;
    protected String _strCrtX500DNM_PLACE_OF_BIRTH_ = null;
    protected String _strCrtX500DNM_GENDER_ = null;
    protected String _strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_ = null;
    protected String _strCrtX500DNM_COUNTRY_OF_RESIDENCE_ = null;
    protected String _strCrtX500DNM_NAME_AT_BIRTH_ = null;
    protected String _strCrtX500DNM_POSTAL_ADDRESS_ = null;

    protected String _strCrtX520N_SURNAME_ = null;
    protected String _strCrtX520N_GIVENNAME_ = null;
    protected String _strCrtX520N_INITIALS_ = null;
    protected String _strCrtX520N_GENERATION_ = null;
    protected String _strCrtX520N_UNIQUE_IDENTIFIER_  = null;
    
    protected void _doneJob_(
        String strFormatKstTarget,
        String strTypeKeypair)
    {
        

       
        
        String strDlgBody = "Successfully created " + strTypeKeypair + " keypair." + "\n" +
            "New keypair entry stored in keystore:" + "\n    " + super._strPathAbsKst_ + "\n\n" +
            "View keystore?";
        
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strDlgBody))
            return;
            
        // show file
        // --
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstJks.s_showFile(super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstJceks.s_showFile(super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            char[] chrsPasswdKst = new char[0];
            
            if (super._strPasswdKst_ != null)
            {
                String strPasswdKst = super._strPasswdKst_.trim();
                
                if (strPasswdKst.length() > 0)
                    chrsPasswdKst = strPasswdKst.toCharArray();
            }
            
            UtilKstPkcs12.s_showFile(super._frmOwner_, super._strPathAbsKst_,
                    chrsPasswdKst);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstBks.s_showFile(super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            char[] chrsPasswdKst = new char[0];
            
            if (super._strPasswdKst_ != null)
            {
                String strPasswdKst = super._strPasswdKst_.trim();
                
                if (strPasswdKst.length() > 0)
                    chrsPasswdKst = strPasswdKst.toCharArray();
            }
            
            UtilKstUber.s_showFile(super._frmOwner_, super._strPathAbsKst_,
                    chrsPasswdKst);
            return;
        }
        
        
    }
    
    protected void _fillInPanelInput_()
    {        
        super._fillInPanelKst_(super._pnlInput_);
    }

    protected void _fillInPanelOutput_()
    {
        String strMethod = "_fillInPanelOutput_()";
        
        JPanel pnlOutputKeypair = _createPanelOutputKeypair();
        
        if (pnlOutputKeypair == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil pnlOutputKeypair");
        }
        
        JPanel pnlOutputX500DN = _createPanelOutputX500DN();
        
        if (pnlOutputX500DN == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil pnlOutputX500DN");
        }
        
        JPanel pnlOutputX500DNM = _createPanelOutputX500DNM();
        
        if (pnlOutputX500DNM == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil pnlOutputX500DNM");
        }
        
        JPanel pnlOutputX520N = _createPanelOutputX520N();
        
        if (pnlOutputX520N == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil pnlOutputX520N");
        }
        
        
        this._gbcOutputAll_.gridy ++;
        this._pnlOutputAll_.add(pnlOutputKeypair, this._gbcOutputAll_);
        
        this._gbcOutputAll_.gridy ++;
        this._pnlOutputAll_.add(pnlOutputX500DN, this._gbcOutputAll_);
        
        this._gbcOutputAll_.gridy ++;
        this._pnlOutputAll_.add(pnlOutputX500DNM, this._gbcOutputAll_);
        
        this._gbcOutputAll_.gridy ++;
        this._pnlOutputAll_.add(pnlOutputX520N, this._gbcOutputAll_);
        
        
        // ----
        super._pnlOutput_.add(this._pnlOutputAll_);
    }
    
    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {  
        String strMethod = "_updateActionButtonDataChanged_(blnFieldInserted)";
        
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
            {
                //MySystem.s_printOutTrace(this, strMethod, "super._btnAction_.isEnabled()");
                return;
            }
            
            if (super._strPathAbsKst_ == null)
            {
                //MySystem.s_printOutTrace(this, strMethod, "nil super._strPathAbsKst_");
                return;
            }
            
            /*if (super._strPasswdKst_ == null)
            {
                //MySystem.s_printOutTrace(this, strMethod, "nil super._strPasswdKst_");
                return;
            }*/
            
            if (this._intValidityKpr_ < 1)
            {
                //MySystem.s_printOutTrace(this, strMethod, "this._intValidityKpr_ < 1");
                return;
            }
            
            if (this._strCN_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCN)
                    return;
            }
            
            if (this._strOU_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldOU)
                    return;
            }
            
            if (this._strO_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldO)
                    return;
            }
            
            if (this._strL_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldL)
                    return;
            }
            
            if (this._strST_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldST)
                    return;
            }
            
            if (this._strC_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldC)
                    return;
            }
            
            // ----
            
           
            if (this._strCrtX500DNM_T_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldT)
                    return;
            }
            
            if (this._strCrtX500DNM_SN_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSN)
                    return;
            }
            
            if (this._strCrtX500DNM_STREET_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSTREET)
                    return;
            }
            
            if (this._strCrtX500DNM_BUSINESS_CATEGORY_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldBUSINESS_CATEGORY)
                    return;
            }
            
            if (this._strCrtX500DNM_POSTAL_CODE_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_CODE)
                    return;
            }
            
            if (this._strCrtX500DNM_DN_QUALIFIER_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDN_QUALIFIER)
                    return;
            }
            
            if (this._strCrtX500DNM_PSEUDONYM_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPSEUDONYM)
                    return;
            }
            
            if (this._strCrtX500DNM_DATE_OF_BIRTH_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDATE_OF_BIRTH)
                    return;
            }
            
            if (this._strCrtX500DNM_PLACE_OF_BIRTH_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPLACE_OF_BIRTH)
                    return;
            }
            
            if (this._strCrtX500DNM_GENDER_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENDER)
                    return;
            }
            
            if (this._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_CITIZENSHIP)
                    return;
            }
            
            if (this._strCrtX500DNM_COUNTRY_OF_RESIDENCE_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_RESIDENCE)
                    return;
            }
            
            if (this._strCrtX500DNM_NAME_AT_BIRTH_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldNAME_AT_BIRTH)
                    return;
            }
            
            if (this._strCrtX500DNM_POSTAL_ADDRESS_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_ADDRESS)
                    return;
            }
            
            // ----
            
           
            if (this._strCrtX520N_SURNAME_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSURNAME)
                    return;
            }
            
            if (this._strCrtX520N_GIVENNAME_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGIVENNAME)
                    return;
            }
            
            if (this._strCrtX520N_INITIALS_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldINITIALS)
                    return;
            }
            
            if (this._strCrtX520N_GENERATION_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENERATION)
                    return;
            }
            
            if (this._strCrtX520N_UNIQUE_IDENTIFIER_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldUNIQUE_IDENTIFIER)
                    return;
            }
            
            // ----
            
            // --
            //MySystem.s_printOutTrace(this, strMethod, "==> super._btnAction_.setEnabled(true)");
            super._btnAction_.setEnabled(true);
            return;
        }
        
        else
        {
            if (! super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsKst_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            /*if (super._strPasswdKst_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }*/
            
            if (this._intValidityKpr_ < 1)
            {
                super._btnAction_.setEnabled(false);
                return;
            }
            
            if (this._strCN_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCN)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strOU_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldOU)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strO_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldO)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strL_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldL)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strST_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldST)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strC_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldC)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            // ----
            
            if (this._strCrtX500DNM_T_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldT)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_SN_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSN)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_STREET_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSTREET)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_BUSINESS_CATEGORY_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldBUSINESS_CATEGORY)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_POSTAL_CODE_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_CODE)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_DN_QUALIFIER_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDN_QUALIFIER)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_PSEUDONYM_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPSEUDONYM)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_DATE_OF_BIRTH_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDATE_OF_BIRTH)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_PLACE_OF_BIRTH_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPLACE_OF_BIRTH)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_GENDER_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENDER)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_CITIZENSHIP)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_COUNTRY_OF_RESIDENCE_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_RESIDENCE)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_NAME_AT_BIRTH_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldNAME_AT_BIRTH)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX500DNM_POSTAL_ADDRESS_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_ADDRESS)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            // ----
            
            if (this._strCrtX520N_SURNAME_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSURNAME)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX520N_GIVENNAME_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGIVENNAME)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX520N_INITIALS_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldINITIALS)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX520N_GENERATION_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENERATION)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
            
            if (this._strCrtX520N_UNIQUE_IDENTIFIER_ == null)
            {
                if (PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldUNIQUE_IDENTIFIER)
                {
                    super._btnAction_.setEnabled(false);
                    return;
                }
            }
 
            // ----
            
            return;
        }
        
    }
    
    protected PTabUICmdKtlKstOpenCrKprAbs(
        String strHelpID, 
        Frame frmOwner, 
  
        Integer[] itgsSizeListKpr,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        super(
            strHelpID, 
            frmOwner, 
          
            true, // blnAllowTypeJks
            true, // blnAllowTypeJceks
            blnAllowTypePkcs12,
            blnAllowTypeBks,
            blnAllowTypeUber
            );
        
            
        // --
        // KeyPair
        
        //this._pnlSelectVersionCert_ = new PSelCmbItgVersionCert();
        
        this._pnlSelectValidityKpr = new PSelBtnTfdRngIntValidityKpr(
            (javax.swing.event.DocumentListener) this,
            frmOwner);

        // ----
        // cert info
        
        // ----
        
        this._pnlSelectCN = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Common (or domain) name:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCN,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCN // blnFieldRequired
            );
            
        this._pnlSelectOU = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Organizational unit:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValOU,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldOU // blnFieldRequired
            );
            
        this._pnlSelectO = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Organization:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValO,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldO // blnFieldRequired
            );
            
        this._pnlSelectL = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "City or locality:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValL,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldL // blnFieldRequired
            );
            
        this._pnlSelectST = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "State or province:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValST,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldST // blnFieldRequired
            );
            
        
            
        this._pnlSelectC = new PSelBtnTfdStrXlsCountryCode(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "2-letter country code:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValC,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldC
            );
            
        this._pnlSelectE = new PSelBtnTfdStrW20Email(
            (javax.swing.event.DocumentListener) this,
            frmOwner,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValEMAIL,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldEMAIL // blnFieldRequired
            );
        
        
        // ----
        
        this._pnlSelectT = new PSelBtnTfdStrW10(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Title:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValT,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldT // blnFieldRequired
            );
        
        this._pnlSelectSN = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Device serial number name:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSN,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSN // blnFieldRequired
            );
        
        this._pnlSelectSTREET = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Street:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSTREET,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSTREET // blnFieldRequired
            );
        
        this._pnlSelectBUSINESS_CATEGORY = new PSelBtnTfdStrW30(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Business category:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValBUSINESS_CATEGORY,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldBUSINESS_CATEGORY // blnFieldRequired
            );
        
        this._pnlSelectPOSTAL_CODE = new PSelBtnTfdStrW10(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Postal code:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPOSTAL_CODE,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_CODE // blnFieldRequired
            );
        
        this._pnlSelectDN_QUALIFIER = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "DN qualifier:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValDN_QUALIFIER,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDN_QUALIFIER // blnFieldRequired
            );
        
        this._pnlSelectPSEUDONYM = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Pseudonym:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPSEUDONYM,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPSEUDONYM // blnFieldRequired
            );
        
        this._pnlSelectDATE_OF_BIRTH = new PSelBtnTfdStrW10DateBirth(
            (javax.swing.event.DocumentListener) this,
            frmOwner,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValDATE_OF_BIRTH,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldDATE_OF_BIRTH // blnFieldRequired
            );
        
        this._pnlSelectPLACE_OF_BIRTH = new PSelBtnTfdStrW30(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Place of birth:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPLACE_OF_BIRTH,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPLACE_OF_BIRTH // blnFieldRequired
            );
        
        this._pnlSelectGENDER = new PSelBtnTfdStrXlsCbxGender(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "1-letter gender:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGENDER,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENDER
            );
        
        this._pnlSelectCOUNTRY_OF_CITIZENSHIP = new PSelBtnTfdStrXlsCountryCode(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "2-letter country of citizenship:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCOUNTRY_OF_CITIZENSHIP,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_CITIZENSHIP
            );
                
                /*new PSelBtnTfdStrW10(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Country of citizenship:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCOUNTRY_OF_CITIZENSHIP,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_CITIZENSHIP // blnFieldRequired
            );*/
        
        this._pnlSelectCOUNTRY_OF_RESIDENCE = new PSelBtnTfdStrXlsCountryCode(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "2-letter country of residence:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValCOUNTRY_OF_RESIDENCE,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldCOUNTRY_OF_RESIDENCE
            ); 
                
        
        this._pnlSelectNAME_AT_BIRTH = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Name at birth:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValNAME_AT_BIRTH,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldNAME_AT_BIRTH // blnFieldRequired
            );
        
        this._pnlSelectPOSTAL_ADDRESS = new PSelBtnTfdStrW10(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Postal address:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValPOSTAL_ADDRESS,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldPOSTAL_ADDRESS // blnFieldRequired
            );
        
        // ----
        
        this._pnlSelectSURNAME = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Surname:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValSURNAME,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldSURNAME // blnFieldRequired
            );
        
        this._pnlSelectGIVENNAME = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Given name:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGIVENNAME,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGIVENNAME // blnFieldRequired
            );
        
        this._pnlSelectINITIALS = new PSelBtnTfdStrW10(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Initials:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValINITIALS,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldINITIALS // blnFieldRequired
            );
        
        this._pnlSelectGENERATION = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Generation:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValGENERATION,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldGENERATION // blnFieldRequired
            );
        
        this._pnlSelectUNIQUE_IDENTIFIER = new PSelBtnTfdStrW20(
            (javax.swing.event.DocumentListener) this,
            frmOwner, "Unique identifier:",
            PTabUICmdKtlKstOpenCrKprAbs._f_s_strDocPropValUNIQUE_IDENTIFIER,
            PTabUICmdKtlKstOpenCrKprAbs._f_s_blnReqFieldUNIQUE_IDENTIFIER // blnFieldRequired
            );
        
        
        
        // ----
        
        this._pnlOutputAll_ = new JPanel();
        this._gbcOutputAll_ = new GridBagConstraints();
        
        this._pnlOutputAll_.setLayout(new GridBagLayout());
        
        
        //natural height, maximum width
        this._gbcOutputAll_.fill = GridBagConstraints.HORIZONTAL;
        this._gbcOutputAll_.fill = GridBagConstraints.VERTICAL;
        
        this._gbcOutputAll_.anchor = GridBagConstraints.WEST; // ? left side of space
        this._gbcOutputAll_.gridx = 0;
        this._gbcOutputAll_.gridy = -1;
    }
    
    
    // -------
    // PRIVATE
    
  
    private PSelBtnTfdRngIntValidityKpr _pnlSelectValidityKpr = null;

    private PSelBtnTfdStrAbs _pnlSelectCN = null;
    private PSelBtnTfdStrAbs _pnlSelectOU = null;
    private PSelBtnTfdStrAbs _pnlSelectO = null;
    private PSelBtnTfdStrAbs _pnlSelectL = null;
    private PSelBtnTfdStrAbs _pnlSelectST = null;
    private PSelBtnTfdStrAbs _pnlSelectE = null;
    private PSelBtnTfdStrAbs _pnlSelectC = null;
    
    // ----
    
    private PSelBtnTfdStrAbs _pnlSelectT = null;
    private PSelBtnTfdStrAbs _pnlSelectSN = null;
    private PSelBtnTfdStrAbs _pnlSelectSTREET = null;
    private PSelBtnTfdStrAbs _pnlSelectBUSINESS_CATEGORY = null;
    private PSelBtnTfdStrAbs _pnlSelectPOSTAL_CODE = null;
    private PSelBtnTfdStrAbs _pnlSelectDN_QUALIFIER = null;
    private PSelBtnTfdStrAbs _pnlSelectPSEUDONYM = null;
    private PSelBtnTfdStrAbs _pnlSelectDATE_OF_BIRTH = null;
    private PSelBtnTfdStrAbs _pnlSelectPLACE_OF_BIRTH = null;
    private PSelBtnTfdStrAbs _pnlSelectGENDER = null;
    private PSelBtnTfdStrAbs _pnlSelectCOUNTRY_OF_CITIZENSHIP = null;
    private PSelBtnTfdStrAbs _pnlSelectCOUNTRY_OF_RESIDENCE = null;
    private PSelBtnTfdStrAbs _pnlSelectNAME_AT_BIRTH = null;
    private PSelBtnTfdStrAbs _pnlSelectPOSTAL_ADDRESS = null;
    
    // ----
    
    private PSelBtnTfdStrAbs _pnlSelectSURNAME = null;
    private PSelBtnTfdStrAbs _pnlSelectGIVENNAME = null;
    private PSelBtnTfdStrAbs _pnlSelectINITIALS = null;
    private PSelBtnTfdStrAbs _pnlSelectGENERATION = null;
    private PSelBtnTfdStrAbs _pnlSelectUNIQUE_IDENTIFIER = null;
    
    // ----
    
    private boolean _alignLabelsPanelContents()
    {
        String strMethod = "_alignLabelsPanelContents()";
        
        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();
               
        
        // keystore
        
        
        vecPanel.add(super._pnlSelectFileKst_);
        vecPanel.add(super._pnlSelectPasswdKst_);
        
        
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        vecPanel.clear();
        
        // KeyPair
        
        vecPanel.add(this._pnlSelectSizeKeypair_);
        //vecPanel.add(this._pnlSelectVersionCert_);
        vecPanel.add(this._pnlSelectSigAlgoCert_);
        vecPanel.add(this._pnlSelectValidityKpr);
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        vecPanel.clear();
        
        
        // X500DN
        vecPanel.add(this._pnlSelectCN);
        vecPanel.add(this._pnlSelectOU);
        vecPanel.add(this._pnlSelectO);
        vecPanel.add(this._pnlSelectL);
        vecPanel.add(this._pnlSelectST);
        vecPanel.add(this._pnlSelectC);
        vecPanel.add(this._pnlSelectE);

        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        vecPanel.clear();
        
        // ----
        
        // X500DNM
        vecPanel.add(this._pnlSelectT);
        vecPanel.add(this._pnlSelectSN);
        vecPanel.add(this._pnlSelectSTREET);
        vecPanel.add(this._pnlSelectBUSINESS_CATEGORY);
        vecPanel.add(this._pnlSelectPOSTAL_CODE);
        vecPanel.add(this._pnlSelectDN_QUALIFIER);
        vecPanel.add(this._pnlSelectPSEUDONYM);
        vecPanel.add(this._pnlSelectDATE_OF_BIRTH);
        vecPanel.add(this._pnlSelectPLACE_OF_BIRTH);
        vecPanel.add(this._pnlSelectGENDER);
        vecPanel.add(this._pnlSelectCOUNTRY_OF_CITIZENSHIP);
        vecPanel.add(this._pnlSelectCOUNTRY_OF_RESIDENCE);
        vecPanel.add(this._pnlSelectNAME_AT_BIRTH);
        vecPanel.add(this._pnlSelectPOSTAL_ADDRESS);

        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        vecPanel.clear();
        
        
        // ----
        
        // X520N
        vecPanel.add(this._pnlSelectSURNAME);
        vecPanel.add(this._pnlSelectGIVENNAME);
        vecPanel.add(this._pnlSelectINITIALS);
        vecPanel.add(this._pnlSelectGENERATION);
        vecPanel.add(this._pnlSelectUNIQUE_IDENTIFIER);

        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        vecPanel.clear();
        
        // ----
        
        vecPanel = null;
        return true;
    }
    
    
    /**
        contains 1 panel that allow to assign values to generated KeyPair entry
        . 1) values of type String:
            . KeyPair size
            . KeyPair alias
            . KeyPair password
            . validity (DSA only), done in subclass
          
      
    **/
    private JPanel _createPanelOutputKeypair()
    {
        String strMethod = "_createPanelOutputKeypair()";
        
        // --
        
        JPanel pnl = new JPanel();
        pnl.setBorder(new TitledBorder("Private Key")); // keypair
        
        pnl.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        pnl.add(this._pnlSelectSizeKeypair_);
        //pnl.add(this._pnlSelectVersionCert_);
        pnl.add(this._pnlSelectSigAlgoCert_);
        pnl.add(this._pnlSelectValidityKpr);
        
        // ending
        return pnl;
    }
    
    private JPanel _createPanelOutputX520N()
    {
        JPanel pnl = new JPanel();
        pnl.setBorder(new TitledBorder("Cert. - X.520 Name"));
        
        pnl.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        pnl.add(this._pnlSelectSURNAME);
        pnl.add(this._pnlSelectGIVENNAME);
        pnl.add(this._pnlSelectINITIALS);
        pnl.add(this._pnlSelectGENERATION);
        pnl.add(this._pnlSelectUNIQUE_IDENTIFIER);
        
        // ending
        return pnl;
    }
    
    private JPanel _createPanelOutputX500DNM()
    {
        JPanel pnl = new JPanel();
        pnl.setBorder(new TitledBorder("Cert. - X.500 Distinguished Name ..."));
        
        pnl.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        pnl.add(this._pnlSelectT);
        pnl.add(this._pnlSelectSN);
        pnl.add(this._pnlSelectBUSINESS_CATEGORY);
        pnl.add(this._pnlSelectDN_QUALIFIER);
        pnl.add(this._pnlSelectPSEUDONYM);
        pnl.add(this._pnlSelectGENDER);
        pnl.add(this._pnlSelectNAME_AT_BIRTH);
        pnl.add(this._pnlSelectDATE_OF_BIRTH);
        pnl.add(this._pnlSelectPLACE_OF_BIRTH);
        pnl.add(this._pnlSelectSTREET);
        pnl.add(this._pnlSelectPOSTAL_CODE);
        pnl.add(this._pnlSelectPOSTAL_ADDRESS);
        pnl.add(this._pnlSelectCOUNTRY_OF_RESIDENCE);
        pnl.add(this._pnlSelectCOUNTRY_OF_CITIZENSHIP);
        
        // ending
        return pnl;
    }
    
    /**
        contains 1 panel that allow to assign values to generated keystore
        . 1) values of type String:
          . CN
          . OU
          . O
          . L
          . ST
          . C (2-letters)
          . E (email, optional)

         
         "DName" means "Distinguished Name"
    **/
    private JPanel _createPanelOutputX500DN()
    {
        JPanel pnl = new JPanel();
        pnl.setBorder(new TitledBorder("Cert. - X.500 Distinguished Name"));
        
        pnl.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        pnl.add(this._pnlSelectCN);
        pnl.add(this._pnlSelectOU);
        pnl.add(this._pnlSelectO);
        pnl.add(this._pnlSelectL);
        pnl.add(this._pnlSelectST);
        pnl.add(this._pnlSelectC);
        pnl.add(this._pnlSelectE);
        
        // ending
        return pnl;
    }
    
    //protected static boolean _blnShowDlgWarnOptDnameMiss_ = true;
    
    /*protected boolean _showDlgWarnOptDnameMiss_()
    {
        String strMethod = "_showDlgWarnOptDnameMiss_()";
                  
        Toolkit.getDefaultToolkit().beep();	
     
        com.google.code.p.keytooliui.ktl.swing.dialog.DWChoice3DNameMiss dew = new 
            com.google.code.p.keytooliui.ktl.swing.dialog.DWChoice3DNameMiss(
                (java.awt.Frame) super._frmOwner_);
            
        if (! dew.init())
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }
            
        dew.setVisible(true);
			    
		int intReply = dew.getValue();
		dew.destroy();
		dew = null;
			    
	    if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWChoice3Abs.f_s_intClose)
		{
            return false;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWChoice3Abs.f_s_intYes)
		{
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWChoice3Abs.f_s_intYesAllways)
		{
            PTabUICmdKtlKstOpenCrKprAbs._blnShowDlgWarnOptDnameMiss_ = false;
            return true;
        }
                
        MySystem.s_printOutExit(this, strMethod, "uncaught dialog answer, intReply=" + intReply);
              
        // !!! statement never reached
        return false;
    }*/
}