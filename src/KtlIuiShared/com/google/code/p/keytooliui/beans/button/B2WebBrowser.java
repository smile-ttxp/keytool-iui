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
    
    a button displayed as a label, while clicked: open up user's default browser/mailer
    
    "B" for "Button"
    "Web" for "System's web browser/mailer"
    "Abs" for "Abstract class"


    Known subclasses:
    . B2WebBrowser
    . B2WebMailer
**/


import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.event.*;

final public class B2WebBrowser extends B2WebAbs
{
    // --------------------
    // final static private
    
    final static private String _f_s_strErrorTitle = com.google.code.p.keytooliui.beans.BeanInfoAbs.f_s_strName + " - " + "URL";
    final static private String _f_s_strErrorBodyPrefix = "Failed to open Web browser";
    
    
    // ------
    // PUBLIC
    
    // ----------------
    // begin properties
    
    
    // should be a fully qualified URL, eg: http://www.foo.com
    public void setUrl(String str) { this._strUrl = str; }
    public String getUrl() { return this._strUrl; }
    
    // --------------
    // end properties
    
    public B2WebBrowser()
    {
        super();
    }
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
        
        if (this._strUrl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strUrl");
            _showDialogWarningFailed();
            return;
        }
        
        if (this._strUrl.toLowerCase().startsWith("jar:") == true)
        {
            MySystem.s_printOutError(this, strMethod, "url pointing to JAR, this._strUrl=" + this._strUrl);
            _showDialogWarningFailed();
            return;
        }
        
       
        String strErrorBody = new String(B2WebBrowser._f_s_strErrorBodyPrefix);
        strErrorBody += "\n\n" + "URL:" + "\n" + "  " + this._strUrl;
        
        if (! super._doJob_(B2WebBrowser._f_s_strErrorTitle, strErrorBody, this._strUrl))
        {
            MySystem.s_printOutError(this, strMethod, "failed, this._strUrl=" + this._strUrl);
            //_showDialogWarningFailed(); // handled in superclass
            return;
        }     
    }
    
    // -------
    // PRIVATE
    
    private String _strUrl = null;
    
    
    private void _showDialogWarningFailed()
    {
        String strErrorBody = new String(B2WebBrowser._f_s_strErrorBodyPrefix);;
        
        if (this._strUrl != null)
            strErrorBody += "\n\n" + "URL:" + "\n" + "  " + this._strUrl;
            
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
            null, B2WebBrowser._f_s_strErrorTitle, strErrorBody);
    }
}