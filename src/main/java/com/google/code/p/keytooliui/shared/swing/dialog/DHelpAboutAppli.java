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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

/**


**/




import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public final class DHelpAboutAppli extends DEscapeAbstract implements ActionListener
{
    // ------
    // PUBLIC
    
    /* NOT WORKING!
    public void show()
    {
        if (this._pnl != null)
            this._pnl.showIt();
            
        super.show();
    }*/
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";

        if (! (evtAction.getSource() instanceof com.google.code.p.keytooliui.shared.swing.button.BClose))
            MySystem.s_printOutExit(this, strMethod, "wrong source instance");
        
        super._cancel_(); 
    }
    
   
    // ----
    
    
    public boolean init()
    {   
        String strMethod = "init()";
        
        if (this._pnl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnl");
            return false;
        }
        
        if (! this._pnl.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public void destroy()
    {          
        if (this._pnl != null)
        {
            this._pnl.destroy();
            this._pnl = null;
        }
        
        super.destroy();
    }
    

    public DHelpAboutAppli( 
        Component cmpFrameOwner,
        String strTitlePrefix,
        String strAppliVersion,
        String strAppliCopyright,
        String strTextThirdParty,
        PTabHelpAppliAdvancedAbs pnlTabAdvanced)
    {
        super((Frame) cmpFrameOwner, true);
        
        String strMethod = "DHelpAboutAppli(...)";
        
        
        if (cmpFrameOwner==null || strTitlePrefix==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        setTitle(strTitlePrefix + " " + System.getProperty("_appli.title"));

        // --
    
        this._pnl = new PHelpAboutAppli(
            (ActionListener) this, 
            strAppliVersion,
            strAppliCopyright,
            strTextThirdParty,
            pnlTabAdvanced);
        
        setContentPane(this._pnl);
    }
    
    
    // -------
    // PRIVATE
    
    private PHelpAboutAppli _pnl = null;
}