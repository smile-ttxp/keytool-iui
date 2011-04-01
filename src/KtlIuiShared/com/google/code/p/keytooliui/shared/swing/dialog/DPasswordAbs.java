/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
    known subclasses:
    
    . DPasswordOpen
    . DPasswordConfirmAbs (save, change)
**/

import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

abstract public class DPasswordAbs extends DEscapeAbstract implements 
    ActionListener
{
    // ---------------
    // ABSTRACT PUBLIC
    
    
    
    // ------------------
    // ABSTRACT PROTECTED
    
    abstract protected boolean _contentsOk_();
    
    // ------
    // PUBLIC
    
    public char[] getPassword() { return this._chrsPassword_; }
    
    public void actionPerformed(ActionEvent evt) 
    {
        _cancel_();
    }
    
    
    public boolean init()
    {
        // ----
        
        // contents
        this._pnlContents_.setBorder(new javax.swing.border.EmptyBorder(4, 4, 4, 4));
        
        // action
        
        this._pnlAction.add(this._btnOk);
        this._pnlAction.add(this._btnCancel);

        // all
        
        getContentPane().add(this._pnlContents_, BorderLayout.CENTER);
        getContentPane().add(this._pnlAction, BorderLayout.SOUTH);
        setResizable(false);
        getRootPane().setDefaultButton(this._btnOk);
        
        
        // ----
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected JPanel _pnlContents_ = null;
    protected char[] _chrsPassword_ = null;
    
    protected DPasswordAbs(
        Component cmpFrameOwner,
        String strTitleThis)
    {
        super((Frame) cmpFrameOwner, 
            System.getProperty("_appli.title") + " - " + strTitleThis,
            true); // true ==> modal=true
        
                 
        // ----
        
        this._btnOk = new JButton("OK");
        
        
        this._btnCancel = new BCancel((ActionListener) this);
        //
        
        this._pnlContents_ = new JPanel();
        this._pnlAction = new JPanel();  
        
        
        // ----
        getContentPane().setLayout(new BorderLayout());
        this._pnlAction.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        
        
        this._btnOk.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt) 
            {
                if (_contentsOk_())
                    _cancel_();
                
            }
        });
        
        /**this._btnCancel.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt) 
            {
                _cancel_();
            }
        });**/
    }
    
    
    // -------
    // PRIVATE
    
    private JButton _btnOk = null;
    private JButton _btnCancel = null;
    
    
    private JPanel _pnlAction = null;
}