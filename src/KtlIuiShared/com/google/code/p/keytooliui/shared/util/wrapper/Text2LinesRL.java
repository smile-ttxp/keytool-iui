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
    "RL" means "Resource Location", eg: URL, RTP

input: a strText and a intWidth
output: a vecStrText, with a series of texts that matches 
        the intWidth (encapsulated)

**/


import com.google.code.p.keytooliui.shared.lang.*;

import java.util.*;



final public class Text2LinesRL extends Text2LinesAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private int _f_s_intLengthMax = 60;
    final static private String _f_s_strDelim = "/";
    
    // ------
    // PUBLIC
    
    
    public Text2LinesRL(String str)
	{
	    super(str);
	}

	public boolean init()
	{
	    String strMethod = "init()";
	    
	    if (super._str_ == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil super._str_"); // tempo
	        return false;
	    }
	    
	    if (super._str_.length() < Text2LinesRL._f_s_intLengthMax+1)
	    {
	        super._vecStrLine_.addElement(super._str_);
	        return true;
	    }

        StringTokenizer stk = new StringTokenizer(
            super._str_, 
            Text2LinesRL._f_s_strDelim,
            true // blnReturnDelims
            );        
        
        Vector<String> vec = new Vector<String>();
        
        while(stk.hasMoreElements())
        {
            String strCur = (String) stk.nextElement();
			vec.addElement(strCur);
		}
		
		// --
		int intLengthCur =0;
		String strCandidate = null;
		strCandidate = new String();
		
		for (int i=0; i<vec.size(); i++)
		{
		    String strCur = (String) vec.elementAt(i);
		    strCandidate += strCur;
		    
		    if (strCandidate.length() < Text2LinesRL._f_s_intLengthMax+1)
		    {
		        if (i < vec.size()-1) // not the last one
		            continue;
		    }
		    
		    // --
		    super._vecStrLine_.addElement(strCandidate);
		    strCandidate = new String();
		}
		
		// --
		return true;
	}
	
	
	
}

