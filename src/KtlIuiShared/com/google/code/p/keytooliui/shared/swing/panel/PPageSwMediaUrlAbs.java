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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/*
    displays contents of page in a secondary window
    
    "Sw" means "Secondary Window"
    
    Known subclasses:
    . PPageSwMediaUrlAudio
    . PPageSwMediaUrlVideo
    
*/

import com.google.code.p.keytooliui.shared.lang.*;

import java.net.*;


abstract public class PPageSwMediaUrlAbs extends PPageSwMediaAbs 
{    
    // ------
    // PUBLIC
       
    // !! rather a "reset media" than a "reload page" !!
    public boolean pageReload()
    {
        String strMethod = "pageReload()";
             
        
        if (super._plyCur_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._plyCur_, returning false");
            return false;
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
    
    public void setReadyStart(boolean bln) { super._blnReadyStart_ = bln; }
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
	            
	            // ending
	            return;
	        }
    	    
	        if (evtController instanceof javax.media.PrefetchCompleteEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof javax.media.PrefetchCompleteEvent");
    	        
	            if (super._blnReadyStart_)
	                mpr.start();
	            
	            // ending
	            return;
	        }
    	    
	        if (evtController instanceof javax.media.EndOfMediaEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof javax.media.EndOfMediaEvent");
    	        
    	        if (mpr.getState() >= javax.media.Controller.Realized)
	                mpr.setMediaTime(new javax.media.Time(0));
	            
	            if (this._blnLoop)
	                mpr.start();
	                
	            // ending
	            return;
	        }
	        
	        super.controllerUpdate(evtController);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
        
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
        
        
        
        //
	    return true;
	}
	
    
    
    
    public boolean init()
    {
        String strMethod = "init()";
     
        if (! super.init())
            return false;
        
        if (! _selectPage())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected PPageSwMediaUrlAbs(
        URL url, 
        javax.media.ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        java.awt.Window winParent,
        boolean blnReadyStart,
        boolean blnLoop)
    {
        super(strTitleAppli, winParent, (Object) url, blnReadyStart, ctrListenerToolbar);

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
        
        if (super._objRL_ == null)
	    {
		    MySystem.s_printOutError(this, strMethod, "nil super._objRL_");
		    return false;
	    }  
        
        javax.media.MediaLocator mlr = null;

	    try
	    {
	        // ----
	        // Create an instance of a player for this media 
	        mlr = new javax.media.MediaLocator((java.net.URL) super._objRL_);
	    }

	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "exc caught, MediaLocator, super._objRL_.toString()=" + super._objRL_.toString());
		    return false;
	    }
	    
	    super._plyCur_ = null;
	    
	    // --
	    
	    javax.media.Player ply = com.google.code.p.keytooliui.javax.media.MyManager.s_createPlayer(
	        mlr, super._winParent_, super._strTitleAppli_);
        
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
	        MySystem.s_printOutError(this, strMethod, "exc caught, realize, super._objRL_.toString()=" + super._objRL_.toString());
	        String strBody = "Failed to realize player for page of type URL/Media:";
	        strBody += "\n";
	        strBody += " " + super._objRL_.toString();
                
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(
                super._winParent_, super._strTitleAppli_, strBody);
                
		    return false;
	    }**/
        
        
        return true;
    }
}