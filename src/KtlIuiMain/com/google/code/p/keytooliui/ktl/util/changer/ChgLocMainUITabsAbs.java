/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.util.changer;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;
import com.google.code.p.keytooliui.shared.util.changer.*;


import javax.swing.*;

import java.io.*;

abstract public class ChgLocMainUITabsAbs extends ChgLocMainUIAbs
{
    // ---------
    // PROTECTED
    
    protected ChgLocMainUITabsAbs(FMainAbs fmn)
    {
        super(fmn);
    }
    
    protected boolean _set_(int intValue)
    {
        String strMethod = "_set_(intValue)";
        
        if (super._fmn_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._fmn_");
            return false;
        }
        
        java.awt.Container cnt = super._fmn_.getContentPane();
        
        if (cnt == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil cnt");
            return false;
        }
        
        if (! (cnt instanceof JPanel))
        {
            MySystem.s_printOutError(this, strMethod, "! (cnt instanceof JPanel)");
            return false;
        }
        
        JPanel pnlContentPane = (JPanel) cnt;       
        
        // there should be 2 components: 
          // . toolBar,
          // . panel
           
        //   panel should contain:
        //   . tabbedPane
        //   . status bar (panel)
        
        
        
        java.awt.Component[] cmpsChild = pnlContentPane.getComponents();
        
        if (cmpsChild == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil cmpsChild");
            return false;
        }
        
        // --
        
        JPanel pnlSub = null;
        
        for (int i=0; i<cmpsChild.length; i++)
        {
            java.awt.Component cmpCur = cmpsChild[i];
            
            if (cmpCur instanceof JPanel)
            {
                pnlSub = (JPanel) cmpCur;
                break;
            }
        }
        
        if (pnlSub == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil pnlSub");
            return false;
        }
        
        // --
        
        cmpsChild = pnlSub.getComponents();
        
        if (cmpsChild == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil cmpsChild");
            return false;
        }
        
        JTabbedPane tpn = null;
        
        for (int i=0; i<cmpsChild.length; i++)
        {
            java.awt.Component cmpCur = cmpsChild[i];
            
            if (cmpCur instanceof JTabbedPane)
            {
                tpn = (JTabbedPane) cmpCur;
                break;
            }
        }
        
        if (tpn == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil tpn");
            return false;
        }
        
        if (tpn.getTabPlacement() == intValue)
        {
            MySystem.s_printOutTrace(this, strMethod, "already selected, intValue=" + intValue);
            return true;
        }
        
        tpn.setTabPlacement(intValue);
        tpn.validate();
        
        return true;
    }
}