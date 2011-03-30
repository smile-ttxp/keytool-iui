/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
 package com.google.code.p.keytooliui.shared.lang.bool;

/**
    a 3-state renderer
    for use in table, contains:
    . a "checked" image
    . a "notChecked" warning image
    . an intState
    
    if (value == _intChecked)
    {
        returns:
        . a "checked" icon
        . a nil text
    }
    
    else if (value == _intUnchecked)
    {
        returns:
        . a warning icon
        . a nil text
    }
    
    else if (value == _intUnchecked)
    {
        returns:
        . a nil icon
        . a "-" text
    }
    
**/

import com.google.code.p.keytooliui.shared.swing.imageicon.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


public class BOChecked3StateWarning extends BOChecked3StateAbstract
{

    
    
    // -------------
    // PUBLIC ASSIGN
    
    
    
    
    // -------------
    // PUBLIC ACCESS
    
    
    
    // ------
    // PUBLIC
    
    public BOChecked3StateWarning(int intState)
    {
        super(intState);
    }

    
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (! super._init_())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (! _createResourceImage())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
            
            
        return true;
    }
    
    public void destroy()
    {
        super._destroy_();
    }
    
    // -------
    // PRIVATE
    
    
    private boolean _createResourceImage()
    {
        String f_strMethod = "_createResourceImage()";
        
        ImageIcon iin = null;
        String strName = null;
	    
	    // -------
        // unchecked
        
        strName = "xred.gif"; 
        
        super._iinUnchecked_ = S_IINShared.s_get(strName);
	    
	    if (super._iinUnchecked_ == null)
	    {
	        MySystem.s_printOutError(this, f_strMethod, strName + ": nil super._iinUnchecked_");
	        return false;
	    }
	    
	    return true;
	}
}