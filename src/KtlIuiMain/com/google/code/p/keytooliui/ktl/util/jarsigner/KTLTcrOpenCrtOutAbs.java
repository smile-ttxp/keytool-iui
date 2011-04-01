package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . ?

    "Tcr" for "Trusted certificate" entry
    

**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.KeyStore;
import java.security.KeyStoreException;
// --
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLTcrOpenCrtOutAbs extends KTLTcrOpenCrtAbs
{
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectTcr_(
        File fleSaveCrt,
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
    
    // ------
    // PUBLIC
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileSave crt (MEMO: check for files prior to open up trusted certificate entries selection dialog)
        . open keystore
        . if no entry in keystore, show warning dialog, then return false
        . if no entry of type "Trusted certificate" in keystore, show warning dialog, then return false
        . fill in table with entrie
        . show dialog trusted certificate select, to get:
          . alias trusted certificate
        . get X509 trusted certificate 
        . generate certificate ?string from trusted certificate
        
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
        
        if (this._strProviderSig == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strProviderSig");
        }
        
        
        // ---
        // check cert file format
        
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
            super._frmOwner_,  super._strPathAbsKst_);
        
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
            super._frmOwner_,  super._strPathAbsFileIO_,
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
                
        
        if (! _doJobSelectTcr_(
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
        . get fileSave crt (MEMO: check for files prior to open up trusted certificate entries selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . fill in table entries
        . show dialog trusted certificate select, to get:
          . alias
        . get trusted certificate
        . generate CRT string from tcr
        
        . write crt string to fileSave
    **/
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasTcr,
        File fleSaveCrt
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasTcr, fleSaveCrt)";
        
        if (kstOpen==null || strAliasTcr==null || fleSaveCrt==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        Certificate crtEntryTcr = null;
        
        try
        {
            crtEntryTcr = kstOpen.getCertificate(strAliasTcr);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            
            MySystem.s_printOutError(this, strMethod, "failed");
			String strBody = "got keystore exception."; 
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            
            return false;
        }
        
        
        // get private key
        // is password corrrect?
 
        
        // ----
        // . create certificate from trusted certificate
        
        X509Certificate crtX509 = null;
        
        try
        {
            crtX509 = (X509Certificate) crtEntryTcr;
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "got excClassCast");
            
            String strBody = "Failed. Got class cast exception."; 
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            
            return false;
        }
        
        
        if (this._blnFormatCrtPrintable) // eg: "PKCS7 Printable"
        {
            if (! _writeCrtAscii(crtX509, fleSaveCrt))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        else
        {
            if (! _writeCrtBinary(crtX509, fleSaveCrt))
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
            str = UtilCrtX509Pem.s_generateCrt(super._frmOwner_,  crt);
                
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
                        
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
                    
                    
            // -- aborting
            return false;
        }
        
        return true;
    }
    
    private boolean _writeCrtBinary(
        X509Certificate crtX509,
        File fleSaveCrt
        )
    {
        String strMethod = "_writeCrtBinary(...)";
        
        byte[] bytsCrt = null;
        
        if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPkcs7.toLowerCase()) == 0)
        {
            bytsCrt = UtilCrtX509Pkcs7.s_generateCrt(
                super._frmOwner_, 
                crtX509, 
                this._strProviderSig);
        }
        
        else if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtDer.toLowerCase()) == 0)
        {
            bytsCrt = UtilCrtX509Der.s_generateCrt(
                super._frmOwner_, 
                crtX509, 
                this._strProviderSig);
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
            
            
            String strBody = "Got an IO exception while attempting to write certificate file:";
            strBody += "\n";
            strBody += "  ";
            strBody += fleSaveCrt.getAbsolutePath();
                        
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
              
            return false;
        }
        
        return true;
    }
    
  

    protected KTLTcrOpenCrtOutAbs(
        Frame frmOwner, 
 
        
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
    
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileSaveCrt,
            strProviderKst,
            strFormatFileIO // export, cert
            );
            
         this._strProviderSig = KTLAbs.f_s_strProviderKstBC; // should be final static 
         
         if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) == 0)
            this._blnFormatCrtPrintable = true;
    }
    
    // -------
    // private
    
    private String _strProviderSig = null;
    private boolean _blnFormatCrtPrintable = false;
}