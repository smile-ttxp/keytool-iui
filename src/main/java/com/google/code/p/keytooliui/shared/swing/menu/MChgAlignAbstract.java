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
 
 
 package com.google.code.p.keytooliui.shared.swing.menu;

/**
    a menu for changing alignments:
    . top,
    . bottom
    . left,
    . right

    known subclasses:
    . MChgAlignToolAbstract
    . MChgAlignTabsAbstract
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;


public abstract class MChgAlignAbstract extends MAbstract
{ 
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._rbmAlignTop_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._rbmAlignTop_");
            return false;
        }
        
        if (! this._rbmAlignTop_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._rbmAlignBottom_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._rbmAlignBottom_");
            return false;
        }
        
        if (! this._rbmAlignBottom_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._rbmAlignLeft_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._rbmAlignLeft_");
            return false;
        }
        
        if (! this._rbmAlignLeft_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._rbmAlignRight_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._rbmAlignRight_");
            return false;
        }
        
        if (! this._rbmAlignRight_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // 1) group the buttons
        javax.swing.ButtonGroup bgp = new javax.swing.ButtonGroup();
        bgp.add(this._rbmAlignTop_);
        bgp.add(this._rbmAlignLeft_);
        bgp.add(this._rbmAlignBottom_);
        bgp.add(this._rbmAlignRight_);
            
        
        
        // 4) add buttons
        add(this._rbmAlignTop_);
        add(this._rbmAlignLeft_);
        add(this._rbmAlignBottom_);
        add(this._rbmAlignRight_);
        return true;
    }

    public void destroy()
    {
        if (this._rbmAlignTop_ != null)
        {
            this._rbmAlignTop_.destroy();
            this._rbmAlignTop_ = null;
        }
        
        if (this._rbmAlignLeft_ != null)
        {
            this._rbmAlignLeft_.destroy();
            this._rbmAlignLeft_ = null;
        }
        
        if (this._rbmAlignBottom_ != null)
        {
            this._rbmAlignBottom_.destroy();
            this._rbmAlignBottom_ = null;
        }
        
        if (this._rbmAlignRight_ != null)
        {
            this._rbmAlignRight_.destroy();
            this._rbmAlignRight_ = null;
        }   
    }

    
    
    // ---------
    // PROTECTED
    
    
    protected RBMIAlignAbstract _rbmAlignTop_ = null;
    protected RBMIAlignAbstract _rbmAlignLeft_ = null;
    protected RBMIAlignAbstract _rbmAlignBottom_ = null;
    protected RBMIAlignAbstract _rbmAlignRight_ = null;
        
    protected MChgAlignAbstract(String strText)
    {
        setText(strText);
    }
}
