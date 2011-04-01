package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprOpenSigDetOutKPAbs ==> "KP" means "Keypair with Password"

    "Kpr" for "keypair"
    

**/

import java.nio.ByteBuffer;
import java.security.cert.CertificateExpiredException;
import javax.security.cert.CertificateNotYetValidException;
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

abstract public class KTLKprOpenSigDetOutAbs extends KTLKprOpenSigDetAbs
{
    // ---------------------------
    // final static private string
    
    final static private String _STR_PROVIDERSIGINSTANCE = KTLAbs.f_s_strProviderKstBC; 
    
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectKpr_(
        File fleSaveSig,
        File fleSaveCrt, // optional
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
        // check sig file format 
        // ??????????????????bc just provides support for CRT generation in PKCS#7 format????????????
        
        if (this._strFormatFileSig_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil this._strFormatFileSig_");
        }
        
        // ---
        // get fileOpen keystore
        
        
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        // . get fileOpen Data
        
        if (super._strPathAbsFileData_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileData_");
        }
        
        File fleOpenData = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsFileData_//,
            //false //true // blnShowDlgOverwrite
            );
        
        if (fleOpenData == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenData");
            return false;
        }
        
        // ----
        // . get fileSave Sig
        
        if (super._strPathAbsFileSig_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileSig_");
        }
        
        File fleSaveSig = UtilJsrFile.s_getFileSave(
            super._frmOwner_,  super._strPathAbsFileSig_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveSig == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveSig");
            return false;
        }
        
        // ----
        // . get fileSave Crt, optional
        File fleSaveCrt = null;
        
        if (super._strPathAbsFileSaveCrt_ != null)
        {
        
            fleSaveCrt = UtilJsrFile.s_getFileSave(
                super._frmOwner_,  super._strPathAbsFileSaveCrt_,
                true // blnShowDlgOverwrite
                    );
        
            if (fleSaveCrt == null)
            {
                super._setEnabledCursorWait_(false);
                MySystem.s_printOutWarning(this, strMethod, "nil fleSaveCrt");
                return false;
            }
        
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
            fleSaveSig,
            fleSaveCrt,
            fleOpenData,
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
        File fleSaveSig,
        File fleSaveCrt, // optional
        File fleOpenData
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleSaveSig, fleSaveCrt, fleOpenData)";
        
