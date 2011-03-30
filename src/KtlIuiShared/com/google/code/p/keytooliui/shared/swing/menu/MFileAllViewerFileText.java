/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
 
 
package com.google.code.p.keytooliui.shared.swing.menu;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.*;

import java.awt.event.*;

final public class MFileAllViewerFileText extends MFileAllViewerFileAbs
{    
    // ------
    // PUBLIC
    
    
    
    public boolean doFileNew()
        throws Exception
    {        
        if (! super.doFileNew())
            return false;
            
        this._mimPrint.setEnabled(true);
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        if (! super.doFileOpen())
            return false;
            
        this._mimPrint.setEnabled(true);
        return true;
    }
    
    public MFileAllViewerFileText(
        ActionListener actListenerParentFrame,
        ActionListener actListenerParentPanel)
    {
        super(actListenerParentFrame);
        
        this._mimPrint = new MIPrint(actListenerParentPanel);
    }
 
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! this._mimPrint.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        _addChildren();
        
        if (! super.init())
            return false;
        
        _disableChildrenAtInit();
    
        // ending
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._mimPrint != null)
        {
            this._mimPrint.destroy();
            this._mimPrint = null;
        }
    }
    
    // -------
    // PRIVATE
    
    
    // --------
    // children
    private MIAbstract _mimPrint = null;
    
    private void _addChildren()
    {
        add(this._mimPrint);
    } 
    
    private void _disableChildrenAtInit() 
    {
        this._mimPrint.setEnabled(false);
    }
}