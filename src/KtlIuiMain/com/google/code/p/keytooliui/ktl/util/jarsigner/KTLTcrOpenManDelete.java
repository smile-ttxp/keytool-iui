package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Tcr" for "Trusted certificate"
  

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
import java.security.cert.Certificate;
// ----


import java.awt.*;
import java.io.*;


final public class KTLTcrOpenManDelete extends KTLTcrOpenManAbs
{
    // ------
    // PUBLIC
    
    
    
    /**
        giving an alias,
        delete the respective trusted certificate entry
        
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
        	            
        	            
        String strWarningBody = "Are you sure you want to delete trusted certificate entry aliases ";

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
        // delete trusted certificate entry
        
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

    
    public KTLTcrOpenManDelete(
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