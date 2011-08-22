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
import com.google.code.p.keytooliui.ktl.lang.bool.BOEntrySK;
import com.google.code.p.keytooliui.ktl.swing.table.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;
import com.google.code.p.keytooliui.shared.swing.table.*;


import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

public final class PTblEntSKShowAll extends PTblEntSKAbs 
{
    // --------------
    // private static
    
    // not working coz not taking into account length of columns' titles
    private static int _s_getWidth()
    {
        int intVal = 0;
        
        for (int i=0; i<TMEntSKShowAll.f_s_intsColW.length; i++)
            intVal += TMEntSKShowAll.f_s_intsColW[i];
        
        // dummy value
        intVal += 100;
        
        return intVal;
    }
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    //private static final int _f_s_intDeltaW = 0; // using default width assigned in superclass
    
    // ------
    // PUBLIC

    public boolean load(
        String[] strsAlias,
        Date[] dtesLastModified
        )
    {
        String strMethod = "load(...)";
        
        
        if (strsAlias==null || dtesLastModified==null)
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
        

        if (intNbRow!=dtesLastModified.length)
        {
            MySystem.s_printOutError(this, strMethod, "wrong args length");
            return false;
        }
        
        // -----
        
        Object[][] objssData = new Object[intNbRow][];
        
        for (int i=0; i<intNbRow; i++)
        {   
            // trick, tempo, in order to show icon of type "key""
            BOEntrySK bocIsKeyEntry = new BOEntrySK();
            
            
            Object[] objsLine = {
                strsAlias[i],
                bocIsKeyEntry,
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
    
    public PTblEntSKShowAll(
        ListSelectionListener lsnListenerParent, MouseListener mouListenerParent
        )
    {
        super(PTblEntSKShowAll._s_getWidth(), lsnListenerParent, mouListenerParent); 
        super._mtm_ = new TMEntSKShowAll(); 
    }
    
    public PTblEntSKShowAll()
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

        for (int i=0; i<TMEntSKShowAll.f_s_intsColW.length; i++)
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
            
            tcn.setPreferredWidth(Math.max(intHeaderWidth, TMEntSKShowAll.f_s_intsColW[i]));
            tcn.setMinWidth(Math.max(intHeaderWidth, TMEntSKShowAll.f_s_intsColW[i]));
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
        
        super._mtm_ = new TMEntSKShowAll(objssData);
            
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
        tcnCur = tcm.getColumn(TMEntSKShowAll.f_s_intColumnIdIsSKEntry);
        tcnCur.setCellRenderer(tcr);
        
    
        // ending
        return true;
    }
}

