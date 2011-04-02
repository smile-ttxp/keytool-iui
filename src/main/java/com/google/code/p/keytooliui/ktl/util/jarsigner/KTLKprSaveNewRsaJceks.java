/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    KTLKprSaveNewRsaJceks: "Kpr" for "KeyPair"
    
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.awt.*;
import java.io.*;
import java.util.*;

public class KTLKprSaveNewRsaJceks extends KTLKprSaveNewRsaJAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . fill in table KeyPair
        . show dialog KeyPair new Rsa
          . get aliasKpr
          . get passwdKpr
        . create new KeyPair
        . create new certificate of type X.509
        . assign new entry to open keystore
        . save keystore
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        super._setEnabledCursorWait_(true);

        // memo: keystore should be of type "JCEKS", provided by "SunJCE"
        File fleOpenKstJceks = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKst_);
        
        if (fleOpenKstJceks == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstJceks");
            return false;
        }
        
        // ----
        // open JCEKS keystore
        
        if (super._chrsPasswdKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        }
        
        KeyStore kstOpenJceks = UtilKstJceks.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKstJceks,
            super._chrsPasswdKst_);
        
        if (kstOpenJceks == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil kstOpenJceks");
            return false;
        }
        
        if (! super._doJob_(fleOpenKstJceks, kstOpenJceks))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        // ending
        return true;
    }
    
    public KTLKprSaveNewRsaJceks(
        Frame frmOwner, 
    
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JCEKS 
        char[] chrsPasswdOpenKst, 
        
        // output
        int intSizeKpr,
        //int intCertX509Version, // either 1 or 3 (version #1 or version #3)
        String strCertAlgoSignType, //
        
        
        int intValidityKpr,
        
        String strKprX500DN_CN,    // "DN" for "Distinguisheds names", "CN" for "Common Name"
        String strKprX500DN_OU,
        String strKprX500DN_O,
        String strKprX500DN_L,
        String strKprX500DN_ST,
        String strKprX500DN_C,
        String strKprX500DN_EMAIL,
            
        String strKprX500DNM_T, // "DN" for "Distinguished Name", "M" for "More""
        String strKprX500DNM_SN,
        String strKprX500DNM_STREET,
        String strKprX500DNM_BUSINESS_CATEGORY,
        String strKprX500DNM_POSTAL_CODE,
        String strKprX500DNM_DN_QUALIFIER,
        String strKprX500DNM_PSEUDONYM,
        String strKprX500DNM_DATE_OF_BIRTH,
        String strKprX500DNM_PLACE_OF_BIRTH,
        String strKprX500DNM_GENDER,
        String strKprX500DNM_COUNTRY_OF_CITIZENSHIP,
        String strKprX500DNM_COUNTRY_OF_RESIDENCE,
        String strKprX500DNM_NAME_AT_BIRTH,
        String strKprX500DNM_POSTAL_ADDRESS,

        String strKprX520N_SURNAME,
        String strKprX520N_GIVENNAME,
        String strKprX520N_INITIALS,
        String strKprX520N_GENERATION,
        String strKprX520N_UNIQUE_IDENTIFIER
        )
    {
        super(
            frmOwner, 
      
        
            // input
            strPathAbsOpenKst, // existing keystore of type JCEKS 
            chrsPasswdOpenKst, 
        
            // output
            //KTLAbs.f_s_strSecurityProviderSunRsaSign, // provider for keypair generator
            intSizeKpr,
            //intCertX509Version, // either 1 or 3 (version #1 or version #3)
            strCertAlgoSignType, //
        
        
            intValidityKpr,
        
            strKprX500DN_CN,    // "DN" for "Distinguisheds names", "CN" for "Common Name"
            strKprX500DN_OU,
            strKprX500DN_O,
            strKprX500DN_L,
            strKprX500DN_ST,
            strKprX500DN_C,
            strKprX500DN_EMAIL,
                
            strKprX500DNM_T, // "DN" for "Distinguished Name", "M" for "More""
            strKprX500DNM_SN,
            strKprX500DNM_STREET,
            strKprX500DNM_BUSINESS_CATEGORY,
            strKprX500DNM_POSTAL_CODE,
            strKprX500DNM_DN_QUALIFIER,
            strKprX500DNM_PSEUDONYM,
            strKprX500DNM_DATE_OF_BIRTH,
            strKprX500DNM_PLACE_OF_BIRTH,
            strKprX500DNM_GENDER,
            strKprX500DNM_COUNTRY_OF_CITIZENSHIP,
            strKprX500DNM_COUNTRY_OF_RESIDENCE,
            strKprX500DNM_NAME_AT_BIRTH,
            strKprX500DNM_POSTAL_ADDRESS,

            strKprX520N_SURNAME,
            strKprX520N_GIVENNAME,
            strKprX520N_INITIALS,
            strKprX520N_GENERATION,
            strKprX520N_UNIQUE_IDENTIFIER,
            
            KTLAbs.f_s_strProviderKstJceks // provider for keystore
            
            );
    }
    
}