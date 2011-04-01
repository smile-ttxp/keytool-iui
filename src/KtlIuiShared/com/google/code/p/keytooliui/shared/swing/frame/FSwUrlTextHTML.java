/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.frame;


import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.editorpane.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.event.*;

import java.awt.*;
import java.net.*;
import java.awt.event.*;

final public class FSwUrlTextHTML extends FSwUrlTextAbs implements
    HyperlinkListener,
    ActionListener
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        if (this._stkPageIdBack != null)
        {
            this._stkPageIdBack.clear();
            this._stkPageIdBack = null;
        }
        
        if (this._stkPageIdForward != null)
        {
            this._stkPageIdForward.clear();
            this._stkPageIdForward = null;
        }

        super.destroy();
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";  
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESBack16)
        {
            if (! _pageBack())
                MySystem.s_printOutWarning(this, strMethod, "failed, ignoring");
                
            return;
        }
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESForward16)
        {
            if (! _pageForward())
                MySystem.s_printOutWarning(this, strMethod, "failed, ignoring");
                
            return;
        }
    }
    
    /**
     * Notification of a change relative to a 
     * hyperlink.
     
     MEMO: bug jdk1.2.2 & jdk1.3: cannot catch all "exited" events
     
     */
    public void hyperlinkUpdate(HyperlinkEvent evtHyperlink)
    {
        String strMethod = "hyperlinkUpdate(evtHyperlink)";
        
        if (! (evtHyperlink.getSource() instanceof EPTextHTMLAbs))
        {
            MySystem.s_printOutExit(this, strMethod, "unknown source");    
        }
        
        
        
        EPTextHTMLAbs pep = (EPTextHTMLAbs) evtHyperlink.getSource();
        URL urlLink = evtHyperlink.getURL();
        
        if (urlLink == null)
        {
            // BUG jdk1.3final/winNt4.0SP5: not supported protocols of type "https", nil urlLink
            
            String strDescription = evtHyperlink.getDescription();
            
            if (strDescription == null)
                MySystem.s_printOutExit(this, strMethod, "nil urlLink, nil strDescription");
            else
                MySystem.s_printOutExit(this, strMethod, "nil urlLink, strDescription=" + strDescription);
            
            // -------
        }
        
        if (evtHyperlink.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
	    {
	        if (super._pnlContentPane_ != null)
	            ((PCpSwUrlTextHTML) super._pnlContentPane_).setStatusText("");
	        
	        URL urlOri = super._url_;
	         
	        if (! _select(urlLink))
	        {
	            MySystem.s_printOutWarning(this, strMethod, "failed, urlLink.toString()=" + urlLink.toString());
	            return;
	        }
	        
	        if (urlOri.toString().compareTo(urlLink.toString()) == 0) // same page
	        {
	            MySystem.s_printOutTrace(this, strMethod, "urlOri.toString().compareTo(urlLink.toString()) == 0");
	            return;
	        }
	        
	        super._url_ = urlLink;
    	    
    	    
	        this._stkPageIdBack.push(urlOri);
	        this._stkPageIdForward.clear();
	        
	        if (super._pnlContentPane_ != null)
	        {
                ((PCpSwUrlTextHTML) super._pnlContentPane_).setEnabledButtonHistBack(true);
                ((PCpSwUrlTextHTML) super._pnlContentPane_).setEnabledButtonHistForward(false);
    	    }
	        
	        // ----
	        
	        
	        return;
	    }
        
        
        if (evtHyperlink.getEventType() == HyperlinkEvent.EventType.ENTERED)
	    {   
	        if (super._pnlContentPane_ != null)
	            ((PCpSwUrlTextHTML) super._pnlContentPane_).setStatusText(urlLink.toString());
	        
	        return;
	    }
	    
	    if (evtHyperlink.getEventType() == HyperlinkEvent.EventType.EXITED)
	    { 
	        if (super._pnlContentPane_ != null)
	            ((PCpSwUrlTextHTML) super._pnlContentPane_).setStatusText("");
	        
	        return;
	    }
        
        MySystem.s_printOutExit(this, strMethod, "unknown event type");
	    
	}
    
        
    public FSwUrlTextHTML(
        Window winMainAppli,
        String strTitleApplication, 
        Image imgIcon,
        java.net.URL url,
        Color colTextSelection)
    {
        super(winMainAppli, strTitleApplication, imgIcon, url);
        
        super._pnlContentPane_ = new PCpSwUrlTextHTML(
            url,
            (Component) this, 
            strTitleApplication,
            (HyperlinkListener) this,
            (ActionListener) this, // history: back-forward
            colTextSelection
            );
            
            this._stkPageIdBack = new java.util.Stack<URL>();
	    this._stkPageIdForward = new java.util.Stack<URL>();
    }
    
    // -------
    // PRIVATE
    
    private java.util.Stack<URL> _stkPageIdBack = null; // history
    private java.util.Stack<URL> _stkPageIdForward = null; // history
    
    private boolean _pageForward()
    {
        String strMethod = "_pageForward()";
        
        
        if (this._stkPageIdBack==null || this._stkPageIdForward==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._stkPageId[Back-Forward]");
            return false;
        }
        
        if (this._stkPageIdForward.isEmpty())
        {
            MySystem.s_printOutError(this, strMethod, "empty page history forward stack");
            return false;
        }
        
        URL itgForward = (URL) this._stkPageIdForward.pop();
        
      
        
        
        if (super._url_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._url_");
            return false;
        }
        
        
        this._stkPageIdBack.push(super._url_);
        
        
        // ------------------------------
        // DO STG with tclBack ==> select
       
        if (! _select(itgForward))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
                
        
        if (this._stkPageIdForward.isEmpty())
        {   
            if (super._pnlContentPane_ != null)
                ((PCpSwUrlTextHTML) super._pnlContentPane_).setEnabledButtonHistForward(false);
            
        }
        
        if (this._stkPageIdBack.size() == 1)
        {
            if (super._pnlContentPane_ != null)
                ((PCpSwUrlTextHTML) super._pnlContentPane_).setEnabledButtonHistBack(true);
            
        }
        
        
        // ending
        return true;
    }
    
    
    private boolean _pageBack()
    {
        String strMethod = "_pageBack()";
        
        
        if (this._stkPageIdBack==null || this._stkPageIdForward==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._stkPageId[Back-Forward]");
            return false;
        }
        
        if (this._stkPageIdBack.isEmpty())
        {
            MySystem.s_printOutError(this, strMethod, "empty this._stkPageIdBack");
            return false;
        }
        
        URL itgBack = (URL) this._stkPageIdBack.pop();
        
        
        // -----
                
        if (super._url_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._url_");
            return false;
        }
        
        if (super._url_.toString().compareTo(itgBack.toString()) == 0)
        {
            MySystem.s_printOutTrace(this, strMethod, "super._url_.toString().compareTo(itgBack.toString() == 0");
            return true;
        }
        
        
        this._stkPageIdForward.push(super._url_);
        
        
        // ------------------------------
        // DO STG with tclBack ==> select
        if (! _select(itgBack))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        

        
        if (this._stkPageIdBack.isEmpty())
        {
            if (super._pnlContentPane_ != null)
                ((PCpSwUrlTextHTML) super._pnlContentPane_).setEnabledButtonHistBack(false);
        }
        
        if (this._stkPageIdForward.size() == 1)
        {
            if (super._pnlContentPane_ != null)
                ((PCpSwUrlTextHTML) super._pnlContentPane_).setEnabledButtonHistForward(true);
        }
        
        // ending
        return true;
    }
    
    private boolean _select(URL urlCandidate)
    {
        String strMethod = "_select(urlCandidate)";
        
        URL urlOri = super._url_;
        
        //if (super._strTitleApplication_ != null)
          //      setTitle(super._strTitleApplication_);
            
   
        if (((PCpSwUrlTextHTML) super._pnlContentPane_).select(urlCandidate))
        {
            super._url_ = urlCandidate;
            
            if (super._strTitleApplication_ != null)
                setTitle(super._strTitleApplication_);
            else
                setTitle(super._url_.toString());
            
            return true;
        }
        
        // failed

        //setTitle(getTitle() + " - " + "[no page]"); 
        if (super._strTitleApplication_ != null)
            setTitle(super._strTitleApplication_ + " - " + "[no page]"); 
        else
            setTitle("[no page]");
        
        MySystem.s_printOutWarning(this, strMethod, "failed, urlCandidate.toString()=" + urlCandidate.toString());
        String strBody = "Failed to load page:" + "\n  " + urlCandidate.toString(); 
	    OPAbstract.s_showDialogError(this, super._strTitleApplication_, strBody); 
	            
	            
	    // TRYING TO RELOAD PAGE:
	            
	    if (((PCpSwUrlTextHTML) super._pnlContentPane_).select(urlOri))
	    {
	        super._url_ = urlOri; // not really needed
	        
	        if (super._strTitleApplication_ != null)
	            setTitle(super._strTitleApplication_);
	        else
                setTitle(super._url_.toString());
        }
        else
        {
            MySystem.s_printOutError(this, strMethod, "failed to reload page, super._url_.toString()=" + super._url_.toString());
            super._url_ = null;
        }
	            
	    return false;
    }
}