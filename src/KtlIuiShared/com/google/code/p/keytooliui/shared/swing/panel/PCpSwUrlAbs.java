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

/*
    displays contents of page in a secondary window
    
    "Sw" means "Secondary Window"
    
    Known subclasses:
    . PCpSwUrlTextAbs
    . PCpSwUrlMediaAbs
    
    
*/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;
import java.awt.event.*;

abstract public class PCpSwUrlAbs extends PCpSwAbs 
{
    
    
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";  
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESPageReload16)
        {
            if (super._pnlPage_ != null)
            {
                if (! super._pnlPage_.pageReload())
                {
                    MySystem.s_printOutWarning(this, strMethod, "failed");
                    com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
                    
	                com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
	                    super._cmpFrameOwner_, 
	                    super._strTitleAppli_, 
	                    "Failed to reload page.");
                }
            }
            
            // ending
            return;
        }
            
        super.actionPerformed(evtAction);
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
            
        if (super._tbrToolbar_ != null)
            super._tbrToolbar_.setEnabledPageReload(true);
            
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    protected PCpSwUrlAbs(
        Component cmpFrameOwner, 
        String strTitleAppli)
    {
        super(cmpFrameOwner, strTitleAppli);
    }
    
    // -------
    // PRIVATE
    
   
}