/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

package com.google.code.p.keytooliui.applets;


/**
   "AAbs" means "Applet, abstract class"


    known subclasses:
    . A2SwAbs (contains a button)
    . AMediaAbs (contains a panel)
**/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

abstract public class AAbs extends JApplet
{
    // ------
    // PUBLIC
    
    public void start()
    {
        
        String strMethod = "start()";
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        super.start();
    }
    
    public void destroy()
    {
        
        String strMethod = "destroy()";
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        super.destroy();
    }
    
    public void stop() 
    {
        String strMethod = "stop()";
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        
        if (this._cmpBean_ != null) 
        {
            getContentPane().remove(this._cmpBean_);
            this._cmpBean_ = null;
        }
        
        super.stop();
    }
    
    
    // ---------
    // PROTECTED
    
    protected JComponent _cmpBean_ = null;
    
    protected boolean _init_()
    {
        String strMethod = "_init_()";
        
        // ---------
        // IMPORTANT
        MySystem.s_printOutTrace(this, strMethod, "..., calling super.init()");
        super.init();
        // ----
        
        
        if (this._cmpBean_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._cmpBean_");
            return false;
        }
        
        getContentPane().add(this._cmpBean_);
        
        return true;
    }
    
    protected AAbs()
    {
        super();
        
        String strMethod = "AAbs()";
        MySystem.s_printOutTrace(this, strMethod, "...");
        
        getContentPane().setLayout(new BorderLayout());
    }
}