package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblEntryKprSaveAny extends DTblEntryKprSaveAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
        
    private static final String _f_s_strTitleThisPrefix = "create (DSA or RSA) keypair in ";
    
    // ------
    // PUBLIC
    
    
    
    public DTblEntryKprSaveAny(
        Component cmpFrameOwner, 
   
        java.security.KeyStore kseLoaded,
        boolean blnIsPassword
        )
    {
        super(
            cmpFrameOwner, 
      
            DTblEntryKprSaveAny._f_s_strTitleThisPrefix,

            kseLoaded,
            blnIsPassword
            ); 
    }
}