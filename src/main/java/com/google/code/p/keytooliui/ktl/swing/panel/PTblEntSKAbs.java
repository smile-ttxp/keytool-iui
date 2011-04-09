package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.event.MouseListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.TitledBorder;

/**
    known subclasses:
    . PTblEntSKShowAll
    . PTblEntSKSelAbs: pending

**/




public abstract class PTblEntSKAbs extends PTblEntAbs
{   
    // ---------
    // PROTECTED
    
    protected PTblEntSKAbs(
        int intW,
        ListSelectionListener lsnListenerParent, 
        MouseListener mouListenerParent)
    {
        super(intW, lsnListenerParent, mouListenerParent);   
        setBorder(new TitledBorder("Secret Key (shared key) Entries"));
    }
 
}

