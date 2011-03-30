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
    "Ktl" means Keytool


    known subclasses:
    . PTabUICmdKtlKstOpenCrKprAbs
    . PTabUICmdKtlKstOpenCrtAbs
    . PTabUICmdKtlKstOpenPkcs12Abs
**/

import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.*;

abstract public class PTabUICmdKtlKstOpenAbs extends PTabUICmdKtlAbs 
{
    // ---------
    // PROTECTED   
    
    protected PTabUICmdKtlKstOpenAbs(
        String strLabelPasswd,
        String strHelpID,
        Frame frmOwner,
        String strTitleAppli,
            
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
            strTitleAppli, 
            BESPasswordAbs.f_s_intModeOpen, // password for KeyStore of type [JKS-JCEKS-BKS-UBER]
            "Source:", // String strLabelBorderPanelIn, // nil value allowed
            "Target:"  // String strLabelBorderPanelOut // nil value allowed
            );
            
        // ----
        // KeyStore
            
         super._pnlSelectFileKst_ = new PSelBtnTfdFileOpenKst(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli,
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
        String strTitleAppli,
            
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
            strTitleAppli, 
            BESPasswordAbs.f_s_intModeOpen, // password for KeyStore of type [JKS-JCEKS-BKS-UBER]
            "Source:", // String strLabelBorderPanelIn, // nil value allowed
            "Target:"  // String strLabelBorderPanelOut // nil value allowed
            );
            
        // ----
        // KeyStore
            
         super._pnlSelectFileKst_ = new PSelBtnTfdFileOpenKst(
            (javax.swing.event.DocumentListener) this,
            frmOwner, 
            strTitleAppli,
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
        String strTitleAppli,
            
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
            strTitleAppli,
            
            PSelBtnTfdFileOpenKst.f_s_strLabel,
            blnAllowTypeJks,
            blnAllowTypeJceks,
            blnAllowTypePkcs12,
            blnAllowTypeBks,
            blnAllowTypeUber);
    }
}