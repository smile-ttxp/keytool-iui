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
    W: windows
**/

import com.google.code.p.keytooliui.shared.lang.*;

public class _S_ToBrowserW_ extends _S_ToBrowserAbs_
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserW_.";
    
    // The default system browser under windows.
    final static private String _f_s_strAction = "rundll32";
    // The flag to display a url.
    final static private String _f_s_strFlag = "url.dll,FileProtocolHandler";
    
    
    
    // ----------------
    // STATIC PROTECTED
    
    static protected boolean _s_displayURL_(String strUrl)
    {    
        String strMethod = _f_s_strClass + "_s_displayURL_(strUrl)";
        
        if (strUrl == null)
        {
            MySystem.s_printOutError(strMethod, "nil strUrl");
            return false;
        }
        
        String[] strsCommand = new String[] 
                { 
                    _f_s_strAction,
					_f_s_strFlag ,
					strUrl
			    };
        
        Process prc = _S_ToBrowserAbs_._s_exec_(strsCommand);
        
        if (prc == null)
        {
            MySystem.s_printOutError(strMethod, "nil prc");
            return false;
        }
        
						
        
        // added  oct 4, 2001
        // excerpt from "mtj", in "Question of the Week #68"
        // - Query the resulting process' exit code after it has finished. If you omit this, you will leave hanging Zombie-processes on various operating systems. 
		// MORE: http://developer.java.sun.com/developer/qow/archive/68/
			
		if (! _S_ToBrowserAbs_._s_waitFor_(prc))
		{
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }
		
		if (! _S_ToBrowserAbs_._s_exitValue_(prc))
		{
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }		
    
        // ending
        return true;
    }
    
    
    // -------
    // PRIVATE
    
    
	// Preventing to instantiate.
	private _S_ToBrowserW_() { }
}