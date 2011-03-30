/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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


import com.google.code.p.keytooliui.shared.swing.scrollpane.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.event.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.net.*;

final public class PPageSwUrlTextHTML extends PPageSwUrlTextAbs 
{
    // ------
    // PUBLIC
    
    // used while cliked on a link inside active HTML page
    public boolean select(URL url)
    {
        //String strMethod = "select(url)";
        
        if (super._spn_ != null)
        {
            boolean blnOk = ((SPPageTextSWHTML) super._spn_).select(url);
            
            if (blnOk)
                super._objRL_ = (Object) url;
                
            return blnOk;
        }
        
        //!!
        return true; 
    }
    
    
    public PPageSwUrlTextHTML(
        URL url,
        java.awt.Window winParent,
        String strApplicationTitle,
        HyperlinkListener hypListenerParent,
        java.awt.Color colTextSelection)
    {
        super(url, winParent, strApplicationTitle);
        
        super._spn_ = new SPPageTextSWHTML(
            (Component) winParent, 
            strApplicationTitle,
            url,
            hypListenerParent,
            (MouseListener) null,
            colTextSelection
        );
    }
}