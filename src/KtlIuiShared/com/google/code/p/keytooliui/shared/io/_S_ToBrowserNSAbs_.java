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
    known subclasses:
    . _S_ToBrowserNSMozilla_
    . _S_ToBrowserNSNetscape_

**/

import com.google.code.p.keytooliui.shared.lang.*;


public class _S_ToBrowserNSAbs_ extends _S_ToBrowserAbs_
{

    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserNSAbs_.";
    
    
    //
    
    // The flag for remote (mozilla/netscape should be activated).
    final static private String _f_s_strFlag = "-remote openURL";
    
    // 
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strWarningTitle = null;
    static private String _s_strWarningBody = null;
   
    // ------------------
    // STATIC INITIALIZER
   
    static
    {
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            "._S_ToBrowserNSAbs_" // class name
        ;
        
        final String f_strBundleFileLong = f_strBundleFileShort + ".properties";  
    
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
	        _s_strWarningTitle = rbeResources.getString("warningTitle");     
	        _s_strWarningBody = rbeResources.getString("warningBody"); 
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(_f_s_strClass, "excMissingResource caught");
        }
    }
    
    
    // ----------------
    // STATIC PROTECTED
    
    
    static protected boolean _s_displayURL_(String strBrowserName, String strUrl)
    {    
        String strMethod = _f_s_strClass + "_s_displayURL_(strBrowserName, strUrl)";
        
        if (strBrowserName==null || strUrl==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        
        /**
            modif 17/1/1 bug in linux/rh6.1/kde
            . if sending an email that contains a subject, only the first word of the subject is taken 
                in Mozilla/Netscape Messenger

            ==> ???? TODO: replace all mail-related in the future by JavaMail stuff
                   
        **/


        boolean blnSendMail = strUrl.toLowerCase().startsWith("mailto:");

        if (blnSendMail)
        {
            MySystem.s_printOutWarning(strMethod, "blnSendMail: not done yet, handle spaces in subject (if any)");
        }

                
        
        String[] strsCommand = new String[] 
            { 
                strBrowserName,
                _f_s_strFlag + "(" + strUrl + ")"
            };
        
        Process prc = _S_ToBrowserAbs_._s_exec_(strsCommand);
        
        if (prc == null)
        {
            MySystem.s_printOutError(strMethod, "nil prc");
            return false;
        }  
        
        
                
        try
        {
            int intExitValue = prc.waitFor();
	        MySystem.s_printOutTrace(strMethod, "intExitValue=" + intExitValue);

            if (intExitValue == 0) // means Netscape or Mozilla was already open
            {
                /*if (! _S_ToBrowserAbs_._s_exitValue_(prc))
		        {
                    MySystem.s_printOutError(strMethod, "failed");
                    return false;
                }	
		*/
            }
            
            else // means Netscape or Mozilla was NOT already open
            {
                /**
                        modif 17/1/1 bug in linux/rh6.1/kde
                        . if Mozilla/Netscape not already started & trying to send a mail,
                        then cannot open up Mozilla/Netscape even if intExitValue == 0

                        ==> open up a warning_confirm dialog
                **/

                if (blnSendMail)
                {
                    if (! com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showWarningConfirmDialog((java.awt.Component) null, _s_strWarningTitle, _s_strWarningBody))
                    {
		                MySystem.s_printOutTrace(strMethod, "action cancelled by user, strUrl=" + strUrl);
                        return true;
                    }
                    
                    strsCommand = new String[] 
                    { 
                        strBrowserName,
		                _f_s_strFlag + "(" + strUrl + ")"
	                };
                    
                    prc = _S_ToBrowserAbs_._s_exec_(strsCommand);
        
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
                   
                    /*if (! _S_ToBrowserAbs_._s_exitValue_(prc))
		            {
                        MySystem.s_printOutError(strMethod, "failed");
                        return false;
                    }*/	
                }

                else // should be http, ftp, ...
                {
                    strsCommand = new String[] 
                    { 
                        strBrowserName,
			            strUrl,
			        };
			            
			        prc = _S_ToBrowserAbs_._s_exec_(strsCommand);
        
                    if (prc == null)
                    {
                        MySystem.s_printOutError(strMethod, "nil prc");
                        return false;
                    }  

                    // IMPORTANT: NEITHER CALL PROCESS.WAITFOR NOR PROCESS.EXITVALUE!!!!!!!!!!!!!!!!!!!!
                    // else the appli hangs up while the default browser is activated!
                    
                } 

		                            
            }
        }                
                
        catch(InterruptedException excInterrupted)                
        {
            // BEGIN modif january 17, 2002
            //excInterrupted.printStackTrace();
            //MySystem.s_printOutError(strMethod, "excInterrupted caught, strUrl=" + strUrl); 
            //return false; 
            
            MySystem.s_printOutTrace(strMethod, "excInterrupted caught, strUrl=" + strUrl + ", ignoring"); 
            return true; 
            
            // END modif january 17, 2002
        }
            
    
        // ending
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    
	// Preventing to instantiate.
	protected _S_ToBrowserNSAbs_() {}
}
