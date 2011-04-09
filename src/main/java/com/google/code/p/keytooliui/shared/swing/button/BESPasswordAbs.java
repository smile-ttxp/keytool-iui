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
 
 
package com.google.code.p.keytooliui.shared.swing.button;

/**
    known subclasses:
    . BESPassword16

**/

import com.google.code.p.keytooliui.shared.lang.*;


abstract public class BESPasswordAbs extends BEnabledState
{
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final int f_s_intModeSave = 1; // create
    public static final int f_s_intModeOpen = 2; // enter-select
    public static final int f_s_intModeChange = 3; // change password
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTipSave = "click to create new password";
    private static final String _f_s_strTipOpen = "click to enter password";
    private static final String _f_s_strTipChange = "change password";
    
    // ------
    // PUBLIC
    
    public int getMode() { return this._intMode; }
    
    // ---------
    // PROTECTED
        
    protected BESPasswordAbs(
        java.awt.event.ActionListener alr, 
        String strIcon,
        int intMode,
        int intDim)
    {
        super(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strIcon), alr);        
        
        String strMethod = "BESPasswordAbs(...)";
        
        this._intMode = intMode;
        
        if (intMode == BESPasswordAbs.f_s_intModeSave)
            setToolTipText(BESPasswordAbs._f_s_strTipSave);
            
        else if (intMode == BESPasswordAbs.f_s_intModeOpen)
            setToolTipText(BESPasswordAbs._f_s_strTipOpen);
            
        else
            setToolTipText(BESPasswordAbs._f_s_strTipChange);
        
        intDim += 8;
        java.awt.Dimension dim = new java.awt.Dimension(intDim, intDim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
    }
    
    // -------
    // PRIVATE
    
    private int _intMode = -1;
    
}