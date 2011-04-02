package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**

    
 **/
 
 import java.awt.event.*;
 
 final public class RBTypeKstUber extends RBTypeKstAbs
 {   
    // ------
    // PUBLIC
    
    public RBTypeKstUber(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstUber
            );
       
        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
 }