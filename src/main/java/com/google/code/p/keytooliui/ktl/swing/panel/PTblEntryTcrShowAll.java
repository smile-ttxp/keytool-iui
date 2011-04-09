package com.google.code.p.keytooliui.ktl.swing.panel;

import com.google.code.p.keytooliui.ktl.swing.table.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;
import com.google.code.p.keytooliui.shared.swing.table.*;


import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

public final class PTblEntryTcrShowAll extends PTblEntryTcrAbs 
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final int _f_s_intDeltaW = 0; // using default width assigned in superclass
    
    // ------
    // PUBLIC

    public boolean load(
        String[] strsAlias,
        Boolean[] boosIsEntryKpr,
        Boolean[] boosIsEntryTcr,
        Boolean[] boosIsSelfSignedCert,
        Boolean[] boosIsTrustedCert,
        String[] strsAlgoKeyPubl, // DSA, RSA, other
        String[] strsSizeKeyPubl,
        String[] strsTypeCert,
        String[] strsAlgoSigCert,
        Date[] dtesLastModified
        )
    {
        String strMethod = "load(...)";
        
        
        if (strsAlias==null || 
            boosIsEntryKpr==null || boosIsEntryTcr==null || 
            boosIsSelfSignedCert==null || boosIsTrustedCert==null || 
            strsAlgoKeyPubl==null ||
            strsSizeKeyPubl==null || 
            strsTypeCert==null || strsAlgoSigCert==null || dtesLastModified==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }
        
        int intNbRow = strsAlias.length;
        
        if (intNbRow < 0)
        {
            MySystem.s_printOutError(this, strMethod, "wrong value, intNbRow=" + intNbRow);
            return false;
        }
        

        if (
            intNbRow!=boosIsEntryKpr.length || 
            intNbRow!=boosIsEntryTcr.length || 
            intNbRow!=boosIsSelfSignedCert.length || 
            intNbRow!=boosIsTrustedCert.length || 
            intNbRow!=strsAlgoKeyPubl.length ||
            intNbRow!=strsSizeKeyPubl.length ||
            intNbRow!=strsTypeCert.length ||
            intNbRow!=strsAlgoSigCert.length ||
            intNbRow!=dtesLastModified.length
            )
        {
            MySystem.s_printOutError(this, strMethod, "wrong args length");
            return false;
        }
        
        // -----
        
        Object[][] objssData = new Object[intNbRow][];
        
        for (int i=0; i<intNbRow; i++)
        {     
            BOCheckedDotAbs bocIsKeyEntry = new BOCheckedDotBlack(boosIsEntryKpr[i].booleanValue());
            BOCheckedDotAbs bocIsCertEntry = new BOCheckedDotBlack(boosIsEntryTcr[i].booleanValue());
            
            BOCheckedDotAbs bocIsSelfSignedCert = new BOCheckedDotBlack(boosIsSelfSignedCert[i].booleanValue());
            BOCheckedDotAbs bocIsTrustedCert = new BOCheckedDotBlack(boosIsTrustedCert[i].booleanValue());
            
            if (! bocIsKeyEntry.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            if (! bocIsCertEntry.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            if (! bocIsSelfSignedCert.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            if (! bocIsTrustedCert.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            
            
            Object[] objsLine = {
                strsAlias[i],
                bocIsKeyEntry,
                bocIsCertEntry,
                bocIsSelfSignedCert,
                bocIsTrustedCert,
                strsAlgoKeyPubl[i],
                strsSizeKeyPubl[i],
                strsTypeCert[i],
                strsAlgoSigCert[i],
                dtesLastModified[i]
                };
                
             objssData[i] = objsLine;   
        }
        
        
        if (! _load(objssData))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        return true;        
    }
    
    public PTblEntryTcrShowAll(
        ListSelectionListener lsnListenerParent
        )
    {
        this();   
        
        this._lsnListenerParent = lsnListenerParent;
    }
    
    public PTblEntryTcrShowAll()
    {
        super(PTblEntryTcrShowAll._f_s_intDeltaW); 
        super._mtm_ = new TMEntryTcrShowAll();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._mtm_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._mtm_");
            return false;
        }
        
        JTable tbl = new JTable(super._mtm_);
        tbl.setRowSelectionAllowed(false);
        tbl.setColumnSelectionAllowed(false);
        this.setBackground(tbl.getBackground());
        
        if (! _initColumnSize(tbl))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
        }
        
        if (this._lsnListenerParent != null)
        {
            tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tbl.setRowSelectionAllowed(true);
            
            ListSelectionModel lsm = tbl.getSelectionModel();
            lsm.addListSelectionListener(this._lsnListenerParent);
        }
        
        if (super._speList_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._speList_");
            return false;
        }
       
        super._speList_.setViewportView(tbl);
        
        // ending
        return true;
    }
    
    
    
    // -------
    // PRIVATE
    
    private ListSelectionListener _lsnListenerParent = null;
    
    private boolean _initColumnSize(JTable tbl)
    {
        String strMethod = "_initColumnSize(tbl)";
        
        if (tbl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil tbl");
            return false;
        }

        for (int i=0; i<TMEntryTcrShowAll.f_s_intsColW.length; i++)
        {
            TableColumnModel tcm = tbl.getColumnModel();
            
            if (tcm == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil tcm");
                return false;
            }
            
            TableColumn tcn = tcm.getColumn(i);
            
            if (tcn == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil tcn");
                return false;
            }
            
            TableCellRenderer tcrHeader = tcn.getHeaderRenderer();
     
            // ADDITION IN ORDER TO HANDLE CHANGES BETWEEN JDK1.2.2 & JDK1.3final       
            if (tcrHeader == null)
            {
                JTableHeader thr = tbl.getTableHeader();
                
                if (thr == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil thr");
                    return false;
                }
                
                tcrHeader = thr.getDefaultRenderer();
                
                if (tcrHeader == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil tcrHeader");
                    return false;
                }
            }
          
            Object objHeaderValue = tcn.getHeaderValue();
            
            if (objHeaderValue == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil objHeaderValue");
                return false;
            }
            
            Component cmp = tcrHeader.getTableCellRendererComponent(
                tbl, objHeaderValue, 
                false, false, 0, i);
            
            // ----
            
            if (cmp == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil cmp");
                return false;
            }
            
            int intHeaderWidth = cmp.getPreferredSize().width;
            
            tcn.setPreferredWidth(Math.max(intHeaderWidth, TMEntryTcrShowAll.f_s_intsColW[i]));
            tcn.setMinWidth(Math.max(intHeaderWidth, TMEntryTcrShowAll.f_s_intsColW[i]));
        }

        // ending
        return true;
    }
    
    
    
    
    private boolean _load(Object[][] objssData)
    {
        String strMethod = "_load(objssData)";
        
        if (objssData == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil objssData");
            return false;
        }
        
        super._mtm_ = new TMEntryTcrShowAll(objssData);
            
        if (! super._mtm_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
           
        JTable tbl = new JTable(super._mtm_);
        tbl.setRowSelectionAllowed(false);
        tbl.setColumnSelectionAllowed(false);
        this.setBackground(tbl.getBackground());
            
        if (! _initColumnSize(tbl))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
        }
        
        if (this._lsnListenerParent != null)
        {
            tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tbl.setRowSelectionAllowed(true);
            
            ListSelectionModel lsm = tbl.getSelectionModel();
            lsm.addListSelectionListener(this._lsnListenerParent);
        }
        
        
        if (! _setRendererStyled(tbl))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ---------
        
        if (super._speList_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._speList_");
            return false;
        }
            
        super._speList_.setViewportView(tbl);
    
        // ending
        return true;
    }
    
    private boolean _setRendererStyled(JTable tbl)
    {
        String strMethod = "_setRendererStyled(tbl)";
        
        if (tbl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil tbl");
            return false;
        }
        
        // 
        TableColumnModel tcm = tbl.getColumnModel();
        
        // --
        TCRAbs tcr = null;
        TableColumn tcnCur = null;
        
        // --
        tcr = new TCRCheckedDot();
        tcnCur = tcm.getColumn(TMEntryTcrShowAll.f_s_intColumnIdIsKeyEntry);
        tcnCur.setCellRenderer(tcr);
        
        // --
        tcr = new TCRCheckedDot();
        tcnCur = tcm.getColumn(TMEntryTcrShowAll.f_s_intColumnIdIsCertEntry);
        tcnCur.setCellRenderer(tcr);
        
        // --
        tcr = new TCRCheckedDot();
        tcnCur = tcm.getColumn(TMEntryTcrShowAll.f_s_intColumnIdIsSelfSignedCert);
        tcnCur.setCellRenderer(tcr);
        
        // --
        tcr = new TCRCheckedDot();
        tcnCur = tcm.getColumn(TMEntryTcrShowAll.f_s_intColumnIdIsTrustedCert);
        tcnCur.setCellRenderer(tcr);
    
        // ending
        return true;
    }
}