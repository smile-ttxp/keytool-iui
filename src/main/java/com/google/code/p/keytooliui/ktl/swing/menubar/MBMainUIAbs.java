/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

/*
 *  @author bantchao
 */
 
package com.google.code.p.keytooliui.ktl.swing.menubar;


import com.google.code.p.keytooliui.ktl.swing.menu.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import javax.swing.Box;



abstract public class MBMainUIAbs extends MBMainAbstract
{        
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        
        if (this._menView_ != null)
        {
            this._menView_.destroy();
            this._menView_ = null;
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // view
        
        if (this._menView_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._menView_");
            return false;
        }
        
        if (! this._menView_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // tools
        
        if (super._mimTools_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._mimTools_");
            return false;
        }
        
        
        // -----------
        // preferences
        /*if (super._pamPreference_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pamPreference_");
            return false;
        }*/
        
        
        // ----
        // help
        if (super._menHelp_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._hamHelp_");
            return false;
        }
        
        // adding
        add(this._menView_);
        
        if (super._pamPreference_ != null)
            add(super._pamPreference_);
        
        add(super._mimTools_);

        super.add(Box.createHorizontalGlue());
	super.add(Box.createVerticalGlue()); // !!
        add(super._menHelp_);
        
        // ending
        return true;
    }
    
    
    public void setEnabledMenus(boolean bln)
    {
        super._faaFile_.setEnabled(bln);
        super._mimTools_.setEnabled(bln);
    }
    
    

    // ---------
    // PROTECTED
    
    protected MViewAllMainAbs _menView_ = null;
    
    protected MBMainUIAbs(
        java.awt.Component cmpFrameOwner,
    
        java.awt.event.ActionListener actListenerParent
        )
    {
        super(
            (String) null // strHelpID
        );
        
        super._faaFile_ = new MFileAllUI(actListenerParent);
        
        
        super._mimTools_ = new MToolAllMainUI(
            (javax.swing.JFrame) cmpFrameOwner, actListenerParent);
        
 
    }
    
    
    
}