package com.google.code.p.keytooliui.ktl.util.jarsigner;

import java.awt.Frame;

public abstract class XmlAbs extends Object
{
    // -------------------
    // public static final
    
    public static final String STR_INSTANCESIGNATURE = "DOM";
     
    // ---------
    // protected
   
    protected Frame _frmOwner_ = null;
    protected String _strPathAbsFile_ = null;
    
    protected XmlAbs(
            Frame frmOwner, 
            String strPathAbsFile)
    {
        this._frmOwner_ = frmOwner;
        this._strPathAbsFile_ = strPathAbsFile;
    }
    
   
}
