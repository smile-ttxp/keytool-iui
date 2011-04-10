package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    


**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;



// ----
import java.security.KeyStore;
// --
// ----

import java.awt.*;
import java.io.*;

final public class KTLKprOpenCrtInPkcs12 extends KTLKprOpenCrtInAbs
{
    // ------
    // PUBLIC
    
    

    public KTLKprOpenCrtInPkcs12(
        Frame frmOwner, 
  
        
        // input
        String strPathAbsOpenKst, // existing keystore of type PKCS12 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenCrt, // Cert to import
        
        String strFormatFileCert // should be "PKCS#7", or "other"
       
        )
    {
        super(
            frmOwner, 
       
        
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

        return new DTblsKstSelPKOpenNoPass(super._frmOwner_, kstOpen,
                super._strPathAbsKst_,
            "Import certificate file as C.A. cert. reply to private key"
                );
    }
    
    protected KeyStore _getKeystoreOpen_(File fleOpenKst)
    {
        return UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_,
            fleOpenKst,
            super._chrsPasswdKst_);
    }
}