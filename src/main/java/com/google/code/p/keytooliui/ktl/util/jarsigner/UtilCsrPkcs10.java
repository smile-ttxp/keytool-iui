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
import org.bouncycastle.jce.PKCS10CertificationRequest;
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

public final class UtilCsrPkcs10
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.jarsigner.UtilCsrPkcs10";
    
    private static final String _f_s_strLineBeg = "-----BEGIN NEW CERTIFICATE REQUEST-----";
    private static final String _f_s_strLineEnd = "-----END NEW CERTIFICATE REQUEST-----";
    private static final int _f_s_intLineLengthMax = 76;
    
    // -------------
    // PUBLIC STATIC
    
    public static void s_showFile(
        Frame frmParent)
    {
        String strMethod = UtilCsrPkcs10._f_s_strClass + "." + "s_showFile(...)";
        
        File fle = com.google.code.p.keytooliui.ktl.io.S_FileChooserUI.s_getOpenFile(
            frmParent, 
            "select",
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCsrPkcs10, 
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescCsrPkcs10,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultCsr
            );
            
        if (fle == null)
        {
            MySystem.s_printOutTrace(strMethod, "nil fle");
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
            System.getProperty("_appli.title") + " - " + "view" + " " + com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strFileDescCrtX509Pkcs7);
        

        if (! dlg.show(fle))
            MySystem.s_printOutExit(strMethod, "failed"); 
    }
    
    /**
        MEMO: strProviderSignature: 
        . supported:     "BC"
        . NOT supported: "SunRsaSign"
    **/
    public static String s_generateCsr(
        Frame frmOwner, 
        X509Certificate crt, 
        PrivateKey keyPrivate,
        String strProviderSignature)
    {
        String strMethod = UtilCsrPkcs10._f_s_strClass + "." + "s_generateCsr(...)";
        
        if (crt==null || keyPrivate==null || strProviderSignature==null)
            MySystem.s_printOutExit(strMethod, "nil arg");
        
        
        if (crt.getSubjectDN() == null)
            MySystem.s_printOutExit(strMethod, "nil crt.getSubjectDN()");
        
        X509Name namSubject = new X509Name(crt.getSubjectDN().toString());
        
        if (namSubject == null)
            MySystem.s_printOutExit(strMethod, "nil namSubject");


        
        PKCS10CertificationRequest crtPkcs10 = null;
        boolean blnVerified = false;
        
        try
        {
            crtPkcs10 = new PKCS10CertificationRequest(
                crt.getSigAlgName(),
                namSubject,
                crt.getPublicKey(),
                null,
                keyPrivate,
                strProviderSignature
                );
                    
            blnVerified = crtPkcs10.verify(strProviderSignature);
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchAlgorithm caught");
            
            String strBody = "Got NoSuchAlgorithm exception";
            strBody += "\n  " + excNoSuchAlgorithm.getMessage();
            
            strBody += "\n\n";
            strBody += "More info in log file.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        catch(NoSuchProviderException excNoSuchProvider)
        {
            excNoSuchProvider.printStackTrace();
            MySystem.s_printOutError(strMethod, "excNoSuchProvider caught");
            
            String strBody = "Got NoSuchProvider exception";
            strBody += "\n  " + excNoSuchProvider.getMessage();
            
            strBody += "\n\n";
            strBody += "More info in log file.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        catch(InvalidKeyException excInvalidKey)
        {
            excInvalidKey.printStackTrace();
            MySystem.s_printOutError(strMethod, "excInvalidKey caught");
            
            String strBody = "Got InvalidKey exception";
            strBody += "\n  " + excInvalidKey.getMessage();
            
            
            strBody += "\n\n";
            strBody += "More info in log file.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        catch(SignatureException excSignature)
        {
            excSignature.printStackTrace();
            MySystem.s_printOutError(strMethod, "excSignature caught");
            
            String strBody = "Got Signature exception";
            strBody += "\n  " + excSignature.getMessage();
            
            strBody += "\n\n";
            strBody += "More info in log file.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }

        
        
        if (! blnVerified)
        {
            MySystem.s_printOutError(strMethod, "! blnVerified");
                
            String strBody = "Failed to verify CSR";
            
            strBody += "\n\n";
            strBody += "More info in session.log file.";
            
            OPAbstract.s_showDialogError(frmOwner, strBody);
                
            return null;
        }

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        DEROutputStream dos = new DEROutputStream(bao);
        
        try
        {
            dos.writeObject(crtPkcs10.getDERObject());
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            
            String strBody = "Got IO exception";
            strBody += "\n  " + excIO.getMessage();
            
            strBody += "\n\n";
            strBody += "More info in log file.";
            
                
            OPAbstract.s_showDialogError(
                frmOwner, strBody);
                
            return null;
        }
        
        String strEncoder = new String(Base64.encode(bao.toByteArray()));

        String strResult = new String(UtilCsrPkcs10._f_s_strLineBeg + "\n");

        for (int i=0; i < strEncoder.length(); i += UtilCsrPkcs10._f_s_intLineLengthMax)
        {
            int intLineLength = UtilCsrPkcs10._f_s_intLineLengthMax;

            if ((i + UtilCsrPkcs10._f_s_intLineLengthMax) > strEncoder.length())
            {
                intLineLength = (strEncoder.length() - i);
            }

            strResult += strEncoder.substring(i, (i + intLineLength)) + "\n";
        }

 
        strResult += UtilCsrPkcs10._f_s_strLineEnd + "\n";
        return strResult;
    }
}