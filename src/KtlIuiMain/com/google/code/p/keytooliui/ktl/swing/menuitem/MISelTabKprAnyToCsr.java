package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**

**/



import com.google.code.p.keytooliui.shared.lang.*;


import java.awt.event.*;

final public class MISelTabKprAnyToCsr extends MISelTabAbs
{    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        /*javax.swing.ImageIcon iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(
            com.google.code.p.keytooliui.ktl.swing.tabbedpane.TPMainUIKtl.f_s_strIconExport);
            
        if (iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil iin");
            return false;
        }
        
        setIcon(iin);
            */
        // --
        return true;
    }
    
    public MISelTabKprAnyToCsr(
        ActionListener actListenerParent
        )
    {
        super(
            "CSR from private key entry", 
            actListenerParent);
    }
}