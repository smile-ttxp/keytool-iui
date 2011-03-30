package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.event.MouseListener;
import javax.swing.event.ListSelectionListener;

/**
    known subclasses:
    . PTblEntPKTCShowAll
    . PTblEntPKTCSelAbs: pending

**/




abstract public class PTblEntPKTCAbs extends PTblEntAbs
{   
    // ---------
    // PROTECTED
    
    protected PTblEntPKTCAbs(
        int intW, 
        ListSelectionListener lsnListenerParent,
        MouseListener mouListenerParent)
    {
        super(intW, lsnListenerParent, mouListenerParent);   
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(this, "Private Key (keypair) & Trusted Certificate Entries:");
    }
 
}
