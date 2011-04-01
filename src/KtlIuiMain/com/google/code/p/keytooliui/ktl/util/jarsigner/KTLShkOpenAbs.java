package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Shk" for "Shared Key" (Secret Key)
    
    
    known subclasses:
    . KTLShkOpenManAbs "Man" ==> Manager
   
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

abstract public class KTLShkOpenAbs extends KTLShkAbs
{
    // ---------
    // PROTECTED

    protected KTLShkOpenAbs(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strProviderKst // should be "?SunJCE?", or "SunRsaSign", or "BC"
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
    }
}
