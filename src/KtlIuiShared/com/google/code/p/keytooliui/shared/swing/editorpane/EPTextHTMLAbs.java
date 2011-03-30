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
 
 
 package com.google.code.p.keytooliui.shared.swing.editorpane;

/**
    known subclasses:
    . EPTextHTMLSys
    . EPTextHTMLJar
**/


import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.text.html.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*; 
import java.util.*;
import java.net.*;



/** 
 * Allows printing of mousePressed documents displayed in JEditorPanes 
 */ 
abstract public class EPTextHTMLAbs extends EPTextAbs 
{     
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        
        if (this._lstAnchor != null)
        {
            this._lstAnchor.clear();
            this._lstAnchor = null;
        }
        
        if (this._hypListenerParent != null)
        {
            removeHyperlinkListener(this._hypListenerParent);
            this._hypListenerParent = null;
        }
        
        if (this._mouListenerParent != null)
        {
           removeMouseListener(this._mouListenerParent);
           this._mouListenerParent = null;
        }
   }
    
    public boolean setEnabledEvent(boolean bln)
    {
        String strMethod = "setEnabledEvent(bln)";
        
        EditorKit ekt = this.getEditorKit();
        
        if (! (ekt instanceof HTMLEditorKit))
        {
            MySystem.s_printOutError(this, strMethod, "wrong instanceof ekt");
            return false;
        }
        
        HTMLEditorKit ektHtml = (HTMLEditorKit) ekt;
        
        
        if (bln == true)
        {
            if (this._hypListenerParent != null)
                addHyperlinkListener(this._hypListenerParent);
            
            if (this._mouListenerParent != null)
                addMouseListener(this._mouListenerParent);
                
             ektHtml.setLinkCursor(Cursor.getPredefinedCursor(this._intTypeCursor));
        }
        
        else
        {
            if (this._hypListenerParent != null)
                removeHyperlinkListener(this._hypListenerParent);
            
            if (this._mouListenerParent != null)
                removeMouseListener(this._mouListenerParent);
                
            ektHtml.setLinkCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        
        return true;
    }
    
    public boolean setLinkCursorKit(int intVal)
    {
        String strMethod = "setLinkCursorKit(intVal)";
        EditorKit ekt = this.getEditorKit();
        
        if (! (ekt instanceof HTMLEditorKit))
        {
            MySystem.s_printOutError(this, strMethod, "wrong instanceof ekt");
            return false;
        }
        
        HTMLEditorKit ektHtml = (HTMLEditorKit) ekt;
        ektHtml.setLinkCursor(Cursor.getPredefinedCursor(intVal));
        
        // ending
        return true;
    }
    
    
    
    
    // get anchors
    public java.util.List<String> getListStringRef() // TEMPO AS PUBLIC
    {
        if (this._lstAnchor != null)
            return this._lstAnchor;
        
        String strMethod = "getListStringRef()";
        this._lstAnchor = Collections.synchronizedList(new ArrayList<String>());
        Document doc = getDocument();
		 
        // ---------- TEMPO

        if (! (doc instanceof HTMLDocument))
            MySystem.s_printOutExit(this, strMethod, "wrong doc instance");

        HTMLDocument docHtml = (HTMLDocument) doc;
       
        
        // ----
        
        // ----
        
        HTMLDocument.Iterator itr = docHtml.getIterator(HTML.Tag.A);
        
        while (true)
        {  
            if (! itr.isValid())
                break;   
            
            AttributeSet astCur = (AttributeSet) itr.getAttributes();
            
            for (Enumeration e = astCur.getAttributeNames() ; e.hasMoreElements() ;)
            {
                Object objKeyCur = e.nextElement();
                
                System.out.println("objKeyCur.toString()=" + objKeyCur.toString());
                
                if (! (objKeyCur.toString().equalsIgnoreCase("name")))
                    continue;
                
                 System.out.println("GOT IT: objKeyCur.toString()=" + objKeyCur.toString());
                Object objValueCur = astCur.getAttribute(objKeyCur);
                this._lstAnchor.add(objValueCur.toString());
            }
             
            try
            {
                itr.next();
            }
            
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "exc caught");   
            }
            
          
        }
        
        return this._lstAnchor;
    }
    
    // ---------
    // PROTECTED
    
    
    // TEMPO
    protected boolean _dumpStyleSheetRules_() // TEMPO AS PUBLIC
    {
        String strMethod = "_dumpStyleSheetRules_()";
        // -------
        
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
        
        StyleSheet sst = ektHtml.getStyleSheet();
        
        if (sst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil sst");
            return false;
        }
        
        // -------
        
        Enumeration rules = sst.getStyleNames();
   	    
   	    while (rules.hasMoreElements())
   	    {
   	        String name = (String) rules.nextElement();
   	        
   	        //System.out.println(">> name=" + name);
   	        
   	        Style rule = sst.getStyle(name);
   	        System.out.println("rule=" + rule.toString());
   	    }
   	
   	    // ending
   	    return true;
   	}
    
   
    
    
    
    protected EPTextHTMLAbs(
        HyperlinkListener hypListenerParent,
        MouseListener mouListenerParent,
        EPTextAbsListener pepListenerParent,
        Component cmpFrameOwner,
        String strTitleApplication, 
        Color colPageTextSelection,
        URL url,
        int intCursorType)
        
        //throws IOException
    {
        super(pepListenerParent, cmpFrameOwner, strTitleApplication, colPageTextSelection, url,
            new HTMLEditorKit());        
        
        // ----
        
        this._hypListenerParent = hypListenerParent;
        this._mouListenerParent = mouListenerParent;
        this._intTypeCursor = intCursorType;
        
        if (hypListenerParent != null)
            addHyperlinkListener(hypListenerParent);
            
        if (mouListenerParent != null)
            addMouseListener(mouListenerParent);
        
        HTMLEditorKit ektHtml = (HTMLEditorKit) this.getEditorKit();
        ektHtml.setLinkCursor(Cursor.getPredefinedCursor(intCursorType));
        
    }
    
    // -------
    // PRIVATE
    private java.util.List<String> _lstAnchor = null;
    private HyperlinkListener _hypListenerParent = null;
    private MouseListener _mouListenerParent = null;
    
    private int _intTypeCursor = Cursor.HAND_CURSOR;
} 
