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

package com.google.code.p.keytooliui.beans.util;

/**
    utilities for beans embedded in HTMLDocument
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.net.*;
import java.awt.*;

final public class UBeanInsideDocHTML
{
    // --------------------
    // FINAL STATIC PRIVATE

    final static private String _f_s_strClass = "com.google.code.p.keytooliui.beans.util.UBeanInsideDocHTML.";
	    
	// -------------
    // STATIC PUBLIC
    
    /**
        argument named cmp should be a bean, the HTML browser should be of type JEditorPane (not IceBrowser)
    **/
    static public URL s_getUrlBaseOwner(Component cmp)
    {
        String strMethod = _f_s_strClass + "s_getUrlBaseOwner(cmp)";
        MySystem.s_printOutFlagDev(strMethod, "uncomment here to catch all ancestors");
        
        while (cmp != null)
	    {        
	        //System.out.println("cmp.getClass().toString()=" + cmp.getClass().toString());
	        
	        if (cmp instanceof JViewport)
	        {
	            JViewport vpt = (JViewport) cmp;
	            Component cmpView = vpt.getView();
	            
	            if (cmpView == null)
	            {
	                MySystem.s_printOutError(strMethod, "nil cmpView");
	                return null;
	            }
	            
	            if (! (cmpView instanceof javax.swing.text.JTextComponent))
	            {
	                MySystem.s_printOutError(strMethod, "! (cmpView instanceof javax.swing.text.JTextComponent)");
	                return null;
	            }
	            
	            // ----
	            javax.swing.text.JTextComponent tct = (javax.swing.text.JTextComponent) cmpView;
	            javax.swing.text.Document doc = tct.getDocument();
	                
	            if (doc == null)
	            {
	                MySystem.s_printOutError(strMethod, "nil doc");
	                return null;
	            }
	                
	            // -----------
	            // not nil doc
	                
	         
	            if (! (doc instanceof javax.swing.text.html.HTMLDocument))
	            {
	                MySystem.s_printOutError(strMethod, "! (doc instanceof javax.swing.text.html.HTMLDocument)");
	                return null;
	            }
	                
	            // ---------------------------
	            // doc instanceof HTMLDocument
	          
	            javax.swing.text.html.HTMLDocument docHtml = (javax.swing.text.html.HTMLDocument) doc;
	            URL urlBaseDocHtml = docHtml.getBase();
	                
	            if (urlBaseDocHtml == null)
	            {
	                MySystem.s_printOutError(strMethod, "nil urlBaseDocHtml");
	                return null;
	            }
	                        
	            // successfully ending
	            //return urlBaseDocHtml;
	            
	            String strUrlBugFixed = com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getToString(urlBaseDocHtml);
	            
	            URL urlBugFixed = null;
	            
	            try
                {
                    urlBugFixed = new URL(strUrlBugFixed);
                }
                
                catch(MalformedURLException excMalformedURL)
                {
                    excMalformedURL.printStackTrace();
                    
                    MySystem.s_printOutError(strMethod, 
                        "excMalformedURL caught" +
                        ", strUrlBugFixed=" + 
                        strUrlBugFixed
                    );
                    
                    return null;
                }
                
                return urlBugFixed;
	            
	        }
        	
	        cmp = cmp.getParent();
	    }
	            
	    MySystem.s_printOutError(strMethod, "failed to get (parent) JViewport");
        return null;
    }
}