package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
   "Cca" for "Cert CA", Trusted certificate candidate to CA certs store

    known subclasses:
    . KTLCcaSaveNewDMAbs


**/


import com.google.code.p.keytooliui.shared.lang.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.KeyStore;


import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLCcaSaveNewDMAbs extends KTLCcaSaveNewAbs
{
    // ------------------
    // ABSTRACT PROTECTED
    
    // definition in subclasses, call right there
    abstract protected boolean __doJob__(
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
            );
    
    
    
    // ---------
    // PROTECTED
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . fill in table KeyPair
        . show dialog with existing entries
          . get alias
        . create new trusted certificate entry
        . assign new entry to open keystore
        . save keystore
    **/
    protected boolean _doJob_(
        File fleOpenKst,
        KeyStore kstOpen
        )
    {
        String strMethod = "_doJob_(fleOpenKst, kstOpen)";
        
        if (fleOpenKst==null || kstOpen==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        // ----
        // fill in table entries
        
        // ----
        // get aliases
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
    
            kstOpen);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
            
            kstOpen);
        
        if (strsAliasSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        // --
        // get arrays for dialogTableSelectKeypair
        // TC versus PK
        Boolean[] boosIsTCEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
             kstOpen, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            kstOpen, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }

        // ----
        // restore default cursor
        super._setEnabledCursorWait_(false);
        
        if (! __doJob__(
            kstOpen, 
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
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        // save keystore
        if (super._chrsPasswdKst_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._chrsPasswdKst_");
            return false;
        }        
        
        // ----
        
        if (! super._saveKeyStore_(kstOpen, fleOpenKst, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
      
        // ending
        return true;
    }
    
    protected KTLCcaSaveNewDMAbs(
        Frame frmOwner, 
   
        
        // output
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // source
        // input
        String strPathAbsCrt,
        String strFormatFileCrt,
        
        String strProviderKst//,
        //boolean blnTrustCACerts
        )
    {
        super(
            frmOwner, 
          
            
            // output
            strPathAbsOpenKst,
            chrsPasswdOpenKst, 
            strProviderKst, // assigned in subclasses
            
            // input
            strPathAbsCrt,
            strFormatFileCrt//,
            //blnTrustCACerts
            );
            
    }
}
