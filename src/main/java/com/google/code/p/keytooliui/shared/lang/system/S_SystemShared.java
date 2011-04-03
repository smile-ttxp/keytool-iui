/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.lang.system;

import java.io.File;

import com.google.code.p.keytooliui.shared.lang.string.S_StringShared;


public class S_SystemShared
{
    // -------------------
    // FINAL STATIC PUBLIC 
    
    final static public String f_s_strPrefixFolderJWS = "DM";
    final static public String f_s_strPrefixFileJWS = "RM";
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _s_f_strClass = "com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.";
    
    // -------------
    // STATIC PUBLIC
    
    /**
        converting EG: 
        input = "./foo1/foo2/foo3.txt"
        output = "./DMfoo1/DMfoo2/RMfoo3.txt"
        
        
        usage: com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.s_javaPathToJWSPath(strPathJava)
    **/
    
    static public String s_javaPathToJWSPath(String strPathJava)
    {
        String strMethod = _s_f_strClass + "s_javaPathToJWSPath(strPathJava)";
        
        if (strPathJava == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strPathJava");
            return null;
        }
        
        // --------------
        //-- make changes
        
        String[] strs = S_StringShared.s_getArrayFromStringFileSeparatorJava(strPathJava);
        
        if (strs == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strs");
            return null;
        }
        
        if (strs.length < 1)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "wrong strs length");
            return null;
        }
        
        if (strs.length == 1)
        {
            return new String(S_SystemShared.f_s_strPrefixFileJWS + strPathJava);
        }
        
        // ---------------------------
        // at least one file separator
        String strResult = new String(S_SystemShared.f_s_strPrefixFolderJWS + strs[0]);
        
        if (strs.length == 2)
        {
            strResult += "/";
            strResult += S_SystemShared.f_s_strPrefixFileJWS;
            strResult += strs[1];
            return strResult;
        }
        
        // more than 2 args
        for (int i=1; i<strs.length-1; i++)
        {
            strResult += "/";
            strResult += S_SystemShared.f_s_strPrefixFolderJWS;
            strResult += strs[i];
        }
        
        strResult += "/";
        strResult += S_SystemShared.f_s_strPrefixFileJWS;
        strResult += strs[strs.length-1];
        
        return strResult;
    }
    
    /**
        converting EG: 
        input = "./foo1/foo2"
        output = ".\foo1\foo2"
    **/
    
    static public String s_javaPathToSystemPath(String strPathJava)
    {
        String strMethod = _s_f_strClass + "s_javaPathToSystemPath(strPathJava)";
        
        if (strPathJava == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strPathJava");
            return null;
        }
        
        String strFileSeparator = File.separator;
        
        if (strFileSeparator == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strFileSeparator");
            return null;
        }
        
        if (strFileSeparator.equalsIgnoreCase("/"))
            return new String(strPathJava); // nothing to do!
            
        // --------------
        //-- make changes
        
        String[] strs = S_StringShared.s_getArrayFromStringFileSeparatorJava(strPathJava);
        
        if (strs == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strs");
            return null;
        }
        
        if (strs.length < 1)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "wrong strs length");
            return null;
        }
        
        if (strs.length == 1) // nothing todo
        {
            return new String(strPathJava);
        }
        
        // ---------------------------
        // at least one file separator
        String strResult = new String(strs[0]);
        
        for (int i=1; i<strs.length; i++)
        {
            strResult += strFileSeparator;
            strResult += strs[i];
        }
        
        return strResult;
    }
    
    
    /**
        converting EG: 
        input = ".\foo1\foo2"
        output = "./foo1/foo2"
    **/
    
    static public String s_systemPathToJavaPath(String strPathSystem)
    {
        String strMethod = _s_f_strClass + "s_systemPathToJavaPath(strPathSystem)";
        
        if (strPathSystem == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strPathSystem");
            return null;
        }
        
        String strFileSeparator = File.separator;
        
        if (strFileSeparator == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strFileSeparator");
            return null;
        }
        
        if (strFileSeparator.equalsIgnoreCase("/"))
            return new String(strPathSystem); // nothing to do!
            
        // --------------
        //-- make changes
        
        String[] strs = S_StringShared.s_getArrayFromStringFileSeparatorSystem(strPathSystem);
        
        if (strs == null)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "nil strs");
            return null;
        }
        
        if (strs.length < 1)
        {
            com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutError(strMethod, "wrong strs length");
            return null;
        }
        
        if (strs.length == 1) // nothing todo
        {
            return new String(strPathSystem);
        }
        
        // ---------------------------
        // at least one file separator
        String strResult = new String(strs[0]);
        
        for (int i=1; i<strs.length; i++)
        {
            strResult += "/";
            strResult += strs[i];
        }
        
        return strResult;
    }
}