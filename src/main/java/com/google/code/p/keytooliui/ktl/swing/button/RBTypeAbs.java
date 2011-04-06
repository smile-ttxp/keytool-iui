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
 
 
 package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**
    known subclasses:
    . RBTypeCrtAbs     certificate
    . RBTypeJarAbs     jar
    
    
    may be either enabled or disabled
    
 **/
 
 import javax.swing.*;
 
 import java.awt.event.*;
 
 abstract public class RBTypeAbs extends JRadioButton
 {
    // ------
    // PUBLIC
    
    public String[] getNamesFileExtension() { return this._strsExtension; }
    public String getFileDesc() { return this._strFileDesc; }
    
    public boolean init() { return true; }
    
    public void destroy() 
    {
        if (this._itmListenerParent != null)
        {
            removeItemListener(this._itmListenerParent);
            this._itmListenerParent = null;
        }
        
        this._strsExtension = null;
        this._strFileDesc = null;
    }
    
    // ---------
    // PROTECTED
    
    protected RBTypeAbs(
        boolean blnIsEnabled,
        ItemListener itmListenerParent,
        String strDescription,
        String[] strsExtension
        )
    {
        this._itmListenerParent = itmListenerParent;
        this._strsExtension = strsExtension;
        this._strFileDesc = strDescription;
        
        _setText(strDescription);
        
        
        setEnabled(blnIsEnabled);
        
        if (this._itmListenerParent != null) // memo: if "! blnIsEnabled", then "itmListenerParent" should be nil
            addItemListener(this._itmListenerParent);
        
        if (blnIsEnabled)
        {
            setToolTipText("click to select file format");
        }
        
        else
            setToolTipText("default file format");
    }
    
    
    // -------
    // PRIVATE
    
    private String[] _strsExtension = null;
    private String _strFileDesc = null;
    private ItemListener _itmListenerParent = null;
    
    private void _setText(String strDesc)
    {
        String strText = new String(strDesc);
        
        /*strText += " ";
        strText += "(";
        
        
        for (int i=0; i<this._strsExtension.length; i++)
        {
            strText += ".";
            strText += this._strsExtension[i];
            
            if (i <this._strsExtension.length-1)
                strText += ", ";
        }
        
        strText += ")";*/

        setText(strText);
    }
    
    
 }