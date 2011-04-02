package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks;

final public class MIViewKstJceks extends MIViewKstAbs
{
    public MIViewKstJceks(ActionListener actListenerParent)
    {
        super(UtilKstJceks.f_s_strKeystoreType, actListenerParent);
    }
}
