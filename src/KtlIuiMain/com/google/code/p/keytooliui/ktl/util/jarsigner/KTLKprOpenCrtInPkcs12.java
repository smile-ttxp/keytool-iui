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

final public class KTLKprOpenCrtInPkcs12 extends KTLKprOpenCrtInAbs
{
    // ------
    // PUBLIC
    
    

    public KTLKprOpenCrtInPkcs12(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type PKCS12 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenCrt, // Cert to import
        
        String strFormatFileCert // should be "PKCS#7", or "other"
       
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
        
            // input
            strPathAbsOpenKst, // existing keystore of type PKCS12 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenCrt, // Cert to import
        
            strFormatFileCert, // should be "PKCS#7", or "other"
            
            KTLAbs.f_s_strProviderKstPkcs12
            
            );
    }
    
    // ---------
    // protected
    
    
    protected DTblsKstSelPKAbs _getDialogSelectKpr_(KeyStore kstOpen)
    {
        //return new DTblEntryKprOpenPkcs12Any(super._frmOwner_, super._strTitleAppli_, kstOpen);
        return new DTblsKstSelPKOpenNoPass(super._frmOwner_, super._strTitleAppli_, kstOpen,
                super._strPathAbsKst_,
            "Import certificate file as C.A. cert. reply to private key"
                );
    }
    
    protected KeyStore _getKeystoreOpen_(File fleOpenKst)
    {
        return UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKst,
            super._chrsPasswdKst_);
    }
}