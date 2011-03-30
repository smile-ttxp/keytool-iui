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
 
 
 /**
 
    known subclasses:
    . shared_read: PRunScrollbarAbs
    . shared_read: PRunScrollMainAbs
    . shared_read: PRunScrollCntAbs
    
    
    a panel which extends Runnable
    
    
 
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.lang.thread.*;
 
 import javax.swing.*;
 
 import java.awt.*;
 
 abstract public class PRunScrollAbs extends PRunAbs
 {
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        
        // test
        if (this._imgOff_ != null)
        {
            this._imgOff_.flush();
            this._imgOff_ = null;
        }
        
        this._graOff_ = null;
        //System.gc();
    }
    
    /*
        memo: "synchronized" required in order to avoid artifacts while mouse moves over cells!
    */
    public synchronized void update(Graphics g)
	{
	    if (g != null)
		    paintComponent(g); 
	}
    
    public boolean setEnabledEvent(boolean bln)
    {
        this._blnFlagSetEnabledEvent_ = bln;
        return true;
    }
    
    public void setEnabledPaint(boolean bln)
    {
        this._blnPaintable_ = bln;
    }
    
    public void paintComponent(Graphics g)
	{	    
        String strMethod = "paintComponent(g)";    

        if (g == null)
        {       
            MySystem.s_printOutTrace(this, strMethod, "nil g");
            return;
        }
        
        if (! this._blnPaintable_)
        {
            MySystem.s_printOutTrace(this, strMethod, "! this._blnPaintable_");
			return;
        }
	    
	    if (this._cmpJpanelParent_ == null)
		{
		    // application should be exiting
            MySystem.s_printOutTrace(this, strMethod, "nil this._cmpJpanelParent_");
            return;
	    }
	    
	    if (! this._cmpJpanelParent_.isVisible())
	    {
	        MySystem.s_printOutTrace(this, strMethod, "! this._cmpJpanelParent_.isVisible()");
            return;
	    }
	    
	    //MySystem.s_printOutTrace(this, strMethod, "this._cmpJpanelParent_.isEnabled()=" + this._cmpJpanelParent_.isEnabled());
	    
	    if (this._cmpJpanelParent_.getWidth()<1 || this._cmpJpanelParent_.getHeight()<1)
        {
            MySystem.s_printOutTrace(this, strMethod, "this._cmpJpanelParent_.getWidth()<1 || this._cmpJpanelParent_.getHeight()<1");
            return;
        }
        

        if (this._imgOff_ != null)
        {
            g.drawImage(this._imgOff_, 0, 0, this);
		    this._graOff_ = this._imgOff_.getGraphics();
		    //MySystem.s_printOutTrace(this, strMethod, "<<<< DONE >>>>");
		}
		
		else
		{
		    MySystem.s_printOutTrace(this, strMethod, "nil this._imgOff_");
		    this._graOff_ = null;
		}
    }
    
    // ---------
    // PROTECTED
    
    protected Image _imgOff_;
    protected Graphics _graOff_;
    protected Component _cmpJpanelParent_ = null;
    
    protected boolean _blnPaintable_ = false; // MEMO used by the tabPanel of rcr (tabOwner selected/notSelected)
    protected boolean _blnFlagSetEnabledEvent_ = true;
    
    protected PRunScrollAbs(
        String strThreadName, 
        int intThreadPriority,
        Component cmpJpanelParent
        )
    {
        super(strThreadName, intThreadPriority);
        this._cmpJpanelParent_ = cmpJpanelParent;
    }
    
    protected Image _getImageOffScreenOnly_(int intW, int intH)
    {
        String strMethod = "_getImageOffScreenOnly_(intW, intH)";
        
        if (! isDisplayable())
        {
            MySystem.s_printOutError(this, strMethod, "! isDisplayable()");
            return null;
        }
        
        Image img = createImage(intW, intH); 
        
        if (img == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil img");
            return null;
        }

        return img;
    }
    
    
    /*
        create both image-offScreen and gaphics-offScreen
    */
    protected boolean _createImageOffScreen_(int intW, int intH)
    {
        String strMethod = "_createImageOffScreen_(intW, intH)";
        
        if (! isDisplayable())
        {
            MySystem.s_printOutError(this, strMethod, "! isDisplayable()");
            return false;
        }
        
        this._imgOff_ = createImage(intW, intH); 
        
        
        if (this._imgOff_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._imgOff_");
            this._graOff_ = null;
            return false;
        }
        
        this._graOff_ = this._imgOff_.getGraphics();
        return true;
    }
 }