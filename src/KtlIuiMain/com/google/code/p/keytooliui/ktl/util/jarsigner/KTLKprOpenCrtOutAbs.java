package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprOpenCrtOutKPAbs ==> "KP" means "Keypair with Password"
    . KTLKprOpenCrtOutPkcs12

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

abstract public class KTLKprOpenCrtOutAbs extends KTLKprOpenCrtAbs
{
    // ---------------------------
    // final static private string
    
    final static private String _STR_PROVIDERSIGINSTANCE = KTLAbs.f_s_strProviderKstBC; 
    
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectKpr_(
        File fleSaveCrt,
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
        );
    
    // ------
    // PUBLIC
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileSave crt (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . WRONG !!!!!!!!!! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        . fill in table keypair
        . WRONG !!!!!!!!!  show dialog keypair select RSA self, to get:
          . alias keypair
          . password keypair
        . get private key
        . get first X509 cert in cert chain
        . generate CRT string from cert and private key
        
        . write crt string to fileSave
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
        
        
        // ---
        // check cert file format (bc just provides support for CRT generation in PKCS#7 format
        
        if (super._strFormatFileIO_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strFormatFileIO_");
        }
        
        boolean blnGotIt = false;

        for (int i=0; i<KTLAbs.f_s_strsFormatFileCertOutBc.length; i++)
        {
            String str = KTLAbs.f_s_strsFormatFileCertOutBc[i];
            
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
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        // . get fileSave CRT
        
        if (super._strPathAbsFileIO_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileIO_");
        }
        
        File fleSaveCrt = UtilJsrFile.s_getFileSave(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsFileIO_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveCrt == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveCrt");
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
            super._strTitleAppli_,
            kstOpen);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
            super._strTitleAppli_,
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
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
                
        
        if (! _doJobSelectKpr_(
            fleSaveCrt,
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
                ))
        {
            MySystem.s_printOutTrace(this, strMethod, "either aborted or failed");
            return false;
        }

        // ending
        return true;
    }
    
    
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileSave crt (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . WRONG LINE !! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        . fill in table keypair
        . WRONG LINE show dialog keypair select RSA self, to get:
          . alias keypair
          . password keypair (if not from pkcs12 kst)
        . get private key
        . get first X509 cert in cert chain
        . generate CRT string from cert and private key
        
        . write crt string to fileSave
    **/
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasKpr,
        char[] chrsPasswdKpr,
        File fleSaveCrt
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleSaveCrt)";
        
        if (kstOpen==null || strAliasKpr==null || chrsPasswdKpr==null || fleSaveCrt==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        // get private key
        // is password corrrect?
 
        Key key = UtilKstAbs.s_getKey(
            super._frmOwner_, 
            super._strTitleAppli_,
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
            MySystem.s_printOutError(this, strMethod, "nil crtX509FirstInChain, failed");

	    String strBody = "Failed to get first in chain X509 certificate."; 
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_, super._strTitleAppli_, strBody);
            
            return false;
        }
        
        // ----
        // . create certificate from private key
        
        
        if (this._blnFormatCrtPrintable) // eg: "PKCS7 Printable"
        {
            if (! _writeCrtAscii(crtX509FirstInChain, fleSaveCrt))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        else
        {
            if (! _writeCrtBinary(crtX509FirstInChain, pkyPrivate, fleSaveCrt))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
       
        
        // ending
        return true;
    }
    
    private boolean _writeCrtAscii(
        X509Certificate crt,
        File fleSaveCrt)
    {
        String strMethod = "_writeCrtAscii(...)";
        
        
        String str = null;
        
        if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) == 0)
        {
            str = UtilCrtX509Pem.s_generateCrt(super._frmOwner_, super._strTitleAppli_, crt);
                
            if (str == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil str");
                return false;
            }
                
            // ----
            
               
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, 
                "DEV CODE ERROR, uncaught ascii certificate format, super._strFormatFileIO_=" +
                super._strFormatFileIO_);
        }
        
        FileWriter fwrSaveCrt = null;
                
        try
        {
            fwrSaveCrt = new FileWriter(fleSaveCrt); // the specified file could not found or some other I/O error may occur
            fwrSaveCrt.write(str);                // an I/O error may occur
            fwrSaveCrt.close();                      // an I/O error may occur
            fwrSaveCrt = null;
        }
                
        // just show a warning dialog, then abort
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIO caught");
                    
            String strBody = "Got an IO exception while attempting to write certificate file:";
            strBody += "\n";
            strBody += "  ";
            strBody += fleSaveCrt.getAbsolutePath();
            
