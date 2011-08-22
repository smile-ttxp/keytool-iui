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
    . MBViewerFileTextAbs
    . MBViewerFileMediaAbs
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;

import javax.swing.*;


public abstract class MBViewerFileAbs extends JMenuBar
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        if (this._menFile_ != null)
        {
            this._menFile_.destroy();
            this._menFile_ = null;
        }
        
        if (this._menHelpAbout_ != null)
        {
            this._menHelpAbout_.destroy();
            this._menHelpAbout_ = null;
        }
    }
    
    public boolean doFileOpen()
        throws Exception
    {
        String strMethod = "doFileOpen()";
        
        if (! this._menFile_.doFileOpen())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        setEnabled(true);

        return true;
    }
    
    public boolean init()
    {
        String strMethod = "init()";

        // --
        
        if (this._menFile_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._menFile_");
            return false;
        }
        
        if (! this._menFile_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
      
        // --
      
        if (this._menHelpAbout_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._menHelpAbout_");
            return false;
        }
        
        if (! this._menHelpAbout_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----       
    
        add(this._menFile_);
    
        add(this._menHelpAbout_);

        setEnabled(false);
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected MFileAllViewerFileAbs _menFile_ = null;   
    protected MHelpAboutAbstract _menHelpAbout_ = null;
    
    protected MBViewerFileAbs()
    {
    }
}