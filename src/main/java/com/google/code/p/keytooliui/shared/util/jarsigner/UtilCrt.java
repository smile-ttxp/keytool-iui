/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
 
 
package com.google.code.p.keytooliui.shared.util.jarsigner;

/**
    "UtilCrt" ==> "Utility, Certificate"
    
    known subclasses:
    . UtilCrtX509
**/


import com.google.code.p.keytooliui.shared.lang.*;


// ----
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
// ----

public class UtilCrt
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.util.jarsigner.UtilCrt";
    
    // -------------
    // PUBLIC STATIC
    
    public static boolean s_isSelfSigned(Certificate crt)
    {
        String strMethod = UtilCrt._f_s_strClass + "." + "s_isSelfSigned(crt)";
        
        if (crt == null)
        {
            MySystem.s_printOutExit(strMethod, "nil crt");
        }
        
        return UtilCrt.s_isSignedBy(crt, crt);
    }
    
    /**
        crtSigned & crtSigner could be the same
    **/
    public static boolean s_isSignedBy(Certificate crtSigned, Certificate crtSigner)
    {
        String strMethod = UtilCrt._f_s_strClass + "." + "s_isSignedBy(...)";
        
        if (crtSigned==null || crtSigner==null)
        {
            MySystem.s_printOutExit(strMethod, "nil arg");
        }
        
        PublicKey pkySigner = crtSigner.getPublicKey();
        
        
        try
        {
            crtSigned.verify(pkySigner);
        }
        
        catch(CertificateException excCertificate)
        {
            //excCertificate.printStackTrace();
            //MySystem.s_printOutWarning(strMethod, "got excCertificate");
            return false;
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            //excNoSuchAlgorithm.printStackTrace();
            //MySystem.s_printOutWarning(strMethod, "got excNoSuchAlgorithm");
            return false;
        }
        
        catch(InvalidKeyException excInvalidKey)
        {
            //excInvalidKey.printStackTrace();
            //MySystem.s_printOutWarning(strMethod, "got excInvalidKey");
            return false;
        }
        
        catch(NoSuchProviderException excNoSuchProvider)
        {
            //excNoSuchProvider.printStackTrace();
            //MySystem.s_printOutWarning(strMethod, "got excNoSuchProvider");
            return false;
        }
        
        catch(SignatureException excSignature)
        {
            //excSignature.printStackTrace();
            //MySystem.s_printOutWarning(strMethod, "got excSignature");
            return false;
        }
        
        return true;
    }
    
    
}