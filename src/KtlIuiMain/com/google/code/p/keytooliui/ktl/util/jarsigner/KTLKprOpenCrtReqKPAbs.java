package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    

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

abstract public class KTLKprOpenCrtReqKPAbs extends KTLKprOpenCrtReqAbs
{
    // ---------
    // protected

    protected KTLKprOpenCrtReqKPAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore of type XXX 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveCsr, // CSR to save
        
        String strFormatFileCsr, // should be PKCS#10
        String strProviderKst
        )
    {
        super(
            frmOwner, 

        
            // input
            strPathAbsOpenKst, // existing keystore of type BKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveCsr, // CSR to save
        
            strFormatFileCsr, // should be PKCS#10
            strProviderKst // 
            );
            
    }
    
    protected boolean _doJobSelectKpr_(
        File fleSaveCsr,
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
            
        /** OLD
        Boolean[] boosElligible, 
        String[] strsAlias, 
        Boolean[] boosEntryKpr,
        Boolean[] boosEntryTcr,
        Boolean[] boosSelfSignedCert, 
        Boolean[] boosTrustedCert, 
        String[] strsAlgoKeyPubl, 
        String[] strsSizeKeyPubl, 
        String[] strsTypeCert, 
        String[] strsAlgoSigCert, 
        Date[] dtesLastModified**/
        )
    {
        String strMethod = "_doJobSelectKpr_(...)";

        //DTblEntryKprOpenAbs dlg = new DTblEntryKprOpenKPAny(
        DTblsKstSelPKOpen dlg = new DTblsKstSelPKOpen(
            super._frmOwner_, 

            kstOpen,
            super._strPathAbsKst_,
            "Export certificate from private key entry as CSR file"
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
            dtesLastModifiedSK
            /*boosElligible, strsAlias, 
            boosEntryKpr,
            boosEntryTcr,
            boosSelfSignedCert, 
            boosTrustedCert, 
            strsAlgoKeyPubl,
            strsSizeKeyPubl,
            strsTypeCert, 
            strsAlgoSigCert, dtesLastModified
             */
             ))
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
        
        if (! super._doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleSaveCsr))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        return true;
    }
    
}