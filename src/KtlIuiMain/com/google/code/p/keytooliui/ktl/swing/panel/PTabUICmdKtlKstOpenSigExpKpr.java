package com.google.code.p.keytooliui.ktl.swing.panel;



import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

final public class PTabUICmdKtlKstOpenSigExpKpr extends PTabUICmdKtlKstOpenSigExpAbs
{
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Sign data file with private key entry & save as other detached signature file";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    //static private String _s_strDlgInfoActionBodyBeg = null;
    //static private String _s_strDlgInfoActionBodySig = null;
    //static private String _s_strDlgInfoActionBodyQuery = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenSigExpKpr";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenSigExpKpr" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              */
            _s_strHelpID = "_sign_det_pke_"; // TEMPO rbeResources.getString("helpID");
            //_s_strDlgInfoActionBodyBeg = "dlgInfoActionBodyBeg"; // TEMPO rbeResources.getString("dlgInfoActionBodyBeg");
            //_s_strDlgInfoActionBodySig = "dlgInfoActionBodySig"; // TEMPO rbeResources.getString("dlgInfoActionBodySig");
            //_s_strDlgInfoActionBodyQuery = "dlgInfoActionBodyQuery"; // TEMPO rbeResources.getString("dlgInfoActionBodyQuery");
        /*}
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }*/
    }
    
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        String strFormatKst = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();
        
        if (strFormatKst == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKst");
        
        String strFormatFileSig = ((PSelBtnTfdFileSaveSig) super._pnlSelectFileSig_).getSelectedFormatFile(); 
        
        char[] chrsPasswdKst = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKst = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKst = "".toCharArray();
        
        String strFormatFileCrt = ((PSelBtnTfdFileSaveCrt) super._pnlSelectFileCrt_).getSelectedFormatFile(); 
        
        KTLKprOpenSigDetOutAbs ktl = null;
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOutJks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSig_, // mandatory
                super._strPathAbsFileCrt_, // optional
                
                strFormatFileSig, // mandatory
                strFormatFileCrt // optional
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOutJceks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSig_, // mandatory
                super._strPathAbsFileCrt_, // optional
                
                strFormatFileSig, // mandatory
                strFormatFileCrt // optional
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOutPkcs12(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSig_, // mandatory
                super._strPathAbsFileCrt_, // optional
                
                strFormatFileSig, // mandatory
                strFormatFileCrt // optional
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOutBks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSig_, // mandatory
                super._strPathAbsFileCrt_, // optional
                
                strFormatFileSig, // mandatory
                strFormatFileCrt // optional
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOutUber(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSig_, // mandatory
                super._strPathAbsFileCrt_, // optional
                
                strFormatFileSig, // mandatory
                strFormatFileCrt // optional
            );
        }
        
       
        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }
        
        // --
        
        if (ktl.doJob())
            MySystem.s_printOutTrace(this, strMethod, "OK!");     
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
       
    }
    
    public PTabUICmdKtlKstOpenSigExpKpr(Frame frmOwner, String strTitleAppli)
    {
        super(
            frmOwner, 
            strTitleAppli,
            PTabUICmdKtlKstOpenSigExpKpr._s_strHelpID
            );
    }

    // -------
    // PRIVATE
}