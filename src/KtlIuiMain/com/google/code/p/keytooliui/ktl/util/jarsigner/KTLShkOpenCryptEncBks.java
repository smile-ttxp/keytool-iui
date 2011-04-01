package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Shk" for "shared key"
**/




// ----
import java.security.KeyStore;

import java.awt.*;
import java.io.*;

final public class KTLShkOpenCryptEncBks extends KTLShkOpenCryptEncKPAbs
{
    // ------
    // PUBLIC

    public KTLShkOpenCryptEncBks(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        String strPathAbsFileOpenData,
        
        String strPathAbsFileSaveCrypt // signature to save
        )
    {
        super(
            frmOwner, 
   
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
            strPathAbsFileOpenData,
        
            strPathAbsFileSaveCrypt, // signature to save
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
