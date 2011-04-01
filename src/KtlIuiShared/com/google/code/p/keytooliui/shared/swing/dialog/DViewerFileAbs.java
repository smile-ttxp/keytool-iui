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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    IMPORTANT:
    
    do not change extension from JDIALOG to JFRAME,
    as this window is opened thanks to a dialog window.
    ==> if extending from JFRAME, then this frame will freeze, as the appli wait for actions on the dialog window
    
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    
    known subclasses:
    . DViewerFileTextAbs
    . DViewerFileMediaAbs
    
**/


import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

abstract public class DViewerFileAbs extends DEscapeAbstract implements
    ActionListener
{
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.menuitem.MIFileExit ||
            evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESExit24)
	    {
	        super._cancel_();
	                  
	        return;
	    }
	    
        MySystem.s_printOutExit(this, strMethod, "unknown source");
    }
   

    
    // ----
    
    public boolean init()
    {
        String strMethod = "init()";

        if (this._pnd_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnd_");
            return false;
        }
        
        if (! this._pnd_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        _addChildren();
    
        // ending
        return true;
    }
    
    public void destroy()
    {            
        if (this._pnd_ != null)
        {
            this._pnd_.destroy();
            this._pnd_ = null;
        }
        
        super.destroy();
        
        //System.gc();
    }
    
    
    // ---------
    // PROTECTED
    
    // --------
    // children
    protected PViewerFileAbs _pnd_ = null;
    
    protected String _strTitleApplication_ = null;

    
    
    protected DViewerFileAbs(
        Component cmpFrameOwner,
        String strTitleApplication,
        String strTitleSuffix)
    {
        super((Frame) cmpFrameOwner, true);
        
        this._strTitleApplication_ = strTitleApplication;
    }

    // -------
    // PRIVATE
    
        
    private boolean _addChildren()
    {
        String strMethod = "_addChildren()";
          // ----
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        
        if (this._pnd_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnd_");
            return false;
        }
        
        cntContentPane.add(this._pnd_, BorderLayout.CENTER);
    
        // ending
        return true;
    }
}