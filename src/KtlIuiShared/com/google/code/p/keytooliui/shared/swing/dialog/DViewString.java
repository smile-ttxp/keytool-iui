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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

final public class DViewString extends DEscapeAbstract implements
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
        
        this._tpeContents = null;
        
        super.destroy();
    }
    
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (! (evtAction.getSource() instanceof BClose))
            MySystem.s_printOutExit(this, strMethod, "wrong source instance");

        super._cancel_(); 
    }

    
    public boolean init()
    {
        String strMethod = "init()";

        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());
        
        if (this._tpeContents == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._tpeContents");
            return false;
        }
        
        if (this._tpeContents.getText() == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._tpeContents.getText()");
            return false;
        }
        
        this._tpeContents.setEditable(false);
        
        pnl.add(this._tpeContents, BorderLayout.CENTER);

	    JScrollPane spnContent = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spnContent.getViewport().add(pnl); 
        
        // --------------  
        
        JPanel pnlCloseThis = _createPanelCloseThis();
        
        // -------
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        
        JPanel pnlAll = new JPanel();
        // --- BEGIN border
	    
	    com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEtched(pnlAll);
	    
	    // --- END border
	    cntContentPane.add(pnlAll, BorderLayout.CENTER);

        /**
            modif 17/1/1, bad display under linux RH6.1/KDE/jdk1.3:
            . contents height too small,
            . button located on the middle of the screen
            ==> changing layout Box to Border
        **/
        // NEW
        pnlAll.setLayout(new BorderLayout());
	    pnlAll.add(spnContent, BorderLayout.CENTER);
	    pnlAll.add(pnlCloseThis, BorderLayout.SOUTH);


    	    
	    pnlAll.setPreferredSize(new Dimension(500, 400));
        
        // ending
        return true;
    }
   
    
    public DViewString(Component cmpFrameOwner, String strSource)
    {
        super((Frame) cmpFrameOwner, true); // true ==> modal=true
        
        this._btnClose = new BClose((ActionListener) this);
        
        setTitle(System.getProperty("_appli.title"));
                
        this._tpeContents = new JTextPane();
        // --
        
        this._tpeContents.setText(strSource);
    }
    
    // -------
    // PRIVATE
        
    private JTextPane _tpeContents = null;
    private BAbs _btnClose = null;
    
    private JPanel _createPanelCloseThis()
    {
        JPanel pnl = new JPanel();
        
        pnl.add(this._btnClose);
        return pnl;
    }
}
