package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Kpr" for "keypair"
 *  "SigDet" for "Signature" "Detached"
**/




// ----
import java.security.KeyStore;

// --
// ----

import java.awt.*;
import java.io.*;

final public class KTLKprOpenSigDetOutUber extends KTLKprOpenSigDetOutKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenSigDetOutUber(
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


