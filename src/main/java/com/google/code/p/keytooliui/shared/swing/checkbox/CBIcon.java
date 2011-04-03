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
 
 
package com.google.code.p.keytooliui.shared.swing.checkbox;

/**
    known subclasses:
    . rcr: CBISmoothMotionBmkRdr
    . rcr: CBISelfCloseFolderBmkRdr
    . rcr: CBINavVisibleRdr
    . rcr: CBISndfxOnRdr
**/


import com.google.code.p.keytooliui.shared.lang.*;
 
import javax.swing.*;
 
import java.awt.event.*;
 
public class CBIcon extends CBAbs
 {
    // ------
    // PUBLIC
    
    public void setEnabledEvent(boolean bln)
    {
        if (bln)
        {
            this.setEnabled(this._blnEnabledState);
        }
        
        else
        {
            this._blnEnabledState = this.isEnabled();
            setEnabled(false);
        }
    }
    
    public CBIcon(ActionListener actListenerParent, ImageIcon iin, String strTip)
    {
        super(actListenerParent, strTip);
        
        String strMethod = "CBIcon(...)";
        
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
            
        setIcon(iin);
	    // FIXING UP BUG jdk1.3.0-final (not in jdk1.2.2 or in jdk1.3.0beta) ...
	    setSelectedIcon(iin);
	    setDisabledIcon(null);
	    setDisabledSelectedIcon(null);
	    // ... if disabled & selected, icon not shown as grayed
        
        setBorderPainted(true);
        
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnEnabledState = false;
 }