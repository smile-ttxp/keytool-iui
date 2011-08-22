/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenVerifyAbs;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenVerifyBks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenVerifyJceks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenVerifyJks;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenVerifyPkcs12;
import com.google.code.p.keytooliui.ktl.util.jarsigner.KTLKprOpenVerifyUber;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.dialog.DPasswordOpen;
import com.google.code.p.keytooliui.shared.swing.panel.PSelAbs;
import net.miginfocom.swing.MigLayout;

public final class PTabUICmdJsrVerify extends PTabUICmdJsrAbs
{
    public static final String STR_TITLETASK = "Verify signed JAR file";
    
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTextEntrySize               = "Entries' size";
    private static final String _f_s_strTextEntryDate               = "Entries' date";
    private static final String _f_s_strTextEntryCertsType          = "Certs type";
    private static final String _f_s_strTextEntryCertsX509AlgoName  = "Algos name";
    private static final String _f_s_strTextEntryCertsX509SubjectDN = "Subject DN";
    
    private static final String _f_s_strTipEntrySize = "display size of each entry";
    private static final String _f_s_strTipEntryDate = "display date of each entry";
    private static final String _f_s_strTipEntryCertsType = "display entry's certificate(s) type(s)";
    private static final String _f_s_strTipEntryCertsX509AlgoName = "display entry's certificate(s) of type X509 algorithm name";
    private static final String _f_s_strTipEntryCertsX509SubjectDN = "display entry's certificate(s) of type X509 subject distinguished name";
    
    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strHelpID = null;
    
    //private static String _s_strDlgErrorActionBody = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdJsrVerify";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".PTabUICmdJsrVerify" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
                
