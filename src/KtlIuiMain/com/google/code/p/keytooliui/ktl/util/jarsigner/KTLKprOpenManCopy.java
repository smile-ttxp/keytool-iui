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

    "Kpr" for "keypair" (Private Key)
  

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


final public class KTLKprOpenManCopy extends KTLKprOpenManAbs
{
    // ------
    // PUBLIC
    
    public String getAliasNew() { return this._strAliasNew; } 
    
    
    /**
        giving an alias,
        copy the respective entry
        if an alias with the same name already exists, ask for overwrite
        
        the new alias may be obtained by called the public method "getAliasNew()";
        
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
                
    
        if (! super.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "failed");
            return false;
        }
            
        // ---
        // get new alias name
        
        String strAliasNew = _getAliasNew();
        
        if (strAliasNew == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasNew, user canceled");
            return false;
        }
        
        // if same name, abort
        if (this._strAlias_.compareTo(strAliasNew) == 0)
        {
            MySystem.s_printOutWarning(this, strMethod, "this._strAlias_.compareTo(strAliasNew) == 0");
            
            String strBody = "Same alias name:\n  " + strAliasNew;
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
                super._strTitleAppli_, 
                strBody);
                
            return false;
        }
        
        try
        {
            // --
            // if new alias already exists, ask for overwrite
            if (super._kstOpen_.containsAlias(strAliasNew))
            {
                
                com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
        	            
    	        String strWarningTitle = super._strTitleAppli_;
    	        strWarningTitle += " - ";
    	        strWarningTitle += "warning";
    	        strWarningTitle += " - ";
    	        strWarningTitle += "confirm";
        	            
        	            
	            String strWarningBody = "An alias with the same name ";
        	            
	            strWarningBody += "\"";
	            strWarningBody += strAliasNew;
	            strWarningBody += "\"";
    	        
	            strWarningBody += " already exists!";
	            strWarningBody += "\n";
	            strWarningBody += "\n";
    	                
	            strWarningBody += "\n";
	            strWarningBody += "\n";
	            strWarningBody += "Overwrite this entry?";

                if (! OPAbstract.s_showWarningConfirmDialog(
                    super._frmOwner_, strWarningTitle, strWarningBody))
                {
                    MySystem.s_printOutTrace(this, strMethod, "action cancelled");
                    return false;
                }    
            }
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
        
        
        
        // ---
        // get password for this alias
        char[] chrsPasswdKpr = super._getPasswordKpr_();
            
    
                
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, user canceled");
            return false;
        }
                

        
        // --
        // do job
        
        Key key = null;
        
        try
        {
            key = super._kstOpen_.getKey(super._strAlias_, chrsPasswdKpr);
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
            super._kstOpen_.setKeyEntry(strAliasNew, key, chrsPasswdKpr, crtsChain);
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
        
        
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            
            return false;
        }
        
        // ----
        // save keystore
        if (super._chrsPasswdKst_ == null) // rather exit
        {
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_");
        }        
        
        // ----
        
        if (! super._saveKeyStore_(super._kstOpen_, fleOpenKst, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
        this._strAliasNew = strAliasNew;
        
        
        // --
        return true;
    }

    
    public KTLKprOpenManCopy(
        Frame frmOwner, 
        String strTitleAppli,
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS-JCEKS-PKCS12-BKS-UBER 
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
    
    private String _strAliasNew = null;
    
    private String _getAliasNew()
    {
        String strMethod = "_getAliasNew()";
        
        String strDialogTitle = super._strTitleAppli_ + " - new alias";
        
        DSelectString dlg = new DSelectString(
            super._frmOwner_, strDialogTitle);
            
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
            
        dlg.setVisible(true);

        String str = dlg.getValidatedText();
        
        dlg.destroy();
        dlg = null;
        
        if (str == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil str, user canceled");
            return null;
        }
        
        if (! _isValidAlias(str))
        {
            MySystem.s_printOutWarning(this, strMethod, "! _isValidAlias(str)");
            return null;
        }
        
        return str;
    }
    
    private boolean _isValidAlias(String str)
    {
        String strMethod = "_isValidAlias(str)";
        
        if (str == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil str");
        }
                
        
        if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedAlias(str))
        {
            MySystem.s_printOutWarning(this, strMethod, "failed");
             
            String strBody = "Alias value not allowed:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += str;
            strBody += "\"";
                    
            strBody += "\n\n";
            strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRuleAlias();
                    
            OPAbstract.s_showDialogWarning(super._frmOwner_, super._strTitleAppli_, strBody);
            return false;
        }
        
        
        // ending
        return true;
    }
}