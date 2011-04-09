/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared;

/**

**/

import com.google.code.p.keytooliui.shared.lang.*;


import java.util.*;

abstract public class Shared extends Object
{ 
    // -------------------
    // PUBLIC STATIC FINAL
    
    // !!! "1.5" ==> "15", value should be checked in a static method
    public static final String f_s_strPackLibVersionXP = "15"; 
    
    public static final String f_s_strLibNameShortXPBeans = "bns";
    public static final String f_s_strLibNameShortXPApplets = "app";
    
    public static final String f_s_strLibSeparator = "_";
    
    // ----------------------
    // PROTECTED STATIC FINAL
    
    protected static final String _f_s_strPackLibNameXP_ = "rc"; // RagingCat Project 

    
    protected static final String _f_s_strLibSuffix_ = "." + 
        com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strJARDocument.toLowerCase(); // memo: already lowcase
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.Shared.";
    
    private static final String _f_s_strBundleFileShortMagic = "_magic_rcp";
    
    
    // -- package name
    
    private static final String _f_s_strPackLibNameJH = "jh";   // JavaSoft's JavaHelp
    private static final String _f_s_strPackLibNameOH = "oh";   // Oracle Help for Java
    
    // -- package version
    private static final String _f_s_strPackLibVersionJH = "2004"; // changed march  25, 2007 (jh2002: from oct 23, 2005, previous: jh2001)
    private static final String _f_s_strPackLibVersionOH = "427";  // changed dec 01, 2004 (old:"423") 
    
    // -- lib short name
    
    private static final String _f_s_strLibNameShortXPsharedCls = "shr";
    private static final String _f_s_strLibNameShortXPsharedRes = Shared._f_s_strLibNameShortXPsharedCls + "_rs";
    
    private static final String _f_s_strLibNameShortOHh = "h";
    private static final String _f_s_strLibNameShortOHi = "i";
    private static final String _f_s_strLibNameShortOHj = "j";
    private static final String _f_s_strLibNameShortJHjh = "jh";
    private static final String _f_s_strLibNameShortJHjsearch = "jsearch"; // used in XLBuilder
    
    // --------------------------
    // PUBLIC STATIC FINAL STRING  
    
    // eg: oh[javahelp.version]h.jar
    public static final String f_s_strLibOHh = 
        Shared._f_s_strPackLibNameOH + // package name
        Shared._f_s_strPackLibVersionOH + // package version
        Shared._f_s_strLibNameShortOHh + // library short name
        Shared._f_s_strLibSuffix_
        ; 
        
    // eg: oh[javahelp.version]i.jar
    public static final String f_s_strLibOHi = 
        Shared._f_s_strPackLibNameOH + // package name
        Shared._f_s_strPackLibVersionOH + // package version
        Shared._f_s_strLibNameShortOHi + // library short name
        Shared._f_s_strLibSuffix_
        ; 
        
    // eg: oh[javahelp.version]j.jar
    public static final String f_s_strLibOHj = 
        Shared._f_s_strPackLibNameOH + // package name
        Shared._f_s_strPackLibVersionOH + // package version
        Shared._f_s_strLibNameShortOHj + // library short name
        Shared._f_s_strLibSuffix_
        ; 
    
    // eg: jh[javahelp.version]jh.jar
    public static final String f_s_strLibJHjh = 
        Shared._f_s_strPackLibNameJH + // package name
        Shared._f_s_strPackLibVersionJH + // package version
        Shared._f_s_strLibNameShortJHjh + // library short name
        Shared._f_s_strLibSuffix_
        ; 
    
    // eg: jh[javahelp.version]_srh.jar, may not be in used for now (this lib is used by XLBuilder)
    public static final String f_s_strLibJHjsearch =
        Shared._f_s_strPackLibNameJH + // package name
        Shared._f_s_strPackLibVersionJH + // package version
        Shared._f_s_strLibNameShortJHjsearch + // library short name
        Shared._f_s_strLibSuffix_
        ; 
    
    public static final String f_s_strBundleDir = "com.google.code.p.keytooliui.shared.bundle";
    
