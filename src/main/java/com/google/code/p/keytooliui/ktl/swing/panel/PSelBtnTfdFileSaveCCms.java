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

public final class PSelBtnTfdFileSaveCCms extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_file_cms_crt_save";
    public static final String s_strDirNameDefault = "myccms";      // !!! already defined
    public static final String f_s_strLabel = "CMS Certs-only file:"; // !!! already defined
    
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileCrtCms != null)
            if (this._btnTypeFileCrtCms.isSelected())
                return this._btnTypeFileCrtCms.getFormatFile();
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileCrtCms != null)
        {
            this._btnTypeFileCrtCms.removeItemListener(this);
            this._btnTypeFileCrtCms.destroy();
            this._btnTypeFileCrtCms = null;
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
        
            
        if (! this._btnTypeFileCrtCms.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileSaveCCms(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
  
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 

            PSelBtnTfdFileSaveCCms.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveCCms.f_s_strDocPropVal);
        
       
        
        this._btnTypeFileCrtCms = new RBTypeCrtCms(
            false, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
        this._btnTypeFileCrtCms.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileCCmsCur = _getTypeFileCCmsCur();
                
        if (strsTypeFileCCmsCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileCCmsCur");

        String strFileDesc = _getDescFileCCmsCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
            
        fle = S_FileChooserUI.s_getSaveFile(
            
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileCCmsCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultCCms);
       
        
        
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

    private RBTypeCrtAbs _btnTypeFileCrtCms = null;
    
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
        grouping  PKCS7-[XXX]-[XXX] files
    **/
    private boolean _addGroup()
    {
        String strMethod = "_addGroup()";
        
        // adding radioButtons/labelChecks for selecting in between JAR, and JHR, and RCR files
        
        if (this._btnTypeFileCrtCms == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCCms[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileCrtCms);
        
        // selecting first button
	    this._btnTypeFileCrtCms.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileCCms = new JPanel();
        pnlTypeFileCCms.setLayout(new BoxLayout(pnlTypeFileCCms, BoxLayout.Y_AXIS));
        pnlTypeFileCCms.add(this._btnTypeFileCrtCms); // default
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileCCms);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileCCmsCur()
    {
        String strMethod = "_getTypeFileCCmsCur()";

        
        if (this._btnTypeFileCrtCms.isSelected())
        {
            return this._btnTypeFileCrtCms.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileCCmsCur()
    {
        String strMethod = "_getDescFileCCmsCur()";
        
        if (this._btnTypeFileCrtCms.isSelected())
        {
            return this._btnTypeFileCrtCms.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
