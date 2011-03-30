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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/*
    displays contents of page in a secondary window
    
    "Sw" means "Secondary Window"
    "Rtp" means "RTP-RTSP"
    
    "VideoAudio" means waiting for 2 rtp-streams, one of type video, the other of type audio
*/

import javax.media.rtp.*;


final public class PPageSwMediaRtpVideoAudio extends PPageSwMediaRtpVideoAbs
{        
    // ------
    // PUBLIC
    
    public PPageSwMediaRtpVideoAudio(
        String strIPTransmitter,
        int intPortTransmitter,
        int intTTL,
        javax.media.ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        java.awt.Window winParent,
        boolean blnReadyStart,
        java.awt.Component cmpParent,
        ReceiveStreamListener rsmListenerParent,
        ReceiveStreamListener rsmListenerToolbar
        )
    {
        super(
            new com.google.code.p.keytooliui.javax.media.rtp.RtpMediaVideoAudio(
                strIPTransmitter,
                intPortTransmitter,
                intTTL),
            
            ctrListenerToolbar,
            strTitleAppli,
            winParent,
            blnReadyStart,
            cmpParent,
            true, // blnMerge2Streams, // eg: "video only: false", "video & audio: true"
            rsmListenerParent,
            rsmListenerToolbar
            );
    }
}