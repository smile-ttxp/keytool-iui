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

/**

**/

import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.checkbox.*;

import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

final public class PTabUICmdJsrVerify extends PTabUICmdJsrAbs
{
    final static public String STR_TITLETASK = "Verify signed JAR file";
    
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTextEntrySize               = "Entries' size:";
    final static private String _f_s_strTextEntryDate               = "Entries' date:";
    final static private String _f_s_strTextEntryCertsType          = "Certs type:";
    final static private String _f_s_strTextEntryCertsX509AlgoName  = "Algos name:";
    final static private String _f_s_strTextEntryCertsX509SubjectDN = "Subject DN:";
    
    
    
    final static private String _f_s_strTipEntrySize = "display size of each entry";
    final static private String _f_s_strTipEntryDate = "display date of each entry";
    final static private String _f_s_strTipEntryCertsType = "display entry's certificate(s) type(s)";
    final static private String _f_s_strTipEntryCertsX509AlgoName = "display entry's certificate(s) of type X509 algorithm name";
    final static private String _f_s_strTipEntryCertsX509SubjectDN = "display entry's certificate(s) of type X509 subject distinguished name";
    
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    //static private String _s_strDlgErrorActionBody = null;
    
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
        
        _alignLabelsEntries();
        
        // ---------
        return true;
    }
    
    
    public PTabUICmdJsrVerify(Frame frmOwner)
    {
        super(
            frmOwner, 
     
            PTabUICmdJsrVerify._s_strHelpID, 
            false, // blnFileSignedJarSave
            false // blnFieldRequiredKeystore
            );
        
        this._cbxEntrySize = new JCheckBox();
        this._cbxEntryDate = new JCheckBox();
        this._cbxEntryCertsType = new JCheckBox();
        this._cbxEntryCertsX509AlgoName = new JCheckBox();
        this._cbxEntryCertsX509SubjectDN = new JCheckBox();
        
        // ----
        
        /* july 10, 03:
            modif coz an empty entry of type file may be confusing while looking for "M" or "K" properties, forcing selected, and disabling
        */
        this._cbxEntrySize.setSelected(true);
        this._cbxEntrySize.setHorizontalTextPosition(SwingConstants.LEFT);
        this._cbxEntrySize.setEnabled(false);
        // end modif
        
        this._cbxEntryDate.setSelected(false);
        this._cbxEntryDate.setHorizontalTextPosition(SwingConstants.LEFT);
        
        this._cbxEntryCertsType.setSelected(true);
        //this._cbxEntryCertsType.setBorderPainted(true);
        this._cbxEntryCertsType.setHorizontalTextPosition(SwingConstants.LEFT);
        
        this._cbxEntryCertsX509AlgoName.setSelected(true);
        //this._cbxEntryCertsX509AlgoName.setBorderPainted(true);
        this._cbxEntryCertsX509AlgoName.setHorizontalTextPosition(SwingConstants.LEFT);
        
        this._cbxEntryCertsX509SubjectDN.setSelected(false);
        //this._cbxEntryCertsX509SubjectDN.setBorderPainted(true);
        this._cbxEntryCertsX509SubjectDN.setHorizontalTextPosition(SwingConstants.LEFT);
        
        
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
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        super._pnlInput_.add(super._fssSelectSignedJar_, gbc);
        
        gbc.gridy ++;
        super._pnlInput_.add(super._pnlSelectFileKst_, gbc);

    }
    

    protected void _fillInPanelOutput_() 
    {
        JPanel pnlEntrySize = _createPanelEntrySize();
        JPanel pnlEntryDate = _createPanelEntryDate();
        JPanel pnlEntryCertsType = _createPanelEntryCertsType();
        JPanel pnlEntryCertsX509AlgoName = _createPanelEntryCertsX509AlgoName();
        JPanel pnlEntryCertsX509SubjectDN = _createPanelEntryCertsX509SubjectDN();
        
        
        super._pnlOutput_.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = -1;
        
        
        
        gbc.gridy ++;
        super._pnlOutput_.add(pnlEntrySize, gbc);
        
        gbc.gridy ++;
        super._pnlOutput_.add(pnlEntryDate, gbc);
        
        gbc.gridy ++;
        super._pnlOutput_.add(pnlEntryCertsType, gbc);
        
        gbc.gridy ++;
        super._pnlOutput_.add(pnlEntryCertsX509AlgoName, gbc);
        
        gbc.gridy ++;
        super._pnlOutput_.add(pnlEntryCertsX509SubjectDN, gbc);
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
    
    
    private JLabel _lblEntrySize = null;
    private JLabel _lblEntryDate = null;
    private JLabel _lblEntryCertsType = null;
    private JLabel _lblEntryCertsX509AlgoName = null;
    private JLabel _lblEntryCertsX509SubjectDN = null;
    
    
    /**
        done in the hurry, should take the top-most width of all labels
    **/
    private void _alignLabelsEntries()
    {
        Dimension dimPref = this._lblEntryDate.getPreferredSize();
        Dimension dimMin = this._lblEntryDate.getMinimumSize();
        Dimension dimMax = this._lblEntryDate.getMaximumSize();
        
        this._lblEntrySize.setPreferredSize(dimPref);
        this._lblEntryCertsType.setPreferredSize(dimPref);
        this._lblEntryCertsX509AlgoName.setPreferredSize(dimPref);
        this._lblEntryCertsX509SubjectDN.setPreferredSize(dimPref);
        
        this._lblEntrySize.setMinimumSize(dimMin);
        this._lblEntryCertsType.setMinimumSize(dimMin);
        this._lblEntryCertsX509AlgoName.setMinimumSize(dimMin);
        this._lblEntryCertsX509SubjectDN.setMinimumSize(dimMin);
        
        this._lblEntrySize.setMaximumSize(dimMax);
        this._lblEntryCertsType.setMaximumSize(dimMax);
        this._lblEntryCertsX509AlgoName.setMaximumSize(dimMax);
        this._lblEntryCertsX509SubjectDN.setMaximumSize(dimMax);
    }
    
    
    private JPanel _createPanelEntrySize()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblEntrySize = new JLabel();
        this._lblEntrySize.setText(_f_s_strTextEntrySize);
        this._lblEntrySize.setToolTipText(_f_s_strTipEntrySize);
        this._lblEntrySize.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblEntrySize);
        pnl.add(this._cbxEntrySize);

        return pnl;
    }
    
    private JPanel _createPanelEntryDate()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblEntryDate = new JLabel();
        this._lblEntryDate.setText(_f_s_strTextEntryDate);
        this._lblEntryDate.setToolTipText(_f_s_strTipEntryDate);
        this._lblEntryDate.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblEntryDate);
        pnl.add(this._cbxEntryDate);

        return pnl;
    }
    
    private JPanel _createPanelEntryCertsType()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblEntryCertsType = new JLabel();
        this._lblEntryCertsType.setText(_f_s_strTextEntryCertsType);
        this._lblEntryCertsType.setToolTipText(_f_s_strTipEntryCertsType);
        this._lblEntryCertsType.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblEntryCertsType);
        pnl.add(this._cbxEntryCertsType);

        return pnl;
    }
    
    private JPanel _createPanelEntryCertsX509AlgoName()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblEntryCertsX509AlgoName = new JLabel();
        this._lblEntryCertsX509AlgoName.setText(_f_s_strTextEntryCertsX509AlgoName);
        this._lblEntryCertsX509AlgoName.setToolTipText(_f_s_strTipEntryCertsX509AlgoName);
        this._lblEntryCertsX509AlgoName.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblEntryCertsX509AlgoName);
        pnl.add(this._cbxEntryCertsX509AlgoName);

        return pnl;
    }
    
    private JPanel _createPanelEntryCertsX509SubjectDN()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
	    
        this._lblEntryCertsX509SubjectDN = new JLabel();
        this._lblEntryCertsX509SubjectDN.setText(_f_s_strTextEntryCertsX509SubjectDN);
        this._lblEntryCertsX509SubjectDN.setToolTipText(_f_s_strTipEntryCertsX509SubjectDN);
        this._lblEntryCertsX509SubjectDN.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblEntryCertsX509SubjectDN);
        pnl.add(this._cbxEntryCertsX509SubjectDN);

        return pnl;
    }
}