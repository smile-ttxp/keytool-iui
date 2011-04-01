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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a secondary window
    
    "B" for "Button"
    "Sw" for "Secondary Window"
    "Rtp": Internet-standard protocol for the transport of real-time data, including audio and video
    "Media" for JMF-related stuffs
    "Abs" for "Abstract class"


    Known subclasses:
    . B2SwRtpMediaAudio ==> 1 stream
    . B2SwRtpMediaVideoOnly ==> 1 stream
    . B2SwRtpMediaVideoAudio ==> 2 streams
**/


abstract public class B2SwRtpMediaAbs extends B2SwRtpAbs 
{ 
    // ---------
    // PROTECTED
    
    protected B2SwRtpMediaAbs(String strContentType)
    {
        super(strContentType);
    }   
}