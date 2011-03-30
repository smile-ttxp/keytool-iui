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
    Important: only works with JWS-powered applications
    
    a button displayed as a label, while clicked: open up a Web [browser/mailer]
    
    "B" for "Button"
    "Web" for "System's web browser/mailer"
    "Abs" for "Abstract class"


    Known subclasses:
    . B2WebBrowser
    . B2WebMailer
**/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.border.*;

import java.net.*;


abstract public class B2WebAbs extends B2Abs
{

    // ------
    // PUBLIC    
    
    
    

    // ---------
    // PROTECTED
    
    protected boolean _doJob_(
        String strErrorTitle, // in case of failure
        String strErrorBody,
        String strUrl2Check)
    {
        String strMethod = "_doJob_(strUrl2Check)";
        
        if (strUrl2Check == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strUrl2Check");
            strErrorBody += "\n" + "nil url"; // !!!!!!! RATHER EXIT !!!!!!!
            _showDialogWarningFailed(strErrorTitle, strErrorBody);
            return false;    
        }
        
        // ----
        
        
        URL urlChecked = null;
        
        
        try
        {
            urlChecked = new URL(strUrl2Check);
        }
            
        catch(MalformedURLException excMalformedURL)
        {
            excMalformedURL.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excMalformedURL caught, strUrl2Check=" + strUrl2Check);
            strErrorBody += "\n\n";
            strErrorBody += excMalformedURL.toString();
            _showDialogWarningFailed(strErrorTitle, strErrorBody);
            return false;
        }

        
        // ---------
        // JWS stuff
        
        final String f_strBseJWS = "javax.jnlp.BasicService";
        
        try
        {
            Class cls1 = Class.forName(f_strBseJWS);
            Class cls2 = Class.forName("javax.jnlp.ServiceManager");
            
            //try 
            //{
                javax.jnlp.BasicService bseJWS = 
                    (javax.jnlp.BasicService) javax.jnlp.ServiceManager.lookup(f_strBseJWS);
                
                bseJWS.showDocument(urlChecked);
            //}
        
            /* cannot use this one, in case no classdeffound
            catch(javax.jnlp.UnavailableServiceException excUnavailableService) 
            {
                excUnavailableService.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excUnavailableService caught");
                return false;
            }*/
        }
        
        catch(ClassNotFoundException excClassNotFound)
        {
            excClassNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassNotFound caught");
            strErrorBody += "\n\n";
            strErrorBody += excClassNotFound.toString();
            _showDialogWarningFailed(strErrorTitle, strErrorBody);
            return false;
        }
        
        catch(Exception exc) 
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            strErrorBody += "\n\n";
            strErrorBody += exc.toString();
            _showDialogWarningFailed(strErrorTitle, strErrorBody);
            return false;
        }
        
        return true;
    }
    
    protected B2WebAbs()
    {
        super();   
    } 
    
    
    private void _showDialogWarningFailed(
        String strTitle,
        String strBody
        )
    {
        com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogWarning(
            null, strTitle, strBody);
    }
}
