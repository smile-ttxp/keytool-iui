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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable

    ... used to select a file (not a directory) located in user's system:
    . for opening
    . for saving
    
    eg: keytool
    
    
    known subclasses:
    . PSelBtnTfdFileIcnOpen
**/



//import com.google.code.p.keytooliui.shared.io.*;


import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;


import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.io.*;

abstract public class PSelBtnTfdFileIcnAbs extends PSelBtnTfdAbs
{   
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strFileDescIcn = "GIF-JPEG-PNG icons";
    
    // ------
    // public
    
    public void destroy()
    {
        super.destroy();
        
        if (super._tfdCurSelection_ != null)
        {
            ((TFAbstract)super._tfdCurSelection_).destroy();
            super._tfdCurSelection_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected PSelBtnTfdFileIcnAbs(
        DocumentListener docListenerParent,
        Frame frmParent, String strLabel,
        Object objDocPropValue,
        int intMode,
        boolean blnFieldRequired
        )
    {
        super(
            frmParent, 
        
            strLabel,
            blnFieldRequired
            );
        
        this._intMode = intMode; 
        
        if (this._intMode == JFileChooser.SAVE_DIALOG)
            super._btnSelect_ = new BESFileSave16(this);
        else
            super._btnSelect_ = new BESFileOpen16(this);
            
        super._tfdCurSelection_ = new TF30x20SelFile(docListenerParent);
        
        
        String strMethod = "PSelBtnTfdFileIcnAbs(...)";
        
        if (objDocPropValue == null)
            MySystem.s_printOutExit(this, strMethod, "nil objDocPropValue");
        
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, objDocPropValue);
    }
    
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";
        
        if (this._intMode!=JFileChooser.SAVE_DIALOG && this._intMode!=JFileChooser.OPEN_DIALOG)
            MySystem.s_printOutExit(this, strMethod, "this._intMode!=JFileChooser.SAVE_DIALOG && this._intMode!=JFileChooser.OPEN_DIALOG, this._intMode=" + this._intMode);
        
        // tempo
        String _s_strFileOpenButtonTextOk = "Select icon";
        
        String strFileDesc = _f_s_strFileDescIcn;
        
        File fle = null;
        
        if (this._intMode == JFileChooser.OPEN_DIALOG)
        {
            fle = S_FileChooserAbs.s_getOpenFile(
                super._frmParent_, _s_strFileOpenButtonTextOk,
                S_FileExtension.f_s_strsImage, strFileDesc);
        }
        
        else
        {
            MySystem.s_printOutExit(strMethod, "CODE ERROR, NOT DONE YET: (this._intMode != JFileChooser.OPEN_DIALOG)");
            
            /*
            fle = S_FileChooserAbs.s_getSaveFile(
                super._frmParent_, _s_strFileOpenButtonTextOk,
                S_FileExtension.f_s_strsImage, strFileDesc
                );*/
              
        }
        
        if (fle == null)
        {
            // cancelled
            return;
        }
    		
		if (! _assignValues(fle))
		    MySystem.s_printOutExit(this, strMethod, "failed, fle.getName()=" + fle.getName());
    }
    
    
    // -------
    // PRIVATE
    
    private int _intMode = 0; // should be assigned by subclasses: SAVE_DIALOG or OPEN_DIALOG
        
    private boolean _assignValues(File fle)
    {
        String strMethod = "_assignValues(fle)";

        if (fle == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fle");
            return false;
        }

        if (this._intMode == JFileChooser.SAVE_DIALOG)
        {
            if (fle.exists())
            {
                MySystem.s_printOutWarning(this, strMethod, "fle.exists(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
                
                String strBody = fle.getAbsolutePath();
                strBody += ":\nFile already exists.";
                
                OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
                return true;
            }
        }
        
        else if (this._intMode == JFileChooser.OPEN_DIALOG)
        {
            if (! fle.exists())
            {
                MySystem.s_printOutWarning(this, strMethod, "! fle.exists(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
                
                String strBody = fle.getAbsolutePath();
                strBody += ":\nFile not found.";
                
                OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
                
                return true;
            }
            
            if (fle.isDirectory())
            {
                MySystem.s_printOutWarning(this, strMethod, "fle.isDirectory(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
                
                String strBody = fle.getAbsolutePath();
                strBody += ":\nFile is a directory.";
                
                OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
                
                return true;
            }
        }
      
        if (super._tfdCurSelection_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._tfdCurSelection_");
            return false;
        }
        
        super._tfdCurSelection_.setText(fle.getAbsolutePath());
        super._setSelectedValue_(true);
        
         if (super._btnClearSelection_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._btnClearSelection_");
            return false;
        }
        
        super._btnClearSelection_.setEnabled(true);
        
        // ending
        return true;
    }
}