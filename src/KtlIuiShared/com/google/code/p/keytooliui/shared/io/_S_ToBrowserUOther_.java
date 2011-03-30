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

    U: unix, other than linux and sun


    !!!! SAME CODE AS the one in _S_ToBrowserULinux_ !!!!


  launching first web navigator found from the following list:
  . mozilla
  . netscape
  . konqueror
  . lynx 
  
    
*/



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.awt.*;

public class _S_ToBrowserUOther_ extends _S_ToBrowserUAbs_
{
     // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserUOther_.";
    
    // ----------------
    // STATIC PROTECTED
    
    static protected boolean _s_displayURL_(Component cmpFrameOwner, String strTitleAppli, String strUrl)
    {    
        String strMethod = _f_s_strClass + "_s_displayURL_(cmpFrameOwner, strTitleAppli, strUrl)";
        
        // ----------
        // 1) mozilla
        

        if (_S_ToBrowserUAbs_._s_gotCommand_(_S_ToBrowserNSMozilla_._f_s_strName_))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _S_ToBrowserNSMozilla_._f_s_strName_);

            if (! _S_ToBrowserNSMozilla_._s_displayURL_(strUrl))
            {
                MySystem.s_printOutError(strMethod, "failed, strUrl=" + strUrl);
                return false;
            }
            
            return true;
        }
        

        
        // -----------
        // 2) netscape
        
        if (_S_ToBrowserUAbs_._s_gotCommand_(_S_ToBrowserNSNetscape_._f_s_strName_))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _S_ToBrowserNSNetscape_._f_s_strName_);

            if (! _S_ToBrowserNSNetscape_._s_displayURL_(strUrl))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
                        
            return true;
        }
        
        
        // ------------
        // 3) konqueror
        
        if (_S_ToBrowserUAbs_._s_gotCommand_(_S_ToBrowserKonqueror_._f_s_strName_))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _S_ToBrowserKonqueror_._f_s_strName_);

            if (! _S_ToBrowserKonqueror_._s_displayURL_(strUrl))
            {
                MySystem.s_printOutError(strMethod, "failed");
                return false;
            }
            
            return true;
        }
        
        // -------
        // 4) lynx
        
        if (_S_ToBrowserUAbs_._s_gotCommand_(_S_ToBrowserLynx_._f_s_strNameXterm_))
        {
            MySystem.s_printOutTrace(strMethod, "got:" + _S_ToBrowserLynx_._f_s_strNameXterm_);

            if (_S_ToBrowserUAbs_._s_gotCommand_(_S_ToBrowserLynx_._f_s_strNameLynx_))
            {
                MySystem.s_printOutTrace(strMethod, "got:" + _S_ToBrowserLynx_._f_s_strNameLynx_);

                if (! _S_ToBrowserLynx_._s_displayURL_(strUrl))
                {
                    MySystem.s_printOutError(strMethod, "failed");
                    return false;
                }
                
                return true;
            }
        }
        
            
        String strBody = _S_ToBrowserUAbs_._s_strWarningBody_;
        strBody += "\n";
        strBody += ". " + _S_ToBrowserNSMozilla_._f_s_strName_ + "\n";
        strBody += ". " + _S_ToBrowserNSNetscape_._f_s_strName_ + "\n";
        strBody += ". " + _S_ToBrowserKonqueror_._f_s_strName_ + "\n";
        strBody += ". " + _S_ToBrowserLynx_._f_s_strNameLynx_ + "\n";
        
        OPAbstract.s_showDialogWarning(cmpFrameOwner, strTitleAppli, strBody);

        // ending
        return true;
    }
    
    // -------
    // PRIVATE
    
	// Preventing to instantiate.
	private _S_ToBrowserUOther_() { }
	
}