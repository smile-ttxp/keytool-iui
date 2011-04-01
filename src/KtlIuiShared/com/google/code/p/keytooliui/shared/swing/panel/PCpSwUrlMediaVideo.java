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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;


import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;
import java.net.*;
import java.awt.*;

final public class PCpSwUrlMediaVideo extends PCpSwUrlMediaAbs
{
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
            
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESFullScreen16)
        {
            if (super._pnlPage_ == null)
                return;
                    
            if (! ((PPageSwMediaUrlVideo) super._pnlPage_).setFullScreen(true))
                MySystem.s_printOutExit(this, strMethod, "failed");
            
            return;
        }
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.checkbox.CBIFullWindow16)
        {
            if (super._pnlPage_ == null)
                return;
                
            JCheckBox cbx = (JCheckBox) evtAction.getSource();
                    
            if (! ((PPageSwMediaUrlVideo) super._pnlPage_).setFullWindow(cbx.isSelected()))
                MySystem.s_printOutExit(this, strMethod, "failed");
            
            return;
        }
   
        super.actionPerformed(evtAction);   
    }
    
    public PCpSwUrlMediaVideo(
        URL url,
        String strTitleAppli,
        java.awt.Window winParent)
    {
        super((Component) winParent, strTitleAppli);
        
        super._tbrToolbar_ = new TBPageSecMediaUrlVideo(
            (ActionListener) this,
            true  // setSelectedLoop
            );
            
        super._pnlPage_ = new PPageSwMediaUrlVideo(
            url, 
            (javax.media.ControllerListener) super._tbrToolbar_,
            strTitleAppli, 
            winParent,
            true, // setSelectedReadyStart
            true, // setSelectedLoop
            this //cmpAncestor
            );
    }
}