    /*
        ==> public released:
        
        . compiled with j2sdk 1.4.2-b28-Windows2000, 
          
          
          external packages:
           . JavaHelp 2.0-02
           . JMF 2.1.1e
           . BouncyCastle 1.3.0 (jdk1.5)
           . Oracle Help for Java 4.2.7.?.?
           . FreeTTS  1.1.2 (unused in the delivered applis)
          
          ORIGINAL_PACKAGE | VERSION | VENDOR | RENAMED_PACKAGE
          
          . bcprov-jdk15-130.jar 1.3.0  (BouncyCastle)         ==> bcprov120.jar (name changed coz of 72 char limits in manifest "Class-Path:" line !!!! for UIKeytool only !!!!)
          . jh.jar               2.0_2  (JavaHelp)             ==> jh2002_jh.jar
          . jsearch.jar          2.0_2  (JavaHelp)             ==> jh2002_srh.jar (!!!! for XLBuilder only !!!!)
          . jmf2.1.1e.jar        2.1.1e (JavaMediaFramework)   ==> jmf211e_cor.jar (core)
                                                                   jmf211e_plg.jar (plugin)
                                                                   jmf211e_dll_win.jar (DLLs jarred for use with JWS/windows)
          
          . help4.jar            4.2.7 (Oracle Help for Java) ==> oh427h.jar (OHJ engine implementation)
          . ohj-jewt.jar         4.2.7 (Oracle Help for Java) ==> oh427i.jar (OHJ engine dependency)
          . oracle_ice5.jar      4.2.7 (Oracle Help for Java) ==> oh427j.jar (OHJ's version of ICEBrowser)
          
    
          Was released :
          . "SignIt! for Windows"
          . RCReader
          . JHReader (through XLBeans)
          . OHReader (Through XLApplets)
    */
    
    public static final String f_s_strVersionCurr = "1.5"; // released: [month] [day], [year]
    
    public static final String[] f_s_strsVersionPrev =
    { 
        "1.01b",      // released: april 22, 2001 ==> public release
        "1.0.2-beta", // released: april 30, 2001 ==> for 2001 JavaOne Conference, as a demo only
        "1.0.3-beta", // released: may 23, 2001 ==> public released compiled with jdk1.3.0 final/WindowsNT
        "1.0.4-beta", // released: september 8, 2001 ==> public released compiled with jdk1.3.1 final/WindowsNT
        "1.0",        // released: february 15, 2002 ==> public released compiled with jdk1.3.1 final/Windows2000/JH1.1.2
        "1.1",        // released: ????????
        "1.2",        // released: october 7, 2003
        "1.3",        // released: ????????
        "1.4"
    };
    
    public static final String f_s_strValueNil = "_void_";
    
    // -------------
    // PUBLIC STATIC
    
