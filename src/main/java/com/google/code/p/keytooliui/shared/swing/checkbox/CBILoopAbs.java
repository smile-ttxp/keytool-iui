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
 
 
package com.google.code.p.keytooliui.shared.swing.checkbox;

/**
    known subclasses:
    . CBILoop16
    . CBILoop24

**/


import com.google.code.p.keytooliui.shared.lang.*;
 
import javax.swing.*;
 
import java.awt.event.*;
 
public abstract class CBILoopAbs extends CBIcon
{    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strTip = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.checkbox.CBILoopAbs";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".CBILoopAbs" // class name
            ;
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());                
 
            _s_strTip = rbeResources.getString("tip");
            
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    }
    
    // ---------
    // PROTECTED
    
    protected CBILoopAbs(ActionListener actListenerParent, ImageIcon iin)
    {
        super(
            actListenerParent,
            iin,
            _s_strTip);
    }
    
 }