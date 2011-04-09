package com.google.code.p.keytooliui.ktl.util.jarsigner;



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.KeyStore;


import java.awt.*;
import java.io.*;

public final class KTLCcaSaveCrtInJks extends KTLCcaSaveCrtInDMAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . fill in table entries
        . show dialog keystore
          . enter new alias for trusted certificate entry candidate
        . create new tcr from crt file
        . assign new entry to open keystore
        . save keystore
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        /*if (! super.doJob())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/
        
        super._setEnabledCursorWait_(true);
        
        // ---
        // get file keystore 
        
        // memo: keystore should be of type "JKS", provided by "SUN"
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        // open keystore
        
        if (super._chrsPasswdKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        }
        
        KeyStore kstOpen = UtilKstJks.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKst,
            super._chrsPasswdKst_);
        
        if (kstOpen == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil kstOpen");
            return false;
        }
        
        if (! super._doJob_(fleOpenKst, kstOpen))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
      
        // ending
        return true;
    }
    
    
    public KTLCcaSaveCrtInJks(
        Frame frmOwner, 

        
        // output
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        
        // input
        String strPathAbsCrt,
        String strFormatFileCrt
        )
    {
        
        super(
            frmOwner, 
      
        
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst, 
            
            strPathAbsCrt,
            strFormatFileCrt,
        
            KTLAbs.f_s_strProviderKstJks // provider for keystore
            );
    }
}
