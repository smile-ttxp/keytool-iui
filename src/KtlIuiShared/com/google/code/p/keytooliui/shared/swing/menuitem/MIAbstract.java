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
 
 
 package com.google.code.p.keytooliui.shared.swing.menuitem;
 
 /**
    known subclasses:
    . MIHelpAboutAppliAbstract
    . MICaptureScreen
    . MIFileExit
    . MIHelpJHAbstract
    . MIHelpOnlineAbstract
    . MIHelpAboutProjAbstract
    
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 
 import javax.swing.*;
 
 import java.awt.event.*;
 
 abstract public class MIAbstract extends JMenuItem
 {
    // ------
    // PUBLIC
    
    public void destroy() 
    {
        if (this._actListenerParent != null)
        {
            removeActionListener(this._actListenerParent);
            this._actListenerParent = null;
        }
    }
    
    public boolean init() { return true; }
 
    // ---------
    // PROTECTED
    
    protected MIAbstract(String strText, ActionListener actListenerParent)
    {
        this(strText);
        
        this._actListenerParent = actListenerParent;
        
        if (this._actListenerParent != null)
            addActionListener(this._actListenerParent);
    }
    
    protected MIAbstract(String strText, ImageIcon iin, ActionListener actListenerParent)
    {
        this(strText, actListenerParent);
        
        String strMethod = "MIAbstract(strText, iin)";
        
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
        
        setIcon(iin); 
    }
    
    protected MIAbstract(String strText)
    {
        super();
        
        String strMethod = "MIAbstract(strText)";
        
        if (strText == null)
            MySystem.s_printOutExit(this, strMethod, "nil strText");
        
        setText(strText); 
    }
    
    // private
    private ActionListener _actListenerParent = null;
 }