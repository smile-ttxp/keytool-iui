package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "KeyPair" entry
    
    "SigEmb" for "Signature" "Embedded"

**/



// ----
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
   
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData,
        
        String strProviderKst // eg "SUN"
        
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileOpenData_ = strPathAbsFileOpenData;
        this._strPathAbsFileSaveData_ = strPathAbsFileSaveData;
    }
}
