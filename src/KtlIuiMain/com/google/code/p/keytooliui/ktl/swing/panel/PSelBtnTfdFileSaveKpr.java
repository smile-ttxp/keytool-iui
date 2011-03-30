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

final public class PSelBtnTfdFileSaveKpr extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_kpr_save";
    final static public String s_strDirNameDefault = "mykprs";      // !!! already defined
    final static public String f_s_strLabel = "Private key file:"; // !!! already defined
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileKprDer != null)
            if (this._btnTypeFileKprDer.isSelected())
                return this._btnTypeFileKprDer.getFormatFile();
                
        if (this._btnTypeFileKprPem != null)
            if (this._btnTypeFileKprPem.isSelected())
                return this._btnTypeFileKprPem.getFormatFile();
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        
        if (this._btnTypeFileKprPem != null)
        {
            this._btnTypeFileKprPem.removeItemListener(this);
            this._btnTypeFileKprPem.destroy();
            this._btnTypeFileKprPem = null;
        }
        
        if (this._btnTypeFileKprDer != null)
        {
            this._btnTypeFileKprDer.removeItemListener(this);
            this._btnTypeFileKprDer.destroy();
            this._btnTypeFileKprDer = null;
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

            
        if (this._btnTypeFileKprPem != null)
        {
            if (! this._btnTypeFileKprPem.init())
                return false;
        } 
        
        if (! this._btnTypeFileKprDer.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileSaveKpr(
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
            PSelBtnTfdFileSaveKpr.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveKpr.f_s_strDocPropVal);
        
       
        
        this._btnTypeFileKprDer = new RBTypeKprDer(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
            
         this._btnTypeFileKprPem = new RBTypeKprPem(
            true, 
            itmListenerParent);

        
        if (this._btnTypeFileKprPem != null)
            this._btnTypeFileKprPem.addItemListener(this);
        
        this._btnTypeFileKprDer.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileKprCur = _getTypeFileKprCur();
                
        if (strsTypeFileKprCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileKprCur");

        String strFileDesc = _getDescFileKprCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
            
        fle = S_FileChooserUI.s_getSaveFile(
            super._strTitleAppli_, 
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileKprCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultKpr);
       
        
        
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

    private RBTypeKprAbs _btnTypeFileKprPem = null;
    private RBTypeKprAbs _btnTypeFileKprDer = null;
    
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
        
        if (this._btnTypeFileKprDer==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileKpr[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        //bgp.add(this._btnTypeFileKprPkcs7);
        
        if (this._btnTypeFileKprPem != null)
            bgp.add(this._btnTypeFileKprPem);
        
        bgp.add(this._btnTypeFileKprDer);
        
        
        if (bgp.getButtonCount() < 2)
        {
            this._btnTypeFileKprDer.setEnabled(false);
        }
        
        // selecting first button
	this._btnTypeFileKprDer.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileKpr = new JPanel();
        pnlTypeFileKpr.setLayout(new BoxLayout(pnlTypeFileKpr, BoxLayout.Y_AXIS));
        pnlTypeFileKpr.add(this._btnTypeFileKprDer); // default
        
        if (this._btnTypeFileKprPem != null)
            pnlTypeFileKpr.add(this._btnTypeFileKprPem);
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	super._pnl_.add(pnlTypeFileKpr);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileKprCur()
    {
        String strMethod = "_getTypeFileKprCur()";
        

        
        if (this._btnTypeFileKprPem != null)
        {
            if (this._btnTypeFileKprPem.isSelected())
            {
                return this._btnTypeFileKprPem.getNamesFileExtension();
            }
        }
        
        if (this._btnTypeFileKprDer.isSelected())
        {
            return this._btnTypeFileKprDer.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileKprCur()
    {
        String strMethod = "_getDescFileKprCur()";

        
        if (this._btnTypeFileKprPem != null)
        {
            if (this._btnTypeFileKprPem.isSelected())
            {
                return this._btnTypeFileKprPem.getFileDesc();
            }
        }
        
        if (this._btnTypeFileKprDer.isSelected())
        {
            return this._btnTypeFileKprDer.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
