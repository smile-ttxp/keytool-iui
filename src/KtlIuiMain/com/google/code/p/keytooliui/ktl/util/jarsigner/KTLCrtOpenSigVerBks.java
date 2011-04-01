
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Crt" for "Certificate"
    "Sig" for "Signature"
    

**/




// ----
import java.security.KeyStore;

// --
// ----

import java.awt.*;
import java.io.*;

final public class KTLCrtOpenSigVerBks extends KTLCrtOpenSigVerANAbs
{
    // ------
    // PUBLIC

    public KTLCrtOpenSigVerBks(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore of type Bks 
        char[] chrsPasswdOpenKst,
        String strPathAbsFileOpenData,
        
        String strPathAbsFileOpenSig, // digital signature
        String strFormatFileSig 
        )
    {
        super(
            frmOwner, 

        
            // input
            strPathAbsOpenKst, // existing keystore of type Bks 
            chrsPasswdOpenKst,
            strPathAbsFileOpenData,
        
            strPathAbsFileOpenSig, // digital signature
            strFormatFileSig,
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
