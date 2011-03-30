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

/*

    IMPORTANT: don't launch mozilla as remote, else appli hangs up! while mozilla is activated.
       moreover mozilla will not be able to launch the HTML page!


*/

import com.google.code.p.keytooliui.shared.lang.*;


public class _S_ToBrowserNSMozilla_ extends _S_ToBrowserNSAbs_
{
    // ---------------------------
    // FINAL STATIC PUBLIC STRING
    
    final static protected String _f_s_strName_ = "mozilla";
    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserNSMozilla_.";
    
    
    // ----------------
    // STATIC PROTECTED
    
    
    static protected boolean _s_displayURL_(String strUrl)
    {    
        String strMethod = _f_s_strClass + "_s_displayURL_(strUrl)";
        
        if (! _S_ToBrowserAbs_._s_displayURLDirect_(_f_s_strName_, strUrl))
        {
            MySystem.s_printOutError(strMethod, "failed, strUrl=" + strUrl);
            return false;
        }
            
    
        // ending
        return true;
    }
    
    
    // -------
    // PRIVATE
    
    
	// Preventing to instantiate.
	private _S_ToBrowserNSMozilla_() {}
}
