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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**
    "P" means "Panel"

    showing status bar, eg:
    . HTMLs, mouse over link
    . RTP sessions, session state
**/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

public final class PBarStatus extends JPanel 
{    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final int _f_s_intW = 400;
       
    
    // ------
    // PUBLIC
    
    public boolean setStatus(String str)
    {
        String strMethod = "setStatus(str)";
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil str");
            return false;
        }
        
        if (this._lbl!=null && this._strLabelTip!=null)
        {
        
            this._lbl.setText(str);
            
            this._lbl.setToolTipText(this._strLabelTip + ": " + str);
            
            if (! this._lbl.isVisible())
                this._lbl.setVisible(true);
        }
    
        // ending
        return true;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._lbl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._lbl");
            return false;
        }
        
        this._lbl.setText("dummy text");
        this._lbl.setToolTipText(this._strLabelTip);
        this._lbl.setVisible(false);
        
        // --
        
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEmptyLightLowered2(this);
        setLayout(new BorderLayout());
        this.add(this._lbl, BorderLayout.CENTER);
        
        this.setPreferredSize(getMinimumSize());
        
        return true;
    }
    
    public void destroy()
    {
        this._lbl = null;
        this._strLabelTip = null;
    }
    
    
    
    
    public PBarStatus(int intH)
    {
        this._lbl = new JLabel();
        this._strLabelTip = new String("current status: ");
        
        this.setMinimumSize(new Dimension(_f_s_intW, intH));
    }
    
    // -------
    // PRIVATE
    
    private JLabel _lbl = null;
    private String _strLabelTip = null;
}