    private static PropertyResourceBundle _s_prbMagic = null;
    static boolean _s_blnFirstRun = true;
    // --
    /**
        if any error, exiting.
        missing file allowed!
    **/
    // in comments, april 11, 07
    /*public static PropertyResourceBundle s_getPrbMagic()
    {
        String strMethod = Shared._f_s_strClass + "s_getPrbMagic()";
        
        if (Shared._s_prbMagic != null)
            return Shared._s_prbMagic;
        
        if (! Shared._s_blnFirstRun)
            return null;
        
        File fleParentAppli = com.google.code.p.keytooliui.shared.io.S_FileSys.s_getPathAbsParentAppli(false);
        
        if (fleParentAppli == null)
        {
            
            MySystem.s_printOutExit(strMethod, "nil fleParentAppli");
        }
        
        File fleMagic = new File(
            fleParentAppli, 
            Shared._f_s_strBundleFileShortMagic + ".properties"
            );
        
        if (Shared._s_blnFirstRun)
        {
            Shared._s_blnFirstRun = false;
            //System.out.println("fleMagic.getAbsolutePath()= " + fleMagic.getAbsolutePath());
            //MySystem.s_printOutTrace(strMethod, "fleMagic.getAbsolutePath()= " + fleMagic.getAbsolutePath());
        }
        
        
        if (! fleMagic.exists())
        {
            //System.out.println("! fleMagic.exists()");
            return null;
        }
        
        FileInputStream fisMagic = null;
        
        try
        {
            fisMagic = new FileInputStream(fleMagic);
        }
        
        catch(FileNotFoundException excFileNotFound)
        {
            //System.out.println("fleMagic: excFileNotFound");
            
            MySystem.s_printOutTrace(strMethod, "IGNORING: got excFileNotFound, fleMagic.getAbsolutePath()=" + 
                fleMagic.getAbsolutePath());
            
            return null;
        }
     
        
        try
        {
            Shared._s_prbMagic = new PropertyResourceBundle(fisMagic);
            
            fisMagic.close();
            fisMagic = null;
        }
        
        catch(IOException excIO)
        {
            //System.out.println("fleMagic: excIO");
            
            // ignoring
            MySystem.s_printOutTrace(strMethod, "IGNORING: got excIO, fleMagic.getAbsolutePath()=" + 
                fleMagic.getAbsolutePath());
            
            return null;
        }
        
        
        
        return Shared._s_prbMagic;
    }
    */
    /*
        eg: strAppliLibNameShort="rcr"
        ==> rc[version]rcr
    */
    public static String s_getNameShortLibClass(String strAppliLibNameShort)
    {
        String strMethod = "s_getNameShortLibClass(strAppliLibNameShort)";
        
        if (strAppliLibNameShort == null)
            MySystem.s_printOutExit(strMethod, "nil strAppliLibNameShort");
            
        strAppliLibNameShort = strAppliLibNameShort.trim();
        
        if (strAppliLibNameShort.length() != 3)
            MySystem.s_printOutExit(strMethod, "strAppliLibNameShort.length() != 3, strAppliLibNameShort.length()=" + strAppliLibNameShort.length());
  
        return 
            Shared._f_s_strPackLibNameXP_ + // package name
            Shared.f_s_strPackLibVersionXP + // package version
            strAppliLibNameShort; // library short name
    }
    
    /*
        eg: strAppliLibNameShort="rcr_rs"
        ==> rc[version]rcr_rs
    */
    public static String s_getNameShortLibResource(String strAppliLibNameShort)
    {
        String strMethod = "s_getNameShortLibResource(strAppliLibNameShort)";
        
        if (strAppliLibNameShort == null)
            MySystem.s_printOutExit(strMethod, "nil strAppliLibNameShort");
            
        strAppliLibNameShort = strAppliLibNameShort.trim();
        
        if (strAppliLibNameShort.length() != 6)
            MySystem.s_printOutExit(strMethod, "strAppliLibNameShort.length() != 6, strAppliLibNameShort.length()=" + strAppliLibNameShort.length());
  
        return 
            Shared._f_s_strPackLibNameXP_ + // package name
            Shared.f_s_strPackLibVersionXP + // package version
            strAppliLibNameShort; // library short name
    }
    
    // eg: rc[version]rcr_rs.jar
    public static String s_getNameLibResource(String strAppliLibNameShort)
    {
        String strMethod = "s_getNameLibResource(strAppliLibNameShort)";
        
        String str = s_getNameShortLibResource(strAppliLibNameShort);
        
        if (str == null)
            MySystem.s_printOutExit(strMethod, "nil str");
          
        return str + Shared._f_s_strLibSuffix_;
    }
    
    // eg: rc[version]rcr.jar
    public static String s_getNameLibClass(String strAppliLibNameShort)
    {
        String strMethod = "s_getNameLibClass(strAppliLibNameShort)";
        
        String str = s_getNameShortLibClass(strAppliLibNameShort);
        
        if (str == null)
            MySystem.s_printOutExit(strMethod, "nil str");
          
        return str + Shared._f_s_strLibSuffix_;
    }
    
    // eg: rc[version]shr.jar
    public static String s_getNameLibClass()
    {
        return Shared.s_getNameLibClass(Shared._f_s_strLibNameShortXPsharedCls);
    }
    
    // eg: rc[version]shr.jar
    public static String s_getNameLibResource()
    {
        return Shared.s_getNameLibResource(Shared._f_s_strLibNameShortXPsharedRes);
    }
    
    

    // ---------
    // PROTECTED
    

    protected Shared()
    {   
    }
    

}