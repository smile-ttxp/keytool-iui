 package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**

    
 **/
 
 import java.awt.event.*;
 
 public final class RBTypeKstPkcs12 extends RBTypeKstAbs
 {   
    // ------
    // PUBLIC
    
    public RBTypeKstPkcs12(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsKstPkcs12
            );
            
        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
 }