package com.google.code.p.keytooliui.ktl.swing.table;

/**

**/

import com.google.code.p.keytooliui.shared.lang.*;

final public class TMEntryTcrSel extends TMEntryTcrAbs
{   
    // ----
    
    final static private int _f_s_intNbRowMore = 1;
    
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public int f_s_intColumnIdElligible = 0;
    
    final static public int f_s_intColumnIdAlias =
        TMEntryTcrShowAll.f_s_intColumnIdAlias + 
        TMEntryTcrSel._f_s_intNbRowMore; // see array of integers right below
        
    final static public int f_s_intColumnIdIsKeyEntry = 
        TMEntryTcrShowAll.f_s_intColumnIdIsKeyEntry + 
        TMEntryTcrSel._f_s_intNbRowMore;
        
    final static public int f_s_intColumnIdIsCertEntry = 
        TMEntryTcrShowAll.f_s_intColumnIdIsCertEntry + 
        TMEntryTcrSel._f_s_intNbRowMore;
    
    final static public int f_s_intColumnIdIsSelfSignedCert = 
        TMEntryTcrShowAll.f_s_intColumnIdIsSelfSignedCert + 
        TMEntryTcrSel._f_s_intNbRowMore;
        
    final static public int f_s_intColumnIdIsTrustedCert = 
        TMEntryTcrShowAll.f_s_intColumnIdIsTrustedCert + 
        TMEntryTcrSel._f_s_intNbRowMore;
    
    
    // -------------
    // STATIC PUBLIC
    
    // preferred columns width
    static public int[] s_intsColW = null;
    
    // --------------------
    // FINAL STATIC PRIVATE 
    
    static private String[] _s_strsColumnNames = null; 
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        // -----
        TMEntryTcrSel.s_intsColW = new int[TMEntryTcrShowAll.f_s_intsColW.length+1];
        
        TMEntryTcrSel.s_intsColW[0] = 30; // booElligible
        
        for (int i=0; i<TMEntryTcrShowAll.f_s_intsColW.length; i++)
            TMEntryTcrSel.s_intsColW[i+1] = TMEntryTcrShowAll.f_s_intsColW[i];
            
        // ----
        
        TMEntryTcrSel._s_strsColumnNames = new String[TMEntryTcrSel.s_intsColW.length];
        
        // ----
        
        for (int i=0; i<TMEntryTcrShowAll.f_s_strsColumnNames.length; i++)
            TMEntryTcrSel._s_strsColumnNames[i+1] = TMEntryTcrShowAll.f_s_strsColumnNames[i];
        
        // ----
        
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".TMEntryTcrSel" // class name
            ;
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.table.TMEntryTcrSel";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            TMEntryTcrSel._s_strsColumnNames[0] = rbeResources.getString("elligible");
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
        return TMEntryTcrSel._s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntryTcrSel._s_strsColumnNames[col];
    }
   
    public TMEntryTcrSel()
    {
        this(new Object[0][0]);
    }

    public TMEntryTcrSel(Object[][] objssData)
    {
        super(objssData);
    }
}