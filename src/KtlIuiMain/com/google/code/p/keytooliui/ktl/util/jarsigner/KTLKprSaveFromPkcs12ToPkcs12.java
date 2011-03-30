package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    KTLKprSaveFromPkcs12Pkcs12: "Kpr" for "KeyPair", 
    create a new entry in existing PKCS12 keystore by importing a KeyPair entry from PKCS12 keystore
    
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
// --
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

final public class KTLKprSaveFromPkcs12ToPkcs12 extends KTLKprSaveFromPkcs12ToAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        
        . add providers
        
        . open keystoreSource
        . open keystoreKstTarget
        . select aliasSource pointing to valid kprSource
        . get respective keySource & crtsSource
        
        . create new KeyPair
        . create new certificate of type X.509
        . assign new entry to open PKCS12 keystore
        . save PKCS12 keystore
    **/
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        
        // memo: PKCS12 keystore type, provided by "BC" or "SunRsaSign"
        File fleOpenKstSource = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKstSource_);
        
        if (fleOpenKstSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstSource");
            return false;
        }
        
        // ----
        // open PKCS12 keystore
        
        if (super._chrsPasswdKstSource_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKstSource_"); 
        
        KeyStore kstOpenSource = UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKstSource,
            super._chrsPasswdKstSource_);
        
        if (kstOpenSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenSource");
            return false;
        }
            
            
        
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        // open PKCS12 target keystore
        
        if (super._chrsPasswdKst_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        
        KeyStore kstOpen = UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKst,
            super._chrsPasswdKst_);
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpen");
            return false;
        }
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpenSource, kstOpen, fleOpenKst))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        // ending
        return true;
    }
    
    public KTLKprSaveFromPkcs12ToPkcs12(
        Frame frmOwner, 
        String strTitleAppli,
        
        // 
        String strPathAbsOpenKst, // existing keystore of type PKCS12 target 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsKstSource, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstSource
        
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
        
            // 
            strPathAbsOpenKst, // existing keystore of type PKCS12 target 
            chrsPasswdOpenKst,
        
            strPathAbsKstSource, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
            chrsPasswdKstSource,

            KTLAbs.f_s_strProviderKstBC, // provider for keytool of type PKCS12 target
            false // blnIsPasswdKprTarget
            );
    }
    
    
    // ---------
    // PROTECTED
    
    
    // called in subclass
    protected boolean __createNewEntry__(
        KeyStore kstOpenTarget,
        PrivateKey pkyPrivateSource,
        Certificate[] crtsSource
        )
    {
        String strMethod = "__createNewEntry__(...)";
        
        // ----
        // create new alias, NO password
        
        // NEW
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpenTarget);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpenTarget);
        
        if (strsAliasSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        // --
        // get arrays for dialogTableSelectKeypair
        // TC versus PK
        Boolean[] boosIsTCEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        
        // ----
        // fill in table KeyPair
        
        /*String[] strsAlias = UtilKstAbs.s_getStrsAlias(
            super._frmOwner_, 
            super._strTitleAppli_, 
            kstOpenTarget);
        
        if (strsAlias == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlias");
            return false;
        }
        
        // --
        
        Boolean[] boosEntryKpr = UtilKstAbs.s_getBoosEntryKpr(
            super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (boosEntryKpr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKpr");
            return false;
        }
        
        Boolean[] boosEntryTcr = UtilKstAbs.s_getBoosEntryTcr(
            super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (boosEntryTcr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcr");
            return false;
        }
        
        
        Boolean[] boosSelfSignedCert = UtilKstAbs.s_getBoosSelfSigned(
            super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (boosSelfSignedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCert");
            return false;
        }
        
        Boolean[] boosTrustedCert = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (boosTrustedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCert");
            return false;
        }
        
        String[] strsSizeKeyPubl = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (strsSizeKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPubl");
            return false;
        }
        
        String[] strsAlgoKeyPubl = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (strsAlgoKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPubl");
            return false;
        }
        
        String[] strsTypeCert = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (strsTypeCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsTypeCert");
            return false;
        }
        
        String[] strsAlgoSigCert = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);
        
        if (strsAlgoSigCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoSigCert");
            return false;
        }

        Date[] dtesLastModified = UtilKstAbs.s_getDtesLastModified(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAlias);

        if (dtesLastModified == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil dtesLastModified");
            return false;
        }*/
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
        
        
        // ----
        
        // MEMO: overwriting alias-key not allowed
        // -----

        // ----
        // show dialog KeyPair new entry
        //  . get aliasKpr
        //  . get passwdKpr
       

        
        DTblsKstViewKeySavePKNoPass dlgTarget = new DTblsKstViewKeySavePKNoPass(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpenTarget,
            super._strPathAbsKst_,
            "Import private key from other keystore - step 2/2: target");
        
        if (! dlgTarget.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlgTarget.load(
                
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
            dtesLastModifiedSK
                /*strsAlias, 
            boosEntryKpr, boosEntryTcr, 
            boosSelfSignedCert, boosTrustedCert, 
            strsAlgoKeyPubl,
            strsSizeKeyPubl,
            strsTypeCert, strsAlgoSigCert, dtesLastModified*/))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlgTarget.setVisible(true);
        
        // ---
        
        String strAliasKpr = dlgTarget.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }
        
        // ----
        // store privateKeySource & crtsSource associated with alias (NO PASSWORD) in kstOpenTarget
        

        if (! UtilKstPkcs12.s_setKeyEntry(super._frmOwner_, super._strTitleAppli_, 
            kstOpenTarget, strAliasKpr, pkyPrivateSource, crtsSource))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        return true;
    }
    
}