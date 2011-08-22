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
 
 
 package com.google.code.p.keytooliui.shared.swing.scrollpane;

/**
    known subclasses:
    . SPEditorStyled
    . SPEditorDefaultSys
    . SPEditorDefaultJar
**/

import com.google.code.p.keytooliui.shared.swing.textpane.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

public abstract class SPEditorAbstract extends JScrollPane
{    
    // ---------------
    // ABSTRACT PUBLIC
    
    // ------
    // PUBLIC
    
    public boolean printContent()
    {
        String f_strMethod = "printContent()";
        
        if (this._ntt_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._ntt_");
            return false;
        }
        
        return this._ntt_.printContent();
    }
    
    public boolean doFileNew()
        throws Exception
    {
        String f_strMethod = "doFileNew()";
        
        if (! this._ntt_.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        setEnabled(true);
        return true;
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (this._ntt_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._ntt_");
            return false;
        }
        
        if (! this._ntt_.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        JViewport port = getViewport();

	    port.add(this._ntt_); 
	    
    
        /** deprecated in jdk13final
	    port.setBackingStoreEnabled(false);
        **/
        
        port.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
         
        setEnabled(false);
        
        // ending
        return true;
    }
    
    public void destroy()
    {
        
        if (this._ntt_ != null)
        {
            this._ntt_.destroy();
            this._ntt_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected SPEditorAbstract()
    {
    }
    
    protected TPEditorAbstract _ntt_ = null;
}