/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a [secondary window/Web browser/Web Mailer]
    
    "B" for "Button"
    "2" for "To [secondary-window/Web-browser]"
    "Abs" for "Abstract class"


    Known subclasses:
    . B2SwAbs
    . B2WebAbs
**/


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.border.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

abstract public class B2Abs extends JButton implements 
    Serializable,
    ActionListener
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private Cursor _f_s_crsHand =
	    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	    
	// ---------------
    // ABSTRACT PUBLIC
    
    abstract public void actionPerformed(ActionEvent e);
    
    // ------
    // PUBLIC
    
    // ----------------
    // begin properties
    
    /*
        added july 12, 2002 ==> else failing to get text property value!
    */
    public void setText(String str)
    {
        super.setText(str);
    }
    
    public String getText()
    {
        return super.getText();
    }
    
    
    public void setTextTip(String str)
    {
        setToolTipText(str);
    }
    
    public String getTextTip()
    {
        return getToolTipText();
    }
    
    
    
    /**
     * Sets the text Font family for the activator text.
     * For JDK 1.1 this must a family name of Dialog, DialogInput, Monospaced, 
     * Serif, SansSerif, or Symbol.
     */
     
    /*
        Logical name:
         Dialog, DialogInput, Monospaced, Serif, or SansSerif
    */
    public void setTextFontFamily(String str) 
    {
        String strMethod = "setTextFontFamily(str)";
        
        
        if (str == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil str");
            return;
        }
        
        if (str.toLowerCase().compareTo("dialog") == 0) 
	    {
	        this._strTextFontFamily = "Dialog";
	    }
	    
	    else if (str.toLowerCase().compareTo("dialoginput") == 0) 
	    {
	        this._strTextFontFamily = "DialogInput";
	    }
	    
	    else if (str.toLowerCase().compareTo("monospaced") == 0) 
	    {
	        this._strTextFontFamily = "Monospaced";
	    }
	    
	    else if (str.toLowerCase().compareTo("serif") == 0) 
	    {
	        this._strTextFontFamily = "Serif";
	    }
	    
	    else if (str.toLowerCase().compareTo("sansserif") == 0) 
	    {
	        this._strTextFontFamily = "SansSerif";
	    }
	    
	    else 
	    {
	        MySystem.s_printOutWarning(this, strMethod, "uncaught str, str=" + str);
	        return;
	    }
	    
	    Font fnt = new Font(this._strTextFontFamily, getFont().getStyle(), getFont().getSize());
	    setFont(fnt);
    }

    /**
     * Returns the text Font family name of the activator text
     */
    public String getTextFontFamily() 
    {
	    return this._strTextFontFamily;
    }
    
    
    /*
        plain, bold, italic, or bolditalic
    */
    public void setTextFontStyle(String str) 
    {
        String strMethod = "setTextFontStyle(str)";
        
        int intStyle = -1; // !!!!
        
        if (str == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil str");
            return;
        }
        
        if (str.toLowerCase().compareTo("plain") == 0) 
	    {
	        intStyle = Font.PLAIN;
	    }
	    
	    else if (str.toLowerCase().compareTo("bold") == 0) 
	    {
	        intStyle = Font.BOLD;
	    }
	    
	    else if (str.toLowerCase().compareTo("italic") == 0) 
	    {
	        intStyle = Font.ITALIC;
	    }
	    
	    
	    else if (str.toLowerCase().compareTo("bolditalic") == 0) 
	    {
	        intStyle = Font.BOLD | Font.ITALIC;
	    }
	    
	    else 
	    {
	        MySystem.s_printOutWarning(this, strMethod, "uncaught str, str=" + str);
	        return;
	    }
	    
	    Font fnt = new Font(this._strTextFontFamily, intStyle, getFont().getSize());
	    setFont(fnt);
    }

    /**
     * Returns the text Font family name of the activator text
     */
    public String getTextFontStyle() 
    {
	    return Integer.toString(getFont().getStyle());
    }
    
    /*
        integer, range=8-72
    */
    public void setTextFontSize(String str) 
    {
        String strMethod = "setTextFontSize(str)";
        
        
        
        if (str == null)
        {
            MySystem.s_printOutWarning(this, strMethod, "nil str");
            return;
        }
        
        int intSize = -1; // !!!!
        
        try
        {
            intSize = Integer.parseInt(str);
        }
        
        catch(NumberFormatException excNumberFormat)
        {
            MySystem.s_printOutWarning(this, strMethod, "excNumberFormat caught, str=" + str);
            return;
        }
        
        if (intSize<8 || intSize>72)
        {
            MySystem.s_printOutWarning(this, strMethod, "intSize out of range, intSize=" + intSize);
            return;
        }
	    
	    Font fnt = new Font(this._strTextFontFamily, getFont().getStyle(), intSize);
	    setFont(fnt);
    }

    /**
     * Returns the text Font family name of the activator text
     */
    public String getTextFontSize() 
    {
	    return Integer.toString(getFont().getSize());
    }
    
    
    
    
    
    /**
     * Sets the text Color for the activator text.
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
    public void setTextColor(String strCol) 
    {
        String strMethod = "setTextColor(strCol)";
        
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

	    setForeground(col);
    }

    /**
     * Returns the text Color of the activator text
     */
    public String getTextColor() 
    {
	    Color col = getForeground();
	    return col.toString();
    }
    
    // --------------
    // end properties
	    
    // ---------
    // PROTECTED
    
    protected B2Abs()
    {
        super();
        
        _assignText();	
        _addListeners();
    }
    
    // -------
    // PRIVATE
    
    // ----------
    // properties
    
    private String _strTextFontFamily = "Dialog"; // default
    
    // ---
    
    private void _assignText()
    {
        setText(">"); // default
	    setMargin(new Insets(0,0,0,0));
        setBorder(new EmptyBorder(1,1,1,1));
	    setBorderPainted(false);
	    setFocusPainted(false);
	    setContentAreaFilled(false);
	    
	    invalidate();
    }
    
    private void _addListeners()
    {
        addActionListener(this);
        	    
	    final Cursor f_crsOri = getCursor();
	    
	    addMouseListener(new MouseAdapter() 
	    {
	        public void mouseEntered(MouseEvent e) 
	        {
		        setCursor(_f_s_crsHand);
	        }

	        public void mouseExited(MouseEvent e) 
	        {
		        setCursor(f_crsOri);
	        }
	    });
    }
}