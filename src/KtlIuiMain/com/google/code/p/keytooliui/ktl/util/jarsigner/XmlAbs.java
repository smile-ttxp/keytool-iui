package com.google.code.p.keytooliui.ktl.util.jarsigner;

import java.awt.Frame;

abstract public class XmlAbs extends Object
{
    // -------------------
    // final static public
    
    final static public String STR_INSTANCESIGNATURE = "DOM";
     
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
