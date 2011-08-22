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
 
 
package com.google.code.p.keytooliui.ktl.swing.table;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;

import javax.swing.*;
import javax.swing.table.*;

public final class TMEntSKSelAll extends TMEntAbs
{
    // -------------------
    // PUBLIC STATIC FINAL
    
    // see array of integers right below
    // tempo, should be rewritten, in order to handle subclasses of SK
    public static final int f_s_intColumnIdIsCandidate = 0;
    public static final int f_s_intColumnIdIsSKEntry = 2;        
    
    // preferred columns width 
    public static final int[] f_s_intsColW = 
    { 
        30,  // booIsCandidate (for selection)
        TMEntAbs._INT_W_ALIAS_, // strAlias
        30,  // // SK versus [XXX] tempo
        TMEntAbs._INT_W_MODIFIEDDATE_  // dteLastModified
    }; // sum: ?
    
    
    public static final String[] f_s_strsColumnNames =
    { 
        "Candidate ?",
        TMEntAbs._STR_ALIAS_,
        "Entry", // SK versus [XXX] tempo
        TMEntAbs._STR_MODIFIEDDATE_
    };
    

    // ------
    // PUBLIC

    public TMEntSKSelAll()
    {
        this(new Object[0][0]);
    }

    public TMEntSKSelAll(Object[][] objssData)
    {
        super(objssData);
    }
    
        
    public int getColumnCount()
    {
        return TMEntSKSelAll.f_s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntSKSelAll.f_s_strsColumnNames[col];
    }
}

