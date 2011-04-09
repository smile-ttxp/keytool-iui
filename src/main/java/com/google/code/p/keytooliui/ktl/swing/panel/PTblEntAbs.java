package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import com.google.code.p.keytooliui.ktl.swing.table.TMEntryAbs;
import com.google.code.p.keytooliui.shared.lang.MySystem;

/**
 * "Ent" for Entry
    known subclasses:
    . PTblEntPKAbs: PK for Private Key (keypair)
    . PTblEntTCAbs: TC for Trusted Certificate
    . PTblEntSKAbs: SK for Secret Key (shared key)
**/


public abstract class PTblEntAbs extends JPanel
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    //private static final int _f_s_intWidth = 590;
    private static final int _f_s_intHeight = 240;
    
    // ------
    // PUBLIC
    
    public JTable getTable()
    {
        String strMethod = "getTable()";
        
        if (this._speList_ == null)
            return null; // rather a bug
        
        JViewport vpt = this._speList_.getViewport();
        
        if (vpt == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil vpt");
        }
        
        JTable tbl = (JTable) vpt.getView();
        
        if (tbl == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil tbl");
        }
        
        return tbl;
    }
    
    public ListSelectionModel getTableSelectionModel()
    {        
        return getTable().getSelectionModel();
    }
    
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
    
    // ---------
    // PROTECTED
    
    protected TMEntryAbs _mtm_ = null;
    protected JScrollPane _speList_ = null;
    
    protected PTblEntAbs(
        int intWidth,
        ListSelectionListener lsnListenerParent,
        MouseListener mouListenerParent)
    {
        this._lsnListenerParent = lsnListenerParent;
        this._mouListenerParent = mouListenerParent;

        this._speList_ = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        Dimension dim = new Dimension(intWidth, PTblEntAbs._f_s_intHeight);
        setSize(dim);
        setPreferredSize(dim);
    }
    
    protected void _addListeners_(JTable tbl)
    {
        if (this._mouListenerParent != null)
        {
            tbl.addMouseListener(this._mouListenerParent);
        }
        
        if (this._lsnListenerParent != null)
        {
            tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tbl.setRowSelectionAllowed(true);
            
            ListSelectionModel lsm = tbl.getSelectionModel();
            lsm.addListSelectionListener(this._lsnListenerParent);
        }
    }

    
    // -------
    // private
    
    private ListSelectionListener _lsnListenerParent = null;
    private MouseListener _mouListenerParent = null;
}