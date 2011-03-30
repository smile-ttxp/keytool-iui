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

final public class PTabUICmdKtlKstOpenCrtExpKpr extends PTabUICmdKtlKstOpenCrtExpAbs
{
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Export certificate from private key entry as certificate file";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    static private String _s_strDlgInfoActionBodyBeg = null;
    static private String _s_strDlgInfoActionBodyCrt = null;
    static private String _s_strDlgInfoActionBodyQuery = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenCrtExpKpr";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenCrtExpKpr" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              
            _s_strHelpID = "_out_pke_to_file_crt_"; // rbeResources.getString("helpID");
            _s_strDlgInfoActionBodyBeg = rbeResources.getString("dlgInfoActionBodyBeg");
            _s_strDlgInfoActionBodyCrt = rbeResources.getString("dlgInfoActionBodyCrt");
            _s_strDlgInfoActionBodyQuery = rbeResources.getString("dlgInfoActionBodyQuery");
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
        
        String strFormatFileCert = ((PSelBtnTfdFileSaveCrt) super._pnlSelectFileIO_).getSelectedFormatFile(); 
        
        char[] chrsPasswdKst = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKst = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKst = "".toCharArray();
        
        KTLKprOpenCrtOutAbs ktl = null;
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtOutJks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtOutJceks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtOutPkcs12(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtOutBks(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtOutUber(
                super._frmOwner_, 
                super._strTitleAppli_,
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert
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
            
            if (! _queryPreviewResults(strFormatFileCert))
                MySystem.s_printOutExit(this, strMethod, "failed");
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
        
    }
    
    
    
    public PTabUICmdKtlKstOpenCrtExpKpr(Frame frmOwner, String strTitleAppli)
    {
        super(
            frmOwner, 
            strTitleAppli,
            PTabUICmdKtlKstOpenCrtExpKpr._s_strHelpID
            );
            
    }
    
    // -------
    // PRIVATE

    
    /**

    **/
    private boolean _queryPreviewResults(String strFormatFileCert)
    {
        String strMethod = "_queryPreviewResults(strFormatFileCert)";
        
        if (super._strPathAbsFileIO_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strPathAbsFileIO_");
            return false;
        }
        
        
        
        // show warning confirm dialog
        String strTitle = super._strTitleAppli_ + " - " + "confirm";   
       
        
        String strDlgBody = _s_strDlgInfoActionBodyBeg + "\n" +
            _s_strDlgInfoActionBodyCrt + "\n    " + super._strPathAbsFileIO_ + "\n\n" +
            _s_strDlgInfoActionBodyQuery;
        
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strTitle, strDlgBody))
            return true;
        
        
        // ----------
        // check file
        
        java.io.File fle = new java.io.File(super._strPathAbsFileIO_);
        
        if (! fle.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.exists(), super._strPathAbsFileIO_=" + super._strPathAbsFileIO_);
            return false;
        }
        
        if (! fle.canRead())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.canRead(), super._strPathAbsFileIO_=" + super._strPathAbsFileIO_);
            return false;
        }
        
        //----
        
        if (strFormatFileCert.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPkcs7.toLowerCase()) == 0/* || 
            strFormatFileCert.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) == 0*/
        )
        {
            boolean blnGotIt = false;
            
            for (int i=0; i<com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Pkcs7.length; i++)
            {
                if (super._strPathAbsFileIO_.toLowerCase().endsWith("." + 
                    com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Pkcs7[i].toLowerCase()))
                {
                    blnGotIt = true;
                    break;
                }
            }
            
            if (! blnGotIt)
            {
                MySystem.s_printOutError(this, strMethod, "wrong file extension, super._strPathAbsFileIO_=" + super._strPathAbsFileIO_);
                return false;
            }
            
            
            
            // launch dialog
            
            UtilCrtX509Pkcs7.s_showFile(super._strTitleAppli_, super._frmOwner_, fle);
        }
        
        else if (strFormatFileCert.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtDer.toLowerCase()) == 0)
        {
        
            boolean blnGotIt = false;
            
            for (int i=0; i<com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Der.length; i++)
            {
                if (super._strPathAbsFileIO_.toLowerCase().endsWith("." + 
                    com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Der[i].toLowerCase()))
                {
                    blnGotIt = true;
                    break;
                }
            }
            
            if (! blnGotIt)
            {
                MySystem.s_printOutError(this, strMethod, "wrong file extension, super._strPathAbsFileIO_=" + super._strPathAbsFileIO_);
                return false;
            }
            
            
            
            // launch dialog
            
            UtilCrtX509Der.s_showFile(super._strTitleAppli_, super._frmOwner_, fle);
        }              
       
        else if (strFormatFileCert.toLowerCase().compareTo(KTLAbs.f_s_strFormatFileCrtPem.toLowerCase()) == 0)
        {
        
            boolean blnGotIt = false;
            
            for (int i=0; i<com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Pem.length; i++)
            {
                if (super._strPathAbsFileIO_.toLowerCase().endsWith("." + 
                    com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCrtX509Pem[i].toLowerCase()))
                {
                    blnGotIt = true;
                    break;
                }
            }
            
            if (! blnGotIt)
            {
                MySystem.s_printOutError(this, strMethod, "wrong file extension, super._strPathAbsFileIO_=" + super._strPathAbsFileIO_);
                return false;
            }
            
            
            
            // launch dialog
            
            UtilCrtX509Pem.s_showFile(super._strTitleAppli_, super._frmOwner_, fle);
        }              
       
        
        else
        {
            // !!!!!!!
        }
        // ---
        
        // ending
        return true;
    }
}