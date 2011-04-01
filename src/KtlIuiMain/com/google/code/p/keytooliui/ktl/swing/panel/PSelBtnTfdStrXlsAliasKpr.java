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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    . PSelBtnTfdStrXlsAliasKpr (KeyPair)

     
**/


import java.awt.*;


final public class PSelBtnTfdStrXlsAliasKpr extends PSelBtnTfdStrXlsAliasAbs
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strDocPropVal = "select_alias_keypair";
    
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    
    final static private String _f_s_strLabelPrefix = "keypair";
    
    
    
    // ------
    // PUBLIC
    
     public PSelBtnTfdStrXlsAliasKpr(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent)
    {
        super(
            docListenerParent,
            frmParent,

            PSelBtnTfdStrXlsAliasKpr.f_s_strDocPropVal,
            PSelBtnTfdStrXlsAliasKpr._f_s_strLabelPrefix
        );        
    }
}