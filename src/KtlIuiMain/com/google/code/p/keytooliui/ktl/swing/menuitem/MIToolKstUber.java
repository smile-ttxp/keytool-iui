package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber;

final public class MIToolKstUber extends MIToolKstAbs
{
    public MIToolKstUber(ActionListener actListenerParent)
    {
        super(UtilKstUber.f_s_strKeystoreType, actListenerParent);

        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
}
