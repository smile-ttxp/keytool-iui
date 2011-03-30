/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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


import com.google.code.p.keytooliui.shared.util.eventlistener.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.text.html.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;
import java.net.*;

final public class EPTextHTMLSys extends EPTextHTMLAbs
{
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        //this._strPathAbsoluteCSS = null;
        this._sst = null;
    }
    
    /*
        sst may be nil
    */
    public EPTextHTMLSys(
        HyperlinkListener hypListenerParent,
        MouseListener mouListenerParent,
        EPTextAbsListener lisParent,
        Component cmpFrameOwner,
        String strTitleAppli,
        Color colPageTextSelection,
        URL url,
        StyleSheet sst
        )
        
        //throws IOException
    {
        super(
            hypListenerParent,
            mouListenerParent,
            lisParent, 
            cmpFrameOwner, 
            strTitleAppli, 
            colPageTextSelection, 
            url, 
            Cursor.HAND_CURSOR);
     
        
        //this._strPathAbsoluteCSS = strPathAbsoluteCSS;
        this._sst = sst;
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! _setStyleSheet())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
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
    
    //private String _strPathAbsoluteCSS;
    private StyleSheet _sst = null;
    
    private boolean _setStyleSheet()
    {
        String strMethod = "_setStyleSheet()";
        
        // tempo
        //if (true) return true;
        
        javax.swing.text.EditorKit editorkit = getEditorKit();
        
        if (editorkit == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil editorkit");
            return false;
        }
        
        if (! (editorkit instanceof HTMLEditorKit))
        {
            MySystem.s_printOutError(this, strMethod, "wrong instanceof editorkit");
            return false;
        }
        
        HTMLEditorKit htmleditorkit = (HTMLEditorKit) editorkit;
        
        if (htmleditorkit == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil htmleditorkit");
            return false;
        }
        
        if (this._sst != null)
        {
            htmleditorkit.setStyleSheet(this._sst);
            return true;
        }
        
        this._sst = com.google.code.p.keytooliui.shared.swing.text.html.stylesheet.S_StyleSheet.s_getDefault();
            
        if (this._sst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._sst (default)");
            return false;
        }
            
        htmleditorkit.setStyleSheet(this._sst);
        
        
        
        /*StyleSheet stylesheet = null;
        
        if (this._sst == null)
        {
            stylesheet = com.google.code.p.keytooliui.shared.swing.text.html.stylesheet.S_StyleSheet.s_getDefault();
            
            if (stylesheet == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil stylesheet");
                return false;
            }
            
            htmleditorkit.setStyleSheet(stylesheet);
            return true;
        }*/
        
        /*try
        {
            FileReader filereader = new FileReader(this._strPathAbsoluteCSS);
            stylesheet = new StyleSheet();
            stylesheet.loadRules(filereader, null);
        }
        
        catch(FileNotFoundException excFileNotFound)
        {
            excFileNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excFileNotFound caught");
            return false;
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return false;
        }
        
        htmleditorkit.setStyleSheet(stylesheet);
        */
        
        // ending
        return true;
    }
}