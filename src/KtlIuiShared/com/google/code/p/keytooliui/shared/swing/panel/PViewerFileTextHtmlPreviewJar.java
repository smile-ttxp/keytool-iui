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


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;

import javax.swing.text.html.*;
import javax.swing.*;
import javax.swing.event.*;

import java.net.*;
import java.awt.*;
import java.awt.event.*;

final public class PViewerFileTextHtmlPreviewJar extends PViewerFileTextHtmlPreviewAbs
{    
    // ------
    // PUBLIC
    
    public boolean doFileOpen(URL url, StyleSheet sst)
    {
        String strMethod = "doFileOpen(url, sst)";
        
        if (! super._doFileOpen_(url, sst))
        {
            setEnabled(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        return true;
    }
    
    public PViewerFileTextHtmlPreviewJar(

        ActionListener actListenerParent,
        Component cmpFrameOwner,
        String strApplicationTitle)
    {
        super(actListenerParent, cmpFrameOwner, strApplicationTitle);
    
        String strMethod = "PViewerFileTextHtmlPreviewJar(actListenerParent, cmpFrameOwner, strApplicationTitle)";
	    
	    // ----
	    super._nsl_ = new SPViewerFileTextHtmlJar((HyperlinkListener) this);
	    
	     // IMPORTANT MEMO: _nsl_.init(): there, in order to get actions for other children constructors
	     // not in use in this class, BUT in in classes extending the same superclass (eg: PEditorNote, for superclass PEditorAbstract)
	    if (! ((SPViewerFileTextAbs) super._nsl_).init())
            MySystem.s_printOutExit(this, strMethod, "failed");
	    
        // ----
        
        super._mbr_ = new MBViewerFileTextHtmlPreviewJar(
            cmpFrameOwner, 
            strApplicationTitle,
            actListenerParent, // catching exit
            (ActionListener) this // catching print
            );
    }
    
    static public void main(String[] strsArg)
    {
        final String f_strWhere = "main(strsArg)";
        
        try
        {
            JFrame frame = new JFrame();
	        frame.getContentPane().setLayout(new BorderLayout());
	        String strApplicationTitle = "PViewerFileTextHtmlPreviewJar as a standalone application";
	        
	        PViewerFileTextHtmlAbs ntp = new PViewerFileTextHtmlPreviewJar(null, frame, strApplicationTitle);
	        
	        if (! ntp.init())
	        {
	            MySystem.s_printOutExit(f_strWhere, "failed");
	        }
	        
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
            MySystem.s_printOutExit(f_strWhere, "exc caught");
            
        }
    }
    
    // -------
    // PRIVATE
}