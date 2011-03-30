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

/*
    known subclasses:
    . _OPullStreamJarYes_
    . _OPullStreamJarNo_
*/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.PullSourceStream;
import javax.media.protocol.Seekable;
import javax.media.protocol.SourceStream;

import java.net.*;
import java.io.*;

abstract public class _OPullStreamJarAbs_ extends Object implements 
    PullSourceStream, 
    Seekable
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strContentDescriptor = "unknown";
    
    // ----------------
    // STATIC PROTECTED

    static protected Object[] _s_objsEmpty_ = {};
    
    
    // ------
    // PUBLIC
        
    public void open() throws IOException
    {
        //URLConnection conn = (URLConnection) this._url.openConnection();
        this._ism = this._url.openConnection().getInputStream();
        this._lngTellPoint = 0;
    }

    public void close() 
    {
        String strMethod = "close()";
        
        try 
        {
            this._ism.close();
        } 
        catch (IOException excIO) 
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught, ignoring");
        }
    }

    public void thoroughSkip (long skipCount) 
        throws IOException 
    {
        long totalSkipped = 0;
        while (totalSkipped < skipCount) {
            long skipped =
                this._ism.skip (skipCount-totalSkipped);
            totalSkipped += skipped;
            this._lngTellPoint += skipped;
        }
    }

    // spec'ed by PullSourceStream

    public int read (byte[] buf, int off, int length) 
        throws IOException 
    {
        int bytesRead = this._ism.read(buf, off, length);
        this._lngTellPoint += bytesRead;
        return bytesRead;
    }

    public boolean willReadBlock() 
    {
        try 
        {
            return (this._ism.available() > 0);
        } 
        
        catch (IOException ioe) 
        {
            return true;
        }
    }

    // spec'ed by SourceStream

    public long getContentLength() {
        // TODO: implement - maybe can use jar.Attributes
        return SourceStream.LENGTH_UNKNOWN;
    }

    public boolean endOfStream() 
    {
        try 
        {
            return (this._ism.available() == -1);
        } 
        
        catch (IOException ioe) 
        {
            return true;
        }
    }

    public ContentDescriptor getContentDescriptor() 
    {
        return this._cdrUnknown;
    }

    // spec'ed by Controls
    public Object getControl (String controlType) 
    {
        return null;
    }

    public Object[] getControls() 
    {
        return _OPullStreamJarAbs_._s_objsEmpty_;
    }


    // spec'ed by Seekable
    public boolean isRandomAccess() 
    {
        return true;
    }

    public long seek(long lngPosition) 
    {
        String strMethod = "seek(lngPosition)";
        
        // approach -- if seek is further in than tell,
        // then just skip bytes to get there
        // else close, reopen, and skip to lngPosition
        try 
        {
            if (lngPosition > this._lngTellPoint) 
            {
                thoroughSkip (lngPosition - this._lngTellPoint);
            } 
            
            else 
            {
                close();
                open();
                // now skip to this lngPosition
                thoroughSkip (lngPosition);
            }
            
            return this._lngTellPoint;
        } 
        
        catch (IOException excIO) 
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIO caught, returning 0");
            return 0; // bogus... who even knows where we are now?
        }
    }
        
    public long tell() { return this._lngTellPoint; }
        
    // ---------
    // protected
        
    protected _OPullStreamJarAbs_(URL url)
        throws IOException 
    {
        this._url = url;
        
        this._cdrUnknown = new ContentDescriptor(_OPullStreamJarAbs_._f_s_strContentDescriptor);
        
        open();
    }
        
    // -------
    // private

    private InputStream _ism;
    private ContentDescriptor _cdrUnknown = null;
            
    private long _lngTellPoint;
    
    private URL _url = null;
}