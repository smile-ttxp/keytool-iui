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