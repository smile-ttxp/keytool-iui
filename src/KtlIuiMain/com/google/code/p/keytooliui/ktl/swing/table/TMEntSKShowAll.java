/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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

final public class TMEntSKShowAll extends TMEntAbs
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    // see array of integers right below
    // tempo, should be rewritten, in order to handle subclasses of SK
    final static public int f_s_intColumnIdIsSKEntry = 1;        
    
    // preferred columns width 
    final static public int[] f_s_intsColW = 
    { 
        TMEntAbs._INT_W_ALIAS_, // strAlias
        30,  // // SK versus [XXX] tempo
        TMEntAbs._INT_W_MODIFIEDDATE_  // dteLastModified
    }; // sum: ?
    
    
    final static public String[] f_s_strsColumnNames =
    { 
        TMEntAbs._STR_ALIAS_,
        "Entry", // SK versus [XXX] tempo
        TMEntAbs._STR_MODIFIEDDATE_
    };
    

    // ------
    // PUBLIC

    public TMEntSKShowAll()
    {
        this(new Object[0][0]);
    }

    public TMEntSKShowAll(Object[][] objssData)
    {
        super(objssData);
    }
    
        
    public int getColumnCount()
    {
        return TMEntSKShowAll.f_s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntSKShowAll.f_s_strsColumnNames[col];
    }
}


