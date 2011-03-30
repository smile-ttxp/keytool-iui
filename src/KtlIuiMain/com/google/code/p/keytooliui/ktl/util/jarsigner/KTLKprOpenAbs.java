package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    
    
    known subclasses:
    . KTLKprOpenCrtAbs ==> export CSR, or import CA Cert reply
    . KTLKprOpenManAbs "Man" ==> Manager
    . KTLKprOpenSignAbs  ==> sign jarred file by selecting an entry located in (JKS-PKCS12] keystore
    . KTLKprOpenVerifyAbs
   
**/


import com.google.code.p.keytooliui.shared.lang.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// ----
import java.security.KeyStore;
import java.security.KeyStoreException;
// ----
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
// ----


import java.awt.*;

abstract public class KTLKprOpenAbs extends KTLKprAbs
{
    // ---------
    // PROTECTED

    protected KTLKprOpenAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strProviderKst // should be "SUN", or "SunRsaSign", or "BC"
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
    }
}