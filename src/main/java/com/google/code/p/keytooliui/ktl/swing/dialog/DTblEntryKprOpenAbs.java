package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Kpr" means "keypair"
    
    known subclasses:
 
    . DTblEntryKprOpenKPAbs (key with password)
    . DTblEntryKprOpenPkcs12Abs (key w/o password)
**/

import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;

// ----
import java.security.KeyStore;
import java.security.KeyStoreException;
// ----

import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public abstract class DTblEntryKprOpenAbs extends DTblEntryKprAbs implements
    ListSelectionListener
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strLabelAliasSelected = 
        "Selected alias:";
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
        
        if (super._tfdAlias_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._tfdAlias_");
            return false;
        }
        
        super._tfdAlias_.setEditable(false);
        
        return true;
    }
    
    public boolean load(
        Boolean[] boosIsValidKey,
        String[] strsAlias,
        Boolean[] boosIsEntryKpr,
        Boolean[] boosIsEntryTcr,
        Boolean[] boosIsSelfSignedCert,
        Boolean[] boosIsTrustedCert,
        String[] strsAlgoKeyPubl, // DSA v/s RSA
        String[] strsSizeKeyPubl,
        String[] strsTypeCert,
        String[] strsAlgoSigCert,
        Date[] dtesLastModified
        )
    {
        String strMethod = "load(...)";
        
        if (boosIsValidKey == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil boosIsValidKey");
            return false;
        }
        
        if (strsAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAlias");
            return false;
        }
        
        if (strsAlias.length != boosIsValidKey.length)
        {
            MySystem.s_printOutError(this, strMethod, "strsAlias.length != boosIsValidKey.length");
            return false;
        }
        
        this._boosIsValidKey_ = boosIsValidKey;
        this._strsAlias_ = strsAlias;
        
        if (super._pnlTable_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTable_");
            return false;
        }
        
        return ((PTblEntryKprSel) super._pnlTable_).load(
            boosIsValidKey, strsAlias, 
            boosIsEntryKpr, boosIsEntryTcr, 
            boosIsSelfSignedCert, boosIsTrustedCert, 
            strsAlgoKeyPubl, 
            strsSizeKeyPubl,
            strsTypeCert, 
            strsAlgoSigCert, dtesLastModified);
    }
    
    public void valueChanged(ListSelectionEvent evtListSelection)
    {
        String strMethod = "valueChanged(evtListSelection)";
        
        if (evtListSelection.getValueIsAdjusting())
        {
            return;
        }
        
        ListSelectionModel lsm = (ListSelectionModel) evtListSelection.getSource();
        
        if (lsm.isSelectionEmpty())
        {
            return;
        }
        
        int intSelectionRow = lsm.getMinSelectionIndex();
        
        
        if (intSelectionRow<0 || intSelectionRow>this._boosIsValidKey_.length-1)
            MySystem.s_printOutExit(this, strMethod, "intSelectionRow<0 || intSelectionRow>this._boosIsValidKey_.length-1");
            
        if (this._boosIsValidKey_[intSelectionRow].booleanValue() == false)
            super._tfdAlias_.setText("");
        else
            super._tfdAlias_.setText(this._strsAlias_[intSelectionRow]);
    }
    
    // ---------
    // PROTECTED
    
    protected Boolean[] _boosIsValidKey_ = null;
    protected String[] _strsAlias_ = null;
    
    protected DTblEntryKprOpenAbs(
        Component cmpFrameOwner, 
       
        String strTitleThis,
        KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner,
           
            strTitleThis,
            DTblEntryKprOpenAbs._f_s_strLabelAliasSelected,
            kseLoaded,
            true // blnIsPassword !!!!!!!!!!!!!!
            ); 
            
        super._pnlTable_ = new PTblEntryKprSel((ListSelectionListener) this);
    }

}