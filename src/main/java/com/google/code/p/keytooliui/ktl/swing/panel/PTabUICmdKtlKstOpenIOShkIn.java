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

public final class PTabUICmdKtlKstOpenIOShkIn extends PTabUICmdKtlKstOpenIOShkInAbs
{
    // ---------------------------
    // private static final String
    
    public static final String STR_TITLETASK = "Import DER secret key file as new secret key entry";
    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strHelpID = null;
    
    //private static String _s_strDlgInfoActionBodyBeg = null;
    //private static String _s_strDlgInfoActionBodyCrypt = null;
    //private static String _s_strDlgInfoActionBodyQuery = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenIOShkIn";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenIOShkIn" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              */
            _s_strHelpID = "_in_ske_from_file_"; // TEMPO rbeResources.getString("helpID");
            //_s_strDlgInfoActionBodyBeg = "dlgInfoActionBodyBeg"; // TEMPO rbeResources.getString("dlgInfoActionBodyBeg");
            //_s_strDlgInfoActionBodyCrypt = "dlgInfoActionBodyCrypt"; // TEMPO rbeResources.getString("dlgInfoActionBodyCrypt");
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
        
        
        char[] chrsPasswdKstTarget = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKstTarget = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKstTarget = "".toCharArray();
        
        String strInstanceKeyGenerator = (String) ((PSelCmbAbs) super._pnlSelectSigAlgo_).getSelectedItemCmb();

        
        KTLShkOpenIOInAbs ktl = null;
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkOpenIOInJceks(
                super._frmOwner_, 
              
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                
                // I/O
                super._strPathAbsFileData_,
                strInstanceKeyGenerator
                    
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkOpenIOInBks(
                super._frmOwner_, 
           
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                
                // I/O
                super._strPathAbsFileData_,
                strInstanceKeyGenerator
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkOpenIOInUber(
                super._frmOwner_, 
          
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                
                // I/O
                super._strPathAbsFileData_,
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
    
    public PTabUICmdKtlKstOpenIOShkIn(Frame frmOwner)
    {
        super(
            frmOwner, 
   
            PTabUICmdKtlKstOpenIOShkIn._s_strHelpID
            );
    }

    // -------
    // PRIVATE
}

