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
 
 
 package com.google.code.p.keytooliui.shared.swing.scrollpane;
 
 /**
 
    known subclasses:
    . SPPageTextSWAbs
    . SPPageTextIFRdrAbs
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.swing.editorpane.*;
 
 import javax.swing.*;
 
 import java.awt.*;
 
 abstract public class SPPageTextAbs extends JScrollPane
 {
    // ---------------
    // ABSTRACT PUBLIC
    
    
    // ------
    // PUBLIC
    
    public boolean scrollPageTextToReference(String strUrlRefNew)
    {
        String strMethod = "scrollPageTextToReference(strUrlRefNew)";
        
        if (strUrlRefNew == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strUrlRefNew");
            return false;
        }
        
        if (this._pep_ == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil this._pep_, returning true");
            return true;
        }
        
        this._pep_.scrollToReference(strUrlRefNew);
        return true;
    }
    
    
    
    public boolean setPageTextOccurrenceHighlighted(String strText, boolean blnMatchCase, boolean blnMatchWord, int intOccurrenceExpected)
    {
        String strMethod = "setPageTextOccurrenceHighlighted(strText, blnMatchCase, blnMatchWord, intOccurrenceExpected)";
  
        
        if (this._pep_ == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil this._pep_, returning true");
            return true;
        }
        
        return this._pep_.setPageTextOccurrenceHighlighted(strText, blnMatchCase, blnMatchWord, intOccurrenceExpected);
    }
    
    public boolean doPageUrlTextSelectAll()
    {
        String strMethod = "doPageUrlTextSelectAll()";
        
        if (this._pep_ == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil this._pep_, returning true");
            return true;
        }
    
        if (! this._pep_.doTextSelectAll())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean doPageUrlTextCopy()
    {
        String strMethod = "doPageUrlTextCopy()";
        
        if (this._pep_ == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil this._pep_, returning true");
            return true;
        }
    
        if (! this._pep_.doTextCopy())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public boolean setPageTextSelectionColor(Color col)
    {
        String strMethod = "setPageTextSelectionColor(col)";

        if (col == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil col");
            return false;
        }
        
        if (this._pep_ == null)
            return true;
            
        return this._pep_.setTextSelectionColor(col);
    }
    
    public void updateTreeUI()
    {   
        if (this._pep_ != null)
            this._pep_.updateTreeUI();
    }
    
    public boolean pagePrint()
    {
        if (this._pep_ == null)
            return true;
            
        return this._pep_.doPageUrlTextPrint();
    }
    
    public boolean findText()
    {
        if (this._pep_ == null)
            return true;
            
        return this._pep_.doTextSearch();
    }
    
    public boolean text2Speech()
    {
        if (this._pep_ == null)
            return true;
            
        return this._pep_.doText2Speech();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._pep_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pep_");
            return false;
        }
            
        

        JViewport vpt = getViewport();
        vpt.add(this._pep_);
            
        if (! this._pep_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }   
    
    public void destroy() 
    {
        if (this._pep_ != null)
        {
            this._pep_.destroy();
            this._pep_ = null;
        }
    }
    
    
    // ---------
    // PROTECTED
    
    protected EPTextAbs _pep_ = null;
    
    protected SPPageTextAbs()
    {
        super();
    }
    
 }