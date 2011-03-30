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
    secondary window
    
    todo: add support for back/forward
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

final public class PViewerFileTextHtmlViewSys extends PViewerFileTextHtmlAbs
{    
    // ------
    // PUBLIC
    
    /**
     * Notification of a change relative to a 
     * hyperlink.
     
     MEMO: bug jdk1.2.2 & jdk1.3: cannot catch all "exited" events
     
     */
    public void hyperlinkUpdate(HyperlinkEvent evtHyperlink)
    {
        String strMethod = "hyperlinkUpdate(evtHyperlink)";

        URL urlLink = evtHyperlink.getURL();
        
        if (urlLink == null)
        {
	        MySystem.s_printOutExit(this, strMethod, "nil urlLink");   
        }
        
        if (evtHyperlink.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
	    {
	        //MySystem.s_printOutWarning(this, strMethod, "evtHyperlink.getEventType() == HyperlinkEvent.EventType.ACTIVATED, not done yet");
            //com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(super._cmpFrameOwner_, super._strTitleApplication_, "not done yet");
	        
	        String strProtocol = urlLink.getProtocol();
        
            // ----
            /// ????????????????????????????? what about jar:http ???????????????????????????????
            if (strProtocol.equalsIgnoreCase("file") || strProtocol.equalsIgnoreCase(com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strJARDocument))
            {
	            if (! doFileOpen(urlLink, this._sstPrevious))
	            {
	                MySystem.s_printOutWarning(this, strMethod, "failed, urlLink.toString()=" + urlLink.toString());   
	                com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(super._cmpFrameOwner_, super._strTitleApplication_, "failed to open:\n" + urlLink.toString());
	            }
	        }
	        
	        else
	        {
	            // redirecting to default browser
	            if (! com.google.code.p.keytooliui.shared.io.S_ToBrowserDefault.s_displayURL(
                    super._cmpFrameOwner_, super._strTitleApplication_, urlLink.toString()))
                {
                    MySystem.s_printOutExit(this, strMethod, "failed");
                }
	        }
	        
	        
	        return;
	    }
        
        
        if (evtHyperlink.getEventType() == HyperlinkEvent.EventType.ENTERED)
	    {   	        
	        return;
	    }
	    
	    if (evtHyperlink.getEventType() == HyperlinkEvent.EventType.EXITED)
	    { 
	        return;
	    }
        
        MySystem.s_printOutExit(this, strMethod, "unknown event type");    
	}
	
    
    public boolean doFileOpen(URL url, StyleSheet sst)
    {
        String strMethod = "doFileOpen(url, sst)";
        
        if (url == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil url");
            return false;
        }
        
        
        
        // DONE IN A HURRY !!

        if (! super._doFileOpen_(url, sst))
        {
            //setEnabled(false);
            MySystem.s_printOutError(this, strMethod, "failed, url.toString()=" + url.toString());
            
            if (this._urlPrevious != null)
            {
                MySystem.s_printOutTrace(this, strMethod, "installing previous url");
                
                if (! super._doFileOpen_(this._urlPrevious, this._sstPrevious))
                    MySystem.s_printOutExit(this, strMethod, "failed to install this._urlPrevious");
            }
            
            return false;
        }
        
        this._urlPrevious = url;
        this._sstPrevious = sst;
        
        return true;
    }
    
    public PViewerFileTextHtmlViewSys(
        ActionListener actListenerParent,
        Component cmpFrameOwner,
        String strApplicationTitle)
    {
        super(actListenerParent, cmpFrameOwner, strApplicationTitle);
    
        String strMethod = "PViewerFileTextHtmlViewSys(actListenerParent, cmpFrameOwner, strApplicationTitle)";
	    
	    // ----
	    super._nsl_ = new SPViewerFileTextHtmlSys((HyperlinkListener) this);
	    
	    if (! ((SPViewerFileTextAbs) super._nsl_).init())
            MySystem.s_printOutExit(this, strMethod, "failed");
	    
        // ----
        
        super._mbr_ = new MBViewerFileTextHtmlViewSys(
            cmpFrameOwner, 
            strApplicationTitle, 
            actListenerParent, // catching exit
            (ActionListener) this // catching print
            );
    }
    
    // -------
    // PRIVATE
    
    // --------
    // children

    // should be replaced by stack once added back/forward support
    private URL _urlPrevious = null;
    private StyleSheet _sstPrevious = null;
}