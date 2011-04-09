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

    known subclasses:
    
    . KTLShkSaveNewAllJAbs

    

**/

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

public abstract class KTLShkSaveNewAllAbs extends KTLShkSaveNewAbs
{
    // ------------------
    // ABSTRACT PROTECTED
    
    // definition in subclasses, called there
    protected abstract boolean __doJob__(
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
        Date[] dtesLastModifiedSK);
    
    // ---------
    // PROTECTED
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . fill in table 
        . show dialog  new All
          . get aliasShk
          . get passwdShk
        . create new 
        . create new certificate of type X.509
        . assign new entry to open keystore
        . save keystore
    **/
    protected boolean _doJob_(
        File fleOpenKst,
        KeyStore kstOpen
        )
    {
        String strMethod = "_doJob_(fleOpenKst, kstOpen)";
        
        if (fleOpenKst==null || kstOpen==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        // ----
        // fill in table 
        
        // NEW
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
     
            kstOpen);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,

            kstOpen);
        
        if (strsAliasSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        // --
        // get arrays for dialogTableSelectSharedKey
        // TC versus PK
        Boolean[] boosIsTCEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            kstOpen, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            kstOpen, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
      
        
        // ----
        // restore default cursor
        super._setEnabledCursorWait_(false);
        
        
        if (! __doJob__(
            kstOpen,
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
            dtesLastModifiedSK
            ))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
       
        
        // ----
        // save keystore
        if (super._chrsPasswdKst_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._chrsPasswdKst_");
            return false;
        }        
        
        // ----
        
        if (! super._saveKeyStore_(kstOpen, fleOpenKst, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    protected KTLShkSaveNewAllAbs(
        Frame frmOwner, 
   
        
        // input
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // output
        String strCertAlgoSignType, 
        String strProviderKst
        )
    {
        super(
            frmOwner, 

            
            // input
            strPathAbsOpenKst,
            chrsPasswdOpenKst, 
            strProviderKst,

            // output
            strCertAlgoSignType
            );

    }
    
}
