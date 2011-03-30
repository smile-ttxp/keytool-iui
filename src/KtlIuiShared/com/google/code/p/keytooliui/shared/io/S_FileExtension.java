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


package com.google.code.p.keytooliui.shared.io;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;

public class S_FileExtension
{
    // --------------------
    // FINAL STATIC PRIVATE

    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io.S_FileExtension.";

    // --------------------------
    // FINAL STATIC PUBLIC STRING

    final static public String f_s_strJarDocRcrScript = "txt"; // script.txt
    final static public String f_s_strJarDocJhrHelpset = "hs"; // helpset.hs
    


    final static public String f_s_strProjectReaderDocument = "jar"; // jarred RCReader document
    final static public String f_s_strProjectReaderTemplate = "tpr";
    final static public String f_s_strProjectReaderHelpSun = "jar"; // jarred JavaHelp document
    final static public String f_s_strProjectReaderHelpOracle = "jar"; // jarred OracleHelp document

    final static public String f_s_strProjectBuilderDocument = "xlb";
    final static public String f_s_strProjectBuilderTemplate = "tpb";

    final static public String f_s_strProjectBuilderAsciiDoc = "dtx";
    final static public String f_s_strProjectBuilderAsciiTpl = "ttx";

    final static public String f_s_strJARDocument = "jar";
    final static public String f_s_strAPKDocument = "apk";

    final static public String[] f_s_strsImage = { "gif", "jpg", "jpeg", "png" };
    final static public String[] f_s_strsSndfx =  {"aif", "aiff", "au", "wav"};
    final static public String[] f_s_strsCSS =  { "css" };


    final static public String[] f_s_strsPageTextHTML = { "htm", "html" };
    final static public String[] f_s_strsPageTextRTF = { "rtf" };

    /*
        Important: JMF requires the following file extensions, else
        TBRL, eg: using JWS (JavaWebStart) with a video file extension named ".mpeg",
        which is not listed in see com/sun/media/MimeManager.java (JMF2.1.1e)
        
        #### BEG ERROR MESSAGE ####
        
        java.io.IOException: Permission Denied: From an applet cannot read media file with extension mpeg

        java.io.IOException: Permission Denied: From an applet cannot read media file with extension mpeg

        javax.media.NoPlayerException: Error instantiating class: com.sun.media.protocol.file.DataSource : java.io.IOException: Permission Denied: From an applet cannot read media file with extension mpeg

	        at javax.media.Manager.createPlayerForContent(Manager.java:1362)

	        at javax.media.Manager.createPlayer(Manager.java:417)

	        at com.google.code.p.keytooliui.beans.swing.panel.PPageMediaUrlAbs._initPlayer(PPageMediaUrlAbs.java:315)

	        at com.google.code.p.keytooliui.beans.swing.panel.PPageMediaUrlAbs._selectPage(PPageMediaUrlAbs.java:272)

	        at com.google.code.p.keytooliui.beans.swing.panel.PPageMediaUrlAbs.pageReload(PPageMediaUrlAbs.java:97)

	        at com.google.code.p.keytooliui.beans.panel.PMediaUrlAbs.actionPerformed(PMediaUrlAbs.java:129)

	        at com.google.code.p.keytooliui.beans.panel.PMediaUrlVideo.actionPerformed(PMediaUrlVideo.java:126)

	        at javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:1764)

	        at javax.swing.AbstractButton$ForwardActionEvents.actionPerformed(AbstractButton.java:1817)

	        at javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:419)

	        at javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:257)

	        at javax.swing.plaf.basic.BasicButtonListener.mouseReleased(BasicButtonListener.java:245)

	        at java.awt.AWTEventMulticaster.mouseReleased(AWTEventMulticaster.java:227)

	        at java.awt.AWTEventMulticaster.mouseReleased(AWTEventMulticaster.java:227)

	        at java.awt.Component.processMouseEvent(Component.java:5093)

	        at java.awt.Component.processEvent(Component.java:4890)

	        at java.awt.Container.processEvent(Container.java:1566)

	        at java.awt.Component.dispatchEventImpl(Component.java:3598)

	        at java.awt.Container.dispatchEventImpl(Container.java:1623)

	        at java.awt.Component.dispatchEvent(Component.java:3439)

	        at java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:3450)

	        at java.awt.LightweightDispatcher.processMouseEvent(Container.java:3165)

	        at java.awt.LightweightDispatcher.dispatchEvent(Container.java:3095)

	        at java.awt.Container.dispatchEventImpl(Container.java:1609)

	        at java.awt.Window.dispatchEventImpl(Window.java:1585)

	        at java.awt.Component.dispatchEvent(Component.java:3439)

	        at java.awt.EventQueue.dispatchEvent(EventQueue.java:450)

	        at java.awt.EventDispatchThread.pumpOneEventForHierarchy(EventDispatchThread.java:197)

	        at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:150)

	        at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:144)

	        at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:136)

	        at java.awt.EventDispatchThread.run(EventDispatchThread.java:99)


        23 ? ERROR (instance ID: 1)

        . location: com.google.code.p.keytooliui.beans.swing.panel.PPageMediaUrlVideo._initPlayer()

        . message: excNoPlayer caught, this._url_.toString()=file:C:/_my_stuff/myprogs/javasoft/jws cache/file/D/P-1/DMC&c/DM_my_stuff/DMprod/DMrcr/DM1 2/DM_deploy_jws/DMhv/temp/fby42706.mpeg

                
        #### END ERROR MESSAGE ####
        
    */
    // ???? AVI, MPEG-1, QT, H.261, H.263 ????
    // see com/sun/media/MimeManager.java (JMF2.1.1e)
    final static public String[] f_s_strsPageMediaVideo =
    {
        "mov", // "video/quicktime"
        "avi", // "video/x_msvideo"
        "mpg", // "video/mpeg"
        "mpv", // "video/mpeg"
        "viv"  // "video/vivo"
    };



