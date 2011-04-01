package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromPkcs12ToPkcs12
    . KTLKprSaveFromPkcs12ToKPAbs
     
    
    !!!!!!!!! not done yet: overwritting existing keypair entry

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;


// ----
import java.security.Key;
import java.security.PrivateKey;
import java.security.KeyStore;
import java.security.KeyStoreException;
// --
import java.security.cert.Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLKprSaveFromPkcs12ToAbs extends KTLKprSaveFromAbs
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
        . assign new entry to open [JKS-JCEKS-PKCS12] keystore
        . save [JKS-JCEKS-PKCS12] keystore
    **/
    
    protected boolean _doJob_(
        KeyStore kstOpenSource,
        KeyStore kstOpenTarget,
        File fleOpenKstTarget//,
        //boolean blnIsEntryPasswordTarget // eg: target keystore: JKS-JCEKS ==> true, PKCS12 ==> false
        )
    {
        String strMethod = "_doJob_(kstOpenSource, kstOpenTarget, fleOpenKstTarget)";
        
        if (kstOpenSource==null || kstOpenTarget==null || fleOpenKstTarget==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        // --
        
        // NEW
        
        String[] strsAliasPKTCSource = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
           
            kstOpenSource);
        
        if (strsAliasPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTCSource");
        }
        
        String[] strsAliasSKSource = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
         
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
            kstOpenSource, strsAliasPKTCSource);
        
        if (boosIsTCEntryPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTCSource");
        }
        
        Boolean[] boosValidDatePKTCSource = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
           kstOpenSource, strsAliasPKTCSource);
        
        if (boosValidDatePKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTCSource");
        }

        Boolean[] boosSelfSignedCertPKTCSource = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
             kstOpenSource, strsAliasPKTCSource);
        
        if (boosSelfSignedCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTCSource");
        }
        
        Boolean[] boosTrustedCertPKTCSource = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
             kstOpenSource, strsAliasPKTCSource);
        
        if (boosTrustedCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTCSource");
        }
        
        String[] strsSizeKeyPublPKTCSource = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
            kstOpenSource, strsAliasPKTCSource);
        
        if (strsSizeKeyPublPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTCSource");
        }
        
        String[] strsTypeCertPKTCSource = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            kstOpenSource, strsAliasPKTCSource);
        
        if (strsTypeCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTCSource");
        }
        
        String[] strsAlgoSigCertPKTCSource = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
             kstOpenSource, strsAliasPKTCSource);
        
        if (strsAlgoSigCertPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTCSource");
        }

        Date[] dtesLastModifiedPKTCSource = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            kstOpenSource, strsAliasPKTCSource);

        if (dtesLastModifiedPKTCSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTCSource");
        }
        
        Date[] dtesLastModifiedSKSource = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
             kstOpenSource, strsAliasSKSource);

        if (dtesLastModifiedSKSource == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTCSource");
        }
        
        DTblsKstSelPKOpenNoPass dlgSource = new DTblsKstSelPKOpenNoPass(
            super._frmOwner_, 
            
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
            dtesLastModifiedSKSource))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlgSource.setVisible(true);
        
        
        // ---
        /*char[] chrsPasswdKprSource = dlgSource.getPassword();
        
        if (chrsPasswdKprSource == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKprSource, aborted by user");
            return false;
        }*/
        
        String strAliasKprSource = dlgSource.getAlias();
        
        if (strAliasKprSource == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprSource, aborted by user");
            return false;
        }
        
        /*String strAliasKprSource = _getAliasKprSource(kstOpenSource);
       
        if (strAliasKprSource == null) // either aborted or failed
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprSource, either aborted by user or failed");
            return false;
        }*/
        
        super._setEnabledCursorWait_(true);
               
        
        // get privateKeySource & crtsSource
        
        Key keySource = UtilKstPkcs12.s_getKeyProviderBc(
            super._frmOwner_, 
        
            kstOpenSource,
            strAliasKprSource);
   
        
        if (keySource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil keySource");
            return false;
        }
        
        PrivateKey pkyPrivateSource = null;
        
        try
        {
            pkyPrivateSource = (PrivateKey) keySource;
        }
        
        catch(ClassCastException excClassCast)
        {
            super._setEnabledCursorWait_(false);
            excClassCast.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excClassCast caught");
        }
        
        Certificate[] crtsSource = null;
        
        try
        {
            crtsSource = kstOpenSource.getCertificateChain(strAliasKprSource);;
        }
        
        catch(KeyStoreException excKeyStore)
        {
            super._setEnabledCursorWait_(false);
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            
            // show dialog
            String strBody = "Got keystore Exception while reading " +
                UtilKstPkcs12.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKstSource_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
            
            return false;
        }
        
        if (crtsSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil crtsSource");
        }
        
        
        // -----
        
        if (! __createNewEntry__(kstOpenTarget, pkyPrivateSource, crtsSource))
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
    
    protected KTLKprSaveFromPkcs12ToAbs(
        Frame frmOwner, 
      
        
        // 
        String strPathAbsOpenKstTarget, // existing keystore of type [JKS-JCEKS-PKCS12-BKS-UBER] 
        char[] chrsPasswdOpenKstTarget,
        
        String strPathAbsKstSource, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstSource,
        
        String strProviderKstTarget,
        boolean blnIsPasswdKprTarget
        
        )
    {
        super(
            frmOwner, 
           
            
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
                UtilKstPkcs12.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKstSource_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
            
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
                UtilKstPkcs12.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += super._strPathAbsKstSource_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
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
            strBody += super._strPathAbsKstSource_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
            
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
    
    /**
        check for valid keypairs,
        
        if nothing found, show dialog, then return null
        if a unique valid keypair found, just return it
        else show dialogSelectAliasPointing2ValidKeypair
    **/
    private String _getAliasKprSource(KeyStore kstOpenToSource)
    {
        String strMethod = "_getAliasKprSource(kstOpenToSource)";

        super._setEnabledCursorWait_(true);
        
        if (kstOpenToSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil kstOpenToSource");
        }
        
        // ----
        // -- get  aliases of keystoreSource source
        
        // ----
        // fill in table keypair
        
        String[] strsAliasSource = _getStrsAliasSourceToKpr(kstOpenToSource);
        
        if (strsAliasSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAliasSource");
            return null;
        }
        
        
        // select aliasSource
        
        // --
        // get arrays for dialogTableSelectKeypair
        
        Boolean[] boosEntryTcrSource = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,  kstOpenToSource, strsAliasSource);
        
        if (boosEntryTcrSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcrSource");
            return null;
        }
        
        Boolean[] boosEntryKprSource = 
            UtilKstAbs.s_getBoosEntryKpr(super._frmOwner_,  kstOpenToSource, strsAliasSource);
        
        if (boosEntryKprSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKprSource");
            return null;
        }
        

        Boolean[] boosSelfSignedCertSource = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_, kstOpenToSource, strsAliasSource);
        
        if (boosSelfSignedCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCertSource");
            return null;
        }
        
        Boolean[] boosTrustedCertSource = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,  kstOpenToSource, strsAliasSource);
        
        if (boosTrustedCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCertSource");
            return null;
        }
        
        String[] strsSizeKeyPublSource = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,  kstOpenToSource, strsAliasSource);
        
        if (strsSizeKeyPublSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPublSource");
            return null;
        }
                
        String[] strsAlgoKeyPublSource = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_,  kstOpenToSource, strsAliasSource);
        
        if (strsAlgoKeyPublSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPublSource");
            return null;
        }
        
        
        String[] strsTypeCertSource = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_, kstOpenToSource, strsAliasSource);
        
        if (strsTypeCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsTypeCertSource");
            return null;
        }
        
        String[] strsAlgoSigCertSource = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_, kstOpenToSource, strsAliasSource);
        
        if (strsAlgoSigCertSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoSigCertSource");
            return null;
        }

        Date[] dtesLastModifiedSource = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,  kstOpenToSource, strsAliasSource);

        if (dtesLastModifiedSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil dtesLastModifiedSource");
            return null;
        }
        
        // ----
        
        Boolean[] boosTypeCertX509Source = super._getBoosTypeCertX509_(kstOpenToSource, strsAliasSource);
        
        if (boosTypeCertX509Source == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTypeCertX509Source");
            return null;
        }
        
        
        Boolean[] boosElligibleSource = this._getBoosElligibleSource(
            strsAlgoKeyPublSource, boosTypeCertX509Source);
        
        if (boosElligibleSource == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosElligibleSource");
            return null;
        }
        
        
        // -- 
        // clean-up
        boosTypeCertX509Source = null;
        
        // ---
        // check for elligible entry
        
        //boolean blnElligibleSource = false;
        int intIdElligibleLast = -1;
        int intNbElligible = 0;
        
        
        for (int i=0; i<boosElligibleSource.length; i++)
        {
            if (boosElligibleSource[i].booleanValue() == true)
            {
                //blnElligibleSource = true;
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
                super._frmOwner_,  strBody);
        
            return null;
        }
        
        // ----
        
        //    if only one elligible keypair, just return the respective alias!
        //    ==> no need to display dialogSelectValidKeypair
        
        
        //MySystem.s_printOutWarning(this, strMethod, "TEMPO IN COMMENTS: if (intNbElligible == 1)");
        
        //if (intNbElligible == 1)
        //{
          //  super._setEnabledCursorWait_(false);
          //  MySystem.s_printOutTrace(this, strMethod, "intNbElligible == 1, strsAliasSource[intIdElligibleLast]=" + strsAliasSource[intIdElligibleLast]);
          //  return strsAliasSource[intIdElligibleLast];
        //}
        
        // ----
        // restore default cursor
        super._setEnabledCursorWait_(false);
        
        // ----

       
        DTblEntryKprOpenPkcs12Any dlgSource = new DTblEntryKprOpenPkcs12Any(
            super._frmOwner_, 
    
            kstOpenToSource
            );
        
        if (! dlgSource.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlgSource.load(boosElligibleSource, strsAliasSource, 
            boosEntryKprSource, 
            boosEntryTcrSource, 
            boosSelfSignedCertSource, 
            boosTrustedCertSource, 
            strsAlgoKeyPublSource, 
            strsSizeKeyPublSource,
            strsTypeCertSource, 
            strsAlgoSigCertSource, dtesLastModifiedSource))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlgSource.setVisible(true);
        
        // --
        
        String strAliasKprSource = dlgSource.getAlias();
        
        if (strAliasKprSource == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprSource, aborted by user");
            return null;
        }
        
        return strAliasKprSource;
    }
}