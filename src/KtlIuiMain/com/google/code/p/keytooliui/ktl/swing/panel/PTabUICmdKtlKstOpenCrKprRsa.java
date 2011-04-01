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
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import java.awt.*;
import java.awt.event.*;

final public class PTabUICmdKtlKstOpenCrKprRsa extends PTabUICmdKtlKstOpenCrKprAbs 
{
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Create RSA private key entry, with vers. #1 cert";
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strHelpID = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenCrKprRsa";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".PTabUICmdKtlKstOpenCrKprRsa" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            PTabUICmdKtlKstOpenCrKprRsa._s_strHelpID = rbeResources.getString("helpID");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
    }
    
    
    
    
    // ------
    // PUBLIC
    
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        /*if (super._strOU_==null || super._strO_==null || super._strL_==null || super._strST_==null)
        {
            // show dialog warning: process, abort, process and don't ask me again
            
            if (PTabUICmdKtlKstOpenCrKprAbs._blnShowDlgWarnOptDnameMiss_)
            {
                if (! super._showDlgWarnOptDnameMiss_())
                {
                    MySystem.s_printOutTrace(this, strMethod, "cancelled by user");
                    return;
                }
            }
        }*/
        
        String strFormatKst = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();
        
        if (strFormatKst == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKst");
        
                
        Integer itgSizeKpr = (Integer) ((PSelCmbAbs) super._pnlSelectSizeKeypair_).getSelectedItemCmb();
        int intSizeKpr = itgSizeKpr.intValue();
        
        //Integer itgCertX509Version = (Integer) ((PSelCmbAbs) super._pnlSelectVersionCert_).getSelectedItemCmb();
        //int intCertX509Version = itgCertX509Version.intValue();

        String strCertAlgoSignType = (String) ((PSelCmbAbs) super._pnlSelectSigAlgoCert_).getSelectedItemCmb();
        
        char[] chrsPasswdKstTarget = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKstTarget = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKstTarget = "".toCharArray();
        
        
        KTLKprSaveNewRsaAbs ktl = null;
        
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprSaveNewRsaJks(
                super._frmOwner_, 
              
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
                intSizeKpr,
                //intCertX509Version,
                strCertAlgoSignType,
            
            
                super._intValidityKpr_,
                
                // --
                super._strCN_, 
                super._strOU_, 
                super._strO_, 
                super._strL_, 
                super._strST_, 
                super._strC_,
                super._strEMAIL_,
                    
                super._strCrtX500DNM_T_, // "DN" for "Distinguished Name", "M" for "More""
                super._strCrtX500DNM_SN_,
                super._strCrtX500DNM_STREET_,
                super._strCrtX500DNM_BUSINESS_CATEGORY_,
                super._strCrtX500DNM_POSTAL_CODE_,
                super._strCrtX500DNM_DN_QUALIFIER_,
                super._strCrtX500DNM_PSEUDONYM_,
                super._strCrtX500DNM_DATE_OF_BIRTH_,
                super._strCrtX500DNM_PLACE_OF_BIRTH_,
                super._strCrtX500DNM_GENDER_,
                super._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_,
                super._strCrtX500DNM_COUNTRY_OF_RESIDENCE_,
                super._strCrtX500DNM_NAME_AT_BIRTH_,
                super._strCrtX500DNM_POSTAL_ADDRESS_,

                super._strCrtX520N_SURNAME_,
                super._strCrtX520N_GIVENNAME_,
                super._strCrtX520N_INITIALS_,
                super._strCrtX520N_GENERATION_,
                super._strCrtX520N_UNIQUE_IDENTIFIER_
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprSaveNewRsaJceks(
                super._frmOwner_, 
              
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
                intSizeKpr,
                //intCertX509Version,
                strCertAlgoSignType,
            
            
                super._intValidityKpr_,
                
                // --
                super._strCN_, 
                super._strOU_, 
                super._strO_, 
                super._strL_, 
                super._strST_, 
                super._strC_,
                super._strEMAIL_,
                    
                super._strCrtX500DNM_T_, // "DN" for "Distinguished Name", "M" for "More""
                super._strCrtX500DNM_SN_,
                super._strCrtX500DNM_STREET_,
                super._strCrtX500DNM_BUSINESS_CATEGORY_,
                super._strCrtX500DNM_POSTAL_CODE_,
                super._strCrtX500DNM_DN_QUALIFIER_,
                super._strCrtX500DNM_PSEUDONYM_,
                super._strCrtX500DNM_DATE_OF_BIRTH_,
                super._strCrtX500DNM_PLACE_OF_BIRTH_,
                super._strCrtX500DNM_GENDER_,
                super._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_,
                super._strCrtX500DNM_COUNTRY_OF_RESIDENCE_,
                super._strCrtX500DNM_NAME_AT_BIRTH_,
                super._strCrtX500DNM_POSTAL_ADDRESS_,

                super._strCrtX520N_SURNAME_,
                super._strCrtX520N_GIVENNAME_,
                super._strCrtX520N_INITIALS_,
                super._strCrtX520N_GENERATION_,
                super._strCrtX520N_UNIQUE_IDENTIFIER_
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprSaveNewRsaPkcs12(
                super._frmOwner_, 
              
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
                intSizeKpr,
                //intCertX509Version,
                strCertAlgoSignType,
            
            
                super._intValidityKpr_,
                
                // --
                super._strCN_, 
                super._strOU_, 
                super._strO_, 
                super._strL_, 
                super._strST_, 
                super._strC_,
                super._strEMAIL_,
                    
                super._strCrtX500DNM_T_, // "DN" for "Distinguished Name", "M" for "More""
                super._strCrtX500DNM_SN_,
                super._strCrtX500DNM_STREET_,
                super._strCrtX500DNM_BUSINESS_CATEGORY_,
                super._strCrtX500DNM_POSTAL_CODE_,
                super._strCrtX500DNM_DN_QUALIFIER_,
                super._strCrtX500DNM_PSEUDONYM_,
                super._strCrtX500DNM_DATE_OF_BIRTH_,
                super._strCrtX500DNM_PLACE_OF_BIRTH_,
                super._strCrtX500DNM_GENDER_,
                super._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_,
                super._strCrtX500DNM_COUNTRY_OF_RESIDENCE_,
                super._strCrtX500DNM_NAME_AT_BIRTH_,
                super._strCrtX500DNM_POSTAL_ADDRESS_,

                super._strCrtX520N_SURNAME_,
                super._strCrtX520N_GIVENNAME_,
                super._strCrtX520N_INITIALS_,
                super._strCrtX520N_GENERATION_,
                super._strCrtX520N_UNIQUE_IDENTIFIER_
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprSaveNewRsaBks(
                super._frmOwner_, 
              
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
                intSizeKpr,
                //intCertX509Version,
                strCertAlgoSignType,
            
            
                super._intValidityKpr_,
                
                // --
                super._strCN_, 
                super._strOU_, 
                super._strO_, 
                super._strL_, 
                super._strST_, 
                super._strC_,
                super._strEMAIL_,
                    
                super._strCrtX500DNM_T_, // "DN" for "Distinguished Name", "M" for "More""
                super._strCrtX500DNM_SN_,
                super._strCrtX500DNM_STREET_,
                super._strCrtX500DNM_BUSINESS_CATEGORY_,
                super._strCrtX500DNM_POSTAL_CODE_,
                super._strCrtX500DNM_DN_QUALIFIER_,
                super._strCrtX500DNM_PSEUDONYM_,
                super._strCrtX500DNM_DATE_OF_BIRTH_,
                super._strCrtX500DNM_PLACE_OF_BIRTH_,
                super._strCrtX500DNM_GENDER_,
                super._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_,
                super._strCrtX500DNM_COUNTRY_OF_RESIDENCE_,
                super._strCrtX500DNM_NAME_AT_BIRTH_,
                super._strCrtX500DNM_POSTAL_ADDRESS_,

                super._strCrtX520N_SURNAME_,
                super._strCrtX520N_GIVENNAME_,
                super._strCrtX520N_INITIALS_,
                super._strCrtX520N_GENERATION_,
                super._strCrtX520N_UNIQUE_IDENTIFIER_
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprSaveNewRsaUber(
                super._frmOwner_, 
            
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
                intSizeKpr,
                //intCertX509Version,
                strCertAlgoSignType,
            
            
                super._intValidityKpr_,
                
                // --
                super._strCN_, 
                super._strOU_, 
                super._strO_, 
                super._strL_, 
                super._strST_, 
                super._strC_,
                super._strEMAIL_,
                    
                super._strCrtX500DNM_T_, // "DN" for "Distinguished Name", "M" for "More""
                super._strCrtX500DNM_SN_,
                super._strCrtX500DNM_STREET_,
                super._strCrtX500DNM_BUSINESS_CATEGORY_,
                super._strCrtX500DNM_POSTAL_CODE_,
                super._strCrtX500DNM_DN_QUALIFIER_,
                super._strCrtX500DNM_PSEUDONYM_,
                super._strCrtX500DNM_DATE_OF_BIRTH_,
                super._strCrtX500DNM_PLACE_OF_BIRTH_,
                super._strCrtX500DNM_GENDER_,
                super._strCrtX500DNM_COUNTRY_OF_CITIZENSHIP_,
                super._strCrtX500DNM_COUNTRY_OF_RESIDENCE_,
                super._strCrtX500DNM_NAME_AT_BIRTH_,
                super._strCrtX500DNM_POSTAL_ADDRESS_,

                super._strCrtX520N_SURNAME_,
                super._strCrtX520N_GIVENNAME_,
                super._strCrtX520N_INITIALS_,
                super._strCrtX520N_GENERATION_,
                super._strCrtX520N_UNIQUE_IDENTIFIER_
            );
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }
        
        // ----
        
        if (ktl.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "OK!");
            super._doneJob_(
                strFormatKst,
                com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strTypeKeypairRsa);
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
    }
    
    public PTabUICmdKtlKstOpenCrKprRsa(Frame frmOwner)
    {
        super(
            PTabUICmdKtlKstOpenCrKprRsa._s_strHelpID, 
            frmOwner, 
        
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_getItgsListSizeKprRsa(),
            true, // blnAllowTypePkcs12
            true, // blnAllowTypeBks
            true // blnAllowTypeUber
            );
        
        super._pnlSelectSizeKeypair_ = new PSelCmbItgSizeKprRsa();
        super._pnlSelectSigAlgoCert_ = new PSelCmbStrCertSigAlgoRsa();
    }
}
