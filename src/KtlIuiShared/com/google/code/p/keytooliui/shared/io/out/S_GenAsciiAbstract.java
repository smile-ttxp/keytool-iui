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
   
   known subclasses:
   
   . shared: S_GenAsciiHtmlRemoteURL
   
   . xlb: S_GenAsciiHtmlFrameset
   . xlb: S_GenAsciiFromNode2Abs
   . xlb: S_GenAsciiHtmlFrameRight   
   . xlb: S_GenAsciiJHHelpSet

   
   . xlb: S_GenAsciiBatchSecAbstract
   . xlb: S_GenAsciiJnlpAbs
   . xlb: S_GenAsciiReadmeJnlp
   
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;

abstract public class S_GenAsciiAbstract
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.out.S_GenAsciiAbstract.";
    
    // ----------------
    // STATIC PROTECTED
 
    static protected File _s_getFile_(File fleParent, String strSource)
    {
        String strMethod = _f_s_strClass + "_s_getFile_(fleParent, strSource)";
        
        if (fleParent == null)
        {
            MySystem.s_printOutError(strMethod, "nil fleParent");
            return null;
        }
        
        if (strSource == null)
        {
            MySystem.s_printOutError(strMethod, "nil strSource");
            return null;
        }
        
        if (! fleParent.exists())
        {
            MySystem.s_printOutError(strMethod, "! fleParent.exists(), fleParent.getAbsolutePath()=" + fleParent.getAbsolutePath());
            return null;
        }
        
        if (! fleParent.isDirectory())
        {
            MySystem.s_printOutError(strMethod, "! fleParent.isDirectory(), fleParent.getAbsolutePath()=" + fleParent.getAbsolutePath());
            return null;
        }
        
        if (! fleParent.canWrite())
        {
            MySystem.s_printOutError(strMethod, "! fleParent.canWrite(), fleParent.getAbsolutePath()=" + fleParent.getAbsolutePath());
            return null;
        }
        
        File fleNew = new File(fleParent, strSource);
        
        if (fleNew.exists())
        {
            MySystem.s_printOutError(strMethod, "fleNew.exists()");
            return null;
        }
        
        // ending
        return fleNew;
    }
    
    static protected boolean _s_writeContent_(File fle, String str)
    {
        String strMethod = _f_s_strClass + "_s_writeContent_(fle, str)";
        
        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil fle");
            return false;
        }
        
        if (str == null)
        {
            MySystem.s_printOutError(strMethod, "nil str");
            return false;
        }
        
        try
        {
            FileOutputStream fos = new FileOutputStream(fle);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(str);
           
            fos.flush(); //!!
            osw.close();
        }
        
        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            return false;
        }
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    // trick to avoid instantiation
    protected S_GenAsciiAbstract() {}
}