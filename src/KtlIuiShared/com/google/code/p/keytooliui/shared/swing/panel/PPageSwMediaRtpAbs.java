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
    "Rtp" means "RTP-RTSP"
    
    Known subclasses:
    . PPageSwMediaRtpAudio
    . PPageSwMediaRtpVideoAbs
    
*/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import javax.media.control.*;
import javax.media.protocol.*;

import java.net.*;
import java.io.*;

abstract public class PPageSwMediaRtpAbs extends PPageSwMediaAbs implements
    ReceiveStreamListener, 
    SessionListener
{        
    
    // -------------
    // PUBLIC ACCESS
    
    public Object getObjectDataSync() { return this._objDataSync; }
    public boolean isDataReceived() { return this._blnDataReceived; }
    
    public String getRtp()
    {                        
        if (super._objRL_ == null)
            return null;
        
        return ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).toString();
            
    }
    
    // ------
    // PUBLIC
    
    
    public void destroy()
    {
        if (super._objRL_ != null)
        {
            ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).destroy();
            super._objRL_ = null;
        }
        
        super.destroy();
    }
    
    /**
        memo: there could be no current open page
    **/
    public boolean pageClose()
    {
        String strMethod = "pageClose()";
        
        int intPortTransmitter = -1;
        
        if (super._objRL_ != null)
        {
            intPortTransmitter = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getPortTransmitter();
        }
 
        if (! _closePlayer())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._objRL_ != null)
        {
            if (! _closeSession(intPortTransmitter))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
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
		            //MySystem.s_printOutTrace(this, strMethod, "nil participant");
		            //System.err.println("      The sender of this stream had yet to be identified.");
		        }
		        
		        else 
		        {
		            //MySystem.s_printOutTrace(this, strMethod, "! nil participant, participant.getCNAME()=" + participant.getCNAME());
		            //System.err.println("      The stream comes from: " + participant.getCNAME()); 
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
	        
	        //System.err.println("  - Got \"bye\" from: " + participant.getCNAME());
	        
	        //MySystem.s_printOutWarning(this, strMethod, "IN PROGRESS, participant.getCNAME()=" + participant.getCNAME());
	        
	        /*if (this._pww != null)
	        {
	            this._pww.close();
	            this._pww = null;
	        }*/
	        
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
	        
	        MySystem.s_printOutTrace(this, strMethod, "  - A new participant had just joined, \n    parNew.getClass().toString()=" + parNew.getClass().toString() + ", \n    parNew.getCNAME()=: " + parNew.getCNAME());
	    
	        return;
	    }
	    
	    MySystem.s_printOutTrace(this, strMethod, "evtSession.getClass().toString()=" + evtSession.getClass().toString());
    }
    
    
    
    
    public boolean init()
    {
        String strMethod = "init()";
     
        if (! super.init())
            return false;
        
        if (! ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // check port
        
        int intPortTransmitter = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getPortTransmitter();
        String strIPTransmitter = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getIPTransmitter();
        
        if (com.google.code.p.keytooliui.javax.media.rtp.Port.s_isOpen(intPortTransmitter))
        {
            String strIPUsed = com.google.code.p.keytooliui.javax.media.rtp.Port.s_getIP(intPortTransmitter);
            
            if (strIPUsed == null)
            {
                MySystem.s_printOutExit(this, strMethod, "nil strIPUsed");
            }

            
            MySystem.s_printOutWarning(this, strMethod, 
                "port already open, intPortTransmitter=" + intPortTransmitter +
                ", strIPUsed=" + strIPUsed +
                ", strIPTransmitter=" + strIPTransmitter
                );
           
            
            String strBody = "Attempting to open port: ";
            strBody += intPortTransmitter;
            strBody += "\nfor IP: " + strIPTransmitter;
            strBody += "\n\n";
            strBody += "this port is already open for IP: " + strIPUsed;
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
                super._winParent_, super._strTitleAppli_, strBody);
            
            return false;
        }
        
        
        
        // ---
        
        if (! _selectPage())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // store open port
        
        if (! com.google.code.p.keytooliui.javax.media.rtp.Port.s_setOpen(intPortTransmitter, strIPTransmitter))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (! com.google.code.p.keytooliui.javax.media.rtp.Port.s_setViewer(intPortTransmitter, (java.awt.Component) super._winParent_))
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
        
    
    protected PPageSwMediaRtpAbs(
        com.google.code.p.keytooliui.javax.media.rtp.RtpAbs rtp,
        ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        java.awt.Window winParent,
        boolean blnReadyStart,
        boolean blnMerge2Streams,
        ReceiveStreamListener rsmListenerParent,
        ReceiveStreamListener rsmListenerToolbar)
    {
        super(
            strTitleAppli, 
            winParent,
            rtp,
            blnReadyStart,
            ctrListenerToolbar
             );
        
                
        this._blnMerge2Streams = blnMerge2Streams;
        this._rsmListenerParent = rsmListenerParent;
        this._rsmListenerToolbar = rsmListenerToolbar;
        
    }
    
    // -------
    // PRIVATE
    
    private DataSource _dse1 = null;
    
    private boolean _blnMerge2Streams = false;
    
    private ReceiveStreamListener _rsmListenerParent = null;
    private ReceiveStreamListener _rsmListenerToolbar = null;
    
    
    private RTPManager _rmrManager = null;
    private Object _objDataSync = new Object(); 
    private boolean _blnDataReceived = false;
    
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
            int intPortTransmitter = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getPortTransmitter();
            String strIPTransmitter = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getIPTransmitter();
	        int intTTL = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getTTL();
	        
	        // Open the RTP sessions.

		    
		    // --
		    // trick to handle transmitter & receiver on same machine
		    
		    boolean blnSameMachine = _isSameMachine();
		    
		    
		    // ----
		    

		    /*System.out.println(
		        "  - Open RTP sllSession for: addr: " + 
		        sllSession.getAddress() + 
		        " port: " + 
		        sllSession.getPort() + 
		        " ttl: " + sllSession.getTTL());
		    */
		    
		    this._rmrManager = (RTPManager) RTPManager.newInstance();
		    this._rmrManager.addSessionListener(this);
		    this._rmrManager.addReceiveStreamListener(this);
		    
		    if (this._rsmListenerParent != null)
		        this._rmrManager.addReceiveStreamListener(this._rsmListenerParent);
		        
		    if (this._rsmListenerToolbar != null)
		        this._rmrManager.addReceiveStreamListener(this._rsmListenerToolbar);
		    
		    // --

		    InetAddress iasIPTransmitter = InetAddress.getByName(strIPTransmitter);
		    
		    SessionAddress sasReceiverLocal = new SessionAddress(); // ???????????????
	        SessionAddress sasTransmitter = null;

		    if(iasIPTransmitter.isMulticastAddress()) 
		    {
		        //MySystem.s_printOutTrace(this, strMethod, "iasIPTransmitter.isMulticastAddress()");
		            
		        // local and remote address pairs are identical:
		        sasReceiverLocal = new SessionAddress(
		            iasIPTransmitter,
					intPortTransmitter,
					intTTL);
    		        
		        sasTransmitter = new SessionAddress( 
		            iasIPTransmitter,
				    intPortTransmitter,
				    intTTL);
		    } 
		    
		    else 
		    {
		        //MySystem.s_printOutTrace(this, strMethod, "! iasIPTransmitter.isMulticastAddress()");
		        
		        if (blnSameMachine)
		        {
		            sasReceiverLocal = new SessionAddress(
		                InetAddress.getLocalHost(),
			  		    intPortTransmitter + 10000);
			  	}
			  	else
			  	{
			  		sasReceiverLocal = new SessionAddress(
			  		    InetAddress.getLocalHost(),
			  		    intPortTransmitter);
			    }            
			  		            
                    
                // ---
                    
                sasTransmitter = new SessionAddress(iasIPTransmitter, intPortTransmitter);
    			
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
    
        catch (Exception exc)
        {
            exc.printStackTrace();
	        MySystem.s_printOutError(this, strMethod, "exc caught");	                
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
        
        String strIPTransmitter = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getIPTransmitter();
                
        for (int i=0; i<iass.length; i++)
        {            
            if (iass[i].getHostAddress().compareTo(strIPTransmitter) == 0)
            {
                MySystem.s_printOutTrace(this, strMethod, "same machine, strIPTransmitter=" + strIPTransmitter);
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

        
		
		super._plyCur_.addControllerListener(this);
		
		if (super._ctrListenerToolbar_ != null)
		    super._plyCur_.addControllerListener(super._ctrListenerToolbar_);
		
		super._plyCur_.realize();
		

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
    
    private boolean _closeSession(int intPortTransmitter)
    {   
        String strMethod = "_closeSession(intPortTransmitter)";
        
        //int intPortTransmitter = ((com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) super._objRL_).getPortTransmitter();
        
        if (this._rmrManager != null) 
	    {
            this._rmrManager.removeTargets( "Closing RTP sessions from this page");
            
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
		    
		    if (! com.google.code.p.keytooliui.javax.media.rtp.Port.s_setClosed(intPortTransmitter))
	        {
                MySystem.s_printOutWarning(this, strMethod, "failed, ignoring");
	        }
	    }

	    return true;
    }
}