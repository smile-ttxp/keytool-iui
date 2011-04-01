package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblEntryKprOpenKPAny extends DTblEntryKprOpenKPAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleThisPrefix = "select (RSA or DSA) keypair entry in ";
    
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