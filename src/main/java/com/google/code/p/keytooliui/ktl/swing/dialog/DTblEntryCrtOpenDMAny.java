package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblEntryCrtOpenDMAny extends DTblEntryCrtOpenDMAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThisPrefix = "select trusted certificate entry in ";
    
    // ------
    // PUBLIC
    
    
    public DTblEntryCrtOpenDMAny(
        Component cmpFrameOwner, 
    
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
       
            DTblEntryCrtOpenDMAny._f_s_strTitleThisPrefix,
            kseLoaded
            ); 
    }
}
