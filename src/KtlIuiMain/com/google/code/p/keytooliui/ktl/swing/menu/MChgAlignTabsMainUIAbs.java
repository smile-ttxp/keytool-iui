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
 
 
package com.google.code.p.keytooliui.ktl.swing.menu;

/**
    a menu for changing alignments:
    . top,
    . bottom
    . left,
    . right

    created in order to distinguish between different menuAlignTabs while getting a itemListener
**/

import com.google.code.p.keytooliui.ktl.util.changer.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

abstract public class MChgAlignTabsMainUIAbs extends MChgAlignTabsAbstract
{     
    // ---------
    // PROTECTED
    
    protected MChgAlignTabsMainUIAbs(java.awt.event.ItemListener itmListenerParent)
    {
        super();
               
        // ---------------
        // create children
        super._rbmAlignTop_ = new RBMIAlignTopTabsMain(itmListenerParent);
        super._rbmAlignLeft_ = new RBMIAlignLeftTabsMain(itmListenerParent);
        super._rbmAlignBottom_ = new RBMIAlignBottomTabsMain(itmListenerParent);
        super._rbmAlignRight_ = new RBMIAlignRightTabsMain(itmListenerParent);       
    }
}