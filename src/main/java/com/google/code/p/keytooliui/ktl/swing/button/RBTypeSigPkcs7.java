package com.google.code.p.keytooliui.ktl.swing.button;
 
// default format: binary
 
import java.awt.event.*;
 


public final class RBTypeSigPkcs7 extends RBTypeSigPkcs7Abs
{    
    // ------
    // PUBLIC
    
    public RBTypeSigPkcs7(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileSigPkcs7
            );
    }
 }
