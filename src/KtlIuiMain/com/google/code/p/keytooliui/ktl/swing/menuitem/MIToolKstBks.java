package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks;

final public class MIToolKstBks extends MIToolKstAbs
{
    public MIToolKstBks(ActionListener actListenerParent)
    {
        super(UtilKstBks.f_s_strKeystoreType, actListenerParent);

        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
}
