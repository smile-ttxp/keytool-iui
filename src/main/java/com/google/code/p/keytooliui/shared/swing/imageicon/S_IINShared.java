/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
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
 
package com.google.code.p.keytooliui.shared.swing.imageicon;

import java.io.File;

public class S_IINShared extends S_IINAbstract
{
    // trick to handle JavaWebStart's cache resources
    public S_IINShared() {}
    private static final S_IINShared _f_s_iin = new S_IINShared();
    
    
    // --------------------------
    // PUBLIC STATIC FINAL STRING
    
    // display/res_assoc2_html
    public static final String f_s_strFileImage16 = "warp16.gif";
    public static final String f_s_strFileSndfx16 = "volume16.gif";
    public static final String f_s_strFileCSS16 = "css16.gif"; // tempo
    
              
    // contents
    public static final String f_s_strFilePage16 = "page16.gif";
    public static final String f_s_strFileHTML16 = "html16.gif"; // "_fhl16_.gif";
    public static final String f_s_strFileRTF16 = "rtf16.gif";
    public static final String f_s_strFilePDF16 = "pdf15x16.gif";
    
    
    // ----
                                          
    public static final String f_s_strFileProjGenTpl16 = "jar16.gif";
    public static final String f_s_strFileProjGenDoc16 = "jar16.gif";
    
    public static final String f_s_strFileTemplate16 = "_prt16_.gif";
    public static final String f_s_strFileProjRcr16 = "jar16.gif";
    public static final String f_s_strFileReaderSec16 = "jar16.gif";
    public static final String f_s_strFileProjJhr16 = "jar16.gif";
    
    
    public static final String f_s_strAppliXlb16 = "application16.gif";
    public static final String f_s_strAppliTpb16 = "application16.gif";
    public static final String f_s_strAppliUIKtl16 = "application16.gif";
    public static final String f_s_strAppliUIJsr16 = "application16.gif";
    public static final String f_s_strAppliDin16 = "application16.gif";
    
    public static final String f_s_strWait28_32 = "hglass28_32.gif";
    
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strResourcePathRelativeSystem =
        "com" +
        File.separator +
        "google" +
        File.separator +
        "code" +
        File.separator +
        "p" +
        File.separator +
        "keytooliui" +
        File.separator +
        "shared" +
        File.separator +
        "images" +
        File.separator +
        "";
    
    
    private static final String _f_s_strResourcePathRelativeJar = "com/google/code/p/keytooliui/shared/images/";
    
    // -------------
    // PUBLIC STATIC
    
    public static javax.swing.ImageIcon s_get(String strFileName)
    {
        ClassLoader cld = _f_s_iin.getClass().getClassLoader();
       
        return S_IINAbstract._s_get_(cld, strFileName, _f_s_strResourcePathRelativeSystem, _f_s_strResourcePathRelativeJar);
    }
}
