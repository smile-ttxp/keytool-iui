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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/*
    known subclasses:
    . TBPageSecMediaRtpAudio
    . TBPageSecMediaRtpVideo
    

*/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.rtp.*;
import javax.media.rtp.event.*;

import java.awt.event.*;

abstract public class TBPageSecMediaRtpAbs extends TBPageSecMediaAbs implements
    ReceiveStreamListener
{
    // ------
    // PUBLIC
    
    public void update(ReceiveStreamEvent evtReceiveStream)
    {        
        if (evtReceiveStream instanceof ByeEvent) 
	    {
	        // ---
            // enable button page reload
            setEnabledPageReload(true);
            
	        super._removeControl_();	      
	        
	        // beg added, oct 8, 2002
	        if (this.isVisible())
	        {
                java.awt.Graphics g = getGraphics();
            
                if (g != null)
                    update(g);
            }
            // end added, oct 8, 2002

	        // ending
	        return;
	    }
	    
	    if (evtReceiveStream instanceof NewReceiveStreamEvent ||
	        evtReceiveStream instanceof StreamMappedEvent)
	    {
	         setEnabledPageReload(false);
	    }
    }
    
    
    public void controllerUpdate(javax.media.ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)"; 
            
        try
        {            
	        if (evtController instanceof javax.media.RealizeCompleteEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof javax.media.RealizeCompleteEvent");
	            
	            javax.media.Player mpr = (javax.media.Player) evtController.getSource();
	            super._addControl_(mpr.getControlPanelComponent());
	            
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
    
    
    
    // ---------
    // PROTECTED
    
    protected TBPageSecMediaRtpAbs(
        ActionListener actListenerParentFrame,
        String strName)
    {
        super(actListenerParentFrame, strName);   
    }
    
}