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

/*
    displays contents of page in a secondary window
    
    contains:
    . at top: toolbar
    . centered: panel (display page)
    
    "CP" means "Content Pane"
    "SW" means "Secondary Window"
    
    Known subclasses:
    . PCpSwUrlAbs
    . PCpSwRtpAbs
    
*/

import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

abstract public class PCpSwAbs extends JPanel implements
    ActionListener
{    
    // ------
    // PUBLIC
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BESPageAbout16)
        {
             if (this._pnlPage_ != null)
             {
                this._pnlPage_.showAbout();
             }
            
            // ending
            return;
        }
        
        MySystem.s_printOutExit(this, strMethod, 
            "instance of evtAction.getSource() not caught, evtAction.getSource().getClass().toString()=" + 
            evtAction.getSource().getClass().toString());
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        boolean blnGotChild = false;
        
        if (this._tbrToolbar_ != null)
        {
            blnGotChild = true;
            
            if (! this._tbrToolbar_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            add(this._tbrToolbar_, java.awt.BorderLayout.NORTH);
            this._tbrToolbar_.addNotify();
        }
        
        if (this._pnlPage_ != null)
        {
            blnGotChild = true;
        
            if (! this._pnlPage_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            add(this._pnlPage_, java.awt.BorderLayout.CENTER);
            this._pnlPage_.addNotify();
        }
        
        
        
        if (blnGotChild)
            validate();
        
        return true;
    }
    
    public void destroy()
    {
        if (this._pnlPage_ != null)
        {
            this._pnlPage_.destroy();
            this._pnlPage_ = null;
        }
        
        if (this._tbrToolbar_ != null)
        {
            this._tbrToolbar_.destroy();
            this._tbrToolbar_ = null;
        } 
    }
    
    // ---------
    // PROTECTED
    
    protected PPageSwAbs _pnlPage_ = null;
    protected TBPageSecAbs _tbrToolbar_ = null;
    
    protected Component _cmpFrameOwner_ = null;
    protected String _strTitleAppli_ = null;
    
    protected PCpSwAbs(Component cmpFrameOwner, String strTitleAppli)
    {
        super();
        
        this._cmpFrameOwner_ = cmpFrameOwner;
        this._strTitleAppli_ = strTitleAppli;
        
        setDoubleBuffered(true);
        setLayout(new java.awt.BorderLayout());
    }
    
    // -------
    // PRIVATE
    
    
}