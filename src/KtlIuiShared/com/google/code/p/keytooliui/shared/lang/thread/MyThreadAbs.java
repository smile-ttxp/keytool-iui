/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.lang.thread;

/**
    known subclasses:
    . MyThreadShared
    . MyThreadSharedGen
    . MyThreadRdr
    
    
    
    MEMO: thread ID refers to respective thread that extend class MyThreadAbs
    incrementing IDs in this class.
**/

import com.google.code.p.keytooliui.shared.lang.*;

abstract public class MyThreadAbs extends Thread
{
    
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public String f_s_strSeparatorWord = "_"; // 1 underline 
    
    final static public int f_s_intIdMin = 1; // memo: integer size = 2pow16 = 65536
    final static public int f_s_intIdMax = 30000; // memo: integer size = 2pow16 = 65536
    
    // --------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strNamePrefix = "rcreader"; // comes from "com.google.code.p.keytooliui" package
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.lang.thread.MyThreadAbs.";
    
    
    // -------------
    // STATIC PUBLIC
    
    static public void s_interruptThreadsActive()
    {
        String strMethod = _f_s_strClass + "s_interruptThreadsActive()";
        
        Thread[] thrsActive = new Thread[Thread.activeCount()];
        Thread.enumerate(thrsActive);
        
        if (thrsActive.length == 0)
        {
            MySystem.s_printOutTrace(strMethod, "thrsActive.length == 0");
            return;
        }
        
        // --
        
        MySystem.s_printOutTrace(strMethod, "thrsActive.length=" + thrsActive.length);
        
        for (int i=0; i<thrsActive.length; ++i)
        {
            String strNameCur = thrsActive[i].getName();
            System.out.println("    . " + i + ": " + strNameCur);
            
            /*if (strNameCur.toLowerCase().startsWith("awt-eventqueue"))
            {
                System.out.println("       " + "interrupting: " + strNameCur);
                thrsActive[i].interrupt();
                continue;
            }*/
            
            if (strNameCur.startsWith(f_s_strSeparatorWord + 
                "com" +
                f_s_strSeparatorWord + 
                _f_s_strNamePrefix + f_s_strSeparatorWord))
            {
                if (strNameCur.endsWith(f_s_strSeparatorWord))
                {
                    System.out.println("      " + ">> interrupting: " + strNameCur);
                    
                    try
                    {
                        thrsActive[i].interrupt();
                    }
                    
                    catch(Exception exc)
                    {
                        //exc.printStackTrace();
                        MySystem.s_printOutTrace(strMethod, "exc caught, ignoring");
                    }

                    
                    continue;
                }
            }
        }
    }
    
    static public void s_dumpThreadsActive()
    {
        String strMethod = _f_s_strClass + "s_dumpThreadsActive()";
        
        Thread[] thrsActive = new Thread[Thread.activeCount()];
        Thread.enumerate(thrsActive);
        
        if (thrsActive.length == 0)
        {
            MySystem.s_printOutTrace(strMethod, "thrsActive.length == 0");
            return;
        }
        
        // --
        
        MySystem.s_printOutTrace(strMethod, "thrsActive.length=" + thrsActive.length);
        
        for (int i=0; i<thrsActive.length; ++i)
        {
            String strNameCur = thrsActive[i].getName();
            System.out.println("    . " + i + ": " + strNameCur);
        }
    }
    
    // --------------
    // STATIC PRIVATE
    
     static private int _s_intThreadID = f_s_intIdMin - 1; // = "0"
    
    
    // ------
    // PROTECTED
    
    /*
        trick to bypass final method "setName(...)"
    */
    protected MyThreadAbs(String strNameSuffix, Runnable run)
    {
        super(run);
        
        String strMethod = "MyThreadAbs(...)";
        
         _s_intThreadID ++;
        
        if (_s_intThreadID > f_s_intIdMax)
        {
            MySystem.s_printOutTrace(this, strMethod, "_s_intThreadID > f_s_intIdMax, resetting");
            _s_intThreadID = f_s_intIdMin;
        }
               
        super.setName(
            f_s_strSeparatorWord + 
            "org" +
            f_s_strSeparatorWord + 
            _f_s_strNamePrefix + 
            f_s_strSeparatorWord +
            strNameSuffix +
            "-" + _s_intThreadID +
            f_s_strSeparatorWord);
    }
    
   
}