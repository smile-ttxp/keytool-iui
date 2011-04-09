/*
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 package com.google.code.p.keytooliui.ktl.util.filter;
 
 /**
    !!!!!!! DONE IN A HURRY !!!!!!!!!
    should be rewritten, feb 13, 2002, rdm
 
 
    a tool to filter strings in use with UIKeytool
    
    eg: 2 letter country code ==> "alpha"
    eg: due to limitation of DOS and some SHELL:
        file names allowed may just contain:
        . alpha
        . numeric
        . "."
        . "_"
        . "-"
        
        eg: NOT ALLOWED: "�" 
 **/
 
 
 import java.awt.Toolkit;

 import com.google.code.p.keytooliui.shared.lang.*;
 
 
 public final class StringFilterUI
 {
    // -----------------------
    // PUBLIC STATIC FINAL INT
    
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.";
    
    // KeyStore, KeyPair
    private static final int _f_s_intMinPassword = 6; 
    private static final int _f_s_intMaxPassword = 40;

    // KeyPair
    private static final int _f_s_intMinAlias = 1;
    private static final int _f_s_intMaxAlias = 40;
    
    // while signing, sigfile base name
    private static final int _f_s_intMinSigfile = 1;
    private static final int _f_s_intMaxSigfile = 8;
    
    private static final int _f_s_intMinFilename = 1;
    private static final int _f_s_intMaxFilename = 40;
    
    // -------------
    // PUBLIC STATIC
    
    /**
        if any error, exiting
    **/
    public static boolean s_isAllowedCbxGender(String strValue)
    {
        String strMethod = _f_s_strClass + "s_isAllowedCbxGender(strValue)";
        
        if (strValue == null)
            MySystem.s_printOutExit(strMethod, "nil strValue");
        
          
        // check for space chars at beginning or ending of the string
        if (strValue.length() < strValue.trim().length())
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < strValue.trim().length(), strValue=" + strValue);
            return false;
        }
        
        
        // check for any spaces inside string
        if (strValue.indexOf(' ') != -1)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.indexOf(' ') != -1, strValue=" + strValue);
            return false;
        }
          
          
        //strValue = strValue.trim(); // !!!!!!!!!!!!!!
        
        if (strValue.length() != 1)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() != 1, strValue=" + strValue);
            return false;
        }
        
        String strReference = "MFmf";
        
        if (! _s_check(strReference, strValue))
        {
            MySystem.s_printOutWarning(strMethod, "! _s_check(strReference, strValue)");
            return false;
        }
        
        return true;
    }
    
    /**
        if any error, exiting
    **/
    public static boolean s_isAllowedDateBirth(String strValue)
    {
        String strMethod = _f_s_strClass + "s_isAllowedDateBirth(strValue)";
        
        if (strValue == null)
            MySystem.s_printOutExit(strMethod, "nil strValue");
        
          
        // check for space chars at beginning or ending of the string
        if (strValue.length() < strValue.trim().length())
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < strValue.trim().length(), strValue=" + strValue);
            return false;
        }
        
        
        // check for any spaces inside string
        if (strValue.indexOf(' ') != -1)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.indexOf(' ') != -1, strValue=" + strValue);
            return false;
        }
          
          
        //strValue = strValue.trim(); // !!!!!!!!!!!!!!
        
        if (strValue.length() != 8)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() != 8, strValue=" + strValue);
            return false;
        }
        
        String strReference = com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strNumeric;
        
        
        if (! _s_check(strReference, strValue))
        {
            MySystem.s_printOutWarning(strMethod, "! _s_check(strReference, strValue)");
            return false;
        }
        
        return true;
    }
    
    /**
        if any error, exiting
    **/
    public static boolean s_isAllowedCountryCode(String strValue)
    {
        String strMethod = _f_s_strClass + "s_isAllowedCountryCode(strValue)";
        
        if (strValue == null)
            MySystem.s_printOutExit(strMethod, "nil strValue");
        
          
        // check for space chars at beginning or ending of the string
        if (strValue.length() < strValue.trim().length())
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < strValue.trim().length(), strValue=" + strValue);
            return false;
        }
        
        
        // check for any spaces inside string
        if (strValue.indexOf(' ') != -1)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.indexOf(' ') != -1, strValue=" + strValue);
            return false;
        }
          
          
        //strValue = strValue.trim(); // !!!!!!!!!!!!!!
        
        if (strValue.length() != 2)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() != 2, strValue=" + strValue);
            return false;
        }
        
        String strReference = com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strLowerCase;
        
        strReference += com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strUpperCase;
        
        if (! _s_check(strReference, strValue))
        {
            MySystem.s_printOutWarning(strMethod, "! _s_check(strReference, strValue)");
            return false;
        }
        
        return true;
    }
    
    // string to be displayed for eg in infoDialog, errorDialog, ...
    public static String s_getRuleCbxGender()
    {
        String strRule = "Value rules:";
        
        strRule += "\n\n";
        strRule += ". value should contain 1 character,";
        strRule += "\n";
        strRule += ". characters allowed:";
        strRule += "\n";
        strRule += "  . M, F";
        strRule += "\n";
        strRule += "  . m, f";
        return strRule;
    }
    
    // string to be displayed for eg in infoDialog, errorDialog, ...
    public static String s_getRuleDateBirth()
    {
        String strRule = "Value rules:";
        
        strRule += "\n\n";
        strRule += ". value should contain 8 numeric characters,";
        strRule += "\n";
        strRule += ". in the following format: yyyymmdd.";
        strRule += "\n";
        strRule += "\n";
        strRule += "ie. 20060709 ==> July 9, 2006";
        
        return strRule;
    }
    
    // string to be displayed for eg in infoDialog, errorDialog, ...
    public static String s_getRuleCountryCode()
    {
        String strRule = "Value rules:";
        
        strRule += "\n\n";
        strRule += ". value should contain 2 characters,";
        strRule += "\n";
        strRule += ". characters allowed:";
        strRule += "\n";
        strRule += "  . " + com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strLowerCase;
        strRule += "\n";
        strRule += "  . " + com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strUpperCase;
        return strRule;
    }
    
    /**
        if any error, exiting
        
        This was the code used for filenames used in DOS or SHELL commands!
    **/
    /**public static boolean s_isAllowedFileName(String strValue)
    {
        String strMethod = _f_s_strClass + "s_isAllowedFileName(strValue)";
        
        if (strValue == null)
            MySystem.s_printOutExit(strMethod, "nil strValue");
            
        strValue = strValue.trim(); // !!!!!!!!!!!!!!
        
        
        if (strValue.length() < StringFilterUI._f_s_intMinFilename)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < StringFilterUI._f_s_intMinFilename, strValue=" + strValue);
            return false;
        }
        
        if (strValue.length() > StringFilterUI._f_s_intMaxFilename)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() > StringFilterUI._f_s_intMaxFilename, strValue=" + strValue);
            return false;
        }
        
        String strReference = com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strLowerCase;
        strReference += com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strUpperCase;
        strReference += com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strNumeric;
        strReference += "._-";
        
        if (! _s_check(strReference, strValue))
        {
            MySystem.s_printOutWarning(strMethod, "! _s_check(strReference, strValue)");
            return false;
        }
        
        return true;
    }**/
    
    
    // string to be displayed for eg in infoDialog, errorDialog, ...
    // should be removed once no more calls to keytool (to do) or jarsigner (done) throw command line
    public static String s_getRuleFileName()
    {
        String strRule = "Value rule:";
        
        strRule += "\n\n";
      
        strRule += "Due to current limitations with some DOS and SHELL commands,";
        strRule += "\nonly the following chars are allowed as filenames:";
        strRule += "\n    " + com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strLowerCase;
        strRule += "\n    " + com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strUpperCase;
        strRule += "\n    " + com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strNumeric;
        strRule += "\n    " +  "._-";  
      
        strRule += "\n";
        
        
        strRule += "\n";
        strRule += "\n" + ". characters number"; // 1 to 40 characters,";
        strRule += "\n" + "  . min: " + StringFilterUI._f_s_intMinFilename;
        strRule += "\n" + "  . max: " + StringFilterUI._f_s_intMaxFilename;
      
        return strRule;
    }
    
    /**
    
    **/
    public static boolean s_isAllowedPassword(String strValue)
    {
        String strMethod = _f_s_strClass + "s_isAllowedPassword(strValue)";
        
        if (strValue == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil strValue");
            return false;
        }
        
        // check for space chars at beginning or ending of the string
        if (strValue.length() < strValue.trim().length())
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < strValue.trim().length(), strValue=" + strValue);
            return false;
        }
        
        
        // check for any spaces inside string
        if (strValue.indexOf(' ') != -1)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.indexOf(' ') != -1, strValue=" + strValue);
            return false;
        }
        
        //strValue = strValue.trim(); // !!!!!!!!!!!!!!
        
        if (strValue.length() < StringFilterUI._f_s_intMinPassword)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < StringFilterUI._f_s_intMinPassword, strValue=" + strValue);
            return false;
        }
        
        if (strValue.length() > StringFilterUI._f_s_intMaxPassword)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() > StringFilterUI._f_s_intMaxPassword, strValue=" + strValue);
            return false;
        }
        
        return true;
    }
    
    /**
    
    **/
    public static boolean s_isAllowedAlias(String strValue)
    {
        String strMethod = _f_s_strClass + "s_isAllowedAlias(strValue)";
        
        if (strValue == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil strValue");
            return false;
        }
        
        // check for space chars at beginning or ending of the string
        if (strValue.length() < strValue.trim().length())
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < strValue.trim().length(), strValue=" + strValue);
            return false;
        }
        
        
        // check for any spaces inside string
        if (strValue.indexOf(' ') != -1)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.indexOf(' ') != -1, strValue=" + strValue);
            return false;
        }
        
        
        // strValue = strValue.trim(); // !!!!!!!!!!!!!!
        
        if (strValue.length() < StringFilterUI._f_s_intMinAlias)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < StringFilterUI._f_s_intMinAlias, strValue=" + strValue);
            return false;
        }
        
        if (strValue.length() > StringFilterUI._f_s_intMaxAlias)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() > StringFilterUI._f_s_intMaxAlias, strValue=" + strValue);
            return false;
        }
        
        return true;
    }
    
    // string to be displayed for eg in infoDialog, errorDialog, ...
    public static String s_getRulePassword()
    {
        String strRule = "Value rules:";
        
        strRule += "\n";
        strRule += "\n" + ". characters number"; // 6 to 40 characters,";
        strRule += "\n" + "  . min: " + StringFilterUI._f_s_intMinPassword;
        strRule += "\n" + "  . max: " + StringFilterUI._f_s_intMaxPassword;
        strRule += "\n";
        strRule += "\n" + ". no space characters allowed.";
        
        return strRule;
    }
    
    // string to be displayed for eg in infoDialog, errorDialog, ...
    public static String s_getRuleAlias()
    {
        String strRule = "Value rules:";
        
        strRule += "\n";
        strRule += "\n" + ". characters number"; // 1 to 40 characters,";
        strRule += "\n" + "  . min: " + StringFilterUI._f_s_intMinAlias;
        strRule += "\n" + "  . max: " + StringFilterUI._f_s_intMaxAlias;
        strRule += "\n";
        strRule += "\n" + ". no space characters allowed.";
        
        strRule += "\n";
        strRule += "\n";
        strRule += "\n" + "Note: aliases are case insensitive.";
        
        
        return strRule;
    }
    
    /**
        1 to 8 chars
        chars allowed:
          A, ..., Z
          0, ..., 9
          -, _
    **/
    public static boolean s_isAllowedSigfile(String strValue)
    {
        String strMethod = _f_s_strClass + "s_isAllowedSigfile(strValue)";
        
        if (strValue == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil strValue");
            return false;
        }
        
        // check for space chars at beginning or ending of the string
        if (strValue.length() < strValue.trim().length())
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < strValue.trim().length(), strValue=" + strValue);
            return false;
        }
        
        
        // check for any spaces inside string
        if (strValue.indexOf(' ') != -1)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.indexOf(' ') != -1, strValue=" + strValue);
            return false;
        }
        
        //strValue = strValue.trim(); // !!!!!!!!!!!!!!
        
        if (strValue.length() < StringFilterUI._f_s_intMinSigfile)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() < StringFilterUI._f_s_intMinSigfile, strValue=" + strValue);
            return false;
        }
        
        if (strValue.length() > StringFilterUI._f_s_intMaxSigfile)
        {
            MySystem.s_printOutWarning(strMethod, "strValue.length() > StringFilterUI._f_s_intMaxSigfile, strValue=" + strValue);
            return false;
        }
        
        String strReference = com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strUpperCase;
        
        strReference += com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strNumeric;
        strReference += "_";
        strReference += "-";
        
        if (! _s_check(strReference, strValue))
        {
            MySystem.s_printOutWarning(strMethod, "! _s_check(strReference, strValue)");
            return false;
        }
        
        return true;
    }
    
    // string to be displayed for eg in infoDialog, errorDialog, ...
    public static String s_getRuleSigfile()
    {
        String strRule = "Value rules:";
        
        strRule += "\n";
        strRule += "\n" + ". characters number"; // 1 to 8 characters,";
        strRule += "\n" + "  . min: " + StringFilterUI._f_s_intMinSigfile;
        strRule += "\n" + "  . max: " + StringFilterUI._f_s_intMaxSigfile;
        strRule += "\n";
        strRule += "\n" + ". characters allowed:";
        strRule += "\n";
        strRule += "\n" + "  . " + com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strUpperCase;
        strRule += "\n" + "  . " + com.google.code.p.keytooliui.shared.swing.text.PlainDocumentFilter.f_s_strNumeric;
        strRule += "\n" + "  . " + "_";
        strRule += "\n" + "  . " + "-";
        
        return strRule;
    }
    
    
    // --------------
    // PRIVATE STATIC
    
    
    
    private static boolean _s_check(String strReference, String strRequest)
    {
        String strMethod = _f_s_strClass + "_s_check(strReference, strRequest)";
        
        if (strReference==null || strRequest==null)
            MySystem.s_printOutExit(strMethod, "nil arg");
        
        for (int i=0; i < strRequest.length(); i++)
        {
            if (strReference.indexOf(strRequest.valueOf(strRequest.charAt(i))) == -1)
            {
                //MySystem.s_printOutWarning(this, strMethod, "this._strCharsAccepted_");
                Toolkit.getDefaultToolkit().beep();
                return false;
            }
        }
        
        return true;
    }
 }