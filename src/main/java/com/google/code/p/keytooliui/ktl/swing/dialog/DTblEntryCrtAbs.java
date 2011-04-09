package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Crt" means "Certificate" (entry)
    
    known subclasses:
    . DTblEntryCrtOpenAbs
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

public abstract class DTblEntryCrtAbs extends DTblEntryAbs
{
    // ---------
    // PROTECTED
   
    protected DTblEntryCrtAbs(
        Component cmpFrameOwner, 

        String strTitleThis,
        String strLabelAlias,
        KeyStore kseLoaded
        )
    {
        super(cmpFrameOwner, strTitleThis, strLabelAlias, kseLoaded); 
    }
 
}