            strBody += "\n\n" + "Exception message";
            strBody+= "\n";
            strBody += excIO.getMessage();
                        
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
                    
                    
            // -- aborting
            return false;
        }
        
        return true;
    }
    
    private boolean _writeCrtBinary(
        X509Certificate crtX509FirstInChain,
        PrivateKey pkyPrivate,
        File fleSaveCrt
        )
    {
        String strMethod = "_writeCrtBinary(...)";
        
        byte[] bytsCrt = null;
        
        if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPkcs7.toLowerCase()) == 0)
        {
            bytsCrt = UtilCrtX509Pkcs7.s_generateCrt(
                super._frmOwner_, super._strTitleAppli_,
                crtX509FirstInChain,
                KTLKprOpenCrtOutAbs._STR_PROVIDERSIGINSTANCE);
        }
        
        else if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtDer.toLowerCase()) == 0)
        {
            bytsCrt = UtilCrtX509Der.s_generateCrt(
                super._frmOwner_, super._strTitleAppli_,
                crtX509FirstInChain,
                KTLKprOpenCrtOutAbs._STR_PROVIDERSIGINSTANCE);
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, 
                "DEV CODE ERROR: uncaught format, super._strFormatFileIO_=" + super._strFormatFileIO_);
            //return false;
        }
        
        if (bytsCrt == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil bytsCrt");
            return false;
        }
        
        try
        {
            FileOutputStream fos = new FileOutputStream(fleSaveCrt);
            fos.write(bytsCrt);
            fos.close();
            fos = null;
        }
                
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            
            
            String strBody = "Got an exception while attempting to write certificate file:";
            strBody += "\n";
            strBody += "  ";
            strBody += fleSaveCrt.getAbsolutePath();
            
            strBody += "\n\n" + "Exception message";
            strBody+= "\n";
            strBody += exc.getMessage();
                        
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
            
            
            
            return false;
        }
        
        return true;
    }
    
    /*
        for each, should be:
        keypairEntry, [DSA-RSA], X.509
        
    */
    private Boolean[] _getBoosElligible(
        Boolean[] boosEntryKpr,
        String[] strsAlgoKeyPubl,
        Boolean[] boosTypeCertX509,
        String[] strsAlgoSigCert
        )
    {
        String strMethod = "_getBoosElligible(...)";
        
        if (boosEntryKpr==null || strsAlgoKeyPubl==null || boosTypeCertX509==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosElligible = new Boolean[boosEntryKpr.length];
        
        for (int i=0; i<boosEntryKpr.length; i++)
        {
            boolean blnOk = true;
        
            // should be keypair entry, no trusted cert entry
            if (boosEntryKpr[i].booleanValue() == false)
            {
                blnOk = false;
            }
            
            
            // should be of type RSA or DSA, else !!!!!!!!!!!! 
            else if (
                strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairRsa.toLowerCase())!=0
                &&
                strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairDsa.toLowerCase())!=0
                )
            {
                blnOk = false;
            }
            
            // certificate should be of type X509
            else if (boosTypeCertX509[i].booleanValue() == false)
            {
                blnOk = false;
            }
           
            // finally should be a sig algo name supported by BC's certificate generator
            /** TEMPO
            else
            {
                boolean blnGotIt = false;
                
                for (int j=0; j<KTLAbs.f_s_strsCertSigAlgoKprBc2Crt.length; j++)
                {
                    //System.out.println("strsAlgoSigCert[i]=" + strsAlgoSigCert[i]);
                    
                    String x = strsAlgoSigCert[i];
                    String y = KTLAbs.f_s_strsCertSigAlgoKprBc2Crt[j];
                    
                    if (x.toLowerCase().compareTo(y.toLowerCase()) == 0)
                    {
                        blnGotIt = true;
                        break;
                    }
                }
                
                // --
                if (blnGotIt == false)
                {
                    blnOk = false;
                }
            }**/
            
            // --
            
            boosElligible[i] = new Boolean(blnOk);
        }

        // ----
        return boosElligible;
    }
    
  

    protected KTLKprOpenCrtOutAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveCrt, // cert to save
        String strFormatFileIO, // eg: DER-PKCS7
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileSaveCrt,
            strProviderKst,
            strFormatFileIO // export, cert
            );
            
         
         
         if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) == 0)
            this._blnFormatCrtPrintable = true;
    }
    
    // -------
    // private
    
    private boolean _blnFormatCrtPrintable = false;
}