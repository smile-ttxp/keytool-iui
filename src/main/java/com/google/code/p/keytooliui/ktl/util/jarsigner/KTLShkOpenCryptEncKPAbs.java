package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Shk" for "shared key"
    

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

// --
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public abstract class KTLShkOpenCryptEncKPAbs extends KTLShkOpenCryptEncAbs
{
    // ---------
    // protected

    protected KTLShkOpenCryptEncKPAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveCrypt, // signature to save
        String strProviderKst
        )
    {
        super(
            frmOwner, 
      
        
            // input
            strPathAbsOpenKst, // existing keystore 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenData,
            strPathAbsFileSaveCrypt, // signature to save
            strProviderKst 
            );
            
    }
    
    protected boolean _doJobSelectShk_(
        File fleSaveCrypt,
        File fleOpenData,
        KeyStore kstOpen,
            
        // NEW
        // below: about PKTC (Private Key & Trusted Certificate)
        String[] strsAliasPKTC, 
        Boolean[] boosIsTCEntryPKTC, 
        Boolean[] boosValidDatePKTC, 
        Boolean[] boosSelfSignedCertPKTC, 
        Boolean[] boosTrustedCertPKTC, 
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC, 
        String[] strsAlgoSigCertPKTC, 
        Date[] dtesLastModifiedPKTC,
        // below: about SK (Secret Key)
        String[] strsAliasSK,
        Date[] dtesLastModifiedSK
        )
    {
        String strMethod = "_doJobSelectShk_(...)";
        
        DTblsKstSelSKOpen dlg = new DTblsKstSelSKOpen(
            super._frmOwner_, 
        
            kstOpen,
            super._strPathAbsKst_,
            "Encrypt data file with secret key, save result as file"
            );
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlg.load(
            // below: about PKTC (Private Key & Trusted Certificate)
            strsAliasPKTC, 
            boosIsTCEntryPKTC, 
            boosValidDatePKTC, 
            boosSelfSignedCertPKTC, 
            boosTrustedCertPKTC, 
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC, 
            strsAlgoSigCertPKTC, 
            dtesLastModifiedPKTC,
            // below: about SK (Secret Key)
            strsAliasSK,
            dtesLastModifiedSK))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlg.setVisible(true);
        
        // ----
     
        char[] chrsPasswdShk = dlg.getPassword();
        
        if (chrsPasswdShk == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdShk, aborted by user");
            return false;
        }
        
        
        String strAliasShk = dlg.getAlias();
        
        if (strAliasShk == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasShk, aborted by user");
            return false;
        }
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpen, strAliasShk, chrsPasswdShk, fleSaveCrypt, fleOpenData))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        return true;
    }
    
}
