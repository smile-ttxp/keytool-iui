 package com.google.code.p.keytooliui.ktl.swing.table;
 
 /**

not a selectable table
    
 **/
 
 
 final public class TableEntryTcrShowAll extends TableEntryTcrAbs
 {
    // ------
    // PUBLIC
    
    public TableEntryTcrShowAll(TMEntryTcrAbs tml)
    {
        super(tml);
        setRowSelectionAllowed(false);
        setColumnSelectionAllowed(false);
    }
 }