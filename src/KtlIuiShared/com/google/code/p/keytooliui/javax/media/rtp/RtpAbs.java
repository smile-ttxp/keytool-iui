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
 
 
package com.google.code.p.keytooliui.javax.media.rtp;

/**
    known subclasses:
    . RtpMediaAudio
    . RtpMediaVideoOnly
    . RtpMediaVideoAudio


    Exerpt from JMF's
    
    The format of an RTP Media Locator is: 
        rtp://address:port[:ssrc]/content-type/[ttl] 
        
        where: 
        . address The IP address of the RTP session 
        . port The port of the RTP session.  
        . ssrc SSRC Identifier of the source from which data is to be received.  
          If ssrc is not specified, the first stream detected by the RTP Session Manager 
          will be selected as a stream for the DataSource.  
        . ttl Time to Live of the RTP session.  
        . content-type A string defining the data content type. e.g. video, audio, motion, text, etc. The RTP media handler (Player) will be created for this specific media type. 



   This format:
       rtp://[address]:[port]/[content-type]/[ttl]
       
       where:
       . [address]: 
         . min: 0.0.0.0
         . max: 255.255.255.255
       . [port]:
         . min: 1025
         . max: ????
       . [content-type]: one of the following:
         . media-audio
         . media-videoonly
         . media-videoaudio
         
       . [ttl]:
         . min: 1
         . max: 255

**/

import com.google.code.p.keytooliui.shared.lang.*;


abstract public class RtpAbs extends Object
{
    // -------------------
    // FINAL STATIC PUBLIC
       

    final static public String f_s_strWaitStreamTimeBeg = "Elapsed time: ";
    final static public String f_s_strWaitStreamTimeEnd = " second(s)";
    final static public String f_s_strWaitStreamWait = ", waiting for stream ...";
    
    final static public String f_s_strStatusStreamingFailed = "Failed to receive stream!";
    final static public String f_s_strStatusStreaming = "Receiving stream from ";
    
    final static public String f_s_strStatusStreamingBye = "Stream stopped by ";
    
    final static public String f_s_strStatusStreamingInactive = "Inactive stream from ";
    
    final static public String f_s_strStatusTransmitter = "transmitter";
    
    
    final static public String f_s_strCntTypeMediaAudio = "media-audio_only";
    final static public String f_s_strCntTypeMediaVideoOnly = "media-video_only";
    final static public String f_s_strCntTypeMediaVideoAudio = "media-audio_video";
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.javax.media.rtp.RtpAbs";
    
    final static private String _f_s_FullRtpPrefix = "rtp://";
    final static private String _f_s_FullRtpSep1 = ":";
    final static private String _f_s_FullRtpSep2 = "/";
    final static private String _f_s_FullRtpSep3 = "/";
    
    final static private String _f_s_strSeparatorAddress = ".";
    final static private int _f_s_strNbAddressPart = 4;
    
    final static private int _f_s_intMinAddressPart = 0;
    final static private int _f_s_intMaxAddressPart = 255;
    
    final static private int _f_s_intMinPort = 1024 + 1;
    
    final static private int _f_s_intMinTTL = 1;
    final static private int _f_s_intMaxTTL = 255;
    
    
    // -------------
    // STATIC PUBLIC
    
    /**
        if any error, returns nil
        
        
        eg: strRtp = "rtp://111.112.113.114:5555/media-audio_only/1
        ==> returns "111.112.113.114"
        
        algo:
        substring(intBeg, intEnd)
        ... with intBeg from "rtp://".length(),
            and intEnd from first occurence of ":" after intBeg
    **/
    static public String s_fullRtp2Address(String strRtp)
    {
        String strMethod = _f_s_strClass + "." + "s_fullRtp2Address(strRtp)";
        
        if (strRtp == null)
        {
            MySystem.s_printOutError(strMethod, "nil strRtp");
            return null;
        }
            
        try
        {
            int intBeg = RtpAbs._f_s_FullRtpPrefix.length();
            int intEnd = strRtp.indexOf(RtpAbs._f_s_FullRtpSep1, intBeg);;
            
            
            String strRes = strRtp.substring(intBeg, intEnd);
            
            
            if (! s_isValidAddress(strRes))
            {
                MySystem.s_printOutError(strMethod, "! s_isValidAddress(strRes), strRes=" + strRes);
                return null;
            }
            
            return strRes;
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught, strRtp=" + strRtp);
            return null;
        }
    }
    
