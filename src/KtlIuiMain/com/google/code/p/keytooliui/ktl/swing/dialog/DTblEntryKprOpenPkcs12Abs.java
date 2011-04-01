package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Kpr" means "KeyPair"
**/


import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

abstract public class DTblEntryKprOpenPkcs12Abs extends DTblEntryKprOpenAbs 
{    
    // ------
    // PUBLIC
    
    /**
        overwritting superclass's method
        NOT ALLOWED
    **/
    public char[] getPassword()
    {
        /*String strMethod = "getPassword()";
        MySystem.s_printOutExit(this, strMethod, "CODING ERROR");
        return null; // statement never reached!*/
        return new char[0];
    }
    
    /**
        overwritting superclass's method
    **/
    public String getAlias()
    {
        return this._strAliasSelected;
    }
    

    // ---------
    // protected
    
    protected DTblEntryKprOpenPkcs12Abs(
        Component cmpFrameOwner, 
    
        String strTitleThis,
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
         
            strTitleThis,
            //DTblEntryKprOpenPkcs12Abs._f_s_strTitleThis,
            kseLoaded
            );
        
        super._pnlContentsTextfields_.setLayout(new GridLayout(1, 2, 5, 5)); 
    }
    
    // ---------
    // PROTECTED
    
    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        this._strAliasSelected = null;
        
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
        
        this._strAliasSelected = super.getAlias();
        
        return true;
    }
    
    // -------
    // PRIVATE
   
    
    private String _strAliasSelected = null;
}