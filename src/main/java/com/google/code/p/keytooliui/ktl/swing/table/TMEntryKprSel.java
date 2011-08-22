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

public final class TMEntryKprSel extends TMEntryKprAbs
{   
    // ----
    
    private static final int _f_s_intNbRowMore = 1;
    
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final int f_s_intColumnIdElligible = 0;
    
    public static final int f_s_intColumnIdAlias =
        TMEntryKprShowAll.f_s_intColumnIdAlias + 
        TMEntryKprSel._f_s_intNbRowMore; // see array of integers right below
        
    public static final int f_s_intColumnIdIsKeyEntry = 
        TMEntryKprShowAll.f_s_intColumnIdIsKeyEntry + 
        TMEntryKprSel._f_s_intNbRowMore;
        
    public static final int f_s_intColumnIdIsCertEntry = 
        TMEntryKprShowAll.f_s_intColumnIdIsCertEntry + 
        TMEntryKprSel._f_s_intNbRowMore;
    
    public static final int f_s_intColumnIdIsSelfSignedCert = 
        TMEntryKprShowAll.f_s_intColumnIdIsSelfSignedCert + 
        TMEntryKprSel._f_s_intNbRowMore;
        
    public static final int f_s_intColumnIdIsTrustedCert = 
        TMEntryKprShowAll.f_s_intColumnIdIsTrustedCert + 
        TMEntryKprSel._f_s_intNbRowMore;
    
    
    // -------------
    // PUBLIC STATIC
    
    // preferred columns width
    public static int[] s_intsColW = null;
    
    // --------------------
    // PRIVATE STATIC FINAL 
    
    private static String[] _s_strsColumnNames = null; 
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        // -----
        TMEntryKprSel.s_intsColW = new int[TMEntryKprShowAll.f_s_intsColW.length+1];
        
        TMEntryKprSel.s_intsColW[0] = 30; // booElligible
        
        for (int i=0; i<TMEntryKprShowAll.f_s_intsColW.length; i++)
            TMEntryKprSel.s_intsColW[i+1] = TMEntryKprShowAll.f_s_intsColW[i];
            
        // ----
        
        TMEntryKprSel._s_strsColumnNames = new String[TMEntryKprSel.s_intsColW.length];
        
        // ----
        
        for (int i=0; i<TMEntryKprShowAll.f_s_strsColumnNames.length; i++)
            TMEntryKprSel._s_strsColumnNames[i+1] = TMEntryKprShowAll.f_s_strsColumnNames[i];
        
        // ----
        
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".TMEntryKprSel" // class name
            ;
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.table.TMEntryKprSel";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            TMEntryKprSel._s_strsColumnNames[0] = rbeResources.getString("elligible");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }
    
    
    // ---------------
    // ABSTRACT PUBLIC
    

    
    // ------
    // PUBLIC
    
    public int getColumnCount()
    {
        return TMEntryKprSel._s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntryKprSel._s_strsColumnNames[col];
    }
   
    public TMEntryKprSel()
    {
        this(new Object[0][0]);
    }

    public TMEntryKprSel(Object[][] objssData)
    {
        super(objssData);
    }
}