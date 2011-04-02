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
 
 
package com.google.code.p.keytooliui.shared.swing.button;

/*

    known subclasses:
    . BESAboutAppli24

*/


import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.DHelpAboutAppli;
import com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpAboutAppliAbstract;
import com.google.code.p.keytooliui.shared.swing.panel.PTabHelpAppliAdvancedAbs;

abstract public class BESAboutAppliAbs extends BEnabledState
{    
    // --------------
    // STATIC PRIVATE
    
    static private String _STR_TOOL_TIP_PREFIX = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
    
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
            ".BESAboutAppliAbs" // class name
            ;
    
        
        
        String strWhere = "com.google.code.p.keytooliui.shared.swing.button.BESAboutAppliAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _STR_TOOL_TIP_PREFIX = rbeResources.getString("toolTipTextPrefix");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strWhere = null;
    }
    
    // ------
    // PUBLIC
    
    @Override
     public void destroy()
    {
        super.destroy();
        
        if (this._dlg != null)
        {
            if (this._dlg.isVisible())
                this._dlg.setVisible(false);
            
            this._dlg.destroy();
            this._dlg = null;
        }
    }
    
    // ---------
    // PROTECTED
        
    protected BESAboutAppliAbs(
            String strImage,
            final Component cmpFrameOwner,
            final String strProductID,
            final String strDlgBodyTextThirdParty,
            final PTabHelpAppliAdvancedAbs pnlTabAdvanced // created in subclasses
            )
    {
        super(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strImage));
        
        String strMethod = "BESAboutAppliAbs(..)";

        if (_STR_TOOL_TIP_PREFIX != null)
            setToolTipText(_STR_TOOL_TIP_PREFIX + " " + System.getProperty("_appli.title"));
        
        if (strProductID == null)
            MySystem.s_printOutExit(this, strMethod, "nil strProductID");
            
        this.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                String strMethod = "BESAboutAppliAbs(...).addActionListener(...).actionPerformed(...)";
                
                Toolkit.getDefaultToolkit().beep();

                String strDlgBodyCopyright = MIHelpAboutAppliAbstract.s_strDlgBodyCopyright;
                
                if (_dlg == null)
                {
                    _dlg = new DHelpAboutAppli(
                        cmpFrameOwner,
                        MIHelpAboutAppliAbstract.s_strTitlePrefix,
                        strDlgBodyCopyright,
                        strProductID,
                        strDlgBodyTextThirdParty,
                        pnlTabAdvanced
                        );
                
                    if (! _dlg.init())
                        MySystem.s_printOutExit(this, strMethod, "failed");
                }
                
          
                _dlg.setVisible(true);
            }
        });
        
    }
    
    // -------
    // PRIVATE
    
    private DHelpAboutAppli _dlg = null;
}

