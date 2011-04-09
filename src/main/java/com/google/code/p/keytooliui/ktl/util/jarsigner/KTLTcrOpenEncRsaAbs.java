package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . ?

    "Tcr" for "Trusted certificate" entry
    

**/

import java.security.PublicKey;
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

public abstract class KTLTcrOpenEncRsaAbs extends KTLTcrOpenEncAbs
{
    // ------------------
    // protected abstract
    
    protected abstract KeyStore _getKeystoreOpen_(File fleOpen);
    
    protected abstract boolean _doJobSelectTcr_(
        File fleOpenData,
        File fleSaveData,
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
        . get fileSave Enc (MEMO: check for files prior to open up trusted certificate entries selection dialog)
        . open keystore
        . if no entry in keystore, show warning dialog, then return false
        . if no entry of type "Trusted certificate" in keystore, show warning dialog, then return false
        . fill in table with entrie
        . show dialog trusted certificate select, to get:
          . alias trusted certificate
        . get X509 trusted certificate 
        . generate certificate ?string from trusted certificate
        
        . write Enc string to fileSave
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
        // . get fileSave Enc
        
        if (super._strPathAbsFileSaveData_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileSaveData_");
        }
        
        File fleOpenData = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsFileOpenData_//,
            //true // blnShowDlgOverwrite
            );
        
        if (fleOpenData == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenData");
            return false;
        }
        
        File fleSaveData = UtilJsrFile.s_getFileSave(
            super._frmOwner_, super._strPathAbsFileSaveData_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveData == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveData");
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
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
             kstOpen, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            kstOpen, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
                
        
        if (! _doJobSelectTcr_(
            fleOpenData,
            fleSaveData,
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
        . get fileSave Enc (MEMO: check for files prior to open up trusted certificate entries selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . fill in table entries
        . show dialog trusted certificate select, to get:
          . alias
        . get trusted certificate
        . generate Enc string from tcr
        
        . write Enc string to fileSave
    **/
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasTcr,
        File fleOpenData,
        File fleSaveData
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasTcr, fleOpenData, fleSaveData)";
        
        if (kstOpen==null || strAliasTcr==null || fleOpenData==null || fleSaveData==null)
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
            
            OPAbstract.s_showDialogError(super._frmOwner_, strBody);
            
            return false;
        }
        
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
        
        try
        {
            // should check for valid RSA
            int intSizeFileInput = (int) fleOpenData.length();
            
            boolean blnOk = KTLAbs._s_can_encryptRsa_(
                    super._frmOwner_,  crtX509, intSizeFileInput);
            
            if (! blnOk)
            {
                // should increase key size
                return false;
            }

            // -----
            
            PublicKey pky = crtX509.getPublicKey(); 
            InputStream ism = new FileInputStream(fleOpenData);
            OutputStream osm = new FileOutputStream(fleSaveData);
            KTLAbs._s_encryptRsa_(pky, ism, osm, intSizeFileInput, this._strInstanceCipherAlgo);      
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "got exc");
            
            String strBody = "Failed. Got exception."; 
            strBody += "\n" + exc.getMessage();
            
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            
            return false;
        }
        
        
       
       
        // ending
        return true;
    }

    protected KTLTcrOpenEncRsaAbs(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData,

        
        String strProviderKst,
        String strInstanceCipherAlgo
        )
    {
        super(
            frmOwner, 
  
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenData,
            strPathAbsFileSaveData,
            strProviderKst
            );
            
         this._strProviderSig = KTLAbs.f_s_strProviderKstBC; // should be final static 
         this._strInstanceCipherAlgo = strInstanceCipherAlgo;
    }
    
    // -------
    // private
    
    private String _strProviderSig = null;
    private String _strInstanceCipherAlgo = null;
    private boolean _blnFormatEncPrintable = false;
}