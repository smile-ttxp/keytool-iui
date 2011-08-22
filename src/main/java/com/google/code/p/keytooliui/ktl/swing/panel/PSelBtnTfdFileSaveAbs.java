/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**
 
    contains radio buttons for file extension selection
 
    known subclasses:
    
    . PSelBtnTfdFileSaveKst
**/




import com.google.code.p.keytooliui.shared.swing.button.*;


import java.awt.*;

public abstract class PSelBtnTfdFileSaveAbs extends PSelBtnTfdFileAbs
{
    // ---------
    // PROTECTED
    
    protected PSelBtnTfdFileSaveAbs(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent,
       
        String strLabel,
        boolean blnFieldRequired
        )
    {
        super(
            docListenerParent,
            frmParent, 
       
            strLabel,
            blnFieldRequired
            );
            
        super._btnSelect_ = new BESFileSave16(this);
    }
}