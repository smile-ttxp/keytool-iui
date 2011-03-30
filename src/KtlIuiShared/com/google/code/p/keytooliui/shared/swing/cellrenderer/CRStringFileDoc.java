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
 
 
package com.google.code.p.keytooliui.shared.swing.cellrenderer;

/**

unsorted list of files of type RCR

**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.util.*;

final public class CRStringFileDoc extends CRStringAbstract
{
    // --------------------
    // final static private
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.swing.cellrenderer.CRStringFileDoc.";
    
    final static private ImageIcon _f_s_iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
        com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileProjRcr16);

    
    // -------------
    // static public
    
    /**
        from a vector of String, constructs then returns an array of CRStringFileDoc
    **/
    static public CRStringAbstract[] s_get(Vector vecString)
    {
        String strWhere = _f_s_strClass + "s_get(vecString)";
        
        if (vecString == null)
        {
            MySystem.s_printOutError(strWhere, "nil vecString");
            return null;
        }
        
        // ---
        // ending
        
        CRStringAbstract[] frrs = new CRStringFileDoc[vecString.size()];
		
		for (int i=0; i<vecString.size(); i++)
		{
		    String strCur = (String) vecString.elementAt(i);
		    CRStringAbstract srr = new CRStringFileDoc(strCur);
		    srr.init();
		    frrs[i] = srr;
		}
		
		return frrs;
    }
    
    
    
    // ------
    // public
    
    public CRStringFileDoc(String str)
    {
        super(str);
        
        String strMethod = "strMethod(str)";
        
        if (_f_s_iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil _f_s_iin");
        
        super._iin_ = _f_s_iin;
    }
    

}