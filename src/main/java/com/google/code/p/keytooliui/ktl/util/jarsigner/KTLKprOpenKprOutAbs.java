
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprOpenKprOutKPAbs ==> "KP" means "Keypair with Password"
    . KTLKprOpenKprOutPkcs12

    "Kpr" for "keypair"
    

**/

/**
 *
 *note: some codes come from:
 *https://bugs.internet2.edu/jira/secure/attachment/10050/ExtKeyTool.java

**/

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

abstract public class KTLKprOpenKprOutAbs extends KTLKprOpenKprAbs
{
    // ---------------------------
    // private static final string
    
    private static final String _STR_PROVIDERSIGINSTANCE = KTLAbs.f_s_strProviderKstBC;

    
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpen);
    
    abstract protected boolean _doJobSelectKpr_(
        File fleSaveKpr,
        File fleSaveCrts,
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
        // check private key file format
        
        if (super._strFormatFileKpr_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strFormatFileKpr_");
        }
        
        /*boolean blnGotIt = false;

        for (int i=0; i<KTLAbs.f_s_strsFormatFileCertOutBc.length; i++)
        {
            String str = KTLAbs.f_s_strsFormatFileCertOutBc[i];
            
            if (str.toLowerCase().compareTo(super._strFormatFileKpr_.toLowerCase()) == 0)
            {
                blnGotIt = true;
                break;
            }
        }
        
        if (! blnGotIt)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "! blnGotIt, super._strFormatFileKpr_=" + super._strFormatFileKpr_);
        }*/
        
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
        // . get fileSave private key
        
        if (super._strPathAbsFileKpr_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileKpr_");
        }
        
        File fleSaveKpr = UtilJsrFile.s_getFileSave(
            super._frmOwner_,  super._strPathAbsFileKpr_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveKpr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveKpr");
            return false;
        }
        
        // ----
        // . get fileSave certs chain
        
        if (super._strPathAbsFileCrts_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileCrts_");
        }
        
        File fleSaveCrts = UtilJsrFile.s_getFileSave(
            super._frmOwner_, super._strPathAbsFileCrts_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveCrts == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveCrts");
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
            fleSaveKpr,
            fleSaveCrts,
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
        File fleSaveKpr,
        File fleSaveCrts
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleSaveKpr, fleSaveCrts)";
        
        if (kstOpen==null || strAliasKpr==null || chrsPasswdKpr==null || fleSaveKpr==null || fleSaveCrts==null)
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
        
        
        try
        {
            PrivateKey pkyPrivate = (PrivateKey) key;
            _writeKpr(pkyPrivate, fleSaveKpr);
            
            X509Certificate[] crtsX509Ordered = UtilCrtX509.s_getX509CertificateChain(
                kstOpen, strAliasKpr, 
                true // blnOrderChain
                );
            
            if (crtsX509Ordered == null)
            {
                MySystem.s_printOutError(strMethod, "nil crtsX509Ordered");
                return false;
            }

            if (crtsX509Ordered.length < 1)
            {
                MySystem.s_printOutError(strMethod, "crtsX509Ordered.length < 1");
                return false;
            }
            
            _writeCrts(crtsX509Ordered, fleSaveCrts);
            
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
                
            String strBody = "Got exception:";
            strBody += "\n  " + exc.getMessage();

            strBody += "\n\n" + "More: see your session.log";

            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            return false;
        } 
       
        // ending
        return true;
    }
    
    private void _writeKpr(
            PrivateKey pkyPrivate,
            File fleSaveKpr
            )
        throws Exception
    {
        if (super._strFormatFileKpr_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileKprPem.toLowerCase()) == 0)
            _writeKprPem(pkyPrivate, fleSaveKpr);
        else if (super._strFormatFileKpr_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileKprDer.toLowerCase()) == 0)
            _writeKprDer(pkyPrivate, fleSaveKpr);
        else
            throw new Exception("Uncaught private key file format: " + super._strFormatFileKpr_);
    }
    
    private void _writeKprPem(
            PrivateKey pkyPrivate,
            File fleSaveKpr)
        throws Exception
    {
        FileWriter fwr = new FileWriter(fleSaveKpr);
        org.bouncycastle.openssl.PEMWriter wrt = new org.bouncycastle.openssl.PEMWriter(fwr);

        wrt.writeObject(pkyPrivate);
        wrt.close();
        wrt = null;
    }
    
    private void _writeKprDer(
            PrivateKey pkyPrivate,
            File fleSaveKpr)
        throws Exception
    {
        FileOutputStream fos = new FileOutputStream(fleSaveKpr);
        PrintStream psm = new PrintStream(fos);
        
        psm.write(pkyPrivate.getEncoded());
        psm.close();
        psm = null;
    }
    
    private void _writeCrts(
            X509Certificate[] crtsX509,
            File fleSaveKpr
            )
        throws Exception
    {
        if (super._strFormatFileCrts_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileKprPem.toLowerCase()) == 0)
            _writeCrtsPem(crtsX509, fleSaveKpr);
        else if (super._strFormatFileCrts_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileKprDer.toLowerCase()) == 0)
            _writeCrtsDer(crtsX509, fleSaveKpr);
        else
            throw new Exception("Uncaught certificates chain file format: " + super._strFormatFileCrts_);
    }

    /*
     *limitations: first cert in chain only
     */
    private void _writeCrtsPem(
            X509Certificate[] crtsX509,
            File fleSaveKpr)
        throws Exception
    {
        FileWriter fwr = new FileWriter(fleSaveKpr);
        org.bouncycastle.openssl.PEMWriter wrt = new org.bouncycastle.openssl.PEMWriter(fwr);
        
        for (int i=0; i<crtsX509.length; i++)
            wrt.writeObject(crtsX509[i]);
        
        wrt.close();
        wrt = null;
    }
    
    private void _writeCrtsDer(
            X509Certificate[] crtsX509,
            File fleSaveCrts)
        throws Exception
    {
        byte[] bytsCrts = UtilCrtX509Der.s_generateCrts(
                super._frmOwner_, 
                crtsX509,
                KTLKprOpenKprOutAbs._STR_PROVIDERSIGINSTANCE);
        
        if (bytsCrts == null)
            throw new Exception("Failed to get bytes from certificates chain");
    
        FileOutputStream fos = new FileOutputStream(fleSaveCrts);
        fos.write(bytsCrts);
        fos.close();
        fos = null;
    }
    
  
    
    /*private boolean _writeCrtsAscii(
        X509Certificate[] crts,
        File fleSaveCrts)
        throws
            Exception
    {
        String strMethod = "_writeCrtsAscii(...)";
        
        String str = null;
        
        if (super._strFormatFileCrts_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) != 0)
        {
            MySystem.s_printOutExit(this, strMethod, 
                "DEV CODE ERROR, uncaught ascii private key format, super._strFormatFileCrts_=" +
                super._strFormatFileCrts_);
            
            return false; // statement never reached
        }
        
        
        FileOutputStream fos = new FileOutputStream(fleSaveCrts);
        PrintStream psm = new PrintStream(fos);

        psm.println("---- BEGIN CERTIFICATES CHAIN ----");
        
        for (int i=0; i<crts.length; i++)
        {
            String b64 = new String( Base64.encode( crts[i].getEncoded()) );
            char[] b64split = b64.toCharArray();

            for (int j=0; j<b64split.length; j++) 
            {
                if ( ( j!=0 && ((j+1)%64.0)==0 ) || ((j+1)==b64split.length) ) 
                {
                    psm.println(b64split[j]);
                }

                else 
                {
                    psm.print(b64split[j]);
                }
            }
        }

        psm.println("---- END CERTIFICATES CHAIN ----");

        psm.close();
       
        
        return true;
    }*/
    
   
    
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
    
  

    protected KTLKprOpenKprOutAbs(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveKpr, 
        String strPathAbsFileSaveCrts, 
        String strFormatFileKpr,
        String strFormatFileCrts,
            
        String strProviderKst
        )
    {
        super(
            frmOwner, 

            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileSaveKpr,
            strPathAbsFileSaveCrts,
            strProviderKst,
            strFormatFileKpr,
            strFormatFileCrts    
            );
            
         
         
         if (super._strFormatFileKpr_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileKprPem.toLowerCase()) == 0)
            this._blnFormatKprPrintable = true;
    }
    
    // -------
    // private
    
    private boolean _blnFormatKprPrintable = false;
    
   
}