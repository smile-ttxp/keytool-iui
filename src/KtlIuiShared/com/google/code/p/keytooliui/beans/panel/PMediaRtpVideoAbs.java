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

package com.google.code.p.keytooliui.beans.panel;

/**
    a panel that displays JMF-based medias
    
    "P" for "Panel"
    "Media" for JMF-based audios & videos
    "Rtp" ==> streaming medias
    "Abs" for "Abstract class"


    Known subclasses:
    . PMediaRtpVideoOnly
    . PMediaRtpVideoAudio ==> 2 streams being merged
**/

import com.google.code.p.keytooliui.beans.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

abstract public class PMediaRtpVideoAbs extends PMediaRtpAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intWidth = 150;
    
    final static private int _f_s_intHeightVideoMin = 80; // dummy value !
    final static private int _f_s_intHeightVideoDefault = 400;
    
    // ------
    // PUBLIC  
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
            
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESFullScreen16)
        {
            if (super._pnlPage_ == null)
                return;
                    
            if (! ((PPageMediaRtpVideoAbs) super._pnlPage_).setFullScreen(true))
                MySystem.s_printOutExit(this, strMethod, "failed");
            
            return;
        }
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.checkbox.CBIFullWindow16)
        {
            if (super._pnlPage_ == null)
                return;
                
            JCheckBox cbx = (JCheckBox) evtAction.getSource();
                    
            if (! ((PPageMediaRtpVideoAbs) super._pnlPage_).setFullWindow(cbx.isSelected()))
                MySystem.s_printOutExit(this, strMethod, "failed");
            
            return;
        }
        
        
            
        super.actionPerformed(evtAction);  
      
    }
    
    
    
    // ----------------
    // begin properties
    
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
    
    
    // ---------
    // PROTECTED
    
    
    
    protected PMediaRtpVideoAbs(String strContentType)
    {
        super(strContentType);  
        _setSize();
    }
 
    
    // -------
    // PRIVATE
    
    private int _intHeightVideo = _f_s_intHeightVideoDefault;
    
    private void _setSize()
    {
        java.awt.Dimension dim = new java.awt.Dimension(
            _f_s_intWidth, 
            PMediaAbs._f_s_intHeightMinToolbar_ + 
            PMediaRtpAbs._f_s_intHeightMinStatusbar_ + 
            this._intHeightVideo);
        
        setPreferredSize(dim);
        setMinimumSize(dim);
        setSize(dim);
    }
}