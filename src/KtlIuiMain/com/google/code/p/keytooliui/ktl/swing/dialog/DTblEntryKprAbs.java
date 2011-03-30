package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Kpr" means "keypair"
    
    known subclasses:
    . DTblEntryKprSaveAbs
    . DTblEntryKprOpenAbs
**/

import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;
import javax.swing.border.*;

// ----
import java.security.KeyStore;
import java.security.KeyStoreException;
// ----

import java.awt.*;
import java.awt.event.*;

abstract public class DTblEntryKprAbs extends DTblEntryAbs 
{
    // -------------
    // PUBLIC ACCESS
      
    public char[] getPassword() { return this._chrsPassword_; }
    
    // ---------
    // PROTECTED
     
    protected char[] _chrsPassword_ = null;
    protected boolean _blnIsPassword_;
    
    protected DTblEntryKprAbs(
        Component cmpFrameOwner, 
        String strTitleAppli,
        String strTitleThis,
        String strLabelAlias,
        KeyStore kseLoaded,
        boolean blnIsPassword
        )
    {
        super(cmpFrameOwner, strTitleAppli, strTitleThis, strLabelAlias, kseLoaded); 
            
        this._blnIsPassword_ = blnIsPassword;
    }
   
}