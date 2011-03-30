package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "J" ==> new entry has a password

    known subclasses:
    . KTLKprSaveNewDsaJks
    . KTLKprSaveNewDsaJceks

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;
import com.google.code.p.keytooliui.ktl.swing.dialog.DTblsKstViewKeySavePK;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLKprSaveNewDsaJAbs extends KTLKprSaveNewDsaAbs
{    
    // ---------
    // PROTECTED
    
    // called in superclass
    protected boolean __doJob__(
        KeyStore kstOpen,
        
        // below: about PKTC (Private Key & Trusted Certificate)
        String[] strsAliasPKTC, 
        Boolean[] boosIsTCEntryPKTC, 
        Boolean[] boosValidDatePKTC, 
        Boolean[] boosSelfSignedCertPKTC, 
        Boolean[] boosTrustedCertPKTC, 
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC, 
        String[] strsAlgoSigCertPKTC, 
        Date[] dtesLastModifiedPKTC,
        // below: about SK (Secret Key)
        String[] strsAliasSK,
        Date[] dtesLastModifiedSK

        )
    {
        String strMethod = "__doJob__(...)";
        
        // ----        
        // MEMO: overwriting alias-key not allowed
        // -----

        // ----
        // show dialog KeyPair new Dsa
        //  . get aliasKpr
        //  . get passwdKpr
       
        
        DTblsKstViewKeySavePK dlg = new DTblsKstViewKeySavePK(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpen,
            super._strPathAbsKst_,
            "Create DSA private key entry");
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlg.load(
                
            // below: about PKTC (Private Key & Trusted Certificate)
            strsAliasPKTC, 
            boosIsTCEntryPKTC, 
            boosValidDatePKTC, 
            boosSelfSignedCertPKTC, 
            boosTrustedCertPKTC, 
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC, 
            strsAlgoSigCertPKTC, 
            dtesLastModifiedPKTC,
            // below: about SK (Secret Key)
            strsAliasSK,
            dtesLastModifiedSK))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlg.setVisible(true);
        
        // ---
        char[] chrsPasswdKpr = dlg.getPassword();
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, aborted by user");
            return false;
        }
        
        String strAliasKpr = dlg.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }
        
        // ----
        // create new KeyPair
        
        KeyPair kprNew = super._getKprNew_();
        
        if (kprNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kprNew");
            return false;
        }
        
        // beg trick
            
        //if (com.google.code.p.keytooliui.ktl.UIKeytool.S_BLN_NEWENTRYDSAPUBKEY2FILE)
          //  super._savePublicAsStringArray_(kprNew, strAliasKpr);
            
        // end trick
        
        // ----
        // create new certificate of type X.509
        // memo: sig algo: SHA1withDSA
        X509Certificate crtNew = super._getX509CertNew_(kprNew);
        
        if (crtNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtNew");
            return false;
        }
        
        // ----
        // assign new entry to open keystore
        
        if (! super._assignNewEntry2OpenKeystore_(kstOpen, kprNew, crtNew, strAliasKpr, chrsPasswdKpr))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        return true;
    }
    
    protected KTLKprSaveNewDsaJAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // output
        //String strProviderKpg,   // eg: "SUN, "BC", "SunRsaSign", "SunRsaSign", "SunJCE", SunJGSS"
        int intSizeKpr,
        //int intCertX509Version, // either 1 or 3 (version #1 or version #3)
        String strCertAlgoSignType, // eg: SHA1withDSA  
        
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
        String strKprX520N_UNIQUE_IDENTIFIER,
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
        
            // input
            strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
            chrsPasswdOpenKst, 
        
            // output
            //strProviderKpg,   // eg: "SUN, "BC", "SunRsaSign", "SunRsaSign", "SunJCE", SunJGSS"
            intSizeKpr,
            //intCertX509Version, // either 1 or 3 (version #1 or version #3)
            strCertAlgoSignType, // eg: SHA1withDSA  
        
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
        
            strProviderKst
            );
            
    }
}