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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;


import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.*;

import java.net.*;
import java.awt.event.*;
import java.awt.*;

final public class PPageSwMediaUrlVideo extends PPageSwMediaUrlAbs implements
    ComponentListener
{    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intInsetW = 20; 
    final static private int _f_s_intInsetH = 20;   
    
    // ------
    // PUBLIC
    
    public boolean setFullWindow(boolean bln)
    {
        String strMethod = "setFullWindow(bln)";
        
        this._blnSizeVisualRegular = !bln;
        
        if (! _setSizePanel())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        if (! _setLocationPanel())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        
        return true;
    }
    
    
    
    public boolean setFullScreen(boolean bln)
    {
        String strMethod = "setFullScreen(bln)";
        
        if (super._plyCur_ == null)
            return true;
            
        Dimension dimScreen;
        Dimension dimPrefSize;
        Rectangle rectVideo;
            
            
        try
        {              
            // else possible bug while media over http, not realized, trying to get visualComponent
            if (super._plyCur_.getState() < javax.media.Controller.Realized)
            {
                MySystem.s_printOutTrace(this, strMethod, "super._plyCur_.getState() < javax.media.Controller.Realized\n  super._plyCur_.getState()=" + super._plyCur_.getState());
                return true;
            }
            
            Component cmpVisual = super._plyCur_.getVisualComponent();
            
            if (cmpVisual == null)
                return true;
        

            if (bln==true && cmpVisual.getParent()!=this._winFullScreen)
            {
                this._dimFrameSizeBeforeFullScreen = this.getSize();

                dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
                
                if (this._winFullScreen == null)
                {
                    this._winFullScreen = new Window(super._winParent_);
                   
                    this._winFullScreen.setLayout(null);
                    this._winFullScreen.setBackground(Color.black);
                }
                
                this._winFullScreen.setBounds(0, 0, dimScreen.width, dimScreen.height);
                
                super._pvm_.remove(cmpVisual);
                
                super._pvm_.setVisible(false);
                
                
                dimPrefSize = cmpVisual.getPreferredSize();
                

                rectVideo = new Rectangle(0, 0, dimScreen.width, dimScreen.height);
                
                if ( (float)dimPrefSize.width/dimPrefSize.height >= (float)dimScreen.width/dimScreen.height)
                {
                    rectVideo.height = (dimPrefSize.height * dimScreen.width) / dimPrefSize.width;
                    rectVideo.y = (dimScreen.height - rectVideo.height) / 2;
                }
                
                else
                {
                    rectVideo.width = (dimPrefSize.width * dimScreen.height) / dimPrefSize.height;
                    rectVideo.x = (dimScreen.width - rectVideo.width) / 2;
                }

                Toolkit.getDefaultToolkit().sync();
                this._winFullScreen.add(cmpVisual);
                this._winFullScreen.setVisible(true);
                cmpVisual.setBounds(rectVideo);
                this._winFullScreen.validate();

                this._mlrFullScreen = new MouseAdapter ()
                {
                    public void mouseClicked(MouseEvent evtAction)
                    {
                        setFullScreen(false);
                    }
                };
                
                cmpVisual.addMouseListener(this._mlrFullScreen);
                
                // ----
            }
            
            else if (bln == false && cmpVisual.getParent() == this._winFullScreen)
            {
                this.setVisible(false);
                
                if (this._mlrFullScreen != null)
                    cmpVisual.removeMouseListener(this._mlrFullScreen);
                
                Toolkit.getDefaultToolkit().sync();
                this._winFullScreen.setVisible(false);
                this._winFullScreen.remove(cmpVisual);
                        
                super._pvm_.add(cmpVisual, BorderLayout.CENTER);
                super._pvm_.setVisible(true);
                        
                        
                if (this._dimFrameSizeBeforeFullScreen != null)
                {
                    this.setSize(this._dimFrameSizeBeforeFullScreen);
                    this.validate();
                }
                
                this.setVisible(true);
            }
        
        
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            return false;
        }
        
    
        // ending
        return true;
    }
    
    
    public void componentResized(ComponentEvent evtComponent)
    {
        String strMethod = "componentResized(evtComponent)";
        MySystem.s_printOutTrace(this, strMethod, "getSize()=" + getSize());
        
        if (! _setSizePanel())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        if (! _setLocationPanel())
            MySystem.s_printOutExit(this, strMethod, "failed");
            
    }
    
    public void componentMoved(ComponentEvent evtComponent)
    {
    }
    
    public void componentHidden(ComponentEvent evtComponent)
    {
    }
    
    public void componentShown(ComponentEvent evtComponent)
    {
    }
    
    public void controllerUpdate(ControllerEvent evtController)
    {
        String strMethod = "controllerUpdate(evtController)";
        
        try
        {    
	        /*if (evtController instanceof PrefetchCompleteEvent)
	        {
	            MySystem.s_printOutTrace(this, strMethod, "evtController instanceof PrefetchCompleteEvent");
	            Player mpr = (Player) evtController.getSource();
	            
	            // else possible bug while media over http, not realized, trying to get visualComponent
                //if (mpr.getState() != javax.media.Controller.Realized)
                  //  return;
	            
                _addVisual(mpr.getVisualComponent());             
	        }*/
	        
	        if (evtController instanceof RealizeCompleteEvent)
	        {
	            MySystem.s_printOutTrace(this, strMethod, "evtController instanceof RealizeCompleteEvent");
	            Player mpr = (Player) evtController.getSource();
	            
                _addVisual(mpr.getVisualComponent());             
	        }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
        
        super.controllerUpdate(evtController);
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        add(super._pvm_);
        super._pvm_.addNotify();        
        
        // ending
        return true;
    }
    
    public PPageSwMediaUrlVideo(
        URL url,
        ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        Window winParent,
        boolean blnReadyStart,
        boolean blnLoop,
        Component cmpParent)
    {
        super(url, ctrListenerToolbar, strTitleAppli, winParent, blnReadyStart, blnLoop);
        
        if (cmpParent != null)
            cmpParent.addComponentListener(this);
        
        
        super._pvm_ = new PSubPageJmfVideo();
        super._pvm_.setVisible(false);
        
        setLayout(null);
    }
    
    // -------
    // PRIVATE
    
    private Window _winFullScreen = null;
    
    private Dimension _dimFrameSizeBeforeFullScreen = null;
    private MouseListener _mlrFullScreen;
    
    
    private boolean _blnSizeVisualRegular = true;
    
    private boolean _setSizePanelFullPanel()
    {
        String strMethod = "_setSizePanelFullPanel()";
        
        if (super._plyCur_ == null)
            return true;
            
        
        if (super._pvm_ == null)
            return true;
            
        // else possible bug while media over http, not realized, trying to get visualComponent
        if (super._plyCur_.getState() < javax.media.Controller.Realized)
        {
            MySystem.s_printOutTrace(this, strMethod, "super._plyCur_.getState() < javax.media.Controller.Realized\n  super._plyCur_.getState()=" + super._plyCur_.getState());
            return true;
        }
            
        Component cmpVisual = super._plyCur_.getVisualComponent();
            
        if (cmpVisual == null) // no visual renderer for this file
        {
            //MySystem.s_printOutTrace(this, strMethod, "nil cmpVisual, ignoring");
            return true;
        }

        Dimension dimVideo = cmpVisual.getPreferredSize();
		
        
        double dblScaleW = 1.;
        
        int intSizeAllowedW = getWidth() - _f_s_intInsetW;
        
        if (intSizeAllowedW != dimVideo.width)
            dblScaleW = ((double)intSizeAllowedW) / ((double)(dimVideo.width));
            
        double dblScaleH = 1.;
        
        int intSizeAllowedH = getHeight() - _f_s_intInsetH;
        
        if (intSizeAllowedH != dimVideo.height)
            dblScaleH = ((double) intSizeAllowedH) / ((double)(dimVideo.height));
        
        
        // --
        
        double dblScale = 1.;
        
        if (dblScaleW>1. || dblScaleH>1.)
        {
            if (dblScaleW > dblScaleH)
                dblScale = dblScaleH;
            else
                dblScale = dblScaleW;
        }
        
        else if (dblScaleW<1. || dblScaleH<1.)
        {
            if (dblScaleW > dblScaleH)
                dblScale = dblScaleH;
            else
                dblScale = dblScaleW;
        }
        
        // --
        
        
        if (dblScale != 1.)
        {
            dimVideo.width = (int)(dimVideo.width * dblScale);
		    dimVideo.height = (int)(dimVideo.height * dblScale);  
        }
		   
		super._pvm_.setSize(dimVideo.width, dimVideo.height);
        
        // !!!!!!!!!
		//invalidate();
		validate();
        
        // ending
        return true;
        
    }
    
    
    /*
        set preferred video size to panel containing video,
        unless panelContainer is smaller than preferred video, if so: reduce size to fit pane
    */
    private boolean _setSizePanelRegular()
    {
        String strMethod = "_setSizePanelRegular()";
        
        if (super._plyCur_ == null)
            return true;

        if (super._pvm_ == null)
            return true;
            
        // else possible bug while media over http, not realized, trying to get visualComponent
        if (super._plyCur_.getState() < javax.media.Controller.Realized)
        {
            MySystem.s_printOutTrace(this, strMethod, "super._plyCur_.getState() < javax.media.Controller.Realized\n  super._plyCur_.getState()=" + super._plyCur_.getState());
            return true;
        }
            
        Component cmpVisual = super._plyCur_.getVisualComponent();
        
        if (cmpVisual == null)
        {
            //MySystem.s_printOutTrace(this, strMethod, "nil cmpVisual, ignoring");
            return true;
        }

        Dimension dimVideo = cmpVisual.getPreferredSize();
		
        
        double dblScaleW = 1.;
        
        int intSizeAllowedW = getWidth() - _f_s_intInsetW;
        
        if (intSizeAllowedW != dimVideo.width)
            dblScaleW = ((double)intSizeAllowedW) / ((double)(dimVideo.width));
            
        double dblScaleH = 1.;
        
        int intSizeAllowedH = getHeight() - _f_s_intInsetH;
        
        if (intSizeAllowedH != dimVideo.height)
            dblScaleH = ((double) intSizeAllowedH) / ((double)(dimVideo.height));
        
        // --
        double dblScale = 1.;
        
        if (dblScaleW<1. || dblScaleH<1.)
        {
            if (dblScaleW > dblScaleH)
                dblScale = dblScaleH;
            else
                dblScale = dblScaleW;
        }
        
        if (dblScale != 1.)
        {
            dimVideo.width = (int)(dimVideo.width * dblScale);
		    dimVideo.height = (int)(dimVideo.height * dblScale);
        }
        
        
        super._pvm_.setSize(dimVideo.width, dimVideo.height);
        
        
        // !!!!!!!!!
		//invalidate();
		validate();
        
        return true;
    }
    
    
    private boolean _setSizePanel()
    {        
        if (this._blnSizeVisualRegular)
            return _setSizePanelRegular();
            
        return _setSizePanelFullPanel();
    }
    
    private boolean _setLocationPanel()
    {
        String strMethod = "_setLocationPanel()";
        
        
        if (! this.isVisible())
            return true;
            
        if (super._plyCur_ == null)
            return true;
            
        // else possible bug while media over http, not realized, trying to get visualComponent
        if (super._plyCur_.getState() < javax.media.Controller.Realized)
        {
            MySystem.s_printOutTrace(this, strMethod, "super._plyCur_.getState() < javax.media.Controller.Realized\n  super._plyCur_.getState()=" + super._plyCur_.getState());
            return true;
        }
        
        if (super._plyCur_.getVisualComponent() == null)
            return true;
            
        if (super._pvm_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._pvm_");
	        return false;
        }
	        
	    int intX = this.getWidth();
	    int intSizeAllowedH = getHeight() - _f_s_intInsetH; 
	        
	   
	    if (intX<1 || intSizeAllowedH<1)
	    {
	        return true;
	    }
	    
	    intX /=2;
	    intX -= super._pvm_.getWidth()/2;
	    
	    if (intX < (_f_s_intInsetW/2))
	        intX = _f_s_intInsetW/2;
	        
	    
	    int intY = intSizeAllowedH/2;
	    intY -= super._pvm_.getHeight()/2;
	    
	    intY += _f_s_intInsetH/2;
	    
	    
	    if (intY < _f_s_intInsetH/2)
	        intY = _f_s_intInsetH/2;
	        
	
	    super._pvm_.setLocation(new Point(intX, intY));
	    // !!
	    super._pvm_.validate();
	    
	    
	    validate();
	    return true;
    }
    
    private void _addVisual(Component cmpVisual)
    {
    	String strMethod = "_addVisual(cmpVisual)";     
    	        
	    if (cmpVisual == null)
	    {
	        // show an error message?
	        MySystem.s_printOutWarning(this, strMethod, "nil cmpVisual");
	                
	        String strBody = "Video not supported";
                
            com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
                super._winParent_, super._strTitleAppli_, strBody);
                
		    return;
	    }
	            
	    super._pvm_.add(cmpVisual, BorderLayout.CENTER);
	    cmpVisual.addNotify();
	    super._pvm_.setVisible(true);
		        
        if (! _setSizePanel())
	        MySystem.s_printOutExit(this, strMethod, "failed");
            
	    if (! _setLocationPanel())
	        MySystem.s_printOutExit(this, strMethod, "failed"); 
    }
}