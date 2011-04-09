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

public final class UtilCrtX509Der extends UtilCrtX509
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilCrtX509Der";
    

    
    // -------------
    // PUBLIC STATIC
    
    public static byte[] s_generateCrts(
        Frame frmOwner, 
        X509Certificate[] crts, 
        String strProviderSignature)
        throws Exception
    {
        String strMethod = UtilCrtX509Der._f_s_strClass + "." + "s_generateCrts(...)";
        
        if (crts==null || strProviderSignature==null)
            MySystem.s_printOutExit(strMethod, "nil arg");
        
        if (crts.length < 1)
            MySystem.s_printOutExit(strMethod, "crts.length < 1"); 
        
        int intLengthAll = 0;
        
        for (int i=0; i<crts.length; i++)
        {
            byte[] bytsCur = crts[i].getEncoded();
            
            intLengthAll += bytsCur.length;
        }
            
        byte[] bytsAll = new byte[intLengthAll];
        
        int intIDAll = 0;
        for (int i=0; i<crts.length; i++)
        {
            byte[] bytsCur = crts[i].getEncoded();
            
            for (int j=0; j<bytsCur.length;j++)
            {
                bytsAll[intIDAll] = bytsCur[j];
                intIDAll ++;
            }
        }

        return bytsAll;
    }
    
    
    /**
        MEMO: strProviderSignature: 
        . ? supported:     "BC"
        . ? NOT supported: "SunRsaSign"
    **/
    public static byte[] s_generateCrt(
        Frame frmOwner, 
        X509Certificate crt, 
        String strProviderSignature)
    {
        String strMethod = UtilCrtX509Der._f_s_strClass + "." + "s_generateCrt(...)";
        
        if (crt==null || strProviderSignature==null)
            MySystem.s_printOutExit(strMethod, "nil arg");
            
        byte[] byts = null;
            
       
        try
        {            
            byts = crt.getEncoded();
        }
        
        catch (CertificateException excCertificate)
        {
            excCertificate.printStackTrace();
            
            MySystem.s_printOutError(strMethod, "excNoSuchAlgorithm caught");
            
            String strBody = "Got Certificate exception";
            strBody += "\n" + excCertificate.getMessage();
            
            strBody += "\n\n";
            strBody += "More info in log file.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        
        return byts;
    }
    
    
    public static void s_showFile(
        Frame frmParent,
        File fleCrt)
    {
        String strMethod = UtilCrtX509Der._f_s_strClass + "." + "s_showFile(...)";
        
        if (fleCrt == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleCrt");
            return;
        }
        
        
        // --
        
        X509Certificate[] crts = UtilCrtX509.s_load(frmParent, fleCrt);
        
        if (crts == null)
        {
            MySystem.s_printOutError(strMethod, "nil crts");
            return;
        }
        
        UtilCrtX509.s_showChain(frmParent, crts);
    }
    
    public static void s_showFile(
        Frame frmParent)
    {
        String strMethod = UtilCrtX509Der._f_s_strClass + "." + "s_showFile(...)";
        
        File fleCrt = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Der, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescCrtX509Der,
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
                frmParent, strBody);
                
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
                frmParent, strBody);
                
            return;
        }
        
        UtilCrtX509Der.s_showFile(frmParent, fleCrt);
    }
    
   
}