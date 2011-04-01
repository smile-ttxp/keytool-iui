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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PViewerFileTextRtfPreviewAbs
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;

import java.awt.event.*;
import java.awt.*;
import java.net.*;

abstract public class PViewerFileTextRtfAbs extends PViewerFileTextAbs 
{   
    // ------
    // PUBLIC

    public boolean doFileOpen(URL url)
    {
        String strMethod = "doFileOpen(url)";
        
        if (! super._doFileOpen_(url))
            return false;
        
        if (super._nsl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._nsl_");
            return false;
        }
        
        if (! ((SPViewerFileTextRtfAbs) super._nsl_).doFileOpen(url))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
    
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected PViewerFileTextRtfAbs(
        ActionListener actListenerParent,
        Component cmpFrameOwner, 
        String strTitleApplication)
    {
        super(actListenerParent, cmpFrameOwner, strTitleApplication);
    }
    
}