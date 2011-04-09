package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromPkcs12ToKPJks ("KP" for "Key with Password")
    . KTLKprSaveFromPkcs12ToKPJceks
     
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;


// ----
import java.security.PrivateKey;
import java.security.KeyStore;
// --
import java.security.cert.Certificate;
// ----

import java.awt.*;
import java.util.*;

// subclasses: Jceks or Jks
public abstract class KTLKprSaveFromPkcs12ToKPAbs extends KTLKprSaveFromPkcs12ToAbs
{
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
        
        // NEW
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
          
            kstOpenTarget);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
          
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
             kstOpenTarget, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            kstOpenTarget, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
             kstOpenTarget, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
            kstOpenTarget, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
             kstOpenTarget, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
             kstOpenTarget, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
            kstOpenTarget, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
             kstOpenTarget, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            kstOpenTarget, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        // ----
        // create new alias & new password
        
        
        // ----
        // fill in table KeyPair
        
        /*String[] strsAlias = UtilKstAbs.s_getStrsAlias(
            super._frmOwner_, 
            
            kstOpenTo);
        
        if (strsAlias == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlias");
            return false;
        }
        
        // --
        
        Boolean[] boosEntryKpr = UtilKstAbs.s_getBoosEntryKpr(
            super._frmOwner_, kstOpenTo, strsAlias);
        
        if (boosEntryKpr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKpr");
            return false;
        }
        
        Boolean[] boosEntryTcr = UtilKstAbs.s_getBoosEntryTcr(
            super._frmOwner_, kstOpenTo, strsAlias);
        
        if (boosEntryTcr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcr");
            return false;
        }
        
        
        Boolean[] boosSelfSignedCert = UtilKstAbs.s_getBoosSelfSigned(
            super._frmOwner_, kstOpenTo, strsAlias);
        
        if (boosSelfSignedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCert");
            return false;
        }
        
        Boolean[] boosTrustedCert = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_, kstOpenTo, strsAlias);
        
        if (boosTrustedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCert");
            return false;
        }
        
        String[] strsSizeKeyPubl = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_, kstOpenTo, strsAlias);
        
        if (strsSizeKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPubl");
            return false;
        }
        
        String[] strsAlgoKeyPubl = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_, kstOpenTo, strsAlias);
        
        if (strsAlgoKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPubl");
            return false;
        }
        
        String[] strsTypeCert = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_, kstOpenTo, strsAlias);
        
        if (strsTypeCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsTypeCert");
            return false;
        }
        
        String[] strsAlgoSigCert = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_, kstOpenTo, strsAlias);
        
        if (strsAlgoSigCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoSigCert");
            return false;
        }

        Date[] dtesLastModified = UtilKstAbs.s_getDtesLastModified(super._frmOwner_, kstOpenTo, strsAlias);

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
       
        /*DTblEntryKprSaveAny dlgTarget = new DTblEntryKprSaveAny(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpenTo,
            true // blnIsPassword
            );*/
        
        DTblsKstViewKeySavePK dlgTarget = new DTblsKstViewKeySavePK(
            super._frmOwner_, 
            
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
        char[] chrsPasswdKpr = dlgTarget.getPassword();
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, aborted by user");
            return false;
        }
        
        String strAliasKpr = dlgTarget.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }
        
        
        // ----
        // store privateKeySource & crtsSource associated with alias & password in kstOpenTo
        
        if (! UtilKstAbs.s_setKeyEntry(super._frmOwner_, 
            kstOpenTarget, strAliasKpr, pkyPrivateSource, chrsPasswdKpr, crtsSource))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    
    protected KTLKprSaveFromPkcs12ToKPAbs(
        Frame frmOwner, 
     
        
        // 
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS-PKCS12] 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsKstSource, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstSource,
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 
         
            strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS-PKCS12] 
            chrsPasswdOpenKst,
            strPathAbsKstSource, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
            chrsPasswdKstSource,
            strProviderKst,
            true // blnIsPasswdKprTarget
            );
            
    }
    
    // -------
    // PRIVATE
    
}