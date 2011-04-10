package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprOpenCrtReqJks
    . KTLKprOpenCrtReqJceks

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

abstract public class KTLKprOpenCrtReqAbs extends KTLKprOpenCrtAbs
{
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectKpr_(
        File fleSaveCsr,
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
            
        /* OLD
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
        Date[] dtesLastModified*/
        );
    
    // ------
    // PUBLIC
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileSave csr (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . !! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        . fill in table keypair
        . show dialog keypair select RSA self, to get:
          . alias keypair
          . password keypair
        . get private key
        . get first X509 cert in cert chain
        . generate CSR string from cert and private key
        
        . write csr string to fileSave
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
       
        super._setEnabledCursorWait_(true);
        
        // ---
        // check providers
        
        if (super._strProviderKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strProviderKst_");
        }
        
        if (this._strProviderSig == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strProviderSig");
        }
        
        
        // ---
        // check cert file format (bc just provides support for CSR generation in PKCS#10 format
        
        if (super._strFormatFileIO_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strFormatFileIO_");
        }
        
        boolean blnGotIt = false;

        for (int i=0; i<KTLAbs.f_s_strsFormatFileCertReqBc.length; i++)
        {
            String str = KTLAbs.f_s_strsFormatFileCertReqBc[i];
            
            if (str.toLowerCase().compareTo(super._strFormatFileIO_.toLowerCase()) == 0)
            {
                blnGotIt = true;
                break;
            }
        }
        
        if (! blnGotIt)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "! blnGotIt, super._strFormatFileIO_=" + super._strFormatFileIO_);
        }
        
        // ---
        // get fileOpen keystore
        
        
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        // . get fileSave CSR
        
        if (super._strPathAbsFileIO_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileIO_");
        }
        
        File fleSaveCsr = UtilJsrFile.s_getFileSave(
            super._frmOwner_,  super._strPathAbsFileIO_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveCsr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveCsr");
            return false;
        }
        
        // ----
        // open keystore
        
        if (super._chrsPasswdKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        }
        
        KeyStore kstOpen = _getKeystoreOpen_(fleOpenKst);
        
        if (kstOpen == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil kstOpen");
            return false;
        }
        
        
        // NEW
        
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
        
        
        
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
                
        
        if (! _doJobSelectKpr_(
            fleSaveCsr,
            kstOpen,
                
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
            
            /* OLD
            boosElligible, 
            strsAlias, 
            boosEntryKpr,
            boosEntryTcr,
            boosSelfSignedCert, 
            boosTrustedCert, 
            strsAlgoKeyPubl, 
            strsSizeKeyPubl, 
            strsTypeCert, 
            strsAlgoSigCert, 
            dtesLastModified*/
             ))
        {
            MySystem.s_printOutTrace(this, strMethod, "either aborted or failed");
            return false;
        }
        
        
        
        
        // ending
        return true;
    }
    
    // -------
    // private
    
    private String _strProviderSig = null;
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileSave csr (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . !! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        . fill in table keypair
        . show dialog keypair select RSA self, to get:
          . alias keypair
          . password keypair
        . get private key
        . get first X509 cert in cert chain
        . generate CSR string from cert and private key
        
        . write csr string to fileSave
    **/
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasKpr,
        char[] chrsPasswdKpr,
        File fleSaveCsr
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleSaveCsr)";
        
        if (kstOpen==null || strAliasKpr==null || chrsPasswdKpr==null || fleSaveCsr==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        // get private key
        // is password corrrect?
 
        Key key = UtilKstAbs.s_getKey(
            super._frmOwner_, 
      
            kstOpen,
            strAliasKpr,
            chrsPasswdKpr);
        
        if (key == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil key");
            return false;
        }
        
        PrivateKey pkyPrivate = null;
        
        try
        {
            pkyPrivate = (PrivateKey) key;
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excClassCast caught");
        }
                        
        
        // ----
        // . get first X509 cert in cert chain
        // MEMO: tests already done while selecting alias and password, if any error, exiting!
        
        X509Certificate crtX509FirstInChain = KTLKprOpenCrtAbs.s_getCertX509FirstInChain(kstOpen, strAliasKpr);
        
        if (crtX509FirstInChain == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtX509FirstInChain");
            
            MySystem.s_printOutError(this, strMethod, "failed");
			String strBody = "Failed to get first in chain X509 certificate."; 
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_, strBody);
            
            return false;
        }
        
        // ----
        // . generate CSR from cert and private key
        
        String strCsr = null;
        
        strCsr = UtilCsrPkcs10.s_generateCsr(
            super._frmOwner_, 
            crtX509FirstInChain, pkyPrivate,
            this._strProviderSig);
        
        
        if (strCsr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strCsr");
            return false;
        }
       
        
        // ----
        // . save string CSR to fileSave
        FileWriter fwrSaveCsr = null;
        
        try
        {
            fwrSaveCsr = new FileWriter(fleSaveCsr); // the specified file could not found or some other I/O error may occur
            fwrSaveCsr.write(strCsr);                // an I/O error may occur
            fwrSaveCsr.close();                      // an I/O error may occur
            fwrSaveCsr = null;
        }
        
        // just show a warning dialog, then abort
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");
            
            String strBody = "Got an IO exception while attempting to write CSR file:";
            strBody += "\n";
            strBody += "  ";
            strBody += fleSaveCsr.getAbsolutePath();
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
            
            
            // -- aborting
            return false;
        }   
        
        // ending
        return true;
    }
    
    /*
        for each, should be:
        keypairEntry, self-signed, RSA, X.509
    */
    private Boolean[] _getBoosElligible(
        Boolean[] boosEntryKpr,
        Boolean[] boosSelfSigned,
        String[] strsAlgoKeyPubl,
        Boolean[] boosTypeCertX509,
        String[] strsAlgoSigCert
        )
    {
        String strMethod = "_getBoosElligible(...)";
        
        if (boosEntryKpr==null || boosSelfSigned==null || strsAlgoKeyPubl==null || boosTypeCertX509==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosElligible = new Boolean[boosSelfSigned.length];
        
        for (int i=0; i<boosSelfSigned.length; i++)
        {
            boolean blnOk = true;
            
            // should be keypair entry, no trusted cert entry
            if (boosEntryKpr[i].booleanValue() == false)
            {
                blnOk = false;
            }
            
            
            // should be self-signed
            if (boosSelfSigned[i].booleanValue() == false)
            {
                blnOk = false;
            }
            
            // should be of type RSA
            /*else if (strsAlgoKeyPubl[i].toLowerCase().compareTo(
                KTLAbs.f_s_strTypeKeypairRsa.toLowerCase()) != 0)
            {
                blnOk = false;
            }*/
            
            // modif: allow either RSA or DSA
            else if (strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairRsa.toLowerCase()) != 0 &&
                     strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairDsa.toLowerCase()) != 0)
            {
                blnOk = false;
            }
            
            // certificate should be of type X509
            else if (boosTypeCertX509[i].booleanValue() == false)
            {
                blnOk = false;
            }
            
            // --
            
            boosElligible[i] = new Boolean(blnOk);
        }

        // ----
        return boosElligible;
    }
    
  

    protected KTLKprOpenCrtReqAbs(
        Frame frmOwner, 
        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveCsr, // CSR to save
        String strFormatFileCsr, // should be PKCS#10
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileSaveCsr,
            strProviderKst,
            strFormatFileCsr
            );
            
         this._strProviderSig = KTLAbs.f_s_strProviderKstBC; // should be final static 
    }
    
    // -------
    // PRIVATE
    
    
    
    
    
    
}