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
    "Kpr" means "KeyPair"

**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

public final class PSelBtnTfdRngIntValidityKpr extends PSelBtnTfdRngInt
{
    // -------------------
    // PUBLIC STATIC FINAL
    
    public static final String f_s_strDocPropVal = "select_validity_keypair:";
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strLabel = "Validity (days):";
    
    private static String _s_strDlgValidityTitle = "select keypair validity";
    private static String _s_strDlgValidityLabel = "Please enter keypair's validity in days";
    private static String _s_strDlgValidityTitlePanel = "Current value:";

    // ------
    // PUBLIC

    public PSelBtnTfdRngIntValidityKpr(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent
        )
    {
        super(
            docListenerParent,
            frmParent, 
 
            
            PSelBtnTfdRngIntValidityKpr._f_s_strLabel,
            PSelBtnTfdRngIntValidityKpr.f_s_strDocPropVal,
        
            PSelBtnTfdRngIntValidityKpr._s_strDlgValidityTitle, 
            PSelBtnTfdRngIntValidityKpr._s_strDlgValidityLabel, 
            PSelBtnTfdRngIntValidityKpr._s_strDlgValidityTitlePanel,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_intCertValidityMin, 
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_intCertValidityMax,
            com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_intCertValidityDefault,
            true // blnFieldRequired
            );
    }
    
    
}