    /**
        if any error, returns nil
        
        
        eg: strRtp = "rtp://111.112.113.114:5555/media-audio_only/1
        ==> returns "5555"
    **/
    static public String s_fullRtp2Port(String strRtp)
    {
        String strMethod = _f_s_strClass + "." + "s_fullRtp2Port(strRtp)";
        
        if (strRtp == null)
        {
            MySystem.s_printOutError(strMethod, "nil strRtp");
            return null;
        }
            
        try
        {
            int intBegSub = RtpAbs._f_s_FullRtpPrefix.length();
            
            String strRtpSub = strRtp.substring(intBegSub);
            
            // ----
            // at this step, strRtpSub ==> "111.112.113.114:5555/media-audio_only/1"
            
            int intBeg = strRtpSub.indexOf(RtpAbs._f_s_FullRtpSep1);
            int intEnd = strRtpSub.indexOf(RtpAbs._f_s_FullRtpSep2);
            
            intBeg ++;
            
            String strRes = strRtpSub.substring(intBeg, intEnd);
            
            
            if (! s_isValidPort(strRes))
            {
                MySystem.s_printOutError(strMethod, "! s_isValidPort(strRes), strRes=" + strRes);
                return null;
            }
            
            return strRes;
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught, strRtp=" + strRtp);
            return null;
        }
    }
    
    /**
        if any error, returns nil
        
        
        eg: strRtp = "rtp://111.112.113.114:5555/media-audio_only/1
        ==> returns "1"
    **/
    static public String s_fullRtp2TTL(String strRtp)
    {
        String strMethod = _f_s_strClass + "." + "s_fullRtp2TTL(strRtp)";
        
        if (strRtp == null)
        {
            MySystem.s_printOutError(strMethod, "nil strRtp");
            return null;
        }
            
        try
        {            
            int intBeg = strRtp.lastIndexOf(RtpAbs._f_s_FullRtpSep3);
            intBeg ++;
            
            String strRes = strRtp.substring(intBeg);
            
            
            if (! s_isValidTTL(strRes))
            {
                MySystem.s_printOutError(strMethod, "! s_isValidTTL(strRes), strRes=" + strRes);
                return null;
            }
            
            return strRes;
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(strMethod, "exc caught, strRtp=" + strRtp);
            return null;
        }
    }
    
    
    static public boolean s_isValidContentType(String str)
    {
        if (str == null)
            return false;
            
        if (str.toLowerCase().compareTo(f_s_strCntTypeMediaAudio.toLowerCase()) == 0)
            return true;
            
        if (str.toLowerCase().compareTo(f_s_strCntTypeMediaVideoOnly.toLowerCase()) == 0)
            return true;
            
        if (str.toLowerCase().compareTo(f_s_strCntTypeMediaVideoAudio.toLowerCase()) == 0)
            return true;
            
        return false;
    }
    
    static public String s_getAddress(String str1, String str2, String str3, String str4)
    {
        String str = new String();
        
        str += str1;
        str += ".";
        str += str2;
        str += ".";
        str += str3;
        str += ".";
        str += str4;
        
        return str;
    }
    
