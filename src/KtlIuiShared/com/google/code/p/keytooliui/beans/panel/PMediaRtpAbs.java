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

package com.google.code.p.keytooliui.beans.panel;

/**
    a panel that displays JMF-based medias
    
    "P" for "Panel"
    "Media" for JMF-based audios & videos
    "Rtp" ==> streaming medias
    "Abs" for "Abstract class"


    Known subclasses:
    . PMediaRtpAudio
    . PMediaRtpVideoAbs
**/

import com.google.code.p.keytooliui.beans.swing.panel.*;

import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.thread.*;

import javax.media.rtp.*;
import javax.media.rtp.event.*;

import java.awt.*;
import java.awt.event.*;

abstract public class PMediaRtpAbs extends PMediaAbs implements
    ReceiveStreamListener,
    Runnable
{
    // ----------------------
    // FINAL STATIC PROTECTED
    
    final static protected int _f_s_intHeightMinStatusbar_ = 32;
    
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_getRtp(PMediaRtpAbs mra)
    {
        if (mra == null)
            return null;
            
        return com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.s_toString(
            mra.getTransmitterIP(), mra.getTransmitterPort(), mra.getContentType(), mra.getTimeToLive());
    }
    
    
    // ------
    // PUBLIC
    
    // this one assigned in subclasses! (not a bean property)
    public String getContentType() { return this._strContentType; }
    
    public void windowClosing(WindowEvent e)
    {
        stop();
        _destroyChildren();
        super.windowClosing(e);
    }
    
    public void propertyChange(java.beans.PropertyChangeEvent evtPropertyChange)
    {       
        String strMethod = "propertyChange(evtPropertyChange)";
        
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        if (evtPropertyChange.getPropertyName().toLowerCase().compareTo("ancestor") == 0)
        {
            Object objAncestorOld = evtPropertyChange.getOldValue(); 
            Object objAncestorNew = evtPropertyChange.getNewValue(); 

            if (objAncestorNew==null && objAncestorOld!=null)
            {
                MySystem.s_printOutTrace(this, strMethod, "objAncestorNew==null && objAncestorOld!=null");
                stop();
                _destroyChildren();
            }
        }
        
        super.propertyChange(evtPropertyChange);
    }
    
    public void run()
    {
        String strMethod = "run()";
        
        if (! _runSession())
        {
            MySystem.s_printOutError(this, strMethod, "failed, ignoring");
        }
    }
    
    public void start()
    {
        if (this._thr != null) 
        {
            stop();
        }
        
		this._thr = new MyThreadShared("PMediaRtpAbs", this); 
		this._thr.setPriority(Thread.MIN_PRIORITY);
        this._thr.start();
    }
    
    
    public synchronized void stop()
    {        
        this._thr = null;
    }
    
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BIcn2StateRtpStreaming)
        {            
            com.google.code.p.keytooliui.shared.swing.button.BIcn2StateRtpStreaming btn =
                (com.google.code.p.keytooliui.shared.swing.button.BIcn2StateRtpStreaming) evtAction.getSource();
                
            String strRtp = _s_getRtp(this);
                
            String strTitle = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + "RTP Session";
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
        
        // handle in frameContainer
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESPageReload16)
        {
            ((com.google.code.p.keytooliui.shared.swing.button.BESPageReload16) evtAction.getSource()).setEnabled(false);
            
            stop();
            start();
            
            // ending
            return;
        }
        
        super.actionPerformed(evtAction); 
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
    
    // ----------------
    // begin properties
    
    public void setTransmitterIP(String str)
    {
        String strMethod = "setTransmitterIP(str)";
        
        if (! com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.s_isValidAddress(str))
		{
	        MySystem.s_printOutWarning(this, strMethod, "wrong value, str=" + str); 
	        return;
	    }


        this._strIPTransmitter_ = str;
    }
    
    public String getTransmitterIP()
    {
        return this._strIPTransmitter_;
    }
    

    public void setTransmitterPort(String str)
    {
        String strMethod = "setTransmitterPort(str)";
        
        int intPort = -1;
        
        try
        {
            intPort = Integer.parseInt(str);
        }
        
        catch(NumberFormatException excNumberFormat)
        {
            MySystem.s_printOutWarning(this, strMethod, "excNumberFormat caught, str=" + str);
            return;
        }
        
        if (! com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.s_isValidPort(intPort))
		{
	        MySystem.s_printOutWarning(this, strMethod, "wrong value, intPort=" + intPort); 
	        return;
	    }
        
        this._intPortTransmitter_ = intPort;
    }
    
    public String getTransmitterPort()
    {
        return Integer.toString(this._intPortTransmitter_);
    }
    
    /**
        should be in between 1 and 255
    **/
    public void setTimeToLive(String str)
    {
        String strMethod = "setTimeToLive(str)";
        
        int intTTL = -1;
        
        try
        {
            intTTL = Integer.parseInt(str);
        }
        
        catch(NumberFormatException excNumberFormat)
        {
            MySystem.s_printOutWarning(this, strMethod, "excNumberFormat caught, str=" + str);
            return;
        }
	    
	    if (! com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.s_isValidTTL(intTTL))
		{
	        MySystem.s_printOutWarning(this, strMethod, "wrong value, intTTL=" + intTTL); 
	        return;
	    }
        
        this._intTTL_ = intTTL;
    }
    
    public String getTimeToLive()
    {
        return Integer.toString(this._intTTL_);
    }
    
    /**
        should be in between 20 and 300
    **/
    public void setTimeToWait(String str)
    {
        String strMethod = "setTimeToWait(str)";
        
        int intTTW = -1;
        
        try
        {
            intTTW = Integer.parseInt(str);
        }
        
        catch(NumberFormatException excNumberFormat)
        {
            MySystem.s_printOutWarning(this, strMethod, "excNumberFormat caught, str=" + str);
            return;
        }
	    
	    if (intTTW < 20)
		{
	        MySystem.s_printOutWarning(this, strMethod, "intTTW < 20, intTTW=" + intTTW); 
	        return;
	    }
	    
	    if (intTTW > 300)
		{
	        MySystem.s_printOutWarning(this, strMethod, "intTTW > 300, intTTW=" + intTTW); 
	        return;
	    }
        
        this._intTimeToWait = intTTW;
    }
    
    public String getTimeToWait()
    {
        return Integer.toString(this._intTimeToWait);
    }
    
    
    // --------------
    // end properties
    
    
    // ---------
    // PROTECTED
    
    protected String _strIPTransmitter_ = null;
    protected int _intPortTransmitter_ = -1;
    protected int _intTTL_ = -1;
    
    protected void _showDialogWarningFailed_()
    {
        String strTitle = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + "RTP Session";
        String strBody = "Failed to open RTP session in content page";
        
        String strRlRtp = com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.s_toString(
                    this._strIPTransmitter_,
                    this._intPortTransmitter_,
                    this._strContentType,
                    this._intTTL_);
        
        if (strRlRtp != null)
            strBody += "\n\n" + "RTP:" + "\n" + "  " + strRlRtp;
            
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
            null, strTitle, strBody);
    }
    
    
    protected PMediaRtpAbs(String strContentType)
    {
        super();
        
        this._strContentType = strContentType;
    }
    
    public boolean _initChildren_()
    {
        String strMethod = "_initChildren_()";
        
        if (! super._initChildren_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
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
        
        try
        {
        
            add(this._pnlStatusBar, BorderLayout.SOUTH);
            
            this._pnlStatusBar.addNotify();
            validate();
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            return false;
        }
        
        start();
        
        return true;
    }
 
    protected boolean _createChildren_()
    {
        this._blnDestroyed = false;
        this._pnlStatusBar = new PBarContainerSecRtp((java.awt.event.ActionListener) this);
        return true;
    }
    
  
    
    // -------
    // PRIVATE
    
    // ----------
    // properties
    private int _intTimeToWait = 60; // default
    
    
    private PBarContainerSecRtp _pnlStatusBar = null;
    private boolean _blnDestroyed = false;
    private Thread _thr = null;
    
    private String _strContentType = null;
    
    /**private boolean _runSession()
    {
        String strMethod = "_runSession()";
 
        while (true)
        {
            if (this._blnDestroyed || this._thr==null)
                return true;
                
            if (_gotData()) // looping
            {
                if (this._blnDestroyed || this._thr==null)
                    return true;
                
                // send message to status bar
                if (this._pnlStatusBar != null)
                {
                    this._pnlStatusBar.setStatusText(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusStreaming + com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strStatusTransmitter);
                    this._pnlStatusBar.setStreamingStateOn(true);
                }  

                break;
            }
            
            if (this._blnDestroyed || this._thr==null)
                return true;
            
            // not received
            MySystem.s_printOutWarning(this, strMethod, "failed to receive stream");
            
            String strRtp = _s_getRtp(this);
                
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
                super._cmpFrameOwner_, 
                
                //super._strTitleAppli_ +
                com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName +
                    " - " + strWarningConfirmTitle, strWarningConfirmBody);
                
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
                strRtp = ((PPageMediaRtpAbs) super._pnlPage_).getRtp();
                
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
                super._cmpFrameOwner_, 
                //super._strTitleAppli_ + 
                com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName +
                  " - " + strWarningConfirmTitle, 
                strWarningConfirmBody);
                
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
	        synchronized(((PPageMediaRtpAbs) super._pnlPage_).getObjectDataSync()) 
	        {
		        while (
		            (! this._blnDestroyed) &&
		            (this._thr != null) &&
		            (this._thr==Thread.currentThread()) &&
		            ! ((PPageMediaRtpAbs) super._pnlPage_).isDataReceived() && 
			        System.currentTimeMillis() - lngThen < lngWaitingPeriod) 
			    {
			        if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
                        return false;
            
		            if (! ((PPageMediaRtpAbs) super._pnlPage_).isDataReceived())
		            {
		                if (this._blnDestroyed || this._thr==null || this._thr!=Thread.currentThread())
                            return false;
            
		                //
		                //MySystem.s_printOutTrace(this, strMethod, "! ((PPageMediaRtpAbs) super._pnlPage_).isDataReceived(), intIncSecs=" + intIncSecs);
			            
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
		            
		            ((PPageMediaRtpAbs) super._pnlPage_).getObjectDataSync().wait(1000);
		            
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

	    if (! ((PPageMediaRtpAbs) super._pnlPage_).isDataReceived()) 
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
        
        if (this._blnDestroyed || this._thr==null)
            return false;
        
        int intIncSecs = 1;
        //System.out.println("END");
        
        while (this._thr!=null && this._thr==Thread.currentThread() && !this._blnDestroyed &&
            intIncSecs<this._intTimeToWait+1)
        {
            try
            {
                this._thr.sleep(1000);
                
                if (this._blnDestroyed || this._thr==null)
                    return false;
                
                
                //System.out.println("intCount=" + intCount);
                intIncSecs ++;
                
                if (super._pnlPage_ == null)
                {
                    MySystem.s_printOutTrace(this, strMethod, "nil super._pnlPage_");
                    return false;
                }
                
                if (! ((PPageMediaRtpAbs) super._pnlPage_).isDataReceived())
                {
                    MySystem.s_printOutTrace(this, strMethod, "! ((PPageMediaRtpAbs) super._pnlPage_).isDataReceived(), intIncSecs=" + intIncSecs);
			        //System.err.println("  - Waiting for RTP data to arrive...");
			        if (this._pnlStatusBar != null)
                        this._pnlStatusBar.setStatusText(
                            com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamTimeBeg +
                            intIncSecs +
                            com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamTimeEnd +
                            com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strWaitStreamWait
                                
                            );
                }
                
                else
                {
                    break;
                }
            }
            
            catch (InterruptedException excInterrupted)
            {
                //excInterrupted.printStackTrace();
                MySystem.s_printOutTrace(this, strMethod, "excInterrupted caught, ignoring");
                return false;
            }
        }
        
        //System.out.println("END");
        
        if (this._blnDestroyed || this._thr==null)
            return false;
        
        if (super._pnlPage_ == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil super._pnlPage_");
	        return false;
        }

	    if (((PPageMediaRtpAbs) super._pnlPage_).isDataReceived()) 
	        return true;

	    MySystem.s_printOutWarning(this, strMethod, "No RTP data was received.");
	    return false;

    }
    **/

    
    private void _destroyChildren()
    {
        
        stop();
        this._blnDestroyed = true;
                
        if (this._pnlStatusBar != null)
        {
            if (this.isAncestorOf(this._pnlStatusBar))
            {
                remove(this._pnlStatusBar);
            }
            
            this._pnlStatusBar.destroy();
            this._pnlStatusBar = null;
        }
    }
}