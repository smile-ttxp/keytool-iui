package com.google.code.p.keytooliui.ktl.swing.button;
 
// default format: binary
 
import java.awt.event.*;
 


public final class RBTypeCrtPkcs7 extends RBTypeCrtPkcs7Abs
{    
    // ------
    // PUBLIC
    
    public RBTypeCrtPkcs7(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileCrtPkcs7
            );
    }
 }