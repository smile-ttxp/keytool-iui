package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "KeyPair"
    
    
    known subclasses:
    . KTLKprSaveNewDsaAbs
    . KTLKprSaveNewRsaAbs

**/


import org.bouncycastle.jce.provider.JDKKeyPairGenerator;
import com.google.code.p.keytooliui.ktl.swing.dialog.*;
import com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI;

import com.google.code.p.keytooliui.shared.lang.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

import org.bouncycastle.jce.X509Principal;
//import org.bouncycastle.jce.X509V1CertificateGenerator; // deprecated from bc120 to bc130
import org.bouncycastle.x509.X509V1CertificateGenerator;
//import org.bouncycastle.jce.X509V3CertificateGenerator; // deprecated from bc120 to bc130
import org.bouncycastle.asn1.DERObjectIdentifier;

// ----
// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.Security;
import java.security.SecureRandom;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.InvalidKeyException;
import java.security.Provider;
// --
import java.security.cert.X509Certificate;

// ----

import java.awt.*;
import java.util.*;
import java.math.*;


abstract public class KTLKprSaveNewAbs extends KTLKprSaveAbs
{
    // -----------------------------
    // final static protected String
    
    final static protected String _STR_PROVIDER_KPG_ = "BC";
    
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTypeSecureRandom = UtilCrtX509.f_s_strDigestAlgoSHA1 + "PRNG";
    final static private String _f_s_strProviderSecureRandom = KTLAbs.f_s_strProviderKstJks;
    
    

    
    // ---------
    // PROTECTED
    
    protected String _strCertAlgoSignType_ = null;
    
    /*
     *generate cert version #1
     *MEMO: this method is overwritten by final subclasses that should generate certs version #3!
     */
    protected X509Certificate _generateCertX509_(
        PublicKey pkyKeyPublic,
        PrivateKey pkyKeyPrivate,
        Date dteCertNotBefore,
        Date dteCertNotAfter,
        Hashtable<DERObjectIdentifier, String> hstAttributes,
        Vector<DERObjectIdentifier> vecX509Principal
        )
    {
        String strMethod = "_generateCertX509_(...)";
        
        if (pkyKeyPublic==null || pkyKeyPrivate==null || 
            dteCertNotBefore==null || dteCertNotAfter==null ||
            hstAttributes==null || vecX509Principal==null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }
        
        
        
        X509V1CertificateGenerator certGen = new X509V1CertificateGenerator();

        certGen.setIssuerDN(new X509Principal(vecX509Principal, hstAttributes));
        certGen.setSubjectDN(new X509Principal(vecX509Principal, hstAttributes));
        
        certGen.setNotBefore(dteCertNotBefore);   
        certGen.setNotAfter(dteCertNotAfter);
        
        certGen.setPublicKey(pkyKeyPublic);
        certGen.setSignatureAlgorithm(this._strCertAlgoSignType_);

        BigInteger bir = new BigInteger(Long.toString(System.currentTimeMillis() / 1000));
        certGen.setSerialNumber(bir);
        X509Certificate crt = null;

        try
        {
            crt = certGen.generate(pkyKeyPrivate, KTLKprSaveNewAbs._STR_PROVIDER_KPG_);   
        }
        
        catch (InvalidKeyException excInvalidKey)
        {
            excInvalidKey.printStackTrace();
            
            MySystem.s_printOutError(this, strMethod, "Got excInvalidKey Exception");
                
            String strBody = "Got InvalidKey Exception";
            strBody += "\n  " + excInvalidKey.getMessage();
            
            if (excInvalidKey.getMessage().trim().toLowerCase().startsWith("key is too short"))
              strBody += "\n\n " + "Workaround: please increase key size.";
                
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
                
            return null;
        }
        
        catch (Exception exc)
        {
            exc.printStackTrace();
            
            MySystem.s_printOutError(this, strMethod, "Got excException");
                
            String strBody = "Got Exception.";
            strBody += "\n  " + exc.getMessage();
                
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
                
            return null;
        }
        
        return crt;
    }
    
