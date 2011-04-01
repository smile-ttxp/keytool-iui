package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**


    known subclasses:
    . KTLCcaSaveNewDsaAbs
    
**/



import com.google.code.p.keytooliui.shared.lang.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;




// ----
// memo: assigning full class path coz ambiguous: same class name in several Java packages

import java.security.KeyStore;
import java.security.KeyStoreException;

// --
import java.security.cert.X509Certificate;

// ----

import java.awt.*;
import java.io.*;



abstract public class KTLCcaSaveNewAbs extends KTLTcrSaveAbs
{
    // ------
    // public
    
    /*public boolean doJob()
    {
        String strMethod = "doJob()";
        
        if (this._blnTrustCACerts)
        {
            if (! _loadKstCcaSys())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        return true;
    }*/
    
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsCrt_;
    protected String _strFormatFileCrt_;  
        
    protected KTLCcaSaveNewAbs(
        Frame frmOwner, 

        
        // output
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // output
        String strProviderKst,   // eg: "SUN", "BC"
        
        // input
        String strPathAbsCrt,
        String strFormatFileCrt
        )
    {
        super(frmOwner, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        // input
        this._strPathAbsCrt_ = strPathAbsCrt;
        this._strFormatFileCrt_ = strFormatFileCrt;
        //this._blnTrustCACerts = blnTrustCACerts;
    }
    
    // memo: only one cert in chain allowed!
    
    protected X509Certificate _getCcaNew_(KeyStore kstOpen)
    {
        String strMethod = "_getCcaNew_(kstOpen)";
        
        
        File fleOpenCrt = new File(this._strPathAbsCrt_);
        
        if (! fleOpenCrt.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fleOpenCrt.exists(), this._strPathAbsCrt_=" + 
                    this._strPathAbsCrt_);
            
            String strBody = "File not found:";
            
            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;
            
                
            OPAbstract.s_showDialogError(
                super._frmOwner_, strBody);
            
            return null;
        }
        
        if (! fleOpenCrt.canRead())
        {
            MySystem.s_printOutError(this, strMethod, "! fleOpenCrt.canRead(), this._strPathAbsCrt_=" + 
                    this._strPathAbsCrt_);
            
            String strBody = "Cannot read file:";
            
            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;
            
            OPAbstract.s_showDialogError(
                super._frmOwner_, strBody);
            
            return null;
        }
    
        // memo: this one takes care of DER-PKCS7-PEM crt file
        X509Certificate[] crtsX509ToImport = UtilCrtX509.s_load(
            super._frmOwner_, 
            fleOpenCrt);
        
        if (crtsX509ToImport == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil crtsX509ToImport");
           
            String strBody = "Failed to load cert file:";
            
            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;
            
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
                        
            return null;
        }
        
        
        if (crtsX509ToImport.length == 0)
        {
            MySystem.s_printOutError(this, strMethod, "crtsX509ToImport.length == 0");
            
            String strBody = "No certificate found in file:";
            
            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;
            
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
            
            return null;
        }
        
        else if (crtsX509ToImport.length > 1)
        {
            MySystem.s_printOutError(this, strMethod, "crtsX509ToImport.length > 1");
            
            String strBody = "Found " + crtsX509ToImport.length + " certificates in file:";
            
            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;
            
            strBody += "\n\n";
              strBody += "Action not allowed: attempting to store more than one certificate in root CA certs store.";
            strBody += "\n\n";
            strBody += "MEMO: Only certificate file with one self-signed certificate in certs list allowed as trusted certificate entry!";
            strBody += "\n";
            strBody += "in root CA certs keystore";
            
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
            
            return null;
        }
        
        X509Certificate crtX509 = crtsX509ToImport[0];
        
	    
        if (! UtilCrt.s_isSelfSigned(crtX509))
        {
            // memo, rule: root CA certs store: cert candidate should be self-signed!
            MySystem.s_printOutError(this, strMethod, "! UtilCrt.s_isSelfSigned(crtX509)");
            
            String strBody = "Certificate is not self-signed";
            
            strBody += "\n\n";
            
            strBody += "Action not allowed: attempting to store not self-signed certificate in root CA certs store.";
            
            strBody += "\n\n";
            strBody += "Certificate file path:";
            strBody += "  " + this._strPathAbsCrt_;
            
            strBody += "\n\n";
            strBody += "MEMO: Only certificate file with one self-signed certificate in certs list allowed as trusted certificate entry!";
            strBody += "\n";
            strBody += "in root CA certs keystore";
            
            OPAbstract.s_showDialogError(
                super._frmOwner_, strBody);
            
            return null;
            
        }
   
        try
        {
            crtX509.verify(crtX509.getPublicKey());
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");

            String strBody = "Failed to verify self-signed certificate in file:";

            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;

            strBody += "\n\n";
            strBody += "More in session.log file.";

            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);

            return null;
        }


        
        
