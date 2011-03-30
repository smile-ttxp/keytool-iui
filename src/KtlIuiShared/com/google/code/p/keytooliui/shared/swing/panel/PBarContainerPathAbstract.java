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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PBarContainerPathAbsolute (shared)
    . PBarContainerPathRelative (shared)

**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

abstract public class PBarContainerPathAbstract extends PBarContainerAbstract 
{ 
    // ------
    // PUBLIC
    
    
    public boolean setVisiblePath(boolean bln)
    {
        String f_strMethod = "setVisiblePath(bln)";
        
        if (this._bpr_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._bpr_");
            return false;
        }
        
        return this._bpr_.setVisibleIcon(bln);
    }

    
    public boolean setPath(String strPath)
    {
        String f_strMethod = "setPath(strPath)";
        
        if (this._bpr_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._bpr_");
            return false;
        }
        
        return this._bpr_.setPath(strPath);
    }
    
   
    
    // ---------
    // PROTECTED
    
    protected PBarPathAbstract _bpr_ = null;
    
    protected PBarContainerPathAbstract()
    {        
        super();
    }
    
    protected boolean _init_()
    {
        String f_strMethod = "_init_()";
        
        if (this._bpr_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._bpr_");
            return false;
        }
        
        if (! this._bpr_.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
       
        setLayout(new BorderLayout());
        
        this.add(this._bpr_, BorderLayout.EAST);
        
        // --
        this.setMinimumSize(new Dimension(this.getMinimumSize().width, PBarContainerAbstract._f_s_intH_));
        this.setPreferredSize(getMinimumSize());
    
        // ending
        return true;
    }
    
    protected void _destroy_()
    {        
        if (this._bpr_ != null)
        {
            this._bpr_.destroy();
            this._bpr_ = null;
        }
    }
}