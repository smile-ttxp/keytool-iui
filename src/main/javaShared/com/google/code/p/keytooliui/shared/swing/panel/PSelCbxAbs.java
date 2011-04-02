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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**
known subclasses:
    
    . din: PSelCbxTestOffline
    
    
    a panel that displays, from left to right:
    
    . 1 label
    . 1 checkbox
    
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

abstract public class PSelCbxAbs extends PSelAbs
{
    // ------
    // PUBLIC   
    
    public void destroy()
    {
        super.destroy();
        this._cbx = null;
    }
    
    public boolean isSelectedItem()
    {
        if (this._cbx == null)
            return false;
            
        return this._cbx.isSelected();
    }
    
    public boolean init()
    {        
        if (! super.init())
            return false;
        
	    _addItem();

        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
   
    
    
    protected PSelCbxAbs(
        String strLabel
        )
    {
        super(
            strLabel, 
            false // blnFieldRequired
            ); 
        
        this._cbx = new JCheckBox();
    }
    
    // -------
    // PRIVATE
    
    private JCheckBox _cbx = null;
    
    private void _addItem()
    {
        Dimension dimWidthIcon = new Dimension(16+8,16+8);
        
        super._pnl_.add(Box.createRigidArea(dimWidthIcon));
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
        super._pnl_.add(Box.createRigidArea(dimWidthIcon));
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
        // --
        
        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
	    super._pnl_.add(this._cbx);	
        
    }
    
}