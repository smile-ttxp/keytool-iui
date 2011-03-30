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

/**
    used inside status bar

    known subclasses:
    . PIcn2StateRtpStreaming

**/


import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


import java.awt.*;


abstract public class PIcn2StateAbs extends JPanel 
{     
    // ------
    // PUBLIC
    
    public void setStateOn(boolean bln)
    {
        if (this._btnOnOff_ != null)
            this._btnOnOff_.setStateOn(bln);
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._btnOnOff_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnOnOff_");
            return false;
        }
        
        this.add(this._btnOnOff_, BorderLayout.CENTER);
        
        
        this._btnOnOff_.setMinimumSize(this._btnOnOff_.getPreferredSize());
        
        if (! this._btnOnOff_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }

    public void destroy()
    {
        
        if (this._btnOnOff_ != null)
        {
            this._btnOnOff_.destroy();
            this._btnOnOff_ = null;
        }
        
    }
    
    
    // ---------
    // PROTECTED
    
    protected BIcn2StateAbs _btnOnOff_ = null;
    
    protected PIcn2StateAbs(int intH)
    {
        // tempo
        super();
        
        this.setMinimumSize(new Dimension(intH, intH));
        this.setPreferredSize(getMinimumSize());
        
        setLayout(new BorderLayout());
        
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEmptyLightLowered0(this);
    } 
}