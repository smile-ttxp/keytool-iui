package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblEntryKprSaveDsa extends DTblEntryKprSaveAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    
    private static final String _f_s_strTitleThisPrefix = "create DSA keypair in ";

    // ------
    // PUBLIC
    
    
    
    public DTblEntryKprSaveDsa(
        Component cmpFrameOwner, 
    
        java.security.KeyStore kseLoaded,
        boolean blnIsPassword
        )
    {
        super(
            cmpFrameOwner, 
    
            DTblEntryKprSaveDsa._f_s_strTitleThisPrefix,
            kseLoaded,
            blnIsPassword
            ); 
    }
}