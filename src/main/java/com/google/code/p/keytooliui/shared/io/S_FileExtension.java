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


package com.google.code.p.keytooliui.shared.io;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;

public class S_FileExtension
{
    // --------------------
    // PRIVATE STATIC FINAL

    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.S_FileExtension.";

    // --------------------------
    // PUBLIC STATIC FINAL STRING

    public static final String f_s_strJarDocRcrScript = "txt"; // script.txt
    public static final String f_s_strJarDocJhrHelpset = "hs"; // helpset.hs
    


    public static final String f_s_strProjectReaderDocument = "jar"; // jarred RCReader document
    public static final String f_s_strProjectReaderTemplate = "tpr";
    public static final String f_s_strProjectReaderHelpSun = "jar"; // jarred JavaHelp document
    public static final String f_s_strProjectReaderHelpOracle = "jar"; // jarred OracleHelp document

    public static final String f_s_strProjectBuilderDocument = "xlb";
    public static final String f_s_strProjectBuilderTemplate = "tpb";

    public static final String f_s_strProjectBuilderAsciiDoc = "dtx";
    public static final String f_s_strProjectBuilderAsciiTpl = "ttx";

    public static final String f_s_strJARDocument = "jar";
    public static final String f_s_strAPKDocument = "apk";

    public static final String[] f_s_strsImage = { "gif", "jpg", "jpeg", "png" };
    public static final String[] f_s_strsSndfx =  {"aif", "aiff", "au", "wav"};
    public static final String[] f_s_strsCSS =  { "css" };


    public static final String[] f_s_strsPageTextHTML = { "htm", "html" };
    public static final String[] f_s_strsPageTextRTF = { "rtf" };

    // -------------
    // PUBLIC STATIC

    public static boolean s_isHtml(String strFile)
    {
        String strMethod = _f_s_strClass + "s_isHtml(strFile)";

        if (strFile == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strFile");
        }

        for (int i=0; i<f_s_strsPageTextHTML.length; i++)
        {
            if (strFile.toLowerCase().endsWith(
                f_s_strsPageTextHTML[i].toLowerCase()))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean s_kindOfTemplate(String strFile)
    {
        if (strFile == null)
            return false;

        int intTemplateExtensionLength = f_s_strProjectReaderTemplate.length() + 1;

        if (strFile.length() < intTemplateExtensionLength + 1)
            return false;


	    // --

        String strExtension = com.google.code.p.keytooliui.shared.lang.string.S_StringShared.s_getExtension(strFile);

        if (strExtension == null)
            return false;


		if (strExtension.toLowerCase().compareTo(f_s_strProjectReaderTemplate.toLowerCase()) == 0)
		    return true;

        // ending
        return false;
    }
}