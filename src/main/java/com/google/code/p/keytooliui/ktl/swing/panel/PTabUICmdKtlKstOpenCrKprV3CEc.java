/*
 *  Copyright (C) 2011 geoForge Project
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



/**
 *
 * @author bantchao
 *
 * email: bantchao_AT_gmail.com
 * ... please remove "_AT_" from the above string to get the right email address
 *
 */

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

import java.util.Vector;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.KeyUsage;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

public final class PTabUICmdKtlKstOpenCrKprV3CEc extends PTabUICmdKtlKstOpenCrKprV3CAbs
{
    // ---------------------------
    // private static final String

    public static final String STR_TITLETASK = "Create EC private key entry, with vers. #3 cert";

    // ---------------------
    // PRIVATE STATIC STRING

    private static String _s_strHelpID = null;

    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenCrKprV3CEc";

        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".PTabUICmdKtlKstOpenCrKprV3CEc" // class name
            ;

        String strBundleFileLong = strBundleFileShort + ".properties";

        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort,
                java.util.Locale.getDefault());

            _s_strHelpID = rbeResources.getString("helpID");
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

        // ----

        // keyUsage cert's extension
        boolean blnCrtExtKeyUsage = super._cbxCrtExtKUEnabled_.isSelected();
        boolean blnCrtExtKeyUsageCritical = super._cbxCrtExtKUCritical_.isSelected();
        int intCrtExtKeyUsageValue = super._getCrtExtKUValue_();


        // ----
        // extKeyUsage cert's extension
        boolean blnCrtExtExtKeyUsageCritical = false;
        Vector<DERObjectIdentifier> vecCrtExtExtKeyUsage = null;

        if (super._cbxCrtExtEKUEnabled_.isSelected())
        {
            blnCrtExtExtKeyUsageCritical = super._cbxCrtExtEKUCritical_.isSelected();
            vecCrtExtExtKeyUsage = super._getCrtExtEKUValue_();
        }
        // ----

        // ----


        KTLKprSaveNewEcAbs ktl = null;


        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            ktl = new KTLKprSaveNewEcJksV3C(
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
                super._strCrtX520N_UNIQUE_IDENTIFIER_,

                blnCrtExtKeyUsage,
                blnCrtExtKeyUsageCritical,
                intCrtExtKeyUsageValue,

                blnCrtExtExtKeyUsageCritical,
                vecCrtExtExtKeyUsage
            );
        }

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            ktl = new KTLKprSaveNewEcJceksV3C(
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
                super._strCrtX520N_UNIQUE_IDENTIFIER_,

                blnCrtExtKeyUsage,
                blnCrtExtKeyUsageCritical,
                intCrtExtKeyUsageValue,

                blnCrtExtExtKeyUsageCritical,
                vecCrtExtExtKeyUsage
            );
        }

        // NOT SUPPORTED: PKCS12

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            ktl = new KTLKprSaveNewEcPkcs12V3C(
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
                super._strCrtX520N_UNIQUE_IDENTIFIER_,

                blnCrtExtKeyUsage,
                blnCrtExtKeyUsageCritical,
                intCrtExtKeyUsageValue,

                blnCrtExtExtKeyUsageCritical,
                vecCrtExtExtKeyUsage
            );
        }

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            ktl = new KTLKprSaveNewEcBksV3C(
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
                super._strCrtX520N_UNIQUE_IDENTIFIER_,

                blnCrtExtKeyUsage,
                blnCrtExtKeyUsageCritical,
                intCrtExtKeyUsageValue,

                blnCrtExtExtKeyUsageCritical,
                vecCrtExtExtKeyUsage
            );
        }

        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {

            ktl = new KTLKprSaveNewEcUberV3C(
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
                super._strCrtX520N_UNIQUE_IDENTIFIER_,

                blnCrtExtKeyUsage,
                blnCrtExtKeyUsageCritical,
                intCrtExtKeyUsageValue,

                blnCrtExtExtKeyUsageCritical,
                vecCrtExtExtKeyUsage
            );
        }

        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }

        // --

        if (ktl.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "OK!");

            super._doneJob_(
                strFormatKst,
                com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strTypeKeypairEc);
        }

        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
    }


    public PTabUICmdKtlKstOpenCrKprV3CEc(Frame frmOwner)
    {
        super(
            PTabUICmdKtlKstOpenCrKprV3CEc._s_strHelpID,
            frmOwner,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_getItgsListSizeKprEc(),
            true, // blnAllowTypePkcs12
            true, // blnAllowTypeBks
            true // blnAllowTypeUber
            );

        super._pnlSelectSizeKeypair_ = new PSelCmbItgSizeKprEc();
        super._pnlSelectSigAlgoCert_ = new PSelCmbStrCertSigAlgoEc();
    }

    // -------
    // PRIVATE

}