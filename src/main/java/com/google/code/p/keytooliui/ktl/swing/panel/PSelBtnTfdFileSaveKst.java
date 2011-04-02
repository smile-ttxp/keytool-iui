package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    . a group of 2 radioButtons (for switching)
    
    textfield is not editable

    ... used to select a file (not a directory) located in user's system:
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

final public class PSelBtnTfdFileSaveKst extends PSelBtnTfdFileSaveAbs
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_kst_save";
    final static public String s_strDirNameDefault = "mykeystores";      // !!! already defined
    final static public String f_s_strLabel = "Keystore file:"; // !!! already defined
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    
    // ------
    // PUBLIC
    
    public String getSelectedFormatFile()
    {
        if (this._btnTypeFileKstJks != null)
            if (this._btnTypeFileKstJks.isSelected())
                return this._btnTypeFileKstJks.getFormatFile();
                
        if (this._btnTypeFileKstJceks != null)
            if (this._btnTypeFileKstJceks.isSelected())
                return this._btnTypeFileKstJceks.getFormatFile();
                
        if (this._btnTypeFileKstPkcs12 != null)
            if (this._btnTypeFileKstPkcs12.isSelected())
                return this._btnTypeFileKstPkcs12.getFormatFile();
        
        if (this._btnTypeFileKstBks != null)
            if (this._btnTypeFileKstBks.isSelected())
                return this._btnTypeFileKstBks.getFormatFile();
                
        if (this._btnTypeFileKstUber != null)
            if (this._btnTypeFileKstUber.isSelected())
                return this._btnTypeFileKstUber.getFormatFile();
        
        return null;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnTypeFileKstJks != null)
        {
            this._btnTypeFileKstJks.removeItemListener(this);
            this._btnTypeFileKstJks.destroy();
            this._btnTypeFileKstJks = null;
        }
        
        if (this._btnTypeFileKstJceks != null)
        {
            this._btnTypeFileKstJceks.removeItemListener(this);
            this._btnTypeFileKstJceks.destroy();
            this._btnTypeFileKstJceks = null;
        }
        
        if (this._btnTypeFileKstPkcs12 != null)
        {
            this._btnTypeFileKstPkcs12.removeItemListener(this);
            this._btnTypeFileKstPkcs12.destroy();
            this._btnTypeFileKstPkcs12 = null;
        } 
        
        if (this._btnTypeFileKstBks != null)
        {
            this._btnTypeFileKstBks.removeItemListener(this);
            this._btnTypeFileKstBks.destroy();
            this._btnTypeFileKstBks = null;
        } 
        
        if (this._btnTypeFileKstUber != null)
        {
            this._btnTypeFileKstUber.removeItemListener(this);
            this._btnTypeFileKstUber.destroy();
            this._btnTypeFileKstUber = null;
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
        
        if (! this._btnTypeFileKstJks.init())
            return false;
            
        if (! this._btnTypeFileKstJceks.init())
            return false;
        
        if (this._btnTypeFileKstPkcs12 != null)
        {
            if (! this._btnTypeFileKstPkcs12.init())
                return false;
        }
        
        if (this._btnTypeFileKstBks != null)
        {
            if (! this._btnTypeFileKstBks.init())
                return false;
        }
        
        if (this._btnTypeFileKstUber != null)
        {
            if (! this._btnTypeFileKstUber.init())
                return false;
        }
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    

    public PSelBtnTfdFileSaveKst(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        
        ItemListener itmListenerParent,
        boolean blnFieldRequired,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        super(
            docListenerParent,
            frmParent, 
    
            PSelBtnTfdFileSaveKst.f_s_strLabel,
            blnFieldRequired
            );
    
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, 
            (Object) PSelBtnTfdFileSaveKst.f_s_strDocPropVal);
        
        this._btnTypeFileKstJks = new RBTypeKstJks(
            true, // blnEnabledButton
            itmListenerParent);
            
        this._btnTypeFileKstJceks = new RBTypeKstJceks(
            true, // blnEnabledButton
            itmListenerParent);
        
        if (blnAllowTypePkcs12)
        {
            this._btnTypeFileKstPkcs12 = new RBTypeKstPkcs12(
                true, // blnEnabledButton
                itmListenerParent);
        }
        
        if (blnAllowTypeBks)
        {
            this._btnTypeFileKstBks = new RBTypeKstBks(
                true, // blnEnabledButton
                itmListenerParent);
        }   
        
        if (blnAllowTypeUber)
        {
            this._btnTypeFileKstUber = new RBTypeKstUber(
                true, // blnEnabledButton
                itmListenerParent);
        }  
        
        // in order to cleanup textfield, if switching radiobuttons
        this._btnTypeFileKstJks.addItemListener(this);
        this._btnTypeFileKstJceks.addItemListener(this);
        
        if (this._btnTypeFileKstPkcs12 != null)
            this._btnTypeFileKstPkcs12.addItemListener(this);
            
        if (this._btnTypeFileKstBks != null)
            this._btnTypeFileKstBks.addItemListener(this);
            
        if (this._btnTypeFileKstUber != null)
            this._btnTypeFileKstUber.addItemListener(this);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";

        String[] strsTypeFileKstCur = _getTypeFileKstCur();
                
        if (strsTypeFileKstCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileKstCur");

        String strFileDesc = _getDescFileKstCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Save file";
            
        fle = S_FileChooserUI.s_getSaveFile(
            
            super._frmParent_, 
            strButtonTextOk,
            strsTypeFileKstCur, 
            strFileDesc,
            com.google.code.p.keytooliui.ktl.io.S_FileExtensionUI.f_s_strDirNameDefaultKst);
       
        
        
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

    private RBTypeKstAbs _btnTypeFileKstJks = null;
    private RBTypeKstAbs _btnTypeFileKstJceks = null;
    private RBTypeKstAbs _btnTypeFileKstPkcs12 = null;
    private RBTypeKstAbs _btnTypeFileKstBks = null;
    private RBTypeKstAbs _btnTypeFileKstUber = null;
    
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
        grouping  Jks & JHR & RCR files
    **/
    private boolean _addGroup()
    {
        String strMethod = "_addGroup()";
        
        // adding radioButtons/labelChecks for selecting in between JAR, and JHR, and RCR files
        
        if (this._btnTypeFileKstJks == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileKstJks");
            return false;
        }
        
        if (this._btnTypeFileKstJceks == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnTypeFileKstJceks");
            return false;
        }

        // ----

        ButtonGroup bgp = new ButtonGroup();
        bgp.add(this._btnTypeFileKstJks);
        bgp.add(this._btnTypeFileKstJceks);
        
        if (this._btnTypeFileKstPkcs12 != null)
            bgp.add(this._btnTypeFileKstPkcs12);
            
        if (this._btnTypeFileKstBks != null)
            bgp.add(this._btnTypeFileKstBks);
            
        if (this._btnTypeFileKstUber != null)
            bgp.add(this._btnTypeFileKstUber);
            
        // --
	    this._btnTypeFileKstJks.setSelected(true);
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileKst = new JPanel();
        pnlTypeFileKst.setLayout(new BoxLayout(pnlTypeFileKst, BoxLayout.Y_AXIS));
        pnlTypeFileKst.add(this._btnTypeFileKstJks);
        pnlTypeFileKst.add(this._btnTypeFileKstJceks);
        
        if (this._btnTypeFileKstPkcs12 != null)
            pnlTypeFileKst.add(this._btnTypeFileKstPkcs12);
            
        if (this._btnTypeFileKstBks != null)
            pnlTypeFileKst.add(this._btnTypeFileKstBks);
            
        if (this._btnTypeFileKstUber != null)
            pnlTypeFileKst.add(this._btnTypeFileKstUber);
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	    super._pnl_.add(pnlTypeFileKst);
	    
        // ending
        return true;
    }

    private String[] _getTypeFileKstCur()
    {
        String strMethod = "_getTypeFileKstCur()";
        
        if (this._btnTypeFileKstJks.isSelected())
        {
            return this._btnTypeFileKstJks.getNamesFileExtension();
        }    
        
        if (this._btnTypeFileKstJceks.isSelected())
        {
            return this._btnTypeFileKstJceks.getNamesFileExtension();
        }  
        
        if (this._btnTypeFileKstPkcs12 != null)
        {
            if (this._btnTypeFileKstPkcs12.isSelected())
            {
                return this._btnTypeFileKstPkcs12.getNamesFileExtension();
            }
        }
        
        if (this._btnTypeFileKstBks != null)
        {
            if (this._btnTypeFileKstBks.isSelected())
            {
                return this._btnTypeFileKstBks.getNamesFileExtension();
            }
        }
        
        if (this._btnTypeFileKstUber != null)
        {
            if (this._btnTypeFileKstUber.isSelected())
            {
                return this._btnTypeFileKstUber.getNamesFileExtension();
            }
        }

        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
     private String _getDescFileKstCur()
    {
        String strMethod = "_getDescFileKstCur()";
        
        if (this._btnTypeFileKstJks.isSelected())
        {
            return this._btnTypeFileKstJks.getFileDesc();
        }    
        
        if (this._btnTypeFileKstJceks.isSelected())
        {
            return this._btnTypeFileKstJceks.getFileDesc();
        }  
        
        if (this._btnTypeFileKstPkcs12 != null)
        {
            if (this._btnTypeFileKstPkcs12.isSelected())
            {
                return this._btnTypeFileKstPkcs12.getFileDesc();
            }
        }
        
        if (this._btnTypeFileKstBks != null)
        {
            if (this._btnTypeFileKstBks.isSelected())
            {
                return this._btnTypeFileKstBks.getFileDesc();
            }
        }
        
        if (this._btnTypeFileKstUber != null)
        {
            if (this._btnTypeFileKstUber.isSelected())
            {
                return this._btnTypeFileKstUber.getFileDesc();
            }
        }

        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
}