package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    known subclasses:
  
    . PTabUICmdKtlAbs ==> keytool (programmatically)
    . PTabUICmdJsrAbs ==> jarsigner (programmatically)
**/

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.google.code.p.keytooliui.ktl.swing.button.BIcnAction;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.border.S_Border;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import com.google.code.p.keytooliui.shared.swing.panel.PSelBtnTfdAbs;
import net.miginfocom.swing.MigLayout;

public abstract class PTabUICmdAbs extends PTabUIAbs implements
    DocumentListener,
    ActionListener
{    
    // ---------------
    // ABSTRACT PUBLIC
    
    public abstract void insertUpdate(DocumentEvent e); 
    public abstract void removeUpdate(DocumentEvent e); 
    public abstract void actionPerformed(ActionEvent evtAction);
    
    // ------------------
    // ABSTRACT PROTECTED

    protected abstract void _fillInPanelInput_();
    protected abstract void _fillInPanelOutput_();
    
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

 
    protected void _doneJob_(String strBody)
    {                    
        // oct 23, 05 tbrl with jdk1.5 ==> artifacts, dialog shows up also at the topLeft of the window!
        // not always!
        OPAbstract.s_showDialogInfo(
            this._frmOwner_,  strBody);        
            
    }
    
    
    protected PTabUICmdAbs(
        //String strTitleTab,
        String strHelpID,
        Frame frmOwner,

        String strLabelBorderPanelIn, // nil value allowed
        String strLabelBorderPanelOut // nil value allowed
        )
    {
        super(strHelpID);
        
     
        this._frmOwner_ = frmOwner;

        
        // new objects
        this._pnlInput_ = new JPanel();
        this._pnlOutput_ = new JPanel(); // !!!!
        
        this._btnAction_ = new BIcnAction((ActionListener) this); 
        
        // --
        this._pnlInput_.setBorder(new TitledBorder(strLabelBorderPanelIn != null ? strLabelBorderPanelIn : "Input"));
        this._pnlOutput_.setBorder(new TitledBorder(strLabelBorderPanelOut != null ? strLabelBorderPanelOut : "Output"));
        
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
        _pnlOutput_.setLayout(new BoxLayout(this._pnlOutput_, BoxLayout.Y_AXIS));
        _pnlOutput_.setAlignmentX(LEFT_ALIGNMENT);
        _pnlOutput_.setAlignmentY(TOP_ALIGNMENT);
        
        _fillInPanelInput_(); // done in subclasses
        _fillInPanelOutput_(); // done in subclasses
        
            
        JPanel pnlContents = new JPanel();
        pnlContents.setLayout(new MigLayout("center, wrap 1", "[fill]"));
        
        if (_pnlInput_.getComponentCount() > 0)
        {    
            pnlContents.add(_pnlInput_);
        }
        
        if (_pnlOutput_.getComponentCount() > 0)
        {
            pnlContents.add(_pnlOutput_);
        }
        
        return pnlContents;
    }
}
