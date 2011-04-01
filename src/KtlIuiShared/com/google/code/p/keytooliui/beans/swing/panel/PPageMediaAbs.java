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
 
 
package com.google.code.p.keytooliui.beans.swing.panel;

/*
    displays contents of page in a secondary window
    
    
    Known subclasses:
    . PPageMediaUrlAbs ==> HTTP-FTP
    . PPageMediaRtpAbs ==> RTP-RTSP
    
*/

import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.*;



abstract public class PPageMediaAbs extends PPageAbs implements
    ControllerListener
{   
    // ------
    // PUBLIC
    
    
    public void setReadyStart(boolean bln) { this._blnReadyStart_ = bln; }
    
    public void controllerUpdate(ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)";     	        
    	
    	try
        {
            if (evtController instanceof AudioDeviceUnavailableEvent)
	        {
	            MySystem.s_printOutWarning(this, strMethod, "evtController instanceof AudioDeviceUnavailableEvent");
	            
	            String strMessage = "AudioDeviceUnavailable";
	            
	            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
	                super._winParent_, 
	                //super._strTitleAppli_, 
	                com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName,
	                strMessage);
	                
	            return;
    	    }
    	    
    	    if (evtController instanceof ControllerErrorEvent)
	        {
	            String strMessage = ((ControllerErrorEvent)evtController).getMessage();
	            MySystem.s_printOutWarning(this, strMethod, "evtController instanceof ControllerErrorEvent, strMessage=" + strMessage);
	        
	            String strBody = "Controller Error.";
	            
	            strBody += "\n\n";
	            strBody += strMessage;
	            
	            //String strTitleAppli = super._strTitleAppli_;
	            
	            //if (strTitleAppli == null)
	              //  strTitleAppli = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName;
	            
	            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
	                super._winParent_, 
	                //strTitleAppli, 
	                com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName,
	                strBody);
	        
	            return;
	        }
    	    
    	    // void
	        if (evtController instanceof PrefetchCompleteEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof PrefetchCompleteEvent");
    	        
	            // ending
	            return;
	        }
    	    
    	    // void
	        if (evtController instanceof EndOfMediaEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof EndOfMediaEvent");
    	        
	            // ending
	            return;
	        }
    	    
    	    if (evtController instanceof StopByRequestEvent)
    	    {
    	        // clicked on "stop" button of media control
    	        //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof StopByRequestEvent");
    	        
    	        return;
            }
            
            if (evtController instanceof ControllerClosedEvent)
    	    {
    	        //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof ControllerClosedEvent");
    	        
    	        
    	        return;
            }
            
            if (evtController instanceof DurationUpdateEvent)
    	    {
    	        //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof DurationUpdateEvent");
    	        
    	        return;
            }

	        
	        //MySystem.s_printOutTrace(this, strMethod, "evtController.getClass().toString()=" + evtController.getClass().toString());
            
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
    }
    
    public boolean init() 
    {
        String strMethod = "init()";
        
        if (this._pvm_ != null)
        {  
            if (! this._pvm_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        return true; 
    }
    
    public void destroy()
    {        
        String strMethod = "destroy()";
        
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        if (! pageClose())
        {
            MySystem.s_printOutError(this, strMethod, "failed, ignoring");
        }
        
        if (this._pvm_ != null)
        {
            this._pvm_.destroy();
            this._pvm_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected Player _plyCur_ = null;
    protected PSubPageJmfAbs _pvm_ = null;
    
    protected boolean _blnReadyStart_ = false;
    protected ControllerListener _ctrListenerToolbar_ = null;
        
    protected PPageMediaAbs(
        javax.media.ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        java.awt.Window winParent,
        boolean blnReadyStart
        )
    {
        super(strTitleAppli, winParent);
        
        this._ctrListenerToolbar_ = ctrListenerToolbar;
        this._blnReadyStart_ = blnReadyStart;
            
        if (((Boolean)Manager.getHint(Manager.LIGHTWEIGHT_RENDERER)).booleanValue() == false)
            Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
    }
}