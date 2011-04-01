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
 
 
package com.google.code.p.keytooliui.ktl.util.changer;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;
import com.google.code.p.keytooliui.shared.util.changer.*;


import javax.swing.*;

import java.io.*;

final public class ChgLocMainUITabsKtl extends ChgLocMainUITabsAbs
{
    // ------------------------
    // FINAL STATIC PRIVATE INT
    
    // memo: not necessary, BUT shows how to assign a non default location
    // in such a case, should use the default value assigned in this class, NOT in the superclass
    //final static private int _f_s_intDefault = ChgLocAbstract.F_S_INT_TOP; // ChgLocAbstract.F_S_INT_LEFT;
    
    // -----------------
    // STATIC PUBLIC INT   
    
    static public int S_INT_LAST = ChgLocMainUIAbs._f_s_intDefault;
    
    // ------
    // PUBLIC
    
    public boolean write(ObjectOutputStream oos)
    {
        String strMethod = "write(oos)";
        
        if (! super._write_(oos, ChgLocMainUITabsKtl.S_INT_LAST))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean read(ObjectInputStream ois)
    {
        String strMethod = "read(ois)";
        
        int intVal = super._read_(ois);
        
        if (intVal == -1)
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (intVal == ChgLocMainUITabsKtl._f_s_intDefault)
        {
            return true;
        }
        
        ChgLocMainUITabsKtl.S_INT_LAST = intVal;
        return true;
    }
    
    public boolean assignLast()
    {
        String strMethod = "assignLast()";
        
        if (ChgLocMainUITabsKtl.S_INT_LAST == -1)
        {
            MySystem.s_printOutError(this, strMethod, "ChgLocMainUITabsKtl.S_INT_LAST == -1");
            return true;
        }
        
	    // not checked!! range!!
	    if (! __set__(ChgLocMainUITabsKtl.S_INT_LAST))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
   
    
    public ChgLocMainUITabsKtl(FMainAbs fmn)
    {
        super(fmn);
    }
    
    // --------
    // PROTECTED
    
    // also called by subclass
    protected boolean __set__(int intValue)
    {
        ChgLocMainUITabsKtl.S_INT_LAST = intValue;
        return super._set_(intValue);
    }
}