    /**
        min: 0.0.0.0
        max: 255.255.255.255
    **/
    static public boolean s_isValidAddress(String str)
    {
        if (str == null)
            return false;
            
        if (str.length() < "0.0.0.0".length())
            return false;
            
        if (str.length() > "255.255.255.255".length())
            return false;
        
        String[] strs = com.google.code.p.keytooliui.shared.lang.string.S_StringShared.s_getArrayFromStringSeparator(str, _f_s_strSeparatorAddress);
         
        if (strs == null)
            return false;
         
        if (strs.length != _f_s_strNbAddressPart)
            return false;
        
        for (int i=0; i<strs.length; i++)
        {            
            try
            {
                int intVal = Integer.parseInt(strs[i]);
                
                if (! s_isValidAddressPart(intVal))
                    return false;
            }
            
            catch(NumberFormatException excNumberFormat)
            {
                //excNumberFormat.printStackTrace();
                //MySystem.s_printOutError(this, strMethod, "excNumberFormat caught, strs[i]=" + strs[i]);
                return false;
            }
        }
         
        //
        return true;
    }
    
    static public boolean s_isValidAddressPart(String strVal)
    {
        try
        {
            int intVal = Integer.parseInt(strVal);
                
            if (! s_isValidAddressPart(intVal))
                return false;
        }
            
        catch(NumberFormatException excNumberFormat)
        {
            return false;
        }
        
        return true;
    }
    
    static public boolean s_isValidPort(String strVal)
    {
        try
        {
            int intVal = Integer.parseInt(strVal);
                
            if (! s_isValidPort(intVal))
                return false;
        }
            
        catch(NumberFormatException excNumberFormat)
        {
            return false;
        }
        
        return true;
    }
    
    static public boolean s_isValidTTL(String strVal)
    {
        try
        {
            int intVal = Integer.parseInt(strVal);
                
            if (! s_isValidTTL(intVal))
                return false;
        }
            
        catch(NumberFormatException excNumberFormat)
        {
            return false;
        }
        
        return true;
    }
    
    static public boolean s_isValidAddressPart(int intVal)
    {
        if (intVal < _f_s_intMinAddressPart)
            return false;
            
        if (intVal > _f_s_intMaxAddressPart)
            return false;
        
        return true;
    }
    
    static public boolean s_isValidAddress(int int1, int int2, int int3, int int4)
    {
        if (! s_isValidAddressPart(int1))
            return false;
            
        if (! s_isValidAddressPart(int2))
            return false;
            
        if (! s_isValidAddressPart(int3))
            return false;
            
        if (! s_isValidAddressPart(int4))
            return false;
        
        return true;
    }
    
    static public boolean s_isValidTTL(int intTTL)
    {
        if (intTTL < _f_s_intMinTTL)
            return false;
            
        if (intTTL > _f_s_intMaxTTL)
            return false;
        
        return true;
    }
    
    static public boolean s_isValidPort(int intPort)
    {
        if (intPort < _f_s_intMinPort)
            return false;
            
        return true;
    }
    
    static public boolean s_samePathUnique(RtpAbs rtp1, RtpAbs rtp2)
    {
        if (rtp1==null || rtp2==null) // ? rather exiting ?
            return false;
            
        if (rtp1 == rtp2) // ? bug ?
            return true;
        
        if (rtp1.getPathUnique().compareTo(rtp2.getPathUnique()) == 0)
            return true;
            
        return false;
    }
    

    static public String s_toString(RtpAbs rtp)
    {
        if (rtp == null)
            return null;
            
        return s_toString(rtp.getIPTransmitter(), rtp.getPortTransmitter(), rtp.getContentType(), rtp.getTTL());
    }
    
    /**
        rtp://[address]:[port]/[content-type]/[ttl]
    **/
    static public String s_toString(
        String strIPTransmitter,
        int intPortTransmitter,
        String strContentType,
        int intTTL
        )
    {
        
        String strPortTransmitter = null;
        
        if (intPortTransmitter != -1)
            strPortTransmitter = Integer.toString(intPortTransmitter);
            
        String strTTL = null;
        
        if (intTTL != -1)
            strTTL = Integer.toString(intTTL);
            
        return s_toString(strIPTransmitter, strPortTransmitter, strContentType, strTTL);
    }
    
