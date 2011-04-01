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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a secondary window
    
    "B" for "Button"
    "Sw" for "Secondary Window"
    "Url" for "Uniform Resource Locator"
    "Text" means window displays a page of type "text", and contains a "text search" tool
    "RTF" version 1.0
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;

import javax.swing.*;

import java.net.*;

final public class B2SwUrlTextRTF extends B2SwUrlTextAbs 
{
    // ------
    // PUBLIC
    
    
    
    public B2SwUrlTextRTF()
    {
        super();
    }
    
    // ---------
    // PROTECTED
    
    protected boolean _createWindow_()
    {
        String strMethod = "_createWindow_()";
        
        URL url = super._getUrl_();
        
        if (url == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil url");
            return false;
        }
        
        JFrame frmOwner = B2SwAbs._s_getFrameOwnerHelpSet_(this);
        
        if (frmOwner == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil frmOwner");
            return false;
        }
      
        
        // --
        
        super._frmWindow_ = 
            new FSwUrlTextRTF(
                frmOwner,
                super.getWindowTitle(), 
                super._getImageIconFrameTarget_(),
                url,
                super._colWindowTextSelection_); 
        
        return true;
    }
}