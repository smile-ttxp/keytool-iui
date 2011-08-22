/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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

    known subclasses:
    
    . KTLShkSaveNewAllJks // !!! not sure
    . KTLShkSaveNewAllJceks
    

**/

import javax.crypto.SecretKey;
import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
//import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.awt.*;
import java.io.*;
import java.util.*;

public abstract class KTLShkSaveNewAllJAbs extends KTLShkSaveNewAllAbs
{
    // ---------
    // PROTECTED
    
    protected KTLShkSaveNewAllJAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        String strCertAlgoSignType,
        
        String strProviderKst
        )
    {
        super(
            frmOwner, 

            
            // input
            strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
            chrsPasswdOpenKst, 
            
            // output
            strCertAlgoSignType, //            
            strProviderKst
        );
            
    }
    
    
    protected boolean __doJob__(
        KeyStore kstOpen, 
            
        // below: about PKTC (Private Key & Trusted Certificate)
        String[] strsAliasPKTC, 
        Boolean[] boosIsTCEntryPKTC, 
        Boolean[] boosValidDatePKTC, 
        Boolean[] boosSelfSignedCertPKTC, 
        Boolean[] boosTrustedCertPKTC, 
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC, 
        String[] strsAlgoSigCertPKTC, 
        Date[] dtesLastModifiedPKTC,
        // below: about SK (Secret Key)
        String[] strsAliasSK,
        Date[] dtesLastModifiedSK
            
        )
    {
        String strMethod = "__doJob__(...)";
        // ----
        
        // MEMO: overwriting alias-key not allowed
        // -----

        // ----
        // show dialog shared key new All
        //  . get aliasShk
        //  . get passwdShk
       
        
        DTblsKstViewKeySaveSK dlg = new DTblsKstViewKeySaveSK(
            (Component) super._frmOwner_, 

            kstOpen,
            super._strPathAbsKst_,
            "Create secret key entry");
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlg.load(
            
            // below: about PKTC (Private Key & Trusted Certificate)
            strsAliasPKTC, 
            boosIsTCEntryPKTC, 
            boosValidDatePKTC, 
            boosSelfSignedCertPKTC, 
            boosTrustedCertPKTC, 
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC, 
            strsAlgoSigCertPKTC, 
            dtesLastModifiedPKTC,
            // below: about SK (Secret Key)
            strsAliasSK,
            dtesLastModifiedSK))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlg.setVisible(true);
        
        // ---
        char[] chrsPasswdShk = dlg.getPassword();
        
        if (chrsPasswdShk == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdShk, aborted by user");
            return false;
        }
        
        String strAliasShk = dlg.getAlias();
        
        if (strAliasShk == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasShk, aborted by user");
            return false;
        }
        
        SecretKey sky = null;
        
        try
        {
            sky = super._getShkNew_();
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            excNoSuchAlgorithm.printStackTrace();
            
            MySystem.s_printOutError(this, strMethod, "excNoSuchAlgorithm caught");
            


            // show warning dialog
            String strBody = "got NoSuchAlgorithmException:";
            strBody += "\n  " + excNoSuchAlgorithm.getMessage();
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            
            
            
            return false;
        }
        
        if (sky == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil sky");
            return false;
        }
        
        if (! super._assignNewEntry2OpenKeystore_(kstOpen, sky, strAliasShk, chrsPasswdShk))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        return true;
    }
    
    
}
