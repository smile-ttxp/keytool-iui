/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.shared.lang;

/**
**/


import java.util.*;
import java.text.*;

public class MySystem
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.lang.MySystem.";
   
    // --
    static private boolean _S_BLN_PRINTOUTTRACE = false; // true for developping/debugging
    static private boolean _S_BLN_PRINTOUTWARNING = true;
    static private boolean _S_BLN_PRINTOUTERROR = true;
    static private boolean _S_BLN_PRINTOUTFLAGDEV = false;
    
    static
    {
        String str = System.getProperty("_rcp_.printOutTrace");
        
        if (str != null)
        {
            if (str.toLowerCase().compareTo("true") == 0)
              MySystem._S_BLN_PRINTOUTTRACE = true;
        }
        
        // --
    }
        
    // -------------
    // STATIC PUBLIC
    
    
    // beg test
    
    static private String _STR_PATHABSLOGSESSION = null;
    
    // should be called by ?main?
    // and used prior to exit
    static public void s_storePathLogSession(String str)
    {
        if (str == null)
            return;
        
        MySystem._STR_PATHABSLOGSESSION = str;
    }
    
    static public String s_getPathLogSession() { return MySystem._STR_PATHABSLOGSESSION; }
    
    // end test
    
    /**
        JVM: Java Virtual Machine
    **/
    static public boolean s_checkJvmVersion(String strTitleApplication)
    {
        String strMethod = _f_s_strClass + "s_checkJvmVersion(strTitleApplication)";
        
        // changes coz cannot assign a JVM download in JWS like for example v:1.4.2, just supports handling of eg: v:1.4
        // ==> adding a warning
        
        final String[] f_strsJavaVersionError = { "1.0", "1.1", "1.2", "1.3", "1.4", "1.5" };
        //final String[] f_strsJavaVersionWarning = { "1.4.0", "1.4.1" };
        final String[] f_strsJavaVersionOk = { "1.6.0" };
        
        // --
 
        String strJavaVersion = System.getProperty("java.version");
        
        // ------
        // is OK?
        
        for (int i=0; i<f_strsJavaVersionOk.length; i++)
        {
            if (strJavaVersion.startsWith(f_strsJavaVersionOk[i]))
                return true;
        }
        
        // -----------
        // is warning?
        
        /*for (int i=0; i<f_strsJavaVersionWarning.length; i++)
        {
            if (strJavaVersion.startsWith(f_strsJavaVersionWarning[i]))
            {
                MySystem.s_printOutWarning(strMethod, "JVM warning, strJavaVersion=" + strJavaVersion);
                
                // shows a warning-confirm message
                
                com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();	
                
                 
                String strBody = "Your are using Java VM: " + strJavaVersion;
                
                strBody += "\n";
                strBody += "Preferred Java VM(s):";
                
                for (int j=0; j<f_strsJavaVersionOk.length; j++)
                    strBody += "\n" + "  " + f_strsJavaVersionOk[j];
                
                strBody += "\n" + "  " + "... or higher";
                
                
                strBody += "\n";
                strBody += "\n";
                strBody += "Continue anyway?";
                
                boolean blnOk = com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showWarningConfirmDialog(null, strTitleApplication, strBody);
        
                if (blnOk)
                    return true;
                else
                {
                    // forcing an exit, not returning false
                    MySystem.s_printOutTrace(strMethod, "forcing an exit(0), aborted by user");
                    System.exit(0);
                }
        
                // ending
                return true;
            }
        }*/
        
        // ---
        
        boolean blnJavaVersionOk = true;
        
        for (int i=0; i<f_strsJavaVersionError.length; i++)
        {
            if (strJavaVersion.startsWith(f_strsJavaVersionError[i]))
            {
                blnJavaVersionOk = false;
                break;
            }
        }
        
        // ----
        
        if (blnJavaVersionOk)
        {
            MySystem.s_printOutWarning(strMethod, "NOT YET TESTED UNDER JVM:" + strJavaVersion);
            return true;
        }
                
                
        MySystem.s_printOutError(strMethod, "wrong java version: " + strJavaVersion + ", should be " + f_strsJavaVersionOk[0]  + ", or higher");   
        
        /**
            memo: JVM 1.1.7A (webgain): dialog works ok
        **/
        if (! strJavaVersion.startsWith("1.0"))
        {

            com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
            String strErrorJvmBody2 = "\n    " + strJavaVersion + "\n\n";
            String strErrorJvmBody4 = "\n    " + f_strsJavaVersionOk[0] + "\n\n";
            String strBody = _s_strDialogErrorJvmBody1 + strErrorJvmBody2 + _s_strDialogErrorJvmBody3 + strErrorJvmBody4 /*+ _s_strDialogErrorJvmBody5*/;
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(null, strTitleApplication, strBody);
            
            // forcing an exit, not returning false
            MySystem.s_printOutTrace(strMethod, "forcing an exit(0)");
            System.exit(0);
        }
        
        
        // will show an error dialog, then exit
        // ending
        return false;
    }
    
     
    
    
    // -------------
    // STATIC PUBLIC
    
    static public String s_getFileSeparator()
    {
        if (_s_strFileSeparator == null)
            _s_getFileSeparator();  
        
        return _s_strFileSeparator;
    }
    
    static public String s_getDateFromTime(long lngTime)
    {
        final String f_strWhere = _f_s_strClass + "s_getDateFromTime(lngTime)";
        
        Date dte = new Date(lngTime);
        
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL,
            Locale.getDefault());
            
        DateFormat timeFormatter = DateFormat.getTimeInstance(
				DateFormat.SHORT, Locale.getDefault());
        
        
        String dateOut = dateFormatter.format(dte);
        String timeOut = timeFormatter.format(dte);

        return dateOut + " " + timeOut;
    }
    
    static public String s_getDateFromTime(String strTime)
    {
        final String f_strWhere = _f_s_strClass + "s_getDateFromTime(strTime)";
        
        if (strTime == null)
        {
            MySystem.s_printOutError(f_strWhere, "nil strTime");
            return null;
        }
                
        return s_getDateFromTime(Long.parseLong(strTime));
    }
    
     /**
        ENGLISH FORMAT
        EG: 18:35 2000-02-10
    **/
    
    static public String s_getDateCurrent()
    {
        return s_getDate(new Date());
    }
    
    static public String s_getDate(Date dat)
    {
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL,
            Locale.getDefault());
            
        DateFormat timeFormatter = DateFormat.getTimeInstance(
				DateFormat.SHORT, Locale.getDefault());
        
        String strDateOut = dateFormatter.format(dat);
        String strTimeOut = timeFormatter.format(dat);

        return strDateOut + " " + strTimeOut;
    }
    
    // used by statics
    static public void s_printOutFlagDev(String strWhat, String strMessage)
    {
        if (_S_BLN_PRINTOUTFLAGDEV)
            _s_printOut("* FLAGDEV", strWhat, strMessage);
    }
    
    // used by statics
    static public void s_printOutTrace(String strWhat, String strMessage)
    {
        if (_S_BLN_PRINTOUTTRACE)
            _s_printOut("> TRACE", strWhat, strMessage);
    }
    
    static public void s_printOutWarning(String strWhat, String strMessage)
    {
        if (_S_BLN_PRINTOUTWARNING)
            _s_printOut("! WARNING", strWhat, strMessage);
    }
    
    static public void s_printOutError(String strWhat, String strMessage)
    {
        //System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        
        if (_S_BLN_PRINTOUTERROR)
            _s_printOut("? ERROR", strWhat, strMessage);
    }
    
    /**
        1) print current error in buffer
        2) save buffer in "error.log"
        3) shows a error dialog
        4) exit
    **/
    
    static public void s_printOutExit(String strWhat, String strMessage)
    {
        // 1) print current error in buffer
        if (_S_BLN_PRINTOUTERROR)
             _s_printOut("? ERROR", strWhat, strMessage);
             
        _s_printOutExit(strMessage);
    }
    
    // used by non-statics
    static public void s_printOutFlagDev(Object obj, String strMethod, String strMessage)
    {
        if (_S_BLN_PRINTOUTFLAGDEV)
            _s_printOut("* FLAGDEV", obj.getClass().getName() + "." + strMethod, strMessage);
    }
    
    // used by non-statics
    static public void s_printOutTrace(Object obj, String strMethod, String strMessage)
    {
        if (_S_BLN_PRINTOUTTRACE)
            _s_printOut("> TRACE", obj.getClass().getName() + "." + strMethod, strMessage);
    }
    
    static public void s_printOutWarning(Object obj, String strMethod, String strMessage)
    {
        if (_S_BLN_PRINTOUTWARNING)
            _s_printOut("! WARNING", obj.getClass().getName() + "." + strMethod, strMessage);
    }
    
    static public void s_printOutError(Object obj, String strMethod, String strMessage)
    {
        //System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        
        if (_S_BLN_PRINTOUTERROR)
            _s_printOut("? ERROR", obj.getClass().getName() + "." + strMethod, strMessage);
    }
    
    
    /**
        1) print current error in buffer
        2) save buffer in "error.log"
        3) shows a error dialog
        4) exit
    **/
    
    static public void s_printOutExit(Object obj, String strMethod, String strMessage)
    {
        // 1) print current error in buffer
        if (_S_BLN_PRINTOUTERROR)
            _s_printOut("? ERROR", obj.getClass().getName() + "." + strMethod, strMessage);
            
        _s_printOutExit(strMessage);
    }
    
    // --------------
    // STATIC PRIVATE
    
    static private int _s_intInstanceCount = 0;
    
    // (used by "main" method)
    
    // ---------------------
    // begin resource bundle
    
    // dialog error wrong JVM
    static private String _s_strDialogErrorJvmBody1 = null;
    static private String _s_strDialogErrorJvmBody3 = null;
    
    // dialog error exiting
    static private String _s_strDialogErrorExitTitle = null;
    static private String _s_strDialogErrorExitBody = null;
    
    
    
    static private String _s_strFileSeparator = null;
    
    static private int _S_INT_PRINTOUTCOUNT = 1;
    
    
    
    static private void _s_getFileSeparator()
    {
        String strWhere = _f_s_strClass + "_s_getFileSeparator()";
        
        try
        {
            _s_strFileSeparator = System.getProperty("file.separator");      
        }
        
        catch (SecurityException excSecurity)
        {
            excSecurity.printStackTrace();
            s_printOutExit(strWhere, "excSecurity caught");
        }
        
        if (_s_strFileSeparator == null)
        {
            s_printOutExit(strWhere, "nil _s_strFileSeparator");
            
        }
    
    }
    
    static private void _s_printOut(String strWhat, String strWhere, String strMessage)
    {
        String str = "\n" + _S_INT_PRINTOUTCOUNT + " " + strWhat;
        str += " (instance ID: " + _s_intInstanceCount + ")";
        System.out.println(str);
        System.out.println(" . location: " + strWhere);
        System.out.println(" . message: " + strMessage);
        _S_INT_PRINTOUTCOUNT ++;
        str = null;
    }
    
    /**
        memo: error caught, forcing an exit
    **/
    static private void _s_printOutExit(String strMessage)
    {
        //2) save all errors in "error.log"
        
        //3) shows a error dialog
        //String strApplicationTitle = "[application.title]";
        //String strErrorBody = "An error occurred, a log of the error is located on [appli.home]/usr/[user.name]/[appli.shortname]/error.log";
        
        // -----
        // TRICK
        
        String strApplicationTitle = "application";
        
        if (_s_strDialogErrorExitTitle != null)
            strApplicationTitle = _s_strDialogErrorExitTitle;
            
        String strApplicationBody = "An error occurred in the application.";
        strApplicationBody += "\n";
        strApplicationBody += "The application will now terminate.";
        strApplicationBody += "\n\n";
        strApplicationBody += "Please report this issue to";
        strApplicationBody += "\n";
        strApplicationBody += "http://code.google.com/p/keytool-iui/";
        
        if (_s_strDialogErrorExitBody != null)
        {
            strApplicationBody = strMessage;
            strApplicationBody += "\n\n";
            strApplicationBody += _s_strDialogErrorExitBody;
        }
        
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(
                    (java.awt.Component) null, strApplicationTitle, strApplicationBody);

        //4) exit
    
        System.exit(1); // "1" ==> wrong exit, "0" ==>  correct exit
    }
    
    
    
     // ------------------
    // STATIC INITIALIZER
    
    static
    {
        // System.out.println(s_getDateCurrent());
        _s_intInstanceCount ++;
    }
    
    // ----
    
    // in comments, april 11, 07
    /*static
    {
        String strMethod = _f_s_strClass + "initializer";
        
       
        
        if (Shared.s_getPrbMagic() != null)
        {
            String str = null;
            
            // --
            // --
            str = null;
            try { str = Shared.s_getPrbMagic().getString(_f_s_strClass + "printOutTrace"); }
            catch (MissingResourceException excMissingResource) {} // ignoring
                
            if (str != null) // else ignoring
            {
                str = str.toLowerCase();
                    
                if (str.compareTo("false") == 0)
                    _S_BLN_PRINTOUTTRACE = false;
                else if (str.compareTo("true") == 0)
                    _S_BLN_PRINTOUTTRACE = true;
                 else
                    MySystem.s_printOutTrace(strMethod, "ignoring uncaught value, key=" + _f_s_strClass + "printOutTrace" + ", value =" + str);

            }
                
            // --
            str = null;
            try { str = Shared.s_getPrbMagic().getString(_f_s_strClass + "printOutWarning"); }
            catch (MissingResourceException excMissingResource) {} // ignoring
                
            if (str != null) // else ignoring
            {
                str = str.toLowerCase();
                    
                if (str.compareTo("false") == 0)
                    _S_BLN_PRINTOUTWARNING = false;
                else if (str.compareTo("true") == 0)
                    _S_BLN_PRINTOUTWARNING = true;
                 else
                    MySystem.s_printOutTrace(strMethod, "ignoring uncaught value, key=" + _f_s_strClass + "printOutWarning" + ", value =" + str);

            }
                
            // --
            str = null;
            try { str = Shared.s_getPrbMagic().getString(_f_s_strClass + "printOutError"); }
            catch (MissingResourceException excMissingResource) {} // ignoring
                
            if (str != null) // else ignoring
            {
                str = str.toLowerCase();
                    
                if (str.compareTo("false") == 0)
                    _S_BLN_PRINTOUTERROR = false;
                else if (str.compareTo("true") == 0)
                    _S_BLN_PRINTOUTERROR = true;
                else
                    MySystem.s_printOutTrace(strMethod, "ignoring uncaught value, key=" + _f_s_strClass + "printOutError" + ", value =" + str);

            }
            
            // --
            str = null;
            try { str = Shared.s_getPrbMagic().getString(_f_s_strClass + "printOutFlagDev"); }
            catch (MissingResourceException excMissingResource) {} // ignoring
                
            if (str != null) // else ignoring
            {
                str = str.toLowerCase();
                    
                if (str.compareTo("false") == 0)
                    _S_BLN_PRINTOUTFLAGDEV = false;
                else if (str.compareTo("true") == 0)
                    _S_BLN_PRINTOUTFLAGDEV = true;
                else
                    MySystem.s_printOutTrace(strMethod, "ignoring uncaught value, key=" + _f_s_strClass + "printOutFlagDev" + ", value =" + str);
            }
            
            // --
        }
    }*/
    
    static
    {
        String strMethod = _f_s_strClass + "initializer";
        
        try
        {
            String strBundleFileShort =
                com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
                ".MySystem" // class name
                ;

            ResourceBundle rbeResources = ResourceBundle.getBundle(strBundleFileShort, 
               Locale.getDefault());
            
            _s_strDialogErrorJvmBody1 = rbeResources.getString("dialogErrorJvmBody1");
            _s_strDialogErrorJvmBody3 = rbeResources.getString("dialogErrorJvmBody3");
            
            _s_strDialogErrorExitTitle = rbeResources.getString("dialogErrorExitTitle");
            _s_strDialogErrorExitBody = rbeResources.getString("dialogErrorExitBody");
        }
        
        catch (MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strMethod, "excMissingResource caught");
        }
    }
}