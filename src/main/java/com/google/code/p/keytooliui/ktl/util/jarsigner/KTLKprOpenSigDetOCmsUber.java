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

public final class KTLKprOpenSigDetOCmsUber extends KTLKprOpenSigDetOCmsKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenSigDetOCmsUber(
        Frame frmOwner, 
    
        
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
            
        
            // input
            strPathAbsOpenKst, // existing keystore
            chrsPasswdOpenKst,
            strPathAbsFileOpenData,
        
            strPathAbsFileSaveSig, // signature to save
            strPathAbsFileSaveCrt, // certificate to save, optional

            KTLAbs.f_s_strProviderKstUber,
            blnDataEncapsulated
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
