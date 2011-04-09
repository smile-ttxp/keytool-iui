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

public final class PSelBtnTfdFileOpenCms extends PSelBtnTfdFileOpenAbs
{   
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_file_cms_open";
    public static final String s_strDirNameDefault = "mycmss";
    
    
    private static final String _STR_LABELSUFFIX = "(detached) CMS signature file:"; 
    
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
                
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileSCmsP7m != null)
        {
            this._btnTypeFileSCmsP7m.removeItemListener(this);
            this._btnTypeFileSCmsP7m.destroy();
            this._btnTypeFileSCmsP7m = null;
        }
        
        if (this._btnTypeFileSCmsP7s != null)
        {
            this._btnTypeFileSCmsP7s.removeItemListener(this);
            this._btnTypeFileSCmsP7s.destroy();
            this._btnTypeFileSCmsP7s = null;
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
        
        if (! this._btnTypeFileSCmsP7m.init())
            return false;
            
        if (! this._btnTypeFileSCmsP7s.init())
            return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileOpenCms(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
     
        ItemListener itmListenerParent
        )
    {
        super(
            docListenerParent,
            frmParent, 
     
            PSelBtnTfdFileOpenCms._STR_LABELSUFFIX,
            true // blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileOpenCms.f_s_strDocPropVal);
        
        this._btnTypeFileSCmsP7m = new RBTypeSCmsP7m(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
        
        this._btnTypeFileSCmsP7s = new RBTypeSCmsP7s(
            true, // blnEnabledButton, if just one button, disabling it
            itmListenerParent);
            
        if (itmListenerParent != null)
            this._btnTypeFileSCmsP7m.addItemListener(this);
        
        if (itmListenerParent != null)
            this._btnTypeFileSCmsP7s.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileCmsCur = _getTypeFileCmsCur();
                
        if (strsTypeFileCmsCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileCmsCur");

        String strFileDesc = _getDescFileCmsCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
      
        String strButtonTextOk = "Open file";
            
        File fle = S_FileChooserUI.s_getOpenFile(
            
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileCmsCur, 
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

    private RBTypeSigAbs _btnTypeFileSCmsP7m = null;
    private RBTypeSigAbs _btnTypeFileSCmsP7s = null;
    
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
        
        if (this._btnTypeFileSCmsP7m==null || this._btnTypeFileSCmsP7s==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileCms[xxx]");
            return false;
        }
        
      
        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileSCmsP7m);
        bgp.add(this._btnTypeFileSCmsP7s);
        
        // selecting first button
	this._btnTypeFileSCmsP7m.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileCms = new JPanel();
        pnlTypeFileCms.setLayout(new BoxLayout(pnlTypeFileCms, BoxLayout.Y_AXIS));
        pnlTypeFileCms.add(this._btnTypeFileSCmsP7m); // default
        pnlTypeFileCms.add(this._btnTypeFileSCmsP7s); 
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
	    super._pnl_.add(pnlTypeFileCms);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileCmsCur()
    {
        String strMethod = "_getTypeFileCmsCur()";
        

        if (this._btnTypeFileSCmsP7m.isSelected())
        {
            return this._btnTypeFileSCmsP7m.getNamesFileExtension();
        }   
        
        if (this._btnTypeFileSCmsP7s.isSelected())
        {
            return this._btnTypeFileSCmsP7s.getNamesFileExtension();
        }   
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileCmsCur()
    {
        String strMethod = "_getDescFileCmsCur()";
        
        if (this._btnTypeFileSCmsP7m.isSelected())
        {
            return this._btnTypeFileSCmsP7m.getFileDesc();
        }  
        
        if (this._btnTypeFileSCmsP7s.isSelected())
        {
            return this._btnTypeFileSCmsP7s.getFileDesc();
        }  
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
