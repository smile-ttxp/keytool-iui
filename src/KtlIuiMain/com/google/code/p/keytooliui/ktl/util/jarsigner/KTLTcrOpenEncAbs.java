package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Tcr" for "Trusted certificate" entry
    
    known subclasses:
    . KTLTcrOpenCrtRsaAbs

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

abstract public class KTLTcrOpenEncAbs extends KTLTcrOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileOpenData_ = null;
    protected String _strPathAbsFileSaveData_ = null;
    
    protected KTLTcrOpenEncAbs(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData,
        
        String strProviderKst // eg "SUN"
        
        )
    {
        super(frmOwner,  strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileOpenData_ = strPathAbsFileOpenData;
        this._strPathAbsFileSaveData_ = strPathAbsFileSaveData;
    }
}
