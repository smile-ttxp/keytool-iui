package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "KeyPair" entry
    


**/



// ----
// ----


import java.awt.*;

public abstract class KTLKprOpenEncAbs extends KTLKprOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileOpenData_ = null;
    protected String _strPathAbsFileSaveData_ = null;
    
    protected KTLKprOpenEncAbs(
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
