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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a secondary window
    
    "B" for "Button"
    "Sw" for "Secondary Window"
    "Rtp": Internet-standard protocol for the transport of real-time data, including audio and video
    "Abs" for "Abstract class"

    Known subclasses:
    . B2SwRtpMediaAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;

import java.awt.event.*;

abstract public class B2SwRtpAbs extends B2SwAbs 
{
    // ------
    // PUBLIC
    
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
        
        this._intTimeToWait_ = intTTW;
    }
    
    public String getTimeToWait()
    {
        return Integer.toString(this._intTimeToWait_);
    }
    
    
    // --------------
    // end properties
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        boolean blnStarted = true;
        
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
            
            blnStarted = false;
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
        
        if (! blnStarted)
            ((FSwRtpAbs) super._frmWindow_).startSession();
    }
    
    // ---------
    // PROTECTED
    
    protected String _strIPTransmitter_ = null;
    protected int _intPortTransmitter_ = -1;
    protected int _intTTL_ = -1;
    protected int _intTimeToWait_ = 60; // default
    
    protected B2SwRtpAbs(String strContentType)
    {
        super();
        this._strContentType = strContentType;
    }
    
    // -------
    // PRIVATE
    
    private String _strContentType = null;
    
    
    private void _showDialogWarningFailed()
    {
        String strTitle = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + "RTP Session";
        String strBody = "Failed to open RTP session in secondary window";
        
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
}