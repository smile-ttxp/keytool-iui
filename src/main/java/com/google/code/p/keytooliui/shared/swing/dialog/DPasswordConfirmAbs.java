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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    known subclasses:
    
    . DPasswordConfirmCreate
    . DPasswordConfirmChange
**/

import javax.swing.*;

import java.awt.*;

abstract public class DPasswordConfirmAbs extends DPasswordAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTextLabelEnterNew = "Enter new password:";
    final static private String _f_s_strTextLabelConfirmNew = "Confirm new password:";
    
    // ------
    // PUBLIC

    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // ----
        super._pnlContents_.setLayout(new GridLayout(2, 2, 5, 5)); 
        super._pnlContents_.add(this._lblEnterNew_);
        super._pnlContents_.add(this._pfdEnterNew_);
        super._pnlContents_.add(this._lblConfirmNew_);
        super._pnlContents_.add(this._pfdConfirmNew_);

        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected JLabel _lblEnterNew_ = null;
    protected JLabel _lblConfirmNew_ = null;
    
    protected JPasswordField _pfdEnterNew_ = null;
    protected JPasswordField _pfdConfirmNew_ = null;
    
    protected DPasswordConfirmAbs(
        Component cmpFrameOwner,

        String strTitleThis)
    {
        super(cmpFrameOwner, strTitleThis);

        this._lblEnterNew_ = new JLabel(DPasswordConfirmAbs._f_s_strTextLabelEnterNew);
        this._lblConfirmNew_ = new JLabel(DPasswordConfirmAbs._f_s_strTextLabelConfirmNew);
        
        this._pfdEnterNew_ = new JPasswordField(12);
        this._pfdConfirmNew_ = new JPasswordField(12);
    }
}