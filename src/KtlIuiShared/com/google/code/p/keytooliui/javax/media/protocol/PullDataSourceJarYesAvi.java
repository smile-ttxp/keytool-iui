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

package com.google.code.p.keytooliui.javax.media.protocol;

/**
   
**/

import javax.media.*;

import java.io.*;

 
final public class PullDataSourceJarYesAvi extends PullDataSourceJarYesAbs 
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strContentType = "video.x_msvideo";
    
    // ------
    // PUBLIC
    
    public PullDataSourceJarYesAvi(MediaLocator mlr)
        throws IllegalArgumentException, IOException 
    {
        super(mlr, PullDataSourceJarYesAvi._f_s_strContentType);
        
        String strUrl = mlr.getURL().toString().toLowerCase();
        
        if (! strUrl.endsWith(".avi"))
            throw new IOException("url not ending with .avi, strUrl=" + strUrl);
    }
}