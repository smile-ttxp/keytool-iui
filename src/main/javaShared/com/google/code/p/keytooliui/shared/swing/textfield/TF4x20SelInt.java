/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
    textfield:
    for use to display the current selection of a integer
**/

import com.google.code.p.keytooliui.shared.lang.*;


final public class TF4x20SelInt extends TF4x20Abs
{   
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strTip = "current integer value (not editable textfield)";
    final static private String _f_s_strDefault = "";
    // ------
    // PUBLIC
    
    public void setDefault()
    {
        setText(TF4x20SelInt._f_s_strDefault);
    }
    
    public boolean isDefault()
    {
        String strMethod = "isDefault()";
        
        if (getText() == null)
            MySystem.s_printOutExit(this, strMethod, "getText() == null");
            
        if (getText().compareTo(TF4x20SelInt._f_s_strDefault) == 0)
            return true;
            
        return false;
    }
    
    public TF4x20SelInt(javax.swing.event.DocumentListener docListenerParent)
    {
        super(TF4x20SelInt._f_s_strTip, docListenerParent);
        setDefault();
       
    }
}