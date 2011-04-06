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

import com.google.code.p.keytooliui.ktl.util.jarsigner.XmlVerif;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.panel.PSelAbs;
import net.miginfocom.swing.MigLayout;

final public class PTabUICmdXmlSigVerify extends PTabUICmdXmlAbs
{
    final static public String STR_TITLETASK = "Verify signed XML document with DSA_SHA1 or RSA_SHA1 signature algorithm";
    
    // --------------------
    // FINAL STATIC PRIVATE

    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strHelpID = null;
    
    //static private String _s_strDlgErrorActionBody = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        /*String strWhere = "com.google.code.p.keytooliui.ktl.swing.panel.PTabUICmdXmlSigVerify";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".PTabUICmdXmlSigVerify" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
          */      
            _s_strHelpID = "_verify_emb_xml_"; // rbeResources.getString("helpID");
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
        
        XmlVerif xvf = new  XmlVerif(super._frmOwner_,  super._strPathAbsXmlSigned_);
        
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

        //vecPanel.add(super._pnlSelectFileKst_);
        // MEMO: CANNOT ADD Certs' stuff, assuming Certs' text is always less in size than others!
        vecPanel.add(super._fssSelectSignedXml_);        
        
        if (! PSelAbs.s_alignLabels(vecPanel))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        //_alignLabelsEntries();
        
        // ---------
        return true;
    }
    
    
    public PTabUICmdXmlSigVerify(Frame frmOwner)
    {
        super(
            frmOwner, 
        
            PTabUICmdXmlSigVerify._s_strHelpID, 
            false // blnFieldRequiredKeystore
            );
        
        
    }
    
    // ---------
    // PROTECTED
    
    protected void _fillInPanelInput_()
    {
        super._pnlInput_.setLayout(new MigLayout("fill, wrap 1", "[left]"));
        super._pnlInput_.add(super._fssSelectSignedXml_);
    }
    
    protected void _fillInPanelOutput_() {}
    
    protected void _updateActionButtonDataChanged_(boolean blnFieldInserted)
    {
        if (blnFieldInserted)
        {
            if (super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsXmlSigned_ == null)
                return;

            // --
            super._btnAction_.setEnabled(true);
            return;
        }
        
        else
        {
            if (! super._btnAction_.isEnabled())
                return;
            
            if (super._strPathAbsXmlSigned_ == null)
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
