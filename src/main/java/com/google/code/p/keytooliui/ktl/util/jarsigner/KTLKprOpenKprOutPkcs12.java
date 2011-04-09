package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "keypair"
    

**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;


// ----
import java.security.KeyStore;

// --
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public final class KTLKprOpenKprOutPkcs12 extends KTLKprOpenKprOutAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprOutPkcs12(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Pkcs12 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileSaveKpr, // private key to save
        String strPathAbsFileSaveCrts,
        String strFormatFileKpr,
        String strFormatFileCrts
        )
    {
        super(
            frmOwner, 
         
        
            // input
            strPathAbsOpenKst, // existing keystore of type Pkcs12 
            chrsPasswdOpenKst,
        
            strPathAbsFileSaveKpr, // private key to save
            strPathAbsFileSaveCrts,
            strFormatFileKpr, 
            strFormatFileCrts,
            KTLAbs.f_s_strProviderKstPkcs12
            );
            
    }
    
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, 
          
            fleOpen,
            super._chrsPasswdKst_);
    }

    protected boolean _doJobSelectKpr_(
            File fleSaveKpr, 
            File fleSaveCrts, KeyStore kstOpen, String[] strsAliasPKTC, 
            Boolean[] boosIsTCEntryPKTC, Boolean[] boosValidDatePKTC, 
            Boolean[] boosSelfSignedCertPKTC, Boolean[] boosTrustedCertPKTC, 
            String[] strsSizeKeyPublPKTC, String[] strsTypeCertPKTC, 
            String[] strsAlgoSigCertPKTC, Date[] dtesLastModifiedPKTC, String[] strsAliasSK, Date[] dtesLastModifiedSK) 
    
    
    {
        String strMethod = "_doJobSelectKpr_(...)";

        DTblsKstSelPKOpenNoPass dlg = new DTblsKstSelPKOpenNoPass(
            super._frmOwner_, 
    
            kstOpen,
            super._strPathAbsKst_,
            "Export private key entry as private key and certificates chain files"
            );
        
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
            dtesLastModifiedSK
                ))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlg.setVisible(true);
        
        // ----
     
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
        
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleSaveKpr, fleSaveCrts))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        return true;
    }
}
