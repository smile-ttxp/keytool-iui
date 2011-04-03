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

import com.google.code.p.keytooliui.shared.lang.*;


/**

 **/

final public class S_OperatingSystem
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.S_OperatingSystem.";
    
    // important: use lowercase
    final static private String _f_s_strOsWindows = "windows";
    final static private String _f_s_strOsLinux = "linux";
    final static private String _f_s_strOsMac = "mac";
    
    // UNIX-SUN
    final static private String _f_s_strOsUnixSunOS = "sunos"; // SunOS
    
    // UNIX-IBM
    final static private String _f_s_strOsUnixAix = "aix"; // AIX
    
    // UNIX-DEC ?ALPHA?
    
    // UNIX-HP
    final static private String _f_s_strOsUnixHp_ux = "hp-ux"; // HP-UX
    
    // UNIX-SGI
    final static private String _f_s_strOsUnixIrix = "irix"; // IRIX
    
    
    // UNIX-OTHERS
    final static private String[] _f_s_strsOsUnixOther = 
    {
        "osf1", // OSF!
        "bsd"   // BSD
    };
    
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strOsName = null;

    // -------------
    // STATIC PUBLIC
    
    static public boolean s_isWinPro()
    {
        String strMethod = _f_s_strClass + "s_isWinPro()";
        
        if (! s_isWindows())
            return false;
            
        if (s_isWinPub())
            return false;
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().endsWith("nt") ||
            _s_strOsName.toLowerCase().endsWith("2000") ||
            _s_strOsName.toLowerCase().endsWith("xp"))
            return true;
        
        MySystem.s_printOutWarning(strMethod, "unknown OS, assuming WinPro, _s_strOsName=" + _s_strOsName);
            
        return true;
    }
    
                
    static public boolean s_isWinPub()
    {
        String strMethod = _f_s_strClass + "s_isWinPub()";
        
        if (! s_isWindows())
            return false;
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().endsWith("95") ||
            _s_strOsName.toLowerCase().endsWith("98") ||
            _s_strOsName.toLowerCase().endsWith("me"))
            return true;
            
            
        return false;
    }
    
    static public boolean s_isWindows()
    {
        String strMethod = _f_s_strClass + "s_isWindows()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().startsWith(_f_s_strOsWindows))
            return true;
            
        return false;
    }
    
    static public boolean s_isULinux()
    {
        String strMethod = _f_s_strClass + "s_isULinux()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().startsWith(_f_s_strOsLinux))
            return true;
            
        return false;
    }
    
    static public boolean s_isMac()
    {
        String strMethod = _f_s_strClass + "s_isMac()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().startsWith(_f_s_strOsMac))
            return true;
            
        return false;
    }
    
    static public boolean s_isUIbm()
    {
        String strMethod = _f_s_strClass + "s_isUIbm()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().startsWith(_f_s_strOsUnixAix))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _s_strOsName);
            return true;
        }
        
        return false;
    }
    
    static public boolean s_isUHp()
    {
        String strMethod = _f_s_strClass + "s_isUHp()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().startsWith(_f_s_strOsUnixHp_ux))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _s_strOsName);
            return true;
        }
        
        return false;
    }
    
    static public boolean s_isUSgi()
    {
        String strMethod = _f_s_strClass + "s_isUSgi()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().startsWith(_f_s_strOsUnixIrix))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _s_strOsName);
            return true;
        }
        
        return false;
    }
    
    static public boolean s_isUOther()
    {
        String strMethod = _f_s_strClass + "s_isUOther()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        for (int i=0; i<_f_s_strsOsUnixOther.length; i++)
        {
            if (_s_strOsName.toLowerCase().startsWith(_f_s_strsOsUnixOther[i]))
            {
                MySystem.s_printOutTrace(strMethod, "got:" + _s_strOsName);
                return true;
            }
        }
        
        return false;
    }
    
    
    static public boolean s_isUSun()
    {
        String strMethod = _f_s_strClass + "s_isUSun()";
        
        if (_s_strOsName == null)
            _s_strOsName = System.getProperty("os.name");
            
        if (_s_strOsName == null)
            MySystem.s_printOutExit(strMethod, "nil _s_strOsName");
            
        if (_s_strOsName.toLowerCase().startsWith(_f_s_strOsUnixSunOS))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _s_strOsName);
            return true;
        }
        
        return false;
    }
    
    /*
        meaning all Unix, including linux
    */
    static public boolean s_isUnix()
    {
        String strMethod = _f_s_strClass + "s_isUnix()";
        
        if (s_isWindows())
            return false;
            
        if (s_isMac())
            return false;
            
        // --
            
        if (s_isULinux())
            return true;  
        
            
        if (s_isUSun())
            return true;
            
        if (s_isUIbm())
            return true;
            
        if (s_isUHp())
            return true;
            
        if (s_isUSgi())
            return true;
            
        if (s_isUOther())
            return true;
            
        // assuming this is Unix
        MySystem.s_printOutTrace(strMethod, "not windows, not mac, not linux, not unixSun, assuming this is UNIX, _s_strOsName=" + _s_strOsName);
            
        return true;
    }
     
     // -------
     // PRIVATE
     
     // trick to avoid instanciation
     private S_OperatingSystem() {}
 }
