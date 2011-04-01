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
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.swing.*;

import java.awt.*;

final public class PEditorDefaultJar extends PEditorAbstract
{   
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.swing.panel.PEditorDefaultJar.";
    
    // ------
    // PUBLIC
    
    public boolean doFileOpen(byte[] bytsBuffer, String strPathRelative)
        throws Exception
    {
        String f_strMethod = "doFileOpen(bytsBuffer, strPathRelative)";
        
        if (! ((SPEditorDefaultJar)super._nsl_).doFileOpen(bytsBuffer))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._ntl_.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._nbr_.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (super._bar_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil super._bar_");
            return false;
        }
        
        if (! ((PBarContainerPathAbstract)super._bar_).setPath(strPathRelative))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! ((PBarContainerPathAbstract)super._bar_).setVisiblePath(true))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
    
        setEnabled(true);
        return true;
    }
    
    public boolean doFileNew(String strPathRelative)
        throws Exception
    {
        String f_strMethod = "doFileNew(strPathRelative)";
        
        if (! super._nsl_.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._ntl_.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! super._nbr_.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (super._bar_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil super._bar_");
            return false;
        }
        
        if (! ((PBarContainerPathAbstract)super._bar_).setPath(strPathRelative))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! ((PBarContainerPathAbstract)super._bar_).setVisiblePath(true))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        setEnabled(true);
        return true;
    }

    public boolean writeTo(FileJar fjr, String strPathRelativeFile)
        throws Exception
    {
        return ((SPEditorDefaultJar)super._nsl_).writeTo(fjr, strPathRelativeFile);
    }
    
    public PEditorDefaultJar(
        PEditorAbstractListener pndListenerParent,
        Component cmpFrameOwner,
        String strApplicationTitle)
    {
        super(pndListenerParent, cmpFrameOwner, strApplicationTitle);
    
        String f_strMethod = "PEditorDefaultJar(pndListenerParent, cmpFrameOwner, strApplicationTitle)";
	    
	    // ----
	    super._nsl_ = new SPEditorDefaultJar();
	    
	    if (! super._nsl_.init())
        {
            MySystem.s_printOutExit(this, f_strMethod, "failed");
            
        }
	    
        // ----
        
        super._nbr_ = new MBEditorDefault(
            cmpFrameOwner, strApplicationTitle, 
            (java.awt.event.ActionListener) this,
            super._nfeListenerThis_);
        
        super._ntl_ = new TBEditorDefault((java.awt.event.ActionListener) this, super._ntlListenerThis_);
        super._bar_ = new PBarContainerPathRelative();
    }
    
    
    
    public void destroy()
    {
        super._destroy_();
    }
    
    static public void main(String[] args)
    {
        final String f_strWhere = _f_s_strClass + "main(strsArg)";
        
        try
        {
            
            JFrame frame = new JFrame();
	        frame.getContentPane().setLayout(new BorderLayout());
	        String strApplicationTitle = "PEditorDefaultJar as a standalone application";
	        
	        PEditorAbstract ntp = new PEditorDefaultJar(null, frame, strApplicationTitle);
	        
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
            MySystem.s_printOutExit(f_strWhere, "exc caught");
            
        }
    }
}