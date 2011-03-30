package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import java.awt.event.*;

final public class MIToolKstBks extends MIToolKstAbs
{    
    // ------
    // PUBLIC
    
    public MIToolKstBks(ActionListener actListenerParent)
    {
        super(
            UtilKstBks.f_s_strKeystoreType,
            actListenerParent
            );
            
        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
}