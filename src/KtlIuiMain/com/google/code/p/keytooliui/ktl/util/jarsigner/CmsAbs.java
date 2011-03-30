package com.google.code.p.keytooliui.ktl.util.jarsigner;

/*
 * "Cms": "Cryptographic Message Syntax"
 */

import java.awt.Frame;

abstract public class CmsAbs extends Object
{
    // -------------------
    // final static public
    
    //final static public String STR_INSTANCESIGNATURE = "DOM";
     
    // ---------
    // protected
   
    protected Frame _frmOwner_ = null;
    protected String _strTitleAppli_ = null;
    protected String _strPathAbsFileData_ = null;
    protected String _strPathAbsFileSig_ = null;
    
    
    protected CmsAbs(
            Frame frmOwner, 
            String strTitleAppli,
            String strPathAbsFileData,
            String strPathAbsFileSig)
    {
        this._frmOwner_ = frmOwner;
        this._strTitleAppli_ = strTitleAppli;
        this._strPathAbsFileData_ = strPathAbsFileData;
        this._strPathAbsFileSig_ = strPathAbsFileSig;
    }
    
   
}
