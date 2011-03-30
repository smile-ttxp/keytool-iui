package com.google.code.p.keytooliui.ktl.swing.panel;

/**

**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

final public class PTabUICmdKtlKstOpenCrShkAll extends PTabUICmdKtlKstOpenCrShkAbs 
{        
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Create secret key (shared key) entry";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;

    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenCrShkAll";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenCrShkAll" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strHelpID = "_create_ske_"; // rbeResources.getString("helpID"); // tempo value: _versus_create_all_sk_
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
    }
    
    // ------
    // PUBLIC

    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        
        
        String strFormatKst = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();
        
        if (strFormatKst == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKst");
        
        
        String strInstanceKeyGenerator = (String) ((PSelCmbAbs) super._pnlSelectSigAlgo_).getSelectedItemCmb();
        
        char[] chrsPasswdKstTarget = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKstTarget = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKstTarget = "".toCharArray();
        
        KTLShkSaveNewAllAbs ktl = null;
        
        
        // !!!! statement should never bean reached coz no support in JKS for secret keys
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            
            ktl = new KTLShkSaveNewAllJks(
                super._frmOwner_, 
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
   
                strInstanceKeyGenerator
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            
            ktl = new KTLShkSaveNewAllJceks(
                super._frmOwner_, 
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
   
                strInstanceKeyGenerator
            );
        }
        
        // NOT SUPPORTED: PKCS12
        
        // statement should never be reached, not supported: secretKey with bc stuff
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkSaveNewAllPkcs12(
                super._frmOwner_, 
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
   
                strInstanceKeyGenerator
            );
            
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            
            ktl = new KTLShkSaveNewAllBks(
                super._frmOwner_, 
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
   
                strInstanceKeyGenerator
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkSaveNewAllUber(
                super._frmOwner_, 
                super._strTitleAppli_,
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // output
   
                strInstanceKeyGenerator
            );
           
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }
        
        // --
        
        if (ktl.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "OK!");
            
            super._doneJob_(
                strFormatKst,
                strInstanceKeyGenerator);
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
    }
    
    
    public PTabUICmdKtlKstOpenCrShkAll(Frame frmOwner, String strTitleAppli)
    {
        super(
            PTabUICmdKtlKstOpenCrShkAll._s_strHelpID, 
            frmOwner, 
            strTitleAppli,
            true, // blnAllowTypeBks
            true // blnAllowTypeUber
            );
        
        super._pnlSelectSigAlgo_ = new PSelCmbStrSigAlgoSKAll();
    }
    
    // -------
    // PRIVATE
    
}
