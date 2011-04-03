/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    known subclasses:
    . shared: DFindPage
    

**/


import com.google.code.p.keytooliui.shared.lang.*;
 
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
 
import java.awt.*;
import java.awt.event.*;
import com.google.code.p.keytooliui.shared.swing.checkbox.CBAbs;
import com.google.code.p.keytooliui.shared.swing.button.*;

abstract public class DFindAbstract extends DEscapeAbstract implements
    ActionListener,
    DocumentListener,
    KeyListener // test
{    
    // --------------
    // STATIC PRIVATE

    static private String _s_strCheckboxTextMatchCase = null;
    static private String _s_strCheckboxTextWholeWord = null;
    
    static private String _s_strCheckboxTipMatchCase = null;
    static private String _s_strCheckboxTipWholeWord = null;
    //static private String _s_strCheckboxTipLists = null;
    //static private String _s_strCheckboxTipBody = null;
    
    static private String _s_strLabelTextFindWhat = null;
    
    // ------------------
    // STATIC INITIALIZER
        

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DFindAbstract";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DFindAbstract" // class name
            ;
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strCheckboxTextMatchCase = rbeResources.getString("checkboxTextMatchCase"); 
            _s_strCheckboxTextWholeWord = rbeResources.getString("checkboxTextWholeWord"); 
            
            _s_strCheckboxTipMatchCase = rbeResources.getString("checkboxTipMatchCase"); 
            _s_strCheckboxTipWholeWord = rbeResources.getString("checkboxTipWholeWord"); 
              
            _s_strLabelTextFindWhat = rbeResources.getString("labelTextFindWhat");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
            
        }
        
        strWhere = null;
        strBundleFileShort = null;
    } 
    
    // ---------
    
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void keyReleased(KeyEvent evtKey);
    
    
    // ------
    // PUBLIC
    
    public void keyTyped(KeyEvent evtKey) {}
    public void keyPressed(KeyEvent evtKey) {}
   
    public void insertUpdate(DocumentEvent evtDocument)
    {
        if (evtDocument == null)
            return;
        
        if (evtDocument.getDocument() == this._docTextFieldFileNameNew)
            _updateOkButtonEnablingStatus(this._docTextFieldFileNameNew);
    }   
    
    public void removeUpdate(DocumentEvent evtDocument)
    {
        //String strMethod = "removeUpdate(evtDocument)";
        
        if (evtDocument == null)
            return;
        
        if (evtDocument.getDocument() == this._docTextFieldFileNameNew) 
            _updateOkButtonEnablingStatus(this._docTextFieldFileNameNew);
    }   
    
    // ?????????????????????
    public void changedUpdate(DocumentEvent evtDocument)
    {
        //final String strMethod = "changedUpdate(evtDocument)";
        
        if (evtDocument == null)
            return;
        
        if (evtDocument.getDocument() == this._docTextFieldFileNameNew) 
            _updateOkButtonEnablingStatus(this._docTextFieldFileNameNew);
    }   
    
    public boolean init()
    {
        return true;
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
       
        if (! (evtAction.getSource() instanceof JButton))
        {
            MySystem.s_printOutExit(this, strMethod, "wrong instance");
            
        }
        
        JButton btnSource = (JButton) evtAction.getSource();
        
        if (btnSource instanceof BClose)
        {
            super._cancel_();
            return;
        }
        
           
        MySystem.s_printOutExit(this, strMethod, "unknown source");
        
    }
    
    // ---------
    // PROTECTED
    
    protected JPanel _pnlTop_ = null;
    protected JButton _btnFindNow_ = null;
    protected JTextField _tfd_ = null;
    protected CBAbs _cbxMatchCase_ = null;
    protected CBAbs _cbxWholeWord_ = null;
    
    
    public void destroy()
    {  
        this._pnlTop_ = null;
        
        
        if (this._docTextFieldFileNameNew != null)
        {
            this._docTextFieldFileNameNew.removeDocumentListener(this);
            this._docTextFieldFileNameNew = null;
        }
        
        
        if (this._btnFindNow_ != null)
        {
            this._btnFindNow_.removeActionListener(this);
            this._btnFindNow_ = null;
        }
        
        
        if (this._docTextFieldFileNameNew != null)
        {
            this._docTextFieldFileNameNew.removeDocumentListener(this);
            this._docTextFieldFileNameNew = null;
        }
        
        if (this._tfd_ != null)
        {
            this._tfd_.removeKeyListener(this); // test
            this._tfd_ = null;
        }
        
        if (this._cbxMatchCase_ != null)
        {
            this._cbxMatchCase_.destroy();
            this._cbxMatchCase_ = null;
        }
        
        if (this._cbxWholeWord_ != null)
        {
            this._cbxWholeWord_.destroy();
            this._cbxWholeWord_ = null;
        }
        
        if (this._btnClose != null)
        {
            this._btnClose.destroy();
            this._btnClose = null;
        }
        
        
        
        super.destroy();
    }
    
    protected DFindAbstract(Component cmpFrameOwner, String strTitle,
        String strButtonTextFindNow, String strButtonTipFindNow)
    {
        super((Frame) cmpFrameOwner, true);
        
       
        setTitle(System.getProperty("_appli.title") + " - " + strTitle);
        
       
        
        // children
        
        JPanel pnlText = _createPanelText();
        JPanel pnlCheck = _createPanelCheck();
        JPanel pnlButtons = _createPanelButtons(strButtonTextFindNow, strButtonTipFindNow);
        
        // --
        JPanel pnlAll = new JPanel();
        this._pnlTop_ = new JPanel();
        
        
        // --
        pnlAll.setLayout(new BoxLayout(pnlAll, BoxLayout.Y_AXIS));
        this._pnlTop_.setLayout(new BoxLayout(this._pnlTop_, BoxLayout.Y_AXIS));
        
        // --
        
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEtched(pnlAll);
        
        // --
	    this._pnlTop_.add(pnlText);
        this._pnlTop_.add(Box.createVerticalStrut(10));
        this._pnlTop_.add(pnlCheck);
        
        pnlAll.add(this._pnlTop_);
        pnlAll.add(Box.createVerticalStrut(10));
        pnlAll.add(pnlButtons);
	        
	     // ----
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        cntContentPane.add(pnlAll, BorderLayout.CENTER);
    }

    
    
    
    // -------
    // PRIVATE
    
    private Document _docTextFieldFileNameNew = null;
    private BAbs _btnClose = null;
    
    private JPanel _createPanelText()
    {
        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        JLabel lbl = new JLabel(_s_strLabelTextFindWhat);
        
        this._tfd_ = new JTextField();
        this._tfd_.addKeyListener(this); // test
        
        
        this._tfd_.setEditable(true);
        this._docTextFieldFileNameNew = this._tfd_.getDocument();
        this._docTextFieldFileNameNew.addDocumentListener(this);

        pnl.add(lbl);
        pnl.add(Box.createHorizontalStrut(10));
        pnl.add(this._tfd_);
            
        return pnl;
    }
    
    private JPanel _createPanelCheck()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        
        this._cbxMatchCase_ = new com.google.code.p.keytooliui.shared.swing.checkbox.CBText(
            (ActionListener) null, _s_strCheckboxTextMatchCase, _s_strCheckboxTipMatchCase);
            
        this._cbxWholeWord_ = new com.google.code.p.keytooliui.shared.swing.checkbox.CBText(
            (ActionListener) null, _s_strCheckboxTextWholeWord, _s_strCheckboxTipWholeWord);
   
        this._cbxMatchCase_.setSelected(false);
        this._cbxWholeWord_.setSelected(false);
        
        pnl.add(this._cbxMatchCase_);
        pnl.add(this._cbxWholeWord_);
        
        return pnl;
    }
    
    
    private JPanel _createPanelButtons(String strButtonTextFindNow, String strButtonTipFindNow)
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        
        this._btnClose = new BClose((ActionListener) this);
        
        this._btnFindNow_ = new JButton();
        this._btnFindNow_.addActionListener(this);
        this._btnFindNow_.setText(strButtonTextFindNow);
        this._btnFindNow_.setToolTipText(strButtonTipFindNow);
        
        this._btnFindNow_.setEnabled(false);
        
        // ----
        pnl.setAlignmentX(LEFT_ALIGNMENT);
	    pnl.setAlignmentY(TOP_ALIGNMENT);
        
        pnl.add(this._btnClose);
        pnl.add(Box.createHorizontalStrut(10));
        pnl.add(this._btnFindNow_);  
        return pnl;
    }
    
    private void _updateOkButtonEnablingStatus()
    {
        String strMethod = "_updateOkButtonEnablingStatus()";
        
        if (this._tfd_==null || this._btnFindNow_==null)
        {
            return;
        }
        
        String strText = this._tfd_.getText();
        
        if (strText == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil field");
            
        }
        
        if (strText.trim().length() < 1)
        {
            this._btnFindNow_.setEnabled(false);
            return;
        }
        
        this._btnFindNow_.setEnabled(true);
    }
    
    private void _updateOkButtonEnablingStatus(Document doc)
    {            
        if (doc.getLength() < 1)
        {
            this._btnFindNow_.setEnabled(false);
            return;
        }
        
        _updateOkButtonEnablingStatus();       
    }
    
}