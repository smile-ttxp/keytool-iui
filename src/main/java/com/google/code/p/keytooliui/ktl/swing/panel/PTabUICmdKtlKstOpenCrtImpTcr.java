package com.google.code.p.keytooliui.ktl.swing.panel;

/**
**/

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLTcrSaveCrtInBks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLTcrSaveCrtInJceks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLTcrSaveCrtInJks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLTcrSaveCrtInPkcs12;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLTcrSaveCrtInUber;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLTcrSaveNewAbs;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12;
import com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.panel.PSelAbs;
import com.google.code.p.keytooliui.shared.swing.panel.PSelCbxAbs;
import net.miginfocom.swing.MigLayout;


public final class PTabUICmdKtlKstOpenCrtImpTcr extends PTabUICmdKtlKstOpenCrtAbs
{
    // ---------------------------
    // private static final String
    
    public static final String STR_TITLETASK = "Import regular certificate file as new trusted certificate entry to keystore";
    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strHelpID = null;
    
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenCrtImpTcr";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstOpenCrtImpTcr" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
          */      
            _s_strHelpID = "_in_crt_tce_from_file_"; // rbeResources.getString("helpID");
        /*}
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }*/
    }
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._pnlChkTrustCA.init())
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
        
        String strFormatFileCert = ((PSelBtnTfdFileOpenCrtTcr) super._pnlSelectFileIO_).getSelectedFormatFile();
        
        if (strFormatFileCert == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatFileCert");  
        
        char[] chrsPasswdKstTarget = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKstTarget = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKstTarget = "".toCharArray();
            
        boolean blnTrustCACerts = ((PSelCbxAbs) this._pnlChkTrustCA).isSelectedItem();
        
        KTLTcrSaveNewAbs ktl = null;
        
        if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLTcrSaveCrtInJks(
                super._frmOwner_, 
         
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert, // eg: "DER", "PKCS#7", "PEM"
                blnTrustCACerts
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLTcrSaveCrtInJceks(
                super._frmOwner_, 
          
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert, // eg: "DER", "PKCS#7", "PEM"
                blnTrustCACerts
            );
        }
        
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLTcrSaveCrtInPkcs12(
                super._frmOwner_, 
              
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert, // eg: "DER", "PKCS#7", "PEM"
                blnTrustCACerts
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLTcrSaveCrtInBks(
                super._frmOwner_, 
            
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert, // eg: "DER", "PKCS#7", "PEM"
                blnTrustCACerts
            );
        }
        
        else if (strFormatKstTarget.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLTcrSaveCrtInUber(
                super._frmOwner_, 
          
                // input
                super._strPathAbsKst_, 
                chrsPasswdKstTarget,
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert, // eg: "DER", "PKCS#7", "PEM"
                blnTrustCACerts
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
    
    
    public PTabUICmdKtlKstOpenCrtImpTcr(Frame frmOwner)
    {
        super(
            PTabUICmdKtlKstOpenCrtImpTcr._s_strHelpID, 
            frmOwner, 
         
            PSelBtnTfdFileOpenCrtTcr.f_s_strDocPropVal
            );
        
            
        super._pnlSelectFileIO_ = new PSelBtnTfdFileOpenCrtTcr(
            PCntsMainLeft.STR_NODE_KW_TCREGULAR + " file:",
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
   
            (ItemListener) null
            );
            
        
        this._pnlChkTrustCA = new PSelCbxTrustcacerts();
    }
    
    // ---------
    // PROTECTED
    
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlInput_.add(super._pnlSelectFileIO_);
        super._pnlInput_.add(this._pnlChkTrustCA);
    }
    
    
    protected void _fillInPanelOutput_()
    {
        super._fillInPanelKst_(super._pnlOutput_);
    }
    
    
    // -------
    // PRIVATE
    
    private PSelAbs _pnlChkTrustCA = null;
    
    
    private void _doneJob(String strFormatKstTarget)
    {
        String strMethod = "_doneJob(strFormatKstTarget)";
  
        
        // show warning confirm dialog
        //String strTitle = super._strTitleAppli_ + " - " + "confirm";
       
        
        String strDlgBody = "Successfully imported certificate file as trusted certificate entry." + "\n" +
            "New trusted certificate entry located in keystore:" + "\n    " + super._strPathAbsKst_ + "\n\n" +
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