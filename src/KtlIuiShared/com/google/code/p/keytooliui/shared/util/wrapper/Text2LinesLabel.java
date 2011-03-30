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


 
package com.google.code.p.keytooliui.shared.util.wrapper;

/**
    modif october 16, 2002:
      fixed up bugID #18, REMOVED test in method getLines():
        if (_intCurrentW < 1) ==> error
**/

/**

input: a strText and a intWidth
output: a vecStrText, with a series of texts that matches 
        the intWidth (encapsulated)

**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.util.*;




final public class Text2LinesLabel extends Text2LinesAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strDelim = "\n";
    
    // ------
    // PUBLIC
    
    public int getMissingW()
    {        
	    if (this._intCurrentW < 0)
			return this._intCurrentW;
	    
	    return 0;
    }
    
    
    public int getDeltaW() { return this._intDeltaW; }
    
    public Text2LinesLabel(String str, int intCurrentW, java.awt.FontMetrics fms)
	{
	    super(str);
	    
		
		this._intCurrentW = intCurrentW;
		this._fms = fms;
		
		
	}

    // create a vector from width Return Carriage
	public boolean init()
	{
	    String strMethod = "init()";
	    
	    if (this._fms == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil this._fms");
            return false;     
	    }
	    
	    if (super._str_ == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil super._str_"); // tempo
	        return false;
	    }
	    
	    
		// checking for <br>
		
		StringTokenizer stk = new StringTokenizer(super._str_, Text2LinesLabel._f_s_strDelim);

        while(stk.hasMoreElements())
        {
            
			super._vecStrLine_.addElement((String) stk.nextElement());
		}
		
		return true;
	}
	
	
	/**
	    if all lines contained in the W, ok, just compute the max W
	    
	    else
	    ...
	    
	**/
	public Vector<String> getLines()
	{
	    String strMethod = "getLines()";

		// -------
		// case 1)
		
	    int intLengthMax = _getLengthMax(super._vecStrLine_);
	    int intDeltaW = intLengthMax - this._intCurrentW;
	    
	    if (intDeltaW < 1)
	    {
	        this._intDeltaW = intDeltaW;
	        return super.getLines();
	    }
	    
	    // --- case 2) need to wrap
	    
	    Vector<String> vecLineWrapped = _getVectorLineWrapped(); 
	    
	    intLengthMax = _getLengthMax(vecLineWrapped);
	    this._intDeltaW = intLengthMax - this._intCurrentW;
	    return vecLineWrapped;
	}

	
    
    // -------
	// PRIVATE
	
	private int _intCurrentW = -1;
	private java.awt.FontMetrics _fms = null;
	private int _intDeltaW = 0;
	
	private int _getLengthMax(Vector<String> vecString)
	{
	    int intLengthMax = -1;
		
		for (int i=0; i<vecString.size(); i++)
		{
			String strLineCur = (String) vecString.elementAt(i);
	        int intLineCurW = this._fms.stringWidth(strLineCur);
	        
	        if (intLengthMax < intLineCurW)
	            intLengthMax = intLineCurW;   
	        
	    }
	    
	    return intLengthMax;
	}
	
	/**
	    january 26, 2000
	    
	    BUG: one big string larger than the availabled cell's width
	    ==> 3 lines used
	    . the 2 first are empty,
	    . the third one displays the string
	    
	    
	    BUG FIXED! see below
	
	**/
	
	private Vector<String> _getVectorLineWrapped()
	{
	    Vector<String> vecLineWrapped = new Vector<String>();
	    
	    for (int i=0; i<super._vecStrLine_.size(); i++)
		{
			StringBuffer sbr = new StringBuffer();
			String str1 = (String) super._vecStrLine_.elementAt(i);
			StringTokenizer tok2 = new StringTokenizer(str1);
			
			while (tok2.hasMoreTokens())
			{
				String strCurr = tok2.nextToken();

				String strLine = sbr.toString();
				
				if (strLine.trim().length() < 1) // added, see bug jan 26, 2000
				{
				    sbr.append(strCurr + " ");
			    }
				
				// -------
				else
				{
				    int intSumW = this._fms.stringWidth(strLine + " " + strCurr);
		
				    if (intSumW > this._intCurrentW)
				    {
					    vecLineWrapped.addElement(strLine.trim());
					    sbr = new StringBuffer(strCurr + " ");
				    }

				    else
					    sbr.append(strCurr + " ");
                }
			}

			// last one
			vecLineWrapped.addElement(sbr.toString().trim());

		}
		
		return vecLineWrapped;
	}
}

