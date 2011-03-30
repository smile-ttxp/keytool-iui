/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
    "Ktl" means Keytool


    known subclasses:
  
    . PTabUICmdKtlKstOpenCryptExpAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.DViewSourceFileTextSys;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.awt.*;

abstract public class PTabUICmdKtlKstOpenCryptAbs extends PTabUICmdKtlKstOpenAbs 
{
    
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
        
        // --------
      
        // input-output
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenAnyFile.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileDataOpen_ = strText;
            _updateActionButtonDataChanged(true);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveAny.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileDataSave_ = strText;
            _updateActionButtonDataChanged(true);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = strText;
            _updateActionButtonDataChanged(true);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = strText;
            //_updateActionButtonDataChanged(true);
            return;
        }
        
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
        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenAnyFile.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileDataOpen_ = null;
            _updateActionButtonDataChanged(false);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveAny.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsFileDataSave_ = null;
            _updateActionButtonDataChanged(false);
            return;
        }

        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            _updateActionButtonDataChanged(false);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = null;
            //_updateActionButtonDataChanged(false);
            return;
        }

        // -------
        
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._pnlSelectFileDataSave_ != null)
        {
            this._pnlSelectFileDataSave_.destroy();
            this._pnlSelectFileDataSave_ = null;
        }
        
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
        
        
        if (this._pnlSelectFileDataSave_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectFileDataSave_");
            return false;
        }
        
        if (! this._pnlSelectFileDataSave_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._pnlSelectFileDataOpen_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // alignment
        
        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();
        
        
        Component[] cmps = null;
        
        try
        {
            cmps = super._pnlInput_.getComponents();
            
            for (int i=0; i<cmps.length; i++)
            {
                PSelAbs pnlCur = (PSelAbs) cmps[i];
                vecPanel.add(pnlCur);
            }
            
             cmps = super._pnlOutput_.getComponents();
            
            for (int i=0; i<cmps.length; i++)
            {
                PSelAbs pnlCur = (PSelAbs) cmps[i];
                vecPanel.add(pnlCur);
            }
        
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassCast caught, DEV ERROR");
            return false;
        }
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        // ---
        return true;
    }
    
    // ---------
    // PROTECTED   
    
    protected PSelAbs _pnlSelectFileDataOpen_;
    
    // either cert (import) or csr (request), created in subclasses
    protected PSelAbs _pnlSelectFileDataSave_ = null;
    
    
    // either sig create, or sig to verify doc, created in subclasses
    protected String _strPathAbsFileDataSave_ = null;
    
    // either data document to verify with sig, or to create (export) signature, created in subclasses
    protected String _strPathAbsFileDataOpen_ = null;
     
    protected PTabUICmdKtlKstOpenCryptAbs(
        String strHelpID,
        Frame frmOwner,
        String strTitleAppli,
        boolean blnAllowTypeJks,
        boolean blnAllowTypePkcs12
        )
    {
        super(
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            blnAllowTypeJks,
            true, // blnAllowTypeJceks
            blnAllowTypePkcs12, //==> ?allowed for CSR or cert reply?
            true, // blnAllowTypeBks ==> allowed for CSR or cert reply
            true // blnAllowTypeUber ==> allowed for CSR or cert reply
            );
        
        
        
    }
    
    protected void _fillInPanelInput_()
    {        
        GridBagConstraints gbc = super._fillInPanelKst_(super._pnlInput_);
        
        gbc.gridy ++;
        super._pnlInput_.add(this._pnlSelectFileDataOpen_, gbc);
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
        super._pnlOutput_.add(this._pnlSelectFileDataSave_, gbc);
    }
    
    // input file was assumed to be in ascii format
    protected boolean _queryPreviewResults_()
    {
        String strMethod = "_queryPreviewResults()";
        
        if (this._strPathAbsFileDataSave_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strPathAbsFileDataSave_");
            return false;
        }
        
        boolean blnGotIt = false;

        

        
        // show warning confirm dialog
        String strTitle = super._strTitleAppli_ + " - " + "confirm";   
       
        
        String strDlgBody = new String("");
        strDlgBody += "Assuming input file is in ascii format.";
        strDlgBody += "\n\n";
        strDlgBody += "See contents of target file?";
                
                /*"_s_strDlgInfoActionBodyBeg" + "\n" +
            "_s_strDlgInfoActionBodyCSR" + "\n    " + this._strPathAbsFileDataSave_ + "\n\n" +
            "_s_strDlgInfoActionBodyQuery";*/
        
        
        if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showConfirmDialog(
            super._frmOwner_, strTitle, strDlgBody))
            return true;
        
            
        // check file
        
        java.io.File fle = new java.io.File(this._strPathAbsFileDataSave_);
        
        if (! fle.exists())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.exists(), this._strPathAbsFileDataSave_=" + this._strPathAbsFileDataSave_);
            return false;
        }
        
        if (! fle.canRead())
        {
            MySystem.s_printOutError(this, strMethod, "! fle.canRead(), this._strPathAbsFileDataSave_=" + this._strPathAbsFileDataSave_);
            return false;
        }
        
        // launch dialog 
        
        
        // fix up linux bug, force dispose
        if (this._dlgViewResult != null)
        {
            this._dlgViewResult.destroy(); 
            this._dlgViewResult = null;
        }
        
        this._dlgViewResult = new DViewSourceFileTextSys(super._frmOwner_, super._strTitleAppli_);
        
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
    
    // -------
    // PRIVATE
    
    private DViewSourceFileTextSys _dlgViewResult = null;
    
    private void _updateActionButtonDataChanged(boolean blnFieldInserted)
    {        
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;
            
            if (this._strPathAbsFileDataOpen_ == null)
                return;
            
            if (this._strPathAbsFileDataSave_ == null)
                return;
                
            if (super._strPathAbsKst_ == null)
                return;
                
            //if (super._strPasswdKst_ == null)
              //  return;
                
            // --
            super._btnAction_.setEnabled(true);
            return;
        }
        
        else
        {
            if (! super._btnAction_.isEnabled())
                return;
            
            if (this._strPathAbsFileDataOpen_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }
            
            if (this._strPathAbsFileDataSave_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }
            
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
}
