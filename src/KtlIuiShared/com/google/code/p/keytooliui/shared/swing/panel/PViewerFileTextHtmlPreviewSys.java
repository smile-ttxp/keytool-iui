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


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

final public class PViewerFileTextHtmlPreviewSys extends PViewerFileTextHtmlPreviewAbs
{    
    // ------
    // PUBLIC
    
    public boolean doFileOpen(URL url)
    {
        String strMethod = "doFileOpen(url)";
        
        if (url == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil url");
            return false;
        }
        
        
        if (! super._doFileOpen_(url, (javax.swing.text.html.StyleSheet) null))
        {
            setEnabled(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public PViewerFileTextHtmlPreviewSys(
        ActionListener actListenerParent,
        Component cmpFrameOwner,
        String strApplicationTitle)
    {
        super(actListenerParent, cmpFrameOwner, strApplicationTitle);
    
        String strMethod = "PViewerFileTextHtmlPreviewSys(actListenerParent, cmpFrameOwner, strApplicationTitle)";
	    
	    // ----
	    super._nsl_ = new SPViewerFileTextHtmlSys((HyperlinkListener) this);
	    
	    if (! ((SPViewerFileTextAbs) super._nsl_).init())
            MySystem.s_printOutExit(this, strMethod, "failed");
	    
        // ----
        
        super._mbr_ = new MBViewerFileTextHtmlPreviewSys(
            cmpFrameOwner, 
            strApplicationTitle,
            actListenerParent, // catching exit
            (ActionListener) this // catching pring
            );
    }
    
    
    static public void main(String[] args)
    {
        final String f_strWhere = "main(strsArg)";
        
        try
        {
            JFrame frame = new JFrame();
	        frame.getContentPane().setLayout(new BorderLayout());
	        String strApplicationTitle = "PViewerFileTextHtmlPreviewSys as a standalone application";
	        
	        PViewerFileTextHtmlAbs ntp = new PViewerFileTextHtmlPreviewSys((ActionListener) null, frame, strApplicationTitle);
	        
	        if (! ntp.init())
	            MySystem.s_printOutExit(f_strWhere, "failed");
	        
	        frame.getContentPane().add("Center", ntp);
	        
	        com.google.code.p.keytooliui.shared.awt.event.WindowCloser acr = new com.google.code.p.keytooliui.shared.awt.event.WindowCloser();
	        
	        frame.addWindowListener(acr);
	        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
	        frame.pack();
	        
	        int intPreferredW = frame.getPreferredSize().width;
	        int intW = 400;
	        
	        if (intPreferredW < intW)
	            intW = intPreferredW;
	        
	        frame.setSize(intW, 400);
            frame.setVisible(true);
        }
        
        catch (Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "exception caught");
        }
    }
}