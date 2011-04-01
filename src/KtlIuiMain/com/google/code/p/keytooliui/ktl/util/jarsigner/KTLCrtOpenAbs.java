package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Crt" for "Certificate"
    
    
    known subclasses:
    . ??
   
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

abstract public class KTLCrtOpenAbs extends KTLCrtAbs
{
    // ---------
    // PROTECTED

    protected KTLCrtOpenAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strProviderKst // should be "SUN", or "SunRsaSign", or "BC"
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
    }
}
