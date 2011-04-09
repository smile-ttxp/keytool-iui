package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

public final class DTblKstView extends DTblKstAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThisPrefix = "view";
    private static final String _f_s_strTitleThisSuffix = "keystore";

    public String get_f_s_strTitleThisPrefix() {
        return _f_s_strTitleThisPrefix;
    }
    
    // ------
    // PUBLIC

    public DTblKstView(
        Component cmpFrameOwner, 
     
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
      
            DTblKstView._f_s_strTitleThisPrefix + " " + kseLoaded.getType() + " " + DTblKstView._f_s_strTitleThisSuffix,
            kseLoaded
            ); 
            
    }
}