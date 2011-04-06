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
 
 
 package com.google.code.p.keytooliui.shared.swing.menu;

/**
    known subclasses:
    ??
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

abstract public class MAbstract extends JMenu
{
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    abstract public void destroy();
    
    // ---------
    // PROTECTED
    
    // tempo
    static private boolean _s_blnDumpDevInfo = false;
    
    protected MAbstract()
    {
        /*
            added march 7, 2002
            in order to Swing-based menus overlapping AWT-based stuffs like JMF player
            
            WARNING: be aware of SUN's bug parade: 4168483
        */
        String strMethod = "MAbstract()";
        
        if (! _s_blnDumpDevInfo)
        {
            _s_blnDumpDevInfo = true;
            MySystem.s_printOutFlagDev(this, strMethod, "added for JMF handling");
        }
        
        getPopupMenu().setLightWeightPopupEnabled(false);
    }
}