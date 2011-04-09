package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**


    known subclasses:
    . KTLTcrSaveNewDsaAbs
    
**/


import com.google.code.p.keytooliui.shared.lang.*;

import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
// memo: assigning full class path coz ambiguous: same class name in several Java packages

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;

// ----

import java.awt.*;
import java.io.*;
import java.util.*;



public abstract class KTLTcrSaveNewAbs extends KTLTcrSaveAbs
{
    // ------
    // public
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        if (this._blnTrustCACerts)
        {
            if (! _loadKstTcrSys())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsCrt_;
    protected String _strFormatFileCrt_;  
        
    protected KTLTcrSaveNewAbs(
        Frame frmOwner, 
       
        
        // output
        String strPathAbsOpenKst, // existing keystore of type [JKS-JCEKS] 
        char[] chrsPasswdOpenKst, 
        
        // output
        String strProviderKst,   // eg: "SUN", "BC"
        
        // input
        String strPathAbsCrt,
        String strFormatFileCrt,
        boolean blnTrustCACerts
        
        )
    {
        super(frmOwner,strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst);
        
        // input
        this._strPathAbsCrt_ = strPathAbsCrt;
        this._strFormatFileCrt_ = strFormatFileCrt;
        this._blnTrustCACerts = blnTrustCACerts;
    }
    
    private X509Certificate _getCertificate(File fleOpenCrt)
    {
        String strMethod = "_getCertificate(kstOpen)";
        
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
                super._frmOwner_, strBody);
                        
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
            strBody += "Only certificate file with one certificate in certs list allowed as trusted certificate entry!";
            
            OPAbstract.s_showDialogError(
                super._frmOwner_, strBody);
            
            return null;
        }
        
