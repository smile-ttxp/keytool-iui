/*
 *
 * Copyright (c)  2001-2011 RagingCat Project.
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
 
 
 package com.google.code.p.keytooliui.shared.util.sort;

//: StrSortVector.java
// Automatically sorted Vector that 
// accepts and produces only Strings

import com.google.code.p.keytooliui.shared.lang.*;

import java.util.*;

public class StrSortVector
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.util.sort.StrSortVector.";
    
    // -------------
    // PUBLIC STATIC
    
    /**
        if any error, returning nil
    **/
    public static String[] s_sort(String[] strsOri)
    {
        String f_strMethod = _f_s_strClass + "s_sort(strsOri)";
        
        if (strsOri == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil strsOri");
            return null;
        }
        
        StrSortVector fsv = new StrSortVector();
        
        for (int i=0; i<strsOri.length; i++)
            fsv.addElement(strsOri[i]);
            
        String[] strsNew = new String[strsOri.length];
        
        Enumeration e = fsv.elements();
        
        int i = 0;
        while(e.hasMoreElements())
            strsNew[i++] = (String) e.nextElement();
        
        // ending
        return strsNew;
    }
    
    // ------
    // PUBLIC
  
    public void addElement(String s)
    {
        this._svr.addElement(s);
        this._blnSorted = false;
    }
    
    public String elementAt(int index)
    {
        if(! this._blnSorted)
        {
            this._svr.sort();
            this._blnSorted = true;
        }
        
        return (String)this._svr.elementAt(index);
    }
    
    public Enumeration elements()
    {
        if(! this._blnSorted)
        {
            this._svr.sort();
            this._blnSorted = true;
        }
        
        return this._svr.elements();
    }
    
    // Test it:
    public static void main(String[] args)
    {
        StrSortVector sv = new StrSortVector();
        sv.addElement("d");
        sv.addElement("A");
        sv.addElement("C");
        sv.addElement("c");
        sv.addElement("b");
        sv.addElement("B");
        sv.addElement("D");
        sv.addElement("a");
        Enumeration e = sv.elements();
        
        while(e.hasMoreElements())
            System.out.println(e.nextElement());
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnSorted = false;
    
    private SortVector<Object> _svr = new SortVector<Object>(
        // Anonymous inner class:
        new Compare()
        {
            public boolean lessThan(Object l, Object r)
            {
                return 
                    ((String)l).toLowerCase().compareTo(
                    ((String)r).toLowerCase()) < 0;
            }
            
            public boolean lessThanOrEqual(Object l, Object r)
            {
                return 
                    ((String)l).toLowerCase().compareTo(
                    ((String)r).toLowerCase()) <= 0;
            }
        }
    );    
}  
