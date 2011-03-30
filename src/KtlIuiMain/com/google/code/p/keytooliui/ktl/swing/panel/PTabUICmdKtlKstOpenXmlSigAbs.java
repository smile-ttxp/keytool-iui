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
 *
 *  embedded signature
 *
    "Ktl" means Keytool


    known subclasses:
  
    . PTabUICmdKtlKstOpenXmlSigExpAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.awt.*;

abstract public class PTabUICmdKtlKstOpenXmlSigAbs extends PTabUICmdKtlKstOpenAbs 
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
        
        if (strPropVal.compareTo(this._strPropValFileDataIn) == 0)
        {
            this._strPathAbsFileData_ = strText;
            _updateActionButtonDataChanged(true);
            return;
        }
        
        if (strPropVal.compareTo(this._strPropValFileDataOut) == 0)
        {
            this._strPathAbsFileDataOut_ = strText;
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
        
        if (strPropVal.compareTo(this._strPropValFileDataIn) == 0)
        {
            this._strPathAbsFileData_ = null;
            _updateActionButtonDataChanged(false);
            return;
        }
        
        if (strPropVal.compareTo(this._strPropValFileDataOut) == 0)
        {
            this._strPathAbsFileDataOut_ = null;
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
        
        if (this._pnlSelectFileDataOut_ != null)
        {
            this._pnlSelectFileDataOut_.destroy();
            this._pnlSelectFileDataOut_ = null;
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
        
        
        if (this._pnlSelectFileDataOut_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectFileDataOut_");
            return false;
        }
        
        if (! this._pnlSelectFileDataOut_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._pnlSelectFileDataIn_.init())
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
    
    protected PSelBtnTfdFileOpenAbs _pnlSelectFileDataIn_;
    
    // either cert (import) or csr (request), created in subclasses
    protected PSelAbs _pnlSelectFileDataOut_ = null;

    // either sig create, or sig to verify doc, created in subclasses
    protected String _strPathAbsFileDataOut_ = null;
    
    // either data document to verify with sig, or to create (export) signature, created in subclasses
    protected String _strPathAbsFileData_ = null;
     
    protected PTabUICmdKtlKstOpenXmlSigAbs(
        String strHelpID,
        Frame frmOwner,
        String strTitleAppli,
        //String strPropValFileData, // ie document file to sign or verify
        String strPropValFileDataOut // eg: select_file_xxx, ie. digital signature resulting from signing, or as input to verify
        )
    {
        super(
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            true, // blnAllowTypeJks
            true, // blnAllowTypeJceks
            true, // blnAllowTypePkcs12 ==> ?allowed for CSR or cert reply?
            true, // blnAllowTypeBks ==> allowed for CSR or cert reply
            true // blnAllowTypeUber ==> allowed for CSR or cert reply
            );
        
        this._strPropValFileDataIn = PSelBtnTfdFileOpenXml.f_s_strDocPropVal; //strPropValFileData;
        this._strPropValFileDataOut = strPropValFileDataOut;
       
        
        this._pnlSelectFileDataIn_ = new PSelBtnTfdFileOpenXml(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli, 
            (java.awt.event.ItemListener) null,
             "Unsigned" // strLabelPrefix
                );
        
        /*
         *PSelBtnTfdFileSaveXml(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        ItemListener itmListenerParent
         */
    }
    
    protected void _fillInPanelInput_()
    {        
        GridBagConstraints gbc = super._fillInPanelKst_(super._pnlInput_);
        
        gbc.gridy ++;
        super._pnlInput_.add(this._pnlSelectFileDataIn_, gbc);
    }
    
    // -------
    // PRIVATE
    
    
    private String _strPropValFileDataIn = null; 
    private String _strPropValFileDataOut = null; // assigned by subclasses
    
    private void _updateActionButtonDataChanged(boolean blnFieldInserted)
    {        
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;
            
            if (this._strPathAbsFileData_ == null)
                return;
            
            if (this._strPathAbsFileDataOut_ == null)
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
            
            if (this._strPathAbsFileData_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }
            
            if (this._strPathAbsFileDataOut_ == null)
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
