package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Shk" for "shared key"
**/




// ----
import java.security.KeyStore;

import java.awt.*;
import java.io.*;

public final class KTLShkOpenIOInJceks extends KTLShkOpenIOInKPAbs
{
    // ------
    // PUBLIC

    public KTLShkOpenIOInJceks(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenIO,
        String strAlgoSignature
        )
    {
        super(
            frmOwner, 
        
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenIO, // signature to save
            strAlgoSignature,
            KTLAbs.f_s_strProviderKstJceks
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

