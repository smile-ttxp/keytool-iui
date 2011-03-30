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
    based on: JarEntryDataSource.java - (c) 2002 Chris Adamson, invalidname@mac.com
    http://homepage.mac.com/invalidname/spmovie/


    avoiding to add in [jre.home]\lib\content-types.properties, the following:
    
    **** BEG PART OF CONTENT-TYPES.PROPERTIES ***
    application/ogg: \
	    description=Ogg Vorbis Format File;\
	    file_extensions=.ogg;\
	    icon=aiff

    application/x-ogg: \
	    description=Ogg Vorbis Format File;\
	    file_extensions=.ogg;\
	    icon=aiff
	**** END PART OF CONTENT-TYPES.PROPERTIES ***
	(note about the above: don't pay attention to "icon=aiff")
    
    
    IMPORTANT: url should point to a file ending with ".ogg"
**/

import javax.media.*;
import javax.media.protocol.*;
import java.io.*;
import java.net.*;
 
final public class PullDataSourceJarNoOgg extends PullDataSourceJarNoAbs 
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    
    
    final static public String f_s_strContentType = "application.x_ogg";
    
    
    // tempo location
    final static public String[] f_s_strsExtension =
    {
        "ogg"
    };
    
    // -------------
    // STATIC PUBLIC
    
    // tempo location
    static public boolean s_isValidExtension(URL url)
    {
        if (url == null)
            return false;
            
        String str = url.toString().toLowerCase();
            
        for (int i=0; i<PullDataSourceJarNoOgg.f_s_strsExtension.length; i++)
        {
            if (str.endsWith(PullDataSourceJarNoOgg.f_s_strsExtension[i]))
                return true;
        }
        
        return false;
    }
    
    // ------
    // PUBLIC
    
    public PullDataSourceJarNoOgg(MediaLocator mlr)
        throws IllegalArgumentException, IOException 
    {
        super(mlr, PullDataSourceJarNoOgg.f_s_strContentType);
        
        if (! PullDataSourceJarNoOgg.s_isValidExtension(mlr.getURL()))
            throw new IOException("wrong url extension, mlr.getURL().toString()=" + mlr.getURL().toString());
    }
}
