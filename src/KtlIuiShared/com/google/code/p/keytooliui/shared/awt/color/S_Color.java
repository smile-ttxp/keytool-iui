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
 
 
 package com.google.code.p.keytooliui.shared.awt.color;

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.Color;

final public class S_Color
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING 
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.awt.color.";
    
    // -------------
    // STATIC PUBLIC
    
    
    
	static public Color s_getFromRGB(String str)
	{
	    final String f_strMethod = _f_s_strClass + "s_getFromRGB(str)";
	
	    if (str == null)
	    {
		    MySystem.s_printOutError(f_strMethod, "nil str");
			return null;
		}
	    
		if (str.indexOf(58) == -1)
		{
		    MySystem.s_printOutError(f_strMethod, "wrong str: " + str);
			return null;
		}

		java.util.StringTokenizer stk = new java.util.StringTokenizer(str, ":");

		if (stk.countTokens() != 3)
		{
			MySystem.s_printOutError(f_strMethod, "wrong str: " + str);
			return null;
		}

		int i=0;
		int j=0;
		int k=0;

		try
		{
			i = Integer.parseInt(stk.nextToken());
			j = Integer.parseInt(stk.nextToken());
			k = Integer.parseInt(stk.nextToken());
		}

		catch(NumberFormatException excNumberFormat)
		{
		    excNumberFormat.printStackTrace();
			MySystem.s_printOutError(f_strMethod, "excNumberFormat caught, str: " + str);
			return null;
		}
			
		if (i<0 || i> 255 || j<0 || j>255 || k<0 || k>255)
		{
			MySystem.s_printOutError(f_strMethod, "outOfRange, str: " + str);
			return null;
		}
			
		return new Color(i, j, k);
	}
}