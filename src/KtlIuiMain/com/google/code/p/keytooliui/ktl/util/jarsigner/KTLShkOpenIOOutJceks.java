package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Shk" for "shared key"
**/




// ----
import java.security.KeyStore;

import java.awt.*;
import java.io.*;

final public class KTLShkOpenIOOutJceks extends KTLShkOpenIOOutKPAbs
{
    // ------
    // PUBLIC

    public KTLShkOpenIOOutJceks(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveIO,
        String strFormatFileShk
        )
    {
        super(
            frmOwner, 
  
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveIO,
            strFormatFileShk,
                
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
