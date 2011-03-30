/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
    "Kst" means "keystore"
**/


import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

final public class PTabUICmdKtlKstSave extends PTabUICmdKtlAbs
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static public String STR_TITLETASK = "Create keystore without any entries";
    
    final static private String _s_strDlgInfoActionBodyPref1 = 
        "Successfully created an empty keystore of type ";
        
    final static private String _s_strDlgInfoActionBodyPref2 = 
        "." +
        "\n\n" +
        "File location:" +
        "\n" + "  ";
    
    // --------------
    // STATIC PRIVATE
    
    
    static private String _s_strHelpID = null;

    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdKtlKstSave";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdKtlKstSave" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
              
            _s_strHelpID = "_create_kst_"; // rbeResources.getString("helpID");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }
    }
    
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        String strFormatKst = ((PSelBtnTfdFileSaveKst) super._pnlSelectFileKst_).getSelectedFormatFile();
        
        if (strFormatKst == null)
            MySystem.s_printOutExit(this, strMethod, "nil strFormatKst");
        
        
        //if (super._strPasswdKst_ == null)
          //  MySystem.s_printOutExit(this, strMethod, "nil super._strPasswdKst_");
                    
        char[] chrsPasswdKst = null;
        
        if (super._strPasswdKst_ != null)
            chrsPasswdKst = super._strPasswdKst_.toCharArray();
        else
            chrsPasswdKst = "".toCharArray(); // no password
        
        KTLKstSaveAbs ktl = null;
        
        // --
        
        if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKstSaveJks(
                super._frmOwner_, 
                super._strTitleAppli_,
                super._strPathAbsKst_, 
                chrsPasswdKst
                );
        }   
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstJceks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKstSaveJceks(
                super._frmOwner_, 
                super._strTitleAppli_,
                super._strPathAbsKst_, 
                chrsPasswdKst
                );
        }  
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstPkcs12.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKstSavePkcs12(
                super._frmOwner_, 
                super._strTitleAppli_,
                super._strPathAbsKst_, 
                chrsPasswdKst
                );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstBks.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKstSaveBks(
                super._frmOwner_, 
                super._strTitleAppli_,
                super._strPathAbsKst_, 
                chrsPasswdKst
                );
        }
        
        else if (strFormatKst.toLowerCase().compareTo(
            com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstUber.f_s_strKeystoreType.toLowerCase()) == 0)
        {
            ktl = new KTLKstSaveUber(
                super._frmOwner_, 
                super._strTitleAppli_,
                super._strPathAbsKst_, 
                chrsPasswdKst
                );
        }
        
        else
        {
            MySystem.s_printOutExit(this, strMethod, "uncaught value, strFormatKst=" + strFormatKst);
        }
        
               
        if (ktl.doJob())
        {
            MySystem.s_printOutTrace(this, strMethod, "OK!");
            
            String strBody = _s_strDlgInfoActionBodyPref1;
            strBody += strFormatKst;
            strBody += _s_strDlgInfoActionBodyPref2;
            strBody += super._strPathAbsKst_;
                    
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogInfo(
                super._frmOwner_, super._strTitleAppli_, strBody);
        }
        
        else
            MySystem.s_printOutTrace(this, strMethod, "either aborted by user or failed");
    }
    
    public void insertUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "insertUpdate(evtDocument)";
        
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        
        
        // ----
        
        int intLength = doc.getLength();
        
        if (intLength == 0)
            MySystem.s_printOutExit(this, strMethod, "intLength == 0");
            
        String strText = null;
        
        try
        {
            strText = doc.getText(0, intLength);
        }
            
        catch(BadLocationException excBadLocation)
        {
            excBadLocation.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excBadLocation caught");
        }
        
        // --------
        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = strText;
            _updateActionButtonDataChanged(true);
            return;
        }
        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = strText;
            //_updateActionButtonDataChanged(true);
            return;
        }
        
       
        
        
        // ------------
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
    public void removeUpdate(DocumentEvent evtDocument)
    {
        String strMethod = "removeUpdate(evtDocument)";
  
        Document doc = evtDocument.getDocument();
        
        if (doc == null)
            MySystem.s_printOutExit(this, strMethod, "nil doc");
            
        Object objPropVal = doc.getProperty(com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey);
        
        if (objPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil objPropVal");
            
        if (! (objPropVal instanceof String))
            MySystem.s_printOutExit(this, strMethod, "! (objPropVal instanceof String)");
            
        String strPropVal = (String) objPropVal;
        
        if (strPropVal == null)
            MySystem.s_printOutExit(this, strMethod, "nil strPropVal");
        
        
        // --------

        
        if (strPropVal.compareTo(PSelBtnTfdFileSaveKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            _updateActionButtonDataChanged(false);
            return;
        }

        
        if (strPropVal.compareTo(PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal) == 0)
        {
            super._strPasswdKst_ = null;
            //_updateActionButtonDataChanged(false);
            return;
        }
        
  

        // -------
        
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        // --
        // alignment
        
        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();
        vecPanel.add(super._pnlSelectPasswdKst_);
        vecPanel.add(super._pnlSelectFileKst_);
        
        
        
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // ---------
        return true;
    }
    
    
    public PTabUICmdKtlKstSave(
        Frame frmOwner, 
        String strTitleAppli)
    {
        super(
            PTabUICmdKtlKstSave._s_strHelpID, 
            frmOwner, 
            strTitleAppli,
            BESPasswordAbs.f_s_intModeSave,
            (String) null,     // String strLabelBorderPanelIn, // nil value allowed
            "Target:"          // String strLabelBorderPanelOut // nil value allowed
            );
            
        super._pnlSelectFileKst_ = new PSelBtnTfdFileSaveKst(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli,
            (java.awt.event.ItemListener) null,
            true, // blnFieldRequiredKeystore
            true, // blnAllowTypePkcs12
            true,  // blnAllowTypeBks
            true  // blnAllowTypeUber
            ); 
        
    }
    
    // ---------
    // PROTECTED
    
    // void
    protected void _fillInPanelInput_() {}
    
    protected void _fillInPanelOutput_()
    {   
        super._fillInPanelKst_(super._pnlOutput_);
        super._pnlOutput_.add(Box.createRigidArea(new Dimension(1, 10)));
    }
    
    // -------
    // PRIVATE

    
    
    private void _updateActionButtonDataChanged(boolean blnFieldInserted)
    {        
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;

            if (super._strPathAbsKst_ == null)
                return;
                
            //if (super._strPasswdKst_ == null)
              //  return;

            // --
            super._btnAction_.setEnabled(true);
            return;
        }
        
        else
        {
            if (! super._btnAction_.isEnabled())
                return;

            if (super._strPathAbsKst_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }
            
            /*if (super._strPasswdKst_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }*/

            // --
            return;
        }
    }
    
}