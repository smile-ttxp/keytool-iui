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
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;

import javax.swing.*;
import javax.swing.table.*;

final public class TMEntryKprShowAll extends TMEntryKprAbs
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public int f_s_intColumnIdAlias = 0;            // see array of integers right below
    final static public int f_s_intColumnIdIsKeyEntry = 1;       // see array of integers right below
    final static public int f_s_intColumnIdIsCertEntry = 2;      // see array of integers right below
    final static public int f_s_intColumnIdIsSelfSignedCert = 3; // see array of integers right below
    final static public int f_s_intColumnIdIsTrustedCert = 4;    // see array of integers right below
    
    // preferred columns width 
    final static public int[] f_s_intsColW = 
    { 
        120, // strAlias
        30,  // booKeyEntry
        30,  // booCertEntry
        30,  // booSelfSignedCert
        30,  // booTrustedCert
        30,  // strAlgoKeyPubl
        30,  // strSizeKeyPubl
        30,  // strTypeCert
        60,  // strAlgSigCert
        120  // dteLastModified
    }; // sum: ?
    
    
    final static public String[] f_s_strsColumnNames = new String[TMEntryKprShowAll.f_s_intsColW.length];
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".TMEntryKprShowAll" // class name
            ;
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.table.TMEntryKprShowAll";
        
        try
        { 
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
            
            for (int i=0; i<TMEntryKprShowAll.f_s_strsColumnNames.length; i++)
                TMEntryKprShowAll.f_s_strsColumnNames[i] = rbeResources.getString("column_" + Integer.toString(i));
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");  
        }
    }
 
    // ------
    // PUBLIC

    public TMEntryKprShowAll()
    {
        this(new Object[0][0]);
    }

    public TMEntryKprShowAll(Object[][] objssData)
    {
        super(objssData);
    }
    
        
    public int getColumnCount()
    {
        return TMEntryKprShowAll.f_s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntryKprShowAll.f_s_strsColumnNames[col];
    }
}
