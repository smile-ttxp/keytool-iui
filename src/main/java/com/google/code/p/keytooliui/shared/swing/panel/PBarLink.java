/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
    . PBarLinkRdr
**/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

public class PBarLink extends JPanel 
{  
    // ------
    // PUBLIC
    
    public boolean showStatus(String str)
    {
        String strMethod = "showStatus(str)";
        
        // maybe check if visible too!!
        
        if (this._lblStatus == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._lblStatus");
            return false;
        }
        
        this._lblStatus.setText(str);
        
        return true;
    }
    
    public PBarLink(int intH, Color colFg, Font fnt)
    {
        this.setMinimumSize(new Dimension(this.getMinimumSize().width, intH));   
        this._lblStatus = new JLabel();
        this._lblStatus.setForeground(colFg);
        this._lblStatus.setFont(fnt);
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._lblStatus == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._lblStatus");
            return false;
        }
        
        this._lblStatus.setText("");
        this.setPreferredSize(getMinimumSize());
        
        
        
        setLayout(new BorderLayout());
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEmptyLightLowered2(this);
        this.add(this._lblStatus, BorderLayout.WEST);
    
        // ending
        return true;
    }
    
    public void destroy()
    {
        this._lblStatus = null;
    }
    
    // -------
    // PRIVATE
    
    private JLabel _lblStatus = null;
}