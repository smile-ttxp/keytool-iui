package com.google.code.p.keytooliui.ktl.swing.table;

/**

**/

import com.google.code.p.keytooliui.shared.lang.*;

final public class TMEntryCrtSel extends TMEntryCrtAbs
{   
    // ----
    
    final static private int _f_s_intNbRowMore = 1;
    
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public int f_s_intColumnIdElligible = 0;
    
    final static public int f_s_intColumnIdAlias =
        TMEntryCrtShowAll.f_s_intColumnIdAlias + 
        TMEntryCrtSel._f_s_intNbRowMore; // see array of integers right below
        
    final static public int f_s_intColumnIdIsKeyEntry = 
        TMEntryCrtShowAll.f_s_intColumnIdIsKeyEntry + 
        TMEntryCrtSel._f_s_intNbRowMore;
        
    final static public int f_s_intColumnIdIsCertEntry = 
        TMEntryCrtShowAll.f_s_intColumnIdIsCertEntry + 
        TMEntryCrtSel._f_s_intNbRowMore;
    
    final static public int f_s_intColumnIdIsSelfSignedCert = 
        TMEntryCrtShowAll.f_s_intColumnIdIsSelfSignedCert + 
        TMEntryCrtSel._f_s_intNbRowMore;
        
    final static public int f_s_intColumnIdIsTrustedCert = 
        TMEntryCrtShowAll.f_s_intColumnIdIsTrustedCert + 
        TMEntryCrtSel._f_s_intNbRowMore;
    
    
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
        TMEntryCrtSel.s_intsColW = new int[TMEntryCrtShowAll.f_s_intsColW.length+1];
        
        TMEntryCrtSel.s_intsColW[0] = 30; // booElligible
        
        for (int i=0; i<TMEntryCrtShowAll.f_s_intsColW.length; i++)
            TMEntryCrtSel.s_intsColW[i+1] = TMEntryCrtShowAll.f_s_intsColW[i];
            
        // ----
        
        TMEntryCrtSel._s_strsColumnNames = new String[TMEntryCrtSel.s_intsColW.length];
        
        // ----
        
        for (int i=0; i<TMEntryCrtShowAll.f_s_strsColumnNames.length; i++)
            TMEntryCrtSel._s_strsColumnNames[i+1] = TMEntryCrtShowAll.f_s_strsColumnNames[i];
        
        // ----
        
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.F_STR_BUNDLE_DIR +
            ".TMEntryTcrSel" // !!!!!!!!!!! identical
            ;
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.table.TMEntryCrtSel";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            TMEntryCrtSel._s_strsColumnNames[0] = rbeResources.getString("elligible");
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
        return TMEntryCrtSel._s_strsColumnNames.length;
    }
        

    public String getColumnName(int col)
    {
        return TMEntryCrtSel._s_strsColumnNames[col];
    }
   
    public TMEntryCrtSel()
    {
        this(new Object[0][0]);
    }

    public TMEntryCrtSel(Object[][] objssData)
    {
        super(objssData);
    }
}
