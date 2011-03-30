package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**


**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.awt.*;
import java.io.*;
import java.util.*;

final public class KTLTcrSaveCrtInBks extends KTLTcrSaveCrtInDMAbs
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
        
        if (! super.doJob())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(true);
        
        
        // ---
        // get file keystore
        
        
        // memo: keystore should be of type "BKS", provided by "?"
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
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
        
        KeyStore kstOpen = UtilKstBks.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
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
    
    
    public KTLTcrSaveCrtInBks(
        Frame frmOwner, 
        String strTitleAppli,
        
        // output
        String strPathAbsOpenKst, // existing keystore of type BKS 
        char[] chrsPasswdOpenKst,
        
        // input
        String strPathAbsCrt,
        String strFormatFileCrt,
        boolean blnTrustCACerts
        )
    {
        
        super(
            frmOwner, 
            strTitleAppli,
        
          
            strPathAbsOpenKst, // existing keystore of type BKS 
            chrsPasswdOpenKst, 
            
            strPathAbsCrt,
            strFormatFileCrt,
        
            KTLAbs.f_s_strProviderKstBks, // provider for keystore
            blnTrustCACerts
            );
    }
}