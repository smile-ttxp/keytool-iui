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
    . _S_ToBrowserW_
    . _S_ToBrowserNSAbs_
    . _S_ToBrowserLynx_

**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;

public class _S_ToBrowserAbs_
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserAbs_.";
    
    // ----------------
    // STATIC PROTECTED

    static protected boolean _s_displayURLDirect_(String strBrowserName, String strUrl)
    {    
        String strMethod = _f_s_strClass + "_s_displayURLDirect_(strBrowserName, strUrl)";
        
        if (strBrowserName==null || strUrl==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }

        String[] strsCommand = new String[] 
        { 
            strBrowserName,
	        strUrl,
	    };
			            
        Process prc = _s_exec_(strsCommand);
        
        if (prc == null)
        {
            MySystem.s_printOutError(strMethod, "nil prc, strsCommand=" + strsCommand);
            return false;
        }  

        // IMPORTANT: NEITHER CALL PROCESS.WAITFOR NOR PROCESS.EXITVALUE!!!!!!!!!!!!!!!!!!!!
        // else the appli hangs up while the default browser is activated!
                    
        return true;
    }
        
    
    // added  oct 4, 2001
    // excerpt from "mtj", in "Question of the Week #68"
    // - Query the resulting process' exit code after it has finished. If you omit this, you will leave hanging Zombie-processes on various operating systems. 
	// MORE: http://developer.java.sun.com/developer/qow/archive/68/
	
    static protected boolean _s_waitFor_(Process prc)
    {
        String strMethod = _f_s_strClass + "_s_waitFor_(prc)";
        
        if (prc == null)
        {
            MySystem.s_printOutError(strMethod, "nil prc");
            return false;
        }
				
		try
		{
			int intExitValue = prc.waitFor();
		    MySystem.s_printOutTrace(strMethod, "intExitValue=" + intExitValue);
					
			if (intExitValue != 0)
			{
				MySystem.s_printOutError(strMethod, "intExitValue != 0, intExitValue=" + intExitValue);
				return false;
			}
					
		}
				
		catch (InterruptedException excInterrupted)
		{
		    // BEGIN modif january 17, 2002
			//excInterrupted.printStackTrace();
			//MySystem.s_printOutError(strMethod, "excInterrupted caught");
			//return false;
			MySystem.s_printOutTrace(strMethod, "excInterrupted caught, ignoring");
			return true;
			// END modif january 17, 2002
		}
		
		return true;
    }
    
    static protected boolean _s_exitValue_(Process prc)
    {
        String strMethod = _f_s_strClass + "_s_exitValue_(prc)";
        
        if (prc == null)
        {
            MySystem.s_printOutError(strMethod, "nil prc");
            return false;
        }
        
				
		try
		{
			int intExitValue = prc.exitValue();
		    MySystem.s_printOutTrace(strMethod, "intExitValue=" + intExitValue);
					
			if (intExitValue != 0)
			{
				MySystem.s_printOutError(strMethod, "intExitValue != 0, intExitValue=" + intExitValue);
				return false;
			}
					
		}
				
		catch (IllegalThreadStateException  excIllegalThreadState)
		{
			excIllegalThreadState.printStackTrace();
			MySystem.s_printOutError(strMethod, "excIllegalThreadState caught");
			return false;
		}
		
		return true;
    }
    
    static protected Process _s_exec_(String[] strsCommand)
    {
        String strMethod = _f_s_strClass + "_s_exec_(strsCommand)";
        
        if (strsCommand == null)
        {
            MySystem.s_printOutError(strMethod, "nil strsCommand");
            return null;
        }

        Process prc = null;
        
        try
        {
            prc = Runtime.getRuntime().exec(strsCommand);
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught, strsCommand=" + strsCommand);
            return null;
        }
        
        _s_sleep();
        return prc;
    }
    
    // --------------
    // STATIC PRIVATE
    
    static private void _s_sleep()
    {
        String strMethod = _f_s_strClass + "_s_sleep()";
        
        for (int i=0; i<2; i++)
        {
		    try
		    {
		        // !!!!!!!!
		        Thread.currentThread().sleep(1000);
		    } 
    		
		    catch (InterruptedException excInterrupted)
		    {
		        // BEGIN modif january 17, 2002
		        //excInterrupted.printStackTrace();
		        //MySystem.s_printOutWarning(strMethod, "excInterrupted caught, ignoring");
		        
		        MySystem.s_printOutTrace(strMethod, "excInterrupted caught, ignoring");
		        return;
		        // END modif january 17, 2002
		        
	        }
	    }
    }
    
}
