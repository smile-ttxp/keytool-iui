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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/*
    known subclasses:
    . TBPageSecWinUrlTextAbs
    . TBPageSecMediaAbs
    

    Contains imageButtonReload 16x16 pixels
    Contains imageButtonAbout 16x16 pixels
*/

import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.event.*;

abstract public class TBPageSecAbs extends TBAbs
{
    // ------
    // PUBLIC
    
    public void setEnabledPageReload(boolean bln)
    {
        if (this._btnPageReload == null)
            return;
            
        this._btnPageReload.setEnabled(bln);
    }
    
    
    public void destroy()
    {
        this._btnPageReload = null;
        this._btnPageAbout = null;
    }
    
    public boolean init()
    {
        // start
        addSeparator(TBAbs._f_s_dimSeparator10_);
        
        add(this._btnPageReload);
        addSeparator(TBAbs._f_s_dimSeparator4_);
        add(this._btnPageAbout);
        
        this._btnPageReload.setEnabled(false);
        this._btnPageAbout.setEnabled(true);
        
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected TBPageSecAbs(ActionListener actListenerParent)
    {
        super(
            SwingConstants.HORIZONTAL,
            false // blnFloatable
            );
        
        this._btnPageReload = new BESPageReload16(actListenerParent);
        this._btnPageAbout = new BESPageAbout16(actListenerParent);
    }
    
    // -------
    // PRIVATE
    
    private BESPageReload16 _btnPageReload = null;
    private BESPageAbout16 _btnPageAbout = null;
}