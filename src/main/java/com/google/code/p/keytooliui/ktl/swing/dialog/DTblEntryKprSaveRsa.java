package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblEntryKprSaveRsa extends DTblEntryKprSaveAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThisPrefix = "create RSA keypair in ";
    
    // ------
    // PUBLIC
    
    
    
    public DTblEntryKprSaveRsa(
        Component cmpFrameOwner, 
    
        java.security.KeyStore kseLoaded,
        boolean blnIsPassword
        )
    {
        super(
            cmpFrameOwner, 
      
            DTblEntryKprSaveRsa._f_s_strTitleThisPrefix,
            kseLoaded,
            blnIsPassword
            ); 
    }
    
    
}