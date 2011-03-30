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


import com.google.code.p.keytooliui.beans.button.*;

import com.google.code.p.keytooliui.shared.lang.*;

final public class A2WebMailer extends A2WebAbs 
{
    // ------
    // PUBLIC
    
    public void init()
    {
        String strMethod = "init()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return;
        }
        
        if (! _assignParams())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            getContentPane().remove(super._cmpBean_);
            super._cmpBean_ = null;
            return; // not really needed!
        }
    }

    public A2WebMailer()
    {
        super();
        super._cmpBean_ = new B2WebMailer();
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
        
        str = getParameter("address");
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil str, mandatory parameter \"address\" missing");
            return false;
        }
        
        ((B2WebMailer) super._cmpBean_).setAddress(str);
        
        // --------
        // optional
        
        // --
        str = getParameter("subject");
        
        if (str != null)
        {
            ((B2WebMailer) super._cmpBean_).setSubject(str);
        }
 
        // ------
        // ending
        return true;
    }
}