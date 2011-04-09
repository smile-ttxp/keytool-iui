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

public final class KTLKprOpenCrtReqUber extends KTLKprOpenCrtReqKPAbs
{
    // ------
    // public
    
  

    public KTLKprOpenCrtReqUber(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore of type UBER 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveCsr, // CSR to save
        
        String strFormatFileCsr // should be PKCS#10
        )
    {
        super(
            frmOwner, 

        
            // input
            strPathAbsOpenKst, // existing keystore of type UBER 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveCsr, // CSR to save
        
            strFormatFileCsr, // should be PKCS#10
            KTLAbs.f_s_strProviderKstUber // 
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