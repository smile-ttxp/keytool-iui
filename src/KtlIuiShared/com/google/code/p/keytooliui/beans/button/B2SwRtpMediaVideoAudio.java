/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
    "Media" for JMF-related stuffs

    . waiting for 2 streams
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;

import javax.swing.*;

final public class B2SwRtpMediaVideoAudio extends B2SwRtpMediaAbs 
{
    // ------
    // PUBLIC
    
    public B2SwRtpMediaVideoAudio()
    {
        super(com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strCntTypeMediaVideoAudio);
    }
    
    // ---------
    // PROTECTED
    
    protected boolean _createWindow_()
    {
        String strMethod = "_createWindow_()";
        
        
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
        
        JFrame frmOwner = B2SwAbs._s_getFrameOwnerHelpSet_(this);
        
        if (frmOwner == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil frmOwner");
            return false;
        }
        
        // --
        
        super._frmWindow_ = 
            new FSwRtpVideoAudio(
                frmOwner,
                super.getWindowTitle(), 
                super._getImageIconFrameTarget_(),
                super._strIPTransmitter_,
                super._intPortTransmitter_,
                super._intTTL_,
                super._intTimeToWait_); 
        
        return true;
    }
}