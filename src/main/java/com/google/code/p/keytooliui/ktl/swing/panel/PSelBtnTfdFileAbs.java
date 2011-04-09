/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**
 
    contains radio buttons for file extension selection
 
    known subclasses:
    
    . PSelBtnTfdFileSaveAbs
    . PSelBtnTfdFileOpenAbs
**/



import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.swing.button.*;

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

public abstract class PSelBtnTfdFileAbs extends PSelBtnTfdAbs implements
    ItemListener
{
    // ----------------------
    // PROTECTED STATIC FINAL
    
    protected static final String _f_s_strSuffixFileDesc_ = " files";
    
    // ------
    // PUBLIC
    
    // radioButton: clearing textfield, and disabling btnClear
    public void itemStateChanged(ItemEvent evtItem) 
    {
        String strMethod = "itemStateChanged(evtItem)";
        
        if (! (evtItem.getSource() instanceof JRadioButton))
            MySystem.s_printOutExit(this, strMethod, "wrong source instance, \n evtItem.getSource().getClass().toString()=" + evtItem.getSource().getClass().toString());
       
        if (super._tfdCurSelection_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._tfdCurSelection_");
            
        super._tfdCurSelection_.setText("");
        super._setSelectedValue_(false);
        
        if (super._btnClearSelection_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._btnClearSelection_");
        
        super._btnClearSelection_.setEnabled(false);
    }
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // added sept 6, 2002
        super.setEnabledSelect(true);
     
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (super._tfdCurSelection_ != null)
        {
            ((TFAbstract)super._tfdCurSelection_).destroy();
            super._tfdCurSelection_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected PSelBtnTfdFileAbs(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent,

        String strLabel,
        boolean blnFieldRequired
        )
    {
        super(
            frmParent, 
       
            strLabel,
            blnFieldRequired
            );
            
        super._tfdCurSelection_ = new TF30x20SelFile(docListenerParent);
        
    }
}