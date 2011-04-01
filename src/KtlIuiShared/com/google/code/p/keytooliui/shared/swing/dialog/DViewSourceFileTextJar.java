/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

final public class DViewSourceFileTextJar extends DViewSourceFileTextAbs 
{    
    // ------
    // PUBLIC
    
    public boolean show(String strPathAbsoluteJar, String strPathRelative)
    {
        String strMethod = "show(strPathAbsoluteJar, strPathRelative)";

        String strSource = _loadSource(strPathAbsoluteJar, strPathRelative);
        
        if (strSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! super._show_(strSource, /*strPathAbsoluteJar + com.google.code.p.keytooliui.shared.io.FileJar.f_s_strFileSeparatorMain +*/ strPathRelative))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        

        return true;
    }
    
    public DViewSourceFileTextJar(Component cmpFrameOwner, String strTitleApplication)
    {
        super(cmpFrameOwner, strTitleApplication);
    }
    
    
    // -------
    // PRIVATE
    
    
    private String _loadSource(String strPathAbsoluteJar, String strPathRelative)
    {
	    String strMethod = "_loadSource(strPathAbsoluteJar, strPathRelative)";
	    
	    byte[] byts = com.google.code.p.keytooliui.shared.io.FileJar.s_getFileResource(strPathAbsoluteJar, strPathRelative);
	    
	    if (byts == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil byts");
	        return null;
	    }
	    
	    return new String(byts);
    }
}