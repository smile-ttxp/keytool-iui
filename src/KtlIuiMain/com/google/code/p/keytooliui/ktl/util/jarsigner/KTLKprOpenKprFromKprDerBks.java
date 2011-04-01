package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Kpr" for "private key"
**/




// ----
import java.security.KeyStore;

import java.awt.*;
import java.io.*;

final public class KTLKprOpenKprFromKprDerBks extends KTLKprOpenKprFromKprDerKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprFromKprDerBks(
        Frame frmOwner, 
      
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Bks 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenKpr,
        String strPathAbsFileOpenCrts,
        String strAlgoKey
        )
    {
        super(
            frmOwner, 
          
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenKpr, // private key to import
            strPathAbsFileOpenCrts,
            strAlgoKey,
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
