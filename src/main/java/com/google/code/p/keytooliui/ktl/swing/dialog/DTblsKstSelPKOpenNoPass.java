package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.Component;

public class DTblsKstSelPKOpenNoPass extends DTblsKstSelPKAbs
{
    // --------------------
    // PRIVATE STATIC FINAL

    private static final String _f_s_strTitleThisSuffix = "keystore";
    private static final String _STR_BODYBUTTONUSAGE = "Usage:\n  Mouse-click  valid candidate row to select respective entry's alias,\n  then click \"OK\" button.";
    private static final String _STR_TEXTLABELALIAS = "Selected entry's alias:";
    
    // ------
    // public
    
    public char[] getPassword() { return new char[0]; } // trick
    
    public DTblsKstSelPKOpenNoPass(
        Component cmpFrameOwner, 
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strTitlePrefix)
    {
        super(
                cmpFrameOwner,  
                strTitlePrefix,
                kseLoaded,
                strPathAbs,
                DTblsKstSelPKOpenNoPass._STR_BODYBUTTONUSAGE,
                DTblsKstSelPKOpenNoPass._STR_TEXTLABELALIAS,
                false // blnSave (v/s Open) !!!!!!!!!!!!! always false !!!!!!!!!
                );
    }
    
    // ---------
    // protected
    
    protected boolean _contentsOk_()
    {
        return super._contentsAliasOk_();
    }
    
    // -------
    // private
}  
      