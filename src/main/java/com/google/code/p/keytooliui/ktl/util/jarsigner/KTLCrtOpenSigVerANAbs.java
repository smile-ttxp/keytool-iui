package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Crt" for "Certificate"
    

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;


// ----
import java.security.KeyStore;

// --
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public abstract class KTLCrtOpenSigVerANAbs extends KTLCrtOpenSigVerAbs
{
    // ---------
    // protected

    protected KTLCrtOpenSigVerANAbs(
        Frame frmOwner, 
  
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileOpenSig, // sign to open
        
        String strFormatFileSig, // eg: DER-PKCS7-PEM
        String strProviderKst
        )
    {
        super(
            frmOwner, 

        
            // input
            strPathAbsOpenKst, // existing keystore 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenData,
            strPathAbsFileOpenSig, // CRT to save
        
            strFormatFileSig, 
            strProviderKst 
            );
            
    }
    
    protected boolean _doJobSelectCrt_(
        File fleOpenSig,
        File fleOpenData,
        KeyStore kstOpen,
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
        String strMethod = "_doJobSelectCrt_(...)";

        DTblsKstSelTCOpen dlg = new DTblsKstSelTCOpen(
            super._frmOwner_, 
      
            kstOpen,
            super._strPathAbsKst_,
            "Verify signed file with other detached signature file and trusted certificate entry"
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
     
        /*char[] chrsPasswdKpr = dlg.getPassword();
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, aborted by user");
            return false;
        }*/
        
        
        String strAliasCrt = dlg.getAlias();
        
        if (strAliasCrt == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasCrt, aborted by user");
            return false;
        }
        
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpen, strAliasCrt, fleOpenSig, fleOpenData))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        
        return true;
        
    }
    
}

