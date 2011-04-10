package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Shk" for "shared key"
    
    
    known subclasses:
    . KTLShkOpenIOOutAbs ==> export/import secret key
 *                            to/from binary file

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

abstract public class KTLShkOpenIOAbs extends KTLShkOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileData_ = null; //  
    
    protected KTLShkOpenIOAbs(
        Frame frmOwner, 
      
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,

        String strPathAbsFileData, 
        
        String strProviderKst // eg "SUN"
        )
    {
        super(frmOwner,  strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileData_ = strPathAbsFileData;
    }
}
