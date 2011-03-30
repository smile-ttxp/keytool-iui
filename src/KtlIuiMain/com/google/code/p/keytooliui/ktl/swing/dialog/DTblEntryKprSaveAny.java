package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblEntryKprSaveAny extends DTblEntryKprSaveAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
        
    final static private String _f_s_strTitleThisPrefix = "create (DSA or RSA) keypair in ";
    
    // ------
    // PUBLIC
    
    
    
    public DTblEntryKprSaveAny(
        Component cmpFrameOwner, 
        String strTitleAppli,
        java.security.KeyStore kseLoaded,
        boolean blnIsPassword
        )
    {
        super(
            cmpFrameOwner, 
            strTitleAppli,
            DTblEntryKprSaveAny._f_s_strTitleThisPrefix,

            kseLoaded,
            blnIsPassword
            ); 
    }
}