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

public final class PSelBtnTfdFileOpenSig extends PSelBtnTfdFileOpenAbs
{   
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_file_sig_open";
    public static final String s_strDirNameDefault = "mysigs";
    public static final String f_s_strLabel = "(detached) Signature file:"; 
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileSigDer != null)
            if (this._btnTypeFileSigDer.isSelected())
                return this._btnTypeFileSigDer.getFormatFile();
     
        if (this._btnTypeFileSigPkcs7 != null)
            if (this._btnTypeFileSigPkcs7.isSelected())
                return this._btnTypeFileSigPkcs7.getFormatFile();
                
        if (this._btnTypeFileSigPkcs7Prt != null)
            if (this._btnTypeFileSigPkcs7Prt.isSelected())
                return this._btnTypeFileSigPkcs7Prt.getFormatFile();
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileSigPkcs7 != null)
        {
            this._btnTypeFileSigPkcs7.removeItemListener(this);
            this._btnTypeFileSigPkcs7.destroy();
            this._btnTypeFileSigPkcs7 = null;
        }
        
        if (this._btnTypeFileSigPkcs7Prt != null)
        {
            this._btnTypeFileSigPkcs7Prt.removeItemListener(this);
            this._btnTypeFileSigPkcs7Prt.destroy();
            this._btnTypeFileSigPkcs7Prt = null;
        }
        
        if (this._btnTypeFileSigDer != null)
        {
            this._btnTypeFileSigDer.removeItemListener(this);
            this._btnTypeFileSigDer.destroy();
            this._btnTypeFileSigDer = null;
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
        
        if (! this._btnTypeFileSigPkcs7.init())
            return false;
            
        if (! this._btnTypeFileSigPkcs7Prt.init())
            return false;
            
        if (! this._btnTypeFileSigDer.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileOpenSig(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
     
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 

            PSelBtnTfdFileOpenSig.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileOpenSig.f_s_strDocPropVal);
        
       
        
        this._btnTypeFileSigDer = new RBTypeSigDer(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
         this._btnTypeFileSigPkcs7 = new RBTypeSigPkcs7(
            true, 
            itmListenerParent);
            
         this._btnTypeFileSigPkcs7Prt = new RBTypeSigPem(
            true, 
            itmListenerParent);
        
        
        this._btnTypeFileSigPkcs7.addItemListener(this);
        this._btnTypeFileSigPkcs7Prt.addItemListener(this);
        this._btnTypeFileSigDer.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileSigCur = _getTypeFileSigCur();
                
        if (strsTypeFileSigCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileSigCur");

        String strFileDesc = _getDescFileSigCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
      
        String strButtonTextOk = "Open file";
            
        File fle = S_FileChooserUI.s_getOpenFile(
            
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileSigCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultSig);
       
        
        
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

    private RBTypeSigAbs _btnTypeFileSigPkcs7 = null;
    private RBTypeSigAbs _btnTypeFileSigPkcs7Prt = null;
    private RBTypeSigAbs _btnTypeFileSigDer = null;
    
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
        
        if (this._btnTypeFileSigPkcs7Prt==null || this._btnTypeFileSigPkcs7==null || this._btnTypeFileSigDer==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileSig[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileSigPkcs7);
        bgp.add(this._btnTypeFileSigPkcs7Prt);
        bgp.add(this._btnTypeFileSigDer);
        
        // selecting first button
	    this._btnTypeFileSigDer.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileSig = new JPanel();
        pnlTypeFileSig.setLayout(new BoxLayout(pnlTypeFileSig, BoxLayout.Y_AXIS));
        pnlTypeFileSig.add(this._btnTypeFileSigDer); // default
        pnlTypeFileSig.add(this._btnTypeFileSigPkcs7);
        pnlTypeFileSig.add(this._btnTypeFileSigPkcs7Prt);
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileSig);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileSigCur()
    {
        String strMethod = "_getTypeFileSigCur()";
        
        if (this._btnTypeFileSigPkcs7.isSelected())
        {
            return this._btnTypeFileSigPkcs7.getNamesFileExtension();
        }  
        
        if (this._btnTypeFileSigPkcs7Prt.isSelected())
        {
            return this._btnTypeFileSigPkcs7Prt.getNamesFileExtension();
        }    
        
        if (this._btnTypeFileSigDer.isSelected())
        {
            return this._btnTypeFileSigDer.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileSigCur()
    {
        String strMethod = "_getDescFileSigCur()";
        
        if (this._btnTypeFileSigPkcs7.isSelected())
        {
            return this._btnTypeFileSigPkcs7.getFileDesc();
        }   
        
        if (this._btnTypeFileSigPkcs7Prt.isSelected())
        {
            return this._btnTypeFileSigPkcs7Prt.getFileDesc();
        } 
        
        if (this._btnTypeFileSigDer.isSelected())
        {
            return this._btnTypeFileSigDer.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
