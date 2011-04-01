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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a secondary window
    
    "B" for "Button"
    "Sw" for "Secondary Window"
    "Abs" for "Abstract class"


    Known subclasses:
    . B2SwUrlAbs
    . B2SwRtpAbs
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

abstract public class B2SwAbs extends B2Abs implements 
    //com.sun.java.help.impl.ViewAwareComponent, // TEMPO
    MyViewAwareComponent, // TEMPO
    WindowListener // addded in subclasses
{    
	// ----------------
    // STATIC PROTECTED
    
    static protected JFrame _s_getFrameOwnerHelpSet_(Component cmp)
    {
        String strMethod = "com.google.code.p.keytooliui.beans.button.B2SwAbs._s_getFrameOwnerHelpSet_(cmp)";
        MySystem.s_printOutFlagDev(strMethod, "uncomment here to catch all ancestors");
        
        //System.out.println("\n");
        
        while (cmp != null)
	    {        
	        //System.out.println("cmp.getClass().toString()=" + cmp.getClass().toString());
	        
	        if (cmp instanceof JFrame) 
	            return (JFrame) cmp;
        	        
	        cmp = cmp.getParent();
	    }
	    
	    //System.out.println("\n");
	            
        return null;
    }
    
    // ------------------
    // ABSTRACT PROTECTED
    
    /*
        . called in abstract subclasses, 
        . defined in final subclasses.
    */
    abstract protected boolean _createWindow_();
    
    // ------
    // PUBLIC
    
    // TEMPO
    /**
     * Sets data optained from the View
     */
    public void setViewData(javax.swing.text.View v) 
    {
        String strMethod = "setViewData(v)";
        
        MySystem.s_printOutTrace(this, strMethod, "#### ... ####");
    }
    
    // ----------------
    // begin properties

    
    public void setWindowTitle(String str)
    {
        this._strTitleWindow = str;
    }
    
    public String getWindowTitle()
    {
        if (this._strTitleWindow != null)
            return com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + this._strTitleWindow;
            
        return com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName;
    }
    
    
    // --------------
    // end properties
    
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {} 
    public void windowDeactivated(WindowEvent e) {} 
    public void windowDeiconified(WindowEvent e) {}     
    public void windowIconified(WindowEvent e) {}     
    public void windowOpened(WindowEvent e) {}  
    
    public void windowClosing(WindowEvent e) 
    { 
        if (this._frmWindow_ != null)
        {
            this._frmWindow_.dispose();
            this._frmWindow_ = null; 
        }
    }

    // ---------
    // PROTECTED
    
    protected FSwAbs _frmWindow_ = null;
    
    protected B2SwAbs()
    {
        super();
        com.google.code.p.keytooliui.javax.media.MyManager.s_loadLibrary(); // ?? should be in JMF's related subclasses? eg: B2SwRtpAbs & B2SwUrlMediaAbs
    }
    
    protected Image _getImageIconFrameTarget_()
    {
        if (! this._blnFrameTargetIconFromFrameSource)
        {
            ImageIcon iin = com.google.code.p.keytooliui.beans.swing.imageicon.S_IINBeans.s_get("bean16.gif");
        
            if (iin == null)
                return null;
            
            return iin.getImage();
        }
        
   
        return null;
    }
    
    // -------
    // PRIVATE
    
    
    // ----------
    // properties
    
    private boolean _blnFrameTargetIconFromFrameSource = false; // default
    private String _strTitleWindow = null;
    
}
