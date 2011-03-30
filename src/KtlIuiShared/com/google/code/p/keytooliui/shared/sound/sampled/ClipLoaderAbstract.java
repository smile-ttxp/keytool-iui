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
 
 
 package com.google.code.p.keytooliui.shared.sound.sampled;

/**
    known subclasses:
    . ClipLoaderJar
    . ClipLoaderSys
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.sound.sampled.*;

abstract public class ClipLoaderAbstract
{
    // --------------------
    // FINAL STATIC PRIVATE

    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.sound.sampled.ClipLoaderAbstract.";
    

	
    // ------------------
    // STATIC INITIALIZER

    
    // -------------
    // STATIC PUBLIC
    
    /**
        if any error, exiting
    **/
    static public double s_getDurationSeconds(Clip clp)
    {
        String strMethod = _f_s_strClass + "s_getDurationSeconds(clp)";
        
        if (clp == null)
        {
            MySystem.s_printOutExit(strMethod, "nil clp");
        }
        
        double dbl = 0.0;
        AudioFormat aft = clp.getFormat();
        
        if (aft == null)
            MySystem.s_printOutExit(strMethod, "nil aft");
  
        dbl = clp.getBufferSize() / 
            (aft.getFrameSize() * aft.getFrameRate());
        
        return dbl;
    }
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    
    // -------------
    // PUBLIC ACCESS
    
    public Clip getClip() { return this._clp; }
    
    public boolean isErrorIllegalArgument() { return this._blnErrorIllegalArgument; }
    public boolean isErrorLineUnavailable() { return this._blnErrorLineUnavailable; }
    public boolean isErrorSecurity() { return this._blnErrorSecurity; }
    
    public boolean isShowDialogErrorAllNo() { return this._blnShowDialogErrorAllNo_; }
    public boolean isShowDialogErrorIllegalArgument() { return this._blnShowDialogErrorIllegalArgument; }
    public boolean isShowDialogErrorLineUnavailable() { return this._blnShowDialogErrorLineUnavailable; }
    public boolean isShowDialogErrorSecurity() { return this._blnShowDialogErrorSecurity; }

    // ------
    // PUBLIC
    
    public void destroy() {}
    
    
    
    // ---------
    // PROTECTED
    
    // -----
    // input
    protected String _strTitleApplication_ = null;
    protected java.awt.Component _cmpFrameOwner_ = null;
    protected String _strPath_ = null;
    
    protected boolean _blnShowDialogErrorAllNo_ = false;
    
    
    protected ClipLoaderAbstract(
        String strTitleApplication,
        java.awt.Component cmpFrameOwner,
        String strPath,
        
        
        boolean blnShowDialogErrorAllNo,
        boolean blnShowDialogErrorIllegalArgument,
        boolean blnShowDialogErrorLineUnavailable,
        boolean blnShowDialogErrorSecurity)
    {
        this._strTitleApplication_ = strTitleApplication;
        this._cmpFrameOwner_ = cmpFrameOwner;
        this._strPath_ = strPath;
        
        this._blnShowDialogErrorAllNo_ = blnShowDialogErrorAllNo;
        
        this._blnShowDialogErrorIllegalArgument = blnShowDialogErrorIllegalArgument;
        this._blnShowDialogErrorLineUnavailable = blnShowDialogErrorLineUnavailable;
        this._blnShowDialogErrorSecurity = blnShowDialogErrorSecurity;
    }
    
    
    /**
        memo: fjr either from docJar or from templateJar inside docJar
        if any error, exiting
        
        load and open clip
        
        
        if returning false, means clip not loaded
    **/
    
    protected boolean _init_()
    {
        String strMethod = "_init_()";
        
        if (this._strTitleApplication_==null || this._cmpFrameOwner_==null || this._strPath_==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil field");
            return false;
        }
     
        return true;
    }
    
    protected boolean _load_(AudioInputStream ais)
    {
        String strMethod = "_load_(ais)";
        
        if (ais == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil ais");
            return false;
        }
        
        // --------
        
        try
        {
            AudioFormat aft = ais.getFormat(); // memo: no exception thrown

                
            // * we can't yet open the device for ALAW/ULAW playback,
            // * convert ALAW/ULAW to PCM
                 
            if ((aft.getEncoding() == AudioFormat.Encoding.ULAW) ||
                (aft.getEncoding() == AudioFormat.Encoding.ALAW)) 
            {
                AudioFormat aftTmp = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED, 
                    aft.getSampleRate(),
                    aft.getSampleSizeInBits() * 2,
                    aft.getChannels(),
                    aft.getFrameSize() * 2,
                    aft.getFrameRate(),
                    true);
                    
                ais = AudioSystem.getAudioInputStream(aftTmp, ais); // memo: throws IllegalArgumentException
                aft = aftTmp;
            }
                
            DataLine.Info info = new DataLine.Info(
                Clip.class, 
                ais.getFormat(), 
                ((int) ais.getFrameLength() *
                aft.getFrameSize()));

            Clip clp = (Clip) AudioSystem.getLine(info); // memo: throws IllegalArgumentException, LineUnavailableException, SecurityException
            
            /**
                memo: throws
                . LineUnavailableException - if the line cannot be opened due to resource restrictions
                . IOException - if an I/O exception occurs during reading of the stream
                . IllegalStateException - if the line is already open
                . SecurityException - if the line cannot be opened due to security restrictions
            **/
            
            clp.open(ais);
            
            // ending
            this._clp = clp;
            return true;
        }
        
        catch(IllegalArgumentException excIllegalArgument)
        {
            excIllegalArgument.printStackTrace(); 
		    MySystem.s_printOutWarning(this, strMethod, "excIllegalArgument caught, this._strPath_=" + this._strPath_);
            
            if (!this._blnShowDialogErrorAllNo_ && this._blnShowDialogErrorIllegalArgument)
            {
                if (! _showDialogWarningIllegalArgument())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
            
            this._blnErrorIllegalArgument = true;
            return true;
        }
        
        catch(LineUnavailableException excLineUnavailable)
        {
            excLineUnavailable.printStackTrace(); 
		    MySystem.s_printOutWarning(this, strMethod, "excLineUnavailable caught, this._strPath_=" + this._strPath_);
            
            if (!this._blnShowDialogErrorAllNo_ && this._blnShowDialogErrorLineUnavailable)
            {
                if (! _showDialogWarningLineUnavailable())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }    
            
            this._blnErrorLineUnavailable = true;
            return true;
        }
	    
	    catch(SecurityException excSecurity)
        { 
		    excSecurity.printStackTrace(); 
		    MySystem.s_printOutWarning(this, strMethod, "excSecurity caught, this._strPath_=" + this._strPath_);
            
            if (!this._blnShowDialogErrorAllNo_ && this._blnShowDialogErrorSecurity)
            {
                if (! _showDialogWarningSecurity())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }    
            
            this._blnErrorSecurity = true;
            return true;
	    }
        
        
        catch(IllegalStateException excIllegalStateException)
        { 
		    excIllegalStateException.printStackTrace(); 
		    MySystem.s_printOutExit(this, strMethod, "IllegalStateException caught, line is already open, this._strPath_=" + this._strPath_);
		    
		    // not reached
		    return false;
	    }
            
        catch (Exception exc)
        { 
		    exc.printStackTrace(); 
		    MySystem.s_printOutExit(this, strMethod, "exception caught, this._strPath_=" + this._strPath_);
		    
		    // not reached
		    return false;
	    }
        
        // ending
        // statement not reached
        
        
        // BEGIN TEMPO, forcing error dialog to show up
        /**finally
        {
            //excIllegalArgument.printStackTrace(); 
		    //MySystem.s_printOutWarning(this, strMethod, "excIllegalArgument caught, this._strPath_=" + this._strPath_);
            
            if (!this._blnShowDialogErrorAllNo_ && this._blnShowDialogErrorIllegalArgument)
            {
                if (! _showDialogWarningIllegalArgument())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
            
            this._blnErrorIllegalArgument = true;
            return true;
        }**/
        
        // END TEMPO
        
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnErrorIllegalArgument = false;
    private boolean _blnErrorLineUnavailable = false;
    private boolean _blnErrorSecurity = false;
    
    private boolean _blnShowDialogErrorIllegalArgument = false;
    private boolean _blnShowDialogErrorLineUnavailable = false;
    private boolean _blnShowDialogErrorSecurity = false;
    
    
    // ------
    // output
    private Clip _clp = null;
    
    private boolean _showDialogWarningSecurity()
    {
        String strMethod = "_showDialogWarningSecurity()";
                  
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
     
        com.google.code.p.keytooliui.shared.swing.dialog.DWClipSecurity dew = new com.google.code.p.keytooliui.shared.swing.dialog.DWClipSecurity(
            (java.awt.Frame) this._cmpFrameOwner_,
            this._strTitleApplication_,
            this._strPath_);
            
        if (! dew.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
        dew.setVisible(true);
        
			    
		int intReply = dew.getValue();
		dew.destroy();
		dew = null;
			    
	    if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intClose)
		{
		    
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYes)
		{
            this._blnShowDialogErrorSecurity = false;
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYesAll)
		{
            this._blnShowDialogErrorAllNo_ = true;
            return true;
        }
                
        MySystem.s_printOutError(this, strMethod, "uncaught dialog answer, intReply=" + intReply);
        return false;
    }
    
    private boolean _showDialogWarningLineUnavailable()
    {
        String strMethod = "_showDialogWarningLineUnavailable()";
                  
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
     
        com.google.code.p.keytooliui.shared.swing.dialog.DWClipLineUnavailable dew = new com.google.code.p.keytooliui.shared.swing.dialog.DWClipLineUnavailable(
            (java.awt.Frame) this._cmpFrameOwner_,
            this._strTitleApplication_,
            this._strPath_);
            
        if (! dew.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
        dew.setVisible(true);

			    
		int intReply = dew.getValue();
		dew.destroy();
		dew = null;
			    
	    if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intClose)
		{
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYes)
		{
            this._blnShowDialogErrorLineUnavailable = false;
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYesAll)
		{
            this._blnShowDialogErrorAllNo_ = true;
            return true;
        }
                
        MySystem.s_printOutError(this, strMethod, "uncaught dialog answer, intReply=" + intReply);
        return false;
    }
    
    private boolean _showDialogWarningIllegalArgument()
    {
        String strMethod = "_showDialogWarningIllegalArgument()";
                  
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
     
        com.google.code.p.keytooliui.shared.swing.dialog.DWClipIllegalArgument dew = new com.google.code.p.keytooliui.shared.swing.dialog.DWClipIllegalArgument(
            (java.awt.Frame) this._cmpFrameOwner_,
            this._strTitleApplication_,
            this._strPath_);
            
        if (! dew.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
        dew.setVisible(true);
			    
		int intReply = dew.getValue();
		dew.destroy();
		dew = null;
			    
	    if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intClose)
		{
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYes)
		{
		    
            this._blnShowDialogErrorIllegalArgument = false;
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYesAll)
		{
            this._blnShowDialogErrorAllNo_ = true;
            return true;
        }
        
        MySystem.s_printOutError(this, strMethod, "uncaught dialog answer, intReply=" + intReply);
        return false;
    }
}
