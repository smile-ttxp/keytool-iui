package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "UtilCrt" ==> "Utility, Certificate"
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// ----
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.asn1.DEROutputStream;
// ----


// ----
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
// --
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateFactory;

// ----

import java.io.*;
import java.awt.*;
import java.util.Arrays;

final public class UtilCrtX509Pkcs7 extends UtilCrtX509
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilCrtX509Pkcs7";
    

    
    // -------------
    // STATIC PUBLIC
    
    /**
        MEMO: strProviderSignature: 
        . ? supported:     "BC"
        . ? NOT supported: "SunRsaSign"
    **/
    static public byte[] s_generateCrt(
        Frame frmOwner, 
        String strTitleAppli,
        X509Certificate crt, 
        String strProviderSignature
        )
    {
        String strMethod = UtilCrtX509Pkcs7._f_s_strClass + "." + "s_generateCrt(...)";
        
        if (crt==null || strProviderSignature==null)
            MySystem.s_printOutExit(strMethod, "nil arg");
            
        byte[] byt = null;
            
        X509Certificate[] crts = new X509Certificate[] {crt};
       
        try
        {
            CertificateFactory cf =
                CertificateFactory.getInstance("X.509");
            
            byt = cf.generateCertPath(
                Arrays.asList(crts)).getEncoded("PKCS7");
        }
        catch (CertificateException excCertificate)
        {
            excCertificate.printStackTrace();
            
            MySystem.s_printOutError(strMethod, "excNoSuchAlgorithm caught");
            
            String strBody = "Got Certificate exception";
            
            strBody += "\n\n";
            strBody += "More info in log file.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strTitleAppli, strBody);
                
            return null;
        }
        
        
        return byt;
    }
    
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent,
        File fleCrt)
    {
        String strMethod = UtilCrtX509Pkcs7._f_s_strClass + "." + "s_showFile(...)";
        
        if (fleCrt == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleCrt");
            return;
        }
        
        
        // --
        
        X509Certificate[] crts = UtilCrtX509.s_load(frmParent, strTitleAppli, fleCrt);
        
        if (crts == null)
        {
            MySystem.s_printOutError(strMethod, "nil crts");
            return;
        }
        
        UtilCrtX509.s_showChain(frmParent, strTitleAppli, crts);
        
    }
    
    static public void s_showFile(
        String strTitleAppli,
        Frame frmParent)
    {
        String strMethod = UtilCrtX509Pkcs7._f_s_strClass + "." + "s_showFile(...)";
        
        File fleCrt = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            strTitleAppli, 
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Pkcs7, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescCrtX509Pkcs7,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultCrt
            );
            
        if (fleCrt == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fleCrt");
            return;
        }
            
        if (! fleCrt.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleCrt.exists(), fleCrt.getAbsolutePath()=" + fleCrt.getAbsolutePath());
            
            String strBody = "File does not exist:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleCrt.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        if (! fleCrt.canRead())
        {
            MySystem.s_printOutError(strMethod, "! fleCrt.canRead(), fleCrt.getAbsolutePath()=" + fleCrt.getAbsolutePath());
            
            String strBody = "File cannot be read:";
            
            strBody += "\n\n";
            strBody += "  ";
            strBody += fleCrt.getAbsolutePath();
            
                
            OPAbstract.s_showDialogError(
                frmParent, strTitleAppli, strBody);
                
            return;
        }
        
        UtilCrtX509Pkcs7.s_showFile(strTitleAppli, frmParent, fleCrt);
    }
    
   
}