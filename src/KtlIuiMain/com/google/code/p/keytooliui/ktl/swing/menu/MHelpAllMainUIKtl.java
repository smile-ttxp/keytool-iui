/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
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
 

 
package com.google.code.p.keytooliui.ktl.swing.menu;

/**
  
    
    contains:
    . 1 menu helpOffline
    . 1 menu helpOnline
    . 1 menu about
    
    children are:
    . created in this class,
    . inited, added, and destroyed in superclass
**/

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;

import com.google.code.p.keytooliui.shared.swing.menu.*;

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

final public class MHelpAllMainUIKtl extends MHelpAllMainUIAbs
{    
    // ------
    // PUBLIC
    
    
    
    public MHelpAllMainUIKtl(
        java.awt.Component cmpFrameOwner,
        String strTitleApplication,
        //javax.help.HelpBroker hbrHelpStandard,
        java.awt.event.ActionListener actListenerParent,
        String strLic)
    {
        super(cmpFrameOwner, strTitleApplication/*, hbrHelpStandard*/, strLic);
        
        
        // march 14, 2003: in comments coz not cross-platforms
        //super._matHelpOnline_ = new MHelpOnlineUIKtl(cmpFrameOwner, strTitleApplication);
        
        super._matAbout_ = new MHelpAboutMainUIKtl(cmpFrameOwner, strTitleApplication, actListenerParent, strLic);
        
        if (super._matHelpOffline_ != null)
           ((MHelpOffline) super._matHelpOffline_).getItemHelpOnItem().setText("Help on active window's task");
        
    }
}