/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 /**
     
 **/
package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.event.MouseListener;
import com.google.code.p.keytooliui.ktl.lang.bool.BOEntryPKTC;
import com.google.code.p.keytooliui.ktl.swing.table.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;
import com.google.code.p.keytooliui.shared.swing.table.*;


import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

public final class PTblEntPKTCShowAll extends PTblEntPKTCAbs 
{
    // --------------
    // private static
    
    // not working coz not taking into account length of columns' titles
    private static int _s_getWidth()
    {
        int intVal = 0;
        
        for (int i=0; i<TMEntPKTCShowAll.f_s_intsColW.length; i++)
            intVal += TMEntPKTCShowAll.f_s_intsColW[i];
        
        // dummy value
        intVal += 100;
        
        return intVal;
    }
    
    // ------
    // PUBLIC

    public boolean load(
        String[] strsAlias,
        Boolean[] boosIsEntryTC, // versus PK
        Boolean[] boosIsValidDate,
        Boolean[] boosIsSelfSignedCert,
        Boolean[] boosIsTrustedCACert,
        //String[] strsAlgoKeyPubl, // DSA, RSA, other
        String[] strsSizeKeyPubl,
        String[] strsTypeCert,
        String[] strsAlgoSigCert,
        Date[] dtesLastModified
        )
    {
        String strMethod = "load(...)";
        
        
        if (strsAlias==null || 
            boosIsEntryTC==null || boosIsValidDate==null || 
            boosIsSelfSignedCert==null || boosIsTrustedCACert==null || 
            //strsAlgoKeyPubl==null ||
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
            intNbRow!=boosIsEntryTC.length || 
            intNbRow!=boosIsValidDate.length || 
            intNbRow!=boosIsSelfSignedCert.length || 
            intNbRow!=boosIsTrustedCACert.length || 
            //intNbRow!=strsAlgoKeyPubl.length ||
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
            //BOCheckedDotAbs bocIsTCEntry = new BOCheckedDotBlack(boosIsEntryTC[i].booleanValue());
            BOEntryPKTC bocIsKeyEntry = new BOEntryPKTC(! boosIsEntryTC[i].booleanValue());
            BOCheckedWarnAbs bocIsValidDate = new BOCheckedWarnBlack(boosIsValidDate[i].booleanValue());
            
            BOCheckedDotAbs bocIsSelfSignedCert = new BOCheckedDotBlack(boosIsSelfSignedCert[i].booleanValue());
            BOCheckedDotAbs bocIsTrustedCert = new BOCheckedDotBlack(boosIsTrustedCACert[i].booleanValue());
            
            if (! bocIsKeyEntry.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            if (! bocIsValidDate.init())
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
                bocIsValidDate,
                bocIsSelfSignedCert,
                bocIsTrustedCert,
                //strsAlgoKeyPubl[i],
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
    
    public PTblEntPKTCShowAll(
        ListSelectionListener lsnListenerParent,
        MouseListener mouListenerParent
        )
    {
        super(PTblEntPKTCShowAll._s_getWidth(), lsnListenerParent, mouListenerParent); 
        super._mtm_ = new TMEntPKTCShowAll();
    }
    
    public PTblEntPKTCShowAll()
    {
        this((ListSelectionListener) null, (MouseListener) null); 
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
        
        super._addListeners_(tbl);
        
        
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
    
    
    
    private boolean _initColumnSize(JTable tbl)
    {
        String strMethod = "_initColumnSize(tbl)";
        
        if (tbl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil tbl");
            return false;
        }

        for (int i=0; i<TMEntPKTCShowAll.f_s_intsColW.length; i++)
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
            
            tcn.setPreferredWidth(Math.max(intHeaderWidth, TMEntPKTCShowAll.f_s_intsColW[i]));
            tcn.setMinWidth(Math.max(intHeaderWidth, TMEntPKTCShowAll.f_s_intsColW[i]));
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
        
        super._mtm_ = new TMEntPKTCShowAll(objssData);
            
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
        
        super._addListeners_(tbl);
        
        
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
        tcnCur = tcm.getColumn(TMEntPKTCShowAll.f_s_intColumnIdIsTCEntry);
        tcnCur.setCellRenderer(tcr);
        
        
        // --
        tcr = new TCRCheckedWarn();
        tcnCur = tcm.getColumn(TMEntPKTCShowAll.f_s_intColumnIdIsValidDate);
        tcnCur.setCellRenderer(tcr);
        
        // --
        tcr = new TCRCheckedDot();
        tcnCur = tcm.getColumn(TMEntPKTCShowAll.f_s_intColumnIdIsSelfSignedCert);
        tcnCur.setCellRenderer(tcr);
        
        // --
        tcr = new TCRCheckedDot();
        tcnCur = tcm.getColumn(TMEntPKTCShowAll.f_s_intColumnIdIsTrustedCACert);
        tcnCur.setCellRenderer(tcr);
    
        // ending
        return true;
    }
}
