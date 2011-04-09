package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;

public class DTblsKstView extends DTblsKstViewAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
   
    private static final String _f_s_strTitleThisPrefix = "view";
    private static final String _f_s_strTitleThisSuffix = "keystore";
    
    // ------
    // PUBLIC
    

    public DTblsKstView(
        Component cmpFrameOwner, 
     
        java.security.KeyStore kseLoaded,
        String strPathAbs
        )
    {
        this(
            cmpFrameOwner, 
        
            DTblsKstView._f_s_strTitleThisPrefix + " " + kseLoaded.getType() + " " + DTblsKstView._f_s_strTitleThisSuffix,
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
    
    protected DTblsKstView(
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
