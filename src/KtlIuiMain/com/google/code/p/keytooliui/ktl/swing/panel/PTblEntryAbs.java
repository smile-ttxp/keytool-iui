package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    known subclasses:
    . PTblEntryTcrAbs
    . PTblEntryKprAbs

**/

import com.google.code.p.keytooliui.ktl.swing.table.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


import java.awt.*;


abstract public class PTblEntryAbs extends JPanel
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intWidth = 590;
    final static private int _f_s_intHeight = 300;
    
    // ------
    // PUBLIC
    
    public boolean updateSelectionRowId(int intRowId)
    {
        String strMethod = "updateSelectionRowId(intRowId)"; 
        
        
        // ----
        
        if (intRowId < 0)
        {
            MySystem.s_printOutError(this, strMethod, "intRowId < 0");
            return false;
        }
        
        if (this._mtm_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mtm_");
            return false;
        }
        
        
        if (this._mtm_.getRowCount() < 1)
        {
            MySystem.s_printOutError(this, strMethod, "this._mtm_.getRowCount() < 1");
            return false;
        }
        
        if (intRowId > this._mtm_.getRowCount()-1)
        {
            MySystem.s_printOutError(this, strMethod, "intRowId > this._mtm_.getRowCount()-1");
            return false;
        }
        
        
        // ---------
        
        JViewport vpt = this._speList_.getViewport();
        
        if (vpt == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil vpt");
            return false;
        }
        
        JTable tbl = (JTable) vpt.getView();
        
        if (tbl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil tbl");
            return false;
        }
        
    
        ListSelectionModel lsm = tbl.getSelectionModel();
            
        if (! lsm.isSelectionEmpty())
            lsm.clearSelection();    
        
        lsm.setSelectionInterval(intRowId, intRowId);
        
        // --------
        
        return true;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._mtm_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mtm_");
            return false;
        }
        
        if (! this._mtm_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._speList_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._speList_");
            return false;
        }
        
        setLayout(new BorderLayout());
        add(this._speList_, BorderLayout.CENTER);
    
        // ending
        return true;
    }
    
    
    
    
    /**
        if any error, exiting
    **/
    public int getRowCount()
    {
        String strMethod = "getRowCount()";
        
        if (this._mtm_ == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil this._mtm_");
        }
        
        return this._mtm_.getRowCount();
    }
    
    public void destroy()
    {
    }
    
    // ---------
    // PROTECTED
    
    protected TMEntryAbs _mtm_ = null;
    protected JScrollPane _speList_ = null;
    
    protected PTblEntryAbs(int intDeltaW)
    {
        super();   

        this._speList_ = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        Dimension dim = new Dimension(PTblEntryAbs._f_s_intWidth + intDeltaW, PTblEntryAbs._f_s_intHeight);
        setSize(dim);
        setPreferredSize(dim);
    }
    
    

    // -------
    // PRIVATE    
}
