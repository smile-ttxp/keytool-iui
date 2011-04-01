package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.ktl.lang.bool.BOEntrySK;
import com.google.code.p.keytooliui.shared.lang.MySystem;

final public class MISelTabCreateShkAll extends MISelTabAbs
{
    public boolean init()
    {
        String strMethod = "init()";

        if (!super.init())
        {
            return false;
        }

        javax.swing.ImageIcon iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(
                BOEntrySK.STR_IMAGE);

        if (iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil iin");
            return false;
        }

        setIcon(iin);

        // --
        return true;
    }

    public MISelTabCreateShkAll(ActionListener actListenerParent)
    {
        super("Secret key", actListenerParent);
    }
}
