/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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


import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

public final class MHelpAboutViewerAudioPreview extends MHelpAboutAbstract 
{
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strInfoAboutBody = null;
    

    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MHelpAboutViewerAudioPreview";
        
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MHelpAboutViewerAudioPreview" // class name
            ;
    
    
        final String f_strBundleFileLong = f_strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strInfoAboutBody = rbeResources.getString("infoAboutBody");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, f_strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    // ------
    // PUBLIC
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        super._strInfoAboutBody_ = _s_strInfoAboutBody;
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public MHelpAboutViewerAudioPreview(Component cmpFrameOwner)
    {
        super(cmpFrameOwner);
    }
 
    // -------
    // PRIVATE
}