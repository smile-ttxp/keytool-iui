package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    KTLKprSaveFromJceksToKPJks: "Kpr" for "KeyPair", 
    create a new entry in existing JKS keystore by importing a KeyPair entry from JCEKS keystore
    
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
// --
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

final public class KTLKprSaveFromJceksToKPJks extends KTLKprSaveFromJceksToKPAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        
        . add providers
        
        . open keystoreSource
        . open keystoreTarget
        . select aliasSource pointing to valid kprSource
        . get respective keySource & crtsSource
        
        . create new KeyPair
        . create new certificate of type X.509
        . assign new entry to open JKS keystore
        . save JKS keystore
    **/
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        File fleOpenKstSource = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKstSource_);
        
        if (fleOpenKstSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstSource");
            return false;
        }
        
        File fleOpenKstTarget = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKstTarget == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstTarget");
            return false;
        }
        
        // --
        // . open keystoreSource
        
        if (super._chrsPasswdKstSource_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKstSource_"); 
        }
        
        KeyStore kstOpenSource = UtilKstJceks.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKstSource,
            super._chrsPasswdKstSource_);
        
        if (kstOpenSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil kstOpenSource"); // could be wrong password
            return false;
        }
        
        // ----
        // open keystore target
        
        if (super._chrsPasswdKst_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        
        KeyStore kstOpenTarget = UtilKstJks.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKstTarget,
            super._chrsPasswdKst_);
        
        if (kstOpenTarget == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil kstOpenTarget"); // could be wrong password
            return false;
        }
        
        super._setEnabledCursorWait_(true);
        
        
        if (! super._doJob_(kstOpenSource, kstOpenTarget, fleOpenKstTarget))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        super._setEnabledCursorWait_(false);

        // ending
        return true;
    }
    
    public KTLKprSaveFromJceksToKPJks(
        Frame frmOwner, 
        String strTitleAppli,
        
        String strPathAbsOpenKstTarget, // existing keystore of type JKS 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type JCEKS 
        char[] chrsPasswdKstSource
        
        )
    {
        super(
            frmOwner, 
            strTitleAppli,

            strPathAbsOpenKstTarget, // existing keystore of type JKS 
            chrsPasswdOpenKstTarget,
        
            strPathAbsKstSource, // keystore of type JCEKS 
            chrsPasswdKstSource,            
            
            KTLAbs.f_s_strProviderKstJks // provider for keystore target of type JKS
            );
    }
    
}