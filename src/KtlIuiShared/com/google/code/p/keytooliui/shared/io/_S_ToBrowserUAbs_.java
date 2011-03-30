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
    U: unix
    
    
    known subclasses:
    . _S_ToBrowserULinux_
    . _S_ToBrowserUSun_
    . _S_ToBrowserUOther_
    
    
*/



import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;

abstract public class _S_ToBrowserUAbs_
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserUAbs_.";
    
    
    // ----------------
    // STATIC PROTECTED
    
    static protected String _s_strWarningBody_ = null;
    
   
    // ------------------
    // STATIC INITIALIZER
   
    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            "._S_ToBrowserUAbs_" // class name
            ;
            
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
	        _s_strWarningBody_ = rbeResources.getString("warningBody"); 
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(_f_s_strClass, "excMissingResource caught");
        }
    }
    
    
    // ----------------
    // STATIC PROTECTED
    
    
	// Preventing to instantiate.
	//private _S_ToBrowserUAbs_() { }
	
	static protected boolean _s_gotCommand_(String strCommandName)
	{
	    String strMethod = _f_s_strClass + "_s_gotCommand_(strCommandName)";
	    
	    if (strCommandName == null)
	        MySystem.s_printOutExit(strMethod, "nil strCommandName");
	        
	    String strCmd = "which " + strCommandName;
	        
	    
	    try
	    {
		    Process p = Runtime.getRuntime().exec(strCmd);
		    
		    int intRetVal = p.waitFor();
			
			if (intRetVal == 0)
			{
			    MySystem.s_printOutTrace(strMethod, "intRetVal == 0, strCmd=" + strCmd);
				return true;
			}
			
			MySystem.s_printOutWarning(strMethod, "intRetVal != 0, intRetVal=" + intRetVal + ", strCmd=" + strCmd);
			return false;
		} 
		
		catch (IOException excIO)
		{
		    // BEGIN modif january 17, 2002
		    excIO.printStackTrace();
		    MySystem.s_printOutWarning(strMethod, "excIO caught"+ ", strCmd=" + strCmd);
		    return false;
		    // END modif january 17, 2002
		} 
		
		catch (InterruptedException excInterrupted)
		{
		    // BEGIN modif january 17, 2002
		    MySystem.s_printOutWarning(strMethod, "excInterrupted caught"+ ", strCmd=" + strCmd);
		    return false;
		    // END modif january 17, 2002
		}
		
		catch (Exception exc)
		{
		    // BEGIN modif january 17, 2002
		    MySystem.s_printOutWarning(strMethod, "exc caught"+ ", strCmd=" + strCmd);
		    return false;
		    // END modif january 17, 2002
		}
	}
}