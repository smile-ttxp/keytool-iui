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

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.sound.sampled.*;

import java.io.*;

final public class ClipLoaderJar extends ClipLoaderAbstract
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    // -------------
    // PUBLIC ACCESS

    
    public boolean isErrorUnsupportedAudioFile() { return this._blnErrorUnsupportedAudioFile; }
    public boolean isErrorIO() { return this._blnErrorIO; }
    public boolean isErrorFileNotFound() { return this._blnErrorFileNotFound; }
    
    public boolean isShowDialogErrorUnsupportedSndfxFile() { return this._blnShowDialogErrorUnsupportedSndfxFile; }
    public boolean isShowDialogErrorIO() { return this._blnShowDialogErrorIO; }
    public boolean isShowDialogErrorFileNotFound() { return this._blnShowDialogErrorFileNotFound; }
    
    // ------
    // PUBLIC
    
    public ClipLoaderJar(
        String strTitleApplication,
        java.awt.Component cmpFrameOwner,
        FileJar fjr,
        String strPathRelativeSndfx,
        
        boolean blnShowDialogErrorAllNo,
        boolean blnShowDialogErrorIllegalArgument,
        boolean blnShowDialogErrorLineUnavailable,
        boolean blnShowDialogErrorSecurity,
        
        boolean blnShowDialogErrorUnsupportedAudioFile,
        boolean blnShowDialogErrorIO,
        boolean blnShowDialogErrorFileNotFound)
    {
        super(strTitleApplication, cmpFrameOwner, strPathRelativeSndfx,
            blnShowDialogErrorAllNo,
            blnShowDialogErrorIllegalArgument,
            blnShowDialogErrorLineUnavailable,
            blnShowDialogErrorSecurity);
            
        this._fjr = fjr;
    
        this._blnShowDialogErrorUnsupportedSndfxFile = blnShowDialogErrorUnsupportedAudioFile;
        this._blnShowDialogErrorIO = blnShowDialogErrorIO;
        this._blnShowDialogErrorFileNotFound = blnShowDialogErrorFileNotFound;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._fjr == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._fjr");
            return false;
        }
        
        if (! _load())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnErrorUnsupportedAudioFile = false;
    private boolean _blnErrorIO = false;
    private boolean _blnErrorFileNotFound = false;
    
    private boolean _blnShowDialogErrorUnsupportedSndfxFile = false;
    private boolean _blnShowDialogErrorIO = false;
    private boolean _blnShowDialogErrorFileNotFound = false;
    
    private FileJar _fjr = null;
    
    /**
        memo: fjr either from docJar or from templateJar inside docJar
        if any error, returning false
        
        even if loading fails, returning true
        
        strPath means strPathRelativeSndfx (to jar)
    **/
    
    private boolean _load()
    {
        String strMethod = "_load()";        
        
        // ---------
        // get bytes
        
        byte[] byts = this._fjr.getResource(super._strPath_);
        
        if (byts == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil byts, super._strPath_=" + super._strPath_);
            
            if (!super._blnShowDialogErrorAllNo_ && this._blnShowDialogErrorFileNotFound)
            {
                if (! _showDialogWarningFileNotFound())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
            
            this._blnErrorFileNotFound = true;
            return true;
        }
        
        // -----------
        // create clip
        
        ByteArrayInputStream bai = new ByteArrayInputStream(byts);
        AudioInputStream  ais = null;
        
        try
        {
            ais = AudioSystem.getAudioInputStream(bai);
            
            
            //MySystem.s_printOutTrace(this, strMethod, "bai.close()");
            //bai.close();
        }
            
        catch(UnsupportedAudioFileException excUnsupportedAudioFile)
        {
            excUnsupportedAudioFile.printStackTrace(); 
            MySystem.s_printOutWarning(this, strMethod, "excUnsupportedAudioFile caught, super._strPath_=" + super._strPath_);
            
            if (!super._blnShowDialogErrorAllNo_ && this._blnShowDialogErrorUnsupportedSndfxFile)
            {
                if (! _showDialogWarningUnsupportedAudioFile())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
            }
            
            this._blnErrorUnsupportedAudioFile = true;
			return true;  
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace(); 
            MySystem.s_printOutWarning(this, strMethod, "excIO caught, super._strPath_=" + super._strPath_);
     
            if (!super._blnShowDialogErrorAllNo_ && this._blnShowDialogErrorIO)
            {

                if (! _showDialogWarningIO())
                {
                    MySystem.s_printOutError(this, strMethod, "failed");
                    return false;
                }
                
            }
            
            this._blnErrorIO = true;
			return true;  
        }
        
        
        if (! super._load_(ais))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    


    private boolean _showDialogWarningFileNotFound()
    {
        String strMethod = "_showDialogWarningFileNotFound()";
                  
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
     
        com.google.code.p.keytooliui.shared.swing.dialog.DWClipFileNotFound dew = new com.google.code.p.keytooliui.shared.swing.dialog.DWClipFileNotFound(
            (java.awt.Frame) super._cmpFrameOwner_,
            super._strTitleApplication_,
            super._strPath_);
            
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
            this._blnShowDialogErrorFileNotFound = false;
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYesAll)
		{
            super._blnShowDialogErrorAllNo_ = true;
            return true;
        }
                
        MySystem.s_printOutError(this, strMethod, "uncaught dialog answer, intReply=" + intReply);
        return false;
    }
    
    private boolean _showDialogWarningUnsupportedAudioFile()
    {
        String strMethod = "_showDialogWarningUnsupportedAudioFile()";
                  
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
     
        com.google.code.p.keytooliui.shared.swing.dialog.DWClipUnsupportedSndfxFile dew = new com.google.code.p.keytooliui.shared.swing.dialog.DWClipUnsupportedSndfxFile(
            (java.awt.Frame) super._cmpFrameOwner_,
            super._strTitleApplication_,
            super._strPath_);
            
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
            this._blnShowDialogErrorUnsupportedSndfxFile = false;
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYesAll)
		{
            super._blnShowDialogErrorAllNo_ = true;
            return true;
        }
                
        MySystem.s_printOutError(this, strMethod, "uncaught dialog answer, intReply=" + intReply);
        return false;
    }
    
    private boolean _showDialogWarningIO()
    {
        String strMethod = "_showDialogWarningIO()";
                  
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
     
        com.google.code.p.keytooliui.shared.swing.dialog.DWClipIO dew = new com.google.code.p.keytooliui.shared.swing.dialog.DWClipIO(
            (java.awt.Frame) super._cmpFrameOwner_,
            super._strTitleApplication_,
            super._strPath_);
            
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
            this._blnShowDialogErrorIO = false;
            return true;
        }
        
        if (intReply == com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.f_s_intYesAll)
		{
            super._blnShowDialogErrorAllNo_ = true;
            return true;
        }
                
        MySystem.s_printOutError(this, strMethod, "uncaught dialog answer, intReply=" + intReply);
        return false;
    }
}
