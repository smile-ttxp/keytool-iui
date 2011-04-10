package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**

    
 **/
 
 import java.awt.event.*;
 
 final public class RBTypeCsrPkcs10 extends RBTypeCsrAbs
 {    
    // ------
    // PUBLIC
    
    public RBTypeCsrPkcs10(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileCsrPkcs10,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCsrPkcs10
            );
    }
 }