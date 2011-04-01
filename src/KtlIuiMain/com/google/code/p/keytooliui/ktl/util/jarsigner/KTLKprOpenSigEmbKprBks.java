package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "KeyPair" (entry)

     sign XML file with (right now) SHA1withRSA or SHA1withDSA private key entry
**/




// ----
import java.security.KeyStore;
// --
// ----

import java.awt.*;
import java.io.*;

final public class KTLKprOpenSigEmbKprBks extends KTLKprOpenSigEmbKprDMAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenSigEmbKprBks(
        Frame frmOwner, 
      
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Bks 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData
        )
    {
        super(
            frmOwner, 
      
        
            // input
            strPathAbsOpenKst, // existing keystore of type Bks 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenData,
            strPathAbsFileSaveData,
         
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
