package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    known subclasses:
  
    . PTabUICmdKtlAbs ==> keytool (programmatically)
    . PTabUICmdJsrAbs ==> jarsigner (programmatically)
**/

import com.google.code.p.keytooliui.ktl.swing.button.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.border.S_Border;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.event.*;
import java.awt.*;

abstract public class PTabUICmdAbs extends PTabUIAbs implements
    DocumentListener,
    ActionListener
{    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void insertUpdate(DocumentEvent e); 
    abstract public void removeUpdate(DocumentEvent e); 
    abstract public void actionPerformed(ActionEvent evtAction);
    
    // ------------------
    // ABSTRACT PROTECTED

    abstract protected void _fillInPanelInput_();
    abstract protected void _fillInPanelOutput_();
    
    // ------
    // PUBLIC
    
    
    
    public void changedUpdate(DocumentEvent e) {}

    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._pnlSelectFileKst_ != null)
        {
            if (! this._pnlSelectFileKst_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        // --
        
        // add listeners
         
        
        /**if (! this._pnlSelectFileKst_.addDocListener(this))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }**/
        
        // --

        JPanel pnlContents = _createPanelContents();

        if (pnlContents == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil pnlContents");
            return false;
        }        
        
     
        
            
        JPanel pnlAction = _createPanelAction();
        
        //JPanel pnlTitle = _createPanelTitle();

        if (pnlAction == null)
            MySystem.s_printOutExit(this, strMethod, "nil pnlAction");
        
        
        JScrollPane spnContents = new JScrollPane(
            pnlContents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         

        
        
       
        //add(pnlTitle, BorderLayout.NORTH); 
        add(spnContents, BorderLayout.CENTER);
        add(pnlAction, BorderLayout.SOUTH); 
        
        // ----
        return true;
    }
    
    
    public void destroy()
    {
        if (this._pnlSelectFileKst_ != null)
        {
            this._pnlSelectFileKst_.destroy();
            this._pnlSelectFileKst_ = null;
        }
        
        if (this._btnAction_ != null)
        {
            this._btnAction_.removeActionListener(this);
            this._btnAction_ = null;
        }
        
        this._frmOwner_ = null;
        this._strTitleAppli_ = null;
        this._strPathAbsKst_ = null;
        
        this._pnlInput_ = null;
        this._pnlOutput_ = null;
    }
    
    
    // ---------
    // PROTECTED
    
    // I/O
    protected JPanel _pnlInput_ = null;
    protected JPanel _pnlOutput_ = null;
    
    // action
    protected JButton _btnAction_ = null;
    
    
    // contents
    protected PSelBtnTfdAbs _pnlSelectFileKst_ = null; // may remain nil
    protected String _strPathAbsKst_ = null;
    
    protected Frame _frmOwner_ = null;
    protected String _strTitleAppli_ = null;
 
    protected void _doneJob_(String strBody)
    {                    
        // oct 23, 05 tbrl with jdk1.5 ==> artifacts, dialog shows up also at the topLeft of the window!
        // not always!
        OPAbstract.s_showDialogInfo(
            this._frmOwner_, this._strTitleAppli_, strBody);        
            
    }
    
    
    protected PTabUICmdAbs(
        //String strTitleTab,
        String strHelpID,
        Frame frmOwner,
        String strTitleAppli,
        String strLabelBorderPanelIn, // nil value allowed
        String strLabelBorderPanelOut // nil value allowed
        )
    {
        super(strHelpID);
        
        //this._strTitleTab = strTitleTab;
        this._frmOwner_ = frmOwner;
        this._strTitleAppli_ = strTitleAppli;
        
        // new objects
        this._pnlInput_ = new JPanel();
        this._pnlOutput_ = new JPanel(); // !!!!
        
        this._btnAction_ = new BIcnAction((ActionListener) this); 
        
        // --
        if (strLabelBorderPanelIn != null)
            com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(this._pnlInput_, strLabelBorderPanelIn);
        else
            com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(this._pnlInput_, "Input:");
        
        if (strLabelBorderPanelOut != null)
            com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(this._pnlOutput_, strLabelBorderPanelOut);
        else
            com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(this._pnlOutput_, "Output:");
        
        
        // --
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEtched(this);
        
        
    }
    
    // -------
    // PRIVATE
    
    //private String _strTitleTab = null;
    
    /*private JPanel _createPanelTitle()
    {
        String strMethod = "_createPanelTitle()";
        
        java.awt.Font fnt = null;
        
        // !!!!!!!!!!!! should be static !!!!!!!!!
        try
        {
            fnt = new java.awt.Font("Freestyle Script", java.awt.Font.ITALIC|java.awt.Font.BOLD, 20);
        }   
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "exc caught, ignoring");
            fnt = new java.awt.Font(java.awt.Font.DIALOG_INPUT, java.awt.Font.ITALIC|java.awt.Font.BOLD, 16);
        }
        
        JLabel lbl = new JLabel();
        
        Color col = new Color(120, 0, 0);
        lbl.setForeground(col);
        lbl.setText(this._strTitleTab);
        
        if (fnt != null)
            lbl.setFont(fnt);
        
        
        JPanel pnl = new JPanel();
        
        Color colBg = new Color(220, 220, 220);
        pnl.setBackground(colBg);
        
        pnl.add(lbl);
        
        S_Border.s_setRaised(pnl);
        
        
        return pnl;  
    }*/
    
    private JPanel _createPanelAction()
    {
        JPanel pnl = new JPanel();
        pnl.add(this._btnAction_);
        
        S_Border.s_setRaised(pnl);
        
        return pnl;  
    }
    
    private JPanel _createPanelContents()
    {
        
        this._pnlOutput_.setLayout(new BoxLayout(this._pnlOutput_, BoxLayout.Y_AXIS));
        this._pnlOutput_.setAlignmentX(LEFT_ALIGNMENT);
        this._pnlOutput_.setAlignmentY(TOP_ALIGNMENT);
        
        
          
        // ----
        
        _fillInPanelInput_(); // done in subclasses
        _fillInPanelOutput_(); // done in subclasses
        
        // ---
            
        JPanel pnlContents = new JPanel();
        
        // ----
        
        pnlContents.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        if (this._pnlInput_.getComponentCount() > 0)
        {    
            pnlContents.add(this._pnlInput_, gbc);
            gbc.gridy ++;
        }
        
        if (this._pnlOutput_.getComponentCount() > 0)
        {
            pnlContents.add(this._pnlOutput_, gbc);
        }
               
        return pnlContents;
    }
}