            _s_strHelpID = rbeResources.getString("helpID");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
    }
    
    // ------
    // PUBLIC
    
    // void
    public void itemStateChanged(ItemEvent evtItem) {}
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        KTLKprOpenVerifyAbs sig = null;
        
        String strFormatKst = ((PSelBtnTfdFileOpenKst) super._pnlSelectFileKst_).getSelectedFormatFile();
        
        if (strFormatKst == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKst");
        
        
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {            
            sig = new KTLKprOpenVerifyJks(
                super._frmOwner_, 
            
                super._strPathAbsKst_, // nil value allowed 
                KTLAbs.f_s_strProviderKstJks,
                super._strPathAbsJarSigned_,
                this._cbxEntrySize.isSelected(),
                this._cbxEntryDate.isSelected(),
                
                this._cbxEntryCertsType.isSelected(),
                this._cbxEntryCertsX509AlgoName.isSelected(),
                this._cbxEntryCertsX509SubjectDN.isSelected()
                );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {            
            sig = new KTLKprOpenVerifyJceks(
                super._frmOwner_, 
            
                super._strPathAbsKst_, // nil value allowed 
                KTLAbs.f_s_strProviderKstJceks,
                super._strPathAbsJarSigned_,
                this._cbxEntrySize.isSelected(),
                this._cbxEntryDate.isSelected(),
                
                this._cbxEntryCertsType.isSelected(),
                this._cbxEntryCertsX509AlgoName.isSelected(),
                this._cbxEntryCertsX509SubjectDN.isSelected()
                );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {            
            sig = new KTLKprOpenVerifyBks(
                super._frmOwner_, 
              
                super._strPathAbsKst_, // nil value allowed 
                KTLAbs.f_s_strProviderKstBks,
                super._strPathAbsJarSigned_,
                this._cbxEntrySize.isSelected(),
                this._cbxEntryDate.isSelected(),
                
                this._cbxEntryCertsType.isSelected(),
                this._cbxEntryCertsX509AlgoName.isSelected(),
                this._cbxEntryCertsX509SubjectDN.isSelected()
                );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {            
            sig = new KTLKprOpenVerifyUber(
                super._frmOwner_, 
          
                super._strPathAbsKst_, // nil value allowed 
                KTLAbs.f_s_strProviderKstUber,
                super._strPathAbsJarSigned_,
                this._cbxEntrySize.isSelected(),
                this._cbxEntryDate.isSelected(),
                
                this._cbxEntryCertsType.isSelected(),
                this._cbxEntryCertsX509AlgoName.isSelected(),
                this._cbxEntryCertsX509SubjectDN.isSelected()
                );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {            
            
            // !!!!!!
            /**
                if pathAbsKst != nil, require the respective password!
            **/
            
            char[] chrsPasswdKst = null;
            
            if (super._strPathAbsKst_ != null)
            {
                // open up a passwordOpen dialog
                
                DPasswordOpen dlgPasswordKst = new DPasswordOpen(
                    super._frmOwner_, 
         
                    true // blnNoPasswdAllowed
                        );
                    
                String strTitleSuffixDlg = " ";
                strTitleSuffixDlg += "for";
                strTitleSuffixDlg += " ";
                strTitleSuffixDlg += "PKCS12";
                strTitleSuffixDlg += " ";
                strTitleSuffixDlg += "keystore";
                
                dlgPasswordKst.setTitle(dlgPasswordKst.getTitle() + strTitleSuffixDlg);
                    
                if (! dlgPasswordKst.init())
                    MySystem.s_printOutExit(this, strMethod, "failed");
                    
                dlgPasswordKst.setVisible(true);
                
                chrsPasswdKst = dlgPasswordKst.getPassword();
                
                if (chrsPasswdKst == null)
                {
                    MySystem.s_printOutTrace(this, strMethod, "nil chrsPasswdKst, user canceled");
                    return;
                }
                
                
                dlgPasswordKst.destroy();
                dlgPasswordKst = null;
                
            }
            
            // --
            
            sig = new KTLKprOpenVerifyPkcs12(
                super._frmOwner_, 
           
                super._strPathAbsKst_, // nil value allowed
                "com.google.code.p.keytooliui.ktl.UIKeytool.S_STR_PROVIDERKSTPKCS12VERIFY",
                super._strPathAbsJarSigned_,
                this._cbxEntrySize.isSelected(),
                this._cbxEntryDate.isSelected(),
                
                this._cbxEntryCertsType.isSelected(),
                this._cbxEntryCertsX509AlgoName.isSelected(),
                this._cbxEntryCertsX509SubjectDN.isSelected(),
                chrsPasswdKst // !!!! nil value ONLY IF _strPathAbsKst_ is also nil !!!!
                );
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }
        
                
        if (! sig.doJob()) // just failed, no "user cancelled" 
            MySystem.s_printOutWarning(this, strMethod, "failed");
    }
    
    public void removeUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "removeUpdate(evtDocument)";
        
        
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        

        // ----
        
        if (super._doneRemoveUpdate_(strPropVal))
        {
            return;
        }
        
        // ----
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }
        
        // ------- 
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
    public void insertUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "insertUpdate(evtDocument)";
        
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        
        // ----
        
        int intLength = doc.getLength();
        
        if (intLength == 0)
            MySystem.s_printOutExit(this, strMethod, "intLength == 0");
            
        String strText = null;
        
        try
        {
            strText = doc.getText(0, intLength);
        }
            
        catch(BadLocationException excBadLocation)
        {
            excBadLocation.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excBadLocation caught");
        }
        
        // -----
        
        // -----
        
        if (super._doneInsertUpdate_(strPropVal, strText))
        {
            return;
        }
        
        
        // ----
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = strText;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }
        
        // ------------
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
   
    /*public void destroy()
    {
        super.destroy();
    }*/
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        // alignment
        
        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();

        vecPanel.add(super._pnlSelectFileKst_);
        // MEMO: CANNOT ADD Certs' stuff, assuming Certs' text is always less in size than others!
        vecPanel.add(super._fssSelectSignedJar_);        
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    
    public PTabUICmdJsrVerify(Frame frmOwner)
    {
        super(frmOwner, PTabUICmdJsrVerify._s_strHelpID, false, false);
        
        this._cbxEntrySize = new JCheckBox(_f_s_strTextEntrySize);
        this._cbxEntryDate = new JCheckBox(_f_s_strTextEntryDate);
        this._cbxEntryCertsType = new JCheckBox(_f_s_strTextEntryCertsType);
        this._cbxEntryCertsX509AlgoName = new JCheckBox(_f_s_strTextEntryCertsX509AlgoName);
        this._cbxEntryCertsX509SubjectDN = new JCheckBox(_f_s_strTextEntryCertsX509SubjectDN);

        this._cbxEntrySize.setToolTipText(_f_s_strTipEntrySize);
        this._cbxEntryDate.setToolTipText(_f_s_strTipEntryDate);
        this._cbxEntryCertsType.setToolTipText(_f_s_strTipEntryCertsType);
        this._cbxEntryCertsX509AlgoName.setToolTipText(_f_s_strTipEntryCertsX509AlgoName);
        this._cbxEntryCertsX509SubjectDN.setToolTipText(_f_s_strTipEntryCertsX509SubjectDN);
        // ----
        
        /* july 10, 03:
            modif coz an empty entry of type file may be confusing while looking for "M" or "K" properties, forcing selected, and disabling
        */
        this._cbxEntrySize.setSelected(true);
        this._cbxEntrySize.setEnabled(false);
        // end modif
        this._cbxEntryDate.setSelected(false);
        this._cbxEntryCertsType.setSelected(true);
        this._cbxEntryCertsX509AlgoName.setSelected(true);
        this._cbxEntryCertsX509SubjectDN.setSelected(false);
    }
    
    // ---------
    // PROTECTED
    
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlInput_.add(super._fssSelectSignedJar_);
        super._pnlInput_.add(super._pnlSelectFileKst_);
    }

    protected void _fillInPanelOutput_() 
    {
        super._pnlOutput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlOutput_.add(_cbxEntrySize);
        super._pnlOutput_.add(_cbxEntryDate);
        super._pnlOutput_.add(_cbxEntryCertsType);
        super._pnlOutput_.add(_cbxEntryCertsX509AlgoName);
        super._pnlOutput_.add(_cbxEntryCertsX509SubjectDN);
    }
    
    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsJarSigned_ == null)
                return;

            // --
            super._btnAction_.setEnabled(true);
            return;
        }
        
        else
        {
            if (! super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsJarSigned_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            // --
            return;
        }
    }
    
    // -------
    // PRIVATE
    
    
    private JCheckBox _cbxEntrySize = null;
    private JCheckBox _cbxEntryDate = null;
    private JCheckBox _cbxEntryCertsType = null;
    private JCheckBox _cbxEntryCertsX509AlgoName = null;
    private JCheckBox _cbxEntryCertsX509SubjectDN = null;  
}
