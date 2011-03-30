/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

package com.google.code.p.keytooliui.beans.panel;

/**
    a panel that displays JMF-based medias
    
    "P" for "Panel"
    "Media" for JMF-based audios & videos
    "Url" ==> url-based protocol, eg: http, file, jar:file, ftp, ...
    "Abs" for "Abstract class"


    Known subclasses:
    . PMediaUrlAudio
    . PMediaUrlVideo
**/

import com.google.code.p.keytooliui.beans.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;
import java.net.*;

abstract public class PMediaUrlAbs extends PMediaAbs
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
         
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.checkbox.CBILoop16)
        {
            if (super._pnlPage_ == null)
                return;
                
            JCheckBox cbx = (JCheckBox) evtAction.getSource();
                    
            ((PPageMediaUrlAbs) super._pnlPage_).setLoop(cbx.isSelected());
            
            return;
        } 
           
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESPageReload16)
        {
            if (super._pnlPage_ != null)
            {
                if (! super._pnlPage_.pageReload())
                {
                    MySystem.s_printOutWarning(this, strMethod, "failed");
                    com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
                    
	                com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
	                    super._cmpFrameOwner_, 
	                    com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName,
	                    "Failed to reload page.");
                }
            }
            
            // ending
            return;
        }
        
        super.actionPerformed(evtAction); 
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
    
    
    protected boolean _blnSetSelectedLoop_ = true;
    
    protected PMediaUrlAbs()
    {
        super();
    }
    
    protected void _showDialogWarningFailed_()
    {
        String strTitle = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + "URL";
        String strBody = "Failed to open URL in content page";
        
        if (this._strUrl_ != null)
            strBody += "\n\n" + "URL:" + "\n" + "  " + this._strUrl_;
            
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
            null, strTitle, strBody);
    }
    
    protected boolean _initChildren_()
    {
        if (! super._initChildren_())
            return false;
            
        if (super._tbrToolbar_ != null)
            super._tbrToolbar_.setEnabledPageReload(true);
            
        return true;
    }
    
    
    /*
        replacing current private method named _getUrl() in order to handle URL starting by "jar:file:"
        
        // 1) make a copy of the media (jarred) file in a temp file that is deleted at session's exit
        
        // 2) return the url of newly generated temp file
    */
    protected URL _getUrl_()
    {
        String strMethod = "_getUrl_()";
        
        MySystem.s_printOutFlagDev(this, strMethod, "only jarred ogg files supported for now");
        
        URL url = this._getUrl();
        
        if (url == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil url");
            return null;
        }
        
        // if jarred
        if (url.getProtocol().toLowerCase().equals("jar"))
        {
            // if ogg file format
            if (com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarNoOgg.s_isValidExtension(url))
                return url;
        }
        
        // unjarred
        else 
        {
            return url;
        }
        
        // -----------------------
        // jarred, all but not ogg
        
        String str = com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getToString(url);
        URL urlCloneTemp = com.google.code.p.keytooliui.shared.io.FileJar.s_getUrlCloneTemp(str);
        
        if (urlCloneTemp == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil urlCloneTemp, url.toString()=" + url.toString());
            return null;
        }
	    
	    return urlCloneTemp;
    }
    
    // -------
    // PRIVATE
    
    // assigned by applet, if and only if:
    // . bean's parent is an applet
    // . and url is relative
    private URL _urlBaseHtmlOwner = null;
    
    // called by subclasses
    private URL _getUrl()
    {
        String strMethod = "_getUrl()";
        
        if (this._strUrl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strUrl_");
            return null;
        }
        
        URL url = null;
        
        // ----------
        // 1) if as resource in a JAR included in the classpath
        if (this._blnUrlIsRelative_ && this._strUrl_.startsWith("/"))
        {
	        try 
	        {
		        //eg: this._strUrl_ = "/resources/index.hs";
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
        
        
        // 2) inside JAR document
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
            
            //System.out.println("urlBaseHtmlOwner.toString()=" + urlBaseHtmlOwner.toString());
            
            //String strBaseHtmlOwner = com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getToString(urlBaseHtmlOwner);
            
            // --
            
            /**try
            {
                urlBaseHtmlOwner = new URL(strBaseHtmlOwner);
            }
            
            catch(MalformedURLException excMalformedURL)
            {
                excMalformedURL.printStackTrace();
                
                MySystem.s_printOutError(this, strMethod, 
                    "excMalformedURL caught" +
                    ", strBaseHtmlOwner=" + 
                    strBaseHtmlOwner
                );
                
                return null;
            }**/
            
            // --
            
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
        
        // --- 
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
}
