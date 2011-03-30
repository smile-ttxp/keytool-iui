/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
 package com.google.code.p.keytooliui.shared.io.out;

/**
    a static class that generates an temporary html file used to automatically redirect to remote URL
    
    
    <HTML>
    <HEAD>
    <META HTTP-EQUIV="Refresh" CONTENT="1; URL=http[X]">
	<TITLE>RagingCat Project Link To Remote Page</TITLE>
    </HEAD>

    <BODY>
    <P>Loading remote page http[X].<BR>
    In case the page does not load automatically, please click <A HREF="http[X]">here</A> ...
    </BODY>
    </HTML>
    
    
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;

public class S_GenAsciiHtmlRemoteURL extends S_GenAsciiAbstract
{

    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.out.S_GenAsciiHtmlRemoteURL.";

    final static private String _f_s_strMetaBeg = "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"1; URL=";
    final static private String _f_s_strMetaEnd = "\">";
    final static private String _f_s_strTitle = "<TITLE>RagingCat Project Link To Remote Page</TITLE>";
    
    final static private String _f_s_strLine1Beg = "<P>Loading remote page ";
    final static private String _f_s_strLine1End = " .<BR>";
    
    final static private String _f_s_strLine2Beg = "In case the page does not load automatically, please click <A HREF=\"";
    final static private String _f_s_strLine2End = "\">here</A> ...";
    
    // -------------
    // STATIC PUBLIC
    
    /**
        returns absolute path
    **/
    static public String s_doJob(String strUrl)
    {
        String strMethod = _f_s_strClass + "s_doJob(strUrl)";
        
        // 1) get temp file
        
        
        String strPrefix = "rmt"; // rmt meaning remote
        String strSuffix = ".html";
        
        // BEGIN MODIF nov 7, 2001
        // BUG: under windows, if there are any space in appli's path, default browser fails to show the url!
        //      EG: path like this "C:\Program Files\foo
        // creating temp file that should reside in eg Windows: C:\TEMP\
        
        // NEW
        
        File fleNew = null;
        
        try
        {
            fleNew = File.createTempFile(strPrefix, strSuffix);
            fleNew.deleteOnExit();
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            return null;
        }
        
        // MORE: http://developer.java.sun.com/developer/bugParade/bugs/4171239.html
        MySystem.s_printOutFlagDev(strMethod, "EYES WIDE OPEN: fleNew.deleteOnExit(), fleNew.getAbsolutePath()=" + fleNew.getAbsolutePath());
        
        // OLD
        /**
        File fleNew = com.google.code.p.keytooliui.shared.io.S_FileSys.s_createTemp(strPrefix, strSuffix);
            
        if (fleNew == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleNew");
            return null;
        }
        
        **/
        
        // END MODIF nov 7, 2001
        
 
        // 2) generate contents
        String strContent = _s_getStringContent(strUrl);
        
        if (strContent == null)
        {
            MySystem.s_printOutError(strMethod, "nil strContent");
            return null;
        }
        
        // 3) write
        
        if (! S_GenAsciiAbstract._s_writeContent_(fleNew, strContent))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }
        
        MySystem.s_printOutTrace(strMethod, "fleNew.getAbsolutePath()=" + fleNew.getAbsolutePath());
        // ending

        // -----------
        // BEGIN modif, dec 28, 2001, coz bug in Mozilla/Linux RH 7.2

        // OLD
        //return fleNew.getAbsolutePath();
        // NEW
        return "file:" + fleNew.getAbsolutePath();

        // END modif, dec 28, 2001, coz bug in Mozilla/Linux RH 7.2
    }
    
    
    // --------------
    // STATIC PRIVATE
    
    /**
        if any error, returning nil
    **/
    static private String _s_getStringContent(String strUrl)
    {
        String strMethod = _f_s_strClass + "_s_getStringContent(strUrl)";
        
        String strContent = new String();
        
        strContent += "<HTML>"; strContent += "\n";
        strContent += "<HEAD>"; strContent += "\n";
        
        strContent += _f_s_strMetaBeg;
        strContent += strUrl;
        strContent += _f_s_strMetaEnd; strContent += "\n";
        
        strContent += _f_s_strTitle; strContent += "\n";
        // ---
        strContent += "</HEAD>"; strContent += "\n";

        strContent += "<BODY>"; strContent += "\n";
        
        strContent += _f_s_strLine1Beg; 
        strContent += strUrl;
        strContent += _f_s_strLine1End; strContent += "\n";
        
        strContent += _f_s_strLine2Beg;
        strContent += strUrl;
        strContent += _f_s_strLine2End; strContent += "\n";
        
        strContent += "</BODY>"; strContent += "\n";
        strContent += "</HTML>"; strContent += "\n";
    
    
        // ending
        return strContent;
    }
}
