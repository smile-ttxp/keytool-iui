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


import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.event.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.net.*;

final public class PCpSwUrlTextHTML extends PCpSwUrlTextAbs
{
    // ------
    // PUBLIC
    
    public void setStatusText(String str)
    {
        if (this._pnlStatus != null)
            this._pnlStatus.setStatusText(str);
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._pnlStatus != null)
        {
            this._pnlStatus.destroy();
            this._pnlStatus = null;
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        if (this._pnlStatus == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlStatus");
            return false;
        }
        
        if (! this._pnlStatus.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
            
        add(this._pnlStatus, java.awt.BorderLayout.SOUTH);
        this._pnlStatus.addNotify();
        
        validate();
        
        return true;
    }
    
    public void setEnabledButtonHistBack(boolean bln)
    {
        if (super._tbrToolbar_ == null)
            return;
            
        ((TBPageSecWinUrlTextHTML) super._tbrToolbar_).setEnabledHistBack(bln);
    }
    
    public void setEnabledButtonHistForward(boolean bln)
    {
        if (super._tbrToolbar_ == null)
            return;
            
        ((TBPageSecWinUrlTextHTML) super._tbrToolbar_).setEnabledHistForward(bln);
    }
    
    
    
    // used while cliked on a link inside active HTML page
    public boolean select(URL url)
    {
        super._tbrToolbar_.setEnabledPageReload(false);
        ((TBPageSecWinUrlTextAbs) super._tbrToolbar_).setEnabledFindText(false);
        ((TBPageSecWinUrlTextAbs) super._tbrToolbar_).setEnabledPagePrint(false);
        
        if (! ((PPageSwUrlTextHTML) super._pnlPage_).select(url))
            return false;
        
        
        super._tbrToolbar_.setEnabledPageReload(true);
        ((TBPageSecWinUrlTextAbs) super._tbrToolbar_).setEnabledFindText(true);
        ((TBPageSecWinUrlTextAbs) super._tbrToolbar_).setEnabledPagePrint(true);
        
        return true;
    }
    
    public PCpSwUrlTextHTML(
        URL url,
        Component cmpFrameParent,
        String strTitleAppli,
        HyperlinkListener hypListenerParent,
        ActionListener actListenerParentFrame,
        Color colTextSelection)
    {
        super(cmpFrameParent, strTitleAppli);
        
        super._tbrToolbar_ = new TBPageSecWinUrlTextHTML((ActionListener) this, actListenerParentFrame);
        super._pnlPage_ = new PPageSwUrlTextHTML(url, (JFrame) cmpFrameParent, strTitleAppli, hypListenerParent, colTextSelection);
        
        this._pnlStatus = new PBarContainerSec();
    }
    
    // -------
    // PRIVATE
    
    private PBarContainerSec _pnlStatus = null;
}