/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.lang.bool;

/**
    for use in table, contains:
    . a black/red "checked" image
    . a booleanChecked y/n
    
    if (value==true)
    {
        returns:
        . a "checked" icon
        . a nil text
    }
    
    else
    {
        returns:
        . a nil icon
        . a "-" text
    }
    
    
    known subclasses:
    . BOCheckedCandidateBlack
    . BOCheckedCandidateRed
**/

import java.awt.Color;
import com.google.code.p.keytooliui.shared.swing.imageicon.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;


public abstract class BOCheckedCandidateAbs extends BOCheckedAbs
{
    // ------
    // PUBLIC
    
    
    public boolean init() { return true; }
    public void destroy() {}
    
    
    public void setChecked(boolean bln) { this._blnChecked = bln; }
    public boolean getChecked() { return this._blnChecked; }
    
    public Color getColorBG()
    {
        if (this._blnChecked)
            return Color.green;
        else
            return Color.white;
    }
    
    public Color getColorFG()
    {
        if (this._blnChecked)
            return Color.black; // unused
        else
            return Color.GRAY;
    }
    
    public String getText()
    {
        if (this._blnChecked)
            return null;
        else
            return "-";
    }
    
    public ImageIcon getIcon()
    {
        if (this._blnChecked)
            return this._iin;
        else
            return null;
    }

 
    
    // ---------
    // PROTECTED
    
    protected BOCheckedCandidateAbs(
        String strIcon,
        boolean blnChecked)
    {
        this(strIcon);
        this._blnChecked = blnChecked;
    }
    
    protected BOCheckedCandidateAbs(String strIcon)
    {
        String strMethod = "BOCheckedCandidateAbs(...)";
        
        this._iin = S_IINShared.s_get(strIcon);
	    
	if (this._iin == null)
	    MySystem.s_printOutExit(this, strMethod, "this._iin == null, strIcon=" + strIcon);
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnChecked = false;
    private ImageIcon _iin = null;
}
