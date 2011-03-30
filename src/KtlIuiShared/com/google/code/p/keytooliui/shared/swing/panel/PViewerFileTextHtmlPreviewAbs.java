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
    . PViewerFileTextHtmlPreviewSys
    . PViewerFileTextHtmlPreviewJar
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.event.*;

import java.net.*;
import java.awt.*;
import java.awt.event.*;

abstract public class PViewerFileTextHtmlPreviewAbs extends PViewerFileTextHtmlAbs
{   
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strDialogLinkDisabledBody = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.panel.PViewerFileTextHtmlPreviewAbs";
        
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".PViewerFileTextHtmlPreviewAbs" // class name
            ;
        
        String strBundleFileLong = f_strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        _s_strDialogLinkDisabledBody = rbeResources.getString("dialogLinkDisabledBody");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, strBundleFileLong + " not found, excMissingResource caught");
        }
    }

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
            OPAbstract.s_showDialogWarning(super._cmpFrameOwner_, super._strTitleApplication_, _s_strDialogLinkDisabledBody);
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
	
    
    // ---------
    // PROTECTED
    
    
    protected PViewerFileTextHtmlPreviewAbs(
        ActionListener actListenerParent,
        Component cmpFrameOwner, 
        String strTitleApplication)
    {
        super(actListenerParent, cmpFrameOwner, strTitleApplication);
    }
    
    
    // -------
    // PRIVATE
}