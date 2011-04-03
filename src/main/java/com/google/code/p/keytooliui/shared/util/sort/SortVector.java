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
 
 
 //: SortVector.java
// A generic sorting vector
package com.google.code.p.keytooliui.shared.util.sort;

import java.util.*;

public class SortVector<Object> extends Vector<Object>
{
    // ------
    // PUBLIC
    
    public SortVector(Compare comp)
    {
        this._cmp = comp;
    }
    
    public void sort()
    {
        _quickSort(0, size() - 1);
    }
    
    // -------
    // PRIVATE
    
    private Compare _cmp = null; // To hold the callback
    
    
    
    private void _quickSort(int left, int right)
    {
        if(right > left)
        {
            Object o1 = elementAt(right);
            int i = left - 1;
            int j = right;
            
            while(true)
            {
                while(this._cmp.lessThan(elementAt(++i), o1));
                
                while(j > 0)
                    if(this._cmp.lessThanOrEqual(elementAt(--j), o1))
                        break; // out of while
                
                if(i >= j) break;
                _swap(i, j);
            }
        
            _swap(i , right);
            _quickSort(left, i-1);
            _quickSort(i+1, right);
        }
    }
    
    private void _swap(int loc1, int loc2)
    {
        Object tmp = elementAt(loc1);
        setElementAt((Object) elementAt(loc2), loc1);
        setElementAt(tmp, loc2);
    }
} 