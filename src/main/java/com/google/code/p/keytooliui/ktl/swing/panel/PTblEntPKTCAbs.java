package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.event.MouseListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.TitledBorder;

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
        setBorder(new TitledBorder("Private Key (keypair) & Trusted Certificate Entries"));
    }
 
}
