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
 
package com.google.code.p.keytooliui.shared.swing.text;

/**
       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       IMPORTANT:
       "Doc" does not mean RCR document, but Java Document
       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



    getting a list of IRange, these IRange are the text selection delimiters
    
    usage:
    
    JDocText2ListOccurr ohl = new JDocText2ListOccurr(doc, strText, blnMatchCase, blnMatchWord);
    
    if (! ohl.init())
    {
      // error
    }
    
    java.util.List lstRange = ohl.getLstSelection();
    
    // ----
    ohl.destroy();
    
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.text.*;

final public class JDocText2ListOccurr
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final private static String _f_s_strWordDelimiter = " =.,;:!?\"'(){}[]";
    
    
    
    // ------
    // PUBLIC 
    
    
    public java.util.List<IRange> getLstSelection()
    {
        return this._lstStringSelection;
    }
    
    
    public JDocText2ListOccurr(
        Document doc,
        String strText,
        boolean blnMatchCase,
        boolean blnMatchWord)
    {
        this._doc = doc;
        this._strText = strText;
        this._blnMatchCase = blnMatchCase;
        this._blnMatchWord = blnMatchWord;
        
        this._lstStringSelection = java.util.Collections.synchronizedList(new java.util.ArrayList<IRange>());
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._doc == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._doc");
            return false;
        }
        
        // ----
        ElementIterator itrElement = new ElementIterator(this._doc); 
        
        if (itrElement == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil itrElement");
            return false;
        }
        
        Element elmFirst = itrElement.first(); 
        
        if (elmFirst == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil elmFirst");
            return false;
        }
        
        Element elmCur = elmFirst;
        
        while (elmCur != null) 
        { 
            if (! elmCur.isLeaf()) 
            {
                elmCur = itrElement.next(); 
                continue;
            }
            
            Document docCur = elmCur.getDocument();     
            
            if (docCur == null)
            {
                elmCur = itrElement.next(); 
                continue;
            }
                            
            int intBeg  = elmCur.getStartOffset(); 
            int intEnd  = elmCur.getEndOffset(); 
            String strDocCur = null;        
                           
            try 
            { 
                strDocCur = docCur.getText(intBeg,intEnd-intBeg); 
                //System.out.println(">> strDocCur=" + strDocCur);
            }
                
            catch (BadLocationException excBadLocation)
            { 
                excBadLocation.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "excBadLocation caught");
                return false;
            }
            
            if (strDocCur == null)
            {
                elmCur = itrElement.next(); 
                continue;
            }
            
            if (strDocCur.trim().length() < 1)
            {
                elmCur = itrElement.next(); 
                continue;
            }
            
            if (strDocCur.trim().length() < this._strText.trim().length())
            {
                elmCur = itrElement.next(); 
                continue;
            }
            
            _findInLeaf(strDocCur, intBeg);
                    
            /****/
            elmCur = itrElement.next(); 
        
        } // end of while 
        
        // list has been filled (if any)
        // now: cur selection id!
        
        
        return true;
    }
    
    public void destroy()
    {
        this._doc = null;
        this._strText = null;
        
        if(this._lstStringSelection != null)
        {
            this._lstStringSelection.clear();
            this._lstStringSelection = null;
        }
    }
    
    // -------
    // PRIVATE
    
    // input
    private Document _doc = null;
    
    private String _strText = null;
    private boolean _blnMatchWord = false;
    private boolean _blnMatchCase = false;
    
    // output
    private java.util.List<IRange> _lstStringSelection = null;
    
    /**
        at this point, strDocCur is not nil.
        should check for all occurences of _strText
    **/
    
    private boolean _findInLeaf(String strDocCur, int intBeg)
    {
        String strMethod = "_findInLeaf(strDocCur, intBeg)";
        
        if (this._strText.length() > strDocCur.length())
            return true;
            
        String strDocCurClone = new String(strDocCur);
        String strTextClone = new String(this._strText);
        
        if (this._blnMatchCase == false)
        {
            strDocCurClone = strDocCurClone.toLowerCase();
            strTextClone = strTextClone.toLowerCase();
        }
        
        int intIndexFrom = 0;
        
        while (true)
        {
            if (intIndexFrom > strDocCurClone.length() - strTextClone.length())
                return true;
            
            int intIndexFound  = strDocCurClone.indexOf(strTextClone, intIndexFrom); 
            
            if (intIndexFound < 0)
            {
                intIndexFrom += strTextClone.length();
                continue;
            }
            
            // got it
            int findBeg = intIndexFound; 
            int findEnd = intIndexFound + strTextClone.length();
            
            
            // 
            if (this._blnMatchWord)
            {
                if (findBeg > 1)
                {
                    if (_f_s_strWordDelimiter.indexOf(strDocCurClone.charAt(findBeg-1)) == -1)
                    {
                        intIndexFrom += strTextClone.length();
                        continue;
                    }   
                }
                
                if (findEnd < strDocCurClone.length()-2)
                {
                    if (_f_s_strWordDelimiter.indexOf(strDocCurClone.charAt(findEnd)) == -1)
                    {
                        intIndexFrom += strTextClone.length();
                        continue;
                    } 
                }
            }
            
            
            // --- adding
            int intSelectionStart = intBeg + findBeg;
            int intSelectionEnd =  intBeg + findEnd;
                                
            IRange ssn = new IRange(intSelectionStart, intSelectionEnd);
            
            if (! ssn.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
                                
            this._lstStringSelection.add(ssn);
            
            intIndexFrom = intIndexFound + strTextClone.length();
        }
    }
}