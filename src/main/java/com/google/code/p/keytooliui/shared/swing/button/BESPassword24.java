package com.google.code.p.keytooliui.shared.swing.button;

import com.google.code.p.keytooliui.shared.lang.*;


public final class BESPassword24 extends BESPasswordAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strImage = "mask24.gif";
    
    // ------
    // PUBLIC
        
    public BESPassword24(
        java.awt.event.ActionListener alr,
        int intMode // eg: open, save, change
        )
    {
        super(alr, BESPassword24._f_s_strImage, intMode, 24);           
    }
}