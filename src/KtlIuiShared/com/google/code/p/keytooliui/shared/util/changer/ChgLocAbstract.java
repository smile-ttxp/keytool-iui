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
 
 
 package com.google.code.p.keytooliui.shared.util.changer;

/**
    known subclasses:
    . ChgLocToolMainRdr
    . ChgLocToolMainGenDoc
    . ChgLocToolMainGenTpl
    . ChgLocMainUIAbs
    
    . ChgLocToolBmkReader
    . ChgLocToolSchReader
    . ChgLocToolThmReader
    
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

import com.google.code.p.keytooliui.shared.swing.frame.*;

import javax.swing.*;

import java.io.*;

abstract public class ChgLocAbstract extends UserChoice
{
    // -----------------------
    // FINAL STATIC PUBLIC INT   
    
    final static public int F_S_INT_TOP = SwingConstants.TOP; // !!!!!!!!!!!!!!!!!!!!!!!!!! REDUNDANT
    final static public int F_S_INT_LEFT = SwingConstants.LEFT;
    final static public int F_S_INT_BOTTOM = SwingConstants.BOTTOM;
    final static public int F_S_INT_RIGHT = SwingConstants.RIGHT;
   
    final static public int F_S_INT_DEFAULT = ChgLocAbstract.F_S_INT_TOP;
    
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean setTop();
    abstract public boolean setBottom();
    abstract public boolean setLeft();
    abstract public boolean setRight();
    abstract public boolean assignLast();
    abstract public boolean read(ObjectInputStream ois);
    abstract public boolean write(ObjectOutputStream oos);
    
    
    // ------
    // PUBLIC
    
    public void destroy() {}
    
    public boolean init()
    {
        final String f_strMethod = "init()";
        
        if (this._fmn_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._fmn_");
            return false;
        }
        
        return true;
    }
   
    
    
    
    
    // ---------
    // PROTECTED
    
    protected FMainAbs _fmn_ = null;
    
    /**
        TEMPO: get _strCurrent
        TO BE MODIFIED IN : UIManaget.get......LookAnfFeel
    **/
    protected boolean _write_(ObjectOutputStream oos, int intVal)
    {
        String strMethod = "_write_(oos, intVal)";
        
        if (oos == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil oos");
            return false;
        }
        
        if (intVal != F_S_INT_TOP &&
            intVal != F_S_INT_LEFT && 
            intVal != F_S_INT_BOTTOM &&
            intVal != F_S_INT_RIGHT
            )
        {
            MySystem.s_printOutError(this, strMethod, "wrong value, intVal=" + intVal);
            return false;
        }
        
        Integer itgVal = new Integer(intVal);
          
        try
        {        
            oos.writeObject(itgVal);
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return false;
        }
        
        return true;
    }
    
    /**
        if returning "-1", then there is a bug!
    **/
    
    protected int _read_(ObjectInputStream ois)
    {
        String strMethod = "read(ois)";
        
        Integer itgVal = null;
        
        try
        {  
            itgVal = (Integer) ois.readObject();
        }
        
        catch(ClassNotFoundException excClassNotFound)
        {
            excClassNotFound.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassNotFound caught");
            return -1;
        }
        
        catch(ClassCastException excClassCast)
        {
            excClassCast.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excClassCast caught");
            return -1;
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught");
            return -1;
        }
        
        if (itgVal == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil itgVal");
            return -1;
        }
        
        int intVal = itgVal.intValue();
        
        if (intVal != F_S_INT_TOP &&
            intVal != F_S_INT_LEFT && 
            intVal != F_S_INT_BOTTOM && 
            intVal != F_S_INT_RIGHT
            )
        {
            MySystem.s_printOutError(this, strMethod, "out of range, intVal=" + intVal);
            return -1;
        }
        
        return intVal;
    }
    
    
    
    protected ChgLocAbstract(FMainAbs fmn)
    {
        super();
        this._fmn_ = fmn;
    }
}