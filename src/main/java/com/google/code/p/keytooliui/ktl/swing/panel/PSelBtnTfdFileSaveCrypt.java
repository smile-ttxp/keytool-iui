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

public final class PSelBtnTfdFileSaveCrypt extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_file_crypt_save";
    public static final String s_strDirNameDefault = "mycrypts";
    public static final String f_s_strLabel = "Encrypted data file:"; 
    
    // ------
    // PUBLIC
    
    /*public String getSelectedFormatFile()
    {
        if (this._btnTypeFileCryptDer != null)
            if (this._btnTypeFileCryptDer.isSelected())
                return this._btnTypeFileCryptDer.getFormatFile();
     
        if (this._btnTypeFileCryptPkcs7 != null)
            if (this._btnTypeFileCryptPkcs7.isSelected())
                return this._btnTypeFileCryptPkcs7.getFormatFile();
                
        if (this._btnTypeFileCryptPkcs7Prt != null)
            if (this._btnTypeFileCryptPkcs7Prt.isSelected())
                return this._btnTypeFileCryptPkcs7Prt.getFormatFile();
                
        return null;
    }*/
    
    public void destroy()
    {
        super.destroy();
        
        /*if (this._btnTypeFileCryptPkcs7 != null)
        {
            this._btnTypeFileCryptPkcs7.removeItemListener(this);
            this._btnTypeFileCryptPkcs7.destroy();
            this._btnTypeFileCryptPkcs7 = null;
        }
        
        if (this._btnTypeFileCryptPkcs7Prt != null)
        {
            this._btnTypeFileCryptPkcs7Prt.removeItemListener(this);
            this._btnTypeFileCryptPkcs7Prt.destroy();
            this._btnTypeFileCryptPkcs7Prt = null;
        }
        
        if (this._btnTypeFileCryptDer != null)
        {
            this._btnTypeFileCryptDer.removeItemListener(this);
            this._btnTypeFileCryptDer.destroy();
            this._btnTypeFileCryptDer = null;
        }*/
    }
    

    /*public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! this._btnTypeFileCryptPkcs7.init())
            return false;
            
        if (! this._btnTypeFileCryptPkcs7Prt.init())
            return false;
            
        if (! this._btnTypeFileCryptDer.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }*/
    

    public PSelBtnTfdFileSaveCrypt(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
     
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 
       
            PSelBtnTfdFileSaveCrypt.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveCrypt.f_s_strDocPropVal);
        
       
        
        /*this._btnTypeFileCryptDer = new RBTypeCryptDer(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
         this._btnTypeFileCryptPkcs7 = new RBTypeCryptPkcs7(
            true, 
            itmListenerParent);
            
         this._btnTypeFileCryptPkcs7Prt = new RBTypeCryptPem(
            true, 
            itmListenerParent);
        
        
        this._btnTypeFileCryptPkcs7.addItemListener(this);
        this._btnTypeFileCryptPkcs7Prt.addItemListener(this);
        this._btnTypeFileCryptDer.addItemListener(this);*/
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        /*String[] strsTypeFileCryptCur = _getTypeFileCryptCur();
                
        if (strsTypeFileCryptCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileCryptCur");

        String strFileDesc = _getDescFileCryptCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        */
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
        
            
        fle = S_FileChooserAbs.s_getSaveFile(
            
            super._frmParent_, 
            strButtonTextOk);
        
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

    //private RBTypeCryptAbs _btnTypeFileCryptPkcs7 = null;
    //private RBTypeCryptAbs _btnTypeFileCryptPkcs7Prt = null;
    //private RBTypeCryptAbs _btnTypeFileCryptDer = null;
    
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
    /*private boolean _addGroup()
    {
        String strMethod = "_addGroup()";
        
        // adding radioButtons/labelChecks for selecting in between JAR, and JHR, and RCR files
        
        if (this._btnTypeFileCryptPkcs7Prt==null || this._btnTypeFileCryptPkcs7==null || this._btnTypeFileCryptDer==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCrypt[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileCryptPkcs7);
        bgp.add(this._btnTypeFileCryptPkcs7Prt);
        bgp.add(this._btnTypeFileCryptDer);
        
        // selecting first button
	    this._btnTypeFileCryptDer.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileCrypt = new JPanel();
        pnlTypeFileCrypt.setLayout(new BoxLayout(pnlTypeFileCrypt, BoxLayout.Y_AXIS));
        pnlTypeFileCrypt.add(this._btnTypeFileCryptDer); // default
        pnlTypeFileCrypt.add(this._btnTypeFileCryptPkcs7);
        pnlTypeFileCrypt.add(this._btnTypeFileCryptPkcs7Prt);
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileCrypt);
	    
        // ending
        return true;
    }*/

    /*private String[] _getTypeFileCryptCur()
    {
        String strMethod = "_getTypeFileCryptCur()";
        
        if (this._btnTypeFileCryptPkcs7.isSelected())
        {
            return this._btnTypeFileCryptPkcs7.getNamesFileExtension();
        }  
        
        if (this._btnTypeFileCryptPkcs7Prt.isSelected())
        {
            return this._btnTypeFileCryptPkcs7Prt.getNamesFileExtension();
        }    
        
        if (this._btnTypeFileCryptDer.isSelected())
        {
            return this._btnTypeFileCryptDer.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }*/
    
    /*private String _getDescFileCryptCur()
    {
        String strMethod = "_getDescFileCryptCur()";
        
        if (this._btnTypeFileCryptPkcs7.isSelected())
        {
            return this._btnTypeFileCryptPkcs7.getFileDesc();
        }   
        
        if (this._btnTypeFileCryptPkcs7Prt.isSelected())
        {
            return this._btnTypeFileCryptPkcs7Prt.getFileDesc();
        } 
        
        if (this._btnTypeFileCryptDer.isSelected())
        {
            return this._btnTypeFileCryptDer.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }*/
}

