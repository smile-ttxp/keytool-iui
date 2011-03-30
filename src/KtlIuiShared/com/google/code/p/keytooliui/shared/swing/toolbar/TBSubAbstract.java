/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/**
    known subclasses:
    . rcr: TBDocTabAbstract ==> tab panels for navigating through RCReader
    . rcr: TBSubPageIfRdrAbs ==> contents panels for viewing in RCReader 
         eg: 
         . url/text/html, 
         . url/text/rtf, 
         . url/media/audio,
         . url/media/video, 
         . rtp/media/audio
         . rtp/media/videoOnly
         . rtp/media/videoAudio)
   
    . shared_gen: TBAbstract

    contains a series of ImageButtons, images of size 16x16
    . __VOID__
    
    
    Important:
    buttonPrint is created if there is a listener for it, IF SO: added in subclasses
**/


import com.google.code.p.keytooliui.shared.swing.button.*;


abstract public class TBSubAbstract extends TBMSAbstract 
{
    // ---------
    // PROTECTED
    
    protected TBSubAbstract(
        String strHelpID,
        java.awt.event.ActionListener actListenerParent,
        int intOrientation,
        javax.swing.ImageIcon iinFrameFloatable // should be used once bug fixed "setFloatable(true), see below
        )
    {
        super(
            intOrientation, 
            iinFrameFloatable, 
            strHelpID,
            false // blnFloatable
            );
        
        if (actListenerParent != null)
            super._btnPrint_ = new BESPrint16(actListenerParent);
    }
}