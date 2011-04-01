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
 
    known subclasses:
    . SPPageTextSWHTML
    . SPPageTextSWRTF
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.swing.editorpane.*;
 
 import javax.swing.*;
 
 import java.net.*;
 
 abstract public class SPPageTextSWAbs extends SPPageTextAbs
 {
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean pageReload();
    
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        this._url_ = null;
    }
    
    
    // ---------
    // PROTECTED
    
    protected URL _url_ = null;
    
    protected SPPageTextSWAbs(URL url)
    {
        super();
        
        this._url_ = url;
    }
    
    // !!!!!!!! same code as "init()" !!!!!!!!
    protected boolean _reload_()
    {
        String strMethod = "_reload_()";
        
        if (this._pep_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pep_");
            return false;
        }
            
        

        JViewport vpt = getViewport();
        vpt.add(this._pep_);
            
        if (! this._pep_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
 }