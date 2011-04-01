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

package com.google.code.p.keytooliui.applets;


/**
   "A2SwAbs" means "Applet to secondary window, abstract class"


    known subclasses:
    . A2SwUrlAbs
    . A2SwRtpAbs
**/


import com.google.code.p.keytooliui.beans.button.*;

import com.google.code.p.keytooliui.shared.lang.*;

abstract public class A2SwAbs extends A2Abs
{    
    // ---------
    // PROTECTED

    protected A2SwAbs()
    {
        super();
    }
    
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
    
    // -------
    // PRIVATE
    
    private boolean _assignParams()
    {
        String strMethod = "_assignParams()";
        
        String str = null;
        
        // ---------
        // mandatory
        
        // void ....
        
        // --------
        // optional

        
        // --
        str = getParameter("windowTitle");
        
        if (str != null)
        {
            ((B2SwAbs) super._cmpBean_).setWindowTitle(str);
        }       
        
        // ------
        // ending
        return true;
    }
}