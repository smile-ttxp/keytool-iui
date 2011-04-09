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

public final class KTLKprOpenKprOutJceks extends KTLKprOpenKprOutKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprOutJceks(
        Frame frmOwner, 
  
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JCEKS 
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
            strPathAbsOpenKst, // existing keystore of type JCEKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveKpr, // private key to save
            strPathAbsFileSaveCrts,
            strFormatFileKpr, 
            strFormatFileCrts,
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
