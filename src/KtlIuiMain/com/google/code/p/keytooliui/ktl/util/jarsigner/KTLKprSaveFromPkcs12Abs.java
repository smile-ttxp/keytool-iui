package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . KTLKprSaveFromPkcs12Jks
    . KTLKprSaveFromPkcs12Jceks
     
    
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
// --
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

abstract public class KTLKprSaveFromPkcs12Abs extends KTLKprSaveAbs
{
    // ------------------
    // ABSTRACT PROTECTED
    
    // definition in subclasses, called there
    
    abstract protected boolean __createNewEntry__(
        KeyStore kstOpenTo, PrivateKey pkyPrivateFromPkcs12, Certificate[] crtsFromPkcs12);
    
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsKstFromPkcs12_ = null;
    protected char[] _chrsPasswdKstFromPkcs12_ = null;
    protected String _strProviderKstFromPkcs12_ = null;
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        
        . add providers
        
        . open keystoreFromPkcs12Source
        . open keystoreKstTarget
        . select aliasFromPkcs12Source pointing to valid kprFromPkcs12Source
        . get respective keyFromPkcs12 & crtsFromPkcs12
        
        . create new keypair
        . create new certificate of type X.509
        . assign new entry to open [JKS-JCEKS-PKCS12] keystore
        . save [JKS-JCEKS-PKCS12] keystore
    **/
    
