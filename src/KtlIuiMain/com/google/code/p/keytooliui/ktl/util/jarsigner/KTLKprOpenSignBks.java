/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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

    "Kpr" for "keypair"
    

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

import sun.misc.BASE64Encoder;
//import sun.security.util.SignatureFile;
import sun.security.util.ManifestDigester;

// ----
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
// --
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.jar.*;

final public class KTLKprOpenSignBks extends KTLKprOpenSignKPAbs
{
    // ------
    // PUBLIC
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        if (super._strProviderKst_.toLowerCase().compareTo(KTLAbs.f_s_strProviderKstBks.toLowerCase()) != 0)
        {
            MySystem.s_printOutExit(this, strMethod, "wrong value, super._strProviderKst_=" + super._strProviderKst_);
        }
        
        if (! super.doJob())
            return false;
     
        return true;
    }
    
    

    public KTLKprOpenSignBks(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Bks 
        char[] chrsPasswdOpenKst,
        String strProviderKst,
        
        String strPathAbsOpenJarSource, // unsigned
        
        // output
        String strPathAbsSaveJarTarget, // to be signed
        String strNameBaseSigFile // nil value allowed
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsOpenKst, 
            chrsPasswdOpenKst,
            strProviderKst, // "Kst": "Bks"
            
            strPathAbsOpenJarSource,
            strPathAbsSaveJarTarget,
            strNameBaseSigFile
            );
        
        
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpenKst)
    {
        KeyStore kstOpen = UtilKstBks.s_getKeystoreOpen(
            super._frmOwner_, super._strTitleAppli_,
            fleOpenKst,
            super._chrsPasswdKst_);
        
        return kstOpen;
    }
}
