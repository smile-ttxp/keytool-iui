/*
 *  Copyright (C) 2001-2011 keyTool IUI Project
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author bantchao
 *
 *
 */
 
 
package com.google.code.p.keytooliui.ktl.swing.button;

/*
    a button that just contains icons, not text!
*/


import java.awt.Font;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

final public class BIcnAction extends JButton
{   
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strIconEnabled = "_bluelgt.gif";
    final static private String _f_s_strIconDisabled = "_graylgt.gif";
    final static private String _f_s_strIconRollover = "_blue.gif";
    final static private String _f_s_strIconPressed = "_bluedrk.gif";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strTipEnabled = "action button enabled";
    static private String _s_strTipDisabled = "action button disabled, becomes enabled once all required fields are filled";
        
    // ------
    // PUBLIC

    public void setEnabled(boolean bln)
    {
        if (bln)
            setToolTipText(BIcnAction._s_strTipEnabled);
        else
            setToolTipText(BIcnAction._s_strTipDisabled);
            
        super.setEnabled(bln);
    }
    
    public BIcnAction(java.awt.event.ActionListener actListenerParent)
    {
        String strMethod = "BIcnAction(...)";
        
        if (actListenerParent != null)
            addActionListener(actListenerParent);
          
        
        // ----
        
        setRolloverEnabled(false);
        setFocusPainted(false);
        setContentAreaFilled(false); // icon only
        setBorderPainted(false);
        setEnabled(false);
        
        // --
        
        ImageIcon iin = null;
        
        
        iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(BIcnAction._f_s_strIconEnabled);
         
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
       
        setIcon(iin);
        
        // ----
        
        iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(BIcnAction._f_s_strIconDisabled);
      
         
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
       
        setDisabledIcon(iin);
        
        
        // ----
        
        iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(BIcnAction._f_s_strIconRollover);
         
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
       
        setRolloverIcon(iin);
        
        // ----
        
        iin = com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(BIcnAction._f_s_strIconPressed);
         
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
       
        setPressedIcon(iin);
        
        // new stuff
        Font fnt = new java.awt.Font(java.awt.Font.DIALOG_INPUT, /*java.awt.Font.ITALIC|*/java.awt.Font.BOLD, 16);
        setText("OK");
        this.setIconTextGap(10);
        this.setFont(fnt);
    }
}