    protected boolean _doJob_(
        KeyStore kstOpenFromPkcs12,
        KeyStore kstOpenTo,
        File fleOpenKstTo//,
        //boolean blnIsEntryPasswordTarget // eg: target keystore: JKS-JCEKS ==> true, PKCS12 ==> false
        )
    {
        String strMethod = "_doJob_(kstOpenFromPkcs12, kstOpenTo, fleOpenKstTo)";
        
        if (kstOpenFromPkcs12==null || kstOpenTo==null || fleOpenKstTo==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        
        // --
        
        String strAliasKprFromPkcs12 = _getAliasKprFromPkcs12(kstOpenFromPkcs12);
       
        if (strAliasKprFromPkcs12 == null) // either aborted or failed
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprFromPkcs12, either aborted by user or failed");
            return false;
        }
        
        super._setEnabledCursorWait_(true);
               
        
        // get privateKeyFromPkcs12 & crtsFromPkcs12
        
        Key keyFromPkcs12 = null;
        
        
        if (this._strProviderKstFromPkcs12_.toLowerCase().compareTo(
                //"SunRsaSign"
                KTLAbs.f_s_strSecurityProviderSunRsaSign // modified may 17, 07
                .toLowerCase()) == 0)
        {
            keyFromPkcs12 = UtilKstPkcs12.s_getKeyProviderSunJsse(
                super._frmOwner_, 
             
                kstOpenFromPkcs12,
                strAliasKprFromPkcs12,
                this._chrsPasswdKstFromPkcs12_
                );
        }
        
        else
        {
            keyFromPkcs12 = UtilKstPkcs12.s_getKeyProviderBc(
                super._frmOwner_, 
            
                kstOpenFromPkcs12,
                strAliasKprFromPkcs12);
            
        }
        
        
        if (keyFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil keyFromPkcs12");
            return false;
        }
        
        PrivateKey pkyPrivateFromPkcs12 = null;
        
        try
        {
            pkyPrivateFromPkcs12 = (PrivateKey) keyFromPkcs12;
        }
        
        catch(ClassCastException excClassCast)
        {
            super._setEnabledCursorWait_(false);
            excClassCast.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excClassCast caught");
        }
        
        Certificate[] crtsFromPkcs12 = null;
        
        try
        {
            crtsFromPkcs12 = kstOpenFromPkcs12.getCertificateChain(strAliasKprFromPkcs12);;
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
            strBody += this._strPathAbsKstFromPkcs12_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
            
            return false;
        }
        
        if (crtsFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil crtsFromPkcs12");
        }
        
        
        // -----
        
        if (! __createNewEntry__(kstOpenTo, pkyPrivateFromPkcs12, crtsFromPkcs12))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        
      
        // ----
        // save kstOpenTo
        
        if (super._chrsPasswdKst_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._chrsPasswdKst_");
            return false;
        }        
        
        // ----
        
        if (! super._saveKeyStore_(kstOpenTo, fleOpenKstTo, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
       

        // ending
        return true;
    }
    
    protected KTLKprSaveFromPkcs12Abs(
        Frame frmOwner, 
       
        // 
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS-PKCS12] 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsKstFromPkcs12, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstFromPkcs12,
        
        String strProviderKst
        
        )
    {
        super(
            frmOwner, 
     
            
            // input
            strPathAbsOpenKst,
            chrsPasswdOpenKst,
            
            strProviderKst// provider for keytool of type [JKS-JCEKS-PKCS12]
            );
            
        this._strPathAbsKstFromPkcs12_ = strPathAbsKstFromPkcs12;
        this._chrsPasswdKstFromPkcs12_ = chrsPasswdKstFromPkcs12;
        this._strProviderKstFromPkcs12_ = KTLAbs.f_s_strProviderKstBC; // should be final
    }
    
    // -------
    // PRIVATE
    
    
    
    
    
    
    
    private String[] _getStrsAliasFromPkcs12ToKpr(KeyStore kstOpenToFromPkcs12)
    {
        String strMethod = "_getStrsAliasFromPkcs12ToKpr(kstOpenToFromPkcs12)";
        
        String[] strsAliasFromPkcs12All = UtilKstAbs.s_getStrsAlias(
            super._frmOwner_, 
          
            kstOpenToFromPkcs12);
   
        
        if (strsAliasFromPkcs12All == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAliasFromPkcs12All");
            return null;
        }
        
        if (strsAliasFromPkcs12All.length < 1)
        {
            MySystem.s_printOutWarning(this, strMethod, "strsAliasFromPkcs12All.length < 1");
                
            String strBody = "No aliases found in " +
                UtilKstPkcs12.f_s_strKeystoreType +
                " keystore:";
            
            strBody += "\n" + "  ";
            strBody += this._strPathAbsKstFromPkcs12_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
            
            return null;
        }
        
        Vector<String> vec = new Vector<String>();
        
        try
        {
            for (int i=0; i<strsAliasFromPkcs12All.length; i++)
            {
                if (! kstOpenToFromPkcs12.isKeyEntry(strsAliasFromPkcs12All[i]))
                    continue;
                    
                Certificate[] certs = kstOpenToFromPkcs12.getCertificateChain(strsAliasFromPkcs12All[i]);
                    
                if (certs == null)
                    continue;
                
                if (certs.length < 1)
                    continue;
                    
                vec.addElement(strsAliasFromPkcs12All[i]);
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
            strBody += this._strPathAbsKstFromPkcs12_;
                
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
            strBody += this._strPathAbsKstFromPkcs12_;
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_,  strBody);
            
            return null;
        }
        
        // ---
        
        String[] strsAliasFromPkcs12ToKpr = new String[vec.size()];
        
        for (int i=0; i<vec.size(); i++)
            strsAliasFromPkcs12ToKpr[i] = (String) vec.elementAt(i);
            
        return strsAliasFromPkcs12ToKpr;
    }
    
    
    /**
        should be X509Cert, either RSA or DSA
    **/
    private Boolean[] _getBoosElligibleFromPkcs12(
        String[] strsAlgoKeyPublFromPkcs12,
        Boolean[] boosTypeCertX509FromPkcs12
        )
    {
        String strMethod = "_getBoosElligibleFromPkcs12(...)";
        
        if (strsAlgoKeyPublFromPkcs12==null || boosTypeCertX509FromPkcs12==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosElligibleFromPkcs12 = new Boolean[strsAlgoKeyPublFromPkcs12.length];
        
        for (int i=0; i<strsAlgoKeyPublFromPkcs12.length; i++)
        {
            boolean blnOk = true;
            
            String str = strsAlgoKeyPublFromPkcs12[i].toLowerCase();

            
            // should be of type DSA OR RSA
            if (
                (str.compareTo(KTLAbs.f_s_strTypeKeypairDsa.toLowerCase())!= 0) &&
                (str.compareTo(KTLAbs.f_s_strTypeKeypairRsa.toLowerCase())!= 0)
                )
            {
                blnOk = false;
            }
            
            // certificate should be of type X509
            else if (boosTypeCertX509FromPkcs12[i].booleanValue() == false)
            {
                blnOk = false;
            }
           
            
            // --
            
            boosElligibleFromPkcs12[i] = new Boolean(blnOk);
        }

        // ----
        return boosElligibleFromPkcs12;
    }
    
    /**
        check for valid keypairs,
        
        if nothing found, show dialog, then return null
        if a unique valid keypair found, just return it
        else show dialogSelectAliasPointing2ValidKeypair
    **/
    private String _getAliasKprFromPkcs12(KeyStore kstOpenToFromPkcs12)
    {
        String strMethod = "_getAliasKprFromPkcs12(kstOpenToFromPkcs12)";

        super._setEnabledCursorWait_(true);
        
        if (kstOpenToFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil kstOpenToFromPkcs12");
        }
        
        // ----
        // -- get  aliases of keystoreFromPkcs12 source
        
        // ----
        // fill in table keypair
        
        String[] strsAliasFromPkcs12 = _getStrsAliasFromPkcs12ToKpr(kstOpenToFromPkcs12);
        
        if (strsAliasFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAliasFromPkcs12");
            return null;
        }
        
        
        // select aliasFromPkcs12
        
        // --
        // get arrays for dialogTableSelectKeypair
        
        Boolean[] boosEntryTcrFromPkcs12 = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_, 
          kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (boosEntryTcrFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryTcrFromPkcs12");
            return null;
        }
        
        Boolean[] boosEntryKprFromPkcs12 = 
            UtilKstAbs.s_getBoosEntryKpr(super._frmOwner_, kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (boosEntryKprFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosEntryKprFromPkcs12");
            return null;
        }
        

        Boolean[] boosSelfSignedCertFromPkcs12 = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_, kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (boosSelfSignedCertFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosSelfSignedCertFromPkcs12");
            return null;
        }
        
        Boolean[] boosTrustedCertFromPkcs12 = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_, kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (boosTrustedCertFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTrustedCertFromPkcs12");
            return null;
        }
        
        String[] strsSizeKeyPublFromPkcs12 = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,  kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (strsSizeKeyPublFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsSizeKeyPublFromPkcs12");
            return null;
        }
        
        String[] strsAlgoKeyPublFromPkcs12 = UtilKstAbs.s_getStrsAlgoKeyPubl(super._frmOwner_,  kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (strsAlgoKeyPublFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoKeyPublFromPkcs12");
            return null;
        }
        
        
        String[] strsTypeCertFromPkcs12 = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_, kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (strsTypeCertFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsTypeCertFromPkcs12");
            return null;
        }
        
        String[] strsAlgoSigCertFromPkcs12 = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,  kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (strsAlgoSigCertFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil strsAlgoSigCertFromPkcs12");
            return null;
        }

        Date[] dtesLastModifiedFromPkcs12 = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,  kstOpenToFromPkcs12, strsAliasFromPkcs12);

        if (dtesLastModifiedFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil dtesLastModifiedFromPkcs12");
            return null;
        }
        
        
        // ----
        
        Boolean[] boosTypeCertX509FromPkcs12 = super._getBoosTypeCertX509_(kstOpenToFromPkcs12, strsAliasFromPkcs12);
        
        if (boosTypeCertX509FromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosTypeCertX509FromPkcs12");
            return null;
        }
        
        
        Boolean[] boosElligibleFromPkcs12 = this._getBoosElligibleFromPkcs12(
            strsAlgoKeyPublFromPkcs12, boosTypeCertX509FromPkcs12);
        
        if (boosElligibleFromPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil boosElligibleFromPkcs12");
            return null;
        }
        
        
        
        
        
        // -- 
        // clean-up
        boosTypeCertX509FromPkcs12 = null;
        
        // ---
        // check for elligible entry
        
        //boolean blnElligibleFromPkcs12 = false;
        int intIdElligibleLast = -1;
        int intNbElligible = 0;
        
        
        for (int i=0; i<boosElligibleFromPkcs12.length; i++)
        {
            if (boosElligibleFromPkcs12[i].booleanValue() == true)
            {
                //blnElligibleFromPkcs12 = true;
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
        
        //    if only one elligible keypair, just return therespective alias!
        //    ==> no need to display dialogSelectValidKeypair
        
        
        //MySystem.s_printOutWarning(this, strMethod, "TEMPO IN COMMENTS: if (intNbElligible == 1)");
        
        //if (intNbElligible == 1)
        //{
          //  super._setEnabledCursorWait_(false);
          //  MySystem.s_printOutTrace(this, strMethod, "intNbElligible == 1, strsAliasFromPkcs12[intIdElligibleLast]=" + strsAliasFromPkcs12[intIdElligibleLast]);
          //  return strsAliasFromPkcs12[intIdElligibleLast];
        //}
        
        // ----
        // restore default cursor
        super._setEnabledCursorWait_(false);
        
        // ----

       
        DTblEntryKprOpenPkcs12Any dlgFromPkcs12 = new DTblEntryKprOpenPkcs12Any(
            super._frmOwner_, 
          
            kstOpenToFromPkcs12
            );
        
        if (! dlgFromPkcs12.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlgFromPkcs12.load(boosElligibleFromPkcs12, strsAliasFromPkcs12, 
            boosEntryKprFromPkcs12, 
            boosEntryTcrFromPkcs12, 
            boosSelfSignedCertFromPkcs12, 
            boosTrustedCertFromPkcs12, 
            strsAlgoKeyPublFromPkcs12, 
            strsSizeKeyPublFromPkcs12,
            strsTypeCertFromPkcs12, 
            strsAlgoSigCertFromPkcs12, dtesLastModifiedFromPkcs12))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlgFromPkcs12.setVisible(true);
        
        // --
        
        String strAliasKprFromPkcs12 = dlgFromPkcs12.getAlias();
        
        if (strAliasKprFromPkcs12 == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKprFromPkcs12, aborted by user");
            return null;
        }
        
        return strAliasKprFromPkcs12;
    }
}