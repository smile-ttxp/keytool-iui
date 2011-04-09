package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Shk" for "shared key"
    
    
    known subclasses:
    . KTLShkOpenCryptEncAbs ==> export digital signature from keypair entry and data (document) file
 *                            sign unjarred file

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

public abstract class KTLShkOpenCryptAbs extends KTLShkOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileDataOpen_ = null; // input
    protected String _strPathAbsFileDataSave_ = null; // output 
    
    protected KTLShkOpenCryptAbs(
        Frame frmOwner, 
 
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileDataOpen, // input
        String strPathAbsFileDataSave,  // output (sign) 
        
        String strProviderKst // eg "SUN"
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileDataOpen_ = strPathAbsFileDataOpen;
        this._strPathAbsFileDataSave_ = strPathAbsFileDataSave;
    }
}
