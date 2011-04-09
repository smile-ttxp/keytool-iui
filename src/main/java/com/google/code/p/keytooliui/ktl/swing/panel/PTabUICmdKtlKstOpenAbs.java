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
    "Ktl" means Keytool


    known subclasses:
    . PTabUICmdKtlKstOpenCrKprAbs
    . PTabUICmdKtlKstOpenCrtAbs
    . PTabUICmdKtlKstOpenPkcs12Abs
**/

import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.*;

public abstract class PTabUICmdKtlKstOpenAbs extends PTabUICmdKtlAbs 
{
    // ---------
    // PROTECTED   
    
    protected PTabUICmdKtlKstOpenAbs(
        String strLabelPasswd,
        String strHelpID,
        Frame frmOwner,
  
            
        String strTextLabelFile,
        boolean blnAllowTypeJks,
        boolean blnAllowTypeJceks,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        super(
            strLabelPasswd,
            strHelpID, 
            frmOwner, 
    
            BESPasswordAbs.f_s_intModeOpen, // password for KeyStore of type [JKS-JCEKS-BKS-UBER]
            "Source", // String strLabelBorderPanelIn, // nil value allowed
            "Target"  // String strLabelBorderPanelOut // nil value allowed
            );
            
        // ----
        // KeyStore
            
         super._pnlSelectFileKst_ = new PSelBtnTfdFileOpenKst(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
    
            (java.awt.event.ItemListener) null,
            strTextLabelFile,
            true, // blnFieldRequiredKeystore,
            
            blnAllowTypeJks,
            blnAllowTypeJceks,
            blnAllowTypePkcs12,
            blnAllowTypeBks,
            blnAllowTypeUber
            ); 
    }
    
    protected PTabUICmdKtlKstOpenAbs(
        String strHelpID,
        Frame frmOwner,
      
            
        String strTextLabelFile,
        boolean blnAllowTypeJks,
        boolean blnAllowTypeJceks,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        super(
            strHelpID, 
            frmOwner, 
         
            BESPasswordAbs.f_s_intModeOpen, // password for KeyStore of type [JKS-JCEKS-BKS-UBER]
            "Source", // String strLabelBorderPanelIn, // nil value allowed
            "Target"  // String strLabelBorderPanelOut // nil value allowed
            );
            
        // ----
        // KeyStore
            
         super._pnlSelectFileKst_ = new PSelBtnTfdFileOpenKst(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
      
            (java.awt.event.ItemListener) null,
            strTextLabelFile,
            true, // blnFieldRequiredKeystore,
            
            blnAllowTypeJks,
            blnAllowTypeJceks,
            blnAllowTypePkcs12,
            blnAllowTypeBks,
            blnAllowTypeUber
            ); 
    }
    
    protected PTabUICmdKtlKstOpenAbs(
        //String strTitleTab,
        String strHelpID,
        Frame frmOwner,
 
            
        boolean blnAllowTypeJks,
        boolean blnAllowTypeJceks,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        this(
           strHelpID,
            frmOwner,
     
            
            PSelBtnTfdFileOpenKst.f_s_strLabel,
            blnAllowTypeJks,
            blnAllowTypeJceks,
            blnAllowTypePkcs12,
            blnAllowTypeBks,
            blnAllowTypeUber);
    }
}