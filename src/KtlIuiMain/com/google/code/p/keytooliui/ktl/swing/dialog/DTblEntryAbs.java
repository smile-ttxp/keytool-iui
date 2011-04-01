package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.*;
import java.awt.event.*;
import java.security.*;
import javax.swing.*;
import com.google.code.p.keytooliui.ktl.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BCancel;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;


abstract public class DTblEntryAbs extends DEscapeAbstract implements
    ActionListener
{
    // ------
    // public
    
    public boolean init()
    {
        String strMethod = "init()";
        
        // ----
        
        if (! this._pnlTable_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // contents
        this._pnlContentsAll.setBorder(new javax.swing.border.EmptyBorder(4, 4, 4, 4));
        
        this._pnlContentsAll.add(this._pnlTable_, BorderLayout.CENTER);
        this._pnlContentsAll.add(this._pnlContentsTextfields_, BorderLayout.SOUTH);
        
        this._pnlContentsTextfields_.add(this._lblAlias);
        this._pnlContentsTextfields_.add(this._tfdAlias_);
        
        // action
        
        this._pnlAction.add(this._btnOk);
        this._pnlAction.add(this._btnCancel);

        // all 
        
        getContentPane().add(this._pnlContentsAll, BorderLayout.CENTER);
        getContentPane().add(this._pnlAction, BorderLayout.SOUTH);
        //setResizable(false); // TEMPO
        getRootPane().setDefaultButton(this._btnOk);
        
        
        // ----
        return true;
    }
    
    public void actionPerformed(ActionEvent evt) 
    {
        String strMethod = "actionPerformed(evt)";
        
        if (evt.getSource() instanceof JButton)
        {
            JButton btn = (JButton) evt.getSource();
            
            if (btn == this._btnOk)
            {
                 if (! _contentsOk_())
                    return;
            }
            
            else if (btn instanceof BCancel)
            {
                 this._tfdAlias_ = null;
            }
            
            else
            {
                MySystem.s_printOutExit(this, strMethod, "uncaught button, btn.getText()=" + btn.getText());
                return; // !!
            }
        }
        
        else
        {
            this._tfdAlias_ = null;
        }
        
        _cancel_();
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._pnlTable_ != null)
        {
            this._pnlTable_.destroy();
            this._pnlTable_ = null;
        }
    }
    
    public void windowClosing(WindowEvent evt)
    {
        this._tfdAlias_ = null;
        super.windowClosing(evt);
    }
    
    public String getAlias()
    {
        if (this._tfdAlias_ == null)
            return null;
            
        return this._tfdAlias_.getText();
    }
    
    // ------------------
    // ABSTRACT PROTECTED
    
    abstract protected boolean _contentsOk_();
    
    // ---------
    // protected 
    protected JPanel _pnlContentsTextfields_ = null;
    protected JTextField _tfdAlias_;
    protected PTblEntryAbs _pnlTable_ = null;
    protected KeyStore _kseLoaded_ = null;
    
    
    
    
    protected boolean _contentsAliasOk_(
        boolean blnSave // Save v/s Open (create v/s select)
        )
    {
        String strMethod = "_contentsAliasOk_(blnSave)";
        
        // check alias
        
        String strAlias = this._tfdAlias_.getText();
        
        if (strAlias.length() < 1)
        {
            if (blnSave)
                OPAbstract.s_showDialogWarning(this, "Please enter alias");
            else
                OPAbstract.s_showDialogWarning(this, "Please click a valid alias");
            
            return false;
        }
        
        // --
        
        if (blnSave)
        {
            if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedAlias(strAlias))
            {
                String strBody = "Alias value not allowed:";
                strBody += "\n";
                strBody += "  ";
                strBody += "\"";
                strBody += strAlias;
                strBody += "\"";
                        
                strBody += "\n\n";
                strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRuleAlias();
                        
                OPAbstract.s_showDialogWarning(this, strBody);
                
                return false;
            }
        }
        
        // --
        
        if (blnSave) // (create)
        {
            // alias should not already exist in KeyStore
            if (this._keystoreContainsAlias(strAlias))
            {
                String strBody = "Alias already listed in keystore:";
                strBody += "\n";
                strBody += "  ";
                strBody += "\"";
                strBody += strAlias;
                strBody += "\"";
                        
                strBody += "\n\n";
                strBody += "(Memo: aliases are case-insensitive)";
                        
                OPAbstract.s_showDialogWarning(this, strBody);
                
                return false;
            }
        }
        
        else // Open (select)
        {
            // alias should be in keystore
            if (! this._keystoreContainsAlias(strAlias))
            {
                String strBody = "Alias not listed in keystore:";
                strBody += "\n";
                strBody += "  ";
                strBody += "\"";
                strBody += strAlias;
                strBody += "\"";
                        
                OPAbstract.s_showDialogWarning(this, strBody);
                
                return false;
            }
        }       
        
        // ----
        return true;
    }
    
    
    
    // overwrite superclass's method
    protected JRootPane createRootPane()
    {
        ActionListener actionListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                _tfdAlias_ = null; // adding this line! to handle PKCS12 keystores
                _cancel_();
            }
        };
    
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    
    protected DTblEntryAbs(
        Component cmpFrameOwner, 

        String strTitleThis,
        String strLabelAlias,
        KeyStore kseLoaded)
    {
        super(
            (Frame) cmpFrameOwner, 
            //strTitleAppli + " - " + strTitleThis + kseLoaded.getType() + " keystore", // title
            true // true ==> modal=true
            ); 
        
        this._kseLoaded_ = kseLoaded;
        
        this._btnOk = new JButton("OK");
        this._btnCancel = new BCancel((ActionListener) this);
        this._lblAlias = new JLabel();
        this._lblAlias.setText(strLabelAlias);
        
        this._tfdAlias_ = new JTextField();

        //
        
        this._pnlContentsAll = new JPanel();
        this._pnlContentsTextfields_ = new JPanel();
        
        this._pnlAction = new JPanel();  
        
        
        // ----
        getContentPane().setLayout(new BorderLayout());
        this._pnlContentsAll.setLayout(new BorderLayout());
        this._pnlAction.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        this._btnOk.addActionListener(this);
    }
    
    // -------
    // private
    
    private JButton _btnOk = null;
    private JButton _btnCancel = null;
    private JLabel _lblAlias = null;
    private JPanel _pnlAction = null;
    private JPanel _pnlContentsAll = null;
    
    /**
        IMPORTANT NOTICE: aliases are case insensitive.
    **/
    private boolean _keystoreContainsAlias(String str)
    {
        String strMethod = "_keystoreContainsAlias(str)";
        
        if (str == null)
            MySystem.s_printOutError(this, strMethod, "nil str");
            
        if (this._kseLoaded_ == null)
            MySystem.s_printOutError(this, strMethod, "nil this._kseLoaded_");
            
        boolean blnAliasOk = false;
        
        try
        {
            blnAliasOk = this._kseLoaded_.containsAlias(str);
        }
        
        catch(KeyStoreException excKeyStore) // keystore has not been initialized (loaded). 
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, " excKeyStore caught");
        }
            
  
        
        // ending
        return blnAliasOk;
    }
}
