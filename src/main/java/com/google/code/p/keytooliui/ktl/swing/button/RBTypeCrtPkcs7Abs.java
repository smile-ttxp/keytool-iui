package com.google.code.p.keytooliui.ktl.swing.button;
 

/*
    known subclasses:
    . RBTypeCrtPkcs7 binary (default)
*/
 
import java.awt.event.*;
 
public abstract class RBTypeCrtPkcs7Abs extends RBTypeCrtAbs
{    
    // ---------
    // protected
    
    protected RBTypeCrtPkcs7Abs(
        boolean blnIsEnabled,
        ItemListener itmListenerParent,
        String strFormatFileCrtPkcs7 // binary v/s ascii (default v/s printable)
        )
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            strFormatFileCrtPkcs7,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Pkcs7
            );
    }
 }