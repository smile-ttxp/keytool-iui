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

final public class KTLKprOpenSigDetOCmsBks extends KTLKprOpenSigDetOCmsKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenSigDetOCmsBks(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        String strPathAbsFileOpenData,
        
        String strPathAbsFileSaveSig, // signature to save
        String strPathAbsFileSaveCrt, // certificate to save, optional
        boolean blnDataEncapsulated
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
        
            // input
            strPathAbsOpenKst, // existing keystore
            chrsPasswdOpenKst,
            strPathAbsFileOpenData,
        
            strPathAbsFileSaveSig, // signature to save
            strPathAbsFileSaveCrt, // certificate to save, optional

            KTLAbs.f_s_strProviderKstBks,
            blnDataEncapsulated
            );
            
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstBks.s_getKeystoreOpen(
            super._frmOwner_, 
            super._strTitleAppli_,
            fleOpen,
            super._chrsPasswdKst_);
    }
}
