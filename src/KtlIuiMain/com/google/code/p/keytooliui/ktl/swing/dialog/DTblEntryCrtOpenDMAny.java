package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblEntryCrtOpenDMAny extends DTblEntryCrtOpenDMAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleThisPrefix = "select trusted certificate entry in ";
    
    // ------
    // PUBLIC
    
    
    public DTblEntryCrtOpenDMAny(
        Component cmpFrameOwner, 
    
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
       
            DTblEntryCrtOpenDMAny._f_s_strTitleThisPrefix,
            kseLoaded
            ); 
    }
}
