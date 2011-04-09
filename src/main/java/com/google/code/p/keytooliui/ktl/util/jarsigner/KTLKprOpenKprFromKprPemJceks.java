package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Kpr" for "private key"
**/




// ----
import java.security.KeyStore;

import java.awt.*;
import java.io.*;

public final class KTLKprOpenKprFromKprPemJceks extends KTLKprOpenKprFromKprPemKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprFromKprPemJceks(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JceKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenKpr,
        String strPathAbsFileOpenCrts//,
        //char[] chrsPasswdsFileOpenKpr
        )
    {
        super(
            frmOwner, 
   
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenKpr, // private key to import
            strPathAbsFileOpenCrts,
            //chrsPasswdsFileOpenKpr,
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
