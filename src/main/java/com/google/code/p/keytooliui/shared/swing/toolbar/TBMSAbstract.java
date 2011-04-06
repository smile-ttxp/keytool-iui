/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/**
    known subclasses:
    . TBMainAbstract,
    . TBSubAbstract
    

    contains a series of ImageButtons, images of size [16x16-24x24]
    . print
    
    
    Important:
    buttons are created and added in subclasses
**/

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;


abstract public class TBMSAbstract extends TBAbs 
{
    // ------
    // PUBLIC
    
    public void updateTreeUI()
    {
        if (this._btnPrint_ != null)
        {
            if (! isAncestorOf(this._btnPrint_))
                javax.swing.SwingUtilities.updateComponentTreeUI(this._btnPrint_);
        }
    }
    
    public boolean init()
    {
        return true;
    }
    
    public void destroy()
    {
        if (this._btnPrint_ != null)
        {
            this._btnPrint_.destroy();
            this._btnPrint_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected BEnabledState _btnPrint_ = null;
    
    protected TBMSAbstract(
        int intOrientation,
        javax.swing.ImageIcon iinFrameFloatable, // should be used once bug fixed "setFloatable(true), see below
        String strHelpID, // help, onItem
        boolean blnFloatable
        )
    {
        super(intOrientation, blnFloatable);
        
        
        String strMethod = "TBMSAbstract(...)";
        
        if (iinFrameFloatable == null)
            MySystem.s_printOutExit(this, strMethod, "nil iinFrameFloatable");
            
        
        if (strHelpID != null)
            com.google.code.p.keytooliui.shared.help.MyCSH.setHelpIDString(this, strHelpID);
    }
}