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
    "Ktl" means Keytool


    known subclasses:
  
    . PTabUICmdKtlKstOpenKprExpAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.awt.*;

abstract public class PTabUICmdKtlKstOpenKprAbs extends PTabUICmdKtlKstOpenAbs 
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
      
        // input
        
        if (strPropVal.compareTo(this._strPropValFileKpr) == 0)
        {
            this._strPathAbsFileKpr_ = strText;
            _updateActionButtonDataChanged_(true);
            return;
        }
        
        if (strPropVal.compareTo(this._strPropValFileCrts) == 0)
        {
            this._strPathAbsFileCrts_ = strText;
            _updateActionButtonDataChanged_(true);
            return;
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
        
        if (strPropVal.compareTo(this._strPropValFileKpr) == 0)
        {
            this._strPathAbsFileKpr_ = null;
            _updateActionButtonDataChanged_(false);
            return;
        }
        
        
        if (strPropVal.compareTo(this._strPropValFileCrts) == 0)
        {
            this._strPathAbsFileCrts_ = null;
            _updateActionButtonDataChanged_(false);
            return;
        }

        
        if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            _updateActionButtonDataChanged_(false);
            return;
        }
        
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
        
        if (this._pnlSelectFileCrts_ != null)
        {
            this._pnlSelectFileCrts_.destroy();
            this._pnlSelectFileCrts_ = null;
        }

        
        if (this._pnlSelectFileKpr_ != null)
        {
            this._pnlSelectFileKpr_.destroy();
            this._pnlSelectFileKpr_ = null;
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
        
        if (this._pnlSelectFileKpr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectFileKpr_");
            return false;
        }
        
        if (! this._pnlSelectFileKpr_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        if (this._pnlSelectFileCrts_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlSelectFileCrts_");
            return false;
        }
        
        if (! this._pnlSelectFileCrts_.init())
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
    

    

    protected PSelAbs _pnlSelectFileKpr_ = null;
    protected PSelAbs _pnlSelectFileCrts_ = null;
    protected String _strPathAbsFileKpr_ = null;
    protected String _strPathAbsFileCrts_ = null;
     
    protected PTabUICmdKtlKstOpenKprAbs(
        String strHelpID,
        Frame frmOwner,
        String strTitleAppli,
        String strPropValFileKpr, // eg: select_file_[xxx]
        String strPropValFileCrts
        )
    {
        super(
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            true, // blnAllowTypeJks
            true, // blnAllowTypeJceks
            true, // blnAllowTypePkcs12 ==> ?allowed for C reply?
            true, // blnAllowTypeBks ==> allowed for  reply
            true // blnAllowTypeUber ==> allowed for  reply
            );
            
        this._strPropValFileKpr = strPropValFileKpr;
        this._strPropValFileCrts = strPropValFileCrts;
    }
    
    // -------
    // PRIVATE
    
    private String _strPropValFileKpr = null; // assigned by subclasses
    private String _strPropValFileCrts = null; // assigned by subclasses
    
    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {        
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;
            
            if (this._strPathAbsFileCrts_ == null)
                return;
            
            if (this._strPathAbsFileKpr_ == null)
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
            
            if (this._strPathAbsFileKpr_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }
            
            if (this._strPathAbsFileCrts_ == null)
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
