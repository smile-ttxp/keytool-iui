package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/



import java.awt.event.*;

final public class MISelTabCreateKprDsa extends MISelTabCreateKprAbs
{    
    // ------
    // PUBLIC
    
    public MISelTabCreateKprDsa(
        ActionListener actListenerParent
        )
    {
        super(
            "DSA private key, with vers. #1 cert.", 
            actListenerParent);
    }
}