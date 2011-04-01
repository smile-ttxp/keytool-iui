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

import com.google.code.p.keytooliui.shared.lang.*;

public class S_EPEShared extends S_EPEAbs
{
    // trick to handle JavaWebStart's cache resources
    public S_EPEShared() {}
    final static private S_EPEShared _f_s_epe = new S_EPEShared();
    
    
    // --------------------------
    // FINAL STATIC PUBLIC STRING
    
    // display/res_assoc2_html
    /*final static public String f_s_strFileImage16 = "warp16.gif";
    final static public String f_s_strFileSndfx16 = "volume16.gif";
    final static public String f_s_strFileCSS16 = "css16.gif"; // tempo
    
              
    // contents
    final static public String f_s_strFilePage16 = "page16.gif";
    final static public String f_s_strFileHTML16 = "html16.gif"; // "_fhl16_.gif";
    final static public String f_s_strFileRTF16 = "rtf16.gif";
    final static public String f_s_strFilePDF16 = "pdf15x16.gif";
    final static public String f_s_strFileAudio16 = "audio16.gif"; // !!! DUMMY ICON
    final static public String f_s_strFileVideo16 = "video16.gif"; // !!! DUMMY ICON
    
    
    // ----
                                          
    final static public String f_s_strFileProjGenTpl16 = "jar16.gif";
    final static public String f_s_strFileProjGenDoc16 = "jar16.gif";
    
    final static public String f_s_strFileTemplate16 = "_prt16_.gif";
    final static public String f_s_strFileProjRcr16 = "jar16.gif";
    final static public String f_s_strFileReaderSec16 = "jar16.gif";
    final static public String f_s_strFileProjJhr16 = "jar16.gif";
    
    
    final static public String f_s_strAppliJhr16 = "application16.gif";
    final static public String f_s_strAppliOhr16 = "application16.gif";
    final static public String f_s_strAppliRcr16 = "application16.gif";
    final static public String f_s_strAppliXlb16 = "application16.gif";
    final static public String f_s_strAppliTpb16 = "application16.gif";
    final static public String f_s_strAppliUIKtl16 = "application16.gif";
    final static public String f_s_strAppliUIJsr16 = "application16.gif";
    final static public String f_s_strAppliDin16 = "application16.gif";
    
    final static public String f_s_strWait28_32 = "hglass28_32.gif";*/
    
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
        "shared" +
        MySystem.s_getFileSeparator() +
        "htmls" +
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
        "shared" +
        "/" +
        "htmls" +
        "/" +
        "";
    
    // -------------
    // STATIC PUBLIC
    
    static public javax.swing.JEditorPane s_get(String strFileName)
    {
        ClassLoader cld = _f_s_epe.getClass().getClassLoader();
       
        return S_EPEAbs._s_get_(cld, strFileName, _f_s_strResourcePathRelativeSystem, _f_s_strResourcePathRelativeJar);
    }
}
