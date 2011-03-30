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
 
 
package com.google.code.p.keytooliui.shared.swing.icon;

/**

    known subclasses:
    . IColoredSquare12
    . IColoredSquare16

**/

import java.awt.*;

import javax.swing.*;

abstract public class IColoredSquareAbs implements Icon
{    
    // ------
	// PUBLIC
    
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
	    Color colPrev = g.getColor();
	    g.setColor(this._col);
	    g.fill3DRect(x,y,getIconWidth(), getIconHeight(), true);
	    g.setColor(colPrev);
	}
	
	public int getIconWidth() { return this._intSize; }
	public int getIconHeight() { return this._intSize; }
	
	// ---------
	// PROTECTED
	
	
	
	protected IColoredSquareAbs(int intSize, Color col)
	{
	    this._intSize = intSize;
	    this._col = col;
    }
	
	// -------
	// PRIVATE
	
	private int _intSize = -1; // !!!!!!!
	private Color _col = null;
}