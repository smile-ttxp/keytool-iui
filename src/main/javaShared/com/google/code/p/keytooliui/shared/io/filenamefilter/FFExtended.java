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
 
 
 package com.google.code.p.keytooliui.shared.io.filenamefilter;

import java.io.*;
import java.util.*;

public class FFExtended implements FilenameFilter
{
    
    
    
    /** ######
        PUBLIC
        ###### **/
    
    

    
    public void init()
    {
    }
    
    public void destroy()
    {
    }
    
    public FFExtended(String[] strsExtension)
    {   
        this._hstFilters = new Hashtable<String, FFExtended>(strsExtension.length);
	    
	    for (int i = 0; i < strsExtension.length; i++)
	    {
	        // add filters one by one
	        _addExtension(strsExtension[i]);
	    }
    }
    
    // overriding the method of the superclass!
    public boolean accept(File fleDir, String strName)
    {
        if (fleDir==null || strName==null)
            return false;
        
        //String extension = _getExtension(strName);
        String strExtension = com.google.code.p.keytooliui.shared.lang.string.S_StringShared.s_getExtension(strName);
        
        
        
        if(strExtension == null)
            return false;
	        
	     
	     
	    if (this._hstFilters.get(strExtension.toLowerCase()) != null)
		    return true;
        
        return false;
    }
    
    /** #######
        PRIVATE
        ####### **/
        
    private Hashtable<String, FFExtended> _hstFilters = null;
    
    
    private void _addExtension(String strExtension)
    {
	    if(this._hstFilters == null)
	    {
	        this._hstFilters = new Hashtable<String, FFExtended>(5);
	    }
	    
	    this._hstFilters.put(strExtension.toLowerCase(), this);
    }
}