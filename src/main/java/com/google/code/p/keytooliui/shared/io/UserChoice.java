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
    
    . shared: UCIntegerAbstract
    . shared: ChgLocAbstract
    
    . rcr: UCManagProjRdr
    . rcr: UCBooleanAbstract
    . rcr: UCIntegerCacheNoteRdr // memo: doesn't contain Frame
    . rcr: UCHistProjRdr
    . rcr: UCColSelTxtCntRdr
     

    . shared_gen: UCManagProjGenAbs
    . shared_gen: UCHistProjGenAbs

**/


import java.io.*;

public abstract class UserChoice extends Object
{
    // ---------------
    // ABSTRACT PUBLIC
    
    public abstract boolean assignLast();
    public abstract boolean read(ObjectInputStream ois);
    public abstract boolean write(ObjectOutputStream oos);
    
    public abstract boolean init();
    public abstract void destroy();
    
    // ---------
    // PROTECTED
    
    protected UserChoice()
    {
    }
}