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
 
 
 package com.google.code.p.keytooliui.shared.swing.textfield;

/**
    "PF" means "Password Field"

    . columns = 10
    . height = 20
**/


import com.google.code.p.keytooliui.shared.lang.*;

public final class PF10x30 extends PFAbs
{  
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final int _f_s_intColumns = 10; // 75; // 10;
    private static final int _f_s_intH = 30;
    
    
    private static final String _f_s_strTip = "current password value (not editable textfield)";
    private static final String _f_s_strDefault = "";
    
    // ------
    // PUBLIC
    
    public void setDefault()
    {
        setText(PF10x30._f_s_strDefault);
    }
    
    public boolean isDefault()
    {
        String strMethod = "isDefault()";
        
        if (getPassword() == null)
            MySystem.s_printOutExit(this, strMethod, "getPassword() == null");
            
        String str = new String(getPassword());
            
        if (str.compareTo(PF10x30._f_s_strDefault) == 0)
            return true;
            
        return false;
    }
    
    public PF10x30(javax.swing.event.DocumentListener docListenerParent)
    {
        super(PF10x30._f_s_strTip, PF10x30._f_s_intColumns, PF10x30._f_s_intH, docListenerParent);
        setDefault(); 
    }
}