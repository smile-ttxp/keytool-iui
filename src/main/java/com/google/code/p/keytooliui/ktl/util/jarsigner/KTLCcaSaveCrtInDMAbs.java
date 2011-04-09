package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**


    known subclasses:
    . ?

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

public abstract class KTLCcaSaveCrtInDMAbs extends KTLCcaSaveNewDMAbs
{    
    // ---------
    // PROTECTED
    
    // called in superclass
    protected boolean __doJob__(
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
        String strMethod = "__doJob__(...)";

        
        // ----        
        // MEMO: overwriting alias-certificate not allowed
        // -----

        // ----
        // show dialog entries
        //  . get alias for new certificate entry
        
        // ----
        // launch dialog 

        DTblsKstViewTCSaveIn dlg = new DTblsKstViewTCSaveIn(
            super._frmOwner_,
            kstOpen,
            super._strPathAbsKst_
            );
        
        if (! dlg.init())
            MySystem.s_printOutExit(strMethod, "failed");
        
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
                ))
        {
            MySystem.s_printOutExit(strMethod, "failed");
        }   
        
        dlg.setVisible(true);
       
        String strAliasCca = dlg.getAlias();
        
        if (strAliasCca == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasCca, aborted by user");
            return false;
        }
        
        
        // ----
        // import new trusted certificate
        
        X509Certificate tcrNew = super._getCcaNew_(kstOpen);
        
        if (tcrNew == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil tcrNew, could be cancelled by user");
            return false;
        }

        // ----
        // assign new entry to open keystore
        
        if (! super._assignNewEntry2OpenKeystore_(kstOpen, tcrNew, strAliasCca))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
           
        return true;
    }
    
    protected KTLCcaSaveCrtInDMAbs(
        Frame frmOwner, 
        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // input
        String strPathAbsCrt,
        String strFormatFileCrt,

        String strProviderKst
        )
    {
        super(
            frmOwner, 
        
            // output
            strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
            chrsPasswdOpenKst, 
        
            // input
            strPathAbsCrt,
            strFormatFileCrt, 
            
            strProviderKst
            );
            
    }
}
