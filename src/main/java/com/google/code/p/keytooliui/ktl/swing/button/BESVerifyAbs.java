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

package com.google.code.p.keytooliui.ktl.swing.button;

/**
 *
 * @author bantchao
 */

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;


public abstract class BESVerifyAbs extends BEnabledState
{
// --------------
    // PRIVATE STATIC
    
    private static String _s_strToolTipText = "Verify (not JAR) signed file with public key";
    
    // ------------------
    // STATIC INITIALIZER

    /*static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strBundleDir +
            ".BESVerifyAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.ktl.swing.button.BESVerifyAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strToolTipText = rbeResources.getString("toolTipText");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strWhere = null;
    }*/
    
    // ---------
    // PROTECTED
        
    protected BESVerifyAbs(java.awt.event.ActionListener alr, String strImage)
    {
        super(com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI.s_get(strImage), alr);
        
        String strMethod = "BESVerifyAbs(alr, strImage)";
        
        if (BESVerifyAbs._s_strToolTipText == null)
            MySystem.s_printOutExit(this, strMethod, "nil BESVerifyAbs._s_strToolTipText");
            
        setToolTipText(BESVerifyAbs._s_strToolTipText);
        
    }
    
}
