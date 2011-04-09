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

public class DTblsKstSelSKOpen extends DTblsKstSelSKAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
   
    private static final String _f_s_strTitleThisSuffix = "keystore";
    private static final String _STR_BODYBUTTONUSAGE = "Usage:\n  Mouse-click  valid candidate row to select respective entry's alias,\n  next enter respective password,\n  then click \"OK\" button.";
    private static final String _STR_TEXTLABELALIAS = "Selected secret key entry's alias:";
    
    private static final String _f_s_strLabelPasswdRespective = "Enter respective password:";
    
    // ------
// public
    
    public char[] getPassword() { return this._chrsPassword_; }
    
    public DTblsKstSelSKOpen(
        Component cmpFrameOwner, 
 
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strTitleSuffix
            )
    {
        super(
                cmpFrameOwner, 
             
                strTitleSuffix,
                kseLoaded,
                strPathAbs,
                DTblsKstSelSKOpen._STR_BODYBUTTONUSAGE,
                DTblsKstSelSKOpen._STR_TEXTLABELALIAS,
                false // blnSave (v/s Open) !!!!!!!!!!!!! always false !!!!!!!!!
                );
        
        this._lblEnterRespective = new JLabel(DTblsKstSelSKOpen._f_s_strLabelPasswdRespective);
        
        this._pfdEnterRespective = new JPasswordField(12);
        
        super._pnlContentsTextfields_.setLayout(new GridLayout(2, 2, 5, 5)); 
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // ----
        
        super._pnlContentsTextfields_.add(this._lblEnterRespective);
        super._pnlContentsTextfields_.add(this._pfdEnterRespective);

        return true;
    }
    
    // ---------
    // protected
    
    protected char[] _chrsPassword_ = null;
    
    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        // check alias
        
        if (! super._contentsAliasOk_(
            //false // blnSave, Save v/s Open (create v/s select)
            ))
            return false;
            
        // is alias pointing to valid key?
        boolean blnGotAlias = false;
        
        int intId = 0;
        
        for (; intId<super._strsAliasSK_.length; intId++) // memo: aliases are case-insensitive
        {
            if (super.getAlias().toLowerCase().compareTo(super._strsAliasSK_[intId].toLowerCase()) == 0)
            {
                blnGotAlias = true;
                break;
            }
        }
        
        if (! blnGotAlias)
            MySystem.s_printOutExit(this, strMethod, "! blnGotAlias");
            
        if (intId > (super._strsAliasSK_.length-1))
            MySystem.s_printOutExit(this, strMethod, "intId > (super._strsAliasSK_.length-1)");
        
        /*if (super._boosIsTCEntryPKTC_[intId].booleanValue() != false)
        {
            String strBody = "Alias not pointing to valid key:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += super.getAlias();
            strBody += "\"";
                    
            OPAbstract.s_showDialogWarning(this, strBody);
            return false;
        }*/
        
        
        if (true /*super._blnIsPassword_*/) // should be true
        {
            // check password
            
            String strEnterRespective = new String(this._pfdEnterRespective.getPassword());
            
            if (strEnterRespective.length() < 1)
            {
                OPAbstract.s_showDialogWarning(this, "Please enter respective password");
                
                return false;
            }
            
            
            // is password corrrect?
   
            if (com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstAbs.s_getKey(
                this, 
              
                super._kstOpen_,
                super.getAlias(),
                strEnterRespective.toCharArray()) == null)
            {
                return false;
            }

            this._chrsPassword_ = strEnterRespective.toCharArray();
        
        }
        
        return true;
    }
    
    // -------
    // private
    
    private JLabel _lblEnterRespective = null;
    
    private JPasswordField _pfdEnterRespective = null;
}  
      

