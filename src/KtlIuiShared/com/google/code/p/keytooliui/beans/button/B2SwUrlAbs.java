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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a secondary window
    
    "B" for "Button"
    "Sw" for "Secondary Window"
    "Url" for "Uniform Resource Locator"
    "Abs" for "Abstract class"


    Known subclasses:
    . B2SwUrlTextAbs
    . B2SwUrlMediaAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.event.*;
import java.net.*;

abstract public class B2SwUrlAbs extends B2SwAbs 
{
    // ------
    // PUBLIC
    
    // assigned by applet, if and only if:
    // . bean's parent is an applet
    // . and url is relative
    public void setUrlBaseHtmlOwner(URL url)
    {
        this._urlBaseHtmlOwner = url;
    }
    
    // ----------------
    // begin properties
    
    /*
        true or false, default: false
    */
    public void setUrlIsRelative(String str)
    {
        String strMethod = "setUrlIsRelative(str)";
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil str");
            return;
        }
        
        if (str.trim().toLowerCase().compareTo("true") == 0)
        {
            this._blnUrlIsRelative_ = true;
            return;
        }
        
        if (str.trim().toLowerCase().compareTo("false") == 0)
        {
            this._blnUrlIsRelative_ = false;
            return;
        }
        
        MySystem.s_printOutWarning(this, strMethod, "uncaught str, str=" + str);
    }
    
    public String getUrlIsRelative()
    {
        return Boolean.toString(this._blnUrlIsRelative_);
    }
    
    public void setUrl(String str)
    {
        this._strUrl_ = str;
    }
    
    public String getUrl()
    {
        return this._strUrl_;
    }
    
    // --------------
    // end properties
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (super._frmWindow_ == null)
        {
            //MySystem.s_printOutTrace(this, strMethod, "nil super._frmWindow_");
            
            // create frame
            // method defined in subclasses
            if (! _createWindow_())
            {
                MySystem.s_printOutWarning(this, strMethod, "failed");
                _showDialogWarningFailed();
                
                return;
            }
            
            // not really needed
            if (super._frmWindow_ == null)
            {
                MySystem.s_printOutWarning(this, strMethod, "nil super._frmWindow_");
                _showDialogWarningFailed();
                return;
            }
            
            if (! super._frmWindow_.init())
            {
                MySystem.s_printOutWarning(this, strMethod, "failed");
                super._frmWindow_ = null;
                _showDialogWarningFailed();
                
                
                return;
            }
            
            super._frmWindow_.addWindowListener(this);
        }
        
        else
        {
            //MySystem.s_printOutTrace(this, strMethod, "! nil super._frmWindow_");
        }
        
        if (super._frmWindow_.getState() == java.awt.Frame.ICONIFIED)
        {
            super._frmWindow_.setState(java.awt.Frame.NORMAL);
        }
            
 
                
        if (! super._frmWindow_.isVisible())
        {
            super._frmWindow_.setVisible(true);
        }
          
        super._frmWindow_.toFront();
    }
    
    
    // ---------
    // PROTECTED
    
    /**
        eg, full url:
        . All OS:
          . http://www.foo.com/mystuff/myfile.[file_extension]
        . Windows OS: 
          . file:/D:/foo/mystuff/myfile.[file_extension]
          . jar:file:/D:/foo.jar!/mystuff/myfile.[file_extension]
          
        eg, relative url (relative to location of HTML containing the bean):
        . All OS:
          . mystuff/myfile.[file_extension]
        
    **/
    protected String _strUrl_ = null;
    protected boolean _blnUrlIsRelative_ = false; // default: full url
    
    protected B2SwUrlAbs()
    {
        super();
    }
    
    /**
    **/
    
    
    // called by subclasses
    protected URL _getUrl_()
    {
        String strMethod = "_getUrl_()";
        
        if (this._strUrl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strUrl_");
            return null;
        }
        
        URL url = null;
        
        
        MySystem.s_printOutTrace(this, strMethod, "this._strUrl_=" + this._strUrl_);
        
        // ----------
        // 1) if as resource in a JAR included in the classpath
        if (this._blnUrlIsRelative_ && this._strUrl_.startsWith("/"))
        {
	        try 
	        {
		        //eg: this._strUrl_ = "/resources/index.[xxx]";
		        url = getClass().getResource(this._strUrl_);
            } 
                
            catch (Exception exc) 
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught, this._strUrl_=" + this._strUrl_);
                return null;
            }
                
            if (url == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil url, this._strUrl_=" + this._strUrl_);
                return null;
            }
            
            // -- ending
            return url;
        }
        
        
        // 2) inside JAR, jar is the document
        if (this._blnUrlIsRelative_)
        {
            URL urlBaseHtmlOwner = null;
            
            if (this._urlBaseHtmlOwner != null) // bean inside an applet
                urlBaseHtmlOwner = this._urlBaseHtmlOwner;
            else
                urlBaseHtmlOwner = com.google.code.p.keytooliui.beans.util.UBeanInsideDocHTML.s_getUrlBaseOwner(this);
            
            if (urlBaseHtmlOwner == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil urlBaseHtmlOwner");
                return null;
            }
            
            try
            {
                url = new URL(urlBaseHtmlOwner, this._strUrl_);
            }
            
            catch(MalformedURLException excMalformedURL)
            {
                excMalformedURL.printStackTrace();
                
                MySystem.s_printOutError(this, strMethod, 
                    "excMalformedURL caught" +
                    ", this._strUrl_=" + 
                    this._strUrl_ + 
                    ", urlBaseHtmlOwner.toString()=" + 
                    urlBaseHtmlOwner.toString()
                );
                
                return null;
            }
            
            return url;
        }
        
        // ----
        // 3) full url

        try
        {
            url = new URL(this._strUrl_);
        }
            
        catch(MalformedURLException excMalformedURL)
        {
            excMalformedURL.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excMalformedURL caught, this._strUrl_=" + this._strUrl_);
            return null;
        }
     
        
        return url;
    }
    
    // -------
    // PRIVATE
    
    // assigned by applet, if and only if:
    // . bean's parent is an applet
    // . and url is relative
    private URL _urlBaseHtmlOwner = null;
    

    private void _showDialogWarningFailed()
    {
        String strTitle = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + "URL";
        String strBody = "Failed to open URL in secondary window";
        
        if (this._strUrl_ != null)
            strBody += "\n\n" + "URL:" + "\n" + "  " + this._strUrl_;
            
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
            null, strTitle, strBody);
    }
    
}