    //protected String _strProviderKpg_ = null; // "Kpg" means KeyPair Generator", "SUN" for DSA, "BC", "SunRsaSign" for RSA    
        
    protected KTLKprSaveNewAbs(
        Frame frmOwner, 
      
        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // output
        String strProviderKst,   // eg: "SUN", "BC"
        //String strProviderKpg,   // eg: "SUN, "BC", "SunRsaSign", "SunJCE", SunJGSS"
        int intSizeKpr,
        //int intCertX509Version, // either 1 or 3 (version #1 or version #3)
        String strCertAlgoSignType, // eg: SHA1withDSA
        
        
        
        int intValidityKpr,
        
        String strKprX500DN_CN,    // "DN" for "Distinguisheds names", "CN" for "Common Name"
        String strKprX500DN_OU,
        String strKprX500DN_O,
        String strKprX500DN_L,
        String strKprX500DN_ST,
        String strKprX500DN_C,
        String strKprX500DN_EMAIL,
            
        String strKprX500DNM_T, // "DN" for "Distinguished Name", "M" for "More""
        String strKprX500DNM_SN,
        String strKprX500DNM_STREET,
        String strKprX500DNM_BUSINESS_CATEGORY,
        String strKprX500DNM_POSTAL_CODE,
        String strKprX500DNM_DN_QUALIFIER,
        String strKprX500DNM_PSEUDONYM,
        String strKprX500DNM_DATE_OF_BIRTH,
        String strKprX500DNM_PLACE_OF_BIRTH,
        String strKprX500DNM_GENDER,
        String strKprX500DNM_COUNTRY_OF_CITIZENSHIP,
        String strKprX500DNM_COUNTRY_OF_RESIDENCE,
        String strKprX500DNM_NAME_AT_BIRTH,
        String strKprX500DNM_POSTAL_ADDRESS,

        String strKprX520N_SURNAME,
        String strKprX520N_GIVENNAME,
        String strKprX520N_INITIALS,
        String strKprX520N_GENERATION,
        String strKprX520N_UNIQUE_IDENTIFIER,
        
        String strTypeKpr          // eg: DSA, RSA, assigned by subclasses
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        
        
        // output
        //this._strProviderKpg_ = strProviderKpg;
        this._intSizeKpr = intSizeKpr;
        //this._intCertX509Version = intCertX509Version;
        this._strCertAlgoSignType_ = strCertAlgoSignType;
        
        
        this._intValidityKpr = intValidityKpr;
        
        this._strKprX500DN_CN = strKprX500DN_CN;
        this._strKprX500DN_OU = strKprX500DN_OU;
        this._strKprX500DN_O = strKprX500DN_O;
        this._strKprX500DN_L = strKprX500DN_L;
        this._strKprX500DN_ST = strKprX500DN_ST;
        this._strKprX500DN_C = strKprX500DN_C;
        this._strKprX500DN_EMAIL = strKprX500DN_EMAIL; // nil value allowed
        
        this._strKprX500DNM_T = strKprX500DNM_T; // "DN" for "Distinguished Name", "M" for "More""
        this._strKprX500DNM_SN = strKprX500DNM_SN;
        this._strKprX500DNM_STREET = strKprX500DNM_STREET;
        this._strKprX500DNM_BUSINESS_CATEGORY = strKprX500DNM_BUSINESS_CATEGORY;
        this._strKprX500DNM_POSTAL_CODE = strKprX500DNM_POSTAL_CODE;
        this._strKprX500DNM_DN_QUALIFIER = strKprX500DNM_DN_QUALIFIER;
        this._strKprX500DNM_PSEUDONYM = strKprX500DNM_PSEUDONYM;
        this._strKprX500DNM_DATE_OF_BIRTH = strKprX500DNM_DATE_OF_BIRTH;
        this._strKprX500DNM_PLACE_OF_BIRTH = strKprX500DNM_PLACE_OF_BIRTH;
        this._strKprX500DNM_GENDER = strKprX500DNM_GENDER;
        this._strKprX500DNM_COUNTRY_OF_CITIZENSHIP = strKprX500DNM_COUNTRY_OF_CITIZENSHIP;
        this._strKprX500DNM_COUNTRY_OF_RESIDENCE = strKprX500DNM_COUNTRY_OF_RESIDENCE;
        this._strKprX500DNM_NAME_AT_BIRTH = strKprX500DNM_NAME_AT_BIRTH;
        this._strKprX500DNM_POSTAL_ADDRESS = strKprX500DNM_POSTAL_ADDRESS;

        this._strKprX520N_SURNAME = strKprX520N_SURNAME;
        this._strKprX520N_GIVENNAME = strKprX520N_GIVENNAME;
        this._strKprX520N_INITIALS = strKprX520N_INITIALS;
        this._strKprX520N_GENERATION = strKprX520N_GENERATION;
        this._strKprX520N_UNIQUE_IDENTIFIER = strKprX520N_UNIQUE_IDENTIFIER;
        
        this._strTypeKpr = strTypeKpr;
    }
    
