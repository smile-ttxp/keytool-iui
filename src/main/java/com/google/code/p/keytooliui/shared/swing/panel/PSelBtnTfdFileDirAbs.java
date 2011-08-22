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
    known subclasses:
    . PSelBtnTfdFileDirSave
    
    
    
    
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable

    ... used to select a directory (not a file) located in user's system:
    . for opening
    . for saving
    
    
    
    
**/



import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.io.*;

public abstract class PSelBtnTfdFileDirAbs extends PSelBtnTfdAbs
{   
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strFileDescDir = "directories";
    
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
    
    protected PSelBtnTfdFileDirAbs(
        DocumentListener docListenerParent,
        Frame frmParent, String strLabel,
        Object objDocPropValue,
        int intMode,
        String strDirAppli, // eg: xlb, xls, dpl
        String strDirSub, // eg: mydeploys
        boolean blnFieldRequired
        )
    {
        super(
            frmParent, 
         
            strLabel,
            blnFieldRequired
            );
        
        this._intMode = intMode; 
        
        this._strDirAppli = strDirAppli;
        this._strDirSub = strDirSub;
        
        if (this._intMode == JFileChooser.SAVE_DIALOG)
            super._btnSelect_ = new BESFileSave16(this);
        else
            super._btnSelect_ = new BESFileOpen16(this);
        
        
        super._tfdCurSelection_ = new Tfd30x30SelFile(docListenerParent);
        
        String strMethod = "PSelBtnTfdFileDirAbs(...)";
        
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
        String _s_strFileOpenButtonTextOk = "Select directory";
        
        // tempo
        String[] strsFileExtension = {""};
        
        
        File fle = null;
        
        if (this._intMode == JFileChooser.OPEN_DIALOG)
        {
            //fle = _getFileOpen();
            MySystem.s_printOutExit(this, strMethod, "CODING ERROR: should not get this: this._intMode == JFileChooser.OPEN_DIALOG");
        }
        
        else
        {
            fle = _getFileSave();
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
    
    private String _strDirAppli = null;
    private String _strDirSub = null;
    
    private int _intMode = 0; // should be assigned by subclasses: SAVE_DIALOG or OPEN_DIALOG
    
    // MEMO: unused!, no derived class XXOpen
    /**private File _getFileOpen()
    {
        String strMethod = "_getFileOpen()";
        
        JFileChooser fcr = new JFileChooser();
        fcr.setDialogType(JFileChooser.OPEN_DIALOG);
        fcr.setApproveButtonText("select dir");
        fcr.setAcceptAllFileFilterUsed(true);
        fcr.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fcr.setMultiSelectionEnabled(false);
        
        
	    int retval = fcr.showDialog(super._frmParent_, null);
	    
	    if (retval != JFileChooser.APPROVE_OPTION)
	        return null;
	    
	    File fle = fcr.getSelectedFile();
	    
	    if (fle == null)
	    {
	        return null;
	    }
	    
	    if (! fle.exists())
	    {
	        MySystem.s_printOutWarning(this, strMethod, "! fle.exists()");
	        return null;
	    }
	    
	    return fle;
    }**/
    
    private File _getFileSave()
    {
        String strMethod = "_getFileSave()";
        
        File fleApplicationHome = com.google.code.p.keytooliui.shared.io.S_FileSys.s_getPathAbsParentAppli(
            false
            );
	    
	    if (fleApplicationHome == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil fleApplicationHome");
	        return null;
	    }
	    
	    
	    /*return S_FileChooserAbs.s_getNewDirectoryEmpty(
            fleApplicationHome.getAbsolutePath(),
         
            "select",
            super._frmParent_,
            "Select new folder",
            "click to select new folder",
            this._strDirAppli, // eg: xlb, xls, din
            this._strDirSub // eg: mydeploys
            );
            */
            
            
            
            
        return S_FileChooserAbs.s_getNewDirectoryEmpty(
            this._strDirAppli, // eg: xlb, xls
            this._strDirSub, // eg: mydeploys
           
            "select",
            super._frmParent_,
            "Select new folder",
            "click to select new folder"
            );
    }
        
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
            
            if (! fle.isDirectory())
            {
                MySystem.s_printOutWarning(this, strMethod, "! fle.isDirectory(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
                
                String strBody = fle.getAbsolutePath();
                strBody += ":\nFile is not a directory.";
                
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