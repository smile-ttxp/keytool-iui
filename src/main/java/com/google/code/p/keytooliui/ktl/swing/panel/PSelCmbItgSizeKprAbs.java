/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
known subclasses:
    
    . kst: PSelCmbItgSizeKprDsa 
    . kst: PSelCmbItgSizeKprRsa 
    
    
    a panel that displays, from left to right:
    
    . 1 label
    . 1 combobox with an array of Integer, KeyPair size
    
**/

import com.google.code.p.keytooliui.shared.swing.panel.*;


public abstract class PSelCmbItgSizeKprAbs extends PSelCmbItgAbs
{    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTextLabel = "Key size (bits):";
    
    // ---------
    // PROTECTED   
    
    
    protected PSelCmbItgSizeKprAbs(
        Integer[] itgsSizeListKpr,
        Integer itgSizeKprDefault
        )
    {
        super(
            PSelCmbItgSizeKprAbs._f_s_strTextLabel,
            itgsSizeListKpr,
            itgSizeKprDefault // coz may take time to compute using: itgsSizeListKpr.length-1 // top-most value for "setSelected(...)"
            ); 
    }
}