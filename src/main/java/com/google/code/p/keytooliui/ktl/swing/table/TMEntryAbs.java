/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.table;

/**
    known subclasses:
    . TMEntryTcrAbs
    . TMEntryKprAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.table.*;

import javax.swing.table.*;


abstract public class TMEntryAbs extends TMAbs
{   
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public int getColumnCount();
    abstract public String getColumnName(int col);
    
    // ------
    // PUBLIC
    

    
    public Object getValueAt(int row, int col)
    {
        String strMethod = "getValueAt(row, col)";
        
        if (this._objssData_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._objssData_");

        return this._objssData_[row][col];
    }

    /*
    * JTable uses this method to determine the default renderer/
    * editor for each cell.  If we didn't implement this method,
    * then the last column would contain text ("true"/"false"),
    * rather than a check box.
    */
    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }
        
    public void setData(Object[][] objssData)
    {
        String strMethod = "setData(objssData)";
        
        if (objssData == null)
            MySystem.s_printOutExit(this, strMethod, "nil objssData");
        
        this._objssData_ = objssData;
        
        // fireEvent??????
    }
    
    public boolean init() { return true; }
    public void destroy() {}

    
    public int getRowCount()
    {
        String strMethod = "getRowCount()";
        
        if (this._objssData_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._objssData_");
        
        return this._objssData_.length;
    }
    
    // ---------
    // PROTECTED
    
    protected Object[][] _objssData_ = null;

    protected TMEntryAbs(Object[][] objssData)
    {
        super();
        
        String strMethod = "TMEntryAbs(objssData)";
        
        if (objssData == null)
            MySystem.s_printOutExit(this, strMethod, "nil objssData");
        
        this._objssData_ = objssData;
    }
}

