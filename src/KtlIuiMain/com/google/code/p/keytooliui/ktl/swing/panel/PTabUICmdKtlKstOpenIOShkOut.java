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

final public class PTabUICmdKtlKstOpenIOShkOut extends PTabUICmdKtlKstOpenIOShkOutAbs
{
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Export secret key entry as secret key file";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    //static private String _s_strDlgInfoActionBodyBeg = null;
    //static private String _s_strDlgInfoActionBodyCrypt = null;
    //static private String _s_strDlgInfoActionBodyQuery = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenIOShkOut";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenIOShkOut" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              */
            _s_strHelpID = "_out_ske_to_file_"; // TEMPO rbeResources.getString("helpID");
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
        
        
        char[] chrsPasswdKst = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKst = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKst = "".toCharArray();
        
        String strFormatFileShk = ((PSelBtnTfdFileSaveShk) super._pnlSelectFileData_).getSelectedFormatFile(); 
        
        if (strFormatFileShk == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatFileShk");
        

        KTLShkOpenIOOutAbs ktl = null;
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkOpenIOOutJceks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileData_, // mandatory
                strFormatFileShk
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkOpenIOOutBks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileData_, // mandatory
                strFormatFileShk
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLShkOpenIOOutUber(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileData_, // mandatory
                strFormatFileShk
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
            
            if (! _queryPreviewResultsShk(strFormatFileShk))
                MySystem.s_printOutWarning(this, strMethod, "failed to query preview results");
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
        
       
    }
    
    public PTabUICmdKtlKstOpenIOShkOut(Frame frmOwner, String strTitleAppli)
    {
        super(
            frmOwner, 
            strTitleAppli,
            PTabUICmdKtlKstOpenIOShkOut._s_strHelpID
            );
    }

    // -------
    // PRIVATE
    
    private boolean _queryPreviewResultsShk(String strFormatFileShk)
    {
        // if ascii file (PEM): query view results
        
         if (strFormatFileShk.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileShkDer.toLowerCase()) == 0)
         {
             // show dialog OK
             
             String strDlgBody = "Successfully exported secret key entry:";
             strDlgBody += "\n" + " . secret key file:"+ super._strPathAbsFileData_;
             
             com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogInfo(
                super._frmOwner_, super._strTitleAppli_, strDlgBody);
             
             return true;
         }
             
        
        String strMethod = "_queryPreviewResults(strFormatFileShk)";
        
        
        
        // show warning confirm dialog
        String strTitle = super._strTitleAppli_ + " - " + "confirm";   
       
        
        String strDlgBody = "Successfully exported secret key entry:";
             strDlgBody += "\n" + " . secret key file:"+ super._strPathAbsFileData_;
             
        strDlgBody += "\n\n" + "View contents?";
        
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strTitle, strDlgBody))
            return true;
        
        
        // ----------
        // check file
        
        java.io.File fle = new java.io.File(super._strPathAbsFileData_);
        
        if (! fle.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.exists(), super._strPathAbsFileData_=" + super._strPathAbsFileData_);
            return false;
        }
        
        if (! fle.canRead())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.canRead(), super._strPathAbsFileData_=" + super._strPathAbsFileData_);
            return false;
        }
        
        //----
        
        if (strFormatFileShk.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileShkPem.toLowerCase()) == 0)
        {
        
            boolean blnGotIt = false;
            
            for (int i=0; i<com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsShkPem.length; i++)
            {
                if (super._strPathAbsFileData_.toLowerCase().endsWith("." + 
                    com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsShkPem[i].toLowerCase()))
                {
                    blnGotIt = true;
                    break;
                }
            }
            
            if (! blnGotIt)
            {
                MySystem.s_printOutError(this, strMethod, "wrong file extension, super._strPathAbsFileData_=" + super._strPathAbsFileData_);
                return false;
            }
            
            
            
            // launch dialog
            
            UtilPemKeyPrivate.s_showFile(super._strTitleAppli_, super._frmOwner_, fle);
        }        
       
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, 
                "DEV ERROR: uncaught Shk's file format, strFormatFileShk=" + strFormatFileShk);
        }
        // ---
        
        // ending
        return true;
    }
}
