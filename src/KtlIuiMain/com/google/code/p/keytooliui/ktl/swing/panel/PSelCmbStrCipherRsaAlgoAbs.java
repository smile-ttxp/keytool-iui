/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    known subclasses:
    . PSelCmbStrCipherRsaAlgoAll
    
    
    a panel that displays, from left to right:
    
    . 1 label
    . 1 combobox with an array of String: Cipher algorithm
    
 *
 * MEMO: using BC Provider, 
 * ie. 
 *    Cipher.getInstance("xxxx, "BC")
**/

import com.google.code.p.keytooliui.shared.swing.panel.*;


abstract public class PSelCmbStrCipherRsaAlgoAbs extends PSelCmbStrAbs
{   
    // --------------------
    // FINAL STATIC PRIVATE
    
    // X509 certificate version
    final static private String _f_s_strTextLabel = "RSA Encryption algorithm:";
    final static private int _f_s_intWidthComboBox = 300;
    
    // ---------
    // PROTECTED   
     
    protected PSelCmbStrCipherRsaAlgoAbs(
        String[] strsValue)
    {        
        super(
            PSelCmbStrCipherRsaAlgoAbs._f_s_strTextLabel,
            strsValue,
            0, // first value in array for "setSelected(...)"
            PSelCmbStrCipherRsaAlgoAbs._f_s_intWidthComboBox
            ); 
    }
}
