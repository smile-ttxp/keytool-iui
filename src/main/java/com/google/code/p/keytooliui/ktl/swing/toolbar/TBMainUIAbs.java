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

package com.google.code.p.keytooliui.ktl.swing.toolbar;

/**
    @author bantchao

**/


import com.google.code.p.keytooliui.ktl.swing.button.*;

import javax.swing.*;

import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

public abstract class TBMainUIAbs extends TBMainAbstract
{
    @Override
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
        }
        
        
        // ------------
        // add children
        
        if (! _addTools())
	    {
	        MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
	    }
	    
        
        // ------
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected TBMainUIAbs(
        java.awt.event.ActionListener actListenerParentAppli,
        //javax.help.HelpBroker hbrHelpStandard,
        javax.swing.ImageIcon iinFrameFloatable)
    {
        super(
            (String) null, // strHelpId
            actListenerParentAppli,
            //hbrHelpStandard,
            SwingConstants.HORIZONTAL,
            
            iinFrameFloatable, // not in use for now
            
            true, // blnDoHelpOnItem
            false
            );
            
        
         setName(System.getProperty("_appli.title") + " - " + "Main toolbar"); // in case of floatable toolbar
         
         this._btnKstView = new BESView24(actListenerParentAppli);
         this._btnKstTool = new BESTool24(actListenerParentAppli);
         
      
    }
    
    
    
    
    // -------
    // PRIVATE
    
    private BEnabledState _btnKstView;
    private BEnabledState _btnKstTool;
    
    private boolean _addTools()
    {
        String strMethod = "_addTools()";
        
        add(this._btnKstView);
        add(this._btnKstTool);
        
        if (super._btnPrint_ != null)
            add(super._btnPrint_);
        
        super.add(Box.createHorizontalGlue());
    	super.add(Box.createVerticalGlue());
        
        if (! super._addButtonHelp_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._btnAboutAppli_ != null)
        {
            if (! super._addButtonAboutAppli_())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            } 
        }

        // TODO:  remove all about exit-button!
        /*if (! super._addButtonExit_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }*/
     
        return true;
    }
}