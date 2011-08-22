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
 
 
package com.google.code.p.keytooliui.shared.swing.menu;

/**
    known subclasses:
    
    . MFileAllViewerFileText
    . MFileAllViewerFileMedia
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.*;

import java.awt.event.*;

public abstract class MFileAllViewerFileAbs extends MFileAllAbstract
{
    // ------
    // PUBLIC
    
    public void updateTreeUI() {}
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
        
        
        
        _addChildren();
        _disableChildrenAtInit();
    
        // ending
        return true;
    }
    
    public boolean doFileNew()
        throws Exception
    {        
        super._fet_.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        super._fet_.setEnabled(true);
        
        setEnabled(true);
        
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected MFileAllViewerFileAbs(ActionListener actListenerParentFrame)
    {
        super(actListenerParentFrame);
    }
    
    // -------
    // PRIVATE
    
    private void _addChildren()
    {
        addSeparator();
        add(super._fet_);
    }
    
    private void _disableChildrenAtInit() 
    {
        super._fet_.setEnabled(false); // ????????????????????
    }
}