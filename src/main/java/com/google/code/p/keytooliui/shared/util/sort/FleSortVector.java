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
 
 
 // FleSortVector.java
// Automatically sorted Vector that 
// accepts and produces only Strings
package com.google.code.p.keytooliui.shared.util.sort;

/**
usage:
FleSortVector fsv = new FleSortVector();
fsv.addElement(fle1);
fsv.addElement(fle2);

// ---
Enumeration e = fsv.elements();
while(e.hasMoreElements())
 System.out.println(e.nextElement());

**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.util.*;
import java.io.File;

public class FleSortVector
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.util.sort.FleSortVector.";
    
    // -------------
    // PUBLIC STATIC
    
    /**
        if any error, returning nil
    **/
    public static File[] s_sort(File[] flesOri)
    {
        String f_strMethod = _f_s_strClass + "s_sort(flesOri)";
        
        if (flesOri == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil flesOri");
            return null;
        }
        
        FleSortVector fsv = new FleSortVector();
        
        for (int i=0; i<flesOri.length; i++)
            fsv.addElement(flesOri[i]);
            
        File[] flesNew = new File[flesOri.length];
        
        Enumeration e = fsv.elements();
        
        int i = 0;
        while(e.hasMoreElements())
            flesNew[i++] = (File) e.nextElement();
        
        // ending
        return flesNew;
    }
    
    // ------
    // PUBLIC
  
    public void addElement(File fle)
    {
        this._svr.addElement(fle);
        this._blnSorted = false;
    }
    
    public File elementAt(int index)
    {
        if(! this._blnSorted)
        {
            this._svr.sort();
            this._blnSorted = true;
        }
        
        return (File)this._svr.elementAt(index);
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
    
  
    
    // -------
    // PRIVATE
    
    private boolean _blnSorted = false;
    
    private SortVector<File> _svr = new SortVector<File>(
        // Anonymous inner class:
        new Compare()
        {
            public boolean lessThan(Object l, Object r)
            {
                File fleL = (File) l;
                File fleR = (File) r;
                return 
                    ((String)fleL.getName()).toLowerCase().compareTo(
                    ((String)fleR.getName()).toLowerCase()) < 0;
            }
            
            public boolean lessThanOrEqual(Object l, Object r)
            {
                File fleL = (File) l;
                File fleR = (File) r;
                return 
                    ((String)fleL.getName()).toLowerCase().compareTo(
                    ((String)fleR.getName()).toLowerCase()) <= 0;
            }
        }
    );    
}  