        // ----
        
        // check if crtX509 already exists in keystore as tcr
        //String reply = null;
        String strAliasCcaCheck = null;

        try
        {
            strAliasCcaCheck = kstOpen.getCertificateAlias(crtX509);
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");

            String strBody = "Got exception. Failed to open root CA certs keystore:";

            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;


            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);

            return null;
        }


        if (strAliasCcaCheck != null) // if crtX509 already in keystore 
        {
            MySystem.s_printOutWarning(this, strMethod, "tcr already exists in root CA certs keystore as " + strAliasCcaCheck);

            Toolkit.getDefaultToolkit().beep();

            /*String strWarningTitle = System.getProperty("_appli.title");
            strWarningTitle += " - ";
            strWarningTitle += "warning";
            strWarningTitle += " - ";
            strWarningTitle += "confirm";*/


            String strWarningBody = "Trusted certificate entry already exists in root CA certs keystore, under alias ";

            strWarningBody += "\"";
            strWarningBody += strAliasCcaCheck;
            strWarningBody += "\"";

            strWarningBody += "\n";
            strWarningBody += "\n";
            strWarningBody += "Do you still want to install this certificate?";

            if (! OPAbstract.s_showWarningConfirmDialog(
                super._frmOwner_, 
                //strWarningTitle,
                strWarningBody))
            {
                MySystem.s_printOutTrace(this, strMethod, "action cancelled");
                return null;
            } 
        } 


        if (strAliasCcaCheck == null)
        {
            // first show info dialog: blahblah
            String strBody = "First self-signed trusted certificate candidate will show up.";
            strBody += "\n";
            strBody += "Then you will be asked to trust this certificate";


            OPAbstract.s_showDialogInfo(
            super._frmOwner_, strBody);

            // next show this certificate
            UtilCrtX509.s_show(super._frmOwner_, crtX509);

            // then show question-confirm dialog
            //String strTitleConfirm = System.getProperty("_appli.title") + " - " + "confirm";


            String strBodyConfirm = "Trust this certificate?";
            strBodyConfirm += "\n\n";

            if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
                super._frmOwner_, 
                //strTitleConfirm,
                strBodyConfirm))
            {
                MySystem.s_printOutTrace(this, strMethod, "action cancelled");
                return null;
            }
        }  // if (strAliasCcaCheck == null)


        
            
        return crtX509;
    }
    
    
    
    /**
            NOT FOR PKCS12 kesytore
            
        MEMO: if alias already existing in keystore
        will be overwritten!!
        ... BUT already tested in calling code ==> never be the case
        
        if any code error, exiting
        else if any other error, show warning dialog, then return false;
        else return true
    **/
    protected boolean _assignNewEntry2OpenKeystore_(
        KeyStore kstOpen,
        X509Certificate tcrNew,
        String strAliasCca
        )
    {
        String strMethod = "_assignNewEntry2OpenKeystore_(...)";
        
        
        if (kstOpen==null || tcrNew==null || strAliasCca==null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }
 
        try
        {
            boolean blnAlreadyListed = kstOpen.containsAlias(strAliasCca);
            
            if (blnAlreadyListed) // should never  appear, as it has been checked in the calling code.
            {
                MySystem.s_printOutWarning(this, strMethod, "blnAlreadyListed, will be overwritten");
                
            }
            
            kstOpen.setCertificateEntry(strAliasCca, tcrNew);
        }
        
        catch(KeyStoreException excKeyStore)
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excKeyStore caught");
            MySystem.s_printOutError(this, strMethod, "!!!! TODO SHOW ERROR DIALOG !!!!");
            return false;
        }
        
        
        return true;
    }
    
   
}
