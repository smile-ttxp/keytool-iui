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


final public class KTLKprOpenManDelete extends KTLKprOpenManAbs
{
    // ------
    // PUBLIC
    
    
    
    /**
        giving an alias,
        delete the respective private key entry
        
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
        // confirm deleting
        
        Toolkit.getDefaultToolkit().beep();
        	            
    	String strWarningTitle = super._strTitleAppli_;
    	strWarningTitle += " - ";
    	strWarningTitle += "warning";
    	strWarningTitle += " - ";
    	strWarningTitle += "confirm";
        	            
        	            
	    String strWarningBody = "Are you sure you want to delete private key aliased ";
        	            
	    strWarningBody += "\"";
	    strWarningBody += super._strAlias_;
	    strWarningBody += "\"";
    	        
	    strWarningBody += "?";
	    strWarningBody += "\n";
	    strWarningBody += "\n";
    	                

	    strWarningBody += "Please confirm";

        if (! OPAbstract.s_showWarningConfirmDialog(
            super._frmOwner_, strWarningTitle, strWarningBody))
        {
            MySystem.s_printOutTrace(this, strMethod, "action cancelled");
            return false;
        }    
        
        
        // --
        // ---
        // get password for this alias - user enters password
        char[] chrsPasswdKpr = super._getPasswordKpr_();
            
    
                
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, user canceled");
            return false;
        }
        
        // check for valid password
        
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
        // delete private key entry
        
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

    
    public KTLKprOpenManDelete(
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
    
    
    
}