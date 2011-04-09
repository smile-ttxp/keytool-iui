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

    KTLShkSaveNewAllPkcs12: "Shk" for "Shared key"
    
    
    !!!!!!!!! not done yet: overwritting existing Shared key entry

**/

import javax.crypto.SecretKey;
import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// memo: assigning full class path coz ambiguous: same class name in several Java packages
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


import java.awt.*;
import java.io.*;
import java.util.*;

public final class KTLShkSaveNewAllPkcs12 extends KTLShkSaveNewAllAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . fill in tables
        . show dialog new 
          . get aliasShk
          . get passwdShk
        . create new shared key
        . assign new entry to open keystore
        . save keystore
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        //MySystem.s_printOutWarning(this, strMethod, "<<<< IN PROGRESS >>>>");
        
        
        super._setEnabledCursorWait_(true);
        
        
        // ---
        
        // memo: keystore should be of type "Pkcs12", provided by "??"
        File fleOpenKstPkcs12 = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKst_);
        
        if (fleOpenKstPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstPkcs12");
            return false;
        }
        
        // ----
        // open Pkcs12 keystore
        
        if (super._chrsPasswdKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        }
        
        KeyStore kstOpenPkcs12 = UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKstPkcs12,
            super._chrsPasswdKst_);
        
        if (kstOpenPkcs12 == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil kstOpenPkcs12");
            return false;
        }
        
        if (! super._doJob_(fleOpenKstPkcs12, kstOpenPkcs12))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        
        
        
        // ending
        return true;
    }
    
    public KTLShkSaveNewAllPkcs12(
        Frame frmOwner, 
  
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Pkcs12 
        char[] chrsPasswdOpenKst, 
        
        // output
        String strInstanceKeygenerator
        )
    {
        super(
            frmOwner, 
       
        
            // input
            strPathAbsOpenKst, // existing keystore of type Pkcs12 
            chrsPasswdOpenKst, 
        
            // output
            strInstanceKeygenerator,
            KTLAbs.f_s_strProviderKstPkcs12 // provider for keystore
            
            );
    }
    
    // ---------
    // protected
    
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
       
        
        DTblsKstViewKeySaveSKNoPass dlg = new DTblsKstViewKeySaveSKNoPass(
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
        char[] chrsPasswdShk = new char[0]; // dlg.getPassword();
        
        /*if (chrsPasswdShk == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdShk, aborted by user");
            return false;
        }*/
        
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

