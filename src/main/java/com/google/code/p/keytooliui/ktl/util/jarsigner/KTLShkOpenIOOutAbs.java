package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    

**/

import java.nio.ByteBuffer;
import java.security.cert.CertificateExpiredException;
import javax.crypto.SecretKey;
import javax.security.cert.CertificateNotYetValidException;
import org.bouncycastle.util.encoders.Base64;
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

public abstract class KTLShkOpenIOOutAbs extends KTLShkOpenIOAbs
{
    // ------------------
    // protected abstract
    
    protected abstract KeyStore _getKeystoreOpen_(File fleOpen);
    
    protected abstract boolean _doJobSelectShk_(
        File fleSaveIO,
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
        . WRONG !!!!!!!!!! if no entry of type "RSA-self-IOned" in keystore, show warning dialog, then return false
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
        // . get fileSave IO
        
        if (super._strPathAbsFileData_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileData_");
        }
        
        File fleSaveIO = UtilJsrFile.s_getFileSave(
            super._frmOwner_,  super._strPathAbsFileData_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveIO == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveIO");
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
            fleSaveIO,
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
        . WRONG LINE !! if no entry of type "RSA-self-IOned" in keystore, show warning dialog, then return false
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
        File fleSaveIO
        )
    {
        
        String strMethod = "_doJob_(kstOpen, strAliasShk, chrsPasswdShk, fleSaveIO)";
        
        if (kstOpen==null || strAliasShk==null || chrsPasswdShk==null || fleSaveIO==null)
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
        

         try
         {     
             SecretKey sky = (SecretKey) key;   
        
            if (this._strFormatFileShk_.compareToIgnoreCase("DER") == 0)
            {         
                KTLShkAbs._writeKey_(sky, fleSaveIO);
            }
        
            else if (this._strFormatFileShk_.compareToIgnoreCase("PEM") == 0)
            {
                PrintStream psm = new PrintStream(new FileOutputStream(fleSaveIO));
                 
                psm.println("-----BEGIN PRIVATE KEY-----");
                
                String str = new String( Base64.encode( key.getEncoded()) );
                char[] chrsSplit = str.toCharArray();
                
                for (int i=0;i<chrsSplit.length;i++) 
                {
                        if ( ( i!=0 && ((i+1)%64.0)==0 ) || ((i+1)==chrsSplit.length) ) {
                                psm.println(chrsSplit[i]);
                        }
                        else {
                                psm.print(chrsSplit[i]);
                        }
                }
                
                psm.println("-----END PRIVATE KEY-----");
                psm.close();
                psm = null;
                                
                 /* NOT WORKING
                FileWriter fwr = new FileWriter(fleSaveIO);
                org.bouncycastle.openssl.PEMWriter wrt = new org.bouncycastle.openssl.PEMWriter(fwr);

                wrt.writeObject(sky);
                wrt.close();
                wrt = null;
                */
            }
        
            else
            {
                MySystem.s_printOutError(this, strMethod, "uncaught this._strFormatFileShk_:" + this._strFormatFileShk_);

                String strBody = "Uncaught secret key file format: " + this._strFormatFileShk_; 

                OPAbstract.s_showDialogError(super._frmOwner_,  strBody);

                return false;
            }
        
        }
    
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, exc.getMessage());

            String strBody = "Failed to export file."; 
            strBody += "\n" + exc.getMessage();

            strBody += "\n\n" + "More: see your session.log file";

            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);

            return false;
        }
            

        // ----
        
  
       
        
        // ending
        return true;
    }
    


    protected KTLShkOpenIOOutAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveIO, // IOnature to save
        String strFormatFileShk,
        String strProviderKst
        )
    {
        super(
            frmOwner, 
  
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileSaveIO,
            strProviderKst
            );
        
        this._strFormatFileShk_ = strFormatFileShk;
    }
    
    // -------
    // private
    
    protected String _strFormatFileShk_ = null;

}
