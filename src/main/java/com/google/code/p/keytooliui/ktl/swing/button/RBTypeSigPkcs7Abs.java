package com.google.code.p.keytooliui.ktl.swing.button;
 

/*
    known subclasses:
    . RBTypeSigPkcs7 binary (default)
*/
 
import java.awt.event.*;
 
public abstract class RBTypeSigPkcs7Abs extends RBTypeSigAbs
{    
    // ---------
    // protected
    
    protected RBTypeSigPkcs7Abs(
        boolean blnIsEnabled,
        ItemListener itmListenerParent,
        String strFormatFileSigPkcs7 // binary v/s ascii (default v/s printable)
        )
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            strFormatFileSigPkcs7,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsSigX509Pkcs7
            );
    }
 }
