package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblEntryKprOpenKPRsa extends DTblEntryKprOpenKPAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThisPrefix = "select RSA keypair entry in ";
    
    // ------
    // PUBLIC

    public DTblEntryKprOpenKPRsa(
        Component cmpFrameOwner, 
      
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
     
            DTblEntryKprOpenKPRsa._f_s_strTitleThisPrefix,
            kseLoaded
            ); 
    }
}