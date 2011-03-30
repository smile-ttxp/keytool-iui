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

    known subclasses:
    . PullDataSourceJarYesOgg
    . PullDataSourceJarYesMpeg !! NOT WORKING !!
    . PullDataSourceJarYesAvi !! NOT WORKING !!
    . PullDataSourceJarYesMov !! NOT WORKING !!
   
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.*;

import java.io.*;

 
abstract public class PullDataSourceJarYesAbs extends PullDataSourceJarAbs 
{
    // ------
    // PUBLIC
    
    public void setLocator(MediaLocator mlr) 
        throws IllegalArgumentException
    {
        String strMethod = "setLocator(mlr)";
        
        if (mlr == null)
            MySystem.s_printOutExit(this, strMethod, "nil mlr");
        
        try
        {
            super._setLocator_(mlr, new _OPullStreamJarYes_(mlr.getURL()));
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excIO caught");
        }
    }

    // ---------
    // PROTECTED
    
    protected PullDataSourceJarYesAbs(
        MediaLocator mlr,
        String strContentType
        )
        throws IllegalArgumentException, IOException 
    {
        super(mlr, strContentType);
        
        if (! mlr.getURL().getProtocol().toLowerCase().equals("jar"))
            throw new IOException("url not starting with\"jar\", mlr.getURL().toString()=" + mlr.getURL().toString());
    }
}
