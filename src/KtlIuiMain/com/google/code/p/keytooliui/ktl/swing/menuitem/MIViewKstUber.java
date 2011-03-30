package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import java.awt.event.*;

final public class MIViewKstUber extends MIViewKstAbs
{    
    // ------
    // PUBLIC
    
    public MIViewKstUber(ActionListener actListenerParent)
    {
        super(
            UtilKstUber.f_s_strKeystoreType,
            actListenerParent
            );
            
        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
}