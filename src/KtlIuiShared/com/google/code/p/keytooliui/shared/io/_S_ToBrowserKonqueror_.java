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
  a LINUX-KDE web navigator
**/

import com.google.code.p.keytooliui.shared.lang.*;


public class _S_ToBrowserKonqueror_ extends _S_ToBrowserAbs_
{

    // ---------------------------
    // FINAL STATIC PUBLIC STRING
    
    final static protected String _f_s_strName_ = "konqueror";
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserKonqueror_.";
    

    
    // ----------------
    // STATIC PROTECTED
    
    
    static protected boolean _s_displayURL_(String strUrl)
    {    
        String strMethod = _f_s_strClass + "_s_displayURL_(strUrl)";
        
        if (strUrl == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        // -----------

        if (! _S_ToBrowserAbs_._s_displayURLDirect_(_f_s_strName_, strUrl))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }

        if (true) return true;
        
        // -----------
        
        String[] strsCommand = new String[] 
        { 
            _f_s_strName_,
	    strUrl
        };
			            
	Process prc = _S_ToBrowserAbs_._s_exec_(strsCommand);
        
        if (prc == null)
        {
            MySystem.s_printOutError(strMethod, "nil prc");
            return false;
        }  
                    
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
	private _S_ToBrowserKonqueror_() {}
}
