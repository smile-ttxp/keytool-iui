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
 
 
package com.google.code.p.keytooliui.beans.swing.panel;

/*
    displays contents of page in a secondary window
    
    "Url" for "HTTP-FTP-FILE-JAR:FILE"
    
    Known subclasses:
    . PPageMediaUrlAudio
    . PPageMediaUrlVideo
    
*/

import com.google.code.p.keytooliui.shared.lang.*;


import javax.swing.*;

import java.net.*;


abstract public class PPageMediaUrlAbs extends PPageMediaAbs
{       
    // ------
    // PUBLIC
    
    public void showAbout()
    {
        String strMethod = "showAbout()";
        
        if (this._url_ == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil this._url_");
            return;
        }
            
        super._showAbout_("Page URL", this._url_.toString());
    }
    
    
    
    // !! rather a "reset media" than a "reload page" !!
    public boolean pageReload()
    {
        String strMethod = "pageReload()";
             
        
        if (super._plyCur_ == null)
        {
            if (! _selectPage())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            return true;
        }
        
        try
        {        
            if (super._plyCur_.getState() == javax.media.Controller.Started)
            {
                super._plyCur_.stop();
            }
            
            if (super._plyCur_.getState() >= javax.media.Controller.Realized)
                super._plyCur_.setMediaTime(new javax.media.Time(0));
	        
	        if (super._blnReadyStart_)
	            super._plyCur_.start();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught, returning false");
            return false;
        }
        
        
        // ending
        return true;
    }
    
    
    public void setLoop(boolean bln) { this._blnLoop = bln; }
    
    
    public void controllerUpdate(javax.media.ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)";      
        
        try
        {
            javax.media.Player mpr = (javax.media.Player) evtController.getSource();
            
	        if (evtController instanceof javax.media.RealizeCompleteEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof javax.media.RealizeCompleteEvent");
	            mpr.prefetch();
	            
	            return;
	        }
    	    
	        else if (evtController instanceof javax.media.PrefetchCompleteEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof javax.media.PrefetchCompleteEvent");
    	        
	            if (super._blnReadyStart_)
	                mpr.start();
	            
	            return;
	        }
    	    
	        else if (evtController instanceof javax.media.EndOfMediaEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof javax.media.EndOfMediaEvent");
    	        
    	        if (mpr.getState() >= javax.media.Controller.Realized)
	                mpr.setMediaTime(new javax.media.Time(0));
	            
	            if (this._blnLoop)
	                mpr.start();
	                
	            return;
	        }
    	    
	       
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
        
        super.controllerUpdate(evtController);
    }
    
    public boolean pageClose()
    {
        String strMethod = "pageClose()";
        
        if (super._plyCur_ == null)
        {
            return true;
        }
        
        super._plyCur_.removeControllerListener(this);
            
        if (super._ctrListenerToolbar_ != null)
	        super._plyCur_.removeControllerListener(super._ctrListenerToolbar_);
	        
	    com.google.code.p.keytooliui.javax.media.MyManager.s_cleanUp(super._plyCur_);
        super._plyCur_ = null;
        
	    return true;
	}
    
    
    // ---------
    // PROTECTED
    
    protected URL _url_ = null;
        
    
    protected PPageMediaUrlAbs(
        URL url, 
        javax.media.ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        JFrame frmParent,
        boolean blnReadyStart,
        boolean blnLoop
        )
    {
        super(ctrListenerToolbar, strTitleAppli, (java.awt.Window) frmParent, blnReadyStart);
        
        this._url_ = url;
        this._blnLoop = blnLoop;
    }
    
    // -------
    // PRIVATE
    
    
    
    private boolean _blnLoop = false;
    
    private boolean _selectPage()
    {
        String strMethod = "_selectPage()";

        if (! pageClose())
        {
		    MySystem.s_printOutError(this, strMethod, "failed");
		    return false;
	    }
        
        if (! _initPlayer())
        {
		    MySystem.s_printOutError(this, strMethod, "failed");
		    return false;
	    }
            
        return true;
    }
    
    private boolean _initPlayer()
    {
        String strMethod = "_initPlayer()";
        
        if (this._url_ == null)
	    {
		    MySystem.s_printOutError(this, strMethod, "nil this._url_");
		    return false;
	    }  
        
        javax.media.MediaLocator mlr = null;

	    try
	    {
	        // ----
	        // Create an instance of a player for this media 
	        mlr = new javax.media.MediaLocator(this._url_);
	    }

	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "exc caught, MediaLocator, this._url_.toString()=" + this._url_.toString());
		    return false;
	    }
	    
	    super._plyCur_ = null;
	    
	    // --
	    
	    javax.media.Player ply = com.google.code.p.keytooliui.javax.media.MyManager.s_createPlayer(
	        mlr, super._winParent_, 
	        com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName
	        );
        
        if (ply == null)
        {
		    MySystem.s_printOutError(this, strMethod, "nil ply");
		    return false;
		}
		
		super._plyCur_ = ply;
	    
	    // --
	    
	    super._plyCur_.addControllerListener(this);
	    
	    if (super._ctrListenerToolbar_ != null)
	        super._plyCur_.addControllerListener(super._ctrListenerToolbar_);
	    
	    //try
	    //{
	        super._plyCur_.realize();
	    /**}
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "exc caught, realize, this._url_.toString()=" + this._url_.toString());
	        String strBody = "Failed to realize player for page of type URL/Media:";
	        strBody += "\n";
	        strBody += " " + this._url_.toString();
                
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(
                super._winParent_, 
                com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName,
                strBody);
                
		    return false;
	    }**/
        
        
        return true;
    }
}