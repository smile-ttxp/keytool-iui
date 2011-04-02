
package com.google.code.p.keytooliui.shared.util.jarsigner;

/**
    "UtilCrt" ==> "Utility, Certificate"
    
    known subclasses:
    . UtilCrtX509Pkcs7
**/



import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.spec.ECPublicKeySpec;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

// ----
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.Principal;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.security.KeyFactory;
// ----
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateException;
import java.security.cert.CertificateEncodingException;
// ----


import java.io.*;
import java.util.*;
import java.awt.*;
import java.math.BigInteger;

public class UtilCrtX509 extends UtilCrt
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strType = "X.509";
    final static public String f_s_strDigestAlgoSHA1 = "SHA1";
    final static public String f_s_strDigestAlgoMD2 = "MD2";
    final static public String f_s_strDigestAlgoMD5 = "MD5";
    
    // added with KTL/J2SE6.0, KTL#1.3
    // added may 17, 07
    final static public String f_s_strDigestAlgoSHA256 = "SHA256"; // RSA sig algo
    final static public String f_s_strDigestAlgoSHA384 = "SHA384"; // RSA sig algo
    final static public String f_s_strDigestAlgoSHA512 = "SHA512"; // RSA sig algo
    final static public String f_s_strDigestAlgoNONE = "NONE";     // DSA sig algo
     
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.util.jarsigner.UtilCrtX509";
    final static private String _f_s_strProvider = "SUN";
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strDumpMoreUnknown = null; 
    
    static private String _s_strDumpMoreCertsChainNb = null;
    static private String _s_strDumpMoreCertsChainBegin = null; 
    static private String _s_strDumpMoreCertsChainEnd = null; 
    static private String _s_strDumpMoreOwner = null; 
    static private String _s_strDumpMoreIssuer = null; 
    static private String _s_strDumpMoreVersion = null; 
    static private String _s_strDumpMoreSerialNumber = null; 
    static private String _s_strDumpMoreDateStart = null; 
    static private String _s_strDumpMoreDateEnd = null; 
    static private String _s_strDumpMoreSignatureAlgorithm = null; 
    static private String _s_strDumpMoreFingerprints = null; 
    
    static private String _s_strDumpMoreExtKeyUsage = "Extended Key Usage";
    
    static private String _s_strDumpMoreKeyUsage = "Key Usage";
    
    static private String[] _s_strsKeyUsage =
    {
        "digitalSignature",
        "nonRepudation",
        "keyEncipherment",
        "dataEncipherment",
        "keyAgreement",
        "keyCertSign",
        "cRLSign",
        "encipherOnly",
        "decipherOnly"
    };
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = _f_s_strClass + "." + "static";
        
        try
        {
            String strBundleFileShort =
                com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
                ".UtilCrtX509" // class name
                ;

            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());

            UtilCrtX509._s_strDumpMoreUnknown = rbeResources.getString("dumpMoreUnknown");
            
            UtilCrtX509._s_strDumpMoreCertsChainNb = rbeResources.getString("dumpMoreCertsChainNb");
            UtilCrtX509._s_strDumpMoreCertsChainBegin = rbeResources.getString("dumpMoreCertsChainBegin");
            UtilCrtX509._s_strDumpMoreCertsChainEnd = rbeResources.getString("dumpMoreCertsChainEnd");
            UtilCrtX509._s_strDumpMoreOwner = rbeResources.getString("dumpMoreOwner");
            UtilCrtX509._s_strDumpMoreIssuer = rbeResources.getString("dumpMoreIssuer");
            UtilCrtX509._s_strDumpMoreVersion = rbeResources.getString("dumpMoreVersion");
            UtilCrtX509._s_strDumpMoreSerialNumber = rbeResources.getString("dumpMoreSerialNumber");
            UtilCrtX509._s_strDumpMoreDateStart = rbeResources.getString("dumpMoreDateStart");
            UtilCrtX509._s_strDumpMoreDateEnd = rbeResources.getString("dumpMoreDateEnd");
            UtilCrtX509._s_strDumpMoreSignatureAlgorithm = rbeResources.getString("dumpMoreSignatureAlgorithm"); 
            UtilCrtX509._s_strDumpMoreFingerprints = rbeResources.getString("dumpMoreFingerprints");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    } 
    
    // -------------
    // STATIC PUBLIC
    
    // if any error, returns -1
    // this code comes from ?don't remember
    public static int s_getSizeKey(X509Certificate crt)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_getSizeKey(crt)";
        
        
        if (crt == null)
        {
            MySystem.s_printOutExit(strMethod, "nil crt");
        }
        
        try
        {
            PublicKey kpu = crt.getPublicKey();

            // Get the public key algorithm
            String strAlgoKpu = kpu.getAlgorithm();
            // TODO: for some certs, eg. some in OpenSSL's certs/ dir in the
            // source tarball, this may return an OID, which will confuse us.

            /* If the algorithm is RSA then use a KeyFactory to create
               an RSA public key spec and get the keysize from the
               modulus length in bits */
            if (strAlgoKpu.toLowerCase().compareTo("rsa") == 0)
            {
                KeyFactory kfy = KeyFactory.getInstance(strAlgoKpu);
                
                RSAPublicKeySpec keySpec = (RSAPublicKeySpec)
                    kfy.getKeySpec(kpu, RSAPublicKeySpec.class);
                    
                BigInteger bigModulus = keySpec.getModulus();
                return bigModulus.toString(2).length();
            }
            
            /* If the algorithm is RSA then use a KeyFactory to create
               a DSA public key spec and get the keysize from the
               prime length in bits */
            else if (strAlgoKpu.toLowerCase().compareTo("dsa") == 0)
            {
                KeyFactory kfy = KeyFactory.getInstance(strAlgoKpu);
                
                DSAPublicKeySpec keySpec = (DSAPublicKeySpec)
                    kfy.getKeySpec(kpu, DSAPublicKeySpec.class);
                
                BigInteger bigPrime = keySpec.getP();
                return bigPrime.toString(2).length();
            }
            
            else if (strAlgoKpu.toLowerCase().compareTo("ec") == 0)
            {
                KeyFactory kfy = KeyFactory.getInstance(strAlgoKpu);
                
                ECPublicKeySpec keySpec = (ECPublicKeySpec)
                    kfy.getKeySpec(kpu, ECPublicKeySpec.class);
                
                java.security.spec.ECPoint pnt = keySpec.getW();

                BigInteger bigX = pnt.getAffineX();
                //BigInteger bigY = pnt.getAffineY();
                
                int intX = bigX.toString(2).length();
                //int intY = bigY.toString(2).length();
                
                return intX;
            }
            
            // Otherwise cannot calculate keysize
            else
            {
                MySystem.s_printOutWarning(strMethod, "cannot get key size, strAlgoKpu=" + strAlgoKpu);
                return -1;
            }
        }
        
        catch (Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "got exc");
            return -1;
        }
    }
    
    
    static public void s_show(
        Frame frmParent, 
        X509Certificate crt)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_show(...)";
        
        
        if (crt == null)
        {
            MySystem.s_printOutExit(strMethod, "nil crt");
        }
        
        String strContents = UtilCrtX509.s_dumpIt(crt);
        
        if (strContents == null)
        {
            MySystem.s_printOutError(strMethod, "nil strContents");
            
            String strBody = "Failed to get certificate.";
 
            OPAbstract.s_showDialogError(frmParent, strBody);
            
            // --
            return;
        }
        
        // launch dialog 
        
        
        
        com.google.code.p.keytooliui.shared.swing.dialog.DViewString vsg = new
            com.google.code.p.keytooliui.shared.swing.dialog.DViewString(
            frmParent,
            //System.getProperty("_appli.title") + " - " + "view" + " " + "X.509 certificate",
            strContents
            );
                    
        if (! vsg.init())
            MySystem.s_printOutExit(strMethod, "failed");
                
        vsg.setVisible(true);
    }
    
    static public void s_showChain(
        Frame frmParent, 
        X509Certificate[] crts)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_showChain(...)";
        
        
        if (crts == null)
        {
            MySystem.s_printOutExit(strMethod, "nil crts");
        }
        
        String strContents = UtilCrtX509.s_dumpIt(crts);
        
        if (strContents == null)
        {
            MySystem.s_printOutError(strMethod, "nil strContents");
            
            String strBody = "Failed to get certificates chain.";
 
            OPAbstract.s_showDialogError(frmParent, strBody);
            
            // --
            return;
        }
        
        // launch dialog 
        
        
        
        com.google.code.p.keytooliui.shared.swing.dialog.DViewString vsg = new
            com.google.code.p.keytooliui.shared.swing.dialog.DViewString(
            frmParent,
            //System.getProperty("_appli.title") + " - " + "view" + " " + "X.509 certificates chain",
            strContents
            );
                    
        if (! vsg.init())
            MySystem.s_printOutExit(strMethod, "failed");
                
        vsg.setVisible(true);
    }
    
    // if any error, return nil, then calling method should exit immediately!
    static public X509Certificate s_getX509Certificate(
        KeyStore kstOpen,
        String strAliasKpr
        )
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_getX509Certificate(...)";
        
        if (kstOpen==null || strAliasKpr==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        
        }
        
        // MEMO: tests already done while selecting alias and password, if any error, exiting!
        
        Certificate crt = null;
        
        try
        {
            crt = kstOpen.getCertificate(strAliasKpr);
        }
        
        catch(KeyStoreException excKeyStore) // KeyStore has not been initialized (loaded). 
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(strMethod, "excKeyStore caught");
            return null;
        }
        
        
        
        if (! (crt instanceof X509Certificate))
        {
            MySystem.s_printOutError(strMethod, "! (crt instanceof X509Certificate)");
            return null;
        }

        return (X509Certificate) crt;
    }  
    
    
    // if any error, return nil, then calling method should exit immediately!
    static public X509Certificate[] s_getX509CertificateChain(
        KeyStore kstOpen,
        String strAliasKpr,
        boolean blnOrderChain // true for certReq & certImport, false for jarSign
        )
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_getX509CertificateChain(...)";
        
        if (kstOpen==null || strAliasKpr==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        
        }
        
        // MEMO: tests already done while selecting alias and password, if any error, exiting!
        
        Certificate[] crts = null;
        
        try
        {
            crts = kstOpen.getCertificateChain(strAliasKpr);
        }
        
        catch(KeyStoreException excKeyStore) // KeyStore has not been initialized (loaded). 
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(strMethod, "excKeyStore caught");
            return null;
        }
        
        if (crts.length < 1)
        {
            MySystem.s_printOutError(strMethod, "crts.length < 1");
            return null;
        }

        X509Certificate[] ctrsX509Unordered = new X509Certificate[crts.length];
        
        // memo: all certs should be of type X509
        
        for (int i=0; i<crts.length; i++)
        {
            if (! (crts[i] instanceof X509Certificate))
                MySystem.s_printOutExit(strMethod, "! (crts[i] instanceof X509Certificate)");
                
            ctrsX509Unordered[i] = (X509Certificate) crts[i];
        }
        
        if (! blnOrderChain)
            return ctrsX509Unordered;
        
        // ----
        // reordering
        
        X509Certificate[] crtsX509Ordered = 
            UtilCrtX509.s_orderChain(ctrsX509Unordered);
        
        if (crtsX509Ordered == null)
        {
            MySystem.s_printOutError(strMethod, "crtsX509Ordered == null");
            return null;
        }
        
        if (crtsX509Ordered.length < 1)
        {
            MySystem.s_printOutError(strMethod, "crtsX509Ordered.length < 1");
            return null;
        }
        
        // ----
        return crtsX509Ordered;
    }  
    
    
    // ----------
    // 2) serial number
    // memo: hexadecimal value
    static public String s_dumpSerialNumber(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_dumpSerialNumber(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil arg");
            
        
        return cer.getSerialNumber().toString(16).toUpperCase();
        
        /** THE CODE BELOW WORKS TOO !!!!!!!
        java.math.BigInteger bir = x5c.getSerialNumber();
        
        String str = bir.toString();
        Integer itg = null;
        
        try
        {
            itg = new Integer(str);
        }
        
        catch(NumberFormatException excNumberFormat)
        {
            excNumberFormat.printStackTrace();
            MySystem.s_printOutWarning(strMethod, "excNumberFormat caught, returning string representation");
            return str;
        }
        
         
        return Integer.toHexString(itg.intValue()).toUpperCase();
        **/
    }
    
    static public String s_dumpIt(X509Certificate[] crts)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_dumpIt(crts)";
        
        if (crts == null)
        {
            MySystem.s_printOutError(strMethod, "nil crts");
            return null;
        }
        
        if (crts.length == 0)
        {
            MySystem.s_printOutError(strMethod, "crts.length == 0");
            return null;
        }
        
        StringBuffer sbr = new StringBuffer();
        
        sbr.append(UtilCrtX509._s_strDumpMoreCertsChainNb);  // ("nb certificates");
        
        sbr.append("=" + Integer.toString(crts.length));
        sbr.append("\n");
        
        for (int i=0; i<crts.length; i++)
        {
            X509Certificate cerCur = crts[i];
            
            sbr.append("\n");
            sbr.append("---- ");
           
            // --
            sbr.append(UtilCrtX509._s_strDumpMoreCertsChainBegin);  
            
            sbr.append(Integer.toString(i+1) + "/" + Integer.toString(crts.length) + "----");
           
            sbr.append("\n");
            
            
            // ----
            
            String strCurCrt = UtilCrtX509.s_dumpIt(cerCur);
            
            if (strCurCrt == null)
            {
                MySystem.s_printOutError(strMethod, "nil strCurCrt");
                return null;
            }
            
            sbr.append(strCurCrt);
            
            // ----
            
            
             
            
            // ---
            // END
            sbr.append("---- ");
            
            sbr.append(UtilCrtX509._s_strDumpMoreCertsChainEnd);
            
            sbr.append(Integer.toString(i+1) + "/" + Integer.toString(crts.length) + " ----");
            sbr.append("\n");
        }
        
        // ending
        return sbr.toString();
    }
    
    static public String s_dumpIt(X509Certificate crt)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_dumpIt(crt)";
        
        if (crt == null)
        {
            MySystem.s_printOutError(strMethod, "nil crt");
            return null;
        }
        
        
        StringBuffer sbr = new StringBuffer();
        
 
            
        // ----------
        // 1) owner
        sbr.append(UtilCrtX509._s_strDumpMoreOwner);                  // ("Owner=");
            
        sbr.append("=" + "\n" + "  ");
        sbr.append(UtilCrtX509._s_dumpSubject(crt));
            
        sbr.append("\n");
            
        // ----------
        // 2) issuer
        sbr.append(UtilCrtX509._s_strDumpMoreIssuer); 
            
        sbr.append("=" + "\n" + "  ");
        sbr.append(UtilCrtX509._s_dumpIssuer(crt));
        sbr.append("\n");
            
        // ----------
        // 3) version
        sbr.append(UtilCrtX509._s_strDumpMoreVersion);
            
        sbr.append("=" + UtilCrtX509._s_dumpVersion(crt));
        sbr.append("\n");
            
        // ----------
        // 4) serial number
        sbr.append(UtilCrtX509._s_strDumpMoreSerialNumber);
            
        sbr.append("=" + s_dumpSerialNumber(crt));
        sbr.append("\n");
            
        // ----------
        // 5) start date
        sbr.append(UtilCrtX509._s_strDumpMoreDateStart);
            
        sbr.append("=" + UtilCrtX509._s_dumpDateStart(crt));
        sbr.append("\n");
            
        // ----------
        // 6) end date
        sbr.append(UtilCrtX509._s_strDumpMoreDateEnd);
            
        sbr.append("=" + UtilCrtX509._s_dumpDateEnd(crt));
        sbr.append("\n");
            
            
            
        // ----------
        // 8) algorithm signature
        sbr.append(UtilCrtX509._s_strDumpMoreSignatureAlgorithm);
            
        sbr.append("=" + UtilCrtX509._s_dumpSignatureAlgo(crt));
        sbr.append("\n");
            
            
        // ----------
        // 9) fingerprints
        sbr.append(UtilCrtX509._s_strDumpMoreFingerprints);
            
        sbr.append("=" + UtilCrtX509._s_dumpCertFingerprints(crt));
        sbr.append("\n");
        
        
        // ----
        // 10) keyUsage
        
        sbr.append(UtilCrtX509._s_strDumpMoreKeyUsage);
            
        sbr.append("=" + UtilCrtX509._s_dumpKeyUsage(crt));
        sbr.append("\n");
        
        // ----
        // 11) extKeyUsage
        
        sbr.append(UtilCrtX509._s_strDumpMoreExtKeyUsage);
            
        sbr.append("=" + UtilCrtX509._s_dumpExtKeyUsage(crt));
        sbr.append("\n");
             

        
        // ending
        return sbr.toString();
    }
    
    /**
     * Load one or more certificates from the specified file.
     *
     * @param fCertFile The file to load certificates from.
     * @return The certificates.
     * @throws CryptoException Problem encountered while loading the certificate(s).
     * @throws FileNotFoundException If the certificate file does not exist,
     *                               is a directory rather than a regular
     *                               file, or for some other reason cannot
     *                               be opened for reading.
     * @throws IOException An I/O error occurred.
     */
    static public X509Certificate[] s_load(
        Frame frmOwner, 
        File fleCrt
        )
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_load(...)";
        
        if (fleCrt == null)
            MySystem.s_printOutExit(strMethod, "nil fleCrt");
        
        Vector<X509Certificate> vecCrt = new Vector<X509Certificate>();

        FileInputStream fisCrt = null;
        
        try
        {
            fisCrt = new FileInputStream(fleCrt);
        }
        
        catch(FileNotFoundException excFileNotFound)
        {
            excFileNotFound.printStackTrace();
            MySystem.s_printOutError(strMethod, "excFileNotFound caught");
            
            String strBody = "Got FileNotFound exception.";
            strBody += "\n" + excFileNotFound.getMessage();
            
            strBody += "\n\n";
            strBody += "See log file to get more info.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }

        CertificateFactory cfy;

        try
        {
            cfy = CertificateFactory.getInstance(UtilCrtX509.f_s_strType/*, _f_s_strProvider*/); // MEMO: the provider doesn't have to be registered
        }
        
        catch (CertificateException excCertificate)
        {
            excCertificate.printStackTrace();
            MySystem.s_printOutError(strMethod, "excCertificate caught");
            
            String strBody = "Got Certificate exception.";
            strBody += "\n" + excCertificate.getMessage();
            
            strBody += "\n\n";
            strBody += "See log file to get more info.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
            
            
            return null;
        }
        

        try
        {
            Collection colCrts = cfy.generateCertificates(fisCrt);
            Iterator itrCrts = colCrts.iterator();

            while (itrCrts.hasNext())
            {
                X509Certificate crtCur = (X509Certificate) itrCrts.next();
                
                if (crtCur != null)
                {
                    vecCrt.add(crtCur);
                    
                    
                    // beg test
                     
                    /*byte[] byts = UtilCrtX509.getSubjectKeyIdentifier(crtCur);
                    
                    if (byts == null)
                    {
                        System.err.println("failed to get SKI");
                    }
                    
                    else
                        System.err.println("got SKI");
                    */
                    
                    // end test
                }
            }
        }
        
        catch (Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            
            String strBody = "Got exception.";
            strBody += "\n" + exc.getMessage();
            
            strBody += "\n\n";
            strBody += "See log file to get more info.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
            
            
            return null;
        }
        
        try
        {
            fisCrt.close();
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            
            String strBody = "Got IO exception.";
            strBody += "\n" + excIO.getMessage();
            
            strBody += "\n\n";
            strBody += "See log file to get more info.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        X509Certificate[] crtsNew = new X509Certificate[vecCrt.size()];
        return (X509Certificate[]) vecCrt.toArray(crtsNew);
    }
    
    // BEG TEST
    
    private static byte[] getSubjectKeyIdentifier(X509Certificate cert) 
    {
        String SUBJECT_KEY_IDENTIFIER_OID = "2.5.29.14";
        byte[] subjectKeyIdentifier =
        cert.getExtensionValue(SUBJECT_KEY_IDENTIFIER_OID);
        
        if (subjectKeyIdentifier == null)
        {
            System.out.println("nil subjectKeyIdentifier");
            return null;
        }
        
        try 
        {
            sun.security.x509.KeyIdentifier keyId = null;

            sun.security.util.DerValue derVal = new sun.security.util.DerValue(
            new sun.security.util.DerInputStream(subjectKeyIdentifier).getOctetString());

            keyId = new sun.security.x509.KeyIdentifier(derVal.getOctetString());
            System.out.println("got it");
            return keyId.getIdentifier();
        } 
        
        catch (NoClassDefFoundError errNoClassDefFound) 
        {
            String strError = errNoClassDefFound.getMessage();
            System.err.println("got errNoClassDefFound:" + strError);
            
            if (subjectKeyIdentifier == null)
                return null;
            
            byte[] dest = new byte[subjectKeyIdentifier.length - 4];
            
            System.arraycopy(subjectKeyIdentifier, 4, dest, 0, subjectKeyIdentifier.length - 4);
            return dest;
        } 
        
        catch ( java.io.IOException excIO) 
        {
            excIO.printStackTrace();
            System.err.println("got excIO, returning nil");
            //ignore
            return null;
        }
    }
    
    // END TEST
    
    /**
        reordering from root (issuer == subject)
    **/
    static public X509Certificate[] s_orderChain(
        X509Certificate crtsSource[]
        )
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "s_orderChain(...)";
        
        if (crtsSource==null)
        {
            MySystem.s_printOutError(strMethod, "nil crtsSource");
            return null;
        }
        
        if (crtsSource.length == 0)
        {
            MySystem.s_printOutError(strMethod, "crtsSource.length == 0");
            return null;
        }
        
        if (crtsSource.length == 1)
            return crtsSource;
        
        try
        {
        
            X509Certificate[] crtsSourceCloned = (X509Certificate[])crtsSource.clone();
            X509Certificate[] crtsTarget = new X509Certificate[crtsSource.length];
            X509Certificate crtRoot = null;
            int intIdTarget = 0;

            for (int i=0; i<crtsSourceCloned.length; i++)
            {
                X509Certificate crtCloneCur = crtsSourceCloned[i];
                
                if (crtCloneCur.getIssuerDN().equals(crtCloneCur.getSubjectDN()))
                {
                    crtRoot = crtCloneCur;
                    crtsTarget[intIdTarget] = crtRoot;
                    intIdTarget++;
                }
            }

            // root not found!
            if (crtRoot == null)
            {
                return crtsSource;
            }

            // !!!!
            //intIdTarget --;

            while (true)
            {
                boolean blnGotIt = false;
                
                for (int i=0; i < crtsSourceCloned.length; i++)
                {
                    X509Certificate crtCloneCur = crtsSourceCloned[i];

                    if ((crtCloneCur.getIssuerDN().equals(crtRoot.getSubjectDN())) && (crtCloneCur != crtRoot))
                    {
                        // Yes
                        crtRoot = crtCloneCur;
                        crtsTarget[intIdTarget] = crtRoot; // !!!!!!!!!! TBRL out of range
                        intIdTarget ++;
                        blnGotIt = true;
                        break;
                    }
                }
                
                if (! blnGotIt)
                {
                    break;
                }
            }


            crtsSourceCloned = new X509Certificate[intIdTarget];
            System.arraycopy(crtsTarget, 0, crtsSourceCloned, 0, intIdTarget);

            crtsTarget = new X509Certificate[intIdTarget];

            for (int i=0; i < intIdTarget; i++)
            {
                crtsTarget[i] = crtsSourceCloned[crtsSourceCloned.length - 1 - i];
            }

            return crtsTarget;
        
        }
        
        catch(Exception exc) // eg ArrayIndexOutOfBoundsException, case of genkey, followed by selfcert
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            return null;
        }
    }
    
    // --------------
    // STATIC PRIVATE
    
    // ----------
    // 0) type
    
    /*static private String _s_dumpType(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpType(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil arg");
        
        return cer.getType();
    }*/
    
    // ----------
    // 1) version

    static private String _s_dumpVersion(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpVersion(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil arg");
            
        int intVersion = cer.getVersion();
        
        return Integer.toString(intVersion);
    }
    
    
    
    // ----------
    // 3) algorithm signature
    static private String _s_dumpSignatureAlgo(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpSignatureAlgo(cer)";
        
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");
        
        /** OK IN COMMENTS, this code just extracts "DSA" or "RSA"
        java.security.PublicKey pky = cer.getPublicKey(); 
        
        if (pky == null)
            MySystem.s_printOutExit(strMethod, "nil pky");
         
        return pky.getAlgorithm(); 
        **/
        

        return cer.getSigAlgName();
    }
    
    // ----------
    // 4) issuer
    static private String _s_dumpIssuer(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpIssuer(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");
            
        Principal pri = cer.getIssuerDN();
        
        return pri.getName();
    }
    
    // ----------
    // 5) start date
    static private String _s_dumpDateStart(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpDateStart(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");
           
        Date dat = cer.getNotBefore();
        return MySystem.s_getDate(dat);
    }
    
    // ----------
    // 6) end date
    static private String _s_dumpDateEnd(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpDateEnd(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");
            
        Date dat = cer.getNotAfter();
        return MySystem.s_getDate(dat);
    }
    
    // ----------
    // 7) subject
    static private String _s_dumpSubject(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpSubject(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");

        Principal pri = cer.getSubjectDN();
        
        return pri.getName();
    }
    
    // ----------
    // 9) fingerprints
    static private String _s_dumpCertFingerprints(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpCertFingerprints(cer)";
        
        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");
            
            
        String strResult = new String("");
            
            
        byte[] bytsCertificateData = null;
            
        try
        {
            bytsCertificateData = cer.getEncoded();
        }
            
        catch(CertificateEncodingException excCertificateEncoding)
        {
            excCertificateEncoding.printStackTrace();
            MySystem.s_printOutExit(strMethod, "excCertificateEncoding caught");
        }
            
            
        // --
        String str = null;
            
        // ------
            
            
        strResult += "\n" + "    " + UtilCrtX509.f_s_strDigestAlgoMD5 + ":" + "    ";
        strResult += " "; // one les letter
            
            
        str = _s_getFingerprint(bytsCertificateData, UtilCrtX509.f_s_strDigestAlgoMD5);
            
        if (str == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil str, ignoring");
            strResult += UtilCrtX509._s_strDumpMoreUnknown;
        }
            
        else
            strResult += str;
            
        // ------
            
            
        strResult += "\n" + "    " + UtilCrtX509.f_s_strDigestAlgoSHA1 + ":" + "    ";
            
            
        str = _s_getFingerprint(bytsCertificateData, UtilCrtX509.f_s_strDigestAlgoSHA1);
            
        if (str == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil str, ignoring");
            strResult += UtilCrtX509._s_strDumpMoreUnknown;
        }
            
        else
            strResult += str;
        
        // ending
        return strResult;
    }
    
    static private String _s_dumpKeyUsage(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpKeyUsage(cer)";

        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");
            
        String strResult = new String("");
        boolean[] blns = cer.getKeyUsage();

        if (blns == null)
        {
            strResult += " none";
            return strResult;
        }
        
        if (blns.length != _s_strsKeyUsage.length)
        {
            strResult += " ERROR!";
            strResult += "\nunexpected keyUsage length: " + blns.length; 
            strResult += "\nIGNORING";
            return strResult;
        }
        
        // beg check critical
        
        boolean blnCritical = false;
        Set<String> set = cer.getCriticalExtensionOIDs();
        
        if (set != null)
        {
            Iterator itr = set.iterator();
            
            while (itr.hasNext())
            {
                String strCur = (String) itr.next();
                
                if (strCur!=null && strCur.compareTo("2.5.29.15") != 0)
                    continue;
                
                blnCritical = true;
                break;
            }
                
        }
        
        if (! blnCritical)
            strResult += " not";
        
        strResult += " critical";
        
        strResult += "\n    List:";
        
        // end check critical
        
        boolean blnGotOne = false;
        
        for (int i=0; i<_s_strsKeyUsage.length; i++)
        {
            if (blns[i] == true)
            {
                if (! blnGotOne)
                    blnGotOne = true;
                
                 strResult += "\n    . " + _s_strsKeyUsage[i];
            }
        }
        
        if (! blnGotOne)
        {
            strResult += " empty";
            return strResult;
        }
        
        // ending
        return strResult;
    }
    

    static private String _s_dumpExtKeyUsage(X509Certificate cer)
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_dumpExtKeyUsage(cer)";

        if (cer == null)
            MySystem.s_printOutExit(strMethod, "nil cer");
            
        String strResult = new String("");
        
        java.util.List<String> lst = null;
        
        try
        {
            lst = cer.getExtendedKeyUsage();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "excFileNotFound caught");
            
            String strBody = "Got exception while parsing certificate:";
            strBody += "\n" + exc.getMessage();
            
            strBody += "\n\n";
            strBody += "Ignoring ...";
            
            OPAbstract.s_showDialogError(
                null, strBody);
            
            strResult += " unknown!";
            return strResult;
        }
        
        

        if (lst == null || lst.size() < 1)
        {
            strResult += " none";
            return strResult;
        }
        
        // beg check critical
        
        boolean blnCritical = false;
        Set<String> set = cer.getCriticalExtensionOIDs();
        
        if (set != null)
        {
            Iterator itr = set.iterator();
            
            while (itr.hasNext())
            {
                String strCur = (String) itr.next();
                
                if (strCur!=null && strCur.compareTo("2.5.29.37") != 0)
                    continue;
                
                blnCritical = true;
                break;
            }
                
        }
        
        if (! blnCritical)
            strResult += " not";
        
        strResult += " critical";
        
        strResult += "\n    List:";
        
        // end check critical

        
        
        for (java.util.Iterator itr = lst.iterator(); itr.hasNext();)
        {
            String strCur = (String) itr.next();
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.anyExtendedKeyUsage.getId()) == 0)
            {
                strResult += "\n    . " + "anyExtendedKeyUsage";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_serverAuth.getId()) == 0)
            {
                strResult += "\n    . " + "serverAuth";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_clientAuth.getId()) == 0)
            {
                strResult += "\n    . " + "clientAuth";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_codeSigning.getId()) == 0)
            {
                strResult += "\n    . " + "codeSigning";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_emailProtection.getId()) == 0)
            {
                strResult += "\n    . " + "emailProtection";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_ipsecEndSystem.getId()) == 0)
            {
                strResult += "\n    . " + "ipsecEndSystem";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_ipsecTunnel.getId()) == 0)
            {
                strResult += "\n    . " + "ipsecTunnel";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_ipsecUser.getId()) == 0)
            {
                strResult += "\n    . " + "ipsecUser";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_timeStamping.getId()) == 0)
            {
                strResult += "\n    . " + "timeStamping";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_OCSPSigning.getId()) == 0)
            {
                strResult += "\n    . " + "OCSPSigning";
                continue;
            }
            
            if (strCur.compareTo(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_smartcardlogon.getId()) == 0)
            {
                strResult += "\n    . " + "Microsoft's smartcardlogon";
                continue;
            }
           
            if (strCur.compareTo(com.google.code.p.keytooliui.shared.crypto.asnl.x509.MyKeyPurposeId.szOID_SERVER_GATED_CRYPTO.getId()) == 0)
            {
                strResult += "\n    . " + "Microsoft's server gated crypto";
                continue;
            }
            
            if (strCur.compareTo(com.google.code.p.keytooliui.shared.crypto.asnl.x509.MyKeyPurposeId.szOID_SERIALIZED.getId()) == 0)
            {
                strResult += "\n    . " + "Microsoft's serialized";
                continue;
            }
            
            if (strCur.compareTo(com.google.code.p.keytooliui.shared.crypto.asnl.x509.MyKeyPurposeId.szOID_EFS_CRYPTO.getId()) == 0)
            {
                strResult += "\n    . " + "Microsoft's EFS crypto";
                continue;
            }
            
            if (strCur.compareTo(com.google.code.p.keytooliui.shared.crypto.asnl.x509.MyKeyPurposeId.szOID_EFS_RECOVERY.getId()) == 0)
            {
                strResult += "\n    . " + "Microsoft's EFS recovery";
                continue;
            }
            
            // memo: CDS: Certified Document Services
            if (strCur.compareTo(com.google.code.p.keytooliui.shared.crypto.asnl.x509.MyKeyPurposeId.szOID_CDS_PKI.getId()) == 0)
            {
                strResult += "\n    . " + "Adobe's CDS PKI";
                continue;
            }
            
            if (strCur.compareTo(com.google.code.p.keytooliui.shared.crypto.asnl.x509.MyKeyPurposeId.id_kp_unknownKeyUsage.getId()) == 0)
            {
                strResult += "\n    . " + "unknown key usage";
                continue;
            }
            
 
            
            // Uncaught!
            strResult += "\n    . " + strCur;
        }
        
        // ending
        return strResult;
    }
    
    static private String _s_getFingerprint(
        byte[] bytsCertificate,
        String strAlgorithm) 
    {
        String strMethod = UtilCrtX509._f_s_strClass + "." + "_s_getFingerprint(bytsCertificate, strAlgorithm)";
        
        if (bytsCertificate==null || strAlgorithm==null)
        {
            MySystem.s_printOutExit(strMethod, "nil arg");
        }
        
        
        String str = new String("");
        
        
        MessageDigest mdt = null;
        
        try
        {
            mdt = MessageDigest.getInstance(strAlgorithm);
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutWarning(strMethod, "excNoSuchAlgorithm caught");
            return null;
        }
        
        
        mdt.update(bytsCertificate);
        byte[] bytsDigest = mdt.digest();

  
    
        for (int i=0; i<bytsDigest.length; i++)
        {
            if (i != 0)
                str += ":";
            
            int b = bytsDigest[i] & 0xff;
            String strHexa = Integer.toHexString(b).toUpperCase();
            
            if (strHexa.length() == 1)
                str += "0";
            
            str += strHexa;
        }
        
        return str;
    }
    

    
    static public X509Certificate s_generateCertX509V3(
        PublicKey pkyKeyPublic,
        PrivateKey pkyKeyPrivate,
        Date dteCertNotBefore,
        Date dteCertNotAfter,
        Hashtable<DERObjectIdentifier, String> hstAttributes,
        Vector<DERObjectIdentifier> vecX509Principal,
        
        String strCertAlgoSignType,
        Frame frmOwner,
        Vector<DERObjectIdentifier> vecCrtExtExtKeyUsage,
        boolean blnCrtExtExtKeyUsageCritical,
        boolean blnCrtExtKeyUsage,
        boolean blnCrtExtKeyUsageCritical,
        int intCrtExtKeyUsageValue,
        String strProviderKpg
        )
    {
        String strMethod = "UtilCrtX509._s_generateCertX509V3_(...)";
        
        if (pkyKeyPublic==null || pkyKeyPrivate==null || 
            dteCertNotBefore==null || dteCertNotAfter==null ||
            hstAttributes==null || vecX509Principal==null)
        {
            MySystem.s_printOutExit(strMethod, "nil arg");
        }

        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();

        certGen.setIssuerDN(new X509Principal(vecX509Principal, hstAttributes));
        certGen.setSubjectDN(new X509Principal(vecX509Principal, hstAttributes));
        
        certGen.setNotBefore(dteCertNotBefore);   
        certGen.setNotAfter(dteCertNotAfter);
        
        certGen.setPublicKey(pkyKeyPublic);
        
        try
        {
            certGen.setSignatureAlgorithm(strCertAlgoSignType);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "Got Exception, strCertAlgoSignType=" + strCertAlgoSignType);
                
            String strBody = "Got Exception.";
            strBody += "\n  " + exc.getMessage();
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        BigInteger bir = new BigInteger(Long.toString(System.currentTimeMillis() / 1000));
        certGen.setSerialNumber(bir);

        // extKeyUsage
        
        if (vecCrtExtExtKeyUsage!=null && vecCrtExtExtKeyUsage.size() > 0)
        {
            
            try
            {
                //ExtendedKeyUsage eku = new ExtendedKeyUsage(KeyPurposeId.id_kp_timeStamping);
                //Vector vec = new Vector();
                //vec.add(KeyPurposeId.id_kp_timeStamping);
                ExtendedKeyUsage eku = new ExtendedKeyUsage(vecCrtExtExtKeyUsage);
                certGen.addExtension(X509Extensions.ExtendedKeyUsage, blnCrtExtExtKeyUsageCritical, eku);
            }

            catch(Exception exc)
            {
                exc.printStackTrace();

                MySystem.s_printOutError(strMethod, "Got Exception, strCertAlgoSignType=" + strCertAlgoSignType);

                String strBody = "Got Exception.";
                strBody += "\n  " + exc.getMessage();

                OPAbstract.s_showDialogError(
                    frmOwner, strBody);

                return null;
            }
        }
        
        // beg keyUsage
        
        if (blnCrtExtKeyUsage)
        {
            certGen.addExtension(
                X509Extensions.KeyUsage,
                blnCrtExtKeyUsageCritical,
                //new org.bouncycastle.asn1.x509.KeyUsage(KeyUsage.cRLSign | KeyUsage.keyCertSign)
                new org.bouncycastle.asn1.x509.KeyUsage(intCrtExtKeyUsageValue)
                );
        
        }
        
        // end keyUsage
        X509Certificate crt = null;

        try
        { 
            crt = certGen.generate(pkyKeyPrivate, strProviderKpg);  
        }
        
        catch (InvalidKeyException excInvalidKey)
        {
            excInvalidKey.printStackTrace();
            
            MySystem.s_printOutError(strMethod, "Got excInvalidKey Exception");
                
            String strBody = "Got InvalidKey Exception";
            strBody += "\n  " + excInvalidKey.getMessage();
            
            if (excInvalidKey.getMessage().trim().toLowerCase().startsWith("key is too short"))
              strBody += "\n\n " + "Workaround: please increase key size.";
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        catch (Exception exc)
        {
            exc.printStackTrace();
            
            MySystem.s_printOutError(strMethod, "Got excException");
                
            String strBody = "Got Exception.";
            strBody += "\n  " + exc.getMessage();
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        return crt;
    }
    
}