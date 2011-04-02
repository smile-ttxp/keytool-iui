package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Crt" for "Certificate"
    
    
    known subclasses:
    . KTLCrtOpenSigVerAbs ==> verify signed (unjarred) document with certificate in keystore and  digital signature file
 *                            

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

abstract public class KTLCrtOpenSigAbs extends KTLCrtOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileData_ = null;
    protected String _strPathAbsFileSig_ = null;
    protected String _strFormatFileSig_ = null;
    
    protected KTLCrtOpenSigAbs(
        Frame frmOwner, 
 
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileData, // input
        String strPathAbsFileSig,  // input (verify) 
        
        String strProviderKst, // eg "SUN"
        String strFormatFileSig // 
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileData_ = strPathAbsFileData;
        this._strPathAbsFileSig_ = strPathAbsFileSig;
        this._strFormatFileSig_ = strFormatFileSig;
    }
}
