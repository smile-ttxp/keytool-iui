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
    "Save", like "file.save()". ==> create new password
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;

import java.awt.*;

public final class DPasswordConfirmSave extends DPasswordConfirmAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitleThis = "create password";

    // ------
    // PUBLIC
    
    
    public DPasswordConfirmSave(
        Component cmpFrameOwner)
    {
        super(
            cmpFrameOwner, 

            DPasswordConfirmSave._f_s_strTitleThis); 
    }
    
    // ---------
    // PROTECTED
    

    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        String strEnterNew = new String(super._pfdEnterNew_.getPassword());
        String strConfirmNew = new String(super._pfdConfirmNew_.getPassword());
        
        if (strEnterNew.length() < 1)
        {
            OPAbstract.s_showDialogWarning(this, "Please enter new password");
            
            return false;
        }
        
        if (strConfirmNew.length() < 1)
        {
            OPAbstract.s_showDialogWarning(this, "Please confirm new password");
            
            return false;
        }
        
        if (strEnterNew.compareTo(strConfirmNew) != 0)
        {
            OPAbstract.s_showDialogWarning(this, "Passwords do not match");
            
            return false;
        }
        
        super._chrsPassword_ = strEnterNew.toCharArray();
        return true;
    }
    
    // -------
    // PRIVATE
    
}