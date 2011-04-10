package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprOpenSigDetOCmsKPAbs ==> "KP" means "Keypair with Password"

    "Kpr" for "keypair"
    "OCms" for "Out Cryptographic Message Syntax"
    

**/

import java.security.cert.CertStore;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CollectionCertStoreParameters;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSSignedGenerator;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
// --
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLKprOpenSigDetOCmsAbs extends KTLKprOpenSigDetAbs
{
    // --------------------
    // final static private
    
    static private void s_byteArrayToFile(byte[] byts, File fle)
        throws Exception
    {
        FileOutputStream fos = new FileOutputStream(fle);
        OutputStream osm = new BufferedOutputStream(fos);
        osm.write(byts);
        osm.close();
    }
    
    static private byte[] _s_streamToByteArray(InputStream ism) 
        throws Exception 
    {
        if (ism == null) 
        {
            return null;
        } 
        
        else 
        {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024];
            int c = 0;
            
            while ((c = ism.read(buffer)) > 0) 
            {
                byteArray.write(buffer, 0, c);
            }
            
            byteArray.flush();
            return byteArray.toByteArray();
        }
    }
   
    
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
        // . get fileOpen Data
        
        if (super._strPathAbsFileData_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileData_");
        }
        
        File fleOpenData = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,super._strPathAbsFileData_//,
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
        
        // beg check for valid "CMSSignedGenerator.DIGEST_[xxx]"
        
        if (strCrtSigAlgo == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strCrtSigAlgo");
            return false;
        }
        
        StringBuffer sbr = new StringBuffer(strCrtSigAlgo.toLowerCase());
        String strCmsSigGenDigest = null;
        
        
        if (sbr.indexOf("md5") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_MD5;
        else if (sbr.indexOf("ripemd128") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_RIPEMD128;
        else if (sbr.indexOf("ripemd160") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_RIPEMD160;
        else if (sbr.indexOf("ripemd256") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_RIPEMD256;
        else if (sbr.indexOf("sha1") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA1;
        else if (sbr.indexOf("sha224") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA224;
        else if (sbr.indexOf("sha256") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA256;
        else if (sbr.indexOf("sha384") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA384;
        else if (sbr.indexOf("sha512") != -1)
            strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA512;
        else
        {
            //uncaught
            MySystem.s_printOutWarning(this, strMethod, "uncaught certificate signature algorithm, strCrtSigAlgo=" + strCrtSigAlgo);
            
            
            
            // ----
            // show option dialog

            Object[] objsOptions = 
            { 
                "SHA1",
                "SHA224",
                "SHA256",
                "SHA384",
                "SHA512",
                "MD5",
                "RIPEMD128", 
                "RIPEMD160", 
                "RIPEMD256"
            }; 

            Object objInitialValue = objsOptions[0]; // "Cancel"  

            String strMessageBody = "Unknown certificate signature algorithm: " + strCrtSigAlgo;

            strMessageBody += "\n\n" + "Continue anyway?"; 
            strMessageBody += "\n" + "Please choose a digest option below"; 


            String strResult = OPAbstract.s_showQuestionInputDialog(
                _frmOwner_, strMessageBody, objsOptions, objInitialValue);

            if (strResult == null) // "Abort"
            {
                MySystem.s_printOutTrace(strMethod, "action cancelled");
                return false;
            }

            // -----
            strResult = strResult.trim();

            if (strResult.length() < 1)
            {
                MySystem.s_printOutExit(strMethod, "strResult.length()<1: " + strResult);
            }

            if (strResult.equalsIgnoreCase((String) objsOptions[0]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA1;
            else if (strResult.equalsIgnoreCase((String) objsOptions[1]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA224;
            else if (strResult.equalsIgnoreCase((String) objsOptions[2]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA256;
            else if (strResult.equalsIgnoreCase((String) objsOptions[3]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA384;
            else if (strResult.equalsIgnoreCase((String) objsOptions[4]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_SHA512;
            else if (strResult.equalsIgnoreCase((String) objsOptions[5]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_MD5;
            else if (strResult.equalsIgnoreCase((String) objsOptions[6]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_RIPEMD128;
            else if (strResult.equalsIgnoreCase((String) objsOptions[7]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_RIPEMD160;
            else if (strResult.equalsIgnoreCase((String) objsOptions[8]))
                strCmsSigGenDigest = CMSSignedGenerator.DIGEST_RIPEMD256;
            else
            {
                MySystem.s_printOutExit(strMethod, "uncaught value, strResult=" + strResult);
            }

            
        }
        
        // end check for valid "CMSSignedGenerator.DIGEST_[xxx]"
        
        if (! _doSignFile(kstOpen, fleSaveSig, fleOpenData, pkyPrivate, strAliasKpr, strCmsSigGenDigest))
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

    protected KTLKprOpenSigDetOCmsAbs(
        Frame frmOwner, 
        
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveSig, // signature to save
        String strPathAbsFileSaveCrt, // certificate to save, optional
        
        String strProviderKst,
        boolean blnDataEncapsulated
        )
    {
        super(
            frmOwner, 
         
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenData,
            strPathAbsFileSaveSig,
            strPathAbsFileSaveCrt,
            strProviderKst
            );
            
         
        this._blnDataEncapsulated = blnDataEncapsulated;
    }
    
    // -------
    // private
    
    private boolean _blnDataEncapsulated = false;
    
    private boolean _doSignFile(
            KeyStore kstOpen,
            File fleSaveSig, 
            File fleOpenData, 
            PrivateKey pkyPrivate,
            String strAlias,
            String strCmsSigGenDigest
            //String strCrtSigAlgo
            )
    {
        String strMethod = "_doSignFile(...)";
        
        try
        {
            CMSSignedData cms = _getSignaturePkcs7(kstOpen, strAlias, pkyPrivate, fleOpenData, strCmsSigGenDigest);
            byte[] bytsSignaturePkcs7 = cms.getEncoded();
            KTLKprOpenSigDetOCmsAbs.s_byteArrayToFile(bytsSignaturePkcs7, fleSaveSig);
            
            //TimeStampToken tst = KTLKprOpenSigDetOCmsAbs._s_getTimeStampToken(bytsSignaturePkcs7, 1);
            //KTLKprOpenSigDetOCmsAbs.s_byteArrayToFile(tst.getEncoded(), this._strPathAbsSigTsaOut);
       
            //CMSSignedData cmsTsa = KTLKprOpenSigDetOCmsAbs._s_addTimestamp(cms);
            //KTLKprOpenSigDetOCmsAbs.s_byteArrayToFile(cmsTsa.getEncoded(), this._strPathAbsSigTsaOut);
            
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
    
    private CMSSignedData _getSignaturePkcs7(
            KeyStore kstOpen,
            String strAliasKpr,
            PrivateKey pkyPrivate,
            File fleOpenData,
            String strCmsSigGenDigest
            )
        throws Exception
    {
        java.security.cert.Certificate[] crtsChain = kstOpen.getCertificateChain(strAliasKpr);
        
        ArrayList certList = new ArrayList();
        
        for (int i=0; i<crtsChain.length; i++)
            certList.add(crtsChain[i]);
        
        CertStore cseCerts = CertStore.getInstance("Collection",
           new CollectionCertStoreParameters(certList), KTLAbs.f_s_strProviderKstBC);
        
        //PrivateKey privKey = _getPrivateKey(kstOpen);
        X509Certificate crt = (X509Certificate) kstOpen.getCertificate(strAliasKpr);
        
        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
        gen.addSigner(pkyPrivate, crt, strCmsSigGenDigest);
        gen.addCertificatesAndCRLs(cseCerts);

        InputStream ism = new FileInputStream(fleOpenData);
        byte[] bytsData = KTLKprOpenSigDetOCmsAbs._s_streamToByteArray(ism);
        
        CMSProcessable preData = new CMSProcessableByteArray(bytsData);
        
        
        CMSSignedData sdaSignature = gen.generate(preData, this._blnDataEncapsulated, KTLAbs.f_s_strProviderKstBC);
        
        return sdaSignature;
    }
    
    private boolean _writeCrtBinary(
        X509Certificate crtX509FirstInChain,
        PrivateKey pkyPrivate,
        File fleSaveCrt
        )
    {
        String strMethod = "_writeCrtBinary(...)";
        
        byte[] bytsCrt = UtilCrtX509Pkcs7.s_generateCrt(
            super._frmOwner_, 
            crtX509FirstInChain,
            KTLAbs.f_s_strProviderKstBC);

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
        

        if (! _writeCrtBinary(crtX509FirstInChain, pkyPrivate, fleSaveCrt))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
       
        
        // ending
        return true;
    }
}

