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

final public class KTLKprOpenCrtInBks extends KTLKprOpenCrtInKPAbs
{
    // ------
    // PUBLIC
    
    

    public KTLKprOpenCrtInBks(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore of type BKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenCrt, // Cert to import
        
        String strFormatFileCert // should be "PKCS#7", or "other"
       
        )
    {
        super(
            frmOwner, 
      
        
            // input
            strPathAbsOpenKst, // existing keystore of type BKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenCrt, // Cert to import
        
            strFormatFileCert, // should be "PKCS#7", or "other"
            
            KTLAbs.f_s_strProviderKstBks
            
            );
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpenKst)
    {
        return UtilKstBks.s_getKeystoreOpen(
            super._frmOwner_,
            fleOpenKst,
            super._chrsPasswdKst_);
    
    }
}