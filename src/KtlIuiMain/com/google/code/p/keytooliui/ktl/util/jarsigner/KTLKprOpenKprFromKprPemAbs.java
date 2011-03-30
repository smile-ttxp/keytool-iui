package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
 *
 *note: some codes come from:
 *https://bugs.internet2.edu/jira/secure/attachment/10050/ExtKeyTool.java

**/

import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.security.cert.CertificateNotYetValidException;
import org.bouncycastle.jce.interfaces.ECKey;
import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.DPasswordOpen;
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

abstract public class KTLKprOpenKprFromKprPemAbs extends KTLKprOpenKprFromKprAbs
{
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectKpr_(
        File fleOpenKpr,
        File fleOpenCrts,
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
        . get fileOpen crt (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . WRONG !!!!!!!!!! if no entry of type "RSA-self-ned" in keystore, show warning dialog, then return false
        . fill in table keypair
        . WRONG !!!!!!!!!  show dialog keypair select RSA self, to get:
          . alias keypair
          . password keypair
        . get private key
        . get first X509 cert in cert chain
        . generate CRT string from cert and private key
        
        . write crt string to fileOpen
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
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        // . get fileOpen Kpr
        
        if (super._strPathAbsFileKpr_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileKpr_");
        }
        
        File fleOpenKpr = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsFileKpr_//,
            //true // blnShowDlgOverwrite
            );
        
        if (fleOpenKpr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenKpr");
            return false;
        }

        // ----
        // . get fileOpen Kpr
        
        if (super._strPathAbsFileCrts_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileCrts_");
        }
        
        File fleOpenCrts = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsFileCrts_//,
            //true // blnShowDlgOverwrite
            );
        
        if (fleOpenCrts == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenCrts");
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
            fleOpenKpr,
            fleOpenCrts,
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
    
    private X509Certificate[] _readCrts(
            File fleOpenCrts,
            PrivateKey pkyPrivate
            )
        throws Exception
    {
         return _readCrtsPem(fleOpenCrts);
    }
    
   
    
    private X509Certificate[] _readCrtsPem(
            File fleOpenCrts
            )
        throws Exception
    {
        java.io.FileReader frr = new java.io.FileReader(fleOpenCrts);
        org.bouncycastle.openssl.PEMReader rdr = new org.bouncycastle.openssl.PEMReader(frr);
        
        Vector vec = new Vector();
        
        
        while (true)
        {
            Object obj = rdr.readObject();

            if (obj == null)
                break;

            if (!(obj instanceof X509Certificate))
            {
                throw new Exception("obj: not an instance of X509Certificate");
            }

            X509Certificate crt = (X509Certificate) obj;
            vec.add(crt);
        }
        
        rdr.close();
        
        if (vec.size() < 1)
        {
            throw new Exception("No X509 Certificate found in certificates chain file!");
        }
        
        X509Certificate[] crts = new X509Certificate[vec.size()];
        
        for (int i=0; i<vec.size(); i++)
            crts[i] = (X509Certificate) vec.elementAt(i);
        
        return crts;
    }
    
    // if returning nil, user could have given wrong password
    private PrivateKey _readKprPem(
            File fleOpenKpr
            )
        throws Exception
    {
        String strMethod = "_readKprPem(...)";
        
        // ----
        // get password
        
        /*char[] chrsPasswdKst = null;
        
        
        // open up a passwordOpen dialog
                
        DPasswordOpen dlgPasswordKst = null;
        
        // ---
        
        dlgPasswordKst = new DPasswordOpen(
            super._frmOwner_, super._strTitleAppli_);
                    
        String strTitleSuffixDlg = " ";
        strTitleSuffixDlg += "for";
        strTitleSuffixDlg += " ";
        strTitleSuffixDlg += "private key PEM file";
                
                
        dlgPasswordKst.setTitle(dlgPasswordKst.getTitle() + strTitleSuffixDlg);
                    
        if (! dlgPasswordKst.init())
            MySystem.s_printOutExit(strMethod, "failed");
                    
        dlgPasswordKst.setVisible(true);
                
        chrsPasswdKst = dlgPasswordKst.getPassword();
                
        if (chrsPasswdKst == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil chrsPasswdKst, user canceled");
            return null;
        }
                
                
        dlgPasswordKst.destroy();
        dlgPasswordKst = null;
        */
        
        
        
        // ----
        
        // IN COMMENTS! NOT NEEDED
        ///MyPasswordFinder mpf = new MyPasswordFinder(this._chrsPasswdFileOpenKpr);

        java.io.FileReader frr = new java.io.FileReader(fleOpenKpr);
        org.bouncycastle.openssl.PEMReader rdr = new org.bouncycastle.openssl.PEMReader(frr, null /*mpf*/);

        Object objKpr = rdr.readObject();
        rdr.close();

        if (!(objKpr instanceof KeyPair))
        {
            throw new Exception("objKpr: not an instance of KeyPair");
        }

        KeyPair kpr = (KeyPair) objKpr;
        return kpr.getPrivate();
    }
    
   
    private PrivateKey _readKpr(
            File fleOpenKpr
            )
        throws Exception
    {
        return _readKprPem(fleOpenKpr);
    }
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileOpen crt (MEMO: check for files prior to open up keypair selection dialog)
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
        
        . write crt string to fileOpen
    **/
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasKpr,
        char[] chrsPasswdKpr,
        File fleOpenKpr,
        File fleOpenCrts
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleOpenKpr, fleOpenCrts)";
        
        if (kstOpen==null || strAliasKpr==null || chrsPasswdKpr==null || fleOpenKpr==null || fleOpenCrts==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
  
        // ----
        
        PrivateKey pky = null;
        X509Certificate[] crtsX509 = null;

        try
        {
            pky = _readKpr(fleOpenKpr);
            
            if (pky == null)
            {
                MySystem.s_printOutWarning(this, strMethod, "nil pky, user may have cancelled!");
                return false;
            }
            
            crtsX509 = _readCrts(fleOpenCrts, pky);
            
            if (crtsX509 == null)
            {
                throw new Exception("Failed to read X509 certificates chain");
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
                
            String strBody = "Got exception:";
            strBody += "\n  " + exc.getMessage();

            strBody += "\n\n" + "More: see your session.log";

            OPAbstract.s_showDialogError(super._frmOwner_, super._strTitleAppli_, strBody);
            return false;
        }

        if (! UtilKstAbs.s_setKeyEntry(
                super._frmOwner_, super._strTitleAppli_, kstOpen,
                    strAliasKpr, pky, chrsPasswdKpr, crtsX509
                ))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --- saving keystore
        
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
        
        if (! super._saveKeyStore_(kstOpen, fleOpenKst, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
        
        // ending
        return true;
    }
    
   
  

    protected KTLKprOpenKprFromKprPemAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenKpr,
        String strPathAbsFileOpenCrts,
            
        //char[] chrsPasswdFileOpenKpr,
        String strProviderKst
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenKpr,
            strPathAbsFileOpenCrts,
            "PEM", // files format
            strProviderKst
            );
        
        //this._chrsPasswdFileOpenKpr = chrsPasswdFileOpenKpr; // needed in case of PEM format file
    }
    
    // -------
    // private
    
    //private char[] _chrsPasswdFileOpenKpr = null;
    

    // beg inner-class
    
    /*class MyPasswordFinder implements org.bouncycastle.openssl.PasswordFinder
    {
        private char[] _chrs = null;
       
        MyPasswordFinder(char[] chrs)
        {
            this._chrs = chrs;
        }
        
        public char[] getPassword() { return this._chrs; }
    }*/
    
    // end inner-class
}