    private KeyPair _getKprNewProvOther() // RSA, DSA
    {
        String strMethod = "_getKprNewProvOther()";
        
        // ----
        // get provider
        Provider prv = _getProviderKpr();
        
        if (prv == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil prv");
        }
        

        
        // Get a key pair generator
        KeyPairGenerator kpg = null;
        
        
        try
        {
            kpg = KeyPairGenerator.getInstance(this._strTypeKpr, "BC" /*prv*/);
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excNoSuchAlgorithm caught, this._strTypeKpr=" + this._strTypeKpr + ", prv.getName()=" + prv.getName());
        }
        
        catch(NoSuchProviderException excNoSuchProvider)
        {
            excNoSuchProvider.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excNoSuchAlgorithm caught, this._strTypeKpr=" + this._strTypeKpr + ", prv.getName()=" + prv.getName());
        }
        
        // Create a SecureRandom
        SecureRandom srm = null;
        
        // beg
        
        if (this._strTypeKpr.equalsIgnoreCase("DSA"))
        {
        
            Provider prvSrm = Security.getProvider(KTLKprSaveNewAbs._f_s_strProviderSecureRandom);
            // test for EC (Elliptic Curve)
            //Provider prvSrm = Security.getProvider("BC");
            // NOT WORKING

            if (prvSrm == null)
                MySystem.s_printOutExit(this, strMethod, "nil prvSrm");


            try
            {
                srm = SecureRandom.getInstance(
                    KTLKprSaveNewAbs._f_s_strTypeSecureRandom, prvSrm);
            }

            catch(NoSuchAlgorithmException excNoSuchAlgorithm)
            {
                excNoSuchAlgorithm.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excNoSuchAlgorithm caught, this._strTypeKpr=" + this._strTypeKpr + ", prv.getName()=" + prv.getName());
            }
        
        }
        // end
		
		
        // !!!!! Exception mentionned in J2SDK 1.4.0 API, but does not compile with J2SDK 1.4.1 compiler !!!! 
        // ==> InvalidParameterException 
                
        try
        {
            if (srm != null) // DSA: SUN provider
                kpg.initialize(this._intSizeKpr, srm);
            else
                kpg.initialize(this._intSizeKpr/*, srm*/);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught, aborting");
            
            String strBody = "Got Exception.";
            strBody += "\n  " + exc.getMessage();
                
            OPAbstract.s_showDialogError(
                super._frmOwner_, strBody);
            
            return null;
        }
        

        
        DGenKeypair dlg = new DGenKeypair(super._frmOwner_, kpg);
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");

        dlg.setVisible(true);
  
        
        
        KeyPair kprNew = dlg.getKeypair();        
        
        if (kprNew == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "user aborted");
            return null;
        }
        	
