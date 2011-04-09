package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    

**/




// ----
import java.security.KeyStore;

// --
// ----

import java.awt.*;
import java.io.*;

public final class KTLKprOpenKprOutUber extends KTLKprOpenKprOutKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprOutUber(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Uber 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveKpr, // private key to save
        String strPathAbsFileSaveCrts,
        String strFormatFileKpr,
        String strFormatFileCrts
        )
    {
        super(
            frmOwner, 
   
        
            // input
            strPathAbsOpenKst, // existing keystore of type Uber 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveKpr, // private key to save
            strPathAbsFileSaveCrts,
            strFormatFileKpr, 
            strFormatFileCrts,
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
