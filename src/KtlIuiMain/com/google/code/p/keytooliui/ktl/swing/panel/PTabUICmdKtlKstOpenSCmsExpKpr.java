package com.google.code.p.keytooliui.ktl.swing.panel;

/*
 *"SCms" : "Signature Cryptographic Message Syntax"
*/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

final public class PTabUICmdKtlKstOpenSCmsExpKpr extends PTabUICmdKtlKstOpenSCmsExpAbs
{
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Sign data file with private key entry & save as CMS detached signature file";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    //static private String _s_strDlgInfoActionBodyBeg = null;
    //static private String _s_strDlgInfoActionBodySCms = null;
    //static private String _s_strDlgInfoActionBodyQuery = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenSCmsExpKpr";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenSCmsExpKpr" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              */
            _s_strHelpID = "_cms_sign_det_pke_"; // TEMPO rbeResources.getString("helpID");
            //_s_strDlgInfoActionBodyBeg = "dlgInfoActionBodyBeg"; // TEMPO rbeResources.getString("dlgInfoActionBodyBeg");
            //_s_strDlgInfoActionBodySCms = "dlgInfoActionBodySCms"; // TEMPO rbeResources.getString("dlgInfoActionBodySCms");
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
        
        String strFormatFileSCms = ((PSelBtnTfdFileSaveSCms) super._pnlSelectFileSCms_).getSelectedFormatFile(); 
        
        boolean blnEnvelopedData = false;
        
        if (strFormatFileSCms.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_strFormatFileSCmsP7m.toLowerCase()) == 0)
            blnEnvelopedData = true;
        
        char[] chrsPasswdKst = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKst = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKst = "".toCharArray();
        
        String strFormatFileCrt = ((PSelBtnTfdFileSaveCCms) super._pnlSelectFileCrt_).getSelectedFormatFile(); 
        
        KTLKprOpenSigDetOCmsAbs ktl = null;
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOCmsPkcs12(
                super._frmOwner_, 
            
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSCms_, // mandatory
                super._strPathAbsFileCrt_, // optional
                
                blnEnvelopedData
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOCmsBks(
                super._frmOwner_, 
         
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSCms_, // mandatory
                super._strPathAbsFileCrt_, // optional
                blnEnvelopedData
            );
       
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenSigDetOCmsUber(
                super._frmOwner_, 
         
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                
                // I/O
                super._strPathAbsFileSCms_, // mandatory
                super._strPathAbsFileCrt_, // optional
                
                blnEnvelopedData
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
    
    public PTabUICmdKtlKstOpenSCmsExpKpr(Frame frmOwner)
    {
        super(
            frmOwner, 
   
            PTabUICmdKtlKstOpenSCmsExpKpr._s_strHelpID
            );
    }

    // -------
    // PRIVATE
}
