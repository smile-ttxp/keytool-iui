package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import java.awt.event.*;

final public class MIViewKstPkcs12 extends MIViewKstAbs
{    
    // ------
    // PUBLIC
    
    public MIViewKstPkcs12(ActionListener actListenerParent)
    {
        super(
            UtilKstPkcs12.f_s_strKeystoreType,
            actListenerParent
            );
            
        /*if (! com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.s_isPolicyExtended())
        {
            setEnabled(false);
            setVisible(false);
        }*/
    }
}