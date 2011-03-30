/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

import com.google.code.p.keytooliui.shared.lang.*;

import java.io.*;
import java.awt.*;


final public class DViewSourceFileTextSys extends DViewSourceFileTextAbs 
{
    // ------
    // PUBLIC
    
    public boolean show(File fle)
    {
        String strMethod = "show(fle)";
        
        
        String strSource = _loadSource(fle);
        
        if (strSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! super._show_(strSource, fle.getName()))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    
    
    public DViewSourceFileTextSys(Component cmpFrameOwner, String strTitleApplication)
    {
        super(cmpFrameOwner, strTitleApplication);
    }
    
    
    
    // -------
    // PRIVATE
    
    private String _loadSource(File fle)
    {
	    String strMethod = "_loadSource(fle)";
	    
	    String strSourceCode = new String();
	    char[] chrsBuffer = new char[50000];
	    
	    try
	    {		    
		    InputStreamReader isr = new InputStreamReader(new FileInputStream(fle));
		   
		    int intNch;
		    
		    while ((intNch = isr.read(chrsBuffer, 0, chrsBuffer.length)) != -1)
		    {
		        strSourceCode += new String(chrsBuffer, 0, intNch);
		    }
		    
		    isr.close();
		    isr = null;
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
		    MySystem.s_printOutWarning(this, strMethod, "exc caught, failed to open file:" + fle.getAbsolutePath());
		    //return null;
		    
		    return new String("failed to open file \n\n" + fle.getAbsolutePath());
	    }
	    
	    return strSourceCode;
    }
}