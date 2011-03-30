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

final public class PSelBtnTfdFileSaveShk extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_shk_save";
    final static public String s_strDirNameDefault = "myshks";      // !!! already defined
    final static public String f_s_strLabel = "Secret key file:"; // !!! already defined
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileShkDer != null)
            if (this._btnTypeFileShkDer.isSelected())
                return this._btnTypeFileShkDer.getFormatFile();
                
        if (this._btnTypeFileShkPem != null)
            if (this._btnTypeFileShkPem.isSelected())
                return this._btnTypeFileShkPem.getFormatFile();
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        
        if (this._btnTypeFileShkPem != null)
        {
            this._btnTypeFileShkPem.removeItemListener(this);
            this._btnTypeFileShkPem.destroy();
            this._btnTypeFileShkPem = null;
        }
        
        if (this._btnTypeFileShkDer != null)
        {
            this._btnTypeFileShkDer.removeItemListener(this);
            this._btnTypeFileShkDer.destroy();
            this._btnTypeFileShkDer = null;
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

            
        if (this._btnTypeFileShkPem != null)
        {
            if (! this._btnTypeFileShkPem.init())
                return false;
        } 
        
        if (! this._btnTypeFileShkDer.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileSaveShk(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            PSelBtnTfdFileSaveShk.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveShk.f_s_strDocPropVal);
        
       
        
        this._btnTypeFileShkDer = new RBTypeShkDer(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
            
         this._btnTypeFileShkPem = new RBTypeShkPem(
            true, 
            itmListenerParent);

        
        if (this._btnTypeFileShkPem != null)
            this._btnTypeFileShkPem.addItemListener(this);
        
        this._btnTypeFileShkDer.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileShkCur = _getTypeFileShkCur();
                
        if (strsTypeFileShkCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileShkCur");

        String strFileDesc = _getDescFileShkCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
            
        fle = S_FileChooserUI.s_getSaveFile(
            super._strTitleAppli_, 
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileShkCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultShk);
       
        
        
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

    private RBTypeShkAbs _btnTypeFileShkPem = null;
    private RBTypeShkAbs _btnTypeFileShkDer = null;
    
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
        
        if (this._btnTypeFileShkDer==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileShk[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        //bgp.add(this._btnTypeFileShkPkcs7);
        
        if (this._btnTypeFileShkPem != null)
            bgp.add(this._btnTypeFileShkPem);
        
        bgp.add(this._btnTypeFileShkDer);
        
        
        if (bgp.getButtonCount() < 2)
        {
            this._btnTypeFileShkDer.setEnabled(false);
        }
        
        // selecting first button
	this._btnTypeFileShkDer.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileShk = new JPanel();
        pnlTypeFileShk.setLayout(new BoxLayout(pnlTypeFileShk, BoxLayout.Y_AXIS));
        pnlTypeFileShk.add(this._btnTypeFileShkDer); // default
        
        if (this._btnTypeFileShkPem != null)
            pnlTypeFileShk.add(this._btnTypeFileShkPem);
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	super._pnl_.add(pnlTypeFileShk);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileShkCur()
    {
        String strMethod = "_getTypeFileShkCur()";
        

        
        if (this._btnTypeFileShkPem != null)
        {
            if (this._btnTypeFileShkPem.isSelected())
            {
                return this._btnTypeFileShkPem.getNamesFileExtension();
            }
        }
        
        if (this._btnTypeFileShkDer.isSelected())
        {
            return this._btnTypeFileShkDer.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileShkCur()
    {
        String strMethod = "_getDescFileShkCur()";

        
        if (this._btnTypeFileShkPem != null)
        {
            if (this._btnTypeFileShkPem.isSelected())
            {
                return this._btnTypeFileShkPem.getFileDesc();
            }
        }
        
        if (this._btnTypeFileShkDer.isSelected())
        {
            return this._btnTypeFileShkDer.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
