/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.bugfixes.net;



/**

BUG id #2, jdk13final, cannot delete a jar file, which was previously used in a JTextPane
eg:
String strPathAbsoluteJar = "D:/foo1/foo2";
String strPathRelativeFile = "foo3/foo4.html";

String strUrl = "jar:file:";
strUrl += strPathAbsoluteJar;
strUrl += com.google.code.p.keytooliui.shared.io.FileJar.f_s_strFileSeparatorMain;
strUrl += strPathRelativeFile;

tpn = new JTextPane();
tpn.setPage(strUrl);
...
tpn = null;
System.gc();

File fle = new File(strPathAbsoluteJar);

boolean blnOk = fle.delete();

==> blnOk = false!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

workaround: extract the bytes from file inside jar, generate a temp file (deleteOnExit)
assign the url using "setPage(url)".



URL url = S_Url.s_getUrlTempFromJarredFile(strPathAbsoluteJar, strPathRelativeFile);

**/

/**
    fixing up windows bug!
    
    /**
                    BUG in:
                     boolean java.net.URL.sameFile(java.net.URL urlToCompare)
                     
                     if for example the first url was generated from a link in an htmlEditorKit,
                     and the one to compare is generated from a leaf
                     the first one has the following file:
                     D:/prod/........./foo1.html
                     the second one has the following file:
                     D:\prod\.........\foo1.html
                     ==> a call to url.sameFile will always return false in windowsNT!!!!!!
                         but will be ok in linux!
                **/



import com.google.code.p.keytooliui.shared.lang.*;

import java.net.*;

public class S_Url
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.";
    
    // --------------
    // PRIVATE STATIC
    
    private static boolean _s_blnOperatingSystemWindows = false;
    
    // ------------------
    // STATIC INITIALIZER
    
    
    static
    {        
        // NEED TO BE REWRITTEN
        _s_blnOperatingSystemWindows = com.google.code.p.keytooliui.shared.io.S_OperatingSystem.s_isWindows();
    }
    
    // -------------
    // PUBLIC STATIC
    
    /* created: june 17, 2003
         Coz bug since JVM from 1.3 to 1.4:
         In 1.4, for a local URL, calling url.getFile().toString(), spaces show up as %20
         
         eg: C:\Program Files\foo ==> C:\Program%20Files\foo
         
         ----
         note: alternate (but deprecated) code:
           URLDecoder.decode(url.getFile());
         ----
    */
        
    // bug still remaining dec 7, 2005
    public static String s_getFileToString(URL url)
    {
        String strWhere = _f_s_strClass + "s_getFileToString(url)";
        
        String strCodeSpaceUrl = "%20";
        String strCodeSpaceFile = " ";
        
        if (url == null)
        {
            MySystem.s_printOutExit(strWhere, "nil url");
        }
        
        String strUrl = url.getFile().toString();
        return strUrl.replaceAll(strCodeSpaceUrl, strCodeSpaceFile);
    }
    
    public static String s_getToString(URL url)
    {
        String strWhere = _f_s_strClass + "s_getToString(url)";
        
        String strCodeSpaceUrl = "%20";
        String strCodeSpaceFile = " ";
        
        if (url == null)
        {
            MySystem.s_printOutExit(strWhere, "nil url");
        }
        
        String strUrl = url.toString();
        return strUrl.replaceAll(strCodeSpaceUrl, strCodeSpaceFile);
    }
    
    
    
    
    /**
    
        BUG in jdk1.2.2, winNt, Dell:
        
        DON'T KNOW ABOUT LINUX!!
        
        if the link is about a reference located in the same file,
        then, url has this form:
        
        url1.getFile(): D:/prod/.../Jse/D:/prod2/.../Jse/Sec2.htm
        
        url2.getFile(): D:\prod\...\Jse\Sec2.htm
        
        
        MEMO: the second url is assumed to be ok!!
    **/
    
    
    public static boolean s_sameFileFromEditorPane(URL url1, URL url2)
    {
        String strWhere = _f_s_strClass + "s_sameFileFromEditorPane(url1, url2)";

        if (! _s_blnOperatingSystemWindows)
            return s_sameFile(url1, url2);
            
        // ----
        
        if (url1==null || url2==null)
            MySystem.s_printOutExit(strWhere, "nil arg");
        
        String strFile2 = url2.getFile();
        strFile2 = strFile2.replace('\\', '/');
        
        String strFile1 = url1.getFile();
        
        return strFile1.endsWith(strFile2);
    }
    
    public static boolean s_sameFile(URL url1, URL url2)
    {
        String strWhere = _f_s_strClass + "s_sameFile(url1, url2)";
        
        if (url1==null || url2==null)
            MySystem.s_printOutExit(strWhere, "nil arg");
        
        
        /**
            because of tbrl using linux, EG:
            url1 comes up from a link activated inside the current page
            and url2 is the current url from the current (active) cell lead of the tree,then:
            protocol of url1 = "file"
            and
            protocol of url2 = "jar"
            a call to url1.sameFile(url2) will not give the expected results
        **/
        //if (! _s_blnOperatingSystemWindows)
        //    return url1.sameFile(url2);
            
        // -------------------------------
        // --- !!!!! ok! now, what's next?
        
        String strFile1 = url1.getFile();
        String strFile2 = url2.getFile();
        
        if (strFile1==null || strFile2==null)
            MySystem.s_printOutExit(strWhere, "nil strFile[1-2]");
            

        // ------------------------
        // BEGIN BUG FIXES IN LINUX

        if (strFile1.trim().length() < 1) // under linux: if url not of a file (eg: http), length (?may be?) == 0!
        {
            if (strFile2.trim().length() < 1)
            {
                // BUG: should be active page
                MySystem.s_printOutExit(strWhere, "strFile1.trim().length()<1 & strFile2.trim().length()<1");
                 
            }

            return false;
        }

        // END BUG FIXES IN LINUX
        // ----------------------
        
        if (strFile1.trim().length()<1 || strFile2.trim().length()<1)
        {
            MySystem.s_printOutExit(strWhere, "wrong strFile[1-2].trim().length()");
            
        }
        
         if (strFile1.length() != strFile2.length())
            return false;
            
        // same length!
        
        if (strFile1.compareTo(strFile2) == 0)
            return true;
        
        /**
            because of bug in jdk1.2.2 (not in jdk1.3b, in comments:
            if (strFile1.length() != strFile2.length())
            return false;
            
        // same length!
        
        if (strFile1.equalsIgnoreCase(strFile2))
            return true;
            
        **/
        
        // ... REPLACED BY
        
        /**if (strFile2.startsWith(strFile1))
        {
            return true;
        }**/
        
            
        // same length and not equals (ignoring case)
        
        String strReplaced1 = _s_getStringReplaced(strFile1);
        String strReplaced2 = _s_getStringReplaced(strFile2);
        
        return (strReplaced1.compareTo(strReplaced2) == 0);
        
    }
    
    // --------------
    // PRIVATE STATIC
    /**
        returning a copy of the string arg, replacing all slashes or/and backshlashes occurences by '_'
        that the arg may contain
    **/
    
    private static String _s_getStringReplaced(String str)
    {
        String strShrinked = new String(str);
        
        /**String replace(char oldChar,
                      char newChar)
        **/
        strShrinked = strShrinked.replace('/', '_');
        strShrinked = strShrinked.replace('\\', '_');
        return strShrinked;
    }
}


