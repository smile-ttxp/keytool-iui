package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

// --
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

final public class KTLKprOpenKprOutUber extends KTLKprOpenKprOutKPAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprOutUber(
        Frame frmOwner, 
        String strTitleAppli,
        
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
            strTitleAppli,
        
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
            super._strTitleAppli_,
            fleOpen,
            super._chrsPasswdKst_);
    }
}
