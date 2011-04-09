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

public final class KTLKprOpenCrtReqJks extends KTLKprOpenCrtReqKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenCrtReqJks(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveCsr, // CSR to save
        String strFormatFileCsr // should be PKCS#10
        )
    {
        super(
            frmOwner, 
 
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveCsr, // CSR to save
            strFormatFileCsr, // should be PKCS#10
            KTLAbs.f_s_strProviderKstJks
            );
            
    }
    
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstJks.s_getKeystoreOpen(
            super._frmOwner_, 

            fleOpen,
            super._chrsPasswdKst_);
    }
}