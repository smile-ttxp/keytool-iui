package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLCrtOpenSigVerKPAbs 

    "Crt" for "Certificate"
    

**/

import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.security.Signature;
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

abstract public class KTLCrtOpenSigVerAbs extends KTLCrtOpenSigAbs
{
    // ---------------------------
    // private static final string
    
    private static final String _STR_PROVIDERSIGINSTANCE = KTLAbs.f_s_strProviderKstBC; 
    
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectCrt_(
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
        MySystem.s_printOutWarning(this, strMethod, "TODO - in progress");
        
        
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
        
        if (super._strFormatFileSig_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strFormatFileSig_");
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
            super._frmOwner_, super._strPathAbsFileData_//,
            //false //true // blnShowDlgOverwrite
            );
        
        if (fleOpenData == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenData");
            return false;
        }
        
        // ----
        // . get fileOpen Sig
        
        if (super._strPathAbsFileSig_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileSig_");
        }
        
        File fleOpenSig = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsFileSig_//,
            //true // blnShowDlgOverwrite
            );
        
        if (fleOpenSig == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenSig");
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
                
        
        if (! _doJobSelectCrt_(
            fleOpenSig,
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
            dtesLastModifiedSK))
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
        String strAliasCrt,
        File fleOpenSig,
        File fleOpenData
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasCrt, fleOpenSig, fleOpenData)";
        
        
       
        if (kstOpen==null || strAliasCrt==null || fleOpenSig==null || fleOpenData==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        if (! UtilKstAbs.s_checkValidDateCert(super._frmOwner_,  kstOpen, strAliasCrt))
        {
            MySystem.s_printOutTrace(this, strMethod, "not a valid cert, aborted by user");
            return false;
        }
        
        // get certificate
 
        PublicKey pkyPublic = UtilKstAbs.s_getKeyPublic(
            super._frmOwner_, 
            kstOpen,
            strAliasCrt);
        
        if (pkyPublic == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil pkyPublic");
            return false;
        }
        
        if (! _doVerifySignedUnjarredFile(kstOpen, fleOpenSig, fleOpenData, pkyPublic, strAliasCrt))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        // ending
        return true;
    }
    
    // -------
    // private
    
    private boolean _verifySignedUnjarredFile(
        String strCrtSigAlgo,
        byte[] bytsText,
        PublicKey pkyPublic,
        byte[] bytsSignature)
            throws Exception
    {
         Signature sig = Signature.getInstance(strCrtSigAlgo, KTLCrtOpenSigVerAbs._STR_PROVIDERSIGINSTANCE);
         sig.initVerify(pkyPublic);
         sig.update(bytsText);

         return sig.verify(bytsSignature);      
    }
    
    /*
        for each, should be:
        certificateEntry, [DSA-RSA], X.509
        
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
        
            // should be certificate entry, not keypair entry
            // TEMPO
            /*if (boosEntryKpr[i].booleanValue())
            {
                blnOk = false;
            }
            
            
            // should be of type RSA or DSA, else !!!!!!!!!!!! 
            else*/ if (
                strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeCrtRsa.toLowerCase())!=0
                &&
                strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeCrtDsa.toLowerCase())!=0
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
    
  

    protected KTLCrtOpenSigVerAbs(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileOpenSig, // 
        String strFormatFileSig, // eg: DER-PKCS7
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 
     
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenData,
            strPathAbsFileOpenSig,
            strProviderKst,
            strFormatFileSig // export, cert
            );
            
         //this._strProviderSig = KTLAbs.f_s_strProviderKstBC; // should be final static 
         
         if (super._strFormatFileSig_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileSigPem.toLowerCase()) == 0)
            this._blnFormatSigPrintable = true;
    }
    
    // -------
    // private

    private boolean _blnFormatSigPrintable = false;
    
    private byte[] _read(File file) 
        throws Exception 
    {
       FileInputStream fis = new FileInputStream(file);
       java.nio.channels.FileChannel fcl = fis.getChannel();
       
       byte[] byts = new byte[(int) fcl.size()];   // fcl.size returns the size of the file which backs the channel
       ByteBuffer bbr = ByteBuffer.wrap(byts);
       fcl.read(bbr);
       return byts;
    }
    
    private boolean _doVerifySignedUnjarredFile(
            KeyStore kstOpen, File fleOpenSig, File fleOpenData, PublicKey pkyPublic, String strAliasCrt)
    {
        String strMethod = "_doVerifySignedUnjarredFile(...)";
        
        try
        {
            String strCrtSigAlgo = UtilKstAbs.s_getCertSigAlgo(super._frmOwner_, 
                kstOpen, strAliasCrt);
         
            if (strCrtSigAlgo == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil strCrtSigAlgo");
                return false;
            }
            
            byte[] bytsSignature = _read(fleOpenSig);
            byte[] bytsData = _read(fleOpenData);
            
            
            if (! _verifySignedUnjarredFile(strCrtSigAlgo, bytsData, pkyPublic, bytsSignature))
            {
                String strBody = "Wrong signature!";
                
                strBody += "\n\n" + "Signature:"; 
                strBody += "\n  " + fleOpenSig.getAbsolutePath();
                strBody += "\n" + "does not match document:";
                strBody += "\n  " + fleOpenData.getAbsolutePath();
                strBody += "\n" + ", and public key of entry aliased:";
                strBody += "\n  " + strAliasCrt;
            
                OPAbstract.s_showDialogWarning(
                    super._frmOwner_, 
           
                    strBody);
                
                return false;
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            String strBody = "Failed to verify signed unjarred file!";
            
            strBody += "\n\n";
            strBody += "Exception caught:";
            strBody += "\n  " + exc.getMessage();
            
            strBody += "\n\n" + "Possible failure reason is wrong alias selection:";
            strBody += "\n  " + strAliasCrt;
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
      
                strBody);
            
            return false;
        }
        
        // --
        // OK
        
        String strBody = "Signature is OK!";
                
        strBody += "\n\n" + "Signature:"; 
        strBody += "\n  " + fleOpenSig.getAbsolutePath();
        strBody += "\n" + "does match document:";
        strBody += "\n  " + fleOpenData.getAbsolutePath();
        strBody += "\n" + ", and public key of entry aliased:";
        strBody += "\n  " + strAliasCrt;
           
        OPAbstract.s_showDialogInfo(super._frmOwner_, 
 
            strBody);
        
        return true;
    }
}
