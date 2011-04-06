package com.google.code.p.keytooliui.ktl.swing.panel;

/**
**/

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtInAbs;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtInBks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtInJceks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtInJks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtInPkcs12;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtInUber;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import net.miginfocom.swing.MigLayout;


final public class PTabUICmdKtlKstOpenCrtImpReply extends PTabUICmdKtlKstOpenCrtAbs
{
    // ---------------------------
    // final static private String
    
    final static public String STR_TITLETASK = "Import CA (Certificate Authority) certificate reply to private key entry";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenCrtImpReply";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".PTabUICmdKtlKstOpenCrtImpReply" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strHelpID = "_in_pke_z_crt_from_file_"; //rbeResources.getString("helpID");
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
        
        String strFormatKstTarget = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();
        
        if (strFormatKstTarget == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKstTarget");
        
        String strFormatFileCert = ((PSelBtnTfdFileOpenCrtReply) super._pnlSelectFileIO_).getSelectedFormatFile();
        
        if (strFormatFileCert == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatFileCert");  
        
        char[] chrsPasswdKstTarget = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKstTarget = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKstTarget = "".toCharArray();
        
        KTLKprOpenCrtInAbs ktl = null;
        
        if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtInJks(
                super._frmOwner_, 
            
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // eg: "PKCS#7", "Other"
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtInJceks(
                super._frmOwner_, 
             
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // eg: "PKCS#7", "Other"
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtInPkcs12(
                super._frmOwner_, 
             
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // eg: "PKCS#7", "Other"
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtInBks(
                super._frmOwner_, 
         
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // eg: "PKCS#7", "Other"
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtInUber(
                super._frmOwner_, 
         
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // eg: "PKCS#7", "Other"
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
    
    
    public PTabUICmdKtlKstOpenCrtImpReply(Frame frmOwner)
    {
        super(
            PTabUICmdKtlKstOpenCrtImpReply._s_strHelpID, 
            frmOwner, 
       
            PSelBtnTfdFileOpenCrtReply.f_s_strDocPropVal
            );
        
            
        super._pnlSelectFileIO_ = new PSelBtnTfdFileOpenCrtReply(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
         
            (ItemListener) null
            );
            
    }
    
    // ---------
    // PROTECTED
    
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlInput_.add(super._pnlSelectFileIO_);
    }
    
    
    protected void _fillInPanelOutput_()
    {
        super._fillInPanelKst_(super._pnlOutput_);
    }
    
    
    // -------
    // PRIVATE

   
    
     private void _doneJob(String strFormatKstTarget)
    {        
        // show warning confirm dialog
        //String strTitle = super._strTitleAppli_ + " - " + "confirm";
       
        
        String strDlgBody = "Successfully imported C.A. certificate file in private key entry." + "\n" +
            "Modified private key entry located in keystore:" + "\n    " + super._strPathAbsKst_ + "\n\n" +
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