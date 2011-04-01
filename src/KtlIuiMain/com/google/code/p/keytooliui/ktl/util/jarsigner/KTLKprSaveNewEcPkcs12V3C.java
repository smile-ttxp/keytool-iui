package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    KTLKprSaveNewEcPkcs12V3C: 
 *     "Kpr" for "KeyPair"
 *     "V3C" for "Version #3 Certificate"
    
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry
 *
 *MEMO: this one for certs version #3
 *      superclass of this one will generate certs version #1!

**/


import com.google.code.p.keytooliui.shared.lang.MySystem;


import org.bouncycastle.asn1.DERObjectIdentifier;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;


import java.awt.*;
import java.util.*;

import com.google.code.p.keytooliui.shared.util.jarsigner.UtilCrtX509;


final public class KTLKprSaveNewEcPkcs12V3C extends KTLKprSaveNewEcPkcs12
{
    // ------
    // public
    
    public KTLKprSaveNewEcPkcs12V3C(
        Frame frmOwner, 
        
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Pkcs12 
        char[] chrsPasswdOpenKst, 
        
        // output
        int intSizeKpr,
        String strCertAlgoSignType,
        
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
            
        // keyUsage cert's extension
        boolean blnCrtExtKeyUsage,
        boolean blnCrtExtKeyUsageCritical,
        int intCrtExtKeyUsageValue,
            
        // extKeyUsage cert's extension
        boolean blnCrtExtExtKeyUsageCritical,
        Vector<DERObjectIdentifier> vecCrtExtExtKeyUsage
        )
    {
        
        super(
            frmOwner, 
     
        
            // input
            strPathAbsOpenKst, // existing keystore of type Pkcs12 
            chrsPasswdOpenKst, 
        
            // output
            intSizeKpr,
            strCertAlgoSignType,  
        
            intValidityKpr,
        
            strKprX500DN_CN,    // "DN" for "Distinguisheds names", "CN" for "Common Name"
            strKprX500DN_OU,
            strKprX500DN_O,
            strKprX500DN_L,
            strKprX500DN_ST,
            strKprX500DN_C,
            strKprX500DN_EMAIL,
                
            strKprX500DNM_T, // "DN" for "Distinguished Name", "M" for "More""
            strKprX500DNM_SN,
            strKprX500DNM_STREET,
            strKprX500DNM_BUSINESS_CATEGORY,
            strKprX500DNM_POSTAL_CODE,
            strKprX500DNM_DN_QUALIFIER,
            strKprX500DNM_PSEUDONYM,
            strKprX500DNM_DATE_OF_BIRTH,
            strKprX500DNM_PLACE_OF_BIRTH,
            strKprX500DNM_GENDER,
            strKprX500DNM_COUNTRY_OF_CITIZENSHIP,
            strKprX500DNM_COUNTRY_OF_RESIDENCE,
            strKprX500DNM_NAME_AT_BIRTH,
            strKprX500DNM_POSTAL_ADDRESS,

            strKprX520N_SURNAME,
            strKprX520N_GIVENNAME,
            strKprX520N_INITIALS,
            strKprX520N_GENERATION,
            strKprX520N_UNIQUE_IDENTIFIER
            );
        
        this._blnCrtExtKeyUsage = blnCrtExtKeyUsage;
        this._blnCrtExtKeyUsageCritical = blnCrtExtKeyUsageCritical;
        this._intCrtExtKeyUsageValue = intCrtExtKeyUsageValue;
        
        this._blnCrtExtExtKeyUsageCritical = blnCrtExtExtKeyUsageCritical;
        this._vecCrtExtExtKeyUsage = vecCrtExtExtKeyUsage;
    }
    
    // ---------
    // protected
    
    /*
     *overwriting superclass's method
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
        
        X509Certificate crt = UtilCrtX509.s_generateCertX509V3(
            pkyKeyPublic,
            pkyKeyPrivate,
            dteCertNotBefore,
            dteCertNotAfter,
            hstAttributes,
            vecX509Principal,
        
            super._strCertAlgoSignType_,
            super._frmOwner_,
           
            this._vecCrtExtExtKeyUsage,
            this._blnCrtExtExtKeyUsageCritical,
            this._blnCrtExtKeyUsage,
            this._blnCrtExtKeyUsageCritical,
            this._intCrtExtKeyUsageValue,
            KTLKprSaveNewAbs._STR_PROVIDER_KPG_);
        
        if (crt == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crt");
            return null;
        }
        
        return crt;
    }
    
    // -------
    // private
    
    private boolean _blnCrtExtKeyUsage = false;
    private boolean _blnCrtExtKeyUsageCritical = false;
    private int _intCrtExtKeyUsageValue = 0;
    
    private boolean _blnCrtExtExtKeyUsageCritical = false;
    private Vector<DERObjectIdentifier> _vecCrtExtExtKeyUsage = null;
}