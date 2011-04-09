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

public final class KTLKprOpenSigEmbKprUber extends KTLKprOpenSigEmbKprDMAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenSigEmbKprUber(
        Frame frmOwner, 
    
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Uber 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData
        )
    {
        super(
            frmOwner, 
      
        
            // input
            strPathAbsOpenKst, // existing keystore of type Uber 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenData,
            strPathAbsFileSaveData,
         
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