        return crtsX509ToImport[0];
    }
    
    private File _getFileTcrCandidate(KeyStore kstOpenTarget)
    {
        String strMethod = "_getFileTcrCandidate(kstOpen)";
        
        File fleOpenCrt = new File(this._strPathAbsCrt_);
        
        if (! fleOpenCrt.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fleOpenCrt.exists(), this._strPathAbsCrt_=" + 
                    this._strPathAbsCrt_);
            
            String strBody = "File not found:";
            
            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;
            
                
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
            
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
                super._frmOwner_,  strBody);
            
            return null;
        }
        
        return fleOpenCrt;
    }
    
    private boolean _checkNotSelfSignedWithRootCaCerts(X509Certificate crtX509)
    {
        String strMethod = "_checkNotSelfSignedWithRootCaCerts(crtX509)";
        
        if (this._kstTcrSys == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._kstTcrSys");
            
            String strBody = "Failed to get root CA certs store!";
            
            OPAbstract.s_showDialogError(
                super._frmOwner_, strBody);
            
            return false;
        }
        
        // TODO: check for issuer in root CA certs,
        // if so, show message dialog
        MySystem.s_printOutWarning(this, strMethod, "TODO: check for issuer in root CA certs, if so, show message dialog");
        
        if (this._kstTcrSys == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._kstTcrSys");
            
            String strBody = "Failed to get root CA certs store!";
            
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
            
            return false;
        }
        
        
        try
        {
            // beg test
            java.security.Principal prpIssuerCandidate = crtX509.getIssuerDN();
            String strNameIssuerCandidateLC = prpIssuerCandidate.getName().toLowerCase();
            
            for (Enumeration<String> e = this._kstTcrSys.aliases(); e.hasMoreElements();)
            { 
                String strCur = e.nextElement(); 

                X509Certificate crtCur = (X509Certificate) this._kstTcrSys.getCertificate(strCur);

                java.security.Principal prpIssuerCur = crtCur.getIssuerDN();

                String strNameIssuerCurLC = prpIssuerCur.getName().toLowerCase();

                if (strNameIssuerCurLC.compareTo(strNameIssuerCandidateLC) != 0)
                    continue;
                   
                // got it
                // show message
                MySystem.s_printOutTrace(this, strMethod, "got it!");
                
                String strBody = "Certificate issuer is listed in root CA certificates store.";
                
                strBody += "\n\n";
                strBody += "Issuer name:";
                strBody += "\n  ";
                strBody += prpIssuerCur.getName();
  
                OPAbstract.s_showDialogInfo(
                    super._frmOwner_, strBody);
                
                // ending
                return true;
            } 
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");

            String strBody = "Got exception. Failed to read system-level root CA certificate store";
            
            strBody += "\n\n";
            
            strBody += exc.getMessage();

            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);

            return false;
        } 
        
        return true;
    }
    
    private boolean _checkSelfSignedWithRootCaCerts(X509Certificate crtX509)
    {
        String strMethod = "_checkSelfSignedWithRootCaCerts(crtX509)";
        
        if (this._kstTcrSys == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._kstTcrSys");
            
            String strBody = "Failed to get root CA certs store!";
            
            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);
            
            return false;
        }
        
        String strAliasTcrCheck = null;
        
        try
        {
            strAliasTcrCheck = this._kstTcrSys.getCertificateAlias(crtX509);
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");

            String strBody = "Got exception. Failed to read system-level root CA certificate store";
            
            strBody += "\n\n";
            
            strBody += exc.getMessage();

            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);

            return false;
        } 

        if (strAliasTcrCheck == null)
            return true;
        
    
        // show warning-confirm dialog

        MySystem.s_printOutWarning(this, strMethod, "tcr already exists in this._kstTcrSys as " + strAliasTcrCheck);

        Toolkit.getDefaultToolkit().beep();

        String strWarningBody = "Trusted certificate already exists in";
        strWarningBody += "\n";
        strWarningBody += "System-level root CA certificate store, under alias ";

        strWarningBody += "\"";
        strWarningBody += strAliasTcrCheck;
        strWarningBody += "\"";

        strWarningBody += "\n";
        strWarningBody += "\n";
        strWarningBody += "Do you still want to install this certificate in your keystore?";

        if (! OPAbstract.s_showWarningConfirmDialog(super._frmOwner_, strWarningBody))
        {
            MySystem.s_printOutTrace(this, strMethod, "action cancelled");
            return false;
        } 
        
        
        return true;
    }
    
    private boolean _checkSelfSigned(X509Certificate crtX509)
    {
        String strMethod = "_checkSelfSigned(crtX509)";
        
        // API: Verifies that this certificate was signed using the private key 
        //      that corresponds to the specified public key.
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

            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);

            return false;
        }
        
        if (! this._blnTrustCACerts)
            return true;
        
        if (! _checkSelfSignedWithRootCaCerts(crtX509))
        {
            // action could be cancelled by user
            MySystem.s_printOutWarning(this, strMethod, "! _checkSelfSignedWithRootCaCerts(crtX509)");
            return false;
        }
        
        return true;
    }
    
    private boolean _checkNotSelfSigned(X509Certificate crtX509)
    {
        String strMethod = "_checkNotSelfSigned(crtX509)";
        
        if (! this._blnTrustCACerts)
            return true;
        
        if (! _checkNotSelfSignedWithRootCaCerts(crtX509))
        {
            // action could be cancelled by user
            MySystem.s_printOutWarning(this, strMethod, "! _checkNotSelfSignedWithRootCaCerts(crtX509)");
            return false;
        }
        
        
        return true;
    }
    
    // check for existing cert in same keystore with different alias
    private boolean _checkForExistingCert(KeyStore kstOpen, X509Certificate crtX509) throws
            Exception
    {
        String strMethod = "_checkForExistingCert(crtX509)";
        
        String strAliasTcrCheck = kstOpen.getCertificateAlias(crtX509);
        
        if (strAliasTcrCheck == null)
            return true;
        
        // if crtX509 already in keystore 
        
        MySystem.s_printOutWarning(this, strMethod, "tcr already exists in keystore as " + strAliasTcrCheck);

        Toolkit.getDefaultToolkit().beep();

        String strWarningBody = "Trusted certificate entry already exists in keystore, under alias ";

        strWarningBody += "\"";
        strWarningBody += strAliasTcrCheck;
        strWarningBody += "\"";

        strWarningBody += "\n";
        strWarningBody += "\n";
        strWarningBody += "Do you still want to install this certificate?";

        if (! OPAbstract.s_showWarningConfirmDialog(
            super._frmOwner_, strWarningBody))
        {
            MySystem.s_printOutTrace(this, strMethod, "action cancelled");
            return false;
        } 
       
        return true;
    }
    
    private boolean _grantThisCert(X509Certificate crtX509)
    {
        String strMethod = "_grantThisCert(crtX509)";
        
        // first show info dialog: blahblah
        String strBody = "First contents of trusted certificate candidate will show up.";
        strBody += "\n";
        strBody += "Then you will be asked to trust this certificate";


        OPAbstract.s_showDialogInfo(
            super._frmOwner_,  strBody);

        // next show this certificate
        UtilCrtX509.s_show(super._frmOwner_,  crtX509);

       
        String strBodyConfirm = "Do you trust this certificate?";
        strBodyConfirm += "\n\n";

        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strBodyConfirm))
        {
            MySystem.s_printOutTrace(this, strMethod, "action cancelled");
            return false;
        }
                
        
        return true;
    }
    
    // memo: only one cert in chain allowed!
    
    protected X509Certificate _getTcrNew_(KeyStore kstOpen)
    {
        String strMethod = "_getTcrNew_(kstOpen)";
        
        
        File fleOpenCrt = _getFileTcrCandidate(kstOpen);
        
        if (fleOpenCrt == null)
        {
            MySystem.s_printOutError(this, strMethod, "fleOpenCrt == null");
            return null;
        }
        
        
        X509Certificate crtX509 = _getCertificate(fleOpenCrt);
        
        if (crtX509 == null)
        {
            MySystem.s_printOutError(this, strMethod, "crtX509 == null");
            return null;
        }
        
        try
        {
            if (! _checkForExistingCert(kstOpen, crtX509))
            {
                MySystem.s_printOutError(this, strMethod, "action cancelled by user");
                return null;
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");

            String strBody = "Got exception. Failed to open keystore:";

            strBody += "\n\n";
            strBody += "  " + this._strPathAbsCrt_;
            
            strBody += "\n\n";
            strBody += exc.getMessage();


            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);

            return null;
        }
        
	
        if (UtilCrt.s_isSelfSigned(crtX509))
        {
            if (! _checkSelfSigned(crtX509))
            {
                // action could be cancelled by user
                MySystem.s_printOutWarning(this, strMethod, "! _checkSelfSigned()");
                return null;
            }
        }
        
        else
        {
            if (! _checkNotSelfSigned(crtX509))
            {
                MySystem.s_printOutError(this, strMethod, "! _checkNotSelfSigned()");
                return null;
            }
        }
        
        if (! _grantThisCert(crtX509))
        {
            MySystem.s_printOutTrace(this, strMethod, "! _grantThisCert(crtX509)");
            return null;
        }

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
        String strAliasTcr
        )
    {
        String strMethod = "_assignNewEntry2OpenKeystore_(...)";
        
        if (kstOpen==null || tcrNew==null || strAliasTcr==null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        }
        
        try
        {
            boolean blnAlreadyListed = kstOpen.containsAlias(strAliasTcr);
            
            if (blnAlreadyListed) // should never  appear, as it has been checked in the calling code.
            {
                MySystem.s_printOutWarning(this, strMethod, "blnAlreadyListed, will be overwritten");
                
            }
            
            kstOpen.setCertificateEntry(strAliasTcr, tcrNew);
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
    
    // private
    private boolean _blnTrustCACerts = false; // should be an option in UI
    private KeyStore _kstTcrSys = null;
    
    // should be as static in UtilKstJks!!!!!!!!!
    private boolean _loadKstTcrSys()
    {
        String strMethod = "_loadKstTcrSys()";
        
        File fleKstTcrSys = UtilKstJks.s_getFileKstCertsTrustCASys();
        
        if (fleKstTcrSys == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleKstTcrSys, fleKstTcrSys.getAbsolutePath()=" +
                fleKstTcrSys.getAbsolutePath());
                
            return false;
        }
        
        KeyStore kstTcrSys = null;
        
        
        try
        {
            FileInputStream fis = new FileInputStream(fleKstTcrSys);
	        kstTcrSys = KeyStore.getInstance("jks");
	        kstTcrSys.load(fis, null);
	        fis.close();
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");

            String strBody = "Got exception. Failed to load system-level root CA certificate store";

            OPAbstract.s_showDialogError(
                super._frmOwner_,  strBody);

            return false;
        }

        this._kstTcrSys = kstTcrSys;
        return true;
    }
}