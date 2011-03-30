package com.google.code.p.keytooliui.ktl.swing.table;
 
 /**
    known subclasses:
    
    . TableEntryTcrAbs
    . TableEntryKprAbs
    . TableEntryCrtAbs
 **/
 
 import com.google.code.p.keytooliui.shared.swing.table.*;
 
 abstract public class TableEntryAbs extends TableAbs
 {
    // ---------
    // PROTECTED
    
    protected TableEntryAbs(TMEntryAbs tml)
    {
        super(tml);
    }
 }
