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
    "Jsr" means JarSigner


    known subclasses:
  
    . PTabUICmdJsrSign
    . PTabUICmdJsrVerify
**/

import com.google.code.p.keytooliui.ktl.io.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

abstract public class PTabUICmdJsrAbs extends PTabUICmdAbs implements
    ItemListener
{       
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void itemStateChanged(ItemEvent evtItem);
    
    // ------------------
    // ABSTRACT PROTECTED
    
    abstract protected void _updateActionButtonDataChanged_(boolean blnFieldInserted);
    
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
        
        if (this._fssSelectSignedJar_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._fssSelectSignedJar_");
            return false;
        }
        
        if (! this._fssSelectSignedJar_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // -------------
        // add listeners
        
        /*if (! this._fssSelectSignedJar_.addDocListener(this))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/

        return true;
    }
    
    
    public void destroy() 
    {
        super.destroy();
        
        if (this._fssSelectSignedJar_ != null)
        {
            this._fssSelectSignedJar_.destroy();
            this._fssSelectSignedJar_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected String _strPathAbsJarSigned_ = null;
    
    
    // either input or output, depends on subclass
    protected PSelBtnTfdFileJarAbs _fssSelectSignedJar_ = null;
    
    // action
    
    protected boolean _doneInsertUpdate_(String strPropVal, String strText)
    {
        if (strPropVal.compareTo(PSelBtnTfdFileJarOpenSigned.f_s_strDocPropVal) == 0 ||
            strPropVal.compareTo(PSelBtnTfdFileJarSaveSigned.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsJarSigned_ = strText;
            _updateActionButtonDataChanged_(true);
            return true;
        }
        
        return false;
    }
    
    protected boolean _doneRemoveUpdate_(String strPropVal)
    {
        if (strPropVal.compareTo(PSelBtnTfdFileJarOpenSigned.f_s_strDocPropVal) == 0 ||
            strPropVal.compareTo(PSelBtnTfdFileJarSaveSigned.f_s_strDocPropVal) == 0)
        {
            this._strPathAbsJarSigned_ = null;
            _updateActionButtonDataChanged_(false);
            return true;
        }
        
        return false;
    }
    
    protected PTabUICmdJsrAbs(
        Frame frmOwner, 
        String strTitleAppli, 
        String strHelpID,
        boolean blnFileSignedJarSave, // if "true" means "sign file", else if false means "verify signed file"
        boolean blnFieldRequiredKeystore
        )
    {
        super(
            //"String strTitleTab", // TEMPO
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            "Source", // strLabelBorderPanelIn, nil value allowed
            "Target" // strLabelBorderPanelOut nil value allowed
            );
        
        // ----
        
        super._pnlSelectFileKst_ = new PSelBtnTfdFileOpenKst(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli,
            (ItemListener) null,
            blnFieldRequiredKeystore,
            true, // blnAllowTypeJks
            true, // blnAllowTypeJceks
            true, // blnAllowTypePkcs12
            true, // blnAllowTypeBks
            true // blnAllowTypeUber
            ); 
           
        // ------
        
        if (blnFileSignedJarSave) // eg: sign file
        {
            this._fssSelectSignedJar_ = new PSelBtnTfdFileJarSaveSigned(
                (javax.swing.event.DocumentListener) this,
                frmOwner, 
                strTitleAppli, 
                S_FileExtensionUI.f_s_strDirNameDefaultFileSigned,
                true // blnFieldRequired
            );
        }
        
        else // eg: verify signed file
        {
            this._fssSelectSignedJar_ = new PSelBtnTfdFileJarOpenSigned(
                (javax.swing.event.DocumentListener) this,
                frmOwner, 
                strTitleAppli,
                (ItemListener) this,
                S_FileExtensionUI.f_s_strDirNameDefaultFileSigned,
                true // blnFieldRequired
            );
        }
        
        
            
        
    }
    
    
    
    // -------
    // PRIVATE
}