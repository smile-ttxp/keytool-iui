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

final public class MChgAlignTabsMainUIKtl extends MChgAlignTabsMainUIAbs
{     
    // ------
    // PUBLIC    
    
    /**
        IMPORTANT: 
        first assign selected item, 
        then do super.init() ==> adding listener
    **/
    public boolean init()
    {
        String strMethod = "init()";
        
        // ------------
        // select child
        if (ChgLocMainUITabsKtl.S_INT_LAST == ChgLocMainUITabsKtl.F_S_INT_TOP)
            super._rbmAlignTop_.setSelected(true);
        
        else if (ChgLocMainUITabsKtl.S_INT_LAST == ChgLocMainUITabsKtl.F_S_INT_LEFT)
            super._rbmAlignLeft_.setSelected(true);
        
        else if (ChgLocMainUITabsKtl.S_INT_LAST == ChgLocMainUITabsKtl.F_S_INT_BOTTOM)
            super._rbmAlignBottom_.setSelected(true);
          
        else if (ChgLocMainUITabsKtl.S_INT_LAST == ChgLocMainUITabsKtl.F_S_INT_RIGHT)
            super._rbmAlignRight_.setSelected(true);
           
        else
        {
            MySystem.s_printOutError(this, strMethod, "unknown value, ChgLocMainUITabsKtl.S_INT_LAST=" + ChgLocMainUITabsKtl.S_INT_LAST);
            return false;
        }
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    public MChgAlignTabsMainUIKtl(java.awt.event.ItemListener itmListenerParent)
    {
        super(itmListenerParent);    
    }
}