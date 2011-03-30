package com.google.code.p.keytooliui.ktl.util.jarsigner;

import java.awt.Frame;
import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Date;
import com.google.code.p.keytooliui.ktl.swing.dialog.DTblEntryKprOpenKPAny;
import com.google.code.p.keytooliui.ktl.swing.dialog.DTblsKstSelPKOpen;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import com.google.code.p.keytooliui.shared.util.jarsigner.UtilJsrFile;


abstract public class KTLKprOpenSignKPAbs extends KTLKprOpenSignAbs
{
    // ------------------
    // abstract protected
    
    abstract protected KeyStore _getKeystoreOpen_(File fleOpenKst);
    
    // ------
    // public
    
     /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . !! if no entry of type "RSA-self-signed" in keystore, show warning dialog, then return false
        . fill in table keypair
        . show dialog keypair select RSA self
        
        . ????
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        // assign wait cursor till dialog window construction
        super._setEnabledCursorWait_(true);
        
        // ---
        // get file keystore
        
        

        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        
        // ----
        
        if (super._strPathAbsOpenJarSource_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsOpenJarSource_");
        }
        
        File fleOpenJarUnsigned = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsOpenJarSource_);
        
        if (fleOpenJarUnsigned == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenJarUnsigned");
            return false;
        }
        
        
        // ----
        
        if (super._strPathAbsSaveJarTarget_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsSaveJarTarget_");
        }
        
        File fleSaveJarSigned = UtilJsrFile.s_getFileSave(
            super._frmOwner_, super._strTitleAppli_, super._strPathAbsSaveJarTarget_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveJarSigned == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveJarSigned");
            return false;
        }
                
        // ----
        // open keystore
        
        if (super._chrsPasswdKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        }

        KeyStore kstOpen = _getKeystoreOpen_(fleOpenKst); // call to subclass        
        
        if (kstOpen == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil kstOpen"); // could be wrong password
            return false;
        }
          
        // ----
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpen);
        
        if (strsAliasPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
            super._strTitleAppli_,
            kstOpen);
        
        if (strsAliasSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        // --
        // get arrays for dialogTableSelectKeypair
        // TC versus PK
        Boolean[] boosIsTCEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
            super._strTitleAppli_, kstOpen, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }

        
        DTblsKstSelPKOpen dlg = new DTblsKstSelPKOpen(
            super._frmOwner_, 
            super._strTitleAppli_,
            kstOpen,
            super._strPathAbsKst_,
            "Sign JAR file with private key entry"
            );
        
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
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
            dtesLastModifiedSK
                ))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        } 
        
        
        
        
        dlg.setVisible(true);
        
        
        // ---
        char[] chrsPasswdKpr = dlg.getPassword();
        
        if (chrsPasswdKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKpr, aborted by user");
            return false;
        }
        
        
        String strAliasKpr = dlg.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }
        
        
        // ----
        // x) get privateKey
        
        
        PrivateKey keyPrivateKpr = null;
        
        
        try
        {
            keyPrivateKpr = (PrivateKey) UtilKstAbs.s_getKey(
                super._frmOwner_, 
                super._strTitleAppli_,
                kstOpen,
                strAliasKpr,
                chrsPasswdKpr);
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excClassCast caught");
        }

        if (keyPrivateKpr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil keyPrivateKpr");
            return false;
        }  
        
        // ----
        // x) get X509Certificates, done in superclass
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(
            fleOpenJarUnsigned,
            fleSaveJarSigned,
            kstOpen,
            strAliasKpr,
            keyPrivateKpr))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);

        // ending
        return true;
    }
    
    // ---------
    // protected
    
    protected KTLKprOpenSignKPAbs(
        Frame frmOwner,
        String strTitleAppli, 
        String strPathAbsOpenKst, 
        char[] chrsPasswdOpenKst,
        
        String strProviderKst,
        
        String strPathAbsOpenJarSource,
        String strPathAbsSaveJarTarget,
        String strNameBaseSigFile
        )
    {
        super(frmOwner, strTitleAppli, strPathAbsOpenKst, chrsPasswdOpenKst, strProviderKst,
                strPathAbsOpenJarSource, strPathAbsSaveJarTarget, strNameBaseSigFile);
    }
}
