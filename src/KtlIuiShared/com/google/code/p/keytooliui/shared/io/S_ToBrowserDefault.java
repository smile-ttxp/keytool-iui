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
 
package com.google.code.p.keytooliui.shared.io;

/**
 Example:
    S_ToBrowserDefault.s_displayURL(frmOwner, strTitleAppli, "http://www.javasoft.com")
    S_ToBrowserDefault.s_displayURL(frmOwner, strTitleAppli, "mailto:someone@somewhere.com")
    S_ToBrowserDefault.s_displayURL(frmOwner, strTitleAppli, "file://C:\mystuff\foo.html")
    
    
    for info, browsers:
    . MSIE
    . Netscape
    . Netscape (compatible)
    . Googlebot
    . Opera
    . FAST-Webcrawler
    . Gulliver
    . Teleport Pro
    . Slurp
    . Link Walker
    . Space Bison
    . Xenu's Link Sleath 1.1c
    . MSIE+5.01
    . WWWC
    . Scooter-W3-1.0
    . Openfind data gatherer, Openbot
    . MSIE+5.5
    
 **/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;



final public class S_ToBrowserDefault
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.S_ToBrowserDefault.";
    
    // -------------
    // STATIC PUBLIC
    
    static public boolean s_displayURL(Component cmpFrameOwner, String strTitleAppli, String strUrlOri)
    {    
        String strMethod = _f_s_strClass + "s_displayURL(cmpFrameOwner, strTitleAppli, strUrlOri)";
        
        if (strUrlOri == null)
        {
            MySystem.s_printOutError(strMethod, "nil strUrlOri");
            return false;
        }
        
        // --
        
        //check for malformed URL
        
        java.net.URL url = null;
        
        try
        {
            url = new java.net.URL(strUrlOri);
        }
        
        catch(java.net.MalformedURLException excMalformedURL)
        {
            excMalformedURL.printStackTrace();
            MySystem.s_printOutError(strMethod, "excMalformedURL caught, strUrlOri=" + strUrlOri);
            return false;
        }
        
        MySystem.s_printOutTrace(strMethod, "url.toString()=" +  url.toString());
        
        // --
        
        String strUrlNew = url.toString(); // strUrlOri;
        
        
        if (strUrlOri.toLowerCase().startsWith("http") || strUrlOri.toLowerCase().startsWith("ftp"))
        {
            // creating a temp file that redirects to remote URL
            strUrlNew = com.google.code.p.keytooliui.shared.io.out.S_GenAsciiHtmlRemoteURL.s_doJob(strUrlNew);
            
            if (strUrlNew == null)
            {
                MySystem.s_printOutError(strMethod, "nil strUrlNew");
                return false;
            }
            
        }
        
        /**
         BEGIN MODIF, march 21, 2002
         coz bug under WindowsNT (note appearing with Windows2000)
         
         "Echec du raccourci" (in french)
         
        else if (strUrlOri.toLowerCase().startsWith("file"))
        {
            //!!! problems under windows! if spaces inside path to appli!
            MySystem.s_printOutTrace(strMethod, "! strUrlOri.toLowerCase().startsWith(\"http\"), strUrlOri=" + strUrlOri);
        
            if (strUrlOri.indexOf(' ') != -1)
            {
                MySystem.s_printOutWarning(strMethod, "strUrlOri.indexOf(' ') != -1");
                
                // creating a temp file that redirects to remote URL
                strUrlNew = com.google.code.p.keytooliui.shared.io.out.S_GenAsciiHtmlRemoteURL.s_doJob(strUrlOri);
                
                if (strUrlNew == null)
                {
                    MySystem.s_printOutError(strMethod, "nil strUrlNew");
                    return false;
                }
            }
            
        }
        **/
        
        else if (strUrlOri.toLowerCase().startsWith("file"))
        {   
            // creating a temp file that redirects to remote URL
            strUrlNew = com.google.code.p.keytooliui.shared.io.out.S_GenAsciiHtmlRemoteURL.s_doJob(strUrlNew);
                
            if (strUrlNew == null)
            {
                MySystem.s_printOutError(strMethod, "nil strUrlNew");
                return false;
            }
        }
        
        // END MODIF, march 21, 2002
        
        
        // -- END TEMPO

        MySystem.s_printOutTrace(strMethod, "strUrlNew=" + strUrlNew);
        
        if (S_OperatingSystem.s_isWindows())
        {
            if (! _S_ToBrowserW_._s_displayURL_(strUrlNew))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
        }
        
        
        
        else if (S_OperatingSystem.s_isMac())
        {
            if (! _S_ToBrowserM_._s_displayURL_(strUrlNew))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
        }
        
        else if (S_OperatingSystem.s_isULinux())
        {
            if (! _S_ToBrowserULinux_._s_displayURL_(cmpFrameOwner, strTitleAppli, strUrlNew))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
        }
        
        
        
        else if (S_OperatingSystem.s_isUSun())
        {
            if (! _S_ToBrowserUSun_._s_displayURL_(cmpFrameOwner, strTitleAppli, strUrlNew))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
        }
        
        
        
        else if (S_OperatingSystem.s_isUnix())
        {
            if (! _S_ToBrowserUOther_._s_displayURL_(cmpFrameOwner, strTitleAppli, strUrlNew))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
        }
        
        else
        {
            MySystem.s_printOutError(strMethod, "unknown O.S.");
            return false;
        }
         
        // ending
        return true;
     }
     
     // -------
     // PRIVATE
     
     // trick to avoid instanciation
     private S_ToBrowserDefault() {}
 }
