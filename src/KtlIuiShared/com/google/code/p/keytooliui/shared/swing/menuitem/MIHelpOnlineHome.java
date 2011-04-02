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

/*
 *  @author bantchao
 */
 
package com.google.code.p.keytooliui.shared.swing.menuitem;

/**
    . MIHelpOnlineHome     ==> redirecting to default browser: http://code.google.com/p/keytool-iui
**/

import com.google.code.p.keytooliui.shared.lang.*;
import javax.swing.ImageIcon;

final public class MIHelpOnlineHome extends MIHelpOnlineAbstract 
{
    final static private String _F_STR_NAME_ICON = "webcomponent16.gif";
    
    static public String STR_TEXT = null;
    static public String STR_URL = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
            ".MIHelpOnlineHome" // class name
            ;
    
        
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpOnlineAbstract";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            MIHelpOnlineHome.STR_TEXT = rbeResources.getString("text");
            MIHelpOnlineHome.STR_URL = rbeResources.getString("url");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
    }
    
    @Override
    public boolean init()
    {
        String strMethod = "init()";

        if (! super.init())
            return false;

        ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
            MIHelpOnlineHome._F_STR_NAME_ICON
                );

        if (iin == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil iin");
            return false;
        }

        setIcon(iin);

        // ending
        return true;
    }
    
    public MIHelpOnlineHome(java.awt.Component cmpFrameOwner)
    {
        super(cmpFrameOwner, MIHelpOnlineHome.STR_TEXT, MIHelpOnlineHome.STR_URL);
    }        
}