        // ----
        return kprNew;
    }  
    
    private KeyPair _getKprNewProvBc() // used by EC
    {
        String strMethod = "_getKprNewProvBc()";
        
        // ----
        // get provider
        Provider prv = _getProviderKpr();
        
        if (prv == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil prv");
        }
        

        
        // Get a key pair generator
        
        JDKKeyPairGenerator kpg = null;
        
        try
        {
            kpg = (JDKKeyPairGenerator) JDKKeyPairGenerator.getInstance(this._strTypeKpr, prv);
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excNoSuchAlgorithm caught, this._strTypeKpr=" + this._strTypeKpr + ", prv.getName()=" + prv.getName());
        }
		
        // !!!!! Exception mentionned in J2SDK 1.4.0 API, but does not compile with J2SDK 1.4.1 compiler !!!! 
        // ==> InvalidParameterException 
                
        try
        {
            kpg.initialize(this._intSizeKpr);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught, aborting");
            
            String strBody = "Got Exception.";
            strBody += "\n  " + exc.getMessage();
                
            OPAbstract.s_showDialogError(
                super._frmOwner_, strBody);
            
            return null;
        }
        
        
        DGenKeypair dlg = new DGenKeypair(super._frmOwner_,  kpg);
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");

        dlg.setVisible(true);

        KeyPair kprNew = dlg.getKeypair();        
        
        if (kprNew == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "user aborted");
            return null;
        }	
		
        // ----
        return kprNew;
    }   
    
    
    /*
        if any code error, exiting
        else if any other error, show dialog warning, then return null
        else return new KeyPair
    */
    protected KeyPair _getKprNew_()
    {
        
        if (this._strTypeKpr.equalsIgnoreCase("EC"))
            return _getKprNewProvBc();
        

        return _getKprNewProvOther();
    }     
    
    /**
            ONLY FOR JKS OR JCEKS, NOT FOR PKCS12
            
        MEMO: if alias already existing in keystore
        will be overwritten!!
        ... BUT already tested in calling code ==> never be the case
        
        if any code error, exiting
        else if any other error, show warning dialog, then return false;
        else return true
    **/
    protected boolean _assignNewEntry2OpenKeystore_(
        KeyStore kstOpen,
        KeyPair kprNew,
        X509Certificate crtX509New,
        String strAliasKpr,
        char[] chrsPasswdKpr
        )
    {
        String strMethod = "_assignNewEntry2OpenKeystore_(...)";
        
        if (kstOpen==null || kprNew==null || crtX509New==null || strAliasKpr==null || chrsPasswdKpr==null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }
        
        
        boolean blnAlreadyListed = false;
        
        try
        {
            blnAlreadyListed = kstOpen.containsAlias(strAliasKpr);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excKeyStore caught");
        }
        
        if (blnAlreadyListed) // should never  appear, as it has been checked in the calling code.
        {
            MySystem.s_printOutWarning(this, strMethod, "blnAlreadyListed, will be overwritten");
            
            
            /**String strBody = "keystore already contains an alias named:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += strAliasKpr;
            strBody += "\"";
            
            strBody += "\n\n";
            strBody += "(Memo: aliases are case-insensitive)";
            
                
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, strBody);
                
            return false;**/
        }
        
        // --
        
        PrivateKey pkyKeyPrivate = kprNew.getPrivate();
        
        if (pkyKeyPrivate == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil pkyKeyPrivate");
        }
        
        if (! UtilKstAbs.s_setKeyEntry(super._frmOwner_,  
            kstOpen, strAliasKpr, pkyKeyPrivate, chrsPasswdKpr, new X509Certificate[]{ crtX509New }))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    
    
    /*
        uses package provider "SUN" or "BC" or "SunJCE"
        create certifcate version: either 1 or 3 (only "1" for now)
        
        
        if any code error, exiting
        else if any other error, show dialog warning, then return null
        else return new KeyPair
        
    */
    protected X509Certificate _getX509CertNew_(
        KeyPair kprNew
        )
    {
        String strMethod = "_getX509CertNew_(kprNew)";
        

        if (kprNew == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil kprNew");
        }
        
        
        PrivateKey pkyKeyPrivate = kprNew.getPrivate();
        
        if (pkyKeyPrivate == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil pkyKeyPrivate");
        }

            
        // Holds certificate attributes
        Hashtable<DERObjectIdentifier, String> hstAttributes = new Hashtable<DERObjectIdentifier, String>();
        Vector<DERObjectIdentifier> vecX509Principal = new Vector<DERObjectIdentifier>();

        // Load certificate attributes 
        if (this._strKprX500DN_CN == null) // mandatory
            MySystem.s_printOutExit(this, strMethod, "nil this._strKprX500DN_CN");
        
        
        
        hstAttributes.put(X509Principal.CN, this._strKprX500DN_CN);
        vecX509Principal.add(0, X509Principal.CN);
        
        
        
        
        if (this._strKprX500DN_OU != null)
        {
            hstAttributes.put(X509Principal.OU, this._strKprX500DN_OU);
            vecX509Principal.add(0, X509Principal.OU);
        }
        

        if (this._strKprX500DN_O != null)
        {
            hstAttributes.put(X509Principal.O, this._strKprX500DN_O);
            vecX509Principal.add(0, X509Principal.O);
        }

        if (this._strKprX500DN_L != null)
        {
            hstAttributes.put(X509Principal.L, this._strKprX500DN_L);
            vecX509Principal.add(0, X509Principal.L);
        }

        if (this._strKprX500DN_ST != null)
        {
            hstAttributes.put(X509Principal.ST, this._strKprX500DN_ST);
            vecX509Principal.add(0, X509Principal.ST);
        }

        if (this._strKprX500DN_C != null)
        {
            hstAttributes.put(X509Principal.C, this._strKprX500DN_C);
            vecX509Principal.add(0, X509Principal.C);
        }
        
        if (this._strKprX500DN_EMAIL != null)
        {
            hstAttributes.put(X509Principal.E, this._strKprX500DN_EMAIL);
            vecX509Principal.add(0, X509Principal.E);
        }
        
        // ----
        
        // beg X500DNM (More)
     
        if (this._strKprX500DNM_T != null)
        {
            hstAttributes.put(X509Principal.T, this._strKprX500DNM_T);
            vecX509Principal.add(0, X509Principal.T);
        }
        
        if (this._strKprX500DNM_SN != null)
        {
            hstAttributes.put(X509Principal.SN, this._strKprX500DNM_SN);
            vecX509Principal.add(0, X509Principal.SN);
        }
        
        if (this._strKprX500DNM_STREET != null)
        {
            hstAttributes.put(X509Principal.STREET, this._strKprX500DNM_STREET);
            vecX509Principal.add(0, X509Principal.STREET);
        }
        
        if (this._strKprX500DNM_BUSINESS_CATEGORY != null)
        {
            hstAttributes.put(X509Principal.BUSINESS_CATEGORY, this._strKprX500DNM_BUSINESS_CATEGORY);
            vecX509Principal.add(0, X509Principal.BUSINESS_CATEGORY);
        }
        
        if (this._strKprX500DNM_POSTAL_CODE != null)
        {
            hstAttributes.put(X509Principal.POSTAL_CODE, this._strKprX500DNM_POSTAL_CODE);
            vecX509Principal.add(0, X509Principal.POSTAL_CODE);
        }
        
        if (this._strKprX500DNM_DN_QUALIFIER != null)
        {
            hstAttributes.put(X509Principal.DN_QUALIFIER, this._strKprX500DNM_DN_QUALIFIER);
            vecX509Principal.add(0, X509Principal.DN_QUALIFIER);
        }
        
        if (this._strKprX500DNM_PSEUDONYM != null)
        {
            hstAttributes.put(X509Principal.PSEUDONYM, this._strKprX500DNM_PSEUDONYM);
            vecX509Principal.add(0, X509Principal.PSEUDONYM);
        }
        
        //should be this format: yyyymmdd000000Z
        if (this._strKprX500DNM_DATE_OF_BIRTH != null)
        {
            if (! StringFilterUI.s_isAllowedDateBirth(this._strKprX500DNM_DATE_OF_BIRTH))
                MySystem.s_printOutExit(this, strMethod, "wrong date of birth format: " + this._strKprX500DNM_DATE_OF_BIRTH);
            
            hstAttributes.put(X509Principal.DATE_OF_BIRTH, this._strKprX500DNM_DATE_OF_BIRTH + "000000Z");
            vecX509Principal.add(0, X509Principal.DATE_OF_BIRTH);
            
        }
        
        if (this._strKprX500DNM_PLACE_OF_BIRTH != null)
        {
            hstAttributes.put(X509Principal.PLACE_OF_BIRTH, this._strKprX500DNM_PLACE_OF_BIRTH);
            vecX509Principal.add(0, X509Principal.PLACE_OF_BIRTH);
        }
        
        if (this._strKprX500DNM_GENDER != null)
        {
            hstAttributes.put(X509Principal.GENDER, this._strKprX500DNM_GENDER);
            vecX509Principal.add(0, X509Principal.GENDER);
        }
        
        if (this._strKprX500DNM_COUNTRY_OF_CITIZENSHIP != null)
        {
            hstAttributes.put(X509Principal.COUNTRY_OF_CITIZENSHIP, this._strKprX500DNM_COUNTRY_OF_CITIZENSHIP);
            vecX509Principal.add(0, X509Principal.COUNTRY_OF_CITIZENSHIP);
        }
        
        if (this._strKprX500DNM_COUNTRY_OF_RESIDENCE != null)
        {
            hstAttributes.put(X509Principal.COUNTRY_OF_RESIDENCE, this._strKprX500DNM_COUNTRY_OF_RESIDENCE);
            vecX509Principal.add(0, X509Principal.COUNTRY_OF_RESIDENCE);
        }
        
        if (this._strKprX500DNM_NAME_AT_BIRTH != null)
        {
            hstAttributes.put(X509Principal.NAME_AT_BIRTH, this._strKprX500DNM_NAME_AT_BIRTH);
            vecX509Principal.add(0, X509Principal.NAME_AT_BIRTH);
        }
        
        if (this._strKprX500DNM_POSTAL_ADDRESS != null)
        {
            hstAttributes.put(X509Principal.POSTAL_ADDRESS, this._strKprX500DNM_POSTAL_ADDRESS);
            vecX509Principal.add(0, X509Principal.POSTAL_ADDRESS);
        }
        // end X500DNM (More)
        
        // beg X520N
        
        if (this._strKprX520N_SURNAME != null)
        {
            hstAttributes.put(X509Principal.SURNAME, this._strKprX520N_SURNAME);
            vecX509Principal.add(0, X509Principal.SURNAME);
        }
        
        if (this._strKprX520N_GIVENNAME != null)
        {
            hstAttributes.put(X509Principal.GIVENNAME, this._strKprX520N_GIVENNAME);
            vecX509Principal.add(0, X509Principal.GIVENNAME);
        }
        
        if (this._strKprX520N_INITIALS != null)
        {
            hstAttributes.put(X509Principal.INITIALS, this._strKprX520N_INITIALS);
            vecX509Principal.add(0, X509Principal.INITIALS);
        }
        
        if (this._strKprX520N_GENERATION != null)
        {
            hstAttributes.put(X509Principal.GENERATION, this._strKprX520N_GENERATION);
            vecX509Principal.add(0, X509Principal.GENERATION);
        }
        
        if (this._strKprX520N_UNIQUE_IDENTIFIER != null)
        {
            hstAttributes.put(X509Principal.UNIQUE_IDENTIFIER, this._strKprX520N_UNIQUE_IDENTIFIER);
            vecX509Principal.add(0, X509Principal.UNIQUE_IDENTIFIER);
        }  
        
        // end X520N
        
        // ----
        
        X509Certificate crt = null;
        
        // --
        
        long lng = System.currentTimeMillis();
        Date dteCertNotBefore = new Date(lng);
        Date dteCertNotAfter = new Date(lng + ((long) this._intValidityKpr * 24 * 60 * 60 * 1000));
        
        // --
        
        crt = _generateCertX509_(
                kprNew.getPublic(), pkyKeyPrivate, dteCertNotBefore, dteCertNotAfter, hstAttributes, vecX509Principal);
        
       
        
        /*
        
        if (this._intCertX509Version == KTLAbs.f_s_itgsCertVersion[0].intValue())
            crt = _generateCertX509Vers1(
                kprNew.getPublic(), pkyKeyPrivate, dteCertNotBefore, dteCertNotAfter, hstAttributes, vecX509Principal);
        
        else if (this._intCertX509Version == KTLAbs.f_s_itgsCertVersion[1].intValue())
             crt = _generateCertX509Vers3(
                kprNew.getPublic(), pkyKeyPrivate, dteCertNotBefore, dteCertNotAfter, hstAttributes, vecX509Principal);
        
        else
            MySystem.s_printOutExit(this, strMethod, 
                "uncaught this._intCertX509Version value, this._intCertX509Version=" + this._intCertX509Version);
        */
        
        if (crt == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crt");
            return null;
        }
        
        return crt;
    }
    
    
    // -------
    // PRIVATE
    
    private int _intSizeKpr = -1;
    private int _intValidityKpr = -1;
    
    private String _strTypeKpr = null; // "DSA" or "RSA"
    
    
    //private int _intCertX509Version = -1; // should be either 1 or 3
    
    
 
    private String _strKprX500DN_CN = null;
    private String _strKprX500DN_OU = null;
    private String _strKprX500DN_O = null;
    private String _strKprX500DN_L = null;
    private String _strKprX500DN_ST = null;
    private String _strKprX500DN_C = null;
    private String _strKprX500DN_EMAIL = null;
    
    private String _strKprX500DNM_T = null; // "DN" for "Distinguished Name", "M" for "More""
    private String _strKprX500DNM_SN = null;
    private String _strKprX500DNM_STREET = null;
    private String _strKprX500DNM_BUSINESS_CATEGORY = null;
    private String _strKprX500DNM_POSTAL_CODE = null;
    private String _strKprX500DNM_DN_QUALIFIER = null;
    private String _strKprX500DNM_PSEUDONYM = null;
    private String _strKprX500DNM_DATE_OF_BIRTH = null;
    private String _strKprX500DNM_PLACE_OF_BIRTH = null;
    private String _strKprX500DNM_GENDER = null;
    private String _strKprX500DNM_COUNTRY_OF_CITIZENSHIP = null;
    private String _strKprX500DNM_COUNTRY_OF_RESIDENCE = null;
    private String _strKprX500DNM_NAME_AT_BIRTH = null;
    private String _strKprX500DNM_POSTAL_ADDRESS = null;
    
    private String _strKprX520N_SURNAME = null;
    private String _strKprX520N_GIVENNAME = null;
    private String _strKprX520N_INITIALS = null;
    private String _strKprX520N_GENERATION = null;
    private String _strKprX520N_UNIQUE_IDENTIFIER = null;
    
    
    /**
        if any error, NO ERROR/WARNING dialog, just return false, calling method should perform an exit
        
        in case of BC provider, it should already be installed!
    **/
    private Provider _getProviderKpr()
    {
        String strMethod = "_getProviderKpr()";
        
        /*if (this._strProviderKpg_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strProviderKpg_");
            return null;
        }*/
        
        if (this._strTypeKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strTypeKpr");
            return null;
        }
        
        if (! KTLKprAbs._s_isProviderKpgAllowed_(this._strTypeKpr, KTLKprSaveNewAbs._STR_PROVIDER_KPG_))
        {
            MySystem.s_printOutError(this, strMethod, "! KTLKprAbs._s_isProviderKpgAllowed_(this._strTypeKpr, KTLKprSaveNewAbs._STR_PROVIDER_KPG_), this._strTypeKpr=" +
                    this._strTypeKpr + ", KTLKprSaveNewAbs._STR_PROVIDER_KPG_=" + KTLKprSaveNewAbs._STR_PROVIDER_KPG_);
            return null;
        }
        
        Provider prv = Security.getProvider(KTLKprSaveNewAbs._STR_PROVIDER_KPG_);
        
        if (prv == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil prv, KTLKprSaveNewAbs._STR_PROVIDER_KPG_=" + 
                    KTLKprSaveNewAbs._STR_PROVIDER_KPG_ + 
                    ", this._strTypeKpr=" + 
                    this._strTypeKpr);
            return null;
        }
      
        return prv;
    }
    
    

    


    
  
    
    
}

