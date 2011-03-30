package com.google.code.p.keytooliui.ktl.swing.dialog;

/**

**/


import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.util.*;

abstract public class DTblEntryTcrSaveAbs extends DTblEntryTcrAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strLabelAliasEnter = "Enter new alias:";
    
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
        
        return ((PTblEntryTcrShowAll) super._pnlTable_).load(
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
        
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    
    
    protected DTblEntryTcrSaveAbs(
        Component cmpFrameOwner, 
        String strTitleAppli,
        String strTitleThis,
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
            strTitleAppli,
            strTitleThis,
            DTblEntryTcrSaveAbs._f_s_strLabelAliasEnter,
            kseLoaded
            ); 
            
        super._pnlTable_ = new PTblEntryTcrShowAll();
            
        
        super._pnlContentsTextfields_.setLayout(new GridLayout(1, 2, 5, 5));
    }
    
    /**
        Pseudo algo:
        ----
        
        txtAlias != null
  
  
        txtAlias.trim().length > ?
    **/
    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        // check alias
        
        if (! super._contentsAliasOk_(
            true // blnSave, Save v/s Open (create v/s select)
            ))
            return false;
        
        
        
        return true;
    }
    
    // -------
    // PRIVATE

}