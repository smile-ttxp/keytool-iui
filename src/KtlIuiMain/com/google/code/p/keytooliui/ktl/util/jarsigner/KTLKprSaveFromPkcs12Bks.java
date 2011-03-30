package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    KTLKprSaveFromPkcs12Bks: "Kpr" for "keypair", 
    create a new entry in existing BKS keystore by importing a keypair entry from PKCS12 keystore
    
    
    !!!!!!!!! not done yet: overwritting existing keypair entry

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

final public class KTLKprSaveFromPkcs12Bks extends KTLKprSaveFromPkcs12JAbs
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
        . assign new entry to open BKS keystore
        . save BKS keystore
    **/
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        
        // memo: PKCS12 keystore type, provided by "BC" or "SunRsaSign"
        File fleOpenKstFromPkcs12 = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKstFromPkcs12_);
        
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
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKstFromPkcs12,
            super._chrsPasswdKstFromPkcs12_);
        
        if (kstOpenFromPkcs12 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenFromPkcs12");
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
        // open BKS keystore
        
        if (super._chrsPasswdKst_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        
        KeyStore kstOpen = UtilKstBks.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
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
    
    public KTLKprSaveFromPkcs12Bks(
        Frame frmOwner, 
        String strTitleAppli,
        
        // 
        String strPathAbsOpenKst, // existing keystore of type BKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsKstFromPkcs12, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstFromPkcs12
        
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
        
            // 
            strPathAbsOpenKst, // existing keystore of type BKS 
            chrsPasswdOpenKst,
        
            strPathAbsKstFromPkcs12, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
            chrsPasswdKstFromPkcs12,
            
            
            KTLAbs.f_s_strProviderKstBC // provider for keystore of type BKS
            );
    }
    
}