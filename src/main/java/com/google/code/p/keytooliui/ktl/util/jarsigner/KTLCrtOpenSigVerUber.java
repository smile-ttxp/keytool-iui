package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Crt" for "Certificate"
    "Sig" for "Signature"
    

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

public final class KTLCrtOpenSigVerUber extends KTLCrtOpenSigVerANAbs
{
    // ------
    // PUBLIC

    public KTLCrtOpenSigVerUber(
        Frame frmOwner, 
  
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Uber 
        char[] chrsPasswdOpenKst,
        String strPathAbsFileOpenData,
        
        String strPathAbsFileOpenSig, // digital signature
        String strFormatFileSig 
        )
    {
        super(
            frmOwner, 
   
        
            // input
            strPathAbsOpenKst, // existing keystore of type Uber 
            chrsPasswdOpenKst,
            strPathAbsFileOpenData,
        
            strPathAbsFileOpenSig, // digital signature
            strFormatFileSig,
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

