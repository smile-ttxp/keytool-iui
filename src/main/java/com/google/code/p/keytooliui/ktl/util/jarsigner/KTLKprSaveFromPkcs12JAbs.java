package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromPkcs12Jks
    . KTLKprSaveFromPkcs12Jceks
     
    
    !!!!!!!!! not done yet: overwritting existing keypair entry

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
public abstract class KTLKprSaveFromPkcs12JAbs extends KTLKprSaveFromPkcs12Abs
{
    // ---------
    // PROTECTED
    
    // called in subclass
    protected boolean __createNewEntry__(
        KeyStore kstOpenTo,
        PrivateKey pkyPrivateFromPkcs12,
        Certificate[] crtsFromPkcs12
        )
    {
        String strMethod = "__createNewEntry__(...)";
        
        // ----
        // create new alias & new password
        
        
        // ----
        // fill in table keypair
        
        String[] strsAlias = UtilKstAbs.s_getStrsAlias(
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
            super._frmOwner_,  kstOpenTo, strsAlias);
        
        if (boosEntryKpr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKpr");
            return false;
        }
        
        Boolean[] boosEntryTcr = UtilKstAbs.s_getBoosEntryTcr(
            super._frmOwner_,  kstOpenTo, strsAlias);
        
        if (boosEntryTcr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcr");
            return false;
        }
        
        
        Boolean[] boosSelfSignedCert = UtilKstAbs.s_getBoosSelfSigned(
            super._frmOwner_,  kstOpenTo, strsAlias);
        
        if (boosSelfSignedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCert");
            return false;
        }
        
        Boolean[] boosTrustedCert = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,  kstOpenTo, strsAlias);
        
        if (boosTrustedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCert");
            return false;
        }
        
        String[] strsSizeKeyPubl = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,  kstOpenTo, strsAlias);
        
        if (strsSizeKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPubl");
            return false;
        }
        
        String[] strsAlgoKeyPubl = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_,  kstOpenTo, strsAlias);
        
        if (strsAlgoKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPubl");
            return false;
        }
        
        String[] strsTypeCert = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,  kstOpenTo, strsAlias);
        
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
        }
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
        
        
        // ----
        
        // MEMO: overwriting alias-key not allowed
        // -----

        // ----
        // show dialog keypair new entry
        //  . get aliasKpr
        //  . get passwdKpr
       
        DTblEntryKprSaveAny dlg = new DTblEntryKprSaveAny(
            super._frmOwner_, 
         
            kstOpenTo,
            true // blnIsPassword
            );
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlg.load(strsAlias, 
            boosEntryKpr, boosEntryTcr, 
            boosSelfSignedCert, boosTrustedCert, 
            strsAlgoKeyPubl, 
            strsSizeKeyPubl,
            strsTypeCert, strsAlgoSigCert, dtesLastModified))
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
        // store privateKeyFromPkcs12 & crtsFromPkcs12 associated with alias & password in kstOpenTo
        
        if (! UtilKstAbs.s_setKeyEntry(super._frmOwner_, 
            kstOpenTo, strAliasKpr, pkyPrivateFromPkcs12, chrsPasswdKpr, crtsFromPkcs12))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    
    protected KTLKprSaveFromPkcs12JAbs(
        Frame frmOwner, 
     
        
        // 
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS-PKCS12] 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsKstFromPkcs12, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstFromPkcs12,
        
        String strProviderKst
        
        )
    {
        super(
            frmOwner, 
         
            strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS-PKCS12] 
            chrsPasswdOpenKst,
            strPathAbsKstFromPkcs12, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
            chrsPasswdKstFromPkcs12,
            strProviderKst
            );
            
    }
    
    // -------
    // PRIVATE
    
}