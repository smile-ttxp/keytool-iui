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

package com.google.code.p.keytooliui.beans.panel;

/**
    a panel that displays JMF-based medias
    
    "P" for "Panel"
    "Media" for JMF-based audios & videos
    "Rtp" ==> RTP-RTSP
    "Audio" for JMF-supported audio files

**/


import com.google.code.p.keytooliui.beans.swing.panel.*;

import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.rtp.*;

import java.awt.event.*;

final public class PMediaRtpAudio extends PMediaRtpAbs
{
    // ------
    // STATIC
    
    
    // ------
    // PUBLIC
    
    public PMediaRtpAudio()
    {
        super(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strCntTypeMediaAudio);
        
        _setSize();
    }
    
    // ---------
    // PROTECTED
    
    /*
        called by superclass
        once got all fields
    */
    protected boolean _createChildren_()
    {
        String strMethod = "_createChildren_()";
        
        
        
        if (! super._createChildren_()) // creating status bar
            return false;
        
        super._tbrToolbar_ = new TBPageSecMediaRtpAudio(
            (ActionListener) this
            );
            
        if (super._strIPTransmitter_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strIPTransmitter_");
            return false;
        }
        
        if (super._intPortTransmitter_ == -1)
        {
            MySystem.s_printOutError(this, strMethod, "super._intPortTransmitter_ == -1");
            return false;
        }
        
        if (super._intTTL_ == -1)
        {
            MySystem.s_printOutError(this, strMethod, "super._intTTL_ == -1");
            return false;
        }
        
        
        // --
        
        
        super._pnlPage_ = new PPageMediaRtpAudio(
            super._strIPTransmitter_,
            super._intPortTransmitter_,
            super._intTTL_,
            (javax.media.ControllerListener) super._tbrToolbar_,
            super._strTitleAppli_,
            (javax.swing.JFrame) super._cmpFrameOwner_,
            super._blnSetSelectedReadyStart_,
            (ReceiveStreamListener) this,
            (ReceiveStreamListener) super._tbrToolbar_
            );
        
        if (! super._initChildren_())
        {
            MySystem.s_printOutError(this, strMethod, "failed TODO: show error");
            return false;
        }
        
        
        return true;
    }
    
    // -------
    // PRIVATE
    
    private void _setSize()
    {
        java.awt.Dimension dim = new java.awt.Dimension(88, PMediaAbs._f_s_intHeightMinToolbar_ + PMediaRtpAbs._f_s_intHeightMinStatusbar_);
        setPreferredSize(dim);
        setMinimumSize(dim);
        setSize(dim);
    }
    
}