package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.event.MouseListener;
import javax.swing.event.ListSelectionListener;

/**
    known subclasses:
    . PTblEntSKShowAll
    . PTblEntSKSelAbs: pending

**/




abstract public class PTblEntSKAbs extends PTblEntAbs
{   
    // ---------
    // PROTECTED
    
    protected PTblEntSKAbs(
        int intW,
        ListSelectionListener lsnListenerParent, 
        MouseListener mouListenerParent)
    {
        super(intW, lsnListenerParent, mouListenerParent);   
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(this, "Secret Key (shared key) Entries:");
    }
 
}

