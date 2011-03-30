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

final public class PSelBtnTfdFileSaveSCms extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_sig_save";
    final static public String s_strDirNameDefault = "mysigs";
    final static public String f_s_strLabel = "(detached) CMS signature file:"; 
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileSCmsP7m != null)
            if (this._btnTypeFileSCmsP7m.isSelected())
                return this._btnTypeFileSCmsP7m.getFormatFile();
     
        if (this._btnTypeFileSCmsP7s != null)
            if (this._btnTypeFileSCmsP7s.isSelected())
                return this._btnTypeFileSCmsP7s.getFormatFile();
                
        /*if (this._btnTypeFileSCmsPkcs7Prt != null)
            if (this._btnTypeFileSCmsPkcs7Prt.isSelected())
                return this._btnTypeFileSCmsPkcs7Prt.getFormatFile();
          */      
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileSCmsP7s != null)
        {
            this._btnTypeFileSCmsP7s.removeItemListener(this);
            this._btnTypeFileSCmsP7s.destroy();
            this._btnTypeFileSCmsP7s = null;
        }
        
        /*if (this._btnTypeFileSCmsPkcs7Prt != null)
        {
            this._btnTypeFileSCmsPkcs7Prt.removeItemListener(this);
            this._btnTypeFileSCmsPkcs7Prt.destroy();
            this._btnTypeFileSCmsPkcs7Prt = null;
        }*/
        
        if (this._btnTypeFileSCmsP7m != null)
        {
            this._btnTypeFileSCmsP7m.removeItemListener(this);
            this._btnTypeFileSCmsP7m.destroy();
            this._btnTypeFileSCmsP7m = null;
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
        
        if (! this._btnTypeFileSCmsP7s.init())
            return false;
            
        //if (! this._btnTypeFileSCmsPkcs7Prt.init())
          //  return false;
            
        if (! this._btnTypeFileSCmsP7m.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileSaveSCms(
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
            PSelBtnTfdFileSaveSCms.f_s_strLabel,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveSCms.f_s_strDocPropVal);
        
       
        
        this._btnTypeFileSCmsP7m = new RBTypeSCmsP7m(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
         this._btnTypeFileSCmsP7s = new RBTypeSCmsP7s(
            true, 
            itmListenerParent);
            
         /*this._btnTypeFileSCmsPkcs7Prt = new RBTypeSCmsPem(
            true, 
            itmListenerParent);
        */
        
        this._btnTypeFileSCmsP7s.addItemListener(this);
        //this._btnTypeFileSCmsPkcs7Prt.addItemListener(this);
        this._btnTypeFileSCmsP7m.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileSCmsCur = _getTypeFileSCmsCur();
                
        if (strsTypeFileSCmsCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileSCmsCur");

        String strFileDesc = _getDescFileSCmsCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
            
        fle = S_FileChooserUI.s_getSaveFile(
            super._strTitleAppli_, 
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileSCmsCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultSCms);
       
        
        
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

    private RBTypeSigAbs _btnTypeFileSCmsP7s = null;
    //private RBTypeSigAbs _btnTypeFileSCmsPkcs7Prt = null;
    private RBTypeSigAbs _btnTypeFileSCmsP7m = null;
    
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
        
        if (/*this._btnTypeFileSCmsPkcs7Prt==null ||*/ this._btnTypeFileSCmsP7s==null || this._btnTypeFileSCmsP7m==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileSCms[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileSCmsP7s);
        //bgp.add(this._btnTypeFileSCmsPkcs7Prt);
        bgp.add(this._btnTypeFileSCmsP7m);
        
        // selecting first button
	    this._btnTypeFileSCmsP7m.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileSCms = new JPanel();
        pnlTypeFileSCms.setLayout(new BoxLayout(pnlTypeFileSCms, BoxLayout.Y_AXIS));
        pnlTypeFileSCms.add(this._btnTypeFileSCmsP7m); // default
        pnlTypeFileSCms.add(this._btnTypeFileSCmsP7s);
        //pnlTypeFileSCms.add(this._btnTypeFileSCmsPkcs7Prt);
        
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileSCms);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileSCmsCur()
    {
        String strMethod = "_getTypeFileSCmsCur()";
        
        if (this._btnTypeFileSCmsP7s.isSelected())
        {
            return this._btnTypeFileSCmsP7s.getNamesFileExtension();
        }  
        
        /*if (this._btnTypeFileSCmsPkcs7Prt.isSelected())
        {
            return this._btnTypeFileSCmsPkcs7Prt.getNamesFileExtension();
        }*/    
        
        if (this._btnTypeFileSCmsP7m.isSelected())
        {
            return this._btnTypeFileSCmsP7m.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileSCmsCur()
    {
        String strMethod = "_getDescFileSCmsCur()";
        
        if (this._btnTypeFileSCmsP7s.isSelected())
        {
            return this._btnTypeFileSCmsP7s.getFileDesc();
        }   
        
        /*if (this._btnTypeFileSCmsPkcs7Prt.isSelected())
        {
            return this._btnTypeFileSCmsPkcs7Prt.getFileDesc();
        }*/
        
        if (this._btnTypeFileSCmsP7m.isSelected())
        {
            return this._btnTypeFileSCmsP7m.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
