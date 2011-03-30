package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromUberToKPJks ("KP" for "Key with Password")
    . KTLKprSaveFromUberToKPJceks
    . KTLKprSaveFromUberToKPBks
    . KTLKprSaveFromUberToKPUber
     
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
// --
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;


abstract public class KTLKprSaveFromUberToKPAbs extends KTLKprSaveFromUberToAbs
{
    // ---------
    // PROTECTED
    
    protected KTLKprSaveFromUberToKPAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type UBER 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
            strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
            chrsPasswdOpenKstTarget,
            strPathAbsKstSource, // keystore of type UBER 
            chrsPasswdKstSource,
            strProviderKstTarget,
            true // blnIsPasswdKprTarget
            );
            
    }
    
    // -------
    // PRIVATE
    
}