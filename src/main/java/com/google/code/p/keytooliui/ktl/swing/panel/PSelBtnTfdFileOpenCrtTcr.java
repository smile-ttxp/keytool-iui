package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    . a group of 2 radioButtons (for switching)
    
    textfield is not editable

    ... used to select a file (not a directory) located in user's system:
    . for opening
    . for saving
    
    eg: keytool
    

    
    
    IMPORTANT: in case od Save option, then the radioButtons should be disabled
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

public final class PSelBtnTfdFileOpenCrtTcr extends PSelBtnTfdFileOpenAbs 
{   
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_file_crt_tcr_open";
    
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strLabel = "Trusted certificate file:";
    
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
                
        if (this._btnTypeFileCrtPem != null)
            if (this._btnTypeFileCrtPem.isSelected())
                return this._btnTypeFileCrtPem.getFormatFile();
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileCrtDer != null)
        {
            this._btnTypeFileCrtDer.removeItemListener(this);
            this._btnTypeFileCrtDer.destroy();
            this._btnTypeFileCrtDer = null;
        }
        
        if (this._btnTypeFileCrtPkcs7 != null)
        {
            this._btnTypeFileCrtPkcs7.removeItemListener(this);
            this._btnTypeFileCrtPkcs7.destroy();
            this._btnTypeFileCrtPkcs7 = null;
        }
        
        if (this._btnTypeFileCrtPem != null)
        {
            this._btnTypeFileCrtPem.removeItemListener(this);
            this._btnTypeFileCrtPem.destroy();
            this._btnTypeFileCrtPem = null;
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
        
        if (! this._btnTypeFileCrtDer.init())
            return false;
        
        if (! this._btnTypeFileCrtPkcs7.init())
            return false;
            
        if (! this._btnTypeFileCrtPem.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    public PSelBtnTfdFileOpenCrtTcr(
        String strTextLabel,
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
       
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 
     
            strTextLabel,
            true // blnFieldRequired
            );

        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, (Object) PSelBtnTfdFileOpenCrtTcr.f_s_strDocPropVal);
        
                
        this._btnTypeFileCrtDer = new RBTypeCrtDer(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
        this._btnTypeFileCrtPkcs7 = new RBTypeCrtPkcs7(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
        this._btnTypeFileCrtPem = new RBTypeCrtPem(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
        

          
        this._btnTypeFileCrtDer.addItemListener(this);
        this._btnTypeFileCrtPkcs7.addItemListener(this);
        this._btnTypeFileCrtPem.addItemListener(this);
    }

    public PSelBtnTfdFileOpenCrtTcr(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
      
        ItemListener itmListenerParent
        )
    {
        this(
            PSelBtnTfdFileOpenCrtTcr._f_s_strLabel,
            docListenerParent,
            frmParent, 

            itmListenerParent);
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
        
        String strButtonTextOk = "Open file";
            
        fle = S_FileChooserUI.s_getOpenFile(
            
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
            
    
    private RBTypeCrtAbs _btnTypeFileCrtDer = null;
    private RBTypeCrtAbs _btnTypeFileCrtPkcs7 = null;
    private RBTypeCrtAbs _btnTypeFileCrtPem = null;
    
    private boolean _assignValues(File fle)
    {
        String strMethod = "_assignValues(fle)";

        if (fle == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fle");
            return false;
        }
 
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
        grouping  buttons
    **/
    private boolean _addGroup()
    {
        String strMethod = "_addGroup()";
        
        if (this._btnTypeFileCrtDer == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCrtDer");
            return false;
        }

        if (this._btnTypeFileCrtPkcs7 == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCrtPkcs7");
            return false;
        }
        
        if (this._btnTypeFileCrtPem == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCrtPem");
            return false;
        }
        
        // ----
      
        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileCrtDer);
        bgp.add(this._btnTypeFileCrtPkcs7);
        bgp.add(this._btnTypeFileCrtPem);
            
        // --
        
        // selecting first button
        
	this._btnTypeFileCrtDer.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileCrt = new JPanel();
        pnlTypeFileCrt.setLayout(new BoxLayout(pnlTypeFileCrt, BoxLayout.Y_AXIS));
        pnlTypeFileCrt.add(this._btnTypeFileCrtDer);
        pnlTypeFileCrt.add(this._btnTypeFileCrtPkcs7);
        pnlTypeFileCrt.add(this._btnTypeFileCrtPem);
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	super._pnl_.add(pnlTypeFileCrt);
   
        // ending
        return true;
    }
    
    
    private String[] _getTypeFileCrtCur()
    {
        String strMethod = "_getTypeFileCrtCur()";
        
        if (this._btnTypeFileCrtDer.isSelected())
        {
            return this._btnTypeFileCrtDer.getNamesFileExtension();
        }     

        if (this._btnTypeFileCrtPkcs7.isSelected())
        {
            return this._btnTypeFileCrtPkcs7.getNamesFileExtension();
        }   
        
        if (this._btnTypeFileCrtPem.isSelected())
        {
            return this._btnTypeFileCrtPem.getNamesFileExtension();
        }   

        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
    private String _getDescFileCrtCur()
    {
        String strMethod = "_getDescFileCrtCur()";
        
        if (this._btnTypeFileCrtDer.isSelected())
        {
            return this._btnTypeFileCrtDer.getFileDesc();
        }
        
        if (this._btnTypeFileCrtPkcs7.isSelected())
        {
            return this._btnTypeFileCrtPkcs7.getFileDesc();
        }
        
        if (this._btnTypeFileCrtPem.isSelected())
        {
            return this._btnTypeFileCrtPem.getFileDesc();
        }        

        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}