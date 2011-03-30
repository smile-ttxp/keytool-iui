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

final public class PSelBtnTfdFileOpenKprKpr extends PSelBtnTfdFileOpenAbs 
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_kpr_kpr_open";
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strLabel = "Private key file:";
    
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
        
        if (this._btnTypeFileKprDer != null)
        {
            this._btnTypeFileKprDer.removeItemListener(this);
            this._btnTypeFileKprDer.destroy();
            this._btnTypeFileKprDer = null;
        }
        
        
        if (this._btnTypeFileKprPem != null)
        {
            this._btnTypeFileKprPem.removeItemListener(this);
            this._btnTypeFileKprPem.destroy();
            this._btnTypeFileKprPem = null;
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
        
        if (this._btnTypeFileKprDer != null)
            if (! this._btnTypeFileKprDer.init())
                return false;
         
        if (this._btnTypeFileKprPem != null)
            if (! this._btnTypeFileKprPem.init())
                return false;
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileOpenKprKpr(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        ItemListener itmListenerParent,
        boolean blnDerVersusPem
        )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            PSelBtnTfdFileOpenKprKpr._f_s_strLabel,
            true // blnFieldRequired
            );

        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, (Object) PSelBtnTfdFileOpenKprKpr.f_s_strDocPropVal);
        
        if (blnDerVersusPem)        
            this._btnTypeFileKprDer = new RBTypeKprDer(
                true, // blnEnabledButton, if just one button, disabling it
                itmListenerParent);
            
        else    
            this._btnTypeFileKprPem = new RBTypeKprPem(
                true, // blnEnabledButton, if just one button, disabling it
                itmListenerParent);
        

        if (this._btnTypeFileKprDer != null)  
            this._btnTypeFileKprDer.addItemListener(this);
        
        if (this._btnTypeFileKprPem != null)
            this._btnTypeFileKprPem.addItemListener(this);
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
        
        String strButtonTextOk = "Open file";
            
        fle = S_FileChooserUI.s_getOpenFile(
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
            
    
    private RBTypeKprAbs _btnTypeFileKprDer = null;
    private RBTypeKprAbs _btnTypeFileKprPem = null;
    
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
                
            OPAbstract.s_showDialogWarning(super._frmParent_, super._strTitleAppli_, strBody);
                
            return true;
        }
            
        if (fle.isDirectory())
        {
            MySystem.s_printOutWarning(this, strMethod, "fle.isDirectory(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
                
            String strBody = fle.getAbsolutePath();
            strBody += ":\nFile is a directory.";
                
            OPAbstract.s_showDialogWarning(super._frmParent_, super._strTitleAppli_, strBody);
                
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
        
        

        
        // ----
      
        ButtonGroup bgp = new ButtonGroup();
        
        if (this._btnTypeFileKprDer != null)
            bgp.add(this._btnTypeFileKprDer);
        
        if (this._btnTypeFileKprPem != null)
            bgp.add(this._btnTypeFileKprPem);
            
        // --
        
        // selecting first button
        
        if (this._btnTypeFileKprDer != null)
        {
            this._btnTypeFileKprDer.setSelected(true);
            this._btnTypeFileKprDer.setEnabled(false);
        }
        
        else if (this._btnTypeFileKprPem != null)
        {
            this._btnTypeFileKprPem.setSelected(true);
            this._btnTypeFileKprPem.setEnabled(false);
        }
        // else BUG !
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileKpr = new JPanel();
        pnlTypeFileKpr.setLayout(new BoxLayout(pnlTypeFileKpr, BoxLayout.Y_AXIS));
        
        if (this._btnTypeFileKprDer != null)
            pnlTypeFileKpr.add(this._btnTypeFileKprDer);

        
        if (this._btnTypeFileKprPem != null)
            pnlTypeFileKpr.add(this._btnTypeFileKprPem);
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	super._pnl_.add(pnlTypeFileKpr);
   
        // ending
        return true;
    }
    
    
    private String[] _getTypeFileKprCur()
    {
        String strMethod = "_getTypeFileKprCur()";
        
        if (this._btnTypeFileKprDer != null)
            if (this._btnTypeFileKprDer.isSelected())
            {
                return this._btnTypeFileKprDer.getNamesFileExtension();
            }     


        
        if (this._btnTypeFileKprPem != null)
        {
            if (this._btnTypeFileKprPem.isSelected())
            {
                return this._btnTypeFileKprPem.getNamesFileExtension();
            }
        }

        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
    private String _getDescFileKprCur()
    {
        String strMethod = "_getDescFileKprCur()";
        
        if (this._btnTypeFileKprDer != null)
            if (this._btnTypeFileKprDer.isSelected())
            {
                return this._btnTypeFileKprDer.getFileDesc();
            }
        

        
        if (this._btnTypeFileKprPem != null)
        {
            if (this._btnTypeFileKprPem.isSelected())
            {
                return this._btnTypeFileKprPem.getFileDesc();
            }
        }

        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}
