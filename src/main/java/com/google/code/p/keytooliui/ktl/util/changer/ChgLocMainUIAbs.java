/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.util.changer;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;
import com.google.code.p.keytooliui.shared.util.changer.*;


import javax.swing.*;

import java.io.*;

public abstract class ChgLocMainUIAbs extends ChgLocAbstract
{
    // --------------------------
    // PROTECTED STATIC FINAL INT
    
    // memo: not necessary, BUT shows how to assign a non default location
    // in such a case, should use the default value assigned in this class, NOT in the superclass
    protected static final int _f_s_intDefault = ChgLocAbstract.F_S_INT_TOP; // ChgLocAbstract.F_S_INT_LEFT;
    
    // ------------------
    // ABSTRACT PROTECTED
    
    protected abstract boolean __set__(int intValue);
   
    
    // ------
    // PUBLIC
    
    public boolean setTop()
    {
        String strMethod = "setTop()";
        
        if (! __set__(ChgLocAbstract.F_S_INT_TOP))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean setLeft()
    {
        
        String strMethod = "setLeft()";
        
        if (! __set__(ChgLocAbstract.F_S_INT_LEFT))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean setBottom()
    {
        
        String strMethod = "setBottom()";
        
        if (! __set__(ChgLocAbstract.F_S_INT_BOTTOM))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean setRight()
    {
        String strMethod = "setRight()";
        
        if (! __set__(ChgLocAbstract.F_S_INT_RIGHT))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
   
    // ---------
    // PROTECTED
    
    protected ChgLocMainUIAbs(FMainAbs fmn)
    {
        super(fmn);
    }
    
    
}