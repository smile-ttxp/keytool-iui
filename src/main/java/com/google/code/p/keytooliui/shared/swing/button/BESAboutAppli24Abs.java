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
 * known subclasses:
 * . BESAboutAppli24Ktl
 */

import java.awt.Component;
import com.google.code.p.keytooliui.shared.swing.panel.PTabHelpAppliAdvancedAbs;

public abstract class BESAboutAppli24Abs extends BESAboutAppliAbs
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strImage = "aboutappli24.gif";
    
    // ---------
    // PROTECTED
        
    protected BESAboutAppli24Abs(Component cmpFrameOwner,
  
        String strDlgBodyVersion,
        String strProductID,
        String strDlgBodyTextThirdParty,
       PTabHelpAppliAdvancedAbs pnlAdvanced
       )
    {
        super(
            BESAboutAppli24Abs._f_s_strImage,
            cmpFrameOwner, 
        
            
            strDlgBodyVersion,
            strProductID,
            strDlgBodyTextThirdParty,
            pnlAdvanced
                );        
    }
}
