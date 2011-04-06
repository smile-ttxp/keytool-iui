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
 
 
 package com.google.code.p.keytooliui.shared.swing.menu;

/*
    known subclasses:
    . rcr: MAdvancedPrefAppli
*/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


public class MAdvanced extends MAbstract
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MAdvanced" // class name
            ;
    
        
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MAdvanced";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strText = rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
            
        }
    }
    
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String f_strMethod = "init()";
            
        if (_s_strText == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil _s_strText");
            return false;
        }
        
        setText(_s_strText);
        
        return true;
    }
    
    public void destroy()
    {
        
    }
    
    // PRIVATE
    
}