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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PBarContainerPagerReader (rcr)
    . PBarContainerPagerNote (shared)

**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

abstract public class PBarContainerPagerAbstract extends PBarContainerAbstract 
{ 
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._bpr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._bpr_");
            return false;
        }
        
        if (! this._bpr_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
       
        setLayout(new BorderLayout());
        
        //this.add(this._bpr_, BorderLayout.CENTER);
        
        // --
        this.setMinimumSize(new Dimension(this.getMinimumSize().width, PBarContainerAbstract._f_s_intH_));
        
 
        // --
        
        this.setPreferredSize(getMinimumSize());
    
        // ending
        return true;
    }
    
    public void destroy()
    {        
        if (this._bpr_ != null)
        {
            this._bpr_.destroy();
            this._bpr_ = null;
        }
    }
    
    
    public boolean setVisiblePager(boolean bln)
    {
        String strMethod = "setVisiblePager(bln)";
        
        if (this._bpr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._bpr_");
            return false;
        }
        
        return this._bpr_.setVisibleIcon(bln);
    }

    
    public boolean setPageId(int intId)
    {
        String strMethod = "setPageId(intId)";
        
        if (this._bpr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._bpr_");
            return false;
        }
        
        return this._bpr_.setPageId(intId);
    }
    
    public boolean setPageNb(int intNb)
    {
        String strMethod = "setPageNb(intNb)";
        
        if (this._bpr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._bpr_");
            return false;
        }

        return this._bpr_.setPageNb(intNb);
    }
    
    // ---------
    // PROTECTED
    
    protected PBarPagerAbstract _bpr_ = null;
    
    protected PBarContainerPagerAbstract()
    {        
        super();
    }
    
    
}