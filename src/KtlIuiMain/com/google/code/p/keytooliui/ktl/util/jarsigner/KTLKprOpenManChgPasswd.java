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
 
 
package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair" (private key)
  

**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

// ----
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
// ----


import java.awt.*;
import java.io.*;


final public class KTLKprOpenManChgPasswd extends KTLKprOpenManAbs
{
    // ------
    // PUBLIC
    
    
    /**
        giving an alias,
        change its password
        
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
                
        if (! super.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "failed");
            return false;
        }
        
        // --
        // get password old
        
        char[] chrsPasswordKprOld = super._getPasswordKpr_();
        
        if (chrsPasswordKprOld == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswordKprOld, user canceled");
            return false;
        }
            
        // ---
        // get new password
        
        char[] chrsPasswordKprNew = _getPasswordKprNew();
        
        if (chrsPasswordKprNew == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswordKprNew, user canceled");
            return false;
        }
        
        String strOld = new String(chrsPasswordKprOld);
        String strNew =  new String(chrsPasswordKprNew);
        
        // if same name, abort
        if (strOld.compareTo(strNew) == 0)
        {
            MySystem.s_printOutWarning(this, strMethod, "strOld.compareTo(strNew) == 0");
            
            String strBody = "Same password for alias named:\n  " + super._strAlias_;
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        strOld = null;
        strNew = null;
        
        
        // --
        // do job
        
        Key key = null;
        
        try
        {
            key = super._kstOpen_.getKey(super._strAlias_, chrsPasswordKprOld);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            
            String strBody = "Got keystore exception!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        catch(NoSuchAlgorithmException excNoSuchAlgorithm)
        {
            excNoSuchAlgorithm.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excNoSuchAlgorithm caught");
            
            String strBody = "Got NoSuchAlgorithm exception!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        catch(UnrecoverableKeyException excUnrecoverableKey)
        {
            //excUnrecoverableKey.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excUnrecoverableKey caught");
            
            String strBody = "Failed to recover key!";
            
            strBody += "\n\n" + "Password may be wrong.";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        if (key == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil key");
            
            String strBody = "No valid key found in private key entry!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }

        // --
        Certificate[] crtsChain = null;
        
        try
        {
            crtsChain = super._kstOpen_.getCertificateChain(super._strAlias_);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            
            String strBody = "Got keystore exception!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        if (crtsChain == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsChain");
            
            String strBody = "No certificates chain found in private key entry!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        // --
        // set key entry

        try
        {
            super._kstOpen_.setKeyEntry(super._strAlias_, key, chrsPasswordKprNew, crtsChain);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            
            String strBody = "Got keystore exception!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        
        // get keystore file
        
        // memo: JKS keystore should be of type "JKS", provided by "SUN"
        File fleOpenKstJks = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKstJks == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstJks");
            
            return false;
        }
        
        // --
        // save keystore
        
        // ----
        // save keystore
        if (super._chrsPasswdKst_ == null) // rather exit
        {
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_");
        }        
        
        // ----
        
        if (! super._saveKeyStore_(super._kstOpen_, fleOpenKstJks, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // --
        return true;
    }

    
    public KTLKprOpenManChgPasswd(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        String strProviderKst,
        
        KeyStore kstOpen,
        String strAlias
        )
    {
        super(
            frmOwner, 
            strTitleAppli, 
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strProviderKst,
            
            kstOpen,
            strAlias
            );
  
    }
    
    // -------
    // PRIVATE
    
    
    private char[] _getPasswordKprNew()
    {
        String strMethod = "_getPasswordKprNew()";
        
        DPasswordAbs dlg = new DPasswordConfirmSave(super._frmOwner_, super._strTitleAppli_);
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
            
        dlg.setVisible(true);
        
        char[] chrsPassword = dlg.getPassword();
        
        dlg.destroy();
        dlg = null;
        
        if (chrsPassword == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPassword");
            return null;
        }
        
        
        if (chrsPassword.length < 1)
        {
            MySystem.s_printOutTrace(this, strMethod, "chrsPassword.length < 1");
            return null;
        }
       
        if (! _isValidPassword(chrsPassword))
        {
            MySystem.s_printOutWarning(this, strMethod, "! _isValidPassword(chrsPassword)");
            return null;
        }
                
        return chrsPassword;
    }
    
    private boolean _isValidPassword(char[] chrsPassword)
    {
        String strMethod = "_isValidPassword(chrsPassword)";
        
        if (chrsPassword == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil chrsPassword");
        }
        
        String str = new String(chrsPassword);
             
        
        if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedPassword(str))
        {
            MySystem.s_printOutWarning(this, strMethod, "failed");
             
            String strBody = "Password value not allowed:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += str;
            strBody += "\"";
                    
            strBody += "\n\n";
            strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRulePassword();
                    
            OPAbstract.s_showDialogWarning(super._frmOwner_, super._strTitleAppli_, strBody);
            return false;
        }
        
        // ending
        return true;
    }
}