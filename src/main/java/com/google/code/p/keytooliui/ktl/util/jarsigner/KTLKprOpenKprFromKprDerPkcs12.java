package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Kpr" for "private key"
**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;


// ----
import java.security.KeyStore;

import java.awt.*;
import java.io.*;
import java.util.*;

public final class KTLKprOpenKprFromKprDerPkcs12 extends KTLKprOpenKprFromKprDerAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenKprFromKprDerPkcs12(
        Frame frmOwner, 
    
        
        // input
        String strPathAbsOpenKst, // existing keystore of type Pkcs12 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenKpr,
        String strPathAbsFileOpenCrts,
        String strAlgoKey
        )
    {
        super(
            frmOwner, 
          
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
        
            strPathAbsFileOpenKpr, // private key to import
            strPathAbsFileOpenCrts,
           
            strAlgoKey,
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
        File fleOpenKpr,
        File fleOpenCrts,
        KeyStore kstOpen,
            
        // NEW
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
        String strMethod = "_doJobSelectKpr_(...)";
        
        DTblsKstViewKeySavePKNoPass dlg = new DTblsKstViewKeySavePKNoPass(
            super._frmOwner_, 
      
            kstOpen,
           super._strPathAbsKst_,
            "Import private key and associated certificates chain files as new private key entry"
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
            dtesLastModifiedSK))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlg.setVisible(true);
        
        // ----
     
        char[] chrsPasswdKpr = dlg.getPassword(); // should return new char[0]
        
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
        
        if (! super._doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleOpenKpr, fleOpenCrts))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        return true;
    }
}
