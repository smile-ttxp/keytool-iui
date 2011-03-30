package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    
    
    known subclasses:
    . KTLKprSaveFromPkcs12ToAbs  

**/

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


import java.awt.*;

abstract public class KTLKprSaveFromAbs extends KTLKprSaveAbs
{
        // ------------------
    // ABSTRACT PROTECTED
    
    // definition in subclasses, called there
    
    abstract protected boolean __createNewEntry__(
        KeyStore kstOpenTarget, PrivateKey pkyPrivateSource, Certificate[] crtsSource);
    
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsKstSource_ = null;
    protected char[] _chrsPasswdKstSource_ = null;
    protected String _strProviderKstSource_ = null;
    protected boolean _blnIsPasswdKprTarget_;
    
    
    protected KTLKprSaveFromAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        String strProviderKstTarget,
        
        String strPathAbsKstSource,
        char[] chrsPasswdKstSource,
        String strProviderKstSource,
        boolean blnIsPasswdKprTarget
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsOpenKstTarget, chrsPasswdOpenKstTarget, strProviderKstTarget);
        
        this._strPathAbsKstSource_ = strPathAbsKstSource;
        this._chrsPasswdKstSource_ = chrsPasswdKstSource;
        this._strProviderKstSource_ = strProviderKstSource;
        this._blnIsPasswdKprTarget_ = blnIsPasswdKprTarget;
    }
    
}