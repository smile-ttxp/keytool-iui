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
 
 
 package com.google.code.p.keytooliui.shared.swing.scrollpane;
 
 /**
    "SW" means "Secondary Window"
 **/
 
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.editorpane.*;

import javax.swing.text.html.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.net.*;
 
final public class SPPageTextSWHTML extends SPPageTextSWAbs
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        this._hypListenerParent = null;
        this._mouListenerParent = null;
    }
    
    public boolean select(URL url)
    {
        String strMethod = "select(url)";
        
        super._url_ = url;
        
        if (! pageReload())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean pageReload()
    {
        if (super._pep_ != null)
        {
            super._pep_.destroy();
            super._pep_ = null;
        }
        
        
        super._pep_ = new EPTextHTMLSys(
            this._hypListenerParent,
            this._mouListenerParent,
            null, // EPTextAbsListener lisParent
            null, // cmpFrameOwner
            null, // strTitleAppli
            java.awt.Color.red, // dummy value
            super._url_, // colPageTextSelection
            null // StyleSheet: sst
            );
            
        if (! super._reload_())
            return false;
            
        // BUG FIXING: bug ID8: white border in JEditorPane, if using setStyleSheet that contains a different color)
        super._pep_.setBackground(this.getBackground());
    
        //if (super._hlkListenerParent_ != null)
          //  super._pep_.addHyperlinkListener(super._hlkListenerParent_);
            
        
        return true;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
            return false;
            
        // BUG FIXING: bug ID8: white border in JEditorPane, if using setStyleSheet that contains a different color)
        super._pep_.setBackground(this.getBackground());
    
        //if (super._hlkListenerParent_ != null)
          //  super._pep_.addHyperlinkListener(super._hlkListenerParent_);
    
        return true;
    }
    
    
    public SPPageTextSWHTML(
        java.awt.Component cmpFrameOwner, 
        String strTitleFrame,
        URL url,
        HyperlinkListener hypListenerParent,
        MouseListener mouListenerParent,
        java.awt.Color colTextSelection
        )
    {
        super(url);
        
        this._hypListenerParent = hypListenerParent;
        this._mouListenerParent = mouListenerParent;
        
        super._pep_ = new EPTextHTMLSys(
            this._hypListenerParent,
            this._mouListenerParent,
            null,
            cmpFrameOwner,
            strTitleFrame,
            colTextSelection,
            super._url_,
            null // StyleSheet: sst
            );
    }
    
    // -------
    // PRIVATE
    
    private HyperlinkListener _hypListenerParent = null;
    private MouseListener _mouListenerParent = null;
 }