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
 
 
 package com.google.code.p.keytooliui.shared.swing.boundedrangemodel;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


final public class MRangeToValue extends DefaultBoundedRangeModel
{
    // ------------------------
    // FINAL PRIVATE STATIC INT
    
    final private static int _f_s_intExtent = 0;
    
    public int getDefault() { return this._intValueDefault; }
    
    // new stuff
    public void changeDefault(int intValue)
    {
        String f_strMethod = "changeDefault(intValue)";
        
        if (intValue<getMinimum() || intValue>getMaximum())
        {
            MySystem.s_printOutExit(this, f_strMethod, "out_of_range intValue=" + intValue);
            
        }
        
        
        this._intValueDefault = intValue;
    }
    
    // ------
    // PUBLIC
    
    public void setDefault()
    {
        this.setValue(this._intValueDefault);
    }
    
   
    
    public MRangeToValue(int value, int min, int max)
    {
        super(value, _f_s_intExtent, min, max);
        this._intValueDefault = value;
    }
    
    public boolean init()
    {
        return true;
    }
    
    public void destroy()
    {
    }
    
    // -------
    // PRIVATE
    
    private int _intValueDefault = 0; // dummy value!
    
}