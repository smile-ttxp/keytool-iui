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
 
 
 package com.google.code.p.keytooliui.shared.swing.text.html.stylesheet;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.swing.text.html.*;

import java.io.*;

final public class S_StyleSheetLoaderJar extends S_StyleSheetLoaderAbstract
{
    // --------------------
    // FINAL STATIC PRIVATE
    
      
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.swing.text.html.stylesheet.S_StyleSheetLoaderJar.";
    
    
    
    // -------------
    // STATIC PUBLIC
    
    /**
        memo: fjr either from docJar or from templateJar inside docJar
        if any error, exiting
        
        load StyleSheet
    **/
    
    static public StyleSheet s_load(String strTitleApplication, java.awt.Component cmpFrameOwner, FileJar fjr, String strPathRelative)
    {
        String f_strMethod = _f_s_strClass + "s_load(strTitleApplication, cmpFrameOwner, fjr, strPathRelative)";
        
        
        if (strTitleApplication==null || cmpFrameOwner==null || fjr==null || strPathRelative==null)
        {
            MySystem.s_printOutExit(f_strMethod, "nil arg");
            
        }
        
        
        // ---------
        // get bytes
        
        byte[] byts = fjr.getResource(strPathRelative);
        
        if (byts == null)
        {
            MySystem.s_printOutWarning(f_strMethod, "nil byts");
            
            return null;
        }
        
        // ---
        
        ByteArrayInputStream bai = new ByteArrayInputStream(byts);
            
        Reader rdr = new InputStreamReader(bai); 
        
        // ----
        StyleSheet sst = new StyleSheet(); 
        
        try
        {
            sst.loadRules(rdr, null); 
            
            //MySystem.s_printOutTrace(f_strMethod, "bai.close()"); 
            //bai.close();
        }
            
        catch(IOException excIO)
        {
            excIO.printStackTrace(); 
            MySystem.s_printOutError(f_strMethod, "excIO caught"); 
            return null;
        }
        
        return sst;
    }

}
