package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    
    
    known subclasses:
    . KTLKprOpenCrtReqAbs ==> export CSR from RSA keypair entry
    . KTLKprOpenCrtInAbs  ==> import CA cert reply to RSA keypair entry
    . KTLKprOpenCrtOutAbs ==> export certificate from keypair entry

**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

// ----
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
// ----


import java.awt.*;

abstract public class KTLKprOpenCrtAbs extends KTLKprOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileIO_ = null;
    
    protected String _strFormatFileIO_ = null;
    
    // if any error, return nil, then calling method should exit immediately!
    static public X509Certificate s_getCertX509FirstInChain(
        KeyStore kstOpen,
        String strAliasKpr
        )
    {
        String strMethod = "s_getCertX509FirstInChain(kstOpen, strAliasKpr)";
        
        // ----
        // . get first X509 cert in cert chain
        // MEMO: tests already done while selecting alias and password, if any error, exiting!
        
        X509Certificate[] crtsX509Ordered = UtilCrtX509.s_getX509CertificateChain(
            kstOpen, 
            strAliasKpr, 
            true // blnOrderChain
            );
            
        if (crtsX509Ordered == null)
        {
            MySystem.s_printOutError(strMethod, "nil crtsX509Ordered");
            return null;
        }
        
        if (crtsX509Ordered.length < 1)
        {
            MySystem.s_printOutError(strMethod, "crtsX509Ordered.length < 1");
            return null;
        }
        
        // ----
        // return the first certificate in chain
        //MySystem.s_printOutTrace(strMethod, "crtsX509Ordered[0].getSigAlgName()=" + crtsX509Ordered[0].getSigAlgName());
        return crtsX509Ordered[0];
    }
    
    protected KTLKprOpenCrtAbs(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileIO, // either CSR to save or Cert to Import
        
        String strProviderKst, // eg "SUN"
        
        String strFormatFileIO // eg, request CSR: PKCS#10, import CA Cert: PKCS#7, import cert: DER-PKCS7 
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileIO_ = strPathAbsFileIO;
        this._strFormatFileIO_ = strFormatFileIO;
    }
}