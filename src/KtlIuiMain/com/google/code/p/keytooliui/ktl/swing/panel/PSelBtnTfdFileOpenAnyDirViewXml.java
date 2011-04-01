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

package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import com.google.code.p.keytooliui.ktl.swing.button.BESMore16;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.button.BESClearEntry16;
import com.google.code.p.keytooliui.shared.swing.button.BEnabledState;

/**
 *
 * @author bantchao
 *
 *adding "view XML" button at the right side ==> JOOST requirement
 */
public class PSelBtnTfdFileOpenAnyDirViewXml extends PSelBtnTfdFileOpenAnyDir
{
    
    /** Creates a new instance of PSelBtnTfdFileOpenAnyDirViewXml */
    public PSelBtnTfdFileOpenAnyDirViewXml(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
     
        ItemListener itmListenerParent,
        String strLabel
            ) 
    {
        super(docListenerParent, frmParent, itmListenerParent, strLabel);
        
        this._btnViewXml = new BESMore16(this);  
    }
    
    public void destroy()
    {
        super.destroy();
        
        
        if (this._btnViewXml != null)
        {
            this._btnViewXml.destroy();
            this._btnViewXml = null;
        }
            
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (! (evtAction.getSource() instanceof BEnabledState))
            MySystem.s_printOutExit(this, strMethod, "! (evtAction.getSource() instanceof BEnabledState)");
            
        BEnabledState btnSource = (BEnabledState) evtAction.getSource();
        
        
        if (btnSource instanceof BESMore16)
        {
            // TODO!
            MySystem.s_printOutTrace(this, strMethod, "TODO: show XML contents");
            
            return;
        }
        
        if (btnSource instanceof BESClearEntry16)
        {
            this._btnViewXml.setEnabled(false);
        }
        
        super.actionPerformed(evtAction);
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        

	if (! _addItem())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }    

        // ending
        return true;
    }
    
    // protected
    
    // could be redefined in subclasses
    protected boolean _enableButtonsSelectionDone_()
    {
        String strMethod = "_enableButtonsSelectionDone_()";
        
        if (! super._enableButtonsSelectionDone_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._btnViewXml == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnViewXml");
            return false;
        }
        
        this._btnViewXml.setEnabled(true);
        
        return true;
    }
    
    // -------
    // private
    
    // JOOST req
    
    private BESMore16 _btnViewXml = null;
    
    private boolean _addItem()
    {
        String strMethod = "_addItem()";
        
        // ------
        // adding
        
        if (this._btnViewXml == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._btnViewXml");
            return false;
        }

        //super._pnl_.add(Box.createRigidArea(PSelAbs.f_s_dimBoxX));
        super._pnl_.add(this._btnViewXml);
        
        // disabling!
        
        this._btnViewXml.setEnabled(false);
        
        return true;
    }
}
