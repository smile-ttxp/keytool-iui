package com.google.code.p.keytooliui.ktl.swing.button;
 

import java.awt.event.*;
 
public final class RBTypeKprPem extends RBTypeKprAbs
{    
    // ------
    // PUBLIC
    
    public RBTypeKprPem(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileKprPem,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKprPem
            );
    }
}

