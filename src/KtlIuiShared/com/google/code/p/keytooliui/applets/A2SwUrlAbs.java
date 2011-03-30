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


package com.google.code.p.keytooliui.applets;


/**
   "A2SwUrlAbs" means "Applet to secondary window, pointing to an URL, abstract class"


    known subclasses:
    . A2SwUrlMediaAbs
    . A2SwUrlTextAbs
**/

import com.google.code.p.keytooliui.beans.button.*;

import com.google.code.p.keytooliui.shared.lang.*;

import java.net.*;

abstract public class A2SwUrlAbs extends A2SwAbs
{
    // ---------
    // PROTECTED
    
    protected boolean _init_()
    {
        String strMethod = "_init_()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
         
        if (! _assignParams())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            getContentPane().remove(super._cmpBean_);
            super._cmpBean_ = null;
            return false;
        }
        
        return true;
    }
    
    protected A2SwUrlAbs()
    {
        super();
    }
    
    // -------
    // PRIVATE
    
    private boolean _assignParams()
    {
        String strMethod = "_assignParams()";
        
        if (super._cmpBean_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._cmpBean_");
            return false;
        }
        
        String str = null;
        
        // ---------
        // mandatory
        
        str = getParameter("url");
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil str, mandatory parameter \"url\" missing");
            return false;
        }
        
        ((B2SwUrlAbs) super._cmpBean_).setUrl(str);
        
        // --------
        // optional
        
        // --
        str = getParameter("urlIsRelative");
        
        if (str != null)
        {
            // 1/2
            ((B2SwUrlAbs) super._cmpBean_).setUrlIsRelative(str);
            
            // 2/2
            URL url = getDocumentBase();
            
            if (url == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil url");
                return false;
            }
            
            ((B2SwUrlAbs) super._cmpBean_).setUrlBaseHtmlOwner(url);
        }
 
        // ------
        // ending
        return true;
    }
}