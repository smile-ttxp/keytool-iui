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

abstract public class PTabUICmdKtlKstOpenXmlSigExpAbs extends PTabUICmdKtlKstOpenXmlSigAbs
{    
    // ------
    // public
    
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
        
        // --------
        // output, optional
        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveCrt.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileCrt_ = strText;
            
            // no need to update actionButton coz optional field
            //_updateActionButtonDataChanged(true);
            
            return;
        }
        
        super.insertUpdate(evtDocument);
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
        
        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveCrt.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileCrt_ = null;
            
            // no need to update actionButton coz optional field
            //_updateActionButtonDataChanged(false);
            
            return;
        }

        
        super.removeUpdate(evtDocument);
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._dlgViewResult != null)
        {
            this._dlgViewResult.destroy();
            this._dlgViewResult = null;
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
        
        
    
        return true;
    }
    
    
    // ---------
    // protected
   
   
    protected String _strPathAbsFileCrt_ = null; // output, optional;
    
    protected PTabUICmdKtlKstOpenXmlSigExpAbs(
        Frame frmOwner, 
  
        String strHelpID
        )
    {
        super(
            strHelpID, 
            frmOwner, 
         
            PSelBtnTfdFileSaveXml.f_s_strDocPropVal
            );
        
         super._pnlSelectFileDataOut_ = new PSelBtnTfdFileSaveXml(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
     
            (ItemListener) null);
        
        
    }
    
    
    // input file was assumed to be in ascii format
    protected boolean _queryPreviewResults_()
    {
        String strMethod = "_queryPreviewResults()";
        
        
        if (super._strPathAbsFileDataOut_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strPathAbsFileDataOut_");
            return false;
        }
        
        boolean blnGotIt = false;

        

        
        // show warning confirm dialog
        //String strTitle = super._strTitleAppli_ + " - " + "confirm";
       
        
        String strDlgBody = new String("");
        strDlgBody += "XML document successfully signed with private key entry.";
        strDlgBody += "\n" + "XML signed file location;";
        strDlgBody += "\n  " + super._strPathAbsFileDataOut_;
        strDlgBody += "\n\n";
        strDlgBody += "See contents?";
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strDlgBody))
            return true;
        
            
        // check file
        
        java.io.File fle = new java.io.File(super._strPathAbsFileDataOut_);
        
        if (! fle.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.exists(), super._strPathAbsFileDataOut_=" + super._strPathAbsFileDataOut_);
            return false;
        }
        
        if (! fle.canRead())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.canRead(), super._strPathAbsFileDataOut_=" + super._strPathAbsFileDataOut_);
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
    
    
    protected void _fillInPanelOutput_()
    {
        super._pnlOutput_.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        
        gbc.gridy = 0;
        super._pnlOutput_.add(super._pnlSelectFileDataOut_, gbc);
        

    }

    // -------
    // PRIVATE
    
    private DViewSourceFileTextSys _dlgViewResult = null;
}
