package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    

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

public abstract class KTLKprOpenSigDetOutKPAbs extends KTLKprOpenSigDetOutAbs
{
    // ---------
    // protected

    protected KTLKprOpenSigDetOutKPAbs(
        Frame frmOwner, 
    
        
        // input
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveSig, // signature to save
        String strPathAbsFileSaveCrt, // certificate to save, optional
        
        String strFormatFileSig, // binary
        String strFormatFileSaveCrt,
        String strProviderKst
        )
    {
        super(
            frmOwner, 
 
        
            // input
            strPathAbsOpenKst, // existing keystore 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenData,
            strPathAbsFileSaveSig, // signature to save
            strPathAbsFileSaveCrt, // certificate to save, optional
           
            strFormatFileSig,
            strFormatFileSaveCrt,
            strProviderKst 
            );
            
    }
    
    protected boolean _doJobSelectKpr_(
        File fleSaveSig,
        File fleSaveCrt,
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
        String strMethod = "_doJobSelectKpr_(...)";
        
        DTblsKstSelPKOpen dlg = new DTblsKstSelPKOpen(
            super._frmOwner_, 
      
            kstOpen,
            super._strPathAbsKst_,
            "Sign file with private key and save as other detached signature file"
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
     
        char[] chrsPasswdKpr = dlg.getPassword();
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, aborted by user");
            return false;
        }
        
        
        String strAliasKpr = dlg.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleSaveSig, fleSaveCrt, fleOpenData))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        return true;
    }
    
}
