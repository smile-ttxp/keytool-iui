package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Tcr" for "Trusted certifcate" (entry)

**/




// ----
import java.security.KeyStore;
// --
// ----

import java.awt.*;
import java.io.*;

final public class KTLTcrOpenCrtOutBks extends KTLTcrOpenCrtOutDMAbs
{
    // ------
    // PUBLIC

    public KTLTcrOpenCrtOutBks(
        Frame frmOwner, 
 
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Bks 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveCrt, // certificate to save
        String strFormatFileCrt // could be DER-PKCS#7-PEM
        )
    {
        super(
            frmOwner, 
         
        
            // input
            strPathAbsOpenKst, // existing keystore of type Bks 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveCrt, // certificate to save
            strFormatFileCrt, 
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