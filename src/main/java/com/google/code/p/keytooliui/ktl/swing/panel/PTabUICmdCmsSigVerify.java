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

**/

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.google.code.p.keytooliui.ktl.util.jarsigner.CmsVerif;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.panel.PSelAbs;
import net.miginfocom.swing.MigLayout;

public final class PTabUICmdCmsSigVerify extends PTabUICmdCmsAbs
{
    public static final String STR_TITLETASK = "Verify signed data with CMS detached signature file";
    
    // --------------------
    // PRIVATE STATIC FINAL

    
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strHelpID = null;
    
    //private static String _s_strDlgErrorActionBody = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdCmsSigVerify";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdCmsSigVerify" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
          */      
            _s_strHelpID = "_verify_det_cms_"; // rbeResources.getString("helpID");
        /*}
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught, " + strBundleFileLong + " not found");
        }*/
    }
    
    // ------
    // PUBLIC
    
    // void
    public void itemStateChanged(ItemEvent evtItem) {}
    
    public void actionPerformed(ActionEvent evtAction)
    {
        
        String strMethod = "actionPerformed(evtAction)";
        
        CmsVerif xvf = new  CmsVerif(super._frmOwner_,  
                super._strPathAbsFileData_, super._strPathAbsCmsSigned_);
        
        if (! xvf.doJob())
        {
            MySystem.s_printOutWarning(this, strMethod, "failed, could be aborted by user");
            return;
        }
        
        MySystem.s_printOutTrace(this, strMethod, "DONE!");
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
        

        // ----
        
        if (super._doneRemoveUpdate_(strPropVal))
        {
            return;
        }
        
        // ----
        
        /*if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = null;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }*/
        
        // ------- 
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
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
        
        // -----
        
        // -----
        
        if (super._doneInsertUpdate_(strPropVal, strText))
        {
            return;
        }
        
        
        // ----
        
        /*if (strPropVal.compareTo(PSelBtnTfdFileOpenKst.f_s_strDocPropVal) == 0)
        {
            super._strPathAbsKst_ = strText;
            // MEMO: NO UPDATE COZ THIS ONE IS OPTIONAL!
            return;
        }*/
        
        // ------------
        MySystem.s_printOutExit(this, strMethod, "uncaught strPropVal, strPropVal=" + strPropVal);
    }
    
   
    /*public void destroy()
    {
        super.destroy();
    }*/
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        // alignment
        
        java.util.Vector<PSelAbs> vecPanel = new java.util.Vector<PSelAbs>();

        vecPanel.add(super._fssSelectSignedCms_);   
        vecPanel.add(super._pnlSelectFileData_);
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ---------
        return true;
    }
    
    
    public PTabUICmdCmsSigVerify(Frame frmOwner)
    {
        super(
            frmOwner, 
        
            PTabUICmdCmsSigVerify._s_strHelpID, 
            false // blnFieldRequiredKeystore
            );
        
        
    }
    
    // ---------
    // PROTECTED
    
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlInput_.add(super._pnlSelectFileData_);
        super._pnlInput_.add(super._fssSelectSignedCms_);
    }
    
    protected void _fillInPanelOutput_() {}
    
    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsCmsSigned_ == null)
                return;
          
            if (super._strPathAbsFileData_ == null)
                return;

            // --
            super._btnAction_.setEnabled(true);
            return;
        }
        
        else
        {
            if (! super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsCmsSigned_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }
            
            if (super._strPathAbsFileData_ == null)
            {
                super._btnAction_.setEnabled(false);
                return;
            }

            // --
            return;
        }
    }
    
    // -------
    // PRIVATE

}

