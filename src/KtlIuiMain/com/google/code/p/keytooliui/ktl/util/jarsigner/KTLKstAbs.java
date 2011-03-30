package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kst" for "Keystore"
    
    
    known subclasses:
    . KTLKstSaveAbs
    . KTLKstOpenAbs  

**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// ----
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.KeyStoreException;
import java.security.Provider;
// --
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.util.*;

abstract public class KTLKstAbs extends KTLAbs
{
   
    
    // ---------
    // PROTECTED

    protected KTLKstAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbs, // existing keystore
        char[] chrsPasswd,
        
        String strProvider
        
        )
    {
        super(frmOwner, strTitleAppli, strPathAbs, chrsPasswd, strProvider);
    }

}