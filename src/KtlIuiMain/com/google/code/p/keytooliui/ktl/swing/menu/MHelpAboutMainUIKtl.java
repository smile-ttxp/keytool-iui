/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
    
    contains 2 menuItems:
    . aboutProject
    . aboutAppli
    
    created in this classe
    
    inited, added, and destroyed in superclass
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;

final public class MHelpAboutMainUIKtl extends MHelpAboutMainUIAbs
{
    // ------
    // PUBLIC
           
    public MHelpAboutMainUIKtl(
        java.awt.Component cmpFrameOwner,
        String strTitleApplication,
        java.awt.event.ActionListener actListenerParent,
        String strLic)
    {
        super(actListenerParent);
        
        super._miaAboutAppli_ = new MIHelpAboutAppliUIKtl(cmpFrameOwner, strTitleApplication, strLic);
    }
}