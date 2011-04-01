package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

// --
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

final public class KTLKprOpenCrtOutUber extends KTLKprOpenCrtOutKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenCrtOutUber(
        Frame frmOwner, 
        
        
        // input
        String strPathAbsOpenKst, // existing keystore of type UBER 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveCrt, // certificate to save
        String strFormatFileCrt // 
        )
    {
        super(
            frmOwner, 

        
            // input
            strPathAbsOpenKst, // existing keystore of type UBER 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveCrt, // certificate to save
            strFormatFileCrt, // should be PKCS#7
            KTLAbs.f_s_strProviderKstUber
            );
            
    }
    
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstUber.s_getKeystoreOpen(
            super._frmOwner_, 
  
            fleOpen,
            super._chrsPasswdKst_);
    }
}