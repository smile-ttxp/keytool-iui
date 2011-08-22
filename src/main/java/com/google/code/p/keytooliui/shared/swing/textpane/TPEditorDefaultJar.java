/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.textpane;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

public final class TPEditorDefaultJar extends TPEditorAbstract 
{    
   
    
    // ------
    // PUBLIC
    
    /**
    
        1) fjr.deleteFile(strPathRelative)
        2) fjr.addResource(strPathRelative, byts)
        3) fjr.write();
    
    **/
    
    public boolean writeTo(FileJar fjr, String strPathRelative)
        throws Exception
    {
        String f_strMethod = "writeTo(fjr, strPathRelative)";
        
        if (fjr==null || strPathRelative==null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil arg");
            return false;
        }
        
        // ----
        
        
        byte[] byts = fjr.getResource(strPathRelative);
        
        if (byts != null)
        {
            if (! fjr.removeResource(strPathRelative))
            {
                MySystem.s_printOutError(this, f_strMethod, "failed");
                return false;
            }
        }
        
        /*if (fjr.containsKey(strPathRelative)) // should contain key
        {
            //if (! fjr.deleteFile(strPathRelative))
            if (! fjr.removeResource(strPathRelative))
            {
                MySystem.s_printOutError(this, f_strMethod, "failed");
                return false;
            }
        }*/
        
        String strBuffer = this.getText();
        
        
        if (strBuffer == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil strBuffer");
            return false;
        }
        
        byte[] bytsBuffer = strBuffer.getBytes();
        
        if (bytsBuffer == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil bytsBuffer");
            return false;
        }
        
        if (! fjr.addResource(strPathRelative, bytsBuffer))
        {
            //if (! fjr.deleteFile(strPathRelative))
            //{
                MySystem.s_printOutError(this, f_strMethod, "failed");
                return false;
            //}
        }
        
        if (! fjr.write())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
  
        return true;
    }
    
    public boolean doFileOpen(byte[] bytsBuffer)
        throws Exception
    {
        String f_strMethod = "doFileOpen(bytsBuffer)";
        
        String strSource = _loadSource(bytsBuffer);
        
        if (strSource == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil strSource");            
            return false;
        }
        
        this.setText(strSource);
        setEnabled(true);
        
        return true;
    }

    
    public boolean doFileNew()
        throws Exception
    {
        String f_strMethod = "doFileNew()";
        
        MySystem.s_printOutWarning(this, f_strMethod, "IN PROGRESS");
        
        return true;
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
       
        if (! super._init_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
    
        return true;
    }
    
    public void destroy()
    {
    }
    
   
    // -------
    // PRIVATE
    
    private String _loadSource(byte[] bytsBuffer)
    {
	    String f_strMethod = "_loadSource(bytsBuffer)";
	    
	    if (bytsBuffer == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, "nil bytsBuffer");
		    return null;
	    }
	    
	    return new String(bytsBuffer);
    }
    
}