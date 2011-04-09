package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber;

public final class MIViewKstUber extends MIViewKstAbs
{
    public MIViewKstUber(ActionListener actListenerParent)
    {
        super(UtilKstUber.f_s_strKeystoreType, actListenerParent);

        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
}
