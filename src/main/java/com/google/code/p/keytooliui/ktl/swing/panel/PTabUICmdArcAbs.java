/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 *
 * @author bantchao
 */
/**
    "Arc" means Archive


    known subclasses:
  
    . PTabUICmdArcDir
    . PTabUICmdArcFile(s) ==> pending
**/

import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;


import java.awt.event.*;
import java.awt.*;

public abstract class PTabUICmdArcAbs extends PTabUICmdAbs implements
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
        
        if (this._fssSelectUnsignedJar2Save_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._fssSelectUnsignedJar2Save_");
            return false;
        }
        
        if (! this._fssSelectUnsignedJar2Save_.init())
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
        
        if (this._fssSelectUnsignedJar2Save_ != null)
        {
            this._fssSelectUnsignedJar2Save_.destroy();
            this._fssSelectUnsignedJar2Save_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsUnsignedJar2Save_ = null;
    
    
    // either input or output, depends on subclass
    protected PSelBtnTfdFileJarAbs _fssSelectUnsignedJar2Save_ = null;
    
    // action
    
    protected boolean _doneInsertUpdate_(String strPropVal, String strText)
    {
        if (strPropVal.compareTo(PSelBtnTfdFileJarSaveUnsigned.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsUnsignedJar2Save_ = strText;
            _updateActionButtonDataChanged_(true);
            return true;
        }
        
        return false;
    }
    
    protected boolean _doneRemoveUpdate_(String strPropVal)
    {
        if (strPropVal.compareTo(PSelBtnTfdFileJarSaveUnsigned.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsUnsignedJar2Save_ = null;
            _updateActionButtonDataChanged_(false);
            return true;
        }
        
        return false;
    }
    
    protected PTabUICmdArcAbs(
        Frame frmOwner, 
 
        String strHelpID
        )
    {
        super(
          
            strHelpID, 
            frmOwner, 
  
            "Source", // strLabelBorderPanelIn, nil value allowed
            "Target" // strLabelBorderPanelOut nil value allowed
            );

        this._fssSelectUnsignedJar2Save_ = new PSelBtnTfdFileJarSaveUnsigned(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
    
            S_FileExtensionUI.f_s_strDirNameDefaultFileSigned,
            true // blnFieldRequired
        );
        
        
        
            
        
    }
    
    
    
    // -------
    // PRIVATE
}