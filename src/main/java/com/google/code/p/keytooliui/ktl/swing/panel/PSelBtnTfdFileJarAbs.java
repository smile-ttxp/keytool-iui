/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 
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
    
    
    known subclasses:
    . PSelBtnTfdFileJarOpenAbs
    . PSelBtnTfdFileJarSaveAbs
    
    
    IMPORTANT: in case od Save option, then the radioButtons should be disabled
**/



import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.swing.button.*;
import com.google.code.p.keytooliui.ktl.swing.label.*;


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

abstract public class PSelBtnTfdFileJarAbs extends PSelBtnTfdAbs implements
    ItemListener
{   
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strSuffixFileDesc = " files";
    
    
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        
        if (super._tfdCurSelection_ != null)
        {
            ((TFAbstract)super._tfdCurSelection_).destroy();
            super._tfdCurSelection_ = null;
        }
        
        if (this._cmpTypeFileJarProjDoc != null)
        {
            if (this._cmpTypeFileJarProjDoc instanceof RBTypeJarAbs)
                ((RBTypeJarAbs) this._cmpTypeFileJarProjDoc).destroy();
                
            else if (this._cmpTypeFileJarProjDoc instanceof LabelCheckTypeJarAbs)
                ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjDoc).destroy();

            this._cmpTypeFileJarProjDoc = null;
        }
        
        
        if (this._cmpTypeFileJarProjHelpSun != null)
        {
            if (this._cmpTypeFileJarProjHelpSun instanceof RBTypeJarAbs)
                ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpSun).destroy();
                
            else if (this._cmpTypeFileJarProjHelpSun instanceof LabelCheckTypeJarAbs)
                ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpSun).destroy();

            this._cmpTypeFileJarProjHelpSun = null;
        }
        
        if (this._cmpTypeFileJarProjHelpOracle != null)
        {
            if (this._cmpTypeFileJarProjHelpOracle instanceof RBTypeJarAbs)
                ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).destroy();
                
            else if (this._cmpTypeFileJarProjHelpOracle instanceof LabelCheckTypeJarAbs)
                ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).destroy();

            this._cmpTypeFileJarProjHelpOracle = null;
        }
        
        if (this._cmpTypeFileJarJar != null)
        {
            if (this._cmpTypeFileJarJar instanceof RBTypeJarAbs)
                ((RBTypeJarAbs) this._cmpTypeFileJarJar).destroy();
                
            else if (this._cmpTypeFileJarJar instanceof LabelCheckTypeJarAbs)
                ((LabelCheckTypeJarAbs) this._cmpTypeFileJarJar).destroy();

            this._cmpTypeFileJarJar = null;
        }
        
        if (this._cmpTypeFileJarApk != null)
        {
            if (this._cmpTypeFileJarApk instanceof RBTypeJarAbs)
                ((RBTypeJarAbs) this._cmpTypeFileJarApk).destroy();
                
            else if (this._cmpTypeFileJarApk instanceof LabelCheckTypeJarAbs)
                ((LabelCheckTypeJarAbs) this._cmpTypeFileJarApk).destroy();

            this._cmpTypeFileJarApk = null;
        }
    }
    
     // radioButton: RCR or JHR or JAR, if so: clearing textfield, and disabling btnClear
    public void itemStateChanged(ItemEvent evtItem) 
    {
        String strMethod = "itemStateChanged(evtItem)";
        
        if (! (evtItem.getSource() instanceof JRadioButton))
        {
            MySystem.s_printOutExit(this, strMethod, 
                "wrong source instance, \n evtItem.getSource().getClass().toString()=" + 
                evtItem.getSource().getClass().toString());
        }
        
        _reset();
    }
    
    
    
    public boolean setSelectedTypeFileProjDoc()
    {
        String strMethod = "setSelectedTypeFileProjDoc()";
        
        if (! _doClick(this._cmpTypeFileJarProjDoc))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        } 
        
        return true;
    }
    
    public boolean setSelectedTypeFileProjHelpSun()
    {
        String strMethod = "setSelectedTypeFileProjHelpSun()";
        
        // MODIF coz button hidden: march 14, 2003
        if (this._cmpTypeFileJarProjHelpSun != null)
        {
            if (! _doClick(this._cmpTypeFileJarProjHelpSun))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        return true;
    }
    
    
    // added stuff for OHReader, september 30, 2003
    public boolean setSelectedTypeFileProjHelpOracle()
    {
        String strMethod = "setSelectedTypeFileProjHelpOracle()";
        
        if (this._cmpTypeFileJarProjHelpOracle != null)
        {
            if (! _doClick(this._cmpTypeFileJarProjHelpOracle))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        return true;
    }
    
    public boolean setSelectedTypeFileApk()
    {
        String strMethod = "setSelectedTypeFileApk()";
        
        if (! _doClick(this._cmpTypeFileJarApk))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        } 
        
        return true;
    }
    
    public boolean setSelectedTypeFileJar()
    {
        String strMethod = "setSelectedTypeFileJar()";
        
        if (! _doClick(this._cmpTypeFileJarJar))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        } 
        
        return true;
    }
        
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        if (this._cmpTypeFileJarProjDoc != null)
        {
            if (this._cmpTypeFileJarProjDoc instanceof RBTypeJarAbs)
            {
                if (! ((RBTypeJarAbs) this._cmpTypeFileJarProjDoc).init())
                    return false;
            }   

            else if (this._cmpTypeFileJarProjDoc instanceof LabelCheckTypeJarAbs)
            {
                if (! ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjDoc).init())
                    return false;
            }
        }
        
        
        
        // MODIF coz button hidden: march 14, 2003
        if (this._cmpTypeFileJarProjHelpSun != null)
        {
            if (this._cmpTypeFileJarProjHelpSun instanceof RBTypeJarAbs)
            {
                if (! ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpSun).init())
                    return false;
            }
            
            else if (this._cmpTypeFileJarProjHelpSun instanceof LabelCheckTypeJarAbs)
            {
                if (! ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpSun).init())
                    return false;
            } 
        }
        
        if (this._cmpTypeFileJarProjHelpOracle != null)
        {
            if (this._cmpTypeFileJarProjHelpOracle instanceof RBTypeJarAbs)
            {
                if (! ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).init())
                    return false;
            }
            
            else if (this._cmpTypeFileJarProjHelpOracle instanceof LabelCheckTypeJarAbs)
            {
                if (! ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).init())
                    return false;
            } 
        }
        
        if (this._cmpTypeFileJarJar instanceof RBTypeJarAbs)
        {
            if (! ((RBTypeJarAbs) this._cmpTypeFileJarJar).init())
                return false;
        }   
        
        else if (this._cmpTypeFileJarJar instanceof LabelCheckTypeJarAbs)
        {
            if (! ((LabelCheckTypeJarAbs) this._cmpTypeFileJarJar).init())
                return false;
        } 
        
        
        
        if (this._cmpTypeFileJarApk instanceof RBTypeJarAbs)
        {
            if (! ((RBTypeJarAbs) this._cmpTypeFileJarApk).init())
                return false;
        }   
        
        else if (this._cmpTypeFileJarApk instanceof LabelCheckTypeJarAbs)
        {
            if (! ((LabelCheckTypeJarAbs) this._cmpTypeFileJarApk).init())
                return false;
        } 
        
        
        
  
        // added sept 6, 2002
        super.setEnabledSelect(true);
        
        if (! _addGroup())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected PSelBtnTfdFileJarAbs(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
  
        String strLabel,
        Object objDocPropValue,
        int intMode,
        ItemListener itmListenerParent, // always nil if intMode == SAVE_DIALOG
        String strDirNameDefault, // eg: mysignedfiles
        boolean blnFieldRequired
        )
    {
        super(
            frmParent, 
       
            strLabel,
            blnFieldRequired
            );
        
        String strMethod = "PSelBtnTfdFileJarAbs(...)";
        
        this._intMode = intMode; 
        
        if (this._intMode == JFileChooser.SAVE_DIALOG)
            super._btnSelect_ = new BESFileSave16(this);
        else
            super._btnSelect_ = new BESFileOpen16(this);
        
        super._tfdCurSelection_ = new TF30x20SelFile(docListenerParent);
        
        
        this._strDirNameDefault = strDirNameDefault;

        if (objDocPropValue == null)
            MySystem.s_printOutExit(this, strMethod, "nil objDocPropValue");
        
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, objDocPropValue);
        
        
        boolean blnEnabledButton = false;
        
        if (this._intMode == JFileChooser.OPEN_DIALOG)
            blnEnabledButton = true;
        
        if (blnEnabledButton)
        {
            /*
            this._cmpTypeFileJarProjDoc = new RBTypeJarRcr(blnEnabledButton, itmListenerParent);
            
            // MODIF coz button hidden: march 14, 2003
            // reactivated: april 17, 2003
            this._cmpTypeFileJarProjHelpSun = new RBTypeJarJhr(blnEnabledButton, itmListenerParent);
            
            this._cmpTypeFileJarProjHelpOracle = new RBTypeJarOhr(blnEnabledButton, itmListenerParent);
            */
            
            
            this._cmpTypeFileJarJar = new RBTypeJarJar(blnEnabledButton, itmListenerParent);  
            
            this._cmpTypeFileJarApk = new RBTypeJarApk(blnEnabledButton, itmListenerParent);    
            
            if (this._cmpTypeFileJarProjDoc != null)
                ((RBTypeJarAbs) this._cmpTypeFileJarProjDoc).addItemListener(this);
            
            // MODIF coz button hidden: march 14, 2003
            if (this._cmpTypeFileJarProjHelpSun != null)
                ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpSun).addItemListener(this);
                
            if (this._cmpTypeFileJarProjHelpOracle != null)
                ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).addItemListener(this);

            ((RBTypeJarAbs) this._cmpTypeFileJarJar).addItemListener(this);
        }
        
        else
        {
            this._cmpTypeFileJarJar = new LabelCheckTypeJarJar(true); // checked
            
            
            this._cmpTypeFileJarApk = new LabelCheckTypeJarApk(false); // checked
            
            /*
            this._cmpTypeFileJarProjDoc = new LabelCheckTypeJarRcr(false); //  memo: default false
            
            // MODIF coz button hidden: march 14, 2003
            // reactivated: april 17, 2003
            this._cmpTypeFileJarProjHelpSun = new LabelCheckTypeJarJhr(false); // memo: default false
            
            this._cmpTypeFileJarProjHelpOracle = new LabelCheckTypeJarOhr(false); // memo: default false
             */
        }
    }
    
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";
        
        String[] strsTypeFileJarCur = _getTypeFileJarCur();
        
        
        if (strsTypeFileJarCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileJarCur");
        
        
        
         String strFileDesc = _getDescFileJarCur();
         
         if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        
        
        // ----
        
        File fle = null;
        
        if (this._intMode == JFileChooser.OPEN_DIALOG)
        {
            String strButtonTextOk = "Open file";
            
            fle = S_FileChooserUI.s_getOpenFile(
                
                super._frmParent_, 
                strButtonTextOk,
                strsTypeFileJarCur, 
                strFileDesc,
                this._strDirNameDefault);
                
        }
        
        else
        {
            String strButtonTextOk = "Save file";
            
            fle = S_FileChooserUI.s_getSaveFile(
                super._frmParent_, strButtonTextOk,
                strsTypeFileJarCur, strFileDesc,
                this._strDirNameDefault);
        }
        
        if (fle == null)
        {
            // cancelled
            return;
        }
    		
        if (! _assignValues(fle))
            MySystem.s_printOutExit(this, strMethod, "failed, fle.getName()=" + fle.getName());
    }
    
    // could be redefined in subclasses
    protected boolean _enableButtonsSelectionDone_()
    {
        String strMethod = "_enableButtonsSelectionDone_()";
        
        if (super._btnClearSelection_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._btnClearSelection_");
            return false;
        }
        
        super._btnClearSelection_.setEnabled(true);
        
        return true;
    }
    
    
    // -------
    // PRIVATE
    
    private String _strDirNameDefault = null; // eg: mysignedfiles
    
    private int _intMode = 0; // should be assigned by subclasses: SAVE_DIALOG or OPEN_DIALOG
    
    
    private JComponent _cmpTypeFileJarProjDoc = null;
    private JComponent _cmpTypeFileJarProjHelpSun = null;
    private JComponent _cmpTypeFileJarProjHelpOracle = null;
    private JComponent _cmpTypeFileJarJar = null;
    private JComponent _cmpTypeFileJarApk = null;
    
    
    private boolean _assignValues(File fle)
    {
        String strMethod = "_assignValues(fle)";

        if (fle == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fle");
            return false;
        }
        
        
        
        if (this._intMode == JFileChooser.SAVE_DIALOG)
        {
            // CHANGED! now allow to overwrite
            
            /*if (fle.exists())
            {
                MySystem.s_printOutWarning(this, strMethod, "fle.exists(), fle.getAbsolutePath()=" + fle.getAbsolutePath());
                
                String strBody = fle.getAbsolutePath();
                strBody += ":\nFile already exists.";
                
                OPAbstract.s_showDialogWarning(super._frmParent_, strBody);
                return true;
            }*/
        }
        
        else if (this._intMode == JFileChooser.OPEN_DIALOG)
        {
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
        }
      
        if (super._tfdCurSelection_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._tfdCurSelection_");
            return false;
        }
        
        super._tfdCurSelection_.setText(fle.getAbsolutePath());
        super._setSelectedValue_(true);
        
        
        
        // --
        
        if (! _enableButtonsSelectionDone_()) // maybe redirected to subclasses
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    
    
    /**
        grouping  JAR & JHR & RCR files
    **/
    private boolean _addGroup()
    {
        String strMethod = "_addGroup()";
        
        // adding radioButtons/labelChecks for selecting in between JAR, and JHR, and RCR files
        
        /*if (this._cmpTypeFileJarProjDoc == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpTypeFileJarProjDoc");
            return false;
        }*/
        
        // MODIF coz button may be hidden: march 14, 2003
        /*if (this._cmpTypeFileJarProjHelpSun == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpTypeFileJarProjHelpSun");
            return false;
        }*/
        
        
        // idem above
        /*if (this._cmpTypeFileJarProjHelpOracle == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpTypeFileJarProjHelpOracle");
            return false;
        }*/

        if (this._cmpTypeFileJarJar == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpTypeFileJarJar");
            return false;
        }
        
        if (this._cmpTypeFileJarApk == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpTypeFileJarApk");
            return false;
        }
        
        // ----
        
        if (this._cmpTypeFileJarJar instanceof JRadioButton)
        {
            // --
            ButtonGroup bgp = new ButtonGroup();
             
            if (this._cmpTypeFileJarProjDoc != null)
                bgp.add((JRadioButton) this._cmpTypeFileJarProjDoc);
            
            // MODIF coz button hidden: march 14, 2003
            if (this._cmpTypeFileJarProjHelpSun != null)
                bgp.add((JRadioButton) this._cmpTypeFileJarProjHelpSun);
                
            if (this._cmpTypeFileJarProjHelpOracle != null)
                bgp.add((JRadioButton) this._cmpTypeFileJarProjHelpOracle);
            
            bgp.add((JRadioButton) this._cmpTypeFileJarJar);
            
            bgp.add((JRadioButton) this._cmpTypeFileJarApk);
            
            
            // --
            // BEG MODIF: march 14, 2003
	        //((JRadioButton) this._cmpTypeFileJarProjDoc).setSelected(true);
	        ((JRadioButton) this._cmpTypeFileJarJar).setSelected(true);
	        // END MODIF: march 14, 2003
                
            // if only one item, disable it!
            if (bgp.getButtonCount() < 2)
                this._cmpTypeFileJarJar.setEnabled(false);
        }
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileJar = new JPanel();
        pnlTypeFileJar.setLayout(new BoxLayout(pnlTypeFileJar, BoxLayout.Y_AXIS));
        
        pnlTypeFileJar.add(this._cmpTypeFileJarJar);
        
        pnlTypeFileJar.add(this._cmpTypeFileJarApk);
        
        if (this._cmpTypeFileJarProjDoc != null)
            pnlTypeFileJar.add(this._cmpTypeFileJarProjDoc);
        
        // MODIF coz button hidden: march 14, 2003
        if (this._cmpTypeFileJarProjHelpSun != null)
            pnlTypeFileJar.add(this._cmpTypeFileJarProjHelpSun);
            
        if (this._cmpTypeFileJarProjHelpOracle != null)
            pnlTypeFileJar.add(this._cmpTypeFileJarProjHelpOracle);
        
        // --
        if (super._pnl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnl_");
            return false;
        }
        
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	super._pnl_.add(pnlTypeFileJar);

        // ending
        return true;
    }
    
  
    private boolean _doClick(JComponent cmp)
    {
        String strMethod = "_doClick(cmp)";
        
        if (cmp instanceof RBTypeJarAbs)
        {
            return _doClickButton((RBTypeJarAbs) cmp);
        }
        
        if (cmp instanceof LabelCheckTypeJarAbs)
        {
            return _doClickLabel((LabelCheckTypeJarAbs) cmp);
        }

        MySystem.s_printOutError(this, strMethod, "uncaught instanceof cmp");
        return false;
    }
    
    private boolean _doClickLabel(LabelCheckTypeJarAbs lbl)
    {
        String strMethod = "_doClickLabel(lbl)";
        
        try
        {
            if (lbl.isChecked())
                return true;
                
            if (this._intMode == JFileChooser.SAVE_DIALOG) // coz mastered by another file selection with radioButtons for JFileChooser.OPEN_DIALOG (eg: select unsigned JAR file)
                _reset();
                
            lbl.setChecked(true);
            
            
            if (this._cmpTypeFileJarProjDoc != null)
            {
                if (this._cmpTypeFileJarProjDoc != lbl)
                    ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjDoc).setChecked(false);
            }
            
            // MODIF coz button hidden: march 14, 2003
            if (this._cmpTypeFileJarProjHelpSun != null)
            {
                if (this._cmpTypeFileJarProjHelpSun != lbl)
                    ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpSun).setChecked(false);
            }
            
            if (this._cmpTypeFileJarProjHelpOracle != null)
            {
                if (this._cmpTypeFileJarProjHelpOracle != lbl)
                    ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).setChecked(false);
            }
            
            if (this._cmpTypeFileJarJar != lbl)
                ((LabelCheckTypeJarAbs) this._cmpTypeFileJarJar).setChecked(false);
            
            
            if (this._cmpTypeFileJarApk != lbl)
                ((LabelCheckTypeJarAbs) this._cmpTypeFileJarApk).setChecked(false);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        

        return true;
    }
    
    private boolean _doClickButton(RBTypeJarAbs rbn)
    {
        String strMethod = "_doClickButton(rbn)";

        if (rbn == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil rbn");
            return false;
        }   
        
        if (rbn.isSelected())
            return true;
            
        if (this._intMode == JFileChooser.SAVE_DIALOG) // coz mastered by another file selection with radioButtons for JFileChooser.OPEN_DIALOG (eg: select unsigned JAR file)
            _reset();

        rbn.setSelected(true);
        
        return true;
    }
    
    //
    
    private String _getDescFileJarCur()
    {
        String strMethod = "_getDescFileJarCur()";
        
        // --
        if (this._cmpTypeFileJarProjDoc != null)
        {
            if (this._cmpTypeFileJarProjDoc instanceof RBTypeJarAbs)
            {
                if (((RBTypeJarAbs) this._cmpTypeFileJarProjDoc).isSelected())
                {
                    return ((RBTypeJarAbs) this._cmpTypeFileJarProjDoc).getFileDesc();
                }      
            }

            else if (this._cmpTypeFileJarProjDoc instanceof LabelCheckTypeJarAbs)
            {
                if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjDoc).isChecked())
                {
                    return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjDoc).getFileDesc();
                }      
            }
        }
        
        // --
        // MODIF coz button hidden: march 14, 2003
        if (this._cmpTypeFileJarProjHelpSun != null)
        {
            if (this._cmpTypeFileJarProjHelpSun instanceof RBTypeJarAbs)
            {
                if (((RBTypeJarAbs) this._cmpTypeFileJarProjHelpSun).isSelected())
                {
                    return ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpSun).getFileDesc();
                }      
            }
            
            else if (this._cmpTypeFileJarProjHelpSun instanceof LabelCheckTypeJarAbs)
            {
                if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpSun).isChecked())
                {
                    return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpSun).getFileDesc();
                }      
            }
        }
        
        // --
        if (this._cmpTypeFileJarProjHelpOracle != null)
        {
            if (this._cmpTypeFileJarProjHelpOracle instanceof RBTypeJarAbs)
            {
                if (((RBTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).isSelected())
                {
                    return ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).getFileDesc();
                }      
            }
            
            else if (this._cmpTypeFileJarProjHelpOracle instanceof LabelCheckTypeJarAbs)
            {
                if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).isChecked())
                {
                    return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).getFileDesc();
                }      
            }
        }
        
        // --
        if (this._cmpTypeFileJarJar instanceof RBTypeJarAbs)
        {
            if (((RBTypeJarAbs) this._cmpTypeFileJarJar).isSelected())
            {
                return ((RBTypeJarAbs) this._cmpTypeFileJarJar).getFileDesc();
            }      
        }
        
        else if (this._cmpTypeFileJarJar instanceof LabelCheckTypeJarAbs)
        {
            if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarJar).isChecked())
            {
                return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarJar).getFileDesc();
            }      
        }

        // --
        if (this._cmpTypeFileJarApk instanceof RBTypeJarAbs)
        {
            if (((RBTypeJarAbs) this._cmpTypeFileJarApk).isSelected())
            {
                return ((RBTypeJarAbs) this._cmpTypeFileJarApk).getFileDesc();
            }      
        }
        
        else if (this._cmpTypeFileJarApk instanceof LabelCheckTypeJarAbs)
        {
            if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarApk).isChecked())
            {
                return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarApk).getFileDesc();
            }      
        }
        
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
    private String[] _getTypeFileJarCur()
    {
        String strMethod = "_getTypeFileJarCur()";
        
        // --
        if (this._cmpTypeFileJarProjDoc != null)
        {
            if (this._cmpTypeFileJarProjDoc instanceof RBTypeJarAbs)
            {
                if (((RBTypeJarAbs) this._cmpTypeFileJarProjDoc).isSelected())
                {
                    return ((RBTypeJarAbs) this._cmpTypeFileJarProjDoc).getNamesFileExtension();
                }      
            }

            else if (this._cmpTypeFileJarProjDoc instanceof LabelCheckTypeJarAbs)
            {
                if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjDoc).isChecked())
                {
                    return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjDoc).getNamesFileExtension();
                }      
            }
        }
        
        // --
        // MODIF coz button hidden: march 14, 2003
        if (this._cmpTypeFileJarProjHelpSun != null)
        {
            if (this._cmpTypeFileJarProjHelpSun instanceof RBTypeJarAbs)
            {
                if (((RBTypeJarAbs) this._cmpTypeFileJarProjHelpSun).isSelected())
                {
                    return ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpSun).getNamesFileExtension();
                }      
            }
            
            else if (this._cmpTypeFileJarProjHelpSun instanceof LabelCheckTypeJarAbs)
            {
                if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpSun).isChecked())
                {
                    return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpSun).getNamesFileExtension();
                }      
            }
        }
        
        // --
        if (this._cmpTypeFileJarProjHelpOracle != null)
        {
            if (this._cmpTypeFileJarProjHelpOracle instanceof RBTypeJarAbs)
            {
                if (((RBTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).isSelected())
                {
                    return ((RBTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).getNamesFileExtension();
                }      
            }
            
            else if (this._cmpTypeFileJarProjHelpOracle instanceof LabelCheckTypeJarAbs)
            {
                if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).isChecked())
                {
                    return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarProjHelpOracle).getNamesFileExtension();
                }      
            }
        }

        // --
        if (this._cmpTypeFileJarJar instanceof RBTypeJarAbs)
        {
            if (((RBTypeJarAbs) this._cmpTypeFileJarJar).isSelected())
            {
                return ((RBTypeJarAbs) this._cmpTypeFileJarJar).getNamesFileExtension();
            }      
        }
        
        else if (this._cmpTypeFileJarJar instanceof LabelCheckTypeJarAbs)
        {
            if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarJar).isChecked())
            {
                return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarJar).getNamesFileExtension();
            }      
        }
        
        // --
        if (this._cmpTypeFileJarApk instanceof RBTypeJarAbs)
        {
            if (((RBTypeJarAbs) this._cmpTypeFileJarApk).isSelected())
            {
                return ((RBTypeJarAbs) this._cmpTypeFileJarApk).getNamesFileExtension();
            }      
        }
        
        else if (this._cmpTypeFileJarApk instanceof LabelCheckTypeJarAbs)
        {
            if (((LabelCheckTypeJarAbs) this._cmpTypeFileJarApk).isChecked())
            {
                return ((LabelCheckTypeJarAbs) this._cmpTypeFileJarApk).getNamesFileExtension();
            }      
        }
        
        
        // ----
        // error
        MySystem.s_printOutError(this, strMethod, "failed");
        return null;
    }
    
    private void _reset()
    {
        if (super._tfdCurSelection_ != null)
            super._tfdCurSelection_.setText("");
        
        super._setSelectedValue_(false);
        
        if (super._btnClearSelection_ != null)
            super._btnClearSelection_.setEnabled(false);
    }
}