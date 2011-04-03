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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/*
    known subclasses:
    . TBPageSecWinUrlTextHTML
    . TBPageSecWinUrlTextRTF
    
    "PageSecWin" means "Page Secondary Window"
    Contains 2 imageButtons 16x16 pixels:
    . find text
    . print page
*/

import com.google.code.p.keytooliui.shared.swing.button.*;

import java.awt.event.*;

abstract public class TBPageSecWinUrlTextAbs extends TBPageSecAbs
{
    // ------
    // PUBLIC
    
    public void setEnabledFindText(boolean bln)
    {
        if (this._btnFindText == null)
            return;
            
        this._btnFindText.setEnabled(bln);
    }
    
    public void setEnabledPagePrint(boolean bln)
    {
        if (this._btnPrint == null)
            return;
            
        this._btnPrint.setEnabled(bln);
    }
    
    public void destroy()
    {
        super.destroy();
        
        if (this._btnFindText != null)
        {
            this._btnFindText.destroy();
            this._btnFindText = null;
        }
        
        if (this._btnPrint != null)
        {
            this._btnPrint.destroy();
            this._btnPrint = null;
        }
        
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // start
        addSeparator(TBAbs._f_s_dimSeparator4_);
        add(this._btnFindText);
        addSeparator(TBAbs._f_s_dimSeparator4_);
        add(this._btnPrint);
        
        this._btnFindText.setEnabled(false);
        this._btnPrint.setEnabled(false);
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected TBPageSecWinUrlTextAbs(ActionListener actListenerParent)
    {
        super(actListenerParent);
        
        this._btnPrint = new BESPrint16(actListenerParent);
        this._btnFindText = new BESFindText16(actListenerParent);
    }
    
    // -------
    // PRIVATE
    
    private BESPrint16 _btnPrint = null;
    private BESFindText16 _btnFindText = null;
    
}