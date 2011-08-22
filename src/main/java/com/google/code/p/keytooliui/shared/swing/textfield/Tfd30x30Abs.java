/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.textfield;

/**

    FLAG_DEV: not columns=30, columns=40

    known subclasses:
    . TF30x20SelFile
    . TF30x20SelString
    . TF30x20SelUrlRem (remote URL)

    textfield:
    . columns = 30
    . height = 20
**/


import com.google.code.p.keytooliui.shared.lang.*;


public abstract class Tfd30x30Abs extends TFAbstract
{  
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final int _f_s_intColumns = 30;
    private static final int _f_s_intH = 30;
    
    private static final String _f_s_strDefault = "";
    
    // ------
    // PUBLIC
    
    public boolean isDefault()
    {
        String strMethod = "isDefault()";
        
        if (getText() == null)
            MySystem.s_printOutExit(this, strMethod, "getText() == null");
            
        if (getText().compareTo(Tfd30x30Abs._f_s_strDefault) == 0)
            return true;
            
        return false;
    }
    
    public void setDefault()
    {
        setText(Tfd30x30Abs._f_s_strDefault);
    }
    
    // ---------
    // PROTECTED
    
    // 
    
    protected Tfd30x30Abs(String strTip, javax.swing.event.DocumentListener docListenerParent)
    {
        super(strTip, Tfd30x30Abs._f_s_intColumns, Tfd30x30Abs._f_s_intH, docListenerParent);
        setDefault();
    }
    
    /*protected Tfd30x30Abs(String strTip)
    {
        this(strTip, (javax.swing.event.DocumentListener) null);
    }*/
}