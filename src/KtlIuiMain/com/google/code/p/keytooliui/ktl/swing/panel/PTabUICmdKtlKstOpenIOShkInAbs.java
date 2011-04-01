package com.google.code.p.keytooliui.ktl.swing.panel;

/*
*/

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

abstract public class PTabUICmdKtlKstOpenIOShkInAbs extends PTabUICmdKtlKstOpenIOShkAbs
{   
    // ------
    // public
    
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

     /*   if (! _alignLabelsPanelContents())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
       */ 

        
        // ----
        return true;
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
    
    // ---------
    // protected
    
    protected PSelAbs _pnlSelectSigAlgo_ = null;
    
    protected PTabUICmdKtlKstOpenIOShkInAbs(
        Frame frmOwner, 
    
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
  
            PSelBtnTfdFileOpenShkShk.f_s_strDocPropVal
            );
        
        
             super._pnlSelectFileData_ = new PSelBtnTfdFileOpenShkShk(
                (javax.swing.event.DocumentListener) this,
                frmOwner, 
     
                (ItemListener) null,
                true // blnDerVersusPem // XOR
            );

        
            this._pnlSelectSigAlgo_ = new PSelCmbStrSigAlgoSKAll();
    }
    
     protected void _doneJob_(
        String strFormatKstTarget,
        String strInstanceKeyGeneratorSK)
    {
        
        // show warning confirm dialog
        //String strTitle = super._strTitleAppli_ + " - " + "confirm";
       
       
        
        String strDlgBody = "Successfully imported " + strInstanceKeyGeneratorSK + " secret key from file" + "\n" +
            super._strPathAbsFileData_ + "\n\n" +
            "New secret key entry stored in keystore:" + "\n    " + super._strPathAbsKst_ + "\n\n" +
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
        super._pnlInput_.add(this._pnlSelectFileData_, gbc);
        
        gbc.gridy ++;
        super._pnlInput_.add(this._pnlSelectSigAlgo_, gbc);
        
    }
    
    protected void _fillInPanelOutput_()
    {
        GridBagConstraints gbc = super._fillInPanelKst_(super._pnlOutput_);
        
        //gbc.gridy ++;
        //super._pnlInput_.add(this._pnlSelectFileDataOpen_, gbc);
        
        
        
    }

    // -------
    // PRIVATE

}
