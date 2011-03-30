package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    


**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// ----
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Key;
import java.security.KeyStoreException;
// --
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

final public class KTLKprOpenCrtInJceks extends KTLKprOpenCrtInKPAbs
{
    // ------
    // PUBLIC
    
    

    public KTLKprOpenCrtInJceks(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JCEKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenCrt, // Cert to import
        
        String strFormatFileCert // should be "PKCS#7", or "other"
       
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
        
            // input
            strPathAbsOpenKst, // existing keystore of type JCEKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenCrt, // Cert to import
        
            strFormatFileCert, // should be "PKCS#7", or "other"
            
            KTLAbs.f_s_strProviderKstJceks
            
            );
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpenKst)
    {
        return UtilKstJceks.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKst,
            super._chrsPasswdKst_);
    
    }
}