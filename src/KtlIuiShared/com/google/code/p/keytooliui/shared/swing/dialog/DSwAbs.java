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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

/**
    "Sw" means "Secondary Window"
    
    known subclasses:
    . DSwUrlAbs ==> HTTP-FTP
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import java.awt.*;

abstract public class DSwAbs extends DAbs
{
    // ----------------------
    // FINAL STATIC PROTECTED
    
    final static protected int _f_s_intWidthMin_ = 500;
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._pnlContentPane_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlContentPane_");
            return false;
        }
        
        if (! this._pnlContentPane_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        
        
        cntContentPane.add(this._pnlContentPane_, BorderLayout.CENTER);
        
        return true;
    }
    
    public void destroy()
    {
        if (this._pnlContentPane_ != null)
        {
            this._pnlContentPane_.destroy();
            this._pnlContentPane_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected PCpSwAbs _pnlContentPane_ = null;
        
    protected DSwAbs(
        Dialog dlgOwner,
        String strTitle)
    {
        super(dlgOwner, strTitle);
        
        if (super._strTitleApplication_ != null)
            setTitle(super._strTitleApplication_);
    }
}