package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;
import java.io.File;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks;
import com.google.code.p.keytooliui.shared.io.S_OperatingSystem;

public final class MIViewKstJksUsrTrusSig extends MIKstJksCrtAbs
{
    // --------------
    // PUBLIC STATIC

    public static String s_strPrefix = "User-level trusted signer";

    // ------
    // PUBLIC

    public MIViewKstJksUsrTrusSig(ActionListener actListenerParent)
    {
        super(MIViewKstJksUsrTrusSig.s_strPrefix, actListenerParent);

        // ----
        // the following: only for windows or unix or linux

        if (!(S_OperatingSystem.s_isUnix() || S_OperatingSystem.s_isWindows()))
        {
            setEnabled(false);
            setVisible(false);
            return;
        }

        File fleCertsTrustUsr = UtilKstJks.s_getFileKstCertsTrustUsr();

        if (fleCertsTrustUsr == null)
        {
            setEnabled(false);
            setVisible(false);
            return;
        }

        if (!fleCertsTrustUsr.exists())
        {
            setEnabled(false);
            setVisible(false);
            return;
        }

        // ending
    }
}
