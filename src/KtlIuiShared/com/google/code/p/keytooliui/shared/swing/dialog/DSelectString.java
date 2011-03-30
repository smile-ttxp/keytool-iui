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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;


import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;

final public class DSelectString extends DEscapeAbstract
{
    // ------
    // PUBLIC
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._opn == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._opn");
            return false;
        }
        
        if (! this._opn.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public String getValidatedText()
    {
        return this._strTypedText;
    }
    
    public DSelectString(Frame frmParent, String strTitle)
    {
        super(frmParent, true);
        
        String strMethod = "DSelectString(frmParent, strTitle)";
        
        setTitle(strTitle);

        final String msgString1 = "Please type the text";
        final JTextField textField = new JTextField(10);
        
        Object[] array = {msgString1, textField};

        final String f_strButtonTextEnter = "OK";
        final String f_strButtonTextCancel = "CANCEL";
        
        
        Object[] options = {f_strButtonTextEnter, f_strButtonTextCancel};
        
        this._opn = new OPInputQuestion(array, options);
        
        setContentPane(this._opn);
        

        textField.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (_opn != null)
                    _opn.setValue(f_strButtonTextEnter);
            }
        });

        this._opn.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent e)
            {
                String prop = e.getPropertyName();

                if (isVisible() 
                    && (e.getSource() == _opn)
                    && (prop.equals(OPInputQuestion.VALUE_PROPERTY) ||
                    prop.equals(OPInputQuestion.INPUT_VALUE_PROPERTY)))
                {
                    Object value = _opn.getValue();

                    if (value == OPInputQuestion.UNINITIALIZED_VALUE)
                    {
                        //ignore reset
                        return;
                    }

                    if (value.equals(f_strButtonTextEnter))
                    {
                        _strTypedText = textField.getText().trim();
                        _cancel_();
                    }
                    
                    else
                    { // user closed dialog or clicked cancel
                       
                       _cancel_();
                        
                    }
                }
            }
        });
    }
    
    // ---------
    // PROTECTED
    
    protected void _cancel_()
    {
        if (this._opn != null)
        {
            this._opn.destroy();
            this._opn = null;
        }
        
        super._cancel_();
    }
    
    // -------
    // PRIVATE

    private String _strTypedText = null;
    private OPInputQuestion _opn;
}