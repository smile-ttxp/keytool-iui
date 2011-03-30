package com.google.code.p.keytooliui.ktl.swing.table;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;

import javax.swing.*;
import javax.swing.table.*;

final public class TMEntryTcrShowAll extends TMEntryTcrAbs
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
        30,  // strTypeKeypair
        30,  // strTypeCert
        60,  // strAlgSigCert
        120  // dteLastModified
    }; // sum: ?
    
    
    final static public String[] f_s_strsColumnNames = new String[TMEntryTcrShowAll.f_s_intsColW.length];
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".TMEntryTcrShowAll" // class name
            ;
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.table.TMEntryTcrShowAll";
        
        try
        { 
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
            
            for (int i=0; i<TMEntryTcrShowAll.f_s_strsColumnNames.length; i++)
                TMEntryTcrShowAll.f_s_strsColumnNames[i] = rbeResources.getString("column_" + Integer.toString(i));
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");  
        }
    }
 
    // ------
    // PUBLIC

    public TMEntryTcrShowAll()
    {
        this(new Object[0][0]);
    }

    public TMEntryTcrShowAll(Object[][] objssData)
    {
        super(objssData);
    }
    
        
    public int getColumnCount()
    {
        return TMEntryTcrShowAll.f_s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntryTcrShowAll.f_s_strsColumnNames[col];
    }
}
