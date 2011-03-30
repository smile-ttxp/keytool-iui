/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.button;

/*
    a button that just contains icons, not text!
*/


import java.awt.Font;
import com.google.code.p.keytooliui.shared.swing.button.*;
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
        super();
        
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