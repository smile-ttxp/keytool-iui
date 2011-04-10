package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Shk" for "SharedKey"
    
    
    known subclasses:
    . KTLShkSaveNewAllAbs
**/

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

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
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.Provider;
// --
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;
import java.math.*;


abstract public class KTLShkSaveNewAbs extends KTLShkSaveAbs
{    
    // ---------
    // PROTECTED
    
    protected KTLShkSaveNewAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // output
        String strProviderKst,   // eg: "SUN", "BC"
        String strInstanceKeyGenerator // eg: DES (Signature Algorithm)
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        this._strInstanceKeyGenerator = strInstanceKeyGenerator;
    }
    
    
    /*
        if any code error, exiting
        else if any other error, show dialog warning, then return null
        else return new SecretKey
    */
    protected SecretKey _getShkNew_() 
        throws 
            NoSuchAlgorithmException
    {
        //String strMethod = "_getShkNew_()";
        
        /* MEMO: default sizes:
         *Arcfour: 16
         *Blowfish: 16
         *DES: 8
         *DESede: 24
         *HmacMD5: 64
         *HmacSHA1: 64
         *HmacSHA256: 32
         */
        
        //memo: key length ==> key size
        //init(64) ==> 8 bit key size
        //init(192) ==> 24 bit key size
        //init(256) ==> 32 bit key size
        //init(1024) ==> 128 bit key size
        //init(2048) ==> 256 bit key size
       
                
        
        
         KeyGenerator kgr = KeyGenerator.getInstance(this._strInstanceKeyGenerator);
         
         // the following does not work!
         if (this._strInstanceKeyGenerator.toLowerCase().compareTo("des") == 0) // memo: default: size=8 bits
            kgr.init(64); 
         
         else if (this._strInstanceKeyGenerator.toLowerCase().compareTo("desede") == 0) // memo: default: size=24 bits
            kgr.init(192);
         
         
         // test, AES, memo default 16 bit key size
         /*else if (this._strInstanceKeyGenerator.toLowerCase().compareTo("aes") == 0) // memo: default: size=16 bits
         {
            System.out.println("AES, init(1024)");
            kgr.init(1024);
         }*/
         
         SecretKey sky = kgr.generateKey();
         
         return sky;
        
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
        SecretKey ShkNew,
        String strAliasShk,
        char[] chrsPasswdShk
        )
    {
        String strMethod = "_assignNewEntry2OpenKeystore_(...)";
        
        if (kstOpen==null || ShkNew==null || strAliasShk==null || chrsPasswdShk==null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }
        
        
        boolean blnAlreadyListed = false;
        
        try
        {
            blnAlreadyListed = kstOpen.containsAlias(strAliasShk);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excKeyStore caught");
        }
        
        if (blnAlreadyListed) // should never  appear, as it has been checked in the calling code.
        {
            MySystem.s_printOutWarning(this, strMethod, "blnAlreadyListed, will be overwritten");
        }
        
        
        if (! UtilKstAbs.s_setKeyEntry(super._frmOwner_, 
            kstOpen, strAliasShk, ShkNew, chrsPasswdShk, null))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // --
        
        
        return true;
    }

    // -------
    // PRIVATE

    private String _strInstanceKeyGenerator = null;

}
