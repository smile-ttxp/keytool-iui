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
        String strTitleAppli,
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
            strTitleAppli,
            DTblEntryCrtOpenDMAny._f_s_strTitleThisPrefix,
            kseLoaded
            ); 
    }
}
