/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.checkbox.*;

import java.awt.event.*;

final public class TBPageSecMediaUrlVideo extends TBPageSecMediaUrlAbs
{
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strName = "[name-toolbar-secondary_page-media-video]"; // TEMPO
    
    // ------
    // PUBLIC
    
    public void controllerUpdate(javax.media.ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)";
        
        super.controllerUpdate(evtController);
        
        try
        {  
            if (! (evtController instanceof javax.media.PrefetchCompleteEvent))
                return;
                
            javax.media.Player mpr = (javax.media.Player) evtController.getSource();
            
            // else possible bug while media over http, not realized, trying to get visualComponent
            //if (mpr.getState() != javax.media.Controller.Realized)
              //  return;
            
            if (mpr.getVisualComponent() == null)
                return;
                
            this._btnFullScreen.setEnabled(true);
            this._btnFullWindow.setEnabled(true);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        addSeparator(TBAbs._f_s_dimSeparator4_);
        add(this._btnFullScreen);
        addSeparator(TBAbs._f_s_dimSeparator4_);
        add(this._btnFullWindow);
        
        addSeparator(TBAbs._f_s_dimSeparator10_);
        
        this._btnFullScreen.setEnabled(false);
        this._btnFullWindow.setEnabled(false);
        
        
    
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnFullScreen != null)
        {
            this._btnFullScreen.destroy();
            this._btnFullScreen = null;
        }
        
        if (this._btnFullWindow != null)
        {
            this._btnFullWindow.destroy();
            this._btnFullWindow = null;
        }
    }

    public TBPageSecMediaUrlVideo(
        ActionListener actListenerParent,
        boolean blnSetSelectedLoop)
    {
        super(
            actListenerParent,
            _s_strName,
            blnSetSelectedLoop);
        
        this._btnFullScreen = new BESFullScreen16(actListenerParent); 
        this._btnFullWindow = new CBIFullWindow16(actListenerParent);
    }    
    
    // -------
    // PRIVATE
    
    
    // children
    private BESFullScreen16 _btnFullScreen = null;
    private CBIFullWindow16 _btnFullWindow = null;
}