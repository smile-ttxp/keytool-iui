package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

public final class MIViewKstJksSysRootCA extends MIKstJksCrtAbs
{
    // --------------
    // PRIVATE STATIC

    public static String s_strPrefix = "System-level root CA";

    // ------
    // PUBLIC

    public MIViewKstJksSysRootCA(ActionListener actListenerParent)
    {
        super(MIViewKstJksSysRootCA.s_strPrefix, actListenerParent);
    }
}
