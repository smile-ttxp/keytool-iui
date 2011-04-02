package com.google.code.p.keytooliui.ktl.swing.menuitem;

import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12;

final public class MIToolKstPkcs12 extends MIToolKstAbs
{
    public MIToolKstPkcs12(ActionListener actListenerParent)
    {
        super(UtilKstPkcs12.f_s_strKeystoreType, actListenerParent);

        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
}
