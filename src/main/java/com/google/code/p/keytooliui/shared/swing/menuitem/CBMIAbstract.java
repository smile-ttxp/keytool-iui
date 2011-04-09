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
    a checkBoxMenuItem that allows to show/hide the navigation pane of the active document
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

public abstract class CBMIAbstract extends JCheckBoxMenuItem
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        removeActionListener(this._actListenerParent);
        this._actListenerParent = null;
    }
    
    // ---------
    // PROTECTED
    
    protected CBMIAbstract(String strText, ActionListener actListenerParent)
    {
        String strMethod = "CBMIAbstract(strText, actListenerParent)";
        
        this._actListenerParent = actListenerParent;
        
        if (strText == null)
            MySystem.s_printOutExit(this, strMethod, "nil strText");
        
        setText(strText); 
        
        
        if (this._actListenerParent == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._actListenerParent");
            
        addActionListener(this._actListenerParent);
    }
    
    private ActionListener _actListenerParent = null;
}