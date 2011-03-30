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
 
 
package com.google.code.p.keytooliui.shared.swing.scrollpane;

/**
    known subclasses:
    . SPViewerFileTextHtmlAbs
    . SPViewerFileTextRtfAbs
**/

import com.google.code.p.keytooliui.shared.swing.editorpane.*;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


abstract public class SPViewerFileTextAbs extends JScrollPane
{        
    // ------
    // PUBLIC
    
    public boolean doPageUrlTextPrint()
    {
        String strMethod = "doPageUrlTextPrint()";
        
        if (this._ntt_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._ntt_");
            return false;
        }
        
        return this._ntt_.doPageUrlTextPrint();
    }
    
    public boolean init()
    {   
        return true;
    }
    
    public void destroy()
    {
        if (this._ntt_ != null)
        {
            this._ntt_.destroy();
            this._ntt_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected EPTextAbs _ntt_ = null;
    
    protected SPViewerFileTextAbs()
    {
        super();
    }
    
    
}