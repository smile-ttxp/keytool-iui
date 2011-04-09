package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblEntryKprOpenKPAny extends DTblEntryKprOpenKPAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThisPrefix = "select (RSA or DSA) keypair entry in ";
    
    // ------
    // PUBLIC
    
    
    public DTblEntryKprOpenKPAny(
        Component cmpFrameOwner, 
     
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
     
            DTblEntryKprOpenKPAny._f_s_strTitleThisPrefix,
            kseLoaded
            ); 
    }
}