package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Tcr" means "Trusted certificate" (entry)
    
    known subclasses:
 
    . DTblEntryTcrOpenDMAbs (DM for Dummy, tempo) 
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

abstract public class DTblEntryTcrOpenAbs extends DTblEntryTcrAbs implements
    ListSelectionListener
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strLabelAliasSelected = 
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
        Boolean[] boosIsValidTcr,
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
        
        if (boosIsValidTcr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil boosIsValidTcr");
            return false;
        }
        
        if (strsAlias == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strsAlias");
            return false;
        }
        
        if (strsAlias.length != boosIsValidTcr.length)
        {
            MySystem.s_printOutError(this, strMethod, "strsAlias.length != boosIsValidTcr.length");
            return false;
        }
        
        this._boosIsValidTcr_ = boosIsValidTcr;
        this._strsAlias_ = strsAlias;
        
        
        
        
        
        if (super._pnlTable_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pnlTable_");
            return false;
        }
        
        return ((PTblEntryTcrSel) super._pnlTable_).load(
            boosIsValidTcr, strsAlias, 
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
        
        
        if (intSelectionRow<0 || intSelectionRow>this._boosIsValidTcr_.length-1)
            MySystem.s_printOutExit(this, strMethod, "intSelectionRow<0 || intSelectionRow>this._boosIsValidTcr_.length-1");
            
        if (this._boosIsValidTcr_[intSelectionRow].booleanValue() == false)
            super._tfdAlias_.setText("");
        else
            super._tfdAlias_.setText(this._strsAlias_[intSelectionRow]);
    }
    
    // ---------
    // PROTECTED
    
    protected Boolean[] _boosIsValidTcr_ = null;
    protected String[] _strsAlias_ = null;
    
    protected DTblEntryTcrOpenAbs(
        Component cmpFrameOwner, 
        String strTitleAppli,
        String strTitleThis,
        KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner,
            strTitleAppli,
            strTitleThis,
            DTblEntryTcrOpenAbs._f_s_strLabelAliasSelected,
            kseLoaded
            ); 
            
        super._pnlTable_ = new PTblEntryTcrSel((ListSelectionListener) this);
    }
    
    // -------
    // PRIVATE
    
    
}