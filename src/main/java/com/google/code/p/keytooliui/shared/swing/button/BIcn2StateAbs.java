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
 

 
package com.google.code.p.keytooliui.shared.swing.button;

/**
    used in status bars, 2 icons, 2 tooltips,

    known subclasses:
    . shared: BIcn2StateRtpStreaming
**/

import com.google.code.p.keytooliui.shared.lang.*;


import javax.swing.*;

import java.awt.event.*;

abstract public class BIcn2StateAbs extends BAbs
{
    // --------------
    // STATIC PRIVATE
    
    static private ImageIcon _s_iin0 = 
        com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("circlered11.gif");
        
    static private ImageIcon _s_iin1 = 
        com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("circlegreen11.gif");
       
    // ------
    // PUBLIC
    
    public boolean getStateOn() { return this._blnStateOn; }
    
    public void setStateOn(boolean bln)
    { 
        this._blnStateOn = bln;
        
        if (this._blnStateOn)
        {
            setToolTipText(this._strTip1);
            setIcon(_s_iin1);
        }
        
        else
        {
            setToolTipText(this._strTip0);
            setIcon(_s_iin0);
        }
    }
    
    public boolean init() 
    {
        setEnabled(true);
        return true; 
    }

    // ---------
    // PROTECTED
    
    protected BIcn2StateAbs(
        String strTip0, 
        String strTip1,
        java.awt.event.ActionListener alr)
    {
        super((String) null, strTip0, alr);
        
        setIcon(_s_iin0);
        
        this._strTip0 = strTip0;
        this._strTip1 = strTip1;
        
        setToolTipText(strTip0);
            
        setBorderPainted(false);
        
        addMouseListener(new MouseAdapter()
	    {
            public void mouseEntered(MouseEvent evtMouse)
            {
                setBorderPainted(true);
            }
            
            public void mouseExited(MouseEvent evtMouse)
            {
                setBorderPainted(false);
            }
        });
            
        setEnabled(false);
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnStateOn = false;
    private String _strTip0 = null;
    private String _strTip1 = null;
}