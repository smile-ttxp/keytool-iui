package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Kpr" means "KeyPair"
    
    "KP" ==> "key with password"
**/


import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

abstract public class DTblEntryKprOpenKPAbs extends DTblEntryKprOpenAbs 
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    
    private static final String _f_s_strLabelPasswdRespective = "Enter respective password:";
    
    // ------
    // PUBLIC
    

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
    // PROTECTED
    
    
    
    protected DTblEntryKprOpenKPAbs(
        Component cmpFrameOwner, 
    
        String strTitleThis,
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
       
            strTitleThis,
            kseLoaded
            ); 
            
        
            
        this._lblEnterRespective = new JLabel(DTblEntryKprOpenKPAbs._f_s_strLabelPasswdRespective);
        
        this._pfdEnterRespective = new JPasswordField(12);
        
        super._pnlContentsTextfields_.setLayout(new GridLayout(2, 2, 5, 5)); 
    }
    
    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        // check alias
        
        if (! super._contentsAliasOk_(
            false // blnSave, Save v/s Open (create v/s select)
            ))
            return false;
            
        // is alias pointing to valid key?
        boolean blnGotAlias = false;
        
        int intId = 0;
        
        for (; intId<super._strsAlias_.length; intId++) // memo: aliases are case-insensitive
        {
            if (super.getAlias().toLowerCase().compareTo(super._strsAlias_[intId].toLowerCase()) == 0)
            {
                blnGotAlias = true;
                break;
            }
        }
        
        if (! blnGotAlias)
            MySystem.s_printOutExit(this, strMethod, "! blnGotAlias");
            
        if (intId > (super._boosIsValidKey_.length-1))
            MySystem.s_printOutExit(this, strMethod, "intId > (super._boosIsValidKey_.length-1)");
        
        if (super._boosIsValidKey_[intId].booleanValue() != true)
        {
            String strBody = "Alias not pointing to valid key:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += super.getAlias();
            strBody += "\"";
                    
            OPAbstract.s_showDialogWarning(this, strBody);
            return false;
        }
        
        
        if (super._blnIsPassword_) // should be true
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
   
                super._kseLoaded_,
                super.getAlias(),
                strEnterRespective.toCharArray()) == null)
            {
                return false;
            }

            super._chrsPassword_ = strEnterRespective.toCharArray();
        
        }
        
        return true;
    }
    
    // -------
    // PRIVATE
    
    private JLabel _lblEnterRespective = null;
    
    private JPasswordField _pfdEnterRespective = null;
}