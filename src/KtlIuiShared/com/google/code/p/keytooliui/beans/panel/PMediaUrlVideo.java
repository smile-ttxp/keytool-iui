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

package com.google.code.p.keytooliui.beans.panel;

/**
    a panel that displays JMF-based medias
    
    "P" for "Panel"
    "Media" for JMF-based audios & videos
    "Url" ==> url-based protocol, eg: http, file, jar:file, ftp, ...
    "video" for JMF-supported video files

**/


import com.google.code.p.keytooliui.beans.swing.panel.*;

import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;
import java.net.*;

final public class PMediaUrlVideo extends PMediaUrlAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intWidth = 150;
    
    final static private int _f_s_intHeightVideoMin = 80; // dummy value !
    final static private int _f_s_intHeightVideoDefault = 400;
    
    
    // ------
    // PUBLIC
    
    // -----------------
    //  begin properties
    
    public void setHeightVideo(String str)
    {        
        String strMethod = "setHeightVideo(str)";
        
        int intH = -1; // !!!!
        
        try
        {
            intH = Integer.parseInt(str);
        }
        
        catch(NumberFormatException excNumberFormat)
        {
            MySystem.s_printOutWarning(this, strMethod, "excNumberFormat caught, str=" + str);
            return;
        }
        
        if (intH < _f_s_intHeightVideoMin)
        {
            MySystem.s_printOutWarning(this, strMethod, "intH < _f_s_intHeightVideoMin, intH=" + intH);
            return;
        }
        
        this._intHeightVideo = intH;
        _setSize();
    }
    
    public String getHeightVideo()
    {
        return Integer.toString(this._intHeightVideo);
    }
    
    // --------------
    // end properties
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
            
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESFullScreen16)
        {
            if (super._pnlPage_ == null)
                return;
                    
            if (! ((PPageMediaUrlVideo) super._pnlPage_).setFullScreen(true))
                MySystem.s_printOutExit(this, strMethod, "failed");
            
            return;
        }
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.checkbox.CBIFullWindow16)
        {
            if (super._pnlPage_ == null)
                return;
                
            JCheckBox cbx = (JCheckBox) evtAction.getSource();
                    
            if (! ((PPageMediaUrlVideo) super._pnlPage_).setFullWindow(cbx.isSelected()))
                MySystem.s_printOutExit(this, strMethod, "failed");
            
            return;
        }
   
        super.actionPerformed(evtAction);   
    }
    
    
    public PMediaUrlVideo()
    {
        super();    
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
        
        super._tbrToolbar_ = new TBPageSecMediaUrlVideo(
            (ActionListener) this,
            super._blnSetSelectedLoop_  // setSelectedLoop
            );
            
        
        URL url = super._getUrl_();
        
        if (url == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil url, TODO: show error");
            return false;
        }
        
        if (super._cmpFrameOwner_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._cmpFrameOwner_, TODO: show error");
            return false;
        }
            
        
        super._pnlPage_ = new PPageMediaUrlVideo(
            url, 
            (javax.media.ControllerListener) super._tbrToolbar_,
            super._strTitleAppli_, 
            (javax.swing.JFrame) super._cmpFrameOwner_,
            super._blnSetSelectedReadyStart_, // setSelectedReadyStart
            super._blnSetSelectedLoop_,  // setSelectedLoop
            this //cmpAncestor
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
    
    private int _intHeightVideo = _f_s_intHeightVideoDefault;
    
    private void _setSize()
    {
        java.awt.Dimension dim = new java.awt.Dimension(
            _f_s_intWidth, PMediaAbs._f_s_intHeightMinToolbar_ + this._intHeightVideo);
        
        setPreferredSize(dim);
        setMinimumSize(dim);
        setSize(dim);
    }
}