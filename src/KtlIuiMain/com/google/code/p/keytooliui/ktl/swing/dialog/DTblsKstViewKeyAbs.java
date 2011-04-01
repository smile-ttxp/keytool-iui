package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.security.KeyStoreException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BCancel;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;

abstract public class DTblsKstViewKeyAbs extends DTblsKstView 
{
    // ---------------------------
    // final static private String
    final static private String _f_s_strLabelAliasEnter = "Enter new alias:";
    final static private String _f_s_strLabelPasswdEnter = "Enter new password:";
    final static private String _f_s_strLabelPasswdConfirm = "Confirm new password:";
    
    // ------
    // public
    
    public char[] getPassword() { return this._chrsPassword; }
    
    public void windowClosing(WindowEvent evt)
    {
        this._tfdAlias = null;
        super.windowClosing(evt);
    }
    
    public void actionPerformed(ActionEvent evtAction) 
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof JButton)
        {
            JButton btn = (JButton) evtAction.getSource();
            
            if (btn == this._btnOk)
            {
                if (! _contentsOk())
                    return;
                
                // ending
                _cancel_();
                return;
            }
            
            else if (btn == super._btnExit_)
            {
                this._tfdAlias = null;
                // !! no return;
                super.actionPerformed(evtAction);
                return;
            }
            
            else if (btn instanceof BCancel)
            {
                 this._tfdAlias = null;
                 _cancel_();
                 return;
            }
        }
        
        super.actionPerformed(evtAction);
    }
    
    public String getAlias()
    {
        if (this._tfdAlias == null)
            return null;
            
        return this._tfdAlias.getText();
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        super._pnlContentsAll_.add(this._pnlContentsTextfields, BorderLayout.SOUTH);        
        this._pnlContentsTextfields.add(this._lblAlias);
        this._pnlContentsTextfields.add(this._tfdAlias);
        
        
        if (this._blnIsPassword)
        {
            this._pnlContentsTextfields.add(this._lblPasswdEnterNew);
            this._pnlContentsTextfields.add(this._pfdEnterNew);
            this._pnlContentsTextfields.add(this._lblPasswdConfirmNew);
            this._pnlContentsTextfields.add(this._pfdConfirmNew);
        }
        
        
        // action
        
        this._pnlAction.add(this._btnOk);
        this._pnlAction.add(this._btnCancel);
        
        getRootPane().setDefaultButton(this._btnOk);
        
        return true;
    }
    
    
    
    // ---------
    // protected
    
    
    protected DTblsKstViewKeyAbs(
        Component cmpFrameOwner, 
      
        String strTitleSuffix,
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strBodyButtonUsage,
        String strTextButtonOK,
        boolean blnSave, // Save v/s Open (create v/s select)
        boolean blnIsPassword
            )
    {
        super(
                cmpFrameOwner, 
         
                strTitleSuffix,
                kseLoaded,
                strPathAbs,
                strBodyButtonUsage
                );
        
        
        this._blnSave = blnSave;
        this._blnIsPassword = blnIsPassword;
        this._btnOk = new JButton("OK");
        this._btnCancel = new BCancel((ActionListener) this);
        this._lblAlias = new JLabel();
        String strLabelAlias = strTextButtonOK; //"Enter new alias";
        this._lblAlias.setText(strLabelAlias);
        
        this._tfdAlias = new JTextField();

        //
        
        this._pnlContentsTextfields = new JPanel();
        
        if (this._blnIsPassword)
        {
            this._lblPasswdEnterNew = new JLabel(DTblsKstViewKeyAbs._f_s_strLabelPasswdEnter);
        
            this._lblPasswdConfirmNew = new JLabel(DTblsKstViewKeyAbs._f_s_strLabelPasswdConfirm);
            this._pfdEnterNew = new JPasswordField(12);
            this._pfdConfirmNew = new JPasswordField(12);
        
            this._pnlContentsTextfields.setLayout(new GridLayout(3, 2, 5, 5));
        }
        
        else
            this._pnlContentsTextfields.setLayout(new GridLayout(1, 2, 5, 5));
        
        
        //this._pnlContentsTextfields.setLayout(new GridLayout(1, 2, 5, 5));
        
        this._pnlAction = new JPanel();  
        
        
        // ----
        this._pnlAction.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        this._btnOk.addActionListener(this);
        
        getContentPane().add(this._pnlAction, BorderLayout.SOUTH);
        //setResizable(false); // TEMPO
    }
    
    // overwrite superclass's method
    protected JRootPane createRootPane()
    {
        ActionListener actionListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                _tfdAlias = null; // adding this line! to handle PKCS12 keystores
                _cancel_();
            }
        };
    
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    
    // -------
    // private
    
    
    // ----
    private JPanel _pnlContentsTextfields = null;
    private JPanel _pnlAction = null;
    private JTextField _tfdAlias = null;
    private JButton _btnOk = null;
    private JButton _btnCancel = null;
    private JLabel _lblAlias = null;
    private boolean _blnSave = false; // v/s open trusted certificate (import v/s export) (typeInAlias v/s selectAlias)
    private boolean _blnIsPassword = false; // false if using PKCS12 keystores
    
    private JLabel _lblPasswdEnterNew = null;
    private JLabel _lblPasswdConfirmNew = null;
    
    private JPasswordField _pfdEnterNew = null;
    private JPasswordField _pfdConfirmNew = null;
    private char[] _chrsPassword = null;
    
    /**
        IMPORTANT NOTICE: aliases are case insensitive.
    **/
    private boolean _keystoreContainsAlias(String str)
    {
        String strMethod = "_keystoreContainsAlias(str)";
        
        if (str == null)
            MySystem.s_printOutError(this, strMethod, "nil str");
            
        if (super._kstOpen_ == null)
            MySystem.s_printOutError(this, strMethod, "nil super._kstOpen_");
            
        boolean blnAliasOk = false;
        
        try
        {
            blnAliasOk = super._kstOpen_.containsAlias(str);
        }
        
        catch(KeyStoreException excKeyStore) // keystore has not been initialized (loaded). 
        {
            excKeyStore.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, " excKeyStore caught");
        }
            
  
        
        // ending
        return blnAliasOk;
    }
    
    private boolean _contentsAliasOk()
    {
        String strMethod = "_contentsAliasOk()";
        
        // check alias
        
        String strAlias = this._tfdAlias.getText();
        
        if (strAlias.length() < 1)
        {
            if (this._blnSave)
                OPAbstract.s_showDialogWarning(this, "Please enter alias");
            else
                OPAbstract.s_showDialogWarning(this, "Please click a valid alias");
            
            return false;
        }
        
        // --
        
        if (this._blnSave)
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
        
        if (this._blnSave) // (create)
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
    
    /**
        Pseudo algo:
        ----
        
        txtAlias != null
        txtPasswdEnter != null
        txtPasswdConfirm != null
        
        
        txtAlias.trim().length > ?
        txtPasswdEnter.trim().length > ?
        txtPasswdConfirm.trim().length > ?
    **/
    private boolean _contentsOk()
    {
        String strMethod = "_contentsOk()";
        
        // check alias
        
        if (! this._contentsAliasOk(
            //true // blnSave, Save v/s Open (create v/s select)
            ))
            return false;
        
        
        
        // check password
        if (this._blnIsPassword)
        {
            String strEnterNew = new String(this._pfdEnterNew.getPassword());
            String strConfirmNew = new String(this._pfdConfirmNew.getPassword());
            
            if (strEnterNew.length() < 1)
            {
                OPAbstract.s_showDialogWarning(this, "Please enter new password");
                
                return false;
            }
            
            if (strConfirmNew.length() < 1)
            {
                OPAbstract.s_showDialogWarning(this, "Please confirm new password");
                
                return false;
            }
            
            if (strEnterNew.compareTo(strConfirmNew) != 0)
            {
                OPAbstract.s_showDialogWarning(this, "Passwords do not match");
                
                return false;
            }
            
            if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedPassword(strEnterNew))
            {
                String strBody = "Password value not allowed:";
                strBody += "\n";
                strBody += "  ";
                strBody += "\"";
                strBody += strEnterNew;
                strBody += "\"";
                        
                strBody += "\n\n";
                strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRulePassword();
                        
                OPAbstract.s_showDialogWarning(this, strBody);
                
                return false;
            }
        
            // --- ok, assigning password
            this._chrsPassword = strEnterNew.toCharArray();
        }
        
        return true;
    }
}  
