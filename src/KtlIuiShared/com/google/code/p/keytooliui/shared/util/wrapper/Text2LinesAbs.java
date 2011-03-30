/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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


 
package com.google.code.p.keytooliui.shared.util.wrapper;

/**
    known subclasses:
    . Text2LinesRL
    . Text2LinesLabel
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.util.*;


abstract public class Text2LinesAbs
{
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    
    // ------
    // PUBLIC
    
    public Vector<String> getLines()
    {
        String strMethod = "getLines()";
        
        if (this._vecStrLine_ == null)
            return null;
        
        // memo: can't clone in JDK 1.5 coz -Xlint:unchecked compil option send a warning
        
        Vector<String> vecClone = new Vector<String>();
        
        for (int i=0; i<this._vecStrLine_.size(); i++)
            vecClone.addElement(this._vecStrLine_.elementAt(i));
        
        
        return vecClone;
    }
    
    public void destroy()
    {
        if (this._vecStrLine_ != null)
        {
            this._vecStrLine_.clear();
            this._vecStrLine_ = null;
        }
    }
    
    // ---------
    // PROTECTED
    
    protected Vector<String> _vecStrLine_ = null;
    protected String _str_ = null;
    
    protected Text2LinesAbs(
        String str)
    {
        this._str_ = str;
		this._vecStrLine_ = new Vector<String>();
    }
}