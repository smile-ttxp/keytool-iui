/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.button;

/**

    used by toolBars

    known subclasses:
    . BESHelpJHAbstract
    . BESExit24
    . BESPrintAbstract
    . BESFileOpenAbs
    . rcr: BESPrjRdrAbs
    
    
    roll-over effect (border drawn on mouseOver)
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


import java.awt.event.*;
import java.beans.*;

public class BEnabledState extends JButton implements
        MouseListener
{
    // ------
    // PUBLIC
    
    public void mouseReleased(MouseEvent evtMouse) {}
    public void mousePressed(MouseEvent evtMouse) {}
    public void mouseClicked(MouseEvent evtMouse) {}
    
    public void mouseEntered(MouseEvent evtMouse)
    { 
        if (isEnabled())
        {
            setBorderPainted(true);
            setContentAreaFilled(true);
        }
    }

    public void mouseExited(MouseEvent evtMouse)
    {
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
    
    public void destroy()
    {
        removeMouseListener(this);
        
        if (this._alr != null)
        {
            this.removeActionListener(this._alr);
            this._alr = null;
        }
        
        if (this._prl != null)
        {
            this.removePropertyChangeListener(this._prl);
            this._prl = null;
        }
    }

    public void setEnabledEvent(boolean bln)
    {
        if (bln)
        {
            this.setEnabled(this._blnEnabledState);
        }
        
        else
        {
            this._blnEnabledState = this.isEnabled();
            setEnabled(false);
        }
    }
    
    public BEnabledState(ActionListener alr)
    {
        this();
        
        this._alr = alr;
        
        if (this._alr != null)
            addActionListener(this._alr);
    }
    
    
    public BEnabledState(javax.swing.ImageIcon iin, ActionListener alr, PropertyChangeListener prl)
    {
        this(iin, alr);
        
        this._prl = prl;
        
        if (this._prl != null)
            addPropertyChangeListener(this._prl);
    }
    
    public BEnabledState(javax.swing.ImageIcon iin, ActionListener alr)
    {
        this(iin);
        
        this._alr = alr;
            addActionListener(this._alr);
    }
    
    public BEnabledState(javax.swing.ImageIcon iin)
    {
        this();
        
        String strMethod = "BEnabledState(iin)";
        
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
        
        setIcon(iin);
    }
    
    public BEnabledState()
    {
        setBorderPainted(false);
        this.setContentAreaFilled(false);
        addMouseListener(this);
        
        /** catching memory leaks ! no effects, remaining this
         * this.addMouseListener(new java.awt.event.MouseAdapter()
	    {
            public void mouseEntered(java.awt.event.MouseEvent evtMouse)
            { 
                if (isEnabled())
                {
                    setBorderPainted(true);
                    setContentAreaFilled(true);
                }
            }
            
            public void mouseExited(java.awt.event.MouseEvent evtMouse)
            {
                setBorderPainted(false);
                setContentAreaFilled(false);
            }
            
        });
        **/
        
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnEnabledState = false;
    private ActionListener _alr = null;
    private PropertyChangeListener _prl = null;
}