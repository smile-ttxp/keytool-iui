package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    KTLKprSaveFromPkcs12Pkcs12: "Kpr" for "keypair", 
    create a new entry in existing PKCS12 keystore by importing a keypair entry from PKCS12 keystore
    
    
    !!!!!!!!! not done yet: overwritting existing keypair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.PrivateKey;
import java.security.KeyStore;
// --
import java.security.cert.Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public final class KTLKprSaveFromPkcs12Pkcs12 extends KTLKprSaveFromPkcs12Abs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        
        . add providers
        
        . open keystoreFromPkcs12Source
        . open keystoreKstTarget
        . select aliasFromPkcs12Source pointing to valid kprFromPkcs12Source
        . get respective keyFromPkcs12 & crtsFromPkcs12
        
        . create new keypair
        . create new certificate of type X.509
        . assign new entry to open PKCS12 keystore
        . save PKCS12 keystore
    **/
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        
        // memo: PKCS12 keystore type, provided by "BC" or "SunRsaSign"
        File fleOpenKstFromPkcs12 = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strPathAbsKstFromPkcs12_);
        
        if (fleOpenKstFromPkcs12 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstFromPkcs12");
            return false;
        }
        
        // ----
        // open PKCS12 keystore
        
        if (super._chrsPasswdKstFromPkcs12_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKstFromPkcs12_"); 
        
        KeyStore kstOpenFromPkcs12 = UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKstFromPkcs12,
            super._chrsPasswdKstFromPkcs12_);
        
        if (kstOpenFromPkcs12 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenFromPkcs12");
            return false;
        }
            
            
        
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strPathAbsKst_);
        
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
            super._frmOwner_, 
            fleOpenKst,
            super._chrsPasswdKst_);
        
        if (kstOpen == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpen");
            return false;
        }
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpenFromPkcs12, kstOpen, fleOpenKst))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        // ending
        return true;
    }
    
    public KTLKprSaveFromPkcs12Pkcs12(
        Frame frmOwner, 
      
        
        // 
        String strPathAbsOpenKst, // existing keystore of type PKCS12 target 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsKstFromPkcs12, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstFromPkcs12
        
        )
    {
        super(
            frmOwner, 
    
        
            // 
            strPathAbsOpenKst, // existing keystore of type PKCS12 target 
            chrsPasswdOpenKst,
        
            strPathAbsKstFromPkcs12, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
            chrsPasswdKstFromPkcs12,

            KTLAbs.f_s_strProviderKstBC // provider for keytool of type PKCS12 target
            );
    }
    
    
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
        // create new alias, NO password
        
        
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
            super._frmOwner_, kstOpenTo, strsAlias);
        
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
        
        String[] strsAlgoKeyPubl = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_, kstOpenTo, strsAlias);
        
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

        Date[] dtesLastModified = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,  kstOpenTo, strsAlias);

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
            false // blnIsPassword
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
        
        String strAliasKpr = dlg.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }
        
        // ----
        // store privateKeyFromPkcs12 & crtsFromPkcs12 associated with alias (NO PASSWORD) in kstOpenTo
        

        if (! UtilKstPkcs12.s_setKeyEntry(super._frmOwner_,  
            kstOpenTo, strAliasKpr, pkyPrivateFromPkcs12, crtsFromPkcs12))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        return true;
    }
    
}