package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromJksToKPJks ("KP" for "Key with Password")
    . KTLKprSaveFromJksToKPJceks
    . KTLKprSaveFromJksToKPBks
    . KTLKprSaveFromJksToKPUber
     
    
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


abstract public class KTLKprSaveFromJksToKPAbs extends KTLKprSaveFromJksToAbs
{
    // ---------
    // PROTECTED
    
    
    
    
    protected KTLKprSaveFromJksToKPAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type JKS 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
            strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
            chrsPasswdOpenKstTarget,
            strPathAbsKstSource, // keystore of type JKS 
            chrsPasswdKstSource,
            strProviderKstTarget,
            true // blnIsPasswdKprTarget
            );
            
    }
    
    // -------
    // PRIVATE
    
}