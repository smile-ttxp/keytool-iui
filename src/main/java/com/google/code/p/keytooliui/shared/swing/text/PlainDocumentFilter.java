/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.text;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

final public class PlainDocumentFilter extends PlainDocument
{
   // -------------------
   // FINAL STATIC PUBLIC
               
        
   final static public String f_s_strLowerCase = "abcdefghijklmnopqrstuvwxyz";
   final static public String f_s_strUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   final static public String f_s_strAlpha = f_s_strLowerCase + f_s_strUpperCase;
   final static public String f_s_strNumeric = "0123456789";
   final static public String f_s_strFloat = f_s_strNumeric + ".";
   final static public String f_s_strAlphaNumeric = f_s_strAlpha + f_s_strNumeric;
    
   final static public String f_s_strFilePrefix =  f_s_strAlphaNumeric + "_";
   
   // ------
   // PUBLIC
        
    public PlainDocumentFilter()
    {
        this(f_s_strAlphaNumeric);
    }
   
    public PlainDocumentFilter(String strAcceptedchars)
    {
        this._strCharsAccepted = strAcceptedchars;
    }
  
    public void setNegativeAccepted(boolean blnNegativeAccepted)
    {
        if (this._strCharsAccepted.equals(f_s_strNumeric) ||
            this._strCharsAccepted.equals(f_s_strFloat) ||
            this._strCharsAccepted.equals(f_s_strAlphaNumeric))
        {
            this._blnNegativeAccepted = blnNegativeAccepted;
            this._strCharsAccepted += "-";
        }
    }

    public void insertString(int intOffset, String  str, AttributeSet ast)
         throws BadLocationException
    {
        String strMethod = "insertString(...)";
        
        if (str == null)
        {
            //MySystem.s_printOutWarning(this, strMethod, "nil str");
            Toolkit.getDefaultToolkit().beep();
            return;
        }

        if (this._strCharsAccepted.equals(f_s_strUpperCase))
            str = str.toUpperCase();
        
        else if (this._strCharsAccepted.equals(f_s_strLowerCase))
            str = str.toLowerCase();

        for (int i=0; i < str.length(); i++)
        {
            if (this._strCharsAccepted.indexOf(str.valueOf(str.charAt(i))) == -1)
            {
                //MySystem.s_printOutWarning(this, strMethod, "this._strCharsAccepted");
                Toolkit.getDefaultToolkit().beep();
                return;
            }
        }

        if (this._strCharsAccepted.equals(f_s_strFloat) || 
            (this._strCharsAccepted.equals(f_s_strFloat + "-") && this._blnNegativeAccepted))
        {
            if (str.indexOf(".") != -1)
            {
                if (getText(0, getLength()).indexOf(".") != -1)
                {
                    //MySystem.s_printOutWarning(this, strMethod, "indexOf . != -1");
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
            }
        }

        if (this._blnNegativeAccepted && str.indexOf("-") != -1)
        {
            if (str.indexOf("-") != 0 || intOffset != 0 )
            {
                //MySystem.s_printOutWarning(this, strMethod, "indexOf - != -0");
                Toolkit.getDefaultToolkit().beep();
                return;
            }
        }

        //MySystem.s_printOutTrace(this, strMethod, "inserted");
        super.insertString(intOffset, str, ast);
    }
    
    
    // -------
    // PRIVATE
        
    private String _strCharsAccepted = null;
    private boolean _blnNegativeAccepted = false;
}
