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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;


import com.google.code.p.keytooliui.shared.swing.button.*;

import java.awt.event.*;

final public class TBPageSecWinUrlTextHTML extends TBPageSecWinUrlTextAbs
{
    // ------
    // PUBLIC
    
    public void setEnabledHistBack(boolean bln)
    {
        if (this._btnBack == null)
            return;
            
        this._btnBack.setEnabled(bln);
    }
    
    public void setEnabledHistForward(boolean bln)
    {
        if (this._btnForward == null)
            return;
            
        this._btnForward.setEnabled(bln);
    }
    
    public void destroy()
    {
        super.destroy();
        this._btnBack = null;
        this._btnForward = null;
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // start
        addSeparator(TBAbs._f_s_dimSeparator4_);
        add(this._btnBack);
        //addSeparator(TBAbs._f_s_dimSeparator4_);
        add(this._btnForward);
        
        this._btnBack.setEnabled(false);
        this._btnForward.setEnabled(false);
        
        // ending
        return true;
    }

    public TBPageSecWinUrlTextHTML(
        ActionListener actListenerParentPanel,
        ActionListener actListenerParentFrame)
    {
        super(actListenerParentPanel);
        
        this._btnBack = new BESBack16(actListenerParentFrame);
        this._btnForward = new BESForward16(actListenerParentFrame);
    }   
    
    // -------
    // PRIVATE
    
    private BEnabledState _btnBack = null;
    private BEnabledState _btnForward = null;
}