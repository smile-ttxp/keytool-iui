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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/**
    either file of type HTML
    or file of type RTF
**/


import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

final public class TBViewerFileText extends TBViewerFileAbs
{      
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnPrint != null)
        {
            this._btnPrint.destroy();
            this._btnPrint = null;
        }
    }
    
    public boolean init()
    {   
        _addChildren();

        if (! super.init())
            return false;
            
            
        _disableChildrenAtInit();
        
        // ending
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {    
        
        if (! super.doFileOpen())
            return false;
        
        this._btnPrint.setEnabled(true);
       
    
        return true;
    }
    
    public TBViewerFileText(
        ActionListener actListenerParentFrame,
        ActionListener actListenerParentPanel)
    {
        super(actListenerParentFrame);
        
        this._btnPrint = new BESPrint24(actListenerParentPanel);
    }
 
    // -------
    // PRIVATE
    
    // children

    private BESPrint24 _btnPrint = null;
    
    private void _addChildren()
    {   
        addSeparator(TBAbs._f_s_dimSeparator10_);
        add(this._btnPrint);
    }
    
    private void _disableChildrenAtInit()
    {
        this._btnPrint.setEnabled(false);
    }
}