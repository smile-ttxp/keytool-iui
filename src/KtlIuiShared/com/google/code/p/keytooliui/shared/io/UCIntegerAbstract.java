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
 
 
 package com.google.code.p.keytooliui.shared.io;

/**
    known subclasses:
    . rcr: UCIntegerChooserAppli
    . rcr: UCIntegerMotionBmkRdr
**/

import com.google.code.p.keytooliui.shared.swing.frame.*;

import java.io.*;

import com.google.code.p.keytooliui.shared.lang.*;

abstract public class UCIntegerAbstract extends UserChoice
{        
    // ------
    // PUBLIC
    
    public void destroy()
    {
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._fma_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._fma_");
            return false;
        }
        
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected FMainAbs _fma_ = null;
    
    protected UCIntegerAbstract(FMainAbs fma)
    {
        super();
        this._fma_ = fma;
    }
    
    
    protected Integer _read_(ObjectInputStream ois)
        throws Exception
    {
        String strMethod = "_read_(ois)";
        
        if (ois == null)
            MySystem.s_printOutExit(this, strMethod, "nil ois"); 
        
        return (Integer) ois.readObject();
    }
    
    protected void _write_(ObjectOutputStream oos, Integer itg)
         throws Exception
    {
        String strMethod = "_write_(oos, itg)";
        
        if (oos==null || itg==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        oos.writeObject(itg);
    }
}