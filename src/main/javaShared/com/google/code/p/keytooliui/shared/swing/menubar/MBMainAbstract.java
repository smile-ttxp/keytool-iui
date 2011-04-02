/*
 *
 * Copyright (c) 2001-2009 keyTool IUI Project.
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
    . MBMainReader
    . MBMainGenAbstract
    . MBMainUIKtl
    
    contains the following menus (created in subclasses):
    . file
    . preferences
    . tools
    . help
    
    IMPORTANT:
    only menuFile is added here!
    all others are added in subclasses.
**/


import java.awt.event.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;

import javax.swing.*;

abstract public class MBMainAbstract extends JMenuBar 
        // test
        implements MouseListener
{
    // ------
    // PUBLIC
    
    // test
    public void mouseClicked(MouseEvent e) 
    {   
    }
    
    public void mousePressed(MouseEvent e) 
    {   
    }
    
    public void mouseReleased(MouseEvent e) 
    {   
    }
    
    public void mouseEntered(MouseEvent e) 
    {   
        System.out.println("MBMainAbstract.mouseEntered");
    }
    
    public void mouseExited(MouseEvent e) 
    {   
        System.out.println("MBMainAbstract.mouseExited");
    }
            
    
    
    // menubar's menuItem'
    public void setEnabledHelpSourceAndTrack(javax.help.HelpBroker hbrHelpStandard)  
    {
       if (this._menHelp_ != null)
           this._menHelp_.setEnabledHelpSourceAndTrack(hbrHelpStandard);
    }
    
    // called by parent
    // just do it for components that are not children (not added)
    public void updateTreeUI()
    {   
        if (this._faaFile_ != null)
        {
            this._faaFile_.updateTreeUI();
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        // ----
        
        if (this._faaFile_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._faaFile_");
            return false;
        }
        
        if (! this._faaFile_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._menHelp_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._hamHelp_");
            return false;
        }
        
        if (! this._menHelp_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pamPreference_ != null)
        {
            if (! this._pamPreference_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        if (this._mimTools_ != null)
        {
            if (! this._mimTools_.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        // ----
        
        add(this._faaFile_);
    
        // ending
        return true;
    }
    
    public void destroy()
    {
        if (this._faaFile_ != null)
        {
            this._faaFile_.destroy();
            this._faaFile_ = null;
        }
        
        
        
        if (this._pamPreference_ != null)
        {
            this._pamPreference_.destroy();
            this._pamPreference_ = null;
        }
        
        if (this._mimTools_ != null)
        {
            this._mimTools_.destroy();
            this._mimTools_ = null;
        }
        
        
        
        if (this._menHelp_ != null)
        {
            this._menHelp_.destroy();
            this._menHelp_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected MFileAllAbstract _faaFile_ = null;
    protected MPrefAllMainAbstract _pamPreference_ = null; // TO BE REMOVED
    protected MToolAllMainAbstract _mimTools_ = null; // nil value allowed
    protected MHelpAllMainAbs _menHelp_ = null;
    
    protected MBMainAbstract(String strHelpID)
    {
        super();
        
        // test
        addMouseListener(this);

        if (strHelpID != null)
            com.google.code.p.keytooliui.shared.help.MyCSH.setHelpIDString(this, strHelpID);
    }
}