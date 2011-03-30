
package com.google.code.p.keytooliui.ktl.swing.button;
 

 
import java.awt.event.*;
 
final public class RBTypeSigPem extends RBTypeSigAbs
{    
    // ------
    // PUBLIC
    
    public RBTypeSigPem(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileSigPem,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsSigX509Pem
            );
    }
}