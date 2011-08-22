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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;

/*

    DIImage dialog info image
    
    shows an image in a scrollpane


/** 

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public final class DIImage extends DEscapeAbstract implements
    ActionListener
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        if (this._btnClose != null)
        {
            this._btnClose.destroy();
            this._btnClose = null;
        }
        
        super.destroy();
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (! (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BClose))
            MySystem.s_printOutExit(this, strMethod, "wrong source instance");
      
        super._cancel_();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._iin");
            return false;
        }
        
        
        JLabel lbl = new JLabel();
        lbl.setIcon(this._iin);
        
        
        lbl.setHorizontalAlignment(JLabel.CENTER);
        
        JScrollPane spn = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spn.setViewportView(lbl);
        
        
        // BUTTON
        
        this._btnClose = new BClose((ActionListener) this);
      
        
        // ??
        this._btnClose.setMaximumSize(this._btnClose.getPreferredSize());
        
        
        // panelImage
        
        JPanel pnlImage = new JPanel();
        pnlImage.setLayout(new java.awt.BorderLayout());
        
        EmptyBorder ebr = new EmptyBorder(20,30,20,30);
        BevelBorder bbr = new BevelBorder(BevelBorder.LOWERED);
        pnlImage.setBorder(new CompoundBorder(ebr, bbr));
        
        pnlImage.add(spn, java.awt.BorderLayout.CENTER);
        
        
        // panelButton
        
        JPanel pnlButton = new JPanel();
        pnlButton.add(this._btnClose);
        
        // panelAll
        
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new java.awt.BorderLayout());
	    
	    pnl.add(pnlImage, java.awt.BorderLayout.CENTER);
        
        
        pnl.add(pnlButton, java.awt.BorderLayout.SOUTH);
        
        pnl.setPreferredSize(new java.awt.Dimension(640, 500));
        
        // --
        setContentPane(pnl);
    
        
        
        return true;
    }
    
    
    public DIImage(
        Frame frm,
     
        String strTitle,
        ImageIcon iin)
    {
        super(frm, System.getProperty("_appli.title") + " - " + strTitle, true);
        
        this._iin = iin;
    }
    
    // -------
    // PRIVATE

    private ImageIcon _iin = null;
    private BAbs _btnClose = null;
}