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
    . PullDataSourceJarYesAbs
    . PullDataSourceJarNoOgg
   
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.*;
import javax.media.protocol.*;
import java.io.*;
 
abstract public class PullDataSourceJarAbs extends PullDataSource 
{
    // ------
    // PUBLIC
    
    protected void _setLocator_(
        MediaLocator mlr,
        _OPullStreamJarAbs_ objStream) 
        throws IllegalArgumentException
    {
        String strMethod = "_setLocator_(mlr, objStream)";
        
        super.setLocator (mlr);
        
        try 
        {
            _createStreamSource(objStream);
        } 
        
        catch (IOException excIO) 
        {
            excIO.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "excIO caught");
        }
    }


    // -- spec'ed by PullDataSource
    public PullSourceStream[] getStreams() 
    {
        return this._psssStreamSource;
    }

    // -- spec'ed by DataSource
    public void connect() throws IOException
    {
    }

    public void disconnect() 
    {
        if (this._psmInput != null) 
        {
            this._psmInput.close();
            this._psmInput = null;
        }
    }


    public String getContentType() { return this._strContentType; }

    public void start() 
    {
        // nothing to do
    }

    public void stop() 
    {
        // nothing to do
    }

    // -- spec'ed by Duration
    public Time getDuration () 
    {
        // TODO: real implementation?
        return DataSource.DURATION_UNKNOWN;
    }

    // -- spec'ed by Controls
    public Object getControl(String controlName) 
    {
        return null;
    }

    public Object[] getControls() 
    {
        return _OPullStreamJarAbs_._s_objsEmpty_;
    }
    
    // ---------
    // PROTECTED
    
    protected PullDataSourceJarAbs(
        MediaLocator mlr,
        String strContentType
        )
        throws IllegalArgumentException, IOException 
    {
        super();
        
        this._strContentType = strContentType;
        setLocator(mlr);
    }
    
    // -------
    // PRIVATE

    private String _strContentType = null;
    private _OPullStreamJarAbs_ _psmInput;
    private PullSourceStream[] _psssStreamSource;

    private void _createStreamSource(
        _OPullStreamJarAbs_ objStream
        ) 
        throws IOException 
    {
        this._psmInput = objStream;
        this._psssStreamSource = new PullSourceStream[1];
        this._psssStreamSource[0] = this._psmInput;
    }
}