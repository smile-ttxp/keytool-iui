package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Tcr" for "Trusted certificate" entry
    
    known subclasses:
    . KTLTcrOpenCrtOutAbs ==> export certificate from trusted certificate entry

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

abstract public class KTLTcrOpenCrtAbs extends KTLTcrOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileIO_ = null;
    
    protected String _strFormatFileIO_ = null;  
    
    protected KTLTcrOpenCrtAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileIO, // cert to export (save)
        
        String strProviderKst, // eg "SUN"
        
        String strFormatFileIO // eg,  cert: DER-PKCS#7-PEM 
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileIO_ = strPathAbsFileIO;
        this._strFormatFileIO_ = strFormatFileIO;
    }
}