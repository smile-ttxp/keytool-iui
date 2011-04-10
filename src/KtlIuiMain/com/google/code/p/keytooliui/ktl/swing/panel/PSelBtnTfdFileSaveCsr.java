package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    . a group of radioButtons (for switching)
    
    textfield is not editable

    ... used to select a file (not a directory) located in user's system:
    . for saving
    
   
**/



import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.swing.button.*;

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

final public class PSelBtnTfdFileSaveCsr extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_csr_save";
    final static public String s_strDirNameDefault = "mycsrs";      // !!! already defined
    final static public String f_s_strLabel = "CSR file:"; // !!! already defined
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileCsrPkcs10 != null)
            if (this._btnTypeFileCsrPkcs10.isSelected())
                return this._btnTypeFileCsrPkcs10.getFormatFile();
                
        
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileCsrPkcs10 != null)
        {
            this._btnTypeFileCsrPkcs10.destroy();
            this._btnTypeFileCsrPkcs10 = null;
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
        
        if (! this._btnTypeFileCsrPkcs10.init())
            return false;
            
        
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileSaveCsr(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
      
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 
     
            PSelBtnTfdFileSaveCsr.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveCsr.f_s_strDocPropVal);
        
        this._btnTypeFileCsrPkcs10 = new RBTypeCsrPkcs10(
            false, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
        
        
        this._btnTypeFileCsrPkcs10.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileCsrCur = _getTypeFileCsrCur();
                
        if (strsTypeFileCsrCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileCsrCur");

        String strFileDesc = _getDescFileCsrCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
            
        fle = S_FileChooserUI.s_getSaveFile(
            
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileCsrCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultCsr);
       
        
        
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

    private RBTypeCsrAbs _btnTypeFileCsrPkcs10 = null;

    
    private boolean _assignValues(File fle)
    {
        String strMethod = "_assignValues(fle)";

        if (fle == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fle");
            return false;
        }

        // --
        // allow overwriting
        // --
      
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
        
        // --
        // ending
        return true;
    }
    
    /**
        grouping  PKCS10-[XXX]-[XXX] files
    **/
    private boolean _addGroup()
    {
        String strMethod = "_addGroup()";
        
        // adding radioButtons/labelChecks for selecting in between JAR, and JHR, and RCR files
        
        if (this._btnTypeFileCsrPkcs10 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCsrPkcs10");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileCsrPkcs10);
     
        
        // selecting first button
	    this._btnTypeFileCsrPkcs10.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileCsr = new JPanel();
        pnlTypeFileCsr.setLayout(new BoxLayout(pnlTypeFileCsr, BoxLayout.Y_AXIS));
        pnlTypeFileCsr.add(this._btnTypeFileCsrPkcs10);
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileCsr);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileCsrCur()
    {
        String strMethod = "_getTypeFileCsrCur()";
        
        if (this._btnTypeFileCsrPkcs10.isSelected())
        {
            return this._btnTypeFileCsrPkcs10.getNamesFileExtension();
        }    
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileCsrCur()
    {
        String strMethod = "_getDescFileCsrCur()";
        
        if (this._btnTypeFileCsrPkcs10.isSelected())
        {
            return this._btnTypeFileCsrPkcs10.getFileDesc();
        }    
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}