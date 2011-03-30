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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;
 
 
 /**
 
    known subclasses:
    . shared_read: PRunScrollbarAbs
    . shared_read: PRunScrollMainAbs
    . shared_read: PRunScrollCntAbs
    . intro2d:     PRunSurfaceI2d
    
    
    a panel which extends Runnable
    
    
 
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.lang.thread.*;
 
 import javax.swing.*;
 
 
 abstract public class PRunAbs extends JPanel implements
    Runnable
 {
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void run();
  
    // ------
    // PUBLIC
    
    public boolean init()
    {
        return true;
    }
    
    public void destroy()
    {
        stop();
    }
    
    public void start()
    {
        //String strMethod = "start()";
        
        //MySystem.s_printOutTrace(this, strMethod, "..., this._strThreadName=" + this._strThreadName);
        
        if (this._thr_ != null)
        {
            //MySystem.s_printOutTrace(this, strMethod, "this._thr_ != null, this._strThreadName=" + this._strThreadName);
            return;
        }
        
        
		this._thr_ = new MyThreadShared(this._strThreadName, this); 
		this._thr_.setPriority(this._intThreadPriority);
        this._thr_.start(); 
    }
    
    
    public synchronized void stop()
    {
        //String strMethod = "stop()";
        
        //MySystem.s_printOutTrace(this, strMethod, "..., this._strThreadName=" + this._strThreadName);
        
        if (this._thr_ == null)
        {
            //MySystem.s_printOutTrace(this, strMethod, "this._thr_ == null, this._strThreadName=" + this._strThreadName);
            return;
        }
        
        this._thr_ = null;
        
        /**
       
        try
        {
            this._thr_.interrupt();
        }
            
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "exc caught, returning");
            this._thr_ = null;
            return;
        }
            
        this._thr_ = null;
            
        try
        {
            notifyAll();
        }
            
        catch(IllegalMonitorStateException excIllegalMonitorState)
        {
            excIllegalMonitorState.printStackTrace();
            MySystem.s_printOutWarning(this, strMethod, "excIllegalMonitorState caught, returning");
           return; // not required!
        }
        
        **/
    }
    
    
    // ---------
    // PROTECTED
    
    protected Thread _thr_ = null;
    
    protected PRunAbs(String strThreadName, int intThreadPriority)
    {
        this._strThreadName = strThreadName;
        this._intThreadPriority = intThreadPriority;
    }
    
    // -------
    // PRIVATE
    
    private String _strThreadName = null;
    private int _intThreadPriority = 0;
 }