    // ???? AIFF, AU, AVI, GSM, MIDI, MP2, MP3, QT, RMF, WAV ????
    // see com/sun/media/MimeManager.java (JMF2.1.1e)
    final static public String[] f_s_strsPageMediaAudio =
    {
        "au",    // "audio/basic"
        "wav",   // "audio/x_wav"
        "aiff",  // "audio/x_aiff"
        "aif",   // "audio/x_aiff"
        "mid",   // "audio/midi"
        "midi",  // "audio/midi"
        "rmf",   // "audio/rmf"
        "gsm",   // "audio/x_gsm"
        "mp2",   // "audio/mpeg"
        "mp3",   // "audio/mpeg"
        "mpa",   // "audio/mpeg"
        "g728",  // "audio/g728"
        "g729a", // "audio/g729a"
        "cda"    // "audio/cdaudio"
    };
    
    /** OLD
    { f_s_strsSndfx[0], f_s_strsSndfx[1], f_s_strsSndfx[2], f_s_strsSndfx[3],
    "avi", "gsm", "mid", "mp2", "mp3", "qt", "rmf" };
    **/
        
        


    // -------------
    // STATIC PUBLIC

    static public boolean s_isHtml(String strFile)
    {
        String strMethod = _f_s_strClass + "s_isHtml(strFile)";

        if (strFile == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strFile");
        }

        for (int i=0; i<f_s_strsPageTextHTML.length; i++)
        {
            if (strFile.toLowerCase().endsWith(
                f_s_strsPageTextHTML[i].toLowerCase()))
            {
                return true;
            }
        }

        return false;
    }

    static public boolean s_kindOfTemplate(String strFile)
    {
        if (strFile == null)
            return false;

        int intTemplateExtensionLength = f_s_strProjectReaderTemplate.length() + 1;

        if (strFile.length() < intTemplateExtensionLength + 1)
            return false;


	    // --

        String strExtension = com.google.code.p.keytooliui.shared.lang.string.S_StringShared.s_getExtension(strFile);

        if (strExtension == null)
            return false;


		if (strExtension.toLowerCase().compareTo(f_s_strProjectReaderTemplate.toLowerCase()) == 0)
		    return true;

        // ending
        return false;
    }
}