package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Kpr" for "keypair"
**/




// ----
import java.security.KeyStore;

// --
// ----

import java.awt.*;
import java.io.*;

public final class KTLKprOpenSigDetOutBks extends KTLKprOpenSigDetOutKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenSigDetOutBks(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        String strPathAbsFileOpenData,
        
        String strPathAbsFileSaveSig, // signature to save
        String strPathAbsFileSaveCrt, // certificate to save, optional
        String strFormatFileSig, // should be binary
        String strFormatFileSaveCrt // ie DER, PKCS7, PEM
        )
    {
        super(
            frmOwner, 
        
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
            strPathAbsFileOpenData,
        
            strPathAbsFileSaveSig, // signature to save
            strPathAbsFileSaveCrt, // certificate to save, optional
                
            strFormatFileSig, // should be binary
            strFormatFileSaveCrt,
            KTLAbs.f_s_strProviderKstBks
            );
            
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstBks.s_getKeystoreOpen(
            super._frmOwner_, 
        
            fleOpen,
            super._chrsPasswdKst_);
    }
}

