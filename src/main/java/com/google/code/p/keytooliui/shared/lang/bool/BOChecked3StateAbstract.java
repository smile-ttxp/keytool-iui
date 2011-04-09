/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.lang.bool;

/**
    known subclasses:
    . BOChecked3StateWarning
    . BOChecked3StateUnchecked

    a 3-state renderer
    for use in table, contains:
    . a "checked" image
    . an "unchecked" image
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
        . an unchecked icon
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


abstract public class BOChecked3StateAbstract extends BOCheckedAbs
{
    // ------------------------
    // PUBLIC STATIC FINAL INT
    
    public static final int f_s_intChecked = 1;
    public static final int f_s_intUnchecked = 2;
    public static final int f_s_intNotApplicable = 3;
    
    
    // -------------
    // PUBLIC ASSIGN
    
    
    
    
    // -------------
    // PUBLIC ACCESS
    
    public String getText()
    {
        if (this._intState == BOChecked3StateAbstract.f_s_intNotApplicable)
            return "-";
        else
            return null;
    }
    
    public ImageIcon getIcon()
    {
        if (this._intState == BOChecked3StateAbstract.f_s_intChecked)
            return this._iinChecked;
        else if (this._intState == BOChecked3StateAbstract.f_s_intUnchecked)
            return this._iinUnchecked_;
        else
            return null;
    }
    
    
    // ------
    // PUBLIC
    
    
    
    // ---------
    // PROTECTED
    
    protected ImageIcon _iinUnchecked_ = null;
    
    protected BOChecked3StateAbstract(int intState)
    {
        this._intState = intState;
    }

    protected boolean _init_()
    {
        String f_strMethod = "_init_()";
        
        if (this._intState!=f_s_intChecked &&  this._intState!=f_s_intUnchecked && this._intState!=f_s_intNotApplicable)
        {
            MySystem.s_printOutError(this, f_strMethod, "wrong value, this._intState=" + this._intState);
            return false;
        }
        
        if (! _createResourceImage())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
            
            
        return true;
    }
    
    protected void _destroy_()
    {
    }
    
    // -------
    // PRIVATE
    
    private int _intState = -1;
    private ImageIcon _iinChecked = null;
    
    
    private boolean _createResourceImage()
    {
        String strMethod = "_createResourceImage()";
        
        ImageIcon iin = null;
        String strName = null;
        
        
        
        // -------
        // checked
        
        strName = "checked16.gif"; 
        
        this._iinChecked = S_IINShared.s_get(strName);
	    
	    if (this._iinChecked == null)
	    {
	        MySystem.s_printOutError(this, strMethod, strName + ": nil this._iinChecked");
	        return false;
	    }
	    
	    // -------
        // unchecked
        
        /*strName = "xred.gif"; 
        
        this._iinUnchecked_ = S_IINShared.s_get(strName);
	    
	    if (this._iinUnchecked_ == null)
	    {
	        MySystem.s_printOutError(this, strMethod, strName + ": nil this._iinUnchecked_");
	        return false;
	    }*/
	    
	    return true;
	}
}