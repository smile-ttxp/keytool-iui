package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "KeyPair" (entry)

     encrypting file with RSA public key of private key entry
**/




// ----
import java.security.KeyStore;
// --
// ----

import java.awt.*;
import java.io.*;

final public class KTLKprOpenEncRsaJceks extends KTLKprOpenEncRsaDMAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenEncRsaJceks(
        Frame frmOwner, 
       
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JCEKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData,
        String strInstanceCipherAlgo
        )
    {
        super(
            frmOwner, 
  
        
            // input
            strPathAbsOpenKst, // existing keystore of type JCEKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenData,
            strPathAbsFileSaveData,
         
            KTLAbs.f_s_strProviderKstJceks,
            strInstanceCipherAlgo
            );
            
    }
    
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstJceks.s_getKeystoreOpen(
            super._frmOwner_, 
           
            fleOpen,
            super._chrsPasswdKst_);
    }
}
