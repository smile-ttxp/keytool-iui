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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a secondary window
    
    "B" for "Button"
    "Sw" for "Secondary Window"
    "Url" for "Uniform Resource Locator"
    "Text" means window displays a page of type "text", and contains a "text search" tool
    "Abs" for "Abstract class"


    Known subclasses:
    . B2SwUrlTextHTML
    . B2SwUrlTextRTF
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

abstract public class B2SwUrlTextAbs extends B2SwUrlAbs 
{    
    // ------
    // PUBLIC
    
    // ----------------
    // begin properties
    
    /**
     * Sets the text Color for the selected text in secondary window.
     * The following is a list of supported Color names
     * <ul>
     * <li>black
     * <li>blue
     * <li>cyan
     * <li>darkGray
     * <li>gray
     * <li>green
     * <li>lightGray
     * <li>magenta
     * <li>orange
     * <li>pink
     * <li>red
     * <li>white
     * <li>yellow
     * </ul>
     */
    public void setWindowTextSelectionColor(String strCol) 
    {
        String strMethod = "setWindowTextSelectionColor(strCol)";
        
        if (strCol == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil strCol");
            return;
        }
        
	    Color col = null;
	
	    if (strCol.toLowerCase().compareTo("black") == 0) 
	    {
	        col = Color.black;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("blue") == 0) 
	    {
	        col = Color.blue;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("cyan") == 0)
	    {
	        col = Color.cyan;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("darkGray") == 0) 
	    {
	        col = Color.darkGray;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("gray") == 0) 
	    {
	        col = Color.gray;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("green") == 0) 
	    {
	        col = Color.green;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("lightGray") == 0) 
	    {
	        col = Color.lightGray;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("magenta") == 0) 
	    {
	        col = Color.magenta;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("orange") == 0) 
	    {
	        col = Color.orange;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("pink") == 0) 
	    {
	        col = Color.pink;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("red") == 0) 
	    {
	        col = Color.red;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("white") == 0) 
	    {
	        col = Color.white;
	    } 
	    
	    else if (strCol.toLowerCase().compareTo("yellow") == 0) 
	    {
	        col = Color.yellow;
	    }

	    else 
	    {
	        MySystem.s_printOutWarning(this, strMethod, "uncaught strCol, strCol=" + strCol);
	        return;
	    }
	    
	    this._colWindowTextSelection_ = col;
    }

    /**
     * Returns the text Color of the activator text
     */
    public String getWindowTextSelectionColor() 
    {
	    if (this._colWindowTextSelection_ == null)
	        return null;
	    return this._colWindowTextSelection_.toString();
    }
    
    // --------------
    // end properties
    
    
    // ---------
    // PROTECTED
    
    protected Color _colWindowTextSelection_ = Color.blue;
    
    protected B2SwUrlTextAbs()
    {
        super();
    }
    
    // -------
    // PRIVATE
    
    
}