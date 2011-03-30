package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "KeyPair" entry
    
    "SigEmb" for "Signature" "Embedded"

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

abstract public class KTLKprOpenSigEmbAbs extends KTLKprOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileOpenData_ = null;
    protected String _strPathAbsFileSaveData_ = null;
    
    protected KTLKprOpenSigEmbAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData,
        
        String strProviderKst // eg "SUN"
        
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileOpenData_ = strPathAbsFileOpenData;
        this._strPathAbsFileSaveData_ = strPathAbsFileSaveData;
    }
}
