/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
    "Xml"
    memo: no keystores

    known subclasses:
  
    . PTabUICmdXmlVerify
**/

import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public abstract class PTabUICmdXmlAbs extends PTabUICmdAbs implements
    ItemListener
{       
    // ---------------
    // ABSTRACT PUBLIC
    
    public abstract void itemStateChanged(ItemEvent evtItem);
    
    // ------------------
    // ABSTRACT PROTECTED
    
    protected abstract void _updateActionButtonDataChanged_(boolean blnFieldInserted);
    
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
        
        if (this._fssSelectSignedXml_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._fssSelectSignedXml_");
            return false;
        }
        
        if (! this._fssSelectSignedXml_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // -------------
        // add listeners


        return true;
    }
    
    
    public void destroy() 
    {
        super.destroy();
        
        if (this._fssSelectSignedXml_ != null)
        {
            this._fssSelectSignedXml_.destroy();
            this._fssSelectSignedXml_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsXmlSigned_ = null;
    
    
    // either input or output, depends on subclass
    protected PSelBtnTfdFileOpenXml _fssSelectSignedXml_ = null;
    
    // action
    
    protected boolean _doneInsertUpdate_(String strPropVal, String strText)
    {
        if (strPropVal.compareTo(PSelBtnTfdFileOpenXml.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsXmlSigned_ = strText;
            _updateActionButtonDataChanged_(true);
            return true;
        }
        
        return false;
    }
    
    protected boolean _doneRemoveUpdate_(String strPropVal)
    {
        if (strPropVal.compareTo(PSelBtnTfdFileOpenXml.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsXmlSigned_ = null;
            _updateActionButtonDataChanged_(false);
            return true;
        }
        
        return false;
    }
    
    protected PTabUICmdXmlAbs(
        Frame frmOwner, 
      
        String strHelpID,
        boolean blnFieldRequiredKeystore
        )
    {
        super(
            strHelpID, 
            frmOwner, 
       
            "Source", // strLabelBorderPanelIn, nil value allowed
            (String) null // "Target:" // strLabelBorderPanelOut nil value allowed
            );
        
        // ----
        
        
        this._fssSelectSignedXml_ = new PSelBtnTfdFileOpenXml(
                (javax.swing.event.DocumentListener) this,
                frmOwner, 
         
                (ItemListener) null,
                "Signed" // strLabelPrefix
            );
    
        
            
        
    }
    
    
    
    // -------
    // PRIVATE
}
