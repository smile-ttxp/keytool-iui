/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.textfield;

/**
    "PF" means "Password Field"

    . columns = 10
    . height = 20
**/


import com.google.code.p.keytooliui.shared.lang.*;

final public class PF10x20 extends PFAbs
{  
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intColumns = 10; // 75; // 10;
    final static private int _f_s_intH = 20;
    
    
    final static private String _f_s_strTip = "current password value (not editable textfield)";
    final static private String _f_s_strDefault = "";
    
    // ------
    // PUBLIC
    
    public void setDefault()
    {
        setText(PF10x20._f_s_strDefault);
    }
    
    public boolean isDefault()
    {
        String strMethod = "isDefault()";
        
        if (getPassword() == null)
            MySystem.s_printOutExit(this, strMethod, "getPassword() == null");
            
        String str = new String(getPassword());
            
        if (str.compareTo(PF10x20._f_s_strDefault) == 0)
            return true;
            
        return false;
    }
    
    public PF10x20(javax.swing.event.DocumentListener docListenerParent)
    {
        super(PF10x20._f_s_strTip, PF10x20._f_s_intColumns, PF10x20._f_s_intH, docListenerParent);
        setDefault(); 
    }
}