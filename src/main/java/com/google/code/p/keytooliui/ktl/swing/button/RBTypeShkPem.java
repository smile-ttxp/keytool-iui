package com.google.code.p.keytooliui.ktl.swing.button;
 

import java.awt.event.*;
 
public final class RBTypeShkPem extends RBTypeShkAbs
{    
    // ------
    // PUBLIC
    
    public RBTypeShkPem(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileShkPem,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsShkPem
            );
    }
}


