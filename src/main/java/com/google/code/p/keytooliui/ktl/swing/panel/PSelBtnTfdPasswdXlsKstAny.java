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
    . PSelBtnTfdPasswdXlsKstAny (KeyStore)
      "Any" means "either Jks or Pkcs12"
**/


import java.awt.*;


public final class PSelBtnTfdPasswdXlsKstAny extends PSelBtnTfdPasswdXlsKstAbs
{
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_password_keystore_any";
    
    // --------------------
    // PRIVATE STATIC FINAL
        
    private static final String _f_s_strLabelPrefix = "Keystore";

    // ------
    // PUBLIC
    
    public PSelBtnTfdPasswdXlsKstAny(
        String strLabel,
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
    
        int intModePassword
        )
    {
        super(
            strLabel,
            docListenerParent,
            frmParent,
     
            (Object) PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal,
            intModePassword
        );        
    }
    
    
     public PSelBtnTfdPasswdXlsKstAny(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
     
        int intModePassword
        )
    {
        super(
            docListenerParent,
            frmParent,
      
            (Object) PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal,
            PSelBtnTfdPasswdXlsKstAny._f_s_strLabelPrefix,
            intModePassword
        );        
    }
    
    public PSelBtnTfdPasswdXlsKstAny(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
     
        int intModePassword,
        String strSuffixDocPropVal // in case of more than on panel of same class in the same tab, used to differentiate
        )
    {
        super(
            docListenerParent,
            frmParent,
        
            (Object) PSelBtnTfdPasswdXlsKstAny.f_s_strDocPropVal + strSuffixDocPropVal,
            PSelBtnTfdPasswdXlsKstAny._f_s_strLabelPrefix,
            intModePassword
        );        
    }
}