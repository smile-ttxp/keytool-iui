package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Tcr" means "Trusted certificate" (entry)
    
    known subclasses:
    . DTblEntryTcrSaveAbs
    . DTblEntryTcrOpenAbs
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

public abstract class DTblEntryTcrAbs extends DTblEntryAbs
{
    // ---------
    // PROTECTED
   
    protected DTblEntryTcrAbs(
        Component cmpFrameOwner, 
  
        String strTitleThis,
        String strLabelAlias,
        KeyStore kseLoaded
        )
    {
        super(cmpFrameOwner, strTitleThis, strLabelAlias, kseLoaded); 
    }
 
}