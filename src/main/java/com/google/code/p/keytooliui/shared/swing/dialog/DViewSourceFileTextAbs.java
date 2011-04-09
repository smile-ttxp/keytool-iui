/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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

/**
    known subclasses:
    . DViewSourceFileTextJar
    . DViewSourceFileTextSys
    
    either files of type HTML or files of type RTF (even files of type CSS)

    note: if source comes from system, shows file name
          else (comes from JAR, shows relative path)
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


abstract public class DViewSourceFileTextAbs extends DEscapeAbstract implements
    ActionListener
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strFilePathNo = "file path: dummy";
    private static final String _f_s_strFileSourceNo = "file source: dummy";
    
    
    // ------
    // PUBLIC
    
    @Override
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

        _cancel(); 
    }

    
    
    @Override
    public void windowClosing(WindowEvent e)
    {
        _cancel();
    }
    
    public boolean init()
    {
        JPanel pnlDisplaySource = _createPanelFilePath();
        
        // --------------
        
        
        this._tpeFileSource = new JTextPane();
        this._tpeFileSource.setEditable(false);

        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout() );
        pnl.add(this._tpeFileSource, BorderLayout.CENTER);

	    JScrollPane scroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.getViewport().add(pnl); 
        
        // --------------  
        
        JPanel pnlOkButton = _createPanelOkButton();
        
        // -------
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        
        JPanel pnlAll = new JPanel();
        // --- BEGIN border
	    
	    com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEtched(pnlAll);
	    
	    // --- END border
	    cntContentPane.add(pnlAll, BorderLayout.CENTER);
	    
	    pnlAll.setLayout(new BoxLayout(pnlAll, BoxLayout.Y_AXIS));
	    pnlAll.add(pnlDisplaySource);
	    pnlAll.add(Box.createVerticalStrut(10));
	    pnlAll.add(scroller);
	    pnlAll.add(Box.createVerticalStrut(10));
	    pnlAll.add(pnlOkButton);
	    
	    
        
        // ending
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    protected DViewSourceFileTextAbs(Component cmpFrameOwner)
    {
        super((Frame) cmpFrameOwner, true); // true ==> modal=true

        this._btnClose = new BClose((ActionListener) this);
        
        String strTitleThis = "view source file";
        
        setTitle(System.getProperty("_appli.title") + " - " + strTitleThis);
    }
    
    
    protected boolean _show_(String strSource, String strName)
    {
        String strMethod = "_show_(strSource, strName)";
        
        if (strSource==null || strName==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }
        
        if (this._tpeFileSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._tpeFileSource");
            return false;
        }
        
        this._tpeFileSource.setText(strSource);
        
        if (this._lblFileName == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._lblFileName");
            return false;
        }
        
        this._lblFileName.setText(strName);
        
        
        super.setVisible(true);
        return true;
    }
    
    
    // -------
    // PRIVATE
    
    private JLabel _lblFileName = null;
    private JTextPane _tpeFileSource = null;
    
    private JPanel _createPanelFilePath()
    {
        JPanel pnl = new JPanel();
        this._lblFileName = new JLabel();
        this._lblFileName.setText(_f_s_strFilePathNo);
        pnl.add(this._lblFileName);
        return pnl;
    }
    
    private JPanel _createPanelOkButton()
    {
        JPanel pnl = new JPanel();
        
        this._btnClose = new BClose((ActionListener) this);
        
        pnl.add(this._btnClose);
        return pnl;
    }
    
    
    private void _cancel()
    {   
        super._cancel_();
        
        this._lblFileName.setText(_f_s_strFilePathNo);
        this._tpeFileSource.setText(_f_s_strFileSourceNo);
    }
    
    private BAbs _btnClose = null;
}