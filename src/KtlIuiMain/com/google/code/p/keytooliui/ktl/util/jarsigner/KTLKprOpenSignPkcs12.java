/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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

    "Kpr" for "keypair"
    

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

import sun.misc.BASE64Encoder;
//import sun.security.util.SignatureFile;
import sun.security.util.ManifestDigester;

// ----
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.KeyStoreException;
// --
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.security.cert.Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.jar.*;

final public class KTLKprOpenSignPkcs12 extends KTLKprOpenSignAbs
{
    // ------
    // PUBLIC
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . !! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        . fill in table keypair
        . show dialog keypair select RSA self
        
        . ????
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        //if (! _addProviders())
          //  MySystem.s_printOutExit(this, strMethod, "failed");
        
        // ---
        // get file keystore
        
        // memo: keystore should be of type "Pkcs12", provided by "BC", or "SunRsaSign"
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        
        if (super._strPathAbsOpenJarSource_ == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsOpenJarSource_");
        }
        
        
        File fleOpenJarUnsigned = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsOpenJarSource_);
        
        if (fleOpenJarUnsigned == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenJarUnsigned");
            return false;
        }

        // ----
        
        if (super._strPathAbsSaveJarTarget_ == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsSaveJarTarget_");
        }
        
        
        File fleSaveJarSigned = UtilJsrFile.s_getFileSave(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsSaveJarTarget_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveJarSigned == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveJarSigned");
            return false;
        }
        
        // ----
        // open keystore
        
        if (super._chrsPasswdKst_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        
        KeyStore kstOpen = UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKst,
            super._chrsPasswdKst_);
        
        if (kstOpen == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil kstOpen"); // could be wrong password
            return false;
        }
        
        // ----
        
        String strAliasKpr = _getAliasKprPkcs12(kstOpen);
       
        if (strAliasKpr == null) // either aborted or failed
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, either aborted by user or failed");
            return false;
        }
                
        // ----
        // x) get privateKey
        
        PrivateKey keyPrivateKpr = null;
        
        try
        {
            //
            keyPrivateKpr = (PrivateKey) UtilKstPkcs12.s_getKeyProviderAny(
                super._frmOwner_, super._strTitleAppli_,
                kstOpen,
                strAliasKpr,
                super._chrsPasswdKst_,
                super._strProviderKst_);
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excClassCast caught");
        }
        
        
        
        if (keyPrivateKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil keyPrivateKpr");
            return false;
        }  

        // ----
        // x) get X509Certificates
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(
            fleOpenJarUnsigned,
            fleSaveJarSigned,
            kstOpen,
            strAliasKpr,
            keyPrivateKpr))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);

        // ending
        return true;
    }

    public KTLKprOpenSignPkcs12(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type PKCS12 
        char[] chrsPasswdOpenKst,
        String strProviderKst,
        
        String strPathAbsOpenJarSource, // unsigned
        
        // output
        String strPathAbsSaveJarTarget, // to be signed
        String strNameBaseSigFile // nil value allowed
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsOpenKst, 
            chrsPasswdOpenKst,
            strProviderKst,
            
            strPathAbsOpenJarSource,
            strPathAbsSaveJarTarget,
            strNameBaseSigFile
            );
        
    }
    
    // -------
    // PRIVATE
    
    /**
        !!!! SAME CODE AS IN KTLKprSaveFromPkcs12Jks !!!!
    **/
    private String[] _getStrsAliasPkcs12ToKpr(KeyStore kstOpen)
    {
        String strMethod = "_getStrsAliasPkcs12ToKpr(kstOpen)";
        
        String[] strsAliasPkcs12All = UtilKstAbs.s_getStrsAlias(
            super._frmOwner_, 
            super._strTitleAppli_, 
            kstOpen);
        
        if (strsAliasPkcs12All == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAliasPkcs12All");
            return null;
        }
        
        if (strsAliasPkcs12All.length < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "strsAliasPkcs12All.length < 1");
                
            String strBody = "No aliases found in " +
                UtilKstPkcs12.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKst_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
            
            return null;
        }
        
        Vector<String> vec = new Vector<String>();
        
        try
        {
            for (int i=0; i<strsAliasPkcs12All.length; i++)
            {
                if (! kstOpen.isKeyEntry(strsAliasPkcs12All[i]))
                    continue;
                    
                Certificate[] certs = kstOpen.getCertificateChain(strsAliasPkcs12All[i]);
                    
                if (certs == null)
                    continue;
                
                if (certs.length < 1)
                    continue;
                    
                vec.addElement(strsAliasPkcs12All[i]);
            }
        }
              
        catch(KeyStoreException excKeystore)
        {
            excKeystore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeystore caught");
            
            // show dialog
            String strBody = "Got keystore Exception while reading " +
                UtilKstPkcs12.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKst_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
        }
        
        
        // --
        
        if (vec.size() < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "vec.size() < 1");
            
            // show dialog
            String strBody = "No aliases pointing to keypair found in " +
                UtilKstPkcs12.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKst_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
            
            return null;
        }
        
        // ---
        
        String[] strsAliasPkcs12ToKpr = new String[vec.size()];
        
        for (int i=0; i<vec.size(); i++)
            strsAliasPkcs12ToKpr[i] = (String) vec.elementAt(i);
            
        return strsAliasPkcs12ToKpr;
    }
    
    
    
    /**
        !!!! SAME CODE AS IN KTLKprSaveFromPkcs12Jks !!!!
    
    
        check for valid keypairs,
        
        if nothing found, show dialog, then return null
        if a unique valid keypair found, just return it
        else show dialogSelectAliasPointing2ValidKeypair
    **/
    private String _getAliasKprPkcs12(KeyStore kstOpen)
    {
        String strMethod = "_getAliasKprPkcs12(kstOpen)";
        
        super._setEnabledCursorWait_(true);
        
        if (kstOpen == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil kstOpen");
        }
        
        
        /*
        // ----
        // -- get  aliases of keystorePkcs12 source
        
        // ----
        // fill in table keypair
        
        String[] strsAliasPkcs12 = _getStrsAliasPkcs12ToKpr(kstOpen);
        
        if (strsAliasPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAliasPkcs12");
            return null;
        }
        
        
        // select aliasPkcs12
        
        // --
        // get arrays for dialogTableSelectKeypair
        
        Boolean[] boosEntryKprPkcs12 = UtilKstAbs.s_getBoosEntryKpr(
            super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (boosEntryKprPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKprPkcs12");
            return null;
        }
        
        Boolean[] boosEntryTcrPkcs12 = UtilKstAbs.s_getBoosEntryTcr(
            super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (boosEntryTcrPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcrPkcs12");
            return null;
        }
        

        Boolean[] boosSelfSignedCertPkcs12 = UtilKstAbs.s_getBoosSelfSigned(
            super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (boosSelfSignedCertPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCertPkcs12");
            return null;
        }
        
        Boolean[] boosTrustedCertPkcs12 = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (boosTrustedCertPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCertPkcs12");
            return null;
        }
        
        String[] strsAlgoKeyPublPkcs12 = UtilKstAbs.s_getStrsAlgoKeyPubl(
            super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (strsAlgoKeyPublPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPublPkcs12");
            return null;
        }
        
        String[] strsSizeKeyPublPkcs12 = UtilKstAbs.s_getStrsSizeKeyPubl(
            super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (strsSizeKeyPublPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPublPkcs12");
            return null;
        }
        
        
        String[] strsTypeCertPkcs12 = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (strsTypeCertPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsTypeCertPkcs12");
            return null;
        }
        
        String[] strsAlgoSigCertPkcs12 = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);
        
        if (strsAlgoSigCertPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoSigCertPkcs12");
            return null;
        }

        Date[] dtesLastModifiedPkcs12 = UtilKstAbs.s_getDtesLastModified(super._frmOwner_, super._strTitleAppli_, kstOpen, strsAliasPkcs12);

        if (dtesLastModifiedPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil dtesLastModifiedPkcs12");
            return null;
        }
        
        
        // ----
        
        Boolean[] boosTypeCertX509Pkcs12 = super._getBoosTypeCertX509_(kstOpen, strsAliasPkcs12);
        
        if (boosTypeCertX509Pkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTypeCertX509Pkcs12");
            return null;
        }
        
        
        Boolean[] boosElligiblePkcs12 = super._getBoosElligibleAny_(
            boosEntryKprPkcs12, strsAlgoKeyPublPkcs12, boosTypeCertX509Pkcs12);
        
        if (boosElligiblePkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosElligiblePkcs12");
            return null;
        }

        // -- 
        // clean-up
        boosTypeCertX509Pkcs12 = null;
        
        // ---
        // check for elligible entry
        
        //boolean blnElligiblePkcs12 = false;
        int intIdElligibleLast = -1;
        int intNbElligible = 0;
        
        
        for (int i=0; i<boosElligiblePkcs12.length; i++)
        {
            if (boosElligiblePkcs12[i].booleanValue() == true)
            {
                //blnElligiblePkcs12 = true;
                //break;
                intNbElligible ++;
                intIdElligibleLast = i;
            }
        }
        
        if (intNbElligible < 1)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "intNbElligible < 1");
            
            String strBody = "keystore does not contain any valid entry of type RSA with a CA X.509 certificate.";
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
        
            return null;
        }*/
        
        // ----
        /*
            if only one elligible keypair, just return the respective alias!
            ==> no need to display dialogSelectValidKeypair
        */
        
        
        /*if (intNbElligible == 1)
        {
            MySystem.s_printOutTrace(this, strMethod, "intNbElligible == 1, strsAliasPkcs12[intIdElligibleLast]=" + strsAliasPkcs12[intIdElligibleLast]);
            return strsAliasPkcs12[intIdElligibleLast];
        }*/
        
        
        // ----
        
        // --
        // assign default cursor
        
        //super._setEnabledCursorWait_(false);

        
        // ----
        
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

        
        DTblsKstSelPKOpenNoPass dlg = new DTblsKstSelPKOpenNoPass(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpen,
            super._strPathAbsKst_,
            "Sign JAR file with private key entry"
            );
       
        /*DTblEntryKprOpenPkcs12Any dlgPkcs12 = new DTblEntryKprOpenPkcs12Any(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpen
            );*/
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        
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
        
        // 
        /*if (! dlgPkcs12.load(boosElligiblePkcs12, strsAliasPkcs12, 
            boosEntryKprPkcs12, boosEntryTcrPkcs12, 
            boosSelfSignedCertPkcs12, boosTrustedCertPkcs12, 
            strsAlgoKeyPublPkcs12, 
            strsSizeKeyPublPkcs12,
            strsTypeCertPkcs12, 
            strsAlgoSigCertPkcs12, dtesLastModifiedPkcs12))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        } */  
        
        dlg.setVisible(true);
        
        // --
        
        String strAliasKprPkcs12 = dlg.getAlias();
        
        if (strAliasKprPkcs12 == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprPkcs12, aborted by user");
            return null;
        }
        
        return strAliasKprPkcs12;
    }
    
}