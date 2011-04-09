/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;

import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtReqAbs;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtReqBks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtReqJceks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtReqJks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtReqPkcs12;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenCrtReqUber;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.dialog.DViewSourceFileTextSys;
import net.miginfocom.swing.MigLayout;

public final class PTabUICmdKtlKstOpenCrtSigReq extends PTabUICmdKtlKstOpenCrtAbs
{
    // ---------------------------
    // private static final String
    
    public static final String STR_TITLETASK = "Export certificate from private key entry as CSR (Certificate Signing Request) file";
    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strHelpID = null;
    
    private static String _s_strDlgInfoActionBodyBeg = null;
    private static String _s_strDlgInfoActionBodyCSR = null;
    private static String _s_strDlgInfoActionBodyQuery = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstOpenCrtSigReq";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".PTabUICmdKtlKstOpenCrtSigReq" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              
            _s_strHelpID = "_out_pke_to_file_csr_"; // rbeResources.getString("helpID");
            _s_strDlgInfoActionBodyBeg = rbeResources.getString("dlgInfoActionBodyBeg");
            _s_strDlgInfoActionBodyCSR = rbeResources.getString("dlgInfoActionBodyCSR");
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
        
        String strFormatFileCert = ((PSelBtnTfdFileSaveCsr) super._pnlSelectFileIO_).getSelectedFormatFile(); 
        
        char[] chrsPasswdKst = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKst = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKst = "".toCharArray();
        
        KTLKprOpenCrtReqAbs ktl = null;
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtReqJks(
                super._frmOwner_, 
            
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // should be PKCS#10
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtReqJceks(
                super._frmOwner_, 
               
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // should be PKCS#10
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtReqBks(
                super._frmOwner_, 
              
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // should be PKCS#10
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtReqPkcs12(
                super._frmOwner_, 
           
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // should be PKCS#10
            );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKprOpenCrtReqUber(
                super._frmOwner_, 
              
                
                // input
                super._strPathAbsKst_, 
                chrsPasswdKst,
                
                // I/O
                super._strPathAbsFileIO_,
                
                strFormatFileCert // should be PKCS#10
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
            
            if (! _queryPreviewResults())
                MySystem.s_printOutExit(this, strMethod, "failed");
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
        
    }
    
    
    public void destroy()
    {
        super.destroy();
        
        // dialog
        if (this._dlgViewResult != null)
        {
            this._dlgViewResult.destroy();
            this._dlgViewResult = null;
        }
    }
    
    
    public PTabUICmdKtlKstOpenCrtSigReq(Frame frmOwner)
    {
        super(
            PTabUICmdKtlKstOpenCrtSigReq._s_strHelpID, 
            frmOwner, 
      
            PSelBtnTfdFileSaveCsr.f_s_strDocPropVal
            );

        super._pnlSelectFileIO_ = new PSelBtnTfdFileSaveCsr(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
  
            (ItemListener) null
            );
            
    }
    
    // ---------
    // PROTECTED
    
    
    protected void _fillInPanelInput_()
    {        
        super._fillInPanelKst_(super._pnlInput_);
    }
    
    
    protected void _fillInPanelOutput_()
    {
        super._pnlOutput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlOutput_.add(super._pnlSelectFileIO_);
    }

    
    // -------
    // PRIVATE
    
    private DViewSourceFileTextSys _dlgViewResult = null;

    
    /**

    **/
    private boolean _queryPreviewResults()
    {
        String strMethod = "_queryPreviewResults()";
        
        if (super._strPathAbsFileIO_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strPathAbsFileIO_");
            return false;
        }
        
        boolean blnGotIt = false;
        
        for (int i=0; i<com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCsrPkcs10.length; i++)
        {
            if (super._strPathAbsFileIO_.toLowerCase().endsWith("." + 
                com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strsCsrPkcs10[i].toLowerCase()))
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
        
        // show warning confirm dialog
        //String strTitle = super._strTitleAppli_ + " - " + "confirm";
       
        
        String strDlgBody = _s_strDlgInfoActionBodyBeg + "\n" +
            _s_strDlgInfoActionBodyCSR + "\n    " + super._strPathAbsFileIO_ + "\n\n" +
            _s_strDlgInfoActionBodyQuery;
        
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strDlgBody))
            return true;
        
            
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
        
        // launch dialog 
        
        
        // fix up linux bug, force dispose
        if (this._dlgViewResult != null)
        {
            this._dlgViewResult.destroy(); 
            this._dlgViewResult = null;
        }
        
        this._dlgViewResult = new DViewSourceFileTextSys(super._frmOwner_);
        
        if (! this._dlgViewResult.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
      


        if (! this._dlgViewResult.show(fle))
            MySystem.s_printOutExit(this, strMethod, "failed"); 
        
        
        
        // ---
        
        // ending
        return true;
    }
}