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
 
 
 package com.google.code.p.keytooliui.shared.awt.image;

import com.google.code.p.keytooliui.shared.lang.*;

public class S_Image
{
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.awt.image.S_Image.";
    
    
    // -------------
    // STATIC PUBLIC
    
    
    static public java.awt.Image s_get(String strFileName)
    {
        String f_strMethod = _f_s_strClass + "s_get(strFileName)";
        
        if (strFileName == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil strFileName");
            return null;
        }
        
        
        javax.swing.ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strFileName);
        
        if (iin == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil iin:" + strFileName);
            return null;
        }
        
        java.awt.Image img = iin.getImage();
        
        if (img == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil img: " + strFileName);
            return null;
        }
        
        return img;
    }
}