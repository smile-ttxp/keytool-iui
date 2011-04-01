package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
**/



import java.awt.*;

final public class DTblKstView extends DTblKstAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleThisPrefix = "view";
    final static private String _f_s_strTitleThisSuffix = "keystore";

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