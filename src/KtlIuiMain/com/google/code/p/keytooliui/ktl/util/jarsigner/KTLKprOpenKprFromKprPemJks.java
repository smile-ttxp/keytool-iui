package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Kpr" for "private key"
**/




// ----
import java.security.KeyStore;

import java.awt.*;
import java.io.*;

final public class KTLKprOpenKprFromKprPemJks extends KTLKprOpenKprFromKprPemKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprFromKprPemJks(
        Frame frmOwner, 
    
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Jks 
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
            KTLAbs.f_s_strProviderKstJks
            );
            
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstJks.s_getKeystoreOpen(
            super._frmOwner_, 
         
            fleOpen,
            super._chrsPasswdKst_);
    }
}

