package com.google.code.p.keytooliui.ktl.swing.button;
 

import java.awt.event.*;
 
final public class RBTypeCrtDer extends RBTypeCrtAbs
{    
    // ------
    // PUBLIC
    
    public RBTypeCrtDer(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileCrtDer,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Der
            );
    }
}