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

final public class PSelBtnTfdFileOpenKst extends PSelBtnTfdFileOpenAbs
{   
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_file_kst_open";
    final static public String s_strDirNameDefault = "mykeystores";
    final static public String f_s_strLabel = "Keystore file:";
    
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
        
        // 
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
        
        if (this._btnTypeFileKstJks != null)
            if (! this._btnTypeFileKstJks.init())
                return false;
        
        if (this._btnTypeFileKstJceks != null)
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
    
    public PSelBtnTfdFileOpenKst(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        ItemListener itmListenerParent,
        String strTextLabel, // ie. root CA certs store
        boolean blnFieldRequired,
        boolean blnAllowTypeJks,
        boolean blnAllowTypeJceks,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            strTextLabel,
            blnFieldRequired
            );
        
        
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, (Object) PSelBtnTfdFileOpenKst.f_s_strDocPropVal);
        
                
        _construct(itmListenerParent, blnAllowTypeJks, 
                blnAllowTypeJceks, blnAllowTypePkcs12, blnAllowTypeBks, blnAllowTypeUber);
    }
    

    public PSelBtnTfdFileOpenKst(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        ItemListener itmListenerParent,
        boolean blnFieldRequired,
        boolean blnAllowTypeJks,
        boolean blnAllowTypeJceks,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        this(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            itmListenerParent,
            PSelBtnTfdFileOpenKst.f_s_strLabel,
            blnFieldRequired,
            blnAllowTypeJks,
            blnAllowTypeJceks,
            blnAllowTypePkcs12,
            blnAllowTypeBks,
            blnAllowTypeUber);
    }
    
    public PSelBtnTfdFileOpenKst(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        ItemListener itmListenerParent,
        boolean blnFieldRequired,
        boolean blnAllowTypeJks,
        boolean blnAllowTypeJceks,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber,
        String strSuffixDocPropVal // in case more than on panel of same class in the same tab, used to differentiate
        )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            PSelBtnTfdFileOpenKst.f_s_strLabel,
            blnFieldRequired
            );
        
        String strDocPropVal = PSelBtnTfdFileOpenKst.f_s_strDocPropVal + strSuffixDocPropVal;
        
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, (Object) strDocPropVal);
        
                
        _construct(itmListenerParent, blnAllowTypeJks, 
                blnAllowTypeJceks, blnAllowTypePkcs12, blnAllowTypeBks, blnAllowTypeUber);
    }
    
    // ---------
    // PROTECTED
   
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";
        
        
        String[] strsTypeFileKstCur = _getTypeFileKstCur();
        
        
        if (strsTypeFileKstCur == null)
            MySystem.s_printOutExit(this, strMethod, "nil strsTypeFileKstCur");

         //String strFileExt = strsTypeFileKstCur.toLowerCase();
         //String strFileDesc = "strsTypeFileKstCur + PSelBtnTfdFileAbs._f_s_strSuffixFileDesc_";
        
        String strFileDesc = _getDescFileKstCur();
        
        if (strFileDesc == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFileDesc");
        
        // ----
        
        File fle = null;
        
        String strButtonTextOk = "Open file";
            
        fle = S_FileChooserUI.s_getOpenFile(
            super._strTitleAppli_, 
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
    
    private void _construct(
        ItemListener itmListenerParent,
        boolean blnAllowTypeJks,
        boolean blnAllowTypeJceks,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        int intNbRadioButton = 0;
        
        if (blnAllowTypeJks)
            intNbRadioButton ++;
        if (blnAllowTypeJceks)
            intNbRadioButton ++;
        if (blnAllowTypePkcs12)
            intNbRadioButton ++;
        if (blnAllowTypeBks)
            intNbRadioButton ++;
        if (blnAllowTypeUber)
            intNbRadioButton ++;
        
        boolean blnEnabledButton = true;
        
        if (intNbRadioButton < 2)
            blnEnabledButton = false;
        
        if (blnAllowTypeJks)
        {            
            this._btnTypeFileKstJks = new RBTypeKstJks(
                blnEnabledButton, // blnEnabledButton
                itmListenerParent);
        }
          
        if (blnAllowTypeJceks)
        {   
            this._btnTypeFileKstJceks = new RBTypeKstJceks(
                blnEnabledButton, // blnEnabledButton
                itmListenerParent);
        }
        
        if (blnAllowTypePkcs12)
        {   
            this._btnTypeFileKstPkcs12 = new RBTypeKstPkcs12(
                blnEnabledButton, // blnEnabledButton
                itmListenerParent);
        }   
        
        if (blnAllowTypeBks)
        {   
            this._btnTypeFileKstBks = new RBTypeKstBks(
                blnEnabledButton, // blnEnabledButton
                itmListenerParent);
        }   
        
        if (blnAllowTypeUber)
        {
            this._btnTypeFileKstUber = new RBTypeKstUber(
                blnEnabledButton, // blnEnabledButton
                itmListenerParent);
        }  
        
        if (this._btnTypeFileKstJks != null)
            this._btnTypeFileKstJks.addItemListener(this);
        
        if (this._btnTypeFileKstJceks != null)
            this._btnTypeFileKstJceks.addItemListener(this);
        
        if (this._btnTypeFileKstPkcs12 != null)
            this._btnTypeFileKstPkcs12.addItemListener(this);
            
        if (this._btnTypeFileKstBks != null)
            this._btnTypeFileKstBks.addItemListener(this);
            
        if (this._btnTypeFileKstUber != null)
            this._btnTypeFileKstUber.addItemListener(this);
    }
    
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
        grouping  Jks & JHR & RCR files
    **/
    private boolean _addGroup()
    {
        String strMethod = "_addGroup()";
        
        // adding radioButtons/labelChecks for selecting in between JAR, and JHR, and RCR files
        
        
        // ----
        
 
        ButtonGroup bgp = new ButtonGroup();
        
        if (this._btnTypeFileKstJks != null)
            bgp.add(this._btnTypeFileKstJks);
        
        if (this._btnTypeFileKstJceks != null)
            bgp.add(this._btnTypeFileKstJceks);
        
        if (this._btnTypeFileKstPkcs12 != null)
            bgp.add(this._btnTypeFileKstPkcs12);
            
        if (this._btnTypeFileKstBks != null)
            bgp.add(this._btnTypeFileKstBks);
            
        if (this._btnTypeFileKstUber != null)
            bgp.add(this._btnTypeFileKstUber);
            
            
        // --
        if (this._btnTypeFileKstJks != null)
	    this._btnTypeFileKstJks.setSelected(true);
        else if (this._btnTypeFileKstJceks != null)
            this._btnTypeFileKstJceks.setSelected(true);
        else if (this._btnTypeFileKstPkcs12 != null)
            this._btnTypeFileKstPkcs12.setSelected(true);
        else if (this._btnTypeFileKstBks != null)
            this._btnTypeFileKstBks.setSelected(true);
        else if (this._btnTypeFileKstUber != null)
            this._btnTypeFileKstUber.setSelected(true);
        else
        {
            MySystem.s_printOutError(this, strMethod, "no valid button found");
            return false;
        }
   
        
        // else label: done at construction time
        
        // --
        JPanel pnlTypeFileKst = new JPanel();
        pnlTypeFileKst.setLayout(new BoxLayout(pnlTypeFileKst, BoxLayout.Y_AXIS));
        
        if (this._btnTypeFileKstJks != null)
            pnlTypeFileKst.add(this._btnTypeFileKstJks);
        
        if (this._btnTypeFileKstJceks != null)
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
        
        if (this._btnTypeFileKstJks != null)
            if (this._btnTypeFileKstJks.isSelected())
            {
                return this._btnTypeFileKstJks.getNamesFileExtension();
            }    
        
        if (this._btnTypeFileKstJceks != null)
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
        
        if (this._btnTypeFileKstUber!= null)
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
        
        if (this._btnTypeFileKstJks != null)
            if (this._btnTypeFileKstJks.isSelected())
            {
                return this._btnTypeFileKstJks.getFileDesc();
            }    
        
        if (this._btnTypeFileKstJceks != null)
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