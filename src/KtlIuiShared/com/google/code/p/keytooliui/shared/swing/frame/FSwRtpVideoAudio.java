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
 
 
package com.google.code.p.keytooliui.shared.swing.frame;

/**
    "Sw" means "Secondary Window"
    "Rtp" means "RTP-RRTSP"
    
    video & audio ==> 2 RTP-based streams
**/

import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

final public class FSwRtpVideoAudio extends FSwRtpVideoAbs
{
    // ------
    // PUBLIC
    
           
    public FSwRtpVideoAudio(
        Window winMainAppli,
        String strTitleApplication, 
        Image imgIcon, 
        String strIPTransmitter,
        int intPortTransmitter,
        int intTTL,
        int intTimeToWait)
    {
        super(
            winMainAppli,
            strTitleApplication, 
            imgIcon, 
            strIPTransmitter, 
            intPortTransmitter, 
            com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strCntTypeMediaVideoAudio,
            intTTL);
        
        super._pnlContentPane_ = new PCpSwRtpVideoAudio(
            strIPTransmitter,
            intPortTransmitter,
            intTTL, 
            strTitleApplication, 
            (JFrame) this,
            (ActionListener) this, // buttonPageReload
            intTimeToWait
            );
    }
}