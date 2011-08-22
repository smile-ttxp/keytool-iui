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
 
 
package com.google.code.p.keytooliui.shared.swing.menubar;

/**
    known subclasses:
    . MBEditorDefault
    . MBEditorNote
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;

import javax.swing.*;

public abstract class MBEditorAbstract extends JMenuBar
{
    // ---------------
    // ABSTRACT PUBLIC
    
    public abstract boolean doFileNew() throws Exception;
    public abstract boolean doFileOpen() throws Exception;
    public abstract boolean init();
    public abstract void destroy();
    
    // ------
    // PUBLIC

    // ---------
    // PROTECTED
    
    protected MHelpAboutAbstract _nhp_ = null;
    
    protected boolean _insert_(java.awt.Component com)
    {
        String f_strMethod = "_insert_(com)";
        
        if (com == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil com");
            return false;
        }
        
        if (add(com, this._intInsertPos) == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        this._intInsertPos ++;
        
        // ending
        return true;
    }
    
    protected boolean _doFileNew_()
        throws Exception
    {
        String f_strMethod = "_doFileNew_()";
        
        if (! this._nfl.doFileNew())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }      
        
        setEnabled(true);
        return true;
    }
    
    protected boolean _doFileOpen_()
        throws Exception
    {
        String f_strMethod = "_doFileOpen_()";
        
        if (! this._nfl.doFileOpen())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        setEnabled(true);
        return true;
    }
    
    
    protected MBEditorAbstract(java.awt.event.ActionListener actListenerParent, MFileAllEditorListener nfeListenerParent)
    {
        this._nfl = new MFileAllEditor(actListenerParent, nfeListenerParent);
    }
    
    protected boolean _init_()
    {
        String f_strMethod = "_init_()";

        // --
        
        if (this._nfl == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._nfl");
            return false;
        }
        
        if (! this._nfl.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
      
        // --
      
        if (this._nhp_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._nhp_");
            return false;
        }
        
        if (! this._nhp_.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        // ----

        add(this._nfl); this._intInsertPos ++;
        add(this._nhp_);

        setEnabled(false);
        
        // ending
        return true;
    }
    
    protected void _destroy_()
    {
        if (this._nfl != null)
        {
            this._nfl.destroy();
            this._nfl = null;
        }
        
        if (this._nhp_ != null)
        {
            this._nhp_.destroy();
            this._nhp_ = null;
        }
    }
    
    
    // -------
    // PRIVATE
    private MFileAllEditor _nfl;
    private int _intInsertPos = 0;
}