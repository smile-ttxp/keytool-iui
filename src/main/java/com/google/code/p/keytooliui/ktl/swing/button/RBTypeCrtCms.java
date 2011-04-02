package com.google.code.p.keytooliui.ktl.swing.button;
 

import java.awt.event.*;
 
final public class RBTypeCrtCms extends RBTypeCrtAbs
{    
    // ------
    // PUBLIC
    
    public RBTypeCrtCms(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileCrtCms,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtCms
            );
    }
}
