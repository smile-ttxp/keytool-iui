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

public final class DTblEntryKprOpenPkcs12Any extends DTblEntryKprOpenPkcs12Abs 
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThis = 
        "select any keypair entry in ";
    
    // ------
    // PUBLIC
    
 

    
    public DTblEntryKprOpenPkcs12Any(
        Component cmpFrameOwner, 

        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
   
            DTblEntryKprOpenPkcs12Any._f_s_strTitleThis,
            kseLoaded
            ); 
                        
    }
    
    
    
}