package com.google.code.p.keytooliui.ktl.util.jarsigner;

/*
 * "Cms": "Cryptographic Message Syntax"
 */

import java.awt.Frame;

public abstract class CmsAbs extends Object
{
    // -------------------
    // public static final
    
    //public static final String STR_INSTANCESIGNATURE = "DOM";
     
    // ---------
    // protected
   
    protected Frame _frmOwner_ = null;
    protected String _strPathAbsFileData_ = null;
    protected String _strPathAbsFileSig_ = null;
    
    
    protected CmsAbs(
            Frame frmOwner, 
            String strPathAbsFileData,
            String strPathAbsFileSig)
    {
        this._frmOwner_ = frmOwner;

        this._strPathAbsFileData_ = strPathAbsFileData;
        this._strPathAbsFileSig_ = strPathAbsFileSig;
    }
    
   
}
