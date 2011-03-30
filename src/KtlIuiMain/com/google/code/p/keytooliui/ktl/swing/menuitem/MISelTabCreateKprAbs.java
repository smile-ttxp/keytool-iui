package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/


import com.google.code.p.keytooliui.ktl.lang.bool.BOEntryPKTC;

import java.awt.event.*;
import com.google.code.p.keytooliui.shared.lang.MySystem;

abstract public class MISelTabCreateKprAbs extends MISelTabAbs
{    
    public boolean init()
    {
        String strMethod = "init()";

        if (! super.init())
            return false;
        
        javax.swing.ImageIcon iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(
            BOEntryPKTC.STR_IMAGEPK);
            
        if (iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil iin");
            return false;
        }
        
        setIcon(iin);
        
        // --
        return true;
    }
    
    
    protected MISelTabCreateKprAbs(
        String strText,
        ActionListener actListenerParent
        )
    {
        super(
            strText, 
            actListenerParent);
    }
}
