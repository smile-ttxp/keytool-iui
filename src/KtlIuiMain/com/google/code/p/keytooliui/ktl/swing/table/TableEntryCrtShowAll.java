package com.google.code.p.keytooliui.ktl.swing.table;
 
 /**

not a selectable table
    
 **/
 
 
 final public class TableEntryCrtShowAll extends TableEntryCrtAbs
 {
    // ------
    // PUBLIC
    
    public TableEntryCrtShowAll(TMEntryCrtAbs tml)
    {
        super(tml);
        setRowSelectionAllowed(false);
        setColumnSelectionAllowed(false);
    }
 }
