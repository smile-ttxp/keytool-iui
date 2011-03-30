package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblEntryKprSaveDsa extends DTblEntryKprSaveAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    
    final static private String _f_s_strTitleThisPrefix = "create DSA keypair in ";

    // ------
    // PUBLIC
    
    
    
    public DTblEntryKprSaveDsa(
        Component cmpFrameOwner, 
        String strTitleAppli,
        java.security.KeyStore kseLoaded,
        boolean blnIsPassword
        )
    {
        super(
            cmpFrameOwner, 
            strTitleAppli,
            DTblEntryKprSaveDsa._f_s_strTitleThisPrefix,
            kseLoaded,
            blnIsPassword
            ); 
    }
}