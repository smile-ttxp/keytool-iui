package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks;

public final class MIToolKstJceks extends MIToolKstAbs
{
    public MIToolKstJceks(ActionListener actListenerParent)
    {
        super(UtilKstJceks.f_s_strKeystoreType, actListenerParent);
    }
}
