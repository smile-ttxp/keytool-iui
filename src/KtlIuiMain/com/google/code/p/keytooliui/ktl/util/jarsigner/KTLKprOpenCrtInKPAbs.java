package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprOpenCrtInJks
    . KTLKprOpenCrtInJceks

    "Kpr" for "keypair"
    

    "KP" for "Key with password"

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// ----
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Key;
import java.security.KeyStoreException;
// --
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLKprOpenCrtInKPAbs extends KTLKprOpenCrtInAbs
{
    // ---------
    // PROTECTED
    

    protected KTLKprOpenCrtInKPAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenCrt, // Cert to import
        
        String strFormatFileCert, // should be "PKCS#7", or "other"
       
        String strProviderKst // [SUN-SunJCE], assigned by subclasses
        
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
        
            // input
            strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenCrt, // Cert to import
        
            strFormatFileCert, // should be "PKCS#7", or "other"
       
            strProviderKst // [SUN-SunJCE], assigned by subclasses
            );
    }
    
    //protected DTblEntryKprOpenAbs _getDialogSelectKpr_(KeyStore kstOpen)
    protected DTblsKstSelPKAbs _getDialogSelectKpr_(KeyStore kstOpen)
    {
        //return new DTblEntryKprOpenKPAny(super._frmOwner_, super._strTitleAppli_, kstOpen);
        return new DTblsKstSelPKOpen(super._frmOwner_, super._strTitleAppli_, kstOpen,
                super._strPathAbsKst_,
            "Import certificate file as C.A. cert. reply to private key"
                );
    }
}