package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Kpr" means "Keypair"
    
    known subclasses:
    . DTblEntryKprSaveDsa
    . DTblEntryKprSaveRsa
**/


import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;

import java.awt.*;
import java.util.*;

abstract public class DTblEntryKprSaveAbs extends DTblEntryKprAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strLabelAliasEnter = "Enter new alias:";
    final static private String _f_s_strLabelPasswdEnter = "Enter new password:";
    final static private String _f_s_strLabelPasswdConfirm = "Confirm new password:";
    
    // ------
    // PUBLIC

    public boolean load(
        String[] strsAlias,
        Boolean[] boosIsEntryKpr,
        Boolean[] boosIsEntryTcr,
        Boolean[] boosIsSelfSignedCert,
        Boolean[] boosIsTrustedCert,
        String[] strsAlgoKeyPubl,
        String[] strsSizeKeyPubl,
        String[] strsTypeCert,
        String[] strsAlgoSigCert,
        Date[] dtesLastModified
        )
    {
        String strMethod = "load(...)";
        
        if (super._pnlTable_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTable_");
            return false;
        }
        
        return ((PTblEntryKprShowAll) super._pnlTable_).load(
            strsAlias, 
            boosIsEntryKpr, boosIsEntryTcr,
            boosIsSelfSignedCert, boosIsTrustedCert, 
            strsAlgoKeyPubl, 
            strsSizeKeyPubl,
            strsTypeCert, 
            strsAlgoSigCert, dtesLastModified);
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // ----
        
        
        
        if (super._blnIsPassword_)
        {
            super._pnlContentsTextfields_.add(this._lblPasswdEnterNew);
            super._pnlContentsTextfields_.add(this._pfdEnterNew);
            super._pnlContentsTextfields_.add(this._lblPasswdConfirmNew);
            super._pnlContentsTextfields_.add(this._pfdConfirmNew);
        }
        
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    
    
    protected DTblEntryKprSaveAbs(
        Component cmpFrameOwner, 
  
        String strTitleThis,
        java.security.KeyStore kseLoaded,
        boolean blnIsPassword // true for JKS-JCEKS, false for PKCS12
        )
    {
        super(
            cmpFrameOwner, 
       
            strTitleThis + " in " + kseLoaded.getType() + "keystore",
            DTblEntryKprSaveAbs._f_s_strLabelAliasEnter,
            kseLoaded,
            blnIsPassword
            ); 
            
        super._pnlTable_ = new PTblEntryKprShowAll();
            
        if (super._blnIsPassword_)
        {
            this._lblPasswdEnterNew = new JLabel(DTblEntryKprSaveAbs._f_s_strLabelPasswdEnter);
        
            this._lblPasswdConfirmNew = new JLabel(DTblEntryKprSaveAbs._f_s_strLabelPasswdConfirm);
            this._pfdEnterNew = new JPasswordField(12);
            this._pfdConfirmNew = new JPasswordField(12);
        
            super._pnlContentsTextfields_.setLayout(new GridLayout(3, 2, 5, 5));
        }
        
        else
            super._pnlContentsTextfields_.setLayout(new GridLayout(1, 2, 5, 5));
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
    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        // check alias
        
        if (! super._contentsAliasOk_(
            true // blnSave, Save v/s Open (create v/s select)
            ))
            return false;
        
        
        
        // check password
        if (super._blnIsPassword_)
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
            super._chrsPassword_ = strEnterNew.toCharArray();
        }
        
        return true;
    }
    
    // -------
    // PRIVATE
    
    private JLabel _lblPasswdEnterNew = null;
    private JLabel _lblPasswdConfirmNew = null;
    
    private JPasswordField _pfdEnterNew = null;
    private JPasswordField _pfdConfirmNew = null;
}