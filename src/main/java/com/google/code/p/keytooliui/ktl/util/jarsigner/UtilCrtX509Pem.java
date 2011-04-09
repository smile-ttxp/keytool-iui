package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "UtilCsr" ==> "Utility, CSR (Certificate Signing Request)"
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

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

// ----

import java.io.*;
import java.awt.*;

public final class UtilCrtX509Pem
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilCrtX509Pem";
    
    private static final String _f_s_strLineBeg = "-----BEGIN CERTIFICATE-----";
    private static final String _f_s_strLineEnd = "-----END CERTIFICATE-----";
    private static final int _f_s_intLineLengthMax = 76;
    
    // -------------
    // PUBLIC STATIC
    
 
    public static void s_showFile(
        Frame frmParent,
        File fle)
    {
        String strMethod = UtilCrtX509Pem._f_s_strClass + "." + "s_showFile(...)";
        
            
        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil fle");
            return;
        }
            
        if (! fle.exists())
        {
            MySystem.s_printOutExit(strMethod, "! fle.exists(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
        }
        
        if (! fle.canRead())
        {
            MySystem.s_printOutExit(strMethod, "! fle.canRead(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
        }
        
        // launch dialog 

        com.google.code.p.keytooliui.shared.swing.dialog.DViewSourceFileTextSys dlg = new
            com.google.code.p.keytooliui.shared.swing.dialog.DViewSourceFileTextSys(frmParent);
        
        if (! dlg.init())
        {
            MySystem.s_printOutExit(strMethod, "failed");
        }
        
        dlg.setTitle(
            System.getProperty("_appli.title") + " - " + "view" + " " + com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescCrtX509Pem);
        

        if (! dlg.show(fle))
            MySystem.s_printOutExit(strMethod, "failed"); 
    }
    
    public static void s_showFile(
        Frame frmParent)
    {
        String strMethod = UtilCrtX509Pem._f_s_strClass + "." + "s_showFile(...)";
        
        File fle = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Pem, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescCrtX509Pem,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultCrt
            );
            
        if (fle == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fle");
            return;
        }
        
        UtilCrtX509Pem.s_showFile(frmParent, fle);
        
    }
    
    /**
        MEMO: strProviderSignature: 
        . supported:     "BC"
        . NOT supported: "SunRsaSign"
    **/
    public static String s_generateCrt(
        Frame frmOwner, 
        X509Certificate crt)
    {
        String strMethod = UtilCrtX509Pem._f_s_strClass + "." + "s_generateCrt(...)";
        
        if (crt == null)
        {
            MySystem.s_printOutExit(strMethod, "nil crt");
        }
        
        String strEncoder = null;
        
        try
        {
            strEncoder = new String(Base64.encode(crt.getEncoded()));
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught");
            
            String strBody = "Got an exception while attempting to get certificate encoder";
            strBody += "\n\n";
            strBody += "  ";
            strBody += "More in your session.log file";
                        
            OPAbstract.s_showDialogWarning(
                frmOwner, strBody);
            
            return null;
        }
        
        

        String strResult = new String(UtilCrtX509Pem._f_s_strLineBeg + "\n");

        for (int i=0; i < strEncoder.length(); i += UtilCrtX509Pem._f_s_intLineLengthMax)
        {
            int intLineLength = UtilCrtX509Pem._f_s_intLineLengthMax;

            if ((i + UtilCrtX509Pem._f_s_intLineLengthMax) > strEncoder.length())
            {
                intLineLength = (strEncoder.length() - i);
            }

            strResult += strEncoder.substring(i, (i + intLineLength)) + "\n";
        }

 
        strResult += UtilCrtX509Pem._f_s_strLineEnd + "\n";
        return strResult;
    }
}