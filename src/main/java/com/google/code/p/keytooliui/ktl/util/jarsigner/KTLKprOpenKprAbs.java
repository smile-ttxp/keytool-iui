package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    
    
    known subclasses:
    . KTLKprOpenKprInAbs  ==> import import private key entry from file
    . KTLKprOpenKprOutAbs ==> export private key entry as file

**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

// ----
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
// ----


import java.awt.*;

public abstract class KTLKprOpenKprAbs extends KTLKprOpenAbs
{
    // ---------
    // PROTECTED
    
    protected String _strPathAbsFileKpr_ = null;
    protected String _strPathAbsFileCrts_ = null;
    
    protected String _strFormatFileKpr_ = null;
    protected String _strFormatFileCrts_ = null;
    
    // if any error, return nil, then calling method should exit immediately!
    /*public static X509Certificate s_getCertX509FirstInChain(
        KeyStore kstOpen,
        String strAliasKpr
        )
    {
        String strMethod = "s_getCertX509FirstInChain(kstOpen, strAliasKpr)";
        
        // ----
        // . get first X509 cert in cert chain
        // MEMO: tests already done while selecting alias and password, if any error, exiting!
        
        X509Certificate[] crtsX509Ordered = UtilKprX509.s_getX509CertificateChain(
            kstOpen, 
            strAliasKpr, 
            true // blnOrderChain
            );
            
        if (crtsX509Ordered == null)
        {
            MySystem.s_printOutError(strMethod, "nil crtsX509Ordered");
            return null;
        }
        
        if (crtsX509Ordered.length < 1)
        {
            MySystem.s_printOutError(strMethod, "crtsX509Ordered.length < 1");
            return null;
        }
        
        // ----
        // return the first certificate in chain
        //MySystem.s_printOutTrace(strMethod, "crtsX509Ordered[0].getSigAlgName()=" + crtsX509Ordered[0].getSigAlgName());
        return crtsX509Ordered[0];
    }*/
    
    protected KTLKprOpenKprAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileKpr,
        String strPathAbsFileCrts,
        
        String strProviderKst, // eg "SUN"
        
        String strFormatFileKpr,
        String strFormatFileCrts    
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        this._strPathAbsFileKpr_ = strPathAbsFileKpr;
        this._strPathAbsFileCrts_ = strPathAbsFileCrts;
        this._strFormatFileKpr_ = strFormatFileKpr;
        this._strFormatFileCrts_ = strFormatFileCrts;
    }
}
