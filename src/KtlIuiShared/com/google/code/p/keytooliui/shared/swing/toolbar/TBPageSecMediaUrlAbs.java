/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
    . TBPageSecMediaUrlAudio
    . TBPageSecMediaUrlVideo
    
    Contains 1 imageButtons 24x24 pixels:
    . loop
*/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.checkbox.*;

import java.awt.event.*;

abstract public class TBPageSecMediaUrlAbs extends TBPageSecMediaAbs 
{
    // ------
    // PUBLIC
    
    public void controllerUpdate(javax.media.ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)";
          
        try
        {            
	        if (evtController instanceof javax.media.PrefetchCompleteEvent)
	        {
	            //MySystem.s_printOutTrace(this, strMethod, "evtController instanceof javax.media.PrefetchCompleteEvent");
	            
	            if (this._btnLoop != null)
	                this._btnLoop.setEnabled(true);
    	        
    	        javax.media.Player mpr = (javax.media.Player) evtController.getSource();
    	        super._addControl_(mpr.getControlPanelComponent());	         
    	        
    	        // ending
    	        return;
	        }
	        
	        super.controllerUpdate(evtController);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
    }
    
    public void destroy()
    {
        if (this._btnLoop != null)
        {
            this._btnLoop = null;
        }
        
        super.destroy(); 
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
        
        
        addSeparator(TBAbs._f_s_dimSeparator10_);
        add(this._btnLoop);
        
       
        this._btnLoop.addNotify();
        
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
        
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected TBPageSecMediaUrlAbs(
        ActionListener actListenerParentPanel,
        String strName,
        boolean blnSetSelectedLoop)
    {
        super(actListenerParentPanel, strName);
        

        this._btnLoop = new CBILoop16(actListenerParentPanel);
        
        this._btnLoop.setSelected(blnSetSelectedLoop);
        this._btnLoop.setEnabled(false);    
    }
    
    // -------
    // PRIVATE
    

    private CBILoop16 _btnLoop = null;
    
}