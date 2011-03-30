package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import java.awt.event.*;

final public class MIToolKstJceks extends MIToolKstAbs
{    
    // ------
    // PUBLIC
    
    public MIToolKstJceks(ActionListener actListenerParent)
    {
        super(
            UtilKstJceks.f_s_strKeystoreType,
            actListenerParent
            );
    }
}