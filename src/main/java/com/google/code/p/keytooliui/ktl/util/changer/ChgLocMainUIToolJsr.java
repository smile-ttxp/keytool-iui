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
 
 
package com.google.code.p.keytooliui.ktl.util.changer;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.changer.*;
import com.google.code.p.keytooliui.shared.swing.frame.*;

import javax.swing.*;

import java.io.*;

public final class ChgLocMainUIToolJsr extends ChgLocMainUIToolAbs
{   
    // -----------------
    // PUBLIC STATIC INT   
    
    public static int S_INT_LAST = ChgLocMainUIAbs._f_s_intDefault; // ChgLocAbstract.F_S_INT_DEFAULT;
    
    
    // ------
    // PUBLIC
    
    public boolean write(ObjectOutputStream oos)
    {
        String strMethod = "write(oos)";
        
        if (! super._write_(oos, ChgLocMainUIToolJsr.S_INT_LAST))
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
        
        if (intVal == ChgLocMainUIToolJsr._f_s_intDefault)
        {
            return true;
        }
        
        ChgLocMainUIToolJsr.S_INT_LAST = intVal;
        return true;
    }
    
    public boolean assignLast()
    {
        String strMethod = "assignLast()";
        
        if (ChgLocMainUIToolJsr.S_INT_LAST == -1)
        {
            MySystem.s_printOutError(this, strMethod, "ChgLocMainUIToolJsr.S_INT_LAST == -1");
            return true;
        }
        
	    // not checked!! range!!
	    if (! __set__(ChgLocMainUIToolJsr.S_INT_LAST))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
       
    
    public ChgLocMainUIToolJsr(FMainAbs fmn)
    {
        super(fmn);
    }
    
    // ---------
    // PROTECTED
    
    // also called by subclass
    protected boolean __set__(int intValue)
    {
        ChgLocMainUIToolJsr.S_INT_LAST = intValue;
        return super._set_(intValue);
    }
}