/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.textfield;


import com.google.code.p.keytooliui.shared.swing.text.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*; 
import javax.swing.text.*; 

import java.text.*;

public final class TFDecimal extends TFAbstract
{
    // --------------------
    // PRIVATE STATIC FINAL
    private static final String _f_s_strToolTipText = "text field, displays decimal value";
    private static final int _f_s_intH = 20;
    private static final int _f_s_intColumns = 40; // 30; //4;
    
    // ------
    // PUBLIC
    
    public void destroy()
    {
        super.destroy();
        
        if (this._nft != null)
            this._nft = null;
    }
    
    /**
        CALL NOT ALLOWED
    **/
    public void setDefault()
    {
        String f_strMethod = "setDefault()";
        MySystem.s_printOutExit(this, f_strMethod, "dev error, call not allowed");   
    }
    
    /**
        CALL NOT ALLOWED
    **/
    public boolean isDefault()
    {
        String f_strMethod = "isDefault()";
        MySystem.s_printOutExit(this, f_strMethod, "dev error, call not allowed");
        
        return false; // statement not reached !!!!!!!
    }
    
    public TFDecimal(double dblValue)
    {
        super(_f_s_strToolTipText, _f_s_intColumns, _f_s_intH);

        PlainDocumentFilter pdf = new PlainDocumentFilter(PlainDocumentFilter.f_s_strNumeric);
        setDocument(pdf);

        this._nft = NumberFormat.getNumberInstance();
        setValue(dblValue);

        setEditable(true);
    }
    
    public double getValue()
    {
        String f_strMethod = "getValue()";
        
        double retVal = 0.0;

        try
        {
            retVal = this._nft.parse(getText()).doubleValue();
        }
        
        catch (ParseException excParse)
        {
            // This should never happen because insertString allows
            // only properly formatted data to get in the field.
            excParse.printStackTrace();
            MySystem.s_printOutExit(this, f_strMethod, "excParse caught"); 
        }
        
        return retVal;
    }
    


    public void setValue(double dbl)
    {
        String strMethod = "setValue(dbl)";
        
        /** BUG: if value higher than 999
        
        EG:
        . dbl=1282
        . _fnt.format(1282)=1�282
        . getText()= VOID!!!!!!!!!!!!!
        
        MySystem.s_printOutTrace(this, strMethod, "dbl=" + dbl);
        
        System.out.println("this._nft.format(dbl)=" + this._nft.format(dbl));
        
	    setText(this._nft.format(dbl));
	    System.out.println("getText()=" +getText());
        **/
        
        
        // OK
        int intVal = 0;
        
        try
        {
            intVal = (int) dbl;
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "exc caught");
        }
        
        setText(Integer.toString(intVal));
    }
    
    // -------
    // PRIVATE

    private NumberFormat _nft; // should be removed?
    
}