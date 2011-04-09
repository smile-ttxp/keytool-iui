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

public final class PSelBtnTfdFileSaveCrts extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_file_crts_save";
    public static final String s_strDirNameDefault = "mycrtss";      // !!! already defined
    public static final String f_s_strLabel = "Certificates chain file:"; // !!! already defined
    
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileCrtDer != null)
            if (this._btnTypeFileCrtDer.isSelected())
                return this._btnTypeFileCrtDer.getFormatFile();
     
        if (this._btnTypeFileCrtPkcs7 != null)
            if (this._btnTypeFileCrtPkcs7.isSelected())
                return this._btnTypeFileCrtPkcs7.getFormatFile();
                
        if (this._btnTypeFileCrtPkcs7Prt != null)
            if (this._btnTypeFileCrtPkcs7Prt.isSelected())
                return this._btnTypeFileCrtPkcs7Prt.getFormatFile();
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileCrtPkcs7 != null)
        {
            this._btnTypeFileCrtPkcs7.removeItemListener(this);
            this._btnTypeFileCrtPkcs7.destroy();
            this._btnTypeFileCrtPkcs7 = null;
        }
        
        if (this._btnTypeFileCrtPkcs7Prt != null)
        {
            this._btnTypeFileCrtPkcs7Prt.removeItemListener(this);
            this._btnTypeFileCrtPkcs7Prt.destroy();
            this._btnTypeFileCrtPkcs7Prt = null;
        }
        
        if (this._btnTypeFileCrtDer != null)
        {
            this._btnTypeFileCrtDer.removeItemListener(this);
            this._btnTypeFileCrtDer.destroy();
            this._btnTypeFileCrtDer = null;
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
        
        if (this._btnTypeFileCrtPkcs7 != null)
            if (! this._btnTypeFileCrtPkcs7.init())
                return false;
        
        if (this._btnTypeFileCrtPkcs7Prt != null)
            if (! this._btnTypeFileCrtPkcs7Prt.init())
                return false;
            
        if (! this._btnTypeFileCrtDer.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileSaveCrts(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
       
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 
  
            PSelBtnTfdFileSaveCrts.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveCrts.f_s_strDocPropVal);
        
       
        
        this._btnTypeFileCrtDer = new RBTypeCrtDer(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
         /*this._btnTypeFileCrtPkcs7 = new RBTypeCrtPkcs7(
            true, 
            itmListenerParent);*/
            
         this._btnTypeFileCrtPkcs7Prt = new RBTypeCrtPem(
            true, 
            itmListenerParent);
        
        if (this._btnTypeFileCrtPkcs7 != null)
            this._btnTypeFileCrtPkcs7.addItemListener(this);
        
        if (this._btnTypeFileCrtPkcs7Prt != null)
            this._btnTypeFileCrtPkcs7Prt.addItemListener(this);
        
        this._btnTypeFileCrtDer.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileCrtCur = _getTypeFileCrtCur();
                
        if (strsTypeFileCrtCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileCrtCur");

        String strFileDesc = _getDescFileCrtCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
            
        fle = S_FileChooserUI.s_getSaveFile(
            
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileCrtCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultCrt);
       
        
        
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

    private RBTypeCrtAbs _btnTypeFileCrtPkcs7 = null;
    private RBTypeCrtAbs _btnTypeFileCrtPkcs7Prt = null;
    private RBTypeCrtAbs _btnTypeFileCrtDer = null;
    
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
        
        if (this._btnTypeFileCrtDer == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCrt[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        
        if (this._btnTypeFileCrtPkcs7 != null)
            bgp.add(this._btnTypeFileCrtPkcs7);
        
        if (this._btnTypeFileCrtPkcs7Prt != null)
            bgp.add(this._btnTypeFileCrtPkcs7Prt);
        
        bgp.add(this._btnTypeFileCrtDer);
        
        // selecting first button
	this._btnTypeFileCrtDer.setSelected(true);
       
        if (bgp.getButtonCount() < 2)
            this._btnTypeFileCrtDer.setEnabled(false);
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileCrt = new JPanel();
        pnlTypeFileCrt.setLayout(new BoxLayout(pnlTypeFileCrt, BoxLayout.Y_AXIS));
        pnlTypeFileCrt.add(this._btnTypeFileCrtDer); // default
        
        if (this._btnTypeFileCrtPkcs7 != null)
            pnlTypeFileCrt.add(this._btnTypeFileCrtPkcs7);
        
        if (this._btnTypeFileCrtPkcs7Prt != null)
            pnlTypeFileCrt.add(this._btnTypeFileCrtPkcs7Prt);
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileCrt);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileCrtCur()
    {
        String strMethod = "_getTypeFileCrtCur()";
        
        if (this._btnTypeFileCrtPkcs7 != null)
        {
            if (this._btnTypeFileCrtPkcs7.isSelected())
            {
                return this._btnTypeFileCrtPkcs7.getNamesFileExtension();
            }
        }
        
        if (this._btnTypeFileCrtPkcs7Prt != null)
        {
            if (this._btnTypeFileCrtPkcs7Prt.isSelected())
            {
                return this._btnTypeFileCrtPkcs7Prt.getNamesFileExtension();
            }
        }
        
        if (this._btnTypeFileCrtDer.isSelected())
        {
            return this._btnTypeFileCrtDer.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileCrtCur()
    {
        String strMethod = "_getDescFileCrtCur()";
        
        if (this._btnTypeFileCrtPkcs7 != null)
        {
            if (this._btnTypeFileCrtPkcs7.isSelected())
            {
                return this._btnTypeFileCrtPkcs7.getFileDesc();
            }
        }
        
        if (this._btnTypeFileCrtPkcs7Prt != null)
        {
            if (this._btnTypeFileCrtPkcs7Prt.isSelected())
            {
                return this._btnTypeFileCrtPkcs7Prt.getFileDesc();
            }
        }
        
        if (this._btnTypeFileCrtDer.isSelected())
        {
            return this._btnTypeFileCrtDer.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
