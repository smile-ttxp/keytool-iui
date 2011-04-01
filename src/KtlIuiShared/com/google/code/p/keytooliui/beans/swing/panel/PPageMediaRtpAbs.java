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
    
    "Rtp" means "RTP-RTSP"
    
    Known subclasses:
    . PPageMediaRtpAudio
    . PPageMediaRtpVideoAbs
    
*/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import javax.media.control.*;
import javax.media.protocol.*;

import javax.swing.*;

import java.net.*;
import java.io.*;

abstract public class PPageMediaRtpAbs extends PPageMediaAbs implements
    ReceiveStreamListener, 
    SessionListener
{        
    // -------------
    // PUBLIC ACCESS
    
    public Object getObjectDataSync() { return this._objDataSync; }
    public boolean isDataReceived() { return this._blnDataReceived; }
    
    public String getRtp() 
    { 
        return com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.s_toString(
            this._strIPTransmitter_, this._intPortTransmitter_, this._strContentType_, this._intTTL_);
    }
    
    // ------
    // PUBLIC
    
    public void showAbout()
    {
        String strMethod = "showAbout()";
        
        String strRtp = this.getRtp();
        
        if (strRtp == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil strRtp");
            return;
        }
            
        super._showAbout_("Page RTP", strRtp);
    }
    
    public void destroy()
    {
        String strMethod = "destroy()";
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        super.destroy();
        
        if (! pageClose())
        {
		    MySystem.s_printOutError(this, strMethod, "failed, ignoring");
	    }
    }
    
    public boolean pageClose()
    {
        String strMethod = "pageClose()";
        
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        if (super._plyCur_ != null)
        {
            if (! _closePlayer())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        if (! _closeSession())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        this._blnDataReceived = false;
        this._dse1 = null;
        
	    return true;
	}
    
    public void update(ReceiveStreamEvent evtReceiveStream)
    {
        String strMethod = "update(evtReceiveStream)";
        
        RTPManager mgr = (RTPManager)evtReceiveStream.getSource();
	    Participant participant = evtReceiveStream.getParticipant();	// could be null.
	    ReceiveStream stream = evtReceiveStream.getReceiveStream();  // could be null.

	    if (evtReceiveStream instanceof RemotePayloadChangeEvent) 
	    {
            MySystem.s_printOutExit(this, strMethod, "evtReceiveStream instanceof RemotePayloadChangeEvent, cannot handle payload change.");
	    }
    
	    else if (evtReceiveStream instanceof NewReceiveStreamEvent) 
	    {
            //MySystem.s_printOutTrace(this, strMethod, "evtReceiveStream instanceof NewReceiveStreamEvent");	        
	        
	        try 
	        {
		        stream = ((NewReceiveStreamEvent)evtReceiveStream).getReceiveStream();
		        DataSource dseNew = stream.getDataSource();

		        // Find out the formats.
		        RTPControl ctl = (RTPControl)dseNew.getControl("javax.media.rtp.RTPControl");
		        
		        if (ctl != null)
		        {
		            //MySystem.s_printOutTrace(this, strMethod, "!nil ctl, ctl.getFormat()=" + ctl.getFormat());
		        } 
		        
		        else
		        {
		            //MySystem.s_printOutTrace(this, strMethod, "nil ctl");
                }
                
		        if (participant == null)
		        {
		            //MySystem.s_printOutTrace(this, strMethod, "nil participant"); // participant not yet identified
		        }
		        
		        else 
		        {
		            //MySystem.s_printOutTrace(this, strMethod, "! nil participant, participant.getCNAME()=" + participant.getCNAME());
		        }
		        
		        // NEW
		        
		        DataSource dseMerged = null;
		        
		        if (this._blnMerge2Streams)
		        {
		            if (this._dse1 == null)
		            {
		                this._dse1 = dseNew;
		                return;
		            }
		            
		            // ----
		            DataSource[] dses = new DataSource[2];
		            dses[0] = this._dse1;
		            dses[1] = dseNew;
        		        
		            try
		            {
		                dseMerged = Manager.createMergingDataSource(dses);
		            }
		            
		            // source must be the same, eg: either both "RAW" or both "MIXED"
		            catch(IncompatibleSourceException excIncompatibleSource)
		            {
		                excIncompatibleSource.printStackTrace();
		                // TEMPO, should throw an [XX]Exception
		                MySystem.s_printOutExit(this, strMethod, "excIncompatibleSource caught");
		            }
		        }
		        
		        else // audio only, or video only
		        {
		            dseMerged = dseNew;
		        }
		        
		        // ----
    		    
    		    if (! _initPlayer(dseMerged))
    		    {
    		        MySystem.s_printOutError(this, strMethod, "failed");
    		        MySystem.s_printOutWarning(this, strMethod, "TODO: show warning dialog");
    		        return;
    		    }
	            
	        } 
	        
	        catch (Exception exc) 
	        {
	            exc.printStackTrace();
	            MySystem.s_printOutError(this, strMethod, "exc caught");
	            MySystem.s_printOutWarning(this, strMethod, "TODO: show error dialog");
	            
		        return;
	        }
        
	    }

	    else if (evtReceiveStream instanceof StreamMappedEvent) 
	    {
            //MySystem.s_printOutTrace(this, strMethod, "evtReceiveStream instanceof StreamMappedEvent");
            
	        if (stream != null && stream.getDataSource() != null) 
	        {
		        DataSource ds = stream.getDataSource();
		        // Find out the formats.
		        RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
		        
		        /*if (ctl == null)
		            MySystem.s_printOutTrace(this, strMethod, "nil ctl");
		        else
		            MySystem.s_printOutTrace(this, strMethod, "! nil ctl, ctl.getFormat()=" + ctl.getFormat());
		        */
		          
		        //MySystem.s_printOutTrace(this, strMethod, "participant.getCNAME()=" + participant.getCNAME());
		        
		        /**
		        System.err.println("  - The previously unidentified stream ");
		    
		        if (ctl != null)
		            System.err.println("      " + ctl.getFormat());
		    
		        System.err.println("      had now been identified as sent by: " + participant.getCNAME());
	            **/
	        }
	    }

	    else if (evtReceiveStream instanceof ByeEvent) 
	    {
	        //MySystem.s_printOutTrace(this, strMethod, "evtReceiveStream instanceof ByeEvent"); 
	        //_doBye();
	        if (! pageClose())
	        {
	            MySystem.s_printOutExit(this, strMethod, "evtReceiveStream instanceof ByeEvent, failed");
	        }
	    }
	    
	    else if (evtReceiveStream instanceof InactiveReceiveStreamEvent)
	    {
	        MySystem.s_printOutTrace(this, strMethod, "evtReceiveStream instanceof InactiveReceiveStreamEvent");
	    }
	    
	    else
	    {
	        MySystem.s_printOutTrace(this, strMethod, "uncaught instanceof evtReceiveStream, evtReceiveStream.getClass().toString()=" + evtReceiveStream.getClass().toString());
	    }
    }
    
    public void update(SessionEvent evtSession)
    {
        String strMethod = "update(evtSession)";
                
	    if (evtSession instanceof NewParticipantEvent) 
	    {
	        
	        Participant parNew = ((NewParticipantEvent)evtSession).getParticipant();
	        
	        //MySystem.s_printOutTrace(this, strMethod, "  - A new participant had just joined, \n    parNew.getClass().toString()=" + parNew.getClass().toString() + ", \n    parNew.getCNAME()=: " + parNew.getCNAME());
	    
	        return;
	    }
	    
	    //MySystem.s_printOutTrace(this, strMethod, "evtSession.getClass().toString()=" + evtSession.getClass().toString());
    }
    
    
    
    public boolean init()
    {
        String strMethod = "init()";
     
        if (! super.init())
            return false;
        
        
        // check port
        
        if (com.google.code.p.keytooliui.javax.media.rtp.Port.s_isOpen(this._intPortTransmitter_))
        {
             
            String strIPUsed = com.google.code.p.keytooliui.javax.media.rtp.Port.s_getIP(this._intPortTransmitter_);
            
            if (strIPUsed == null)
            {
                MySystem.s_printOutExit(this, strMethod, "nil strIPUsed");
            }
            
            MySystem.s_printOutWarning(this, strMethod, 
                "port already open, this._intPortTransmitter_=" + this._intPortTransmitter_ +
                ", strIPUsed=" + strIPUsed +
                ", this._strIPTransmitter_=" + this._strIPTransmitter_
                );
           
            
            String strBody = "Attempting to open port: ";
            strBody += this._intPortTransmitter_;
            strBody += "\nfor IP: " + this._strIPTransmitter_;
            strBody += "\n\n";
            strBody += "this port is already open for IP: " + strIPUsed;
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
                super._winParent_, 
                //super._strTitleAppli_, 
                com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName,
                strBody);

            return false;
        }
        
        
        
        // ---
        
        if (! _selectPage())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // store open port
        
        if (! com.google.code.p.keytooliui.javax.media.rtp.Port.s_setOpen(this._intPortTransmitter_, this._strIPTransmitter_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! com.google.code.p.keytooliui.javax.media.rtp.Port.s_setViewer(this._intPortTransmitter_, (java.awt.Component) super._winParent_))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }
        
        
        // ending
        return true;
    }
    
    public void controllerUpdate(ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)";
            	        
    	
    	        
        
        try
        {
            
	        if (evtController instanceof RealizeCompleteEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof RealizeCompleteEvent");
	            
	            if (super._blnReadyStart_)
	            {
	                Player mpr = (Player) evtController.getSource();
	                mpr.start();
    	        }
    	        
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
    
    // !! rather a "reset media" than a "reload page" !!
    public boolean pageReload()
    {
        String strMethod = "pageReload()";
             
        
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
    
    protected String _strIPTransmitter_ = null;
    protected int _intPortTransmitter_ = -1;
    protected String _strContentType_ = null;
    protected int _intTTL_ = -1;
    
    
    protected PPageMediaRtpAbs(
        String strIPTransmitter,
        int intPortTransmitter,
        String strContentType,
        int intTTL,
        ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        JFrame frmParent,
        boolean blnReadyStart,
        boolean blnMerge2Streams, // audio & video
        ReceiveStreamListener rsmListenerParent,
        ReceiveStreamListener rsmListenerToolbar)
    {
        super(ctrListenerToolbar, strTitleAppli, frmParent, blnReadyStart);
        
        String strMethod = "PPageMediaRtpAbs(...)";
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        this._strIPTransmitter_ = strIPTransmitter;
        this._intPortTransmitter_ = intPortTransmitter;
        this._strContentType_ = strContentType;
        this._intTTL_ = intTTL;
        
        
        this._blnMerge2Streams = blnMerge2Streams;
        
        this._rsmListenerParent = rsmListenerParent;
        this._rsmListenerToolbar = rsmListenerToolbar;
    }
    
    // -------
    // PRIVATE
    
    private DataSource _dse1 = null;
    
    private boolean _blnMerge2Streams = false; // !!!!!!!!!!
    
    private ReceiveStreamListener _rsmListenerParent = null;
    private ReceiveStreamListener _rsmListenerToolbar = null;
    
    
    
    private RTPManager _rmrManager = null;
    private boolean _blnDataReceived = false;
    
    private Object _objDataSync = new Object(); 
    
    
    /**private void _doBye()
    {
        String strMethod = "_doBye()";
        
        if (! _closePlayer())
            MySystem.s_printOutExit(this, strMethod, "failed");
            
        this._dse1 = null;
        this._blnDataReceived = false;
    }**/
    
	
	private boolean _selectPage()
    {
        String strMethod = "_selectPage()";
        
        if (! pageClose())
        {
		    MySystem.s_printOutError(this, strMethod, "failed");
		    return false;
	    }
        
        if (! _initSession())
        {
		    MySystem.s_printOutError(this, strMethod, "failed");
		    return false;
	    }
        
            
        return true;
    }
    
    private boolean _initSession() 
    {
        String strMethod = "_initSession()";
        
        
        
        try 
        {
	        
	        // Open the RTP sessions.

		    
		    // --
		    // trick to handle transmitter & receiver on same machine
		    
		    boolean blnSameMachine = _isSameMachine();
		    
		    
		    // ----
		    
		    
		    this._rmrManager = (RTPManager) RTPManager.newInstance();
		    this._rmrManager.addSessionListener(this);
		    this._rmrManager.addReceiveStreamListener(this);
		    
		    if (this._rsmListenerParent != null)
		        this._rmrManager.addReceiveStreamListener(this._rsmListenerParent);
		        
		    if (this._rsmListenerToolbar != null)
		        this._rmrManager.addReceiveStreamListener(this._rsmListenerToolbar);
		    
		    // --

		    InetAddress iasIPTransmitter = InetAddress.getByName(this._strIPTransmitter_);
		    
		    SessionAddress sasReceiverLocal = new SessionAddress(); // ???????????????
	        SessionAddress sasTransmitter = null;

		    if(iasIPTransmitter.isMulticastAddress()) 
		    {
		        //MySystem.s_printOutTrace(this, strMethod, "iasIPTransmitter.isMulticastAddress()");
		            
		        // local and remote address pairs are identical:
		        sasReceiverLocal = new SessionAddress(
		            iasIPTransmitter,
					this._intPortTransmitter_,
					this._intTTL_);
    		        
		        sasTransmitter = new SessionAddress( 
		            iasIPTransmitter,
					this._intPortTransmitter_,
				    this._intTTL_);
		    } 
		    
		    else 
		    {
		        //MySystem.s_printOutTrace(this, strMethod, "! iasIPTransmitter.isMulticastAddress()");
		        
		        if (blnSameMachine)
		        {
		            sasReceiverLocal = new SessionAddress(
		                InetAddress.getLocalHost(),
			  		    this._intPortTransmitter_ + 10000);
			  	}
			  	else
			  	{
			  		sasReceiverLocal = new SessionAddress(
			  		    InetAddress.getLocalHost(),
			  		    this._intPortTransmitter_);
			    }            
			  		            
                    
                // ---
                    
                sasTransmitter = new SessionAddress(iasIPTransmitter, this._intPortTransmitter_);
    			
		        this._rmrManager.initialize(sasReceiverLocal);

		        // You can try out some other buffer size to see
		        // if you can get better smoothness.
    		    BufferControl bcl = (BufferControl)this._rmrManager.getControl("javax.media.control.BufferControl");
    		
    		
		        if (bcl != null)
		            bcl.setBufferLength(350);
    		        
    		    this._rmrManager.addTarget(sasTransmitter);
	        }

        } 
        
        catch (InvalidSessionAddressException excInvalidSessionAddress)
        {
            excInvalidSessionAddress.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "excInvalidSessionAddress caught");	                
            return false;
        }
        
        catch (IOException excIO)
        {
            excIO.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "excIO caught");	                
            return false;
        }
    
        


        return true;
    }
    
    
    
    private boolean _isSameMachine()
    {
        String strMethod = "_isSameMachine()";
        
        // --
        InetAddress ias = null;
        
        try
        {
            ias = InetAddress.getLocalHost();
        }
        
        catch(UnknownHostException excUnknownHost)
        {
            excUnknownHost.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excUnknownHost caught"); // tempo
        }
        
        
        // should be: "rcreader-pablo"
        String strHostName = ias.getHostName();
        
        
        if (strHostName == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strHostName");
            return false;
        }
        
        MySystem.s_printOutTrace(this, strMethod, "strHostName=" + strHostName);
        
        //DEV_NOTE: should be: "127.0.0.1" for rcreader-pablo
        //System.out.println("ias.getHostAddress()=" + ias.getHostAddress());
        
        
        //
        InetAddress[] iass = null;
        
        try
        {
            iass = InetAddress.getAllByName(strHostName);
        }
        
        catch(UnknownHostException excUnknownHost)
        {
            excUnknownHost.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excUnknownHost caught"); // tempo
        }
                
        for (int i=0; i<iass.length; i++)
        {            
            if (iass[i].getHostAddress().compareTo(this._strIPTransmitter_) == 0)
            {
                //System.out.println(">> same machine, this._strIPTransmitter_=" + this._strIPTransmitter_);
                MySystem.s_printOutTrace(this, strMethod, "same machine, this._strIPTransmitter_=" + this._strIPTransmitter_);
                return true;
            }
        }
        
        return false;
    }
    
    private boolean _initPlayer(DataSource dse)
    {
        String strMethod = "_initPlayer(dse)";
        
        if (dse == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil dse");
            return false;
        }
        
        if (super._plyCur_ != null)
        {
            MySystem.s_printOutError(this, strMethod, "! nil super._plyCur_");
            return false;
        }
        
        Player ply = com.google.code.p.keytooliui.javax.media.MyManager.s_createPlayer(dse);
        
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
		
		super._plyCur_.realize();
		

        this._blnDataReceived = true;
        
		// Notify intialize() that a new stream had arrived.
		/**synchronized (this._objDataSync) 
		{
		    this._blnDataReceived = true;
		    this._objDataSync.notifyAll();
		}**/
		
		// Notify intialize() that a new stream had arrived.
		synchronized (this._objDataSync) 
		{
		    this._blnDataReceived = true;
		    this._objDataSync.notifyAll();
		}
		
		return true;
    }
    
    private boolean _closePlayer()
    {
        String strMethod = "_closePlayer()";
        
        if (super._plyCur_ == null) // not really needed, already checked in the calling method
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
    
    private boolean _closeSession()
    {   
        String strMethod = "_closeSession()";
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        if (this._rmrManager != null) 
	    {
            this._rmrManager.removeTargets("Closing RTP sessions from this page");
            
            // --
            // added may 9, 03 coz got bug in JMF211e
            try
            {
                this._rmrManager.dispose();
		    }
		    
		    catch(NullPointerException excNullPointer)
		    {
		        MySystem.s_printOutWarning(this, strMethod, "excNullPointer caught, ignoring");
		    }
		    
		    // --
		    
		    
		    
		    
		    this._rmrManager = null;
		    
		    if (! com.google.code.p.keytooliui.javax.media.rtp.Port.s_setClosed(this._intPortTransmitter_))
	        {
                MySystem.s_printOutWarning(this, strMethod, "failed, ignoring");
	        }
	        
	        MySystem.s_printOutTrace(this, strMethod, "DONE: this._intPortTransmitter_=" + this._intPortTransmitter_);
	    }
	    
	    
	    
	    return true;
    }
}