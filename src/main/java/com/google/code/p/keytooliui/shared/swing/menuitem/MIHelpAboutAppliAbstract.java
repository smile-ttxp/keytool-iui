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
 
 
 package com.google.code.p.keytooliui.shared.swing.menuitem;

/**
    known subclasses:
    . MIHelpAboutAppliReader (rcr)
    . MIHelpAboutAppliGenDoc (xlb)
    . MIHelpAboutAppliGenTpl (tpb)
    . MIHelpAboutAppliUIKtl (kst)
    . MIHelpAboutAppliUIJsr (kst)
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public abstract class MIHelpAboutAppliAbstract extends MIAbstract 
{
    // -------------
    // PUBLIC STATIC
    
    public static String s_strTitlePrefix = null;
    public static String s_strDlgBodyCopyright = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {    
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".MIHelpAboutAppliAbstract" // class name
            ;

        String strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.MIHelpAboutAppliAbstract";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
            
            MIHelpAboutAppliAbstract.s_strTitlePrefix = rbeResources.getString("textPrefix");
                   
			MIHelpAboutAppliAbstract.s_strDlgBodyCopyright = rbeResources.getString("dlgBodyCopyright");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strBundleFileShort = null;
        strWhere = null;
    }
    
    
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
    
    protected MIHelpAboutAppliAbstract(
        final Component cmpFrameOwner,
    
        final String strDlgBodyVersion,
        final String strProductID,
        final String strDlgBodyTextThirdParty,
        final PTabHelpAppliAdvancedAbs pnlTabAdvanced // created in subclasses
            )
    {
        super(System.getProperty("_appli.title") + " ...");
        
        String strMethod = "MIHelpAboutAppliAbstract(...)";
        
    
            
        this.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                String strMethod = "MIHelpAboutAppliAbstract(...).addActionListener(...).actionPerformed(...)";
                
                Toolkit.getDefaultToolkit().beep();

                String strDlgBodyCopyright = MIHelpAboutAppliAbstract.s_strDlgBodyCopyright;
                
                if (_dlg == null)
                {
                    _dlg = new DHelpAboutAppli(
                        cmpFrameOwner,
                  
                        MIHelpAboutAppliAbstract.s_strTitlePrefix,
                        strDlgBodyVersion,
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