package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.Component;

public class DTblsKstSel extends DTblsKstSelAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
   
    final static private String _f_s_strTitleThisPrefix = "select entry in";
    final static private String _f_s_strTitleThisSuffix = "keystore";
    
    // ------
    // PUBLIC
    

    public DTblsKstSel(
        Component cmpFrameOwner, 
        java.security.KeyStore kseLoaded,
        String strPathAbs
        )
    {
        this(
            cmpFrameOwner, 
            DTblsKstSel._f_s_strTitleThisPrefix + " " + kseLoaded.getType() + " " + DTblsKstSel._f_s_strTitleThisSuffix,
            kseLoaded,
            strPathAbs,
            (String) null // strBodyButtonUsage
            ); 
            
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        super._addToolbarButtonExit_();
        
        return true;
    }
    
    // ---------
    // protected
    
    protected DTblsKstSel(
        Component cmpFrameOwner, 
        String strTitleBar,
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strBodyButtonUsage
        )
    {
        super(
            cmpFrameOwner, 
            strTitleBar,
            kseLoaded,
            strPathAbs,
            strBodyButtonUsage
            ); 
            
    }

}
