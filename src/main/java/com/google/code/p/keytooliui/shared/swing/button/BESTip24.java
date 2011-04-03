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
 
 
package com.google.code.p.keytooliui.shared.swing.button;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

final public class BESTip24 extends BEnabledState
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strImage = "tipoftheday24.gif";
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strToolTipText = "Usage ...";
    
    // ------------------
    // STATIC INITIALIZER

    /*static
    {
        java.util.ResourceBundle rbeResources;
    
        String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".BESTip24" // class name
            ;
    
        
        
        String f_strWhere = "com.google.code.p.keytooliui.shared.swing.button.BESTip24";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            BESTip24._s_strToolTipText = rbeResources.getString("toolTipText");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
        
        rbeResources = null;
        f_strBundleFileShort = null;
        f_strWhere = null;
    }*/
    
    // ------
    // PUBLIC
        
    public BESTip24(ActionListener alr)
    {
        super(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(_f_s_strImage), alr);
        //String strMethod = "BESTip24(alr)";
        
        //if (_s_strToolTipText == null)
          //  MySystem.s_printOutExit(this, strMethod, "nil _s_strToolTipText");
            
        setToolTipText(_s_strToolTipText);
        
        
    }
    
    // -------
    // PRIVATE
    
}
