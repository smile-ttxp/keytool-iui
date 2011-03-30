package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Crt" for "Certificate"
    
    
    known subclasses:
    . KTLCrtOpenAbs  

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

abstract public class KTLCrtAbs extends KTLAbs
{
    // ---------
    // PROTECTED
 
    protected Boolean[] _getBoosTypeCertX509_(KeyStore kstOpen, String[] strsAlias)
    {
        String strMethod = "_getBoosTypeCertX509_(kstOpen, strsAlias)";
        
        if (kstOpen==null || strsAlias==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosTypeCertX509 = new Boolean[strsAlias.length];
        
        for (int i=0; i<strsAlias.length; i++)
        {
            Certificate crt = null;
            
            try
            {
                crt = kstOpen.getCertificate(strsAlias[i]);
            }
            
            catch(KeyStoreException excKeyStore) // memo: if the keystore has not been initialized (loaded).
            {
                MySystem.s_printOutError(this, strMethod, "Got keystore Exception");
                
                String strBody = "Got keystore Exception.";
                
                OPAbstract.s_showDialogWarning(
                    super._frmOwner_, super._strTitleAppli_, strBody);
                
                return null;
            }
            
            boolean blnOk = true;
            
            if (crt == null)
                blnOk = false;
            else if (! (crt instanceof X509Certificate)) 
                blnOk = false;
                 
            boosTypeCertX509[i] = new Boolean(blnOk);
        }

        // ----
        return boosTypeCertX509;
    }

    protected KTLCrtAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsKst, // existing keystore of type [JKS-JCEKS-PKCS12]
        char[] chrsPasswdKst,
        
        String strProviderKst
        
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsKst, chrsPasswdKst, strProviderKst);        
    }

}