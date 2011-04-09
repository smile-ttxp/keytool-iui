/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprOpenCrtInKPAbs
    . KTLKprOpenCrtInPkcs12

    "Kpr" for "keypair"
    


**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// ----
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Key;
// --
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public abstract class KTLKprOpenCrtInAbs extends KTLKprOpenCrtAbs
{
    // ------------------
    // protected abstract
    
    protected abstract KeyStore _getKeystoreOpen_(File fleOpenKst);
    protected abstract DTblsKstSelPKAbs _getDialogSelectKpr_(KeyStore kstOpen);
    
    // ------
    // public
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileOpen crt (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . !! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        
        
        
        
        !!!!!!! WHAT'S NEXT !!!!!!!
    
    **/
    
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        
        super._setEnabledCursorWait_(true);
        
        
        // ----
        // if using CACertKeystore, ==> error, not done yet, exiting
        
        if (this._blnUseCACertKeystore)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "this._blnUseCACertKeystore");
        }   
        
        // ---
        // check cert file format (TODO: check for supported cert format by BC)
        // if format = "other", show warning dialog "pending", then return false
        // if format != PKCS#7, exiting
        
        if (super._strFormatFileIO_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strFormatFileIO_");
        }
        

        boolean blnGotIt = false;

        for (int i=0; i<KTLAbs.f_s_strsFormatFileCertImportBc.length; i++)
        {
            String str = KTLAbs.f_s_strsFormatFileCertImportBc[i];
            
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
        // . get fileOpen CA cert
        
        if (super._strPathAbsFileIO_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileIO_");
        }
        
        File fleOpenCrt = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsFileIO_);
        
        if (fleOpenCrt == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenCrt");
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
        
        // ----

       
        DTblsKstSelPKAbs dlg = _getDialogSelectKpr_(kstOpen);
        
        if (dlg == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil dlg");
        }
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlg.load(
                
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
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlg.setVisible(true);
        
        // ---
        char[] chrsPasswdKpr = dlg.getPassword();
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, aborted by user");
            return false;
        }
        
        
        String strAliasKpr = dlg.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }

        super._setEnabledCursorWait_(true);
        
        if (! this._doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleOpenKst, fleOpenCrt))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil pkyKeyPrivate");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        // ----
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    private boolean _blnUseCACertKeystore = false; // PENDING! remaining false!
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileOpen crt (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . !! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        
        
        
        
    
    **/
    
    
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasKpr,
        char[] chrsPasswdKpr,
        File fleOpenKst,
        File fleOpenCrt 
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleOpenKst, fleOpenCrt)";
    
        if (kstOpen==null || strAliasKpr==null || chrsPasswdKpr==null || fleOpenKst==null || fleOpenCrt==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        // ----
        // get first old cert in the array (active keypair in active keystore)
        
        // MEMO: tests already done while selecting alias and password, if any error, exiting!
        
        X509Certificate crtX509FirstInChainOld = KTLKprOpenCrtAbs.s_getCertX509FirstInChain(kstOpen, strAliasKpr);
        
        if (crtX509FirstInChainOld == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtX509FirstInChainOld");
            
            MySystem.s_printOutError(this, strMethod, "failed");
			String strBody = "Failed to get original first in chain X509 certificate."; 
            strBody += "\n\n" + "More: see your session.log";
            OPAbstract.s_showDialogError(super._frmOwner_, strBody);
            return false;
        }
        
        
        // ----
        // load the new CA certs
        
        X509Certificate[] crtsX509ToImport = UtilCrtX509.s_load(
            super._frmOwner_,
            fleOpenCrt);
        
        if (crtsX509ToImport == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsX509ToImport");
            return false;
        }
        
        // ----
        // order new CA certs into a chain
        
        crtsX509ToImport = UtilCrtX509.s_orderChain(crtsX509ToImport);
        
        if (crtsX509ToImport == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsX509ToImport");
            return false;
        }
        
        // ----
        // compare public key of the first cert in the array of newCAcert & oldCertActiveKeypair
        
        PublicKey keyPublicFirstOld = crtX509FirstInChainOld.getPublicKey();
        PublicKey keyPublicFirstNew = crtsX509ToImport[0].getPublicKey();
        
        if (! (keyPublicFirstOld.equals(keyPublicFirstNew)))
        {
            MySystem.s_printOutWarning(this, strMethod, "! (keyPublicFirstOld.equals(keyPublicFirstNew)");
            
            String strBody = "CA certificate reply does not match the selected keypair entry.";
            strBody += "\n\n";
            strBody += "File location:";
            strBody += "\n";
            strBody += "  ";
            strBody += fleOpenCrt.getAbsolutePath();
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, strBody);
            
            // --
            return false;
        }
        
        
        // If the CA Certs keystore is to be used and it has yet to be loaded
        // then do so
        if (this._blnUseCACertKeystore && this._kstCaCerts==null)
        {
            MySystem.s_printOutExit(this, strMethod, "this._blnUseCACertKeystore && this._kstCaCerts==null");
        }
        
        
        // -- check for supported CA cert reply file format
        
        if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtOther.toLowerCase()) == 0)
        {
            MySystem.s_printOutFlagDev(this, strMethod, "PENDING: format file cert = \"other\""); 
            
            String strBody = "PENDING: format file cert = \"other\"";
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, strBody);
  
            return false;
        }
        
        // ----
        // if NOT CA cert format is "PKCS#7" ==> error, exiting
        
        if (super._strFormatFileIO_.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPkcs7.toLowerCase()) != 0)
            MySystem.s_printOutExit(this, strMethod, "wrong value, super._strFormatFileIO_=" + super._strFormatFileIO_);
        
        // ----
        // if new CA crtsX509ToImport length < 1, should not be a PKCS#7 cert format (!!! TO BE VERIFIED !!!!) ==> warning dialog, in progress, return false
        
        X509Certificate[] crtsNew = crtsX509ToImport;
        
        // check for PKCS#7 CA cert reply
        if (crtsX509ToImport.length < 2)
        {
            //MySystem.s_printOutExit(this, strMethod, "crtsX509ToImport.length < 2");
            MySystem.s_printOutWarning(this, strMethod, "crtsX509ToImport.length < 2, crtsX509ToImport.length=" + crtsX509ToImport.length
                    + ", ignoring");
        }
        
        // get private key
        // is password corrrect?
        Key pkyKeyPrivate = UtilKstAbs.s_getKey(
            super._frmOwner_, 
            kstOpen,
            strAliasKpr,
            chrsPasswdKpr);

        if (pkyKeyPrivate == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil pkyKeyPrivate");
            return false;
        }
        
        // Replace the certificate chain
        
        if (! UtilKstAbs.s_setKeyEntry(super._frmOwner_, 
            kstOpen, strAliasKpr, pkyKeyPrivate, chrsPasswdKpr, crtsNew))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        // save keystore
        if (super._chrsPasswdKst_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._chrsPasswdKst_");
            return false;
        }        
        
        // ----
        
        if (! super._saveKeyStore_(kstOpen, fleOpenKst, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        return true;
    }
    

    protected KTLKprOpenCrtInAbs(
        Frame frmOwner, 
 
        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenCrt, // Cert to import
        
        String strFormatFileCert, // should be "PKCS#7", or "other"
       
        String strProviderKst 
        
        )
    {
        super(
            frmOwner, 
  
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenCrt,
            strProviderKst,
            strFormatFileCert
            );
    }
    
    /*
        for each:
        should be self-signed, RSA-DSA, X.509
    */
    private Boolean[] _getBoosElligible(
        Boolean[] boosEntryKpr,
        Boolean[] boosSelfSigned,
        String[] strsAlgoKeyPubl,
        Boolean[] boosTypeCertX509
        )
    {
        String strMethod = "_getBoosElligible(...)";
        
        if (boosEntryKpr==null || boosSelfSigned==null || strsAlgoKeyPubl==null || boosTypeCertX509==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosElligible = new Boolean[boosSelfSigned.length];
        
        for (int i=0; i<boosSelfSigned.length; i++)
        {
            boolean blnOk = true;
            
            
            if (boosEntryKpr[i].booleanValue() == false)
            {
                blnOk = false;
            }
            
            // should be self-signed (not a CA cert)
            if (boosSelfSigned[i].booleanValue() == false)
            {
                blnOk = false;
            }
            
            // also should be of type RSA-DSA
            else if (strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairRsa.toLowerCase()) != 0 &&
                     strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairDsa.toLowerCase()) != 0)
            {
                blnOk = false;
            }
            
            // finally certificate should be of type X509
            else if (boosTypeCertX509[i].booleanValue() == false)
            {
                blnOk = false;
            }
           
            
            boosElligible[i] = new Boolean(blnOk);
        }

        // ----
        return boosElligible;
    }
    
    // -------
    // PRIVATE
    
    // !!!!
    
    private KeyStore _kstCaCerts = null;     // PENDING! remaining nil!
    // !!!!
    


}