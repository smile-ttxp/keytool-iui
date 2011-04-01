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
 
 
package com.google.code.p.keytooliui.shared.swing.editorpane;

import com.google.code.p.keytooliui.shared.util.eventlistener.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.net.*;
import java.awt.*;

final public class EPTextHTMLJar extends EPTextHTMLAbs
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        this._sstCustom = null;
        this._sstDefault = null;
    }
    
    public EPTextHTMLJar(
        HyperlinkListener hypListenerParent,
        MouseListener mouListenerParent,
        EPTextAbsListener pepListenerParent,
        Component cmpFrameOwner,
        String strTitleApplication, 
        Color colPageTextSelection,
        URL url,
        int intCursorType,
        StyleSheet sstCustom)
        //throws java.io.IOException
        
    {
        super(
            hypListenerParent,
            mouListenerParent,
            pepListenerParent, 
            cmpFrameOwner, 
            strTitleApplication, 
            colPageTextSelection, 
            url, 
            intCursorType);
        
        this._sstCustom = sstCustom;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! _setStyleSheet())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // --------
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        // ending
        return true;
    }
    
    // -------
    // PRIVATE
    
    private StyleSheet _sstCustom = null;
    private StyleSheet _sstDefault = null;
    
    private boolean _setStyleSheet()
    {
        String strMethod = "_setStyleSheet()";
            
        // ---
      
        EditorKit ekt = getEditorKit();
        
        if (ekt == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil ekt");
            return false;
        }
        
        if (! (ekt instanceof HTMLEditorKit))
        {
            MySystem.s_printOutError(this, strMethod, "wrong instanceof ekt");
            return false;
        }
        
        
        HTMLEditorKit ektHtml = (HTMLEditorKit) ekt;
        
        if (ektHtml == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil ektHtml");
            return false;
        }

        if (this._sstCustom != null) // assigning custom styleSheet
        {   
            ektHtml.setStyleSheet(this._sstCustom);                
            return true;
        }
                
        // assigning default styleSheet
        
        this._sstDefault = com.google.code.p.keytooliui.shared.swing.text.html.stylesheet.S_StyleSheet.s_getDefault();
        
        
        /*java.util.Enumeration rules = this._sstDefault.getStyleNames();
   	    while (rules.hasMoreElements())
   	    {
   	        String name = (String) rules.nextElement();
   	        Style rule = this._sstDefault.getStyle(name);
   	        System.out.println(rule.toString());
   	    }*/
     
        if (this._sstDefault == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._sstDefault");
            return false;
        }
        
        ektHtml.setStyleSheet(this._sstDefault);
             
        // ending
        return true;
    }
}