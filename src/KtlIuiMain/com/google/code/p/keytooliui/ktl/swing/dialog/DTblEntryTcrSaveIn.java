package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblEntryTcrSaveIn extends DTblEntryTcrSaveAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    
    final static private String _f_s_strTitleThisPrefix = "import trusted certificate entry in ";

    // ------
    // PUBLIC
    
    
    
    public DTblEntryTcrSaveIn(
        Component cmpFrameOwner, 
      
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
      
            DTblEntryTcrSaveIn._f_s_strTitleThisPrefix,
            kseLoaded
            ); 
    }
}