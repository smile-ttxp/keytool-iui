package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblEntryKprOpenKPRsa extends DTblEntryKprOpenKPAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleThisPrefix = "select RSA keypair entry in ";
    
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