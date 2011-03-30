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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PViewerFileTextHtmlPreviewAbs
    . PViewerFileTextHtmlViewSys ==> secondary window
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;

import javax.swing.event.*;
import javax.swing.text.html.*;

import java.awt.event.*;
import java.awt.*;
import java.net.*;

abstract public class PViewerFileTextHtmlAbs extends PViewerFileTextAbs implements
    HyperlinkListener
{   
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void hyperlinkUpdate(HyperlinkEvent evtHyperlink);
    
	
    // ---------
    // PROTECTED

    protected boolean _doFileOpen_(URL url, StyleSheet sst)
    {
        String strMethod = "_doFileOpen_(url, sst)";
        
        if (! super._doFileOpen_(url))
            return false;
        
        if (super._nsl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._nsl_");
            return false;
        }
        
        if (! ((SPViewerFileTextHtmlAbs) super._nsl_).doFileOpen(url, sst))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
    
        
        // ending
        return true;
    }
    
    protected PViewerFileTextHtmlAbs(
        ActionListener actListenerParent,
        Component cmpFrameOwner, 
        String strTitleApplication)
    {
        super(actListenerParent, cmpFrameOwner, strTitleApplication);
    }
    
}