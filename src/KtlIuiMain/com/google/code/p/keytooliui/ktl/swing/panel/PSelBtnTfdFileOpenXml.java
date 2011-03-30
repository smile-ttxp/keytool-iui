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

final public class PSelBtnTfdFileOpenXml extends PSelBtnTfdFileOpenAbs
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_xml_open";
    final static public String s_strDirNameDefault = "myxmls";
    
    
    final static private String STR_LABELSUFFIX = "XML file:"; //(either Signed or Unsigned) 
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileXmlXml != null)
            if (this._btnTypeFileXmlXml.isSelected())
                return this._btnTypeFileXmlXml.getFormatFile();
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileXmlXml != null)
        {
            this._btnTypeFileXmlXml.removeItemListener(this);
            this._btnTypeFileXmlXml.destroy();
            this._btnTypeFileXmlXml = null;
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
            
        if (! this._btnTypeFileXmlXml.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileOpenXml(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        ItemListener itmListenerParent,
        String strLabelPrefix
        )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            strLabelPrefix + " " + PSelBtnTfdFileOpenXml.STR_LABELSUFFIX,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileOpenXml.f_s_strDocPropVal);
        
       
        
        this._btnTypeFileXmlXml = new RBTypeXmlXml(
            false, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
        
        if (itmListenerParent != null)
            this._btnTypeFileXmlXml.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileXmlCur = _getTypeFileXmlCur();
                
        if (strsTypeFileXmlCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileXmlCur");

        String strFileDesc = _getDescFileXmlCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
      
        String strButtonTextOk = "Open file";
            
        File fle = S_FileChooserUI.s_getOpenFile(
            super._strTitleAppli_, 
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileXmlCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultXml);
       
        
        
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

    private RBTypeXmlAbs _btnTypeFileXmlXml = null;
    
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
        
        if (this._btnTypeFileXmlXml==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileXml[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileXmlXml);
        
        // selecting first button
	    this._btnTypeFileXmlXml.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileXml = new JPanel();
        pnlTypeFileXml.setLayout(new BoxLayout(pnlTypeFileXml, BoxLayout.Y_AXIS));
        pnlTypeFileXml.add(this._btnTypeFileXmlXml); // default
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileXml);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileXmlCur()
    {
        String strMethod = "_getTypeFileXmlCur()";
        

        if (this._btnTypeFileXmlXml.isSelected())
        {
            return this._btnTypeFileXmlXml.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileXmlCur()
    {
        String strMethod = "_getDescFileXmlCur()";
        

        
        if (this._btnTypeFileXmlXml.isSelected())
        {
            return this._btnTypeFileXmlXml.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
