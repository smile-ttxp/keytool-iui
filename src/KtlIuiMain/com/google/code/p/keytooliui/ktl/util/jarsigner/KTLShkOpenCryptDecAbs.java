package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    

**/

import java.nio.ByteBuffer;
import java.security.cert.CertificateExpiredException;
import javax.crypto.SecretKey;
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

abstract public class KTLShkOpenCryptDecAbs extends KTLShkOpenCryptAbs
{
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectShk_(
        File fleSaveDecrypted,
        File fleOpenCrypted,
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
        . WRONG !!!!!!!!!! if no entry of type "RSA-self-cryptned" in keystore, show warning dialog, then return false
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
        // . get fileOpen Encrypted Data
        
        if (super._strPathAbsFileDataOpen_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileDataOpen_");
        }
        
        File fleOpenCrypt = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsFileDataOpen_//,
            //false //true // blnShowDlgOverwrite
            );
        
        if (fleOpenCrypt == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenCrypt");
            return false;
        }
        
        // ----
        // . get fileSave Data
        
        if (super._strPathAbsFileDataSave_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileDataSave_");
        }
        
        File fleSaveDecrypted = UtilJsrFile.s_getFileSave(
            super._frmOwner_,  super._strPathAbsFileDataSave_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveDecrypted == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveDecrypted");
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
                
        
        if (! _doJobSelectShk_(
            fleSaveDecrypted,
            fleOpenCrypt,
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
        . WRONG LINE !! if no entry of type "RSA-self-cryptned" in keystore, show warning dialog, then return false
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
        String strAliasShk,
        char[] chrsPasswdShk,
        File fleSaveDecrypted,
        File fleOpenCrypted
        )
    {
        
        String strMethod = "_doJob_(kstOpen, strAliasShk, chrsPasswdShk, fleSaveDecrypted, fleOpenCrypted)";
        
        if (kstOpen==null || strAliasShk==null || chrsPasswdShk==null || fleSaveDecrypted==null || fleOpenCrypted==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        // get private key
        // is password corrrect?
 
        Key key = UtilKstAbs.s_getKey(
            super._frmOwner_, 
   
            kstOpen,
            strAliasShk,
            chrsPasswdShk);
        
        if (key == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil key");
            // TODO: show error dialog
            return false;
        }
        
        // ----
        
        
        
        // ----
        
        try
        {
            SecretKey sky = (SecretKey) key;
            InputStream ism = new FileInputStream(fleOpenCrypted);
            OutputStream osm =new FileOutputStream(fleSaveDecrypted);
            String strInstanceCipher = key.getAlgorithm();
            
            KTLShkAbs._s_decrypt_(sky, ism, osm, strInstanceCipher);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, exc.getMessage());
        
            String strBody = "Failed to decrypt file."; 
            strBody += "\n" + exc.getMessage();
            
            strBody += "\n\n" + "More: see your session.log file";
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            
            return false;
        }
        
        String strBody = "Data file successfully decrypted with secret key aliased:";
        strBody += "\n  " + strAliasShk;
        strBody += "\n\nDecrypted file saved as:";
        strBody += "\n  " + fleSaveDecrypted.getAbsolutePath();
        
        OPAbstract.s_showDialogInfo(super._frmOwner_, 
      
            strBody);
        
        
        // ending
        return true;
    }
  

    protected KTLShkOpenCryptDecAbs(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenCrypted,
        String strPathAbsFileSaveDecrypted, // cryptnature to save
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 
        
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenCrypted,
            strPathAbsFileSaveDecrypted,
            strProviderKst
            );
    }
    
    // -------
    // private

}

