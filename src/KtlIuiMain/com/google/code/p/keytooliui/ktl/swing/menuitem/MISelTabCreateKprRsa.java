package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
**/



import java.awt.event.*;

final public class MISelTabCreateKprRsa extends MISelTabCreateKprAbs
{    
    // ------
    // PUBLIC
    
    public MISelTabCreateKprRsa(
        ActionListener actListenerParent
        )
    {
        super(
            "RSA private key, with vers. #1 cert.", 
            actListenerParent);
    }
}