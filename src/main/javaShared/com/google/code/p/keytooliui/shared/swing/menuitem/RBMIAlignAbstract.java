/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
    . RBMIAlignTop
    . RBMIAlignBottom
    . RBMIAlignLeft
    . RBMIAlignRight
    
    
    Important: addItemListener is done at init time, instead of at construct time
    thus in order to first do select the item in the parentContainer, and not 
    the respective item to send an event at the starting!
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 
 
 abstract public class RBMIAlignAbstract extends RBMIAbstract
 {
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._itmListenerParent == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._itmListenerParent");
            return false;
        }
        
        this.addItemListener(this._itmListenerParent);
        return true;
    }
    
    
    public void destroy()
    {
        if (this._itmListenerParent != null)
        {
            this.removeItemListener(this._itmListenerParent);
            this._itmListenerParent = null;
        }
    }
 
    // ---------
    // PROTECTED
    
    protected RBMIAlignAbstract(
        String strText, 
        String strIcon,
        java.awt.event.ItemListener itmListenerParent)
    {
        super(strText);
        this._itmListenerParent = itmListenerParent;
    
        String strMethod = "RBMIAlignAbstract(strText, strIcon, itmListenerParent)";
        
        if (strIcon == null)
            MySystem.s_printOutExit(this, strMethod, "nil strIcon");
            
        javax.swing.ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strIcon);
        
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
            
        setIcon(iin);
    }
    
    // -------
    // PRIVATE
    
    private java.awt.event.ItemListener _itmListenerParent = null;
 }