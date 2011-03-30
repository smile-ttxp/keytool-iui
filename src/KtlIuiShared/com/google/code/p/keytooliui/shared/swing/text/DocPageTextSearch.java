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
 
 
package com.google.code.p.keytooliui.shared.swing.text;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.text.*;

import java.awt.*;

final public class DocPageTextSearch
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strWordDelimiter = " =.,;:!?\"'(){}[]";
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strWarningEmptyListBody = null;
    static private String _s_strWarningTopReachedBody = null;
    static private String _s_strWarningBottomReachedBody = null;
    
    
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.text.DocPageTextSearch";
     
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DocPageTextSearch" // class name
            ;
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        _s_strWarningEmptyListBody = rbeResources.getString("warningEmptyListBody"); 
	        _s_strWarningTopReachedBody = rbeResources.getString("warningTopReachedBody"); 
	        _s_strWarningBottomReachedBody = rbeResources.getString("warningBottomReachedBody"); 
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
    } 
    
    // ------
    // PUBLIC
    
    /**
        memo: ssnCur may be nil!
    **/
    public MyStringSelection getNext(boolean blnDirectionDown, MyStringSelection ssnCur)
    {
        String strMethod = "getNext(blnDirectionDown, ssnCur)";
        
        if (this._lstStringSelection == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._lstStringSelection");
        
        if (this._lstStringSelection.isEmpty())
        {
            MySystem.s_printOutTrace(this, strMethod, "empty list");
            com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
	        OPAbstract.s_showDialogWarning(this._cmpFrameOwner, this._strTitleApplication, _s_strWarningEmptyListBody + "\"" + this._strText + "\"");
            
            return null;
        }
        
        // --------------
        // list not empty
        
        if (ssnCur == null)
        {
            MyStringSelection ssnNext = null;
            
            if (blnDirectionDown)
            {
                ssnNext = (MyStringSelection) this._lstStringSelection.get(0);
            }
            
            else
            {
                ssnNext = (MyStringSelection) this._lstStringSelection.get(this._lstStringSelection.size()-1);
            }
            
            return ssnNext;
        }
        
        // --------------
        // ssnCur != null
        
        if (blnDirectionDown)
        {
            int intEndCur = ssnCur.getEnd();
            
            for (int i=0; i<this._lstStringSelection.size(); i++)
            {
                MyStringSelection ssn = (MyStringSelection) this._lstStringSelection.get(i);
                
                int intStart = ssn.getStart();
                
                if (intStart <= intEndCur)
                    continue;
                    
                // got it
                return ssn;
            }
            
            MySystem.s_printOutTrace(this, strMethod, "end of document reached");
            com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
	        OPAbstract.s_showDialogWarning(this._cmpFrameOwner, this._strTitleApplication, _s_strWarningBottomReachedBody);
            
            // modif feb 4, 2002, returning last in the least
            //return null;
            return (MyStringSelection) this._lstStringSelection.get(this._lstStringSelection.size()-1);
            // --
        }
        
        // direction: from down to up
        
        int intStartCur = ssnCur.getStart();
            
        for (int i=this._lstStringSelection.size()-1; i>-1; i--)
        {
            MyStringSelection ssn = (MyStringSelection) this._lstStringSelection.get(i);    
            int intEnd = ssn.getEnd();
                
            if (intEnd > intStartCur)
                continue;
                    
            // got it
            return ssn;
        }
            
        MySystem.s_printOutTrace(this, strMethod, "begin of document reached");
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
	    OPAbstract.s_showDialogWarning(this._cmpFrameOwner, this._strTitleApplication, _s_strWarningTopReachedBody);
        // modif feb 4, 2002, returning first in the list
        //return null;
        return (MyStringSelection) this._lstStringSelection.get(0);
    }
    
    /**
        algo should be rather more complex that the following!!!
    **/
    public boolean sameConfig(String strText, boolean blnMatchWord, boolean blnMatchCase)
    {
        String strMethod = "sameConfig(...)";
        
        if (strText == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strText");
            
        }
        
        if (strText.compareTo(this._strText) != 0)
        {
            return false;
        }
        
        if (blnMatchWord != this._blnMatchWord)
            return false;
            
        if (blnMatchCase != this._blnMatchCase)
            return false;
            
        return true;
    }
    
    public DocPageTextSearch(Component cmpFrameOwner, String strTitleApplication, Document doc, String strText, boolean blnMatchWord, boolean blnMatchCase)
    {
        this._cmpFrameOwner = cmpFrameOwner;
        this._strTitleApplication = strTitleApplication;
        this._doc = doc;
        this._strText = strText;
        this._blnMatchWord = blnMatchWord;
        this._blnMatchCase = blnMatchCase;
        
        this._lstStringSelection = java.util.Collections.synchronizedList(new java.util.ArrayList<MyStringSelection>());
    }
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._doc==null || this._strText==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }
        
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
            
            if (! _findInLeaf(strDocCur, intBeg))
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
                    
            /****/
            
            elmCur = itrElement.next(); 
        
        } // end of while 
        
        // list has been filled (if any)
        // now: cur selection id!
       
        return true;
    }
    
    public void destroy()
    {
        if (this._lstStringSelection != null)
            this._lstStringSelection = null;
    }

    
    // -------
    // PRIVATE
    
    private Component _cmpFrameOwner = null;
    private String _strTitleApplication = null;
    
    private Document _doc = null;
    private String _strText = null;
    private boolean _blnMatchWord = false;
    private boolean _blnMatchCase = false;
    
    private java.util.List<MyStringSelection> _lstStringSelection = null;
    
    
    // checking MatchCase & (pending) WholeWord
    //private boolean _fits(String strTextCur)
    //{
        /*if (this._blnMatchWord)
        {
            if (this._strText.compareTo(strTextCur) != 0)
                return false;
        }*/
        
      //  return true;
    //}
    
    /**
        at this point, strDocCur is not nil.
        should check for all occurences of _strText
    **/
    
    private boolean _findInLeaf(String strDocCur, int intBeg)
    {
        String strMethod = "_findInLeaf(strDocCur, intBeg)";
        
        if (this._strText == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strText");
            return false;
        }
        
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
                                
            MyStringSelection ssn = new MyStringSelection(intSelectionStart, intSelectionEnd);
            
            if (! ssn.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
                                
            this._lstStringSelection.add(ssn);
            
            intIndexFrom = intIndexFound + strTextClone.length();
        }
    
        // ending
        //return true;
    }
}