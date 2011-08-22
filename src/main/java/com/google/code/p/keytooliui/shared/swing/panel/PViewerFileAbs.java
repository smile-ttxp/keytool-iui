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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PViewerFileTextAbs
    . PViewerFileMediaAbs
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menubar.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;

import javax.swing.*;

import java.awt.*;
import java.net.*;

public abstract class PViewerFileAbs extends JPanel
{   
    // ------
    // PUBLIC
    
    public void destroy()
    {        
        if (this._mbr_ != null)
        {
            this._mbr_.destroy();
            this._mbr_ = null;
        }
        
        if (this._tbr_ != null)
        {
            this._tbr_.destroy();
            this._tbr_ = null;
        }
    }

    // ----
    
    public boolean init()
    {
        String strMethod = "init()";
        
        // --
        
        /**if (this._nsl_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._nsl_");
            return false;
        }**/
        
        // IMPORTANT MEMO: super._nsl_.init() already done in subclasses, creators
        
        // --
        
        if (this._mbr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mbr_");
            return false;
        }
        
        if (! this._mbr_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._tbr_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._tbr_");
            return false;
        }
        
        if (! this._tbr_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // ----
        
        setBorder(BorderFactory.createEtchedBorder());
	    setLayout(new BorderLayout());
        
        JPanel pnlContents = new JPanel();
	    pnlContents.setLayout(new BorderLayout());
	    
	    pnlContents.add("North", this._tbr_);
	    
	    if (this._nsl_ != null)
	        pnlContents.add("Center", this._nsl_);
	    
	    add("North", this._mbr_);
	    add("Center", pnlContents);
        
        setEnabled(false);
        return true;
    }
    
    // ---------
    // PROTECTED
    
    // --------
    // children
    protected TBViewerFileAbs _tbr_ = null;
    protected MBViewerFileAbs _mbr_ = null;
    protected JComponent _nsl_ = null;
    
    
    
 
    protected Component _cmpFrameOwner_ = null;
        
    
    protected boolean _doFileOpen_(URL url)
    {
        String strMethod = "_doFileOpen_(url)";
    
        try
        {
            if (this._tbr_ == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil this._tbr_");
                return false;
            }
        
            if (! this._tbr_.doFileOpen())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            if (this._mbr_ == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil this._mbr_");
                return false;
            }
            
            if (! this._mbr_.doFileOpen())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            return false;
        }
        
        setEnabled(true);
        
        // ending
        return true;
    }
    
    protected PViewerFileAbs(
        Component cmpFrameOwner)
    {
        super(true); // double buffering
        this._cmpFrameOwner_ = cmpFrameOwner;

    }
}