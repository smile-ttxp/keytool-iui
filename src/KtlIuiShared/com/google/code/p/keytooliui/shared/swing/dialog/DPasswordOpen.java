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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    "Open", like "file.open()".
**/

import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;

import java.awt.*;

final public class DPasswordOpen extends DPasswordAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTitleThis = "get password";
    
    final static private String _f_s_strTextLabel = "Enter password:";
    
    // ------
    // PUBLIC
    
    
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // ----
        super._pnlContents_.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        super._pnlContents_.add(this._lbl);
        super._pnlContents_.add(this._pfd);
        
        return true;
    }
    
    
    
    public DPasswordOpen(
        Component cmpFrameOwner,
        boolean blnNoPasswdAllowed // added february 8, 2008 ==> allow keystores without passwords
            )
    {
        super(
            cmpFrameOwner, 
            DPasswordOpen._f_s_strTitleThis);
            
        this._lbl = new JLabel(DPasswordOpen._f_s_strTextLabel);
        this._pfd = new JPasswordField(12);
        this._blnNoPasswdAllowed = blnNoPasswdAllowed;
    }
    
    // ---------
    // PROTECTED
    
    protected boolean _contentsOk_()
    {
        //String strMethod = "_contentsOk_()";
        
        String str = new String(this._pfd.getPassword());
        
        if (str.length() > 0)
        {
            super._chrsPassword_ = str.toCharArray();
            return true;
        }
        
        if (this._blnNoPasswdAllowed)
        {
            super._chrsPassword_ = "".toCharArray();
            return true;
        }
        
        OPAbstract.s_showDialogWarning(this, "Please enter password");
       
        
        return false;
    }
    
    // -------
    // PRIVATE
    
    private JLabel _lbl = null;
    private JPasswordField _pfd = null;
    private boolean _blnNoPasswdAllowed = false;
}