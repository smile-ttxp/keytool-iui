package com.google.code.p.keytooliui.ktl.swing.panel;

/**
**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


final public class PTabUICmdKtlKstOpenKprFromKprDer extends PTabUICmdKtlKstOpenKprFromKprFileAbs
{
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Import private key entry from DER private key file & certs chain file";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenKprFromKprDer";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".PTabUICmdKtlKstOpenKprFromKprDer" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strHelpID = "_in_pke_from_files_der_"; // rbeResources.getString("helpID");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
    }

    public String getSTR_TITLETASK() {
        return STR_TITLETASK;
    }
    
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
  
        if (this._pnlSelectInstanceKeyGenerator != null)
        {
            this._pnlSelectInstanceKeyGenerator.destroy();
            this._pnlSelectInstanceKeyGenerator = null;
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
        
        
        if (this._pnlSelectInstanceKeyGenerator == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectInstanceKeyGenerator");
            return false;
        }
        
        if (! this._pnlSelectInstanceKeyGenerator.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
         
         
        
        String strFormatKstTarget = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();
        
        if (strFormatKstTarget == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKstTarget");
           
        
        char[] chrsPasswdKstTarget = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKstTarget = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKstTarget = "".toCharArray();
        
        String strInstanceKeyGenerator = (String) ((PSelCmbAbs) this._pnlSelectInstanceKeyGenerator).getSelectedItemCmb();
        
        KTLKprOpenKprFromKprAbs ktl = null;
        
        if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenKprFromKprDerJks(
                super._frmOwner_, 
             
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileKpr_,
                super._strPathAbsFileCrts_,
                strInstanceKeyGenerator
      
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenKprFromKprDerJceks(
                super._frmOwner_, 
            
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileKpr_,
                super._strPathAbsFileCrts_,
   
                strInstanceKeyGenerator
                
           
            );
        }
        
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenKprFromKprDerPkcs12(
                super._frmOwner_, 
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileKpr_,
                super._strPathAbsFileCrts_,
                strInstanceKeyGenerator
         
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenKprFromKprDerBks(
                super._frmOwner_, 
              
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileKpr_,
                super._strPathAbsFileCrts_,
                strInstanceKeyGenerator
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenKprFromKprDerUber(
                super._frmOwner_, 
              
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileKpr_,
                super._strPathAbsFileCrts_,
                strInstanceKeyGenerator
            );
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKstTarget=" + strFormatKstTarget);
        }
        
        // ---
        
        if (ktl.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "OK!");
            this._doneJob(strFormatKstTarget); 
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed"); 
        
    }
    
    
    public PTabUICmdKtlKstOpenKprFromKprDer(Frame frmOwner)
    {
        super(
            PTabUICmdKtlKstOpenKprFromKprDer._s_strHelpID, 
            frmOwner, 
       
            PSelBtnTfdFileOpenKprKpr.f_s_strDocPropVal,
            PSelBtnTfdFileOpenCrtsKpr.f_s_strDocPropVal,
            true // blnDerVersusPem
            );

        this._pnlSelectInstanceKeyGenerator = new PSelCmbStrSigAlgoPKAll();
    }
    
    // ---------
    // PROTECTED
    
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
    
        gbc.gridx = 0;
        
        
        gbc.gridy = 0;
        super._pnlInput_.add(super._pnlSelectFileKpr_, gbc);
        
        
        
        gbc.gridy ++;
        super._pnlInput_.add(this._pnlSelectInstanceKeyGenerator, gbc);
        
        
        gbc.gridy ++;
        super._pnlInput_.add(super._pnlSelectFileCrts_, gbc);
    }
    
    
    
    // -------
    // PRIVATE
    
    private PSelAbs _pnlSelectInstanceKeyGenerator = null;
    
    
    private void _doneJob(String strFormatKstTarget)
    {
        String strMethod = "_doneJob(strFormatKstTarget)";
  
        
        // show warning confirm dialog
       //String strTitle = super._strTitleAppli_ + " - " + "confirm";
       
        
        String strDlgBody = "Successfully imported private key and certs chain files as private key entry." + "\n" +
            "New private key entry located in keystore:" + "\n    " + super._strPathAbsKst_ + "\n\n" +
            "View keystore?";
        
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strDlgBody))
            return;
            
        // show file
        // --
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstJks.s_showFile(super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstJceks.s_showFile(super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstPkcs12.s_showFile(super._frmOwner_, super._strPathAbsKst_,
                    super._strPasswdKst_.toCharArray());
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstBks.s_showFile(super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstUber.s_showFile(super._frmOwner_, super._strPathAbsKst_,
                    super._strPasswdKst_.toCharArray());
            return;
        }
        
    }
}
