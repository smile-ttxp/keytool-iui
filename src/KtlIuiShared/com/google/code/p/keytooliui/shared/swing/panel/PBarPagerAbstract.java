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
    . PBarPagerReader (rcr)
    . PBarPagerNote (shared)

**/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

abstract public class PBarPagerAbstract extends JPanel 
{    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intW = 100;
    
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".PBarPagerAbstract" // class name
        ;
    
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    // ----------------
    // STATIC PROTECTED
    
    static protected String _s_strLabelTextPrefix_;
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strLabelTip;
    
    // ------
    // STATIC
    

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.panel.PBarPagerAbstract";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strLabelTextPrefix_ = rbeResources.getString("labelTextPrefix");
            _s_strLabelTip = rbeResources.getString("labelTip");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + ", excMissingResource caught");
        }
    }
    
    // ------
    // PUBLIC
    
    public boolean setPageNb(int intNb)
    {
        String f_strMethod = "setPageNb(intNb)";
        
        if (intNb < 1)
        {
            MySystem.s_printOutError(this, f_strMethod, "wrong, intNb=" + intNb);
            return false;
        }
        
        this._intPageNb_ = intNb;
        
        if (this._lblPageId_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._lblPageId_");
            return false;
        }
        
        
        if (this._intPageNb_ != -1)
            this._lblPageId_.setText(_s_strLabelTextPrefix_ + this._intPageId_ + "/" + this._intPageNb_);
        else
            this._lblPageId_.setText(_s_strLabelTextPrefix_ + this._intPageId_);
    
        // ending
        return true;
    }
    
    
    public boolean setPageId(int intId)
    {
        String f_strMethod = "setPageId(intId)";
        
        if (intId < 1)
        {
            MySystem.s_printOutError(this, f_strMethod, "wrong, intId=" + intId);
            return false;
        }
        
        this._intPageId_ = intId;
        
        if (this._lblPageId_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._lblPageId_");
            return false;
        }
        
        if (this._intPageNb_ != -1)
            this._lblPageId_.setText(_s_strLabelTextPrefix_ + this._intPageId_ + "/" + this._intPageNb_);
        else
            this._lblPageId_.setText(_s_strLabelTextPrefix_ + this._intPageId_);
        
        return true;
    }
    
    public boolean setVisibleIcon(boolean bln)
    {
        String f_strMethod = "setVisibleIcon(bln)";
        
        if (this._lblPageId_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._lblPageId_");
            return false;
        }
        
        this._lblPageId_.setVisible(bln);
        return true;
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (this._lblPageId_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._lblPageId_");
            return false;
        }
        
        this._lblPageId_.setText("dummy text");
        this._lblPageId_.setToolTipText(_s_strLabelTip);
        this._lblPageId_.setVisible(false);
        
        // --
        
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEmptyLightLowered2(this);
        setLayout(new BorderLayout());
        this.add(this._lblPageId_, BorderLayout.CENTER);
        
        this.setPreferredSize(getMinimumSize());
        
        return true;
    }
    
    public void destroy()
    {
    }
    
    // ---------
    // PROTECTED
    
    protected JLabel _lblPageId_ = null;
    protected int _intPageId_ = -1; // dummy value
    protected int _intPageNb_ = -1; // dummy value
    
    protected PBarPagerAbstract(int intH, Color colFg, Font fnt)
    {
        this._lblPageId_ = new JLabel();
        this._lblPageId_.setForeground(colFg);
        this._lblPageId_.setFont(fnt);
        
        this.setMinimumSize(new Dimension(_f_s_intW, intH));
    }
}