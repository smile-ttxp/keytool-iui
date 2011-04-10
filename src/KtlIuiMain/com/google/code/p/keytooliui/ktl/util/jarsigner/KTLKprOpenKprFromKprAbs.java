package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"


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

abstract public class KTLKprOpenKprFromKprAbs extends KTLKprOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileKpr_ = null;
    protected String _strPathAbsFileCrts_ = null;
    
    protected String _strFormatFiles_ = null;
    
    protected KTLKprOpenKprFromKprAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,

        String strPathAbsFileKpr, 
        String strPathAbsFileCrts, 
        String strFormatFiles, 
        
        String strProviderKst // eg "SUN"
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileKpr_ = strPathAbsFileKpr;
        this._strPathAbsFileCrts_ = strPathAbsFileCrts;
        this._strFormatFiles_ = strFormatFiles;
    }
}
