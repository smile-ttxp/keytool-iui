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


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.editorpane.*;

import javax.swing.text.html.*;
import javax.swing.event.*;
import javax.swing.*;

import java.net.*;
import java.awt.event.*;

final public class SPViewerFileTextHtmlJar extends SPViewerFileTextHtmlAbs
{    
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
    }
    
    public boolean doFileOpen(URL url, StyleSheet sst)
    {
        String f_strMethod = "doFileOpen(url, sst)";
        
        /*try
        {*/
            super._ntt_ = new EPTextHTMLJar(
                super._hlkListenerParent_,
                (MouseListener) null,
                null,
                null,
                null,
                java.awt.Color.red, // dummy value
                url,
                java.awt.Cursor.HAND_CURSOR, // dummy value
                sst);
       /*}   
       
       catch(java.io.IOException excIO)
       {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, f_strMethod, "excIO caught");
            return false;
       }*/
            // BUG FIXING: bug ID8: white border in JEditorPane, if using setStyleSheet that contains a different color)
        super._ntt_.setBackground(this.getBackground());
            
            
        // ?? trying to fix up bug in jdk1.2.2, see EPTextHTMLAbs for more info
        JViewport vpt = getViewport();
        vpt.add(super._ntt_);
            
        if (! super._ntt_.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        f_strMethod = null;
            
        return true;
    }
    
    
    public SPViewerFileTextHtmlJar(HyperlinkListener hlkListenerParent)
    {
        super(hlkListenerParent);
    }

    // -------
    // PRIVATE
}