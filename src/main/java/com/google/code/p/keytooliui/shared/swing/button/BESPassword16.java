package com.google.code.p.keytooliui.shared.swing.button;

import com.google.code.p.keytooliui.shared.lang.*;


public final class BESPassword16 extends BESPasswordAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strImage = "mask16.gif";
    
    // ------
    // PUBLIC
        
    public BESPassword16(
        java.awt.event.ActionListener alr,
        int intMode // eg: open, save, change
        )
    {
        super(alr, BESPassword16._f_s_strImage, intMode, 16);           
    }
}