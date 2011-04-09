package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    "Sig" for "Signature"
    "Det" for "Detached"
    
    
    known subclasses:
    . KTLKprOpenSigDetOutAbs ==> export digital signature from keypair entry and data (document) file
 *                            sign unjarred file
 *  . KTLKprOpenSigDetOCmsAbs ==> Out Cryptographic Message Syntax

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

public abstract class KTLKprOpenSigDetAbs extends KTLKprOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileData_ = null; // input
    protected String _strPathAbsFileSig_ = null; // output  
    protected String _strPathAbsFileSaveCrt_ = null; // optional
    //protected String _strFormatFileSaveCrt_ = null;
    
    protected KTLKprOpenSigDetAbs(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileData, // input
        String strPathAbsFileSig,  // output (sign) 
        String strPathAbsFileCrt, // output, optional
        
        String strProviderKst//, // eg "SUN"
        //String strFormatFileSaveCrt
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileData_ = strPathAbsFileData;
        this._strPathAbsFileSig_ = strPathAbsFileSig;
        this._strPathAbsFileSaveCrt_ = strPathAbsFileCrt;
        //this._strFormatFileSaveCrt_ = strFormatFileSaveCrt;
    }
}
