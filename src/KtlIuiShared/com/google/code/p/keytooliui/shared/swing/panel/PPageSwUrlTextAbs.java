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

/*
    displays contents of page in a secondary window, contains a scrollpane
    
    "SW" means "Secondary Window"
    
    Known subclasses:
    . PPageSwUrlTextHTML
    . PPageSwUrlTextRTF
    
*/

import com.google.code.p.keytooliui.shared.swing.scrollpane.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;
import java.net.*;

abstract public class PPageSwUrlTextAbs extends PPageSwAbs
{
    // ------
    // PUBLIC
    
    public boolean pageClose()
    {
        String strMethod = "pageClose()";
        
        if (this._spn_ != null)
        {
            JViewport vpt = this._spn_.getViewport();
            
            if (vpt == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil vpt");
                return false;
            }
             
          
            Component cmpView = vpt.getView();
            
            if (cmpView != null) 
                vpt.remove(cmpView);
        }
        
        return true;
    }
    
    public boolean findText()
    {
        String strMethod = "findText()";
        
        if (this._spn_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spn_");
            return false;
        }
        
        if (! this._spn_.findText())
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spn_");
            return false;
        }
        
        return true;
    }
    
    public boolean pagePrint()
    {
        String strMethod = "pagePrint()";
        
        if (this._spn_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spn_");
            return false;
        }
        
        if (! this._spn_.pagePrint())
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spn_");
            return false;
        }
        
        return true;
    }
    
    public boolean pageReload()
    {
        String strMethod = "pageReload()";
        
        if (this._spn_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spn_");
            return false;
        }
        
        if (! this._spn_.pageReload())
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spn_");
            return false;
        }
        
        return true;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        if (this._spn_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._spn_");
            return false;
        }
        
        if (! this._spn_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
        add("Center", this._spn_);
        return true;
    }
    
    public void destroy()
    {
        if (this._spn_ != null)
        {
            this._spn_.destroy();
            this._spn_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected SPPageTextSWAbs _spn_ = null;

    
    protected PPageSwUrlTextAbs(
        URL url,
        java.awt.Window winParent,
        String strTitleAppli)
    {
        super(strTitleAppli, winParent, (Object) url);

        setLayout(new BorderLayout());
    }
}