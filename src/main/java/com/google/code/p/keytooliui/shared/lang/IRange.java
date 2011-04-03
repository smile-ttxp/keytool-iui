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
 
 
 package com.google.code.p.keytooliui.shared.lang;


import javax.swing.*;

public class IRange extends Object
{
    // -----------
    // PUBLIC TOOL
    
    public boolean outOfBounds(int intVal)
    {
        if (intVal < this._intStart)
            return true;
        
        if (intVal > this._intEnd)
            return true;
            
        return false;
    }
    

    
    // ---------------
    // PUBLIC ACCESSOR
    
    public int getStart() { return this._intStart; }
    public int getEnd() { return this._intEnd; }
    
        // ------
    // PUBLIC
    
    
    public IRange(int intStart, int intEnd)
    {
        this._intStart = intStart;
        this._intEnd = intEnd;
    }
    
    
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (this._intEnd <= this._intStart)
        {
            MySystem.s_printOutError(this, f_strMethod, "this._intStart=" + this._intStart + ", this._intEnd=" + this._intEnd + ", wrong values");
            return false;
        }
        
        return true;
    }
    
    public void destroy()
    {
        //String f_strMethod = "destroy()";
        //MySystem.s_printOutTrace(this, f_strMethod, "VOID");
    }

    
    // -------
    // PRIVATE
    
    private int _intStart = 0;
    private int _intEnd = 0; // !!!!!!!
}