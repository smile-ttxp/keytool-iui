//PTabUICmdKtlKstOpenCrShkAbs
package com.google.code.p.keytooliui.ktl.swing.panel;

/**




    known subclasses:
    . PTabUICmdKtlKstOpenCrShkAll
    
**/

import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;

abstract public class PTabUICmdKtlKstOpenCrShkAbs extends PTabUICmdKtlKstOpenAbs 
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    
    // ------
    // PUBLIC
    
        
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
        
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = strText;
            _updateActionButtonDataChanged_(true);
            return;
        }
        
        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = strText;
            //_updateActionButtonDataChanged_(true);
            return;
        }
        
       
        
        // input right
        
      
        
        // ------------
        
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
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
        
        // --------
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            _updateActionButtonDataChanged_(false);
            return;
        }
        
        // input left
        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = null;
            //_updateActionButtonDataChanged_(false);
            return;
        }
      
        
        // -------
        
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
    public void destroy()
    {
        super.destroy();
  
        if (this._pnlSelectSigAlgo_ != null)
        {
            this._pnlSelectSigAlgo_.destroy();
            this._pnlSelectSigAlgo_ = null;
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlSelectSigAlgo_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectSigAlgo_");
            return false;
        }
        
        if (! this._pnlSelectSigAlgo_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        
        // --
        // alignment

        if (! _alignLabelsPanelContents())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        

        
        // ----
        return true;
    }
    
    // ---------
    // PROTECTED
    
   
    protected PSelAbs _pnlSelectSigAlgo_ = null;
    
    
    protected void _doneJob_(
        String strFormatKstTarget,
        String strInstanceKeyGeneratorSK)
    {
        
        // show warning confirm dialog
        String strTitle = super._strTitleAppli_ + " - " + "confirm";   
       
        
        String strDlgBody = "Successfully created " + strInstanceKeyGeneratorSK + " secret key." + "\n" +
            "New secret key entry stored in keystore:" + "\n    " + super._strPathAbsKst_ + "\n\n" +
            "View keystore?";
        
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strTitle, strDlgBody))
            return;
            
        // show file
        // --
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstJks.s_showFile(super._strTitleAppli_, super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstJceks.s_showFile(super._strTitleAppli_, super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstPkcs12.s_showFile(super._strTitleAppli_, super._frmOwner_, super._strPathAbsKst_,
                    super._strPasswdKst_.toCharArray());
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstBks.s_showFile(super._strTitleAppli_, super._frmOwner_, super._strPathAbsKst_);
            return;
        }
        
        if (strFormatKstTarget.toLowerCase().compareTo(
                com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            UtilKstUber.s_showFile(super._strTitleAppli_, super._frmOwner_, super._strPathAbsKst_,
                    super._strPasswdKst_.toCharArray());
            return;
        }
        
        
    }
    
    protected void _fillInPanelInput_()
    {        
        super._fillInPanelKst_(super._pnlInput_);
        super._pnlInput_.add(Box.createRigidArea(new Dimension(1, 10)));
    }

    protected void _fillInPanelOutput_()
    {
        String strMethod = "_fillInPanelOutput_()";
        
        JPanel pnlOutputSK = _createPanelOutputSK();
        
        if (pnlOutputSK == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil pnlOutputSK");
        }
        
        JPanel pnlAll = new JPanel();
        
        pnlAll.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlAll.add(pnlOutputSK, gbc);
        
        
        //gbc.gridy ++;
        //pnlAll.add(pnlOutputDistingName, gbc);
        
        
        // ----
        super._pnlOutput_.add(pnlAll);
    }
    
    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {  
        String strMethod = "_updateActionButtonDataChanged_(blnFieldInserted)";
        
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
            {
                //MySystem.s_printOutTrace(this, strMethod, "super._btnAction_.isEnabled()");
                return;
            }
            
            if (super._strPathAbsKst_ == null)
            {
                //MySystem.s_printOutTrace(this, strMethod, "nil super._strPathAbsKst_");
                return;
            }
            
            /*if (super._strPasswdKst_ == null)
            {
                //MySystem.s_printOutTrace(this, strMethod, "nil super._strPasswdKst_");
                return;
            }*/

            // --
            super._btnAction_.setEnabled(true);
            return;
        }
        
        else
        {
            if (! super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsKst_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            /*if (super._strPasswdKst_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }*/
           
 
            // --
            return;
        }
        
    }
    
    protected PTabUICmdKtlKstOpenCrShkAbs(
        String strHelpID, 
        Frame frmOwner, 
        String strTitleAppli,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        super(
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            false, // blnAllowTypeJks // NOT SUPPORTED !!! only private keys, no secret keys
            true, // blnAllowTypeJceks
            false, // blnAllowTypePkcs12, NOT SUPPORTED: secret key with BC keyFactory stuff
            blnAllowTypeBks,
            blnAllowTypeUber
            );
        
    }
    
    
    // -------
    // PRIVATE
    
    private boolean _alignLabelsPanelContents()
    {
        String strMethod = "_alignLabelsPanelContents()";
        
        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();
               
        
        // keystore
        
        
        vecPanel.add(super._pnlSelectFileKst_);
        vecPanel.add(super._pnlSelectPasswdKst_);
        
        
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        vecPanel.clear();
        
        // SK

        vecPanel.add(this._pnlSelectSigAlgo_);
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        vecPanel.clear();
        
        
      
        
        // --
        vecPanel = null;
        return true;
    }
    
    
    /**
        contains 1 panel that allow to assign values to generated secret key entry
        . 1) values of type String:
            . SK size
            . SK alias
            . SK password
            . validity (DSA only), done in subclass
          
      
    **/
    private JPanel _createPanelOutputSK()
    {
        String strMethod = "_createPanelOutputSK()";
        
        // --
        
        JPanel pnl = new JPanel();
        pnl.setBorder(new TitledBorder("New Entry - Secret Key")); // shared key
        
        pnl.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        pnl.add(this._pnlSelectSigAlgo_, gbc);
        
        //gbc.gridy ++;
        //pnl.add(this._pnlSelectValidityShk, gbc);
        
        // ending
        return pnl;
    }
}
