/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.editorpane;

/**

    known subclasses:
    . S_IINShared (shared)
    . S_IINSharedGen (shared_gen)
    . S_IINReader (rcr)
    

this.getClass().getClassLoader(), 
**/

import com.google.code.p.keytooliui.shared.lang.*;


import javax.swing.JEditorPane;

abstract public class S_EPEAbs
{
    
    
    
    
    
    // --------------------------
    // PUBLIC STATIC FINAL STRING
    
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.swing.editorpane.S_EPEAbs.";   
    
    // ----------------
    // PROTECTED STATIC

    /*
        1) development
        2) jarred, development or release
        3) JavaWebStart's cache resources
    */
    protected static javax.swing.JEditorPane _s_get_(
        ClassLoader cldSubClass,
        String strFileName,
        String strResourcePathRelativeSystem,
        String strResourcePathRelativeJar)
    {
        final String f_strWhere = _f_s_strClass + "_s_get_(strFileName, strResourcePathRelativeSystem, strResourcePathRelativeJar)";
        
        if (cldSubClass==null || strFileName==null || strResourcePathRelativeSystem==null || strResourcePathRelativeJar==null)
        {
            MySystem.s_printOutError(f_strWhere, "nil arg");
            return null;
        }
        
        // 1) not jarred & development
        java.io.File fle = new java.io.File(strResourcePathRelativeSystem + strFileName);
        
        if (! fle.exists())
        {       
            //2) jarred, development or release
            java.net.URL urlHtml = ClassLoader.getSystemResource(strResourcePathRelativeJar + strFileName);
            
            if (urlHtml == null)
            {
                // BEGIN MODIF, JAVA WEB START HANDLING, april, 26, 2001
                
                /** 
                ---- BEGIN ORI
                MySystem.s_printOutError(f_strWhere, "missing resource: " + strResourcePathRelativeJar + strFileName);
                return null;
                ---- END ORI
                **/
               
                
                //urlHtml = Thread.currentThread().getContextClassLoader().getResource(strResourcePathRelativeJar + strFileName);
                //3) JavaWebStart's cache resources
                urlHtml = cldSubClass.getResource(strResourcePathRelativeJar + strFileName);
                
                if (urlHtml == null)
                {
                    MySystem.s_printOutError(f_strWhere, "nil urlHtml: " + strResourcePathRelativeJar + strFileName);
                    return null;
                }
                
                // END MODIF, JAVA WEB START HANDLING
            }
            
            try
            {
                return (new JEditorPane(urlHtml));
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
                MySystem.s_printOutError(f_strWhere, exc.getMessage());
                return null;
            }
        }
        
        fle = null;
        // !!!!!!!!!!!!!!!!!!!!!!
        try
        {
            return (new JEditorPane(strResourcePathRelativeSystem + strFileName));
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(f_strWhere, exc.getMessage());
            return null;
        }
    }
}
