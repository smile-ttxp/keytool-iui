package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public final class PTabUICmdKtlKstOpenSigVerCrt extends PTabUICmdKtlKstOpenSigVerAbs
{
    // ---------------------------
    // private static final String
    
    public static final String STR_TITLETASK = "Verify signed data file with trusted certificate entry & other detached signature file";
    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strHelpID = null;
    
    private static String _s_strDlgInfoActionBodyBeg = null;
    private static String _s_strDlgInfoActionBodySig = null;
    private static String _s_strDlgInfoActionBodyQuery = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenSigVerCrt";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenSigVerCrt" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              */
            _s_strHelpID = "_verify_det_tce_"; // TEMPO rbeResources.getString("helpID");
            _s_strDlgInfoActionBodyBeg = "dlgInfoActionBodyBeg"; // TEMPO rbeResources.getString("dlgInfoActionBodyBeg");
            _s_strDlgInfoActionBodySig = "dlgInfoActionBodySig"; // TEMPO rbeResources.getString("dlgInfoActionBodySig");
            _s_strDlgInfoActionBodyQuery = "dlgInfoActionBodyQuery"; // TEMPO rbeResources.getString("dlgInfoActionBodyQuery");
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
        
        String strFormatFileSig = ((PSelBtnTfdFileOpenSig) super._pnlSelectFileSig_).getSelectedFormatFile(); 
        
        char[] chrsPasswdKst = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKst = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKst = "".toCharArray();
        
        
        //MySystem.s_printOutTrace(this, strMethod, "IN PROGRESS!");
        
        
        
        KTLCrtOpenSigVerAbs ktl = null;
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLCrtOpenSigVerJks(
                super._frmOwner_, 
            
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                super._strPathAbsFileSig_,
                
                strFormatFileSig
            );
        }
        
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLCrtOpenSigVerJceks(
                super._frmOwner_, 
            
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                super._strPathAbsFileSig_,
                
                strFormatFileSig
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLCrtOpenSigVerPkcs12(
                super._frmOwner_, 
              
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                super._strPathAbsFileSig_,
                
                strFormatFileSig
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLCrtOpenSigVerBks(
                super._frmOwner_, 
            
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                super._strPathAbsFileSig_,
                
                strFormatFileSig
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLCrtOpenSigVerUber(
                super._frmOwner_, 
          
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                super._strPathAbsFileData_,
                super._strPathAbsFileSig_,
                
                strFormatFileSig
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
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
       
    }
    
    
    
    public PTabUICmdKtlKstOpenSigVerCrt(Frame frmOwner)
    {
        super(
            frmOwner, 
    
            PTabUICmdKtlKstOpenSigVerCrt._s_strHelpID
            );
    }
    
    
    
    // -------
    // PRIVATE
 
}
