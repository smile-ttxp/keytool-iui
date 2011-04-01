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
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
// ----


import java.awt.*;
import java.io.*;


final public class KTLKprOpenManChgAlias extends KTLKprOpenManAbs
{
    // ------
    // PUBLIC
    
    public String getAliasNew() { return this._strAliasNew; } 
    
    
    /**
        giving an alias,
        change it
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
              
                strBody);
                
            return false;
        }
        
        try
        {
            // --
            // if new alias already exists, ask for overwrite
            if (super._kstOpen_.containsAlias(strAliasNew))
            {
                
                Toolkit.getDefaultToolkit().beep();
        
        	            
        	            
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
                    super._frmOwner_, strWarningBody))
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
             
                strBody);
                
            return false;
        }
        
        if (key == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil key");
            
            String strBody = "No valid key found in private key entry!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
              
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
               
                strBody);
                
            return false;
        }
        
        if (crtsChain == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsChain");
            
            String strBody = "No certificates chain found in private key entry!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
          
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
              
                strBody);
                
            return false;
        }
        
        // --
        // delete old entry
        
        try
        {
            super._kstOpen_.deleteEntry(super._strAlias_);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            
            String strBody = "Got keystore exception!";
            
            OPAbstract.s_showDialogWarning(
                super._frmOwner_, 
               
                strBody);
                
            return false;
        }
        
        // get keystore file
        
        // memo: JKS keystore should be of type "JKS", provided by "SUN"
        File fleOpenKstJks = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKst_);
        
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
        
        this._strAliasNew = strAliasNew;
        
        
        // --
        return true;
    }

    
    public KTLKprOpenManChgAlias(
        Frame frmOwner, 

        
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
        
        String strDialogTitle = System.getProperty("_appli.title") + " - rename alias";
        
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
                    
            OPAbstract.s_showDialogWarning(super._frmOwner_, strBody);
            return false;
        }
        
        
        // ending
        return true;
    }
}