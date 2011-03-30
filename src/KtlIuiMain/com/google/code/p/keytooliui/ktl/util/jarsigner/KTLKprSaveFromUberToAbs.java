package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromUberToPkcs12
    . KTLKprSaveFromUberToKPAbs
     
    
    !!!!!!!!! not done yet: overwritting existing keypair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLKprSaveFromUberToAbs extends KTLKprSaveFromAbs
{
    // ---------
    // PROTECTED

    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        
        . add providers
        
        . open keystoreSource
        . open keystoreKstTarget
        . select aliasSource pointing to valid kprSource
        . get respective keySource & crtsSource
        
        . create new keypair
        . create new certificate of type X.509
        . assign new entry to open [JKS-JCEKS-PKCS12-BKS-UBER] keystore
        . save [JKS-JCEKS-PKCS12-BKS-UBER] keystore
    **/
    
    protected boolean _doJob_(
        KeyStore kstOpenSource,
        KeyStore kstOpenTarget,
        File fleOpenKstTarget
        )
    {
        String strMethod = "_doJob_(kstOpenSource, kstOpenTarget, fleOpenKstTarget)";
        
        if (kstOpenSource==null || kstOpenTarget==null || fleOpenKstTarget==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
            
            
         // --
        // . select aliasSource and password pointing to valid kprSource
        
        // NEW
        
        String[] strsAliasPKTCSource = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpenSource);
        
        if (strsAliasPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTCSource");
        }
        
        String[] strsAliasSKSource = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpenSource);
        
        if (strsAliasSKSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTCSource");
        }
        
        // --
        // get arrays for dialogTableSelectKeypair
        // TC versus PK
        Boolean[] boosIsTCEntryPKTCSource = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);
        
        if (boosIsTCEntryPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTCSource");
        }
        
        Boolean[] boosValidDatePKTCSource = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);
        
        if (boosValidDatePKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTCSource");
        }

        Boolean[] boosSelfSignedCertPKTCSource = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);
        
        if (boosSelfSignedCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTCSource");
        }
        
        Boolean[] boosTrustedCertPKTCSource = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);
        
        if (boosTrustedCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTCSource");
        }
        
        String[] strsSizeKeyPublPKTCSource = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);
        
        if (strsSizeKeyPublPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTCSource");
        }
        
        String[] strsTypeCertPKTCSource = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);
        
        if (strsTypeCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTCSource");
        }
        
        String[] strsAlgoSigCertPKTCSource = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);
        
        if (strsAlgoSigCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTCSource");
        }

        Date[] dtesLastModifiedPKTCSource = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasPKTCSource);

        if (dtesLastModifiedPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTCSource");
        }
        
        Date[] dtesLastModifiedSKSource = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpenSource, strsAliasSKSource);

        if (dtesLastModifiedSKSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTCSource");
        }
        
        // ----
        // get aliases
        
        /*
        String[] strsAliasSource = UtilKstAbs.s_getStrsAlias(
            super._frmOwner_, 
            super._strTitleAppli_, 
            kstOpenSource);
     
        if (strsAliasSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAliasSource");
            return false;
        }
        
        // ----
        // check for existing aliases
        
        if (strsAliasSource.length < 1)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "strsAliasSource.length < 1");
            
            String strBody = "source keystore does not contain any entry.";
                
            OPAbstract.s_showDialogInfo(
                super._frmOwner_, super._strTitleAppli_, strBody);
            
            return false;
        }
        
        // --
        // fill in table for dialogSelect
        
        Boolean[] boosEntryTcrSource = UtilKstAbs.s_getBoosEntryTcr(
            super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (boosEntryTcrSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcrSource");
            return false;
        }
        
        Boolean[] boosEntryKprSource = UtilKstAbs.s_getBoosEntryKpr(
            super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (boosEntryKprSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKprSource");
            return false;
        }
        
        Boolean[] boosSelfSignedCertSource = UtilKstAbs.s_getBoosSelfSigned(
            super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (boosSelfSignedCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCertSource");
            return false;
        }
        
        
        Boolean[] boosTrustedCertSource = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (boosTrustedCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCertSource");
            return false;
        }
        
        String[] strsSizeKeyPublSource = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (strsSizeKeyPublSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPublSource");
            return false;
        }
           
        String[] strsAlgoKeyPublSource = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (strsAlgoKeyPublSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPublSource");
            return false;
        }
        
        String[] strsTypeCertSource = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (strsTypeCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsTypeCertSource");
            return false;
        }
        
        String[] strsAlgoSigCertSource = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);
        
        if (strsAlgoSigCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoSigCertSource");
            return false;
        }

        Date[] dtesLastModifiedSource = UtilKstAbs.s_getDtesLastModified(super._frmOwner_, super._strTitleAppli_, kstOpenSource, strsAliasSource);

        if (dtesLastModifiedSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil dtesLastModifiedSource");
            return false;
        }
        
        // ----
        
        Boolean[] boosTypeCertX509Source = super._getBoosTypeCertX509_(kstOpenSource, strsAliasSource);
        
        if (boosTypeCertX509Source == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTypeCertX509Source");
            return false;
        }
        
        Boolean[] boosElligibleSource = super._getBoosElligibleAny_(
            boosEntryKprSource, strsAlgoKeyPublSource, boosTypeCertX509Source);
        
        if (boosElligibleSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosElligibleSource");
            return false;
        } 
        
        // -- 
        // clean-up
        boosTypeCertX509Source = null;
        
        // ---
        // check for elligible entry
        
        boolean blnElligible = false;
        
        for (int i=0; i<boosElligibleSource.length; i++)
        {
            if (boosElligibleSource[i].booleanValue() == true)
            {
                blnElligible = true;
                break;
            }
        }
        
        if (! blnElligible)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "! blnElligible");
            
            String strBody = "source keystore does not contain any keypair entry\n either of type RSA, or of type DSA, with an X.509 certificate .";
                
            OPAbstract.s_showDialogInfo(
                super._frmOwner_, super._strTitleAppli_, strBody);
        
            return false;
        }
         */
         
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
        
        // ----
       
        /*DTblEntryKprOpenKPAny dlgSource = new DTblEntryKprOpenKPAny(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpenSource
            );*/
        
        DTblsKstSelPKOpen dlgSource = new DTblsKstSelPKOpen(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpenSource,
            super._strPathAbsKst_,
            "Import private key from other keystore - step 1/2: source"
            );
        
        if (! dlgSource.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlgSource.load(
                
            strsAliasPKTCSource, 
            boosIsTCEntryPKTCSource, 
            boosValidDatePKTCSource, 
            boosSelfSignedCertPKTCSource, 
            boosTrustedCertPKTCSource, 
            strsSizeKeyPublPKTCSource,
            strsTypeCertPKTCSource, 
            strsAlgoSigCertPKTCSource, 
            dtesLastModifiedPKTCSource,
            // below: about SK (Secret Key)
            strsAliasSKSource,
            dtesLastModifiedSKSource
                
            /*
            boosElligibleSource, strsAliasSource, 
            boosEntryKprSource, 
            boosEntryTcrSource, 
            boosSelfSignedCertSource, 
            boosTrustedCertSource, 
            strsAlgoKeyPublSource,
            strsSizeKeyPublSource,
            strsTypeCertSource, 
            strsAlgoSigCertSource, dtesLastModifiedSource*/))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlgSource.setVisible(true);
        
        
        // ---
        char[] chrsPasswdKprSource = dlgSource.getPassword();
        
        if (chrsPasswdKprSource == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKprSource, aborted by user");
            return false;
        }
        
        String strAliasKprSource = dlgSource.getAlias();
        
        if (strAliasKprSource == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprSource, aborted by user");
            return false;
        }

        //super._setEnabledCursorWait_(true);
            
       
        // at this level, user selected keypair (got alias & password)
            
            
        // --
        // . get respective keySource & crtsSource
        
        // ----
        // x) get privateKey
        
        
        PrivateKey pkySource = null;
         
        try
        {
            pkySource = (PrivateKey) UtilKstAbs.s_getKey(
                super._frmOwner_, super._strTitleAppli_,
                kstOpenSource,
                strAliasKprSource,
                chrsPasswdKprSource);
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excClassCast caught");
        }

        if (pkySource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil pkySource");
            return false;
        }  
        
        // ----
        // x) get X509Certificates
        
        X509Certificate[] crtsX509UnorderedSource = UtilCrtX509.s_getX509CertificateChain(
            kstOpenSource, 
            strAliasKprSource, 
            false // blnOrderChain !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            );
            
        if (crtsX509UnorderedSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsX509UnorderedSource");
            return false;
        }            
        
        // -----
        
        
        if (! __createNewEntry__(kstOpenTarget, pkySource, crtsX509UnorderedSource))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
      
        // ----
        // save kstOpenTarget
        
        if (super._chrsPasswdKst_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._chrsPasswdKst_");
            return false;
        }        
        
        // ----
        
        if (! super._saveKeyStore_(kstOpenTarget, fleOpenKstTarget, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
       
        // ending
        return true;
    }
    
    protected KTLKprSaveFromUberToAbs(
        Frame frmOwner, 
        String strTitleAppli,
        
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type UBER 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget,
        boolean blnIsPasswdKprTarget
        )
    {
        super(
            frmOwner, 
            strTitleAppli,
            
            // input
            strPathAbsOpenKstTarget,
            chrsPasswdOpenKstTarget,
            
            strProviderKstTarget, // provider for keystore-target
            
            strPathAbsKstSource,
            chrsPasswdKstSource,
            KTLAbs.f_s_strProviderKstBC, // provider for keystore-source
            blnIsPasswdKprTarget
            );
            
    
    }
    
    // -------
    // PRIVATE
    
    
    private String[] _getStrsAliasSourceToKpr(KeyStore kstOpenToSource)
    {
        String strMethod = "_getStrsAliasSourceToKpr(kstOpenToSource)";
        
        String[] strsAliasSourceAll = UtilKstAbs.s_getStrsAlias(
            super._frmOwner_, 
            super._strTitleAppli_, 
            kstOpenToSource);
   
        
        if (strsAliasSourceAll == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAliasSourceAll");
            return null;
        }
        
        if (strsAliasSourceAll.length < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "strsAliasSourceAll.length < 1");
                
            String strBody = "No aliases found in " +
                UtilKstUber.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKstSource_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
            
            return null;
        }
        
        Vector<String> vec = new Vector<String>();
        
        try
        {
            for (int i=0; i<strsAliasSourceAll.length; i++)
            {
                if (! kstOpenToSource.isKeyEntry(strsAliasSourceAll[i]))
                    continue;
                    
                Certificate[] certs = kstOpenToSource.getCertificateChain(strsAliasSourceAll[i]);
                    
                if (certs == null)
                    continue;
                
                if (certs.length < 1)
                    continue;
                    
                vec.addElement(strsAliasSourceAll[i]);
            }
        }
              
        catch(KeyStoreException excKeystore)
        {
            excKeystore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeystore caught");
            
            // show dialog
            String strBody = "Got keystore Exception while reading " +
                UtilKstUber.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKstSource_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
        }
        
        // --
        
        if (vec.size() < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "vec.size() < 1");
            
            // show dialog
            String strBody = "No aliases pointing to keypair found in " +
                UtilKstUber.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKstSource_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, super._strTitleAppli_, strBody);
            
            return null;
        }
        
        // ---
        
        String[] strsAliasSourceToKpr = new String[vec.size()];
        
        for (int i=0; i<vec.size(); i++)
            strsAliasSourceToKpr[i] = (String) vec.elementAt(i);
            
        return strsAliasSourceToKpr;
    }
    
    
    /**
        should be X509Cert, either RSA or DSA
    **/
    private Boolean[] _getBoosElligibleSource(
        String[] strsAlgoKeyPublSource,
        Boolean[] boosTypeCertX509Source
        )
    {
        String strMethod = "_getBoosElligibleSource(...)";
        
        if (strsAlgoKeyPublSource==null || boosTypeCertX509Source==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosElligibleSource = new Boolean[strsAlgoKeyPublSource.length];
        
        for (int i=0; i<strsAlgoKeyPublSource.length; i++)
        {
            boolean blnOk = true;
            
            String str = strsAlgoKeyPublSource[i].toLowerCase();

            
            // should be of type DSA OR RSA
            if (
                (str.compareTo(KTLAbs.f_s_strTypeKeypairDsa.toLowerCase())!= 0) &&
                (str.compareTo(KTLAbs.f_s_strTypeKeypairRsa.toLowerCase())!= 0)
                )
            {
                blnOk = false;
            }
            
            // certificate should be of type X509
            else if (boosTypeCertX509Source[i].booleanValue() == false)
            {
                blnOk = false;
            }
           
            
            // --
            
            boosElligibleSource[i] = new Boolean(blnOk);
        }

        // ----
        return boosElligibleSource;
    }
    
    protected boolean __createNewEntry__(
        KeyStore kstOpenTarget,
        PrivateKey pkyPrivateSource,
        Certificate[] crtsSource
        )
    {
        String strMethod = "__createNewEntry__(...)";
        
        // NEW
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpenTarget);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpenTarget);
        
        if (strsAliasSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        // --
        // get arrays for dialogTableSelectKeypair
        // TC versus PK
        Boolean[] boosIsTCEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpenTarget, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        /*
        // ----
        // create new alias (?& new password) 
        
        
        // ----
        // fill in table KeyPair
        
        String[] strsAliasTarget = UtilKstAbs.s_getStrsAlias(
            super._frmOwner_, 
            super._strTitleAppli_, 
            kstOpenTarget);
        
        if (strsAliasTarget == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAliasTarget");
            return false;
        }
        
        // --
        
        Boolean[] boosEntryKpr = UtilKstAbs.s_getBoosEntryKpr(
            super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (boosEntryKpr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKpr");
            return false;
        }
        
        Boolean[] boosEntryTcr = UtilKstAbs.s_getBoosEntryTcr(
            super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (boosEntryTcr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcr");
            return false;
        }
        
        
        Boolean[] boosSelfSignedCert = UtilKstAbs.s_getBoosSelfSigned(
            super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (boosSelfSignedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCert");
            return false;
        }
        
        Boolean[] boosTrustedCert = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (boosTrustedCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCert");
            return false;
        }
        
        String[] strsSizeKeyPubl = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (strsSizeKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPubl");
            return false;
        }
        
        String[] strsAlgoKeyPubl = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (strsAlgoKeyPubl == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPubl");
            return false;
        }
        
        String[] strsTypeCert = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (strsTypeCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsTypeCert");
            return false;
        }
        
        String[] strsAlgoSigCert = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);
        
        if (strsAlgoSigCert == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoSigCert");
            return false;
        }

        Date[] dtesLastModified = UtilKstAbs.s_getDtesLastModified(super._frmOwner_, super._strTitleAppli_, kstOpenTarget, strsAliasTarget);

        if (dtesLastModified == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil dtesLastModified");
            return false;
        }*/
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
        
        
        // ----
        
        // MEMO: overwriting alias-key not allowed
        // -----

        // ----
        // show dialog KeyPair new entry
        //  . get aliasKpr
        //  . get passwdKpr
       
        
        DTblsKstViewKeySavePK dlgTarget = new DTblsKstViewKeySavePK(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpenTarget,
            super._strPathAbsKst_,
            "Import private key from other keystore - step 2/2: target");
        
        if (! dlgTarget.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlgTarget.load(
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
            /*strsAliasTarget, 
            boosEntryKpr, boosEntryTcr, 
            boosSelfSignedCert, boosTrustedCert, 
            strsAlgoKeyPubl, 
            strsSizeKeyPubl,
            strsTypeCert, strsAlgoSigCert, dtesLastModified*/))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlgTarget.setVisible(true);
        
        char[] chrsPasswdKprTarget = new char[0]; // no password, eg, for PKCS12
        
        // ---
        if (super._blnIsPasswdKprTarget_)
        {
            chrsPasswdKprTarget = dlgTarget.getPassword();
            
            if (chrsPasswdKprTarget == null)
            {
                MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKprTarget, aborted by user");
                return false;
            }
        }
        
        String strAliasKprTarget = dlgTarget.getAlias();
        
        if (strAliasKprTarget == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprTarget, aborted by user");
            return false;
        }
        
        // ----
        // store privateKeySource & crtsSource associated with alias (?& password) in kstOpenTarget
        
        if (! UtilKstAbs.s_setKeyEntry(super._frmOwner_, super._strTitleAppli_, 
            kstOpenTarget, strAliasKprTarget, pkyPrivateSource, chrsPasswdKprTarget, crtsSource))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
}