package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**

    
 **/
 
 import java.awt.event.*;
 
 final public class RBTypeKstBks extends RBTypeKstAbs
 {   
    // ------
    // PUBLIC
    
    public RBTypeKstBks(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstBks
            );
            
        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
 }