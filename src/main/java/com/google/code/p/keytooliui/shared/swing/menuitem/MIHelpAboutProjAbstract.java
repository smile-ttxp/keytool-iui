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
 
 
package com.google.code.p.keytooliui.shared.swing.menuitem;

/**
    known subclasses:
    . MIHelpAboutProjReader
    . MIHelpAboutProjGenDoc
    . MIHelpAboutProjGenTpl
    . MIHelpAboutProjUIKtl

    contains an icon
    register actionListener to its parent
**/

import com.google.code.p.keytooliui.shared.lang.*;


public abstract class MIHelpAboutProjAbstract extends MIAbstract
{
    // ------
    // PUBLIC
    
    public boolean init() 
    {
        if (! super.init())
            return false;
        /*
            assuming there is no project at init time!
        */
        setEnabled(false);
        return true; 
    }
    
    
    // ---------
    // PROTECTED
    
    protected MIHelpAboutProjAbstract(
        String strText,
        String strIcon,
        java.awt.event.ActionListener actListenerParent)
    {
        super(strText, actListenerParent);
        
        String strMethod = "MIHelpAboutProjAbstract(hbr, strText)";
        
        if (strIcon == null)
            MySystem.s_printOutExit(this, strMethod, "nil strIcon");
            
        javax.swing.ImageIcon iin = 
            com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strIcon);
        
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
        
        setIcon(iin);
        
        if (actListenerParent == null)
        {
            if (strText != null)
                MySystem.s_printOutExit(this, strMethod, "nil actListenerParent, strText=" + strText);
            else
                MySystem.s_printOutExit(this, strMethod, "nil actListenerParent, nil strText");
        }
    }
}