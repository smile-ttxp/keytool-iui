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

final public class KTLKprOpenKprOutJks extends KTLKprOpenKprOutKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprOutJks(
        Frame frmOwner, 
 
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Jks 
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
            strPathAbsOpenKst, // existing keystore of type Jks 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveKpr, // private key to save
            strPathAbsFileSaveCrts,
            strFormatFileKpr, 
            strFormatFileCrts,
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
