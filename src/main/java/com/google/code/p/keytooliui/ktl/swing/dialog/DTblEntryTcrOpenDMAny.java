package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblEntryTcrOpenDMAny extends DTblEntryTcrOpenDMAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThisPrefix = "select trusted certificate entry in ";
    
    // ------
    // PUBLIC
    
    
    public DTblEntryTcrOpenDMAny(
        Component cmpFrameOwner, 
 
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
      
            DTblEntryTcrOpenDMAny._f_s_strTitleThisPrefix,
            kseLoaded
            ); 
    }
}