        if (kstOpen==null || strAliasKpr==null || chrsPasswdKpr==null || fleSaveSig==null || fleOpenData==null)
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
            // TODO: show error dialog
            return false;
        }
        
        PrivateKey pkyPrivate = (PrivateKey) key;
        
        // ----
        
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
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            
            return false;
        }
        
        // check validity
        
        try
        {
            crtX509FirstInChain.checkValidity();
        }
        
        catch(CertificateExpiredException excCertificateExpired)
        {
            MySystem.s_printOutWarning(this, strMethod, "excCertificateExpired caught");
            
            String strBody = "Certificate has expired!";
            strBody += "\n\n" + "Continue anyway?";
            
            if (! OPAbstract.s_showWarningConfirmDialog(super._frmOwner_,  strBody))
            {
                MySystem.s_printOutTrace(this, strMethod, "aborted by user");
                return false;
            }
        }
        
        catch(java.security.cert.CertificateNotYetValidException excCertificateNotYetValid)
        {
            MySystem.s_printOutWarning(this, strMethod, "excCertificateNotYetValid caught");
            
            String strBody = "Certificate not yet valid!";
            strBody += "\n\n" + "Continue anyway?";
            
            if (! OPAbstract.s_showWarningConfirmDialog(super._frmOwner_,  strBody))
            {
                MySystem.s_printOutTrace(this, strMethod, "aborted by user");
                return false;
            }
        }
                            
        
        
        // ----
        
        String strCrtSigAlgo = crtX509FirstInChain.getSigAlgName();
        
        if (! _doSignFile(fleSaveSig, fleOpenData, pkyPrivate, strAliasKpr, strCrtSigAlgo))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
         
        // ----
        
        boolean blnDoneWriteCrt = false;

        if (fleSaveCrt != null)
        {
            blnDoneWriteCrt =  _doWriteCrt(pkyPrivate, fleSaveCrt, crtX509FirstInChain);
        }
        
        String strBody = "File successfully signed with private key aliased:";
        strBody += "\n  " + strAliasKpr;
        strBody += "\n\nSignature saved in:";
        strBody += "\n  " + fleSaveSig.getAbsolutePath();
        
         if (fleSaveCrt != null)
         {
            strBody += "\n\n";
            
            if (blnDoneWriteCrt)
                strBody += "Certificate (containing public key) saved";
            else
                strBody += "Failed to save certificate";
            
            strBody += " in:";
            strBody += "\n  ";
            strBody += super._strPathAbsFileSaveCrt_;
         }
        
        OPAbstract.s_showDialogInfo(super._frmOwner_, 
    
            strBody);
        
        // ending
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

            // --
            
            boosElligible[i] = new Boolean(blnOk);
        }

        // ----
        return boosElligible;
    }
    
  

    protected KTLKprOpenSigDetOutAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveSig, // signature to save
        String strPathAbsFileSaveCrt, // certificate to save, optional
        String strFormatFileSig, // eg: DER-PKCS7
        String strFormatFileSaveCrt,
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 
      
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenData,
            strPathAbsFileSaveSig,
            strPathAbsFileSaveCrt,
            strProviderKst//, 
            //strFormatFileSaveCrt
            );
        
        this._strFormatFileSig_ = strFormatFileSig; // !!!!!!!!!!!!! NEVER USED !!!!!!!!!!!!!!!!!
        this._strFormatFileSaveCrt_ = strFormatFileSaveCrt;
         
         if (this._strFormatFileSaveCrt_ != null)
         {
            if (this._strFormatFileSaveCrt_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) == 0)
                this._blnFormatCrtPrintable = true;
         }
    }
    
    // -------
    // private
    
    private String _strFormatFileSig_ = null;
    private String _strFormatFileSaveCrt_ = null;

    private boolean _blnFormatCrtPrintable = false;
    
    private byte[] _read(File file) throws Exception // lots of exceptions
    {
       FileInputStream fis = new FileInputStream(file);
       java.nio.channels.FileChannel fcl = fis.getChannel();
       
       
       byte[] byrsData = new byte[(int) fcl.size()];   // fcl.size returns the size of the file which backs the channel
       ByteBuffer bbr = ByteBuffer.wrap(byrsData);
       fcl.read(bbr);
       return byrsData;
    }
    /**
     * MEMO: from 2SE API 1.6
     * There are three phases to the use of a Signature object for either signing data or verifying a signature:

   1. Initialization, with either
          * a public key, which initializes the signature for verification (see initVerify), or
          * a private key (and optionally a Secure Random Number Generator), which initializes the signature for signing (see initSign(PrivateKey) and initSign(PrivateKey, SecureRandom)). 

   2. Updating

      Depending on the type of initialization, this will update the bytes to be signed or verified. See the update methods.

   3. Signing or Verifying a signature on all updated bytes. See the sign methods and the verify method. 
     **/
    
    private boolean _doSignFile(
            File fleSaveSig, 
            File fleOpenData, 
            PrivateKey pkyPrivate,
            String strAlias,
            String strCrtSigAlgo)
    {
        byte[] bytsSignature = null;
        
        try
        {
            byte[] bytsData = _read(fleOpenData);        
            
            bytsSignature = _signFile(strCrtSigAlgo, bytsData, pkyPrivate);
            
            if (bytsSignature == null)
            {
                String strBody = "Failed to sign file!";
            
                OPAbstract.s_showDialogWarning(
                    super._frmOwner_, 
                  
                    strBody);
                
                return false;
            }
            
            KTLKprOpenSigDetOutAbs._s_byteToFile(bytsSignature, fleSaveSig);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            String strBody = "Failed to sign file!";
            
            strBody += "\n\n" + "Exception caught:";
            strBody += "\n  " + exc.getMessage();
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
        
                strBody);
            
            return false;
        }
        
        return true;
    }
    
    static private void _s_byteToFile(byte[] bytes, File file) throws IOException 
    {
        FileOutputStream fos = new FileOutputStream(file);
        OutputStream out = new BufferedOutputStream(fos);
        out.write(bytes);
        out.close();
    }
    
    /*
     * MEMO: from J2SE 1.6 API
     * Signature.sign()
     *Returns the signature bytes of all the data updated. 
     * The format of the signature depends on the underlying signature scheme.
     * A call to this method resets this signature object to the state 
     * it was in when previously initialized for signing via a call to initSign(PrivateKey). 
     * That is, the object is reset and available to generate another signature from the same signer, 
     * if desired, via new calls to update and sign. 
     */
    private byte[] _signFile(
            String strCrtSigAlgo,
            byte[] bytsData, PrivateKey pkyPrivate)
       throws Exception
   {
      java.security.Signature signature = java.security.Signature.getInstance(
              strCrtSigAlgo, KTLKprOpenSigDetOutAbs._STR_PROVIDERSIGINSTANCE);

      signature.initSign(pkyPrivate);
      signature.update(bytsData);
      
      return signature.sign(); 
   }
    
    // !!! duplicated from KTLKprOpenCrtOutAbs
    private boolean _writeCrtAscii(
        X509Certificate crt,
        File fleSaveCrt)
    {
        String strMethod = "_writeCrtAscii(crt, fleSaveCrt)";
        
        
        String str = null;
        
        if (this._strFormatFileSaveCrt_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) == 0)
        {
            str = UtilCrtX509Pem.s_generateCrt(super._frmOwner_, crt);
                
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
                "DEV CODE ERROR, uncaught ascii certificate format, super._strFormatFileSveCrt_=" +
                this._strFormatFileSaveCrt_);
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
        X509Certificate crtX509FirstInChain,
        PrivateKey pkyPrivate,
        File fleSaveCrt
        )
    {
        String strMethod = "_writeCrtBinary(...)";
        
        byte[] bytsCrt = null;
        
        if (this._strFormatFileSaveCrt_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPkcs7.toLowerCase()) == 0)
        {
            bytsCrt = UtilCrtX509Pkcs7.s_generateCrt(
                super._frmOwner_,
                crtX509FirstInChain,
                KTLKprOpenSigDetOutAbs._STR_PROVIDERSIGINSTANCE);
        }
        
        else if (this._strFormatFileSaveCrt_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtDer.toLowerCase()) == 0)
        {
            bytsCrt = UtilCrtX509Der.s_generateCrt(
                super._frmOwner_,
                crtX509FirstInChain,
                KTLKprOpenSigDetOutAbs._STR_PROVIDERSIGINSTANCE);
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, 
                "DEV CODE ERROR: uncaught format, this._strFormatFileSaveCrt_=" + this._strFormatFileSaveCrt_);
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
    
    private boolean _doWriteCrt(
        PrivateKey pkyPrivate,
        File fleSaveCrt,
        X509Certificate crtX509FirstInChain
        )
    {
        String strMethod = "_doWriteCrt(pkyPrivate, fleSaveCrt, crtX509FirstInChain)";
        
        if (pkyPrivate==null || fleSaveCrt==null || crtX509FirstInChain==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        // ----
        // . extracting certificate from private key
        
        
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
}
