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

package com.google.code.p.keytooliui.beans.button;

/**
    IMPORTANT: for now, only in use with JWS (JavaWebStart)
    
    a button displayed as a label, while clicked: open up user's default mailer
    
    "B" for "Button"
    "Web" for "System's web mailer"
    "Abs" for "Abstract class"

**/


import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.event.*;

final public class B2WebMailer extends B2WebAbs
{
    // --------------------
    // final static private
    
    final static private String _f_s_strErrorTitle = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + "EMAIL";
    final static private String _f_s_strErrorBodyPrefix = "Failed to open Web mailer";
    // ------
    // PUBLIC
    
    // ----------------
    // begin properties
    
    
    public void setAddress(String str) { this._strAddress = str; }
    public void setSubject(String str) { this._strSubject = str; }
    
    public String getAddress() { return this._strAddress; }
    public String getSubject() { return this._strSubject; }
    
    // --------------
    // end properties
    
    public B2WebMailer()
    {
        super();
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
             
        String strUrl2Check = _getUrl2Checked();
        
        if (strUrl2Check == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strUrl2Check");
            _showDialogWarningFailed();
            return;
        }
        
   
        String strErrorBody = new String(B2WebMailer._f_s_strErrorBodyPrefix);
        strErrorBody += "\n\n" + "EMAIL:" + "\n" + "  " + this._strAddress;
        
        if (! super._doJob_(B2WebMailer._f_s_strErrorTitle, strErrorBody, strUrl2Check))
        {
            MySystem.s_printOutError(this, strMethod, "failed, strUrl2Check=" + strUrl2Check);
            //_showDialogWarningFailed(); //handled in superclass
            return;
        }  
    }
    
    // -------
    // PRIVATE
    
    private String _strAddress = null;
    private String _strSubject = null;
    
    
    private void _showDialogWarningFailed()
    {
        
        String strErrorBody = new String(B2WebMailer._f_s_strErrorBodyPrefix);
        
        if (this._strAddress != null)
            strErrorBody += "\n\n" + "EMAIL:" + "\n" + "  " + this._strAddress;
            
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
            null, B2WebMailer._f_s_strErrorTitle, strErrorBody);
    }
    
    private String _getUrl2Checked()
    {
        String strMethod = "_getUrl2Checked()";
        
        if (this._strAddress == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strAddress");
            return null;
        }
        
        if (this._strAddress.length() < 3) // eg: a@b
        {
            MySystem.s_printOutError(this, strMethod, "this._strAddress.length() < 3, this._strAddress=" + this._strAddress);
            return null;
        }
        
        if (this._strAddress.indexOf("@") == -1) // should be <1, or >length-1
        {
            MySystem.s_printOutError(this, strMethod, "this._strAdress.indexOf(\"@\") == -1, this._strAddress=" + this._strAddress);
            return null;
        }
        
        String strUrl2Check = "mailto:";
        strUrl2Check += this._strAddress;
        
        if (this._strSubject != null)
        {
            strUrl2Check += "?SUBJECT=";
            strUrl2Check += this._strSubject;
        }
        
        return strUrl2Check;
    }
}