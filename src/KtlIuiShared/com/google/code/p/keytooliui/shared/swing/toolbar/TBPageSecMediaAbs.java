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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/*
    known subclasses:
    . TBPageSecMediaRtpAbs
    . TBPageSecMediaUrlAbs
    

*/

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;


abstract public class TBPageSecMediaAbs extends TBPageSecAbs implements
    javax.media.ControllerListener
{    
    // ------
    // PUBLIC
    
    public void controllerUpdate(javax.media.ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)";
        
        if (evtController instanceof javax.media.ControllerErrorEvent)
	    {
	        // MEMO: also handled in "panelDisplay"
	        String strMessage = ((javax.media.ControllerErrorEvent)evtController).getMessage();
	        MySystem.s_printOutWarning(this, strMethod, "evtController instanceof ControllerErrorEvent, strMessage=" + strMessage);
	        
	        //
	        super.setEnabledPageReload(false); // added april 20, 2003
	        this._removeControl_();
	        
	        
	        
	        if (this.isVisible())
	        {
	            java.awt.Graphics g = getGraphics();
            
                if (g != null)
                    update(g);
	        }
	        
	        return;
	    }
        
        // for info
        //MySystem.s_printOutTrace(this, strMethod, "evtController.getClass().toString()=" + evtController.getClass().toString());
    }
    
    public void destroy()
    {
        super.destroy();
        
        _removeControl_();
        
        if (this._btnEmptyCtrlBeg != null)
        {
            this._btnEmptyCtrlBeg.destroy();
            this._btnEmptyCtrlBeg = null;
        }
        
        if (this._btnEmptyCtrlEnd != null)
        {
            this._btnEmptyCtrlEnd.destroy();
            this._btnEmptyCtrlEnd = null;
        }
    }

    // ---------
    // PROTECTED
    
    
    
    protected void _addControl_(java.awt.Component cmpControl)
    {   
        String strMethod = "_addControl_(cmpControl)";
        
        if (cmpControl == null)
            return;
            
        this._cmpControl = cmpControl;
        
        add(this._btnEmptyCtrlBeg);
        add(this._cmpControl);
        add(this._btnEmptyCtrlEnd);
        
        this._cmpControl.addNotify();
        
        
        /*
            nov 6, 2003, code in comments
            sometimes, getting exception, see below
        
            java.lang.NullPointerException
	        at javax.swing.SizeRequirements.calculateAlignedPositions(SizeRequirements.java:442)
	        at javax.swing.SizeRequirements.calculateAlignedPositions(SizeRequirements.java:403)
	        at javax.swing.OverlayLayout.layoutContainer(OverlayLayout.java:205)
	        at java.awt.Container.layout(Container.java:1020)
	        at java.awt.Container.doLayout(Container.java:1010)
	        at java.awt.Container.validateTree(Container.java:1092)
	        at java.awt.Container.validateTree(Container.java:1099)
	        at java.awt.Container.validate(Container.java:1067)
	        at com.google.code.p.keytooliui.rcr.swing.toolbar.TBSubPageIfRdrMediaAbs._addControl_(TBSubPageIfRdrMediaAbs.java:133)
	        at com.google.code.p.keytooliui.rcr.swing.toolbar.TBSubPageIfRdrMediaUrlAbs.controllerUpdate(TBSubPageIfRdrMediaUrlAbs.java:97)
	        at com.sun.media.BasicController.dispatchEvent(BasicController.java:1254)
	        at com.sun.media.SendEventQueue.processEvent(BasicController.java:1286)
	        at com.sun.media.util.ThreadedEventQueue.dispatchEvents(ThreadedEventQueue.java:65)
	        at com.sun.media.util.ThreadedEventQueue.run(ThreadedEventQueue.java:92)
        2 ? ERROR (instance ID: 1)
        . location: com.google.code.p.keytooliui.rcr.swing.toolbar.TBSubPageIfRdrMediaUrlAudio.controllerUpdate(evtController)
        . message: exc caught
        */
        MySystem.s_printOutFlagDev(this, strMethod, "in comments: call to validate()");
        //this.validate();
    }
    
    protected void _removeControl_()
    {
        if (this._btnEmptyCtrlBeg != null)
        {
            if (this.isAncestorOf(this._btnEmptyCtrlBeg))
            {
                remove(this._btnEmptyCtrlBeg);           
            }
            
            //this._btnEmptyCtrlBeg = null;
        }
        
        if (this._cmpControl != null)
        {
            if (this.isAncestorOf(this._cmpControl))
            {
                remove(this._cmpControl);
                // comments, oct 8, 2002 this._cmpControl.removeNotify();
                
            }
            
            this._cmpControl = null;
        }
     
        if (this._btnEmptyCtrlEnd != null)
        {
            if (this.isAncestorOf(this._btnEmptyCtrlEnd))
            {
                remove(this._btnEmptyCtrlEnd);
            }
            
            //this._btnEmptyCtrlEnd = null;
        }
        
        // comments, oct 8, 2002 this.invalidate();
        
        // !!! update??
    }
    
    protected TBPageSecMediaAbs(
        ActionListener actListenerParent,
        String strName)
    {
        super(actListenerParent);
        
        this._btnEmptyCtrlBeg = new BESEmptyIcon16();
        this._btnEmptyCtrlEnd = new BESEmptyIcon16();
        
        // while floatable, not yet in use
	    setName(getName() + " - " + strName); 
    }
    
    // -------
    // PRIVATE
    
    private BEnabledState _btnEmptyCtrlBeg = null; 
    private BEnabledState _btnEmptyCtrlEnd = null; 
    
    private java.awt.Component _cmpControl = null;
}