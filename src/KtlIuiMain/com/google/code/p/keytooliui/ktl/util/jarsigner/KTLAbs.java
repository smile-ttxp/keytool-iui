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
    "KTL" means "KeyTool"

    Known subclasses:
    . KTLKstSaveJks (create a keystore of type JKS)
    . KTLKprAbs (about keypair entry)
   
   
   
   MEMO: J2SDK-1.4.1 doc, keytool, ...:
   Private Key Size:
    DSA:
     min: 512
     max: 1024 (default)
     step: 64
     
    RSA:
     min: 512 ?
     max: 2048 ?
**/

/**
 int intNbChoice = intSizeMax - intSizeMin;
        intNbChoice /= intStep;
        intNbChoice ++;
        
        String[] strs = new String[intNbChoice];
        
        for (int i=0; i<intNbChoice; i++)
        {
            int intCur = intSizeMin + (i*intStep);
            strs[i] = new String(Integer.toString(intCur));
        }
        
        **/

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.PropertyResourceBundle;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.google.code.p.keytooliui.shared.Shared;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
// ----

import java.io.*;
import java.awt.*;

abstract public class KTLAbs extends Object
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.";
    
    // "Kpr" means "keypair"
    final static private int _f_s_intSizeMinKprDsa = 512;
    final static private int _f_s_intSizeMinKprRsa = 512;
    final static private int _f_s_intSizeMinKprEc = 192; // prime192v1

    final static private int _f_s_intSizeMaxKprDsa = 1024;
    final static private int _f_s_intSizeMaxKprRsa = 65536; // 2048;
    final static private int _f_s_intSizeMaxKprEc = 256; // prime256v1
    
    final static private int _f_s_intSizeStepKprDsa = 64;
    final static private int _f_s_intSizeStepKprRsa = 64;
    
    
    final static private int _f_s_intSizeMidKprEc = 239; // prime239v1
    
    //final static private String _F_STR_propKeyPolicyExtended = "_rcp_.policy.extended";
    
    // -------------------
    // final static public 
    
    final static public String f_s_strFormatFileShkDer = "DER";
    final static public String f_s_strFormatFileShkPem = "PEM"; // ascii, or "PEM"
    
    final static public String f_s_strFormatFileKprDer = "DER";
    final static public String f_s_strFormatFileKprPem = "PEM"; // ascii, or "PEM"
       
    final static public String f_s_strFormatFileCrtDer = "DER";
    final static public String f_s_strFormatFileCrtPkcs7 = "PKCS#7";
    final static public String f_s_strFormatFileCrtPem = "PEM"; // ascii, or "PEM"
    final static public String f_s_strFormatFileCsrPkcs10 = "PKCS#10";
    final static public String f_s_strFormatFileCrtPkcs12 = "PKCS#12"; // not yet in use !!!! DIFFERENT FROM "PKCS12" keystore type
    final static public String f_s_strFormatFileCrtOther = "Other"; // any other, not listed
    final static public String f_s_strFormatFileCrtCms = "P7C"; // Cryptographic Message Syntax
    
    final static public String f_s_strFormatFileXmlXml = "XML";
    
    final static public String f_s_strFormatFileSigDer = KTLAbs.f_s_strFormatFileCrtDer;
    final static public String f_s_strFormatFileSigPkcs7 = KTLAbs.f_s_strFormatFileCrtPkcs7;
    final static public String f_s_strFormatFileSigPem = KTLAbs.f_s_strFormatFileCrtPem;
    
    final static public String f_s_strFormatFileSCmsP7m = "P7M";
    final static public String f_s_strFormatFileSCmsP7s = "P7S";
    
    final static public String f_s_strProviderKstJks = 
        "SUN"; // !!!! IMPORTANT: all instanciations other than KeyPairGenerator should use this one
        
    final static public String f_s_strProviderKstJceks = 
        "SunJCE"; // !!!! IMPORTANT: all instanciations other than KeyPairGenerator should use this one
 
    final static public String f_s_strProviderKstBks = 
        "BC";  // !!!! IMPORTANT: only used to get instance of class KeyPairGenerator
 
    final static public String f_s_strProviderKstUber = 
        "BC";  // !!!! IMPORTANT: only used to get instance of class KeyPairGenerator
        
    final static public String f_s_strProviderKstPkcs12 = 
        "BC";  // !!!! IMPORTANT: only used to get instance of class KeyPairGenerator
 
    final static public String f_s_strProviderKstBC = 
        "BC";  // !!!! IMPORTANT: only used to get instance of class KeyPairGenerator
    
    final static public String f_s_strSecurityProviderSunRsaSign = 
        //"SunJSSE"; // !!!! IMPORTANT: only used to get instance of class KeyPairGenerator
        "SunRsaSign"; // test, may 17, 07
    
     final static public String f_s_strSecurityProviderBC = 
        "BC";
    
    
    /**
        provider(s) below just can read
    **/
    
    final static public String[] f_s_strsProviderKstPkcs12R =
    {
        KTLAbs.f_s_strSecurityProviderSunRsaSign,
        KTLAbs.f_s_strProviderKstBC
    };
    
     // "Kst" means "KeyStore", for use while creating keystore, "no support at this time for SunJSSE with PKCS12-write/store
    /**
        provider(s) below can write
    **/
    final static public String[] f_s_strsProviderKstPkcs12RW =
    {
        KTLAbs.f_s_strProviderKstBC
    };
    
    /**
        provider(s) below can write
    **/
    final static public String[] f_s_strsProviderKstBksRW =
    {
        KTLAbs.f_s_strProviderKstBC
    };
    
    /**
        provider(s) below can write
    **/
    final static public String[] f_s_strsProviderKstUberRW =
    {
        KTLAbs.f_s_strProviderKstBC
    };
    
    
    // "Kpg" means "keypair Generator"
    final static public String[] f_s_strsProviderKpgDsa =
    {
        KTLAbs.f_s_strProviderKstJks,
        KTLAbs.f_s_strProviderKstBC
    };
    
    final static public String[] f_s_strsProviderKpgEc =
    {
        KTLAbs.f_s_strProviderKstBC
    };
    
    final static public String[] f_s_strsProviderKpgRsa =
    {
        KTLAbs.f_s_strProviderKstBC,
        KTLAbs.f_s_strSecurityProviderSunRsaSign
    };
    
    // "Sig" for "Signature"
    final static public String[] f_s_strsProviderSigRsa =
    {
        KTLAbs.f_s_strProviderKstBC
    };
    
    
    final static public String f_s_strTypeKeypairDsa = "DSA";
    final static public String f_s_strTypeKeypairRsa = "RSA";
    
    final static public String f_s_strTypeKeypairEc = "EC";
    
    final static public String f_s_strTypeCrtDsa = KTLAbs.f_s_strTypeKeypairDsa;
    final static public String f_s_strTypeCrtRsa = KTLAbs.f_s_strTypeKeypairRsa;
    
    
    final static public String[] f_s_strsFormatFileCertReqBc =
    {
        KTLAbs.f_s_strFormatFileCsrPkcs10 // same as the one mentionned in j2sdk-1.4.0's doc, keytool
    };
    
    final static public String[] f_s_strsFormatFileCertOutBc =
    {
        KTLAbs.f_s_strFormatFileCrtDer, // use this one, eg to import to keystore
        KTLAbs.f_s_strFormatFileCrtPem, // ascii, also named PEM, same as the one mentionned in j2sdk-1.5.0's doc, keytool, option -rfc
        KTLAbs.f_s_strFormatFileCrtPkcs7 // binary
    };
    
    final static public String[] f_s_strsFormatFileCertImportBc =
    {
        KTLAbs.f_s_strFormatFileCrtPkcs7,
        KTLAbs.f_s_strFormatFileCrtOther
    };
    
    
    /*
        provider: BC (BouncyCastle)
        
       list of supported algos for generating CSR
       different from list of supported algos for generating keypair!
    */ 
    final static public String[] f_s_strsCertSigAlgoRsaBc2Csr =
    {
        UtilCrtX509.f_s_strDigestAlgoMD5 + "withRSA",  // mentionned in j2sdk-1.4.0's doc, Signature.class, default in (RSA) jarsigner tool 
        UtilCrtX509.f_s_strDigestAlgoMD2 + "withRSA",  // mentionned in j2sdk-1.4.0's doc, Signature.class
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withRSA", // // mentionned in j2sdk-1.4.0's doc, Signature.class
        
        UtilCrtX509.f_s_strDigestAlgoMD5 + "withRSA" + "Encryption",  // suffix, new in BC1.4.0-jdk1.5.0
        UtilCrtX509.f_s_strDigestAlgoMD2 + "withRSA" + "Encryption",  // suffix, new in BC1.4.0-jdk1.5.0
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withRSA" + "Encryption"//,  // suffix, new in BC1.4.0-jdk1.5.0
        
        // NOT SUPPORTED! BC API
        //"RIPEMD160withRSA"
        
        
       
    };
    
    
    
 
    
    /*
        !!!! TO BE CHECKED !!!!
        provider: BC (BouncyCastle)
        
       list of supported algos for generating certificate
       different from list of supported algos for generating keypair!
    */ 
    final static public String[] f_s_strsCertSigAlgoKprBc2Crt =
    {
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withDSA",
        
        UtilCrtX509.f_s_strDigestAlgoMD5 + "withRSA",  // mentionned in j2sdk-1.4.0's doc, Signature.class, default in (RSA) jarsigner tool 
        UtilCrtX509.f_s_strDigestAlgoMD2 + "withRSA",  // mentionned in j2sdk-1.4.0's doc, Signature.class
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withRSA", // // mentionned in j2sdk-1.4.0's doc, Signature.class
        
        UtilCrtX509.f_s_strDigestAlgoMD5 + "withRSA" + "Encryption",  // suffix, new in BC1.4.0-jdk1.5.0
        UtilCrtX509.f_s_strDigestAlgoMD2 + "withRSA" + "Encryption",  // suffix, new in BC1.4.0-jdk1.5.0
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withRSA" + "Encryption"  // suffix, new in BC1.4.0-jdk1.5.0
        
        // NOT SUPPORTED! BC API
        //"RIPEMD160withRSA"
    };
    
    final static public String[] f_s_strsCertSigAlgoTcrBc2Crt = KTLAbs.f_s_strsCertSigAlgoKprBc2Crt;
    
    /*
        provider: either Sun or BC (BouncyCastle)
        "Any" means "Any provider"
    */ 
    final static public String[] f_s_strsCertSigAlgoDsaAny =
    {
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withDSA"//,  // mentionned in j2sdk-1.4.0's doc, Signature.class, default in (DSA) jarsigner tool 
        
        /* in comments!
         *Exception in thread "AWT-EventQueue-0" java.lang.IllegalArgumentException: Unknown signature type requested
	at org.bouncycastle.x509.X509V3CertificateGenerator.setSignatureAlgorithm(X509V3CertificateGenerator.java:167)
         */
        // UtilCrtX509.f_s_strDigestAlgoNONE + "withDSA"
                
                
        /*UtilCrtX509.f_s_strDigestAlgoSHA1 + "withECDSA"*/ // buggy with BC!, getting a ClassCastException
    };
    
    /*
     *  "PK" means "Private Key" (keypair)
        provider: BC (KeyGenerator instances)
    */ 
    final static public String[] f_s_strsSigAlgoPKBC =
    {
        "DSA",
        "RSA",
        "EC"
    };
    
    /*
     *  "SK" means "Secret Key" (shared key)
        provider: JCEKS (KeyGenerator instances)
    */ 
    final static public String[] f_s_strsSigAlgoSKJceks =
    {
        // coz no current xxxKeySpec, with SunRsaSign (PKCS11 should be OK)
        "AES",
        "ARCFOUR",
        "Blowfish",
        "DES",
        "DESede",
        "HmacMD5",
        "HmacSHA1",
        "HmacSHA256",
        "HmacSHA384",
        "HmacSHA512",
        "RC2"
    };
    
    
    // !!! BC PROVIDERS for the 2 others in the list
    final static public String[] f_s_strsCipherRsaAlgoJceks =
    {
        "RSA/ECB/PKCS1Padding",
        "RSA/NONE/PKCS1Padding",
        "RSA/NONE/OAEPWithSHA1AndMGF1Padding"
    };
    
    /*
        provider: BC (BouncyCastle)
    */ 
    final static public String[] f_s_strsCertSigAlgoRsaBc =
    {
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withRSA",// // mentionned in j2sdk-1.4.0's doc, Signature.class
        UtilCrtX509.f_s_strDigestAlgoMD2 + "withRSA",  // mentionned in j2sdk-1.4.0's doc, Signature.class
        UtilCrtX509.f_s_strDigestAlgoMD5 + "withRSA",  // mentionned in j2sdk-1.4.0's doc, Signature.class, default in (RSA) jarsigner tool 
        
        
        
        // added may 17, 07
        UtilCrtX509.f_s_strDigestAlgoSHA256 + "withRSA",
        UtilCrtX509.f_s_strDigestAlgoSHA384 + "withRSA",
        UtilCrtX509.f_s_strDigestAlgoSHA512 + "withRSA",
        
        // IN COMMENTS, feb 21, 03 - Not supported by SunJSSE. Also BC allows to generate keypair BUT fails to export CSR
        //"RIPEMD160withRSA" 
        
        "RIPEMD128withRSA",
        "RIPEMD160withRSA",
        "RIPEMD256withRSA"
    };
    
    /*
        provider: BC (BouncyCastle)
    */ 
    final static public String[] f_s_strsCertSigAlgoEcBc =
    {
        UtilCrtX509.f_s_strDigestAlgoSHA1 + "withECDSA",
        "SHA224" + "withECDSA",
        UtilCrtX509.f_s_strDigestAlgoSHA256 + "withECDSA",
        UtilCrtX509.f_s_strDigestAlgoSHA384 + "withECDSA",
        UtilCrtX509.f_s_strDigestAlgoSHA512 + "withECDSA"//,
        
        //"RIPEMD160withECDSA"
                /*
                 *  java.lang.IllegalArgumentException: Unknown signature type requested: RIPEMD160withECDSA
                    at org.bouncycastle.x509.X509V3CertificateGenerator.setSignatureAlgorithm(Unknown Source)
                 */
    };
    
    final static public String f_s_strCertSigAlgoUnknown = "Unknown!";
    
    
    final static public Integer[] f_s_itgsCertVersion =
    {
        new Integer(1),
        new Integer(3)
    };
    
    final static public int f_s_intCertValidityMin = 180;      // approx half year
    final static public int f_s_intCertValidityMax = 14600;     // approx 40 years
    // changing default coz Google wants Android's related to be signed for at least 25 years
    final static public int f_s_intCertValidityDefault = 9125; // approx 25 years 
    
    // --------------
    // static private
    
        /**
        * Encode bytes array to BASE64 string
        * @param bytes
        * @return Encoded string
        */
        private static String encodeBASE64(byte[] bytes)
        {

              sun.misc.BASE64Encoder b64 = new sun.misc.BASE64Encoder();
              return b64.encode(bytes);

        }

        /**
        * Decode BASE64 encoded string to bytes array
        * @param text The string
        * @return Bytes array
        * @throws IOException
        */
        private static byte[] decodeBASE64(String text) throws IOException
        {

              sun.misc.BASE64Decoder b64 = new sun.misc.BASE64Decoder();
              return b64.decodeBuffer(text);

        }

    // ----------------
    // static protected
    
    /*
     *MEMO: (intKeySize/8) - 11 = intDataSize
     *where "11" is padding in bytes
     *ie keySize = 2048 ==> limitation for file encrypting = 245
     */
    static protected boolean _s_can_encryptRsa_(
            Frame frmOwner, X509Certificate crtX509, int intSizeFileInput)
    {
        int intSizeKey = UtilCrtX509.s_getSizeKey(crtX509);
        
        int intSizeDataMax = intSizeKey;
        intSizeDataMax /= 8;
        intSizeDataMax -= 11;
        
        if (intSizeDataMax < intSizeFileInput)
        {
            String strBody = "Data must not be longer than " + intSizeDataMax + " bytes"; 
            strBody += "\n" + "Data size: " + intSizeFileInput + " bytes"; 
            strBody += "\n" + "Key size: " + intSizeKey + " bytes"; 
            
            strBody += "\n\n" + "Workaround: increase key size"; 
            strBody += "\n\n" + "MEMO: Data size should not exceed the following in bytes";
            strBody += "\n" + "  ([key-size] / 8) - 11";          
            
            
            OPAbstract.s_showDialogError(frmOwner, strBody);
            
            return false;
        }
        
        return true;
    }
    
  
    
    static protected void _s_encryptRsa_(
            PublicKey pky, 
            InputStream ism, 
            OutputStream osm,
            int intSizeFileInput,
            String strInstanceCipherAlgo
            )
        throws 
            NoSuchAlgorithmException, 
            InvalidKeyException,
            NoSuchPaddingException, 
            IOException, IllegalBlockSizeException, BadPaddingException, java.security.NoSuchProviderException
    {
        // Create and initialize the encryption engine
        Cipher cip = Cipher.getInstance(strInstanceCipherAlgo, "BC");
        cip.init(Cipher.ENCRYPT_MODE, pky);

        // Create a special output stream to do the work for us
        //CipherOutputStream cos = new CipherOutputStream(osm, cip);

        // Read from the input and write to the encrypting output stream
        byte[] bytsBuffer = new byte[intSizeFileInput];
        int intBytesRead;
        
        while((intBytesRead = ism.read(bytsBuffer)) != -1) 
        {
            //cos.write(bytsBuffer, 0, intBytesRead);
        }
        
        byte[] bytsTarget = cip.doFinal(bytsBuffer);
        
        // ---
        
        //String strTarget = encodeBASE64(bytsTarget);
        //osm.write(strTarget);        
        //osm.write(strTarget.getBytes());
        
        osm.write(bytsTarget);
        
        
        osm.close();
        
        
        // ----
        // test
    }
    
    
    static protected void _s_decryptRsa_(
            PrivateKey pky, 
            InputStream ism, 
            OutputStream osm,
            int intSizeFileInput,
            String strInstanceCipherAlgo
            )
        throws 
            NoSuchAlgorithmException, 
            InvalidKeyException,
            NoSuchPaddingException, 
            IOException, IllegalBlockSizeException, BadPaddingException, java.security.NoSuchProviderException
    {
        // Create and initialize the encryption engine
        Cipher cip = Cipher.getInstance(strInstanceCipherAlgo, "BC");
        cip.init(Cipher.DECRYPT_MODE, pky);


        // Read from the input and write to the encrypting output stream
        byte[] bytsBuffer = new byte[intSizeFileInput];
        int intBytesRead;
        
        while((intBytesRead = ism.read(bytsBuffer)) != -1) 
        {
            //cos.write(bytsBuffer, 0, intBytesRead);
        }
        
        byte[] bytsTarget = cip.doFinal(bytsBuffer);
        
        osm.write(bytsTarget);
        osm.close();
    }
    
    
    
    
    // -------------
    // STATIC PUBLIC
    
    
    
    /* ATTN: be carefull, should be inside range!
     *DSA: should point to 1024
     * ((1024-min) / step)
     *RSA: should point to 2048
     * ((2048-min) / step)
     */
    static public Integer s_getItgDefaultKprDsa()
    {
        int intVal = 1024;
        intVal -= KTLAbs._f_s_intSizeMinKprDsa;
        intVal /= KTLAbs._f_s_intSizeStepKprDsa;
        return new Integer(intVal); 
    }
    
    static public Integer s_getItgDefaultKprRsa()
    {
        int intVal = 2048;
        intVal -= KTLAbs._f_s_intSizeMinKprRsa;
        intVal /= KTLAbs._f_s_intSizeStepKprRsa;
        return new Integer(intVal); 
    }
    
    static public Integer s_getItgDefaultKprEc()
    {
        Integer[] itgs = KTLAbs.s_getItgsListSizeKprEc();
        return new Integer(itgs.length-1); // max value 
    }
    
    static public Integer[] s_getItgsListSizeKprDsa()
    {
        return _s_getItgsListSizeKpr(
            KTLAbs._f_s_intSizeMinKprDsa, 
            KTLAbs._f_s_intSizeMaxKprDsa, 
            KTLAbs._f_s_intSizeStepKprDsa);
    }
    
    static public Integer[] s_getItgsListSizeKprRsa()
    {
        return _s_getItgsListSizeKpr(
            KTLAbs._f_s_intSizeMinKprRsa, 
            KTLAbs._f_s_intSizeMaxKprRsa, 
            KTLAbs._f_s_intSizeStepKprRsa);
    }
    
    static public Integer[] s_getItgsListSizeKprEc()
    {
        Integer[] itgs = new Integer[3];
        
        itgs[0] = new Integer(KTLAbs._f_s_intSizeMinKprEc);
        itgs[1] = new Integer(KTLAbs._f_s_intSizeMidKprEc);
        itgs[2] = new Integer(KTLAbs._f_s_intSizeMaxKprEc);
        
        return itgs;
    }
    
    /*static public boolean s_isPolicyExtended()
    {
        String strMethod = KTLAbs._f_s_strClass + "s_isPolicyExtended()";
       
        
        if (KTLAbs._s_booPolicyExtended != null)
        {   
            return KTLAbs._s_booPolicyExtended.booleanValue();
        }
        
        
        // assign Boolean
        
        // 1/2 first check for property assigned through JNLP's desc (WebStart)
        // org as an arg in standalone appli:
        // -Dorg.ragingcat.kst.util.jarsigner.KTLAbs.policy.extended=true
        String strVal = null;
        
        strVal = System.getProperty("_rcp_.policy.extended");
        
        if (strVal != null)
        {
            if (strVal.toLowerCase().compareTo("false") == 0)
            {
                KTLAbs._s_booPolicyExtended = new Boolean(false);
                MySystem.s_printOutTrace(strMethod, "got val, " + KTLAbs._F_STR_propKeyPolicyExtended + "= " + strVal);
                return KTLAbs._s_booPolicyExtended.booleanValue();
            }
                
            else if (strVal.toLowerCase().compareTo("true") == 0)
            {
                KTLAbs._s_booPolicyExtended = new Boolean(true);
                MySystem.s_printOutTrace(strMethod, "got val, " + KTLAbs._F_STR_propKeyPolicyExtended + "= " + strVal);
                return KTLAbs._s_booPolicyExtended.booleanValue();
            }
            
            else
                MySystem.s_printOutTrace(strMethod, "uncaught val, ignoring, " + KTLAbs._F_STR_propKeyPolicyExtended + "= " + strVal);
        }
        
        

        KTLAbs._s_booPolicyExtended = new Boolean(false);
        return KTLAbs._s_booPolicyExtended.booleanValue();
    }*/
    
    
    
    // -------------
    // STATIC PRIVATE
    
    //static private Boolean _s_booPolicyExtended = null;
    
    static private Integer[] _s_getItgsListSizeKpr(
        int intSizeMin,
        int intSizeMax,
        int intStep)
    {
        int intNbChoice = intSizeMax - intSizeMin;
        intNbChoice /= intStep;
        intNbChoice ++;
        
        Integer[] itgs = new Integer[intNbChoice];
        
        for (int i=0; i<intNbChoice; i++)
        {
            int intCur = intSizeMin + (i*intStep);
            itgs[i] = new Integer(intCur);
        }
        
        return itgs;
    }
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean doJob();
    
    // ---------
    // PROTECTED
    
    protected Frame _frmOwner_ = null;
    protected String _strPathAbsKst_ = null;
    protected char[] _chrsPasswdKst_ = null; 
    
    protected String _strProviderKst_ = null; // eg: "SUN" for "JKS", "BC" for "PKCS12"
    
    protected void _setEnabledCursorWait_(boolean bln)
    {
        if (this._frmOwner_ == null)
            return;
        
        if (bln)
            this._frmOwner_.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        else
            this._frmOwner_.setCursor(Cursor.getDefaultCursor()); 
    }
    
    protected KTLAbs(
        Frame frmOwner, 
        String strPathAbsKst, // existing keystore of type [JKS-JCEKS-PKCS12]
        char[] chrsPasswdKst,
        
        String strProviderKst)
    {
        this._frmOwner_ = frmOwner;

        // input
        this._strPathAbsKst_ = strPathAbsKst;
        this._chrsPasswdKst_ = chrsPasswdKst;
        
        this._strProviderKst_ = strProviderKst;
    }
    
    /**
        if any error in code exiting,
        else if any other errors, show error-warning dialogs, then return false
        else return true
    **/
    protected boolean _saveKeyStore_(
        KeyStore kst, 
        File fleSave, 
        char[] chrsPasswordKst)
    {
        String strMethod = "_saveKeyStore_(...)";
        
        if (kst==null || fleSave==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
 
        FileOutputStream fos = null;
        
        try
        {
            fos = new FileOutputStream(fleSave);
        }
        
        catch(FileNotFoundException excFileNotFound) // file is directory!
        {
            excFileNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excFileNotFound caught");
            
            String strBody = excFileNotFound.getMessage();
            strBody += "\n\n" + "Got FileNotFound exception,\nsounds like you selected a directory.";
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            // error dlg
            return false;
        }
        
        // february 8, 2008
        if (chrsPasswordKst == null) // meaning "no password"
            chrsPasswordKst = "".toCharArray();

        // --
        
        try
        {
            kst.store(fos, chrsPasswordKst);
        }
        
        catch(KeyStoreException excKeyStore) // if the keystore has not been initialized (loaded)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            String strBody = "Got keystore exception.";
            strBody += "\n" + excKeyStore.getMessage();
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
        
        catch(IOException excIO) // if there was an I/O problem with data 
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            String strBody = "Got IO exception.";
            strBody += "\n" + excIO.getMessage();
            
            // error could be coz: using KeyTool IUI Plus WITHOUT JCE Unlimited Strength Jurisdiction Policy Files installed
            //strBody = KTLAbs.s_getErrorBodyCheckPolicy(strBody);
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm) // if the appropriate data integrity algorithm could not be found 
        {
            excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excNoSuchAlgorithm caught");
            String strBody = "Got NoSuchAlgorithm exception.";
            strBody += "\n" + excNoSuchAlgorithm.getMessage();
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
        
        catch(CertificateException excCertificate) // !! empty keystore !! if any of the certificates included in the keystore data could not be stored
        {
            excCertificate.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excCertificate caught");
            String strBody = "Got Certificate exception.";
            strBody += "\n" + excCertificate.getMessage();
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
        
        catch(ExceptionInInitializerError errExceptionInInitializer) // eg create PKCS12 keystore, with restricted policies
        {
            errExceptionInInitializer.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "errExceptionInInitializer caught");
            String strBody = "Got error  exception in initializer.";
            strBody += "\n" + errExceptionInInitializer.getMessage();
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
        
        catch(NoClassDefFoundError errNoClassDefFound) // eg create UBER keystore, with restricted policies
        {
            errNoClassDefFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "errNoClassDefFound caught");
            String strBody = "Got NoClassDefFoundError exception.";
            strBody += "\n" + errNoClassDefFound.getMessage();
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
        
        catch(Exception exc) // eg create UBER keystore, with restricted policies
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            
            String strBody = "Got exception.";
            strBody += "\n" + exc.getMessage();
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
       
        // ----
        
        
        try
        {
            fos.close();
            fos = null;
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            String strBody = "Got IO exception.";
            strBody += "\n" + excIO.getMessage();
            
            OPAbstract.s_showDialogError(
                this._frmOwner_, strBody);
                
            return false;
        }
        
        // ----
        return true;
        
    }
}