    /**
        rtp://[address]:[port]
    **/
    static public String s_getPathUnique(
        RtpAbs rtp
        )
    {
        if (rtp == null)
            return null;
            
        return s_getPathUnique(rtp.getIPTransmitter(), rtp.getPortTransmitter());  
    }
    
    /**
        rtp://[address]:[port]
    **/
    static public String s_getPathUnique(
        String strIPTransmitter,
        int intPortTransmitter
        )
    {
        
        String strPortTransmitter = null;
        
        if (intPortTransmitter != -1)
            strPortTransmitter = Integer.toString(intPortTransmitter);
            
        return s_getPathUnique(strIPTransmitter, strPortTransmitter);
    }
    
    /**
        rtp://[address]:[port]/[content-type]/[ttl]
    **/
    static public String s_toString(
        String strIPTransmitter,
        String strPortTransmitter,
        String strContentType,
        String strTTL
        )
    {
        // 
        String str = s_getPathUnique(strIPTransmitter, strPortTransmitter);
            
        // separator #2
        str += RtpAbs._f_s_FullRtpSep2;
        
        // content type
        if (strContentType == null)
            str += "[unknown_content_type]";
        else
            str += strContentType;
        
        // separator #3
        str += RtpAbs._f_s_FullRtpSep3;
        
        if (strTTL == null)
            str += "[unknown_time_to_live]";
        else
            str += strTTL;
        
        return str;
    }
    
    /**
        rtp://[address]:[port]
    **/
    static public String s_getPathUnique(
        String strIPTransmitter,
        String strPortTransmitter
        )
    {
        // prefix
        String str = RtpAbs._f_s_FullRtpPrefix;
        
        // address
        if (strIPTransmitter == null)
            str += "[unknown_IP_transmitter]";
        else
           str += strIPTransmitter;
        
        // separator #1
        str += RtpAbs._f_s_FullRtpSep1;
        
        // port
        if (strPortTransmitter == null)
            str += "[unknown_port_transmitter]";
        else
            str += strPortTransmitter;
            
        
        return str;
    }
    
    
    // ------
    // PUBLIC
    
    public String getIPTransmitter() { return this._strIPTransmitter; }
    public int getPortTransmitter() { return this._intPortTransmitter; }
    public String getContentType() { return this._strContentType; }
    public int getTTL() { return this._intTTL; }
    
    
    
    public String getPathUnique() 
    { 
        // should be RTP media locator minus content type and TTL ????
        return this.toString(); 
    }
    
    /**
        Constructs a string representation of this RtpAbs.

        Overrides:
            toString in class Object
        Returns:
            a string representation of this object.
    **/
    public String toString()
    {
        return s_toString(this);
    }
    
    
    
    public void destroy()
    {
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! s_isValidAddress(this._strIPTransmitter))
        {
            MySystem.s_printOutError(this, strMethod, "wrong address, this._strIPTransmitter=" + this._strIPTransmitter);
            return false;
        }
        
        if (! s_isValidPort(this._intPortTransmitter))
        {
            MySystem.s_printOutError(this, strMethod, "wrong port, this._intPortTransmitter=" + this._intPortTransmitter);
            return false;
        }
        
        if (! s_isValidContentType(this._strContentType))
        {
            MySystem.s_printOutError(this, strMethod, "wrong content type, this._strContentType=" + this._strContentType);
            return false;
        }
        
        if (! s_isValidTTL(this._intTTL))
        {
            MySystem.s_printOutError(this, strMethod, "wrong TTL, this._intTTL=" + this._intTTL);
            return false;
        }
        
        // --
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected RtpAbs(
        String strIPTransmitter,
        int intPortTransmitter,
        String strContentType,
        int intTTL)
    {
        this._strIPTransmitter = strIPTransmitter;
        this._intPortTransmitter = intPortTransmitter;
        this._strContentType = strContentType;
        this._intTTL = intTTL;
    }
    
    // -------
    // PRIVATE
    
    private String _strIPTransmitter = null;
    private int _intPortTransmitter = -1;
    private String _strContentType = null;
    private int _intTTL = -1;
}