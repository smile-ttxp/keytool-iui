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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/**
    known subclasses:
    
    . TBViewerFileText
    . TBViewerFileMedia
**/


import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

public abstract class TBViewerFileAbs extends TBAbs
{
    // ------
    // PUBLIC
    
    public boolean init()
    {   
        _addChildren();
        
        setEnabled(false);
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._btnExit != null)
        {
            this._btnExit.destroy();
            this._btnExit = null;
        }
    }
    
    public boolean doFileOpen()
        throws Exception
    {    
        this._btnExit.setEnabled(true);
 
        setEnabled(true);
    
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected TBViewerFileAbs(ActionListener actListenerParentFrame)
    {
        super(SwingConstants.HORIZONTAL, false);
        
        this._btnExit = new BESExit24(actListenerParentFrame);
    }
    
    // -------
    // PRIVATE
    
    private BESExit24 _btnExit = null;
    
    private void _addChildren()
    {   
        addSeparator(TBAbs._f_s_dimSeparator10_);
        add(Box.createHorizontalGlue());
        add(this._btnExit);
        addSeparator(TBAbs._f_s_dimSeparator10_);
    }
}