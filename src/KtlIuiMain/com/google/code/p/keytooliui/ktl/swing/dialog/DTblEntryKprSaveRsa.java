package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblEntryKprSaveRsa extends DTblEntryKprSaveAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleThisPrefix = "create RSA keypair in ";
    
    // ------
    // PUBLIC
    
    
    
    public DTblEntryKprSaveRsa(
        Component cmpFrameOwner, 
        String strTitleAppli,
        java.security.KeyStore kseLoaded,
        boolean blnIsPassword
        )
    {
        super(
            cmpFrameOwner, 
            strTitleAppli,
            DTblEntryKprSaveRsa._f_s_strTitleThisPrefix,
            kseLoaded,
            blnIsPassword
            ); 
    }
    
    
}