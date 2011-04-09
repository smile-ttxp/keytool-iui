/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.text;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.thread.*;

import javax.swing.*;

public final class MyStringSelection extends Object
    implements Runnable
{
    // ------
    // PUBLIC
    
    public int getStart() { return this._intStart; }
    public int getEnd() { return this._intEnd; }
    
    public void setEditorPane(JEditorPane epn) { this._epn = epn; }
    
    // MODIF 19/1/00
    public void start()
    {
        String strMethod = "start()";
        
        if (this._epn == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil this._epn");
        }
        
        stop();
        
        if (this._thr == null) 
        {
		    this._thr = new MyThreadShared("MyStringSelection", this);
		    this._thr.setPriority(java.lang.Thread.MAX_PRIORITY);
            this._thr.start(); 
        }
    }
    
    // MODIF 19/1/00
    
    public synchronized void stop()
    {
        String strMethod = "stop()";
        
        if (this._thr != null)
        {
            this._thr = null;
            /**
            try
            {
                this._thr.interrupt();
            }
            
            catch(SecurityException excSecurity)
            {
                excSecurity.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excSecurity caught");
            }
            
            this._thr = null;
            
            try
            {
                notifyAll();
            }
            
            catch(IllegalMonitorStateException excIllegalMonitorState)
            {
                excIllegalMonitorState.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excIllegalMonitorState caught");
            }      
            **/
        }
    }
    
    public void run()
    {
        if (this._epn == null)
            return;
            
        this._epn.select(this._intStart, this._intEnd); 
    }
    
    public MyStringSelection(int intStart, int intEnd)
    {
        this._intStart = intStart;
        this._intEnd = intEnd;
    }
    
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._intEnd <= this._intStart)
        {
            MySystem.s_printOutError(this, strMethod, "this._intStart=" + this._intStart + ", this._intEnd=" + this._intEnd + ", wrong values");
            return false;
        }
        
        return true;
    }
    
    
    
    public void destroy()
    {
        stop();
    }

    
    // -------
    // PRIVATE
    
    private JEditorPane _epn = null;
    private Thread _thr = null;
    
    private int _intStart = 0;
    private int _intEnd = 0;
}