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

/*
    displays contents of page in a secondary window
    
    "SW" means "Secondary Window"
    
    Known subclasses:
    . PCpSwRtpAudio
    . PCpSwRtpVideoAbs
    
*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.lang.thread.*;

import javax.media.rtp.*;
import javax.media.rtp.event.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

abstract public class PCpSwRtpAbs extends PCpSwAbs implements
    ReceiveStreamListener,
    Runnable
{
    // ------
    // PUBLIC
    
    
    public void run()
    {
        String strMethod = "run()";
        
        if (! _runSession())
            MySystem.s_printOutError(this, strMethod, "failed, ignoring");
    }
    
    public void start()
    {
        if (this._thr != null) 
        {
            stop();
        }
        
		this._thr = new MyThreadShared("PCpSwRtpAbs", this); 
		this._thr.setPriority(Thread.MIN_PRIORITY);
        this._thr.start(); 
    }
    
    
    public synchronized void stop()
    {        
        if (this._thr == null)
            return;
        
        this._thr = null;
    }
    
    public void showAboutPage()
    {   
        if (super._pnlPage_ != null)
        {
            ((PPageSwMediaRtpAbs) super._pnlPage_).showAbout();
        }
    }
    
    public void update(ReceiveStreamEvent evtReceiveStream)
    {
        String strMethod = "update(evtReceiveStream)";
        
        Participant participant = evtReceiveStream.getParticipant();	// could be nil.
        
        if (evtReceiveStream instanceof InactiveReceiveStreamEvent) // !! not yet checked !!
	    {    
	        stop();
	        
	        if (this._pnlStatusBar != null)
            {
                if (participant == null)
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreamingInactive + com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusTransmitter);
                else
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreamingInactive + participant.getCNAME());
                    
                this._pnlStatusBar.setStreamingStateOn(false);
            }
	        
	        // ending
	        return;
	    }
        
        
        if (evtReceiveStream instanceof ByeEvent) 
	    {    
	        stop();
	        
	        if (this._pnlStatusBar != null)
            {
                if (participant == null)
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreamingBye + com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusTransmitter);
                else
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreamingBye + participant.getCNAME());
                    
                this._pnlStatusBar.setStreamingStateOn(false);
            }
	        
	        // ending
	        return;
	    }
	    
	    if (evtReceiveStream instanceof NewReceiveStreamEvent ||
	        evtReceiveStream instanceof StreamMappedEvent)
	    {	  
	        stop();
	        
	        if (this._pnlStatusBar != null)
	        {
	            this._pnlStatusBar.setStreamingStateOn(true);
	            
	            if (participant != null)
	                 this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreaming + participant.getCNAME());
	            else  
	                this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreaming + com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusTransmitter);
	        }
	        
	        // ending
	        return;
	    }
        
    }
    
    
    
       
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BIcn2StateRtpStreaming)
        {            
            com.google.code.p.keytooliui.shared.swing.button.BIcn2StateRtpStreaming btn =
                (com.google.code.p.keytooliui.shared.swing.button.BIcn2StateRtpStreaming) evtAction.getSource();
            
            String strRtp = null;
            
            if (super._pnlPage_ != null)
                strRtp = ((PPageSwMediaRtpAbs) super._pnlPage_).getRtp();
            
            String strTitle = "RTP Session";
            String strBody = null;
            
            if (btn.getStateOn())
            {
                strBody = "Receiving stream ...";
            }
            else
                strBody = "Not currently receiving stream!";
                
            if (strRtp != null)
                strBody += "\n\n" + "Session: " + strRtp;
                
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogInfo(
                    super._cmpFrameOwner_,
                    strTitle, 
                    strBody
                );   
            
            // ending
            return;
        }
        
        
        super.actionPerformed(evtAction);
    }
    
    
    public void destroy()
    {   
        this._blnDestroyed = true;
        stop();
        super.destroy();
        
        if (this._pnlStatusBar != null)
        {
            this._pnlStatusBar.destroy();
            this._pnlStatusBar = null;
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        if (this._pnlStatusBar == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlStatusBar");
            return false;
        }
        
        if (! this._pnlStatusBar.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        add(this._pnlStatusBar, BorderLayout.SOUTH);
        
        this._pnlStatusBar.addNotify();
        validate();

        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected PCpSwRtpAbs(
        Component cmpFrameOwner, 
        String strTitleAppli,
        int intTimeToWait)
    {
        super(cmpFrameOwner, strTitleAppli);
        
        this._intTimeToWait = intTimeToWait;
        this._pnlStatusBar = new PBarContainerSecRtp((java.awt.event.ActionListener) this);
    }
    
    // -------
    // PRIVATE
    
    private PBarContainerSecRtp _pnlStatusBar = null;
    private boolean _blnDestroyed = false;
    
    private int _intTimeToWait = -1; // 60; // default
    
    private Thread _thr = null;
    
    private boolean _runSession()
    {
        String strMethod = "_runSession()";
   
        while (true)
        {
            if (this._blnDestroyed || this._thr==null)
                return true;
                
            if (Thread.currentThread() != this._thr)
                return true;
            
            //System.out.println("\n\n");
            
            
            
            //System.out.println("this._thr.getName()=" + this._thr.getName());
            //System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
            //System.out.println("\n\n");
                
            if (_gotData()) // looping
            {
                // send message to status bar
                if (this._pnlStatusBar != null)
                {
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreaming + com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusTransmitter);
                    this._pnlStatusBar.setStreamingStateOn(true);
                }   
                  
                break;
            }
            
            if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
                return true;
            
            // not received
            MySystem.s_printOutWarning(this, strMethod, "failed to receive stream");
            
            String strRtp = null;
            
            if (super._pnlPage_ != null)
                strRtp = ((PPageSwMediaRtpAbs) super._pnlPage_).getRtp();
                
            // open up a warning/confirm dialog: try once more?
            
            String strWarningConfirmTitle = "waiting for stream";
            String strWarningConfirmBody = "The application has been waiting for stream during " + this._intTimeToWait + " seconds";
            
            if (strRtp != null)
            {
                strWarningConfirmBody += "\n";
                strWarningConfirmBody += "Session: " + strRtp;
            }
            
            strWarningConfirmBody += "\n\n";
            strWarningConfirmBody += "Do you want to continue waiting?";
       
            boolean blnConfirm = com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showWarningConfirmDialog(
                super._cmpFrameOwner_, super._strTitleAppli_ + " - " + strWarningConfirmTitle, strWarningConfirmBody);
                
            if (! blnConfirm)
            {
                // ---
                // enable button page reload
                if (super._tbrToolbar_ != null)
                    super._tbrToolbar_.setEnabledPageReload(true);
                    
                if (this._pnlStatusBar != null)
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreamingFailed);
                
                break;
            }       
        }
        
        return true;
    }
    
    /**private boolean _runSession()
    {
        String strMethod = "_runSession()";
        
            
        while (true)
        {
            if (this._blnDestroyed)
                return true;
                
            if (_gotData()) // looping
            {
                // send message to status bar
                if (this._pnlStatusBar != null)
                {
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreaming + com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusTransmitter);
                    this._pnlStatusBar.setStreamingStateOn(true);
                }   
                
                
                
                break;
            }
            
            if (this._blnDestroyed)
                return true;
            
            // not received
            MySystem.s_printOutWarning(this, strMethod, "failed to receive stream");
            
            
            String strRtp = null;
            
            if (super._pnlPage_ != null)
                strRtp = ((PPageSwMediaRtpAbs) super._pnlPage_).getRtp();
            
            
                
            // open up a warning/confirm dialog: try once more?
            
            String strWarningConfirmTitle = "waiting for stream";
            String strWarningConfirmBody = "The application has been waiting for stream during " + this._intTimeToWait + " seconds";
            
            if (strRtp != null)
            {
                strWarningConfirmBody += "\n";
                strWarningConfirmBody += "Session: " + strRtp;
            }
            
            strWarningConfirmBody += "\n\n";
            strWarningConfirmBody += "Do you want to continue waiting?";
       
            boolean blnConfirm = com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showWarningConfirmDialog(
                super._cmpFrameOwner_, super._strTitleAppli_ + " - " + strWarningConfirmTitle, strWarningConfirmBody);
                
            if (! blnConfirm)
            {
                // ---
                // enable button page reload
                if (super._tbrToolbar_ != null)
                    super._tbrToolbar_.setEnabledPageReload(true);
                    
                if (this._pnlStatusBar != null)
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreamingFailed);
                
                break;
            }       
        }
        
        
        return true;
    }**/
    
    /**
        memo: handling destroy
    **/
    private boolean _gotData()
    {
        String strMethod = "_gotData()";
        
        if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
            return false;
        
        // Wait for data to arrive before moving on.

	    long lngThen = System.currentTimeMillis();
	    long lngWaitingPeriod = (long) (this._intTimeToWait * 1000);  // wait for a maximum of this._intTimeToWait secs.
	    
	    int intIncSecs = 1;

	    try
	    {
	        synchronized(((PPageSwMediaRtpAbs) super._pnlPage_).getObjectDataSync()) 
	        {
		        while (
		            (! this._blnDestroyed) &&
		            (this._thr != null) &&
		            (this._thr==Thread.currentThread()) &&
		            ! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived() && 
			        System.currentTimeMillis() - lngThen < lngWaitingPeriod) 
			    {
			        if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
                        return false;
            
		            if (! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived())
		            {
		                if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
                            return false;
            
		                //
		                MySystem.s_printOutTrace(this, strMethod, "! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived(), intIncSecs=" + intIncSecs);
			            
			            if (this._pnlStatusBar != null)
			            {
                            this._pnlStatusBar.setStatusText(
                                com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamTimeBeg +
                                intIncSecs +
                                com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamTimeEnd +
                                com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamWait
                                
                                );
                        }
		            }
		            
		            if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
                        return false;
		            
		            ((PPageSwMediaRtpAbs) super._pnlPage_).getObjectDataSync().wait(1000);
		            
		            intIncSecs ++;
		        }
	        }
	    } 
    	
	    catch (Exception exc) 
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "exc caught");
	        return false;
	    }
	    
	    if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
	        return false;

	    if (! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived()) 
	    {
	        MySystem.s_printOutWarning(this, strMethod, "No RTP data was received.");
	        return false;
	    }
	    
	    return true;
    }
    

    /**
        memo: handling destroy
    **/
    /**private boolean _gotData()
    {
        String strMethod = "_gotData()";
        
        if (this._blnDestroyed)
            return false;
        
        
        // Wait for data to arrive before moving on.

	    long lngThen = System.currentTimeMillis();
	    long lngWaitingPeriod = (long) (this._intTimeToWait * 1000);  // wait for a maximum of this._intTimeToWait secs.
	    
	    int intIncSecs = 1;

	    try
	    {
	        synchronized(((PPageSwMediaRtpAbs) super._pnlPage_).getObjectDataSync()) 
	        {
		        while (
		            (! this._blnDestroyed) &&
		            ! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived() && 
			        System.currentTimeMillis() - lngThen < lngWaitingPeriod) 
			    {
		            if (! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived())
		            {
		                //
		                MySystem.s_printOutTrace(this, strMethod, "! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived(), intIncSecs=" + intIncSecs);
			            if (this._pnlStatusBar != null)
                            this._pnlStatusBar.setStatusText(
                                com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamTimeBeg +
                                intIncSecs +
                                com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamTimeEnd +
                                com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamWait
                                
                                );
		            }
		            
		            ((PPageSwMediaRtpAbs) super._pnlPage_).getObjectDataSync().wait(1000);
		            intIncSecs ++;
		        }
	        }
	    } 
    	
	    catch (Exception exc) 
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "exc caught");
	        return false;
	    }
	    
	    if (this._blnDestroyed)
	        return false;

	    if (! ((PPageSwMediaRtpAbs) super._pnlPage_).isDataReceived()) 
	    {
	        MySystem.s_printOutWarning(this, strMethod, "No RTP data was received.");
	        return false;
	    }
	    
	    return true;
    }**/
}