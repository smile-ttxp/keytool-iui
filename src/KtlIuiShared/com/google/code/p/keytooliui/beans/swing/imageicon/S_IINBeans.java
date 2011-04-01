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
 
 
package com.google.code.p.keytooliui.beans.swing.imageicon;




import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.imageicon.*;

public class S_IINBeans extends S_IINShared
{
    // trick to handle JavaWebStart's cache resources
    public S_IINBeans() {}
    final static private S_IINBeans _f_s_iin = new S_IINBeans();
    
    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strResourcePathRelativeSystem =
        "com" +
        MySystem.s_getFileSeparator() +
        "google" +
        MySystem.s_getFileSeparator() +
        "code" +
        MySystem.s_getFileSeparator() +
        "p" +
        MySystem.s_getFileSeparator() +
        "keytooliui" +
        MySystem.s_getFileSeparator() +
        "beans" +
        MySystem.s_getFileSeparator() +
        "images" +
        MySystem.s_getFileSeparator() +
        "";
    
    
    
    final static private String _f_s_strResourcePathRelativeJar =
        "com" +
        "/" +
         "google" +
        "/" +
        "code" +
        "/" +
        "p" +
        "/" +
        "keytooliui" +
        "/" +
        "beans" +
        "/" +
        "images" +
        "/" +
        "";
    
    
    // -------------
    // STATIC PUBLIC
    
    static public javax.swing.ImageIcon s_get(String strFileName)
    {
        ClassLoader cld = _f_s_iin.getClass().getClassLoader();
        return S_IINAbstract._s_get_(cld, strFileName, _f_s_strResourcePathRelativeSystem, _f_s_strResourcePathRelativeJar);
    }
    
}