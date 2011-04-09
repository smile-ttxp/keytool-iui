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

import com.google.code.p.keytooliui.shared.lang.*;

public abstract class Shared extends Object
{ 
    // -------------------
    // PUBLIC STATIC FINAL
    
    // !!! "1.5" ==> "15", value should be checked in a static method
    public static final String f_s_strPackLibVersionXP = "15"; 
    
    public static final String f_s_strLibSeparator = "_";
    
    // ----------------------
    // PROTECTED STATIC FINAL
    
    protected static final String _f_s_strPackLibNameXP_ = "rc"; // RagingCat Project 

    
    // --------------------------
    // PUBLIC STATIC FINAL STRING  

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
    
    public static final String f_s_strValueNil = "_void_";
    
    // -------------
    // PUBLIC STATIC
    
    // --
    
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

    protected Shared()
    {   
    }

}
